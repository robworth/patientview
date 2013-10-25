package org.patientview.repository.radar.service.impl;

import org.patientview.model.Patient;
import org.patientview.model.radar.Consultant;
import org.patientview.model.radar.DocumentData;
import org.patientview.model.radar.enums.ExportType;
import org.patientview.model.radar.user.PatientUser;
import org.patientview.model.radar.user.ProfessionalUser;
import org.patientview.repository.radar.service.DemographicsManager;
import org.patientview.repository.radar.service.DiagnosisManager;
import org.patientview.repository.radar.service.DocumentDataBuilder;
import org.patientview.repository.radar.service.ExportManager;
import org.patientview.repository.radar.service.UserManager;
import org.patientview.repository.radar.service.UtilityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExportManagerImpl implements ExportManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExportManagerImpl.class);
    public static final String DATE_PATTERN = "dd-MMM-yyyy";

    private UserManager userManager;
    private UtilityManager utilityManager;
    private DemographicsManager demographicsManager;
    private DiagnosisManager diagnosisManager;

    private DocumentDataBuilder csvDocumentDataBuilder;
    private DocumentDataBuilder excelDocumentDataBuilder;
    private DocumentDataBuilder pdfDocumentDataBuilder;

    private Map<ExportType, DocumentDataBuilder> exportTypeToDocumentDataBuilderMap;

    public byte[] getProfessionalUsersExportData(ExportType exportType) {
        List<ProfessionalUser> professionalUsers = userManager.getProfessionalUsers();
        DocumentData documentData = new DocumentData();
        // add the headers/columns
        documentData.setHeaders(Arrays.asList("Surname", "Forename", "Title", "Role", "Email", "Centre", "Date Reg",
                "GMC"));

        // add the row data
        for (ProfessionalUser user : professionalUsers) {
            String username = "";
            documentData.addRow(Arrays.asList(user.getSurname(), user.getForename(), user.getTitle(), user.getRole(),
                    user.getEmail(), user.getCentre() != null ? user.getCentre().getName() :
                    "", new SimpleDateFormat(DATE_PATTERN).format(user.getDateRegistered()), user.getGmc()));
        }
        return getExportData(exportType, documentData);
    }

    public byte[] getConsultantsExportData(ExportType exportType) {
        List<Consultant> consultants = utilityManager.getConsultants();
        DocumentData documentData = new DocumentData();
        documentData.setHeaders(Arrays.asList("Surname", "First Name", "Centre"));

        for (Consultant consultant : consultants) {
            documentData.addRow(Arrays.asList(consultant.getSurname(), consultant.getForename(),
                    consultant.getCentre() != null ? consultant.getCentre().getName() : ""));
        }

        return getExportData(exportType, documentData);
    }

    public byte[] getDemographicsExportData(ExportType exportType) {
        List<Patient> patientList = demographicsManager.getDemographics();
        DocumentData documentData = new DocumentData();
        documentData.setHeaders(Arrays.asList("RADAR No", "Date Reg", "First Name", "Surname", "Address", "Diagnosis",
                "Consultant", "", "Centre"));

        for (Patient patient : patientList) {
            String diagnosisCodeAbbr = diagnosisManager.getDiagnosisName(patient);

            String dateRegistered = "";
            dateRegistered = patient.getDateReg() != null ? new SimpleDateFormat(DATE_PATTERN).
                    format(patient.getDateReg()) : "";

            documentData.addRow(Arrays.asList(patient.getId().toString(), dateRegistered,
                    patient.getForename(), patient.getSurname(),
                    patient.getAddress1() + ", " + patient.getAddress2() + ", " +
                            patient.getAddress3() + ". " + patient.getAddress4(), diagnosisCodeAbbr,
                    patient.getClinician() != null ? patient.getClinician().getForename() : "",
                    patient.getClinician() != null ? patient.getClinician().getSurname() : "",
                    patient.getRenalUnit() != null ? patient.getRenalUnit().getAbbreviation() : ""));
        }

        return getExportData(exportType, documentData);
    }

    public byte[] getPatientsExportData(ExportType exportType) {
        List<PatientUser> patients = userManager.getPatientUsers();
        DocumentData documentData = new DocumentData();
        documentData.setHeaders(Arrays.asList("RadarNO", "User Name", "DOB", "Date Reg"));

        for (PatientUser user : patients) {
            String dob = "";
            dob = user.getDateOfBirth() != null ? new SimpleDateFormat(DATE_PATTERN).
                    format(user.getDateOfBirth()) : "";

            String dateRegistered = "";
            dateRegistered = user.getDateRegistered() != null ? new SimpleDateFormat(DATE_PATTERN).
                    format(user.getDateRegistered()) : "";

            documentData.addRow(Arrays.asList(user.getRadarNumber().toString(), user.getUsername(), dob,
                    dateRegistered));
        }

        return getExportData(exportType, documentData);
    }

    private byte[] getExportData(ExportType exportType, DocumentData documentData) {
        // build the map - maps export type to a document builder
        if (exportTypeToDocumentDataBuilderMap == null) {
            exportTypeToDocumentDataBuilderMap = new HashMap<ExportType,
                    DocumentDataBuilder>();
            exportTypeToDocumentDataBuilderMap.put(ExportType.CSV, csvDocumentDataBuilder);
            exportTypeToDocumentDataBuilderMap.put(ExportType.EXCEL, excelDocumentDataBuilder);
            exportTypeToDocumentDataBuilderMap.put(ExportType.PDF, pdfDocumentDataBuilder);
        }

        DocumentDataBuilder documentDataBuilder = exportTypeToDocumentDataBuilderMap.get(exportType);
        // build and return the data
        return documentDataBuilder.build(documentData);
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

    public void setCsvDocumentDataBuilder(DocumentDataBuilder csvDocumentDataBuilder) {
        this.csvDocumentDataBuilder = csvDocumentDataBuilder;
    }

    public void setExcelDocumentDataBuilder(DocumentDataBuilder excelDocumentDataBuilder) {
        this.excelDocumentDataBuilder = excelDocumentDataBuilder;
    }

    public void setPdfDocumentDataBuilder(DocumentDataBuilder pdfDocumentDataBuilder) {
        this.pdfDocumentDataBuilder = pdfDocumentDataBuilder;
    }
}
