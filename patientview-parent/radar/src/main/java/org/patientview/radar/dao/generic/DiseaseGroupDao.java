package org.patientview.radar.dao.generic;

import org.patientview.model.generic.DiseaseGroup;

import java.util.List;

public interface DiseaseGroupDao {

    List<DiseaseGroup> getAll();

    DiseaseGroup getById(String id);

}
