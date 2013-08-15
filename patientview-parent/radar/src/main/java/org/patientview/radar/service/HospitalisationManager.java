package org.patientview.radar.service;

import org.patientview.radar.model.Hospitalisation;

import java.util.List;

public interface HospitalisationManager {

    void saveHospitilsation(Hospitalisation hospitalisation);

    Hospitalisation getHospitalisation(long id);

    List<Hospitalisation> getHospitalisationsByRadarNumber(long radarNumber);

}
