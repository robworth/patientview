<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<div class="page-header">
    <h1>Messages <input type="submit" id="create-conversation" class="pull-right btn btn-primary" value="+ Create Message"/></h1>

</div>

<section class="conversation-container">
    <a href="/message.jsp">
        <article class="conversation">
            <h2 class="title">This is our conversation title <span class="pull-right conversation-date label label-inverse">Friday</span></h2>
            <h4 class="user">David Roberts </h4>
            <div class="content dull">
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec semper sodales leo, quis dictum justo ultricies at. Quisque congue diam id dui suscipit faucibus.
            </div>
        </article>
    </a>
    <article class="conversation">
        <h2 class="title">This is our conversation title 2 <span class="badge badge-important">6</span><span class="pull-right conversation-date label label-inverse">Feb 27</span></h2>
        <h4 class="user">Andrew Moffatt </h4>
        <div class="content dull">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec semper sodales leo, quis dictum justo ultricies at. Quisque congue diam id dui suscipit faucibus.
        </div>
    </article>
    <article class="conversation">
        <h2 class="title">This is our conversation title 3 <span class="badge badge-important">2</span> <span class="pull-right converation-date label label-inverse">Feb 12</span></h2>
        <h4 class="user">Paul Chenery </h4>
        <div class="content dull">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec semper sodales leo, quis dictum justo ultricies at. Quisque congue diam id dui suscipit faucibus.
        </div>
    </article>
</section>