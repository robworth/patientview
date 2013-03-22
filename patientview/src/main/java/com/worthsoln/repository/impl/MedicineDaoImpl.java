package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.Comment;
import com.worthsoln.patientview.model.Comment_;
import com.worthsoln.patientview.model.Medicine;
import com.worthsoln.patientview.model.Medicine_;
import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.MedicineDao;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 *
 */
@Repository(value = "medicineDao")
public class MedicineDaoImpl extends AbstractHibernateDAO<Medicine> implements MedicineDao {

    @Override
    public List<Medicine> getMedicines(String nhsno) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Medicine> criteria = builder.createQuery(Medicine.class);
        Root<Medicine> medicineRoot = criteria.from(Medicine.class);

        criteria.where(builder.equal(medicineRoot.get(Medicine_.nhsno), nhsno));

        return getEntityManager().createQuery(criteria).getResultList();
    }

    @Override
    public void delete(String nhsno, String unitcode) {
        if (nhsno == null || nhsno.length() == 0) {
            throw new IllegalArgumentException("nhsno and unitcode are required parameter to delete procedure");
        }

        Query query = getEntityManager().createQuery(
                "DELETE FROM medicine WHERE nhsno = :nhsno AND unitcode = :unitcode");

        query.setParameter("nhsno", nhsno);
        query.setParameter("unitcode", unitcode);

        query.executeUpdate();
    }
}
