<%@ Page Title="" Language="VB" MasterPageFile="~/MasterPage.master" AutoEventWireup="false" CodeFile="hospitalisation.aspx.vb" Inherits="hospitalisation2aspx" %>
<%@ Register assembly="AjaxControlToolkit" namespace="AjaxControlToolkit" tagprefix="cc1" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
 <link href="tabs_lab.css" rel="stylesheet" type="text/css" />
<link href="tabs.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">

    function lblVisible() {

        var lbl = document.getElementById('ctl00_ContentPlaceHolder1_lblUploadWarn');
        if (document.getElementById('ctl00_ContentPlaceHolder1_txtBX_DATE').value == "") {
            lbl.style.color = "white";
        }
        else {
            lbl.style.color = "red";
        }
    }

</script>
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
    
.btmBorder {
border-bottom: 1px green solid;
height:30px;
vertical-align:middle;
}

 #tblSample input {
border:0;
}    

/* this because of the update panel for the tabs */
/*#tabsC {
margin-top:-35px;
}*/

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

</style>
<![endif]-->
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
 <div id="tabsC" > 
<ul> 
    <li><asp:hyperlink runat="server" ID="lnkDemographics" NavigateUrl="demographics.aspx" title="Demographics"><span>Demographics</span></asp:hyperlink></li> 
    <li><asp:hyperlink runat="server" ID="lnkDiagnosis" NavigateUrl="diagnosis.aspx" ToolTip="Diagnosis"><span>Diagnosis</span></asp:hyperlink></li> 
    <li><asp:hyperlink runat="server" ID="lnkLabResults" NavigateUrl="first-clinical.aspx" Tooltip="Laboratory Results"><span>First Visit</span></asp:hyperlink></li> 
    <li><asp:hyperlink runat="server" ID="lnk3Months" NavigateUrl="followup-clinical.aspx" Tooltip="Follow Up"><span>Follow Up</span></asp:hyperlink></li> 
    <li><asp:hyperlink runat="server" ID="lnkPathology" NavigateUrl="pathology.aspx" Tootip="Pathology"><span>Pathology</span></asp:hyperlink></li> 
    <li><asp:hyperlink runat="server" ID="lnkRelapse" NavigateUrl="relapse.aspx" Tooltip="Relapse"><span>Relapse</span></asp:hyperlink></li> 
    <li><asp:hyperlink runat="server" ID="lnkHospital" NavigateUrl="#" Tooltip="Hospitalisation"><span class="hovered">Hospitalisation</span></asp:hyperlink></li> 
    <li><asp:hyperlink runat="server" ID="lnkTimeLines" NavigateUrl="timelines.aspx" Tootip="Timelines"><span >Timelines</span></asp:hyperlink></li> 
</ul> 
</div> 
<div id="mainBlock">
<table cellpadding="5" style="width: 100%; background-color: #B6DF9F;">
	<tr>
        
		<td class="btmBorder">
            <asp:DropDownList ID="DropDownListAdminDate" runat="server" 
                 AppendDataBoundItems="True" 
                DataTextFormatString="{0:d}" AutoPostBack="True" 
                DataSourceID="SqlDataSource1" DataTextField="DATE_ADMIT" DataValueField="hID" 
                 >
                <asp:ListItem Value="" Text="Select"></asp:ListItem>
            </asp:DropDownList>           
&nbsp;Select previous record by admission date&nbsp;
            <asp:Button ID="btnAdd" runat="server" Text="Add new record" CausesValidation="false" />
        </td>
		<td class="btmBorder">&nbsp;</td>
		<td class="btmBorder" style="width:120px">
            <strong>Hospitalisation</strong></td>
	</tr>
	</table>
    <asp:Panel ID="pnlAdd" runat="server" Visible="false">
	<table cellpadding="4" style="width: 100%;" id="tblHeader">
	<tr>
		<td align="left" class="style4"><b>Hospitalisation Record</b></td>
		<td style="text-align:right; " class="style2">RADAR No.</td>
		<td >
            <asp:TextBox ID="txtRADAR_NO" runat="server" ReadOnly="True" Width="100px"></asp:TextBox>
        </td>
	</tr>
	<tr>
		<td align="left">Name
		<asp:textbox id="txtFNAME" runat="server" ReadOnly="True"></asp:textbox>
		&nbsp;<asp:textbox id="txtSNAME" runat="server" ReadOnly="True"></asp:textbox>
		&nbsp;DoB
		<asp:textbox id="txtDOB" runat="server" ReadOnly="True" Width="100px"></asp:textbox>
		</td>
		<td style="text-align:right; " >Hospital No.</td>
		<td>
            <asp:TextBox ID="txtHOSP_NO" runat="server" ReadOnly="True" Width="100px"></asp:TextBox>
        </td>
	</tr>
	<tr>
		<td style="text-align:left;" >&nbsp;<asp:TextBox ID="txtDateToday" runat="server" ForeColor="#B6DF9F" 
                BackColor="#B6DF9F" BorderColor="#B6DF9F"></asp:TextBox>
        </td>
		<td style="text-align:right; " >Diagnosis</td>
		<td>
            <asp:TextBox ID="txtDIAGNOSIS" runat="server" ReadOnly="True" BackColor="#FFFF99" 
                Width="100px"></asp:TextBox>
        </td>
	</tr>
