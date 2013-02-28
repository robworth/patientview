package com.worthsoln.patientview.parser;

import java.io.File;
import javax.servlet.ServletContext;
import com.worthsoln.patientview.ResultsUpdater;

public class XmlParserUtils {

    public static void updateXmlData(ServletContext context, File xmlFile) {
        ResultsUpdater updater = new ResultsUpdater();
        updater.update(context, xmlFile);
    }
}
