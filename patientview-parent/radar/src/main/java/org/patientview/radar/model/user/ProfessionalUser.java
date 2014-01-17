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

package org.patientview.radar.model.user;

import org.patientview.model.Centre;

public class ProfessionalUser extends User {

    // super users are hardcoded by id in the previous implementation
    private static final long[] SUPER_USER_IDS = {28, 15};

    private String surname = "", forename = "", title = "", gmc = "", role = "", phone = ""
            , securityQuestion = "", securityQuestionAnsw = "";
    private Centre centre = new Centre();

    private String securityRole;

    private boolean isGroupAdmin;

    /**
     * Maintain the 'wrong' implementation of hard wiring users to have super user status based on id.
     * Allow the security role to be set as ROLE_SUPER_USER for other users with the role.
     *
     * Keep the default of User.ROLE_PROFESSIONAL in case the mapping fails.
     * @return
     */
    @Override
    public String getSecurityRole() {

        for (long id : SUPER_USER_IDS) {
            if (getId().equals(id)) {
                return User.ROLE_SUPER_USER;
            }
        }

        if (securityRole != null) {
            return securityRole;
        } else {
            return User.ROLE_PROFESSIONAL;
        }
    }

    public void setSecurityRole(String securityRole) {
        this.securityRole = securityRole;
    }

    @Override
    public String getName() {
        return forename + " " + surname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGmc() {
        return gmc;
    }

    public void setGmc(String gmc) {
        this.gmc = gmc;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityQuestionAnsw() {
        return securityQuestionAnsw;
    }

    public void setSecurityQuestionAnsw(String securityQuestionAnsw) {
        this.securityQuestionAnsw = securityQuestionAnsw;
    }

    public boolean isGroupAdmin() {
        return isGroupAdmin;
    }

    public void setGroupAdmin(boolean groupAdmin) {
        isGroupAdmin = groupAdmin;
    }
}
