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

package org.patientview.patientview.comment;

import org.patientview.patientview.model.User;
import org.patientview.patientview.model.UserMapping;
import org.patientview.patientview.user.UserUtils;
import org.patientview.utils.LegacySpringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public final class CommentUtils {

    private CommentUtils() {
    }

    public static boolean verifyPermissionToReadItem(HttpServletRequest request, String nhsno) throws Exception {
        boolean permissionToReadComment = false;
        String username = LegacySpringUtils.getSecurityUserManager().getLoggedInUsername();
        if (username != null) {
            User user = LegacySpringUtils.getUserManager().get(username);

            final String role = LegacySpringUtils.getUserManager().getCurrentSpecialtyRole(user);

            if (role.equalsIgnoreCase("superadmin")) {
                permissionToReadComment = true;
            } else {

                List<UserMapping> userMappingsForUser = UserUtils.retrieveUserMappings(user);
                List<UserMapping> userMappingsForComment
                        = LegacySpringUtils.getUserManager().getUserMappingsForNhsNo(nhsno);

                for (UserMapping userMappingComment : userMappingsForComment) {
                    if ("patient".equalsIgnoreCase(role)
                            && userMappingComment.getUsername().equalsIgnoreCase(user.getUsername())) {
                        permissionToReadComment = true;
                        break;
                    }
                    for (UserMapping userMappingUser : userMappingsForUser) {
                        if ("unitadmin".equalsIgnoreCase(role) || "unitstaff".equalsIgnoreCase(role)) {
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
