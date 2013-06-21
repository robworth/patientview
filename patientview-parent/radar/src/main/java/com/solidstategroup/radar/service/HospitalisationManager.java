package com.solidstategroup.radar.service;

import com.solidstategroup.radar.model.Hospitalisation;

import java.util.List;

public interface HospitalisationManager {

    void saveHospitilsation(Hospitalisation hospitalisation);

    Hospitalisation getHospitalisation(long id);

    List<Hospitalisation> getHospitalisationsByRadarNumber(long radarNumber);

}
