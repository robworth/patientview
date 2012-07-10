package com.worthsoln.patientview;

import com.worthsoln.database.DatabaseDAO;
import com.worthsoln.patientview.diagnosis.DiagnosisUtils;
import com.worthsoln.patientview.edtacode.EdtaCode;
import com.worthsoln.patientview.edtacode.EdtaCodeUtils;
import com.worthsoln.patientview.logging.AddLog;
import com.worthsoln.patientview.logon.UserMapping;
import com.worthsoln.patientview.uktransplant.UktUtils;
import com.worthsoln.patientview.unit.Unit;
import com.worthsoln.patientview.unit.UnitUtils;
import com.worthsoln.patientview.user.UserUtils;
import com.worthsoln.utils.LegacySpringUtils;
import net.sf.hibernate.HibernateException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class PatientDetailsUtils {

    static List<PatientDetails> buildPatientDetails(HttpServletRequest request, DatabaseDAO dao) throws HibernateException {
        User user = UserUtils.retrieveUser(request);
        List userMappings = UserUtils.retrieveUserMappings(user);

        List<PatientDetails> patientDetails = new ArrayList();

        for (Object obj : userMappings) {

            UserMapping userMapping = (UserMapping) obj;

            Patient patient = PatientUtils.retrievePatient(userMapping.getNhsno(), userMapping.getUnitcode(), dao);

            Unit unit = UnitUtils.retrieveUnit(userMapping.getUnitcode());

            if (patient != null && unit != null) {
                PatientDetails patientDetail = new PatientDetails();

                patientDetail.setPatient(patient);
                patientDetail.setUnit(unit);

                EdtaCode diagnosisCode = EdtaCodeUtils.retrieveEdtaCode(dao, patient.getDiagnosis());
                patientDetail.setEdtaDiagnosis(diagnosisCode);

                EdtaCode treatmentCode = EdtaCodeUtils.retrieveEdtaCode(dao, patient.getTreatment());
                patientDetail.setEdtaTreatment(treatmentCode);

                patientDetail.setUktStatus(UktUtils.retreiveUktStatus(userMapping.getNhsno()));

                List otherDiagnoses = DiagnosisUtils.getOtherDiagnoses(patient.getNhsno(), patient.getCentreCode());
                patientDetail.setOtherDiagnoses(otherDiagnoses);

                patientDetails.add(patientDetail);

                AddLog.addLog(LegacySpringUtils.getSecurityUserManager().getLoggedInUsername(),
                        AddLog.PATIENT_VIEW, "", patient.getNhsno(),
                        patient.getCentreCode(), "");
            }
        }
        return patientDetails;
    }

}
