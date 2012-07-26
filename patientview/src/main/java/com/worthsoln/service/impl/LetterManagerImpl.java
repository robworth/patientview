package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.Letter;
import com.worthsoln.repository.LetterDao;
import com.worthsoln.service.LetterManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 *
 */
@Service(value = "letterManager")
public class LetterManagerImpl implements LetterManager {

    @Inject
    private LetterDao letterDao;

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
        return letterDao.get(username);
    }
}
