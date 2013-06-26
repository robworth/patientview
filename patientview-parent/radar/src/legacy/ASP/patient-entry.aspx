<%@ Page Title="" Language="VB" MasterPageFile="~/MasterPage.master" AutoEventWireup="false" CodeFile="patient-entry.aspx.vb" Inherits="patient_entry" Debug="true" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
    <style type="text/css">
input, select, textarea {
border: 1px solid gray;
}
select, input, textarea {
	font-size:95%;
	color:#2D2E2E
}
.style3 {
		border-collapse: collapse;
		border-style: none;
		border-color: inherit;
		border-width: 0;
		background-color: #B6DF9F;
	}
	.style4 {
		text-align: right;
	}
	.style5 {
		border-collapse: collapse;
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
    <div id="tabsC" > 
  <ul> 
    <li><a href="#" title="Demographics"><span class="hovered">Demographics</span></a></li> 
    <li><a href="#" title="Diagnosis"><span>Diagnosis</span></a></li> 
    <li><a href="#" title="Clinical Picture"><span>Clinical Picture</span></a></li> 
    <li><a href="#" title="Laboratory Results"><span>Laboratory Results</span></a></li> 
    <li><a href="#" title="Therapy"><span>Therapy</span></a></li> 
    <li><a href="#" title="3 Month Follow Up"><span>6 Month Follow Up</span></a></li> 
  </ul> 
</div> 
<div id="mainBlock">
    <p>
        Once you have entered the demographic details below you can edit and add to 
        the entry at any time.</p>
        <table style="width:100%; " cellpadding="8" cellspacing="0" class="style3">
        <tr><td style="width:50%;" ></td><td class="style4" ><strong>RADAR Number</strong></td>
			<td >
                <asp:TextBox ID="txtRadarNo" runat="server" BackColor="#EEF6ED" Text=" Auto Allocated" Font-Italic="true"></asp:TextBox>
            						</td></tr>
        <tr><td style="width:50%;" ></td><td class="style4" >Date Registered</td>
			<td >
                
                <asp:Label ID="lblDateReg" runat="server" BackColor="#EEF6ED" Width="100px"></asp:Label>
                
            						</td></tr>
        </table>
        <div style="width:100%; background-color:#EEF6ED">
<table style="width:100%;">
<tr>
<td style="width:50%; vertical-align:top;">
<table style="width:100%; background-color:#EEF6ED; margin:0;" cellpadding="5" cellspacing="0" class="style5" >
<tr><td>&nbsp;</td><td>
                &nbsp;</td> </tr>
<tr><td>Surname</td><td>
                <asp:TextBox ID="txtSurname" runat="server" Width="210px"></asp:TextBox>&nbsp;
                <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" 
                    ControlToValidate="txtSurname" ErrorMessage="" Text="*"></asp:RequiredFieldValidator>
            </td> </tr>
<tr><td>First Name</td><td>
                <asp:TextBox ID="txtFirstName" runat="server" Width="210px"></asp:TextBox>
            &nbsp;<asp:RequiredFieldValidator id="RequiredFieldValidator2" runat="server" ControlToValidate="txtFirstName" ErrorMessage="" Text="*"></asp:RequiredFieldValidator>
            </td> </tr>
<tr><td>Date of Birth</td><td>
    <asp:TextBox ID="txtDOB" runat="server"></asp:TextBox>
    &nbsp;<asp:RequiredFieldValidator id="RequiredFieldValidator3" runat="server" ControlToValidate="txtDOB" ErrorMessage="" Text="*"></asp:RequiredFieldValidator>
    &nbsp;
												Age&nbsp;
												<asp:TextBox id="txtAge" runat="server" width="50px" Enabled="false" ></asp:TextBox>
    </td> </tr>
<tr><td>Sex</td><td>
    <asp:DropDownList ID="DropDownList1" runat="server">
    <asp:ListItem Value="">Select</asp:ListItem>
        <asp:ListItem Value="1">Male</asp:ListItem>
        <asp:ListItem Value="2">Female</asp:ListItem>
        <asp:ListItem Value="9">Not specified</asp:ListItem>
    </asp:DropDownList>
    &nbsp;<asp:RequiredFieldValidator id="RequiredFieldValidator4" runat="server" ControlToValidate="DropDownList1" ErrorMessage="" Text="*"></asp:RequiredFieldValidator>
    </td> </tr>
<tr><td>Ethnic Group</td><td>
    <asp:DropDownList ID="DropDownList3" runat="server" 
        DataSourceID="SqlDataSource2" DataTextField="eName" DataValueField="eCode" AppendDataBoundItems="true">
        <asp:ListItem Value="">Please select</asp:ListItem>
    </asp:DropDownList>
    </td> </tr>
<tr><td>Address</td><td rowspan="4" style="vertical-align:top;">
    <asp:TextBox ID="txtAddr" runat="server" Rows="4" TextMode="MultiLine" 
        Width="295px"></asp:TextBox>
    </td> </tr>
<tr><td>&nbsp;</td> </tr>
<tr><td>&nbsp;</td> </tr>
<tr><td>&nbsp;</td> </tr>
<tr><td>Post Code</td><td>
    <asp:TextBox ID="txtPostCode" runat="server"></asp:TextBox>
    </td> </tr>
<tr><td>&nbsp;</td><td>&nbsp;</td> </tr>
<tr><td colspan="2"><asp:ValidationSummary id="ValidationSummary1" runat="server" DisplayMode="SingleParagraph" HeaderText="* Required fields" />
	</td> </tr>
<tr><td colspan="2">
	<asp:Button id="btnAdd" runat="server" Text="Add this patient" />
	</td> </tr>
<tr><td>&nbsp;</td><td>&nbsp;</td> </tr>


</table>
</td>
<td style="width:50%; vertical-align:top;">
<table style="width:100%; background-color:#EEF6ED; margin:0;" cellpadding="5" cellspacing="0" class="style5">
<tr><td style="width: 240px">&nbsp;</td><td>&nbsp;</td> </tr>
<tr><td style="width: 240px">Hospital Number</td><td>
                <asp:TextBox ID="txtHospNo" runat="server" ></asp:TextBox>
            								</td> </tr>
<tr><td style="width: 240px">NHS Number</td><td>
                <asp:TextBox ID="txtNHSNo" runat="server" ></asp:TextBox>
            								</td> </tr>
<tr><td style="width: 240px">Renal registry Number</td><td>
                <asp:TextBox ID="txtRenalRegNo" runat="server" ></asp:TextBox>
            								</td> </tr>
<tr><td style="width: 240px">UK Transplant Number</td><td>
                <asp:TextBox ID="txtUKTrNo" runat="server" ></asp:TextBox>
            								</td> </tr>
<tr><td style="width: 240px">CHI No. (Scotland Only)</td><td>
                <asp:TextBox ID="txtCHINo" runat="server" ></asp:TextBox>
            								</td> </tr>
<tr><td style="width: 240px">&nbsp;</td><td>&nbsp;</td> </tr>
<tr><td style="width: 240px">Consultant (GMC Code)</td><td>
                <asp:TextBox ID="txtCGMC" runat="server" ></asp:TextBox>
            								</td> </tr>
<tr><td style="width: 240px">Paediatric Renal unit
	<asp:RequiredFieldValidator id="RequiredFieldValidator5" runat="server" ControlToValidate="DropDownList2">*</asp:RequiredFieldValidator>
												</td><td>&nbsp;</td> </tr>
<tr><td colspan="2">
	<asp:DropDownList id="DropDownList2" runat="server" DataSourceID="SqlDataSource1" DataTextField="cName" DataValueField="cID" AppendDataBoundItems="true">
	<asp:ListItem Value="">Please select</asp:ListItem>
	</asp:DropDownList>
	&nbsp;</td> </tr>
<tr><td style="width: 240px">Date BAPN Registration</td><td>
                <asp:TextBox ID="txtDateBAPN" runat="server" ></asp:TextBox>
            								</td> </tr>
<tr><td style="width: 240px">&nbsp;</td><td>&nbsp;</td> </tr>
<tr><td style="width: 240px">Consent</td><td>&nbsp;</td> </tr>
<tr><td style="width: 240px">&nbsp;</td><td>&nbsp;</td> </tr>



</table>
</td>
</tr>
	
</table>

</div>
<p><asp:Label ID="lblDebug" runat="server"></asp:Label></p>
<p>&nbsp;<asp:SqlDataSource ID="SqlDataSource1" runat="server" ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" SelectCommand="SELECT [cID], [cName] FROM [tbl_Centres] ORDER BY [cName]">
	</asp:SqlDataSource>
    <asp:SqlDataSource ID="SqlDataSource2" runat="server" 
        ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
        SelectCommand="SELECT [eName], [eCode] FROM [tbl_Ethnicity] ORDER BY [eID]">
    </asp:SqlDataSource>
    </p>
	<%--<table cellpadding="5" class="style2" style="width: 100%;">
		<tr>
			<td style="width:25%;">&nbsp;</td>
			<td style="width:25%;">&nbsp;</td>
			<td style="width:25%;">RADAR Number</td>
			<td style="width:25%;">
                &nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>Date Registered</td>
			<td>
                &nbsp;</td>
		</tr>
		<tr>
			<td>Surname</td>
			<td>
                &nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>First Name</td>
			<td>
                &nbsp;</td>
			<td>Hospital Number</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>Date of Birth</td>
			<td>&nbsp;</td>
			<td>NHS Number</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>Sex</td>
			<td>&nbsp;</td>
			<td>Renal Registry Number</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>Ethnic Group</td>
			<td>&nbsp;</td>
			<td>UK Transplant Number</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>Address</td>
			<td>&nbsp;</td>
			<td>CHI No. (Scotland Only)</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>Consultant (GMC Code)</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>Paediatric Renal Unit</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>Post Code</td>
			<td>&nbsp;</td>
			<td>Date BAPN Registration</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>Consent</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
	</table>--%>
	
    </div>
</asp:Content>

