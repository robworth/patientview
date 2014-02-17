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

import org.patientview.model.Unit;
import org.patientview.patientview.PatientDetails;
import org.patientview.patientview.comment.CommentUtils;
import org.patientview.patientview.controller.Routes;
import org.patientview.patientview.medicine.MedicineWithShortName;
import org.patientview.patientview.model.Letter;
import org.patientview.patientview.model.Medicine;
import org.patientview.patientview.model.Panel;
import org.patientview.patientview.model.TestResultWithUnitShortname;
import org.patientview.patientview.model.User;
import org.patientview.patientview.unit.UnitUtils;
import org.patientview.patientview.user.UserUtils;
import org.patientview.utils.LegacySpringUtils;
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
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Generate the xml for Looking local
 */
public final class LookingLocalUtils {

    public static final int OPTION_1 = 1;
    public static final int OPTION_2 = 2;
    public static final int OPTION_3 = 3;
    public static final int OPTION_4 = 4;
    public static final int OPTION_5 = 5;
    public static final int OPTION_6 = 6;
    public static final int OPTION_7 = 7;
    private LookingLocalUtils() {
    }

    public static Document getDocument() throws  Exception {

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        // root element: screen
        Document doc = docBuilder.newDocument();
        Element screenElement = doc.createElement("screen");
        screenElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
        screenElement.setAttribute("xsi:noNamespaceSchemaLocation", "http://www.digitv.gov.uk/schemas/plugin.xsd");
        doc.appendChild(screenElement);

        return doc;
    }

    public static void outputXml(Document doc, HttpServletResponse response) throws  Exception {

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
        out = response.getWriter();
        out.println(sb.toString());
        out.close();
        out.flush();
    }

