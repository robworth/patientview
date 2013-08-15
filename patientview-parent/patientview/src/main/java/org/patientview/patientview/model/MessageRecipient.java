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

import java.util.Collections;
import java.util.Comparator;

/**
 * Used to display user's unit on FE
 */
public class MessageRecipient {

    private User user;
    private Unit unit;

    public MessageRecipient(User user, Unit unit) {
        this.user = user;
        this.unit = unit;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public static enum Order implements Comparator<MessageRecipient> {
        ByUsername() {
            @Override
            public int compare(MessageRecipient left, MessageRecipient right) {
                return left.getUser().getName().compareTo(right.getUser().getName());
            }
        };

        public Comparator ascending() {
            return this;
        }

        public Comparator descending() {
            return Collections.reverseOrder(this);
        }
    }
}
