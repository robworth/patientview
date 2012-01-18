package com.solidstategroup.radar.web.models;


import com.solidstategroup.radar.dao.DemographicsDao;
import com.solidstategroup.radar.dao.DiagnosisDao;
import com.solidstategroup.radar.model.Demographics;
import com.solidstategroup.radar.model.Diagnosis;
import com.solidstategroup.radar.model.DiagnosisCode;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RadarModelFactory {


    public static IModel getDiagnosisCodeModel(final IModel<Long> radarNumberModel, final DiagnosisDao diagnosisDao) {
        return new LoadableDetachableModel<DiagnosisCode>() {
            @Override
            public DiagnosisCode load() {
                Long radarNumber;
                if (radarNumberModel.getObject() != null) {
                    try {
                        radarNumber = radarNumberModel.getObject();
                    } catch (ClassCastException e) {
                        Object obj = radarNumberModel.getObject();
                        Long.parseLong((String) obj);
                    }

                    Diagnosis diagnosis = diagnosisDao.getDiagnosisByRadarNumber(radarNumberModel.getObject());
                    if (diagnosis != null) {
                        return diagnosis.getDiagnosisCode();
                    } else {
                        return null;
                    }

                } else {
                    return null;
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


    public static IModel getSuccessMessageModel(final Form form) {
       return new LoadableDetachableModel() {
           @Override
           protected Object load() {
               return form.hasError() ? "" : "Save was successful: " + new SimpleDateFormat("h:m:s").format(new Date());
           }
       };
    }
}
