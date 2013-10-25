package org.patientview.repository.radar.web.pages.admin;

import org.patientview.model.radar.user.User;
import org.patientview.repository.radar.web.pages.BasePage;
import org.patientview.repository.radar.web.panels.navigation.AdminNavigationPanel;
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
