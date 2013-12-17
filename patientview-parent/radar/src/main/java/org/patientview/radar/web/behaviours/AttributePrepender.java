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

package org.patientview.radar.web.behaviours;

import org.apache.wicket.AttributeModifier;

/**
 * Adds code to the beginning of an attribute.
 * @see org.apache.wicket.behavior.AttributeAppender
 */
public final class AttributePrepender extends AttributeModifier {

    /**
     * constructor is private. Use static getNewInstance method to get a new instance
     */
    private AttributePrepender(String attribute, org.apache.wicket.model.IModel<?> replaceModel) {
       super(attribute, replaceModel);
    }

    @Override
    protected String newValue(String currentValue, String appendValue) {
      return appendValue + currentValue;
    }

    /**
     * gets new instance
     */

    public static AttributePrepender getNewInstance(String attribute, org.apache.wicket.model.IModel<?> replaceModel) {
        return new AttributePrepender(attribute, replaceModel);
    }
}
