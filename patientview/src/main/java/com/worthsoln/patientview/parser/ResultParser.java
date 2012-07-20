package com.worthsoln.patientview.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.worthsoln.patientview.Centre;
import com.worthsoln.patientview.model.Patient;
import com.worthsoln.patientview.TestResult;
import com.worthsoln.patientview.TestResultDateRange;
import com.worthsoln.patientview.model.Diagnosis;
import com.worthsoln.patientview.model.Letter;
import com.worthsoln.patientview.model.Medicine;

public class ResultParser {

    private ArrayList testResults = new ArrayList();
    private ArrayList dateRanges = new ArrayList();
    private ArrayList letters = new ArrayList();
    private ArrayList otherDiagnoses = new ArrayList();
    private ArrayList medicines = new ArrayList();
    private Map xmlData = new HashMap();
    private String[] topLevelElements = new String[]{"flag", "centrecode", "centrename", "centreaddress1",
            "centreaddress2", "centreaddress3", "centreaddress4", "centrepostcode", "centretelephone", "centreemail",
            "gpname", "gpaddress1", "gpaddress2", "gpaddress3", "gppostcode", "gptelephone", "diagnosisedta",
            "rrtstatus", "tpstatus", "surname", "forename", "dateofbirth", "sex", "nhsno", "hospitalnumber", "address1",
            "address2", "address3", "postcode", "telephone1", "telephone2", "mobile",};

    public void parseResults(ServletContext context, File resultsFile) throws Exception {
        Document doc = getDocument(context, resultsFile);
        for (int i = 0; i < topLevelElements.length; i++) {
            collectTopLevelData(topLevelElements[i], doc);
        }
        collectTestResults(doc);
        collectDateRanges(doc);
        collectLetters(doc);
        collectOtherDiagnosis(doc);
        collectMedicines(doc);
    }

    private void collectDateRanges(Document doc) {
        NodeList testNodeList = doc.getElementsByTagName("test");
        for (int i = 0; i < testNodeList.getLength(); i++) {
            Node testNode = testNodeList.item(i);
            NodeList testResultNodes = testNode.getChildNodes();
            String testCode = "";
            String startDate = "";
            String stopDate = "";
            for (int j = 0; j < testResultNodes.getLength(); j++) {
                Node testResultNode = testResultNodes.item(j);
                if ((testResultNode.getNodeType() == Node.ELEMENT_NODE) &&
                        (testResultNode.getNodeName().equals("daterange"))) {
                    NamedNodeMap attributes = testResultNode.getAttributes();
                    startDate = attributes.getNamedItem("start").getNodeValue();
                    stopDate = attributes.getNamedItem("stop").getNodeValue();
                } else if ((testResultNode.getNodeType() == Node.ELEMENT_NODE) &&
                        (testResultNode.getNodeName().equals("testcode"))) {
                    testCode = testResultNode.getFirstChild().getNodeValue();
                }
            }
            TestResultDateRange dateRange =
                    new TestResultDateRange(getData("nhsno"), getData("centrecode"), testCode, startDate, stopDate);
            dateRanges.add(dateRange);
        }
    }

