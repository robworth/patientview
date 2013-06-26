package org.patientview.radar.web.dataproviders;

import org.patientview.radar.model.Transplant;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;

import java.util.Iterator;

public class TransplantDataProvider implements IDataProvider<Transplant> {

    public TransplantDataProvider(IModel<Long> radarNumberModel) {
        // Todo: Implement
    }

    public Iterator<? extends Transplant> iterator(int first, int count) {
        return null;  // Todo: Implement
    }

    public int size() {
        return 0;  // Todo: Implement
    }

    public IModel<Transplant> model(Transplant object) {
        return null;  // Todo: Implement
    }

    public void detach() {
        // Todo: Implement
    }
}
