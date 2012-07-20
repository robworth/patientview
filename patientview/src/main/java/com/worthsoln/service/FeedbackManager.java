package com.worthsoln.service;

import com.worthsoln.patientview.model.Feedback;

import java.util.List;

/**
 *
 */
public interface FeedbackManager {

    List<Feedback> get(String unitcode);
}
