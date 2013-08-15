package org.patientview.radar.service.generic;

import org.patientview.radar.model.generic.DiseaseGroup;

import java.util.List;

public interface DiseaseGroupManager {

    List<DiseaseGroup> getAll();

    DiseaseGroup getById(String id);

}
