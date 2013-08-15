package org.patientview.radar.model.filter;

public class ConsultantFilter extends BaseFilter {
    public enum UserField {
        SURNAME("cSNAME"),
        FORENAME("cFNAME"),
        CENTRE("cName");

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

    public ConsultantFilter() {
        super();
        sortField = UserField.SURNAME.getDatabaseFieldName();
    }
}
