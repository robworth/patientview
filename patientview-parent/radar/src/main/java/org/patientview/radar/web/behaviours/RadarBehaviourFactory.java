package org.patientview.radar.web.behaviours;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.calldecorator.AjaxCallDecorator;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.model.Model;

import java.io.Serializable;

public class RadarBehaviourFactory {

    public static AttributePrepender getDeleteConfirmationBehaviour() {
        return AttributePrepender.getNewInstance("onclick", new Model<Serializable>("if(!confirm('Are you sure " +
                "you want to delete this record?')){return false;}"));
    }

    /**
     * Adds form is dirty warning on form exit to ajax links
     */
    public static AjaxCallDecorator getWarningOnFormExitCallDecorator() {
        return new AjaxCallDecorator() {
            @Override
            public CharSequence decorateScript(Component c, CharSequence script) {
                return "if (editPatientPage.formIsDirty) {\n" +
                        "    var exit = confirm(\"Are you sure you want " +
                        "to leave the tab without saving the form?\")\n" +
                        "    if (!exit) {\n" +
                        "        return;\n" +
                        "    } else {\n" +
                        "        editPatientPage.formIsDirty = false;\n" +
                        "    }\n" +
                        "}" + script;
            }
        };
    }

    /**
     * form is dirty warning on page exit - activated when you click on a page link navigating away from current page
     */
    public static Behavior getWarningOnPatientPageExitBehaviour() {
        return new Behavior() {
            @Override
            public void renderHead(Component component, IHeaderResponse response) {
                super.renderHead(component, response);
                response.renderJavaScript(
                        "jQuery(function() {\n" +
                        "    jQuery(\"#topNavigation a\").not('.logoutLink').click(function() {\n" +
                        "        if (editPatientPage.formIsDirty) {\n" +
                        "            return confirm(\"Are you sure you want to exit the page without saving " +
                                "the form?\");\n" +
                        "\n" +
                        "        }\n" +
                        "    });\n" +
                        "});", null);
            }
        };
    }
}
