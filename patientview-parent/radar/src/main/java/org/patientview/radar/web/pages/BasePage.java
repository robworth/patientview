package org.patientview.radar.web.pages;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.patientview.radar.web.panels.navigation.DefaultNavigationPanel;

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

    public BasePage(PageParameters pageParameters) {
        super(pageParameters);
        add(new Label("title", getTitle()));
        addNavigation(getPageClass());
    }

}
