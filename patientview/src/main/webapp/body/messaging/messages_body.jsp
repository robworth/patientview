<%@ page import="com.worthsoln.utils.LegacySpringUtils" %>
<%@ page import="com.worthsoln.patientview.model.Specialty" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<%
    String actionPrefix = "patient";

    try {
        actionPrefix = request.getParameter("actionPrefix");
    } catch (Exception e) {
        // Birds the word
    }

    Specialty specialty = LegacySpringUtils.getSecurityUserManager().getLoggedInSpecialty();
    String context = specialty != null ? "/" + specialty.getContext() : "";
%>
<div class="row">
    <div class="<%= (actionPrefix.equals("patient") ? "span12" : "span9") %>">
        <div class="page-header">
            <h1>
                Messages
                <button type="button" data-toggle="modal" data-target="#messageModal" class="pull-right btn btn-primary">+ Create Message</button>
            </h1>
        </div>

        <section class="conversation-container">
            <logic:present name="conversations">
                <logic:notEmpty name="conversations">
                    <logic:iterate name="conversations" id="conversation" indexId="index" >
                        <a href="<%=context%>/<%=actionPrefix%>/conversation.do?id=<bean:write name="conversation" property="id" />#response">
                            <article class="conversation">
                                <h2 class="title">
                                    <bean:write name="conversation" property="otherUser.name" />
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

            <div id="messageModal" class="modal hide fade">
                <form action="<%=context%>/send-message.do" class="js-message-form">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h3>New message</h3>
                    </div>
                    <div class="modal-body">
                        <fieldset>
                            <input type="hidden" class="js-message-redirect" value="<%=context%>/patient/conversation.do" />

                            <div class="control-group">
                                <label class="control-label">To</label>
                                <div class="controls">
                                    <select name="recipientId" class="js-message-recipient-id">
                                        <option value="">Select</option>

                                        <logic:notEmpty name="unitAdminRecipients">
                                            <option></option>

                                            <optgroup label="Unit Admins">
                                                <logic:iterate name="unitAdminRecipients" id="recipient" indexId="index">
                                                    <option value="<bean:write name="recipient" property="id" />"><bean:write name="recipient" property="name" /></option>
                                                </logic:iterate>
                                            </optgroup>
                                        </logic:notEmpty>

                                        <logic:notEmpty name="unitStaffRecipients">
                                            <option></option>

                                            <optgroup label="Unit Staff">
                                                <logic:iterate name="unitStaffRecipients" id="recipient" indexId="index">
                                                    <option value="<bean:write name="recipient" property="id" />"><bean:write name="recipient" property="name" /></option>
                                                </logic:iterate>
                                            </optgroup>
                                        </logic:notEmpty>

                                        <logic:notEmpty name="patientRecipients">
                                            <option></option>

                                            <optgroup label="Patients">
                                                <logic:iterate name="patientRecipients" id="recipient" indexId="index">
                                                    <option value="<bean:write name="recipient" property="id" />"><bean:write name="recipient" property="name" /></option>
                                                </logic:iterate>
                                            </optgroup>
                                        </logic:notEmpty>
                                    </select>
                                </div>
                            </div>

                            <div class="control-group" id="passwordContainer">
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
    </div>
</div>

<script src="/js/messages.js" type="text/javascript"></script>