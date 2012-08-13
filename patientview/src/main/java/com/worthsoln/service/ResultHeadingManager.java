package com.worthsoln.service;

import com.worthsoln.patientview.model.Panel;
import com.worthsoln.patientview.model.ResultHeading;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface ResultHeadingManager {

    ResultHeading get(String headingcode);

    List<ResultHeading> getAll();

    List<ResultHeading> get(int panel);

    void save(ResultHeading resultHeading);

    void delete(String headingCode);

    List<Panel> getPanels();
}
