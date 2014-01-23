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

import com.Ostermiller.util.CSVParser;
import org.patientview.model.Patient;
import org.patientview.patientview.logging.AddLog;
import org.patientview.patientview.model.Comment;
import org.patientview.patientview.model.TestResult;
import org.patientview.patientview.model.UktStatus;
import org.patientview.model.Unit;
import org.patientview.patientview.unit.UnitUtils;
import org.patientview.patientview.utils.TimestampUtils;
import org.patientview.repository.UktStatusDao;
import org.patientview.repository.UnitDao;
import org.patientview.service.SecurityUserManager;
import org.patientview.service.UKTransplantManager;
import org.patientview.utils.LegacySpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.inject.Inject;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 */
@Service(value = "uKTransplantManager")
public class UKTransplantManagerImpl implements UKTransplantManager {

    @Inject
    private UktStatusDao uktStatusDao;

    @Inject
    private UnitDao unitDao;

    @Inject
    private SecurityUserManager securityUserManager;

    @Value("${dataout.directory}")
    private String dataOutDirectory;

    @Value("${dataout.directory.thirdpart}")
    private String dataOutDirThirdPart;

    private static final Logger LOGGER = LoggerFactory.getLogger(UKTransplantManagerImpl.class);

    @Override
    public UktStatus getUktStatus(String nhsno) {
        return uktStatusDao.get(nhsno);
    }

    @Override
    public void importUktStatusData(File file) throws IOException {

        // delete all existing data - this shows that it doesn't make sense to run the import job for multiple files
        deleteAll();

        // import data from file
        updateUktData(file);

        // delete the file
        deleteFile(file);

        // log a success
       AddLog.addLog(AddLog.ACTOR_SYSTEM, AddLog.UKT_DATA_REPLACE, "", "", "", file.getName());
    }

    public void deleteFile(File file) {
        if (!file.delete()) {
            LOGGER.error("Failed to delete uktfile: {}", file.getName());
        }
    }

    private void updateUktData(File uktFile) throws IOException {
        CSVParser uktParser = new CSVParser(new FileReader(uktFile));

        uktParser.changeDelimiter('|');
        String[][] uktValues = uktParser.getAllValues();
        for (int i = 0; i < uktValues.length; i++) {
            UktStatus status = new UktStatus(uktValues[i][0].trim(),
                    uktValues[i][1].trim(), uktValues[i][2].trim());

            save(status);
        }
        uktParser.close();
    }

    @Override
    public void save(UktStatus uktStatus) {
        uktStatusDao.save(uktStatus);
    }

    @Override
    public void deleteAll() {
        uktStatusDao.deleteAll();
    }

    @Override
    public void exportPatientData() throws Exception {
        List<Unit> units = returnAllTheUnits();

        for (Unit unit : units) {
            List<Patient> patients = LegacySpringUtils.getPatientManager().get(unit.getUnitcode());

            for (Patient patient : patients) {
                Document patientDataOutXmlDoc = makePatientDataOutXml(patient);

                if (!(null == patientDataOutXmlDoc)) {
                    writeDataOutFile(patientDataOutXmlDoc, patient, unit);
                }
            }
        }
    }

    private List<Unit> returnAllTheUnits() {

        List<Unit> units = null;
        units = unitDao.getUnitsWithUser(securityUserManager.getLoggedInSpecialty());
        return units;
    }

