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

import org.patientview.radar.model.Consultant;
import org.patientview.radar.model.enums.ExportType;
import org.patientview.radar.model.filter.ConsultantFilter;
import org.patientview.radar.service.ExportManager;
import org.patientview.radar.service.UtilityManager;
import org.patientview.radar.web.components.SortLink;
import org.patientview.radar.web.dataproviders.ConsultantsDataProvider;
import org.patientview.radar.web.panels.RadarAjaxPagingNavigator;
import org.patientview.radar.web.resources.RadarResourceFactory;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.ResourceLink;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AdminConsultantsPage extends AdminsBasePage {

    @SpringBean
    private UtilityManager utilityManager;
    @SpringBean
    private ExportManager exportManager;

    private static final int RESULTS_PER_PAGE = 10;

    public AdminConsultantsPage() {
        final ConsultantsDataProvider consultantsDataProvider = new ConsultantsDataProvider(utilityManager);

        add(new ResourceLink("exportPdf", RadarResourceFactory.getExportResource(
                exportManager.getConsultantsExportData(ExportType.PDF), "consultants" +
                AdminsBasePage.EXPORT_FILE_NAME_SUFFIX, ExportType.PDF)));

        add(new ResourceLink("exportExcel", RadarResourceFactory.getExportResource(
                exportManager.getConsultantsExportData(ExportType.EXCEL), "consultants" +
                AdminsBasePage.EXPORT_FILE_NAME_SUFFIX, ExportType.EXCEL)));

        add(new BookmarkablePageLink<AdminConsultantPage>("addNewConsultant", AdminConsultantPage.class));

        final WebMarkupContainer consultantsContainer = new WebMarkupContainer("consultantsContainer");
        consultantsContainer.setOutputMarkupId(true);
        add(consultantsContainer);

        final DataView<Consultant> consultantList = new DataView<Consultant>("consultants",
                consultantsDataProvider) {
            @Override
            protected void populateItem(Item<Consultant> item) {
                builtDataViewRow(item);
            }
        };
        consultantList.setItemsPerPage(RESULTS_PER_PAGE);
        consultantsContainer.add(consultantList);

        // add paging element
        consultantsContainer.add(new RadarAjaxPagingNavigator("navigator", consultantList,
                consultantsDataProvider.size()));

        // add sort links to the table column headers
        for (Map.Entry<String, String> entry : getSortFields().entrySet()) {
            add(new SortLink(entry.getKey(), entry.getValue(), consultantsDataProvider,
                    consultantList, Arrays.asList(consultantsContainer)));
        }
    }

    /**
     * Build a row in the dataview from the object
     *
     * @param item Item<Consultant>
     */
    private void builtDataViewRow(Item<Consultant> item) {
        Consultant consultant = item.getModelObject();

        item.add(new BookmarkablePageLink<AdminConsultantPage>("edit", AdminConsultantPage.class,
                AdminConsultantPage.getPageParameters(consultant)));
        item.add(new Label("surname", consultant.getSurname()));
        item.add(new Label("forename", consultant.getForename()));

        String centreName;
        try {
            centreName = consultant.getCentre().getName();
        } catch (Exception e) {
            centreName = "";
        }

        item.add(new Label("centre", centreName));

        int numberOfPatients;
        try {
            numberOfPatients = utilityManager.getPatientCountByUnit(consultant.getCentre());
        } catch (Exception e) {
            numberOfPatients = 0;
        }

        item.add(new Label("numberOfPatients", Integer.toString(numberOfPatients)));
    }

    /**
     * List of columns that can be used to sort the results - will return ID of el to be bound to and the field to sort
     *
     * @return Map<String, ProfessionalUserFilter.UserField>
     */
    private Map<String, String> getSortFields() {
        return new HashMap<String, String>() {
            {
                put("orderBySurname", ConsultantFilter.UserField.SURNAME.getDatabaseFieldName());
                put("orderByForename", ConsultantFilter.UserField.FORENAME.getDatabaseFieldName());
                put("orderByCentre", ConsultantFilter.UserField.CENTRE.getDatabaseFieldName());
            }
        };
    }
}
