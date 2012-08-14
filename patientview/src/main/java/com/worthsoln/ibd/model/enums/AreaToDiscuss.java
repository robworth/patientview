package com.worthsoln.ibd.model.enums;

import java.util.Arrays;
import java.util.List;

public enum AreaToDiscuss {
    UNDERSTANDING_YOUR_DISEASE(1, "Understanding your disease"),
    TAKING_CARE_OF_YOURSELF(2, "Taking care of yourself"),
    SPECIFIC_SYMPTOMS_YOUR_ARE_SUFFERING(3, "Specific symptoms you are suffering"),
    MEDICATIONS_FOR_IBD(4, "Medications for IBD"),
    FAMILY_SUPPORT(5, "Family support"),
    WORK_RELATED_ISSUES(6, "Work related issues"),
    CONTACT_WITH_YOUR_HEALTH_TEAM(7, "Contact with your health team"),
    FOOD_AND_YOUR_IBD(8, "Food and your IBD"),
    ALTERNATIVE_AND_COMPLEMENTARY_TREATMENTS(9, "Alternative and complementary treatments"),
    TRAVELLING(10, "Travelling"),
    SURGERY(11, "Surgery"),
    SMOKING_AND_IBD(12, "Smoking and IBD"),
    EXCERCISE_AND_SPORT(13, "Exercise and sport"),
    SEX_AND_RELATIONSHIPS(14, "Sex and Relationships"),
    HEALTH_AND_SUPPORT(15, "Help and support"),
    PLANNING_A_FAMILY_AND_PREGNANCY(16, "Planning a family and pregnancy");

    private long id;
    private String name;

    private AreaToDiscuss(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static AreaToDiscuss getAreasToDiscuss(Long id) {
        for (AreaToDiscuss areasToDiscuss : AreaToDiscuss.values()) {
            if (areasToDiscuss.getId() == id) {
                return areasToDiscuss;
            }
        }

        return null;
    }

    public static List<AreaToDiscuss> getAsList() {
        return Arrays.asList(AreaToDiscuss.values());
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
