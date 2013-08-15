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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.text.DecimalFormat;

@Entity
@Table(name = "ibd_medication_dose")
public class MedicationDose extends BaseModel {

    private static final int MILLION = 1000000;
    private static final int THOUSAND = 1000;

    @Transient
    private static final DecimalFormat FORMAT = new DecimalFormat();

    @Column(nullable = true)
    private Double mg;

    @Column(nullable = true)
    private String extraInformation;

    public MedicationDose() {
        FORMAT.setDecimalSeparatorAlwaysShown(false);
    }

    public String getFormattedValue() {
        String doseValueAsString = "";

        if (mg != null && mg > 0) {
            /**
             * If the mg is grt OR eq to 1,000,000 then convert to KG
             * If the mg is grt OR eq to 1,000 then convert to Grams
             * Else just leave as mg
             */
            if (mg >= MILLION) {
                doseValueAsString = FORMAT.format((mg / (THOUSAND * THOUSAND))) + "Kg";
            } else if (mg >= THOUSAND) {
                doseValueAsString = FORMAT.format((mg / THOUSAND)) + "G";
            } else {
                doseValueAsString = FORMAT.format(mg) + "mg";
            }
        }

        if (extraInformation != null && extraInformation.length() > 0) {
            if (doseValueAsString.length() > 0) {
                doseValueAsString += " ";
            }

            doseValueAsString += extraInformation;
        }

        return doseValueAsString;
    }

    public Double getMg() {
        return mg;
    }

    public void setMg(Double mg) {
        this.mg = mg;
    }

    public String getExtraInformation() {
        return extraInformation;
    }

    public void setExtraInformation(String extraInformation) {
        this.extraInformation = extraInformation;
    }
}
