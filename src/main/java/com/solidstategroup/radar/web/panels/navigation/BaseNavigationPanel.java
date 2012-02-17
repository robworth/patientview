package com.solidstategroup.radar.web.panels.navigation;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.AjaxRequestTarget;
import com.solidstategroup.radar.web.pages.HomePage;
import com.solidstategroup.radar.web.RadarSecuredSession;

public class BaseNavigationPanel extends Panel {
    public BaseNavigationPanel() {
        super("navigationPanel");        
    }

    protected void addHomePageLink() {
        add(new BookmarkablePageLink<HomePage>("homePageLink", HomePage.class));
    }

    protected void addLogoutLink(boolean userLoggedIn) {
        AjaxLink logoutLink = new AjaxLink<HomePage>("logoutLink") {
            @Override
            public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                RadarSecuredSession.get().invalidate();
                setResponsePage(HomePage.class);
            }
        };
        logoutLink.setVisible(userLoggedIn);
        add(logoutLink);
    }
}
