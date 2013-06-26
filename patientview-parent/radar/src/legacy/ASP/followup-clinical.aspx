<%@ Page Title="" Language="VB" MasterPageFile="~/MasterPage.master" AutoEventWireup="false" CodeFile="followup-clinical.aspx.vb" Inherits="followup_clinical" %>
<%@ Register assembly="DevExpress.Web.ASPxEditors.v9.2, Version=9.2.4.0, Culture=neutral, PublicKeyToken=b88d1754d700e49a" namespace="DevExpress.Web.ASPxEditors" tagprefix="dxe" %>

<%@ Register assembly="AjaxControlToolkit" namespace="AjaxControlToolkit" tagprefix="cc1" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
    <link href="tabs_lab.css" rel="stylesheet" type="text/css" />
<link href="tabs.css" rel="stylesheet" type="text/css" />
<script src="jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="jquery-impromptu.2.7.min.js"></script>
<style type="text/css">

input, select, textarea {
	border: 1px solid gray;
}
select, input, textarea {
	font-size: 95%;
	color: #2D2E2E;
}
#subBlock table {
	margin: 0;
}
.indent {
	padding-left: 50px;
}
.noborder {
	border: 0;
}

#tblHeader {
background-color: #b6df9f;
}
fieldset {
border: 1px solid green;
padding:5px;
}
legend {
font-weight:bold;
}

.style2 {
	background-color: #B6DF9F;
	text-align: right;
}
.style4 {
	text-align: right;
	width:120px;
	padding-right:20px;
}
.style5 {
	border-collapse: collapse;
        width: 100%;
        table-layout:auto;
    }
 .style6
    {
        width: 129px;
    }
    
.btmBorder {
border-bottom: 1px green solid;
height:30px;
vertical-align:middle;
}

/* this because of the update panel for the tabs */
/*#tabsC {
margin-top:-35px;
}*/

#ctl00_ContentPlaceHolder1_radCompInfectYes {
border:0;
}
#ctl00_ContentPlaceHolder1_radCompInfectNo {
border:0;
}

#ctl00_ContentPlaceHolder1_radTYes {
border:0;
}
#ctl00_ContentPlaceHolder1_radTNo {
border:0;
}
#ctl00_ContentPlaceHolder1_radTxNo {
border:0;
}
#ctl00_ContentPlaceHolder1_radTxYes {
border:0;
}

#ctl00_ContentPlaceHolder1_rowHyperten input, #ctl00_ContentPlaceHolder1_rowPreceed input, #ctl00_ContentPlaceHolder1_rowClinicalEvidence input {
border:0;
}

#ctl00_ContentPlaceHolder1_radRashYes, #ctl00_ContentPlaceHolder1_radRashNo, #ctl00_ContentPlaceHolder1_radRashUkn {
border:0;
}
#ctl00_ContentPlaceHolder1_radOedemaYes, #ctl00_ContentPlaceHolder1_radOedemaNo, #ctl00_ContentPlaceHolder1_radOedemaUnkn {
border:0;
}
 #ctl00_ContentPlaceHolder1_radHypovYes, #ctl00_ContentPlaceHolder1_radHypovNo, #ctl00_ContentPlaceHolder1_radHypovUn {
border:0;
}
#ctl00_ContentPlaceHolder1_radFeverYes, #ctl00_ContentPlaceHolder1_radFeverNo, #ctl00_ContentPlaceHolder1_radFeverUn {
border:0;
}
#ctl00_ContentPlaceHolder1_radThrombosisYes, #ctl00_ContentPlaceHolder1_radThrombosisNo, #ctl00_ContentPlaceHolder1_radThrombosisUn {
border:0;
}
#ctl00_ContentPlaceHolder1_radPeritYes, #ctl00_ContentPlaceHolder1_radPeritNo, #ctl00_ContentPlaceHolder1_radPeritUn {
border:0;
}
#ctl00_ContentPlaceHolder1_radPulYes, #ctl00_ContentPlaceHolder1_radPulNo, #ctl00_ContentPlaceHolder1_radPulUn {
border:0;
}
 #ctl00_ContentPlaceHolder1_radImmTrigYes, #ctl00_ContentPlaceHolder1_radImmTrigNo, #ctl00_ContentPlaceHolder1_radImmTrigUn {
border:0;
}
#ctl00_ContentPlaceHolder1_radLipodYes, #ctl00_ContentPlaceHolder1_radLipodNo, #ctl00_ContentPlaceHolder1_radLipodUn {
border:0;
}
#ctl00_ContentPlaceHolder1_rowCKD input{
border:0;
}
#ctl00_ContentPlaceHolder1_chkClinicalEvidence, #ctl00_ContentPlaceHolder1_chkOpthalm, #ctl00_ContentPlaceHolder1_chkPreceed {
border:0;
}
#ctl00_ContentPlaceHolder1_radOpthalmYes, #ctl00_ContentPlaceHolder1_radOpthalmNo, #ctl00_ContentPlaceHolder1_radOpthalmUn {
border:0;
}
#ctl00_ContentPlaceHolder1_rowAnemia input{
border:0;
}
#ctl00_ContentPlaceHolder1_rowHypertension input{
border:0;
}

