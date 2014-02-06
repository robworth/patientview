/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.radar.dao;

import org.patientview.model.Centre;
import org.patientview.model.Clinician;
import org.patientview.model.Country;
import org.patientview.model.Ethnicity;
import org.patientview.radar.model.Consultant;
import org.patientview.radar.model.Relative;
import org.patientview.radar.model.DiagnosisCode;
import org.patientview.radar.model.filter.ConsultantFilter;

import java.util.List;
import java.util.Map;

public interface UtilityDao {

    Centre getCentre(long id);

    List<Centre> getCentres();

    Consultant getConsultant(long id);

    void deleteUnit(String unitCode);

    void deletePatientViewUser(String username);

    void deletePatientViewMapping(String username);

    void createUnit(String unitCode);

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

    Clinician getClinician(Long id);

    List<Clinician> getClinicians(Centre centre);

    Centre getCentre(String unitCode);

    List<Centre> getCentres(String nhsNo);

    String getUserName(String nhsNo);

    boolean isGroupAdmin(String username);

    String getUserName(Long id);

    String getUserFullName(Long id);

    List<Centre> getRenalUnitCentre(String nhsNo);

    void deletePatient(String nshNo);

    void deletePatientForRadar(Long radarId);
}
