<%@ Page Title="" Language="VB" MasterPageFile="~/MasterPage.master" AutoEventWireup="false" CodeFile="first-treatment.aspx.vb" Inherits="first_treatment" %>

<%@ Register Assembly="DevExpress.Web.v9.2, Version=9.2.4.0, Culture=neutral, PublicKeyToken=b88d1754d700e49a"
    Namespace="DevExpress.Web.ASPxPopupControl" TagPrefix="dxpc" %>


<%@ Register assembly="DevExpress.Web.ASPxEditors.v9.2, Version=9.2.4.0, Culture=neutral, PublicKeyToken=b88d1754d700e49a" namespace="DevExpress.Web.ASPxEditors" tagprefix="dxe" %>
<%@ Register assembly="AjaxControlToolkit" namespace="AjaxControlToolkit" tagprefix="cc1" %>


<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
    <%--<script type="text/javascript">

    function Date_Start_Dial_Changed(sender,args) {

        $find("stopDate").set_selectedDate(sender.get_selectedDate());

    
    }

</script>--%>
    <link href="tabs_lab.css" rel="stylesheet" type="text/css" />
<link href="tabs.css" rel="stylesheet" type="text/css" />
<style type="text/css">
    input, select, textarea
    {
        border: 1px solid gray;
    }
    select, input, textarea
    {
        font-size: 95%;
        color: #2D2E2E;
        height: 20px;
    }
    #subBlock table
    {
        margin: 0;
    }
    .indent
    {
        padding-left: 50px;
    }
    .noborder
    {
        border: 0;
    }
    #tblHeader
    {
        background-color: #b6df9f;
    }
    fieldset
    {
        border: 1px solid green;
        padding: 5px;
    }
    legend
    {
        font-weight: bold;
    }
    .style2
    {
        border-collapse: collapse;
        border: 0px solid #808080;
    }
    .style4
    {
        border-collapse: collapse;
        border-style: solid;
        border-width: 0px;
    }
    .style9
    {
        width: 100px;
    }

    .pnlEdit
    {
        font-size: 85%;
    }
    
    #pnlDrugs td {
    height:20px;
    white-space:nowrap;
    font-size:95%;
    }
    
    #pnlDrugs table {
    display:inline;
    border-collapse:collapse;
    }
    
    .webkit, #pnlDrugs table {
    display:inline-table;
    margin:0;
}
    
     #tblHeadings td {
    white-space:nowrap;
    height:20px;
    border:1px solid #eef6ed;
    }
  
   
    
      #ctl00_ContentPlaceHolder1_tblDrugs input, #tblDrugs input
    {
        border: 0px;
    }
    
  
    #tblDrugs td, #ctl00_ContentPlaceHolder1_tblDrugs td {
    border:1px solid gray;
   
    }
  
   .style10
    {
        width: 118px;
        height: 37px;
    }
 
   
    .style11
    {
        width: 245px;
    }
    .style12
    {
        width: 245px;
        height: 37px;
    }
    
    #tblDrugs td {
    height:30px;
    
    }
    
    
  #tblDrugs {
  border:1px solid gray;
  table-layout:fixed;
  }
    
    .style13
    {
        width: 480px;
        border-collapse: collapse;
        border-style: solid;
        border-width: 1px;
        
    }
    
      .style15
    {
        width: 150px;
    }
    .style16
    {
        width: 250px;
        font-weight: bold;
    }
    .style17
    {
        width: 140px;
        font-weight: bold;
    }
    
</style>

<!--[if IE]>

<style type="text/css">

#mainBlock {
margin-top: 0px;
}
#subBlock {
margin-top:0;
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
        <li><asp:hyperlink runat="server" ID="lnkDemographics" navigateurl="demographics.aspx" Tooltip="Demographics"><span>Demographics</span></asp:hyperlink></li> 
        <li><asp:hyperlink runat="server" ID="lnkDiagnosis" NavigateUrl="diagnosis.aspx" ToolTip="Diagnosis"><span>Diagnosis</span></asp:hyperlink></li> 
        <li><asp:hyperlink runat="server" ID="lnkLabResults" NavigateUrl="first-clinical.aspx" Tootip="Laboratory Results"><span class="hovered">First Visit</span></asp:hyperlink></li>
        <li><asp:hyperlink runat="server" ID="lnk3Months" NavigateUrl="followup-clinical.aspx" Tooltip="Follow Up"><span>Follow Up</span></asp:hyperlink></li>
        <li><asp:hyperlink runat="server" ID="lnkPathology" NavigateUrl="pathology.aspx" Tootip="Pathology"><span>Pathology</span></asp:hyperlink></li> 
        <li><asp:hyperlink runat="server" ID="lnkRelapse" NavigateUrl="relapse.aspx" Tooltip="Relapse"><span>Relapse</span></asp:hyperlink></li> 
        <li><asp:hyperlink runat="server" ID="lnkHospital" NavigateUrl="hospitalisation.aspx" Tooltip="Hospitalisation"><span>Hospitalisation</span></asp:hyperlink></li> 
        <li><asp:hyperlink runat="server" ID="lnkTimelines" NavigateUrl="timelines.aspx" Tootip="Timelines"><span>Timelines</span></asp:hyperlink></li> 
</ul>
</div>
<div id="mainBlock">
<div id="tabsLab" style="margin:-20px 0 0 -40px;">
<ul>
	<li><asp:hyperlink runat="server" ID="lnkFirstClinical" NavigateUrl="first-clinical.aspx" Tooltip="Clinical Picture"><span>Clinical Picture</span></asp:hyperlink></li>
	<li><asp:hyperlink runat="server" ID="lnkFirstLab" NavigateUrl="first-lab.aspx" Tootip="Laboratory Results"><span>Laboratory Results</span></asp:hyperlink></li>
	<li><asp:hyperlink runat="server" ID="lnkFirstTreatment" NavigateUrl="#" Tooltip="Treatment"><span class="hovered">Treatment</span></asp:hyperlink></li>
</ul>
</div>
<div id="subBlock">
    <%--<%#GetResponse(DataBinder.Eval(Container.DataItem, "RESPONSE_TO_PLASMA"))%>--%>
