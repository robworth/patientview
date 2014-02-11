<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>


<%--
  ~ PatientView
  ~
  ~ Copyright (c) Worth Solutions Limited 2004-2013
  ~
  ~ This file is part of PatientView.
  ~
  ~ PatientView is free software: you can redistribute it and/or modify it under the terms of the
  ~ GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
  ~ or (at your option) any later version.
  ~ PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
  ~ the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
  ~ GNU General Public License for more details.
  ~ You should have received a copy of the GNU General Public License along with PatientView in a file
  ~ titled COPYING. If not, see <http://www.gnu.org/licenses/>.
  ~
  ~ @package PatientView
  ~ @link http://www.patientview.org
  ~ @author PatientView <info@patientview.org>
  ~ @copyright Copyright (c) 2004-2013, Worth Solutions Limited
  ~ @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
  --%>

<html:xhtml/>

<div class="page-header">
    <h1>Results</h1>
</div>
<div>
    <ul class="nav nav-tabs">
        <li class="active"><a href="#">Graphic</a></li>
        <li><html:link action="/patient/results">Tabular</html:link></li>
    </ul>
</div>

<h3>Select test result to graph</h3>
<div class="btn-toolbar">
    <div class="btn-group">
        <button class="btn btn-danger dropdown-toggle" data-toggle="dropdown"><span id="heading1"><bean:write name="resultTypeHeading" property="heading"/></span><span id="d1" class="caret"></span></button>
        <ul class="dropdown dropdown-menu">
            <li><a id="btn_1_none" href="#" onclick='changeChart(this, "","")'>&nbsp;</a></li>
            <logic:iterate name="resultsHeadings" id="heading" type="org.patientview.patientview.model.ResultHeading" >
                <li><a id="btn_1" href="#" onclick='changeChart(this, "<bean:write name="heading" property="headingcode"/>","<bean:write name="heading" property="heading"/>")'><bean:write name="heading" property="heading"/> </a></li>
            </logic:iterate>
        </ul>
    </div>

</div>

<div class="alert alert-error" id="errorMsg" style="display: none">Test result doesn't exist.</div>

<input type="hidden" name="resultCode1" value="<bean:write name='resultTypeHeading' property="headingcode"/>" id="result_Type1"/>
<input type="hidden" name="resultCode2" value="" id="result_Type2"/>
<input type="hidden" name="period" value="<bean:write name='period'/>" id="period"/>

<div id="chart_div" style="height:500px;" ></div>
<div id="time_period">
    <div><h3>Change time period </h3></div>
    <div class="btn-group" style="margin-top: 5px;" id="button_group">
        <button type="button" class="btn btn-default" id="1_Month" onclick="changePeriod(this,1)">1M</button>
        <button type="button" class="btn btn-default" id="3_Months" onclick="changePeriod(this,3)">3M</button>
        <button type="button" class="btn btn-default" id="6_Months" onclick="changePeriod(this,6)">6M</button>
        <button type="button" class="btn btn-default" id="1_Year" onclick="changePeriod(this,12)">1Y</button>
        <button type="button" class="btn btn-default" id="3_Year" onclick="changePeriod(this,36)">3Y</button>
        <button type="button" class="btn btn-default" id="5_Year" onclick="changePeriod(this,60)">5Y</button>
        <button type="button" class="btn btn-default" id="ALL" onclick="changePeriod(this,0)">All</button>
    </div>
</div>



<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script src="/js/testresults.js" type="text/javascript"></script>