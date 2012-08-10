package com.worthsoln.repository;

import com.worthsoln.patientview.logon.UnitAdmin;
import com.worthsoln.patientview.model.Tenancy;
import com.worthsoln.patientview.model.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface UserDao {

    User get(Long id);

    User get(String username);

    void save(User user);

    void delete(User user);

    List<User> getAll();

    List<UnitAdmin> getUnitUsers(String unitcode, Tenancy tenancy);
}