<table cellpadding="4" class="style1" style="width: 100%;" id="tblHeader">
		<tr>
			<td >
                <asp:Label ID="lblTitle" runat="server" Font-Bold="True" 
                    Text="First Visit - Treatment"></asp:Label></td><td style="width:100px; text-align:right;">RADAR No:</td><td  style="width:160px;padding-right:10px;">
			<asp:TextBox id="txtRADAR_NO" runat="server" ReadOnly="True" Width="100px"></asp:TextBox></td></tr><tr>
			<td>Name
			<asp:TextBox id="txtFNAME" runat="server" Width="100px" ReadOnly="True"></asp:TextBox>&nbsp;<asp:TextBox 
                    id="txtSNAME" runat="server" Width="120px" ReadOnly="True"></asp:TextBox>&nbsp; DoB
			<asp:TextBox id="txtDOB" runat="server" Width="80px" ReadOnly="True"></asp:TextBox>&nbsp;<asp:Label ID="lblID" runat="server" Text="" Visible="false"></asp:Label></td><td style="text-align:right;" class="style9" >Hospital No:</td><td >
			<asp:TextBox id="txtHOSP_NO" runat="server" ReadOnly="True" Width="100px"></asp:TextBox></td></tr><tr>
			<td><span class="inline">Date of Treatment record &nbsp;<asp:TextBox ID="txtDateTmt" 
                    runat="server" Width="70px"></asp:TextBox><cc1:CalendarExtender ID="txtDateTmt_CalendarExtender" runat="server" 
                    Enabled="True" TargetControlID="txtDateTmt" Format="dd-MM-yyyy" PopupButtonID="ImageButton1">
                </cc1:CalendarExtender>&nbsp;<asp:ImageButton ID="ImageButton1" runat="server" CausesValidation="False" ImageUrl="~/images/Calendar_scheduleHS.png"  />
                &nbsp;</span><asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" 
                    ControlToValidate="txtDateTmt" ErrorMessage="*" Display="Dynamic"></asp:RequiredFieldValidator>&nbsp; 
                <asp:TextBox ID="txtDateToday" runat="server" BackColor="#B6DF9F" 
                    BorderColor="#B6DF9F" ForeColor="#B6DF9F"></asp:TextBox></td><td style="width:100px; text-align:right;">Diagnosis</td><td>
                <asp:TextBox ID="txtDIAG" runat="server" BackColor="#FFFF99" ReadOnly="True" 
                    Width="100px"></asp:TextBox></td></tr></table><table style="width: 100%; background-color:#EEF6ED; border:0px solid red;" cellpadding="0" cellspacing="10" id="tblMain" runat="server">
	    
	
	<tr>
	<td style="vertical-align:top" >
	
        <asp:Button ID="btnSave2" runat="server" CssClass="saveBtn" Text="Save" />
&nbsp;
        <asp:Label ID="lblSucess2" runat="server"></asp:Label>
	
<br /><br/>
<fieldset style="width:650px;"><legend>Immunosuppression including Monoclonals</legend><br />
        <asp:GridView ID="GridView1" runat="server" Width="600px" 
            AutoGenerateColumns="False" DataSourceID="SqlDataSource7" 
            HeaderStyle-ForeColor="#3c982d" Font-Size="85%" CellPadding="3" DataKeyNames="tID" OnRowEditing="GridView1_Row_Editing" OnRowCreated="GridView_RowCreated">
            <Columns>
               
            <asp:BoundField DataField="tID" Visible="true" ItemStyle-ForeColor="#eef6ed" 
                    ItemStyle-BorderWidth="0" FooterStyle-BorderWidth="0" >
<FooterStyle BorderWidth="0px"></FooterStyle>

