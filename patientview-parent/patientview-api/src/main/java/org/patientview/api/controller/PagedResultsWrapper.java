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
package org.patientview.api.controller;


import java.util.List;

public class PagedResultsWrapper<T> {

    private List<T> results;
    private int pageSize;
    private int pageNumber;
    private Long totalResults;

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Long totalResults) {
        this.totalResults = totalResults;
    }
}
