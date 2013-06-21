package org.patientview.radar.service;


import org.patientview.radar.model.DocumentData;

/**
 * Builds data for specific types of documents e.g. Excel
 */
public interface DocumentDataBuilder {

    /**
     * builds the data for the document
     * @param documentData The data to build to the target document type
     * @return data in the format of target document type
     */
    byte[] build(DocumentData documentData);

}
