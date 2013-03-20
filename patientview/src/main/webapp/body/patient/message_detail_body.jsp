<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<logic:notPresent name="conversation">
    <div class="alert alert-error">
        <strong>Conversation not found.</strong>
    </div>
</logic:notPresent>
<logic:present name="conversation">
    <div class="page-header">
        <div>
            <html:link action="/patient/conversations" styleClass="btn">< back to Messages</html:link>
        </div>

        <h1>
            <br />
            <bean:write name="conversation" property="otherUser.name" />
        </h1>
    </div>

    <section class="js-messages">
        <logic:present name="messages">
            <logic:notEmpty name="messages">
                <logic:iterate name="messages" id="message" indexId="index">
                    <article class="message" id="message-<bean:write name="message" property="id" />">
                        <h4 class="author"><bean:write name="message" property="sender.name" /> <span class="label label-inverse pull-right date"><bean:write name="message" property="friendlyDate" /></span></h4>

                        <div class="content dull">
                            <bean:write name="message" property="content" />
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

    <section class="new-message-container">
        <html:form action="/patient/send-message" styleClass="js-message-form">
            <html:hidden property="recipientId" styleClass="js-message-recipient-id" />
            <html:textarea rows="6" cols="3" property="content" styleClass="span12 new-message js-message-content" />
            <div class="alert alert-error js-message-errors" style="display: none">
                <strong>You do not have any messages.</strong>
            </div>
            <html:submit value="Reply" styleClass="pull-right btn btn-primary js-message-submit-btn" />
        </html:form>
    </section>
</logic:present>

<script src="/js/messages.js" type="text/javascript"></script>


