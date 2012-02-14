package com.solidstategroup.radar.web.components;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import com.solidstategroup.radar.web.dataproviders.user.SortableProvider;

/**
* TextField bound to a Data field - this will update the filter when user puts anything int he field
* This will only update the settings in the provider the onChanged has to be overridden to update the objects
* on the page
*/
public abstract class SearchField extends TextField<String> {
    private SortableProvider dataProvider;
    private String searchField;

    public SearchField(final String id, final String searchField, final SortableProvider dataProvider) {
        super(id, new Model<String>(""));

        this.dataProvider = dataProvider;
        this.searchField = searchField;

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
        final String value = getModelObject();

        if (value == null || value.length() == 0) {
            // if they type nothing in then just bring back all the results
            dataProvider.removeSearchCriteria(searchField);
            onChanged(ajaxRequestTarget);
        } else {
            dataProvider.addSearchCriteria(searchField, value);
            onChanged(ajaxRequestTarget);
        }
    }

    public abstract void onChanged(final AjaxRequestTarget ajaxRequestTarget);
}
