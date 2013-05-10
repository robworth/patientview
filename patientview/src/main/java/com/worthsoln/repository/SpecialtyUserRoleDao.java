package com.worthsoln.repository;

import com.worthsoln.patientview.model.SpecialtyUserRole;
import com.worthsoln.patientview.model.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface SpecialtyUserRoleDao {

    List<SpecialtyUserRole> get(User user);

    void save(SpecialtyUserRole specialtyUserRole);

    void delete(SpecialtyUserRole specialtyUserRole);
}
