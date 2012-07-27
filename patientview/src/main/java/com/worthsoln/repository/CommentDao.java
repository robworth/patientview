package com.worthsoln.repository;

import com.worthsoln.patientview.Panel;
import com.worthsoln.patientview.model.Comment;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.MANDATORY)
public interface CommentDao {

    Comment get(Long id);

    List<Comment> get(String nhsno);

    void save(Comment comment);
}
