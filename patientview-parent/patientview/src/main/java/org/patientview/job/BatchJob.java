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

import org.patientview.service.JobManager;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.batch.core.annotation.OnReadError;
import org.springframework.batch.core.annotation.OnSkipInWrite;
import org.slf4j.Logger;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Definitions base use methods for job
 */
public abstract class BatchJob {

    protected static final Logger LOGGER = LoggerFactory.getLogger(BatchJob.class);

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private JobManager jobManager;

    /**
     * Run the batch job
     */
    public void run() {

        Map<String, JobParameter> map = new HashMap<String, JobParameter>();
        map.put("key", new JobParameter(new Date()));
        try {
            JobExecution result = jobLauncher.run(getBatchJob(), new JobParameters(map));
        } catch (Exception e) {
            LOGGER.debug(e.getStackTrace().toString());
            onRunError(e);
        }
    }

    /**
     * Method done before run job
     */
    @BeforeJob
    public void beforeJob() {
        prepare();
    }

    /**
     * Method done after job
     *
     * @param result jobExecution
     */
    @AfterJob
    public void afterJob(JobExecution result) {
        LOGGER.debug(result.toString());
        afterBatchJob(result);
    }

    /**
     * Catch exception from batch reader
     *
     * @param e reader exception
     */
    @OnReadError
    public void onReadError(Exception e) {
        LOGGER.debug(e.getMessage());
    }

    /**
     * Report information for skip rows in batch reader
     *
     * @param holder
     * @param problem
     */
    @OnSkipInWrite
    public void onSkipInWriter(Object holder, Throwable problem) {
        onJobSkipInWriter(holder, problem);
    }

    /**
     * Prepare job configuration before batch reader
     */
    protected abstract void prepare();

    /**
     * Get the runnable org.springframework.batch.core.Job instance
     * @return Job
     */
    protected abstract org.springframework.batch.core.Job getBatchJob();

    /**
     * Do something when skip in writer
     *
     * @param holder
     * @param problem
     */
    protected abstract void onJobSkipInWriter(Object holder, Throwable problem);

    /**
     * Do something after job
     * @param result
     */
    protected abstract void afterBatchJob(JobExecution result);

    /**
     *
     * @param e
     */
    protected abstract void onRunError(Exception e);

    public JobManager getJobManager() {
        return jobManager;
    }

    public void setJobManager(JobManager jobManager) {
        this.jobManager = jobManager;
    }
}
