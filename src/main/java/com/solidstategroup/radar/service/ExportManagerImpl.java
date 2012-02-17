package com.solidstategroup.radar.service;

import com.solidstategroup.radar.model.Consultant;
import com.solidstategroup.radar.model.Demographics;
import com.solidstategroup.radar.model.Diagnosis;
import com.solidstategroup.radar.model.DiagnosisCode;
import com.solidstategroup.radar.model.user.ProfessionalUser;
import com.solidstategroup.radar.util.TripleDes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;


public class ExportManagerImpl implements ExportManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExportManagerImpl.class);
    public static final String DATE_PATTERN = "dd-MMM-yyyy";

    private UserManager userManager;
    private UtilityManager utilityManager;
    private DemographicsManager demographicsManager;
    private DiagnosisManager diagnosisManager;


    public byte[] getProfessionalUsersExportData(ExportType exportType) {
        if (exportType.equals(ExportType.CSV)) {
            List<ProfessionalUser> professionalUsers = userManager.getProfessionalUsers();
            ExportData exportData = new ExportData();
            exportData.setHeaders(Arrays.asList("Surname", "Forename", "Title", "Role", "Email", "Centre", "Date Reg",
                    "GMC"));

            for (ProfessionalUser user : professionalUsers) {
                String username = "";
                exportData.addRow(Arrays.asList(user.getSurname(), user.getForename(), user.getTitle(), user.getRole(),
                        user.getEmail(), user.getCentre() != null ? user.getCentre().getName() :
                        "", new SimpleDateFormat(DATE_PATTERN).format(user.getDateRegistered()), user.getGmc()));
            }
            return getCsvData(exportData);
        }
        // else do the other cases
        return null;
    }

    public byte[] getConsultantsExportData(ExportType exportType) {
        if (exportType.equals(ExportType.CSV)) {
            List<Consultant> consultants = utilityManager.getConsultants();
            ExportData exportData = new ExportData();
            exportData.setHeaders(Arrays.asList("Surname", "First Name", "Centre"));

            for (Consultant consultant : consultants) {
                exportData.addRow(Arrays.asList(consultant.getSurname(), consultant.getForename(),
                        consultant.getCentre() != null ? consultant.getCentre().getName() : ""));
            }
            return getCsvData(exportData);
        }
        // else do the other cases
        return null;
    }

    public byte[] getDemographicsExportData(ExportType exportType) {
        if (exportType.equals(ExportType.CSV)) {
            List<Demographics> demographicsList = demographicsManager.getDemographics();
            ExportData exportData = new ExportData();
            exportData.setHeaders(Arrays.asList("RADAR No", "Date Reg", "First Name", "Surname", "Address", "Diagnosis",
                    "Consultant", "", "Centre"));

            for (Demographics demographics : demographicsList) {
                String diagnosisCodeAbbr = "";
                Diagnosis diagnosis = diagnosisManager.getDiagnosisByRadarNumber(demographics.getId());
                if (diagnosis != null) {
                    diagnosisCodeAbbr = diagnosis.getDiagnosisCode() != null ? diagnosis.getDiagnosisCode().
                            getAbbreviation() : "";
                }
                String dateRegistered = "";
                dateRegistered = demographics.getDateRegistered() != null ?  new SimpleDateFormat(DATE_PATTERN).
                            format(demographics.getDateRegistered()) : "";

                    exportData.addRow(Arrays.asList(demographics.getId().toString(), dateRegistered,
                            demographics.getForename(), demographics.getSurname(),
                            demographics.getAddress1() + ", " + demographics.getAddress2() + ", " +
                            demographics.getAddress3() + ". " + demographics.getAddress4(), diagnosisCodeAbbr,
                            demographics.getConsultant() != null ? demographics.getConsultant().getForename() : "",
                            demographics.getConsultant() != null ? demographics.getConsultant().getSurname() : "",
                            demographics.getRenalUnit() != null ? demographics.getRenalUnit().getAbbreviation() : ""));
            }
            return getCsvData(exportData);
        }
        // else do the other cases
        return null;
    }

    private byte[] getCsvData(ExportData exportData) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream);

        ListIterator<String> headersIterator = exportData.headers.listIterator();
        while (headersIterator.hasNext()) {
            printWriter.write(headersIterator.next() + (headersIterator.hasNext() ? ", " : ""));
        }

        printWriter.write("\n");

        for (List<String> row : exportData.getRows()) {
            ListIterator<String> rowIterator = row.listIterator();
            while (rowIterator.hasNext()) {
                String value = rowIterator.next();
                value = value != null ? value.replace(",", " - ") : "";
                printWriter.write(value + (rowIterator.hasNext() ? ", " : ""));
            }
            printWriter.write("\n");
        }

        printWriter.flush();
        printWriter.close();
        return outputStream.toByteArray();
    }

    private class ExportData {
        private List<String> headers = new ArrayList<String>();
        private List<List<String>> rows = new ArrayList<List<String>>();

        public void setHeaders(List<String> headers) {
            this.headers = headers;
        }

        public List<String> getHeaders() {
            return headers;
        }

        public void addRow(List<String> data) {
            rows.add(data);
        }

        public List<List<String>> getRows() {
            return rows;
        }
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public void setUtilityManager(UtilityManager utilityManager) {
        this.utilityManager = utilityManager;
    }

    public void setDemographicsManager(DemographicsManager demographicsManager) {
        this.demographicsManager = demographicsManager;
    }

    public void setDiagnosisManager(DiagnosisManager diagnosisManager) {
        this.diagnosisManager = diagnosisManager;
    }
}
