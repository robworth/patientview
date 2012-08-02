package com.worthsoln.repository;

import com.worthsoln.patientview.model.TenancyUserRole;
import com.worthsoln.patientview.model.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface TenancyUserRoleDao {

    List<TenancyUserRole> get(User user);

    void save(TenancyUserRole tenancyUserRole);
}
