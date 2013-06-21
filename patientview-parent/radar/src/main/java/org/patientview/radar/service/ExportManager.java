package org.patientview.radar.service;

import org.patientview.radar.model.enums.ExportType;

public interface ExportManager {

    public byte[] getProfessionalUsersExportData(ExportType type);

    byte[] getConsultantsExportData(ExportType type);

    byte[] getDemographicsExportData(ExportType type);

    byte[] getPatientsExportData(ExportType type);

}
