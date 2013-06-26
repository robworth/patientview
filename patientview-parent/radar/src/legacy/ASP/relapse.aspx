<%@ Page Title="" Language="VB" MasterPageFile="~/MasterPage.master" AutoEventWireup="false" CodeFile="relapse.aspx.vb" Inherits="relapse" %>
<%@ Register assembly="DevExpress.Web.ASPxEditors.v9.2, Version=9.2.4.0, Culture=neutral, PublicKeyToken=b88d1754d700e49a" namespace="DevExpress.Web.ASPxEditors" tagprefix="dxe" %>
<%@ Register Assembly="DevExpress.Web.v9.2, Version=9.2.4.0, Culture=neutral, PublicKeyToken=b88d1754d700e49a"
    Namespace="DevExpress.Web.ASPxPopupControl" TagPrefix="dxpc" %>

<%@ Register assembly="AjaxControlToolkit" namespace="AjaxControlToolkit" tagprefix="cc1" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
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

/* this because of the update panel for the tabs */
/*#tabsC {
margin-top:-35px;
}*/
#tblHeader {
background-color: #b6df9f;
}
.btmBorder {
border-bottom: 1px green solid;
height:30px;
vertical-align:middle;
}
#tblMain td {
border: 1px solid silver;
}

#Tr1 input, #Tr2 input {
border:0;
}

#rowAchieved input, #rowTx input {
border:0px;
}

#ctl00_ContentPlaceHolder1_GridView1 input {
border:0;
}

#ctl00_ContentPlaceHolder1_radAchievedYes, #ctl00_ContentPlaceHolder1_radAchieveNo, #ctl00_ContentPlaceHolder1_radAchievePartial {
border:0;
}

#ctl00_ContentPlaceHolder1_radEditAchieveYes, #ctl00_ContentPlaceHolder1_radEditAchieveNo, #ctl00_ContentPlaceHolder1_radEditAchievePartial {
border:0;
}

#ctl00_ContentPlaceHolder1_radTxYes, #ctl00_ContentPlaceHolder1_radTxNo {
border:0;
}

#ctl00_ContentPlaceHolder1_radEditTxYes, #ctl00_ContentPlaceHolder1_radEditTxNo {
border:0;
}
 .style2
    {
        width: 100px;
        height: 22px;
    }
    .style3
    {
        width: 120px;
        height: 22px;
    }
    .style4
    {
        height: 22px;
    }
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
        <li><asp:hyperlink runat="server" ID="lnkDemographics" navigateurl="demographics.aspx" Tooltip="Demographics"><span>Demographics</span></asp:hyperlink></li> 
        <li><asp:hyperlink runat="server" ID="lnkDiagnosis" NavigateUrl="diagnosis.aspx" ToolTip="Diagnosis"><span>Diagnosis</span></asp:hyperlink></li>           
        <li><asp:hyperlink runat="server" ID="lnkLabResults" NavigateUrl="first-clinical.aspx" Tootip="Laboratory Results"><span>First Visit</span></asp:hyperlink></li> 
        <li><asp:hyperlink runat="server" ID="lnk3Months" NavigateUrl="followup-clinical.aspx" Tooltip="Follow Up"><span>Follow Up</span></asp:hyperlink></li> 
        <li><asp:hyperlink runat="server" ID="lnkPathology" NavigateUrl="pathology.aspx" Tootip="Pathology"><span>Pathology</span></asp:hyperlink></li> 
        <li><asp:hyperlink runat="server" ID="lnkRelapse" NavigateUrl="" Tooltip="Relapse"><span class="hovered">Relapse</span></asp:hyperlink></li> 
        <li><asp:hyperlink runat="server" ID="lnkHospital" NavigateUrl="hospitalisation.aspx" Tooltip="Hospitalisation"><span>Hospitalisation</span></asp:hyperlink></li> 
        <li><asp:hyperlink runat="server" ID="lnkTimeLines" NavigateUrl="timelines.aspx" Tootip="Timelines"><span >Timelines</span></asp:hyperlink></li> 
</ul> 
</div> 
<div id="mainBlock">
   
	<asp:Panel ID="pnlAdd" runat="server" Visible="true">