<ItemStyle BorderWidth="0px" ForeColor="#EEF6ED"></ItemStyle>
                </asp:BoundField>
            <asp:BoundField DataField="FIRST_FLAG" Visible="false" />
            <asp:BoundField DataField="IMMUNSUP_DRUG" Visible="false" />
                <asp:BoundField DataField="IMMUNSUP_DRUG_STARTDATE" DataFormatString="{0:dd-MMM-yyyy}" 
                    HeaderText="Start date" SortExpression="IMMUNSUP_DRUG_STARTDATE" />
                <asp:BoundField DataField="IMMUNSUP_DRUG_ENDDATE" DataFormatString="{0:dd-MMM-yyyy}" 
                    HeaderText="End Date" SortExpression="IMMUNSUP_DRUG_ENDDATE" />
                <asp:BoundField DataField="imDESC" HeaderText="Name" 
                    SortExpression="imDESC" ReadOnly="true" />
                <asp:BoundField DataField="CYCLOPHOS_TOT_DOSE" HeaderText="Total Dose g Cyclophosphamide only" 
                    SortExpression="CYCLOPHOS_TOT_DOSE" >
                <HeaderStyle Width="120px" />
                <ItemStyle Width="120px" />
                </asp:BoundField>
                <%--<asp:CommandField ButtonType="Button" ShowEditButton="True"  >
                    <ItemStyle HorizontalAlign="Center" />
                </asp:CommandField>--%>
                <asp:TemplateField>
                <ItemTemplate>
                <asp:Button ID="delButton" runat="server" CausesValidation="false" CommandName="Delete" OnClientClick="return confirm('Are you sure you want to delete this record?');" Text="Delete"></asp:Button>
              <asp:Button ID="btnImEdit" runat="server" CausesValidation="false" CommandName="Edit" Text="Edit" />
                </ItemTemplate>
                    <ItemStyle VerticalAlign="Top" />
                </asp:TemplateField>
            </Columns>
            <HeaderStyle ForeColor="#3C982D" />
        </asp:GridView>
        <asp:Panel ID="pnlIEdit" runat="server" Visible="false" >
        <br />
       <fieldset style="border-color:Red; padding:10px; width:95%;"><legend>Edit</legend><br />
        <table class="style4" style="margin-top:10px; font-size:85%; ">
         <tr>
            <td >
                Start Date</td><td  >
                Name</td><td  >
                End Date</td><td  style="width:180px" ><asp:label ID="lblMonoDoseEdit" runat="server" Visible="False" 
                    Text="Total dose in g"></asp:label></td><td >
                <asp:Label ID="lblmID" runat="server"  Height="2px" Visible="False"></asp:Label></td></tr><tr>
			<td>
                <asp:CompareValidator ID="CompareValidator2" runat="server" 
                    ControlToCompare="txtDateToday" ControlToValidate="txtMonoSDEdit" 
                    ErrorMessage="&gt; today" Operator="LessThanEqual" Type="Date"></asp:CompareValidator></td><td>
			    &nbsp;</td><td class="style7">
			    <asp:Label ID="lblMonoEDEditWarn" runat="server" ForeColor="Red"></asp:Label></td><td class="style7">
                <asp:RangeValidator ID="RangeValidator3" runat="server" 
                    ControlToValidate="txtMonoDoseEdit" ErrorMessage="0.010-9.999" 
                    MaximumValue="9.999" MinimumValue="0.010" Type="Double"></asp:RangeValidator></td><td class="style7">&nbsp;</td></tr><tr>
			<td class="style7">
                <asp:textbox id="txtMonoSDEdit" runat="server" Width="70px"></asp:textbox><cc1:CalendarExtender ID="CalendarExtender1" runat="server" 
                    Enabled="True" TargetControlID="txtMonoSDEdit" PopupButtonID="ImageButton20" Format="dd-MM-yyyy">
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton20" runat="server" 
                    ImageUrl="~/images/Calendar_scheduleHS.png" CausesValidation="false" />
            </td>
			<td class="style7">
			<asp:dropdownlist ID="Dropdownlist2" runat="server" AppendDataBoundItems="True" DataSourceID="SqlDataSource2" DataTextField="imDesc" DataValueField="imID" Font-Size="100%" AutoPostBack="true">
				<asp:listitem Text="Select" Value=""></asp:listitem></asp:dropdownlist>
				</td>
				<td class="style7">
			<asp:TextBox id="txtMonoEDEdit" runat="server" Width="70px"></asp:TextBox><cc1:CalendarExtender ID="CalendarExtender2" runat="server" 
                    Enabled="True" TargetControlID="txtMonoEDEdit" PopupButtonID="ImageButton5" Format="dd-MM-yyyy">
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton5" runat="server" 
                    ImageUrl="~/images/Calendar_scheduleHS.png" CausesValidation="false" />
			</td>
			<td style="width:90px" valign="bottom">
			    &nbsp;&nbsp;
			<asp:textbox id="txtMonoDoseEdit" runat="server" Width="70px" Visible="false"></asp:textbox></td><td class="style7">
			<asp:button id="btnUpdateMono" runat="server" Text="Save" CausesValidation="false" />&nbsp;
			<asp:button id="btnCancelUpdateMono" runat="server" Text="Cancel" CausesValidation="false" />
			</td>
		</tr>
	    <tr>
            <td colspan="2">
                <asp:Label ID="lblMonoEditWarn" runat="server" ForeColor="Red" Text=""></asp:Label></td><td class="style7">
                &nbsp;</td><td class="style7">
                &nbsp;</td><td class="style7">
                &nbsp;</td></tr></table></fieldset></asp:Panel><table class="style4" style="width: 600px; margin-top:10px; font-size:85%">
        <%--<asp:CommandField ButtonType="Button" ShowEditButton="True"  >
                    <ItemStyle HorizontalAlign="Center" />
                </asp:CommandField>--%>
		<tr>
            <td>
                &nbsp;</td><td >
                </td>
            <td >
                </td>
        	<td class="style11"></td>
			<td class="style7">&nbsp;</td></tr><tr>
            <td class="style10">
                Start Date</td><td class="style10" >
                Name</td><td class="style10" >
                End Date</td><td class="style12" ><asp:label ID="lblDose" runat="server" Visible="false" Text="Total dose of course in g"></asp:label></td><td class="style10">
            &nbsp;</td></tr><tr>
			<td>
                <asp:CompareValidator ID="CompareValidator1" runat="server" 
                    ControlToCompare="txtDateToday" ControlToValidate="txtImmunoStart" 
                    ErrorMessage="&gt; today" Operator="LessThanEqual" Type="Date"></asp:CompareValidator></td><td>
			    &nbsp;</td><td class="style7">
			    <asp:Label ID="lblStopWarnImmuno" runat="server" ForeColor="Red"></asp:Label></td>
            <td class="style11" align="center">
                <asp:RangeValidator ID="RangeValidator2" runat="server" 
                    ControlToValidate="txtIMMUN_SUP_DOSE" ErrorMessage="0.010-9.999" 
                    MaximumValue="9.999" MinimumValue="0.010" Type="Double"></asp:RangeValidator></td><td class="style7">
                &nbsp;</td></tr><tr>
			<td class="style7">
                <asp:textbox id="txtImmunoStart" runat="server" Width="70px"></asp:textbox><cc1:CalendarExtender ID="txtImmunoStart_CalendarExtender" runat="server" 
                    Enabled="True" TargetControlID="txtImmunoStart" PopupButtonID="ImageButton11" Format="dd-MM-yyyy">
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton11" runat="server" 
                    ImageUrl="~/images/Calendar_scheduleHS.png" CausesValidation="false" />
            </td>
			<td class="style7">
			<asp:dropdownlist ID="ddlImmunoSupp1" runat="server" AppendDataBoundItems="True" DataSourceID="SqlDataSource2" DataTextField="imDesc" DataValueField="imID" Font-Size="100%" AutoPostBack="true">
				<asp:listitem Text="Select" Value=""></asp:listitem></asp:dropdownlist></td><td class="style7">
			<asp:TextBox id="txtImmunoEnd" runat="server" Width="70px"></asp:TextBox><cc1:CalendarExtender ID="txtImmunoEnd_CalendarExtender" runat="server" 
                    Enabled="True" TargetControlID="txtImmunoEnd" PopupButtonID="ImageButton2" Format="dd-MM-yyyy">
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton2" runat="server" 
                    ImageUrl="~/images/Calendar_scheduleHS.png" CausesValidation="false" />
			</td>
			<td class="style11" align="center">
			<asp:textbox id="txtIMMUN_SUP_DOSE" runat="server" Width="70px" Visible="false"></asp:textbox></td><td class="style7">
			<asp:button id="btnAddMono" runat="server" Text="Add" CausesValidation="false" />
			</td>
		</tr>
	    <tr>
            <td colspan="2">
                <asp:Label ID="lblWarnImmuno" runat="server" ForeColor="Red" Text=""></asp:Label></td><td class="style7">
                &nbsp;</td><td class="style11">
                &nbsp;</td><td class="style7">
                &nbsp;</td></tr></table></fieldset>
 <br />
  </td>
  </tr>
	
	<tr>
	<td style="width:68%; vertical-align:top;">
	<div id="pnlDrugs">
		<fieldset><legend>Drugs</legend>
