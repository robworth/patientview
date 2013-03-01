package com.worthsoln.repository.ibd;

import com.worthsoln.ibd.model.MyIbd;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.MANDATORY)
public interface MyIbdDao {

    MyIbd get(Long id);

    MyIbd get(String nhsno);

    MyIbd get(String nhsno, String unitcode);

    void save(MyIbd myIbd);

    void delete(String nhsno, String unitcode);
}
