<%@ Page Title="" Language="VB" MasterPageFile="~/MasterPage.master" AutoEventWireup="false" CodeFile="demographics.aspx.vb" Inherits="demographics" Debug="true" %>
<%@ Register assembly="DevExpress.Web.ASPxEditors.v9.2, Version=9.2.4.0, Culture=neutral, PublicKeyToken=b88d1754d700e49a" namespace="DevExpress.Web.ASPxEditors" tagprefix="dxe" %>
<%@ Register assembly="AjaxControlToolkit" namespace="AjaxControlToolkit" tagprefix="cc1" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">

    <script type="text/javascript" language="javascript">
    function ValidatePostCode(sender, args) {
        args.IsValid = (args.Value.match(/(GIR 0AA|[A-PR-UWYZ]([0-9]{1,2}|([A-HK-Y][0-9]|[A-HK-Y][0-9]([0-9]|[ABEHMNPRV-Y]))|[0-9][A-HJKS-UW]) [0-9][ABD-HJLNP-UW-Z]{2})/i) != null);
        return args.IsValid;
    }
</script>
<style type="text/css">

input, select, textarea {
border: 1px solid gray;
padding-left:3px;
}

select, input, textarea {
font-size:95%;
color:#2D2E2E
}

.frmView {
margin-top:0;
}

.style2 {
background-color: #B6DF9F;
table-layout:fixed;
}
.style3 {
border-collapse: collapse;
border-style: none;
border-color: inherit;
border-width: 0;
background-color:#EEF6ED;
}

#ctl00_ContentPlaceHolder1_CONSENTCheckBox {
border:0;
}
/* this because of the update panel for the tabs 
#tabsC {
margin-top:-35px;
}
*/
</style>
<link href="tabs.css" rel="stylesheet" type="text/css" />

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
    
<div id="tabsC" > 
    <ul> 
        <li><a href="#" title="Demographics"><span class="hovered">Demographics</span></a></li> 
        <li><asp:hyperlink runat="server" ID="lnkDiagnosis" NavigateUrl="diagnosis.aspx" ToolTip="Diagnosis" ><span>Diagnosis</span></asp:hyperlink></li> 
        <li><asp:hyperlink runat="server" ID="lnkLabResults" NavigateUrl="first-clinical.aspx" Tooltip="Laboratory Results"><span>First Visit</span></asp:hyperlink></li> 
        <li><asp:hyperlink runat="server" ID="lnk3Months" NavigateUrl="followup-clinical.aspx" Tooltip="Follow Up"><span>Follow Up</span></asp:hyperlink></li> 
        <li><asp:hyperlink runat="server" ID="lnkPathology" NavigateUrl="pathology.aspx" Tootip="Pathology"><span>Pathology</span></asp:hyperlink></li> 
        <li><asp:hyperlink runat="server" ID="lnkRelapse" NavigateUrl="relapse.aspx" Tooltip="Relapse"><span>Relapse</span></asp:hyperlink></li> 
        <li><asp:hyperlink runat="server" ID="lnkHospital" NavigateUrl="hospitalisation.aspx" Tooltip="Hospitalisation"><span>Hospitalisation</span></asp:hyperlink></li> 
        <li><asp:hyperlink runat="server" ID="lnkTimelines" NavigateUrl="timelines.aspx" Tootip="Timelines"><span>Timelines</span></asp:hyperlink></li> 
     </ul> 
</div> 
<div id="mainBlock">
    
<p><asp:Label ID="lblMsg" runat="server"></asp:Label></p>

<table cellpadding="0" style="width:100%; border-collapse:collapse;" class="style2">
<tr>
<td style="height:30px;"><asp:Label ID="lblNew" runat="server" Text="&nbsp;Add a New Patient" Font-Bold="true"></asp:Label></td>
<td style="text-align:right; padding-right:10px;"><b>RADAR Number</b></td>
<td style="width:150px;"><asp:TextBox ID="RADAR_NOTextBox" runat="server" 
        BackColor="#EEF6ED" Font-Italic="true" ReadOnly="true" 
        Text=''></asp:TextBox></td>
</tr>
<tr>
<td style="height:30px;">
    <asp:TextBox ID="txtDateToday" runat="server" BackColor="#B6DF9F" 
        BorderColor="#B6DF9F" ForeColor="#B6DF9F"></asp:TextBox>
    </td>
<td style="text-align:right; padding-right:10px;">Date Registered</td>
<td><asp:TextBox ID="DATE_REGTextBox" runat="server" BackColor="#EEF6ED" 
        Font-Italic="true" ReadOnly="true" Text=''></asp:TextBox></td>
