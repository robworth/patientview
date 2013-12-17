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
