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

package org.patientview.patientview;

import org.patientview.patientview.model.Panel;

import java.util.List;

public class PanelNavigation {

    private List<Panel> panels;
    private Panel currentPanel;
    private Panel previousPanel;
    private Panel nextPanel;
    private Panel firstPanel;
    private Panel lastPanel;

    public Panel getLastPanel() {
        return lastPanel;
    }

    public void setLastPanel(Panel lastPanel) {
        this.lastPanel = lastPanel;
    }

    public Panel getFirstPanel() {
        return firstPanel;
    }

    public void setFirstPanel(Panel firstPanel) {
        this.firstPanel = firstPanel;
    }

    public PanelNavigation(Panel currentPanel, List<Panel> panels) {
        if (currentPanel == null) {
            currentPanel = new Panel(1);
        }
        this.currentPanel = currentPanel;
        this.panels = panels;
        for (int i = 0; i < panels.size(); i++) {
            Panel panel = panels.get(i);
            if (i == 0) {
                firstPanel = panel;
            }
            if (i == panels.size() - 1) {
                lastPanel = panel;
            }
            if (panel.equals(currentPanel)) {
                panel.setCurrentPanel(true);
                if (i == 0) {
                    previousPanel = null;
                } else {
                    previousPanel = (Panel) panels.get(i - 1);
                }
                if (i == panels.size() - 1) {
                    nextPanel = null;
                } else {
                    nextPanel = (Panel) panels.get(i + 1);
                }
            }
        }
    }

    public Panel getPreviousPanel() {
        return previousPanel;
    }

    public Panel getNextPanel() {
        return nextPanel;
    }

    public Panel getCurrentPanel() {
        return currentPanel;
    }

    public void setCurrentPanel(Panel currentPanel) {
        this.currentPanel = currentPanel;
    }

    public List getPanels() {
        return panels;
    }

    public void setPanels(List panels) {
        this.panels = panels;
    }
}
