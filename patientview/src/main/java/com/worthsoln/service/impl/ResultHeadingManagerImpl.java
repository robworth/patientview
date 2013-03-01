package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.Panel;
import com.worthsoln.patientview.model.ResultHeading;
import com.worthsoln.repository.ResultHeadingDao;
import com.worthsoln.service.ResultHeadingManager;
import com.worthsoln.service.SecurityUserManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 *
 */
@Service(value = "resultHeadingManager")
public class ResultHeadingManagerImpl implements ResultHeadingManager {

    @Inject
    private ResultHeadingDao resultHeadingDao;

    @Inject
    private SecurityUserManager securityUserManager;

    @Override
    public ResultHeading get(String headingcode) {
        return resultHeadingDao.get(headingcode, securityUserManager.getLoggedInTenancy());
    }

    @Override
    public List<ResultHeading> getAll() {
        return resultHeadingDao.getAll(securityUserManager.getLoggedInTenancy());
    }

    @Override
    public List<ResultHeading> get(int panel) {
        return resultHeadingDao.get(panel, securityUserManager.getLoggedInTenancy());
    }

    @Override
    public void save(ResultHeading resultHeading) {

        // set the tenancy against the heading if not already set
        if (resultHeading.getTenancy() == null) {
            resultHeading.setTenancy(securityUserManager.getLoggedInTenancy());
        }

        resultHeadingDao.save(resultHeading);
    }

    @Override
    public void delete(String headingCode) {
        resultHeadingDao.delete(headingCode, securityUserManager.getLoggedInTenancy());
    }

    @Override
    public List<Panel> getPanels() {
        return resultHeadingDao.getPanels(securityUserManager.getLoggedInTenancy());
    }
}
