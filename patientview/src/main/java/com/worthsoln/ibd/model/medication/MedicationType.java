package com.worthsoln.ibd.model.medication;

import com.worthsoln.patientview.model.BaseModel;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "ibd_medication_type")
public class MedicationType extends BaseModel {

    @Column(nullable = false)
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "ibd_medication_type_medications",
            joinColumns = {@JoinColumn(name = "medication_type_id")},
            inverseJoinColumns = {@JoinColumn(name = "medication_id")}
    )
    private List<Medication> medications;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }
}
