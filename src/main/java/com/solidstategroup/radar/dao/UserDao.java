package com.solidstategroup.radar.dao;

import com.solidstategroup.radar.model.filter.PatientUserFilter;
import com.solidstategroup.radar.model.filter.ProfessionalUserFilter;
import com.solidstategroup.radar.model.user.AdminUser;
import com.solidstategroup.radar.model.user.PatientUser;
import com.solidstategroup.radar.model.user.ProfessionalUser;
import com.solidstategroup.radar.model.user.User;

import java.util.List;

public interface UserDao {

    public<T extends User> T getUser(String email);

    AdminUser getAdminUser(String email);

    AdminUser getAdminUser(Long id);

    List<AdminUser> getAdminUsers();

    void saveAdminUser(AdminUser adminUser) throws Exception;

    PatientUser getPatientUser(Long id);

    PatientUser getPatientUser(String email);

    PatientUser getPatientUserWithUsername(String username);

    // Only use for testing purposes!
    void createRawUser(String username, String password, String name, String email);

    PatientUser getExternallyCreatedPatientUser(String nhsno);

    List<PatientUser> getPatientUsers(PatientUserFilter filter, int page, int numberPerPage);

    void savePatientUser(PatientUser patientUser) throws Exception;

    void deletePatientUser(PatientUser patientUser) throws Exception;

    ProfessionalUser getProfessionalUser(Long id);

    ProfessionalUser getProfessionalUser(String email);

    User getSuperUser(String email);

    void saveProfessionalUser(ProfessionalUser professionalUser) throws Exception;

    void deleteProfessionalUser(ProfessionalUser professionalUser) throws Exception;

    List<ProfessionalUser> getProfessionalUsers(ProfessionalUserFilter filter, int page, int numberPerPage);

    boolean userExistsInPatientView(String nhsno);
}
