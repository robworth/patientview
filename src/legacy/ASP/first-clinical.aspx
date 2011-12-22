<%@ Page Title="RADAR - Clinical Results" Language="VB" MasterPageFile="~/MasterPage.master" AutoEventWireup="false" CodeFile="first-clinical.aspx.vb" Inherits="first_clinical" %>
<%@ Register assembly="DevExpress.Web.ASPxEditors.v9.2, Version=9.2.4.0, Culture=neutral, PublicKeyToken=b88d1754d700e49a" namespace="DevExpress.Web.ASPxEditors" tagprefix="dxe" %>
<%@ Register Assembly="DevExpress.Web.v9.2, Version=9.2.4.0, Culture=neutral, PublicKeyToken=b88d1754d700e49a"
    Namespace="DevExpress.Web.ASPxPopupControl" TagPrefix="dxpc" %>
<%@ Register assembly="AjaxControlToolkit" namespace="AjaxControlToolkit" tagprefix="cc1" %>

<asp:Content ID="Content1" Runat="Server" ContentPlaceHolderID="head">
    <link href="tabs_lab.css" rel="stylesheet" type="text/css" />
<link href="tabs.css" rel="stylesheet" type="text/css" />
<style type="text/css">

input, select, textarea {
border: 1px solid gray;
}
select, input, textarea {
font-size: 95%;
color: #2D2E2E;
}
.style2 {
background-color: #B6DF9F;
text-align: right;
}

.style4 {
text-align: right;
}
#subBlock table {
margin: 0;
}
.indent {
padding-left: 40px;
}

.noborder {
border: 0;
}


.style6
{
height: 17px;
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
#ctl00_ContentPlaceHolder1_rowAnemia input{
border:0;
}
#ctl00_ContentPlaceHolder1_chkClinicalEvidence, #ctl00_ContentPlaceHolder1_chkOpthalm, #ctl00_ContentPlaceHolder1_chkPreceed {
border:0;
}

#ctl00_ContentPlaceHolder1_radOpthalmYes, #ctl00_ContentPlaceHolder1_radOpthalmNo, #ctl00_ContentPlaceHolder1_radOpthalmUn {
border:0;
}
    
/* this because of the update panel for the tabs */
/*#tabsC {
margin-top:-35px;
}
*/
#tabsLab {

margin:-20px 0 0 -40px;
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
.inline2 {
display: inline;
} 
#tabsC {
margin-top:0px;
}
     
</style>
<![endif]-->
</asp:Content>
<asp:Content ID="Content2" Runat="Server" ContentPlaceHolderID="ContentPlaceHolder1">
   
    <div id="tabsC" > 
    <ul> 
        <li><asp:hyperlink runat="server" ID="lnkDemographics" navigateurl="demographics.aspx" Tooltip="Demographics"><span>Demographics</span></asp:hyperlink></li> 
        <li><asp:hyperlink runat="server" ID="lnkDiagnosis" NavigateUrl="diagnosis.aspx" ToolTip="Diagnosis"><span>Diagnosis</span></asp:hyperlink></li> 
        <li><asp:hyperlink runat="server" ID="lnkLabResults" NavigateUrl="first-clinical.aspx" Tooltip="Laboratory Results"><span class="hovered">First Visit</span></asp:hyperlink></li> 
        <li><asp:hyperlink runat="server" ID="lnk3Months" NavigateUrl="followup-clinical.aspx" Tooltip="Follow Up"><span>Follow Up</span></asp:hyperlink></li> 
        <li><asp:hyperlink runat="server" ID="lnkPathology" NavigateUrl="pathology.aspx" Tootip="Pathology"><span>Pathology</span></asp:hyperlink></li> 
        <li><asp:hyperlink runat="server" ID="lnkRelapse" NavigateUrl="relapse.aspx" Tooltip="Relapse"><span>Relapse</span></asp:hyperlink></li> 
        <li><asp:hyperlink runat="server" ID="lnkHospital" NavigateUrl="hospitalisation.aspx" Tooltip="Hospitalisation"><span>Hospitalisation</span></asp:hyperlink></li> 
        <li><asp:hyperlink runat="server" ID="lnkTimelines" NavigateUrl="timelines.aspx" Tootip="Timelines"><span>Timelines</span></asp:hyperlink></li> 
    </ul> 
