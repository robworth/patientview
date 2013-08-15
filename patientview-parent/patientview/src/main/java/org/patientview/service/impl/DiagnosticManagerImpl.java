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

import org.patientview.patientview.model.Diagnostic;
import org.patientview.patientview.model.User;
import org.patientview.patientview.model.UserMapping;
import org.patientview.patientview.model.enums.DiagnosticType;
import org.patientview.repository.DiagnosticDao;
import org.patientview.service.DiagnosticManager;
import org.patientview.service.UserManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
@Service(value = "diagnosticManager")
public class DiagnosticManagerImpl implements DiagnosticManager {

    @Inject
    private DiagnosticDao diagnosticDao;

    @Inject
    private UserManager userManager;

    @Override
    public Diagnostic get(String nhsno) {
        return diagnosticDao.get(nhsno);
    }

    @Override
    public void save(Diagnostic diagnostic) {
        diagnosticDao.save(diagnostic);
    }

    @Override
    public List<Diagnostic> getForUser(User user, DiagnosticType diagnosticType) {
        // get all nhs nos this user is known as for this Specialty to get there diagnostic results
        if (user != null) {
            List<UserMapping> mappings = userManager.getUserMappings(user.getUsername());
            Set<String> nhsNos = new HashSet<String>();
            for (UserMapping userMapping : mappings) {
                nhsNos.add(userMapping.getNhsno());
            }

            return diagnosticDao.get(nhsNos, diagnosticType);
        }

        return null;
    }

    @Override
    public void delete(String nhsno, String unitcode) {
        diagnosticDao.delete(nhsno, unitcode);
    }
}
