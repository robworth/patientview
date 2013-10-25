package org.patientview.repository.radar.service.generic;

import org.patientview.model.generic.DiseaseGroup;

import java.util.List;

public interface DiseaseGroupManager {

    List<DiseaseGroup> getAll();

    DiseaseGroup getById(String id);

}
