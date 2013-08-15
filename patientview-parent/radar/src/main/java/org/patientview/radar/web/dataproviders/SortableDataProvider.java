package org.patientview.radar.web.dataproviders;

public interface SortableDataProvider {
    void setAscending(boolean ascending);

    boolean isAscending();

    void setSortField(String sortField);

    String getSortField();

    boolean hasSearchCriteria();

    void addSearchCriteria(String searchField, String searchText);

    void removeSearchCriteria(String searchField);

    void clearSearchCriteria();
}
