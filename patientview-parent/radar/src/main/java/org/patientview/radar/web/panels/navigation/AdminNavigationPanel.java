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

package org.patientview.radar.web.panels.navigation;

import org.patientview.radar.web.pages.admin.AdminConsultantsPage;
import org.patientview.radar.web.pages.admin.AdminIssuesPage;
import org.patientview.radar.web.pages.admin.AdminPatientsAllPage;
import org.patientview.radar.web.pages.admin.AdminPatientsPage;
import org.patientview.radar.web.pages.admin.AdminUsersPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

public class AdminNavigationPanel extends BaseNavigationPanel {
    public AdminNavigationPanel() {
        super();

        addHomePageLink();
        addLogoutLink(true);

        add(new BookmarkablePageLink<AdminUsersPage>("adminUsersPageLink", AdminUsersPage.class));
        add(new BookmarkablePageLink<AdminConsultantsPage>("adminConsultantsPageLink", AdminConsultantsPage.class));
        add(new BookmarkablePageLink<AdminPatientsAllPage>("adminPatientsAllPageLink", AdminPatientsAllPage.class));
        add(new BookmarkablePageLink<AdminPatientsPage>("adminPatientsPageLink", AdminPatientsPage.class));
        add(new BookmarkablePageLink<AdminIssuesPage>("adminIssuesPageLink", AdminIssuesPage.class));
    }
}
