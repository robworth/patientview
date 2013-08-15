package org.patientview.radar.web.pages.admin;

import org.patientview.radar.model.Demographics;
import org.patientview.radar.model.enums.ExportType;
import org.patientview.radar.model.filter.DemographicsFilter;
import org.patientview.radar.service.DemographicsManager;
import org.patientview.radar.service.DiagnosisManager;
import org.patientview.radar.service.ExportManager;
import org.patientview.radar.web.behaviours.AJAXDownload;
import org.patientview.radar.web.components.ClearLink;
import org.patientview.radar.web.components.SearchDateField;
import org.patientview.radar.web.components.SearchField;
import org.patientview.radar.web.components.SortLink;
import org.patientview.radar.web.dataproviders.DemographicsDataProvider;
import org.patientview.radar.web.panels.RadarAjaxPagingNavigator;
import org.apache.commons.lang.StringUtils;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.resource.AbstractResourceStreamWriter;
import org.apache.wicket.util.resource.IResourceStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminPatientsAllPage extends AdminsBasePage {
    @SpringBean
    private DemographicsManager demographicsManager;
    @SpringBean
    private ExportManager exportManager;
    @SpringBean
    private DiagnosisManager diagnosisManager;

    private static final int RESULTS_PER_PAGE = 10;

    public AdminPatientsAllPage() {
        final DemographicsDataProvider demographicsDataProvider = new DemographicsDataProvider(demographicsManager);

        // using ajax download - the data is created on request to speed up page load
        final AJAXDownload exportPdf = new AJAXDownload() {
            @Override
            protected IResourceStream getResourceStream() {
                return new AbstractResourceStreamWriter() {
                    public void write(Response response) {
                        response.write(exportManager.getDemographicsExportData(ExportType.PDF));
                    }
                };
            }

            @Override
            protected String getFileName() {
                return "patients-all.pdf";
            }
        };

        // using ajax download - the data is created on request to speed up page load
        final AJAXDownload exportExcel = new AJAXDownload() {
            @Override
            protected IResourceStream getResourceStream() {
                return new AbstractResourceStreamWriter() {
                    public void write(Response response) {
                        response.write(exportManager.getDemographicsExportData(ExportType.EXCEL));
                    }

                    @Override
                    public String getContentType() {
                        return "application/pdf";
                    }
                };
            }

            @Override
            protected String getFileName() {
                return "patients-all.xls";
            }
        };

        add(exportPdf);
        add(exportExcel);


        add(new IndicatingAjaxLink("exportPdf") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                exportPdf.initiate(target);
            }
        });

        add(new IndicatingAjaxLink("exportExcel") {
            @Override
            public void onClick(AjaxRequestTarget target) {
                exportExcel.initiate(target);
            }
        });

        final WebMarkupContainer demographicsContainer = new WebMarkupContainer("demographicsContainer");
        demographicsContainer.setOutputMarkupId(true);
        add(demographicsContainer);

        final DataView<Demographics> demographicsList = new DataView<Demographics>("demographics",
                demographicsDataProvider) {
            @Override
            protected void populateItem(Item<Demographics> item) {
                builtDataViewRow(item);
            }
        };
        demographicsList.setItemsPerPage(RESULTS_PER_PAGE);
        demographicsContainer.add(demographicsList);

        // add paging element
        demographicsContainer.add(new RadarAjaxPagingNavigator("navigator", demographicsList,
                demographicsDataProvider.size()));

        // add sort links to the table column headers
        for (Map.Entry<String, String> entry : getSortFields().entrySet()) {
            add(new SortLink(entry.getKey(), entry.getValue(), demographicsDataProvider, demographicsList,
                    Arrays.asList(demographicsContainer)));
        }

        // button to clear all the filter fields for each colum
        final ClearLink clearButton = new ClearLink("clearButton", demographicsDataProvider, demographicsList,
                demographicsContainer);
        add(clearButton);

        // add a search field to the top of each column - these will AND each search
        for (Map.Entry<String, String> entry : getFilterFields().entrySet()) {
            add(new SearchField(entry.getKey(), entry.getValue(), demographicsDataProvider, demographicsList,
                    Arrays.asList(demographicsContainer, clearButton)));
        }

        // add a date filter
        add(new SearchDateField("searchDateRegistered",
                DemographicsFilter.UserField.REGISTRATION_DATE.getDatabaseFieldName(),
                demographicsDataProvider, demographicsList, Arrays.asList(demographicsContainer, clearButton)));
    }

    /**
     * Build a row in the dataview from the object
     *
     * @param item Item<Demographics>
     */
    private void builtDataViewRow(Item<Demographics> item) {
        Demographics demographics = item.getModelObject();
        item.add(new BookmarkablePageLink<AdminPatientsAllPage>("edit", AdminPatientAllPage.class,
                AdminPatientAllPage.getPageParameters(demographics)));
        item.add(new Label("radarNo", demographics.getId().toString()));
        item.add(DateLabel.forDatePattern("dateRegistered", new Model<Date>(demographics.getDateRegistered()),
                SearchDateField.DATABASE_DATE_PATTERN));
        item.add(new Label("forename", demographics.getForename()));
        item.add(new Label("surname", demographics.getSurname()));

        // build address
        List<String> addressValues = new ArrayList<String>();

        if (demographics.getAddress1() != null && demographics.getAddress1().length() > 0) {
            addressValues.add(demographics.getAddress1());
        }

        if (demographics.getAddress2() != null && demographics.getAddress2().length() > 0) {
            addressValues.add(demographics.getAddress2());
        }

        if (demographics.getAddress3() != null && demographics.getAddress3().length() > 0) {
            addressValues.add(demographics.getAddress3());
        }

        if (demographics.getAddress4() != null && demographics.getAddress4().length() > 0) {
            addressValues.add(demographics.getAddress4());
        }

        if (demographics.getPostcode() != null && demographics.getPostcode().length() > 0) {
            addressValues.add(demographics.getPostcode());
        }

        item.add(new Label("address", StringUtils.join(addressValues, ", ")));

        item.add(new Label("diagnosis", diagnosisManager.getDiagnosisName(demographics)));

        String consultantSurname = "", consultantForename = "", centreAbbrv = "";

        if (demographics.getClinician() != null) {
            consultantSurname = demographics.getClinician().getSurname();
            consultantForename = demographics.getClinician().getForename();

            if (demographics.getClinician().getCentre() != null) {
                centreAbbrv = demographics.getClinician().getCentre().getAbbreviation();
            }
        }

        item.add(new Label("consultantSurname", consultantSurname));
        item.add(new Label("consultantForename", consultantForename));
        item.add(new Label("centre", centreAbbrv));
    }

    /**
     * List of columns that can be used to sort the results - will return ID of el to be bound to and the field to sort
     *
     * @return Map<String, DemographicsFilter.UserField>
     */
    private Map<String, String> getSortFields() {
        return new HashMap<String, String>() {
            {
                put("orderByRadarNo", DemographicsFilter.UserField.RADAR_NO.getDatabaseFieldName());
                put("orderByDateRegistered", DemographicsFilter.UserField.REGISTRATION_DATE.getDatabaseFieldName());
                put("orderByForename", DemographicsFilter.UserField.FORENAME.getDatabaseFieldName());
                put("orderBySurname", DemographicsFilter.UserField.SURNAME.getDatabaseFieldName());
                put("orderByAddress", DemographicsFilter.UserField.ADDRESS.getDatabaseFieldName());
                put("orderByDiagnosis", DemographicsFilter.UserField.DIAGNOSIS.getDatabaseFieldName());
                put("orderByConsultantSurname", DemographicsFilter.UserField.CONSULTANT_SURNAME.getDatabaseFieldName());
                put("orderByConsultantForename",
                        DemographicsFilter.UserField.CONSULTANT_FORNAME.getDatabaseFieldName());
                put("orderByCentre", DemographicsFilter.UserField.CENTRE.getDatabaseFieldName());
            }
        };
    }

    /**
     * List of column filters - will return ID of el to be bound to and the field to filter
     *
     * @return Map<String, DemographicsFilter.UserField>
     */
    private Map<String, String> getFilterFields() {
        return new HashMap<String, String>() {
            {
                put("searchRadarNo", DemographicsFilter.UserField.RADAR_NO.getDatabaseFieldName());
                put("searchDiagnosis", DemographicsFilter.UserField.DIAGNOSIS.getDatabaseFieldName());
                put("searchConsultantSurname", DemographicsFilter.UserField.CONSULTANT_SURNAME.getDatabaseFieldName());
                put("searchConsultantForename", DemographicsFilter.UserField.CONSULTANT_FORNAME.getDatabaseFieldName());
                put("searchCentre", DemographicsFilter.UserField.CENTRE.getDatabaseFieldName());
                // TODO: add the date filter
            }
        };
    }
}
