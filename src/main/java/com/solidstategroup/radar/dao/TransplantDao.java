package com.solidstategroup.radar.dao;

import com.solidstategroup.radar.model.Transplant;

import java.util.List;

public interface TransplantDao {

    Transplant getTransplant(long id);

    List<Transplant> getTransplantsByRadarNumber(long radarNumber);

}