    public static void getMyDetailsXml(HttpServletRequest request, HttpServletResponse response) throws Exception {

        User user = UserUtils.retrieveUser(request);

        List<PatientDetails> patientDetails = LegacySpringUtils.getPatientManager().getPatientDetails(
                user.getUsername());

        Document doc = getDocument();

        // add page to screen
        Element pageElement = doc.createElement("page");
        pageElement.setAttribute("title", "My Details");
        pageElement.setAttribute("transform", "default");
        doc.getElementsByTagName("screen").item(0).appendChild(pageElement);

        // add form to screen
        Element formElement = doc.createElement("form");
        formElement.setAttribute("action", Routes.LOOKING_LOCAL_DETAILS);
        formElement.setAttribute("method", "post");
        formElement.setAttribute("pagingText", "Page 1 of 2");
        pageElement.appendChild(formElement);

        if (patientDetails != null && !patientDetails.isEmpty()) {
            // static element
            Element name = doc.createElement("static");
            name.setAttribute("value", "Name: " + patientDetails.get(0).getPatient().getForename() + " "
                    + patientDetails.get(0).getPatient().getSurname());
            formElement.appendChild(name);

            Element dob = doc.createElement("static");
            dob.setAttribute("value", "Date of Birth: "
                    + patientDetails.get(0).getPatient().getFormatedDateOfBirth());
            formElement.appendChild(dob);

            Element nhsNo = doc.createElement("static");
            nhsNo.setAttribute("value", "NHS Number: " + patientDetails.get(0).getPatient().getNhsno());
            formElement.appendChild(nhsNo);

            Element hospitalNo = doc.createElement("static");
            hospitalNo.setAttribute("value", "Hospital Number: "
                    + patientDetails.get(0).getPatient().getHospitalnumber());
            formElement.appendChild(hospitalNo);

            Element address = doc.createElement("static");
            address.setAttribute("value", "Address: "
                    + patientDetails.get(0).getPatient().getAddress1() + ","
                    + patientDetails.get(0).getPatient().getAddress2() + ","
                    + patientDetails.get(0).getPatient().getAddress3() + ","
                    + patientDetails.get(0).getPatient().getAddress4());
            formElement.appendChild(address);

            Element telephone1 = doc.createElement("static");
            telephone1.setAttribute("value", "Telephone 1: " + patientDetails.get(0).getPatient().getTelephone1());
            formElement.appendChild(telephone1);

            Element telephone2 = doc.createElement("static");
            telephone2.setAttribute("value", "Telephone 2: " + patientDetails.get(0).getPatient().getTelephone2());
            formElement.appendChild(telephone2);

            Element mobile = doc.createElement("static");
            mobile.setAttribute("value", "Mobile: " + patientDetails.get(0).getPatient().getMobile());
            formElement.appendChild(mobile);

            Element diagnosis = doc.createElement("static");
            diagnosis.setAttribute("value", "Diagnosis: " + patientDetails.get(0).getPatient().getDiagnosis());
            formElement.appendChild(diagnosis);

            Element gpName = doc.createElement("static");
            gpName.setAttribute("value", "GP Name: " + patientDetails.get(0).getPatient().getGpname());
            formElement.appendChild(gpName);

            Element gpTelephone = doc.createElement("static");
            gpTelephone.setAttribute("value", "GP Telephone: " + patientDetails.get(0).getPatient().getGptelephone());
            formElement.appendChild(gpTelephone);

            Element gpAddress = doc.createElement("static");
            gpAddress.setAttribute("value", "Address: "
                    + patientDetails.get(0).getPatient().getGpaddress1() + ","
                    + patientDetails.get(0).getPatient().getGpaddress2() + ","
                    + patientDetails.get(0).getPatient().getGpaddress3());
            formElement.appendChild(gpAddress);

            Element treatment = doc.createElement("static");
            treatment.setAttribute("value", "Treatment: " + patientDetails.get(0).getPatient().getTreatment());
            formElement.appendChild(treatment);

            Element transplantStatus = doc.createElement("static");
            transplantStatus.setAttribute("value", "Transplant status: "
                    + patientDetails.get(0).getPatient().getTransplantstatus());
            formElement.appendChild(transplantStatus);

            Element otherConditions = doc.createElement("static");
            otherConditions.setAttribute("value", "Other conditions: "
                    + patientDetails.get(0).getPatient().getOtherConditions());
            formElement.appendChild(otherConditions);
        }

        // back button
        Element back = doc.createElement("submit");
        back.setAttribute("name", "left");
        back.setAttribute("title", "Back");
        formElement.appendChild(back);

        // more button
        Element more = doc.createElement("submit");
        more.setAttribute("name", "right");
        more.setAttribute("title", "More");
        formElement.appendChild(more);

        // form action
        Element formAction = doc.createElement("hiddenField");
        formAction.setAttribute("name", "formAction");
        formAction.setAttribute("value", Routes.LOOKING_LOCAL_DETAILS);
        formElement.appendChild(formAction);

        // form method
        Element formMethod = doc.createElement("hiddenField");
        formMethod.setAttribute("name", "formMethod");
        formMethod.setAttribute("value", "post");
        formElement.appendChild(formMethod);

        outputXml(doc, response);
    }

    public static void getDrugsXml(HttpServletRequest request, HttpServletResponse response) throws Exception {

        User user = UserUtils.retrieveUser(request);
        List<MedicineWithShortName> medicineWithShortNames = getMedicinesForPatient(user);

        Document doc = getDocument();
        // add page to screen
        Element pageElement = doc.createElement("page");
        pageElement.setAttribute("title", "Drugs");
        pageElement.setAttribute("transform", "default");
        doc.getElementsByTagName("screen").item(0).appendChild(pageElement);

        // add form to screen
        Element formElement = doc.createElement("form");
        formElement.setAttribute("action", Routes.LOOKING_LOCAL_DETAILS);
        formElement.setAttribute("method", "post");
        formElement.setAttribute("pagingText", "Page 1 of 4");
        pageElement.appendChild(formElement);

        if (medicineWithShortNames != null && !medicineWithShortNames.isEmpty()) {

            // static element
            Element name = doc.createElement("static");
            name.setAttribute("value", "Start Date   |   Medicine name   | Dose   | Source");
            formElement.appendChild(name);

            StringBuffer sb;
            for (MedicineWithShortName medicine : medicineWithShortNames) {
                Element medicineEl = doc.createElement("static");
                sb = new StringBuffer();
                sb.append(medicine.getFormattedStartDate()).append(" ");
                sb.append(medicine.getName()).append(" ");
                sb.append(medicine.getDose()).append(" ");
                sb.append(medicine.getShortname());
                medicineEl.setAttribute("value", sb.toString());
                formElement.appendChild(medicineEl);
            }

        }

        // back button
        Element back = doc.createElement("submit");
        back.setAttribute("name", "left");
        back.setAttribute("title", "Back");
        formElement.appendChild(back);

        // more button
        Element more = doc.createElement("submit");
        more.setAttribute("name", "right");
        more.setAttribute("title", "More");
        formElement.appendChild(more);

        // form action
        Element formAction = doc.createElement("hiddenField");
        formAction.setAttribute("name", "formAction");
        formAction.setAttribute("value", Routes.LOOKING_LOCAL_DETAILS);
        formElement.appendChild(formAction);

        // form method
        Element formMethod = doc.createElement("hiddenField");
        formMethod.setAttribute("name", "formMethod");
        formMethod.setAttribute("value", "post");
        formElement.appendChild(formMethod);

        outputXml(doc, response);
    }

