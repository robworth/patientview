package com.worthsoln.ibd.model.medication;

import com.worthsoln.patientview.model.BaseModel;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "ibd_medication")
public class Medication extends BaseModel {

    @Column(nullable = false)
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "ibd_medication_allowed_dosages",
            joinColumns = {@JoinColumn(name = "medication_id")},
            inverseJoinColumns = {@JoinColumn(name = "dose_id")}
    )
    private List<MedicationDose> allowedDosages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MedicationDose> getAllowedDosages() {
        return allowedDosages;
    }

    public void setAllowedDosages(List<MedicationDose> allowedDosages) {
        this.allowedDosages = allowedDosages;
    }
}
