package org.patientview.radar.web.dataproviders;

import org.patientview.radar.model.filter.ProfessionalUserFilter;
import org.patientview.radar.model.user.ProfessionalUser;
import org.patientview.radar.service.UserManager;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.Iterator;
import java.util.List;

public class ProfessionalUserDataProvider implements IDataProvider<ProfessionalUser>, SortableDataProvider {

    private UserManager userManager;
    private ProfessionalUserFilter professionalUserFilter;

    public ProfessionalUserDataProvider(UserManager userManager) {
        this.userManager = userManager;
        professionalUserFilter = new ProfessionalUserFilter();
    }

    public Iterator<? extends ProfessionalUser> iterator(int i, int i1) {
        int pageNumber = (i / i1) + 1;
        return getResults(pageNumber, i1).iterator();
    }

    public int size() {
        return getResults(-1, -1).size();
    }

    public IModel<ProfessionalUser> model(ProfessionalUser professionalUser) {
        return new Model<ProfessionalUser>(professionalUser);
    }

    public void detach() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setAscending(boolean ascending) {
        professionalUserFilter.setReverse(ascending);
    }

    public boolean isAscending() {
        return professionalUserFilter.isReverse();
    }

    public void setSortField(String sortField) {
        professionalUserFilter.setSortField(sortField);
    }

    public String getSortField() {
        return professionalUserFilter.getSortField();
    }

    public boolean hasSearchCriteria() {
        return professionalUserFilter.hasSearchCriteria();
    }

    public void addSearchCriteria(String searchField, String searchText) {
        professionalUserFilter.addSearchCriteria(searchField, searchText);
    }

    public void removeSearchCriteria(String searchField) {
        professionalUserFilter.removeSearchCriteria(searchField);
    }

    public void clearSearchCriteria() {
        professionalUserFilter.getSearchFields().clear();
    }

    private List<ProfessionalUser> getResults(int page, int resultsPerPage) {
        return userManager.getProfessionalUsers(professionalUserFilter, page, resultsPerPage);
    }
}
