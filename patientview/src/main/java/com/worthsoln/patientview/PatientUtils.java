package com.worthsoln.patientview;

import com.worthsoln.database.DatabaseDAO;
import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.patientview.model.Patient;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.unit.UnitUtils;
import com.worthsoln.patientview.user.UserUtils;

import javax.servlet.http.HttpServletRequest;

public class PatientUtils {

    public static String retrieveNhsNo(HttpServletRequest request) {
        User user = UserUtils.retrieveUser(request);
        UserMapping userMapping = UserUtils.retrieveUserMappingsPatientEntered(user);
        return userMapping.getNhsno();
    }

    public static Patient retrievePatient(HttpServletRequest request, DatabaseDAO dao) {
        String nhsno = PatientUtils.retrieveNhsNo(request);
        String unitcode = UnitUtils.retrieveUnitcode(request);
        return retrievePatient(nhsno, unitcode, dao);
    }

    public static Patient retrievePatient(String nhsno, String unitcode, DatabaseDAO dao) {
        PatientDao patientDao = new PatientDao(new Patient(nhsno, unitcode));
        Patient patient = (Patient) dao.retrieveItem(patientDao);
        return patient;
    }
}
