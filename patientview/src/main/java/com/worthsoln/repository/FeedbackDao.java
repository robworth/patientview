package com.worthsoln.repository;

import com.worthsoln.patientview.model.Feedback;

import java.util.List;

/**
 *
 */
public interface FeedbackDao {

    List<Feedback> get(String unitcode);
}
