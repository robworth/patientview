package com.solidstategroup.radar.web.dataproviders;

import com.solidstategroup.radar.model.Demographics;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;

import java.util.Iterator;

public class DemographicsDataProvider implements IDataProvider<Demographics>{
    public Iterator<? extends Demographics> iterator(int first, int count) {
        return null;  // Todo: Implement
    }

    public int size() {
        return 0;  // Todo: Implement
    }

    public IModel<Demographics> model(Demographics object) {
        return null;  // Todo: Implement
    }

    public void detach() {
        // Todo: Implement
    }
}
