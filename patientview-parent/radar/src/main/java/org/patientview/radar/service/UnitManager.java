package org.patientview.radar.service;

import org.patientview.model.Unit;
import org.patientview.radar.model.user.User;

import java.util.List;

/**
 * This is replicated from Radar but instead of creating the class the functionality was put in the user Dao/Manager
 * Now this has become to large and needed separating out;
 *
 * User: james@solidstategroup.com
 * Date: 04/12/13
 * Time: 17:40
 */
public interface UnitManager {

    List<String> getUnitCodes(User user);

    List<Unit> getDiseaseUnits(User user);

    List<Unit> getRenalUnits(User user);

}
