package com.worthsoln.patientview.medicine;

import com.worthsoln.patientview.model.Medicine;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.unit.UnitUtils;
import com.worthsoln.patientview.user.UserUtils;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class MedicineDisplayAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {
        User user = UserUtils.retrieveUser(request);
        List medicines = getMedicinesForPatient(user, request);
        sortNullDatesOnMedicines(medicines);

        request.setAttribute("medicines", medicines);
        request.setAttribute("user", user);

        return LogonUtils.logonChecks(mapping, request);
    }

    private List getMedicinesForPatient(User user, HttpServletRequest request) throws Exception {
        List<MedicineWithShortName> medicinesWithShortName = new ArrayList<MedicineWithShortName>();
        if (user != null) {
            List<Medicine> medicines = LegacySpringUtils.getMedicineManager().getUserMedicines(user);

            for (Medicine med : medicines) {
                Unit unit = UnitUtils.retrieveUnit(med.getUnitcode());
                if (unit != null) {
                    medicinesWithShortName.add(new MedicineWithShortName(med, unit.getShortname()));
                } else {
                    medicinesWithShortName.add(new MedicineWithShortName(med, "UNKNOWN UNIT:" + med.getUnitcode()));
                }
            }
        }

        return medicinesWithShortName;
    }

    private List sortNullDatesOnMedicines(List medicines) {
        for (Object obj : medicines) {
            Medicine medicine = (Medicine) obj;

            // todo this probably won't work anymore
            Medicine tempMed = LegacySpringUtils.getMedicineManager().get(medicine.getId());
            medicine.setStartdate(tempMed.getStartdate());
        }

        return medicines;
    }

}
