package com.worthsoln.repository.ibd.impl;

import com.worthsoln.ibd.model.Crohns;
import com.worthsoln.ibd.model.Crohns_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.ibd.CrohnsDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository(value = "crohnsDao")
public class CrohnsDaoImpl extends AbstractHibernateDAO<Crohns> implements CrohnsDao {

    @Override
    public List<Crohns> getAllCrohns(String nhsno) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Crohns> criteria = builder.createQuery(Crohns.class);
        Root<Crohns> crohnsRoot = criteria.from(Crohns.class);

        criteria.where(builder.equal(crohnsRoot.get(Crohns_.nhsno), nhsno));

        return getEntityManager().createQuery(criteria).getResultList();
    }

}
