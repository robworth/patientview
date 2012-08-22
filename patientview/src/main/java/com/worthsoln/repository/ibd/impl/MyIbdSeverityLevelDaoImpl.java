package com.worthsoln.repository.ibd.impl;

import com.worthsoln.ibd.model.MyIbdSeverityLevel;
import com.worthsoln.ibd.model.MyIbdSeverityLevel_;
import com.worthsoln.ibd.model.enums.Severity;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.ibd.MyIbdSeverityLevelDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository(value = "myIbdSeverityLevelDao")
public class MyIbdSeverityLevelDaoImpl extends AbstractHibernateDAO<MyIbdSeverityLevel>
        implements MyIbdSeverityLevelDao {

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
            return null;
        }

    }

    @Override
    public List<MyIbdSeverityLevel> getMyIbdSeverityLevels(String nhsno) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MyIbdSeverityLevel> criteria = builder.createQuery(MyIbdSeverityLevel.class);
        Root<MyIbdSeverityLevel> myIbdSeverityLevelRoot = criteria.from(MyIbdSeverityLevel.class);

        criteria.where(builder.equal(myIbdSeverityLevelRoot.get(MyIbdSeverityLevel_.nhsno), nhsno));

        return getEntityManager().createQuery(criteria).getResultList();
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

        if (existingMyIbdSeverityLevel != null) {
            if (canSave(myIbdSeverityLevel)) {
                if (myIbdSeverityLevel.getLevel() != null && myIbdSeverityLevel.getLevel() > 0) {
                    existingMyIbdSeverityLevel.setLevel(myIbdSeverityLevel.getLevel());
                }

                super.save(existingMyIbdSeverityLevel);

                myIbdSeverityLevel.setId(existingMyIbdSeverityLevel.getId());
            } else {
                delete(existingMyIbdSeverityLevel);
            }
        } else {
            // if the new value is > 0 and not the default then save
            if (canSave(myIbdSeverityLevel)) {
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
        return (myIbdSeverityLevel.getLevel() > 0
                && myIbdSeverityLevel.getLevel() != myIbdSeverityLevel.getSeverity().getDefaultLevel())
                || (myIbdSeverityLevel.getTreatment() != null
                && myIbdSeverityLevel.getTreatment().length() > 0);
    }
}
