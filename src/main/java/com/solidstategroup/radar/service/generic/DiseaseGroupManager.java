package com.solidstategroup.radar.service.generic;

import com.solidstategroup.radar.model.generic.DiseaseGroup;

import java.util.List;

public interface DiseaseGroupManager {

    List<DiseaseGroup> getAll();

    DiseaseGroup getById(String id);

}
