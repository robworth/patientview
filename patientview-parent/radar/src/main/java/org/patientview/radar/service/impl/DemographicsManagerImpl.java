package org.patientview.radar.service.impl;

import org.patientview.model.Patient;
import org.patientview.model.Sex;
import org.patientview.model.Status;
import org.patientview.radar.dao.DemographicsDao;
import org.patientview.radar.dao.UnitDao;
import org.patientview.radar.dao.UserDao;
import org.patientview.radar.model.filter.DemographicsFilter;
import org.patientview.radar.model.user.DemographicsUserDetail;
import org.patientview.radar.model.user.User;
import org.patientview.radar.service.DemographicsManager;

import java.util.List;

public class DemographicsManagerImpl implements DemographicsManager {


    private DemographicsDao demographicsDao;

    private UserDao userDao;

    private UnitDao unitDao;

    public void saveDemographics(Patient patient) {
        // Save or update the demographics object
        demographicsDao.saveDemographics(patient);
    }

    public Patient get(Long id) {
        return demographicsDao.get(id);
    }

    public Patient getDemographicsByRadarNumber(long radarNumber) {
        return demographicsDao.getDemographicsByRadarNumber(radarNumber);
    }

    public Patient getDemographicsByNhsNoAndUnitCode(String nhsNo, String unitCode) {
        return demographicsDao.getDemographicsByNhsNoAndUnitCode(nhsNo, unitCode);
    }

    public List<Patient> getDemographics() {
        return getDemographics(new DemographicsFilter(), -1, -1);
    }

    public List<Patient> getDemographics(DemographicsFilter filter) {
        return getDemographics(filter, -1, -1);
    }

    public List<Patient> getDemographics(DemographicsFilter filter, int page, int numberPerPage) {
        return demographicsDao.getDemographics(filter, page, numberPerPage);
    }

    public Sex getSex(long id) {
        return demographicsDao.getSex(id);
    }

    public List<Sex> getSexes() {
        return demographicsDao.getSexes();
    }

    public Status getStatus(long id) {
        return demographicsDao.getStatus(id);
    }

    public List<Patient> getDemographicsByUser(User user) {

        List<String> unitCodes;
        if (user.getSecurityRole().equals(User.ROLE_SUPER_USER)) {
            unitCodes = unitDao.getAllUnitCodes();
        } else {
            unitCodes = unitDao.getUnitCodes(user);
        }

        return demographicsDao.getDemographicsByUnitCode(unitCodes);
    }

    public List<Status> getStatuses() {
        return demographicsDao.getStatuses();
    }

    public DemographicsDao getDemographicsDao() {
        return demographicsDao;
    }

    public void setDemographicsDao(DemographicsDao demographicsDao) {
        this.demographicsDao = demographicsDao;
    }

    public DemographicsUserDetail getDemographicsUserDetail(String nhsno, String unitcode) {
        return demographicsDao.getDemographicsUserDetail(nhsno, unitcode);
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setUnitDao(UnitDao unitDao) {
        this.unitDao = unitDao;
    }
}
