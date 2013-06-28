package org.patientview.radar.web.visitors;


import org.patientview.radar.web.RadarApplication;
import org.patientview.radar.web.components.RadarDateTextField;
import org.patientview.radar.web.components.RadarRequiredDateTextField;
import org.patientview.radar.web.validators.RadarDateValidator;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

public class PatientFormVisitor implements IVisitor<Component, Object> {
    public void component(Component component, IVisit iVisit) {
        //add onkeyup event to date to santise input - tried attaching behaviour in the component class itself
        // but did not work
        if (component instanceof RadarDateTextField || component instanceof RadarRequiredDateTextField) {
            component.add(new AttributeModifier("onkeyup", "radarUtility.sanitiseDateInput(this);"));
        }

        // add validator to date components - adding it inside the component constructor does not work
        if (component instanceof RadarDateTextField || component instanceof RadarRequiredDateTextField) {
            component.add(new RadarDateValidator());
        }

        //if form component - mark form as dirty onchange
        if (component instanceof FormComponent || component instanceof Radio) {
            // ignore the subform components
            String[] ignoreParents = {"immunosuppression", "plasmapheresispanel", "dialysiscontainer",
                    "transplantscontainer", "rejectDataContainer", "editTransplantContainer", "addTransplantForm"};

            // ignore record switchers
            String[] ignoreIds = {"switcher"};

            boolean ignoreComponent = false;
            for (String ignore : ignoreParents) {
                if (component.getPath().toLowerCase().contains(ignore.toLowerCase())) {
                    ignoreComponent = true;
                    break;
                }
            }
            for (String ignore : ignoreIds) {
                if (component.getId().toLowerCase().contains(ignore.toLowerCase())) {
                    ignoreComponent = true;
                    break;
                }
            }
            if (!ignoreComponent) {
                component.add(new AttributeAppender("onchange", RadarApplication.FORM_IS_DIRTY_TRUE_SCRIPT));
            }
        }

    }
}
