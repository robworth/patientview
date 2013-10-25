package org.patientview.model.radar.filter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BaseFilter implements Serializable {

    private Map<String, String> searchFields;
    private String sortField;
    private boolean reverse;

    public BaseFilter() {
        searchFields = new HashMap<String, String>();
        reverse = true;
    }

    public BaseFilter(Map<String, String> searchFields, String sortField, boolean reverse) {
        this.searchFields = searchFields;
        this.sortField = sortField;
        this.reverse = reverse;
    }

    public Map<String, String> getSearchFields() {
        return searchFields;
    }

    public boolean hasSortFilter() {
        return sortField != null;
    }

    public void addSearchCriteria(String searchField, String searchTerm) {
        searchFields.put(searchField, searchTerm);
    }

    public void removeSearchCriteria(String searchField) {
        searchFields.remove(searchField);
    }

    public boolean hasSearchCriteria() {
        return searchFields != null && !searchFields.isEmpty();
    }

    public void setSearchFields(Map<String, String> searchFields) {
        this.searchFields = searchFields;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public boolean isReverse() {
        return reverse;
    }

    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }
}
