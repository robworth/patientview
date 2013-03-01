package com.worthsoln.repository.ibd.impl;

import com.worthsoln.ibd.model.Nutrition;
import com.worthsoln.ibd.model.Nutrition_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.ibd.NutritionDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository(value = "nutritionDao")
public class NutritionDaoImpl extends AbstractHibernateDAO<Nutrition> implements NutritionDao {

    @Override
    public List<Nutrition> getAllNutritions(String nhsno) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Nutrition> criteria = builder.createQuery(Nutrition.class);
        Root<Nutrition> nutritionRoot = criteria.from(Nutrition.class);

        criteria.where(builder.equal(nutritionRoot.get(Nutrition_.nhsno), nhsno));
        criteria.orderBy(builder.asc(nutritionRoot.get(Nutrition_.nutritionDate)));

        return getEntityManager().createQuery(criteria).getResultList();
    }

}
