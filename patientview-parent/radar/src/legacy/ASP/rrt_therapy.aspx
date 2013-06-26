<%@ Page Title="" Language="VB" MasterPageFile="~/MasterPage.master" AutoEventWireup="false" CodeFile="rrt_therapy.aspx.vb" Inherits="rrt_therapy3" %>

<%@ Register assembly="AjaxControlToolkit" namespace="AjaxControlToolkit" tagprefix="cc1" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
    <link href="tabs_lab.css" rel="stylesheet" type="text/css" />
<link href="tabs.css" rel="stylesheet" type="text/css" />
    <%--<script type="text/javascript">
    function Check_DATE_ESRF() 
    {

if (document.getElementById('txtDATE_ESRF').value =="")
    alert("Please enter the Date of ESRF")
document.getElementById('txtDATE_ESRF').focus();
return false
    }
</script>--%>
<style type="text/css">

input, select, textarea {
	border: 1px solid gray;
}
select, input, textarea {
	font-size: 95%;
	color: #2D2E2E;
        height: 20px;
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
	border-collapse: collapse;
}

.style3
{
    border-collapse: collapse;
    width: 100%;
}

.style4
{
    width: 120px;
}
.style5
{
    width: 106px;
}


#ctl00_ContentPlaceHolder1_radNo, #ctl00_ContentPlaceHolder1_radYes, #ctl00_ContentPlaceHolder1_radEditRecurrYes, #ctl00_ContentPlaceHolder1_radEditRecurrNo {
border:0;
}

#ctl00_ContentPlaceHolder1_GridView3_ctl02_ctl00 {
border:0;
}

#ctl00_ContentPlaceHolder1_GridView3_ctl03_ctl00 {

border:0;
}
#ctl00_ContentPlaceHolder1_GridView3_ctl04_ctl00 {

border:0;
}
#ctl00_ContentPlaceHolder1_GridView3_ctl05_ctl00 {

border:0;
}

#ctl00_ContentPlaceHolder1_GridView3_ctl06_ctl00 {

border:0;
}

#ctl00_ContentPlaceHolder1_GridView3 input {
border:0;
}

#txEntry td {
vertical-align:top;
}

#radCell input {
border:0;
}

