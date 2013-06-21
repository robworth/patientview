/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.service.impl;

import org.patientview.patientview.model.JoinRequest;
import org.patientview.patientview.model.User;
import org.patientview.repository.JoinRequestDao;
import org.patientview.service.JoinRequestManager;
import org.patientview.service.UnitManager;
import org.patientview.service.UserManager;
import org.patientview.utils.LegacySpringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Transactional(propagation = Propagation.REQUIRED)
@Service(value = "joinRequestManager")
public class JoinRequestManagerImpl implements JoinRequestManager {

    @Inject
    private JoinRequestDao joinRequestDao;

    @Inject
    private UserManager userManager;

    @Inject
    private UnitManager unitManager;

    @Override
    public void save(JoinRequest joinRequest) {
        joinRequestDao.save(joinRequest);
    }

    @Override
    public List<JoinRequest> getUsersJoinRequests() {
        List<JoinRequest> joinRequests = new ArrayList<JoinRequest>();
        User user = userManager.getLoggedInUser();

        String userType = LegacySpringUtils.getUserManager().getCurrentSpecialtyRole(user);

        if ("superadmin".equals(userType)) {

            joinRequests = joinRequestDao.getAll();
        } else if ("unitadmin".equals(userType) || "unitstaff".equals(userType)) {

            List<String> unitCodes = unitManager.getUsersUnitCodes(user);
            joinRequests = joinRequestDao.getJoinRequestsForUnitCodes(unitCodes);
        }

        return joinRequests;
    }
}
