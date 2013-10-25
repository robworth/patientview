package org.patientview.model.radar.filter;

public class PatientUserFilter extends BaseFilter {
    public enum UserField {
        RADAR_NO("tbl_Patient_Users.RADAR_NO"),
        SURNAME("patient.surname"), // TODO: cant search these fields at the mo as they are encrypted
        FORENAME("patient.forename"), // TODO: cant search these fields at the mo as they are encrypted
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
        setSortField(UserField.RADAR_NO.getDatabaseFieldName());
    }
}
