package com.worthsoln.patientview.uktransplant;

import java.io.File;
import java.io.FileReader;
import javax.servlet.ServletContext;
import com.Ostermiller.util.CSVParser;
import com.worthsoln.patientview.logging.AddLog;
import com.worthsoln.patientview.model.UktStatus;
import com.worthsoln.utils.LegacySpringUtils;

public class UktUpdater {

    public void update(ServletContext context, File uktFile) {
        try {
            deleteUktRecords();
            updateUktData(uktFile);
            AddLog.addLog(AddLog.ACTOR_SYSTEM, AddLog.UKT_DATA_REPLACE, "", "", "", uktFile.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateUktData(File uktFile) {
        try {
            CSVParser uktParser = new CSVParser(new FileReader(uktFile));

            uktParser.changeDelimiter('|');
            String[][] uktValues = uktParser.getAllValues();
            for (int i = 0; i < uktValues.length; i++) {
                UktStatus status = new UktStatus(uktValues[i][0].trim(),
                        uktValues[i][1].trim(), uktValues[i][2].trim());

                LegacySpringUtils.getUkTransplantManager().save(status);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteUktRecords() {
        LegacySpringUtils.getUkTransplantManager().deleteAll();
    }
}
