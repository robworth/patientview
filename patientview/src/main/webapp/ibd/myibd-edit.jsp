<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		<link href="css/main.css" rel="stylesheet">
	</head>
	<body>
		<div class="navbar">
		<div class="navbar-inner">
		  <div class="container">
		    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
		      <span class="icon-bar"></span>
		      <span class="icon-bar"></span>
		      <span class="icon-bar"></span>
		    </a>
		    <a class="brand" href="#">Site Title</a>
		    <div class="nav-collapse">
		      	<ul class="nav pull-right">
		        	<li><a href="#">Need Help <i class="icon-question-sign icon-white"></i></a></li>
		      	</ul>
	      		<form class="navbar-form pull-right" action="">
        			<input type="text" class="span2" placeholder="Username">
        			<input type="text" class="span2" placeholder="Password">
        			<input class="btn" type="submit" value="Login">
		      	</form>
		    </div><!-- /.nav-collapse -->
		  </div>
		</div><!-- /navbar-inner -->
		</div>
		<div class="container">
			<ul class="nav nav-pills">
			  <li class="first"><a href="index.html">Home</a></li>
			  <li><a href="#">My Details</a></li>
			  <li class="active"><a href="myIbd.html">My IBD</a></li>
			  <li><a href="#">My Care Plan</a></li>
			  <li><a href="#">Enter My Symptoms</a></li>
			  <li><a href="#">Nutrition</a></li>
			  <li><a href="#">Results</a></li>
			  <li><a href="#">Medicines</a></li>
			  <li><a href="#">Letters</a></li>
			  <li><a href="#">Contact</a></li>
			  <li><a href="#">Education</a></li>
			</ul>
			<div class="row">
				<div class="span12">
					<h1>My IBD</h1>
				</div>
			</div>
            <html:form action="/myibd-update">
                <div class="row">
                    <div class="span6">
                        <logic:present name="diagnosisList" scope="session">
                            <div class="row control-group">
                                <div class="span3">
                                    <label class="control-label">Primary Diagnosis:</label>
                                </div>
                                <div class="span3 controls">
                                    <html:select property="diagnosisId">
                                        <html:option value="">Select</html:option>
                                        <html:options collection="diagnosisList" property="id" labelProperty="name" />
                                    </html:select>
                                </div>
                            </div>
                        </logic:present>
                        <logic:present name="diseaseExtentList" scope="session">
                            <div class="row control-group">
                                <div class="span3">
                                    <label class="control-label">Disease Extent:</label>
                                </div>
                                <div class="span3 controls">
                                    <html:select property="diseaseExtentId">
                                        <html:option value="">Select</html:option>
                                        <html:options collection="diseaseExtentList" property="id" labelProperty="name"/>
                                    </html:select>
                                </div>
                            </div>
                        </logic:present>
                        <logic:present name="complicationList" scope="session">
                            <div class="row control-group">
                                <div class="span3">
                                    <label class="control-label">Complications:</label>
                                </div>
                                <div class="span3 controls">
                                        <html:select property="complicationIds" multiple="true">
                                            <html:options collection="complicationList" property="id" labelProperty="name"/>
                                        </html:select>
                                </div>
                            </div>
                        </logic:present>
                    </div>
                </div>
                <div class="row">
                    <div class="span6">
                        <logic:present name="bodyPartAffectedList" scope="session">
                            <div class="row control-group">
                                <div class="span3">
                                    <label class="control-label">Other parts of the body affected:</label>
                                </div>
                                <div class="span3 controls">
                                    <html:select property="bodyPartAffectedId">
                                        <html:options collection="bodyPartAffectedList" property="id" labelProperty="name"/>
                                    </html:select>
                                </div>
                            </div>
                        </logic:present>
                    </div>
                </div>
                <div class="row control-group">
                    <div class="span3">
                        <label class="control-label">Weight:</label>
                    </div>
                    <div class="span3 controls">
                        <html:text property="weight" />
                    </div>
                </div>
                <logic:present name="familyHistoryList" scope="session">
                    <div class="row control-group">
                        <div class="span3">
                            <label class="control-label">IBD Related Family History:</label>
                        </div>
                        <div class="span3 controls">
                            <html:select property="familyHistoryId">
                                <html:options collection="familyHistoryList" property="id" labelProperty="name"/>
                            </html:select>
                        </div>
                    </div>
                </logic:present>
                <logic:present name="smokingList" scope="session">
                    <div class="row control-group">
                        <div class="span3">
                            <label class="control-label">Smoking History:</label>
                        </div>
                        <div class="span3 controls">
                            <html:select property="smokingId">
                                <html:options collection="smokingList" property="id" labelProperty="name"/>
                            </html:select>
                        </div>
                    </div>
                </logic:present>
                <logic:present name="surgeryList" scope="session">
                    <div class="row control-group">
                        <div class="span3">
                            <label class="control-label">Surgery History:</label>
                        </div>
                        <div class="span3 controls">
                            <html:select property="surgeryId">
                                <html:options collection="surgeryList" property="id" labelProperty="name" />
                            </html:select>
                        </div>
                    </div>
                </logic:present>
                <logic:present name="vaccinationRecordList" scope="session">
                    <div class="row control-group">
                        <div class="span3">
                            <label class="control-label">Vaccination History:</label>
                        </div>
                        <div class="span3 controls">
                            <html:select property="vaccinationRecordId">
                                <html:options collection="vaccinationRecordList" property="id" labelProperty="name"/>
                            </html:select>
                        </div>
                    </div>
                </logic:present>

                <div class="row">
                    <div class="span12">
                        <div class="error">
                            <html:errors />
                        </div>

                        <p>
                            <html:submit value="Save" styleClass="btn"/>
                            <html:link action="/myibd" styleClass="btn">Cancel</html:link>
                        </p>
                    </div>
                </div>
            </html:form>

            <div class="row">
                <div class="span12 footer">
                    <ul class="barSpeperatedNav">
                        <li><a href="#">Legal</a></li>
                        <li><a href="#">Privacy Policy</a></li>
                        <li><a href="#">About</a></li>
                        <li><a href="#">Accesibility</a></li>
                    </ul>
                </div>
            </div>
		</div>
		<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.js"></script>
	</body>
</html>