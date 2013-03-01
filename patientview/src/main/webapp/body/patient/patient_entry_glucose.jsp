<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/datetime-1.0" prefix="dt" %>

<html:xhtml/>
<div class="row">
    <div class="span12">    

<div class="page-header">
    <h1>Enter My Glucose</h1>
</div>


<p>Use this page to enter values from home or from your GP surgery, for example. Important: these results will not be automatically sent to anyone at your renal unit. If you need advice, you must contact them in the usual way.</p>


<html:errors/>



  <table cellpadding="3" class="table table-bordered table-striped">

    <tr>
        <th class="tableheader" align="center">Date</th>
        <th class="tableheader" align="center">Time</th>
        <th class="tableheader" align="center">Glucose</th>
        <th></th>
    </tr>

    <logic:present name="glucose" scope="session">
        <logic:iterate name="glucose" id="gluc">
             <tr>
                 <td class="tablecell" align="center"><bean:write name="gluc" property="value.stringDate" /></td>
                 <td class="tablecell" align="center"><bean:write name="gluc" property="value.stringTime" /></td>
                 <td class="tablecell" align="center"><bean:write name="gluc" property="value.value1" /></td>
                 <html:form action="/patient/patientDeletesGlucose">
                     <input type="hidden" name="patientResultKey" value='<bean:write name="gluc" property="key" />' />
                     <input type="hidden" name="patientResultName" value="glucose" />
                   <td align="center" valign="center"><html:submit value="Delete" styleClass="btn" /></td>
                 </html:form>
             </tr>
        </logic:iterate>
    </logic:present>

<html:form action="/patient/patientAddsGlucose">

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
            <html:select property="year"  styleClass="input-mini">
                <option value="<dt:format pattern="yyyy"><dt:currentTime/></dt:format>"><dt:format pattern="yyyy"><dt:currentTime/></dt:format></option>
                <html:option value="2009">2009</html:option>
                <html:option value="2010">2010</html:option>
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
            <html:hidden property="patientResultName" value="glucose"/>
            <html:hidden property="patientResultCode1" value="glucose"/>
            <html:text property="patientResultValue1" size="3"/>
        </td>
      <td align="center"><html:submit value="Add" styleClass="btn"/></td>
    </tr>
</html:form>
</table>

    <logic:present name="glucose" scope="session">
      <logic:notEmpty name="glucose" scope="session">
          <div class="alert alert-block">By pressing the <strong>Submit All</strong> button you will add these glucose values to your record. <strong>After clicking, you will not be able to make any more changes.</strong> Use the Delete and Add buttons above to ensure that you are happy before clicking the Submit All button.</div>
          <html:form action="/patient/patientSubmitsGlucoses">
              <html:submit value="Submit All" styleClass="btn"/>
              <input type="hidden" name="patientResultName" value="glucose" />
          </html:form>
      </logic:notEmpty>
    </logic:present>


    </div>
</div>

