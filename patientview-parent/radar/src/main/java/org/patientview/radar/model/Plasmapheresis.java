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

package org.patientview.radar.model;

import org.patientview.radar.model.enums.RemissionAchieved;

import java.util.Date;

public class Plasmapheresis extends RadarModel {

    private Date startDate, endDate;
    private PlasmapheresisExchangeUnit plasmapheresisExchanges;
    private RemissionAchieved response;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public PlasmapheresisExchangeUnit getPlasmapheresisExchanges() {
        return plasmapheresisExchanges;
    }

    public void setPlasmapheresisExchanges(PlasmapheresisExchangeUnit plasmapheresisExchanges) {
        this.plasmapheresisExchanges = plasmapheresisExchanges;
    }

    public RemissionAchieved getResponse() {
        return response;
    }

    public void setResponse(RemissionAchieved response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Plasmapheresis{" +
                "id=" + getId() +
                ", radarNumber=" + getRadarNumber() +
                '}';
    }
}
