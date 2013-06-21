package org.patientview.radar.service;

import org.patientview.radar.model.Centre;
import org.patientview.radar.model.Consultant;
import org.patientview.radar.model.Clinician;
import org.patientview.radar.model.Country;
import org.patientview.radar.model.DiagnosisCode;
import org.patientview.radar.model.Ethnicity;
import org.patientview.radar.model.Relative;
import org.patientview.radar.model.filter.ConsultantFilter;
import org.jfree.chart.JFreeChart;

import java.util.List;
import java.util.Map;

public interface UtilityManager {

    String getSiteUrl();

    String getPatientViewSiteUrl();

    Centre getCentre(long id);

    List<Centre> getCentres();

    Consultant getConsultant(long id);

    List<Consultant> getConsultants();

    List<Consultant> getConsultants(ConsultantFilter filter);

    List<Consultant> getConsultants(ConsultantFilter filter, int page, int numberPerPage);

    List<Consultant> getConsultantsByCentre(Centre centre);

    void saveConsultant(Consultant consultant) throws Exception;

    void deleteConsultant(Consultant consultant) throws Exception;

    Country getCountry(long id);

    List<Country> getCountries();

    Ethnicity getEthnicityByCode(String ethnicityCode);

    List<Ethnicity> getEthnicities();

    Relative getRelative(long id);

    List<Relative> getRelatives();

    Map<Long, Integer> getPatientCountPerUnitByDiagnosisCode(DiagnosisCode diagnosisCode);

    int getPatientCountByUnit(Centre centre);

    JFreeChart getPatientCountPerUnitChart();

    List<Clinician> getCliniciansByCentre(Centre centre);
}
