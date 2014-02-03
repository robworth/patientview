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

package org.patientview.radar.web.components;

import org.apache.wicket.Component;
import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.ComponentFeedbackPanel;
import org.apache.wicket.validation.IValidator;

import java.util.List;


public class RadarTextFieldWithValidation<T> extends TextField<T> {
    public RadarTextFieldWithValidation(String id, IValidator validator, WebMarkupContainer container,
                                       List<Component> componentsToUpdate) {
        super(id);
        init(id, container, validator, false, componentsToUpdate);
    }

    public RadarTextFieldWithValidation(String id, IValidator validator, boolean required,
                                        WebMarkupContainer container, List<Component> componentsToUpdate) {
        super(id);
        init(id, container, validator, required, componentsToUpdate);
    }

    private void init(String id, WebMarkupContainer form, IValidator validator, boolean required,
                      List<Component> componentsToUpdate) {
        if (validator != null) {
             add(validator);
        }
        final ComponentFeedbackPanel feedbackPanel = new ComponentFeedbackPanel(id + "Feedback", this) {
            @Override
            public boolean isVisible() {
                List<FeedbackMessage> feedbackMessages = getCurrentMessages();
                for (FeedbackMessage feedbackMessage : feedbackMessages) {
                    if (feedbackMessage.getMessage().toString().contains("required")) {
                        return false;
                    }
                }
                return super.isVisible();
            }
        };
        feedbackPanel.setOutputMarkupId(true);
        feedbackPanel.setOutputMarkupPlaceholderTag(true);
        form.add(feedbackPanel);
        componentsToUpdate.add(feedbackPanel);

        if (required) {
            setRequired(true);
            RadarFormComponentFeedbackIndicator radarFormComponentFeedbackIndicator =
                    new RadarFormComponentFeedbackIndicator(getId() + "FeedbackIndicator", this) {
                        @Override
                        public boolean isVisible() {
                            if (feedbackPanel.isVisible()) {
                                return false;
                            }
                            return super.isVisible();
                        }
                    };
            form.add(radarFormComponentFeedbackIndicator);
            radarFormComponentFeedbackIndicator.setOutputMarkupId(true);
            radarFormComponentFeedbackIndicator.setOutputMarkupPlaceholderTag(true);
            componentsToUpdate.add(radarFormComponentFeedbackIndicator);
        }

    }
}
