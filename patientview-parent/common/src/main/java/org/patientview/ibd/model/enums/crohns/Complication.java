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

package org.patientview.ibd.model.enums.crohns;

import java.util.Arrays;
import java.util.List;

public enum Complication {
    NONE(0, "None", 0),
    IBD_RELATED_JOINT_COMPLICATIONS(1, "IBD related joint complications (inflammatory arthritis, sacroilelitis)", 1),
    IBD_RELATED_SKIN_COMPLICATIONS(2, "IBD related skin complications (erythema nodosum, pyoderma gangrenosum)", 1),
    IBD_RELATED_EYE_COMPLICATIONS(3, "IBD related eye complications (uveitis, episclreitis)", 1),
    MOUTH_ULCERS(4, "Mouth ulcers", 1),
    ANAL_FISSURE(5, "Anal fissure", 1),
    FISTULA(6, "Fistula", 1),
    ABSCESS(7, "Abscess", 1);

    private int id;
    private String displayText;
    private int score;

    private Complication(int id, String displayText, int score) {
        this.id = id;
        this.displayText = displayText;
        this.score = score;
    }

    public static Complication getComplication(int id) {
        for (Complication complication : Complication.values()) {
            if (complication.getId() == id) {
                return complication;
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

    public static List<Complication> getAsList() {
        return Arrays.asList(Complication.values());
    }
}
