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
    public List<Feedback> get(String unitcode) {
        return feedbackDao.get(unitcode);
    }
}
