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
            <logic:present name="myIbd">
                <div class="row">
                    <div class="span6">
                        <div class="row control-group">
                            <div class="span3">
                                <label class="control-label">Primary Diagnosis:</label>
                            </div>
                            <div class="span3 controls">
                                <bean:write name="myIbd" property="diagnosis.name" />
                            </div>
                        </div>
                        <div class="row control-group">
                            <div class="span3">
                                <label class="control-label">Disease Extent:</label>
                            </div>
                            <div class="span3 controls">
                                <bean:write name="myIbd" property="diseaseExtent.name" />
                            </div>
                        </div>
                        <div class="row control-group">
                            <div class="span3">
                                <label class="control-label">Complications:</label>
                            </div>
                            <div class="span3 controls">
                                <bean:size id="complicationsListSize" name="myIbd" property="complications" />
                                <logic:iterate name="myIbd" property="complications" id="complication" indexId="index">
                                    <bean:write name="complication" property="name" /><%= (complicationsListSize == (index + 1) ? "" : ",") %>
                                </logic:iterate>
                            </div>
                        </div>
                    </div>
                    <div class="span6">
                        <div class="medicalDiagram">
                            <img src="img/content/proctitis.jpg" alt="Proctitis"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="span3">
                        Current Medications:
                    </div>
                    <div class="span9">
                        <table class="table table-bordered">
                            <thead>
                              <tr>
                                <th>Date Started</th>
                                <th>Type</th>
                                <th>Medication</th>
                                <th>Dose</th>
                                <th>No Tabs/Granules</th>
                                <th>Frequency</th>
                              </tr>
                            </thead>
                            <tbody>
                              <tr>
                                <td>12/12/2012</td>
                                <td>Oral 5 Aminosalicylate</td>
                                <td>Pentasa</td>
                                <td>400mg</td>
                                <td>4</td>
                                <td>Frequencey</td>
                              </tr>
                              <tr>
                                <td>12/12/2012</td>
                                <td>Oral 5 Aminosalicylate</td>
                                <td>Pentasa</td>
                                <td>400mg</td>
                                <td>4</td>
                                <td>Frequencey</td>
                              </tr>
                              <tr>
                                <td>12/12/2012</td>
                                <td>Oral 5 Aminosalicylate</td>
                                <td>Pentasa</td>
                                <td>400mg</td>
                                <td>4</td>
                                <td>Frequencey</td>
                              </tr>
                            </tbody>
                          </table>
                    </div>
                </div>
                <div class="row">
                    <div class="span6">
                        <div class="row control-group">
                            <div class="span3">
                                <label class="control-label">Other parts of the body affected:</label>
                            </div>
                            <div class="span3 controls">
                                <bean:write name="myIbd" property="bodyPartAffected.name" />
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="span6">
                        <div class="row control-group">
                            <div class="span3">
                                <label class="control-label">General:</label>
                            </div>
                            <div class="span3 controls">
                                <p>Weight: <bean:write name="myIbd" property="weight" /></p>
                                <p>IBD Related Family History: <bean:write name="myIbd" property="familyHistory.name" /></p>
                                <p>Smoking History: <bean:write name="myIbd" property="smoking.name" /></p>
                                <p>Surgical History: <bean:write name="myIbd" property="surgery.name" /></p>
                                <p>Vaccination Record: <bean:write name="myIbd" property="vaccinationRecord.name" /></p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="span12">
                        <html:link action="/myibd-edit">Edit</html:link>
                    </div>
                </div>
            </logic:present>
            <logic:notPresent name="myIbd">
                <div class="row">
                    <div class="span12">
                        <html:link action="/myibd-edit">Add</html:link>
                    </div>
                </div>
            </logic:notPresent>

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