    private static List<MedicineWithShortName> getMedicinesForPatient(User user)
            throws Exception {
        List<MedicineWithShortName> medicinesWithShortName = new ArrayList<MedicineWithShortName>();
        if (user != null) {
            List<Medicine> medicines = LegacySpringUtils.getMedicineManager().getUserMedicines(user);
            if (medicines != null) {
                for (Medicine med : medicines) {
                    Unit unit = UnitUtils.retrieveUnit(med.getUnitcode());
                    if (unit != null) {
                        medicinesWithShortName.add(new MedicineWithShortName(med, unit.getShortname()));
                    } else {
                        medicinesWithShortName.add(new MedicineWithShortName(med, "UNKNOWN UNIT:" + med.getUnitcode()));
                    }
                }
            }
        }

        return medicinesWithShortName;
    }

    public static void getMedicalResultsXml(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Document doc = getDocument();

        // add page to screen
        Element pageElement = doc.createElement("page");
        pageElement.setAttribute("title", "Result type");
        pageElement.setAttribute("transform", "default");
        doc.getElementsByTagName("screen").item(0).appendChild(pageElement);

        // add form to screen
        Element formElement = doc.createElement("form");
        formElement.setAttribute("action", Routes.LOOKING_LOCAL_RESULTS_DISPLAY);
        formElement.setAttribute("method", "post");
        formElement.setAttribute("pagingText", "Page 1 of 3");
        pageElement.appendChild(formElement);

        // static element
        Element details = doc.createElement("static");
        details.setAttribute("value", "Select the results type to view:");
        formElement.appendChild(details);

        //  multisubmitField
        Element multisubmit = doc.createElement("multisubmitField");
        multisubmit.setAttribute("name", "selection");
        formElement.appendChild(multisubmit);

        // Urea field Option
        Element fieldOption1 = doc.createElement("fieldOption");
        fieldOption1.setAttribute("name", "Urea");
        fieldOption1.setAttribute("value", "1");
        multisubmit.appendChild(fieldOption1);

        // Creatininefield Option
        Element fieldOption2 = doc.createElement("fieldOption");
        fieldOption2.setAttribute("name", "Creatinine");
        fieldOption2.setAttribute("value", "2");
        multisubmit.appendChild(fieldOption2);

        // Potassium field Option
        Element fieldOption3 = doc.createElement("fieldOption");
        fieldOption3.setAttribute("name", "Potassium");
        fieldOption3.setAttribute("value", "3");
        multisubmit.appendChild(fieldOption3);

        // Calcium field Option
        Element fieldOption4 = doc.createElement("fieldOption");
        fieldOption4.setAttribute("name", "Calcium");
        fieldOption4.setAttribute("value", "4");
        multisubmit.appendChild(fieldOption4);


        // PO4 field Option
        Element fieldOption5 = doc.createElement("fieldOption");
        fieldOption5.setAttribute("name", "PO4");
        fieldOption5.setAttribute("value", "5");
        multisubmit.appendChild(fieldOption5);

        // Hb field Option
        Element fieldOption6 = doc.createElement("fieldOption");
        fieldOption6.setAttribute("name", "Hb");
        fieldOption6.setAttribute("value", "6");
        multisubmit.appendChild(fieldOption6);

        // Hb field Option
        Element fieldOption7 = doc.createElement("fieldOption");
        fieldOption7.setAttribute("name", "Wbc");
        fieldOption7.setAttribute("value", "7");
        multisubmit.appendChild(fieldOption7);

        // back button
        Element back = doc.createElement("submit");
        back.setAttribute("name", "left");
        back.setAttribute("title", "Back");
        formElement.appendChild(back);

        // more button
        Element more = doc.createElement("submit");
        more.setAttribute("name", "left");
        more.setAttribute("right", "More");
        formElement.appendChild(more);

        // form action
        Element formAction = doc.createElement("hiddenField");
        formAction.setAttribute("name", "formAction");
        formAction.setAttribute("value", Routes.LOOKING_LOCAL_RESULTS_DISPLAY);
        formElement.appendChild(formAction);

        // form method
        Element formMethod = doc.createElement("hiddenField");
        formMethod.setAttribute("name", "formMethod");
        formMethod.setAttribute("value", "post");
        formElement.appendChild(formMethod);

        outputXml(doc, response);
    }

