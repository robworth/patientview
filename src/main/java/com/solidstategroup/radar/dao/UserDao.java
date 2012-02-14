package com.solidstategroup.radar.dao;

import com.solidstategroup.radar.model.filter.ProfessionalUserFilter;
import com.solidstategroup.radar.model.user.AdminUser;
import com.solidstategroup.radar.model.user.PatientUser;
import com.solidstategroup.radar.model.user.ProfessionalUser;

import java.util.List;

public interface UserDao {

    AdminUser getAdminUser(String email);

    PatientUser getPatientUser(String email);

    void savePatientUser(PatientUser patientUser);

    ProfessionalUser getProfessionalUser(Long id);

    ProfessionalUser getProfessionalUser(String email);

    void saveProfessionalUser(ProfessionalUser professionalUser) throws Exception;

    void deleteProfessionalUser(ProfessionalUser professionalUser) throws Exception;

    List<ProfessionalUser> getProfessionalUsers(ProfessionalUserFilter filter, int page, int numberPerPage);
}
