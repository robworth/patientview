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

package org.patientview.patientview.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class UktStatus extends BaseModel {

    @Column(nullable = false)
    private String nhsno;

    @Column
    private String kidney;

    @Column
    private String pancreas;

    private static final int HASH_SEED = 29;

    public UktStatus() {
    }

    public UktStatus(String nhsno) {
        this.nhsno = nhsno;
    }

    public UktStatus(String nhsno, String kidney, String pancreas) {
        this.nhsno = nhsno;
        this.kidney = kidney;
        this.pancreas = pancreas;
    }

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public String getKidney() {
        return kidney;
    }

    public void setKidney(String kidney) {
        this.kidney = kidney;
    }

    public String getPancreas() {
        return pancreas;
    }

    public void setPancreas(String pancreas) {
        this.pancreas = pancreas;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UktStatus)) {
            return false;
        }
        final UktStatus uktStatus = (UktStatus) o;
        if (kidney != null ? !kidney.equals(uktStatus.kidney) : uktStatus.kidney != null) {
            return false;
        }
        if (nhsno != null ? !nhsno.equals(uktStatus.nhsno) : uktStatus.nhsno != null) {
            return false;
        }
        if (pancreas != null ? !pancreas.equals(uktStatus.pancreas) : uktStatus.pancreas != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result;
        result = (nhsno != null ? nhsno.hashCode() : 0);
        result = HASH_SEED * result + (kidney != null ? kidney.hashCode() : 0);
        result = HASH_SEED * result + (pancreas != null ? pancreas.hashCode() : 0);
        return result;
    }
}
