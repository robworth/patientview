package com.worthsoln.service;

import com.worthsoln.patientview.model.ResultHeading;

import java.util.List;

/**
 *
 */
public interface ResultHeadingManager {

    List<ResultHeading> getAll();

    void save(ResultHeading resultHeading);

    void delete(String headingCode);
}
