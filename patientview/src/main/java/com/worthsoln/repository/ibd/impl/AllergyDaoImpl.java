package com.worthsoln.repository.ibd.impl;

import com.worthsoln.ibd.model.Allergy;
import com.worthsoln.ibd.model.Allergy_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.ibd.AllergyDao;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository(value = "allergyDao")
public class AllergyDaoImpl extends AbstractHibernateDAO<Allergy> implements AllergyDao {

    @Override
    public Allergy getAllergy(String nhsno) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Allergy> criteria = builder.createQuery(Allergy.class);
        Root<Allergy> allergyRoot = criteria.from(Allergy.class);

        criteria.where(builder.equal(allergyRoot.get(Allergy_.nhsno), nhsno));

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void delete(String nhsno, String unitcode) {
        if (nhsno == null || nhsno.length() == 0) {
            throw new IllegalArgumentException("nhsno and unitcode are required parameter to delete procedure");
        }

        Query query = getEntityManager().createQuery(
                "DELETE FROM pv_allergy WHERE nhsno = :nhsno AND unitcode = :unitcode");

        query.setParameter("nhsno", unitcode);
        query.setParameter("unitcode", unitcode);

        query.executeUpdate();
    }
}
