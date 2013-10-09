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

package org.patientview.service.impl;

import com.Ostermiller.util.CSVParser;
import org.patientview.patientview.logging.AddLog;
import org.patientview.patientview.model.UktStatus;
import org.patientview.repository.UktStatusDao;
import org.patientview.service.UKTransplantManager;
import org.patientview.utils.LegacySpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 */
@Service(value = "uKTransplantManager")
public class UKTransplantManagerImpl implements UKTransplantManager {

    @Inject
    private UktStatusDao uktStatusDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(UKTransplantManagerImpl.class);

    @Override
    public UktStatus getUktStatus(String nhsno) {
        return uktStatusDao.get(nhsno);
    }

    @Override
    public void importUktStatusData(File file) throws IOException {

        // delete all existing data - this shows that it doesn't make sense to run the import job for multiple files
        deleteAll();

        // import data from file
        updateUktData(file);

        // delete the file
        if (!file.delete()) {
            LOGGER.error("Failed to delete uktfile: {}", file.getName());
        }

        // log a success
        AddLog.addLog(AddLog.ACTOR_SYSTEM, AddLog.UKT_DATA_REPLACE, "", "", "", file.getName());
    }

    private void updateUktData(File uktFile) throws IOException {
        CSVParser uktParser = new CSVParser(new FileReader(uktFile));

        uktParser.changeDelimiter('|');
        String[][] uktValues = uktParser.getAllValues();
        for (int i = 0; i < uktValues.length; i++) {
            UktStatus status = new UktStatus(uktValues[i][0].trim(),
                    uktValues[i][1].trim(), uktValues[i][2].trim());

            LegacySpringUtils.getUkTransplantManager().save(status);
        }
        uktParser.close();
    }

    @Override
    public void save(UktStatus uktStatus) {
        uktStatusDao.save(uktStatus);
    }

    @Override
    public void deleteAll() {
        uktStatusDao.deleteAll();
    }
}
