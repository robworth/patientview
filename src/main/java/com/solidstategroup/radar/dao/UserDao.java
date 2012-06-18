package com.solidstategroup.radar.dao;

import com.solidstategroup.radar.model.filter.PatientUserFilter;
import com.solidstategroup.radar.model.filter.ProfessionalUserFilter;
import com.solidstategroup.radar.model.user.AdminUser;
import com.solidstategroup.radar.model.user.PatientUser;
import com.solidstategroup.radar.model.user.ProfessionalUser;

import java.util.List;

public interface UserDao {

    AdminUser getAdminUser(String email);

    PatientUser getPatientUser(Long id);

    PatientUser getPatientUser(String email);

    List<PatientUser> getPatientUsers(PatientUserFilter filter, int page, int numberPerPage);

    void savePatientUser(PatientUser patientUser) throws Exception;

    void deletePatientUser(PatientUser patientUser) throws Exception;

    ProfessionalUser getProfessionalUser(Long id);

    ProfessionalUser getProfessionalUser(String email);

    void saveProfessionalUser(ProfessionalUser professionalUser) throws Exception;

    void deleteProfessionalUser(ProfessionalUser professionalUser) throws Exception;

    List<ProfessionalUser> getProfessionalUsers(ProfessionalUserFilter filter, int page, int numberPerPage);
}
