package com.worthsoln.repository;

import com.worthsoln.patientview.model.Tenancy;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface TenancyDao {

    Tenancy get(Long id);

    List<Tenancy> getAll();

    void save(Tenancy tenancy);
}
