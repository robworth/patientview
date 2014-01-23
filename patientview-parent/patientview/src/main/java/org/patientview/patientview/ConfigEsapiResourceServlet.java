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

package org.patientview.patientview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.io.File;

/**
 * Set the ESAPI.properties absolute path in system properties,
 * so that ESAPI.jar can find the file without exception.
 */
public class ConfigEsapiResourceServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigEsapiResourceServlet.class);

    public void init() throws ServletException {
        super.init();
        try {
            File file = ResourceUtils.getFile("classpath:ESAPI.properties");
            if (file != null) {
                String filePath = file.getAbsolutePath();
                String resourceDir = filePath.substring(0, filePath.indexOf("ESAPI.properties"));
                System.setProperty("org.owasp.esapi.resources", resourceDir);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            LOGGER.debug(e.getMessage(), e);
        }


    }
}
