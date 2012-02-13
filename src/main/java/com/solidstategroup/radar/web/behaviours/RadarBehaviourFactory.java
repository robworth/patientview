package com.solidstategroup.radar.web.behaviours;

import org.apache.wicket.model.Model;
import java.io.Serializable;

public class RadarBehaviourFactory {

    public static AttributePrepender getDeleteConfirmationBehaviour() {
        return AttributePrepender.getNewInstance("onclick", new Model<Serializable>("if(!confirm('Are you sure " +
                "you want to delete this record?')){return false;}"));
    }
}
