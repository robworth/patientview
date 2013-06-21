<%@ Page Title="RADAR - Patient Entry" Language="VB" MasterPageFile="~/MasterPage.master" AutoEventWireup="false" CodeFile="patient-entry2.aspx.vb" Inherits="patient_entry2" Debug="true"   %>
<%@ Register assembly="DevExpress.Web.ASPxEditors.v9.2, Version=9.2.4.0, Culture=neutral, PublicKeyToken=b88d1754d700e49a" namespace="DevExpress.Web.ASPxEditors" tagprefix="dxe" %>

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
</style>
<link href="tabs.css" rel="stylesheet" type="text/css" />

<!--[if IE]>
<style type="text/css">
#mainBlock {
margin-top:0;
}
    
</style>
<![endif]-->	
	
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <%--<asp:ScriptManager ID="ScriptManager1" runat="server"/><asp:UpdatePanel ID="UpdatePanel1" runat="server"><ContentTemplate>--%>
<div id="tabsC" > 
    <ul> 
        <li><a href="#" title="Demographics"><span class="hovered">Demographics</span></a></li> 
        <li><asp:hyperlink runat="server" ID="lnkDiagnosis" NavigateUrl="diagnosis.aspx" ToolTip="Diagnosis"><span>Diagnosis</span></asp:hyperlink></li> 
        <%--<li><asp:hyperlink runat="server" ID="lnkClinical" NavigateUrl="clinical3.aspx" Tooltip="Clinical Picture"><span>Clinical Picture</span></asp:hyperlink></li>--%>
        <li><asp:hyperlink runat="server" ID="lnkLabResults" NavigateUrl="first-clinical.aspx" Tooltip="Laboratory Results"><span>First Visit</span></asp:hyperlink></li> 
        <li><asp:hyperlink runat="server" ID="lnk3Months" NavigateUrl="six-month.aspx" Tooltip="Follow Up"><span>Follow Up</span></asp:hyperlink></li> 
     </ul> 
</div> 
<div id="mainBlock">
    <p><asp:Label ID="lblMsg" runat="server"></asp:Label></p>

	    <asp:FormView ID="FormView1" runat="server" DataKeyNames="RADAR_NO" 
            DataSourceID="SqlDataSource1" DefaultMode="Insert" AllowPaging="false" Width="100%" CssClass="frmView">
            
<EditItemTemplate>
<table style="width:100%;" class="style2">
<tr>
<td style="height:30px">&nbsp;<b>Demographics</b></td>
<td style="width:290px;" ><asp:Label ID="Label1" Text="RADAR Number" runat="server" Font-Bold="true"></asp:Label>
				<asp:TextBox runat="server" 
                        ID="TextBox1" Text='<%# Bind("RADAR_NO") %>' BackColor="#B6DF9F" 
                        Font-Bold="False" BorderStyle="None" ReadOnly="True"></asp:TextBox></td>
</tr>
<tr>
<td class="style2" style="height: 30px;"></td>
<td><asp:Label ID="lblDATE_REG" runat="server" Text="Date Registered" 
        Font-Bold="False"></asp:Label>
				<asp:textbox ID="DATE_REGTextBox" runat="server" 
                        Text='<%# Bind("DATE_REG", "{0:dd-MMM-yyyy}") %>' BackColor="#B6DF9F" 
                        Font-Bold="False" BorderStyle="None" ReadOnly="True" /></td>
