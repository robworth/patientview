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

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.patientview.radar.model.DocumentData;
import org.patientview.radar.service.DocumentDataBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class PdfDocumentDataBuilder implements DocumentDataBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(PdfDocumentDataBuilder.class);

    public static final Font.FontFamily BASE_FONT = Font.FontFamily.HELVETICA;
    public static final BaseColor BASE_FONT_COLOR = BaseColor.BLACK;
    public static final int BASE_FONT_SIZE = 7;

    public byte[] build(DocumentData documentData) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            Document document = new Document(PageSize.A4, 0, 0, 20, 20);

            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            writer.setFullCompression();
            writer.setLinearPageMode();

            document.open();

            PdfPTable table = new PdfPTable(documentData.getHeaders().size());
            table.setWidthPercentage(90);

            // create the header cells
            for (String s : documentData.getHeaders()) {
                table.addCell(createHeadingCell(s));
            }

            // create the data row cells
            for (List<String> l : documentData.getRows()) {
                for (String s : l) {
                    table.addCell(createRowCell(s));
                }
            }

            document.add(table);

            document.close();
        } catch (Exception e) {
            LOGGER.error("Could not build pdf " + e.getMessage());
        }
        return outputStream.toByteArray();
    }

    private PdfPCell createHeadingCell(String text) {
        PdfPCell cell = createBaseCell(text, new Font(BASE_FONT, BASE_FONT_SIZE, Font.BOLD));
        cell.setBackgroundColor(new BaseColor(0xDE, 0xDE, 0xDE));
        return cell;
    }

    private PdfPCell createRowCell(String text) {
        return createBaseCell(text, new Font(BASE_FONT, BASE_FONT_SIZE));
    }

    private PdfPCell createBaseCell(String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setPadding(4f);
        return cell;
    }

}