    public static void getLetterDetailsXml(HttpServletRequest request, HttpServletResponse response,
                                           String selection) throws Exception {

        Letter letter = LegacySpringUtils.getLetterManager().get(Long.parseLong(selection));
        boolean permissionToReadLetter = false;
        if (letter != null && letter.getNhsno() != null) {
            permissionToReadLetter = CommentUtils.verifyPermissionToReadItem(request, letter.getNhsno());
        }

        Document doc = getDocument();
        // add page to screen
        Element pageElement = doc.createElement("page");
        pageElement.setAttribute("transform", "default");
        doc.getElementsByTagName("screen").item(0).appendChild(pageElement);

        // add form to screen
        Element formElement = doc.createElement("form");
        formElement.setAttribute("action", Routes.LOOKING_LOCAL_LETTER_DISPLAY);
        formElement.setAttribute("method", "post");
        formElement.setAttribute("pagingText", "Page 1 of 9");
        pageElement.appendChild(formElement);

        if (permissionToReadLetter && letter != null) {
            pageElement.setAttribute("title", letter.getFormattedDate() + " " + letter.getType());
            // static element
            Element content = doc.createElement("static");
            content.setAttribute("value", letter.getFormattedContent());
            formElement.appendChild(content);
        }

        // back button
        Element back = doc.createElement("submit");
        back.setAttribute("name", "left");
        back.setAttribute("title", "Back");
        formElement.appendChild(back);

        // more button
        Element more = doc.createElement("submit");
        more.setAttribute("name", "left");
        more.setAttribute("right", "More");
        formElement.appendChild(more);

        // form action
        Element formAction = doc.createElement("hiddenField");
        formAction.setAttribute("name", "formAction");
        formAction.setAttribute("value", Routes.LOOKING_LOCAL_LETTER_DISPLAY);
        formElement.appendChild(formAction);

        // form method
        Element formMethod = doc.createElement("hiddenField");
        formMethod.setAttribute("name", "formMethod");
        formMethod.setAttribute("value", "post");
        formElement.appendChild(formMethod);

        outputXml(doc, response);
    }

    public static void getLettersXml(HttpServletRequest request, HttpServletResponse response) throws Exception {

        User user = UserUtils.retrieveUser(request);
        List<Letter> letters = LegacySpringUtils.getLetterManager().get(user.getUsername());

        Document doc = getDocument();
        // add page to screen
        Element pageElement = doc.createElement("page");
        pageElement.setAttribute("title", "Select letter");
        pageElement.setAttribute("transform", "default");
        doc.getElementsByTagName("screen").item(0).appendChild(pageElement);

        // add form to screen
        Element formElement = doc.createElement("form");
        formElement.setAttribute("action", Routes.LOOKING_LOCAL_RESULTS_DISPLAY);
        formElement.setAttribute("method", "post");
        formElement.setAttribute("pagingText", "Page 1 of 10");
        pageElement.appendChild(formElement);

        if (letters != null && !letters.isEmpty()) {
            // static element
            Element details = doc.createElement("static");
            details.setAttribute("value", "Select the letter to view:");
            formElement.appendChild(details);

            Element heading = doc.createElement("static");
            heading.setAttribute("value", "Date        | Letter type");
            formElement.appendChild(heading);

            //  multisubmitField
            Element multisubmit = doc.createElement("multisubmitField");
            multisubmit.setAttribute("name", "selection");
            formElement.appendChild(multisubmit);

            StringBuffer sb;
            for (Letter letter : letters) {
                Element fieldOption = doc.createElement("fieldOption");
                sb = new StringBuffer();
                sb.append(letter.getFormattedDate()).append(" ");
                sb.append(letter.getType());
                fieldOption.setAttribute("name", sb.toString());
                fieldOption.setAttribute("value", letter.getId().toString());
                multisubmit.appendChild(fieldOption);
            }
        }

        // back button
        Element back = doc.createElement("submit");
        back.setAttribute("name", "left");
        back.setAttribute("title", "Back");
        formElement.appendChild(back);

        // more button
        Element more = doc.createElement("submit");
        more.setAttribute("name", "left");
        more.setAttribute("right", "More");
        formElement.appendChild(more);

        // form action
        Element formAction = doc.createElement("hiddenField");
        formAction.setAttribute("name", "formAction");
        formAction.setAttribute("value", Routes.LOOKING_LOCAL_RESULTS_DISPLAY);
        formElement.appendChild(formAction);

        // form method
        Element formMethod = doc.createElement("hiddenField");
        formMethod.setAttribute("name", "formMethod");
        formMethod.setAttribute("value", "post");
        formElement.appendChild(formMethod);

        outputXml(doc, response);
    }

