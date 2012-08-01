package com.worthsoln.repository.ibd.impl;

import com.worthsoln.ibd.model.MyIbd;
import com.worthsoln.ibd.model.MyIbd_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.ibd.MyIbdDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository(value = "myIbdDao")
public class MyIbdDaoImpl extends AbstractHibernateDAO<MyIbd> implements MyIbdDao {

    @Override
    public MyIbd get(String nhsno) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MyIbd> criteria = builder.createQuery(MyIbd.class);
        Root<MyIbd> myIbdRoot = criteria.from(MyIbd.class);

        criteria.where(builder.equal(myIbdRoot.get(MyIbd_.nhsno), nhsno));

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
