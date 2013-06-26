package org.patientview.radar.dao;

import org.patientview.radar.model.Immunosuppression;
import org.patientview.radar.model.ImmunosuppressionTreatment;

import java.util.List;

public interface ImmunosuppressionDao {

    void saveImmunosuppressionTreatment(ImmunosuppressionTreatment immunosuppressionTreatment);

    void deleteImmunosuppressionTreatment(ImmunosuppressionTreatment immunosuppressionTreatment);

    ImmunosuppressionTreatment getImmunosuppressionTreatment(long id);

    List<ImmunosuppressionTreatment> getImmunosuppressionTreatmentByRadarNumber(long radarNumber);

    List<Immunosuppression> getImmunosuppressions();

    Immunosuppression getImmunosuppression(long id);
}
