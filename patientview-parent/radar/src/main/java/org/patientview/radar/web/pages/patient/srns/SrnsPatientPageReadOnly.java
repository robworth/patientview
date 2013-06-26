package org.patientview.radar.web.pages.patient.srns;

import org.patientview.radar.model.user.User;
import org.patientview.radar.web.RadarSecuredSession;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.util.visit.IVisit;
import org.apache.wicket.util.visit.IVisitor;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AuthorizeInstantiation({User.ROLE_PATIENT})
public class SrnsPatientPageReadOnly extends SrnsPatientPage {
    public SrnsPatientPageReadOnly(PageParameters parameters) {
        super(parameters);

        //a patient cannot edit so hide and disable stuff so only a read only view is available
        visitChildren(new IVisitor<Component, Object>() {
            public void component(Component component, IVisit<Object> objectIVisit) {
                RadarSecuredSession session = RadarSecuredSession.get();
                if (session.get().isSignedIn()) {
                    if (session.getRoles().hasRole(User.ROLE_PATIENT)) {
                        if (component instanceof FormComponent) {
                            component.setEnabled(false);
                        }

                        if (component instanceof AjaxSubmitLink) {
                            component.setVisible(false);
                        }

                        for (String regexToHide : Arrays.asList("\\w*edit\\w*(container|form)",
                                "\\w*delete\\w*(container|form)", "\\w*add\\w*(container|form)", "\\w*addnew\\w*")) {
                            Matcher matcher = Pattern.compile(regexToHide, Pattern.CASE_INSENSITIVE).matcher(
                                    component.getId().toLowerCase());
                            if (matcher.matches()) {
                                component.setVisible(false);
                            }
                        }

                        for (String regexToEnable : Arrays.asList("\\w*switcher\\w*")) {
                            Matcher matcher = Pattern.compile(regexToEnable, Pattern.CASE_INSENSITIVE).matcher(
                                    component.getId().toLowerCase());
                            if (matcher.matches()) {
                                component.setEnabled(true);
                            }
                        }
                    }
                }

            }
        });
    }


    public static PageParameters getParameters(Long radarNumber) {
       return new PageParameters().set(PARAM_ID, radarNumber);
    }
}
