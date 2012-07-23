package com.worthsoln.repository;

import com.worthsoln.patientview.model.ResultHeading;

import java.util.List;

/**
 *
 */
public interface ResultHeadingDao {

    ResultHeading get(String headingcode);

    List<ResultHeading> getAll();

    void save(ResultHeading resultHeading);

    void delete(String headingCode);
}
