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

package org.patientview.batch;

import org.patientview.patientview.model.EmailQueue;
import org.patientview.patientview.model.Job;
import org.patientview.patientview.model.User;
import org.patientview.patientview.model.enums.GroupEnum;
import org.patientview.patientview.model.enums.SendEmailEnum;
import org.patientview.service.EmailQueueManager;
import org.patientview.service.JobManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *  CreateEmailQueueJob reader
 */
@Component
public class CreateEmailQueueReader extends ListItemReader<Object> {

    @Autowired
    private EmailQueueManager emailQueueManager;

    @Autowired
    private JobManager jobManager;

    public void refresh(List<Job> jobs) {

        List<User> users = new ArrayList<User>();
        List<Object> emailQueues = new ArrayList<Object>();
        EmailQueue queue;
        String userType = "";

        for (Job job : jobs) {
            if (job != null && GroupEnum.ALL_ADMINS.equals(job.getGroupEnum())) {
                userType = "unitadmin";
            } else if (job != null && GroupEnum.ALL_STAFF.equals(job.getGroupEnum())) {
                userType = "unitstaff";
            } else if (job != null && GroupEnum.ALL_PATIENTS.equals(job.getGroupEnum())) {
                userType = "patient";
            }

            users.addAll(jobManager.getSpecialGroupUsers(
                                job.getCreator(),
                                job.getSpecialty(),
                                userType,
                                job.getMessage().getUnit()));

            if (!users.isEmpty()) {
                for (User user : users) {
                    if (StringUtils.hasText(user.getEmail())
                            && !job.getCreator().getId().equals(user.getId())
                            && emailQueueManager.get(job.getId(), job.getMessage().getId(), user.getId()) == null) {
                        queue = new EmailQueue();
                        queue.setJob(job);
                        queue.setMessage(job.getMessage());
                        queue.setRecipient(user);
                        queue.setStatus(SendEmailEnum.SENDING);

                        emailQueues.add(queue);
                    }
                }

                setList(emailQueues);
            }
        }
    }
}
