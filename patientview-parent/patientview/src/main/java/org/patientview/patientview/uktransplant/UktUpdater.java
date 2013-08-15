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

package org.patientview.patientview.uktransplant;

import java.io.File;
import java.io.FileReader;
import javax.servlet.ServletContext;
import com.Ostermiller.util.CSVParser;
import org.patientview.patientview.logging.AddLog;
import org.patientview.patientview.model.UktStatus;
import org.patientview.utils.LegacySpringUtils;

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
