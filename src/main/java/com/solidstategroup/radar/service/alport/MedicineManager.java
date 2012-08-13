package com.solidstategroup.radar.service.alport;

import com.solidstategroup.radar.model.alport.Medicine;

import java.util.List;

public interface MedicineManager {

    void save(Medicine medicine);

    void delete(Medicine medicine);

    Medicine get(Long id);

    List<Medicine> getMedicines(String nhsNo);
}
