package com.worthsoln.service;

import com.worthsoln.patientview.model.Feedback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface FeedbackManager {

    Feedback get(Long id);

    void save(Feedback feedback);

    List<Feedback> get(String unitcode);
}
