package com.worthsoln.repository;

import com.worthsoln.patientview.model.Panel;
import com.worthsoln.patientview.model.ResultHeading;
import com.worthsoln.patientview.model.Specialty;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface ResultHeadingDao {

    ResultHeading get(String headingcode, Specialty specialty);

    List<ResultHeading> getAll(Specialty specialty);

    List<ResultHeading> get(int panel, Specialty specialty);

    void save(ResultHeading resultHeading);

    void delete(String headingCode, Specialty specialty);

    List<Panel> getPanels(Specialty specialty);
}
