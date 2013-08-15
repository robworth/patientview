package org.patientview.radar.service.impl;

import org.patientview.radar.model.DocumentData;
import org.patientview.radar.service.DocumentDataBuilder;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


public class ExcelDocumentDataBuilder implements DocumentDataBuilder {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelDocumentDataBuilder.class);

    public byte[] build(DocumentData documentData) {

        Workbook workbook = new HSSFWorkbook();
        CreationHelper createHelper = workbook.getCreationHelper();
        Sheet sheet = workbook.createSheet("data");

        // add the headers/columns
        Row headerRow = sheet.createRow((short) 0);
        sheet.autoSizeColumn(0);

        CellStyle headerStyle = workbook.createCellStyle();

        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        headerStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());

        headerStyle.setLeftBorderColor(CellStyle.BORDER_THIN);
        headerStyle.setRightBorderColor(CellStyle.BORDER_THIN);
        headerStyle.setTopBorderColor(CellStyle.BORDER_THIN);
        headerStyle.setBottomBorderColor(CellStyle.BORDER_THIN);

        Font headerFont = workbook.createFont();
        headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        headerStyle.setFont(headerFont);

        List<String> headers = documentData.getHeaders();
        int headerColumnIndex = 0;
        for (String header : headers) {
            sheet.autoSizeColumn(headerColumnIndex);
            Cell cell = headerRow.createCell(headerColumnIndex);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(header);
            headerColumnIndex++;
        }

        // add the row data
        int columnIndex = 0;
        int rowIndex = 1;
        for (List<String> row : documentData.getRows()) {
            Row spreadSheetRow = sheet.createRow((short) rowIndex++);
            for (String data : row) {
                spreadSheetRow.createCell(columnIndex++).setCellValue(data);
            }
            columnIndex = 0;
        }

        // set the column width to fit the contents - this must be done after the data is added
        headerColumnIndex = 0;
        for (String header : headers) {
            sheet.autoSizeColumn(headerColumnIndex);
            headerColumnIndex++;
        }
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            workbook.write(outputStream);
            outputStream.close();
            outputStream.flush();
        } catch (IOException e) {
            LOGGER.error("Unable to write workbook to output stream " + e.getMessage(), e);
        }

        return outputStream.toByteArray();
    }
}
