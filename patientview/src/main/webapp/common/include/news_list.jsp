<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<div class="span4 seperatingBorders pull-right">
        <h3 class="mediumBlueTitle titleSeperator">News</h3>

        <logic:present name="newses">
            <logic:empty name="newses">
                <p>
                    <i>There are currently no news items.</i>
                </p>
            </logic:empty>
            <logic:notEmpty name="newses">
                <ul class="linkList">
                    <logic:iterate id="news" name="newses">
                        <li>
                            <html:link action="/newsView" paramId="id" paramName="news" paramProperty="id" styleClass="readMoreParagraph">
                                &raquo; <bean:write name="news" property="headline" />
                            </html:link>
                        </li>
                    </logic:iterate>
                </ul>
            </logic:notEmpty>

        </logic:present>
    </div>