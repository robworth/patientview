package com.worthsoln.service;

import com.worthsoln.patientview.model.SharedThought;

import java.util.List;

public interface SharedThoughtManager {

    SharedThought getSharedThought(Long sharedThoughtId);

    List<SharedThought> getUsersThoughts(Long userId, boolean isSubmitted);

    void save(SharedThought thought);

    void delete(Long sharedThoughtId);
}
