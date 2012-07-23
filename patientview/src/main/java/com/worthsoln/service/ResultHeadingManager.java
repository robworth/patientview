package com.worthsoln.service;

import com.worthsoln.patientview.model.ResultHeading;

import java.util.List;

/**
 *
 */
public interface ResultHeadingManager {

    ResultHeading get(String headingcode);

    List<ResultHeading> getAll();

    void save(ResultHeading resultHeading);

    void delete(String headingCode);
}
