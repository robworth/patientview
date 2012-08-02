package com.worthsoln.service.ibd.impl;

import com.worthsoln.ibd.model.CarePlan;
import com.worthsoln.ibd.model.MyIbd;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.UserMapping;
import com.worthsoln.repository.ibd.CarePlanDao;
import com.worthsoln.repository.ibd.MyIbdDao;
import com.worthsoln.service.UserManager;
import com.worthsoln.service.ibd.IbdManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(value = "ibdManager")
public class IbdManagerImpl implements IbdManager {

    @Inject
    MyIbdDao myIbdDao;

    @Inject
    CarePlanDao carePlanDao;

    @Inject
    UserManager userManager;

    @Override
    public MyIbd getMyIbd(String nhsno) {
        return myIbdDao.get(nhsno);
    }

    @Override
    public MyIbd getMyIbd(User user) {
        String nhsNo = getNhsNumber(user);

        if (nhsNo != null) {
            return myIbdDao.get(nhsNo);
        }

        return null;
    }

    @Override
    public void saveMyIbd(MyIbd myIbd) {
        myIbdDao.save(myIbd);
    }

    @Override
    public CarePlan getCarePlan(String nhsno) {
        return carePlanDao.get(nhsno);
    }

    @Override
    public CarePlan getCarePlan(User user) {
        String nhsNo = getNhsNumber(user);

        if (nhsNo != null) {
            return carePlanDao.get(nhsNo);
        }

        return null;
    }

    @Override
    public void saveCarePlan(CarePlan carePlan) {
        carePlanDao.save(carePlan);
    }

    private String getNhsNumber(User user) {
        UserMapping userMapping = userManager.getUserMappingPatientEntered(user);

        if (userMapping != null) {
            return userMapping.getNhsno();
        }

        return null;
    }
}
