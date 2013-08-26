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
package org.patientview.patientview.controller.lookinglocal;

import org.patientview.patientview.controller.BaseController;
import org.patientview.patientview.controller.Routes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.servlet.http.HttpServletResponse;

/**
 *  Looking local home controller
 */
@Controller
public class LookingLocalHomeController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LookingLocalHomeController.class);
    /**
     * Deal with the URIs "/lookinglocal/home"
     * get the join requests list(paging and sorting)
     */
    @RequestMapping(value = Routes.LOOKING_LOCAL_HOME)
    @ResponseBody
     public void getHomeXml(HttpServletResponse response) {

        try {
            Document doc = getDocument();
            if (doc == null) {
                return;
            }

            // add page to screen
            Element pageElement = doc.createElement("page");
            pageElement.setAttribute("title", "PatientView (PV) – view your results");
            pageElement.setAttribute("transform", "default");
            getScreenElement().appendChild(pageElement);

            // add form to screen
            Element formElement = doc.createElement("form");
            formElement.setAttribute("action", "XXX.do");
            formElement.setAttribute("method", "post");
            formElement.setAttribute("name", "blank");
            pageElement.appendChild(formElement);

            // Hello World! static element
            Element details = doc.createElement("static");
            details.setAttribute("value", "Please key in your details:");
            formElement.appendChild(details);

            Element username = doc.createElement("textField");
            username.setAttribute("hint", "Enter your username");
            username.setAttribute("label", "Username:");
            username.setAttribute("name", "username");
            username.setAttribute("size", "10");
            username.setAttribute("value", "");
            formElement.appendChild(username);

            Element password = doc.createElement("numbersField");
            password.setAttribute("hint", "Enter your Password");
            password.setAttribute("label", "Password:");
            password.setAttribute("name", "password");
            password.setAttribute("size", "10");
            password.setAttribute("value", "");
            formElement.appendChild(password);

            // Hello World! static element
            Element forget = doc.createElement("static");
            forget.setAttribute("value", "If you have forgotten your password, "
                    + "please contact your unit administrator.");
            formElement.appendChild(forget);

            // sign-in button
            Element signIn = doc.createElement("submit");
            signIn.setAttribute("name", "left");
            signIn.setAttribute("title", "Sign in");
            formElement.appendChild(signIn);

            // form action
            Element formAction = doc.createElement("hiddenField");
            formAction.setAttribute("name", "formAction");
            formAction.setAttribute("value", "XXX.do");
            formElement.appendChild(formAction);

            // form method
            Element formMethod = doc.createElement("hiddenField");
            formMethod.setAttribute("name", "formMethod");
            formMethod.setAttribute("value", "post");
            formElement.appendChild(formMethod);

            outputXml(doc, response);
        } catch (Exception e) {
            LOGGER.error("Could not create response output stream{}" + e);
        }

    }

}
