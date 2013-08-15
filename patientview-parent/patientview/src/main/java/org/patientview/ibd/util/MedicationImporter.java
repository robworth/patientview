/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.ibd.util;

import org.patientview.ibd.model.medication.Medication;
import org.patientview.ibd.model.medication.MedicationDose;
import org.patientview.ibd.model.medication.MedicationType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Will import medication from a spread sheet NOT A CSV!
 * <p/>
 * Spread sheet is expected to have column headers in the first row
 * Required columns are -
 * <p/>
 * Medication Type, Medication Name, Medication Dose MG
 * <p/>
 * If you have multiple medications for a medication type you need a row for each with the same medication type name
 * in each row
 * <p/>
 * The allowed dosages for a medication need to be in mg and seperated by a :
 * If a dosage has no MG value then you have to put 0 as the value
 * If a dosage has extra information you can seperate the mg value and the extra info with a _
 * e.g. 400:800:1200:1600:2400_This is extra information:3200:4800
 * <p/>
 * Example below for 1 medication type with two medications
 * <p/>
 * Aminosalicylates (Mesalazine / 5 ASAs), Asacol MR 800 mg tablet, 400:800:1200:1600:2400_Test info:3200:4800
 * Aminosalicylates (Mesalazine / 5 ASAs), Asacol MR 400 mg tablet, 800:1600:2400:3200:4800
 */
public class MedicationImporter {

    // we should only have 3 cols in the sheet
    private static final int MAX_COLS = 3;
    private static final int MEDICATION_TYPE_NAME_COL = 0;
    private static final int MEDICATION_NAME_COL = 1;
    private static final int MEDICATION_DOSAGES_COL = 2;

    private Long medicationTypeCurrentId = 1L;
    private Long medicationCurrentId = 1L;
    private Long medicationDoseCurrentId = 1L;

    private LinkedHashMap<String, MedicationType> medicationTypes = new LinkedHashMap<String, MedicationType>();

    private String outputFileLocation;

