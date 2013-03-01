package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.Centre;
import com.worthsoln.repository.CentreDao;
import com.worthsoln.service.CentreManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 *
 */
@Service(value = "centreManager")
public class CentreManagerImpl implements CentreManager {

    @Inject
    private CentreDao centreDao;

    @Override
    public void save(Centre centre) {
        centreDao.save(centre);
    }

    @Override
    public void delete(Long id) {
        centreDao.delete(id);
    }

    @Override
    public void delete(String centreCode) {
        centreDao.delete(centreCode);
    }

    @Override
    public List<Centre> getAll() {
        return centreDao.getAll();
    }
}
