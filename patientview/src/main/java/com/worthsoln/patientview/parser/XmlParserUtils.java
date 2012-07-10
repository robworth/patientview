package com.worthsoln.patientview.parser;

import java.io.File;
import javax.servlet.ServletContext;
import com.worthsoln.database.DatabaseDAO;
import com.worthsoln.patientview.ResultsUpdater;

public class XmlParserUtils {

    public static void updateXmlData(ServletContext context, File xmlFile, DatabaseDAO dao) {
        ResultsUpdater updater = new ResultsUpdater(dao);
        updater.update(context, xmlFile);
    }
}
