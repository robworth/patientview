package org.patientview.radar.web.pages.admin;

import org.patientview.radar.model.user.User;
import org.patientview.radar.web.pages.BasePage;
import org.patientview.radar.web.panels.navigation.AdminNavigationPanel;
import org.apache.wicket.Page;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;

@AuthorizeInstantiation({User.ROLE_ADMIN})
public class AdminsBasePage extends BasePage {

    protected static final String EXPORT_FILE_NAME_SUFFIX = "-export";
    public AdminsBasePage() {
        super();
    }

    protected void addNavigation(Class<? extends Page> pageClass) {
        add(new AdminNavigationPanel());
    }
}
