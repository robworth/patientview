package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.Specialty;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.SpecialtyDao;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository(value = "specialtyDao")
public class SpecialtyDaoImpl extends AbstractHibernateDAO<Specialty> implements SpecialtyDao {
}
