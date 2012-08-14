package com.worthsoln.repository.ibd.impl;

import com.worthsoln.ibd.model.symptoms.Colitis;
import com.worthsoln.ibd.model.symptoms.Colitis_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.ibd.ColitisDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository(value = "colitisDao")
public class ColitisDaoImpl extends AbstractHibernateDAO<Colitis> implements ColitisDao {

    @Override
    public List<Colitis> getAllColitis(String nhsno) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Colitis> criteria = builder.createQuery(Colitis.class);
        Root<Colitis> colitisRoot = criteria.from(Colitis.class);

        criteria.where(builder.equal(colitisRoot.get(Colitis_.nhsno), nhsno));

        return getEntityManager().createQuery(criteria).getResultList();
    }

}