</style>
<!--[if IE]>
<style type="text/css">
#mainBlock {
	margin-top: 0;
}
#subBlock {
margin-top:0;
}
#tabsC {
margin-top:0px;
}

.inline2 {
display: inline;
}   

    .style7
    {
        height: 40px;
    }

</style>
<![endif]-->
</asp:Content>
<asp:Content ID="Content2" Runat="Server" ContentPlaceHolderID="ContentPlaceHolder1">
   
    <div id="tabsC" > 
<ul> 
    <li><asp:hyperlink runat="server" ID="lnkDemographics" NavigateUrl="demographics.aspx" title="Demographics"><span>Demographics</span></asp:hyperlink></li> 
    <li><asp:hyperlink runat="server" ID="lnkDiagnosis" NavigateUrl="diagnosis.aspx" ToolTip="Diagnosis"><span>Diagnosis</span></asp:hyperlink></li> 
    <li><asp:hyperlink runat="server" ID="lnkLabResults" NavigateUrl="first-clinical.aspx" Tooltip="Laboratory Results"><span>First Visit</span></asp:hyperlink></li> 
    <li><asp:hyperlink runat="server" ID="lnk3Months" NavigateUrl="followup-clinical.aspx" Tooltip="Follow Up"><span class="hovered">Follow Up</span></asp:hyperlink></li> 
    <li><asp:hyperlink runat="server" ID="lnkPathology" NavigateUrl="pathology.aspx" Tootip="Pathology"><span>Pathology</span></asp:hyperlink></li> 
    <li><asp:hyperlink runat="server" ID="lnkRelapse" NavigateUrl="relapse.aspx" Tooltip="Relapse"><span>Relapse</span></asp:hyperlink></li> 
    <li><asp:hyperlink runat="server" ID="lnkHospital" NavigateUrl="hospitalisation.aspx" Tooltip="Hospitalisation"><span>Hospitalisation</span></asp:hyperlink></li> 
    <li><asp:hyperlink runat="server" ID="lnkTimelines" NavigateUrl="timelines.aspx" Tootip="Timelines"><span>Timelines</span></asp:hyperlink></li> 
</ul> 
</div> 
<div id="mainBlock">
<div id="tabsLab" style="margin:-20px 0 0 -40px;">
<ul>
	<li><asp:hyperlink runat="server" ID="lnkFollowupClinical" NavigateUrl="#" Tooltip="Clinical Picture"><span class="hovered">Clinical Picture</span></asp:hyperlink></li> 
    <li><asp:hyperlink runat="server" ID="lnkFollowupLab" NavigateUrl="followup-lab.aspx" ToolTip="Lab Results"><span>Lab Results</span></asp:hyperlink></li>
    <li><asp:hyperlink runat="server" ID="lnkFollowupTreatment" NavigateUrl="followup-treatment.aspx" ToolTip="Disease Treatment"><span>Disease Treatment</span></asp:hyperlink></li>
    <li><asp:hyperlink runat="server" ID="lnkTherapy" NavigateUrl="rrt_therapy.aspx" Tooltip="Therapy"><span >RRT Therapy</span></asp:hyperlink></li> 
        <%--<tr>
					<td colspan="2">&nbsp;</td>
				</tr>--%>
</ul>
</div>
<div id="subBlock">
<table cellpadding="5" class="style1" style="width: 100%; background-color: #B6DF9F;">
	<tr>
        
		<td class="btmBorder">
            <asp:DropDownList ID="DropDownList10" runat="server" 
                DataSourceID="SqlDataSource2" DataTextField="DATE_CLIN_PIC"  
                DataValueField="SEQ_NO" AppendDataBoundItems="true" 
                DataTextFormatString="{0:d}" AutoPostBack="true">
                <asp:ListItem Value="" Text="Select"></asp:ListItem>
            </asp:DropDownList>
            
