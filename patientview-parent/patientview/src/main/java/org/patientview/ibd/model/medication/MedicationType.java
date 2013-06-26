/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.ibd.model.medication;

import org.patientview.patientview.model.BaseModel;
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

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "ibd_medication_type_medications",
            joinColumns = { @JoinColumn(name = "medication_type_id") },
            inverseJoinColumns = { @JoinColumn(name = "medication_id") }
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
