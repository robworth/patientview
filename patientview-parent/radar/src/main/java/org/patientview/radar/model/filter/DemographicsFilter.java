package org.patientview.radar.model.filter;

public class DemographicsFilter extends BaseFilter {
    public enum UserField {
        RADAR_NO("tbl_Demographics.RADAR_NO"),
        SURNAME("tbl_Demographics.SNAME"),
        FORENAME("tbl_Demographics.FNAME"),
        REGISTRATION_DATE("tbl_Demographics.DATE_REG"),
        NHS_NO("tbl_Demographics.NHS_NO"),
        ADDRESS("tbl_Demographics.ADD1,tbl_Demographics.ADD2," +
                "tbl_Demographics.ADD3,tbl_Demographics.ADD4,tbl_Demographics.POSTCODE"),
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