<br />
	    <table cellpadding="5" class="style13" id="tblDrugs" border="1px">
            <tr>
                <td width="200px">
                    &nbsp;</td>
                <td align="center" class="style17" id="tdPrior" runat="server">
                    Prior to Referral</td>
                <td width="150px" align="center" class="style16">
                <asp:Label ID="lblCurrent" runat="server" Font-Bold="True"></asp:Label></td>
            </tr>
            <tr id="rowNSAID" runat="server">
                <td>
                    NSAID</td>
                <td align="center" class="style15" id="tdNSAID" runat="server">
					    <asp:RadioButton ID="radNsaidInitYes" runat="server" GroupName="NSAID" Text="Yes" /> &nbsp; 
			    <asp:RadioButton ID="radNsaidInitNo" runat="server" GroupName="NSAID" Text="No" /> 
		
			    </td>
                <td align="center">
					    <asp:RadioButton ID="radNsaidYes" runat="server" GroupName="NSAID2" Text="Yes" /> &nbsp;
                <asp:RadioButton ID="radNsaidNo" runat="server" GroupName="NSAID2" Text="No" /> 
		
			    </td>
            </tr>
            <tr id="rowDiuretic" runat="server">
                <td>
                    Diuretic</td>
                <td align="center" class="style15" id="tdDiuretic" runat="server">
					    <asp:RadioButton ID="radP_DiureticYes" GroupName="DIURETIC" runat="server" Text="Yes" /> &nbsp;
			    <asp:RadioButton ID="radP_DiureticNo" GroupName="DIURETIC" runat="server" Text="No" /> 
		
			    </td>
                <td align="center">
					    <asp:RadioButton ID="radDiureticYes" GroupName="DIURETIC2" runat="server" Text="Yes" /> &nbsp;
                <asp:RadioButton ID="radDiureticNo" GroupName="DIURETIC2" runat="server" Text="No" /> 
		
			    </td>
            </tr>
            <tr>
                <td>
                    Antihypertensive</td>
                <td align="center" class="style15" id="tdANTI_HTN" runat="server">
					    <asp:RadioButton ID="radP_ANTI_HtnYes" GroupName="ANTI_HTN" runat="server" AutoPostBack="true" Text="Yes" /> &nbsp;
                <asp:RadioButton ID="radP_ANTI_HtnNo" GroupName="ANTI_HTN" runat="server" AutoPostBack="true" Text="No" /> 
		
			    </td>
                <td align="center">
					    <asp:RadioButton ID="radANTI_HtnYes" GroupName="ANTI_HTN2" runat="server" 
                     Text="Yes" OnCheckedChanged="radANTI_HtnYes_CheckedChanged" AutoPostBack="true" /> &nbsp;
                <asp:RadioButton ID="radANTI_HtnNo" GroupName="ANTI_HTN2" runat="server" 
                    Text="No" OnCheckedChanged="radANTI_HtnNo_CheckedChanged" AutoPostBack="true" /> 
		
			    </td>
            </tr>
            <tr id="rowAce" runat="server">
                <td>
                    ACE Inhibitor</td>
                <td align="center" class="style15" id="tdACE_INHIB" runat="server">
					    <asp:RadioButton ID="radP_ACE_InhibYes" GroupName="ACE_INHIB" runat="server" 
                            Text="Yes" ToolTip="Angiotensin II receptor antagonist" /> &nbsp;
                <asp:RadioButton ID="radP_ACE_InhibNo" GroupName="ACE_INHIB" runat="server" Text="No" /> 
		
			    </td>
                <td align="center">
					    <asp:RadioButton ID="radACE_InhibYes" GroupName="ACE_INHIB2" runat="server" 
                            Text="Yes" ToolTip="Angiotensin-converting enzyme inhibitors" /> &nbsp;
                <asp:RadioButton ID="radACE_InhibNo" GroupName="ACE_INHIB2" runat="server" Text="No" /> 
		
			    </td>
            </tr>
            <tr id="rowARB" runat="server">
                <td>
                    ARB-1 Antagonist</td>
                <td align="center" class="style15" id="tdARB_ANTAG" runat="server">
					    <asp:RadioButton ID="radP_ARB_AntagYes" GroupName="ARB_ANTAG" runat="server" 
                            Text="Yes" ToolTip="Angiotensin II receptor antagonist" /> &nbsp;
                <asp:RadioButton ID="radP_ARB_AntagNo" GroupName="ARB_ANTAG" runat="server" Text="No" /> 
		
			    </td>
                <td align="center">
					    <asp:RadioButton ID="radARB_AntagYes" GroupName="ARB_ANTAG2" runat="server" 
                            Text="Yes" ToolTip="Angiotensin II receptor antagonist" /> &nbsp;
                <asp:RadioButton ID="radARB_AntagNo" GroupName="ARB_ANTAG2" runat="server" Text="No" /> 
		
			    </td>
            </tr>
            <tr id="rowCalcium" runat="server">
                <td>
                    Calcium Channel Blocker</td>
                <td align="center" class="style15" id="tdCA_CH_BLOCK" runat="server">
					    <asp:RadioButton ID="radP_CA_CH_BlockYes" GroupName="CA_CH_BLOCK" runat="server" Text="Yes" /> &nbsp;
                <asp:RadioButton ID="radP_CA_CH_BlockNo" GroupName="CA_CH_BLOCK" runat="server" Text="No" /> 
		
			    </td>
                <td align="center">
					    <asp:RadioButton ID="radCA_CH_BlockYes" GroupName="CA_CH_BLOCK2" runat="server" Text="Yes" /> &nbsp;
                <asp:RadioButton ID="radCA_CH_BlockNo" GroupName="CA_CH_BLOCK2" runat="server" Text="No" /> 
		
			    </td>
            </tr>
            <tr id="rowBeta" runat="server">
                <td>
					&beta; Blocker</td>
                <td align="center" class="style15" id="tdB_BLOCK" runat="server">
					    <asp:RadioButton ID="radP_B_BlockYes" GroupName="B_BLOCK" runat="server" Text="Yes" /> &nbsp;
                <asp:RadioButton ID="radP_B_BlockNo" GroupName="B_BLOCK" runat="server" Text="No" /> 
		
			    </td>
                <td align="center">
					    <asp:RadioButton ID="radB_BlockYes" GroupName="B_BLOCK2" runat="server" Text="Yes" /> &nbsp;
                <asp:RadioButton ID="radB_BlockNo" GroupName="B_BLOCK2" runat="server" Text="No" /> 
		
			    </td>
            </tr>
            <tr id="rowOther" runat="server">
                <td>
                    Other Antihypertensive</td>
                <td align="center" class="style15" id="tdOTHER_ANTI_HTN" runat="server">
					    <asp:RadioButton ID="radP_OTHER_ANTI_HtnYes" GroupName="OTHER_ANTI_HTN" runat="server" Text="Yes" /> &nbsp;
                <asp:RadioButton ID="radP_OTHER_ANTI_HtnNo" GroupName="OTHER_ANTI_HTN" runat="server" Text="No" /> 
		
			    </td>
                <td align="center">
					    <asp:RadioButton ID="radOTHER_ANTI_HtnYes" GroupName="OTHER_ANTI_HTN2" runat="server" Text="Yes" /> &nbsp;
                <asp:RadioButton ID="radOTHER_ANTI_HtnNo" GroupName="OTHER_ANTI_HTN2" runat="server" Text="No" /> 
		
			    </td>
            </tr>
            <tr id="rowInsulin" runat="server">
                <td>
                    Insulin</td>
                <td align="center" class="style15" id="tdINSULIN" runat="server">
					    <asp:RadioButton ID="radP_InsulinYes" GroupName="INSULIN" runat="server" Text="Yes" /> &nbsp;
                <asp:RadioButton ID="radP_InsulinNo" GroupName="INSULIN" runat="server" Text="No" /> 
		
			    </td>
                <td align="center">
					    <asp:RadioButton ID="radInsulinYes" GroupName="INSULIN2" runat="server" Text="Yes" /> &nbsp;
                <asp:RadioButton ID="radInsulinNo" GroupName="INSULIN2" runat="server" Text="No" /> 
		
			    </td>
            </tr>
            <tr id="rowLipid" runat="server">
                <td>
                    Lipid lowering agent</td>
                <td align="center" class="style15" id="tdLIPID_LOWER" runat="server">
		
			    <asp:RadioButton ID="radP_LIP_LOWER_AgYes" GroupName="LIP_LOWER" runat="server" Text="Yes" /> &nbsp;
                <asp:RadioButton ID="radP_LIP_LOWER_AgNo" GroupName="LIP_LOWER" runat="server" Text="No" /> 
		        </td>
                <td align="center">
					    <asp:RadioButton ID="radLIP_LOWER_AgYes" GroupName="LIP_LOWER2" runat="server" Text="Yes" /> &nbsp;
                <asp:RadioButton ID="radLIP_LOWER_AgNo" GroupName="LIP_LOWER2" runat="server" Text="No" /> 
		
			    </td>
            </tr>
            <tr>
                <td>
                    EPO</td>
                <td align="center" class="style15" id="tdEPO" runat="server">
	
			    <asp:RadioButton ID="radP_EPOYes" GroupName="EPO" runat="server" Text="Yes" /> &nbsp; <asp:RadioButton ID="radP_EPONo" GroupName="EPO" runat="server" Text="No" /> 
	
			    </td>
                <td align="center">
					    <asp:RadioButton ID="radEPOYes" GroupName="EPO2" runat="server" Text="Yes" /> &nbsp;
                <asp:RadioButton ID="radEPONo" GroupName="EPO2" runat="server" Text="No" /> 
		
			    </td>
            </tr>
            <tr>
                <td>
                    Other 1</td>
                <td align="center" class="style15" id="tdOTHER_DRUG1" runat="server">
			<asp:TextBox id="txtP_OTHER_DRUG1" runat="server" Width="105px" BorderWidth="1px"></asp:TextBox></td>
                <td align="center">
			<asp:TextBox id="txtOTHER_DRUG1" runat="server" Width="105px" BorderWidth="1px"></asp:TextBox></td>
            </tr>
            <tr>
                <td>
                    Other 2</td>
                <td align="center" class="style15" id="tdOTHER_DRUG2" runat="server">
			<asp:TextBox id="txtP_OTHER_DRUG2" runat="server" Width="105px" BorderWidth="1px"></asp:TextBox></td>
                <td align="center">
			<asp:TextBox id="txtOTHER_DRUG2" runat="server" Width="105px" BorderWidth="1px"></asp:TextBox></td>
            </tr>
            <tr>
                <td>
                    Other 3</td>
                <td align="center" class="style15" id="tdOTHER_DRUG3" runat="server">
			    <asp:TextBox ID="txtP_OTHER_DRUG3" runat="server" BorderWidth="1px" 
                    Width="105px"></asp:TextBox>
                </td>
                <td align="center">
			    <asp:TextBox ID="txtOTHER_DRUG3" runat="server" BorderWidth="1px" Width="105px"></asp:TextBox>
                </td>
            </tr>
            <tr>
                <td>
                    Other 4</td>
                <td align="center" class="style15" id="tdOTHER_DRUG4" runat="server">
			    <asp:TextBox ID="txtP_OTHER_DRUG4" runat="server" BorderWidth="1px" 
                    Width="105px"></asp:TextBox>
                </td>
                <td align="center">
			    <asp:TextBox ID="txtOTHER_DRUG4" runat="server" BorderWidth="1px" Width="105px"></asp:TextBox>
                </td>
            </tr>
        </table>
	<br /><br />
	
	</fieldset>
	&nbsp;</div>
	</td><td style="vertical-align:top;">
	</td>
	</tr>
	
	
  
  <tr>
  <td>
