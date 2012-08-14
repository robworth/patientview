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
        return edtaCodeDao.getEdtaCode(edtaCode, securityUserManager.getLoggedInTenancy());
    }

    @Override
    public void save(EdtaCode edtaCode) {

        // set the tenancy against the code if not already set
        if (edtaCode.getTenancy() == null) {
            edtaCode.setTenancy(securityUserManager.getLoggedInTenancy());
        }

        edtaCodeDao.save(edtaCode);
    }

    @Override
    public void delete(String edtaCode) {
        edtaCodeDao.delete(edtaCode, securityUserManager.getLoggedInTenancy());
    }

    @Override
    public List<EdtaCode> get(String linkType) {
        return edtaCodeDao.get(linkType, securityUserManager.getLoggedInTenancy());
    }
}
