package org.patientview.radar.dao.alport;

import org.patientview.model.generic.DiseaseGroup;
import org.patientview.radar.model.alport.Medicine;

import java.util.List;

public interface MedicineDao {

    void save(Medicine medicine);

    void delete(Medicine medicine);

    Medicine get(Long id);

    List<Medicine> getMedicinesByNhsNo(String nhsNo);

    List<Medicine> getMedicinesByNhsNoAndDiseaseGroup(String nhsNo, DiseaseGroup diseaseGroup);
}
