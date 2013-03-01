package com.worthsoln.repository.ibd.impl;

import com.worthsoln.ibd.model.MyIbd;
import com.worthsoln.ibd.model.MyIbd_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.ibd.MyIbdDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository(value = "myIbdDao")
public class MyIbdDaoImpl extends AbstractHibernateDAO<MyIbd> implements MyIbdDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyIbdDaoImpl.class);

    @Override
    public MyIbd get(String nhsno) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MyIbd> criteria = builder.createQuery(MyIbd.class);
        Root<MyIbd> myIbdRoot = criteria.from(MyIbd.class);

        criteria.where(builder.equal(myIbdRoot.get(MyIbd_.nhsno), nhsno));

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NonUniqueResultException ex) {
            // Bad situation if this is a , the model cannot handle the unit data.
            // too late to throw an exception, log and close your eyes.
            LOGGER.error("Found multiple MyIbds for nhsno {}, returning null", nhsno);
            return null;
        } catch (Exception e) {
            LOGGER.error("Error getting MyIbds for nhsno {}, returning null, {}", nhsno, e.getMessage());
            return null;
        }
    }

    @Override
    public MyIbd get(String nhsno, String unitcode) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MyIbd> criteria = builder.createQuery(MyIbd.class);
        Root<MyIbd> myIbdRoot = criteria.from(MyIbd.class);

        criteria.where(builder.equal(myIbdRoot.get(MyIbd_.nhsno), nhsno),
                builder.equal(myIbdRoot.get(MyIbd_.unitcode), unitcode));

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NonUniqueResultException ex) {
            // Bad situation if this is a , the model cannot handle the unit data.
            // too late to throw an exception, log and close your eyes.
            LOGGER.error("Found multiple MyIbds for nhsno {} and unitcode {}, returning null", nhsno, unitcode);
            return null;
        } catch (NoResultException e) {
            // No entity found for query - this should be ok
            return null;
        }
    }

    @Override
    public void delete(String nhsno, String unitcode) {
        if (nhsno == null || nhsno.length() == 0 || unitcode == null || unitcode.length() == 0) {
            throw new IllegalArgumentException("nhsno and unitcode are required parameter to delete myIbd");
        }

        MyIbd myIbd = get(nhsno, unitcode);

        if (myIbd != null) {
            delete(myIbd);
        }
    }
}
