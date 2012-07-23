package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.Feedback;
import com.worthsoln.repository.FeedbackDao;
import com.worthsoln.service.FeedbackManager;

import javax.inject.Inject;
import java.util.List;

/**
 *
 */
public class FeedbackManagerImpl implements FeedbackManager {

    @Inject
    private FeedbackDao feedbackDao;

    @Override
    public Feedback get(Long id) {
        return feedbackDao.get(id);
    }

    @Override
    public void save(Feedback feedback) {
        feedbackDao.save(feedback);
    }

    @Override
    public List<Feedback> get(String unitcode) {
        return feedbackDao.get(unitcode);
    }
}
