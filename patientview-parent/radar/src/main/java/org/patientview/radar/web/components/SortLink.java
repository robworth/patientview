package org.patientview.radar.web.components;

import org.patientview.radar.web.dataproviders.SortableDataProvider;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;

import java.util.List;

/**
 * AjaxLink bound to a Data field - this will update the filter when user clicks and set the sort on the page
 */
public class SortLink extends AjaxLink {
    private static final String SORT_LINK_CLASS = "sortLink";
    private static final String SORT_ASC_CLASS = "sortAsc";
    private static final String SORT_DESC_CLASS = "sortDesc";
    private static final String REMOVE_CLASSES_JAVASCRIPT = "$('." + SORT_LINK_CLASS + "').removeClass('"
            + SORT_ASC_CLASS + " " + SORT_DESC_CLASS + "');";

    private String sortField;
    private SortableDataProvider dataProvider;
    private DataView dataView;
    private List<? extends Component> componentsToUpdate;

    public SortLink(final String id, final String sortField, final SortableDataProvider dataProvider,
                    final DataView dataView, final List<? extends Component> componentsToUpdate) {
        super(id);
        this.sortField = sortField;
        this.dataProvider = dataProvider;
        this.dataView = dataView;
        this.componentsToUpdate = componentsToUpdate;

        String classValue = SORT_LINK_CLASS;

        if (dataProvider.getSortField().equalsIgnoreCase(sortField)) {
           classValue += " " + (dataProvider.isAscending() ? SORT_DESC_CLASS : SORT_ASC_CLASS);
        }

        add(new AttributeAppender("class", new Model<String>(classValue), " "));

        setOutputMarkupId(true);
        setOutputMarkupPlaceholderTag(true);
    }

    @Override
    public void onClick(final AjaxRequestTarget ajaxRequestTarget) {
        if (dataProvider.isAscending()) {
            dataProvider.setAscending(false);
            ajaxRequestTarget.appendJavaScript(REMOVE_CLASSES_JAVASCRIPT + "$('#" + this.getMarkupId()
                    + "').removeClass('" + SORT_DESC_CLASS
                    + "').addClass('" + SORT_ASC_CLASS + "');");
        } else {
            dataProvider.setAscending(true);
            ajaxRequestTarget.appendJavaScript(REMOVE_CLASSES_JAVASCRIPT + "$('#" + this.getMarkupId()
                    + "').removeClass('" + SORT_ASC_CLASS
                    + "').addClass('" + SORT_DESC_CLASS + "');");
        }

        dataProvider.setSortField(sortField);

        dataView.setCurrentPage(0);

        if (componentsToUpdate != null) {
            for (Component component : componentsToUpdate) {
                ajaxRequestTarget.add(component);
            }
        }
    }
}
