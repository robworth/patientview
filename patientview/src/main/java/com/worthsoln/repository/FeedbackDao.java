package com.worthsoln.repository;

import com.worthsoln.patientview.model.Feedback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.MANDATORY)
public interface FeedbackDao {

    Feedback get(Long id);

    void save(Feedback feedback);

    List<Feedback> get(String unitcode);
}