    private void collectTestResults(Document doc) {
        NodeList testNodeList = doc.getElementsByTagName("test");
        for (int i = 0; i < testNodeList.getLength(); i++) {
            Node testNode = testNodeList.item(i);
            NodeList testResultNodes = testNode.getChildNodes();
            String testCode = "";
            for (int j = 0; j < testResultNodes.getLength(); j++) {
                Node testResultNode = testResultNodes.item(j);
                if ((testResultNode.getNodeType() == Node.ELEMENT_NODE) &&
                        (testResultNode.getNodeName().equals("testname"))) {
                    int anything = 0;
                } else if ((testResultNode.getNodeType() == Node.ELEMENT_NODE) &&
                        (testResultNode.getNodeName().equals("testcode"))) {
                    testCode = testResultNode.getFirstChild().getNodeValue();
                } else if ((testResultNode.getNodeType() == Node.ELEMENT_NODE) &&
                        (testResultNode.getNodeName().equals("result"))) {
                    TestResult testResult = new TestResult(getData("nhsno"), getData("centrecode"), null, testCode, "");
                    NodeList resultDataNodes = testResultNode.getChildNodes();
                    for (int k = 0; k < resultDataNodes.getLength(); k++) {
                        Node resultDataNode = resultDataNodes.item(k);
                        if ((resultDataNode.getNodeType() == Node.ELEMENT_NODE) &&
                                (resultDataNode.getNodeName().equals("datestamp"))) {
                            parseDatestamp(testResult, resultDataNode);
                        } else if ((resultDataNode.getNodeType() == Node.ELEMENT_NODE) &&
                                (resultDataNode.getNodeName().equals("prepost"))) {
                            testResult.setPrepost(resultDataNode.getFirstChild().getNodeValue());
                        } else if ((resultDataNode.getNodeType() == Node.ELEMENT_NODE) &&
                                (resultDataNode.getNodeName().equals("value"))) {
                            testResult.setValue(resultDataNode.getFirstChild().getNodeValue().trim());
                        }
                    }
                    testResults.add(testResult);
                }
            }
        }
    }

    private void collectLetters(Document doc) {
        NodeList letterNodes = doc.getElementsByTagName("letter");
        for (int i = 0; i < letterNodes.getLength(); i++) {
            Node letterNode = letterNodes.item(i);
            Letter letter = new Letter();
            letter.setNhsno(getData("nhsno"));
            letter.setUnitcode(getData("centrecode"));
            NodeList letterDetailNodes = letterNode.getChildNodes();
            for (int j = 0; j < letterDetailNodes.getLength(); j++) {
                Node letterDetailNode = letterDetailNodes.item(j);
                if ((letterDetailNode.getNodeType() == Node.ELEMENT_NODE) &&
                        (letterDetailNode.getNodeName().equals("letterdate"))) {
                    letter.setStringDate(letterDetailNode.getFirstChild().getNodeValue());
                } else if ((letterDetailNode.getNodeType() == Node.ELEMENT_NODE) &&
                        (letterDetailNode.getNodeName().equals("lettertype"))) {
                    letter.setType(letterDetailNode.getFirstChild().getNodeValue());
                } else if ((letterDetailNode.getNodeType() == Node.ELEMENT_NODE) &&
                        (letterDetailNode.getNodeName().equals("lettercontent"))) {
                    NodeList nodes = letterDetailNode.getChildNodes();
                    for (int k = 0; k < nodes.getLength(); k++) {
                        Node node = nodes.item(k);
                        if (node.getNodeType() == Node.CDATA_SECTION_NODE) {
                            CDATASection cdata = (CDATASection) node;
                            letter.setContent(cdata.getData());
                        }
                    }
                }
            }
            letters.add(letter);
        }
    }

    private void collectOtherDiagnosis(Document doc) {
        NodeList diagnosisNodes = doc.getElementsByTagName("diagnosis");
        for (int i = 0; i < diagnosisNodes.getLength(); i++) {
            Node diagnosisNode = diagnosisNodes.item(i);
            Node diagnosisNodeChild = diagnosisNode.getFirstChild();
            if (diagnosisNodeChild != null) {
                Diagnosis diagnosis = new Diagnosis();
                diagnosis.setNhsno(getData("nhsno"));
                diagnosis.setUnitcode(getData("centrecode"));
                diagnosis.setDiagnosis(diagnosisNode.getFirstChild().getNodeValue());
                diagnosis.setDisplayorder(i + "");
                otherDiagnoses.add(diagnosis);
            }
        }
    }

