package org.patientview.radar.web.panels;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.navigation.paging.IPageable;
import org.apache.wicket.model.PropertyModel;

public class RadarAjaxPagingNavigator extends AjaxPagingNavigator {

    private WebMarkupContainer summaryContainer;

    public RadarAjaxPagingNavigator(String id, IPageable pageable, int totalResults) {
        super(id, pageable);

        summaryContainer = new WebMarkupContainer("summary");
        summaryContainer.setOutputMarkupId(true);
        summaryContainer.setOutputMarkupPlaceholderTag(true);

        summaryContainer.add(new Label("currentPage", new PropertyModel<Integer>(this, "currentPage")));
        summaryContainer.add(new Label("totalPages", new PropertyModel<Integer>(getPageable(), "pageCount")));
        summaryContainer.add(new Label("totalResults", Integer.toString(totalResults)));

        add(summaryContainer);
    }

    public int getCurrentPage() {
        return getPageable().getCurrentPage() + 1;
    }

    @Override
    protected void onAjaxEvent(AjaxRequestTarget target) {
        super.onAjaxEvent(target);
        target.add(summaryContainer);
    }

}
