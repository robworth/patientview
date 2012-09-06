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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name = "ibd_medication_type")
public class MedicationType extends BaseModel {

    @Column(nullable = false, columnDefinition = "TEXT")
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @LazyCollection(LazyCollectionOption.FALSE)
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
        // return the medications in alpha order
        if (medications != null) {
            Collections.sort(medications, new Comparator<Medication>() {
                @Override
                public int compare(Medication o1, Medication o2) {
                if (o1.getId() > o2.getId()) {
                    return 1;
                } else if (o1.getId() < o2.getId()) {
                    return -1;
                } else {
                    return 0;
                }
                }
            });
        }

        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }
}
