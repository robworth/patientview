package com.worthsoln.repository.impl;

import com.worthsoln.patientview.Panel;
import com.worthsoln.patientview.model.Comment;
import com.worthsoln.patientview.model.Comment_;
import com.worthsoln.repository.AbstractHibernateDAO;
import com.worthsoln.repository.CommentDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository(value = "commentDao")
public class CommentDaoImpl extends AbstractHibernateDAO<Comment> implements CommentDao {

    @Override
    public List<Comment> get(String nhsno) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Comment> criteria = builder.createQuery(Comment.class);
        Root<Comment> commentRoot = criteria.from(Comment.class);

        criteria.where(builder.equal(commentRoot.get(Comment_.nhsno), nhsno));

        return getEntityManager().createQuery(criteria).getResultList();
    }
}
