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

package org.patientview.patientview.lookinglocal;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class LookinglocalHomeAction extends Action {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root element: screen
        Document doc = docBuilder.newDocument();
        Element screenElement = doc.createElement("screen");
        screenElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        screenElement.setAttribute("xsi:noNamespaceSchemaLocation", "http://www.digitv.gov.uk/schemas/plugin.xsd");
        doc.appendChild(screenElement);

        // add page to screen
        Element pageElement = doc.createElement("page");
        pageElement.setAttribute("title", "PatientView");
        pageElement.setAttribute("transform", "default");
        screenElement.appendChild(pageElement);

        // add page to screen
        Element formElement = doc.createElement("form");
        pageElement.appendChild(formElement);

        // Hello World! static element
        Element helloWorld = doc.createElement("static");
        helloWorld.setAttribute("value", "Hello World!");
        formElement.appendChild(helloWorld);

        // output string
        DOMSource domSource = new DOMSource(doc);
        StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.setOutputProperty("encoding", "ISO-8859-1");
        transformer.transform(domSource, result);

        String sb = writer.toString();

        response.setContentType("text/xml");
        response.setContentLength(sb.length());
        PrintWriter out;
        try {
            out = response.getWriter();
            out.println(sb.toString());
            out.close();
            out.flush();
        } catch (IOException e) {
            int i = 1; // _logger.error("Could not create response output stream");
        }

        return mapping.findForward("success");
    }
}