&nbsp;Select previous visit record by date&nbsp;
            <asp:Button ID="btnAdd" runat="server" Text="Add new visit record" CausesValidation="false" />
        </td>
		<td class="style2, btmBorder">&nbsp;</td>
		<td class="style2, btmBorder" style="width:120px">
            <strong>Clinical</strong></td>
	</tr>
	</table>
	<asp:Panel ID="pnlAdd" runat="server" Visible="false">
	<table cellpadding="5" class="style1" style="width: 100%; background-color: #B6DF9F;">

	<tr>
        
		<td><b>Clinical Features - Follow Up Visit</b></td>
		<td class="style2"><strong>RADAR No.</strong></td>
		<td class="style2" style="width:120px">
            <asp:TextBox ID="txtRADAR_NO" runat="server" ReadOnly="True" Width="100px"></asp:TextBox>
									</td>
	</tr>
	<tr>
		<td><b>Name:</b> 
		<asp:Label id="lblFirstName" runat="server" Text="" >&nbsp;</asp:Label>&nbsp;<asp:Label ID="lblSurname" runat="server" Width="120px"></asp:Label>
            &nbsp;&nbsp;<b>DoB:</b>&nbsp;<asp:Label ID="lblDOB" runat="server"></asp:Label>
		</td>
		<td class="style2"><strong>Hospital No.</strong></td>
		<td class="style2">
            <asp:TextBox ID="txtHOSP_NO" runat="server" ReadOnly="True" Width="100px"></asp:TextBox>
									</td>
	</tr>
	<tr>
		<td>Date of Clinical Picture 
            <asp:TextBox ID="txtDateClinicalPic" runat="server" Width="70px"></asp:TextBox>
           
            <cc1:CalendarExtender ID="txtDateClinicalPic_CalendarExtender" runat="server" 
                Enabled="True" TargetControlID="txtDateClinicalPic" Format="dd-MM-yyyy" PopupButtonID="ImageButton1">
            </cc1:CalendarExtender><asp:ImageButton ID="ImageButton1" runat="server" CausesValidation="False" ImageUrl="~/images/Calendar_scheduleHS.png"  />
           
            <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" 
                ControlToValidate="txtDateClinicalPic" ErrorMessage="*" Font-Bold="True" 
                SetFocusOnError="true"></asp:RequiredFieldValidator>&nbsp;<asp:CompareValidator 
                ID="CompareValidator1" runat="server" ControlToCompare="txtDateToday" 
                ControlToValidate="txtDateClinicalPic" Display="Dynamic" 
                ErrorMessage="&lt;=Today" Operator="LessThanEqual" Type="Date"></asp:CompareValidator>
&nbsp;<asp:TextBox ID="txtDateToday" runat="server" BorderColor="#b6df9f" BackColor="#b6df9f" ForeColor="#b6df9f"></asp:TextBox>
            &nbsp;
            <asp:TextBox ID="txtSEQ_NO" runat="server" BorderColor="#b6df9f" 
                BackColor="#b6df9f" ForeColor="#B6DF9F"></asp:TextBox>
        </td>
		<td class="style2"><strong>Diagnosis</strong></td>
		<td class="style2">
            <asp:TextBox ID="txtDIAGNOSIS" runat="server" ReadOnly="True" 
                BackColor="#FFFF99" Width="100px"></asp:TextBox>
									</td>
	</tr>
</table>
    

<table style="width: 100%; background-color:#EEF6ED;" class="style1" id="tblMain" runat="server">
	<tr>
		<td style="width:50%; vertical-align:top;">
		<table style="width: 100%; table-layout:fixed">
			<tr>
				<td class="style7" colspan="2">
                    <asp:Button ID="btnUpdate2" runat="server" CssClass="saveBtn" Text="Save" />
                    &nbsp;
                    <asp:Label ID="lblUpdate2" runat="server"></asp:Label>
                </td>
			</tr>
			<tr>
                <td class="style4">
                    &nbsp;
                    <asp:Label ID="lblID" runat="server" Visible="False"></asp:Label>
                </td>
                <td>
                </td>
            </tr>
			<tr>
				<td class="style4"  >Height cm</td>
				<td>
                    <asp:TextBox ID="txtHeight" runat="server" Width="40px"></asp:TextBox>
                    
                    
                    
                    
                    
                    
                    
                    &nbsp;<asp:RangeValidator ID="RangeValidator1" runat="server" 
                        ControlToValidate="txtHeight" ErrorMessage="35.0-185.0" MaximumValue="185.0" 
                        MinimumValue="35.0" Type="Double"></asp:RangeValidator>
                    
                    
                    
                    
                    
                    
                    
                </td>
			</tr>
			<tr>
				<td class="style4" >Weight Kg</td>
				<td>
                    <asp:TextBox ID="txtWeight" runat="server" Width="40px"></asp:TextBox>
                </td>
			</tr>
			<tr>
				<td class="style4" >BP</td>
				<td>
                    <asp:TextBox ID="txtBPSys" runat="server" Width="40px"></asp:TextBox>&nbsp;<strong>/</strong>
                &nbsp;<asp:TextBox id="txtBPDia" runat="server" Width="40px"></asp:TextBox>
                &nbsp;
					<asp:TextBox id="txtBPMap" runat="server" Width="40px" ReadOnly="True" 
                        Enabled="False"></asp:TextBox>
                    &nbsp;MAP</td>
			</tr>
			<tr>
				<td class="style6" >&nbsp;</td>
				<td>
				&nbsp;</td>
			</tr>
			<%--<tr>
				<td style="width: 115px; text-align:right; padding-right:20px;">Phenotype 1</td>
				<td>
				    <asp:DropDownList ID="DropDownList6" runat="server" 
                        DataSourceID="SqlDataSource1" DataTextField="pDesc" DataValueField="pID" AppendDataBoundItems="true">
                        <asp:ListItem Value="" Text="Select" />
                    </asp:DropDownList>
                </td>
			</tr>
			<tr>
				<td style="width: 115px; text-align:right; padding-right:20px;">Phenotype 2</td>
				<td>
                    <asp:DropDownList ID="DropDownList7" runat="server" 
                        DataSourceID="SqlDataSource1" DataTextField="pDesc" DataValueField="pID" AppendDataBoundItems="true">
                         <asp:ListItem Value="" Text="Select" />
                    </asp:DropDownList>
                </td>
			</tr>
			<tr>
				<td style="width: 115px; text-align:right; padding-right:20px;">Phenotype 3</td>
				<td>
                    <asp:DropDownList ID="DropDownList8" runat="server" 
                        DataSourceID="SqlDataSource1" DataTextField="pDesc" DataValueField="pID" AppendDataBoundItems="true">
                         <asp:ListItem Value="" Text="Select" />
                    </asp:DropDownList>
                </td>
			</tr>
			<tr>
                <td style="width: 115px; text-align:right; padding-right:20px;">Phenotype 4</td>
                <td>
                    <asp:DropDownList ID="DropDownList9" runat="server" 
                        DataSourceID="SqlDataSource1" DataTextField="pDesc" DataValueField="pID" AppendDataBoundItems="true">
                         <asp:ListItem Value="" Text="Select" />
                    </asp:DropDownList>
                </td>
            </tr>--%>
