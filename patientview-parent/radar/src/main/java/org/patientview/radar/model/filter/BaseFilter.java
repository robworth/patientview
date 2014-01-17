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

package org.patientview.radar.model.filter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BaseFilter implements Serializable {

    protected Map<String, String> searchFields;
    protected String sortField;
    protected boolean reverse;

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
