package org.patientview.radar.service.alport;

import org.patientview.radar.model.Demographics;
import org.patientview.radar.model.alport.Medicine;
import org.patientview.radar.model.generic.DiseaseGroup;

import java.util.List;

public interface MedicineManager {

    void save(Medicine medicine);

    void delete(Medicine medicine);

    Medicine get(Long id);

    List<Medicine> getMedicines(Demographics demographics);

    List<Medicine> getMedicines(Demographics demographics, DiseaseGroup diseaseGroup);
}
