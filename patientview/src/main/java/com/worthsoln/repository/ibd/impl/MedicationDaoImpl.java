package com.worthsoln.repository.ibd.impl;

import com.worthsoln.ibd.model.medication.Medication;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.ibd.MedicationDao;
import org.springframework.stereotype.Repository;

@Repository(value = "medicationDao")
public class MedicationDaoImpl extends AbstractHibernateDAO<Medication> implements MedicationDao {

}
