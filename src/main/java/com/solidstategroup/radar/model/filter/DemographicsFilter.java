package com.solidstategroup.radar.model.filter;

public class DemographicsFilter extends BaseFilter {
    public enum UserField {
        RADAR_NO("RADAR_NO"),
        SURNAME("uSurname"),
        FORENAME("uForename"),
        REGISTRATION_DATE("DATE_REG"),
        ADDRESS("ADD1,ADD2,ADD3,ADD4,POSTCODE"),
        DIAGNOSIS("dcAbbr"),
        CONSULTANT_FORNAME("cFNAME"),
        CONSULTANT_SURNAME("cSNAME"),
        CENTRE("cAbbrev");

        private String databaseFieldName;

        private UserField(String databaseFieldName) {
            this.databaseFieldName = databaseFieldName;
        }

        public static UserField getUserField(String fieldName) {
            for (UserField userField : values()) {
                if (userField.getDatabaseFieldName().equalsIgnoreCase(fieldName)) {
                    return userField;
                }
            }

            return UserField.SURNAME;
        }

        public String getDatabaseFieldName() {
            return databaseFieldName;
        }
    }

    public DemographicsFilter() {
        super();
        sortField = UserField.RADAR_NO.getDatabaseFieldName();
    }
}
