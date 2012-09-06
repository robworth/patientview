package com.worthsoln.ibd.model;

import com.worthsoln.patientview.model.BaseModel;

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
