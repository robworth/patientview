package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.EdtaCode;
import com.worthsoln.repository.EdtaCodeDao;
import com.worthsoln.service.EdtaCodeManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 *
 */
@Service(value = "edtaCodeManager")
public class EdtaCodeManagerImpl implements EdtaCodeManager {

    @Inject
    private EdtaCodeDao edtaCodeDao;

    @Override
    public EdtaCode getEdtaCode(String edtaCode) {
        return edtaCodeDao.getEdtaCode(edtaCode);
    }

    @Override
    public void save(EdtaCode edtaCode) {
        edtaCodeDao.save(edtaCode);
    }

    @Override
    public void delete(String edtaCode) {
        edtaCodeDao.delete(edtaCode);
    }

    @Override
    public List<EdtaCode> get(String linkType) {
        return edtaCodeDao.get(linkType);
    }
}
