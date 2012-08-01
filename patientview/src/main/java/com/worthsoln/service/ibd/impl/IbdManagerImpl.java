package com.worthsoln.service.ibd.impl;

import com.worthsoln.ibd.model.MyIbd;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.UserMapping;
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
    UserManager userManager;

    @Override
    public MyIbd getMyIbd(String nhsno) {
        return myIbdDao.get(nhsno);
    }

    @Override
    public MyIbd getMyIbd(User user) {
        UserMapping userMapping = userManager.getUserMappingPatientEntered(user);

        if (userMapping != null) {
            return myIbdDao.get(userMapping.getNhsno());
        }

        return null;
    }

    @Override
    public void saveMyIbd(MyIbd myIbd) {
        myIbdDao.save(myIbd);
    }
}
