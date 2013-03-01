package com.worthsoln.repository.ibd.impl;

import com.worthsoln.ibd.model.CarePlan;
import com.worthsoln.ibd.model.CarePlan_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.ibd.CarePlanDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository(value = "carePlanDao")
public class CarePlanDaoImpl extends AbstractHibernateDAO<CarePlan> implements CarePlanDao {

    @Override
    public CarePlan get(String nhsno) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<CarePlan> criteria = builder.createQuery(CarePlan.class);
        Root<CarePlan> carePlanRoot = criteria.from(CarePlan.class);

        criteria.where(builder.equal(carePlanRoot.get(CarePlan_.nhsno), nhsno));

        try {
            return getEntityManager().createQuery(criteria).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
