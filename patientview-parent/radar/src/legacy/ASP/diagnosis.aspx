<%@ Page Title="" Language="VB" MasterPageFile="~/MasterPage.master" AutoEventWireup="false" CodeFile="diagnosis.aspx.vb" Inherits="diagnosis" Debug="true" %>
<%@ Register assembly="DevExpress.Web.ASPxEditors.v9.2, Version=9.2.4.0, Culture=neutral, PublicKeyToken=b88d1754d700e49a" namespace="DevExpress.Web.ASPxEditors" tagprefix="dxe" %>

<%@ Register assembly="AjaxControlToolkit" namespace="AjaxControlToolkit" tagprefix="cc1" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
    <link href="tabs.css" rel="stylesheet" type="text/css" />

<style type="text/css">

table {
table-layout:fixed;
}	
		
input, select, textarea {
border: 1px solid gray;
padding-left:3px;
}

select, input, {
font-size:95%;
color:#2D2E2E;
height: 20px;
        }
 textarea  {
 font-size:95%;
 }      
        
.genePadding {
padding-left:12px;
}

.frmView {
margin-top:0;
}

.style2 {
background-color: #B6DF9F;
table-layout:fixed;
}

#mainBlock table {
margin:0;
}

#ctl00_ContentPlaceHolder1_CheckBox10 {
border:0;
}
#ctl00_ContentPlaceHolder1_radBPS {
	border:0;
}
#ctl00_ContentPlaceHolder1_radPresSR {
	border:0;
}

#ctl00_ContentPlaceHolder1_radSSR {
	border:0;
}

#ctl00_ContentPlaceHolder1_radPrimarySteroid {
	border:0;
}

select {

font-size:80%;
}

.relRADAR {
padding-left:145px;
font-size:80%;
}

#rowGeneMutate input {
border:0;
}
/* this because of the update panel for the tabs
#tabsC {
margin-top:-35px;
}
 */

.style3
{
height: 28px;
}
   
.style4
{
width: 380px;
border-collapse: collapse;
margin-left:30px;
font-size:85%;
       
}

#tblGeneMutate input {
border:0;
}

.auto-style2 {
text-align: center;
}

.auto-style3 {
text-align: center;
cursor:help;
}
</style>


<!--[if IE]>
<style type="text/css">
#mainBlock {
margin-top:0;
}
#tabsC {
margin-top:0;
}    
   
   
</style>
<![endif]-->	
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <div id="tabsC"  > 
  <ul> 
    <li><asp:linkbutton runat="server" ID="lnkDemographics" tooltip="Demographics"><span>Demographics</span></asp:linkbutton></li> 
    <li><asp:linkbutton runat="server" ID="lnkDiagnosis" ToolTip="Diagnosis"><span class="hovered">Diagnosis</span></asp:linkbutton></li> 
    <li><asp:linkbutton runat="server" ID="lnkLabResults" Tootip="First Visit"><span>First Visit</span></asp:linkbutton></li> 
    <li><asp:linkbutton runat="server" ID="lnk3Months" Tooltip="Follow Up"><span>Follow Up</span></asp:linkbutton></li> 
    <li><asp:hyperlink runat="server" ID="lnkPathology" NavigateUrl="pathology.aspx" Tootip="Pathology"><span>Pathology</span></asp:hyperlink></li> 
    <li><asp:hyperlink runat="server" ID="lnkRelapse" NavigateUrl="relapse.aspx" Tooltip="Relapse"><span>Relapse</span></asp:hyperlink></li> 
    <li><asp:hyperlink runat="server" ID="lnkHospital" NavigateUrl="hospitalisation.aspx" Tooltip="Hospitalisation"><span>Hospitalisation</span></asp:hyperlink></li> 
    <li><asp:hyperlink runat="server" ID="lnkTimelines" NavigateUrl="timelines.aspx" Tootip="Timelines"><span>Timelines</span></asp:hyperlink></li> 
  </ul> 
