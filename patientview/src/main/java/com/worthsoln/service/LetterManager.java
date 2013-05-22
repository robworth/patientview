package com.worthsoln.service;

import com.worthsoln.patientview.model.Letter;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.REQUIRED)
public interface LetterManager {

    Letter get(Long id);

    void save(Letter letter);

    List<Letter> get(String username);

    List<Letter> getAll();

    void delete(String nhsno, String unitcode, Date date);

    void delete(String nhsno, String unitcode);

    void delete(Long id);
}
