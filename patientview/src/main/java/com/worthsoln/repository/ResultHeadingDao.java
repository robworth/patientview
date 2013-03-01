package com.worthsoln.repository;

import com.worthsoln.patientview.model.Panel;
import com.worthsoln.patientview.model.ResultHeading;
import com.worthsoln.patientview.model.Tenancy;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface ResultHeadingDao {

    ResultHeading get(String headingcode, Tenancy tenancy);

    List<ResultHeading> getAll(Tenancy tenancy);

    List<ResultHeading> get(int panel, Tenancy tenancy);

    void save(ResultHeading resultHeading);

    void delete(String headingCode, Tenancy tenancy);

    List<Panel> getPanels(Tenancy tenancy);
}
