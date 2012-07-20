package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.Letter;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.LetterDao;

import java.util.List;

/**
 *
 */
public class LetterDaoImpl extends AbstractHibernateDAO<Letter> implements LetterDao {

    @Override
    public List<Letter> get(String username) {

//        try {
//            Session session = HibernateUtil.currentSession();
//            Transaction tx = session.beginTransaction();
//
//            lettersAndUserMappings = session.find("from " + Letter.class.getName() + " as letter, " +
//                    UserMapping.class.getName() + " as usermapping " +
//                    " where usermapping.username = ? " +
//                    " and letter.nhsno = usermapping.nhsno " +
//                    " order by letter.date desc",
//                    new Object[]{username}, new Type[]{Hibernate.STRING});
//
//            tx.commit();
//            HibernateUtil.closeSession();
//        } catch (HibernateException e) {
//            e.printStackTrace();
//        }
//
//        List<Letter> letters = new ArrayList();
//
//        for (Object letterUserMapping : lettersAndUserMappings) {
//            Object[] letterUserMappingArray = (Object[]) letterUserMapping;
//            Letter letter = (Letter) letterUserMappingArray[0];
//
//            boolean letterAlreadyInList = false;
//            for (Letter letterInList : letters) {
//                if (letter.equals(letterInList)) {
//                    letterAlreadyInList = true;
//                    break;
//                }
//            }
//            if (!letterAlreadyInList) {
//                letters.add(letter);
//            }
//        }

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
