package com.worthsoln.patientview.uktransplant;

import java.io.File;
import javax.servlet.ServletContext;

public class UktParserUtils {

    public static void updateData(ServletContext context, File uktFile) {
        UktUpdater updater = new UktUpdater();
        updater.update(context, uktFile);
    }
}
