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

google.load("visualization", "1", {packages:["corechart"]});
google.setOnLoadCallback(drawChart);
var chart;

function drawChart() {
    $.ajax({
        url: '/patient/graphic_testresult.do?resultType1=' + $('#result_Type1').val() + "&period=" + $('#period').val(),
        dataType:"json",
        async: false,
        success: function(resultData){
            if (resultData == null || resultData == "") {
                $('#errorMsg').show();
                return;
            } else {
                $('#errorMsg').hide();
            }

            var arrColors = ['red','blue'];
            if ($('#heading1').text() == "") {
                arrColors = ['blue','red'];
            }

            var data = new google.visualization.DataTable(resultData);
            var dataView = new google.visualization.DataView(data);

            var boolScale = true;
            if (dataView.getViewRows().length == 1) {
                boolScale = false;
            }

            var options = {
                title: 'TestResults',
                colors: arrColors,
                tooltip: { isHtml: true, trigger: 'selection' },
                vAxis: { logScale: boolScale },
                interpolateNulls: true
            };
            chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
            chart.draw(dataView, options);

            // Add mouse over handlers.
            google.visualization.events.addListener(chart, 'onmouseover', mouseOver);
        }
    });
}

function mouseOver(e) {
    chart.setSelection([e]);
}

function changeChart(obj, resultCode, resultHeading) {
    if (obj.id == "btn_1" || obj.id == "btn_1_none") {
        $('#result_Type1').val(resultCode);
        $('#heading1').text(resultHeading);
    }

    if (obj.id == "btn_2" || obj.id == "btn_2_none") {
        $('#result_Type2').val(resultCode);
        $('#heading2').text(resultHeading);
    }
    $('#chart_div').empty();
    drawChart();
}

function changePeriod(obj, value) {
    $('#period').val(value);
    $("#button_group").children().each(function(i,n){
        if(n.disabled == true){
            n.disabled = false;
        }
    });
    $(obj).attr("disabled", true);
    $('#chart_div').empty();
    drawChart();
}

