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

package org.patientview.radar.service.impl;

import org.patientview.radar.model.DocumentData;
import org.patientview.radar.service.DocumentDataBuilder;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.ListIterator;


public class CsvDocumentDataBuilder implements DocumentDataBuilder {
    public byte[] build(DocumentData documentData) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream);

        // add the headers/columns
        ListIterator<String> headersIterator = documentData.getHeaders().listIterator();
        while (headersIterator.hasNext()) {
            printWriter.write(headersIterator.next() + (headersIterator.hasNext() ? ", " : ""));
        }

        printWriter.write("\n");

        // add the row data
        for (List<String> row : documentData.getRows()) {
            ListIterator<String> rowIterator = row.listIterator();
            while (rowIterator.hasNext()) {
                String value = rowIterator.next();
                // change any commas
                value = value!= null ? value.replace(",", " - ") : "";
                printWriter.write(value + (rowIterator.hasNext() ? ", " : ""));
            }
            printWriter.write("\n");
        }

        printWriter.flush();
        printWriter.close();
        return outputStream.toByteArray();
    }
}
