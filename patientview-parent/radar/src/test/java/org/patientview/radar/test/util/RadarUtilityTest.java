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

package org.patientview.radar.test.util;


import org.junit.Assert;
import org.junit.Test;
import org.patientview.util.CommonUtils;

import java.util.Calendar;
import java.util.Date;

public class RadarUtilityTest {

    /**
     * Test the date formatter functionality
     *
     *
     */
    @Test
    public void testParseTest(){
        final String testDateString1 = "08.02.94";
        final String testDateString2 = "28-11-95";
        final String testDateString3 = "1964-05-12";

        Date date = CommonUtils.parseDate(testDateString1);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Assert.assertTrue("The year for " + testDateString1 + " should 1994", calendar.get(Calendar.YEAR) == 1994);
        Assert.assertTrue("The month for " + testDateString1 + " should 2", calendar.get(Calendar.MONTH) == 1);
        Assert.assertTrue("The day of the month for " + testDateString1 + " should 2", calendar.get(Calendar.DAY_OF_MONTH) == 8);

        date = CommonUtils.parseDate(testDateString2);

        calendar.setTime(date);
        Assert.assertTrue("The year for " + testDateString2 + " should 1995", calendar.get(Calendar.YEAR) == 1995);
        Assert.assertTrue("The month for " + testDateString2 + " should 11", calendar.get(Calendar.MONTH) == 10);
        Assert.assertTrue("The day of the month for " + testDateString2 + " should 28", calendar.get(Calendar.DAY_OF_MONTH) == 28);

        date = CommonUtils.parseDate(testDateString3);

        calendar.setTime(date);
        Assert.assertTrue("The year for " + testDateString3 + " should 1964", calendar.get(Calendar.YEAR) == 1964);
        Assert.assertTrue("The month for " + testDateString3 + " should 5", calendar.get(Calendar.MONTH) == 4);
        Assert.assertTrue("The day of the month for " + testDateString3 + " should 12", calendar.get(Calendar.DAY_OF_MONTH) == 12);

    }


}
