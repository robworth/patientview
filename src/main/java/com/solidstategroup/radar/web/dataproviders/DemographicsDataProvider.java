package com.solidstategroup.radar.web.dataproviders;

import com.solidstategroup.radar.dao.DemographicsDao;
import com.solidstategroup.radar.model.Centre;
import com.solidstategroup.radar.model.Demographics;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import java.util.Iterator;
import java.util.List;

public class DemographicsDataProvider implements IDataProvider<Demographics> {

    private Centre centre;
    private DemographicsDao demographicsDao;

    public DemographicsDataProvider(DemographicsDao demographicsDao, Centre centre) {
        this.demographicsDao = demographicsDao;
        this.centre = centre;
    }

    public DemographicsDataProvider(DemographicsDao demographicsDao) {
        this.demographicsDao = demographicsDao;
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
            return demographicsDao.getDemographicsByRenalUnit(centre);
        } else {
            return demographicsDao.getDemographics();
        }
    }

    public IModel<Demographics> model(Demographics demographics) {
        return new CompoundPropertyModel<Demographics>(demographics);
    }

    public void detach() {
        // Nothing to do here
    }
}
