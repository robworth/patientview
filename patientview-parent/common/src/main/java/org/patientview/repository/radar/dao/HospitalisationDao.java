package org.patientview.repository.radar.dao;

import org.patientview.model.radar.Hospitalisation;

import java.util.List;

public interface HospitalisationDao {

    void saveHospitilsation(Hospitalisation hospitalisation);

    Hospitalisation getHospitalisation(long id);

    List<Hospitalisation> getHospitalisationsByRadarNumber(long radarNumber);

}
