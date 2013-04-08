package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.EdtaCode;
import com.worthsoln.repository.EdtaCodeDao;
import com.worthsoln.service.EdtaCodeManager;
import com.worthsoln.service.SecurityUserManager;
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

    @Inject
    private SecurityUserManager securityUserManager;

    @Override
    public EdtaCode getEdtaCode(String edtaCode) {
        return edtaCodeDao.getEdtaCode(edtaCode, securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public void save(EdtaCode edtaCode) {

        // set the Specialty against the code if not already set
        if (edtaCode.getSpecialty() == null) {
            edtaCode.setSpecialty(securityUserManager.getLoggedInSpecialty());
        }

        edtaCodeDao.save(edtaCode);
    }

    @Override
    public void delete(String edtaCode) {
        edtaCodeDao.delete(edtaCode, securityUserManager.getLoggedInSpecialty());
    }

    @Override
    public List<EdtaCode> get(String linkType) {
        return edtaCodeDao.get(linkType, securityUserManager.getLoggedInSpecialty());
    }
}
