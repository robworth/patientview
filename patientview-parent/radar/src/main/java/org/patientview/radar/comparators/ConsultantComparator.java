package org.patientview.radar.comparators;

import org.patientview.radar.model.Consultant;
import org.apache.wicket.model.PropertyModel;

import java.io.Serializable;
import java.util.Comparator;

public class ConsultantComparator implements Comparator<Consultant>, Serializable {
    private String sortProperty;
    private boolean ascending;

    public int compare(final Consultant o1, final Consultant o2) {
        PropertyModel<Comparable<Consultant>> model1 = new PropertyModel<Comparable<Consultant>>(o1, getSortProperty());
        PropertyModel<Comparable<Consultant>> model2 = new PropertyModel<Comparable<Consultant>>(o2, getSortProperty());

        int result = model1.getObject().compareTo((Consultant) model2.getObject());

        if (!ascending) {
            result = -result;
        }

        return result;
    }

    public String getSortProperty() {
        return sortProperty;
    }

    public void setSortProperty(String sortProperty) {
        this.sortProperty = sortProperty;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }
}
