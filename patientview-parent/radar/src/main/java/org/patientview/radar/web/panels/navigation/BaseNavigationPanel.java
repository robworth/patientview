package org.patientview.radar.web.panels.navigation;

import org.patientview.radar.web.RadarSecuredSession;
import org.patientview.radar.web.pages.HomePage;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

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
