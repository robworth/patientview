package org.patientview.radar.web.dataproviders;

import org.patientview.radar.model.Plasmapheresis;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;

import java.util.Iterator;

public class PlasmapheresisDataProvider implements IDataProvider<Plasmapheresis> {

    public Iterator<? extends Plasmapheresis> iterator(int first, int count) {
        return null;  // Todo: Implement
    }

    public int size() {
        return 0;  // Todo: Implement
    }

    public IModel<Plasmapheresis> model(Plasmapheresis object) {
        return null;  // Todo: Implement
    }

    public void detach() {
        // Todo: Implement
    }
}
