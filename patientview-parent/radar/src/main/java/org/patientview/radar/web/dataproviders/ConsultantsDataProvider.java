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