</tr>
</table>
<table cellpadding="0" style="width: 100%; border-collapse:collapse" class="style3">
	<tr>
		<td style="width:45%; vertical-align:top;">
		<table cellpadding="5" style="width: 100%; border-collapse:collapse">
			
			
			<tr>
				<td>&nbsp;</td>
				<td>
				    &nbsp;</td>
			</tr>
			<tr>
                <td>
                    Surname</td>
                <td>
                    <asp:TextBox ID="SNAMETextBox" runat="server" Text='<%# Bind("SNAME") %>' />
                </td>
            </tr>
			<tr>
				<td>First Name</td>
				<td>
				<asp:textbox ID="FNAMETextBox" runat="server" Text='<%# Bind("FNAME") %>' />
				</td>
			</tr>
			<tr>
				<td>Date of Birth</td>
				<td><dxe:ASPxDateEdit ID="ASPxDateEdit4" runat="server" Date='<%# Bind("DOB", "{0:dd-MMM-yyyy}") %>' EditFormat="Date" AllowNull="true" Width="150px">
                    </dxe:ASPxDateEdit>
				<%--<asp:textbox ID="DOBTextBox" runat="server" Text='<%# Bind("DOB", "{0:dd-MMM-yyyy}") %>' />--%>
				</td>
			</tr>
			<tr>
				<td>Sex</td>
				<td>
				<asp:dropdownlist ID="DropDownList1" runat="server" SelectedValue='<%# Bind("SEX") %>'>
					<asp:listitem Value="">Select</asp:listitem>
					<asp:listitem Value="1">Male</asp:listitem>
					<asp:listitem Value="2">Female</asp:listitem>
					<asp:listitem Value="9">Not specified</asp:listitem>
				</asp:dropdownlist>
				</td>
			</tr>
			<tr>
				<td>Ethnic Group</td>
				<td>
				<asp:dropdownlist ID="DropDownList3" runat="server" AppendDataBoundItems="true" DataSourceID="SqlDataSource2" DataTextField="eName" DataValueField="eCode" SelectedValue='<%# Bind("ETHNIC_GP") %>'>
					<asp:listitem Value="">Please select</asp:listitem>
				</asp:dropdownlist>
				</td>
			</tr>
			<tr>
				<td valign="top">Address</td>
				<td>
				<asp:textbox ID="ADD1TextBox" runat="server" Text='<%# Bind("ADD1") %>' />
				<br />
				<asp:textbox ID="ADD2TextBox" runat="server" Text='<%# Bind("ADD2") %>' />
				<br />
				<asp:textbox ID="ADD3TextBox" runat="server" Text='<%# Bind("ADD3") %>' />
				<br />
				<asp:textbox ID="ADD4TextBox" runat="server" Text='<%# Bind("ADD4") %>' />
				</td>
			</tr>
			<tr>
				<td>Postcode</td>
				<td>
				<asp:textbox ID="POSTCODETextBox" runat="server" Text='<%# Bind("POSTCODE") %>' />
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
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
				<asp:textbox ID="SNAME_ALIASTextBox" runat="server" Text='<%# Bind("SNAME_ALIAS") %>' />&nbsp;<a href="#" class="info"><em>Help</em><span>Has the patient been known by another surname? If so, enter here.</span></a>
				</td>
			</tr>
			<tr>
				<td>Previous Postcode</td>
				<td>
				<asp:textbox ID="POSTCODE_OLDTextBox" runat="server" Text='<%# Bind("POSTCODE_OLD") %>' />&nbsp;<a href="#" class="info"><em>Help</em><span>This is important if the patient has moved into your care from a different hospital</span></a>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			
		</table>
		</fieldset>
		<br /><br />
		<asp:button ID="UpdateButton" runat="server" CausesValidation="True" 
                        CommandName="Update" Text="Save" />
				&nbsp;
				<asp:button ID="InsertCancelButton" runat="server" CausesValidation="False" CommandName="Cancel" Text="Cancel" Visible="false" />
				
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
				<asp:textbox ID="HOSP_NOTextBox" runat="server" Text='<%# Bind("HOSP_NO") %>' 
                        Width="80px" />
				</td>
			</tr>
			<tr>
				<td>NHS Number</td>
				<td>
				<asp:textbox ID="NHS_NOTextBox" runat="server" Text='<%# Bind("NHS_NO") %>' 
                        Width="80px" />
				</td>
			</tr>
			<tr>
				<td>Renal Registry Number</td>
				<td>
				<asp:textbox ID="RR_NOTextBox" runat="server" Text='<%# Bind("RR_NO") %>' 
                        Width="80px" />
				</td>
			</tr>
			<tr>
				<td>UK Transplant Number</td>
				<td>
				<asp:textbox ID="UKT_NOTextBox" runat="server" Text='<%# Bind("UKT_NO") %>' 
                        Width="80px" />
				</td>
			</tr>
			<tr>
				<td>CHI No. (Scotland Only)</td>
				<td>
				<asp:textbox ID="CHI_NOTextBox" runat="server" Text='<%# Bind("CHI_NO") %>' 
                        Width="80px" />
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>Responsible Consultant (GMC Code)</td>
				<td>
				<asp:textbox ID="CONS_NEPHTextBox" runat="server" Text='<%# Bind("CONS_NEPH") %>' 
                        Width="80px" />
				</td>
			</tr>
			<tr>
				<td colspan="2">Paediatric Renal Unit</td>
			</tr>
			<tr>
				<td colspan="2">
				<asp:dropdownlist id="DropDownList2" runat="server" AppendDataBoundItems="true" DataSourceID="SqlDataSource3" DataTextField="cName" DataValueField="cID" selectedvalue='<%# Bind("RENAL_UNIT") %>' Enabled="false">
					<asp:listitem Value="">Please select</asp:listitem>
				</asp:dropdownlist>
				</td>
			</tr>
			<tr>
				<td>Consent</td>
				<td>
				<asp:checkbox ID="CONSENTTextBox" runat="server" checked='<%# Bind("CONSENT") %>' />
				</td>
			</tr>
			<tr>
				<td><a href="consent.pdf" target="_blank">Downloadable Consent Form</a></td>
				<td>&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</EditItemTemplate> 
