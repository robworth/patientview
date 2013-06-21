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

import org.patientview.batch.CreateEmailQueueReader;
import org.patientview.patientview.model.EmailQueue;
import org.patientview.patientview.model.enums.SendEmailEnum;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Get the unsent messages related members's information then insert the records to EamilQueue
 */
@Component
public class CreateEmailQueueJob extends BatchJob {

    @Resource(name = "createEmailQueueBatchJob")
    private Job batchJob;

    @Resource(name = "createEmailQueueReader")
    private CreateEmailQueueReader reader;

    private List<org.patientview.patientview.model.Job> jobs;

    @Override
    protected Job getBatchJob() {
        return batchJob;
    }

    @Override
    protected void onJobSkipInWriter(Object holder, Throwable problem) {

        if (holder instanceof EmailQueue) {
            EmailQueue emailQueue = (EmailQueue) holder;
            org.patientview.patientview.model.Job job = emailQueue.getJob();
            int jobIndex = jobs.indexOf(job);
            if (jobIndex != -1) {
                job.addReport(
                      "username=" + emailQueue.getRecipient().getUsername()
                    + ",messageId=" + emailQueue.getMessage().getId()
                    + " : " + problem.getMessage());

                job.addErrorCount();
                job.setStatus(SendEmailEnum.FAILED);

            }
        }
    }

    @Override
    protected void afterBatchJob(JobExecution result) {
        if (result.getStatus() != BatchStatus.COMPLETED) {
            updateJobStatusFailded(jobs);
        } else {
            updateJobStatusSucceeded(jobs);
        }
    }

    @Override
    protected void onRunError(Exception e) {
        LOGGER.debug(e.getMessage());
        if (jobs != null) {
            for (org.patientview.patientview.model.Job job : jobs) {
                job.addReport(e.getMessage());
                job.convertReports();
                job.setFinished(new Date());
                job.setStatus(SendEmailEnum.FAILED);
                getJobManager().save(job);
            }
        }
    }

    @Override
    protected void prepare() {
        jobs = getJobManager().getJobList(SendEmailEnum.PENDING);
        reader.refresh(jobs);
    }

    /**
     * Update the jobs' status to FAILED
     * @param jobs
     */
    protected void updateJobStatusFailded(List<org.patientview.patientview.model.Job> jobs) {
        LOGGER.debug("==update failed status==");
        for (org.patientview.patientview.model.Job job : jobs) {
            job.convertReports();
            job.setFinished(new Date());
            job.setStatus(SendEmailEnum.FAILED);
            getJobManager().save(job);
        }
    }

    private void updateJobStatusSucceeded(List<org.patientview.patientview.model.Job> jobs) {
        LOGGER.debug("==update sent status==");
        for (org.patientview.patientview.model.Job job : jobs) {
            job.convertReports();
            job.setFinished(new Date());
            if (!SendEmailEnum.FAILED.equals(job.getStatus())) {
                job.setStatus(SendEmailEnum.SENT);
            }
            getJobManager().save(job);
        }
    }

    /**
     * According to the status, select and set the jobs from Job entry
     */
    private  void setJobs() {
       this.jobs = getJobManager().getJobList(SendEmailEnum.PENDING);
    }
}