</div> 
<div id="mainBlock">

  <table cellpadding="5" class="style2" style="width: 100%;">
	<tr>
		<td style="width:70%"><strong>Diagnosis</strong></td>
		<td class="style2" align="right">RADAR NO:&nbsp;
            </td>
	    <td class="style2" style="width:120px;">
            <asp:TextBox ID="RADAR_NOTextbox" runat="server" ReadOnly="True" Text="" 
                Width="100px" />
        </td>
	</tr>
	<tr>
		<td>Name 
            <asp:TextBox ID="FNAMETextbox" runat="server" Text='' 
                ReadOnly="True" Width="100px" />&nbsp;<asp:TextBox ID="SNAMETextbox" runat="server" 
                Text='' ReadOnly="True" Width="100px" /> &nbsp;DoB&nbsp; 
            <asp:TextBox ID="DOBTextbox" runat="server" 
                Text='' Width="80px" 
                ReadOnly="True" />&nbsp;<asp:TextBox ID="txtDateToday" runat="server" 
                BackColor="#B6DF9F" BorderColor="#B6DF9F" ForeColor="#B6DF9F"></asp:TextBox>
        </td>
		<td class="style2" align="right">Hospital No:
            </td>
	    <td class="style2">
            <asp:TextBox ID="HOSP_NOTextbox" runat="server" ReadOnly="True" Text="" 
                Width="100px" />
        </td>
	</tr>
</table>
<table style="width: 100%; background-color:#EEF6ED;" class="style1" cellpadding="0" cellspacing="0" runat="server" id="mainForm">
	<tr>
		<td valign="top" style="width:45%;">
		<table cellpadding="5" class="style1" style="width: 95%">
			<tr>
				<td colspan="2" style="height:35px;">
                    <asp:Button ID="btnUpdate2" runat="server" CssClass="saveBtn" Text="Save" />
&nbsp;<asp:Label ID="lblUpdate2" runat="server"></asp:Label>
                </td>
			</tr>
			<tr >
				<td class="style3"><asp:Label ID="lblDiagnosis" runat="server" 
                        AssociatedControlID="DropDownList7" Text="Diagnosis"></asp:Label>
                    
                </td>
				<td>
                    <asp:DropDownList ID="DropDownList7" runat="server" AppendDataBoundItems="True" 
                        DataSourceID="SqlDataSource3" DataTextField="dcAbbr" DataValueField="dcID" 
                         TabIndex="1" 
                        onselectedindexchanged="DropDownList7_SelectedIndexChanged" AutoPostBack="true" >
                        <asp:ListItem Value="">Select</asp:ListItem>
                    </asp:DropDownList>
                    
                    &nbsp;<asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" 
                        ControlToValidate="DropDownList7" SetFocusOnError="True">*</asp:RequiredFieldValidator>
