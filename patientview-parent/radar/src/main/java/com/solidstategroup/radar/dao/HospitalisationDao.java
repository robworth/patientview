package com.solidstategroup.radar.dao;

import com.solidstategroup.radar.model.Hospitalisation;

import java.util.List;

public interface HospitalisationDao {

    void saveHospitilsation(Hospitalisation hospitalisation);

    Hospitalisation getHospitalisation(long id);

    List<Hospitalisation> getHospitalisationsByRadarNumber(long radarNumber);

}
