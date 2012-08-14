package com.worthsoln.repository.ibd;

import com.worthsoln.ibd.model.Colitis;
import java.util.List;

public interface ColitisDao {

    Colitis get(Long id);

    void save(Colitis colitis);

    List<Colitis> getAllColitis(String nhsno);

}
