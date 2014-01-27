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

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.patientview.model.Patient;

import java.util.Iterator;

/**
 * User: james@solidstategroup.com
 * Date: 06/11/13
 * Time: 11:19
 */
public class PatientDataProvider  implements IDataProvider<Patient>, SortableDataProvider {
    public Iterator<? extends Patient> iterator(int i, int i2) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int size() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public IModel<Patient> model(Patient patient) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void detach() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setAscending(boolean ascending) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean isAscending() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setSortField(String sortField) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public String getSortField() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean hasSearchCriteria() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void addSearchCriteria(String searchField, String searchText) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void removeSearchCriteria(String searchField) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void clearSearchCriteria() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
