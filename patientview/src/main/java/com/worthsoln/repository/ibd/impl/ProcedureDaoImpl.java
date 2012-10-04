package com.worthsoln.repository.ibd.impl;

import com.worthsoln.ibd.model.Procedure;
import com.worthsoln.ibd.model.Procedure_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.ibd.ProcedureDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository(value = "procedureDao")
public class ProcedureDaoImpl extends AbstractHibernateDAO<Procedure> implements ProcedureDao {

    @Override
    public Procedure getProcedure(String nhsno) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Procedure> criteria = builder.createQuery(Procedure.class);
        Root<Procedure> procedureRoot = criteria.from(Procedure.class);

        criteria.where(builder.equal(procedureRoot.get(Procedure_.nhsno), nhsno));
        criteria.orderBy(builder.asc(procedureRoot.get(Procedure_.date)));

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
