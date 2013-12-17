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

import java.util.Date;

public class Transplant extends RadarModel {

    private Date date;
    private Modality modality;
    private int counter;
    private Boolean recurr;
    private Date dateRecurr, dateRejected, dateBiopsy;
    private RejectData dateFailureRejectData = new RejectData();

    public static class Modality extends BaseModel{
        private Long id;
        private String description;

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public static class RejectData extends BaseModel{
        private Long transplantId;
        private Date rejectedDate;
        private Date biopsyDate;
        private Date failureDate;

        public Long getTransplantId() {
            return transplantId;
        }

        public void setTransplantId(Long transplantId) {
            this.transplantId = transplantId;
        }

        public Date getRejectedDate() {
            return rejectedDate;
        }

        public void setRejectedDate(Date rejectedDate) {
            this.rejectedDate = rejectedDate;
        }

        public Date getBiopsyDate() {
            return biopsyDate;
        }

        public void setBiopsyDate(Date biopsyDate) {
            this.biopsyDate = biopsyDate;
        }

        public Date getFailureDate() {
            return failureDate;
        }

        public void setFailureDate(Date failureDate) {
            this.failureDate = failureDate;
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Modality getModality() {
        return modality;
    }

    public void setModality(Modality modality) {
        this.modality = modality;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public Boolean getRecurr() {
        return recurr;
    }

    public void setRecurr(Boolean recurr) {
        this.recurr = recurr;
    }

    public Date getDateRecurr() {
        return dateRecurr;
    }

    public void setDateRecurr(Date dateRecurr) {
        this.dateRecurr = dateRecurr;
    }

    public RejectData getDateFailureRejectData() {
        return dateFailureRejectData;
    }

    public void setDateFailureRejectData(RejectData dateFailureRejectData) {
        this.dateFailureRejectData = dateFailureRejectData;
    }

    public Date getDateRejected() {
        return dateRejected;
    }

    public void setDateRejected(Date dateRejected) {
        this.dateRejected = dateRejected;
    }

    public Date getDateBiopsy() {
        return dateBiopsy;
    }

    public void setDateBiopsy(Date dateBiopsy) {
        this.dateBiopsy = dateBiopsy;
    }
}
