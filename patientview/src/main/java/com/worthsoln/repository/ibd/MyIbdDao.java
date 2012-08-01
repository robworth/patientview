package com.worthsoln.repository.ibd;

import com.worthsoln.ibd.model.MyIbd;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.MANDATORY)
public interface MyIbdDao {

    MyIbd get(Long id);

    MyIbd get(String nhsno);

    void save(MyIbd myIbd);
}
