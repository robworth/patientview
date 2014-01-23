/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.radar.web.pages.login;

import org.patientview.radar.model.exception.DecryptionException;
import org.patientview.radar.model.exception.EmailAddressNotFoundException;
import org.patientview.radar.service.UserManager;
import org.patientview.radar.web.components.RadarRequiredTextField;
import org.patientview.radar.web.pages.BasePage;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxButton;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.ArrayList;
import java.util.List;

public abstract class ForgottenPasswordPage extends BasePage {

    public static final String EMAIL_ADDRESS_NOT_RECOGNISED_MESSAGE = "Email address not recognised";
    public static final String ERROR_MESSAGE = "An unexpected error occured";
    @SpringBean
    protected UserManager userManager;

    public ForgottenPasswordPage() {

        Label userType = new Label("userType", getUserType());
        add(userType);

        // components to update on ajax submit
        final List<Component> componentsToUpdate = new ArrayList<Component>();
        // Construct form
        final Form<String> form = new Form<String>("form", new Model<String>()) {
            @Override
            protected void onSubmit() {
                try {
                    sendPassword(getModelObject());
                } catch (EmailAddressNotFoundException e) {
                    error(EMAIL_ADDRESS_NOT_RECOGNISED_MESSAGE);
                } catch (DecryptionException e) {
                    error(ERROR_MESSAGE);
                }
            }
        };
        add(form);

        // Feedback
        final FeedbackPanel feedbackPanel = new FeedbackPanel("feedback", new IFeedbackMessageFilter() {
            public boolean accept(FeedbackMessage feedbackMessage) {
                String message = feedbackMessage.getMessage().toString();
                return message.contains(ERROR_MESSAGE) || message.contains(EMAIL_ADDRESS_NOT_RECOGNISED_MESSAGE);
            }
        });

        // success message
        Label successMessage = new Label("successMessage", "Your password has been emailed to you") {
            {
                setOutputMarkupId(true);
                setOutputMarkupPlaceholderTag(true);
                form.add(this);
                componentsToUpdate.add(this);
            }

            @Override
            public boolean isVisible() {
                return form.isSubmitted() && !form.hasError();
            }
        };
        form.add(feedbackPanel);
        componentsToUpdate.add(feedbackPanel);
        feedbackPanel.setOutputMarkupId(true);
        feedbackPanel.setOutputMarkupPlaceholderTag(true);

        // Email - can use same model as the form
        RadarRequiredTextField email = new RadarRequiredTextField("email", form, componentsToUpdate);
        email.setModel(form.getModel());
        form.add(email);

        // Submit link
        form.add(new IndicatingAjaxButton("submit") {
            {
                componentsToUpdate.add(this);
            }
            @Override
            protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
                target.add(componentsToUpdate.toArray(new Component[componentsToUpdate.size()]));
            }

            @Override
            protected void onError(AjaxRequestTarget target, Form<?> form) {
                target.add(componentsToUpdate.toArray(new Component[componentsToUpdate.size()]));
            }

            @Override
            public boolean isVisible() {
                if (form.isSubmitted() && !form.hasError()) {
                     return false;
                }
                return super.isVisible();
            }
        });

    }

    protected abstract String getUserType();

    public abstract void sendPassword(String username) throws EmailAddressNotFoundException, DecryptionException;

}
