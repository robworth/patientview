package com.worthsoln.service.impl;

import com.worthsoln.patientview.model.JoinRequest;
import com.worthsoln.patientview.model.User;
import com.worthsoln.repository.JoinRequestDao;
import com.worthsoln.service.JoinRequestManager;
import com.worthsoln.service.UnitManager;
import com.worthsoln.service.UserManager;
import com.worthsoln.utils.LegacySpringUtils;
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
    JoinRequestDao joinRequestDao;

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
        User user  = userManager.getLoggedInUser();

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