<InsertItemTemplate>
<table cellpadding="0" style="width:100%; border-collapse:collapse;" class="style2">
<tr>
<td style="height:30px;"><asp:Label ID="lblNew" runat="server" Text="&nbsp;Add a New Patient" Font-Bold="true"></asp:Label></td>
<td style="text-align:right; padding-right:10px;"><b>RADAR Number</b></td>
<td style="width:150px;"><asp:TextBox ID="RADAR_NOTextBox" runat="server" 
        BackColor="#EEF6ED" Font-Italic="true" ReadOnly="true" 
        Text='<%# Bind("RADAR_NO") %>'>Auto allocated</asp:TextBox></td>
</tr>
<tr>
<td style="height:30px;">&nbsp;</td>
<td style="text-align:right; padding-right:10px;">Date Registered</td>
<td><asp:TextBox ID="DATE_REGTextBox" runat="server" BackColor="#EEF6ED" 
        Font-Italic="true" ReadOnly="true" Text='<%# Bind("DATE_REG") %>'>Today&#39;s date</asp:TextBox></td>
</tr>
</table>
<table cellpadding="0" style="width: 100%; border-collapse:collapse" class="style3">
	<tr>
		<td style="width:50%; vertical-align:top;">
		<table cellpadding="5" style="width: 100%; border-collapse:collapse">
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>Surname</td>
				<td>
				<asp:textbox ID="SNAMETextBox" runat="server" Text='<%# Bind("SNAME") %>' />
				&nbsp;<asp:requiredfieldvalidator id="RequiredFieldValidator1" runat="server" 
                        ControlToValidate="SNAMETextBox" SetFocusOnError="True" >*</asp:requiredfieldvalidator>
				</td>
			</tr>
			<tr>
				<td>First Name</td>
				<td>
				<asp:textbox ID="FNAMETextBox" runat="server" Text='<%# Bind("FNAME") %>' />
				&nbsp;<asp:requiredfieldvalidator id="RequiredFieldValidator2" runat="server" 
                        ControlToValidate="FNAMETextBox" SetFocusOnError="True" >*</asp:requiredfieldvalidator>
				</td>
			</tr>
			<tr>
				<td>Date of Birth</td>
				<td><dxe:ASPxDateEdit ID="ASPxDateEdit1" runat="server" Date='<%# Bind("DOB", "{0:dd-MMM-yyyy}") %>' EditFormat="Date" AllowNull="true" Width="120px"></dxe:ASPxDateEdit>
				<%--<asp:textbox ID="DOBTextBox" runat="server" Text='<%# Bind("DOB") %>' />--%><asp:RequiredFieldValidator 
                        ID="RequiredFieldValidator6" runat="server" ControlToValidate="ASPxDateEdit1">*</asp:RequiredFieldValidator>
				</td>
			</tr>
			<tr>
				<td>Sex</td>
				<td>
				<asp:DropDownList ID="DropDownList1" runat="server" SelectedValue='<%# Bind("SEX") %>'>
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
				<asp:dropdownlist ID="DropDownList3" runat="server" AppendDataBoundItems="true" DataSourceID="SqlDataSource2" DataTextField="eName" DataValueField="eCode" SelectedValue='<%# Bind("ETHNIC_GP") %>'>
					<asp:listitem Value="">Please select</asp:listitem>
				</asp:dropdownlist>
				</td>
			</tr>
			<tr>
				<td style="vertical-align:top">Address</td>
				<td>
				<asp:textbox ID="ADD1TextBox" runat="server" Text='<%# Bind("ADD1") %>' />
				<br />
				<asp:textbox ID="ADD2TextBox" runat="server" Text='<%# Bind("ADD2") %>' />
				<br />
				<asp:textbox ID="ADD3TextBox" runat="server" Text='<%# Bind("ADD3") %>' />
				<br />
				<asp:textbox ID="ADD4TextBox" runat="server" Text='<%# Bind("ADD4") %>' />
				    </td>
			</tr>
			<tr>
				<td>Postcode</td>
				<td>
				<asp:textbox ID="POSTCODETextBox" runat="server" Text='<%# Bind("POSTCODE") %>' 
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
				<asp:textbox ID="SNAME_ALIASTextBox" runat="server" Text='<%# Bind("SNAME_ALIAS") %>' />&nbsp;<a href="#" class="info"><em>Help</em><span>Has the patient been known by another surname? If so, enter here.</span></a>
				</td>
			</tr>
			<tr>
				<td>Previous Postcode</td>
				<td>
				<asp:textbox ID="POSTCODE_OLDTextBox" runat="server" Text='<%# Bind("POSTCODE_OLD") %>' />&nbsp;<a href="#" class="info"><em>Help</em><span>This is important if the patient has moved into your care from a different hospital</span></a>
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
                DisplayMode="SingleParagraph" 
                HeaderText="One or more required fields are missing" ShowMessageBox="True" 
                ShowSummary="False" />
	<br /><br />
		<asp:button ID="InsertButton" runat="server" CausesValidation="True" CommandName="Insert" Text="Add this patient" />
				&nbsp;
				<asp:button ID="InsertCancelButton" runat="server" CausesValidation="False" CommandName="Cancel" Text="Cancel" Visible="false" />
				
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
				<asp:textbox ID="HOSP_NOTextBox" runat="server" Text='<%# Bind("HOSP_NO") %>' 
                        Width="80px" />
				</td>
			</tr>
			<tr>
				<td>NHS Number</td>
				<td>
				<asp:textbox ID="NHS_NOTextBox" runat="server" Text='<%# Bind("NHS_NO") %>' 
                        Width="80px" />
				</td>
			</tr>
			<tr>
				<td>Renal Registry Number</td>
				<td>
				<asp:textbox ID="RR_NOTextBox" runat="server" Text='<%# Bind("RR_NO") %>' 
                        Width="80px" />
				</td>
			</tr>
			<tr>
				<td>UK Transplant Number</td>
				<td>
				<asp:textbox ID="UKT_NOTextBox" runat="server" Text='<%# Bind("UKT_NO") %>' 
                        Width="80px" />
				</td>
			</tr>
			<tr>
				<td>CHI No. (Scotland Only)</td>
				<td>
				<asp:textbox ID="CHI_NOTextBox" runat="server" Text='<%# Bind("CHI_NO") %>' 
                        Width="80px" />
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>Responsible Consultant(GMC Code)</td>
				<td>
				<asp:textbox ID="CONS_NEPHTextBox" runat="server" Text='<%# Bind("CONS_NEPH") %>' 
                        Width="80px" />
				</td>
			</tr>
			<tr>
				<td colspan="2">Paediatric Renal Unit &nbsp;<asp:requiredfieldvalidator 
                        id="RequiredFieldValidator5" runat="server" ControlToValidate="DropDownList2" 
                        SetFocusOnError="True" >*</asp:requiredfieldvalidator></td>
			</tr>
			<tr>
				<td colspan="2">
				<asp:dropdownlist id="DropDownList2" runat="server" AppendDataBoundItems="true" DataSourceID="SqlDataSource3" DataTextField="cName" DataValueField="cID" selectedvalue='<%# Bind("RENAL_UNIT") %>'>
					<asp:listitem Value="">Please select</asp:listitem>
				</asp:dropdownlist>
				&nbsp;
				</td>
			</tr>
			<tr>
				<td>Consent</td>
				<td>
				    <asp:CheckBox ID="CONSENTTextBox" runat="server" 
                        Checked='<%# Bind("CONSENT") %>' />
				</td>
			</tr>
			<tr>
				<td><a href="consent.pdf" target="_blank">Downloadable Consent Form</a></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>
				    &nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>

		</table>
		</td>
	</tr>
