package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.JoinRequest;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.JoinRequestDao;
import org.springframework.stereotype.Repository;

@Repository(value = "joinRequestDao")
public class JoinRequestDaoImpl extends AbstractHibernateDAO<JoinRequest> implements JoinRequestDao {



}
