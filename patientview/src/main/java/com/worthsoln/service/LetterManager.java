package com.worthsoln.service;

import com.worthsoln.patientview.model.Letter;

import java.util.List;

/**
 *
 */
public interface LetterManager {

    Letter get(Long id);

    void save(Letter letter);

    List<Letter> get(String username);
}
