package com.worthsoln.patientview.letter;

import com.worthsoln.HibernateUtil;
import com.worthsoln.database.action.DatabaseAction;
import com.worthsoln.patientview.User;
import com.worthsoln.patientview.logon.LogonUtils;
import com.worthsoln.patientview.logon.UserMapping;
import com.worthsoln.patientview.user.UserUtils;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;
import net.sf.hibernate.Transaction;
import net.sf.hibernate.type.Type;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class LetterDisplayAction extends DatabaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception {
        User user = UserUtils.retrieveUser(request);
        List letters = getLettersForPatient(user.getUsername(), request);
        request.setAttribute("letters", letters);
        request.setAttribute("user", user);
        return LogonUtils.logonChecks(mapping, request);
    }

    private List getLettersForPatient(String username, HttpServletRequest request) throws Exception {
        List lettersAndUserMappings = new ArrayList();
        try {
            Session session = HibernateUtil.currentSession();
            Transaction tx = session.beginTransaction();

            lettersAndUserMappings = session.find("from " + Letter.class.getName() + " as letter, " +
                    UserMapping.class.getName() + " as usermapping " +
                    " where usermapping.username = ? " +
                    " and letter.nhsno = usermapping.nhsno " +
                    " order by letter.date desc",
                    new Object[]{username}, new Type[]{Hibernate.STRING});

            tx.commit();
            HibernateUtil.closeSession();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        List<Letter> letters = new ArrayList();

        for (Object letterUserMapping : lettersAndUserMappings) {
            Object[] letterUserMappingArray = (Object[]) letterUserMapping;
            Letter letter = (Letter) letterUserMappingArray[0];

            boolean letterAlreadyInList = false;
            for (Letter letterInList : letters) {
                if (letter.equals(letterInList)) {
                    letterAlreadyInList = true;
                    break;
                }
            }
            if (!letterAlreadyInList) {
                letters.add(letter);
            }
        }

        return letters;
    }

    public String getDatabaseName() {
        return "patientview";
    }

    public String getIdentifier() {
        return "letter";
    }
}
