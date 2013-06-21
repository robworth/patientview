package org.patientview.radar.service;

import org.patientview.radar.model.Immunosuppression;
import org.patientview.radar.model.ImmunosuppressionTreatment;
import org.patientview.radar.model.exception.InvalidModelException;

import java.util.List;

public interface ImmunosuppressionManager {

    void saveImmunosuppressionTreatment(ImmunosuppressionTreatment immunosuppressionTreatment) throws
            InvalidModelException;

    void deleteImmunosuppressionTreatment(ImmunosuppressionTreatment immunosuppressionTreatment);

    ImmunosuppressionTreatment getImmunosuppressionTreatment(long id);

    List<ImmunosuppressionTreatment> getImmunosuppressionTreatmentByRadarNumber(long radarNumber);

    List<Immunosuppression> getImmunosuppressions();

    Immunosuppression getImmunosuppression(long id);
}
