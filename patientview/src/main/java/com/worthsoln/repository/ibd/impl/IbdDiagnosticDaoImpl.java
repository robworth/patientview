package com.worthsoln.repository.ibd.impl;

import com.worthsoln.ibd.model.IbdDiagnostic;
import com.worthsoln.ibd.model.IbdDiagnostic_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.ibd.IbdDiagnosticDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository(value = "ibdDiagnosticDao")
public class IbdDiagnosticDaoImpl extends AbstractHibernateDAO<IbdDiagnostic> implements IbdDiagnosticDao {

    @Override
    public IbdDiagnostic getDiagnostic(String nhsno) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<IbdDiagnostic> criteria = builder.createQuery(IbdDiagnostic.class);
        Root<IbdDiagnostic> nutritionRoot = criteria.from(IbdDiagnostic.class);

        criteria.where(builder.equal(nutritionRoot.get(IbdDiagnostic_.nhsno), nhsno));

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
