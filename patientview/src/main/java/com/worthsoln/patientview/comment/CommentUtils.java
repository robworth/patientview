package com.worthsoln.patientview.comment;

import com.worthsoln.HibernateUtil;
import com.worthsoln.patientview.User;
import com.worthsoln.patientview.logon.UserMapping;
import com.worthsoln.patientview.user.UserUtils;
import com.worthsoln.utils.LegacySpringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CommentUtils {

    public static boolean verifyPermissionToReadItem(HttpServletRequest request, String nhsno) throws Exception {
        boolean permissionToReadComment = false;
        String username = LegacySpringUtils.getSecurityUserManager().getLoggedInUsername();
        if (username != null) {
            User user = (User) HibernateUtil.getPersistentObject(User.class, username);

            if (user.getRole().equalsIgnoreCase("superadmin")) {
                permissionToReadComment = true;
            } else {

                List<UserMapping> userMappingsForUser = UserUtils.retrieveUserMappings(user);
                List<UserMapping> userMappingsForComment = UserUtils.retrieveUserMappingsForNhsno(nhsno);

                for (UserMapping userMappingComment : userMappingsForComment) {
                    if ("patient".equalsIgnoreCase(user.getRole()) && userMappingComment.getUsername().equalsIgnoreCase(user.getUsername())) {
                        permissionToReadComment = true;
                        break;
                    }
                    for (UserMapping userMappingUser : userMappingsForUser) {
                        if ("unitadmin".equalsIgnoreCase(user.getRole()) || "unitstaff".equalsIgnoreCase(user.getRole())) {
                            if (userMappingComment.getUnitcode().equalsIgnoreCase(userMappingUser.getUnitcode())) {
                                permissionToReadComment = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        return permissionToReadComment;
    }
}
