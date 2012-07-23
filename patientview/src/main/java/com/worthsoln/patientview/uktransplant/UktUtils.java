package com.worthsoln.patientview.uktransplant;

import javax.servlet.http.HttpServletRequest;
import com.worthsoln.patientview.model.UktStatus;
import com.worthsoln.utils.LegacySpringUtils;

public class UktUtils {

    public static void addUktStatusToRequest(String nhsno, HttpServletRequest request)
            throws Exception {
        UktStatusForPatient readableStatus = retreiveUktStatus(nhsno);
        request.setAttribute("uktstatus", readableStatus);
    }

    public static UktStatusForPatient retreiveUktStatus(String nhsno) {
        UktStatusForPatient readableStatus = null;
        UktStatus status = LegacySpringUtils.getUkTransplantManager().getUktStatus(nhsno);
        if (status != null) {
            readableStatus = new UktStatusForPatient(status.getNhsno(), makeRawStatusReadable(status.getKidney()),
                    makeRawStatusReadable(status.getPancreas()));
        } else {
            readableStatus = new UktStatusForPatient("", "", "");
        }
        return readableStatus;
    }

    static String makeRawStatusReadable(String rawStaus) {
        if ("A".equalsIgnoreCase(rawStaus)) {
            return "Active";
        }
        if ("S".equalsIgnoreCase(rawStaus)) {
            return "Suspended";
        }
        if ("T".equalsIgnoreCase(rawStaus)) {
            return "Transplanted";
        }
        return "Not on list";
    }

}
