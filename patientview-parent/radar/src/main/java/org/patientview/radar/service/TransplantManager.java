package org.patientview.radar.service;

import org.patientview.radar.model.Transplant;
import org.patientview.radar.model.exception.InvalidModelException;

import java.util.Arrays;
import java.util.List;

public interface TransplantManager {

    // transplant data error messages
    public static final String BEFORE_PREVIOUS_FAILURE_DATE = "Cannot add transplant before a previous transplant "
            + "failure date";
    public static final String TRANSPLANTS_INTERVAL_ERROR = "Transplants must be greater than 14 days apart";
    public static final String START_DATE_ERROR = "transplant date must be before recurrance date, failure date, " +
            "reject date and biopsy date";
    public static final String FAILURE_DATE_ERROR = "Date of failure has to be after  reccurance date, reject date " +
            "and " + "biopsy date";
    public static final List<String> ERROR_MESSAGES = Arrays.asList(BEFORE_PREVIOUS_FAILURE_DATE,
            TRANSPLANTS_INTERVAL_ERROR, START_DATE_ERROR, FAILURE_DATE_ERROR);

    // reject data error messages
    public static final String REJECT_DATA_ERROR_MESSAGE = "Reject dates have to be after transplant start date and " +
            "before failure date";
    public static final List<String> REJECT_DATA_ERROR_MESSAGES = Arrays.asList(REJECT_DATA_ERROR_MESSAGE);

    void saveTransplant(Transplant transplant) throws InvalidModelException;

    void deleteTransplant(Transplant transplant);

    Transplant getTransplant(long id);

    List<Transplant> getTransplantsByRadarNumber(long radarNumber);

    List<Transplant.Modality> getTransplantModalitites();

    Transplant.Modality getTransplantModality(long id);

    void saveRejectData(Transplant.RejectData rejectData);

    void saveRejectDataWithValidation(Transplant.RejectData rejectData) throws InvalidModelException;

    void deleteRejectData(Transplant.RejectData rejectData);

    List<Transplant.RejectData> getRejectDataByTransplantNumber(Long transplantNumber);

    Transplant.RejectData getRejectData(Long id);

}
