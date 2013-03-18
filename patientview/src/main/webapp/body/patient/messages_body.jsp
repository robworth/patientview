<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<div class="page-header">
    <h1>Messages <input type="submit" id="create-conversation" class="pull-right btn btn-primary" value="+ Create Message"/></h1>
</div>

<section class="conversation-container">
    <logic:present name="conversations">
        <logic:notEmpty name="conversations">
            <logic:iterate name="conversations" id="conversation" indexId="index">
                <a href="/patient/message.jsp">
                    <article class="conversation">
                        <h2 class="title">
                            <bean:write name="conversation" property="userBasedOnContext.name" />
                            <logic:greaterThan value="0" name="conversation" property="numberUnread">
                                <span class="badge badge-important">
                                    <bean:write name="conversation" property="numberUnread" />
                                </span>
                            </logic:greaterThan>
                            <span class="pull-right conversation-date label label-inverse"><bean:write name="conversation" property="latestMessageDate" /></span>
                        </h2>
                        <div class="content dull">
                            <bean:write name="conversation" property="latestMessageSummary" />
                        </div>
                    </article>
                </a>
            </logic:iterate>
        </logic:notEmpty>
        <logic:empty name="conversations">
            <div class="alert">
                <strong>You do not have any messages.</strong>
            </div>
        </logic:empty>
    </logic:present>


    <%--<article class="conversation">--%>
        <%--<h2 class="title">This is our conversation title 2 <span class="badge badge-important">6</span><span class="pull-right conversation-date label label-inverse">Feb 27</span></h2>--%>
        <%--<h4 class="user">Andrew Moffatt </h4>--%>
        <%--<div class="content dull">--%>
            <%--Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec semper sodales leo, quis dictum justo ultricies at. Quisque congue diam id dui suscipit faucibus.--%>
        <%--</div>--%>
    <%--</article>--%>
    <%--<article class="conversation">--%>
        <%--<h2 class="title">This is our conversation title 3 <span class="badge badge-important">2</span> <span class="pull-right converation-date label label-inverse">Feb 12</span></h2>--%>
        <%--<h4 class="user">Paul Chenery </h4>--%>
        <%--<div class="content dull">--%>
            <%--Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec semper sodales leo, quis dictum justo ultricies at. Quisque congue diam id dui suscipit faucibus.--%>
        <%--</div>--%>
    <%--</article>--%>
</section>