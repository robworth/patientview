package com.worthsoln.repository.ibd.impl;

import com.worthsoln.ibd.model.MyIbd;
import com.worthsoln.ibd.model.MyIbdSeverityLevel;
import com.worthsoln.ibd.model.MyIbdSeverityLevel_;
import com.worthsoln.ibd.model.enums.Diagnosis;
import com.worthsoln.ibd.model.enums.Severity;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.ibd.MyIbdDao;
import com.worthsoln.repository.ibd.MyIbdSeverityLevelDao;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository(value = "myIbdSeverityLevelDao")
public class MyIbdSeverityLevelDaoImpl extends AbstractHibernateDAO<MyIbdSeverityLevel>
        implements MyIbdSeverityLevelDao {

    @Inject
    MyIbdDao myIbdDao;

    @Override
    public MyIbdSeverityLevel get(String nhsno, Severity severity) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MyIbdSeverityLevel> criteria = builder.createQuery(MyIbdSeverityLevel.class);
        Root<MyIbdSeverityLevel> myIbdSeverityLevelRoot = criteria.from(MyIbdSeverityLevel.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(myIbdSeverityLevelRoot.get(MyIbdSeverityLevel_.nhsno), nhsno));
        wherePredicates.add(builder.equal(myIbdSeverityLevelRoot.get(MyIbdSeverityLevel_.severityId),
                severity.getId()));

        buildWhereClause(criteria, wherePredicates);

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            MyIbd myIbd = myIbdDao.get(nhsno);
            Diagnosis diagnosis = myIbd.getDiagnosis();

            return new MyIbdSeverityLevel(nhsno, severity, severity.getDefaultLevel(diagnosis), null);
        }
    }

    @Override
    public void save(List<MyIbdSeverityLevel> myIbdSeverityLevels) {
        if (myIbdSeverityLevels != null) {
            for (MyIbdSeverityLevel myIbdSeverityLevel : myIbdSeverityLevels) {
                save(myIbdSeverityLevel);
            }
        }
    }

    @Override
    public void save(MyIbdSeverityLevel myIbdSeverityLevel) {
        // need to test if there is a record with this level already and just use that
        MyIbdSeverityLevel existingMyIbdSeverityLevel = get(myIbdSeverityLevel.getNhsno(),
                myIbdSeverityLevel.getSeverity());

        if (existingMyIbdSeverityLevel.hasValidId()) {
            if (canSave(myIbdSeverityLevel)) {
                if (levelChanged(myIbdSeverityLevel)) {
                    MyIbd myIbd = myIbdDao.get(myIbdSeverityLevel.getNhsno());
                    Diagnosis diagnosis = myIbd.getDiagnosis();

                    existingMyIbdSeverityLevel.setLevel(myIbdSeverityLevel.getLevel(diagnosis));
                }

                super.save(existingMyIbdSeverityLevel);

                myIbdSeverityLevel.setId(existingMyIbdSeverityLevel.getId());
            } else {
                delete(existingMyIbdSeverityLevel);
            }
        } else {
            // if the new value is > 0 and not the default then save
            if (canSave(myIbdSeverityLevel)) {
                if (!levelChanged(myIbdSeverityLevel)) {
                    myIbdSeverityLevel.setLevel(null);
                }

                super.save(myIbdSeverityLevel);
            }
        }
    }

    /**
     * Only wanna save if it actually has values else we will just have a load of empty rows
     * @param myIbdSeverityLevel MyIbdSeverityLevel
     * @return boolean
     */
    private boolean canSave(MyIbdSeverityLevel myIbdSeverityLevel) {
        return levelChanged(myIbdSeverityLevel) || (myIbdSeverityLevel.getTreatment() != null
                && myIbdSeverityLevel.getTreatment().length() > 0);
    }

    /**
     * will check if the value has actually been updated and is not the default
     * @param myIbdSeverityLevel MyIbdSeverityLevel
     * @return boolean
     */
    private boolean levelChanged(MyIbdSeverityLevel myIbdSeverityLevel) {
        MyIbd myIbd = myIbdDao.get(myIbdSeverityLevel.getNhsno());
        Diagnosis diagnosis = myIbd.getDiagnosis();

        return (myIbdSeverityLevel.getLevel(diagnosis) > 0
                && myIbdSeverityLevel.getLevel(diagnosis) != myIbdSeverityLevel.getSeverity().getDefaultLevel(
                diagnosis));
    }
}