</table>

</InsertItemTemplate>
<ItemTemplate>
<table cellpadding="0" style="width: 100%; border-collapse:collapse; " class="style3">
	<tr>
		<td style="width:50%; vertical-align:top;">
		<table cellpadding="5" style="width: 100%; border-collapse:collapse;">
			<tr class="style2">
				<td style="width:200px;"><b>&nbsp;Demographics</b></td>
				<td >&nbsp;</td>
			</tr>
			<tr class="style2">
				<td style="height:23px;">
                    <asp:Label ID="lblNew" runat="server" Text=""></asp:Label>
                    &nbsp;</td>
				<td>&nbsp;
                </td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>Surname</td>
				<td>
				<asp:label ID="SNAMELabel" runat="server" Text='<%# Bind("SNAME") %>' />
				</td>
			</tr>
			<tr>
				<td>First Name</td>
				<td>
				<asp:label ID="FNAMELabel" runat="server" Text='<%# Bind("FNAME") %>' />
				</td>
			</tr>
			<tr>
				<td>Date Of Birth</td>
				<td>
				<asp:label ID="DOBLabel" runat="server" Text='<%# Bind("DOB","{0:dd-MMM-yyyy}") %>' />
				</td>
			</tr>
			<tr>
				<td>Sex</td>
				<td>
				<asp:label ID="SEXLabel" runat="server" Text='<%# Bind("SEX") %>' />
				</td>
			</tr>
			<tr>
				<td>Ethnic group</td>
				<td>
				<asp:label ID="ETHNIC_GPLabel" runat="server" Text='<%# Bind("ETHNIC_GP") %>' />
				</td>
			</tr>
			<tr>
				<td valign="top">Address</td>
				<td>
				
				<asp:label ID="ADD1Label" runat="server" Text='<%# Bind("ADD1") %>' />
				<br />
				<asp:label ID="ADD2Label" runat="server" Text='<%# Bind("ADD2") %>' />
				<br />
				<asp:label ID="ADD3Label" runat="server" Text='<%# Bind("ADD3") %>' />
				<br />
				<asp:label ID="ADD4Label" runat="server" Text='<%# Bind("ADD4") %>' />
				
				</td>
			</tr>
			<tr>
				<td valign="top">Postcode</td>
				<td>
				
				<asp:label ID="POSTCODELabel" runat="server" Text='<%# Bind("POSTCODE") %>' />
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>
				&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>
				<asp:label ID="SNAME_ALIASLabel" runat="server" Text='<%# Bind("SNAME_ALIAS") %>' />
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>
				<asp:label ID="POSTCODE_OLDLabel" runat="server" Text='<%# Bind("POSTCODE_OLD") %>' />
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>
				&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2">
				<asp:button ID="EditButton" runat="server" CausesValidation="False" 
                        CommandName="Edit" Text="Edit the entry" onclick="EditButton_Click" />
				&nbsp;
				<asp:button ID="DeleteButton" runat="server" CausesValidation="False" CommandName="Delete" OnClientClick="return confirm('Are you sure you want to delete this record?');" Text="Delete" Visible="false" />
				&nbsp;
				<asp:button ID="NewButton" runat="server" CausesValidation="False" 
                        CommandName="New" Text="New" onclick="NewButton_Click" Visible="false" />
				</td>
			</tr>
		</table>
		</td>
		<td style="vertical-align:top;">
		<table cellpadding="5" style="width: 100%; border-collapse:collapse;">
			<tr>
				<td style="width:200px;" align="right" class="style2">&nbsp;
				</td>
				<td class="style2" valign="top"><strong>Radar Number</strong>
                    <asp:TextBox ID="RADAR_NOTextBox" runat="server" 
                        BackColor="#B9DFAC" BorderStyle="None" Font-Bold="True" ReadOnly="True" 
                        Text='<%# Eval("RADAR_NO") %>' />
                    </td>
			</tr>
			<tr>
				<td align="right" class="style2">&nbsp;</td>
				<td class="style2"><b>Date Registered</b>
				<asp:textbox ID="DATE_REGTextbox" runat="server" 
                        Text='<%# Bind("DATE_REG","{0:dd-MMM-yyyy}") %>' BackColor="#B9DFAC" 
                        ReadOnly="True" BorderStyle="None" />
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>Hospital Number</td>
				<td>
				<asp:label ID="HOSP_NOLabel" runat="server" Text='<%# Bind("HOSP_NO") %>' />
				</td>
			</tr>
			<tr>
				<td>NHS Number</td>
				<td>
				<asp:label ID="NHS_NOLabel" runat="server" Text='<%# Bind("NHS_NO") %>' />
				</td>
			</tr>
			<tr>
				<td>Renal Registry Number</td>
				<td>
				<asp:label ID="RR_NOLabel" runat="server" Text='<%# Bind("RR_NO") %>' />
				</td>
			</tr>
			<tr>
				<td>UK Transplant Number</td>
				<td>
				<asp:label ID="UKT_NOLabel" runat="server" Text='<%# Bind("UKT_NO") %>' />
				</td>
			</tr>
			<tr>
				<td>CHI No. (Scotland Only)</td>
				<td>
				<asp:label ID="CHI_NOLabel" runat="server" Text='<%# Bind("CHI_NO") %>' />
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>Consultant (GMC Code)</td>
				<td>
				<asp:label ID="CONS_NEPHLabel" runat="server" Text='<%# Bind("CONS_NEPH") %>' />
				</td>
			</tr>
			<tr>
				<td colspan="2">
				<asp:label ID="RENAL_UNITLabel" runat="server" Text='<%# Bind("RENAL_UNIT") %>' />
				</td>
			</tr>
			<tr>
				<td colspan="2">
				&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>
				    <asp:Label ID="CONSENTLabel" runat="server" Text='<%# Bind("CONSENT") %>' />
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>
				    &nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