<fieldset ><legend>Plasmapheresis</legend><br />
    <asp:GridView ID="GridView3" runat="server" Width="95%" 
        AutoGenerateColumns="False" DataSourceID="SqlDataSource8" Font-Size="85%" 
        HeaderStyle-ForeColor="#3c982d" CellPadding="3" DataKeyNames="plID" 
        OnRowEditing="gridView3_plasma_edit" OnRowCreated="GridView_RowCreated" 
        EnableModelValidation="True" >
        <Columns>
        <asp:BoundField DataField="plID" Visible="true" ItemStyle-Width="0" HeaderStyle-Width="0" ItemStyle-ForeColor="#eef6ed" FooterStyle-Width="0" ShowHeader="False" ItemStyle-BorderColor="#eef6ed" ItemStyle-BorderWidth="0" FooterStyle-BorderWidth="0" ControlStyle-Width="0" ControlStyle-BorderWidth="0" />
            <asp:BoundField DataField="DATE_START_PLASMAPH" 
                HeaderText="Date Started" DataFormatString="{0:dd-MMM-yyyy}"  />
            <asp:BoundField DataField="DATE_STOP_PLASMAPH" HeaderText="Date Stopped" DataFormatString="{0:dd-MMM-yyyy}" 
                 />
            <asp:BoundField DataField="exDesc" HeaderText="No of Exchanges" 
                 />
           
                  <asp:TemplateField HeaderText="Response">
                      <ItemTemplate>
                      <%--<%#GetResponse(DataBinder.Eval(Container.DataItem, "RESPONSE_TO_PLASMA"))%>--%>
                          <asp:Label ID="Label1" runat="server" Text='<%# GetResponse(Databinder.Eval(Container.DataItem, "RESPONSE_TO_PLASMA")) %>'></asp:Label></ItemTemplate></asp:TemplateField><asp:TemplateField>
                <ItemTemplate>
                <asp:Button ID="btnDelete" runat="server" CausesValidation="false" CommandName="Delete" OnClientClick="return confirm('Are you sure you want to delete this record?');" Text="Delete"></asp:Button>
               <asp:Button ID="btnEdit" runat="server" CausesValidation="false" CommandName="Edit" Text="Edit" />
                </ItemTemplate>
                </asp:TemplateField>
        </Columns>
        <HeaderStyle ForeColor="#3C982D" />
    </asp:GridView>
    <br />
    <asp:Panel ID="pnlPlasmaEdit" runat="server" Visible="false">
    <fieldset style="border-color:Red"><legend>Edit</legend><table cellpadding="5" class="style2" style="width: 100%; font-size:85%; margin-top:10px">
		<tr>
			<td>Date Started<br />
                <asp:CompareValidator ID="CompareValidator3" runat="server" 
                    ControlToCompare="txtDateToday" ControlToValidate="txtDateStartPlasmaEdit" 
                    ErrorMessage="&gt; today" Operator="LessThanEqual" Type="Date" 
                    ValidationGroup="PlasmaEdit"></asp:CompareValidator></td><td>Date Stopped
                <br />
              
            &nbsp;<asp:CompareValidator ID="CompareValidator9" runat="server" 
                    ControlToCompare="txtDateToday" ControlToValidate="txtDateStopPlasmaEdit" 
                    Display="Dynamic" ErrorMessage="&gt; today" Operator="LessThanEqual" 
                    Type="Date"></asp:CompareValidator>
&nbsp;<asp:Label ID="lblPlasmaStopEditWarn" runat="server" ForeColor="Red"></asp:Label></td><td>No of Exchanges</td><td>&nbsp;</td><td>
                Response</td><td>
                <asp:Label ID="lblpID" runat="server" Visible="False"></asp:Label>
            </td></tr><tr>
			<td>
			<asp:TextBox id="txtDateStartPlasmaEdit" runat="server" Width="70px"></asp:TextBox><cc1:CalendarExtender ID="CalendarExtender3" runat="server" 
                    Enabled="True" TargetControlID="txtDateStartPlasmaEdit" PopupButtonID="ImageButton3" Format="dd-MM-yyyy">
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton3" runat="server" CausesValidation="False" ImageUrl="~/images/Calendar_scheduleHS.png" />
			</td>
			<td>
			<asp:TextBox id="txtDateStopPlasmaEdit" runat="server" Width="70px"></asp:TextBox><cc1:CalendarExtender ID="CalendarExtender4" runat="server" 
                    Enabled="True" TargetControlID="txtDateStopPlasmaEdit" PopupButtonID="ImageButton4" Format="dd-MM-yyyy">
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton4" runat="server" CausesValidation="False" ImageUrl="~/images/Calendar_scheduleHS.png" />
			</td>
			<td>
			    <asp:DropDownList ID="ddllPlasmaExchEdit" runat="server" 
                    DataSourceID="SqlDataSource9" DataTextField="exDesc" DataValueField="exID" AppendDataBoundItems="true">
                    <asp:ListItem Value="" Text="Select"></asp:ListItem>
                </asp:DropDownList>
            </td><td>
			    &nbsp;</td><td>
                <asp:DropDownList ID="DropDownList3" runat="server">
                <asp:ListItem Value="" Text="Select" Selected="True"></asp:ListItem>
                <asp:ListItem Value="1" Text="Complete"></asp:ListItem>
                <asp:ListItem Value="2" Text="Partial"></asp:ListItem>
                <asp:ListItem Value="3" Text="None"></asp:ListItem>
                </asp:DropDownList>
                </td><td>
			<asp:Button id="btnUpdatePlasma" runat="server" Text="Save" 
                    ValidationGroup="PlasmaEdit" />
                <asp:Button ID="btnCancelPlasmaUpdate" runat="server" Text="Cancel" CausesValidation="false" />
			</td>
		</tr>
		<tr>
			<td colspan="3">
                <asp:Label ID="lblPlasmaEditWarn" runat="server" Text="" ForeColor="Red"></asp:Label></td><td>&nbsp;</td><td>
                &nbsp;</td><td>&nbsp;</td></tr></table></fieldset></asp:Panel><br />
