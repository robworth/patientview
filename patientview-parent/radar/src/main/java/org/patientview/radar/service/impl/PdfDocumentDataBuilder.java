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
