package org.patientview.repository.radar.service;

import org.patientview.model.radar.enums.ExportType;

public interface ExportManager {

    public byte[] getProfessionalUsersExportData(ExportType type);

    byte[] getConsultantsExportData(ExportType type);

    byte[] getDemographicsExportData(ExportType type);

    byte[] getPatientsExportData(ExportType type);

}
