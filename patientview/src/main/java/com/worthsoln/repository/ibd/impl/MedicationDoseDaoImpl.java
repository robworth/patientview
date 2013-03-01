package com.worthsoln.repository.ibd.impl;

import com.worthsoln.ibd.model.medication.MedicationDose;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.ibd.MedicationDoseDao;
import org.springframework.stereotype.Repository;

@Repository(value = "medicationDoseDao")
public class MedicationDoseDaoImpl extends AbstractHibernateDAO<MedicationDose> implements MedicationDoseDao {

}