<tr id="rowPhenotype1" runat="server">
				<td style="width: 140px; text-align:right; padding-right:20px;">
                    <asp:Label ID="lblPhenotype1" runat="server" Text="Phenotype 1"></asp:Label>
                </td>
				<td>
				    <asp:DropDownList ID="DropDownList6" runat="server" 
                        DataSourceID="SqlDataSource1" DataTextField="pDesc" DataValueField="pID" AppendDataBoundItems="true">
                        <asp:ListItem Value="" Text="Select" />
                    </asp:DropDownList>
                </td>
			</tr>
			<tr id="rowPhenotype2" runat="server">
				<td style="width: 140px; text-align:right; padding-right:20px;">
                    <asp:Label ID="lblPhenotype2" runat="server" Text="Phenotype 2"></asp:Label>
                </td>
				<td>
                    <asp:DropDownList ID="DropDownList7" runat="server" 
                        DataSourceID="SqlDataSource1" DataTextField="pDesc" DataValueField="pID" AppendDataBoundItems="true">
                         <asp:ListItem Value="" Text="Select" />
                    </asp:DropDownList>
                </td>
			</tr>
			<tr id="rowPhenotype3" runat="server">
				<td style="width: 140px; text-align:right; padding-right:20px;">
                    <asp:Label ID="lblPhenotype3" runat="server" Text="Phenotype 3"></asp:Label>
                </td>
				<td>
                    <asp:DropDownList ID="DropDownList8" runat="server" 
                        DataSourceID="SqlDataSource1" DataTextField="pDesc" DataValueField="pID" AppendDataBoundItems="true">
                         <asp:ListItem Value="" Text="Select" />
                    </asp:DropDownList>
                </td>
			</tr>
			<tr id="rowPhenotype4" runat="server">
                <td style="width: 140px; text-align:right; padding-right:20px;">
                    <asp:Label ID="lblPhenotype4" runat="server" Text="Phenotype 4"></asp:Label>
                </td>
                <td>
                    <asp:DropDownList ID="DropDownList9" runat="server" 
                        DataSourceID="SqlDataSource1" DataTextField="pDesc" DataValueField="pID" AppendDataBoundItems="true">
                         <asp:ListItem Value="" Text="Select" />
                    </asp:DropDownList>
                </td>
            </tr>
            <tr>
                <td style="width: 140px; text-align:right; padding-right:20px;">
                    &nbsp;</td>
                <td>
                    &nbsp;</td>
            </tr>
<tr>
				<td class="style6" >Comments&nbsp; <a class="info" href="#">Help <span>Enter any 
                    other relevant clinical features</span></a></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td class="style6" >&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" >
                    <asp:TextBox ID="txtComments" runat="server" Rows="15" TextMode="MultiLine" 
                        Width="100%" MaxLength="500"></asp:TextBox>
                </td>
				
			</tr>

			</table>
		
			</td>
			<td style="vertical-align:top;">
			<table style="width:100%" class="style1" cellpadding="4px">
			<tr>
				<td>
                    <asp:Label ID="lblCourseDisease" runat="server" Text="Course of Disease"></asp:Label>
                </td>
				<td><asp:DropDownList ID="DropDownList2" runat="server" >
                    <asp:ListItem Value="">Select</asp:ListItem>
                    <asp:ListItem Value="1">Acute</asp:ListItem>
                    <asp:ListItem Value="2">Chronic</asp:ListItem>
                    <asp:ListItem Value="9">Un</asp:ListItem>
                    </asp:DropDownList>
</td>
			</tr>
			<tr>
				<td>Additional Significant Diagnosis 1</td>
				<td>
				<asp:textbox id="txtSigDiag1" runat="server" Enabled="False" 
                        ToolTip="From Diagnosis"></asp:textbox>
				</td>
			</tr>
			<tr>
				<td>Additional Significant Diagnosis 2</td>
				<td>
				<asp:TextBox id="txtSigDiag2" runat="server" Enabled="False" 
                        ToolTip="From Diagnosis"></asp:TextBox>
											</td>
			</tr>
			<tr>
				<td class="indent">&nbsp;</td>
				<td >
