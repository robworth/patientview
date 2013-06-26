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

package org.patientview.ibd.model.enums;

import java.util.Arrays;
import java.util.List;

public enum Feeling {
    WELL(0, "I feel well", 0),
    BELOW_PAR(1, "Slightly below par", 1),
    POOR(2, "Poor", 2),
    VERY_POOR(3, "Very poor", 3),
    TERRIBLE(4, "Terrible", 4);

    private int id;
    private String displayText;
    private int score;

    private Feeling(int id, String displayText, int score) {
        this.id = id;
        this.displayText = displayText;
        this.score = score;
    }

    public static Feeling getFeeling(int id) {
        for (Feeling feeling : Feeling.values()) {
            if (feeling.getId() == id) {
                return feeling;
            }
        }

        return null;
    }

    public int getId() {
        return id;
    }

    public String getDisplayText() {
        return displayText;
    }

    public int getScore() {
        return score;
    }

    public static List<Feeling> getAsList() {
        return Arrays.asList(Feeling.values());
    }
}
