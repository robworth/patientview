package com.worthsoln.repository.ibd.impl;

import com.worthsoln.ibd.model.medication.MyMedication_;
import com.worthsoln.ibd.model.medication.MyMedication;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.ibd.MyMedicationDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository(value = "myMedicationDao")
public class MyMedicationDaoImpl extends AbstractHibernateDAO<MyMedication> implements MyMedicationDao {

    @Override
    public List<MyMedication> getAllMedications(String nhsno) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MyMedication> criteria = builder.createQuery(MyMedication.class);
        Root<MyMedication> myMedicineRoot = criteria.from(MyMedication.class);

        criteria.where(builder.equal(myMedicineRoot.get(MyMedication_.nhsno), nhsno));

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public List<MyMedication> getCurrentMedications(String nhsno) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MyMedication> criteria = builder.createQuery(MyMedication.class);
        Root<MyMedication> myMedicineRoot = criteria.from(MyMedication.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(myMedicineRoot.get(MyMedication_.nhsno), nhsno));
        wherePredicates.add(builder.isNull(myMedicineRoot.get(MyMedication_.dateStopped)));

        buildWhereClause(criteria, wherePredicates);

        criteria.orderBy(builder.asc(myMedicineRoot.get(MyMedication_.dateStarted)));

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public List<MyMedication> getStoppedMedications(String nhsno) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<MyMedication> criteria = builder.createQuery(MyMedication.class);
        Root<MyMedication> myMedicineRoot = criteria.from(MyMedication.class);

        List<Predicate> wherePredicates = new ArrayList<Predicate>();

        wherePredicates.add(builder.equal(myMedicineRoot.get(MyMedication_.nhsno), nhsno));
        wherePredicates.add(builder.isNotNull(myMedicineRoot.get(MyMedication_.dateStopped)));

        buildWhereClause(criteria, wherePredicates);

        criteria.orderBy(builder.asc(myMedicineRoot.get(MyMedication_.dateStopped)));

        return getEntityManager().createQuery(criteria).getResultList();
    }
}
