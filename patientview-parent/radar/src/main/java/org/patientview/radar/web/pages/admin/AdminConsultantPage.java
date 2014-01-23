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

package org.patientview.radar.web.pages.admin;

import org.patientview.model.Centre;
import org.patientview.radar.model.Consultant;
import org.patientview.radar.service.UtilityManager;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.string.StringValue;

import java.util.Collections;
import java.util.List;

public class AdminConsultantPage extends AdminsBasePage {

    @SpringBean
    private UtilityManager utilityManager;

    private static final String PARAM_ID = "ID";
    private boolean newConsultant = false;

    public AdminConsultantPage(PageParameters parameters) {
        super();

        final Consultant consultant;

        // if id is empty or -1 then its a new consultant else try pull back record
        StringValue idValue = parameters.get(PARAM_ID);
        if (idValue.isEmpty() || idValue.toLong() == -1) {
            consultant = new Consultant();
            newConsultant = true;
        } else {
            consultant = utilityManager.getConsultant(idValue.toLongObject());
        }

        CompoundPropertyModel<Consultant> consultantModel =
                new CompoundPropertyModel<Consultant>(consultant);

        final FeedbackPanel feedback = new FeedbackPanel("feedback");
        feedback.setOutputMarkupId(true);
        feedback.setOutputMarkupPlaceholderTag(true);
        add(feedback);

        final Form<Consultant> userForm = new Form<Consultant>("consultantForm", consultantModel) {
            protected void onSubmit() {
                try {
                    utilityManager.saveConsultant(getModelObject());

                    if (newConsultant) {
                        setResponsePage(AdminConsultantsPage.class);
                    }
                } catch (Exception e) {
                    error("Could not save consultant: " + e.toString());
                }
            }
        };
        add(userForm);

        userForm.add(new RequiredTextField("forename"));
        userForm.add(new RequiredTextField("surname"));

        // get centres and sort by name
        List<Centre> centres = utilityManager.getCentres();
        Collections.sort(centres, Centre.getComparator());

        DropDownChoice<Centre> centre = new DropDownChoice<Centre>("centre", centres,
                new ChoiceRenderer<Centre>("name", "id"));
        centre.setRequired(true);
        userForm.add(centre);

        AjaxLink delete = new AjaxLink("delete") {
            public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                try {
                    utilityManager.deleteConsultant(consultant);
                    setResponsePage(AdminConsultantsPage.class);
                } catch (Exception e) {
                    error("Could not delete consultant " + e);
                    ajaxRequestTarget.add(feedback);
                }

            }
        };
        delete.setVisible(!newConsultant);
        userForm.add(delete);

        userForm.add(new AjaxSubmitLink("saveTop") {
            protected void onSubmit(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                setResponsePage(AdminConsultantsPage.class);
            }

            protected void onError(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                ajaxRequestTarget.add(feedback);
            }
        });

        userForm.add(new AjaxLink("cancelTop") {
            public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                setResponsePage(AdminConsultantsPage.class);
            }
        });

        userForm.add(new AjaxSubmitLink("saveBottom") {
            protected void onSubmit(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                setResponsePage(AdminConsultantsPage.class);
            }

            protected void onError(AjaxRequestTarget ajaxRequestTarget, Form<?> form) {
                ajaxRequestTarget.add(feedback);
            }
        });

        userForm.add(new AjaxLink("cancelBottom") {
            public void onClick(AjaxRequestTarget ajaxRequestTarget) {
                setResponsePage(AdminConsultantsPage.class);
            }
        });
    }

    public static PageParameters getPageParameters(Consultant consultant) {
        return new PageParameters().set(PARAM_ID, consultant.getId());
    }
}
