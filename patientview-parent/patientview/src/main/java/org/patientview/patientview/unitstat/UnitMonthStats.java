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

package org.patientview.patientview.unitstat;

import org.patientview.patientview.model.UnitStat;

import java.util.HashMap;
import java.util.Map;

public class UnitMonthStats {

    private String unitcode;
    private String yearmonth;
    private String downloadFilename;
    private Map<String, String> stats = new HashMap<String, String>();

    private static final int HASH_SEED = 31;

    public UnitMonthStats(String unitcode, String yearmonth) {
        this.unitcode = unitcode;
        this.yearmonth = yearmonth;
    }

    public UnitMonthStats(UnitStat unitStat) {
        this.unitcode = unitStat.getUnitcode();
        this.yearmonth = unitStat.getYearmonth();
    }

    public String getUnitcode() {
        return unitcode;
    }

    public String getYearmonth() {
        return yearmonth;
    }

    public String getDownloadFilename() {
        return downloadFilename;
    }

    public void setDownloadFilename(String downloadFilename) {
        this.downloadFilename = downloadFilename;
    }

    public void addStat(String action, String value) {
        stats.put(action, value);
    }

    public String getValue(String action) {
        Object value = stats.get(action);
        if (value == null) {
            return "";
        } else {
            return (String) value;
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UnitMonthStats that = (UnitMonthStats) o;
        if (stats != null ? !stats.equals(that.stats) : that.stats != null) {
            return false;
        }
        if (unitcode != null ? !unitcode.equals(that.unitcode) : that.unitcode != null) {
            return false;
        }
        if (yearmonth != null ? !yearmonth.equals(that.yearmonth) : that.yearmonth != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int result = unitcode != null ? unitcode.hashCode() : 0;
        result = HASH_SEED * result + (yearmonth != null ? yearmonth.hashCode() : 0);
        result = HASH_SEED * result + (stats != null ? stats.hashCode() : 0);
        return result;
    }
}
