package com.solidstategroup.radar.util;

/**
 * Stand alone utility class for converting radar excel data file into sql file for import into the db.
 * creates sql for:
 * 1. prd codes
 * 2. working groups
 * 3. mapping between prd codes and working groups
 */
public class RadarPhase2ExcelDataToSqlMapper {
    private static final String BASE_PATH = "/radarphase2dataimport/";
    public static final int FIRST_DATA_ROW = 2;
    public static final int LAST_DATA_ROW = 287;
    public static final int FIRST_BOOLEAN_FIELD = 2;
    public static final int LAST_BOOLEAN_FIELD = 10;
    public static final int FIRST_WORKING_GROUP_INDEX = 24;
    public static final int LAST_WORKING_GROUP_INDEX = 68;

    public static void main(String params[]) {
    }
}
