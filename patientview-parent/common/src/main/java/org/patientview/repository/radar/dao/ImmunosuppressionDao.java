package org.patientview.repository.radar.dao;

import org.patientview.model.radar.Immunosuppression;
import org.patientview.model.radar.ImmunosuppressionTreatment;

import java.util.List;

public interface ImmunosuppressionDao {

    void saveImmunosuppressionTreatment(ImmunosuppressionTreatment immunosuppressionTreatment);

    void deleteImmunosuppressionTreatment(ImmunosuppressionTreatment immunosuppressionTreatment);

    ImmunosuppressionTreatment getImmunosuppressionTreatment(long id);

    List<ImmunosuppressionTreatment> getImmunosuppressionTreatmentByRadarNumber(long radarNumber);

    List<Immunosuppression> getImmunosuppressions();

    Immunosuppression getImmunosuppression(long id);
}