<table cellpadding="5" class="style2" style="width: 100%; font-size:85%; margin-top:10px">
		<tr>
			<td>Date Started<br />
                <asp:CompareValidator ID="CompareValidator7" runat="server" 
                    ControlToCompare="txtDateToday" ControlToValidate="txtDATE_START_PLASMAPH" 
                    ErrorMessage="&gt; today" Operator="LessThanEqual" Type="Date"></asp:CompareValidator></td><td>Date Stopped
                <br />
                <%--<%#GetResponse(DataBinder.Eval(Container.DataItem, "RESPONSE_TO_PLASMA"))%>--%>&nbsp;<%--<asp:CommandField ButtonType="Button" ShowEditButton="True">
                    <ItemStyle HorizontalAlign="Center" />
                </asp:CommandField>--%>
                <asp:Label ID="lblStopWarnP" runat="server" Text="" ForeColor="Red"></asp:Label></td><td>No of Exchanges</td><td>&nbsp;</td><td>
                Response&nbsp;&nbsp;<asp:HyperLink ID="hypRemission" runat="server" CssClass="info" NavigateUrl="#" 
                Font-Bold="True" ForeColor="Green">Help<span>Complete: protein:creatinine ratio < 40mg/mmol; Partial: protein:creatinine ratio < 200mg/mmol.</span></asp:HyperLink></td><td>&nbsp;</td></tr><tr>
			<td>
			<asp:TextBox id="txtDATE_START_PLASMAPH" runat="server" Width="70px"></asp:TextBox><cc1:CalendarExtender ID="TextBox27_CalendarExtender" runat="server" 
                    Enabled="True" TargetControlID="txtDATE_START_PLASMAPH" PopupButtonID="ImageButton15" Format="dd-MM-yyyy">
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton15" runat="server" CausesValidation="False" ImageUrl="~/images/Calendar_scheduleHS.png" />
			</td>
			<td>
			<asp:TextBox id="txtDATE_STOP_PLASMAPH" runat="server" Width="70px"></asp:TextBox><cc1:CalendarExtender ID="TextBox28_CalendarExtender" runat="server" 
                    Enabled="True" TargetControlID="txtDATE_STOP_PLASMAPH" PopupButtonID="ImageButton16" Format="dd-MM-yyyy">
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton16" runat="server" CausesValidation="False" ImageUrl="~/images/Calendar_scheduleHS.png" />
			</td>
			<td>
			    <asp:DropDownList ID="ddlPlasmaExch" runat="server" 
                    DataSourceID="SqlDataSource9" DataTextField="exDesc" DataValueField="exID" AppendDataBoundItems="true">
                    <asp:ListItem Value="">Select</asp:ListItem>
                </asp:DropDownList>
            </td><td>
			    &nbsp;</td><td>
                <asp:DropDownList ID="ddlPlasResponse" runat="server">
                <asp:ListItem Value="" Text="Select" Selected="True"></asp:ListItem>
                <asp:ListItem Value="1" Text="Complete"></asp:ListItem>
                <asp:ListItem Value="2" Text="Partial"></asp:ListItem>
                <asp:ListItem Value="3" Text="None"></asp:ListItem>
                </asp:DropDownList>
                </td><td>
			<asp:Button id="btnPlasmaAdd" runat="server" Text="Add" CausesValidation="false" />
			</td>
		</tr>
		<tr>
			<td colspan="3">
                <asp:Label ID="lblWarnP" runat="server" Text="" ForeColor="Red"></asp:Label></td><td>&nbsp;</td><td>
                &nbsp;</td><td>&nbsp;</td></tr></table></fieldset>
 <br /><br />
<fieldset><legend>Dialysis</legend><br />
    <asp:GridView ID="GridView2" runat="server" Width="100%" 
            HeaderStyle-ForeColor="#3c982d" Font-Size="85%" AutoGenerateColumns="False" 
            DataSourceID="SqlDataSource6" CellPadding="3" DataKeyNames="tID"  OnRowCreated="GridView_RowCreated"  >
            <Columns>
            <asp:BoundField DataField="tID" ItemStyle-ForeColor="#eef6ed" ItemStyle-BorderWidth="0" HeaderStyle-BorderWidth="0" HeaderStyle-Width="0" ItemStyle-Width="0" ApplyFormatInEditMode="true"  />
                <asp:BoundField DataField="mType" HeaderText="Dialysis Type" 
                     />
                <asp:BoundField DataField="DATE_START" HeaderText="Date Started" 
                     DataFormatString="{0:dd-MMM-yyyy}" />
                <asp:BoundField DataField="DATE_STOP" HeaderText="Date Stopped" 
                    DataFormatString="{0:dd-MMM-yyyy}" />
            	
                <%--<asp:CommandField ButtonType="Button" ShowEditButton="True">
                    <ItemStyle HorizontalAlign="Center" />
                </asp:CommandField>--%>
            	<asp:TemplateField>
                <ItemTemplate>
                <asp:Button ID="delButton" runat="server" CausesValidation="false" CommandName="Delete" OnClientClick="return confirm('Are you sure you want to delete this record?');" Text="Delete"></asp:Button>
               <asp:Button ID="editButton" runat="server" CausesValidation="false" CommandName="Edit" Text="Edit" />

                </ItemTemplate>
                </asp:TemplateField>
            </Columns>
            <HeaderStyle ForeColor="#3C982D" />
        </asp:GridView>
        	<asp:Panel ID="pnlDiEdit" runat="server" Visible="false">
	<br />
	<fieldset style="width:650px; border:1px solid red;"><legend>Edit</legend><table class="style4" style="margin-top:10px; font-size:85%; " width="100%">
	     <tr>
	     <td style="width:290px">Dialysis Type<br />