</div> 
<div id="mainBlock">
<div id="tabsLab" >
<ul>
	<li><asp:hyperlink runat="server" ID="Hyperlink1" NavigateUrl="#" Tooltip="Clinical Picture"><span class="hovered">Clinical Picture</span></asp:hyperlink></li> 
    <li><asp:hyperlink runat="server" ID="lnkFirstLab" NavigateUrl="first-lab.aspx" Tootip="Laboratory Results"><span>Laboratory Results</span></asp:hyperlink></span></li> 
    <li><asp:hyperlink runat="server" ID="lnkFirstTreatment" NavigateUrl="first-treatment.aspx" Tooltip="Treatment"><span>Treatment</span></asp:hyperlink></span></li> 
</ul>
</div>
<div id="subBlock">

<table cellpadding="5" class="style1" style="width: 100%; background-color: #B6DF9F;">
	<tr>
        
		<td>
            <asp:Label ID="lblTitle" runat="server" Font-Bold="True"></asp:Label>
        </td>
		<td class="style2"><strong>RADAR No.</strong></td>
	    <td class="style2" style="width:120px;">
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
            <asp:TextBox ID="txtDateClinicalPicture" runat="server" Width="70px"></asp:TextBox>
           
            <cc1:CalendarExtender ID="txtDateClinicalPicture_CalendarExtender" 
                runat="server" Enabled="True" TargetControlID="txtDateClinicalPicture" PopupButtonID="ImageButton1" Format="dd-MM-yyyy">
            </cc1:CalendarExtender>&nbsp;<asp:ImageButton ID="ImageButton1" runat="server" CausesValidation="False" ImageUrl="~/images/Calendar_scheduleHS.png"  />
           
            <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" 
                ControlToValidate="txtDateClinicalPicture" ErrorMessage="" Text="*" Font-Bold="True" SetFocusOnError="true"></asp:RequiredFieldValidator></td>
		<td class="style2"><strong>Diagnosis</strong></td>
	    <td class="style2">
            <asp:TextBox ID="txtDIAGNOSIS" runat="server" BackColor="#FFFF99" 
                ReadOnly="True" Width="100px"></asp:TextBox>
        </td>
	</tr>
