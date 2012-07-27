package com.worthsoln.repository.impl;

import com.worthsoln.patientview.model.Feedback;
import com.worthsoln.patientview.model.Feedback_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.FeedbackDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository(value = "feedbackDao")
public class FeedbackDaoImpl extends AbstractHibernateDAO<Feedback> implements FeedbackDao {

    @Override
    public List<Feedback> get(String unitcode) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Feedback> criteria = builder.createQuery(Feedback.class);
        Root<Feedback> feedbackRoot = criteria.from(Feedback.class);

        criteria.where(builder.equal(feedbackRoot.get(Feedback_.unitcode), unitcode));

        criteria.orderBy(builder.desc(feedbackRoot.get(Feedback_.datestamp)));

        return getEntityManager().createQuery(criteria).getResultList();
    }
}
