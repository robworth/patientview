package org.patientview.radar.web.pages;

import org.patientview.radar.web.panels.navigation.DefaultNavigationPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;

public abstract class BasePage extends WebPage {
    public BasePage() {
        add(new Label("title", getTitle()));
        addNavigation(getPageClass());
    }

    protected void addNavigation(Class<? extends org.apache.wicket.Page> pageClass) {
        add(new DefaultNavigationPanel(pageClass));
    }

    public String getTitle() {
        return "RaDaR - National Renal Rare Disease Registry";
    }
}