</table>
<table style="width: 100%; background-color:#EEF6ED;" class="style1" id="mainForm" runat="server">
	<tr>
		<td style="width:48%; vertical-align:top;">
		<table style="width: 100%">
			<tr>
				<td colspan="2" style="height:40px; vertical-align:top;">
                    <asp:Button ID="btnSubmit2" runat="server" CssClass="saveBtn" Text="Save" />
                    &nbsp;
				    <asp:Label ID="lblUpdate2" runat="server"></asp:Label>
				</td>
			</tr>
			<tr>
				<td class="style4" style="width: 140px" >Height cm</td>
				<td>
                    <asp:TextBox ID="txtHeight" runat="server" Width="40px"></asp:TextBox>
                    &nbsp;<asp:RangeValidator ID="RangeValidator3" runat="server" 
                        ControlToValidate="txtHeight" ErrorMessage="35.0-185.0" MaximumValue="185.0" 
                        MinimumValue="35.0" Type="Double"></asp:RangeValidator>
                </td>
			</tr>
			<tr>
				<td class="style4" style="width: 140px">Weight Kg</td>
				<td>
                    <asp:TextBox ID="txtWeight" runat="server" Width="40px"></asp:TextBox>
                    &nbsp;<asp:RangeValidator ID="RangeValidator4" runat="server" 
                        ControlToValidate="txtWeight" ErrorMessage="3.0 - 100" MaximumValue="100" 
                        MinimumValue="3.0" Type="Double"></asp:RangeValidator>
                    <asp:CompareValidator ID="CompareValidator1" runat="server" 
                        ControlToCompare="txtBPSys" ControlToValidate="txtBPDia" Display="Dynamic" 
                        ErrorMessage="Sys to be &gt; dia" Operator="LessThan" Type="Integer"></asp:CompareValidator>
                </td>
			</tr>
			<tr>
				<td class="style4" style="width: 140px">BP&nbsp;</td>
				<td>
                    <asp:TextBox ID="txtBPSys" runat="server" Width="40px"></asp:TextBox>&nbsp;<strong>/</strong>
                &nbsp;<asp:TextBox id="txtBPDia" runat="server" Width="40px"></asp:TextBox>
                &nbsp;
					<asp:TextBox id="txtBPMap" runat="server" Width="40px" ReadOnly="True"></asp:TextBox>
                    &nbsp;MAP</td>
			</tr>
			<tr>
				<td style="width: 140px" align="right">
                    <asp:RangeValidator ID="RangeValidator1" runat="server" 
                        ControlToValidate="txtBPSys" ErrorMessage="Sys BP 50-200" MaximumValue="200" 
                        MinimumValue="50" Type="Integer"></asp:RangeValidator>
                </td>
				<td>
				    <asp:RangeValidator ID="RangeValidator2" runat="server" 
                        ControlToValidate="txtBPDia" CssClass="inline" ErrorMessage="DAI BP 20-150" 
                        MaximumValue="150" MinimumValue="20" Type="Integer"></asp:RangeValidator>
                </td>
			</tr>
			<tr>
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
			<tr>
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
			<tr>
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
			<tr>
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
                <td style="width: 140px">
                    &nbsp;</td>
                <td>
                    &nbsp;</td>
            </tr>
            <tr>
                <td style="width: 140px">
                    &nbsp;</td>
                <td>
                    &nbsp;</td>
            </tr>
			<tr>
				<td style="width: 140px">Comments&nbsp; <a class="info" href="#">Help <span>Enter any 
                    other relevant clinical features</span></a></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td style="width: 140px">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" >
                    <asp:TextBox ID="txtComments" runat="server" Rows="15" TextMode="MultiLine" 
                        Width="100%"></asp:TextBox>
                </td>
				
			</tr>
			<tr>
				<td style="width: 140px">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td style="width: 140px">&nbsp;</td>
				<td>
				    &nbsp;</td>
			</tr>
			<tr>
				<td style="width: 140px">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td style="width: 140px">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td style="width: 140px">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2">
				    &nbsp;<asp:Button ID="btnSubmit" Text="Save" runat="server" 
                        CausesValidation="true" CssClass="saveBtn"></asp:Button>
				    &nbsp;<asp:ValidationSummary ID="ValidationSummary1" runat="server" 
                        DisplayMode="List" 
                        HeaderText="One or more required fields are missing or have incorrect values" 
                        ShowMessageBox="True" ShowSummary="False" />
				</td>
			</tr>
			<tr>
				<td colspan="2">
				&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2">
				    <asp:Label ID="lblUpdate" runat="server"></asp:Label>
				</td>
			</tr>
			<tr>
			<td>
                <%--</ContentTemplate>
    </asp:UpdatePanel>--%>
			</td>
			</tr>
		</table>
		</td>
		<td style="width:50%; vertical-align:top;">
		<table class="style1" style="width: 100%" cellpadding="4">
			<tr>
				<td style="width:200px;">
				<asp:Label id="lblID" runat="server" Visible="False" ></asp:Label>
				</td>
				<td>&nbsp;</td>
			</tr>
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
				<asp:textbox id="txtSigDiag1" runat="server" ToolTip="From Diagnosis page"></asp:textbox>
				</td>
			</tr>
			<tr>
				<td>Additional Significant Diagnosis 2</td>
				<td>
				<asp:TextBox id="txtSigDiag2" runat="server" ToolTip="From Diagnosis page"></asp:TextBox>
											</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>
				&nbsp;</td>
			</tr>
			<tr>
				<td class="indent">Oedema</td>
				<td class="style6">
				    <asp:RadioButton ID="radOedemaYes" runat="server" GroupName="oedema" 
                        Text="Yes" />
