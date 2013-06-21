package com.solidstategroup.radar.dao.generic;

import com.solidstategroup.radar.model.generic.DiseaseGroup;

import java.util.List;

public interface DiseaseGroupDao {

    List<DiseaseGroup> getAll();

    DiseaseGroup getById(String id);

}
