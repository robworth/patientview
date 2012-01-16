package com.solidstategroup.radar.web.models;


import com.solidstategroup.radar.dao.DemographicsDao;
import com.solidstategroup.radar.dao.DiagnosisDao;
import com.solidstategroup.radar.model.DiagnosisCode;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.io.Serializable;

public class RadarModelFactory {


    public static IModel getDiagnosisCodeModel(final IModel<Long> radarNumberModel, final DiagnosisDao diagnosisDao) {
        return new Model<DiagnosisCode>() {
            @Override
            public DiagnosisCode getObject() {
                try {
                    return radarNumberModel.getObject() != null ?
                            diagnosisDao.getDiagnosisByRadarNumber(radarNumberModel.getObject()).getDiagnosisCode() : null;
                } catch (ClassCastException e) {
                    Object obj = radarNumberModel.getObject();
                    return obj != null ? diagnosisDao.getDiagnosisByRadarNumber(
                            Long.parseLong((String)obj)).getDiagnosisCode() : null;
                }

            }
        };
    }

    public static IModel getFirstNameModel(final IModel<Long> radarNumberModel, final DemographicsDao demographicsDao) {
        return new Model() {
            @Override
            public Serializable getObject() {
                try {
                    return radarNumberModel.getObject() != null ? demographicsDao.getDemographicsByRadarNumber(
                            radarNumberModel.getObject()).getForename() : null;
                } catch (ClassCastException e) {
                    Object obj = radarNumberModel.getObject();
                    return obj != null ? demographicsDao.getDemographicsByRadarNumber(Long.parseLong((String) obj)).
                            getForename() : null;
                }
            }
        };
    }

    public static IModel getSurnameModel(final IModel<Long> radarNumberModel, final DemographicsDao demographicsDao) {
        return new Model() {
            @Override
            public Serializable getObject() {
                try {
                    return radarNumberModel.getObject() != null ? demographicsDao.getDemographicsByRadarNumber(
                            radarNumberModel.getObject()).getSurname() : null;
                } catch (ClassCastException e) {
                    Object obj = radarNumberModel.getObject();
                    return obj != null ? demographicsDao.getDemographicsByRadarNumber(Long.parseLong((String) obj)).
                            getSurname() : null;
                }
            }
        };
    }

    public static IModel getDobModel(final IModel<Long> radarNumberModel, final DemographicsDao demographicsDao) {
        return new Model() {
            @Override
            public Serializable getObject() {
                try {
                    return radarNumberModel.getObject() != null ? demographicsDao.getDemographicsByRadarNumber(
                            radarNumberModel.getObject()).getDateOfBirth() : null;
                } catch (ClassCastException e) {
                    Object obj = radarNumberModel.getObject();
                    return obj != null ? demographicsDao.getDemographicsByRadarNumber(Long.parseLong((String) obj)).
                            getDateOfBirth() : null;
                }
            }

        };
    }

    public static IModel<String> getHospitalNumberModel(final IModel<Long> radarNumberModel, final DemographicsDao demographicsDao) {
        return new Model<String>() {
            @Override
            public String getObject() {
                try {
                    return radarNumberModel.getObject() != null ? demographicsDao.getDemographicsByRadarNumber(
                            radarNumberModel.getObject()).getHospitalNumber() : null;
                } catch (ClassCastException e) {
                    Object obj = radarNumberModel.getObject();
                    return obj != null ? demographicsDao.getDemographicsByRadarNumber(Long.parseLong((String) obj)).
                            getHospitalNumber() : null;
                }
            }
        };
    }
}
