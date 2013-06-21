<%@ page import="org.patientview.patientview.messaging.Messaging" %>
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
%>
<div class="row">
    <div class="<%= (actionPrefix.equals("patient") ? "span12" : "span9") %>">

        <logic:present name="noEmailSet">
            <div class="page-header">
                <h1>
                    Messages
                </h1>
                <br/>
                <div style="color: rgb(0, 136, 204)">
                    IMPORTANT: We are still testing messaging. Do not send urgent information or questions this way yet! Click on messages
                    to see the full conversation. To send a new message, click on "Create Message" above right. When you send a message,
                    the recipient will receive and email asking them to log in to read it, if they have an email address in the system.
                </div>
            </div>

            <section class="conversation-container">
                <div class="alert alert-error">
                    <strong>You don't have an email in PatientView. Please ask an RPV administrator in your unit to add one for you.</strong>
                </div>
            </section>
        </logic:present>

        <logic:present name="noRecipients">
            <div class="page-header">
                <h1>
                    Messages
                </h1>
                <br/>
                <div style="color: rgb(0, 136, 204)">
                    IMPORTANT: We are still testing messaging. Do not send urgent information or questions this way yet! Click on messages
                    to see the full conversation. To send a new message, click on "Create Message" above right. When you send a message,
                    the recipient will receive and email asking them to log in to read it, if they have an email address in the system.
                </div>
            </div>

            <section class="conversation-container">
                <div class="alert alert-error">
                    <strong>You do not currently have any recipients to send messages to.</strong>
                </div>
            </section>
        </logic:present>

        <logic:notPresent name="noEmailSet">
            <logic:notPresent name="noRecipients">
                <div class="page-header">
                    <h1>
                        Messages

                        <logic:notPresent name="isReaderTheRecipient">
                            <button type="button" data-toggle="modal" data-target="#messageModal" class="pull-right btn btn-primary">+ Create Message</button>
                        </logic:notPresent>
                    </h1>
                    <br/>
                    <div style="color: rgb(0, 136, 204)">
                        IMPORTANT: We are still testing messaging. Do not send urgent information or questions this way yet! Click on messages
                        to see the full conversation. To send a new message, click on "Create Message" above right. When you send a message,
                        the recipient will receive and email asking them to log in to read it, if they have an email address in the system.
                    </div>
                </div>

                <section class="conversation-container">
                    <logic:present name="conversations">
                        <logic:notEmpty name="conversations">
                            <logic:iterate name="conversations" id="conversation" indexId="index">
                                <%
                                    boolean even = index % 2 == 0;
                                %>

                                <a href="/<%=actionPrefix%>/conversation.do?conversationId=<bean:write name="conversation" property="id" />#response">
                                    <article class="conversation <%=even ? "" : "odd"%>">
                                        <h2 class="title">
                                            <bean:write name="conversation" property="otherUser.name" />
                                            <logic:greaterThan value="0" name="conversation" property="numberUnread">
                                                <span class="badge badge-important">
                                                    <bean:write name="conversation" property="numberUnread" />
                                                </span>
                                            </logic:greaterThan>
                                            <span class="pull-right conversation-date label label-inverse"><bean:write name="conversation" property="friendlyLatestMessageDate" /></span>
                                            <span class="action-test dull">Click to open conversation</span>
                                        </h2>

                                        <h4 class="user"><bean:write name="conversation" property="subject" /></h4>
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

                    <div id="messageModal" class="modal hide fade">
                        <form action="/send-message.do" class="js-message-form">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h3>New message</h3>
                            </div>
                            <div class="modal-body">
                                <fieldset>
                                    <input type="hidden" class="js-message-redirect" value="/<%=actionPrefix%>/conversation.do" />

                                    <logic:present name="<%=Messaging.UNITS_PARAM%>">
                                        <div class="control-group">
                                            <label class="control-label">Unit</label>

                                            <div class="controls">
                                                <select name="recipientId" class="js-message-unit-code">
                                                    <option value="">Select</option>

                                                    <logic:iterate name="<%=Messaging.UNITS_PARAM%>" id="unit" indexId="index">
                                                        <option value="<bean:write name="unit" property="unitcode" />"><bean:write name="unit" property="name" /></option>
                                                    </logic:iterate>
                                                </select>
                                                <span class="js-message-unit-loading" style="display: none">Finding recipients from unit ...</span>
                                            </div>

                                            <div class="alert alert-error js-message-unit-recipient-errors" style="display: none"></div>
                                        </div>
                                    </logic:present>

                                    <div class="control-group js-recipient-container" <logic:present name="<%=Messaging.UNITS_PARAM%>">style="display: none"</logic:present>>
                                        <label class="control-label">To</label>
                                        <div class="controls">
                                            <select name="recipientId" class="js-message-recipient-id">
                                                <option value="">Select</option>

                                                <logic:notEmpty name="unitAdminRecipients">
                                                    <option></option>

                                                    <!-- display the first recipient's unit shortcode. not sure how they used to get the first item of a list in 80s -->
                                                    <logic:iterate name="unitAdminRecipients" id="recipient" indexId="index">
                                                        <logic:equal name="index" value="0">
                                                            <optgroup label="Unit Admins - <bean:write name="recipient" property="unit.shortname" />">">
                                                        </logic:equal>
                                                    </logic:iterate>

                                                        <logic:iterate name="unitAdminRecipients" id="recipient" indexId="index">
                                                            <option value="<bean:write name="recipient" property="user.id" />"><bean:write name="recipient" property="user.name" /></option>
                                                        </logic:iterate>
                                                    </optgroup>
                                                </logic:notEmpty>

                                                <logic:notEmpty name="unitStaffRecipients">
                                                    <option></option>

                                                    <logic:iterate name="unitStaffRecipients" id="recipient" indexId="index">
                                                        <logic:equal name="index" value="0">
                                                            <optgroup label="Unit Staff - <bean:write name="recipient" property="unit.shortname" />">">
                                                        </logic:equal>
                                                    </logic:iterate>

                                                        <logic:iterate name="unitStaffRecipients" id="recipient" indexId="index">
                                                            <option value="<bean:write name="recipient" property="user.id" />"><bean:write name="recipient" property="user.name" /></option>
                                                        </logic:iterate>
                                                    </optgroup>
                                                </logic:notEmpty>

                                                <logic:notEmpty name="unitPatientRecipients">
                                                    <option></option>

                                                    <logic:iterate name="unitStaffRecipients" id="recipient" indexId="index">
                                                        <logic:equal name="index" value="0">
                                                            <optgroup label="Patients - <bean:write name="recipient" property="unit.shortname" />">">
                                                        </logic:equal>
                                                    </logic:iterate>

                                                        <logic:iterate name="unitPatientRecipients" id="recipient" indexId="index">
                                                            <option value="<bean:write name="recipient" property="user.id" />"><bean:write name="recipient" property="user.name" /></option>
                                                        </logic:iterate>
                                                    </optgroup>
                                                </logic:notEmpty>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label">Subject</label>
                                        <div class="controls">
                                            <input type="text" name="subject" class="js-message-subject" />
                                        </div>
                                    </div>

                                    <div class="control-group">
                                        <label class="control-label">Message</label>
                                        <div class="controls">
                                            <textarea rows="6" cols="3" name="content" class="new-message js-message-content"></textarea>
                                        </div>
                                    </div>

                                    <div class="alert alert-error js-message-errors" style="display: none">
                                        <strong>You do not have any messages.</strong>
                                    </div>
                                </fieldset>
                            </div>
                            <div class="modal-footer">
                                <a href="#" class="btn" data-dismiss="modal">Close</a>
                                <input type="submit" value="Send" class="btn btn-primary  js-message-submit-btn" />
                            </div>
                        </form>
                    </div>
                </section>
            </logic:notPresent>
        </logic:notPresent>
    </div>
</div>

<script src="/js/messages.js" type="text/javascript"></script>
