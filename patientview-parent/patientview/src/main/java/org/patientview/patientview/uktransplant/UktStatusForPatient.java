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

package org.patientview.patientview.uktransplant;

public class UktStatusForPatient {

    private String nhsno;
    private String uktkidney;
    private String uktpancreas;

    public UktStatusForPatient() {
    }

    public UktStatusForPatient(String nhsno, String uktkidney, String uktpancreas) {
        this.nhsno = nhsno;
        this.uktkidney = uktkidney;
        this.uktpancreas = uktpancreas;
    }

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public String getUktkidney() {
        return uktkidney;
    }

    public void setUktkidney(String uktkidney) {
        this.uktkidney = uktkidney;
    }

    public String getUktpancreas() {
        return uktpancreas;
    }

    public void setUktpancreas(String uktpancreas) {
        this.uktpancreas = uktpancreas;
    }
}