<asp:Label ID="lblDialEditWarn" runat="server" ForeColor="Red"></asp:Label>
             <br />
             </td><td style="width:100px;">Date Started</td><td>Date Stopped</td><td>
             <asp:Label ID="lblDialID" runat="server" ForeColor="Black" Visible="False"></asp:Label></td></tr><tr>
	     <td >
             <asp:Label ID="lblEditStartWarn" runat="server" ForeColor="Red"></asp:Label></td><td>
                  <asp:CompareValidator ID="CompareValidator8" runat="server" 
                      ControlToCompare="txtDateToday" ControlToValidate="txtEditDialStart" 
                      ErrorMessage="&gt;today" Type="Date" ValidationGroup="EditDial" Operator="LessThanEqual"></asp:CompareValidator></td><td>
                  <asp:Label ID="lblEditStopWarn" runat="server" ForeColor="Red"></asp:Label></td><td>
                  &nbsp;</td></tr><tr>
	     <td >
             <asp:DropDownList ID="ddlEditDial" runat="server" DataSourceID="SqlDataSource3" 
                 DataTextField="mType" DataValueField="mID" AppendDataBoundItems="true">
                 <asp:ListItem Value="" Text="Select"></asp:ListItem></asp:DropDownList></td><td>
             <asp:TextBox ID="txtEditDialStart" runat="server" Width="80px"></asp:TextBox><cc1:CalendarExtender ID="txtEditDialStart_CalendarExtender" runat="server" 
                 Enabled="True" TargetControlID="txtEditDialStart" PopupButtonID="imgDialEditStart" Format="dd-MM-yyyy">
             </cc1:CalendarExtender>
             <asp:Image ID="imgDialEditStart" runat="server" 
                 ImageUrl="~/images/Calendar_scheduleHS.png" />
              </td>
	     <td>
             <asp:TextBox ID="txtEditDialStop" runat="server" Width="80px"></asp:TextBox><cc1:CalendarExtender ID="txtEditDialStop_CalendarExtender" runat="server" 
                 Enabled="True" TargetControlID="txtEditDialStop" PopupButtonID="imgDialEditStop" Format="dd-MM-yyyy">
             </cc1:CalendarExtender>
             <asp:Image ID="imgDialEditStop" runat="server" 
                 ImageUrl="~/images/Calendar_scheduleHS.png" />
              </td>
	     <td style="text-align:right;">
	     	<asp:button id="btnUpdateDial" runat="server" Text="Save" 
                 ValidationGroup="EditDial" />&nbsp;
			<asp:button id="btnCancelDialEdit" runat="server" Text="Cancel" CausesValidation="false" />
	     </td>
	     </tr>
	     </table>
	</fieldset>
	</asp:Panel>

	<table cellpadding="5" class="style2" style="width: 100%; font-size:85%; margin-top:10px;">
		<tr>
			<td>Dialysis Type
                <asp:Label ID="lblWarn" runat="server" Text="" ForeColor="Red"></asp:Label></td><td>Date Started
                
                <br />
                
                <asp:CompareValidator ID="RangeValidator1" runat="server" 
                    ControlToValidate="txtDATE_START_DIAL" ErrorMessage="&gt; today" 
                    ControlToCompare="txtDateToday" Operator="LessThanEqual" Type="Date"></asp:CompareValidator></td><td>Date Stopped
                <br />
            &nbsp;<asp:Label ID="lblStopWarn" runat="server" Text="" ForeColor="Red"></asp:Label></td><td>
			    &nbsp;</td></tr><tr>
			<td>
			<asp:DropDownList id="ddlHD" runat="server" DataSourceID="SqlDataSource3" 
                    DataTextField="mType" DataValueField="mID" AppendDataBoundItems="true" 
                    Font-Size="100%">
			<asp:ListItem Value="" Text="Select"></asp:ListItem></asp:DropDownList></td><td>
			<asp:TextBox id="txtDATE_START_DIAL" runat="server" Width="70px"></asp:TextBox><cc1:CalendarExtender ID="txtDATE_START_DIAL_CalendarExtender" runat="server" 
                    Enabled="True" TargetControlID="txtDATE_START_DIAL" PopupButtonID="ImageButton12" Format="dd-MM-yyyy" BehaviorID="startDate">
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton12" runat="server" CausesValidation="False" ImageUrl="~/images/Calendar_scheduleHS.png"  />
			</td>
			<td>
			<asp:TextBox id="txtDATE_STOP_DIAL" runat="server" Width="70px"></asp:TextBox><cc1:CalendarExtender ID="txtDATE_STOP_DIAL_CalendarExtender" runat="server" 
                    Enabled="True" TargetControlID="txtDATE_STOP_DIAL" PopupButtonID="ImageButton13" Format="dd-MM-yyyy" BehaviorID="stopDate" >
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton13" runat="server" CausesValidation="False" 
                    ImageUrl="~/images/Calendar_scheduleHS.png"  />
			</td>
			<td>
			<asp:Button id="btnHDAdd" runat="server" Text="Add" CausesValidation="false" />
			</td>
		</tr>
	</table>
	</fieldset>