    private void collectMedicines(Document doc) {
        NodeList medicineNodes = doc.getElementsByTagName("drug");
        for (int i = 0; i < medicineNodes.getLength(); i++) {
            Node medicineNode = medicineNodes.item(i);
            Medicine medicine = new Medicine();
            medicine.setNhsno(getData("nhsno"));
            medicine.setUnitcode(getData("centrecode"));
            NodeList medicineDetailNodes = medicineNode.getChildNodes();
            for (int j = 0; j < medicineDetailNodes.getLength(); j++) {
                try {
                    Node medicineDetailNode = medicineDetailNodes.item(j);
                    if ((medicineDetailNode.getNodeType() == Node.ELEMENT_NODE) &&
                            (medicineDetailNode.getNodeName().equals("drugstartdate"))) {
                        medicine.setStartdate(medicineDetailNode.getFirstChild().getNodeValue());
                    } else if ((medicineDetailNode.getNodeType() == Node.ELEMENT_NODE) &&
                            (medicineDetailNode.getNodeName().equals("drugname"))) {
                        medicine.setName(medicineDetailNode.getFirstChild().getNodeValue());
                    } else if ((medicineDetailNode.getNodeType() == Node.ELEMENT_NODE) &&
                            (medicineDetailNode.getNodeName().equals("drugdose"))) {
                        medicine.setDose(medicineDetailNode.getFirstChild().getNodeValue());
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
            medicines.add(medicine);
        }
    }

    private void parseDatestamp(TestResult testResult, Node resultDataNode) {
        String dateStampString = resultDataNode.getFirstChild().getNodeValue();
        testResult.setDatestampString(dateStampString);
    }

    private void collectTopLevelData(String dataId, Document doc) {
        NodeList nhsNoNodeList = doc.getElementsByTagName(dataId);
        if (nhsNoNodeList.getLength() != 0) {
            Node nhsNoNode = nhsNoNodeList.item(0);
            if (nhsNoNode.getFirstChild() == null) {
                topLevelDataSpecialCases(xmlData, dataId);
            } else {
                xmlData.put(dataId, nhsNoNode.getFirstChild().getNodeValue());
            }
        } else {
            topLevelDataSpecialCases(xmlData, dataId);
        }
    }

    private void topLevelDataSpecialCases(Map xmlData, String dataId) {
        if (dataId.equals("rrtstatus")) {
            xmlData.put(dataId, "GEN");
        } else if (dataId.equals("diagnosisedta")) {
            xmlData.put(dataId, "DEF");
        }
    }

    private Document getDocument(ServletContext context, File file) {
        Document doc = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(file);
        } catch (Exception e) {
            e.printStackTrace();
            //EmailUtils.sendEmail(context, e.toString());
        }
        return doc;
    }

    public String getData(String dataId) {
        return (String) xmlData.get(dataId);
    }

    public Collection getTestResults() {
        return testResults;
    }

    public Collection getDateRanges() {
        return dateRanges;
    }

    public ArrayList getLetters() {
        return letters;
    }

    public ArrayList getOtherDiagnoses() {
        return otherDiagnoses;
    }

    public ArrayList getMedicines() {
        return medicines;
    }

    public Patient getPatient() {
        Patient patient = new Patient((String) xmlData.get("nhsno"), (String) xmlData.get("surname"),
                (String) xmlData.get("forename"), (String) xmlData.get("dateofbirth"), (String) xmlData.get("sex"),
                (String) xmlData.get("address1"), (String) xmlData.get("address2"), (String) xmlData.get("address3"),
                (String) xmlData.get("postcode"), (String) xmlData.get("telephone1"),
                (String) xmlData.get("telephone2"), (String) xmlData.get("mobile"), (String) xmlData.get("centrecode"),
                (String) xmlData.get("diagnosisedta"), (String) xmlData.get("rrtstatus"),
                (String) xmlData.get("tpstatus"), (String) xmlData.get("hospitalnumber"),
                (String) xmlData.get("gpname"), (String) xmlData.get("gpaddress1"), (String) xmlData.get("gpaddress2"),
                (String) xmlData.get("gpaddress3"), (String) xmlData.get("gppostcode"),
                (String) xmlData.get("gptelephone"));
        return patient;
    }

    public Centre getCentre() {
        Centre centre = new Centre((String) xmlData.get("centrecode"), (String) xmlData.get("centrename"),
                (String) xmlData.get("centreaddress1"), (String) xmlData.get("centreaddress2"),
                (String) xmlData.get("centreaddress3"), (String) xmlData.get("centreaddress4"),
                (String) xmlData.get("centrepostcode"), (String) xmlData.get("centretelephone"),
                (String) xmlData.get("centreemail"));
        return centre;
    }

    public String getFlag() {
        return (String) xmlData.get("flag");
    }
}
