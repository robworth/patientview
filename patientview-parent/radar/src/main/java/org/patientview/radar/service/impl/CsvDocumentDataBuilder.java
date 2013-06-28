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
