package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.ResultHeading;
import com.worthsoln.repository.ResultHeadingDao;
import com.worthsoln.service.ResultHeadingManager;
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

    @Override
    public ResultHeading get(String headingcode) {
        return resultHeadingDao.get(headingcode);
    }

    @Override
    public List<ResultHeading> getAll() {
        return resultHeadingDao.getAll();
    }

    @Override
    public void save(ResultHeading resultHeading) {
        resultHeadingDao.save(resultHeading);
    }

    @Override
    public void delete(String headingCode) {
        resultHeadingDao.delete(headingCode);
    }
}
