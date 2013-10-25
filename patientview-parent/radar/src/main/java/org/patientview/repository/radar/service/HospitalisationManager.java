package org.patientview.repository.radar.service;

import org.patientview.model.radar.Hospitalisation;

import java.util.List;

public interface HospitalisationManager {

    void saveHospitilsation(Hospitalisation hospitalisation);

    Hospitalisation getHospitalisation(long id);

    List<Hospitalisation> getHospitalisationsByRadarNumber(long radarNumber);

}
