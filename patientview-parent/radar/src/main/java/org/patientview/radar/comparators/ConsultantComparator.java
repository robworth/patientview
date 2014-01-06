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

package org.patientview.radar.comparators;

import org.patientview.radar.model.Consultant;
import org.apache.wicket.model.PropertyModel;

import java.io.Serializable;
import java.util.Comparator;

public class ConsultantComparator implements Comparator<Consultant>, Serializable {
    private String sortProperty;
    private boolean ascending;

    public int compare(final Consultant o1, final Consultant o2) {
        PropertyModel<Comparable<Consultant>> model1 = new PropertyModel<Comparable<Consultant>>(o1, getSortProperty());
        PropertyModel<Comparable<Consultant>> model2 = new PropertyModel<Comparable<Consultant>>(o2, getSortProperty());

        int result = model1.getObject().compareTo((Consultant) model2.getObject());

        if (!ascending) {
            result = -result;
        }

        return result;
    }

    public String getSortProperty() {
        return sortProperty;
    }

    public void setSortProperty(String sortProperty) {
        this.sortProperty = sortProperty;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }
}
