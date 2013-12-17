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
