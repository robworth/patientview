package com.worthsoln.service.ibd;

import com.worthsoln.ibd.model.MyIbd;
import com.worthsoln.patientview.model.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface IbdManager {

    MyIbd getMyIbd(String nhsno);

    MyIbd getMyIbd(User user);

    void saveMyIbd(MyIbd myIbd);
}