<br /><br />	
	</td>
	</tr>	
	<tr>
	<td colspan="2"><asp:Button runat="server" Text="Save" ID="btnSave" 
            CausesValidation="true" CssClass="saveBtn" />&nbsp;<asp:ValidationSummary 
            ID="ValidationSummary1" runat="server" CssClass="inline" 
            DisplayMode="List" 
            HeaderText="One or more required fields are missing" ShowMessageBox="True" 
            ShowSummary="False" />
        <asp:Label ID="lblSucess" runat="server" CssClass="inline"></asp:Label>
        </td>
        </tr>
        </table>
        <p><asp:Label ID="lblDebug" runat="server"></asp:Label><asp:SqlDataSource ID="SqlDataSource1" runat="server" 
            ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
            SelectCommand="SELECT [mID], [mDesc] FROM [tbl_RT_Modality] ORDER BY [mID]">
        </asp:SqlDataSource>
        <asp:SqlDataSource ID="SqlDataSource2" runat="server" 
            ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
            SelectCommand="SELECT [imID], [imDesc] FROM [tbl_ImmunoSupp] ORDER BY [imDesc] ASC">
        </asp:SqlDataSource>
            <%--<asp:CommandField ButtonType="Button" ShowEditButton="True">
                    <ItemStyle HorizontalAlign="Center" />
                </asp:CommandField>--%>
	
	<asp:SqlDataSource ID="SqlDataSource3" runat="server" 
        ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
        SelectCommand="SELECT [mID], [mType] FROM [tbl_RRT_MODALITY] WHERE (([mID] >= @mID) AND ([mID] <= @mID2))">
        <SelectParameters>
            <asp:Parameter DefaultValue="1" Name="mID" Type="Int32" />
            <asp:Parameter DefaultValue="19" Name="mID2" Type="Int32" />
        </SelectParameters>
	</asp:SqlDataSource>
	
			<%--<asp:CommandField ButtonType="Button" ShowEditButton="True">
                    <ItemStyle HorizontalAlign="Center" />
                </asp:CommandField>--%>
			
			<asp:SqlDataSource ID="SqlDataSource4" runat="server" 
        ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
        SelectCommand="SELECT [mID], [mType] FROM [tbl_RRT_MODALITY] WHERE (([mID] &gt;= @mID) AND ([mID] &lt;= @mID2))">
                <SelectParameters>
                    <asp:Parameter DefaultValue="10" Name="mID" Type="Int32" />
                    <asp:Parameter DefaultValue="19" Name="mID2" Type="Int32" />
                </SelectParameters>
			</asp:SqlDataSource>
			
        <asp:SqlDataSource ID="SqlDataSource5" runat="server" 
            ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
            SelectCommand="SELECT [mID], [mDesc] FROM [tbl_MONOCLONAL] ORDER BY [mID]">
        </asp:SqlDataSource>
        <asp:SqlDataSource ID="SqlDataSource6" runat="server" 
            ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
        
            SelectCommand="SELECT tID, tbl_RRT_TREATMENT.DATE_START, tbl_RRT_TREATMENT.DATE_STOP, tbl_RRT_MODALITY.mType, tbl_RRT_TREATMENT.RADAR_NO, tbl_RRT_TREATMENT.MODALITY, tbl_RRT_TREATMENT.FIRST_FLAG FROM tbl_RRT_MODALITY INNER JOIN tbl_RRT_TREATMENT ON tbl_RRT_MODALITY.mID = tbl_RRT_TREATMENT.MODALITY WHERE (tbl_RRT_TREATMENT.RADAR_NO = @RADAR_NO) AND (tbl_RRT_TREATMENT.MODALITY &gt;= 1) AND (tbl_RRT_TREATMENT.MODALITY &lt;= 19) ORDER BY tbl_RRT_TREATMENT.DATE_START DESC"
              DeleteCommand="DELETE FROM [tbl_RRT_TREATMENT] WHERE [tID] = @tID"  >
            <SelectParameters>
                <asp:SessionParameter Name="RADAR_NO" SessionField="patientID" Type="Int32" />
            </SelectParameters>
            <DeleteParameters>
                <asp:Parameter Name="tID" Type="Int32" />
            </DeleteParameters>
        </asp:SqlDataSource>
        <asp:SqlDataSource ID="SqlDataSource7" runat="server" 
            ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
            SelectCommand="SELECT tbl_IMMUNSUP_TREATMENT.IMMUNSUP_DRUG, tbl_IMMUNSUP_TREATMENT.FIRST_FLAG, tbl_IMMUNSUP_TREATMENT.tID, tbl_IMMUNSUP_TREATMENT.IMMUNSUP_DRUG_STARTDATE, tbl_IMMUNSUP_TREATMENT.IMMUNSUP_DRUG_ENDDATE, tbl_IMMUNSUP_TREATMENT.CYCLOPHOS_TOT_DOSE, tbl_ImmunoSupp.imDesc FROM tbl_IMMUNSUP_TREATMENT INNER JOIN tbl_ImmunoSupp ON tbl_IMMUNSUP_TREATMENT.IMMUNSUP_DRUG = tbl_ImmunoSupp.imID WHERE (tbl_IMMUNSUP_TREATMENT.RADAR_NO = @RADAR_NO ) ORDER BY tbl_IMMUNSUP_TREATMENT.IMMUNSUP_DRUG_STARTDATE DESC"
            DeleteCommand="DELETE FROM [tbl_IMMUNSUP_TREATMENT] WHERE [tID] = @tID" 
            InsertCommand="INSERT INTO [tbl_IMMUNSUP_TREATMENT] ([RADAR_NO], [IMMUNSUP_DRUG_STARTDATE], [IMMUNSUP_DRUG_ENDDATE], [IMMUNSUP_DRUG], [CYCLOPHOS_TOT_DOSE], [FIRST_FLAG]) VALUES (@RADAR_NO, @IMMUNSUP_DRUG_STARTDATE, @IMMUNSUP_DRUG_ENDDATE, @IMMUNSUP_DRUG, @CYCLOPHOS_TOT_DOSE, @FIRST_FLAG)" 
            UpdateCommand="UPDATE [tbl_IMMUNSUP_TREATMENT] SET [RADAR_NO] = @RADAR_NO, [IMMUNSUP_DRUG_STARTDATE] = @IMMUNSUP_DRUG_STARTDATE, [IMMUNSUP_DRUG_ENDDATE] = @IMMUNSUP_DRUG_ENDDATE, [IMMUNSUP_DRUG] = @IMMUNSUP_DRUG, [CYCLOPHOS_TOT_DOSE] = @CYCLOPHOS_TOT_DOSE, [FIRST_FLAG] = @FIRST_FLAG WHERE [tID] = @tID">
            <SelectParameters>
                <asp:SessionParameter Name="RADAR_NO" SessionField="patientID" Type="Int32" />
            </SelectParameters>
            <DeleteParameters>
                <asp:Parameter Name="tID" Type="Int32" />
            </DeleteParameters>
            <UpdateParameters>
                <asp:Parameter Name="RADAR_NO" Type="Int32" />
                <asp:Parameter Name="IMMUNSUP_DRUG_STARTDATE" Type="DateTime" />
                <asp:Parameter Name="IMMUNSUP_DRUG_ENDDATE" Type="DateTime" />
                <asp:Parameter Name="IMMUNSUP_DRUG" Type="String" />
                <asp:Parameter Name="CYCLOPHOS_TOT_DOSE" Type="Decimal" />
                <asp:Parameter Name="FIRST_FLAG" Type="Boolean" />
                <asp:Parameter Name="tID" Type="Int32" />
            </UpdateParameters>
        </asp:SqlDataSource>
        
        <asp:SqlDataSource ID="SqlDataSource8" runat="server" 
        ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
        SelectCommand="SELECT tbl_RRT_PLASMA.plID, tbl_RRT_PLASMA.DATE_START_PLASMAPH, tbl_RRT_PLASMA.DATE_STOP_PLASMAPH, tbl_RRT_PLASMA.DUR_PLASMAPH, tbl_RRT_PLASMA.RESPONSE_TO_PLASMA, tbl_RRT_PLASMA_LU.exDesc FROM tbl_RRT_PLASMA INNER JOIN tbl_RRT_PLASMA_LU ON tbl_RRT_PLASMA.NO_EXCH_PLASMAPH = tbl_RRT_PLASMA_LU.exID WHERE (tbl_RRT_PLASMA.RADAR_NO = @RADAR_NO) ORDER BY tbl_RRT_PLASMA.DATE_START_PLASMAPH DESC"
         DeleteCommand="DELETE FROM [tbl_RRT_PLASMA] WHERE [plID] = @plID" >
        <SelectParameters>
            <asp:SessionParameter Name="RADAR_NO" SessionField="patientID" Type="Int32" />
        </SelectParameters>
        <DeleteParameters>
                <asp:Parameter Name="plID" Type="Int32" />
            </DeleteParameters>
    </asp:SqlDataSource>
            <asp:SqlDataSource ID="SqlDataSource9" runat="server" 
                ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
                SelectCommand="SELECT [exID], [exDesc] FROM [tbl_RRT_PLASMA_LU] ORDER BY [exID]">
            </asp:SqlDataSource>
        </p>
    <%--<asp:CommandField ButtonType="Button" ShowEditButton="True"  >
                    <ItemStyle HorizontalAlign="Center" />
                </asp:CommandField>--%>
</div>
<br />
    <asp:Label ID="lblPage" runat="server" Text=""></asp:Label>
</div>
</asp:Content>

