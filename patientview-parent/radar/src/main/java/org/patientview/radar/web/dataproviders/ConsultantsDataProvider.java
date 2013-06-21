package org.patientview.radar.web.dataproviders;

import org.patientview.radar.model.Consultant;
import org.patientview.radar.model.filter.ConsultantFilter;
import org.patientview.radar.service.UtilityManager;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import java.util.Iterator;
import java.util.List;

public class ConsultantsDataProvider implements IDataProvider<Consultant>, SortableDataProvider {

    private UtilityManager utilityManager;
    private ConsultantFilter consultantFilter;

    public ConsultantsDataProvider(UtilityManager utilityManager) {
        this.utilityManager = utilityManager;
        consultantFilter = new ConsultantFilter();
    }

    public Iterator<? extends Consultant> iterator(int i, int i1) {
        int pageNumber = (i / i1) + 1;
        return getResults(pageNumber, i1).iterator();
    }

    public int size() {
        return getResults(-1, -1).size();
    }

    public IModel<Consultant> model(Consultant consultant) {
        return new Model<Consultant>(consultant);
    }

    public void detach() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setAscending(boolean ascending) {
        consultantFilter.setReverse(ascending);
    }

    public boolean isAscending() {
        return consultantFilter.isReverse();
    }

    public void setSortField(String sortField) {
        consultantFilter.setSortField(sortField);
    }

    public String getSortField() {
        return consultantFilter.getSortField();
    }

    public boolean hasSearchCriteria() {
        return consultantFilter.hasSearchCriteria();
    }

    public void addSearchCriteria(String searchField, String searchText) {
        consultantFilter.addSearchCriteria(searchField, searchText);
    }

    public void removeSearchCriteria(String searchField) {
        consultantFilter.removeSearchCriteria(searchField);
    }

    public void clearSearchCriteria() {
         consultantFilter.getSearchFields().clear();
    }

    private List<Consultant> getResults(int page, int resultsPerPage) {
        return utilityManager.getConsultants(consultantFilter, page, resultsPerPage);
    }
}
