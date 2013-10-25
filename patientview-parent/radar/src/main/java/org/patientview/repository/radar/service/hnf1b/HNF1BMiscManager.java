package org.patientview.repository.radar.service.hnf1b;

import org.patientview.model.radar.hnf1b.HNF1BMisc;

public interface HNF1BMiscManager {

    void save(HNF1BMisc hnf1BMisc);

    HNF1BMisc get(Long radarNo);

}
