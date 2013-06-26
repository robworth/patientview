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

import org.patientview.patientview.model.enums.XmlImportNotification;
import org.patientview.repository.AdminNotificationDao;
import org.patientview.service.AdminNotificationManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import java.util.List;

@Service(value = "adminNotificationManager")
public class AdminNotificationManagerImpl implements AdminNotificationManager {

    @Inject
    private AdminNotificationDao adminNotificationDao;

    @Override
    public List<String> getEmailAddresses(XmlImportNotification xmlImportNotification) {
        return adminNotificationDao.getEmailAddresses(xmlImportNotification);
    }

    @Override
    public String getSupportEmailAddress(ServletContext context) {
        String supportEmailAddress = adminNotificationDao.getSupportEmail();

        if (StringUtils.isBlank(supportEmailAddress)) {
            return context.getInitParameter("support.email");
        } else {
            return supportEmailAddress;
        }
    }
}
