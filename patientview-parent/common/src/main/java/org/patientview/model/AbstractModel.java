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

package org.patientview.model;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Common attributes across all model objects
 */
@MappedSuperclass
public abstract class AbstractModel implements Serializable, Comparable {

    public boolean hasValidId() {
        Long id = getId();
        return id != null && id > 0;
    }

    public abstract Long getId();

    public abstract void setId(Long id);
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(this.getClass().isAssignableFrom(o.getClass()))) {
            return false;
        }

        AbstractModel baseModel = (AbstractModel) o;

        Long id = getId();
        return id != null && id.equals(baseModel.getId());
    }

    @Override
    public int hashCode() {
        Long id = getId();
        if (id != null) {
            return id.hashCode();
        } else {
            return -1;
        }
    }

    public int compareTo(Object o) {

        AbstractModel baseModel = (AbstractModel) o;

        return this.getId().compareTo(baseModel.getId());
    }

    public static String getLabelFromEnum(String enumString) {
        String label = enumString;
        label = label.replace("_", " ");
        String[] parts = label.split(" ");
        label = "";
        for (String part : parts) {
            String formatted = part.toLowerCase();
            formatted = Character.toUpperCase(formatted.charAt(0)) + formatted.substring(1);
            label += formatted;
            label += " ";
        }
        return label;
    }
}
