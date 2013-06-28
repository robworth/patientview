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

package org.patientview.ibd.model;

import org.patientview.patientview.model.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "ibd_nutrition")
public class Nutrition extends BaseModel {

    @Column(nullable = false)
    private String nhsno;

    @Column(nullable = false)
    private Date nutritionDate;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String foodsThatDisagree;

    @Column(nullable = true, columnDefinition = "TEXT")
    private String comment;

    public String getFoodsThatDisagree() {
        return foodsThatDisagree;
    }

    public void setFoodsThatDisagree(String foodsThatDisagree) {
        this.foodsThatDisagree = foodsThatDisagree;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public Date getNutritionDate() {
        return nutritionDate;
    }

    public void setNutritionDate(Date nutritionDate) {
        this.nutritionDate = nutritionDate;
    }

    public String getFormattedNutritionDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yy HH:mm");

        Calendar cal = Calendar.getInstance();
        cal.setTime(nutritionDate);

        if ((cal.get(Calendar.HOUR_OF_DAY) == 0) && (cal.get(Calendar.MINUTE) == 0)) {
            return dateFormat.format(cal.getTime());
        } else {
            return dateTimeFormat.format(cal.getTime());
        }
    }
}
