package com.solidstategroup.radar.model.filter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ProfessionalUserFilter  implements Serializable {

    public enum UserField {
        SURNAME("uSurname"),
        FORENAME("uForename"),
        TITLE("uTitle"),
        ROLE("uRole"),
        EMAIL("uEmail"),
        CENTRE("cName"),
        REGISTRATION_DATE("uDateJoin"),
        GMC("uGMC");

        private String databaseFieldName;

        private UserField(String databaseFieldName) {
            this.databaseFieldName = databaseFieldName;
        }

        public String getDatabaseFieldName() {
            return databaseFieldName;
        }
    }
    
    private Map<UserField, String> searchFields;
    private UserField sortField;
    private boolean reverse;

    public ProfessionalUserFilter() {
        searchFields = new HashMap<UserField, String>();
        sortField = UserField.SURNAME;
        reverse = true;
    }

    public ProfessionalUserFilter(Map<UserField, String> searchFields, UserField sortField, boolean reverse) {
        this.searchFields = searchFields;
        this.sortField = sortField;
        this.reverse = reverse;
    }

    public void addSearchCriteria(UserField userField, String searchTerm) {
        searchFields.put(userField, searchTerm);
    }
    
    public void removeSearchCriteria(UserField userField) {
        searchFields.remove(userField);
    }

    public boolean hasSearchFilter() {
        return searchFields != null && !searchFields.isEmpty();
    }

    public boolean hasSortFilter() {
        return sortField != null;
    }

    public Map<UserField, String> getSearchFields() {
        return searchFields;
    }

    public void setSearchFields(Map<UserField, String> searchFields) {
        this.searchFields = searchFields;
    }

    public UserField getSortField() {
        return sortField;
    }

    public void setSortField(UserField sortField) {
        this.sortField = sortField;
    }

    public boolean isReverse() {
        return reverse;
    }

    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }

}
