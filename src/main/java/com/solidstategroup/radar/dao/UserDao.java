package com.solidstategroup.radar.dao;

import com.solidstategroup.radar.model.user.AdminUser;
import com.solidstategroup.radar.model.user.PatientUser;
import com.solidstategroup.radar.model.user.ProfessionalUser;

public interface UserDao {

    AdminUser getAdminUser(String email);

    PatientUser getPatientUser(String email);

    void savePatientUser(PatientUser patientUser);

    ProfessionalUser getProfessionalUser(String email);
}
