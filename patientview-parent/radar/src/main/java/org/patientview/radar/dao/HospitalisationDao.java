package org.patientview.radar.dao;

import org.patientview.radar.model.Hospitalisation;

import java.util.List;

public interface HospitalisationDao {

    void saveHospitilsation(Hospitalisation hospitalisation);

    Hospitalisation getHospitalisation(long id);

    List<Hospitalisation> getHospitalisationsByRadarNumber(long radarNumber);

}
