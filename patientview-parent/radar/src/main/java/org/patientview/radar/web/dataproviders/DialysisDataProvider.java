package org.patientview.radar.web.dataproviders;

import org.patientview.radar.model.Treatment;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;

import java.util.Iterator;

public class DialysisDataProvider implements IDataProvider<Treatment> {
    public Iterator<? extends Treatment> iterator(int first, int count) {
        return null;  // Todo: Implement
    }

    public int size() {
        return 0;  // Todo: Implement
    }

    public IModel<Treatment> model(Treatment object) {
        return null;  // Todo: Implement
    }

    public void detach() {
        // Todo: Implement
    }
}
