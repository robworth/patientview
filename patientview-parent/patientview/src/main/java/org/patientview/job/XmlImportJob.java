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
package org.patientview.job;

import org.patientview.batch.XmlImportReader;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Read the file in specialty path and save to DB.
 */
@Component
public class XmlImportJob extends BatchJob {

    @Resource(name = "xmlImportBatchJob")
    private Job batchJob;

    @Resource(name = "xmlImportReader")
    private XmlImportReader reader;

    @Override
    protected Job getBatchJob() {
        return batchJob;
    }

    @Override
    protected void onJobSkipInWriter(Object holder, Throwable problem) {
    }

    @Override
    protected void afterBatchJob(JobExecution result) {
        if (result.getStatus() != BatchStatus.COMPLETED) {
            if (result.getFailureExceptions() != null && !result.getFailureExceptions().isEmpty()) {
                LOGGER.debug(result.getFailureExceptions().get(0).getMessage());
            }
        }
    }

    @Override
    protected void onRunError(Exception e) {
        LOGGER.debug(e.getMessage());
    }

    @Override
    protected void prepare() {
        reader.refresh();
    }

}
