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

import java.util.List;

public class Panel {

    private int panel;
    private boolean currentPanel;
    private List<ResultHeading> resultHeadings;

    public Panel() {
    }

    public Panel(int panel) {
        this.panel = panel;
    }

    public int getPanel() {
        return panel;
    }

    public void setPanel(int panel) {
        this.panel = panel;
    }

    public boolean isCurrentPanel() {
        return currentPanel;
    }

    public void setCurrentPanel(boolean currentPanel) {
        this.currentPanel = currentPanel;
    }

    public List<ResultHeading> getResultHeadings() {
        return resultHeadings;
    }

    public void setResultHeadings(List<ResultHeading> resultHeadings) {
        this.resultHeadings = resultHeadings;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Panel)) {
            return false;
        }
        final Panel panel1 = (Panel) o;
        return panel == panel1.panel;
    }

    public int hashCode() {
        return panel;
    }

    public String toString() {
        return panel + "";
    }
}