&nbsp;<asp:RadioButton ID="radOedemaNo" runat="server" GroupName="oedema" Text="No" />
&nbsp;<asp:RadioButton ID="radOedemaUnkn" runat="server" GroupName="oedema" Text="Un" 
                        ToolTip="Unknown" />
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
			<tr>
				<td class="indent">Hypovalaemia</td>
				<td>
				    <asp:RadioButton ID="radHypovYes" runat="server" GroupName="hypov" Text="Yes" />
&nbsp;<asp:RadioButton ID="radHypovNo" runat="server" GroupName="hypov" Text="No" />
&nbsp;<asp:RadioButton ID="radHypovUn" runat="server" GroupName="hypov" Text="Un" 
                        ToolTip="Unknown" />
											</td>
			</tr>
			<tr>
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
			<tr>
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
			<tr id="rowRash" runat="server">
				
				<td colspan="2" class="indent">
				<asp:textbox id="txtRashDetail" runat="server" Rows="4" TextMode="MultiLine" 
                        Width="350px"></asp:textbox>
</td>
			</tr>
			<tr>
				<td class="indent">Possible Immunisation trigger</td>
				<td>
				    <asp:RadioButton ID="radImmTrigYes" runat="server" GroupName="radImmTrig" 
                        Text="Yes" />
&nbsp;<asp:RadioButton ID="radImmTrigNo" runat="server" GroupName="radImmTrig" Text="No" />
&nbsp;<asp:RadioButton ID="radImmTrigUn" runat="server" Text="Un" GroupName="radImmTrig" />
											</td>
			</tr>
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
				<td class="indent">Preceeding Infection ( inactive)</td>
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
			<asp:TextBox ID="txtInfectionDetail" runat="server" Rows="4" Width="350px" 
                    TextMode="MultiLine"></asp:TextBox>
			    
			    <cc1:TextBoxWatermarkExtender ID="txtInfectionDetail_TextBoxWatermarkExtender" 
                    runat="server" Enabled="True" TargetControlID="txtInfectionDetail" WatermarkText="Enter details" WatermarkCssClass="grey">
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
			<asp:TextBox ID="txtClinicalEvidence" runat="server" Rows="4" Width="350px" 
                    TextMode="MultiLine"></asp:TextBox>
			    <cc1:TextBoxWatermarkExtender ID="txtClinicalEvidence_TextBoxWatermarkExtender" 
                    runat="server" Enabled="True" TargetControlID="txtClinicalEvidence" WatermarkCssClass="grey" WatermarkText="Enter details">
                </cc1:TextBoxWatermarkExtender>
			</td>
			</tr>
			<tr>
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
			<tr>
				<td class="indent" colspan="2">
				<asp:textbox id="txtOpDetail" runat="server" Rows="5" TextMode="MultiLine" 
                        Visible="false" Width="350px"></asp:textbox>
				    <cc1:TextBoxWatermarkExtender ID="txtOpDetail_TextBoxWatermarkExtender" 
                        runat="server" Enabled="True" TargetControlID="txtOpDetail" WatermarkCssClass="grey" WatermarkText="If abnormal describe result, eg hypertensive, retinopathy or Drusen">
                    </cc1:TextBoxWatermarkExtender>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
        &nbsp;&nbsp;<asp:Label ID="lblDebug" runat="server"></asp:Label>
        <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
            ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
            SelectCommand="SELECT [pID], [pDesc] FROM [tbl_PHENOTYPES] ORDER BY [pID]">
        </asp:SqlDataSource>
<%--</ContentTemplate>
    </asp:UpdatePanel>--%>
</div>
<br />

<asp:Label ID="lblPage" runat="server" Text=""></asp:Label>
</div>
</asp:Content>
