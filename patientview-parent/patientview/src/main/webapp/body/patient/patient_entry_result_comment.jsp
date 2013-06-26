<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/datetime-1.0" prefix="dt" %>

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
    <h1>Enter My Comments</h1>
</div>


<p>Use this page to enter comments. They will appear alongside your results. These comments will not be automatically sent to anyone at your renal unit. If you need advice, you must contact them in the usual way.</p>
<div class="alert alert-info">Currently comments are limited to 100 characters.</div>


<html:errors/>



  <table cellpadding="3" class="table table-bordered table-striped">

    <tr>
        <td class="tableheader" align="center">Date</td>
        <td class="tableheader" align="center">Time</td>
        <td class="tableheader" align="center">Comment</td>
        <td></td>
    </tr>

    <logic:present name="resultcomment" scope="session">
        <logic:iterate name="resultcomment" id="comment">
             <tr>
                 <td class="tablecell" align="center"><bean:write name="comment" property="value.stringDate" /></td>
                 <td class="tablecell" align="center"><bean:write name="comment" property="value.stringTime" /></td>
                 <td class="tablecell" align="center"><bean:write name="comment" property="value.value1" /></td>
                 <html:form action="/patient/patientDeletesResultComment">
                     <input type="hidden" name="patientResultKey" value='<bean:write name="comment" property="key" />' />
                     <input type="hidden" name="patientResultName" value="resultcomment" />
                  <td align="center" valign="center"><html:submit value="Delete" styleClass="btn" /></td>
                 </html:form>
             </tr>
        </logic:iterate>
    </logic:present>

<html:form action="/patient/patientAddsResultComment">

    <tr>
        <td class="tablecell" align="center" >
            <select name="day" class="input-mini">
                <option><dt:format pattern="d"><dt:currentTime/></dt:format></option>
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
                <option>5</option>
                <option>6</option>
                <option>7</option>
                <option>8</option>
                <option>9</option>
                <option>10</option>
                <option>11</option>
                <option>12</option>
                <option>13</option>
                <option>14</option>
                <option>15</option>
                <option>16</option>
                <option>17</option>
                <option>18</option>
                <option>19</option>
                <option>20</option>
                <option>21</option>
                <option>22</option>
                <option>23</option>
                <option>24</option>
                <option>25</option>
                <option>26</option>
                <option>27</option>
                <option>28</option>
                <option>29</option>
                <option>30</option>
                <option>31</option>
            </select>
            -
            <html:select property="month" styleClass="input-mini">
                <option value="<dt:format pattern="M"><dt:currentTime/></dt:format>"><dt:format pattern="MMM"><dt:currentTime/></dt:format></option>
                <html:option value="1">Jan</html:option>
                <html:option value="2">Feb</html:option>
                <html:option value="3">Mar</html:option>
                <html:option value="4">Apr</html:option>
                <html:option value="5">May</html:option>
                <html:option value="6">Jun</html:option>
                <html:option value="7">Jul</html:option>
                <html:option value="8">Aug</html:option>
                <html:option value="9">Sep</html:option>
                <html:option value="10">Oct</html:option>
                <html:option value="11">Nov</html:option>
                <html:option value="12">Dec</html:option>
            </html:select>
            -
            <html:select property="year" styleClass="input-mini">
                <option value="<dt:format pattern="yyyy"><dt:currentTime/></dt:format>"><dt:format pattern="yyyy"><dt:currentTime/></dt:format></option>
                <html:option value="2012">2012</html:option>
                <html:option value="2011">2011</html:option>
                <html:option value="2010">2010</html:option>
                <html:option value="2009">2009</html:option>
            </html:select>
        </td>
        <td class="tablecell" align="center">
            <html:select property="hour" styleClass="input-mini">
                <option value="<dt:format pattern="H"><dt:currentTime/></dt:format>"><dt:format pattern="HH"><dt:currentTime/></dt:format></option>
                <html:option value="0">00</html:option>
                <html:option value="1">01</html:option>
                <html:option value="2">02</html:option>
                <html:option value="3">03</html:option>
                <html:option value="4">04</html:option>
                <html:option value="5">05</html:option>
                <html:option value="6">06</html:option>
                <html:option value="7">07</html:option>
                <html:option value="8">08</html:option>
                <html:option value="9">09</html:option>
                <html:option value="10">10</html:option>
                <html:option value="11">11</html:option>
                <html:option value="12">12</html:option>
                <html:option value="13">13</html:option>
                <html:option value="14">14</html:option>
                <html:option value="15">15</html:option>
                <html:option value="16">16</html:option>
                <html:option value="17">17</html:option>
                <html:option value="18">18</html:option>
                <html:option value="19">19</html:option>
                <html:option value="20">20</html:option>
                <html:option value="21">21</html:option>
                <html:option value="22">22</html:option>
                <html:option value="23">23</html:option>
            </html:select>
            :
            <html:select property="minute" styleClass="input-mini">
                <option value="<dt:format pattern="m"><dt:currentTime/></dt:format>"><dt:format pattern="mm"><dt:currentTime/></dt:format></option>
                <html:option value="0">00</html:option>
                <html:option value="10">10</html:option>
                <html:option value="20">20</html:option>
                <html:option value="30">30</html:option>
                <html:option value="40">40</html:option>
                <html:option value="50">50</html:option>
            </html:select>
        </td>
        <td class="tablecell" align="center">
            <html:hidden property="patientResultName" value="resultcomment"/>
            <html:hidden property="patientResultCode1" value="resultcomment"/>
            <html:textarea property="patientResultValue1" />
        </td>
      <td align="center" colspan="4"><html:submit value="Add" styleClass="btn"/></td>
    </tr>
</html:form>
</table>

    <logic:present name="resultcomment" scope="session">
      <logic:notEmpty name="resultcomment" scope="session">

          <div class="alert alert-block">By pressing the <strong>Submit All</strong> button you will add these comments to your record. <strong>After clicking, you will not be able to make any more changes.</strong> Use the Delete and Add buttons above to ensure that you are happy before clicking the Submit All button.</div>

          <html:form action="/patient/patientSubmitsResultComments">
              <html:submit value="Submit All" styleClass="btn"/>
              <input type="hidden" name="patientResultName" value="resultcomment" />
          </html:form>
      </logic:notEmpty>
    </logic:present>