</table>


</ItemTemplate>            
	</asp:FormView>
	<p>
        <asp:Label ID="lblUpdate" runat="server" Text=""></asp:Label>
    </p>
	<asp:Label runat="server" ID="lblDebug"></asp:Label>
    <%--<asp:textbox ID="DOBTextBox" runat="server" Text='<%# Bind("DOB") %>' />--%>
</div>
<asp:SqlDataSource ID="SqlDataSource1" runat="server" 
            ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
            DeleteCommand="DELETE FROM [tbl_Demographics] WHERE [RADAR_NO] = @RADAR_NO" 
            InsertCommand="INSERT INTO [tbl_Demographics] ([RR_NO], [DATE_REG], [NHS_NO], [HOSP_NO], [UKT_NO], [CHI_NO], [SNAME], [SNAME_ALIAS], [FNAME], [DOB], [AGE], [SEX], [ETHNIC_GP], [ADD1], [ADD2], [ADD3], [ADD4], [POSTCODE], [POSTCODE_OLD], [CONSENT], [DATE_BAPN_REG], [CONS_NEPH], [RENAL_UNIT]) VALUES (@RR_NO, @DATE_REG, @NHS_NO, @HOSP_NO, @UKT_NO, @CHI_NO, @SNAME, @SNAME_ALIAS, @FNAME, @DOB, @AGE, @SEX, @ETHNIC_GP, @ADD1, @ADD2, @ADD3, @ADD4, @POSTCODE, @POSTCODE_OLD, @CONSENT, @DATE_BAPN_REG, @CONS_NEPH, @RENAL_UNIT); SELECT @newID = @@IDENTITY" 
             SelectCommand="SELECT [RADAR_NO], [RR_NO], [DATE_REG], [NHS_NO], [HOSP_NO], [UKT_NO], [CHI_NO], [SNAME], [SNAME_ALIAS], [FNAME], [DOB], [AGE], [SEX], [ETHNIC_GP], [ADD1], [ADD2], [ADD3], [ADD4], [POSTCODE], [POSTCODE_OLD], [CONSENT], [DATE_BAPN_REG], [CONS_NEPH], [RENAL_UNIT] FROM [tbl_Demographics] WHERE (RADAR_NO = @PatientID)" 
            UpdateCommand="UPDATE [tbl_Demographics] SET [RR_NO] = @RR_NO, [DATE_REG] = @DATE_REG, [NHS_NO] = @NHS_NO, [HOSP_NO] = @HOSP_NO, [UKT_NO] = @UKT_NO, [CHI_NO] = @CHI_NO, [SNAME] = @SNAME, [SNAME_ALIAS] = @SNAME_ALIAS, [FNAME] = @FNAME, [DOB] = @DOB, [AGE] = @AGE, [SEX] = @SEX, [ETHNIC_GP] = @ETHNIC_GP, [ADD1] = @ADD1, [ADD2] = @ADD2, [ADD3] = @ADD3, [ADD4] = @ADD4, [POSTCODE] = @POSTCODE, [POSTCODE_OLD] = @POSTCODE_OLD, [CONSENT] = @CONSENT, [DATE_BAPN_REG] = @DATE_BAPN_REG, [CONS_NEPH] = @CONS_NEPH, [RENAL_UNIT] = @RENAL_UNIT WHERE [RADAR_NO] = @RADAR_NO">
            <DeleteParameters>
                <asp:Parameter Name="RADAR_NO" Type="Int32" />
            </DeleteParameters>
            <SelectParameters>
                <asp:SessionParameter Name="PatientID" SessionField="patientID" />
            </SelectParameters>
            <UpdateParameters>
                <asp:Parameter Name="RR_NO" Type="String" />
                <asp:Parameter Name="DATE_REG" Type="DateTime" />
                <asp:Parameter Name="NHS_NO" Type="Int32" />
                <asp:Parameter Name="HOSP_NO" Type="String" />
                <asp:Parameter Name="UKT_NO" Type="Int32" />
                <asp:Parameter Name="CHI_NO" Type="Int32" />
                <asp:Parameter Name="SNAME" Type="String" />
                <asp:Parameter Name="SNAME_ALIAS" Type="String" />
                <asp:Parameter Name="FNAME" Type="String" />
                <asp:Parameter Name="DOB" Type="DateTime" />
                <asp:Parameter Name="AGE" Type="Int32" />
                <asp:Parameter Name="SEX" Type="Int32" />
                <asp:Parameter Name="ETHNIC_GP" Type="String" />
                <asp:Parameter Name="ADD1" Type="String" />
                <asp:Parameter Name="ADD2" Type="String" />
                <asp:Parameter Name="ADD3" Type="String" />
                <asp:Parameter Name="ADD4" Type="String" />
                <asp:Parameter Name="POSTCODE" Type="String" />
                <asp:Parameter Name="POSTCODE_OLD" Type="String" />
                <asp:Parameter Name="CONSENT" Type="String" />
                <asp:Parameter Name="DATE_BAPN_REG" Type="DateTime" />
                <asp:Parameter Name="CONS_NEPH" Type="String" />
                <asp:Parameter Name="RENAL_UNIT" Type="Int32" />
                <asp:Parameter Name="RADAR_NO" Type="Int32" />
            </UpdateParameters>
            <InsertParameters>
                <asp:Parameter Name="RR_NO" Type="String" />
                <asp:Parameter Name="DATE_REG" Type="DateTime"  />
                <asp:Parameter Name="NHS_NO" Type="Int32" />
                <asp:Parameter Name="HOSP_NO" Type="String" />
                <asp:Parameter Name="UKT_NO" Type="Int32" />
                <asp:Parameter Name="CHI_NO" Type="Int32" />
                <asp:Parameter Name="SNAME" Type="String" />
                <asp:Parameter Name="SNAME_ALIAS" Type="String" />
                <asp:Parameter Name="FNAME" Type="String" />
                <asp:Parameter Name="DOB" Type="DateTime" />
                <asp:Parameter Name="AGE" Type="Int32" />
                <asp:Parameter Name="SEX" Type="Int32" />
                <asp:Parameter Name="ETHNIC_GP" Type="String" />
                <asp:Parameter Name="ADD1" Type="String" />
                <asp:Parameter Name="ADD2" Type="String" />
                <asp:Parameter Name="ADD3" Type="String" />
                <asp:Parameter Name="ADD4" Type="String" />
                <asp:Parameter Name="POSTCODE" Type="String" />
                <asp:Parameter Name="POSTCODE_OLD" Type="String" />
                <asp:Parameter Name="CONSENT" Type="String" />
                <asp:Parameter Name="DATE_BAPN_REG" Type="DateTime" />
                <asp:Parameter Name="CONS_NEPH" Type="String" />
                <asp:Parameter Name="RENAL_UNIT" Type="Int32" />
                <asp:Parameter Direction="Output" Name="NewID" Type="Int32" />
            </InsertParameters>
        </asp:SqlDataSource>
        <asp:SqlDataSource ID="SqlDataSource2" runat="server" 
        ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
        SelectCommand="SELECT [eName], [eCode] FROM [tbl_Ethnicity] ORDER BY [eID]">
    </asp:SqlDataSource>
    <asp:SqlDataSource ID="SqlDataSource3" runat="server" ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" SelectCommand="SELECT [cID], [cName] FROM [tbl_Centres] ORDER BY [cName]">
	</asp:SqlDataSource>
</asp:Content>

