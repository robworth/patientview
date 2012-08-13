package com.solidstategroup.radar.dao.alport;

import com.solidstategroup.radar.model.alport.Medicine;

import java.util.List;

public interface MedicineDao {

    void save(Medicine medicine);

    void delete(Medicine medicine);

    Medicine get(Long id);

    List<Medicine> getMedicines(String nhsNo);
}
