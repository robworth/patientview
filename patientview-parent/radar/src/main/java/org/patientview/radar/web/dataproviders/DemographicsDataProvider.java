package org.patientview.radar.web.dataproviders;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.patientview.model.Patient;
import org.patientview.radar.model.filter.DemographicsFilter;
import org.patientview.radar.model.user.User;
import org.patientview.radar.service.DemographicsManager;

import java.util.Iterator;
import java.util.List;

public class DemographicsDataProvider implements IDataProvider<Patient>, SortableDataProvider {

    private User user;
    private DemographicsManager demographicsManager;
    private DemographicsFilter demographicsFilter;

    public DemographicsDataProvider(DemographicsManager demographicsManager, User user) {
        this(demographicsManager);
        this.user = user;
    }

    public DemographicsDataProvider(DemographicsManager demographicsManager) {
        this.demographicsManager = demographicsManager;
        demographicsFilter = new DemographicsFilter();
    }

    public Iterator<? extends Patient> iterator(int i, int i1) {
        int pageNumber = (i / i1) + 1;
        return getResults(pageNumber, i1).iterator();
    }

    public int size() {
        return getResults(-1, -1).size();
    }

    public void setAscending(boolean ascending) {
        demographicsFilter.setReverse(ascending);
    }

    public boolean isAscending() {
        return demographicsFilter.isReverse();
    }

    public void setSortField(String sortField) {
        demographicsFilter.setSortField(sortField);
    }

    public String getSortField() {
        return demographicsFilter.getSortField();
    }

    public boolean hasSearchCriteria() {
        return demographicsFilter.hasSearchCriteria();
    }

    public void addSearchCriteria(String searchField, String searchText) {
        demographicsFilter.addSearchCriteria(searchField, searchText);
    }

    public void removeSearchCriteria(String searchField) {
        demographicsFilter.removeSearchCriteria(searchField);
    }

    public void clearSearchCriteria() {
        demographicsFilter.getSearchFields().clear();
    }

    private List<Patient> getResults(int page, int resultsPerPage) {
        if (user != null) {
            return demographicsManager.getDemographicsByUser(user);
        } else {
            return demographicsManager.getDemographics(demographicsFilter, page, resultsPerPage);
        }
    }

    public IModel<Patient> model(Patient patient) {
        return new CompoundPropertyModel<Patient>(patient);
    }

    public void detach() {
        // Nothing to do here
    }
}
