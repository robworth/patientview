<%@ page import="org.patientview.utils.LegacySpringUtils" %>
<%@ page import="org.patientview.patientview.model.Specialty" %>
<%@ page import="org.patientview.patientview.model.User" %>
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

<%
    String actionPrefix = "patient";

    try {
        actionPrefix = request.getParameter("actionPrefix");
    } catch (Exception e) {
        // Birds the word
    }

    User user = LegacySpringUtils.getUserManager().getLoggedInUser();
%>

<div class="row">
    <div class="<%= (actionPrefix.equals("patient") ? "span12" : "span9") %>">
        <logic:notPresent name="conversation">
            <div class="alert alert-error">
                <strong>Conversation not found.</strong>
            </div>
        </logic:notPresent>
        <logic:present name="conversation">
            <div class="page-header">
                <div>
                    <a href="/<%=actionPrefix%>/conversations.do" class="btn">< back to Messages</a>
                </div>

                <h1>
                    <br />
                    <bean:write name="conversation" property="subject" />
                </h1>

                <h4 class="author">
                    <bean:write name="conversation" property="otherUser.name" />
                </h4>
            </div>

            <section class="js-messages">
                <logic:present name="messages">
                    <logic:notEmpty name="messages">
                        <logic:iterate name="messages" id="message" indexId="index" type="org.patientview.patientview.model.Message">
                            <article class="message" id="message-<bean:write name="message" property="id" />">
                                <h4 class="author">
                                    <bean:write name="message" property="sender.name" />

                                    <%
                                    // check to see if they are the recipient of this message and if they have seen before
                                    if (message.getType() == null && message.getRecipient().equals(user)) {
                                        if (!message.isHasRead()) {
                                        %>
                                        <span class="badge badge-important">
                                            New
                                        </span>
                                        <%
                                        }
                                    }
                                    %>

                                    <span class="label label-inverse pull-right date"><bean:write name="message" property="friendlyDate" /></span>
                                </h4>

                                <div class="content dull">
                                    <bean:write name="message" property="formattedContent" filter="false"/>
                                </div>
                            </article>
                        </logic:iterate>
                    </logic:notEmpty>
                    <logic:empty name="messages">
                        <div class="alert">
                            <strong>You do not have any messages.</strong>
                        </div>
                    </logic:empty>
                </logic:present>
            </section>

            <logic:notPresent name="isReaderTheRecipient">

                <section class="new-message-container" id="response">
                    <form action="/send-message.do" class="js-message-form">
                        <input type="hidden" class="js-message-redirect" value="/patient/conversation.do" />
                        <input type="hidden" class="js-message-conversation-id" value="<bean:write name="conversation" property="id" />" />
                        <logic:present name="isBulkMessage">
                            <div class="alert">
                            <strong>This message was sent to <bean:write name="bulk_message_recipient"/> in <bean:write name="recipient_unit"/>. It is not possible to reply to it.</strong>
                        </div>
                        </logic:present>
                        <logic:notPresent name="isBulkMessage">
                            <textarea rows="6" cols="3" name="content" class="<%= (actionPrefix.equals("patient") ? "span12" : "span9") %> new-message js-message-content"></textarea>
                        </logic:notPresent>
                        <div class="alert alert-error js-message-errors" style="display: none">
                            <strong>You do not have any messages.</strong>
                        </div>
                        <logic:notPresent name="isBulkMessage">
                            <input type="submit" value="Reply" class="pull-right btn btn-primary js-message-submit-btn" />
                        </logic:notPresent>
                    </form>
                </section>

            </logic:notPresent>

        </logic:present>
    </div>
</div>

<script src="/js/messages.js" type="text/javascript"></script>



