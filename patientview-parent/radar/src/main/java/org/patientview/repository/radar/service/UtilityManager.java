package org.patientview.repository.radar.service;

import org.patientview.model.Centre;
import org.patientview.model.Clinician;
import org.patientview.model.Country;
import org.patientview.model.Ethnicity;
import org.patientview.model.radar.Consultant;
import org.patientview.model.radar.DiagnosisCode;
import org.patientview.model.radar.Relative;
import org.patientview.model.radar.filter.ConsultantFilter;
import org.jfree.chart.JFreeChart;

import java.util.List;
import java.util.Map;

public interface UtilityManager {

    String getSiteUrl();

    String getPatientViewSiteUrl();

    String getPatientViewSiteResultsUrl();

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

    List<Centre> getCentres(String nhsNo);

    String getUserName(String nhsNo);

    void generateUserWithUsermapping();
}
