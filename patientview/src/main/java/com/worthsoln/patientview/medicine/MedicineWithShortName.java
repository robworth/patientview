package com.worthsoln.patientview.medicine;

import com.worthsoln.patientview.model.Medicine;

public class MedicineWithShortName extends Medicine {

    private String shortname;

    public MedicineWithShortName() {
    }

    public MedicineWithShortName(Medicine med, String shortName) {
        super(med.getId(), med.getNhsno(), med.getUnitcode(), med.getStartdate(), med.getName(), med.getDose());
        setShortname(shortName);
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }
}