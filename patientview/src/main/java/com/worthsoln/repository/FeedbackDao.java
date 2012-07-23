package com.worthsoln.repository;

import com.worthsoln.patientview.model.Feedback;

import java.util.List;

/**
 *
 */
public interface FeedbackDao {

    Feedback get(Long id);

    void save(Feedback feedback);

    List<Feedback> get(String unitcode);
}
