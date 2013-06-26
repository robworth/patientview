/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */
package org.patientview.service.impl;

import org.patientview.ibd.model.Allergy;
import org.patientview.ibd.model.MyIbd;
import org.patientview.ibd.model.Procedure;
import org.patientview.patientview.TestResultDateRange;
import org.patientview.patientview.XmlImportUtils;
import org.patientview.patientview.logging.AddLog;
import org.patientview.patientview.model.Centre;
import org.patientview.patientview.model.Diagnosis;
import org.patientview.patientview.model.Diagnostic;
import org.patientview.patientview.model.Letter;
import org.patientview.patientview.model.Medicine;
import org.patientview.patientview.model.Patient;
import org.patientview.patientview.model.TestResult;
import org.patientview.patientview.parser.ResultParser;
import org.patientview.patientview.user.UserUtils;
import org.patientview.patientview.utils.TimestampUtils;
import org.patientview.service.ImportManager;
import org.patientview.utils.LegacySpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.servlet.ServletContext;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 */
@Service(value = "importManager")
@Transactional(propagation = Propagation.REQUIRED)
public class ImportManagerImpl implements ImportManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImportManagerImpl.class);

    @Override
    public void update(ServletContext context, File xmlFile) {
        File xsdFile;
        try {
            xsdFile = LegacySpringUtils.getSpringApplicationContextBean().getApplicationContext()
                    .getResource("classpath:importer/pv_schema_2.0.xsd").getFile();
        } catch (IOException e) {
            throw new IllegalStateException("Cannot find pv_schema_2.0.xsd to perform ImportManagerImpl.update()");
        }

        update(context, xmlFile, xsdFile);
    }

    @Override
    public void update(ServletContext context, File xmlFile, File xsdFile) {
        /**
         * Check if the file is empty or not. If a file is completely empty, this probably means that the encryption
         * hasn't worked. Send a mail to RPV admin, and skip validate and process
         */
        if (xmlFile.length() == 0) {
            AddLog.addLog(AddLog.ACTOR_SYSTEM, AddLog.PATIENT_DATA_FAIL, "",
                    XmlImportUtils.extractFromXMLFileNameNhsno(xmlFile.getName()),
                    XmlImportUtils.extractFromXMLFileNameUnitcode(xmlFile.getName()), xmlFile.getName());
            XmlImportUtils.sendEmptyFileEmailToUnitAdmin(xmlFile, context);
        } else {
            validateAndProcess(context, xmlFile, xsdFile);
        }

        // always move the file, so it is not processed multiple times
        renameDirectory(context, xmlFile);
    }

    private void validateAndProcess(ServletContext context, File xmlFile, File xsdFile) {
        // Turn this off without removing the code and it getting lost in ether.
        // The units sending the data are not honouring the xsd, so no point validating yet.
        final boolean whenWeDecideToValidateFiles = false;

        if (whenWeDecideToValidateFiles) {
            /**
             * Check the XML file against XSD schema
             */
            List<SAXParseException> exceptions = getXMLParseExceptions(xmlFile, xsdFile);

            // if there are any exceptions, log them and send an email
            if (exceptions.size() > 0) {
                // log
                AddLog.addLog(AddLog.ACTOR_SYSTEM, AddLog.PATIENT_DATA_CORRUPT, "",
                        XmlImportUtils.extractFromXMLFileNameNhsno(xmlFile.getName()),
                        XmlImportUtils.extractFromXMLFileNameUnitcode(xmlFile.getName()), xmlFile.getName());

                // send email, then continue importing
                XmlImportUtils.sendXMLValidationErrors(xmlFile, xsdFile, exceptions, context);
            }
        }

        // always process regardless of validation state
        process(context, xmlFile);
    }

    private void process(ServletContext context, File xmlFile) {
        try {
            ResultParser parser = new ResultParser();
            parser.parseResults(context, xmlFile);

            if ("Remove".equalsIgnoreCase(parser.getFlag()) || "Dead".equalsIgnoreCase(parser.getFlag())
                    || "Died".equalsIgnoreCase(parser.getFlag()) || "Lost".equalsIgnoreCase(parser.getFlag())
                    || "Suspend".equalsIgnoreCase(parser.getFlag())) {
                removePatientFromSystem(parser);
                AddLog.addLog(AddLog.ACTOR_SYSTEM, AddLog.PATIENT_DATA_REMOVE, "", parser.getPatient().getNhsno(),
                        parser.getPatient().getCentreCode(), xmlFile.getName());
            } else {
                updatePatientData(parser);
                AddLog.addLog(AddLog.ACTOR_SYSTEM, AddLog.PATIENT_DATA_FOLLOWUP, "", parser.getPatient().getNhsno(),
                        parser.getPatient().getCentreCode(), xmlFile.getName());
            }
            //xmlFile.delete();
        } catch (Exception e) {

            // these exceptions can occur because of corrupt/invalid data in xml file
            LOGGER.error("Importer failed to import file {} {}", xmlFile, e.getMessage());

            AddLog.addLog(AddLog.ACTOR_SYSTEM, AddLog.PATIENT_DATA_FAIL, "",
                    XmlImportUtils.extractFromXMLFileNameNhsno(xmlFile.getName()),
                    XmlImportUtils.extractFromXMLFileNameUnitcode(xmlFile.getName()),
                    xmlFile.getName() + " : " + XmlImportUtils.extractErrorsFromException(e));

            XmlImportUtils.sendEmailOfExpectionStackTraceToUnitAdmin(e, xmlFile, context);
        }
    }

    protected void renameDirectory(ServletContext context, File xmlFile) {
        String directory = context.getInitParameter("xml.patient.data.load.directory");
        xmlFile.renameTo(new File(directory, xmlFile.getName()));
    }

    private void removePatientFromSystem(ResultParser parser) {
        String nhsno = parser.getData("nhsno");
        String unitcode = parser.getData("centrecode");
        UserUtils.removePatientFromSystem(nhsno, unitcode);
    }

    private void updatePatientData(ResultParser parser) {
        updatePatientDetails(parser.getPatient());
        updateCentreDetails(parser.getCentre());
        deleteDateRanges(parser.getDateRanges());
        insertResults(parser.getTestResults());
        deleteLetters(parser.getLetters());
        insertLetters(parser.getLetters());
        deleteOtherDiagnoses(parser.getData("nhsno"), parser.getData("centrecode"));
        insertOtherDiagnoses(parser.getOtherDiagnoses());
        deleteMedicines(parser.getData("nhsno"), parser.getData("centrecode"));
        insertMedicines(parser.getMedicines());
        deleteMyIbd(parser.getData("nhsno"), parser.getData("centrecode"));
        insertMyIbd(parser.getMyIbd());
        deleteDiagnostics(parser.getData("nhsno"), parser.getData("centrecode"));
        insertDiagnostics(parser.getDiagnostics());
        deleteProcedures(parser.getData("nhsno"), parser.getData("centrecode"));
        insertProcedures(parser.getProcedures());
        deleteAllergies(parser.getData("nhsno"), parser.getData("centrecode"));
        insertAllergies(parser.getAllergies());
    }

    private void deleteDiagnostics(String nhsno, String unitcode) {
        LegacySpringUtils.getDiagnosticManager().delete(nhsno, unitcode);
    }

    private void insertDiagnostics(Collection<Diagnostic> diagnostics) {
        for (Iterator iterator = diagnostics.iterator(); iterator.hasNext();) {
            Diagnostic diagnostic = (Diagnostic) iterator.next();
            LegacySpringUtils.getDiagnosticManager().save(diagnostic);
        }
    }

    private void deleteProcedures(String nhsno, String unitcode) {
        LegacySpringUtils.getIbdManager().deleteProcedure(nhsno, unitcode);
    }

    private void insertProcedures(Collection<Procedure> procedures) {
        for (Iterator iterator = procedures.iterator(); iterator.hasNext();) {
            Procedure procedure = (Procedure) iterator.next();
            LegacySpringUtils.getIbdManager().saveProcedure(procedure);
        }
    }

    private void deleteAllergies(String nhsno, String unitcode) {
        LegacySpringUtils.getIbdManager().deleteAllergy(nhsno, unitcode);
    }

    private void insertAllergies(Collection<Allergy> allergies) {
        for (Iterator iterator = allergies.iterator(); iterator.hasNext();) {
            Allergy allergy = (Allergy) iterator.next();
            LegacySpringUtils.getIbdManager().saveAllergy(allergy);
        }
    }

    private void deleteMyIbd(String nhsno, String unitcode) {
        LegacySpringUtils.getIbdManager().deleteMyIbd(nhsno, unitcode);
    }

    private void insertMyIbd(MyIbd myIbd) {
        if (myIbd != null) {
            LegacySpringUtils.getIbdManager().saveMyIbd(myIbd);
        }
    }

    private void updatePatientDetails(Patient patient) {
        LegacySpringUtils.getPatientManager().delete(patient.getNhsno(), patient.getCentreCode());
        LegacySpringUtils.getPatientManager().save(patient);
    }

    private void updateCentreDetails(Centre centre) {
        LegacySpringUtils.getCentreManager().delete(centre.getCentreCode());
        LegacySpringUtils.getCentreManager().save(centre);
    }

    private void deleteDateRanges(Collection dateRanges) {
        for (Iterator iterator = dateRanges.iterator(); iterator.hasNext();) {
            TestResultDateRange testResultDateRange = (TestResultDateRange) iterator.next();

            Calendar startDate = TimestampUtils.createTimestampStartDay(testResultDateRange.getStartDate());
            Calendar stopDate = TimestampUtils.createTimestampEndDay(testResultDateRange.getStopDate());

            LegacySpringUtils.getTestResultManager().deleteTestResultsWithinTimeRange(testResultDateRange.getNhsNo(),
                    testResultDateRange.getUnitcode(), testResultDateRange.getTestCode(), startDate.getTime(),
                    stopDate.getTime());
        }
    }

    private void insertResults(Collection testResults) {
        for (Iterator iterator = testResults.iterator(); iterator.hasNext();) {
            TestResult testResult = (TestResult) iterator.next();
            LegacySpringUtils.getTestResultManager().save(testResult);
        }
    }

    private void deleteLetters(Collection letters) {
        for (Iterator iterator = letters.iterator(); iterator.hasNext();) {
            Letter letter = (Letter) iterator.next();

            LegacySpringUtils.getLetterManager().delete(letter.getNhsno(), letter.getUnitcode(),
                    letter.getDate().getTime());
        }
    }

    private void insertLetters(Collection letters) {
        for (Iterator iterator = letters.iterator(); iterator.hasNext();) {
            Letter letter = (Letter) iterator.next();
            LegacySpringUtils.getLetterManager().save(letter);
        }
    }

    private void deleteOtherDiagnoses(String nhsno, String unitcode) {
        LegacySpringUtils.getDiagnosisManager().deleteOtherDiagnoses(nhsno, unitcode);
    }

    private void insertOtherDiagnoses(Collection diagnoses) {
        for (Iterator iterator = diagnoses.iterator(); iterator.hasNext();) {
            Diagnosis diagnosis = (Diagnosis) iterator.next();
            LegacySpringUtils.getDiagnosisManager().save(diagnosis);
        }
    }

    private void deleteMedicines(String nhsno, String unitcode) {
        LegacySpringUtils.getMedicineManager().delete(nhsno, unitcode);
    }

    private void insertMedicines(Collection medicines) {
        for (Iterator iterator = medicines.iterator(); iterator.hasNext();) {
            Medicine medicine = (Medicine) iterator.next();
            LegacySpringUtils.getMedicineManager().save(medicine);
        }
    }

    private List<SAXParseException> getXMLParseExceptions(File xml, File xsd) {
        final List<SAXParseException> exceptions = new LinkedList<SAXParseException>();

        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(xsd));
            Validator validator = schema.newValidator();
            validator.setErrorHandler(new ErrorHandler() {
                @Override
                public void warning(SAXParseException exception) throws SAXException {
                    exceptions.add(exception);
                }

                @Override
                public void fatalError(SAXParseException exception) throws SAXException {
                    exceptions.add(exception);
                }

                @Override
                public void error(SAXParseException exception) throws SAXException {
                    exceptions.add(exception);
                }
            });

            StreamSource xmlFile = new StreamSource(xml);
            validator.validate(xmlFile);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return exceptions;
    }
}
