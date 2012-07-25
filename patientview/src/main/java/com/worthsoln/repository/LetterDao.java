package com.worthsoln.repository;

import com.worthsoln.patientview.model.Letter;

import java.util.List;

public interface LetterDao {

    Letter get(Long id);

    void save(Letter letter);

    List<Letter> get(String username);
}
