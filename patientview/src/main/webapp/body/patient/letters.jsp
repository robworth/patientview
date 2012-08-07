<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<div class="row">
    <div class="span12">

    <div class="page-header">
        <h1>Letters</h1>
    </div>


    <p><bean:message key="cautionary.letters" /></p>
  

  <logic:empty name="letters">
      <div class="alert">>No letters uploaded</div>
  </logic:empty>

  <logic:notEmpty name="letters">

    <logic:present name="user">

    <h2>Letters for <bean:write name="user" property="name"/></h2>

<table width="90%" border="0" cellspacing="1" cellpadding="3" class="table table-bordered table-striped">
      <thead>
          <tr>
            <th>Date</th>
            <th>Letter Type</th>
            <th>&nbsp;</th>
          </tr>
      </thead>

      <logic:iterate name="letters" id="letter">
        <tr>
           <td class="tablecell"><bean:write name="letter" property="formattedDate"/></td>
          <td class="tablecell"><bean:write name="letter" property="type"/></td>
          <td class="tablecell"><html:link action="/patient/letterDetail" paramName="letter" paramProperty="id" paramId="letterId">read letter...</html:link>&nbsp;</td>
        </tr>
      </logic:iterate>

    </logic:present>

  </logic:notEmpty>

</table>
        
    </div>
</div>