&nbsp;</td>
			</tr>
            
			    <tr id="rowOedema" runat="server">
                    <td class="indent">
                        Oedema</td>
                    <td>
                        <asp:RadioButton ID="radOedemaYes" runat="server" GroupName="oedema" 
                            Text="Yes" />
                        &nbsp;<asp:RadioButton ID="radOedemaNo" runat="server" GroupName="oedema" 
                            Text="No" />
                        &nbsp;<asp:RadioButton ID="radOedemaUnkn" runat="server" GroupName="oedema" 
                            Text="Un" ToolTip="Unknown" />
                    </td>
                </tr>
			<tr id="rowAnemia" runat="server">
				<td class="indent">Anaemia</td>
				<td>
				    <asp:RadioButton ID="radAnaemiaYes" runat="server" GroupName="anaemia" 
                        Text="Yes" />
&nbsp;<asp:RadioButton ID="radAnaemiaNo" runat="server" GroupName="anaemia" Text="No" />
&nbsp;<asp:RadioButton ID="radAnaemiaUn" runat="server" GroupName="anaemia" Text="Un" 
                        ToolTip="Unkown" />
											</td>
			</tr>
			<tr id="rowHypovalaemia" runat="server">
				<td  class="indent">Hypovalaemia</td>
				<td>
				    <asp:RadioButton ID="radHypovYes" runat="server" GroupName="hypov" Text="Yes" />
&nbsp;<asp:RadioButton ID="radHypovNo" runat="server" GroupName="hypov" Text="No" />
&nbsp;<asp:RadioButton ID="radHypovUn" runat="server" GroupName="hypov" Text="Un" 
                        ToolTip="Unknown" />
											</td>
			</tr>
			<tr id="rowFever" runat="server">
				<td class="indent">Fever &gt; 38&deg;C</td>
				<td>
				    <asp:RadioButton ID="radFeverYes" runat="server" GroupName="radFever" 
                        Text="Yes" />
&nbsp;<asp:RadioButton ID="radFeverNo" runat="server" GroupName="radFever" Text="No" />
&nbsp;<asp:RadioButton ID="radFeverUn" runat="server" GroupName="radFever" Text="Un" 
                        ToolTip="Unknown" />
											</td>
			</tr>
			<tr id="rowThrombosis" runat="server">
				<td class="indent">Thrombosis</td>
				<td>
				    <asp:RadioButton ID="radThrombosisYes" runat="server" GroupName="radThrombosis" 
                        Text="Yes" />
&nbsp;<asp:RadioButton ID="radThrombosisNo" runat="server" GroupName="radThrombosis" Text="No" />
&nbsp;<asp:RadioButton ID="radThrombosisUn" runat="server" GroupName="radThrombosis" 
                        Text="Un" ToolTip="Unknown" />
											</td>
			</tr>
			<tr id="rowPeritonitis" runat="server">
				<td class="indent">Peritonitis</td>
				<td>
				    <asp:RadioButton ID="radPeritYes" runat="server" GroupName="radPerit" 
                        Text="Yes" />
&nbsp;<asp:RadioButton ID="radPeritNo" runat="server" GroupName="radPerit" Text="No" />
&nbsp;<asp:RadioButton ID="radPeritUn" runat="server" GroupName="radPerit" Text="Un" 
                        ToolTip="Unknown" />
											</td>
			</tr>
			<tr id="rowPulmonary" runat="server">
				<td class="indent">Pulmonary Oedema</td>
				<td>
				    <asp:RadioButton ID="radPulYes" runat="server" GroupName="radPul" Text="Yes" />
&nbsp;<asp:RadioButton ID="radPulNo" runat="server" GroupName="radPul" Text="No" />
&nbsp;<asp:RadioButton ID="radPulUn" runat="server" GroupName="radPul" Text="Un" 
                        ToolTip="Unknown" />
											</td>
			</tr>
			<tr id="rowHyperten" runat="server" >
				<td class="indent">Hypertension, req Rx</td>
				<td>
				    <asp:RadioButton ID="radHyperYes" runat="server" GroupName="hyperten" 
                        Text="Yes" />
&nbsp;<asp:RadioButton ID="radHyperNo" runat="server" GroupName="hyperten" Text="No" />
&nbsp;<asp:RadioButton ID="radHyperUn" runat="server" GroupName="hyperten" Text="Un" 
                        ToolTip="Unknown" />
											</td>
			</tr>
			<tr id="rowDiabetes" runat="server">
				<td class="indent">Diabetes: Type</td>
				<td>
				<asp:DropDownList ID="DropDownList4" runat="server" >
                <asp:ListItem Value="" Selected="True">Select</asp:ListItem>
                <asp:ListItem Value="1">Type I IDDM</asp:ListItem>
                <asp:ListItem Value="2">Type II NIDDM</asp:ListItem>
                <asp:ListItem Value="99">No</asp:ListItem>
                </asp:DropDownList>
</td>
			</tr>
			<tr id="rowRash" runat="server">
				<td class="indent" valign="top">
                    <asp:Label ID="lblRash" runat="server" Text="Rash"></asp:Label>
                </td>
				<td>
				    <asp:RadioButton ID="radRashYes" runat="server" AutoPostBack="True" 
                        GroupName="radRash" Text="Yes" BorderStyle="None" 
                         />
