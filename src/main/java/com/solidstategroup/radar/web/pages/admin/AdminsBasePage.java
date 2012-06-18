package com.solidstategroup.radar.web.pages.admin;

import com.solidstategroup.radar.model.user.User;
import com.solidstategroup.radar.web.pages.BasePage;
import com.solidstategroup.radar.web.panels.navigation.AdminNavigationPanel;
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
