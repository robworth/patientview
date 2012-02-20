package com.solidstategroup.radar.service.impl;

import com.solidstategroup.radar.model.DocumentData;
import com.solidstategroup.radar.service.DocumentDataBuilder;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.ListIterator;


public class PdfDocumentDataBuilder implements DocumentDataBuilder {

    public byte[] build(DocumentData documentData) {
        // todo see CsvDocumentDataBuilder.class as example
        return null;
    }
}
