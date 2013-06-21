package org.patientview.radar.web.resources;

import org.patientview.radar.model.enums.ExportType;
import org.apache.wicket.request.resource.ByteArrayResource;
import org.apache.wicket.request.resource.ContentDisposition;
import org.apache.wicket.util.time.Duration;

import java.util.Map;

/**
 * Factory for Resources
 */

public class RadarResourceFactory {

    private static Map<ExportType, RadarExportResource> exportTypeToExportResourceMap;

    public static ByteArrayResource getExportResource(byte[] data, String fileName, ExportType exportType) {
        if (exportType.equals(ExportType.CSV)) {
           return new CsvExportResource(data, fileName);
        } else if (exportType.equals(ExportType.EXCEL)) {
           return new ExcelExportResource(data, fileName);
        } else if (exportType.equals(ExportType.PDF)) { // else if rather than else as easier to add new case
           return new PdfExportResource(data, fileName);
        }
        // this case should never happen
        return null;

    }

    private static class RadarExportResource extends ByteArrayResource {

        private String mimeType;

        private RadarExportResource(byte[] array, String filename, String extension, String contentType,
                                    String mimeType) {
            super(contentType, array, filename + extension);
            this.mimeType = mimeType;
        }

        @Override
        protected void configureResponse(ResourceResponse response, Attributes attributes) {
            response.setContentType(mimeType);
            response.setContentDisposition(ContentDisposition.ATTACHMENT);
            response.setCacheDuration(Duration.NONE);

        }
    }

    private static class CsvExportResource extends RadarExportResource {
        private CsvExportResource(byte[] array, String filename) {
            super(array, filename, ".csv", "UTF-8", "text/csv");
        }
    }

    private static class ExcelExportResource extends RadarExportResource {
        private ExcelExportResource(byte[] array, String filename) {
            super(array, filename, ".xls", "UTF-8", "application/vnd.ms-excel");
        }
    }

    private static class PdfExportResource extends RadarExportResource {
        private PdfExportResource(byte[] array, String filename) {
            super(array, filename, ".pdf", "UTF-8", "application/pdf");
        }
    }


}
