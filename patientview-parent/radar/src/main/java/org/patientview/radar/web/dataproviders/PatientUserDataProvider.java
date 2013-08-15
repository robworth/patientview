package org.patientview.radar.web.dataproviders;

import org.patientview.radar.model.filter.PatientUserFilter;
import org.patientview.radar.model.user.PatientUser;
import org.patientview.radar.service.UserManager;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.Iterator;
import java.util.List;

public class PatientUserDataProvider implements IDataProvider<PatientUser>, SortableDataProvider {

    private UserManager userManager;
    private PatientUserFilter patientUserFilter;

    public PatientUserDataProvider(UserManager userManager) {
        this.userManager = userManager;
        patientUserFilter = new PatientUserFilter();
    }

    public Iterator<? extends PatientUser> iterator(int i, int i1) {
        int pageNumber = (i / i1) + 1;
        return getResults(pageNumber, i1).iterator();
    }

    public int size() {
        return getResults(-1, -1).size();
    }

    public IModel<PatientUser> model(PatientUser patientUser) {
        return new Model<PatientUser>(patientUser);
    }

    public void detach() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setAscending(boolean ascending) {
        patientUserFilter.setReverse(ascending);
    }

    public boolean isAscending() {
        return patientUserFilter.isReverse();
    }

    public void setSortField(String sortField) {
        patientUserFilter.setSortField(sortField);
    }

    public String getSortField() {
        return patientUserFilter.getSortField();
    }

    public boolean hasSearchCriteria() {
        return patientUserFilter.hasSearchCriteria();
    }

    public void addSearchCriteria(String searchField, String searchText) {
        patientUserFilter.addSearchCriteria(searchField, searchText);
    }

    public void removeSearchCriteria(String searchField) {
        patientUserFilter.removeSearchCriteria(searchField);
    }

    public void clearSearchCriteria() {
        patientUserFilter.getSearchFields().clear();
    }

    private List<PatientUser> getResults(int page, int resultsPerPage) {
        return userManager.getPatientUsers(patientUserFilter, page, resultsPerPage);
    }
}