    private Document makePatientDataOutXml(Patient patient) throws Exception {
        Document doc = null;

        List<TestResult> testResults = LegacySpringUtils.getTestResultManager().get(
                patient.getNhsno(), UnitUtils.PATIENT_ENTERS_UNITCODE);
        List<Comment> comments = LegacySpringUtils.getCommentManager().get(patient.getNhsno());

        if (testResults.isEmpty() && comments.isEmpty()) {
            return doc;
        }

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("patientview");
        doc.appendChild(rootElement);

        Element dateOfReport = addChildElement(doc, rootElement, "dateofreport", getTimeStampNow());

        Element centreDetails = addChildElement(doc, rootElement, "centredetails");
        Element centreCode = addChildElement(doc, centreDetails, "centrecode", patient.getUnitcode());

        Element patientTag = addChildElement(doc, rootElement, "patient");
        Element personalDetails = addChildElement(doc, patientTag, "personaldetails");
        Element nhsno = addChildElement(doc, personalDetails, "nhsno", patient.getNhsno());
        Element surname = addChildElement(doc, personalDetails, "surname", patient.getSurname());
        Element forename = addChildElement(doc, personalDetails, "forename", patient.getForename());
        Element dateofbirth = addChildElement(doc, personalDetails, "dateofbirth", patient.getDateOfBirthStr());

        Element testDetails = addChildElement(doc, rootElement, "testDetails");

        String testCodeTrack = "";
        Element currentTest = null;
        Element currentDateRange = null;
        String currentDateRangeStop = null;

        for (TestResult testResult : testResults) {

            if (!testCodeTrack.equals(testResult.getTestcode())) {
                currentTest = addChildElement(doc, testDetails, "test");
                if (!(null == currentDateRange)) {
                    currentDateRange.setAttribute("stop", currentDateRangeStop);
                }
                Element dateRange = addChildElement(doc, currentTest, "daterange");
                currentDateRange = dateRange;
                dateRange.setAttribute("start", testResult.getIsoDayDatestamp());
                testCodeTrack = testResult.getTestcode();
                Element testCode = addChildElement(doc, currentTest, "testcode", testResult.getTestcode());
            }

            Element result = addChildElement(doc, currentTest, "result");
            Element dateStamp = addChildElement(doc, result, "datestamp", testResult.getIsoDatestamp());
            Element source = addChildElement(doc, result, "source", testResult.getUnitcode());
            currentDateRangeStop = testResult.getIsoDayDatestamp();
            Element prePost = addChildElement(doc, result, "prepost", testResult.getPrepost());
            Element value = addChildElement(doc, result, "value", testResult.getValue());
        }

        if (!(null == currentDateRange) && !(null == currentDateRangeStop)) {
            currentDateRange.setAttribute("stop", currentDateRangeStop);
        }

        Element commentsTag = addChildElement(doc, rootElement, "comments");

        for (Comment comment : comments) {

            Element commentTag = addChildElement(doc, commentsTag, "comment");
            Element commentDate = addChildElement(doc, commentsTag, "commentdate",
                    comment.getIsoFormattedDatestamp());
            Element commentContent = addChildElement(doc, commentsTag, "commentbody", comment.getBody());
        }

        return doc;
    }

    private Element addChildElement(Document doc, Element parentElement, String elementName) {
        Element childElement = doc.createElement(elementName);
        parentElement.appendChild(childElement);
        return childElement;
    }

    private Element addChildElement(Document doc, Element parentElement, String elementName, String data) {
        if (data == null) {
            data = "";
        }
        Element childElement = addChildElement(doc, parentElement, elementName);
        childElement.appendChild(doc.createTextNode(data));
        return childElement;
    }

    private String getTimeStampNow() {
        Calendar nowCal = GregorianCalendar.getInstance();
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat(
                TimestampUtils.DAY_FORMAT_DASH_BACKWARDS_WITH_T_HOUR_MINUTE_AND_SECOND);
        return dateTimeFormat.format(nowCal.getTime());
    }

    private void writeDataOutFile(Document doc, Patient patient, Unit unit)
            throws ParserConfigurationException, TransformerException, IOException {
        String directory1stPart = dataOutDirectory;
        String directory2ndPart = unit.getUnituser();
        String directory3rdPart = dataOutDirThirdPart;

        String filePath = directory1stPart + File.separator + directory2ndPart + File.separator  + directory3rdPart;
        String fileName = "data_out_" + unit.getUnitcode() + "_" + patient.getNhsno() + ".xml";

        File uktDir = new File(filePath);
        if (!uktDir.exists()) {
            uktDir.mkdirs();
        }

        File uktExportFile = new File(uktDir, fileName);
        if (!uktExportFile.exists()) {
            uktExportFile.createNewFile();
        }

        PrintWriter printWriter = new PrintWriter(new FileOutputStream(uktExportFile));

        //write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(printWriter);
        transformer.transform(source, result);
        printWriter.close();
    }
}