    public static void getResultsDetailsXml(HttpServletRequest request,
                                            HttpServletResponse response, String selection) throws Exception {

        User user = UserUtils.retrieveUser(request);
        List<TestResultWithUnitShortname> results
                = LegacySpringUtils.getTestResultManager().getTestResultForPatient(user, new Panel(1));

        Document doc = getDocument();
        // add page to screen
        Element pageElement = doc.createElement("page");
        pageElement.setAttribute("transform", "default");
        doc.getElementsByTagName("screen").item(0).appendChild(pageElement);

        // add form to screen
        Element formElement = doc.createElement("form");
        formElement.setAttribute("action", Routes.LOOKING_LOCAL_DETAILS);
        formElement.setAttribute("method", "post");
        formElement.setAttribute("pagingText", "Page 1 of 6");
        pageElement.appendChild(formElement);

        List<TestResultWithUnitShortname> filterTestResults;
        switch (Integer.parseInt(selection)) {
            case OPTION_1 : filterTestResults = filterTestResults(results, "urea");
                pageElement.setAttribute("title", "Urea type");
                break;
            case OPTION_2 : filterTestResults = filterTestResults(results, "creatinine");
                pageElement.setAttribute("title", "Creatinine type");
                break;
            case OPTION_3 : filterTestResults = filterTestResults(results, "potassium");
                pageElement.setAttribute("title", "Potassium type");
                break;
            case OPTION_4 : filterTestResults = filterTestResults(results, "calcium");
                pageElement.setAttribute("title", "Calcium type");
                break;
            case OPTION_5 : filterTestResults = filterTestResults(results, "phosphate");
                pageElement.setAttribute("title", "PO4 type");
                break;
            case OPTION_6 : filterTestResults = filterTestResults(results, "hb");
                pageElement.setAttribute("title", "Hb type");
                break;
            case OPTION_7 : filterTestResults = filterTestResults(results, "wbc");
                pageElement.setAttribute("title", "Wbc type");
                break;
            default:filterTestResults = null;
                break;
        }

        if (filterTestResults != null && !filterTestResults.isEmpty()) {

            Element heading = doc.createElement("static");
            heading.setAttribute("value", "Date time    |    Label    | Value");
            formElement.appendChild(heading);

            StringBuffer sb;
            for (TestResultWithUnitShortname result : filterTestResults) {
                // static element
                Element data = doc.createElement("static");
                sb = new StringBuffer();
                sb.append(result.getFormattedDatestamp()).append(" ");
                if (result.getPrepost() != null) {
                    sb.append(result.getPrepost()).append(" ");
                } else {
                    sb.append("      ");
                }
                sb.append(result.getValue());

                data.setAttribute("value", sb.toString());
                formElement.appendChild(data);
            }

        }
        // back button
        Element back = doc.createElement("submit");
        back.setAttribute("name", "left");
        back.setAttribute("title", "Back");
        formElement.appendChild(back);

        // more button
        Element more = doc.createElement("submit");
        more.setAttribute("name", "right");
        more.setAttribute("title", "More");
        formElement.appendChild(more);

        // form action
        Element formAction = doc.createElement("hiddenField");
        formAction.setAttribute("name", "formAction");
        formAction.setAttribute("value", Routes.LOOKING_LOCAL_DETAILS);
        formElement.appendChild(formAction);

        // form method
        Element formMethod = doc.createElement("hiddenField");
        formMethod.setAttribute("name", "formMethod");
        formMethod.setAttribute("value", "post");
        formElement.appendChild(formMethod);

        outputXml(doc, response);
    }