</table>
<table style="width:100%; background-color:#eef6ed; font-size:85%" cellpadding="10" id="tblMain" runat="server">
<tr>
<td colspan="2" style="height:40px;">
    <asp:Button ID="btnSave2" runat="server" CssClass="saveBtn" Text="Save" />
    &nbsp;
    <asp:Button ID="btnUpdate2" runat="server" CssClass="saveBtn" Text="Update" />
    &nbsp;
    <asp:Label ID="lblUpdate2" runat="server"></asp:Label>
    </td>
</tr>
<tr>
<td style="width:40%"><fieldset style="width:95%; height:100px;">
    <table cellpadding="5" style="width:100%;" >
        <tr>
            <td class="style5">
                <b>Admission</b></td>
            <td>
                &nbsp;</td>
        </tr>
        <tr>
            <td class="style5">
                Date of admission</td>
            <td>
                <asp:TextBox ID="txtDATE_ADMIT" runat="server" Width="80px" ></asp:TextBox>
                <cc1:CalendarExtender ID="txtDATE_ADMIT_CalendarExtender" runat="server" 
                    Enabled="True" TargetControlID="txtDATE_ADMIT" Format="dd-MM-yyyy" 
                    PopupButtonID="imgCal1">
                </cc1:CalendarExtender>
                <asp:Imagebutton ID="imgCal1" runat="server" 
                    ImageUrl="~/images/Calendar_scheduleHS.png" CausesValidation="false" />
                &nbsp;<asp:CompareValidator ID="CompareValidator1" runat="server" 
                    ControlToCompare="txtDateToday" ControlToValidate="txtDATE_ADMIT" 
                    ErrorMessage="&gt;today" Type="Date" Operator="LessThanEqual" 
                    Display="Dynamic"></asp:CompareValidator>
                &nbsp;<asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" 
                    ControlToValidate="txtDATE_ADMIT" Display="Dynamic" ErrorMessage="*"></asp:RequiredFieldValidator>
            </td>
        </tr>
        <tr>
            <td class="style5">
                Date of Discharge</td>
            <td>
                <asp:TextBox ID="txtDATE_DISCHARGE" runat="server" Width="80px"></asp:TextBox>
                <cc1:CalendarExtender ID="txtDATE_DISCHARGE_CalendarExtender" runat="server" 
                    Enabled="True" TargetControlID="txtDATE_DISCHARGE" Format="dd-MM-yyyy" PopupButtonID="imgCal2">
                </cc1:CalendarExtender>
                <asp:Imagebutton ID="imgCal2" runat="server" 
                    ImageUrl="~/images/Calendar_scheduleHS.png" CausesValidation="false" />
                &nbsp;<asp:CompareValidator ID="CompareValidator2" runat="server" 
                    ControlToCompare="txtDateToday" ControlToValidate="txtDATE_DISCHARGE" 
                    ErrorMessage="&gt;today" Type="Date" Operator="LessThanEqual"></asp:CompareValidator>
            </td>
        </tr>
    </table>
    </fieldset>
    </td>
    <td valign="top" style="width:50%">
    <fieldset style="width:95%; height:100px;">
     <table cellpadding="5" style="width:100%;" >
     <tr>
     <td valign="top" style="width:150px;"><b>Reason for Admission</b></td>
     <td><asp:TextBox runat="server" TextMode="MultiLine" ID="txtREASON_ADMIT" 
             MaxLength="150" Rows="5" Width="95%"></asp:TextBox></td>
     </tr>
     </table>
    </fieldset>
    
    </td>
    
    </tr>
    <tr>
    <td colspan="2">
    <fieldset style="width:97%; padding:10px;">
    <p style="font-weight: 700">Comments&nbsp;
        <asp:Label ID="lblHid" runat="server" ForeColor="#EEF6ED"></asp:Label>
        </p>
    <asp:TextBox runat="server" ID="txtCOMMENTS" TextMode="MultiLine" Width="95%" 
            Rows="20" ></asp:TextBox>
    </fieldset>
    </td>
    </tr></table>
    <p></p>
    &nbsp;<asp:Button runat="server" Text="Save" ID="btnSave" CssClass="saveBtn" />
    &nbsp;<asp:Button ID="btnUpdate" runat="server" Text="Update" Visible="False" 
            CssClass="saveBtn" />
    <asp:Label ID="lblUpdate" runat="server"></asp:Label>

    </asp:Panel>
<p>
    <asp:Label ID="lblDebug" runat="server"></asp:Label>
    </p>
<p>
<asp:Label ID="lblPage" runat="server" Text=""></asp:Label></p>
</div> 
<asp:SqlDataSource ID="SqlDataSource1" runat="server" 
                ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
                SelectCommand="SELECT [DATE_ADMIT], [RADAR_NO], [hID] FROM [tbl_Hospitalisation] WHERE ([RADAR_NO] = @RADAR_NO) ORDER BY [DATE_ADMIT] DESC">
                <SelectParameters>
                    <asp:SessionParameter Name="RADAR_NO" SessionField="patientID" Type="Int32" />
                </SelectParameters>
            </asp:SqlDataSource>
</asp:Content>