</tr>
</table>
<table cellpadding="0" style="width: 100%; border-collapse:collapse" class="style3" id="mainForm" runat="server">
	<tr>
		<td style="width:50%; vertical-align:top;">
		<table cellpadding="5" style="width: 100%; border-collapse:collapse">
			<tr>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td>Surname</td>
				<td>
				<asp:textbox ID="SNAMETextBox" runat="server" Text='' />
				&nbsp;<asp:requiredfieldvalidator id="RequiredFieldValidator1" runat="server" 
                        ControlToValidate="SNAMETextBox" SetFocusOnError="True" >*</asp:requiredfieldvalidator>
				</td>
			</tr>
			<tr>
				<td>First Name</td>
				<td>
				<asp:textbox ID="FNAMETextBox" runat="server" Text='' />
				&nbsp;<asp:requiredfieldvalidator id="RequiredFieldValidator2" runat="server" 
                        ControlToValidate="FNAMETextBox" SetFocusOnError="True" >*</asp:requiredfieldvalidator>
				</td>
			</tr>
			<tr>
				<td>Date of Birth&nbsp;&nbsp;<asp:RequiredFieldValidator 
                        ID="RequiredFieldValidator6" runat="server" ControlToValidate="DOBTextBox" 
                        CssClass="inline">*</asp:RequiredFieldValidator>
				    &nbsp;</td>
				<td>
				<asp:textbox ID="DOBTextBox" runat="server" Text='' Width="70px" />
                    <cc1:CalendarExtender ID="DOBTextBox_CalendarExtender" runat="server" 
                        Enabled="True" TargetControlID="DOBTextBox" PopupButtonID="ImageButton1" Format="dd-MM-yyyy">
                    </cc1:CalendarExtender> <asp:ImageButton ID="ImageButton1" runat="server" CausesValidation="False" ImageUrl="~/images/Calendar_scheduleHS.png"  />
                    &nbsp;
                    <asp:CompareValidator ID="CompareValidator1" runat="server" 
                        ControlToCompare="txtDateToday" ControlToValidate="DOBTextBox" 
                        ErrorMessage="&gt; today" Operator="LessThanEqual" Type="Date" 
                        Display="Dynamic"></asp:CompareValidator> &nbsp;<a href="#" class="info"><em>Help</em><span>To select a different month in the 
                date picker, click the current month at the top.<br /><br /> Click again to select 
                a year.</span>  </a>
                </td>
			</tr>
			<tr>
				<td>Sex</td>
				<td>
				<asp:DropDownList ID="DropDownList1" runat="server" >
    <asp:ListItem Value="">Select</asp:ListItem>
        <asp:ListItem Value="1">Male</asp:ListItem>
        <asp:ListItem Value="2">Female</asp:ListItem>
        <asp:ListItem Value="9">Not specified</asp:ListItem>
    </asp:DropDownList>				&nbsp;<asp:requiredfieldvalidator id="RequiredFieldValidator4" 
                        runat="server" ControlToValidate="DropDownList1" SetFocusOnError="True" >*</asp:requiredfieldvalidator>
				</td>
			</tr>
			<tr>
				<td>Ethnic Group</td>
				<td>
				<asp:dropdownlist ID="DropDownList3" runat="server" AppendDataBoundItems="true" DataSourceID="SqlDataSource2" DataTextField="eName" DataValueField="eCode" >
					<asp:listitem Value="">Please select</asp:listitem>
				</asp:dropdownlist>
				</td>
			</tr>
			<tr>
				<td style="vertical-align:top">Address</td>
				<td>
				<asp:textbox ID="ADD1TextBox" runat="server" Text='' Width="200px" />
				<br />
				<asp:textbox ID="ADD2TextBox" runat="server" Text='' Width="200px" />
				<br />
				<asp:textbox ID="ADD3TextBox" runat="server" Text='' Width="200px" />
				<br />
				<asp:textbox ID="ADD4TextBox" runat="server" Text='' Width="200px" />
				    </td>
			</tr>
			<tr>
				<td>Postcode</td>
				<td>
				<asp:textbox ID="POSTCODETextBox" runat="server" Text='' 
                        Width="70px" />
				    &nbsp;<asp:CustomValidator ID="CustomValidator1" runat="server" 
                        ClientValidationFunction="ValidatePostCode" ControlToValidate="POSTCODETextBox" 
                        ErrorMessage="Invalid postcode"></asp:CustomValidator>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			</table>
			<fieldset style="border:1px solid green; margin-left:10px; width:360px"><legend><strong>Archive</strong></legend>
			<table style="width:95%; margin-left:5px;" >
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>Surname Alias</td>
				<td>
				<asp:textbox ID="SNAME_ALIASTextBox" runat="server" Text='' />&nbsp;<a href="#" class="info"><em>Help</em><span>Has the patient been known by another surname? If so, enter here.</span></a>
				</td>
			</tr>
			<tr>
				<td>Previous Postcode</td>
				<td>
				<asp:textbox ID="POSTCODE_OLDTextBox" runat="server" Text='' />&nbsp;<a href="#" class="info"><em>Help</em><span>This is important if the patient has moved into your care from a different hospital</span></a>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			
		</table>
		</fieldset>
		<br /><br />
		
		
					<asp:validationsummary id="ValidationSummary1" runat="server" 
                DisplayMode="List" 
                HeaderText="One or more required fields are missing" ShowMessageBox="True" 
                ShowSummary="False" />
	<br /><br />
		<asp:button ID="btnSubmit" runat="server" CausesValidation="True" Text="" 
                CssClass="saveBtn" />
				&nbsp;
				<asp:button ID="btnEdit" runat="server" CausesValidation="True" 
                Text="Update" Visible="false" CssClass="saveBtn" />
				
		&nbsp;
            <asp:Button ID="btnContinue" runat="server" CssClass="saveBtn" Text="Continue" 
                Visible="False" />
				
		</td>
		<td style="vertical-align:top;">
		<table cellpadding="5" style="width: 100%; border-collapse:collapse;">
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>Hospital Number</td>
				<td>
				<asp:textbox ID="HOSP_NOTextBox" runat="server" Text='' 
                        Width="80px" />
				    &nbsp;<asp:RequiredFieldValidator ID="RequiredFieldValidator7" runat="server" 
                        ControlToValidate="HOSP_NOTextBox" SetFocusOnError="True">*</asp:RequiredFieldValidator>
				</td>
			</tr>
			<tr>
				<td>NHS Number</td>
				<td>
				<asp:textbox ID="NHS_NOTextBox" runat="server" Text='' 
                        Width="80px" />
				</td>
			</tr>
			<tr>
				<td>Renal Registry Number</td>
				<td>
				<asp:textbox ID="RR_NOTextBox" runat="server" Text='' 
                        Width="80px" />
				</td>
			</tr>
			<tr>
				<td>UK Transplant Number</td>
				<td>
				<asp:textbox ID="UKT_NOTextBox" runat="server" Text='' 
                        Width="80px" />
				</td>
			</tr>
			<tr>
				<td>CHI No. (Scotland Only)</td>
				<td>
				<asp:textbox ID="CHI_NOTextBox" runat="server" Text='' 
                        Width="80px" />
				</td>
			</tr>
			<tr>
				<td>Status</td>
				<td>
                    <asp:DropDownList ID="DropDownList5" runat="server" 
                        DataSourceID="SqlDataSource4" DataTextField="sDesc" DataValueField="sID" AppendDataBoundItems="true">
                        <asp:ListItem Value="">Select</asp:ListItem>
                    </asp:DropDownList>
                </td>
			</tr>
			<tr>
				<td>Responsible Consultant</td>
				<td>
				    <asp:DropDownList ID="DropDownList4" runat="server" 
                        DataSourceID="SqlDataSource1" DataTextField="NAME" DataValueField="cID" AppendDataBoundItems="true">
                        <asp:ListItem Value="">Select</asp:ListItem>
                    </asp:DropDownList>
				</td>
			</tr>
			<tr>
				<td colspan="2">Renal Unit &nbsp;<asp:requiredfieldvalidator 
                        id="RequiredFieldValidator5" runat="server" ControlToValidate="DropDownList2" 
                        SetFocusOnError="True" >*</asp:requiredfieldvalidator></td>
			</tr>
			<tr>
				<td colspan="2">
				<asp:dropdownlist Enabled="false" id="DropDownList2" runat="server" AppendDataBoundItems="true" DataSourceID="SqlDataSource3" DataTextField="cName" DataValueField="cID" >
					<asp:listitem Value="" >Please select</asp:listitem>
				</asp:dropdownlist>
				&nbsp;
				</td>
			</tr>
			<tr>
				<td>Consent (RaDaR only)</td>
				<td>
				    <asp:CheckBox ID="CONSENTCheckBox" runat="server" 
                        />&nbsp;&nbsp;<a href="consent_forms.aspx">Consent Forms</a>
				</td>
			</tr>
			<tr id="rowConsent" runat="server">
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr id="rowPatientInfo" runat="server">
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr id="rowInfo2" runat="server">
				<td colspan="2">
                    &nbsp;</td>
			</tr>
			<tr id="rowConsent2" runat="server">
				<td colspan="2">
                    &nbsp;</td>
				
			</tr>

		</table>
		</td>
	</tr>
</table>

    <p><asp:Label ID="lblUpdate" runat="server"></asp:Label>&nbsp;<asp:Label 
            ID="lblDebug" runat="server" Text=""></asp:Label>
    </p>
    <p>
        <asp:Label ID="lblDebug2" runat="server" Text=""></asp:Label>
    </p>
    <p><asp:Label ID="lblPage" runat="server"></asp:Label></p>
    </div>
   <%-- </ContentTemplate>
    </asp:UpdatePanel>--%>

    <asp:SqlDataSource ID="SqlDataSource2" runat="server" 
        ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
        SelectCommand="SELECT [eName], [eCode] FROM [tbl_Ethnicity] ORDER BY [eID]">
    </asp:SqlDataSource>
    <asp:SqlDataSource ID="SqlDataSource3" runat="server" ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" SelectCommand="SELECT [cID], [cName] FROM [tbl_Centres] ORDER BY [cName]">
	</asp:SqlDataSource>
    <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
        ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
        >
    </asp:SqlDataSource>
   
    <asp:SqlDataSource ID="SqlDataSource4" runat="server" 
        ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
        SelectCommand="SELECT [sID], [sDesc] FROM [tbl_Status]"></asp:SqlDataSource>
   
</asp:Content>

