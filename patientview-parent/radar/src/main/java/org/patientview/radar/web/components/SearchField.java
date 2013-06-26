package org.patientview.radar.web.components;

import org.patientview.radar.web.dataproviders.SortableDataProvider;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;

import java.util.List;

/**
* TextField bound to a Data field - this will update the filter when user puts anything int he field
* This will only update the settings in the provider the onChanged has to be overridden to update the objects
* on the page
*/
public class SearchField extends TextField<String> {
    private SortableDataProvider dataProvider;
    private String searchField;
    private DataView dataView;
    private List<? extends Component> componentsToUpdate;

    public SearchField(final String id, final String searchField, final SortableDataProvider dataProvider,
                       final DataView dataView, final List<? extends Component> componentsToUpdate) {
        super(id, new Model<String>(""));

        this.dataProvider = dataProvider;
        this.searchField = searchField;
        this.dataView = dataView;
        this.componentsToUpdate = componentsToUpdate;

        for (final String s : new String[]{"onchange", "onblur", "onkeyup"}) {
            add(new AjaxFormComponentUpdatingBehavior(s) {
                @Override
                protected void onUpdate(AjaxRequestTarget target) {
                    changed(target);
                }
            });
        }
    }

    private void changed(final AjaxRequestTarget ajaxRequestTarget) {
        String value = getSearchValue();

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

    protected String getSearchValue() {
        String value = "";

        if (getModelObject() != null) {
            value = getModelObject();
        }

        return value;
    }
}
