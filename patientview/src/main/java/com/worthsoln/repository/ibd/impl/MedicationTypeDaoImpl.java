package com.worthsoln.repository.ibd.impl;

import com.worthsoln.ibd.model.medication.MedicationType;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.ibd.MedicationTypeDao;
import org.springframework.stereotype.Repository;

@Repository(value = "medicationTypeDao")
public class MedicationTypeDaoImpl extends AbstractHibernateDAO<MedicationType> implements MedicationTypeDao {

}
