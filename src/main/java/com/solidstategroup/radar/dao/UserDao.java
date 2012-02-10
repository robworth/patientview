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
    
    List<ProfessionalUser> getProfessionalUsers();
    
    List<ProfessionalUser> getProfessionalUsers(ProfessionalUserFilter filter);
    
    List<ProfessionalUser> getProfessionalUsers(ProfessionalUserFilter filter, int page, int numberPerPage);
}
