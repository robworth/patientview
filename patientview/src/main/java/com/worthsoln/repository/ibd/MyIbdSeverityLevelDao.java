package com.worthsoln.repository.ibd;

import com.worthsoln.ibd.model.MyIbdSeverityLevel;
import com.worthsoln.ibd.model.enums.Severity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.MANDATORY)
public interface MyIbdSeverityLevelDao {

    MyIbdSeverityLevel get(Long id);

    MyIbdSeverityLevel get(String nhsno, Severity severity);

    void save(List<MyIbdSeverityLevel> myIbdSeverityLevels);

    void save(MyIbdSeverityLevel myIbdSeverityLevel);
}
