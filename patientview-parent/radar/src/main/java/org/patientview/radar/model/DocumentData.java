package org.patientview.radar.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Used in data export. Serves as a common format that can be understood by different DocumentDataBuilder.class
 * implementations
*/
public class DocumentData {
    private List<String> headers = new ArrayList<String>();
    private List<List<String>> rows = new ArrayList<List<String>>();

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void addRow(List<String> data) {
        rows.add(data);
    }

    public List<List<String>> getRows() {
        return rows;
    }
}
