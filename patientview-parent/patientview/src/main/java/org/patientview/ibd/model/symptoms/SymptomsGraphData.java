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

package org.patientview.ibd.model.symptoms;

import java.util.ArrayList;
import java.util.List;

public class SymptomsGraphData {
    private String error = "";
    private List<SymptomsData> graphData = new ArrayList<SymptomsData>();
    private int severeLevel, moderateLevel, mildLevel;

    public void addGraphData(SymptomsData symptomsData) {
        if (graphData == null) {
            graphData = new ArrayList<SymptomsData>();
        }

        graphData.add(symptomsData);
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<SymptomsData> getGraphData() {
        return graphData;
    }

    public void setGraphData(List<SymptomsData> graphData) {
        this.graphData = graphData;
    }

    public int getSevereLevel() {
        return severeLevel;
    }

    public void setSevereLevel(int severeLevel) {
        this.severeLevel = severeLevel;
    }

    public int getModerateLevel() {
        return moderateLevel;
    }

    public void setModerateLevel(int moderateLevel) {
        this.moderateLevel = moderateLevel;
    }

    public int getMildLevel() {
        return mildLevel;
    }

    public void setMildLevel(int mildLevel) {
        this.mildLevel = mildLevel;
    }
}
