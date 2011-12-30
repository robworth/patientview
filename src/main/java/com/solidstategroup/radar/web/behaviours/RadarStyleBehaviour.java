package com.solidstategroup.radar.web.behaviours;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.protocol.http.WebApplication;

public class RadarStyleBehaviour extends Behavior {

    private static final String CSS_PATH = "/css/";

    @Override
    public void renderHead(Component component, IHeaderResponse response) {
        super.renderHead(component, response);

        // Get the context path we're deployed on
        String path = WebApplication.get().getServletContext().getContextPath() + CSS_PATH;

        // Render these like this so any paths we have still render CSS URLs correctly
        response.renderCSSReference(path + "normal.css");
        response.renderCSSReference(path + "layout.css");
        response.renderCSSReference(path + "ajxmenu.css");
        response.renderCSSReference(path + "tabs_lab.css");
    }
}
