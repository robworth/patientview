package org.patientview.radar.web.models;


import org.apache.wicket.model.AbstractReadOnlyModel;

/**
 * this model is used for displaying page numbers on pages
 * it is abstract so the getObject method has to be defined by implementations
 */

public abstract class PageNumberModel extends AbstractReadOnlyModel<String> {
    private int pageNumber;

    public PageNumberModel(final int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}
