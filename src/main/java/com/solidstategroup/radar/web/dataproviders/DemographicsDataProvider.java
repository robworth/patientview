package com.solidstategroup.radar.web.dataproviders;

import com.solidstategroup.radar.dao.DemographicsDao;
import com.solidstategroup.radar.model.Centre;
import com.solidstategroup.radar.model.Demographics;
import com.solidstategroup.radar.service.DemographicsManager;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import java.util.Iterator;
import java.util.List;

public class DemographicsDataProvider implements IDataProvider<Demographics> {

    private Centre centre;
    private DemographicsManager demographicsManager;

    public DemographicsDataProvider(DemographicsManager demographicsManager, Centre centre) {
        this.demographicsManager = demographicsManager;
        this.centre = centre;
    }

    public DemographicsDataProvider(DemographicsManager demographicsManager) {
        this.demographicsManager = demographicsManager;
    }

    public Iterator<? extends Demographics> iterator(int first, int count) {
        // Todo: Paging
        return getResults().iterator();
    }

    public int size() {
        return getResults().size();
    }

    private List<Demographics> getResults() {

        if (centre != null) {
            return demographicsManager.getDemographicsByRenalUnit(centre);
        } else {
            return demographicsManager.getDemographics();
        }
    }

    public IModel<Demographics> model(Demographics demographics) {
        return new CompoundPropertyModel<Demographics>(demographics);
    }

    public void detach() {
        // Nothing to do here
    }
}