&nbsp;<asp:RadioButton ID="radRashNo" runat="server" AutoPostBack="True" GroupName="radRash" 
                        Text="No" />
&nbsp;<asp:RadioButton ID="radRashUkn" runat="server" GroupName="radRash" Text="Un" 
                        AutoPostBack="True" />
                </td>
			</tr>
			<tr id="rowRashDetail" runat="server">
				
				<td colspan="2" class="indent">
				<asp:textbox id="txtRashDetail" runat="server" Rows="4" TextMode="MultiLine" 
                        Width="350px"></asp:textbox>
</td>
			</tr>
			<%--<tr>
				<td class="indent">Possible Immunisation trigger</td>
				<td>
				    <asp:RadioButton ID="radImmTrigYes" runat="server" GroupName="radImmTrig" 
                        Text="Yes" />
&nbsp;<asp:RadioButton ID="radImmTrigNo" runat="server" GroupName="radImmTrig" Text="No" />
&nbsp;<asp:RadioButton ID="radImmTrigUn" runat="server" Text="Un" GroupName="radImmTrig" />
											</td>
			</tr>--%>
			<tr id="rowLipod" runat="server">
				<td class="indent">Partial Lipodystrophy</td>
				<td>
				    <asp:RadioButton ID="radLipodYes" runat="server" GroupName="radLipod" 
                        Text="Yes" />
&nbsp;<asp:RadioButton ID="radLipodNo" runat="server" GroupName="radLipod" Text="No" />
&nbsp;<asp:RadioButton ID="radLipodUn" runat="server" GroupName="radLipod" Text="Un" />
				</td>
			</tr>
			<tr id="rowPreceed" runat="server">
				<td class="indent">
                    <asp:Label ID="lblPrecInfect" runat="server"></asp:Label>
                </td>
				<td>
				    <asp:RadioButton ID="radPrecInfectYes" runat="server" AutoPostBack="True" 
                        GroupName="precInfect" Text="Yes" />
&nbsp;<asp:RadioButton ID="radPrecInfectNo" runat="server" AutoPostBack="True" 
                        GroupName="precInfect" Text="No" />
&nbsp;<asp:RadioButton ID="radPrecInfectUn" runat="server" AutoPostBack="True" 
                        GroupName="precInfect" Text="Un" ToolTip="Unknown" />
				</td>
			</tr>
			<tr id="rowPreceedDetail" runat="server" >
			<td colspan="2" class="indent">
			<asp:TextBox ID="txtPREC_INF_DETAIL" runat="server" Rows="4" Width="350px" 
                    TextMode="MultiLine"></asp:TextBox>
			    
			    <cc1:TextBoxWatermarkExtender ID="txtPREC_INF_DETAIL_TextBoxWatermarkExtender" 
                    runat="server" Enabled="True" TargetControlID="txtPREC_INF_DETAIL" 
                    WatermarkText="Enter details" WatermarkCssClass="grey">
                </cc1:TextBoxWatermarkExtender>
			    
			</td>
			</tr>
			<tr id="rowClinicalEvidence" runat="server">
				<td class="indent">Chronic infection (active)</td>
				<td>
				    <asp:RadioButton ID="radChronicYes" runat="server" AutoPostBack="True" 
                        GroupName="chronic" Text="Yes" />
&nbsp;<asp:RadioButton ID="radChronicNo" runat="server" AutoPostBack="True" GroupName="chronic" 
                        Text="No" />
&nbsp;<asp:RadioButton ID="radChronicUn" runat="server" AutoPostBack="True" GroupName="chronic" 
                        Text="Un" ToolTip="Unknown" />
				</td>
			</tr>
<tr id="rowClinicalEvidenceDetail" runat="server" >
			<td colspan="2" class="indent">
			<asp:TextBox ID="txtCLIN_EV_CHR_INF_DETAIL" runat="server" Rows="4" Width="350px" 
                    TextMode="MultiLine"></asp:TextBox>
			    <cc1:TextBoxWatermarkExtender ID="txtCLIN_EV_CHR_INF_DETAIL_TextBoxWatermarkExtender" 
                    runat="server" Enabled="True" TargetControlID="txtCLIN_EV_CHR_INF_DETAIL" 
                    WatermarkCssClass="grey" WatermarkText="Enter details">
                </cc1:TextBoxWatermarkExtender>
			</td>
			</tr>
			<tr id="rowOpthalm" runat="server">
				<td class="indent">
				<asp:label ID="lblOpth" runat="server" Text="Ophthalmoscopy"></asp:label>
				</td>
				<td>
                    <asp:RadioButton ID="radOpthalmYes" runat="server" AutoPostBack="True" 
                        GroupName="Opthalm" Text="Yes" />
&nbsp;<asp:RadioButton ID="radOpthalmNo" runat="server" AutoPostBack="True" 
                        GroupName="Opthalm" Text="No" />
