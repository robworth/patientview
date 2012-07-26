package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.Medicine;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.MedicineDao;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository(value = "medicineDao")
public class MedicineDaoImpl extends AbstractHibernateDAO<Medicine> implements MedicineDao {
}
