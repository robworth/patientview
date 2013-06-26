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
