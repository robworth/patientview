package com.worthsoln.repository;

import com.worthsoln.patientview.Panel;
import com.worthsoln.patientview.model.Comment;

import java.util.List;

/**
 *
 */
public interface CommentDao {

    Comment get(Long id);

    List<Comment> get(String nhsno);

    List<Comment> get(String nhsno, Panel currentPanel);

    void save(Comment comment);
}
