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

import org.patientview.radar.web.dataproviders.SortableDataProvider;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SearchDateField extends DateTextField {
    public static final String DATABASE_DATE_PATTERN = "yyyy-MM-dd";
    public static final SimpleDateFormat DATABASE_DATE_FORMAT = new SimpleDateFormat(DATABASE_DATE_PATTERN);
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yy");

    private SortableDataProvider dataProvider;
    private String searchField;
    private DataView dataView;
    private List<? extends Component> componentsToUpdate;

    public SearchDateField(String id, String searchField, SortableDataProvider dataProvider, DataView dataView,
                           List<? extends Component> componentsToUpdate) {
        super(id, new Model<Date>(null), DATABASE_DATE_PATTERN);

        this.dataProvider = dataProvider;
        this.searchField = searchField;
        this.dataView = dataView;
        this.componentsToUpdate = componentsToUpdate;

        for (final String s : new String[]{"onchange", "onblur", "onkeyup"}) {
            add(new AjaxFormComponentUpdatingBehavior(s) {
                @Override
                protected void onUpdate(AjaxRequestTarget target) {
                    changed(target);
                }
            });
        }

        add(new DatePicker());
    }

    private void changed(final AjaxRequestTarget ajaxRequestTarget) {
        String value = getSearchValue();

        if (value == null || value.length() == 0) {
            // if they type nothing in then just bring back all the results
            dataProvider.removeSearchCriteria(searchField);
        } else {
            dataProvider.addSearchCriteria(searchField, value);
        }

        dataView.setCurrentPage(0);

        if (componentsToUpdate != null) {
            for (Component component : componentsToUpdate) {
                ajaxRequestTarget.add(component);
            }
        }
    }

    protected String getSearchValue() {
        if (getModelObject() != null) {
            Date date = getModelObject();

            // try to parse the string into a date and convert to the datebase format
            try {
                return DATABASE_DATE_FORMAT.format(date);
            } catch (Exception e) {
                // user may have just typed some numbers so just try and filter on that
            }

            return getModelObject().toString();
        }

        return "";
    }
}