<table cellpadding="4" style="width: 100%;" id="tblHeader">
	<tr>
		<td align="left" class="style4"><b>Relapse Records</b></td>
		<td style="text-align:right; " class="style2">RADAR No.</td>
		<td class="style3">
            <asp:TextBox ID="txtRADAR_NO" runat="server" ReadOnly="True" Width="100px"></asp:TextBox>
        </td>
	</tr>
	<tr>
		<td align="left">Name
		<asp:textbox id="txtFNAME" runat="server" ReadOnly="True"></asp:textbox>
		&nbsp;<asp:textbox id="txtSNAME" runat="server" ReadOnly="True"></asp:textbox>
		&nbsp;DoB
		<asp:textbox id="txtDOB" runat="server" ReadOnly="True" Width="100px"></asp:textbox>
		    <asp:Label ID="lblRelapseID" runat="server" Text="" Visible="false"></asp:Label>
		</td>
		<td style="text-align:right; " class="style5">Hospital No.</td>
		<td>
            <asp:TextBox ID="txtHOSP_NO" runat="server" ReadOnly="True" Width="100px"></asp:TextBox>
        </td>
	</tr>
	<tr>
		<td style="text-align:left;" >&nbsp;<asp:TextBox ID="txtDateToday" runat="server" ForeColor="#B6DF9F" 
                BackColor="#B6DF9F" BorderColor="#B6DF9F"></asp:TextBox>
        </td>
		<td style="text-align:right; " class="style5">Diagnosis</td>
		<td>
            <asp:TextBox ID="txtDIAGNOSIS" runat="server" ReadOnly="True" BackColor="#FFFF99" 
                Width="100px"></asp:TextBox>
        </td>
	</tr>
