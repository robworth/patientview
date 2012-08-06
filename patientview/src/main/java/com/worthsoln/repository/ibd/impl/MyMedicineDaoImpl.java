package com.worthsoln.repository.ibd.impl;

import com.worthsoln.ibd.model.medication.MyMedicine;
import com.worthsoln.ibd.model.medication.MyMedicine_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.ibd.MyMedicineDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository(value = "myMedicineDao")
public class MyMedicineDaoImpl extends AbstractHibernateDAO<MyMedicine> implements MyMedicineDao {

    @Override
    public List<MyMedicine> getAllMedicines(String nhsno) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MyMedicine> criteria = builder.createQuery(MyMedicine.class);
        Root<MyMedicine> myMedicineRoot = criteria.from(MyMedicine.class);

        criteria.where(builder.equal(myMedicineRoot.get(MyMedicine_.nhsno), nhsno));

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public List<MyMedicine> getCurrentMedicines(String nhsno) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MyMedicine> criteria = builder.createQuery(MyMedicine.class);
        Root<MyMedicine> myMedicineRoot = criteria.from(MyMedicine.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(myMedicineRoot.get(MyMedicine_.nhsno), nhsno));
        wherePredicates.add(builder.isNull(myMedicineRoot.get(MyMedicine_.dateStopped)));

        buildWhereClause(criteria, wherePredicates);

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public List<MyMedicine> getStoppedMedicines(String nhsno) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MyMedicine> criteria = builder.createQuery(MyMedicine.class);
        Root<MyMedicine> myMedicineRoot = criteria.from(MyMedicine.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(myMedicineRoot.get(MyMedicine_.nhsno), nhsno));
        wherePredicates.add(builder.isNotNull(myMedicineRoot.get(MyMedicine_.dateStopped)));

        buildWhereClause(criteria, wherePredicates);

        return getEntityManager().createQuery(criteria).getResultList();
    }
}
