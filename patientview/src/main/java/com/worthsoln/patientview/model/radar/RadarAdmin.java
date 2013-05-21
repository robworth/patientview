package com.worthsoln.patientview.model.radar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 */
@Entity(name = "tbl_adminusers")
public class RadarAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long uID;

    public Long getuID() {
        return uID;
    }

    public void setuID(Long uID) {
        this.uID = uID;
    }
}
