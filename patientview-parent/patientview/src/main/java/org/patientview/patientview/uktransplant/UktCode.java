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

public class UktCode {

    private int id;
    private String uktcode;
    private String description;

    private static final int HASH_SEED = 29;

    public UktCode() {
    }

    public UktCode(String uktcode, String description) {
        this.uktcode = uktcode;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUktcode() {
        return uktcode;
    }

    public void setUktcode(String uktcode) {
        this.uktcode = uktcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UktCode)) {
            return false;
        }
        final UktCode uktCode = (UktCode) o;
        if (description != null ? !description.equals(uktCode.description) : uktCode.description != null) {
            return false;
        }
        if (uktcode != null ? !uktcode.equals(uktCode.uktcode) : uktCode.uktcode != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result;
        result = (uktcode != null ? uktcode.hashCode() : 0);
        result = HASH_SEED * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
