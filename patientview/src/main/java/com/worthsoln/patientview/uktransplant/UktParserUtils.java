package com.worthsoln.patientview.uktransplant;

import java.io.File;
import javax.servlet.ServletContext;
import com.worthsoln.database.DatabaseDAO;

public class UktParserUtils {

    public static void updateData(ServletContext context, File uktFile, DatabaseDAO dao) {
        UktUpdater updater = new UktUpdater(dao);
        updater.update(context, uktFile);
    }
}
