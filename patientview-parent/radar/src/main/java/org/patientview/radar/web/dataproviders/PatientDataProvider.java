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