.zeroPadd {
padding:0;
/*border:0px;*/
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

.zeropadd {
/*border:0px;*/
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
	<li><asp:hyperlink runat="server" ID="lnkFollowupClinical" NavigateUrl="followup-clinical.aspx" Tooltip="Clinical Picture"><span>Clinical Picture</span></asp:hyperlink></li> 
    <li><asp:hyperlink runat="server" ID="lnkFollowupLab" NavigateUrl="followup-lab.aspx" ToolTip="Lab Results"><span >Lab Results</span></asp:hyperlink></li>
    <li><asp:hyperlink runat="server" ID="lnkFollowupTreatment" NavigateUrl="followup-treatment.aspx" ToolTip="Disease Treatment"><span>Disease Treatment</span></asp:hyperlink></li>
    <li><asp:hyperlink runat="server" ID="lnkTherapy" NavigateUrl="#" Tooltip="Therapy"><span class="hovered" >RRT Therapy</span></asp:hyperlink></li> 
        <%--<fieldset><legend>HD</legend>
	<br />
        <asp:GridView ID="GridView1" runat="server" AutoGenerateColumns="False" 
            DataSourceID="SqlDataSource4" Width="100%" Font-Size="85%" CellPadding="3" HeaderStyle-ForeColor="#3c982d" >
            <Columns>
                <asp:BoundField DataField="mType" HeaderText="HD Type" />
                <asp:BoundField DataField="DATE_START" DataFormatString="{0:dd-MMM-yyyy}" 
                    HeaderText="Date Started" />
                <asp:BoundField DataField="DATE_STOP" DataFormatString="{0:dd-MMM-yyyy}" 
                    HeaderText="Date Stopped" />
            </Columns>
            
        </asp:GridView>
	<table cellpadding="5" class="style2" style="width: 100%; font-size:85%; margin-top:10px;">
		<tr>
			<td>HD Type&nbsp;
                <asp:Label ID="lblWarn" runat="server" Text="" ForeColor="Red"></asp:Label>
            </td>
			<td>Date Started
                
                <br />
                
                <asp:CompareValidator ID="RangeValidator1" runat="server" 
                    ControlToValidate="txtDATE_START_HDIAL" ErrorMessage="&gt; today" 
                    ControlToCompare="txtDateToday" Operator="LessThanEqual" Type="Date"></asp:CompareValidator>
                
            </td>
			<td>Date Stopped
                <br />
                <asp:CompareValidator ID="CompareValidator1" runat="server" 
                    ControlToCompare="txtDATE_START_HDIAL" ControlToValidate="txtDATE_STOP_HDIAL" Display="Dynamic" 
                    ErrorMessage="&lt; start date" Operator="GreaterThanEqual" 
                    SetFocusOnError="True" Type="Date"></asp:CompareValidator>
            &nbsp;<asp:CompareValidator ID="CompareValidator4" runat="server" 
                    ControlToCompare="txtDateToday" ControlToValidate="txtDATE_STOP_HDIAL" 
                    ErrorMessage="&gt;today" Operator="LessThanEqual" Type="Date"></asp:CompareValidator>
            </td>
			<td>
			    &nbsp;</td>
		</tr>
		<tr>
			<td>
			<asp:DropDownList id="ddlHD" runat="server" DataSourceID="SqlDataSource1" 
                    DataTextField="mType" DataValueField="mID" AppendDataBoundItems="true" 
                    Font-Size="100%">
			<asp:ListItem Value="" Text="Select"></asp:ListItem>
			</asp:DropDownList>
			</td>
			<td>
			<asp:TextBox id="txtDATE_START_HDIAL" runat="server" Width="70px"></asp:TextBox>
			    <cc1:CalendarExtender ID="txtDATE_START_HDIAL_CalendarExtender" runat="server" 
                    Enabled="True" TargetControlID="txtDATE_START_HDIAL" PopupButtonID="ImageButton12" Format="dd-MM-yyyy">
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton12" runat="server" CausesValidation="False" ImageUrl="~/images/Calendar_scheduleHS.png"  />
			</td>
			<td>
			<asp:TextBox id="txtDATE_STOP_HDIAL" runat="server" Width="70px"></asp:TextBox>
			    <cc1:CalendarExtender ID="txtDATE_STOP_HDIAL_CalendarExtender" runat="server" 
                    Enabled="True" TargetControlID="txtDATE_STOP_HDIAL" PopupButtonID="ImageButton13" Format="dd-MM-yyyy" >
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton13" runat="server" CausesValidation="False" 
                    ImageUrl="~/images/Calendar_scheduleHS.png" Width="16px"  />
			</td>
			<td>
			<asp:Button id="btnHDAdd" runat="server" Text="Add" CausesValidation="false" />
			</td>
		</tr>
	</table>
	</fieldset>--%>
</ul>
</div>
<div id="subBlock">
    
    <asp:UpdatePanel ID="UpdatePanel1" runat="server">
    <ContentTemplate>
<table cellpadding="4" class="style1" style="width: 100%;" id="tblHeader">
		<tr>
			<td >
                <b>RRT Therapy</b></td>
			<td style="width:100px; text-align:right;">RADAR No:</td>
			<td  style="width:120px;padding-right:10px;">
			<asp:TextBox id="txtRADAR_NO" runat="server" ReadOnly="True" Width="100px"></asp:TextBox>
			</td>
		</tr>
		<tr>
			<td>Name
			<asp:TextBox id="txtFNAME" runat="server" Width="100px" ReadOnly="True"></asp:TextBox>
			&nbsp;<asp:TextBox id="txtSNAME" runat="server" Width="120px" ReadOnly="True"></asp:TextBox>
			&nbsp; DoB
			<asp:TextBox id="txtDOB" runat="server" Width="90px" ReadOnly="True"></asp:TextBox>
			    &nbsp;<asp:Label ID="lblID" runat="server" Visible="False"></asp:Label>
			</td>
			<td style="text-align:right;" >Hospital No:</td>
			<td >
			<asp:TextBox id="txtHOSP_NO" runat="server" ReadOnly="True" Width="100px"></asp:TextBox>
			</td>
		</tr>
		<tr>
			<td>
			    <asp:TextBox ID="txtDateToday" runat="server" BackColor="#B6DF9F" 
                    BorderColor="#B6DF9F" ForeColor="#B6DF9F"></asp:TextBox>
            </td>
			<td style="width:100px; text-align:right;">Diagnosis</td>
			<td>
                <asp:TextBox ID="txtDIAG" runat="server" ReadOnly="True" BackColor="#FFFF99" 
                    Width="100px"></asp:TextBox>
            </td>
		</tr>
		<tr>
			<td colspan="3" style="font-size:85%;"><%--Current Renal treatment Modality
			<asp:TextBox id="txtTMT_MODALITY" runat="server" Width="100px" ReadOnly="True"></asp:TextBox>--%>
			    <strong>Date of ESRF:</strong>  <asp:Label ID="lblESRF" runat="server"></asp:Label>
                <%--<asp:TextBox ID="txtDATE_ESRF" runat="server" Width="80px"></asp:TextBox>
                <cc1:CalendarExtender ID="txtDATE_ESRF_CalendarExtender" runat="server" 
                    Enabled="True" TargetControlID="txtDATE_ESRF" PopupButtonID="imgBtnESRF" Format="dd-MM-yyyy">
                </cc1:CalendarExtender>
                <asp:ImageButton ID="imgBtnESRF" runat="server" 
                    ImageUrl="~/images/Calendar_scheduleHS.png" />
			    &nbsp;<asp:CompareValidator ID="CompareValidator21" runat="server" 
                    ControlToCompare="txtDateToday" ControlToValidate="txtDATE_ESRF" 
                    ErrorMessage="&gt; today" Operator="LessThanEqual" Type="Date" 
                    ValidationGroup="esrf"></asp:CompareValidator>--%>
			    &nbsp; (Can be entered/amended on the <a href="diagnosis.aspx">Diagnosis page</a>)</td>
		</tr>
	</table>
	<table style="width: 100%; background-color:#EEF6ED; border:0px solid red;" cellpadding="0" cellspacing="10" id="tblMain" runat="server">
	<tr><td>
        <asp:Label ID="lblLogAll" runat="server" 
            Text="Log ALL treatments in last six months"></asp:Label>
        </td></tr>
	<tr>
	<td style="vertical-align:top; width:65%;">
	<%--<fieldset><legend>HD</legend>
	<br />
        <asp:GridView ID="GridView1" runat="server" AutoGenerateColumns="False" 
            DataSourceID="SqlDataSource4" Width="100%" Font-Size="85%" CellPadding="3" HeaderStyle-ForeColor="#3c982d" >
            <Columns>
                <asp:BoundField DataField="mType" HeaderText="HD Type" />
                <asp:BoundField DataField="DATE_START" DataFormatString="{0:dd-MMM-yyyy}" 
                    HeaderText="Date Started" />
                <asp:BoundField DataField="DATE_STOP" DataFormatString="{0:dd-MMM-yyyy}" 
                    HeaderText="Date Stopped" />
            </Columns>
            
        </asp:GridView>
	<table cellpadding="5" class="style2" style="width: 100%; font-size:85%; margin-top:10px;">
		<tr>
			<td>HD Type&nbsp;
                <asp:Label ID="lblWarn" runat="server" Text="" ForeColor="Red"></asp:Label>
            </td>
			<td>Date Started
                
                <br />
                
                <asp:CompareValidator ID="RangeValidator1" runat="server" 
                    ControlToValidate="txtDATE_START_HDIAL" ErrorMessage="&gt; today" 
                    ControlToCompare="txtDateToday" Operator="LessThanEqual" Type="Date"></asp:CompareValidator>
                
            </td>
			<td>Date Stopped
                <br />
                <asp:CompareValidator ID="CompareValidator1" runat="server" 
                    ControlToCompare="txtDATE_START_HDIAL" ControlToValidate="txtDATE_STOP_HDIAL" Display="Dynamic" 
                    ErrorMessage="&lt; start date" Operator="GreaterThanEqual" 
                    SetFocusOnError="True" Type="Date"></asp:CompareValidator>
            &nbsp;<asp:CompareValidator ID="CompareValidator4" runat="server" 
                    ControlToCompare="txtDateToday" ControlToValidate="txtDATE_STOP_HDIAL" 
                    ErrorMessage="&gt;today" Operator="LessThanEqual" Type="Date"></asp:CompareValidator>
            </td>
			<td>
			    &nbsp;</td>
		</tr>
		<tr>
			<td>
			<asp:DropDownList id="ddlHD" runat="server" DataSourceID="SqlDataSource1" 
                    DataTextField="mType" DataValueField="mID" AppendDataBoundItems="true" 
                    Font-Size="100%">
			<asp:ListItem Value="" Text="Select"></asp:ListItem>
			</asp:DropDownList>
			</td>
			<td>
			<asp:TextBox id="txtDATE_START_HDIAL" runat="server" Width="70px"></asp:TextBox>
			    <cc1:CalendarExtender ID="txtDATE_START_HDIAL_CalendarExtender" runat="server" 
                    Enabled="True" TargetControlID="txtDATE_START_HDIAL" PopupButtonID="ImageButton12" Format="dd-MM-yyyy">
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton12" runat="server" CausesValidation="False" ImageUrl="~/images/Calendar_scheduleHS.png"  />
			</td>
			<td>
			<asp:TextBox id="txtDATE_STOP_HDIAL" runat="server" Width="70px"></asp:TextBox>
			    <cc1:CalendarExtender ID="txtDATE_STOP_HDIAL_CalendarExtender" runat="server" 
                    Enabled="True" TargetControlID="txtDATE_STOP_HDIAL" PopupButtonID="ImageButton13" Format="dd-MM-yyyy" >
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton13" runat="server" CausesValidation="False" 
                    ImageUrl="~/images/Calendar_scheduleHS.png" Width="16px"  />
			</td>
			<td>
			<asp:Button id="btnHDAdd" runat="server" Text="Add" CausesValidation="false" />
			</td>
		</tr>
	</table>
	</fieldset>--%>
	<br/>
	<fieldset><legend>Dialysis</legend>
	<br />
        <asp:GridView ID="GridView2" runat="server" Width="100%" 
            HeaderStyle-ForeColor="#3c982d" Font-Size="85%" AutoGenerateColumns="False" 
            DataSourceID="SqlDataSource5" CellPadding="3" DataKeyNames="tID" OnRowEditing="GridView2_Row_Editing" OnRowCreated="GridView_RowCreated" OnRowDeleted="GridView2_RowDeleted">
            <Columns>
            <asp:BoundField DataField="tID" >
                    
                </asp:BoundField>
                <asp:BoundField DataField="mType" HeaderText="Dialysis Type">
                    <HeaderStyle Width="320px" />
                </asp:BoundField>
                <asp:BoundField DataField="DATE_START" HeaderText="Date Started" 
                     DataFormatString="{0:dd-MMM-yyy}" />
                <asp:BoundField DataField="DATE_STOP" HeaderText="Date Stopped" 
                    DataFormatString="{0:dd-MMM-yyy}" />
                     <asp:TemplateField>
                <ItemTemplate>
                <asp:Button ID="delButton" runat="server" CausesValidation="false" CommandName="Delete" OnClientClick="return confirm('Are you sure you want to delete this record?');" Text="Delete"></asp:Button>
                <asp:Button ID="editButton" runat="server" CausesValidation="false" CommandName="Edit" Text="Edit"></asp:Button>
                </ItemTemplate>
                </asp:TemplateField>
            </Columns>
            <HeaderStyle ForeColor="#3C982D" />
            <EmptyDataTemplate>
            <p>No data for this patient</p>
            </EmptyDataTemplate>
        </asp:GridView>
        <asp:panel runat="server" ID="pnlEditDial" Visible="false">
        <fieldset style="border-color:Red"><legend>Edit</legend>
        <table cellpadding="5" class="style3" style="font-size:85%; margin-top:10px">
		<tr>
			<td width="320px">Dialysis Type
                <asp:Label ID="lblEditStartWarn" runat="server" ForeColor="Red"></asp:Label>
                <br />
                <asp:Label ID="lblEditWarn" runat="server" ForeColor="Red"></asp:Label>
            </td>
			<td width="170px">Date Started
                <br />
                <asp:CompareValidator ID="CompareValidator1" runat="server" 
                    ControlToCompare="txtDateToday" ControlToValidate="txtEditDialStart" 
                    ErrorMessage="&gt; today" Operator="LessThanEqual" Type="Date" 
                    ValidationGroup="dialEdit"></asp:CompareValidator>
            </td>
			<td>Date Stopped
                <br />
            &nbsp;<asp:Label ID="lblEditStopWarn" runat="server" ForeColor="Red"></asp:Label>
            </td>
			<td>
			    <asp:Label ID="lblIntID" runat="server" ForeColor="White"></asp:Label>
			</td>
		</tr>
		<tr>
			<td>
			<asp:DropDownList id="ddlEditDial" runat="server" DataSourceID="SqlDataSource2" 
                    DataTextField="mType" DataValueField="mID" AppendDataBoundItems="true" 
                    Font-Size="100%">
			<asp:ListItem Value="" Text="Select"></asp:ListItem>
			</asp:DropDownList>
			</td>
			<td>
			<asp:TextBox id="txtEditDialStart" runat="server" Width="70px"></asp:TextBox>
			    <%--<cc1:MaskedEditExtender ID="txtDATE_START_DIAL_MaskedEditExtender" 
                    runat="server" CultureAMPMPlaceholder="" CultureCurrencySymbolPlaceholder="" 
                    CultureDateFormat="dd-MMM-yyyy" CultureDatePlaceholder="" CultureDecimalPlaceholder="" 
                    CultureThousandsPlaceholder="" CultureTimePlaceholder="" Enabled="True" 
                    MaskType="Date" TargetControlID="txtDATE_START_DIAL" AutoComplete="False" 
                    Mask="99/99/9999" UserDateFormat="DayMonthYear">
                </cc1:MaskedEditExtender>--%>
			    <cc1:CalendarExtender ID="txtEditDialStart_CalendarExtender" runat="server" 
                    Enabled="True" TargetControlID="txtEditDialStart" 
                    PopupButtonID="ImageButton20" Format="dd-MM-yyyy">
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton20" runat="server" CausesValidation="False" ImageUrl="~/images/Calendar_scheduleHS.png"  />
			</td>
			<td>
			<asp:TextBox id="txtEditDialStop" runat="server" Width="70px"></asp:TextBox>
			    <cc1:CalendarExtender ID="txtEditDialStop_CalendarExtender" runat="server" 
                    Enabled="True" TargetControlID="txtEditDialStop" 
                    PopupButtonID="ImageButton21" Format="dd-MM-yyyy">
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton21" runat="server" CausesValidation="False" ImageUrl="~/images/Calendar_scheduleHS.png"  />
			</td>
			<td align="right">
			<asp:Button id="btnDialUpdate" runat="server" Text="Save" 
                    CausesValidation="false" />&nbsp;
			<asp:Button ID="btnDialEditCancel" runat="server" Text="Cancel" CausesValidation="False" />
			</td>
		</tr>
	</table>
        </fieldset></asp:panel>
	<table cellpadding="5" class="style3" style="font-size:85%; margin-top:10px">
		<tr>
			<td width="320px">Dialysis Type<br />
                <asp:Label ID="lblWarn" runat="server" Text="" ForeColor="Red"></asp:Label>
            </td>
			<td width="170px">Date Started
                <br />
                <asp:CompareValidator ID="CompareValidator5" runat="server" 
                    ControlToCompare="txtDateToday" ControlToValidate="txtDATE_START_DIAL" 
                    ErrorMessage="&gt; today" Operator="LessThanEqual" Type="Date" 
                    ValidationGroup="dial"></asp:CompareValidator>
            </td>
			<td>Date Stopped
                <br />
            &nbsp;<asp:Label ID="lblStopWarn" runat="server" Text="" ForeColor="Red"></asp:Label>
            </td>
			<td>
			</td>
		</tr>
		<tr>
			<td>
			<asp:DropDownList id="ddlPD" runat="server" DataSourceID="SqlDataSource2" 
                    DataTextField="mType" DataValueField="mID" AppendDataBoundItems="true" 
                    Font-Size="100%">
			<asp:ListItem Value="" Text="Select"></asp:ListItem>
			</asp:DropDownList>
			</td>
			<td>
			<asp:TextBox id="txtDATE_START_DIAL" runat="server" Width="70px"></asp:TextBox>
			    <%--<cc1:MaskedEditExtender ID="txtDATE_START_DIAL_MaskedEditExtender" 
                    runat="server" CultureAMPMPlaceholder="" CultureCurrencySymbolPlaceholder="" 
                    CultureDateFormat="dd-MMM-yyyy" CultureDatePlaceholder="" CultureDecimalPlaceholder="" 
                    CultureThousandsPlaceholder="" CultureTimePlaceholder="" Enabled="True" 
                    MaskType="Date" TargetControlID="txtDATE_START_DIAL" AutoComplete="False" 
                    Mask="99/99/9999" UserDateFormat="DayMonthYear">
                </cc1:MaskedEditExtender>--%>
			    <cc1:CalendarExtender ID="txtDATE_START_DIAL_CalendarExtender" runat="server" 
                    Enabled="True" TargetControlID="txtDATE_START_DIAL" PopupButtonID="ImageButton11" Format="dd-MM-yyyy">
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton11" runat="server" CausesValidation="False" ImageUrl="~/images/Calendar_scheduleHS.png"  />
			</td>
			<td>
			<asp:TextBox id="txtDATE_STOP_DIAL" runat="server" Width="70px"></asp:TextBox>
			    <cc1:CalendarExtender ID="txtDATE_STOP_DIAL_CalendarExtender" runat="server" 
                    Enabled="True" TargetControlID="txtDATE_STOP_DIAL" PopupButtonID="ImageButton14" Format="dd-MM-yyyy">
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton14" runat="server" CausesValidation="False" ImageUrl="~/images/Calendar_scheduleHS.png"  />
			</td>
			<td align="right">
			<asp:Button id="btnDialAdd" runat="server" Text="Add" ValidationGroup="dial"  />
			</td>
		</tr>
	</table>
	</fieldset>
	<br/><br />
	

		
	
	<fieldset><legend>Transplant</legend>
	<p>
<%--        <asp:GridView ID="GridView3" runat="server" AutoGenerateColumns="False" 
            DataKeyNames="trID" DataSourceID="SqlDataSource6" Width="100%" CellPadding="3" OnRowEditing="GridView3_Row_Editing" OnRowCreated="GridView_RowCreated" OnRowDeleted="GridView3_RowDeleted" >
            <HeaderStyle ForeColor="#3c982d" Font-Size="85%" HorizontalAlign="Center" />
            <RowStyle HorizontalAlign="Center" Font-Size="85%" />
            <Columns>
                <asp:BoundField DataField="trID" HeaderText="trID" InsertVisible="False" 
                    ReadOnly="True" SortExpression="trID"  />
                <asp:BoundField DataField="RADAR_NO" HeaderText="RADAR_NO" 
                    SortExpression="RADAR_NO"  Visible="false"/>
                <asp:BoundField DataField="TRANSPLANT_COUNTER" HeaderText="TRANSPLANT_COUNTER" 
                    SortExpression="TRANSPLANT_COUNTER" Visible="false" />
                <asp:BoundField DataField="DATE_TRANSPLANT" HeaderText="Date Tx" 
                    SortExpression="DATE_TRANSPLANT" DataFormatString="{0:dd-MMM-yyyy}" ItemStyle-Width="85px" >
                	<ItemStyle Width="85px" />
				</asp:boundfield>
                <asp:BoundField DataField="trDesc" HeaderText="Type" 
                    SortExpression="trDesc" ItemStyle-HorizontalAlign="Left" HeaderStyle-HorizontalAlign="Left" />
                <asp:BoundField DataField="DATE_TX_REJECT" HeaderText="Date Reject" 
                    SortExpression="DATE_TX_REJECT" DataFormatString="{0:dd-MMM-yyyy}" ItemStyle-Width="85px">
                	<ItemStyle Width="85px" />
				</asp:boundfield>
                <asp:BoundField DataField="DATE_BX_TXK" HeaderText="Date Biopsy" 
                    SortExpression="DATE_BX_TXK" DataFormatString="{0:dd-MMM-yyyy}"  ItemStyle-Width="85px">
                    <ItemStyle Width="85px" />
				</asp:boundfield>
                
		<HeaderTemplate>Recurr</HeaderTemplate>
		<ItemTemplate><asp:Label ID="Label1" runat="server"><%#GetRecurr(DataBinder.Eval(Container, "DataItem.TRANS_RECURR"))%></asp:Label></ItemTemplate>
		<ItemStyle Width="45px" />
		</asp:TemplateField>
				
                <asp:BoundField DataField="DATE_RECURR_TXK" HeaderText="Date Recurr" 
                    SortExpression="DATE_RECURR_TXK" DataFormatString="{0:dd-MMM-yyyy}" ItemStyle-Width="85px" >
                	<ItemStyle Width="85px" />
				</asp:boundfield>
                <asp:BoundField DataField="DATE_NEPHRECT" HeaderText="Date Failure" 
                    SortExpression="DATE_NEPHRECT" DataFormatString="{0:dd-MMM-yyyy}" ItemStyle-Width="85px">
                	<ItemStyle Width="85px" />
				</asp:boundfield>
                    <asp:TemplateField >
                <ItemTemplate>
                <asp:Button ID="delButton" runat="server" CausesValidation="false" CommandName="Delete" OnClientClick="return confirm('Are you sure you want to delete this record?');" Text="Delete" BorderColor="Gray" BorderWidth="1"></asp:Button>
                <asp:Button ID="editButton" runat="server" CausesValidation="false" CommandName="Edit" Text="Edit" BorderColor="Gray" BorderWidth="1"></asp:Button>

                </ItemTemplate>
                		<ItemStyle Width="80px" />
                </asp:TemplateField>
                <asp:ButtonField ButtonType="Button" CausesValidation="false" CommandName="txInsert" Text="Add" ControlStyle-BorderStyle="Solid" ControlStyle-BorderColor="Gray" ControlStyle-BorderWidth="1"  />
            </Columns>
            <HeaderStyle ForeColor="#3C982D" />
        </asp:GridView>
--%>

        <asp:GridView ID="GridView3" runat="server" AutoGenerateColumns="False" 
            DataKeyNames="trID" DataSourceID="SqlDataSource6" OnRowDataBound="GridView3_RowDataBound" OnRowEditing="GridView3_Row_Editing" OnRowDeleted="GridView3_RowDeleted" RowStyle-VerticalAlign="top" CellPadding="5" OnRowCreated="GridView_RowCreated" Font-Size="80%">
<RowStyle VerticalAlign="Top"></RowStyle>

<HeaderStyle ForeColor="#3C982D" />
            <Columns>
            
           
                <asp:BoundField DataField="trID" HeaderText="" InsertVisible="False" 
                    ReadOnly="True" SortExpression="trID" Visible="true" ItemStyle-Width="1" 
                    ItemStyle-ForeColor="White" HeaderStyle-ForeColor="White" >
<HeaderStyle ForeColor="White"></HeaderStyle>

<ItemStyle ForeColor="White" Width="1px"></ItemStyle>
                </asp:BoundField>
                <asp:BoundField DataField="DATE_TRANSPLANT" HeaderText="Date Tx" 
                    SortExpression="DATE_TRANSPLANT" DataFormatString="{0:d}" />
                <asp:BoundField DataField="trDesc" HeaderText="Type" 
                    SortExpression="trDesc" HeaderStyle-HorizontalAlign="Left" >
<HeaderStyle HorizontalAlign="Left"></HeaderStyle>
                </asp:BoundField>
                
                    <asp:TemplateField>
		<HeaderTemplate>Recurr</HeaderTemplate>
		<ItemTemplate><asp:Label ID="Label1" runat="server"><%#GetRecurr(DataBinder.Eval(Container, "DataItem.TRANS_RECURR"))%></asp:Label></ItemTemplate>
		<ItemStyle Width="45px" HorizontalAlign="Center" />
		</asp:TemplateField>
                <asp:BoundField DataField="DATE_RECURR_TXK" HeaderText="Date Recurr" 
                    SortExpression="DATE_RECURR_TXK" DataFormatString="{0:d}" />
                    
                   <asp:BoundField DataField="DATE_NEPHRECT" HeaderText="Date Failure" 
                    SortExpression="DATE_NEPHRECT" DataFormatString="{0:d}" /> 
                <asp:TemplateField HeaderText="Date Reject &nbsp;&nbsp; Date Biopsy" HeaderStyle-HorizontalAlign="Left"  >
                    
                   
                    <ItemTemplate>
                        <asp:GridView ID="GridView4" runat="server" AutoGenerateColumns="False" 
                            DataSourceID="SqlDataSource7" DataKeyNames="recID" CellPadding="5" OnRowCreated="GridView_RowCreated">
                            <Columns>
                           
                             <asp:BoundField DataField="trID" HeaderText="trID" 
                                    SortExpression="trID"  />
                                <asp:BoundField DataField="trRejectDate" HeaderText="Date Reject" HeaderStyle-ForeColor="#eef6ed" 
                                    SortExpression="trRejectDate" DataFormatString="{0:d}" />
                                <asp:BoundField DataField="trBiopsyDate" HeaderText="Date Biopsy" HeaderStyle-ForeColor="#eef6ed" 
                                    SortExpression="trBiopsyDate" DataFormatString="{0:d}" />
                                
                                     <asp:BoundField DataField="recID" HeaderText="" Visible="false" />
                                     <asp:TemplateField>
                                     <ItemTemplate>
                                     <asp:Button runat="server" ID="btnDel" CausesValidation="false" CommandName="Delete" Text="X" OnClientClick="return confirm('Are you sure you want to delete this record?');" ForeColor="Red" Font-Bold="true" ToolTip="Delete" BorderWidth="0" BackColor="#eef6ed" />
                                     </ItemTemplate>
                                     </asp:TemplateField>
                            </Columns>
                        </asp:GridView>
                        
                        <asp:SqlDataSource ID="SqlDataSource7" runat="server" 
            ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
            DeleteCommand="DELETE FROM [tbl_Transplant_Reject] WHERE [recID] = @recID" 
            InsertCommand="INSERT INTO [tbl_Transplant_Reject] ([trID], [trRejectDate], [trBiopsyDate]) VALUES (@trID, @trRejectDate, @trBiopsyDate)" 
            SelectCommand="SELECT [recID], [trID], [trRejectDate], [trBiopsyDate] FROM [tbl_Transplant_Reject] WHERE ([trID] = @trID) ORDER BY [trRejectDate] DESC" 
            UpdateCommand="UPDATE [tbl_Transplant_Reject] SET [trID] = @trID, [trRejectDate] = @trRejectDate, [trBiopsyDate] = @trBiopsyDate WHERE [recID] = @recID">
            <SelectParameters>
                <asp:Parameter Name="trID" Type="Int32" />
            </SelectParameters>
            <DeleteParameters>
                <asp:Parameter Name="recID" Type="Int32" />
            </DeleteParameters>
            <UpdateParameters>
                <asp:Parameter Name="trID" Type="Int32" />
               <asp:Parameter Name="trRejectDate" Type="DateTime" />
                <asp:Parameter Name="trBiopsyDate" Type="DateTime" />
               
                <asp:Parameter Name="recID" Type="Int32" />
            </UpdateParameters>
            <InsertParameters>
                <asp:Parameter Name="trID" Type="Int32" />
               <asp:Parameter Name="trRejectDate" Type="DateTime" />
                <asp:Parameter Name="trBiopsyDate" Type="DateTime" />
                
            </InsertParameters>
        </asp:SqlDataSource>
                    </ItemTemplate>
                    <ControlStyle CssClass="zeroPadd"  Font-Size="100%" />
                    <ItemStyle CssClass="zeroPadd"  />
                    <HeaderStyle Font-Size="95%" />
                </asp:TemplateField>
                 <asp:TemplateField >
                <ItemTemplate>
                <asp:Button ID="delButton" runat="server" CausesValidation="false" CommandName="Delete" OnClientClick="return confirm('Are you sure you want to delete this record?');" Text="Delete" BorderColor="Gray" BorderWidth="1"></asp:Button>
                <asp:Button ID="editButton" runat="server" CausesValidation="false" CommandName="Edit" Text="Edit" BorderColor="Gray" BorderWidth="1"></asp:Button>
                <asp:Button ID="addRejectButton" runat="server" OnClick="AddRejectData" Text="Add Reject" ToolTip="Add Reject Data" BorderColor="Gray" BorderWidth="1"  />
</ItemTemplate>
                		<ItemStyle Width="170px" />
                		
                </asp:TemplateField>
                
            </Columns>
            
        </asp:GridView>

        </p>
        <p></p>
                <asp:Panel ID="pnlAdReject" runat="server" Visible="false">
            <fieldset style="border:1px solid red; width:550px;"><legend> Add reject data</legend>
            <table cellpadding="5" class="style1" style="font-size:85%;">
                <tr>
                    <td>
                        Date reject
                        </td>
                    <td>
                        Date biopsy</td>
                    
                    <td>
                        <asp:TextBox ID="txtTrID" runat="server" Width="30px" BackColor="#EEF6ED" 
                            BorderColor="#EEF6ED" ForeColor="#EEF6ED"></asp:TextBox>
                    </td>
                    <td>
                        <asp:TextBox ID="txtDateTxCompare" runat="server" BackColor="#EEF6ED" 
                            BorderColor="#EEF6ED" ForeColor="#EEF6ED"></asp:TextBox>
                    </td>
                    <td>
                        <asp:Label ID="lblTxDate" runat="server" Font-Bold="True" Text="Date of Tx"></asp:Label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <asp:TextBox ID="txtDateRejectAdd" runat="server" Width="80px"></asp:TextBox>
                        <cc1:CalendarExtender ID="txtDateRejectAdd_CalendarExtender" runat="server" 
                            Enabled="True" TargetControlID="txtDateRejectAdd" PopupButtonID="imgCal1" Format="dd-MM-yyyy">
                        </cc1:CalendarExtender>
                        <asp:Imagebutton ID="imgCal1" runat="server" 
                            ImageUrl="~/images/Calendar_scheduleHS.png" CausesValidation="false" />
                    </td>
                    <td>
                        <asp:TextBox ID="txtDateBiopsyAdd" runat="server" Width="80px"></asp:TextBox>
                        <cc1:CalendarExtender ID="txtDateBiopsyAdd_CalendarExtender" runat="server" 
                            Enabled="True" TargetControlID="txtDateBiopsyAdd" PopupButtonID="imgCal2" Format="dd-MM-yyyy">
                        </cc1:CalendarExtender>
                        <asp:Imagebutton ID="imgCal2" runat="server" 
                            ImageUrl="~/images/Calendar_scheduleHS.png" CausesValidation="false" />
                    </td>
                    
                    <td>
                        <asp:Button ID="btnAddRejectData" runat="server" Text="Add" />
                    </td>
                    <td>
                        <asp:Button ID="btnRejectEditCancel" runat="server" Text="Cancel" 
                            CausesValidation="False" />
                    </td>
                    <td>
                        <asp:Label ID="lblDateTx" runat="server" Font-Bold="True"></asp:Label>
                    </td>
                </tr>
                <tr>
                    <td>
                        <asp:CompareValidator ID="CompareValidator22" runat="server" 
                            ControlToCompare="txtDateTxCompare" ControlToValidate="txtDateRejectAdd" 
                            Display="Dynamic" ErrorMessage="&lt; Tx date" Operator="GreaterThanEqual" 
                            Type="Date"></asp:CompareValidator>
                        &nbsp;</td>
                    
                    <td>
                        <asp:CompareValidator ID="CompareValidator23" runat="server" 
                            ControlToCompare="txtDateTxCompare" ControlToValidate="txtDateBiopsyAdd" 
                            Display="Dynamic" ErrorMessage="&lt; Tx date" Operator="GreaterThanEqual" 
                            Type="Date"></asp:CompareValidator>
                    </td>
                    <td>
                        &nbsp;</td>
                    <td colspan="2">
                        <asp:Label ID="lblAddRejectWarn" runat="server" ForeColor="Red" 
                            Text="Reject can't be later than failure" Visible="False"></asp:Label>
                    </td>
                    
                </tr>
            </table>
        </fieldset></asp:Panel>

        <asp:Panel ID="pnlTxEdit" runat="server" Visible="false">
        <fieldset style="border:1px solid red"><legend>Edit</legend>
                <table id="Table1" cellpadding="5" class="style2" style="width: 100%; font-size:80%; margin-top:10px; ">
	<tr>
		<td style="height:30px; vertical-align:top;">Date Tx<asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" 
                ControlToValidate="txtEditDateTX" ErrorMessage="*" ValidationGroup="txEdit"></asp:RequiredFieldValidator>
            <br /><asp:CompareValidator ID="CompareValidator9" runat="server" 
                ControlToCompare="txtDateToday" ControlToValidate="txtEditDateTX" 
                ErrorMessage="&gt; today" Operator="LessThanEqual" Type="Date" ValidationGroup="txEdit"></asp:CompareValidator>
       </td>
		<td valign="top">Type<asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" 
                ControlToValidate="DropDownList2" ErrorMessage="*" ValidationGroup="txEdit"></asp:RequiredFieldValidator>
        </td>
		<%--<td> Date Reject<br />
                    <asp:CompareValidator ID="CompareValidator10" runat="server" 
                        ControlToCompare="txtDateToday" ControlToValidate="txtEditDateReject" 
                        ErrorMessage="&gt; today" Operator="LessThanEqual" Type="Date" 
                            ValidationGroup="txEdit"></asp:CompareValidator></td>
		<td>Date Biopsy<br />
            <asp:CompareValidator ID="CompareValidator11" runat="server" 
                ControlToCompare="txtDateToday" ControlToValidate="txtEditDateBiop" 
                ErrorMessage="&gt; today" Operator="LessThanEqual" Type="Date" 
                ValidationGroup="txEdit"></asp:CompareValidator></td>--%>
		<td valign="top">Recurr</td>
		<td width="85px">Date Recurr<br />
            <asp:CompareValidator ID="CompareValidator12" runat="server" 
                ControlToCompare="txtDateToday" ControlToValidate="txtEditDateRecurr" 
                ErrorMessage="&gt; today" Operator="LessThanEqual" Type="Date" 
                ValidationGroup="tx"></asp:CompareValidator></td>
		<%--<td valign="top">Date Failure<asp:CompareValidator ID="CompareValidator14" runat="server" 
                ControlToCompare="txtDateToday" ControlToValidate="txtEditDateNeph" 
                ErrorMessage="&gt; today" Operator="LessThanEqual" Type="Date" 
                ValidationGroup="txEdit"></asp:CompareValidator></td>--%>
	    <td width="85px">
            Date Failure<br />
            <asp:CompareValidator ID="CompareValidator25" runat="server" 
                ControlToCompare="txtDateToday" ControlToValidate="txtEditDateFail" 
                ErrorMessage="&gt; today" Operator="LessThanEqual" ValidationGroup="tx" 
                Type="Date"></asp:CompareValidator>
        </td>
	</tr>
	<tr>
		<td><asp:TextBox id="txtEditDateTx" runat="server" Width="70px"></asp:TextBox>
			    <cc1:CalendarExtender ID="CalendarExtender_EditDateTx" runat="server" 
                    Enabled="True" TargetControlID="txtEditDateTx" PopupButtonID="ImageButton4" Format="dd-MM-yyyy">
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton4" runat="server" CausesValidation="False" 
                    ImageUrl="~/images/Calendar_scheduleHS.png" />
        </td>
		<td><asp:DropDownList ID="DropDownList2" runat="server" 
                    DataSourceID="SqlDataSource3" DataTextField="trDesc" DataValueField="trID" AppendDataBoundItems="true">
                    <asp:ListItem Value="" Text="Select"></asp:ListItem>
                </asp:DropDownList>
        </td>
	<%--	<td><asp:TextBox id="txtEditDateReject" runat="server" Width="70px"></asp:TextBox>
			    <cc1:CalendarExtender ID="CalendarExtender_EditDateReject" runat="server" 
                    Enabled="True" TargetControlID="txtEditDateReject" PopupButtonID="ImageButton5" Format="dd-MM-yyyy" >
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton5" runat="server" CausesValidation="False" 
                    ImageUrl="~/images/Calendar_scheduleHS.png" /></td>
		<td><asp:TextBox ID="txtEditDateBiop" runat="server" Width="90px"></asp:TextBox>
            <cc1:CalendarExtender ID="CalendarExtender_EditDateBiop" runat="server" 
                Enabled="True" Format="dd-MM-yyyy" PopupButtonID="imagebutton7" 
                TargetControlID="txtEditDateBiop">
            </cc1:CalendarExtender><asp:ImageButton ID="ImageButton7" runat="server" CausesValidation="False" 
                    ImageUrl="~/images/Calendar_scheduleHS.png" /></td>--%>
		<td id="Td1"><asp:RadioButton ID="radEditRecurrYes" GroupName="radEditRecurr" runat="server" Text="Yes" />&nbsp;<asp:RadioButton 
                ID="radEditRecurrNo" GroupName="radEditRecurr" runat="server" Text="No" /></td>
		<td><asp:TextBox id="txtEditDateRecurr" runat="server" Width="70px"></asp:TextBox>
			    <cc1:CalendarExtender ID="CalendarExtender_EditDateRecurr" runat="server" 
                    Enabled="True" TargetControlID="txtEditDateRecurr" PopupButtonID="ImageButton8" Format="dd-MM-yyyy" >
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton8" runat="server" CausesValidation="False" 
                    ImageUrl="~/images/Calendar_scheduleHS.png" /></td>
	<%--	<td><asp:TextBox id="txtEditDateNeph" runat="server" Width="70px"></asp:TextBox>
			    <cc1:CalendarExtender ID="CalendarExtender_EditDateNeph" runat="server" 
                    Enabled="True" TargetControlID="txtEditDateNeph" PopupButtonID="ImageButton10" Format="dd-MM-yyyy" >
                </cc1:CalendarExtender>
                <asp:ImageButton ID="Imagebutton10" runat="server" CausesValidation="False" 
                    ImageUrl="~/images/Calendar_scheduleHS.png" /></td>--%>
	    <td>
            <asp:TextBox ID="txtEditDateFail" runat="server" Width="70px"></asp:TextBox>
            <cc1:CalendarExtender ID="txtEditDateFail_CalendarExtender" runat="server" 
                Enabled="True" TargetControlID="txtEditDateFail" PopupButtonID="imgBtn22"  Format="dd-MM-yyyy">
            </cc1:CalendarExtender>
            <asp:ImageButton ID="imgBtn22" runat="server" 
                ImageUrl="~/images/Calendar_scheduleHS.png" />
        </td>
	</tr>
	<tr>
		<td colspan="5"><%--<asp:CompareValidator ID="CompareValidator15" runat="server" 
                        ControlToCompare="txtEditDateTX" ControlToValidate="txtEditDateReject" 
                        Display="Dynamic" ErrorMessage="Date Rejection must be later than Tx" 
                        Operator="GreaterThan" Type="Date" ValidationGroup="txEdit"></asp:CompareValidator>--%>
                   <%-- <br />
                    <asp:CompareValidator ID="CompareValidator16" runat="server" 
                        ControlToCompare="txtEditDateTX" ControlToValidate="txtEditDateBiop" Display="Dynamic" 
                        ErrorMessage="Date Biopsy must be later than Tx" Operator="GreaterThan" 
                        Type="Date" ValidationGroup="txEdit"></asp:CompareValidator>
                    <br />--%>
                    <%--<asp:CompareValidator ID="CompareValidator17" runat="server" 
                        ControlToCompare="txtEditDateTX" ControlToValidate="txtEditDateNeph" Display="Dynamic" 
                        ErrorMessage="Date Nephrectomy must be greater than Tx" Operator="GreaterThan" 
                        Type="Date" ValidationGroup="txEdit"></asp:CompareValidator>
                    <br />--%>
                    <asp:CompareValidator ID="CompareValidator18" runat="server" 
                        ControlToCompare="txtEditDateTX" ControlToValidate="txtEditDateRecurr" 
                        Display="Dynamic" ErrorMessage="Date Recurrence must be later than Tx" 
                        Operator="GreaterThan" Type="Date" ValidationGroup="txEdit"></asp:CompareValidator>
            &nbsp;
            <asp:Label ID="lblEditTxWarn" runat="server" ForeColor="Red"></asp:Label>
        </td>
		
		<td align="right"> <asp:Button ID="btnTxEdit" runat="server" Text="Save" ValidationGroup="txEdit" />
		<asp:Button ID="txEditCancel" runat="server" Text="Cancel" CausesValidation="false" />
		    <br />
            <asp:Label ID="lblTxID" runat="server" ForeColor="#EEF6ED"></asp:Label>
		</td>
	</tr>
</table>

        </fieldset></asp:Panel>
        
        <table id="txEntry" cellpadding="5" class="style2" style="font-size:80%; margin-top:10px; ">
	<tr>
		<td style="height:30px; vertical-align:top; width:120px">Date Tx<asp:RequiredFieldValidator ID="RequiredFieldValidator4" runat="server" 
                ControlToValidate="txtDateTX" ErrorMessage="*" ValidationGroup="tx"></asp:RequiredFieldValidator>
            <br /><asp:CompareValidator ID="CompareValidator2" runat="server" 
                ControlToCompare="txtDateToday" ControlToValidate="txtDateTX" 
                ErrorMessage="&gt; today" Operator="LessThanEqual" Type="Date" ValidationGroup="tx"></asp:CompareValidator>
       </td>
		<td style="width:260px;">Type<asp:RequiredFieldValidator ID="RequiredFieldValidator5" runat="server" 
                ControlToValidate="DropDownList1" ErrorMessage="*" ValidationGroup="tx"></asp:RequiredFieldValidator>
        </td>
		<td class="style4">Recurr</td>
		<td class="style5">Date Recurr<br />
            <asp:CompareValidator ID="CompareValidator6" runat="server" 
                ControlToCompare="txtDateToday" ControlToValidate="txtDateRecurr" 
                ErrorMessage="&gt; today" Operator="LessThanEqual" Type="Date" 
                ValidationGroup="tx"></asp:CompareValidator></td>
                <td class="style5">
                    Date Failure<br />
                    <asp:CompareValidator ID="CompareValidator24" runat="server" 
                        ControlToCompare="txtDateToday" ControlToValidate="txtDATE_NEPHRECT" 
                        ErrorMessage="&gt; today" Operator="LessThanEqual" Type="Date" 
                        ValidationGroup="tx"></asp:CompareValidator>
        </td>
                <td>&nbsp;</td>
		<%--<td>Date Failure <asp:CompareValidator ID="CompareValidator7" runat="server" 
                ControlToCompare="txtDateToday" ControlToValidate="txtDateNeph" 
                ErrorMessage="&gt; today" Operator="LessThanEqual" Type="Date" 
                ValidationGroup="tx"></asp:CompareValidator></td>--%>
	</tr>
	<tr>
		<td valign="top"><asp:TextBox id="txtDateTx" runat="server" Width="70px" Height="15px"></asp:TextBox>
			    <cc1:CalendarExtender ID="CalendarExtender1" runat="server" 
                    Enabled="True" TargetControlID="txtDateTX" PopupButtonID="ImageButton1" Format="dd-MM-yyyy">
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton1" runat="server" CausesValidation="False" 
                    ImageUrl="~/images/Calendar_scheduleHS.png" />
        </td>
		<td valign="middle"><asp:DropDownList ID="DropDownList1" runat="server" 
                    DataSourceID="SqlDataSource3" DataTextField="trDesc" DataValueField="trID" AppendDataBoundItems="true">
                    <asp:ListItem Value="" Text="Select"></asp:ListItem>
                </asp:DropDownList>
        </td>
		<td id="radCell" class="style4"><asp:RadioButton ID="RadYes" GroupName="radRecurr" runat="server" Text="Yes" />&nbsp;<asp:RadioButton 
                ID="RadNo" GroupName="radRecurr" runat="server" Text="No" /></td>
		<td valign="baseline" class="style5"><asp:TextBox id="txtDateRecurr" runat="server" Width="70px" 
                Height="15px"></asp:TextBox>
			    <cc1:CalendarExtender ID="CalendarExtender4" runat="server" 
                    Enabled="True" TargetControlID="txtDateRecurr" PopupButtonID="ImageButton2" Format="dd-MM-yyyy" >
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton2" runat="server" CausesValidation="False" 
                    ImageUrl="~/images/Calendar_scheduleHS.png" /></td>
		<td class="style5">
            <asp:TextBox ID="txtDATE_NEPHRECT" runat="server" Width="70px" Height="15px"></asp:TextBox>
            <cc1:CalendarExtender ID="txtDATE_NEPHRECT_CalendarExtender" runat="server" 
                Enabled="True" TargetControlID="txtDATE_NEPHRECT" PopupButtonID="imgBtn23"  Format="dd-MM-yyyy">
            </cc1:CalendarExtender>
            <asp:ImageButton ID="imgBtn23" runat="server" 
                ImageUrl="~/images/Calendar_scheduleHS.png" />
        </td>
		<td><%--<asp:TextBox id="txtDateNeph" runat="server" Width="70px"></asp:TextBox>
			    <cc1:CalendarExtender ID="CalendarExtender5" runat="server" 
                    Enabled="True" TargetControlID="txtDateNeph" PopupButtonID="ImageButton9" Format="dd-MM-yyyy" >
                </cc1:CalendarExtender>
                <asp:ImageButton ID="Imagebutton9" runat="server" CausesValidation="False" 
                    ImageUrl="~/images/Calendar_scheduleHS.png" />--%><asp:Button 
                ID="btnAddTx" runat="server" Text="Add" ValidationGroup="tx" />
        </td>
	</tr>
	<tr>
		<td colspan="4"><%--<asp:CompareValidator ID="CompareValidator8" runat="server" 
                        ControlToCompare="txtDateTX" ControlToValidate="txtDateReject" 
                        Display="Dynamic" ErrorMessage="Date Rejection must be later than Tx" 
                        Operator="GreaterThan" Type="Date" ValidationGroup="tx"></asp:CompareValidator>
                    <br />
                    <asp:CompareValidator ID="CompareValidator13" runat="server" 
                        ControlToCompare="txtDateTX" ControlToValidate="txtDateBiop" Display="Dynamic" 
                        ErrorMessage="Date Biopsy must be later than Tx" Operator="GreaterThan" 
                        Type="Date"></asp:CompareValidator>
                    <br />
                    <asp:CompareValidator ID="CompareValidator19" runat="server" 
                        ControlToCompare="txtDateTX" ControlToValidate="txtDateNeph" Display="Dynamic" 
                        ErrorMessage="Date Nephrectomy must be greater than Tx" Operator="GreaterThan" 
                        Type="Date"></asp:CompareValidator>
                    <br />--%>
                    <asp:CompareValidator ID="CompareValidator20" runat="server" 
                        ControlToCompare="txtDateTX" ControlToValidate="txtDateRecurr" 
                        Display="Dynamic" ErrorMessage="Date Recurrence must be later than Tx" 
                        Operator="GreaterThan" Type="Date"></asp:CompareValidator>
            <br />
            <asp:Label ID="lblTxWarn" runat="server" ForeColor="Red"></asp:Label>
        </td>
		
		<td>
            &nbsp;</td>
		
		<td align="right"> &nbsp;</td>
	</tr>
</table>

     <br />   
        
		<%--<table cellpadding="5" class="style2" style="width: 100%; font-size:85%; margin-top:10px; ">
		<tr>
		<td>Date Transplant <asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" 
                ControlToValidate="txtDateTX" ErrorMessage="*" ValidationGroup="tx"></asp:RequiredFieldValidator>
            <br /><asp:CompareValidator ID="CompareValidator9" runat="server" 
                ControlToCompare="txtDateToday" ControlToValidate="txtDateTX" 
                ErrorMessage="&gt; today" Operator="LessThanEqual" Type="Date" ValidationGroup="tx"></asp:CompareValidator>
            &nbsp;</td>
		<td><asp:TextBox id="txtDateTX" runat="server" Width="90px"></asp:TextBox>
			    <cc1:CalendarExtender ID="txtDateTX_CalendarExtender" runat="server" 
                    Enabled="True" TargetControlID="txtDateTX" PopupButtonID="ImageButton5" Format="dd-MM-yyyy">
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton5" runat="server" CausesValidation="False" 
                    ImageUrl="~/images/Calendar_scheduleHS.png" /></td>
		<td>Type
            <asp:RequiredFieldValidator ID="RequiredFieldValidator3" runat="server" 
                ControlToValidate="DropDownList1" ErrorMessage="*" ValidationGroup="tx"></asp:RequiredFieldValidator>
            </td>
		<td> <asp:DropDownList ID="DropDownList1" runat="server" 
                    DataSourceID="SqlDataSource3" DataTextField="trDesc" DataValueField="trID" AppendDataBoundItems="true">
                    <asp:ListItem Value="" Text="Select"></asp:ListItem>
                </asp:DropDownList></td>
		<td>
		 Date Rejection<br />
                    <asp:CompareValidator ID="CompareValidator12" runat="server" 
                        ControlToCompare="txtDateToday" ControlToValidate="txtDateReject" Display="Dynamic" 
                        ErrorMessage="&gt; today" Operator="LessThanEqual" Type="Date" 
                            ValidationGroup="tx"></asp:CompareValidator>
		
            </td>
		<td><asp:TextBox id="txtDateReject" runat="server" Width="90px"></asp:TextBox>
			    <cc1:CalendarExtender ID="txtDateReject_CalendarExtender" runat="server" 
                    Enabled="True" TargetControlID="txtDateReject" PopupButtonID="ImageButton3" Format="dd-MM-yyyy" >
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton3" runat="server" CausesValidation="False" 
                    ImageUrl="~/images/Calendar_scheduleHS.png" />
            
            </td>
	</tr>
	<tr>
		<td>Date Biopsy<br />
            <asp:CompareValidator ID="CompareValidator10" runat="server" 
                ControlToCompare="txtDateToday" ControlToValidate="txtDateBiop" 
                ErrorMessage="&gt; today" Operator="LessThanEqual" Type="Date" 
                ValidationGroup="tx"></asp:CompareValidator>
        </td>
		<td><asp:TextBox ID="txtDateBiop" runat="server" Width="90px"></asp:TextBox>
            <cc1:CalendarExtender ID="txtDateBiop_CalendarExtender" runat="server" 
                Enabled="True" Format="dd-MM-yyyy" PopupButtonID="imagebutton6" 
                TargetControlID="txtDateBiop">
            </cc1:CalendarExtender><asp:ImageButton ID="ImageButton6" runat="server" CausesValidation="False" 
                    ImageUrl="~/images/Calendar_scheduleHS.png" /></td>
		<td>&nbsp;</td>
		<td valign="top">Transplant Recurrence&nbsp;
            <asp:RadioButton ID="radYes" GroupName="radRecurr" runat="server" Text="Yes" />&nbsp;<asp:RadioButton 
                ID="radNo" GroupName="radRecurr" runat="server" Text="No" />&nbsp;<br />
        </td>
		<td>Date Recurrence<br />
            <asp:CompareValidator ID="CompareValidator11" runat="server" 
                ControlToCompare="txtDateToday" ControlToValidate="txtDateRecurr" 
                ErrorMessage="&gt; today" Operator="LessThanEqual" Type="Date" 
                ValidationGroup="tx"></asp:CompareValidator>
        </td>
		<td><asp:TextBox id="txtDateRecurr" runat="server" Width="90px"></asp:TextBox>
			    <cc1:CalendarExtender ID="txtDateRecurr_CalendarExtender" runat="server" 
                    Enabled="True" TargetControlID="txtDateRecurr" PopupButtonID="ImageButton2" Format="dd-MM-yyyy" >
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton2" runat="server" CausesValidation="False" 
                    ImageUrl="~/images/Calendar_scheduleHS.png" /></td>
	</tr>


            <tr>
                <td>
                Date Nephrectomy<br />
            <asp:CompareValidator ID="CompareValidator1" runat="server" 
                ControlToCompare="txtDateToday" ControlToValidate="txtDateNeph" 
                ErrorMessage="&gt; today" Operator="LessThanEqual" Type="Date" 
                ValidationGroup="tx"></asp:CompareValidator>
                       </td>
                <td><asp:TextBox id="txtDateNeph" runat="server" Width="90px"></asp:TextBox>
			    <cc1:CalendarExtender ID="txtDateNeph_CalendarExtender" runat="server" 
                    Enabled="True" TargetControlID="txtDateNeph" PopupButtonID="ImageButton9" Format="dd-MM-yyyy" >
                </cc1:CalendarExtender>
                <asp:ImageButton ID="Imagebutton9" runat="server" CausesValidation="False" 
                    ImageUrl="~/images/Calendar_scheduleHS.png" />
                    </td>
                <td>
                    &nbsp;</td>
                <td>
                    <asp:CompareValidator ID="CompareValidator15" runat="server" 
                        ControlToCompare="txtDateTX" ControlToValidate="txtDateReject" 
                        Display="Dynamic" ErrorMessage="Date Rejection must be later than Tx" 
                        Operator="GreaterThan" Type="Date"></asp:CompareValidator>
                    <br />
                    <asp:CompareValidator ID="CompareValidator16" runat="server" 
                        ControlToCompare="txtDateTX" ControlToValidate="txtDateBiop" Display="Dynamic" 
                        ErrorMessage="Date Biopsy must be later than Tx" Operator="GreaterThan" 
                        Type="Date"></asp:CompareValidator>
                    <br />
                    <asp:CompareValidator ID="CompareValidator17" runat="server" 
                        ControlToCompare="txtDateTX" ControlToValidate="txtDateNeph" Display="Dynamic" 
                        ErrorMessage="Date Nephrectomy must be greater than Tx" Operator="GreaterThan" 
                        Type="Date"></asp:CompareValidator>
                    <br />
                    <asp:CompareValidator ID="CompareValidator18" runat="server" 
                        ControlToCompare="txtDateTX" ControlToValidate="txtDateRecurr" 
                        Display="Dynamic" ErrorMessage="Date Recurrence must be later than Tx" 
                        Operator="GreaterThan" Type="Date"></asp:CompareValidator>
                    </td>
                <td>
                
                </td>
                <td align="right">
                    <asp:Button ID="btnAddTx" runat="server" Text="Add" ValidationGroup="tx" />
                    </td>
            </tr>


</table>--%>
	</fieldset>
	<%--<fieldset id="fsRelapse" runat="server"><legend>RELAPSE  pre/post transplant</legend>
	<table class="style2" style="width:100%; font-size:85%; margin-top:10px;" cellpadding="5px">
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
	</table>
	    <br />
        &nbsp;&nbsp;
        <%--<asp:Button ID="btnSave" runat="server" Text="Save" ValidationGroup="esrf" 
            CssClass="saveBtn" />
        &nbsp;<asp:Label ID="lblUpdate" runat="server"></asp:Label>--%>
        <br />
        <br />
	<asp:Label ID="lblDebug" runat="server" Text=""></asp:Label>
	</ContentTemplate>
    </asp:UpdatePanel>
	<asp:SqlDataSource ID="SqlDataSource1" runat="server" 
        ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
        SelectCommand="SELECT [mID], [mType] FROM [tbl_RRT_MODALITY] WHERE (([mID] >= @mID) AND ([mID] <= @mID2))">
        <SelectParameters>
            <asp:Parameter DefaultValue="1" Name="mID" Type="Int32" />
            <asp:Parameter DefaultValue="9" Name="mID2" Type="Int32" />
        </SelectParameters>
	</asp:SqlDataSource>
			<asp:SqlDataSource ID="SqlDataSource2" runat="server" 
        ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
        SelectCommand="SELECT [mID], [mType] FROM [tbl_RRT_MODALITY] WHERE (([mID] >= @mID) AND ([mID] <= @mID2))">
                <SelectParameters>
                    <asp:Parameter DefaultValue="1" Name="mID" Type="Int32" />
                    <asp:Parameter DefaultValue="19" Name="mID2" Type="Int32" />
                </SelectParameters>
			</asp:SqlDataSource>
                <asp:SqlDataSource ID="SqlDataSource3" runat="server" 
                    ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
                    SelectCommand="SELECT [trID], [trDesc] FROM [tbl_TRANSPLANT_MODALITY] ORDER BY [trID]">
                </asp:SqlDataSource>
    <asp:SqlDataSource ID="SqlDataSource4" runat="server" 
        ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>"      
        
        SelectCommand="SELECT tbl_RRT_TREATMENT.DATE_START, tbl_RRT_TREATMENT.DATE_STOP, tbl_RRT_MODALITY.mType, tbl_RRT_TREATMENT.RADAR_NO, tbl_RRT_TREATMENT.MODALITY FROM tbl_RRT_MODALITY INNER JOIN tbl_RRT_TREATMENT ON tbl_RRT_MODALITY.mID = tbl_RRT_TREATMENT.MODALITY WHERE (tbl_RRT_TREATMENT.RADAR_NO = @RADAR_NO) AND (tbl_RRT_TREATMENT.MODALITY >= 1 AND tbl_RRT_TREATMENT.MODALITY <= 9) ORDER BY tbl_RRT_TREATMENT.DATE_START">
        <SelectParameters>
            <asp:SessionParameter Name="RADAR_NO" SessionField="patientID" Type="Int32" />
        </SelectParameters>
    </asp:SqlDataSource>
	    <asp:SqlDataSource ID="SqlDataSource6" runat="server" 
        ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
                    SelectCommand="SELECT tbl_Transplant.trID, tbl_Transplant.RADAR_NO, tbl_Transplant.DATE_TRANSPLANT, tbl_Transplant.TRANSPLANT_COUNTER, tbl_Transplant.DATE_NEPHRECT, tbl_Transplant.TRANS_RECURR, tbl_Transplant.DATE_RECURR_TXK, tbl_Transplant.DATE_TX_REJECT, tbl_Transplant.DATE_BX_TXK, tbl_Transplant.TRANS_TYPE, tbl_TRANSPLANT_MODALITY.trDesc FROM tbl_Transplant INNER JOIN tbl_TRANSPLANT_MODALITY ON tbl_Transplant.TRANS_TYPE = tbl_TRANSPLANT_MODALITY.trID WHERE (tbl_Transplant.RADAR_NO = @RADAR_NO) ORDER BY [DATE_TRANSPLANT] DESC"

        DeleteCommand="DELETE FROM [tbl_Transplant] WHERE [trID] = @trID" 
        InsertCommand="INSERT INTO [tbl_Transplant] ([RADAR_NO], [DATE_TRANSPLANT], [TRANS_TYPE], [TRANSPLANT_COUNTER], [DATE_NEPHRECT], [TRANS_RECURR], [DATE_RECURR_TXK], [DATE_TX_REJECT], [DATE_BX_TXK]) VALUES (@RADAR_NO, @DATE_TRANSPLANT, @TRANS_TYPE, @TRANSPLANT_COUNTER, @DATE_NEPHRECT, @TRANS_RECURR, @DATE_RECURR_TXK, @DATE_TX_REJECT, @DATE_BX_TXK)" 
        UpdateCommand="UPDATE [tbl_Transplant] SET [RADAR_NO] = @RADAR_NO, [DATE_TRANSPLANT] = @DATE_TRANSPLANT, [TRANS_TYPE] = @TRANS_TYPE, [TRANSPLANT_COUNTER] = @TRANSPLANT_COUNTER, [DATE_NEPHRECT] = @DATE_NEPHRECT, [TRANS_RECURR] = @TRANS_RECURR, [DATE_RECURR_TXK] = @DATE_RECURR_TXK, [DATE_TX_REJECT] = @DATE_TX_REJECT, [DATE_BX_TXK] = @DATE_BX_TXK WHERE [trID] = @trID">
            <SelectParameters>
                <asp:SessionParameter Name="RADAR_NO" SessionField="patientID" Type="Int32" />
            </SelectParameters>
            <DeleteParameters>
                <asp:Parameter Name="trID" Type="Int32" />
            </DeleteParameters>
            <UpdateParameters>
                <asp:Parameter Name="RADAR_NO" Type="Int32" />
                <asp:Parameter Name="DATE_TRANSPLANT" Type="DateTime" />
                <asp:Parameter Name="TRANS_TYPE" Type="String" />
                <asp:Parameter Name="TRANSPLANT_COUNTER" Type="Int32" />
                <asp:Parameter Name="DATE_NEPHRECT" Type="DateTime" />
                <asp:Parameter Name="TRANS_RECURR" Type="Boolean" />
                <asp:Parameter Name="DATE_RECURR_TXK" Type="DateTime" />
                <asp:Parameter Name="DATE_TX_REJECT" Type="DateTime" />
                <asp:Parameter Name="DATE_BX_TXK" Type="DateTime" />
                <asp:Parameter Name="trID" Type="Int32" />
            </UpdateParameters>
            <InsertParameters>
                <asp:Parameter Name="RADAR_NO" Type="Int32" />
                <asp:Parameter Name="DATE_TRANSPLANT" Type="DateTime" />
                <asp:Parameter Name="TRANS_TYPE" Type="String" />
                <asp:Parameter Name="TRANSPLANT_COUNTER" Type="Int32" />
                <asp:Parameter Name="DATE_NEPHRECT" Type="DateTime" />
                <asp:Parameter Name="TRANS_RECURR" Type="Boolean" />
                <asp:Parameter Name="DATE_RECURR_TXK" Type="DateTime" />
                <asp:Parameter Name="DATE_TX_REJECT" Type="DateTime" />
                <asp:Parameter Name="DATE_BX_TXK" Type="DateTime" />
            </InsertParameters>
    </asp:SqlDataSource>
	    <asp:SqlDataSource ID="SqlDataSource5" runat="server" 
            ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>"         
        SelectCommand="SELECT tbl_RRT_TREATMENT.tID, tbl_RRT_TREATMENT.DATE_START, tbl_RRT_TREATMENT.DATE_STOP, tbl_RRT_MODALITY.mType, tbl_RRT_TREATMENT.RADAR_NO, tbl_RRT_TREATMENT.MODALITY, tbl_RRT_TREATMENT.FIRST_FLAG FROM tbl_RRT_MODALITY INNER JOIN tbl_RRT_TREATMENT ON tbl_RRT_MODALITY.mID = tbl_RRT_TREATMENT.MODALITY WHERE (tbl_RRT_TREATMENT.RADAR_NO = @RADAR_NO) AND (tbl_RRT_TREATMENT.MODALITY &gt;= 1) AND (tbl_RRT_TREATMENT.MODALITY &lt;= 19)  ORDER BY tbl_RRT_TREATMENT.DATE_START DESC"
            DeleteCommand = "DELETE FROM tbl_RRT_TREATMENT WHERE tID = @tID"
            ><SelectParameters>
                <asp:SessionParameter Name="RADAR_NO" SessionField="patientID" Type="Int32" />
            </SelectParameters>
             <DeleteParameters>
                <asp:Parameter Name="tID" Type="Int32" />
            </DeleteParameters>
        </asp:SqlDataSource>
        
        
    
</div>
<br />
<asp:Label ID="lblPage" runat="server"></asp:Label>
</div>
</asp:Content>
