<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<div class="span7" id="graphContainer">
    <form action="#" class="form-inline" id="graphForm">
        <input type="hidden" name="graphType" id="graphType" value="1" />

        <label class="control-label" for="graphDataFromDate">From:</label>
        <div class="input-append date datePicker" data-date="<bean:write name="fromDate" />">
            <input name="fromDate" id="graphDataFromDate" class="fromDate span2" size="16" type="text" value="<bean:write name="fromDate" />" readonly>
            <span class="add-on"><i class="icon-th"></i></span>
        </div>

        <label class="control-label" for="graphDataToDate">To:</label>
        <div class="input-append date datePicker" data-date="<bean:write name="toDate" />">
            <input name="toDate" id="graphDataToDate" class="toDate span2" size="16" type="text" value="<bean:write name="toDate" />" readonly>
            <span class="add-on"><i class="icon-th"></i></span>
        </div>

        <input type="submit" class="btn btn-primary" value="Update" />
        <button id="clearData" class="btn" value="Clear">Clear</button>
    </form>

    <div id="graph" class="linegraph"></div>
</div>