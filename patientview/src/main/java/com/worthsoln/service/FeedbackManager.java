package com.worthsoln.service;

import com.worthsoln.patientview.model.Feedback;

import java.util.List;

/**
 *
 */
public interface FeedbackManager {

    Feedback get(Long id);

    void save(Feedback feedback);

    List<Feedback> get(String unitcode);
}
