package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.EdtaCode;
import com.worthsoln.patientview.model.EdtaCode_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.EdtaCodeDao;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository(value = "edtaCodeDao")
public class EdtaCodeDaoImpl extends AbstractHibernateDAO<EdtaCode> implements EdtaCodeDao {

    @Override
    public EdtaCode getEdtaCode(String edtaCode) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<EdtaCode> criteria = builder.createQuery(EdtaCode.class);
        Root<EdtaCode> edtaCodeRoot = criteria.from(EdtaCode.class);

        criteria.where(builder.equal(edtaCodeRoot.get(EdtaCode_.edtaCode), edtaCode));

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void delete(String edtaCode) {
        EdtaCode edtaCodeToRemove = getEdtaCode(edtaCode);

        if (edtaCodeToRemove != null) {
            delete(edtaCodeToRemove);
        }
    }

    @Override
    public List<EdtaCode> get(String linkType) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<EdtaCode> criteria = builder.createQuery(EdtaCode.class);
        Root<EdtaCode> edtaCodeRoot = criteria.from(EdtaCode.class);

        criteria.where(builder.equal(edtaCodeRoot.get(EdtaCode_.linkType), linkType));

        criteria.orderBy(builder.asc(edtaCodeRoot.get(EdtaCode_.edtaCode)));

        return getEntityManager().createQuery(criteria).getResultList();
    }
}
