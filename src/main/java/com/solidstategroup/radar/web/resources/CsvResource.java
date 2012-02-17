package com.solidstategroup.radar.web.resources;

import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.request.resource.AbstractResource;
import org.apache.wicket.request.resource.ByteArrayResource;
import org.apache.wicket.request.resource.ContentDisposition;
import org.apache.wicket.request.resource.IResource;


public class CsvResource extends ByteArrayResource {
    public CsvResource(byte[] array, String filename) {
        super("UTF-8", array, filename + ".csv");
    }

    @Override
    protected void configureResponse(ResourceResponse response, Attributes attributes) {
        response.setContentType("application/vnd.ms-excel");
        response.setContentDisposition(ContentDisposition.ATTACHMENT);

    }
}
