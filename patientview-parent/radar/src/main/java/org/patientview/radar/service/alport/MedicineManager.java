package org.patientview.radar.service.alport;

import org.patientview.model.Patient;
import org.patientview.model.generic.DiseaseGroup;
import org.patientview.radar.model.alport.Medicine;

import java.util.List;

public interface MedicineManager {

    void save(Medicine medicine);

    void delete(Medicine medicine);

    Medicine get(Long id);

    List<Medicine> getMedicines(Patient patient);

    List<Medicine> getMedicines(Patient patient, DiseaseGroup diseaseGroup);
}
