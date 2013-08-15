<%@ Page Title="" Language="VB" MasterPageFile="~/MasterPage.master" AutoEventWireup="false" CodeFile="patient-reg.aspx.vb" Inherits="patient_reg" %>

<%@ Register assembly="AjaxControlToolkit" namespace="AjaxControlToolkit" tagprefix="cc1" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
    <style type="text/css">
.style2 {
	border-collapse: collapse;
}
.feint {

color:silver;
}
</style>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <h1>Patient Registration</h1>
<p>To be able to register on the RaDaR website and view your details and 
treatment information, you need you RaDaR number, supplied to you by your 
doctor, your date of birth and a valid email address. Please enter these below. 
When you have done this a password will be emailed to you and you can then enter 
the site.</p>
<fieldset style="width:550px; padding:10px">
<table class="style2" style="width: 500px" cellpadding="5">
	<tr>
		<td>
		<asp:Label id="lblRadarNo" runat="server" Text="Radar Number" AssociatedControlID="txtRadarNo"></asp:Label>
		</td>
		<td>
		<asp:TextBox id="txtRadarNo" runat="server"></asp:TextBox>
		&nbsp;<asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" 
                ControlToValidate="txtRadarNo" ErrorMessage="*"></asp:RequiredFieldValidator>
		</td>
	</tr>
	<tr>
		<td>
		<asp:Label id="lblEmail" runat="server" AssociatedControlID="txtEmail" Text="Email Address"></asp:Label>
		</td>
		<td>
		<asp:TextBox id="txtEmail" runat="server" Width="250px" AutoCompleteType="Disabled" AutoComplete="off"></asp:TextBox>
		&nbsp;<asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" 
                ControlToValidate="txtEmail" ErrorMessage="*"></asp:RequiredFieldValidator>
		</td>
	</tr>
	<tr>
		<td>
		<asp:Label id="lblDOB" runat="server" AssociatedControlID="txtDOB" Text="Date of birth"></asp:Label>
		</td>
		<td>
		<asp:TextBox id="txtDOB" runat="server"></asp:TextBox>
            <cc1:TextBoxWatermarkExtender ID="txtDOB_TextBoxWatermarkExtender" 
                runat="server" Enabled="True" TargetControlID="txtDOB" WatermarkText="Select with the Calendar" WatermarkCssClass="feint">
            </cc1:TextBoxWatermarkExtender>
            <cc1:CalendarExtender ID="txtDOB_CalendarExtender" runat="server" 
                Enabled="True" TargetControlID="txtDOB" PopupButtonID="ImageButton1" Format="dd-MM-yyyy" >
            </cc1:CalendarExtender><asp:ImageButton ID="ImageButton1" runat="server" CausesValidation="False" ImageUrl="~/images/Calendar_scheduleHS.png"  />
&nbsp;<asp:RequiredFieldValidator ID="RequiredFieldValidator3" runat="server" 
                ControlToValidate="txtDOB" ErrorMessage="*"></asp:RequiredFieldValidator>
            &nbsp;<a href="#" class="info" style="background-color:White"><em>Help</em><span>To select a different month in the 
                date picker, click the current month at the top.<br /><br /> Click again to select 
                a year.</span>  </a></td>
	</tr>
	<tr>
		<td>
		    &nbsp;</td>
		<td>
		    &nbsp;</td>
	</tr>
	<tr>
		<td>
		<asp:Button id="btnSubmit" runat="server" Text="Register" />
		</td>
		<td>&nbsp;</td>
	</tr>
</table>
    <p>&nbsp;</p>
    <p>
    <asp:Label ID="lblSuccess" runat="server"></asp:Label></p>
    </fieldset>
    <p></p>
    <p>
        <asp:Label ID="lblDebug" runat="server"></asp:Label>
    </p>
</asp:Content>

