package org.patientview.repository.radar.service;

import org.patientview.model.radar.Immunosuppression;
import org.patientview.model.radar.ImmunosuppressionTreatment;
import org.patientview.model.radar.exception.InvalidModelException;

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
