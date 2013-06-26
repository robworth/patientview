package org.patientview.radar.service.impl;

import org.patientview.radar.dao.PlasmapheresisDao;
import org.patientview.radar.model.Demographics;
import org.patientview.radar.model.Plasmapheresis;
import org.patientview.radar.model.PlasmapheresisExchangeUnit;
import org.patientview.radar.model.exception.InvalidModelException;
import org.patientview.radar.service.DemographicsManager;
import org.patientview.radar.service.PlasmapheresisManager;
import org.patientview.radar.service.TreatmentManager;
import org.patientview.radar.util.RadarUtility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class PlasmapheresisManagerImpl implements PlasmapheresisManager {

    private PlasmapheresisDao plasmapheresisDao;
    DemographicsManager demographicsManager;

    public void savePlasmapheresis(Plasmapheresis plasmapheresis) throws InvalidModelException {
        // validation
        List<String> errors = new ArrayList<String>();
        List<Plasmapheresis> plasmapheresisList = plasmapheresisDao.
                getPlasmapheresisByRadarNumber(plasmapheresis.getRadarNumber());

        //  must have finish date before you can start it again
        for (Plasmapheresis existingPlasmapheresis : plasmapheresisList) {
            if (existingPlasmapheresis.getId().equals(plasmapheresis.getId())) {
                continue;
            }
            if (existingPlasmapheresis.getEndDate() == null) {
                errors.add(TreatmentManager.PREVIOUS_TREATMENT_NOT_CLOSED_ERROR);
                break;
            }
        }

        // dates must not overlap
        for (Plasmapheresis existingPlasmapheresis : plasmapheresisList) {
            if (existingPlasmapheresis.getId().equals(plasmapheresis.getId())) {
                continue;
            }
            if (RadarUtility.isEventsOverlapping(existingPlasmapheresis.getStartDate(),
                    existingPlasmapheresis.getEndDate(), plasmapheresis.getStartDate(),
                    plasmapheresis.getEndDate())) {
                errors.add(TreatmentManager.OVERLAPPING_ERROR);
                break;
            }

        }

        List<Date> datesToCheck = Arrays.asList(plasmapheresis.getStartDate(), plasmapheresis.getEndDate());

        // cannot be before date of birth
        Demographics demographics = demographicsManager.getDemographicsByRadarNumber(plasmapheresis.getRadarNumber());
        if (demographics != null) {
            Date dob = demographics.getDateOfBirth();
            if (dob != null) {
                for (Date date : datesToCheck) {
                    if (date != null) {
                        if (dob.compareTo(date) > 0) {
                            errors.add(TreatmentManager.BEFORE_DOB_ERROR);
                            break;
                        }
                    }
                }

            }
        }

        // cannot be after today
        Date today = new Date();
        for (Date date : datesToCheck) {
            if (date != null) {
                if (today.compareTo(date) < 0) {
                    errors.add(TreatmentManager.AFTER_TODAY_ERROR);
                    break;
                }
            }
        }

        if (!errors.isEmpty()) {
            InvalidModelException exception = new InvalidModelException("plasmapheresis model is not valid");
            exception.setErrors(errors);
            throw exception;
        }
        plasmapheresisDao.savePlasmapheresis(plasmapheresis);
    }

    public void deletePlasmaPheresis(Plasmapheresis plasmapheresis) {
        plasmapheresisDao.deletePlasmaPheresis(plasmapheresis);
    }

    public Plasmapheresis getPlasmapheresis(long id) {
        return plasmapheresisDao.getPlasmapheresis(id);
    }

    public List<Plasmapheresis> getPlasmapheresisByRadarNumber(long radarNumber) {
        return plasmapheresisDao.getPlasmapheresisByRadarNumber(radarNumber);
    }

    public PlasmapheresisExchangeUnit getPlasmapheresisExchangeUnit(long id) {
        return plasmapheresisDao.getPlasmapheresisExchangeUnit(id);
    }

    public List<PlasmapheresisExchangeUnit> getPlasmapheresisExchangeUnits() {
        return plasmapheresisDao.getPlasmapheresisExchangeUnits();
    }

    public PlasmapheresisDao getPlasmapheresisDao() {
        return plasmapheresisDao;
    }

    public void setPlasmapheresisDao(PlasmapheresisDao plasmapheresisDao) {
        this.plasmapheresisDao = plasmapheresisDao;
    }

    public void setDemographicsManager(DemographicsManager demographicsManager) {
        this.demographicsManager = demographicsManager;
    }
}
