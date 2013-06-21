package org.patientview.radar.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
        InputStream inp = null;
        try {
            /************** 1. first create the prd codes sql  ***************/
            // this is the file from radar originally called ERA_EDTA_new_PRD_codes_27042012_def for NDT_RADAR
            inp = new FileInputStream(BASE_PATH + "input/prd_codes_and_working_group.xls");
            Workbook wb = WorkbookFactory.create(inp);
            Sheet sheet = wb.getSheetAt(0);
            // each list item represents a row to insert
            List<List<String>> dataList = new ArrayList<List<String>>();

            //iterate through the rows in excel file
            for (Row row : sheet) {
                // ignore non data rows
                if (row.getRowNum() < FIRST_DATA_ROW) {
                    continue;
                } else if (row.getRowNum() > LAST_DATA_ROW) {
                    break;
                }

                List<String> values = new ArrayList<String>();
                // iterate through cells
                for (int cn = 0; cn < row.getLastCellNum(); cn++) {
                    Cell cell = row.getCell(cn);
                    // ignore non data cells
                    if (cn > 23) {
                        break;
                    }
                    String value = "";
                    if (cell != null) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        value = cell.getStringCellValue();
                    }
                    // convert x values to 1 which means true, or blank to 0 which means false
                    if ((cn >= FIRST_BOOLEAN_FIELD && cn <= LAST_BOOLEAN_FIELD)) {
                        value = value.equals("x") ? "1" : "0";
                    }

                    values.add(value);
                }

                dataList.add(values);
            }

            StringBuilder outputText = new StringBuilder();
            StringBuilder prdSql = new StringBuilder();
            String sqlBaseInsert = "INSERT INTO rdr_prd_code(ERA_EDTA_PRD_code, ERA_EDTA_primaryRenalDiagnosisTerm, " +
                    "histology, clinicalHistory, familyHistory, clinicalExam, biochemistry, immunology, " +
                    "urineAnalysis, " + "imaging, geneTest, otherCriteriaAndNotes, " +
                    "SNOMED_CT_conceptIdentifierForFocusConcept, " + "SNOMED_CT_fullySpecifiedName, " +
                    "SNOMED_CT_expressionConstraint, majorHeading, mappingToOldPRDCode, " +
                    "mappingToOldPRDTerm, ERA_EDTA_defaultSortOrder, geneticsHomeReferenceLink, " +
                    "nationalCenterForBiotechnologyLink, ICD_10_code, ICD10_rubricTerm, alternativesearchTerms) " +
                    "VALUES (";
            int index = 0;

            // for each row in the data list create an sql insert statement
            for (List<String> row : dataList) {
                String sqlInsert = sqlBaseInsert;
                int valueIndex = 0;
                for (String value : row) {
                    value = value.replace("'", "").replace("\"", "");
                    sqlInsert += "'" + value + "'" + (valueIndex != row.size() - 1 ? "," : "");
                    valueIndex++;
                }
                sqlInsert += ");" + System.getProperty("line.separator");
                prdSql.append(sqlInsert);
                index++;
            }

            // append to output text - output text will eventually be written to a file
            outputText.append(prdSql +  System.getProperty("line.separator"));

            /************** 2. create the working groups sql  ***************/
            Row row = sheet.getRow(1);
            List<String> workingGroups = new ArrayList<String>();
            // iterate through all working groups
            for (Cell cell : row) {
                if (cell.getColumnIndex() < FIRST_WORKING_GROUP_INDEX) {
                    continue;
                } else if (cell.getColumnIndex() > LAST_WORKING_GROUP_INDEX) {
                    break;
                }
                cell.setCellType(Cell.CELL_TYPE_STRING);
                String value = cell.getStringCellValue();
                value = value.replace("'", "\\'");
                workingGroups.add(value);
            }

            // create sql for working groups sql insert
            String workingGroupSql = "" +
                    System.getProperty("line.separator");
            int workingGroupIndex = 0;
            for (String workingGroup : workingGroups) {
                String unitCode = workingGroup.split(" ")[0]+workingGroupIndex; // this is id, has to be unique
                workingGroupSql += "INSERT INTO unit(unitcode, name, shortName, sourceType) VALUES('"+unitCode+"', '"+
                        workingGroup + "', '','radargroup');" + System.getProperty("line.separator");
                workingGroupIndex++;
            }

            // append to output text - output text will eventually be written to a file
            outputText.append(workingGroupSql + System.getProperty("line.separator"));

            /************** 3. create the mapping table sql - this is the tricky bit!  ***************/
            List<List<String>> mappingData = new ArrayList<List<String>>();

            // for each working group collect mapping values to working group
            for (int columnIndex = FIRST_WORKING_GROUP_INDEX; columnIndex < LAST_WORKING_GROUP_INDEX;
                 columnIndex++) {
                List<String> list = new ArrayList<String>();
                for (int rowIndex = FIRST_DATA_ROW; rowIndex <= LAST_DATA_ROW; rowIndex++) {
                    Row mappingRow = sheet.getRow(rowIndex);
                    Cell cell = mappingRow.getCell(columnIndex);
                    String value = "0";
                    if (cell != null) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        value = cell.getStringCellValue();
                    }
                    list.add(value);
                }
                mappingData.add(list);
            }

            // create list of prd ids
            List<String> prdIds = new ArrayList<String>();
            for (int i = FIRST_DATA_ROW; i <= LAST_DATA_ROW; i++) {
                Row aRow = sheet.getRow(i);
                Cell cell = aRow.getCell(0);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                String value = cell.getStringCellValue();
                prdIds.add(value);
            }

            // create sql insert statements based on where working group and disease intersect
            String mappingSql = "";
            String baseSql = "INSERT INTO rdr_diagnosis_mapping(workingGroup, PRDCode, ordering) VALUES(";
            for (int i = 0; i < mappingData.size(); i++) {
                String sql = "";
                List<String> list = mappingData.get(i);
                for (int j = 0; j < list.size(); j++) {
                    sql = baseSql;
                    String value = list.get(j);
                    if (!value.equals("0")) {
                        sql += "'" + (workingGroups.get(i).split(" ")[0] + i) + "', '" + prdIds.get(j) + "','"
                                + value + "');";
                        if (!sql.equals(baseSql)) {
                            mappingSql += sql + System.getProperty("line.separator");
                        }
                    }
                }

            }

            outputText.append(mappingSql);
            // output all sql stuff to file
            FileWriter fileWriter = new FileWriter(BASE_PATH + "output/phase2Data.sql");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(outputText.toString());
            //Close the output stream
            bufferedWriter.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }
}
