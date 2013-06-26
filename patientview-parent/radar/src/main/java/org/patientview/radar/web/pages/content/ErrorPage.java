package org.patientview.radar.web.pages.content;

import org.patientview.radar.web.pages.BasePage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ErrorPage extends BasePage {
    private static final String TYPE_PARAM_KEY = "type";
    private static final String ERROR_404_MESSAGE = "The requested page could not be found";
    private static final String GENERAL_ERROR_MESSAGE = "An unexpected error has occurred";
    public static final String ERROR_TYPE_404 = "404";


    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorPage.class);

    public ErrorPage(Exception e) {
        LOGGER.error("An error occured: {}", e);
        init(GENERAL_ERROR_MESSAGE);
    }

    public ErrorPage(PageParameters parameters) {
        String type = parameters.get("type").toString();
        if (type != null && type.equals(ERROR_TYPE_404)) {
            LOGGER.error("A 404 error occured");
            init(ERROR_404_MESSAGE);
        } else {
            LOGGER.error("An error occured");
            init(GENERAL_ERROR_MESSAGE);
        }
    }

    private void init(String errorMessage) {
       add(new Label("message", errorMessage));
    }
}
