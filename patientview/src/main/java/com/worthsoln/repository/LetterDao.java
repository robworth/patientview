package com.worthsoln.repository;

import com.worthsoln.patientview.model.Letter;
import com.worthsoln.patientview.model.Specialty;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Transactional(propagation = Propagation.MANDATORY)
public interface LetterDao {

    Letter get(Long id);

    void save(Letter letter);

    List<Letter> get(String username, Specialty specialty);

    List<Letter> getAll();

    void delete(String nhsno, String unitcode, Date date);
}