    private static List<TestResultWithUnitShortname> filterTestResults(List<TestResultWithUnitShortname> results,
                                                                       String testCode) {

        List<TestResultWithUnitShortname> filterResults = new ArrayList<TestResultWithUnitShortname>();
        for (TestResultWithUnitShortname result : results) {
            if (testCode.equals(result.getTestcode())) {
                filterResults.add(result);
            }
        }
        return filterResults;
    }

    public static void getHomeXml(HttpServletResponse response) throws Exception {

        Document doc = getDocument();
        // add page to screen
        Element pageElement = doc.createElement("page");
        pageElement.setAttribute("title", "PatientView (PV) – view your results");
        pageElement.setAttribute("transform", "default");
        doc.getElementsByTagName("screen").item(0).appendChild(pageElement);

        // add form to screen
        Element formElement = doc.createElement("form");
        formElement.setAttribute("action", Routes.LOOKING_LOCAL_MAIN); // todo how to login?
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

        // static element
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
        formAction.setAttribute("value", Routes.LOOKING_LOCAL_MAIN);  // todo how to login?
        formElement.appendChild(formAction);

        // form method
        Element formMethod = doc.createElement("hiddenField");
        formMethod.setAttribute("name", "formMethod");
        formMethod.setAttribute("value", "post");
        formElement.appendChild(formMethod);

        outputXml(doc, response);

    }

    public static void getMainXml(HttpServletResponse response) throws Exception {

        Document doc = getDocument();
        // add page to screen
        Element pageElement = doc.createElement("page");
        pageElement.setAttribute("title", "Select what to view");
        pageElement.setAttribute("transform", "default");
        doc.getElementsByTagName("screen").item(0).appendChild(pageElement);

        // add form to screen
        Element formElement = doc.createElement("form");
        formElement.setAttribute("action", Routes.LOOKING_LOCAL_DETAILS);
        formElement.setAttribute("method", "post");
        pageElement.appendChild(formElement);

        // static element
        Element details = doc.createElement("static");
        details.setAttribute("value", "Select what you would like to look at:");
        formElement.appendChild(details);

        //  multisubmitField
        Element multisubmit = doc.createElement("multisubmitField");
        multisubmit.setAttribute("name", "selection");
        formElement.appendChild(multisubmit);

        // my details field Option
        Element fieldOption1 = doc.createElement("fieldOption");
        fieldOption1.setAttribute("name", "My Details");
        fieldOption1.setAttribute("value", "1");
        multisubmit.appendChild(fieldOption1);

        // medical result field Option
        Element fieldOption2 = doc.createElement("fieldOption");
        fieldOption2.setAttribute("name", "Medical Results");
        fieldOption2.setAttribute("value", "2");
        multisubmit.appendChild(fieldOption2);

        // drugs field Option
        Element fieldOption3 = doc.createElement("fieldOption");
        fieldOption3.setAttribute("name", "Drugs");
        fieldOption3.setAttribute("value", "3");
        multisubmit.appendChild(fieldOption3);

        // letters field Option
        Element fieldOption4 = doc.createElement("fieldOption");
        fieldOption4.setAttribute("name", "Letters");
        fieldOption4.setAttribute("value", "4");
        multisubmit.appendChild(fieldOption4);

        // back button
        Element back = doc.createElement("submit");
        back.setAttribute("name", "left");
        back.setAttribute("title", "Back");
        formElement.appendChild(back);

        // form action
        Element formAction = doc.createElement("hiddenField");
        formAction.setAttribute("name", "formAction");
        formAction.setAttribute("value", Routes.LOOKING_LOCAL_DETAILS);
        formElement.appendChild(formAction);

        // form method
        Element formMethod = doc.createElement("hiddenField");
        formMethod.setAttribute("name", "formMethod");
        formMethod.setAttribute("value", "post");
        formElement.appendChild(formMethod);

        outputXml(doc, response);
    }

}
