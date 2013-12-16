package org.patientview.radar.model.filter;

public class DemographicsFilter extends BaseFilter {
    public enum UserField {
        RADAR_NO("patient.radarNo"),
        SURNAME("patient.surname"),
        FORENAME("patient.forename"),
        REGISTRATION_DATE("patient.dateReg"),
        NHSNO("patient.nhsno"),
        ADDRESS("patient.address1,patient.address2," +
                "patient.address3,patient.address4,patient.POSTCODE"),
        DIAGNOSIS("tbl_DiagCode.dcAbbr"),
        CONSULTANT_FORNAME("tbl_Consultants.cFNAME"),
        CONSULTANT_SURNAME("tbl_Consultants.cSNAME"),
        CENTRE("unit.shortname");

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
