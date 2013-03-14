<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<div class="page-header">
    <h1>This is our conversation title <input type="submit" id="create-conversation" class="pull-right btn btn-primary" value="Reply"/></h1>
</div>

<section>
    <article class="message">
        <h4 class="author">Andrew Moffatt <span class="badge badge-inverse pull-right date">7.32am</span></h4>

        <div class="content dull">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec semper sodales leo, quis dictum justo ultricies at. Quisque congue diam id dui suscipit faucibus.
        </div>
    </article>
    <article class="message">
        <h4 class="author">Andrew Moffatt <span class="badge badge-inverse pull-right date">8.12am</span></h4>

        <div class="content dull">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec semper sodales leo, quis dictum justo ultricies at. Quisque congue diam id dui suscipit faucibus.
        </div>
    </article>
    <article class="message">
        <h4 class="author">Paul Chenery <span class="badge badge-inverse pull-right date">5.13pm</span></h4>

        <div class="content dull">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec semper sodales leo, quis dictum justo ultricies at. Quisque congue diam id dui suscipit faucibus.
        </div>
    </article>
    <article class="message">
        <h4 class="message-author">Paul Chenery <span class="badge badge-inverse pull-right date">Yesterday</span></h4>

        <div class="content dull">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec semper sodales leo, quis dictum justo ultricies at. Quisque congue diam id dui suscipit faucibus.
        </div>
    </article>
</section>

<section class="new-message-container">
    <textarea class="span12 new-message" cols="6" rows="3"></textarea>
    <input id="submit-message" class="pull-right btn btn-primary" type="submit" value="Reply"/>
</section>