&nbsp;<asp:Label ID="lbldID" runat="server" Text="" Visible="false"></asp:Label>
                    
                    </td>
			</tr>
			<tr>
				<td style="vertical-align:top;">
                    <asp:Label ID="lblDiagText" runat="server" 
                        AssociatedControlID="DIAG_TXTTextbox" Text="Diagnosis Text"></asp:Label>
                </td>
				<td><asp:TextBox ID="DIAG_TXTTextbox" runat="server" 
                Text='' TabIndex="3" Rows="3" TextMode="MultiLine" Width="90%" Font-Size="95%" /></td>
			</tr>
			<tr>
				<td><asp:label runat="server" ID="lblDateDiag" Text="Date of diagnosis"></asp:label>&nbsp;&nbsp;<asp:RequiredFieldValidator ID="RequiredFieldValidator1" 
                        runat="server" ControlToValidate="txtDateDiag" SetFocusOnError="True">*</asp:RequiredFieldValidator>
                    &nbsp;</td>
				<td><asp:TextBox ID="txtDateDiag" runat="server" Text='' Width="80px"></asp:TextBox>
				
				    <cc1:CalendarExtender ID="txtDateDiag_CalendarExtender" runat="server" 
                        Enabled="True" TargetControlID="txtDateDiag" PopupButtonID="ImageButton1" Format="dd-MM-yyyy" >
                    </cc1:CalendarExtender><asp:ImageButton ID="ImageButton1" runat="server" CausesValidation="False" ImageUrl="~/images/Calendar_scheduleHS.png"  />
				
				    &nbsp;<a runat="server" id="hlpDiag"  href="#" class="info" >Help<span runat="server" id="diagHelp"></span></a></td>
			</tr>
			<tr>
				<td>
                    <asp:Label ID="lblESRF" runat="server" Text="Date of ESRF"></asp:Label>
                    </td>
				<td>
                    <asp:TextBox ID="txtDATE_ESRF" runat="server" Width="80px"></asp:TextBox>
                    <cc1:CalendarExtender ID="txtDATE_ESRF_CalendarExtender" runat="server" 
                        Enabled="True" TargetControlID="txtDATE_ESRF" PopupButtonID="imgBtnDATE_ESRF" Format="dd-MM-yyyy" >
                    </cc1:CalendarExtender>
                    <asp:ImageButton ID="imgBtnDATE_ESRF" runat="server" 
                        ImageUrl="~/images/Calendar_scheduleHS.png" />
                </td>
			</tr>
			<tr>
				<td>
                    <asp:Label ID="lblAge" runat="server" AssociatedControlID="AGE_AT_DIAGTextbox" 
                        Text="Age at Diagnosis"></asp:Label>
                </td>
				<td><asp:TextBox ID="AGE_AT_DIAGTextbox" runat="server" 
                Text='' Width="50px" ReadOnly="true" />&nbsp; 
                    <asp:Label ID="lblCalc" runat="server" Font-Italic="True" Text="calc"></asp:Label>
                </td>
			</tr>
			<tr>
				<td>
                    <asp:Label ID="lblPubertal" runat="server" AssociatedControlID="CheckBox10" 
                        Text="Prepubertal at Diagnosis"></asp:Label>
                </td>
				<td><asp:CheckBox ID="CheckBox10" runat="server" TabIndex="5" /></td>
			</tr>
			<tr>
				<td>
                    <asp:Label ID="lblHeight" runat="server" AssociatedControlID="TextBox11" 
                        Text="Height at diagnosis in cm"></asp:Label>
                </td>
				<td><asp:TextBox ID="TextBox11" runat="server" 
                Text='' TabIndex="6" Width="50px" />&nbsp;<asp:RangeValidator ID="RangeValidator1" 
                        runat="server" ControlToValidate="TextBox11" ErrorMessage="Height" 
                        MaximumValue="185.0" MinimumValue="35.0" SetFocusOnError="True" 
                        Type="Double">35.0 - 185.0 cm</asp:RangeValidator>
                </td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>
                    <asp:Label ID="lblClinicalA" runat="server" AssociatedControlID="DropDownList8" 
                        Text="Clinical Presentation A"></asp:Label>
                </td>
				<td>
                    <asp:DropDownList ID="DropDownList8" runat="server" AppendDataBoundItems="True" 
                        DataSourceID="SqlDataSource4" DataTextField="CLIN_PRES" 
                        DataValueField="cID" AutoPostBack="True" >
                        <asp:ListItem Value="">Select</asp:ListItem>
                    </asp:DropDownList>
                    
                &nbsp;<asp:Label ID="lblWarnA" runat="server" ForeColor="Red" 
                        Text="Can't be the same as B" Visible="False"></asp:Label>
                    
                </td>
			</tr>
			<tr>
				<td class="style3">
                    <asp:Label ID="lblClinicalB" runat="server" Text="Clinical Presentation B"></asp:Label>
                </td>
				<td class="style3">
                    <asp:DropDownList ID="DropDownList14" runat="server" AppendDataBoundItems="true" 
                        DataSourceID="SqlDataSource4" DataTextField="CLIN_PRES" 
                        DataValueField="cID" AutoPostBack="True">
                          <asp:ListItem Value="">Select</asp:ListItem>
                    </asp:DropDownList>
                &nbsp;<asp:Label ID="lblWarnB" runat="server" ForeColor="Red" 
                        Text="Can't be the same as A" Visible="False"></asp:Label>
                </td>
			</tr>
			<tr>
				<td>
                    &nbsp;</td>
				<td>&nbsp;
                    &nbsp;</td>
			</tr>
			<tr id="rowPSS" runat="server">
				<td>
                    <asp:Label ID="lblPSteroid" runat="server" Text="Primary Steroid Resistance" 
                        AssociatedControlID="radPrimarySteroid"></asp:Label>
                </td>
				<td>
                    <asp:RadioButton ID="radPrimarySteroid" runat="server" GroupName="steroidRes" />&nbsp;&nbsp;<asp:HyperLink NavigateUrl="#" id="hypSteroid" visible="false" runat="server" CssClass="info">Help <span>no response (protein:creatinine ratio < 200mg/mmol) to four weeks of oral prednisolone with or without pulsed methylprednisolone</span></asp:HyperLink></td>
			</tr>
			<tr id="rowSSR" runat="server">
				<td>
                    <asp:Label ID="lblSSR" runat="server" AssociatedControlID="radSSR" 
                        Text="Secondary Steroid Resistance"></asp:Label>
                </td>
				<td>
                    <asp:RadioButton ID="radSSR" runat="server" GroupName="steroidRes" />&nbsp;&nbsp;<asp:HyperLink NavigateUrl="#" id="hypSSR" visible="false" runat="server" CssClass="info">Help <span>following initial period of steroid sensitivity, during further relapse, no response (protein:creatinine ratio < 200mg/mmol) to four weeks of oral prednisolon</span></asp:HyperLink>
                </td>
			</tr>
			<tr id="rowPresSR" runat="server">
				<td>
                    <asp:Label ID="lblPresSR" runat="server" AssociatedControlID="radPresSR" 
                        Text="Presumed Steroid Resistance"></asp:Label>
                </td>
				<td>
                    <asp:RadioButton ID="radPresSR" runat="server" GroupName="steroidRes" />&nbsp;&nbsp;<asp:HyperLink NavigateUrl="#" id="hypPresSR" visible="false" runat="server" CssClass="info">Help <span>Where nephrotic syndrome is presumed steroid resistance due to clinical features as a whole but this has not been proven by course of steroids</span></asp:HyperLink>
                </td>
			</tr>
			<tr>
				<td>
                    <asp:Label ID="lblBPS" runat="server" AssociatedControlID="radBPS" 
                        Text="Biospy Proven FSGS"></asp:Label>
                </td>
				<td>
                    <asp:RadioButton ID="radBPS" runat="server" GroupName="steroidRes" />&nbsp;&nbsp;<asp:HyperLink NavigateUrl="#" id="hypBPS" visible="false" runat="server" CssClass="info">Help <span>But currently steroid responsive</span></asp:HyperLink>
                </td>
			</tr>
			<tr>
				<td>
                    <asp:Label ID="lblDateOnset" runat="server" 
                        AssociatedControlID="DATE_ONSET_RENALDISTextbox" 
                        Text="Date of onset of symptoms"></asp:Label>
                </td>
				<td>
                    <asp:TextBox ID="DATE_ONSET_RENALDISTextbox" runat="server" 
                Text='' Width="80px" />
                    <cc1:CalendarExtender ID="DATE_ONSET_RENALDISTextbox_CalendarExtender" 
                        runat="server" Enabled="True" TargetControlID="DATE_ONSET_RENALDISTextbox" PopupButtonID="btnDate1" Format="dd-MM-yyyy">
                    </cc1:CalendarExtender><asp:ImageButton ID="btnDate1" runat="server" 
                        ImageUrl="~/images/Calendar_scheduleHS.png" CausesValidation="false" />