&nbsp;<asp:RadioButton ID="radOpthalmUn" runat="server" AutoPostBack="True" 
                        GroupName="Opthalm" Text="Un" ToolTip="Unknown" />
				</td>
			</tr>
			<tr id="rowOpthalmDetail" runat="server">
				<td class="indent" colspan="2">
				<asp:textbox id="txtOpDetail" runat="server" Rows="5" TextMode="MultiLine" 
                        Visible="false" Width="350px"></asp:textbox>
				    <cc1:TextBoxWatermarkExtender ID="txtOpDetail_TextBoxWatermarkExtender" 
                        runat="server" Enabled="True" TargetControlID="txtOpDetail" WatermarkCssClass="grey" WatermarkText="If abnormal - Opthalmoscopy result            If Hypertensive, retinopathy or Drusen">
                    </cc1:TextBoxWatermarkExtender>
				</td>
			</tr>
           
			    <tr id="rowComplications" runat="server">
                    <td colspan="2">
                        <fieldset>
                            <legend>Complications</legend>
                            <br />
                            <table cellpadding="4" class="style5">
                                <%--<tr>
					<td colspan="2">&nbsp;</td>
				</tr>--%> 
				<%--<tr>
					<td style="width:100px;">RELAPSE</td>
					<td><asp:CheckBox runat="server" id="chkRelapse" AutoPostBack="true" CausesValidation="false"></asp:CheckBox></td>
				</tr>
<tr id="rowSpacer1" runat="server">
					<td colspan="2" >&nbsp;</td>
				</tr>
