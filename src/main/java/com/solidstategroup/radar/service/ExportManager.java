package com.solidstategroup.radar.service;

import java.io.InputStream;

public interface ExportManager {

    public byte[] getProfessionalUsersExportData(ExportType type);

    byte[] getConsultantsExportData(ExportType type);

    byte[] getDemographicsExportData(ExportType type);

    public enum ExportType {
        CSV,
        PDF
    }
}
