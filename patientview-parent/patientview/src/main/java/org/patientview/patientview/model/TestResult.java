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

import org.patientview.patientview.utils.TimestampUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity(name = "testresult")
public class TestResult extends BaseModel {

    @Column(nullable = false)
    private String nhsno;

    @Column(nullable = false)
    private String unitcode;

    @Column(nullable = false, name = "datestamp")
    private Calendar datestamped;

    @Column(nullable = true)
    private String prepost;

    @Column(nullable = false)
    private String testcode;

    @Column(nullable = false)
    private String value;

    private static final int HASH_SEED = 31;

    public TestResult() {
    }

    public TestResult(String nhsno, String unitcode, Calendar datestamp, String testcode, String value) {
        this.nhsno = nhsno;
        setUnitcode(unitcode);
        this.testcode = testcode;
        this.datestamped = datestamp;
        this.value = value;
        this.prepost = "";
    }

    public Calendar getDatestamped() {
        return datestamped;
    }

    public void setDatestamp(Timestamp datestamped) {
        if (datestamped != null && datestamped.getTime() != 0) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(datestamped.getTime());
            this.datestamped = cal;
        }
    }

    public void setTimestamp(Calendar timestamp) {
        this.datestamped = timestamp;
    }

    @Transient
    public Calendar getTimestamp() {
        return datestamped;
    }

    public void setDatestampString(String dateStampString) {
        this.datestamped = TimestampUtils.createTimestamp(dateStampString);
    }

    public String getNhsno() {
        return nhsno;
    }

    public void setNhsno(String nhsno) {
        this.nhsno = nhsno;
    }

    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = (unitcode != null) ? unitcode.toUpperCase() : unitcode;
    }

    public String getPrepost() {
        return prepost;
    }

    public void setPrepost(String prepost) {
        this.prepost = prepost;
    }

    public String getTestcode() {
        return testcode;
    }

    public void setTestcode(String testcode) {
        this.testcode = testcode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Transient
    public String getFormattedDatestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
        if ((datestamped.get(Calendar.HOUR_OF_DAY) == 0) && (datestamped.get(Calendar.MINUTE) == 0)) {
            return dateFormat.format(datestamped.getTime());
        } else {
            return dateTimeFormat.format(datestamped.getTime());
        }
    }

    @Transient
    public String getSortingDatestamp() {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-ddHH:mm");
        return dateTimeFormat.format(datestamped.getTime());
    }

    @Transient
    public String getIsoDatestamp() {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return dateTimeFormat.format(datestamped.getTime());
    }

    @Transient
    public String getIsoDayDatestamp() {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateTimeFormat.format(datestamped.getTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TestResult that = (TestResult) o;
        if (datestamped != null ? !datestamped.equals(that.datestamped) : that.datestamped != null) {
            return false;
        }
        if (nhsno != null ? !nhsno.equals(that.nhsno) : that.nhsno != null) {
            return false;
        }
        if (prepost != null ? !prepost.equals(that.prepost) : that.prepost != null) {
            return false;
        }
        if (testcode != null ? !testcode.equals(that.testcode) : that.testcode != null) {
            return false;
        }
        if (unitcode != null ? !unitcode.equals(that.unitcode) : that.unitcode != null) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result = (nhsno != null ? nhsno.hashCode() : 0);
        result = HASH_SEED * result + (unitcode != null ? unitcode.hashCode() : 0);
        result = HASH_SEED * result + (datestamped != null ? datestamped.hashCode() : 0);
        result = HASH_SEED * result + (prepost != null ? prepost.hashCode() : 0);
        result = HASH_SEED * result + (testcode != null ? testcode.hashCode() : 0);
        result = HASH_SEED * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