</table>
<asp:Panel ID="pnlMain" runat="server">
<table style="width:100%; background-color:#eef6ed; font-size:85%" cellpadding="10" id="tblMain" runat="server">
<tr>
<td>
<asp:GridView ID="GridView1" runat="server" AutoGenerateColumns="False" 
            DataKeyNames="relID" DataSourceID="SqlDataSource1" CellPadding="5" HeaderStyle-ForeColor="#3c982d" OnRowCreated="GridView_RowCreated">
            <Columns>
                <asp:BoundField DataField="relID" HeaderText="relID" InsertVisible="False" 
                    ReadOnly="True" SortExpression="relID" Visible="true" />
                <asp:BoundField DataField="RADAR_NO" HeaderText="Radar No" 
                    SortExpression="RADAR_NO" Visible="False" />
                <asp:BoundField DataField="DATE_ONSET_RELAP" DataFormatString="{0:dd-MMM-yyyy}" 
                    HeaderText="Date of Relapse" SortExpression="DATE_ONSET_RELAP" >
                <ItemStyle Width="100px"  />
                </asp:BoundField>
                <asp:TemplateField>
                <HeaderTemplate>Nat/Trans</HeaderTemplate>
                <ItemTemplate><asp:Label runat="server"><%#GetTxType(DataBinder.Eval(Container, "DataItem.RELAP_TX_NAT"))%></asp:Label></ItemTemplate>
                <ItemStyle HorizontalAlign="Center" />
                </asp:TemplateField>
                <%--<asp:CheckBoxField DataField="RELAP_TX_NAT" HeaderText="Transplanted?" 
                    SortExpression="RELAP_TX_NAT" ItemStyle-HorizontalAlign="Center" >
                    <ItemStyle HorizontalAlign="Center" />
                </asp:CheckBoxField>--%>
                <asp:BoundField DataField="TRIG_VIRAL" HeaderText="Viral Trigger" 
                    SortExpression="TRIG_VIRAL" />
                <asp:BoundField DataField="TRIG_IMMUN" HeaderText="Immunisation Trigger" 
                    SortExpression="TRIG_IMMUN" ItemStyle-Width="120px" >
                    <ItemStyle Width="120px" />
                </asp:BoundField>
                <asp:BoundField DataField="TRIG_OTHER" HeaderText="Other Trigger" 
                    SortExpression="TRIG_OTHER" />
                <asp:BoundField DataField="RELAP_DRUG_1" HeaderText="Drug 1" 
                    SortExpression="RELAP_DRUG_1" />
                <asp:BoundField DataField="RELAP_DRUG_2" HeaderText="Drug 2" 
                    SortExpression="RELAP_DRUG_2" />
                <asp:BoundField DataField="RELAP_DRUG_3" HeaderText="Drug 3" 
                    SortExpression="RELAP_DRUG_3" />
                <asp:TemplateField HeaderText="Remission achieved?" 
                    SortExpression="REMISS_ACHIEVE">
                    <ItemTemplate>
                        <asp:Label ID="Label1" runat="server" Text='<%# getRemission(DataBinder.Eval(Container, "DataItem.REMISS_ACHIEVE")) %>'></asp:Label>
                    </ItemTemplate>
                    <EditItemTemplate>
                        <asp:TextBox ID="TextBox1" runat="server" Text='<%# Bind("REMISS_ACHIEVE") %>'></asp:TextBox>
                    </EditItemTemplate>
                    <ItemStyle HorizontalAlign="Center" Width="80px" />
                </asp:TemplateField>
                <asp:BoundField DataField="DATE_REMISSION" DataFormatString="{0:dd-MMM-yyyy}" 
                    HeaderText="Date of Remission" SortExpression="DATE_REMISSION" >
                <ItemStyle Width="100px" />
                </asp:BoundField>
                 <asp:TemplateField>
                <ItemTemplate>
                <asp:Button ID="delButton" runat="server" CausesValidation="false" CommandName="Delete" OnClientClick="return confirm('Are you sure you want to delete this record?');" Text="Delete" BorderColor="Gray" BorderWidth="1"></asp:Button>
                <asp:Button ID="editButton" runat="server" CausesValidation="false" CommandName="Edit" Text="Edit" BorderColor="Gray" BorderWidth="1"></asp:Button>

                </ItemTemplate>
                </asp:TemplateField>
            </Columns>
            <HeaderStyle ForeColor="#3C982D" />
            <EmptyDataTemplate>
            <p>No relapse data for this patient</p>
            </EmptyDataTemplate>
        </asp:GridView>
        <br /><br />
        <asp:Panel ID="pnlRelapseEdit" runat="server" Visible="false">
        <fieldset style="border:1px solid red"><legend>Edit</legend>
                <table cellpadding="8" style="width: 100%; border-collapse:collapse; table-layout:fixed; font-size:95%; margin-top:10px;" id="tblRelapseEdit" runat="server">
	<tr>
		<td width="150">Date of relapse</td>
		<td>
            <asp:TextBox ID="txtEditDateRelapse" runat="server" Width="80px" 
                ValidationGroup="RelEdit"></asp:TextBox>
            <cc1:CalendarExtender ID="CalendarExtender_EditDateRelapse" runat="server" 
                Enabled="True" PopupButtonID="calImage4" TargetControlID="txtEditDateRelapse" Format="dd-MM-yyyy">
            </cc1:CalendarExtender>
            <asp:imagebutton runat="server" id="calImage4" ImageUrl="images/Calendar_scheduleHS.png" />&nbsp;<asp:CompareValidator 
                ID="CompareValidator2" runat="server" ControlToCompare="txtDateToday" 
                ControlToValidate="txtEditDateRelapse" Display="Dynamic" 
                ErrorMessage="&gt; today" Operator="LessThanEqual" Type="Date" 
                ValidationGroup="RelEdit"></asp:CompareValidator>
            &nbsp;<asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" 
                ControlToValidate="txtDateRelapse" ErrorMessage="*"></asp:RequiredFieldValidator>
        </td>
	</tr>
	<tr id="Tr1">
		<td>Transplanted / Native</td>
		<td>
            <asp:RadioButton ID="radEditTxYes" runat="server" GroupName="radEditTX" 
                Text="Tx" BorderColor="#EEF6ED" />
            &nbsp;
            <asp:RadioButton ID="radEditTxNo" runat="server" GroupName="radEditTX" 
                Text="Nat" BorderColor="#EEF6ED" BorderWidth="0px" />
            &nbsp;
            <asp:Label ID="lblEditID" runat="server" ForeColor="#EEF6ED"></asp:Label>
        </td>
	</tr>
	<tr>
		<td colspan="2">Viral trigger&nbsp;
            <asp:TextBox ID="txtEditViralTrigger" runat="server" Width="80px"></asp:TextBox>
            &nbsp; Immunisation trigger&nbsp;
            <asp:TextBox ID="txtEditImmunTrig" runat="server" Width="80px"></asp:TextBox>
            &nbsp; Other trigger&nbsp;
            <asp:TextBox ID="txtEditOtherTrig" runat="server" Width="200px"></asp:TextBox>
            &nbsp;</td>
	</tr>
	<tr>
		<td>Drugs given specifically for relapse</td>
		<td>Drug 1&nbsp;
            <asp:TextBox ID="txtEditDrug1" runat="server" Width="80px"></asp:TextBox>
            &nbsp; Drug 2&nbsp;
            <asp:TextBox ID="txtEditDrug2" runat="server" Width="80px"></asp:TextBox>
            &nbsp; Drug 3&nbsp;
            <asp:TextBox ID="txtEditDrug3" runat="server" Width="80px"></asp:TextBox>
        </td>
	</tr>
	<tr id="Tr2">
		<td>Remission achieved?</td>
		<td>
            <asp:RadioButton ID="radEditAchieveYes" runat="server" GroupName="radEditAchieve" 
                Text="Complete" />
            &nbsp;<asp:RadioButton ID="radEditAchievePartial" runat="server" 
                GroupName="radEditAchieve" Text="Partial" />
