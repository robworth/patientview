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

package org.patientview.patientview.logon;

import java.util.Date;

public class PatientLogon extends Logon implements Cloneable {

    public PatientLogon() {
    }

    public PatientLogon(String username) {
        setUsername(username);
    }

    public PatientLogon(String username, String password, String name, String email, boolean emailverified,
                        boolean firstlogon, boolean dummypatient, Date lastlogon, int failedlogons,
                        boolean accountlocked) {
        setUsername(username);
        setPassword(password);
        setName(name);
        setEmail(email);
        setEmailverfied(emailverified);
        setRole("patient");
        setFirstlogon(firstlogon);
        setDummypatient(dummypatient);
        setLastlogon(lastlogon);
        setFailedlogons(failedlogons);
        setAccountlocked(accountlocked);
    }

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
