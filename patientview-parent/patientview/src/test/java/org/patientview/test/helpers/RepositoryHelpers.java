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

package org.patientview.test.helpers;

import org.patientview.patientview.model.Message;
import org.patientview.patientview.model.Specialty;
import org.patientview.patientview.model.SpecialtyUserRole;
import org.patientview.patientview.model.User;
import org.patientview.patientview.model.Conversation;

/**
 *
 */
public interface RepositoryHelpers {

    User createUser(String username, String email, String password, String name);

    User createUserWithMapping(String username, String email, String password, String name,
                               String unitcode, String nhsno, Specialty specialty);

    Specialty createSpecialty(String name, String context, String description);

    SpecialtyUserRole createSpecialtyUserRole(Specialty specialty, User user, String role);

    Conversation createConversation(String subject, User participant1, User participant2, boolean store);

    Message createMessage(Conversation conversation, User sender, User recipient, String content, boolean store);
}