&nbsp;<asp:RadioButton ID="radEditAchieveNo" runat="server" GroupName="radEditAchieve" 
                Text="None" />
        </td>
	</tr>
	<tr>
		<td>Date of remission</td>
		<td valign="middle">
            <asp:TextBox ID="txtEditDateRemission" runat="server" Width="80px"></asp:TextBox>
            <cc1:CalendarExtender ID="CalendarExtender_EditDateRemission" runat="server" 
                Enabled="True" TargetControlID="txtEditDateRemission" 
                PopupButtonID="calImage3" Format="dd-MMM-yyyy">
            </cc1:CalendarExtender>
            <asp:imagebutton runat="server" id="calImage3" ImageUrl="images/Calendar_scheduleHS.png" /></td>
	</tr>
	<tr>
		<td>
            <asp:Button ID="btnEditSave" runat="server" Text="Save" 
                ValidationGroup="RelEdit" />
            <asp:Button ID="btnEditCancel" runat="server" Text="Cancel" 
                CausesValidation="False" />
        </td>
		<td>&nbsp;</td>
	</tr>
</table>

        </fieldset><p>&nbsp;</p></asp:Panel>
        
        <fieldset style="border:1px solid green;" id="fieldsetAdd" runat="server">
        <table cellpadding="8" style="width: 100%; border-collapse:collapse; table-layout:fixed; font-size:95%; margin-top:10px;" id="tblRelapseAdd" runat="server">
	<tr>
		<td width="150">Date of relapse</td>
		<td>
            <asp:TextBox ID="txtDateRelapse" runat="server" Width="80px"></asp:TextBox>
            <cc1:CalendarExtender ID="txtDateRelapse_CalendarExtender" runat="server" 
                Enabled="True" PopupButtonID="calImage1" TargetControlID="txtDateRelapse" Format="dd-MM-yyyy">
            </cc1:CalendarExtender>
            <asp:imagebutton runat="server" id="calImage1" ImageUrl="images/Calendar_scheduleHS.png" />&nbsp;<asp:CompareValidator 
                ID="CompareValidator1" runat="server" ControlToCompare="txtDateToday" 
                ControlToValidate="txtDateRelapse" Display="Dynamic" ErrorMessage="&gt; today" 
                Operator="LessThanEqual" Type="Date" ValidationGroup="RelAdd"></asp:CompareValidator>
            &nbsp;<asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" 
                ControlToValidate="txtDateRelapse" ErrorMessage="*"></asp:RequiredFieldValidator>
        </td>
	</tr>
	<tr id="rowTx">
		<td>Transplanted / Native</td>
		<td>
            <asp:RadioButton ID="radTxYes" runat="server" GroupName="radTX" Text="Tx" />
            &nbsp;
            <asp:RadioButton ID="radTxNo" runat="server" GroupName="radTX" Text="Nat" />
        </td>
	</tr>
	<tr>
		<td colspan="2">Viral trigger&nbsp;
            <asp:TextBox ID="txtViralTrig" runat="server" Width="80px"></asp:TextBox>
            &nbsp; Immunisation trigger&nbsp;
            <asp:TextBox ID="txtImmunTrig" runat="server" Width="80px"></asp:TextBox>
            &nbsp; Other trigger&nbsp;
            <asp:TextBox ID="txtOtherTrig" runat="server" Width="200px"></asp:TextBox>
        </td>
	</tr>
	<tr>
		<td>Drugs given specifically for relapse</td>
		<td>Drug 1&nbsp;
            <asp:TextBox ID="txtDrug1" runat="server" Width="80px"></asp:TextBox>
            &nbsp; Drug 2&nbsp;
            <asp:TextBox ID="txtDrug2" runat="server" Width="80px"></asp:TextBox>
            &nbsp; Drug 3&nbsp;
            <asp:TextBox ID="txtDrug3" runat="server" Width="80px"></asp:TextBox>
        </td>
	</tr>
	<tr >
		<td colspan="2">
        <fieldset style="border:1px solid Green; padding:5px;">
        <p><strong>Plasmapheresis</strong></p>
        
         <asp:GridView ID="GridView3" runat="server" Width="95%" 
        AutoGenerateColumns="False" DataSourceID="SqlDataSource8" Font-Size="85%" HeaderStyle-ForeColor="#3c982d" CellPadding="3" DataKeyNames="plID" OnRowCreated="GridView_RowCreated" OnRowEditing="GridView3_RowEditing" OnRowDeleted="GridView3_RowDeleting" >
        <Columns>
            <asp:BoundField DataField="plID" HeaderText="plID" />
            <asp:BoundField DataField="DATE_START_PLASMAPH" 
                HeaderText="Date Started" DataFormatString="{0:dd-MMM-yyyy}"  />
            <asp:BoundField DataField="DATE_STOP_PLASMAPH" HeaderText="Date Stopped" DataFormatString="{0:dd-MMM-yyyy}" 
                 />
            <asp:BoundField DataField="exDesc" HeaderText="No of Exchanges" 
                 />
           
                  <asp:TemplateField HeaderText="Response">
                      <ItemTemplate>
                     
                          <asp:Label ID="Label1" runat="server" Text='<%# GetResponse(Databinder.Eval(Container.DataItem, "RESPONSE_TO_PLASMA")) %>'></asp:Label>
                      </ItemTemplate>
                     
            </asp:TemplateField>
            <asp:TemplateField>
                <ItemTemplate>
                <asp:Button ID="delButton" runat="server" CausesValidation="false" CommandName="Delete" OnClientClick="return confirm('Are you sure you want to delete this record?');" Text="Delete"></asp:Button>
                <asp:Button ID="editButton" runat="server" CausesValidation="false" CommandName="Edit" Text="Edit" />
                </ItemTemplate>
                </asp:TemplateField>
        </Columns>
        <HeaderStyle ForeColor="#3C982D" />
    </asp:GridView>
    <asp:Panel ID="pnlPlasmaEdit" runat="server" Visible="false">
    <fieldset style="border:1px solid Red; width:95%;"><legend>Edit</legend>
    <table cellpadding="5" class="style2" style="width: 100%; font-size:85%; margin-top:10px">
		<tr>
			<td>Date Started<br />
                <asp:CompareValidator ID="CompareValidator3" runat="server" 
                    ControlToCompare="txtDateToday" ControlToValidate="txtDateStartPlasmaEdit" 
                    ErrorMessage="&gt; today" Operator="LessThanEqual" Type="Date" ValidationGroup="PlasmaEdit"></asp:CompareValidator>
            </td>
			<td>Date Stopped
                <br />
                <%--<asp:CompareValidator ID="CompareValidator3" runat="server" 
                    ControlToCompare="txtDATE_START_PLASMAPH" ControlToValidate="txtDATE_STOP_PLASMAPH" 
                    ErrorMessage="&lt; start date" Operator="GreaterThanEqual" 
                    SetFocusOnError="True" Type="Date" Display="Dynamic"></asp:CompareValidator>--%>
            &nbsp;<%--<asp:CompareValidator ID="CompareValidator8" runat="server" 
                    ControlToCompare="txtDateToday" ControlToValidate="txtDATE_STOP_PLASMAPH" 
                    ErrorMessage="&gt; today" Operator="LessThanEqual" Type="Date"></asp:CompareValidator>--%><asp:Label ID="lblPlasmaStopEditWarn" runat="server" ForeColor="Red"></asp:Label>
            </td>
			<td>No of Exchanges</td>
			<td>&nbsp;</td>
			<td>
                Response</td>
			<td>
                <asp:Label ID="lblpID" runat="server" ForeColor="#EEF6ED"></asp:Label>
            </td>
		</tr>
		<tr>
			<td>
			<asp:TextBox id="txtDateStartPlasmaEdit" runat="server" Width="70px" ValidationGroup="PlasmaEdit"></asp:TextBox>
			    <cc1:CalendarExtender ID="CalendarExtender3" runat="server" 
                    Enabled="True" TargetControlID="txtDateStartPlasmaEdit" 
                    PopupButtonID="ImageButton1" Format="dd-MM-yyyy">
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton1" runat="server" CausesValidation="False" ImageUrl="~/images/Calendar_scheduleHS.png" />
			</td>
			<td>
			<asp:TextBox id="txtDateStopPlasmaEdit" runat="server" Width="70px"></asp:TextBox>
			    <cc1:CalendarExtender ID="CalendarExtender4" runat="server" 
                    Enabled="True" TargetControlID="txtDateStopPlasmaEdit" PopupButtonID="ImageButton3" Format="dd-MM-yyyy">
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton3" runat="server" CausesValidation="False" ImageUrl="~/images/Calendar_scheduleHS.png" />
			</td>
			<td>
			    <asp:DropDownList ID="ddlPlasmaExchEdit" runat="server" 
                    DataSourceID="SqlDataSource10" DataTextField="exDesc" DataValueField="exID" AppendDataBoundItems="true">
                    <asp:ListItem Value="" Text="Select"></asp:ListItem>
                </asp:DropDownList>
			</td>
			<td>
			    &nbsp;</td>
			<td>
                <asp:DropDownList ID="DropDownList3" runat="server">
                <asp:ListItem Value="" Text="Select" Selected="True"></asp:ListItem>
                <asp:ListItem Value="1" Text="Complete"></asp:ListItem>
                <asp:ListItem Value="2" Text="Partial"></asp:ListItem>
                <asp:ListItem Value="3" Text="None"></asp:ListItem>
                </asp:DropDownList>
            </td>
			<td>
			<asp:Button id="btnUpdatePlasma" runat="server" Text="Save" CausesValidation="True" ValidationGroup="PlasmaEdit" UseSubmitBehavior="True" />
			<asp:Button ID="btnCancelPlasmaEdit" runat="server" Text="Cancel" 
                    CausesValidation="false" />
			</td>
		</tr>
		<tr>
			<td colspan="3">
                <asp:Label ID="lblPlasmaEditWarn" runat="server" ForeColor="Red"></asp:Label>
            </td>
			<td>&nbsp;</td>
			<td>
                &nbsp;</td>
			<td>&nbsp;</td>
		</tr>
	</table>
    </fieldset></asp:Panel>
   
      <table cellpadding="5"  
        style="width: 100%; font-size:85%; margin-top:10px" id="tblAddPlasma" 
        runat="server">
		<tr>
			<td>Date Started<br />
                <asp:CompareValidator ID="CompareValidator7" runat="server" 
                    ControlToCompare="txtDateToday" ControlToValidate="txtDATE_START_PLASMAPH" 
                    ErrorMessage="&gt; today" Operator="LessThanEqual" Type="Date"></asp:CompareValidator>
            </td>
			<td>Date Stopped
                <br />
                <%--<asp:CompareValidator ID="CompareValidator3" runat="server" 
                    ControlToCompare="txtDATE_START_PLASMAPH" ControlToValidate="txtDATE_STOP_PLASMAPH" 
                    ErrorMessage="&lt; start date" Operator="GreaterThanEqual" 
                    SetFocusOnError="True" Type="Date" Display="Dynamic"></asp:CompareValidator>--%>
            &nbsp;<%--<asp:CompareValidator ID="CompareValidator8" runat="server" 
                    ControlToCompare="txtDateToday" ControlToValidate="txtDATE_STOP_PLASMAPH" 
                    ErrorMessage="&gt; today" Operator="LessThanEqual" Type="Date"></asp:CompareValidator>--%><asp:Label ID="lblStopWarnP" runat="server" Text="" ForeColor="Red"></asp:Label>
            </td>
			<td>No of Exchanges</td>
			<td>&nbsp;</td>
			<td>
                Response</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>
			<asp:TextBox id="txtDATE_START_PLASMAPH" runat="server" Width="70px"></asp:TextBox>
			    <cc1:CalendarExtender ID="TextBox27_CalendarExtender" runat="server" 
                    Enabled="True" TargetControlID="txtDATE_START_PLASMAPH" PopupButtonID="ImageButton15" Format="dd-MM-yyyy">
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton15" runat="server" CausesValidation="False" ImageUrl="~/images/Calendar_scheduleHS.png" />
			</td>
			<td>
			<asp:TextBox id="txtDATE_STOP_PLASMAPH" runat="server" Width="70px"></asp:TextBox>
			    <cc1:CalendarExtender ID="TextBox28_CalendarExtender" runat="server" 
                    Enabled="True" TargetControlID="txtDATE_STOP_PLASMAPH" PopupButtonID="ImageButton16" Format="dd-MM-yyyy">
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton16" runat="server" CausesValidation="False" ImageUrl="~/images/Calendar_scheduleHS.png" />
			</td>
			<td>
			    <asp:DropDownList ID="ddlPlamsExch" runat="server" 
                    DataSourceID="SqlDataSource10" DataTextField="exDesc" DataValueField="exID" AppendDataBoundItems="true">
                    <asp:ListItem Value="">Select</asp:ListItem>
                </asp:DropDownList>
			</td>
			<td>
			    &nbsp;</td>
			<td>
                <asp:DropDownList ID="ddlPlasResponse" runat="server">
                <asp:ListItem Value="" Text="Select" Selected="True"></asp:ListItem>
                <asp:ListItem Value="1" Text="Complete"></asp:ListItem>
                <asp:ListItem Value="2" Text="Partial"></asp:ListItem>
                <asp:ListItem Value="3" Text="None"></asp:ListItem>
                </asp:DropDownList>
            </td>
			<td>
			<asp:Button id="btnPlasmaAdd" runat="server" Text="Add" CausesValidation="false" />
			</td>
		</tr>
		<tr>
			<td colspan="3">
                <asp:Label ID="lblWarnP" runat="server" Text="" ForeColor="Red"></asp:Label>
            </td>
			<td>&nbsp;</td>
			<td>
                &nbsp;</td>
			<td>&nbsp;</td>
		</tr>
	</table>
  
        
     </fieldset>   
        
        </td>
	</tr>
	        <tr ID="rowAchieved">
                <td>
                    Remission achieved?</td>
                <td>
                    <asp:RadioButton ID="radAchievedYes" runat="server" GroupName="radAchieve" 
                        Text="Complete" />
                    &nbsp;<asp:RadioButton ID="radAchievePartial" runat="server" GroupName="radAchieve" 
                        Text="Partial" />
                    &nbsp;<asp:RadioButton ID="radAchieveNo" runat="server" GroupName="radAchieve" 
                        Text="None" />
                    &nbsp;
                    <asp:HyperLink ID="hypRemission" runat="server" CssClass="info" 
                        Font-Bold="True" ForeColor="Green" NavigateUrl="#">Help<span>Complete: protein:creatinine ratio &lt; 40mg/mmol; Partial: protein:creatinine ratio &lt; 200mg/mmol.</span></asp:HyperLink>
                </td>
            </tr>
	<tr>
		<td>Date of remission</td>
		<td valign="middle">
            <asp:TextBox ID="txtDateRemission" runat="server" Width="80px"></asp:TextBox>
            <cc1:CalendarExtender ID="txtDateRemission_CalendarExtender" runat="server" 
                Enabled="True" TargetControlID="txtDateRemission" 
                PopupButtonID="calImage2" Format="dd-MMM-yyyy">
            </cc1:CalendarExtender>
            <asp:imagebutton runat="server" id="calImage2" ImageUrl="images/Calendar_scheduleHS.png" /></td>
	</tr>
	<tr>
		<td>
            <asp:Button ID="btnAdd" runat="server" Text="Add" ValidationGroup="RelAdd" 
                CssClass="saveBtn" />
        </td>
		<td>&nbsp;</td>
	</tr>
