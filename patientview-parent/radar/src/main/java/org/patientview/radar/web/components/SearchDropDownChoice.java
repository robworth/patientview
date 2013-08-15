package org.patientview.radar.web.components;

import org.patientview.radar.web.dataproviders.SortableDataProvider;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;

import java.util.List;

public class SearchDropDownChoice<T> extends DropDownChoice {
    private SortableDataProvider dataProvider;
    private String searchField;
    private DataView dataView;
    private List<? extends Component> componentsToUpdate;

    public SearchDropDownChoice(String s, List list, final String searchField,
                                final SortableDataProvider dataProvider, final DataView dataView,
                                final List<? extends Component> componentsToUpdate) {
        super(s, new Model<String>(null), list);

        this.dataProvider = dataProvider;
        this.searchField = searchField;
        this.dataView = dataView;
        this.componentsToUpdate = componentsToUpdate;

        add(new AjaxFormComponentUpdatingBehavior("onchange") {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                changed(target);
            }
        });
    }

    private void changed(final AjaxRequestTarget ajaxRequestTarget) {
        String value = "";

        if (getModelObject() != null) {
            value = getModelObject().toString();
        }

        if (value == null || value.length() == 0) {
            // if they type nothing in then just bring back all the results
            dataProvider.removeSearchCriteria(searchField);
        } else {
            dataProvider.addSearchCriteria(searchField, value);
        }

        dataView.setCurrentPage(0);

        if (componentsToUpdate != null) {
            for (Component component : componentsToUpdate) {
                ajaxRequestTarget.add(component);
            }
        }
    }
}
