package org.patientview.radar.model.filter;

public class PatientUserFilter extends BaseFilter {
    public enum UserField {
        RADAR_NO("tbl_Patient_Users.RADAR_NO"),
        SURNAME("tbl_Demographics.SNAME"), // TODO: cant search these fields at the mo as they are encrypted
        FORENAME("tbl_Demographics.FNAME"), // TODO: cant search these fields at the mo as they are encrypted
        REGISTRATION_DATE("pDateReg"),
        DOB("pDob"),
        USERNAME("username");

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

            return UserField.RADAR_NO;
        }

        public String getDatabaseFieldName() {
            return databaseFieldName;
        }
    }

    public PatientUserFilter() {
        super();
        sortField = UserField.RADAR_NO.getDatabaseFieldName();
    }    
}
