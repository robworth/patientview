package com.worthsoln.repository;

import com.worthsoln.patientview.model.SharedThought;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.MANDATORY)
public interface SharedThoughtDao {

    SharedThought get(Long id);

    List<SharedThought> getUsersThoughts(Long userId, boolean isSubmitted);

    void save(SharedThought thought);

    void delete(Long id);
}
