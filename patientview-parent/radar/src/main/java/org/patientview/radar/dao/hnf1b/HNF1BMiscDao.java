package org.patientview.radar.dao.hnf1b;

import org.patientview.radar.model.hnf1b.HNF1BMisc;

public interface HNF1BMiscDao {

    void save(HNF1BMisc hnf1BMisc);

    HNF1BMisc get(Long radarNo);

}
