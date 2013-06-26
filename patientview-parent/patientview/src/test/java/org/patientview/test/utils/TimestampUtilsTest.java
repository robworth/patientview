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

package org.patientview.test.utils;

import org.patientview.patientview.utils.TimestampUtils;
import org.joda.time.DateTime;
import org.joda.time.IllegalFieldValueException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(JUnit4.class)
public class TimestampUtilsTest {

    @Test
    public void testCreateTimestampGoodDay() {
        assertEquals("Timestamp not equal", "2012-03-15T00:00:00.000Z", new DateTime((TimestampUtils
                .createTimestamp("2012-03-15")).getTime().getTime()).toString());
    }

    @Test
    public void testCreateTimestampGoodDayHourMin() {
        assertEquals("Timestamp not equal", "2012-03-15T04:34:00.000Z", new DateTime((TimestampUtils
                .createTimestamp("2012-03-15T04:34")).getTime().getTime()).toString());
    }

    @Test
    public void testCreateTimestampGoodDayHourMinSec() {
        assertEquals("Timestamp not equal", "2012-03-15T14:56:45.000Z", new DateTime((TimestampUtils
                .createTimestamp("2012-03-15T14:56:45")).getTime().getTime()).toString());
    }

    @Test
    public void testCreateTimestampGoodDaySlash() {
        assertEquals("Timestamp not equal", "2011-05-22T00:00:00.000+01:00", new DateTime((TimestampUtils
                .createTimestamp("22/05/2011")).getTime().getTime()).toString());
    }

    @Test
    public void testCreateTimestampGoodDaySlashBackwards() {
        assertEquals("Timestamp not equal", "2011-05-22T00:00:00.000+01:00", new DateTime((TimestampUtils
                .createTimestamp("2011/05/22")).getTime().getTime()).toString());
    }

    @Test
    public void testCreateTimestampGoodDayDash() {
        assertEquals("Timestamp not equal", "2011-05-22T00:00:00.000+01:00", new DateTime((TimestampUtils
                .createTimestamp("22-05-2011")).getTime().getTime()).toString());
    }

    @Test
    public void testCreateTimestampBadDayOfMonth() {
        assertNull(TimestampUtils.createTimestamp("2012-02-32"));
    }

    @Test
    public void testCreateTimestampBadMonth() {
        assertNull(TimestampUtils.createTimestamp("2012-13-15"));
    }

    @Test
    public void testCreateTimestampBadDate() throws IllegalFieldValueException {
        assertNull(TimestampUtils.createTimestamp("asdasdasasd"));
    }

    @Test
    public void testCreateTimestampStartDay() {
        assertEquals("Timestamp not equal", "2012-03-15T00:00:00.000Z", new DateTime((TimestampUtils
                .createTimestampStartDay("2012-03-15")).getTime().getTime()).toString());
    }

    @Test
    public void testCreateTimestampStartDayWithHoursEtc() {
        assertEquals("Timestamp not equal", "2012-03-15T00:00:00.000Z", new DateTime((TimestampUtils
                .createTimestampStartDay("2012-03-15T12:43:54.878")).getTime().getTime()).toString());
    }

    @Test
    public void testCreateTimestampEndDay() {
        assertEquals("Timestamp not equal", "2012-03-15T23:59:59.999Z", new DateTime((TimestampUtils
                .createTimestampEndDay("2012-03-15")).getTime().getTime()).toString());
    }

    @Test
    public void testCreateTimestampEndDayWithHoursEtc() {
        assertEquals("Timestamp not equal", "2012-03-15T23:59:59.999Z", new DateTime((TimestampUtils
                .createTimestampEndDay("2012-03-15T12:43:54.878")).getTime().getTime()).toString());
    }

}
