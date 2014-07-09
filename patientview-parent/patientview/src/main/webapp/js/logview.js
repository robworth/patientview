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

$(document).ready(function(){
    // Get current add or minus dayCount param
    function getDateString(dayCount){
        // get current date
        var date = new Date();
        date.setDate(date.getDate() + dayCount);
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        var d = date.getDate();

        var rtnMonth = m > 10 ? m : ("0" + m);
        var rtnDay = d > 10 ? d : ("0" + d);
        return rtnDay + "/" + rtnMonth + "/" + y;
    }

    // Date and Time head click
    $("#dat").click(function(){
        window.location.href="./logView.do?startdate="+$("#startdate").val()+"&enddate="+$("#enddate").val()+"&nhsno="+$("#nhsno").val()+"&user="+$("#user").val()+"&actor="+$("#actor").val()+"&action="+$("#action").val()+"&unitcode="+$("#unitcode").val()+"&order="+$("#order").val();
    });

    // Today button click event
    $("#today").click(function(){
        $("#startdate").val(getDateString(0));
        $("#enddate").val(getDateString(0));
    });

    // Yesterday button click event
    $("#yesterday").click(function(){
        $("#startdate").val(getDateString(-1));
        $("#enddate").val(getDateString(-1));
    });

    // Today and Yesterday button click event
    $("#yesterdayAndToday").click(function(){
        $("#startdate").val(getDateString(-1));
        $("#enddate").val(getDateString(0));
    });

    // 7days button click event
    $("#7days").click(function(){
        $("#startdate").val(getDateString(-7));
        $("#enddate").val(getDateString(0));
    });
});


