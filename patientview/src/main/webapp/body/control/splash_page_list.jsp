<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>
<div class="span9">
<div class="page-header">
    <h1>Splash Pages</h1>
</div>

<html:errors />


<table cellpadding="3" class="table table-bordered table-striped">


    <logic:notEmpty name="splashpages">

        <tr class="tableheader">
            <td class="tableheader">Name</td>
            <td class="tableheader">Headline</td>
            <td class="tableheader">Unit</td>
            <td class="tableheader">Live</td>
            <td></td>
        </tr>

        <logic:iterate id="splashpage" name="splashpages" >
            <tr>

                <td class="tablecell">
                    <html:link action="/control/splashPageEdit" paramId="id" paramName="splashpage" paramProperty="id">
                        <bean:write name="splashpage" property="name" />
                    </html:link>
                </td>

                <td class="tablecell"><bean:write name="splashpage" property="headline" /></td>

                <td class="tablecell"><bean:write name="splashpage" property="unitcode" /></td>

                <td class="tablecell" align="center">
                    <logic:equal    value="true" name="splashpage" property="live"><font color="green">&#10004;</font></logic:equal>
                    <logic:notEqual value="true" name="splashpage" property="live"><font color="red">&#10008;</font></logic:notEqual>
                </td>



                <td class="tablecell" align="center" valign="center">
                    <html:form action="/control/splashPageDelete">
                        <html:hidden name="splashpage" property="id" />
                        <html:submit value="Delete" styleClass="btn" />
                    </html:form>
                </td>


            </tr>
        </logic:iterate>

    </logic:notEmpty>
</table>


<html:form action="/control/splashPageAddInput">
    <html:submit value="Add New" styleClass="btn" />
</html:form>



</div>
</div>

