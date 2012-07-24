package com.worthsoln.patientview.logon;

import com.worthsoln.database.DatabaseDAO;
import com.worthsoln.database.action.DatabaseAction;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.utils.LegacySpringUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PatientEditAction extends DatabaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        String username = BeanUtils.getProperty(form, "username");
        String password = BeanUtils.getProperty(form, "password");
        String name = BeanUtils.getProperty(form, "name");
        String email = BeanUtils.getProperty(form, "email");
        boolean emailverified = "true".equals(BeanUtils.getProperty(form, "emailverified"));
        String nhsno = BeanUtils.getProperty(form, "nhsno");
        String unitcode = BeanUtils.getProperty(form, "unitcode");
        String overrideDuplicateNhsno = BeanUtils.getProperty(form, "overrideDuplicateNhsno");
        boolean firstlogon = "true".equals(BeanUtils.getProperty(form, "firstlogon"));
        boolean dummypatient = "true".equals(BeanUtils.getProperty(form, "dummypatient"));
        Calendar lastlogoncal = createDatestamp(BeanUtils.getProperty(form, "lastlogon"));
        Date lastlogon = lastlogoncal == null ? null : lastlogoncal.getTime();
        String failedlogonsstring = BeanUtils.getProperty(form, "failedlogons");
        int failedlogons = Integer.decode(failedlogonsstring);
        boolean accountlocked = "true".equals(BeanUtils.getProperty(form, "accountlocked"));
        String screenname = BeanUtils.getProperty(form, "screenname");
        String mappingToFind = "";
        List duplicateUsers = findDuplicateUsers(nhsno, username);
        if (!duplicateUsers.isEmpty() && !overrideDuplicateNhsno.equals("on")) {
            request.setAttribute(LogonUtils.NHSNO_ALREADY_EXISTS, nhsno);
            mappingToFind = "input";
        } else {
            DatabaseDAO dao = getDao(request);

            PatientLogon patient =
                    new PatientLogon(username, password, name, email, emailverified, firstlogon, dummypatient, lastlogon,
                            failedlogons, accountlocked, screenname);
            dao.updateItem(new LogonDao(patient));

            List<UserMapping> userMappings = findUsersSiblings(username, unitcode);

            for (UserMapping userMapping : userMappings) {
                userMapping.setNhsno(nhsno);
                LegacySpringUtils.getUserManager().save(userMapping);
            }

            Unit unit = LegacySpringUtils.getUnitManager().get(unitcode);
            if (unit != null) {
                request.setAttribute("unit", unit);
            }

            UnitPatientsAllWithTreatmentDao patientDao = new UnitPatientsAllWithTreatmentDao(unitcode);
            List patients = dao.retrieveList(patientDao);
            request.setAttribute("patients", patients);
            mappingToFind = "success";
        }
        return mapping.findForward(mappingToFind);
    }

    private List<UserMapping> findUsersSiblings(String username, String unitcode) throws Exception {
        return LegacySpringUtils.getUserManager().getUsersSiblings(username, unitcode);
    }

    private List findDuplicateUsers(String nhsno, String username) throws Exception {
        return LegacySpringUtils.getUserManager().getDuplicateUsers(nhsno, username);
    }

    private static Calendar createDatestamp(String dateTimeString) {
        Calendar datestamp = null;
        if (!"".equals(dateTimeString)) {
            datestamp = Calendar.getInstance();
            int year = Integer.parseInt(dateTimeString.substring(0, 4));
            int month = Integer.parseInt(dateTimeString.substring(5, 7));
            int day = Integer.parseInt(dateTimeString.substring(8, 10));
            datestamp.set(year, month - 1, day, 0, 0, 10);
            datestamp.set(Calendar.HOUR_OF_DAY, Integer.parseInt(dateTimeString.substring(11, 13)));
            datestamp.set(Calendar.MINUTE, Integer.parseInt(dateTimeString.substring(14, 16)));
            if (dateTimeString.length() == 19) {
                datestamp.set(Calendar.SECOND, Integer.parseInt(dateTimeString.substring(17, 19)));
            }
            datestamp.set(Calendar.MILLISECOND, 0);
        }
        return datestamp;
    }

    public String getIdentifier() {
        return null;
    }

    public String getDatabaseName() {
        return "patientview";
    }
}