    public void run(String excelFileLocation, String outputFileLocation) {
        this.outputFileLocation = outputFileLocation;

        // first check to see if the file already exists and if it does delete it so we dont append more sql
        // to what exists in it from a previous export
        if (fileExists(outputFileLocation)) {
            deleteFile(outputFileLocation);
        }

        try {
            // try and read the file
            InputStream inp = new FileInputStream(excelFileLocation);

            // create a spreadsheet so we can move through it
            Workbook wb = WorkbookFactory.create(inp);
            Sheet sheet = wb.getSheetAt(0);

            for (Row row : sheet) {
                String medicationTypeName = null;
                String medicationName = null;
                String medicationDosages = null;

                // first row is the title of the columns
                if (row.getRowNum() > 0) {
                    for (int cn = 0; cn < row.getLastCellNum(); cn++) {
                        // we only expect 3 cols of data
                        if (cn > MAX_COLS) {
                            break;
                        }

                        Cell cell = row.getCell(cn);

                        if (cell != null) {
                            cell.setCellType(Cell.CELL_TYPE_STRING);

                            String value = cell.getStringCellValue();

                            // check what cell it is and set data we need
                            if (cn == MEDICATION_TYPE_NAME_COL) {
                                medicationTypeName = value.trim();
                            } else if (cn == MEDICATION_NAME_COL) {
                                medicationName = value.trim();
                            } else if (cn == MEDICATION_DOSAGES_COL) {
                                medicationDosages = value.trim();
                            }

                            if (medicationTypeName != null && medicationTypeName.length() > 0
                                    && medicationName != null && medicationName.length() > 0
                                    && medicationDosages != null && medicationDosages.length() > 0) {
                                // first check if a medication type already exists with this name else create
                                MedicationType medicationType = medicationTypes.get(medicationTypeName);

                                if (medicationType == null) {
                                    medicationType = new MedicationType();
                                    medicationType.setName(medicationTypeName);
                                    medicationType.setMedications(new ArrayList<Medication>());
                                    medicationTypes.put(medicationTypeName, medicationType);

                                    // set the id and increment for the next one
                                    medicationType.setId(medicationTypeCurrentId);
                                    medicationTypeCurrentId++;
                                }

                                // then create a medication object that we can assign the dosages
                                Medication medication = new Medication();
                                medication.setName(medicationName);

                                // now parse any dosages and assign to the medication
                                medication.setAllowedDosages(parseMedicationDosages(medicationDosages));

                                // set the id and increment for the next one
                                medication.setId(medicationCurrentId);
                                medicationCurrentId++;

                                // add this medication to the medication type
                                medicationType.getMedications().add(medication);
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file " + e);
        } catch (IOException e) {
            System.out.println("Could not read file " + e);
        } catch (Exception e) {
            System.out.println("Unknown error " + e);
        }

        // if the file was processed and we have any objects then build some sql statements
        if (!medicationTypes.isEmpty()) {
            for (MedicationType medicationType : medicationTypes.values()) {
                // first enter the medications and dosages
                buildMedicationSqlInsert(medicationType.getMedications());

                // then buld the sql for the medication type and map the medications to it
                buildMedicationTypeSqlInsert(medicationType);
            }
        }

        System.out.println("Import file created " + outputFileLocation);
    }

    private void buildMedicationSqlInsert(List<Medication> medications) {
        String sql = "";

        for (Medication medication : medications) {
            writeLine("insert into `ibd_medication`(`id`,`name`) values (" + medication.getId() + ",'"
                    + medication.getName() + "');");

            // then build the dosages sql for this medication
            buildMedicationDoseSqlInsert(medication);
        }
    }

    private void buildMedicationDoseSqlInsert(Medication medication) {
        String sql = "";

        for (MedicationDose medicationDose : medication.getAllowedDosages()) {
            // insert the dosage into the db
            String extraInfo = "NULL";

            if (medicationDose.getExtraInformation() != null && medicationDose.getExtraInformation().length() > 0) {
                extraInfo = "'" + medicationDose.getExtraInformation() + "'";
            }

            writeLine("insert into `ibd_medication_dose`(`id`,`extraInformation`,"
                    + "`mg`) values (" + medicationDose.getId()
                    + "," + extraInfo + "," + medicationDose.getMg() + ");");

            // then map our dosage to the medication
            writeLine("insert into `ibd_medication_allowed_dosages`(`medication_id`,`dose_id`) values ("
                    + medication.getId() + "," + medicationDose.getId() + ");");
        }
    }

    private void buildMedicationTypeSqlInsert(MedicationType medicationType) {
        // store the type in the db
        writeLine("insert into `ibd_medication_type`(`id`,`name`) values (" + medicationType.getId()
                + ",'" + medicationType.getName() + "');");

        // then map its medications to it
        for (Medication medication : medicationType.getMedications()) {
            writeLine("insert into `ibd_medication_type_medications`(`medication_type_id`,`medication_id`) values ("
                    + medicationType.getId() + "," + medication.getId() + ");");
        }
    }

    /**
     * Will take a string in the format of 400:600:800 where is dosage is in mg and seperated by a semicolon
     * To add extra information to a dosage use the format 400:600_Extra Info:800 where extra info is split with a _
     *
     * @param dosages String of dosages
     * @return List<MedicationDose>
     */
    private List<MedicationDose> parseMedicationDosages(String dosages) {
        List<MedicationDose> medicationDosages = new ArrayList<MedicationDose>();
        String[] dosagesArray;

        // check to see if there is more than one dosage and split
        if (dosages.contains(":")) {
            dosagesArray = dosages.split(":");
        } else {
            dosagesArray = new String[]{dosages};
        }

        if (dosagesArray != null) {
            for (String s : dosagesArray) {
                String doseMg = null;
                String doseExtraInfo = null;

                if (s.contains("_")) {
                    String[] doseInfo = s.split("_");

                    doseMg = doseInfo[0];

                    if (doseInfo.length > 1) {
                        doseExtraInfo = doseInfo[1];
                    }
                } else {
                    doseMg = s;
                }

                try {
                    if (doseMg != null && doseMg.length() > 0) {
                        MedicationDose medicationDose = new MedicationDose();
                        medicationDose.setMg(Double.parseDouble(doseMg.trim()));

                        if (doseExtraInfo != null) {
                            medicationDose.setExtraInformation(doseExtraInfo.trim());
                        }

                        // set the id and increment for the next one
                        medicationDose.setId(medicationDoseCurrentId);
                        medicationDoseCurrentId++;

                        medicationDosages.add(medicationDose);
                    }
                } catch (Exception e) {
                    System.out.println("Could not create medication dose because of unknown error " + e);
                }
            }
        }

        return medicationDosages;
    }

    /**
     * Check if a file exists
     *
     * @param fname File location
     * @return true/false
     */
    public static boolean fileExists(String fname) {
        return new File(fname).exists();
    }

    /**
     * Delete a file from the system
     *
     * @param fname File location
     * @return success/failure
     */
    public static boolean deleteFile(String fname) {
        File f = new File(fname);
        return f.delete();
    }

    /**
     * will append a line to a file
     *
     * @param s New line
     */
    private void writeLine(String s) {
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(outputFileLocation, true));
            bw.write(s);
            bw.newLine();
            bw.flush();
        } catch (Exception e) {
            System.out.println("Could not add line to output file " + e);
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (Exception e) {
                    int j = 1;
                }
            }
        }
    }

    public static void main(String[] params) {
        MedicationImporter medicationImporter = new MedicationImporter();
        medicationImporter.run("C:\\Users\\David\\Documents\\ibd\\medicationImport.xls",
                "C:\\Users\\David\\Documents\\ibd\\medicationImport.sql");
    }
}
