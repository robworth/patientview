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