<tr id="rowRelapse" runat="server" >
					<td colspan="2">
					<table style="width:100%; font-size:90%; border-collapse:collapse;" id="tblRelapse" cellpadding="3px" >
					<tr>
					<td style="border:1px solid gray;">Date of relapse</td>
					<td style="border:1px solid gray;">Remission achieved</td>
					<td style="border:1px solid gray;">Date of Remission</td>
					<td style="border:1px solid gray;">Duration in Weeks</td>
					<td style="border:1px solid gray;">Immuno-<br />supression</td>
					<td style="border:1px solid gray;">Monoclonal</td>
					<td style="border:1px solid gray;">Trigger</td>
					</tr>
					<tr>
					<td style="border:1px solid gray;">
                        <asp:TextBox ID="TextBox1" runat="server" Width="80px" ></asp:TextBox>
                        <cc1:CalendarExtender ID="TextBox1_CalendarExtender" runat="server" 
                            Enabled="True" TargetControlID="TextBox1" Format="dd-MMM-yyyy"  >
                        </cc1:CalendarExtender> 
                        </td>
					<td style="border:1px solid gray; text-align:center">
                        <asp:CheckBox ID="CheckBox1" runat="server" />
                        </td>
					<td style="border:1px solid gray;">
                        <asp:TextBox ID="TextBox2" runat="server" Width="80px"></asp:TextBox>
                        <cc1:CalendarExtender ID="TextBox2_CalendarExtender" runat="server" 
                            Enabled="True" TargetControlID="TextBox2" Format="dd-MMM-yyyy">
                        </cc1:CalendarExtender>
                        </td>
					<td style="border:1px solid gray;">&nbsp;</td>
					<td style="border:1px solid gray;">&nbsp;</td>
					<td style="border:1px solid gray;">&nbsp;</td>
					<td style="border:1px solid gray;">&nbsp;</td>
					</tr>
					</table>
					
					</td>
				</tr>--%> 
				<%--<tr id="rowSpacer2" runat="server">
					<td colspan="2">&nbsp;</td>
				</tr>--%>
                                <tr>
                                    <td colspan="2">
                                        Infection necessitating hospitalisation
                                        <asp:RadioButton ID="radCompInfectYes" runat="server" AutoPostBack="true" 
                                            GroupName="grpInfect" />
                                        Yes
                                        <asp:RadioButton ID="radCompInfectNo" runat="server" AutoPostBack="true" 
                                            GroupName="grpInfect" />
                                        No
                                    </td>
                                </tr>
                                <tr ID="rowType" runat="server">
                                    <td colspan="2">
                                        <asp:TextBox ID="txtINFECTION_DETAIL" runat="server" Rows="4" 
                                            TextMode="MultiLine" Width="100%"></asp:TextBox>
                                    </td>
                                </tr>
                                <tr ID="rowDetails" runat="server">
                                    <td>
                                        &nbsp;</td>
                                    <td>
                                        &nbsp;</td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        Thrombosis
                                        <asp:RadioButton ID="radTYes" runat="server" AutoPostBack="True" 
                                            BorderWidth="0px" GroupName="grpTh" />
                                        &nbsp;Yes<asp:RadioButton ID="radTNo" runat="server" AutoPostBack="True" 
                                            BorderWidth="0px" groupname="grpTh" />
                                        &nbsp;No</td>
                                </tr>
                                <tr ID="rowCOMP_THROMBOSIS_DETAIL" runat="server">
                                    <td colspan="2">
                                        <asp:TextBox ID="txtCOMP_THROMBOSIS_DETAIL" runat="server" Rows="4" TextMode="MultiLine" 
                                            Width="100%" MaxLength="350"></asp:TextBox>
                                    </td>
                                </tr>
                                <tr id="rowHypertension" runat="server">
                                    <td colspan="2">
                                        Hypertension requiring RX:&nbsp;&nbsp;
                                        <asp:RadioButton ID="radHyperYes1" runat="server" GroupName="hyperten" 
                                            Text="Yes" />
                                        &nbsp;<asp:RadioButton ID="radHyperNo1" runat="server" GroupName="hyperten" 
                                            Text="No" />
                                        &nbsp;<asp:RadioButton ID="radHyperUn1" runat="server" GroupName="hyperten" 
                                            Text="Un" ToolTip="Unknown" />
                                    </td>
                                </tr>
                                <tr id="rowCKD" runat="server">
                                    <td colspan="2">
                                        CKD Stage:&nbsp;&nbsp;
                                        <asp:RadioButton ID="radCKD1" runat="server" GroupName="CKD" Text="1" />
                                        &nbsp;<asp:RadioButton ID="radCKD2" runat="server" GroupName="CKD" Text="2" />
                                        &nbsp;<asp:RadioButton ID="radCKD3" runat="server" GroupName="CKD" Text="3" />
                                        &nbsp;<asp:RadioButton ID="radCKD4" runat="server" GroupName="CKD" Text="4" />
                                        &nbsp;<asp:RadioButton ID="radCKD5" runat="server" GroupName="CKD" Text="5" />
                                        &nbsp;<asp:RadioButton ID="radCKDUn" runat="server" GroupName="CKD" Text="Un" />
                                    </td>
                                </tr>
                                			

                            </table>
                        </fieldset>
                    </td>
                </tr><tr>
				<td>
				
					&nbsp;</td>
			    <td>
                    &nbsp;</td>
			</tr>
			    <tr ID="rowTX_Listed" runat="server">
                    <td>
                        <asp:Label ID="lblTx" runat="server" Text="Listed for Transplant" 
                            CssClass="indent"></asp:Label>
                        ?</td>
                    <td>
                        <asp:RadioButton ID="radTxYes" runat="server" GroupName="Tx" Text="Yes" 
                            ToolTip="Yes" />
                        &nbsp;<asp:RadioButton ID="radTxNo" runat="server" GroupName="Tx" Text="No" 
                            ToolTip="No" />
                    </td>
                </tr>
			</table>
			
			<%--<fieldset id="fsRelapse" runat="server"><legend>RELAPSE  pre/post transplant</legend>
	<table style="width:100%; font-size:85%; margin-top:10px; border-collapse:collapse" cellpadding="5px">
		<tr>
			<td>Relapse since last visit</td>
			<td >
                <asp:TextBox ID="TextBox31" runat="server"></asp:TextBox>
            </td>
		</tr>
		<tr>
			<td>Length of relapse</td>
			<td >
			<asp:TextBox id="TextBox32" runat="server"></asp:TextBox>
			</td>
		</tr>
		<tr>
			<td>Viral trigger</td>
			<td >
			<asp:TextBox id="TextBox33" runat="server"></asp:TextBox>
			</td>
		</tr>
		<tr>
			<td>Immunisation Trigger</td>
			<td >
			<asp:TextBox id="TextBox34" runat="server"></asp:TextBox>
			</td>
		</tr>
		<tr>
			<td>Other Trigger</td>
			<td >
			<asp:TextBox id="TextBox35" runat="server"></asp:TextBox>
			</td>
		</tr>
	</table>
	</fieldset>--%>
			
			</td>
			</tr>
			<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			</tr><tr>
			<td>
                <asp:Button ID="btnUpdate" runat="server" Text="Save" CausesValidation="true" 
                    style="height: 24px" CssClass="saveBtn" />
                &nbsp;&nbsp;
                <asp:Button ID="btnSaveNew" runat="server" Text="Add" CausesValidation="true" 
                    CssClass="saveBtn" />
                &nbsp;<asp:Label ID="lblUpdate" runat="server" Text=""></asp:Label>
                <asp:ValidationSummary ID="ValidationSummary1" runat="server" 
                    DisplayMode="List" HeaderText="One or more required fields missing" 
                    ShowMessageBox="True" ShowSummary="False" />
                </td>
			<td>&nbsp;</td>
			</tr>
			<tr>
			<td>
                <asp:Label ID="lblDebug" runat="server" Text=""></asp:Label>
                </td>
			<td>&nbsp;</td>
			</tr>
			</table>
			</asp:Panel>
			
			 <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
            ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
            SelectCommand="SELECT [pID], [pDesc] FROM [tbl_PHENOTYPES] ORDER BY [pID]">
        </asp:SqlDataSource>
        <asp:SqlDataSource ID="SqlDataSource2" runat="server" 
                ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
                SelectCommand="SELECT [cID], [DATE_CLIN_PIC], [SEQ_NO] FROM [tbl_ClinicalData] WHERE (([SEQ_NO] >= @SEQ_NO) AND ([RADAR_NO] = @RADAR_NO)) ORDER BY [DATE_CLIN_PIC] DESC">
                <SelectParameters>
                    <asp:Parameter DefaultValue="0" Name="SEQ_NO" Type="Int32" />
                    <asp:SessionParameter Name="RADAR_NO" SessionField="patientID" Type="Int32" />
                </SelectParameters>
            </asp:SqlDataSource>

</div>
<br />
    <asp:Label ID="lblPage" runat="server" Text=""></asp:Label>

</div>
			

</asp:Content>

