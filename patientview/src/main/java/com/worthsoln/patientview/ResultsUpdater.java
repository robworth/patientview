package com.worthsoln.patientview;

import com.worthsoln.database.DatabaseDAO;
import com.worthsoln.database.DatabaseUpdateQuery;
import com.worthsoln.patientview.model.Diagnosis;
import com.worthsoln.patientview.model.Letter;
import com.worthsoln.patientview.logging.AddLog;
import com.worthsoln.patientview.model.Medicine;
import com.worthsoln.patientview.model.Patient;
import com.worthsoln.patientview.parser.ResultParser;
import com.worthsoln.patientview.user.UserUtils;
import com.worthsoln.patientview.utils.TimestampUtils;
import com.worthsoln.utils.LegacySpringUtils;

import javax.servlet.ServletContext;
import java.io.File;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

public class ResultsUpdater {

    private DatabaseDAO dao;

    public ResultsUpdater(DatabaseDAO dao) {
        this.dao = dao;
    }

    public void update(ServletContext context, File xmlFile) {

        try {
            ResultParser parser = new ResultParser();
            parser.parseResults(context, xmlFile);

            if ("Remove".equalsIgnoreCase(parser.getFlag()) || "Dead".equalsIgnoreCase(parser.getFlag()) ||
                    "Died".equalsIgnoreCase(parser.getFlag()) || "Lost".equalsIgnoreCase(parser.getFlag()) ||
                    "Suspend".equalsIgnoreCase(parser.getFlag()) ) {
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
            e.printStackTrace();
            AddLog.addLog(AddLog.ACTOR_SYSTEM, AddLog.PATIENT_DATA_FAIL, "",
                    XmlImportUtils.extractFromXMLFileNameNhsno(xmlFile.getName()), XmlImportUtils.extractFromXMLFileNameUnitcode(xmlFile.getName()),
                    xmlFile.getName() + " : " +XmlImportUtils.extractStringFromStackTrace(e));
            XmlImportUtils.sendEmailOfExpectionStackTraceToUnitAdmin(e, xmlFile, context);
        }
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
    }

    private void updatePatientDetails(Patient patient) {
        PatientDao patientDao = new PatientDao(patient);
        dao.deleteItem(patientDao);
        dao.insertItem(patientDao);
    }

    private void updateCentreDetails(Centre centre) {
        CentreDao centreDao = new CentreDao(centre);
        dao.deleteItem(centreDao);
        dao.insertItem(centreDao);
    }

    private void deleteDateRanges(Collection dateRanges) {
        String dateRangeDeleteSql = "DELETE FROM testresult WHERE nhsno = ? AND unitcode = ? " +
                " AND testcode = ? AND datestamp > ? AND datestamp < ?";
        for (Iterator iterator = dateRanges.iterator(); iterator.hasNext();) {
            TestResultDateRange testResultDateRange = (TestResultDateRange) iterator.next();
            Calendar startDate = TimestampUtils.createTimestamp(testResultDateRange.getStartDate() + "T00:00");
            startDate.set(Calendar.SECOND, 0);
            Calendar stopDate = TimestampUtils.createTimestamp(testResultDateRange.getStopDate() + "T23:59");
            stopDate.set(Calendar.SECOND, 59);
            Object[] params = new Object[5];
            params[0] = testResultDateRange.getNhsNo();
            params[1] = testResultDateRange.getUnitcode();
            params[2] = testResultDateRange.getTestCode();
            params[3] = new Timestamp(startDate.getTimeInMillis());
            params[4] = new Timestamp(stopDate.getTimeInMillis());
            DatabaseUpdateQuery query = new DatabaseUpdateQuery(dateRangeDeleteSql, params);
            dao.doExecute(query);
        }
    }

    private void insertResults(Collection testResults) {
        for (Iterator iterator = testResults.iterator(); iterator.hasNext();) {
            TestResult testResult = (TestResult) iterator.next();
            TestResultDao testResultDao = new TestResultDao(testResult);
            dao.insertItem(testResultDao);
        }
    }

    private void deleteLetters(Collection letters) {
        String letterDeleteSql = "DELETE FROM letter WHERE nhsno = ? AND unitcode = ? AND date = ?";
        for (Iterator iterator = letters.iterator(); iterator.hasNext();) {
            Letter letter = (Letter) iterator.next();
            Object[] params = new Object[3];
            params[0] = letter.getNhsno();
            params[1] = letter.getUnitcode();
            params[2] = new Timestamp(letter.getDate().getTimeInMillis());
            DatabaseUpdateQuery query = new DatabaseUpdateQuery(letterDeleteSql, params);
            dao.doExecute(query);
        }
    }

    private void insertLetters(Collection letters) {
        for (Iterator iterator = letters.iterator(); iterator.hasNext();) {
            Letter letter = (Letter) iterator.next();
            LegacySpringUtils.getLetterManager().save(letter);
        }
    }

    private void deleteOtherDiagnoses(String nhsno, String unitcode) {
        String diagnosesDeleteSql = "DELETE FROM diagnosis WHERE nhsno = ? AND unitcode = ?";
        Object[] params = new Object[]{nhsno, unitcode};
        DatabaseUpdateQuery query = new DatabaseUpdateQuery(diagnosesDeleteSql, params);
        dao.doExecute(query);
    }

    private void insertOtherDiagnoses(Collection diagnoses) {
        for (Iterator iterator = diagnoses.iterator(); iterator.hasNext();) {
            Diagnosis diagnosis = (Diagnosis) iterator.next();
            LegacySpringUtils.getDiagnosisManager().save(diagnosis);
        }
    }

    private void deleteMedicines(String nhsno, String unitcode) {
        String deleteSql = "DELETE FROM medicine WHERE nhsno = ? AND unitcode = ?";
        Object[] params = new Object[]{nhsno, unitcode};
        DatabaseUpdateQuery query = new DatabaseUpdateQuery(deleteSql, params);
        dao.doExecute(query);
    }

    private void insertMedicines(Collection medicines) {
        for (Iterator iterator = medicines.iterator(); iterator.hasNext();) {
            Medicine medicine = (Medicine) iterator.next();
            LegacySpringUtils.getMedicineManager().save(medicine);
        }
    }
}