</table>
</fieldset>
</td></tr>
</table>
</asp:Panel>
</asp:Panel>
<br /><br /><br />
<asp:Label ID="lblPage" runat="server"></asp:Label>
</div>
<asp:Label ID="lblDebug" runat="server"></asp:Label>

<asp:SqlDataSource ID="SqlDataSource2" runat="server" 
                ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
                SelectCommand="SELECT [relID], [DATE_ONSET_RELAP], [SEQ_NO] FROM [tbl_Relapse] WHERE (([SEQ_NO] &gt; @SEQ_NO) AND ([RADAR_NO] = @RADAR_NO)) ORDER BY [DATE_ONSET_RELAP] ASC">
                <SelectParameters>
                    <asp:Parameter DefaultValue="1" Name="SEQ_NO" Type="Int32" />
                    <asp:SessionParameter Name="RADAR_NO" SessionField="patientID" Type="Int32" />
                </SelectParameters>
            </asp:SqlDataSource>
             <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
            ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
            
            
        SelectCommand="SELECT * FROM [tbl_Relapse] WHERE ([RADAR_NO] = @RADAR_NO) ORDER BY [DATE_ONSET_RELAP] DESC" 
        DeleteCommand="DELETE FROM [tbl_Relapse] WHERE [relID] = @relID" 
        InsertCommand="INSERT INTO [tbl_Relapse] ([RADAR_NO], [DATE_ONSET_RELAP], [RELAP_TX_NAT], [TRIG_VIRAL], [TRIG_IMMUN], [TRIG_OTHER], [RELAP_DRUG_1], [RELAP_DRUG_2], [RELAP_DRUG_3], [REMISS_ACHIEVE], [DATE_REMISSION], [SEQ_NO]) VALUES (@RADAR_NO, @DATE_ONSET_RELAP, @RELAP_TX_NAT, @TRIG_VIRAL, @TRIG_IMMUN, @TRIG_OTHER, @RELAP_DRUG_1, @RELAP_DRUG_2, @RELAP_DRUG_3, @REMISS_ACHIEVE, @DATE_REMISSION, @SEQ_NO)" 
        UpdateCommand="UPDATE [tbl_Relapse] SET [RADAR_NO] = @RADAR_NO, [DATE_ONSET_RELAP] = @DATE_ONSET_RELAP, [RELAP_TX_NAT] = @RELAP_TX_NAT, [TRIG_VIRAL] = @TRIG_VIRAL, [TRIG_IMMUN] = @TRIG_IMMUN, [TRIG_OTHER] = @TRIG_OTHER, [RELAP_DRUG_1] = @RELAP_DRUG_1, [RELAP_DRUG_2] = @RELAP_DRUG_2, [RELAP_DRUG_3] = @RELAP_DRUG_3, [REMISS_ACHIEVE] = @REMISS_ACHIEVE, [DATE_REMISSION] = @DATE_REMISSION, [SEQ_NO] = @SEQ_NO WHERE [relID] = @relID">
            <SelectParameters>
                <asp:SessionParameter Name="RADAR_NO" SessionField="patientID" Type="Int32" />
            </SelectParameters>
                 <DeleteParameters>
                     <asp:Parameter Name="relID" Type="Int32" />
                 </DeleteParameters>
                 <UpdateParameters>
                     <asp:Parameter Name="RADAR_NO" Type="Int32" />
                     <asp:Parameter Name="DATE_ONSET_RELAP" Type="DateTime" />
                     <asp:Parameter Name="RELAP_TX_NAT" Type="Boolean" />
                     <asp:Parameter Name="TRIG_VIRAL" Type="String" />
                     <asp:Parameter Name="TRIG_IMMUN" Type="String" />
                     <asp:Parameter Name="TRIG_OTHER" Type="String" />
                     <asp:Parameter Name="RELAP_DRUG_1" Type="String" />
                     <asp:Parameter Name="RELAP_DRUG_2" Type="String" />
                     <asp:Parameter Name="RELAP_DRUG_3" Type="String" />
                     <asp:Parameter Name="REMISS_ACHIEVE" Type="Boolean" />
                     <asp:Parameter Name="DATE_REMISSION" Type="DateTime" />
                     <asp:Parameter Name="SEQ_NO" Type="Int32" />
                     <asp:Parameter Name="relID" Type="Int32" />
                 </UpdateParameters>
                 <InsertParameters>
                     <asp:Parameter Name="RADAR_NO" Type="Int32" />
                     <asp:Parameter Name="DATE_ONSET_RELAP" Type="DateTime" />
                     <asp:Parameter Name="RELAP_TX_NAT" Type="Boolean" />
                     <asp:Parameter Name="TRIG_VIRAL" Type="String" />
                     <asp:Parameter Name="TRIG_IMMUN" Type="String" />
                     <asp:Parameter Name="TRIG_OTHER" Type="String" />
                     <asp:Parameter Name="RELAP_DRUG_1" Type="String" />
                     <asp:Parameter Name="RELAP_DRUG_2" Type="String" />
                     <asp:Parameter Name="RELAP_DRUG_3" Type="String" />
                     <asp:Parameter Name="REMISS_ACHIEVE" Type="Boolean" />
                     <asp:Parameter Name="DATE_REMISSION" Type="DateTime" />
                     <asp:Parameter Name="SEQ_NO" Type="Int32" />
                 </InsertParameters>
        </asp:SqlDataSource>
        <asp:SqlDataSource ID="SqlDataSource8" runat="server" 
        ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
        SelectCommand="SELECT tbl_RRT_PLASMA.plID, tbl_RRT_PLASMA.DATE_START_PLASMAPH, tbl_RRT_PLASMA.DATE_STOP_PLASMAPH, tbl_RRT_PLASMA.DUR_PLASMAPH, tbl_RRT_PLASMA.RESPONSE_TO_PLASMA, tbl_RRT_PLASMA_LU.exDesc FROM tbl_RRT_PLASMA INNER JOIN tbl_RRT_PLASMA_LU ON tbl_RRT_PLASMA.NO_EXCH_PLASMAPH = tbl_RRT_PLASMA_LU.exID WHERE (tbl_RRT_PLASMA.RADAR_NO = @RADAR_NO) ORDER BY tbl_RRT_PLASMA.DATE_START_PLASMAPH DESC"
        DeleteCommand="DELETE FROM [tbl_RRT_PLASMA] WHERE [plID] = @plID">
        <SelectParameters>
            <asp:SessionParameter Name="RADAR_NO" SessionField="patientID" Type="Int32" />
        </SelectParameters>
         <DeleteParameters>
                <asp:Parameter Name="plID" Type="Int32" />
            </DeleteParameters>
    </asp:SqlDataSource>
    <asp:SqlDataSource ID="SqlDataSource10" runat="server" 
                    ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
                    
                    
             SelectCommand="SELECT [exID], [exDesc] FROM [tbl_RRT_PLASMA_LU] ORDER BY [exID]">
                </asp:SqlDataSource>
</asp:Content>

