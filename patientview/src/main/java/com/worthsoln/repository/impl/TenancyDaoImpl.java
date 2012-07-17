package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.Tenancy;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.TenancyDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Repository
@Transactional(propagation = Propagation.MANDATORY)
public class TenancyDaoImpl extends AbstractHibernateDAO<Tenancy> implements TenancyDao {
}
