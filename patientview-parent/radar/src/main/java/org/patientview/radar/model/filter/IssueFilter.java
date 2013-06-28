package org.patientview.radar.model.filter;

public class IssueFilter extends BaseFilter {
    public enum Field {
        ID("bID"),
        TYPE("bType"),
        PAGE("bPage"),
        DATE_LOGGED("bDateLogged"),
        DATE_RESOLVED("bDateResolved"),
        DESC("bDesc"),
        COMMENTS("bComment"),
        PRIORITY("bPriority"),
        STATUS("bStatus"),
        UPDATED("bUpdated");

        private String databaseFieldName;

        private Field(String databaseFieldName) {
            this.databaseFieldName = databaseFieldName;
        }

        public static Field getField(String fieldName) {
            for (Field field : values()) {
                if (field.getDatabaseFieldName().equalsIgnoreCase(fieldName)) {
                    return field;
                }
            }

            return Field.ID;
        }

        public String getDatabaseFieldName() {
            return databaseFieldName;
        }
    }

    public IssueFilter() {
        super();
        sortField = Field.ID.getDatabaseFieldName();
    }
}