&nbsp;<a href="#" class="info">Help <span runat="server" id="onsetHelp"></span></a><asp:Label ID="lblDateWarn" runat="server" ForeColor="Red" Text=""/>
                </td>
			</tr>
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			</table>
			<fieldset style="padding:5px; border:1px solid green; margin-left:20px; width:85%"><legend >
               <asp:Label ID="lblAdditional" runat="server" Text="Additional Significant Diagnosis"></asp:Label> </legend>
			<table style="width:95%; margin-left:20px;" class="style1">
			<tr>
                <td colspan="2">&nbsp;</td>
            </tr>
			<tr>
				<td colspan="2">1&nbsp;&nbsp;<asp:TextBox ID="SIG_DIAG1Textbox" runat="server" 
                Text='' Width="300px" /></td>
			</tr>
			<tr>
				<td colspan="2">2&nbsp;&nbsp;<asp:TextBox ID="SIG_DIAG2Textbox" runat="server" 
                Text='' Width="300px" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		    
            
		</table>
		</fieldset>
		<br /><br />
		&nbsp;<asp:Button ID="UpdateButton" runat="server" CausesValidation="True" 
                        CommandName="Update" Text="Save" CssClass="saveBtn" />
                    <asp:Button ID="UpdateCancelButton" runat="server" CausesValidation="False" 
                        CommandName="Cancel" Text="Cancel" Visible="False" />
		    &nbsp;
            <asp:Label ID="lblUpdate" runat="server" />
		    &nbsp;<asp:ValidationSummary ID="ValidationSummary1" runat="server" 
                DisplayMode="SingleParagraph" 
                HeaderText="One or more required fields are missing or outside limits" CssClass="inline" 
                ShowMessageBox="True" ShowSummary="False" />
		</td>
		<td valign="top" style="width:55%;">
		<table cellpadding="5" style="width: 100%; background-color:#EEF6ED;" id="rhTable">
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td style="width:200px">
                    <asp:Label ID="lblBiopsy" runat="server" AssociatedControlID="DropDownList11" 
                        Text="Biopsy Proven Diagnosis"></asp:Label>
                </td>
				<td>
                  <asp:DropDownList ID="DropDownList11" runat="server">
                        <asp:ListItem Selected="True" Value="">Select</asp:ListItem>
                        <%--<asp:ListItem Value="1">Yes</asp:ListItem>
                        <asp:ListItem Value="0">No</asp:ListItem>--%>
                    </asp:DropDownList>
                </td>
			</tr>
			<tr id="rowGeneLabel" runat="server">
				<td colspan="2">Gene Mutation</td>
				
			</tr>
			<tr id="rowGeneMutateA" runat="server">
				<td colspan="2" style="padding-left:30px;">
                    <table cellpadding="3" class="style4" id="tblGeneMutate">
                        <tr style="font-weight:bold; color:Green; text-align:center"; valign="baseline" align="center">
                            <td style="font-weight:normal; color:#000; text-align:left">
                                &nbsp;</td>
                            <td style="width:30px" class="auto-style3">
                                <span title="Yes"> Y </span></td>
                            <td style="width:30px" class="auto-style3">
                                <span title="No"> N </span></td>
                            <td style="width:30px" class="auto-style3">
                                <span title="Sent"> S </span></td>
                            <td style="width:30px" class="auto-style3">
                                <span title="Not sent"> NS </span></td>
                        </tr>
                        <tr>
                            <td>
                                NPHS1 - NEPHRIN</td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radNPHS1Y" runat="server" GroupName="nphs1" 
                                    AutoPostBack="True" />
                            </td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radNPHS1N" runat="server" GroupName="nphs1" 
                                    AutoPostBack="True" />
                            </td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radNPHS1S" runat="server" GroupName="nphs1s" />
                            </td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radNPHS1NS" runat="server" GroupName="nphs1s" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                NPHS2 - PODOCIN</td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radNPHS2Y" runat="server" GroupName="nphs2" 
                                    AutoPostBack="True" />
                            </td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radNPHS2N" runat="server" GroupName="nphs2" 
                                    AutoPostBack="True" />
                            </td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radNPHS2S" runat="server" GroupName="nphs2s" />
                            </td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radNPHS2NS" runat="server" GroupName="nphs2s" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                NPHS3 - PLCE1</td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radNPHS3Y" runat="server" GroupName="nphs3" 
                                    AutoPostBack="True" />
                            </td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radNPHS3N" runat="server" GroupName="nphs3" 
                                    AutoPostBack="True" />
                            </td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radNPHS3S" runat="server" GroupName="nphs3s" />
                            </td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radNPHS3NS" runat="server" GroupName="nphs3s" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                WT1 - Wilm&#39;s Tumor</td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radWilmY" runat="server" GroupName="wilm" 
                                    AutoPostBack="True" />
                            </td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radWilmN" runat="server" GroupName="wilm" 
                                    AutoPostBack="True" />
                            </td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radWilmS" runat="server" GroupName="wilms" />
                            </td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radWilmNS" runat="server" GroupName="wilms" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                CD2AP</td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radCD2APYes" runat="server" GroupName="ad2ap" 
                                    AutoPostBack="True" />
                            </td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radCD2APNo" runat="server" GroupName="ad2ap" 
                                    AutoPostBack="True" />
                            </td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radCD2APS" runat="server" GroupName="ad2aps" />
                            </td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radCD2APNS" runat="server" GroupName="ad2aps" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                TRPC6</td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radTRPC6Y" runat="server" GroupName="trpc6" 
                                    AutoPostBack="True" />
                            </td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radTRPC6N" runat="server" GroupName="trpc6" 
                                    AutoPostBack="True" />
                            </td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radTRPC6S" runat="server" GroupName="trpc6s" />
                            </td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radTRPC6NS" runat="server" GroupName="trpc6s" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                ACTN4 - ALPHA-ACTININ 4</td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radACTN4Y" runat="server" GroupName="actn4" 
                                    AutoPostBack="True" />
                            </td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radACTN4N" runat="server" GroupName="actn4" 
                                    AutoPostBack="True" />
                            </td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radACTN4S" runat="server" GroupName="actn4s" />
                            </td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radACTN4NS" runat="server" GroupName="actn4s" />
                    
                            </td>
                        </tr>
                        <tr>
                            <td>
                                LAMB2 - LAMININ-BETA-2</td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radLAMB2Yes" runat="server" GroupName="lamb" 
                                    AutoPostBack="True" />
                            </td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radLAMB2No" runat="server" GroupName="lamb" 
                                    AutoPostBack="True" />
                            </td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radLAMBS" runat="server" GroupName="lambs" />
                            </td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radLAMBNS" runat="server" GroupName="lambs" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Other</td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radOtherY" runat="server" GroupName="other" 
                                    AutoPostBack="True"  />
                            </td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radOtherN" runat="server" GroupName="other" 
                                    AutoPostBack="True" />
                            </td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radOtherS" runat="server" GroupName="otherS" />
                            </td>
                            <td class="auto-style2">
                                <asp:RadioButton ID="radOtherNS" runat="server" GroupName="otherS" />
                            </td>
                        </tr>
                    </table>
                </td>
			</tr>
			<tr id="rowGeneMutation" runat="server" >
				
				<td colspan="2" style="margin-left:20px;">Details of other Gene Mutation<br />
                    <asp:TextBox ID="txtGENE_MUT_TEXT" runat="server" Rows="5" TextMode="MultiLine" 
                        Width="85%" Font-Size="95%"></asp:TextBox>
                    
                </td>
			</tr>
			<tr>
                <td>
                    <asp:Label ID="lblKarotype" runat="server" Text="Karyotype" Visible="false"></asp:Label>
                </td>
                <td>
                    <asp:DropDownList ID="DropDownList9" runat="server" AppendDataBoundItems="True" 
                        DataSourceID="SqlDataSource5" DataTextField="KARYOTYPE" DataValueField="kID" 
                        Visible="false">
                        <asp:ListItem Value="">Select</asp:ListItem>
                    </asp:DropDownList>
                </td>
            </tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>
                    <asp:Label ID="lblParental" runat="server" AssociatedControlID="DropDownList12" 
                        Text="Parental Consanguinity Y/N"></asp:Label>
                </td>
				<td>
                    <asp:DropDownList ID="DropDownList12" runat="server">
                    	<asp:listitem Value="" Selected="True">Select</asp:listitem>
						<asp:listitem Value="1">Yes</asp:listitem>
						<asp:listitem Value="0">No</asp:listitem>
						<asp:listitem Value="9">Unkown</asp:listitem>
                    </asp:DropDownList>
                    </td>
			</tr>
			<tr>
				<td>
                    <asp:Label ID="lblFamHist" runat="server" AssociatedControlID="DropDownList13" 
                        Text="Family History Y/N"></asp:Label>
                </td>
				<td>
                    <asp:DropDownList ID="DropDownList13" runat="server" AutoPostBack="true" CausesValidation="false">
                    	<asp:listitem Value="99" Selected="True">Select</asp:listitem>
						<asp:listitem Value="1">Yes</asp:listitem>
						<asp:listitem Value="0">No</asp:listitem>
						<asp:listitem Value="9">Unkown</asp:listitem>
                    </asp:DropDownList></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>
                    <asp:Label ID="lblRelRADAR" runat="server" CssClass="relRADAR" Text="RADAR No" 
                        Visible="False"></asp:Label>
                </td>
			</tr>
			<tr id="rowREL1" runat="server" >
				<td><asp:Label runat="server" ID="lblFirstRel" Text="1st Relative with same disease" 
                        Visible="False"></asp:Label></td>
				<td>
                    <asp:DropDownList ID="DropDownList1" runat="server" 
                        DataSourceID="SqlDataSource2" DataTextField="RELATIVE" 
                        DataValueField="rID" AppendDataBoundItems="true" 
                         Enabled="False" Visible="false">
                        <asp:ListItem Value="">Select</asp:ListItem>
                    </asp:DropDownList>&nbsp;<asp:TextBox ID="txtREL1_RADAR" runat="server" Width="60px" 
                        CssClass="inline" Visible="False" Height="15px" ></asp:TextBox>
                   
                </td>
			</tr>
			<tr id="rowREL2" runat="server" >
				<td><asp:Label runat="server" ID="lblSecondRel" Text="2nd Relative with same disease" 
                        Visible="False"></asp:Label></td>
				<td>
                    <asp:DropDownList ID="DropDownList2" runat="server" 
                        DataSourceID="SqlDataSource2" DataTextField="RELATIVE" 
                        DataValueField="rID" AppendDataBoundItems="true" 
                         enabled="false" Visible="false"
                        >
                    <asp:ListItem Value="">Select</asp:ListItem>
                    </asp:DropDownList>&nbsp;<asp:TextBox ID="txtREL2_RADAR" runat="server" CssClass="inline" Width="60px" 
                        Visible="False" Height="15px"></asp:TextBox>
                </td>
			</tr>
			<tr id="rowREL3" runat="server" >
				<td><asp:Label runat="server" ID="lblThirdRel" Text="3rd Relative with same disease" 
                        Visible="False"></asp:Label></td>
				<td>
                    <asp:DropDownList ID="DropDownList3" runat="server" Enabled="false" Visible="false" 
                        DataSourceID="SqlDataSource2" DataTextField="RELATIVE" DataValueField="rID" AppendDataBoundItems="true" >
                    <asp:ListItem Value="">Select</asp:ListItem>
                    </asp:DropDownList>&nbsp;<asp:TextBox ID="txtREL3_RADAR" runat="server" Visible="False" Width="60px" 
                        Height="15px"></asp:TextBox>
                    
                </td>
			</tr>
			<tr id="rowREL4" runat="server" >
				<td><asp:Label runat="server" ID="lblFourthRel" Text="4th Relative with same disease" 
                        Visible="False"></asp:Label></td>
				<td>
                    <asp:DropDownList ID="DropDownList4" runat="server" AppendDataBoundItems="True" 
                        DataSourceID="SqlDataSource2" DataTextField="RELATIVE" DataValueField="rID"  Enabled="false" Visible="false">
                     <asp:ListItem Value="">Select</asp:ListItem>
                    </asp:DropDownList>&nbsp;<asp:TextBox ID="txtREL4_RADAR" runat="server" Visible="False" Width="60px" 
                        Height="15px"></asp:TextBox>
                </td>
			</tr>
			<tr id="rowREL5" runat="server" >
				<td><asp:Label runat="server" ID="lblFifthRel" Text="5th Relative with same disease" 
                        Visible="False"></asp:Label></td>
				<td><asp:DropDownList ID="DropDownList5" runat="server" AppendDataBoundItems="True" 
                        DataSourceID="SqlDataSource2" DataTextField="RELATIVE" DataValueField="rID"  Enabled="false" Visible="false">
                     <asp:ListItem Value="">Select</asp:ListItem>
                    </asp:DropDownList>&nbsp;<asp:TextBox ID="txtREL5_RADAR" runat="server" 
                        Visible="False" Width="60px" Height="15px"></asp:TextBox>
                </td>
			</tr>
			<tr id="rowREL6" runat="server">
				<td><asp:Label runat="server" ID="lblSixthRel" Text="6th Relative with same disease" 
                        Visible="False"></asp:Label></td>
				<td><asp:DropDownList ID="DropDownList6" runat="server" AppendDataBoundItems="True" 
                        DataSourceID="SqlDataSource2" DataTextField="RELATIVE" DataValueField="rID"  Enabled="false" Visible="false">
                     <asp:ListItem Value="">Select</asp:ListItem>
                    </asp:DropDownList>&nbsp;<asp:TextBox ID="txtREL6_RADAR" runat="server" 
                        Visible="False" Width="60px" Height="15px"></asp:TextBox>
                </td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		    <tr>
                <td>
                    &nbsp;</td>
                <td>
                    &nbsp;</td>
            </tr>
            <tr>
                <td>
                    &nbsp;</td>
                <td>
                    &nbsp;</td>
            </tr>
		</table>
		</td>
	</tr>
</table>
    <asp:Label ID="lblDebug" runat="server"></asp:Label>
    <br /><br />
    <asp:Label ID="lblPage" runat="server"></asp:Label>
<asp:SqlDataSource ID="SqlDataSource2" runat="server" 
    ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
    SelectCommand="SELECT [rID], [RELATIVE] FROM [tbl_Relative]">
</asp:SqlDataSource>
<asp:SqlDataSource ID="SqlDataSource3" runat="server" 
    ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
    SelectCommand="SELECT [dcID], [dcAbbr], [dcDesc] FROM [tbl_DiagCode]">
</asp:SqlDataSource>
<asp:SqlDataSource ID="SqlDataSource4" runat="server" 
    ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
    SelectCommand="SELECT [cID], [CLIN_PRES] FROM [tbl_Clin_Pres]">
</asp:SqlDataSource>
<asp:SqlDataSource ID="SqlDataSource5" runat="server" 
    ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
    SelectCommand="SELECT [kID], [KARYOTYPE] FROM [tbl_Karyotype] ORDER BY [kID]">
</asp:SqlDataSource>
<asp:SqlDataSource ID="SqlDataSource6" runat="server" 
    ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
    >
</asp:SqlDataSource>
   
</div>    
</asp:Content>

