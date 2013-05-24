package com.worthsoln.batch;

import com.worthsoln.patientview.model.EmailQueue;
import com.worthsoln.patientview.model.Job;
import com.worthsoln.patientview.model.User;
import com.worthsoln.patientview.model.enums.GroupEnum;
import com.worthsoln.patientview.model.enums.SendEmailEnum;
import com.worthsoln.service.EmailQueueManager;
import com.worthsoln.service.JobManager;
import com.worthsoln.service.MessageManager;
import com.worthsoln.service.UserManager;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Component
public class CreateEmailQueueReader extends ListItemReader<Object> {

    @Autowired
    private EmailQueueManager emailQueueManager;

    @Autowired
    private JobManager jobManager;

    @Autowired
    private MessageManager messageManager;

    public void refresh(Job job) {

        List<User> users = new ArrayList<User>();
        List<Object> emailQueues = new ArrayList<Object>();
        EmailQueue queue;
        String userType = "";

        if (job != null && GroupEnum.ALL_ADMINS.equals(job.getGroupEnum())) {
            userType = "unitadmin";
        } else if (job != null && GroupEnum.ALL_STAFF.equals(job.getGroupEnum())) {
            userType = "unitstaff";
        } else if (job != null && GroupEnum.ALL_PATIENTS.equals(job.getGroupEnum())) {
            userType = "patient";
        } else {}

        users.addAll(jobManager.getSpecialGroupUsers(
                            job.getCreator(),
                            job.getSpecialty(),
                            userType,
                            job.getMessage().getId().toString()));

        if (!users.isEmpty()) {
            for (User user : users) {
                queue = new EmailQueue();
                queue.setJob(job);
                queue.setMessage(job.getMessage());
                queue.setRecipient(user);
                queue.setStatus(SendEmailEnum.SENDING);

                emailQueues.add(queue);
            }
            setList(emailQueues);
        }
    }
}
