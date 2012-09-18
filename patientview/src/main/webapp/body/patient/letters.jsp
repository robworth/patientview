<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

    <div class="page-header">
        <h1>Letters</h1>
    </div>

    <ul class="padded-list">
        <li>
            Letters are only shown where they can be retrieved from your unit's computer system.
        </li>
        <li>
            It is important to note that letters may be shown here before they have been finally approved and had
            mistakes corrected. This mainly applies if you are reading letters soon (e.g. within a week) after they have
            been typed.
        </li>
        <li>
            If you have any concerns you should discuss it at your next clinic appointment, or contact the author of the
            letter.                
        </li>
    </ul>
    <h3>Links about Letters</h3>
    <ul>
        <li><a href="http://www.myibdportal.org/medical-terms-used-in-ibd">Glossary of terms used in IBD</a></li>
    </ul>

  <logic:empty name="letters">
      <div class="alert">No letters uploaded</div>
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
        