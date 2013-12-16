package org.patientview.radar.dao;

import org.patientview.model.Unit;
import org.patientview.radar.model.user.User;

import java.util.List;

/**
 * User: james@solidstategroup.com
 * Date: 04/12/13
 * Time: 17:28
 */
public interface UnitDao {

    List<Unit> getUnits(User user);

    List<String> getUnitCodes(User user);

    List<String> getAllUnitCodes();


}
