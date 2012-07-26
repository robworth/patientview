package com.worthsoln.service;

import com.worthsoln.patientview.Panel;
import com.worthsoln.patientview.model.Comment;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface CommentManager {

    Comment get(Long id);

    List<Comment> get(String nhsno);

    void save(Comment comment);
}
