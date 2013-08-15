package org.patientview.radar.web.components;

import org.patientview.radar.web.dataproviders.SortableDataProvider;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.repeater.data.DataView;

public class ClearLink extends AjaxLink {
    private SortableDataProvider sortableProvider;
    private DataView dataView;
    private WebMarkupContainer container;

    public ClearLink(String s, SortableDataProvider sortableProvider, DataView dataView, WebMarkupContainer container) {
        super(s);
        this.sortableProvider = sortableProvider;
        this.dataView = dataView;
        this.container = container;

        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);        
    }

    @Override
    protected IAjaxCallDecorator getAjaxCallDecorator() {
        return new IAjaxCallDecorator() {
            public CharSequence decorateScript(Component component, CharSequence charSequence) {
                return "$('input.dxeEditArea').val(''); " + charSequence;
            }

            public CharSequence decorateOnSuccessScript(Component component, CharSequence charSequence) {
                return charSequence;
            }

            public CharSequence decorateOnFailureScript(Component component, CharSequence charSequence) {
                return charSequence;
            }
        };
    }

    @Override
    public void onClick(AjaxRequestTarget ajaxRequestTarget) {
        if (sortableProvider.hasSearchCriteria()) {
            sortableProvider.clearSearchCriteria();
            dataView.setCurrentPage(0);
            ajaxRequestTarget.add(container);
            ajaxRequestTarget.add(this);
        }
    }

    @Override
    public boolean isVisible() {
        return sortableProvider.hasSearchCriteria();
    }
}
