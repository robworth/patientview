package com.worthsoln.patientview.dataout;

import com.worthsoln.database.DatabaseDAO;
import com.worthsoln.patientview.ParserThread;
import com.worthsoln.patientview.model.Patient;
import com.worthsoln.patientview.TestResult;
import com.worthsoln.patientview.TestResultForPatientDataOutDao;
import com.worthsoln.patientview.model.Comment;
import com.worthsoln.patientview.model.Unit;
import com.worthsoln.utils.LegacySpringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.servlet.ServletContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.GregorianCalendar;
import java.util.Calendar;


public class DataOutThread implements Runnable, ParserThread {

    private String prebit;
    private String directory;
    private String archiveDirectory;
    private int minutesBetweenWait;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private ServletContext servletContext;

    public DataOutThread() {
    }

    public DataOutThread(ServletContext context, String dataoutDir,
                         String dataOutArchiveDirectory, int minutesBetweenWait) {
        this.directory = dataoutDir;
        this.archiveDirectory = dataOutArchiveDirectory;
        this.minutesBetweenWait = minutesBetweenWait;
        this.servletContext = context;
    }

    public void run() {
        try {
            while (true) {
                try {
                    List<Unit> units = returnAllTheUnits();

                    for (Unit unit : units) {
                        List<Patient> patients = patientsInUnit(unit.getUnitcode());

                        for (Patient patient : patients) {
                            Document patientDataOutXmlDoc = makePatientDataOutXml(patient);

                            if (!(null == patientDataOutXmlDoc)) {
                                writeDataOutFile(patientDataOutXmlDoc, patient, unit);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Thread.sleep(1000 * 60 * minutesBetweenWait);
                Date now = new Date(System.currentTimeMillis());
                System.out.println("DataOutThread " + dateFormat.format(now));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Document makePatientDataOutXml(Patient patient) {
        Document doc = null;

        List<TestResult> testResults = fetchTestResultsForPatient(patient);
        List<Comment> comments = fetchCommentsForPatient(patient);

        if (testResults.isEmpty() && comments.isEmpty()) {
            return doc;
        }

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("patientview");
            doc.appendChild(rootElement);

            Element dateOfReport = addChildElement(doc, rootElement, "dateofreport", getTimeStampNow());

            Element centreDetails = addChildElement(doc, rootElement, "centredetails");
            Element centreCode = addChildElement(doc, centreDetails, "centrecode", patient.getCentreCode());

            Element patientTag = addChildElement(doc, rootElement, "patient");
            Element personalDetails = addChildElement(doc, patientTag, "personaldetails");
            Element nhsno = addChildElement(doc, personalDetails, "nhsno", patient.getNhsno());
            Element surname = addChildElement(doc, personalDetails, "surname", patient.getSurname());
            Element forename = addChildElement(doc, personalDetails, "forename", patient.getForename());
            Element dateofbirth = addChildElement(doc, personalDetails, "dateofbirth", patient.getDateofbirth());

            Element testDetails = addChildElement(doc, rootElement, "testDetails");

            String testCodeTrack = "";
            Element currentTest = null;
            Element currentDateRange = null;
            String currentDateRangeStop = null;

            for (TestResult testResult : testResults) {

                if (!testCodeTrack.equals(testResult.getTestcode())) {
                    currentTest = addChildElement(doc, testDetails, "test");
                    if (!(null == currentDateRange)) {
                        currentDateRange.setAttribute("stop", currentDateRangeStop);
                    }
                    Element dateRange = addChildElement(doc, currentTest, "daterange");
                    currentDateRange = dateRange;
                    dateRange.setAttribute("start", testResult.getIsoDayDatestamp());
                    testCodeTrack = testResult.getTestcode();
                    Element testCode = addChildElement(doc, currentTest, "testcode", testResult.getTestcode());
                }

                Element result = addChildElement(doc, currentTest, "result");
                Element dateStamp = addChildElement(doc, result, "datestamp", testResult.getIsoDatestamp());
                Element source = addChildElement(doc, result, "source", testResult.getUnitcode());
                currentDateRangeStop = testResult.getIsoDayDatestamp();
                Element prePost = addChildElement(doc, result, "prepost", testResult.getPrepost());
                Element value = addChildElement(doc, result, "value", testResult.getValue());
            }

            if (!(null == currentDateRange) && !(null == currentDateRangeStop)) {
                currentDateRange.setAttribute("stop", currentDateRangeStop);
            }

            Element commentsTag = addChildElement(doc, rootElement, "comments");

            for (Comment comment : comments) {

                Element commentTag = addChildElement(doc, commentsTag, "comment");
                Element commentDate = addChildElement(doc, commentsTag, "commentdate",comment.getIsoFormattedDatestamp());
                Element commentContent = addChildElement(doc, commentsTag, "commentbody", comment.getBody());
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return doc;
    }

    private List<Comment> fetchCommentsForPatient(Patient patient) {
        List<Comment> comments = null;

        try {
            comments = LegacySpringUtils.getCommentManager().get(patient.getNhsno());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return comments;
    }

    private String getTimeStampNow() {
        Calendar nowCal = GregorianCalendar.getInstance();
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return dateTimeFormat.format(nowCal.getTime());
    }

    private List<TestResult> fetchTestResultsForPatient(Patient patient) {
        DatabaseDAO dao = new DatabaseDAO("patientview");

        TestResultForPatientDataOutDao dataOutDao = new TestResultForPatientDataOutDao(patient.getNhsno());

        List<TestResult> testResults = dao.retrieveList(dataOutDao);

        return testResults;

    }

    private Element addChildElement(Document doc, Element parentElement, String elementName) {
        Element childElement = doc.createElement(elementName);
        parentElement.appendChild(childElement);
        return childElement;
    }

    private Element addChildElement(Document doc, Element parentElement, String elementName, String data) {
        Element childElement = addChildElement(doc, parentElement, elementName);
        childElement.appendChild(doc.createTextNode(data));
        return childElement;
    }

    private List<Patient> patientsInUnit(String unitCode) {
        List<Patient> patients = null;

        try {
            patients = LegacySpringUtils.getPatientManager().get(unitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return patients;
    }

    private List<Unit> returnAllTheUnits() {

        List<Unit> units = null;

        try {
            units = LegacySpringUtils.getUnitManager().getUnitsWithUser();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return units;
    }

    private void writeDataOutFile(Document doc, Patient patient, Unit unit) throws ParserConfigurationException, TransformerException {
        String directory1stPart = directory;
        String directory2ndPart = unit.getUnituser();
        String directory3rdPart = servletContext.getInitParameter(prebit + ".directory.thirdpart");

        String filePath = directory1stPart + "/" + directory2ndPart + "/" + directory3rdPart;

        File uktDir = new File(filePath);
        File uktExportFile = new File(uktDir, "data_out_" + unit.getUnitcode() + "_" + patient.getNhsno() + ".xml");


        //write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(uktExportFile);
        transformer.transform(source, result);

    }

    public String getPrebit() {
        return prebit;
    }

    public void setPrebit(String prebit) {
        this.prebit = prebit;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getArchiveDirectory() {
        return archiveDirectory;
    }

    public void setArchiveDirectory(String archiveDirectory) {
        this.archiveDirectory = archiveDirectory;
    }

    public int getMinutesBetweenWait() {
        return minutesBetweenWait;
    }

    public void setMinutesBetweenWait(int minutesBetweenWait) {
        this.minutesBetweenWait = minutesBetweenWait;
    }

    public ServletContext getServletContext() {
        return servletContext;
    }

    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}
