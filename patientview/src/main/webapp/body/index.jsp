<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<div class="hero">
    <div id="myCarousel" class="carousel slide">
        <!-- Carousel items -->
        <div class="carousel-inner">
            <div class="active item">
                <img src="/img/content/heroImage-man.jpg" alt="heroImage">
                <div class="carousel-caption">
                    <h4 class="largeCarouselTitle"><em>Renal PatientView</em></h4>
                    <ul class="heroList">
                        <li><em>Manage </em> your condition and medications</li>
                        <li><em>Monitor </em> your symptoms and tests</li>
                        <li><em>Make</em> contact with your unit</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="span4 seperatingBorders">
        <h3 class="mediumBlueTitle titleSeperator">About <em>Renal PatientView</em></h3>
        <p>
            Renal PatientView is a project of <a target="_blank" href="http://www.renal.org/rixg">RIXG</a> a UK group representing renal
            patients and the renal team.  It aims to provide online information about renal patients' diagnosis, treatment, and their latest test results.  Patients can share this information with anyone they want, and view it from anywhere in the world.
        </p>
        <p>
            PatientView is only available from some UK renal units, and for patients who have chosen to participate.
            Here is a <a target="_blank" href="http://www.renal.org/rixg/units.html">list of units</a> involved so far.  The information
            comes directly from existing databases within units, so if you suspect a mistake, you should check with your
            own unit.  Links are provided after you log in.
        </p>
    </div>
    <div class="span4 seperatingBorders">
        <h3 class="mediumBlueTitle titleSeperator">Further Information</h3>
        <p>
            You can view our <a href="/infoLinks.do">information links</a>
            without logging in.
        </p>

        <p>
            You can view a <a href="/demo.do">demo</a> as if you were a
            patient.
        </p>

        <p>
            RPV is funded by contributions from renal units in England and Wales and by the Scottish Government in
            Scotland. Development funding has come from the Department of Health in England, Scotland and Wales, and
            from NHS Kidney Care.
        </p>

        <h3 class="mediumBlueTitle titleSeperator">I Want To Join</h3>
        <p>
            If you want to see your results on PatientView then click here to enter your details:

            <div class="control-group">
                <div class="controls">
                    <a href="/join.do" class="btn btn-danger">I want to join</a>
                </div>
            </div>
        </p>
    </div>
    <div class="span4 seperatingBorders">
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
</div>