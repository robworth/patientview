package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.Letter;
import com.worthsoln.repository.LetterDao;
import com.worthsoln.service.LetterManager;
import com.worthsoln.service.SecurityUserManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Service(value = "letterManager")
public class LetterManagerImpl implements LetterManager {

    @Inject
    private LetterDao letterDao;

    @Inject
    private SecurityUserManager securityUserManager;

    @Override
    public Letter get(Long id) {
        return letterDao.get(id);
    }

    @Override
    public void save(Letter letter) {
        letterDao.save(letter);
    }

    @Override
    public List<Letter> get(String username) {
        return letterDao.get(username, securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public List<Letter> getAll() {
        return letterDao.getAll();
    }

    @Override
    public void delete(String nhsno, String unitcode, Date date) {
        letterDao.delete(nhsno, unitcode, date);
    }

    @Override
    public void delete(String nhsno, String unitcode) {
        letterDao.delete(nhsno, unitcode);
    }
}
