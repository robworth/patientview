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

        // Render these like this so any paths we have still renderTemplate CSS URLs correctly
        response.renderCSSReference("normal.css");
        response.renderCSSReference("layout.css");
        response.renderCSSReference("ajxmenu.css");
        response.renderCSSReference("admin.css");
        response.renderCSSReference("tabs_lab.css");
    }
}
