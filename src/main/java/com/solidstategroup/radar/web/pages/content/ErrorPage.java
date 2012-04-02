package com.solidstategroup.radar.web.pages.content;

import com.solidstategroup.radar.web.pages.BasePage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ErrorPage extends BasePage {
    private static final String typeParamKey = "type";
    private static final String error404Message = "The requested page could not be found";
    private static final String generalErrorMessage = "An unexpected error has occurred";
    public static final String ERROR_TYPE_404 = "404";


    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorPage.class);

    public ErrorPage(Exception e) {
        LOGGER.error("An error occured: {}", e);
        init(generalErrorMessage);
    }

    public ErrorPage(PageParameters parameters) {
        String type = parameters.get("type").toString();
        if (type.equals(ERROR_TYPE_404)) {
            LOGGER.error("A 404 error occured");
            init(error404Message);
        } else {
            LOGGER.error("An error occured");
            init(generalErrorMessage);
        }
    }

    private void init(String errorMessage) {
       add(new Label("message", errorMessage));
    }
}
