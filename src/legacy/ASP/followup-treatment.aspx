<%@ Page Title="Renal Radar - Treatment record" Language="VB" MasterPageFile="~/MasterPage.master" AutoEventWireup="false" CodeFile="followup-treatment.aspx.vb" Inherits="followup_treatment" %>
<%@ Register assembly="AjaxControlToolkit" namespace="AjaxControlToolkit" tagprefix="cc1" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
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
#subBlock table {
	margin: 0;
}
.indent {
	padding-left: 50px;
}
#noBorder input {
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
	border: 1px solid #808080;
	text-align:center;
}
    .style3 {
	border: 1px solid #808080;
	width:250px;
}

 .style6 {
	border-collapse: collapse;
	border: 1px solid #808080;
	text-align: center;
	
}
.style7 {
	text-align: center;
}

#subBlock table {
	margin: 0;
}
 .btmBorder {
border-bottom: 1px green solid;
height:30px;
vertical-align:middle;
}
#NSAID input {
border:0;
}

.style6 input {
border:0;
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
    <li><asp:hyperlink runat="server" ID="lnkFollowupTreatment" NavigateUrl="#" ToolTip="Disease Treatment"><span class="hovered">Disease Treatment</span></asp:hyperlink></li>
    <li><asp:hyperlink runat="server" ID="lnkTherapy" NavigateUrl="rrt_therapy.aspx" Tooltip="Therapy"><span >RRT Therapy</span></asp:hyperlink></li> 
        <%--<tr>
	<td colspan="2">Significant change in status <asp:TextBox ID="txtChangeStatus" runat="server"></asp:TextBox>
	</td>
	</tr>--%>
</ul>
</div>
<div id="subBlock">
    <%--<asp:CommandField ButtonType="Button" ShowEditButton="True"  >
                    <ItemStyle HorizontalAlign="Center" />
                </asp:CommandField>--%>
    <table cellpadding="5" class="style1" style="width: 100%; background-color: #B6DF9F;">
	<tr>
        
		<td class="btmBorder">
            <asp:DropDownList ID="DropDownList4" runat="server" 
                DataSourceID="SqlDataSource9" DataTextField="DATE_TREAT"  
                DataValueField="SEQ_NO" AppendDataBoundItems="true" 
                DataTextFormatString="{0:d}" AutoPostBack="true">
                <asp:ListItem Value="" Text="Select"></asp:ListItem>
            </asp:DropDownList>
            
&nbsp;Select previous visit record by date&nbsp;
            <asp:Button ID="btnAdd" runat="server" Text="Add new visit record" CausesValidation="false" />
        </td>
		<td class="style2, btmBorder">&nbsp;</td>
		<td class="style2, btmBorder" style="width:120px">
            <strong>Treatment</strong></td>
	</tr>
	</table>
	<asp:Panel ID="pnlAdd" runat="server" Visible="false">
<table cellpadding="4" class="style1" style="width: 100%;" id="tblHeader">
		<tr>
			<td >
                <b>Follow Up Treatment</b></td>
			<td style="width:100px; text-align:right;">RADAR No:</td>
			<td  style="width:120px;padding-right:10px;">
			<asp:TextBox id="txtRADAR_NO" runat="server" ReadOnly="True" Width="100px"></asp:TextBox>
			</td>
		</tr>
		<tr>
			<td class="style11">Name
			<asp:TextBox id="txtFNAME" runat="server" Width="100px"></asp:TextBox>
			&nbsp;<asp:TextBox id="txtSNAME" runat="server" Width="120px"></asp:TextBox>
			&nbsp; DoB
			<asp:TextBox id="txtDOB" runat="server" Width="80px"></asp:TextBox>
			    &nbsp;<asp:Label ID="lblID" runat="server" Text="" Visible="false"></asp:Label>
			</td>
			<td style="text-align:right;" class="style11" >Hospital No:</td>
			<td class="style11" >
			<asp:TextBox id="txtHOSP_NO" runat="server" ReadOnly="True" Width="100px"></asp:TextBox>
			</td>
		</tr>
		<tr>
			<td>Date of Treatment record &nbsp;<asp:TextBox ID="txtDOT" 
                    runat="server" Width="80px"></asp:TextBox>
                <cc1:calendarextender ID="txtDOT_CalendarExtender" runat="server" 
                    Enabled="True" TargetControlID="txtDOT" PopupButtonID="ImageButton10" 
                    Format="dd-MMM-yyyy">
                </cc1:calendarextender>
                <asp:ImageButton ID="ImageButton10" runat="server" 
                    CausesValidation="False" ImageUrl="~/images/Calendar_scheduleHS.png" />
                <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" 
                    ControlToValidate="txtDOT" SetFocusOnError="True">*</asp:RequiredFieldValidator>
            &nbsp;<a href="#" class="info2"><em>Help</em><span>To select a different month in the 
                date picker, click the current month at the top.<br /><br /> Click again to select 
                a year.</span>  </a></td>
			<td style="width:100px; text-align:right;">Diagnosis</td>
			<td>
                <asp:TextBox ID="txtDIAG" runat="server" ReadOnly="True" BackColor="#FFFF99" 
                    Width="100px"></asp:TextBox>
            </td>
		</tr>
		<tr>
			<td colspan="3"><%--Current Renal treatment Modality
<asp:DropDownList ID="DropDownList1" runat="server" 
                    DataSourceID="SqlDataSource1" DataTextField="mDesc" DataValueField="mID" AppendDataBoundItems="true">
                    <asp:ListItem Value="">Select</asp:ListItem>
                </asp:DropDownList>	--%>		
                
&nbsp;&nbsp;<asp:TextBox ID="txtDateToday" runat="server" BackColor="#b6df9f" 
                    BorderColor="#b6df9f" ForeColor="#b6df9f"></asp:TextBox>
                &nbsp;<asp:TextBox ID="txtSEQ_NO" runat="server" ForeColor="#B6DF9F" 
                    BackColor="#B6DF9F" BorderColor="#B6DF9F"></asp:TextBox>
			</td>
		</tr>
	</table>
	
   
	
	<table style="width: 100%; background-color:#EEF6ED; border:0px solid red;" cellpadding="0" cellspacing="10" id="tblMain" runat="server">
	<%--<tr>
	<td colspan="2">Significant change in status <asp:TextBox ID="txtChangeStatus" runat="server"></asp:TextBox>
	</td>
	</tr>--%>
    <tr>
    <td colspan="2"><asp:Button ID="btnUpdate2" runat="server" Text="Save" 
            CausesValidation="true" CssClass="saveBtn" />
                &nbsp;
        <asp:Button ID="btnSaveNew2" runat="server" Text="Add" CausesValidation="true" 
            CssClass="saveBtn" />
        &nbsp;<asp:Label ID="lblUpdate2" runat="server"></asp:Label></td>
    </tr>
		<tr>
	<td style="vertical-align:top; padding-top:20px;" >
			<fieldset style="width:750px;"><legend>Immunosuppression including Monoclonals</legend>
	<p>
        <asp:Label ID="lblImmunoAdd" runat="server" 
            Text="Please enter all immunosuppresion."></asp:Label>
                 <a href="#" class="info">Help<span>Columns below can be sorted by clicking the titles. Click again to reverse the sort order.</span></a></p>
        <asp:GridView ID="GridView1" runat="server" Width="600px" 
            AutoGenerateColumns="False" DataSourceID="SqlDataSource7" 
            HeaderStyle-ForeColor="#3c982d" Font-Size="85%" CellPadding="3" DataKeyNames="tID" 
                    OnRowCreated="GridView_RowCreated" OnRowEditing="GridView1_RowEditing" 
                    AllowSorting="True">
            <Columns>
            <asp:BoundField DataField="tID" />
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
               <asp:Button ID="editButton" runat="server" CausesValidation="false" CommandName="Edit" Text="Edit" />

                </ItemTemplate>
                </asp:TemplateField>
            </Columns>
            <HeaderStyle ForeColor="#3C982D" />
        </asp:GridView>
	        <asp:Panel ID="pnlIEdit" runat="server" Visible="false" >
        <br />
       <fieldset style="border-color:Red; padding:10px; width:95%;"><legend>Edit</legend>
       <br />
        <table  style="margin-top:10px; font-size:85%; table-layout:fixed; width:100% ">
         <tr>
            <td >
                Start Date</td>
            <td  >
                Name</td>
            <td  >
                End Date</td>
        	<td  style="width:100px" align="center" ><asp:label ID="lblMonoDoseEdit" runat="server" Visible="False" 
                    Text="Total dose in g"></asp:label></td>
			<td >
                <asp:Label ID="lblMonoID" runat="server" Visible="False"></asp:Label>
             </td>
        </tr>
		<tr>
			<td>
                <asp:CompareValidator ID="CompareValidator2" runat="server" 
                    ControlToCompare="txtDateToday" ControlToValidate="txtMonoSDEdit" 
                    ErrorMessage="&gt; today" Operator="LessThanEqual" Type="Date"></asp:CompareValidator>
            </td>
			<td>
			    &nbsp;</td>
			<td class="style7">
			    <asp:Label ID="lblMonoEditStopWarn" runat="server" Text="" ForeColor="red"></asp:Label>
            </td>
			<td align="center">
                <asp:RangeValidator ID="RangeValidator3" runat="server" 
                    ControlToValidate="txtMonoDoseEdit" ErrorMessage="0.010-9.999" 
                    MaximumValue="9.999" MinimumValue="0.010" Type="Double" 
                    ValidationGroup="Immuno"></asp:RangeValidator>
            </td>
			<td class="style7">&nbsp;</td>
		</tr>
		<tr>
			<td class="style7">
                <asp:textbox id="txtMonoSDEdit" runat="server" Width="70px" CssClass="inline"></asp:textbox>
                <cc1:CalendarExtender ID="CalendarExtender1" runat="server" 
                    Enabled="True" TargetControlID="txtMonoSDEdit" PopupButtonID="ImageButton20" Format="dd-MM-yyyy">
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton20" runat="server" 
                    ImageUrl="~/images/Calendar_scheduleHS.png" CausesValidation="false" 
                    CssClass="inline" />
            </td>
			<td class="style7">
			<asp:dropdownlist ID="Dropdownlist2" runat="server" AppendDataBoundItems="True" DataSourceID="SqlDataSource2" DataTextField="imDesc" DataValueField="imID" Font-Size="100%" AutoPostBack="true">
				<asp:listitem Text="Select" Value=""></asp:listitem>
			</asp:dropdownlist>
			</td>
			<td class="style7">
			<asp:TextBox id="txtMonoEDEdit" runat="server" Width="70px"></asp:TextBox>
			    <cc1:CalendarExtender ID="CalendarExtender2" runat="server" 
                    Enabled="True" TargetControlID="txtMonoEDEdit" PopupButtonID="ImageButton5" Format="dd-MM-yyyy" EnableViewState="false">
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton5" runat="server" 
                    ImageUrl="~/images/Calendar_scheduleHS.png" CausesValidation="false" />
			</td>
			<td style="width:90px" align="center">
			<asp:textbox id="txtMonoDoseEdit" runat="server" Width="70px" Visible="false"></asp:textbox>
			</td>
			<td class="style7">
			<asp:button id="btnUpdateMono" runat="server" Text="Save" 
                    ValidationGroup="Immuno" />&nbsp;
			<asp:button id="btnCancelUpdateMono" runat="server" Text="Cancel" CausesValidation="false" />
			</td>
		</tr>
	    <tr>
            <td colspan="2">
                <asp:Label ID="lblMonoEditWarn" runat="server" ForeColor="Red" Text=""></asp:Label>
            </td>
            <td class="style7">
                &nbsp;</td>
            <td class="style7">
                &nbsp;</td>
            <td class="style7">
                &nbsp;</td>
        </tr>
        </table>
        </fieldset></asp:Panel>

	<table class="style4" style="width: 600px; margin-top:10px; font-size:85%" id="tblAddImmuno" runat="server">
		<%--<tr>
			<td style="padding-left:10px;">&nbsp;</td>
			<td style="padding-left:10px;">
                Immunosupression&nbsp;&nbsp;<asp:checkbox ID="chkIMMUN_SUP" runat="server" />
			</td>
			<td>&nbsp;</td>
			<td class="style7">&nbsp;</td>
			<td class="style7">&nbsp;</td>
		</tr>--%>
		<tr>
            <td>
                &nbsp;</td>
            <td >
                </td>
            <td class="style7" >
                </td>
        	<td class="style7"></td>
			<td class="style7">&nbsp;</td>
        </tr>
        <tr>
            <td class="style10">
                Start Date</td>
            <td class="style10" >
                Name</td>
            <td class="style10" >
                End Date</td>
        	<td class="style10" style="width:180px" align="center" ><asp:label ID="lblDose" runat="server" Visible="false" Text="Total dose of course in g"></asp:label></td>
			<td class="style10">&nbsp;</td>
        </tr>
		<tr>
			<td>
                <asp:CompareValidator ID="CompareValidator1" runat="server" 
                    ControlToCompare="txtDateToday" ControlToValidate="txtImmunoStart" 
                    ErrorMessage="&gt; today" Operator="LessThanEqual" Type="Date"></asp:CompareValidator>
            </td>
			<td>
			    &nbsp;</td>
			<td class="style7">
			    <asp:Label ID="lblStopWarnImmuno" runat="server" Text="" ForeColor="red"></asp:Label>
            </td>
			<td class="style7">
                <asp:RangeValidator ID="RangeValidator2" runat="server" 
                    ControlToValidate="txtIMMUN_SUP_DOSE" ErrorMessage="0.010-9.999" 
                    MaximumValue="9.999" MinimumValue="0.010" Type="Double" 
                    ValidationGroup="Immuno2"></asp:RangeValidator>
            </td>
			<td class="style7">&nbsp;</td>
		</tr>
		<tr>
			<td class="style7">
                <asp:textbox id="txtImmunoStart" runat="server" Width="70px"></asp:textbox>
                <cc1:CalendarExtender ID="txtImmunoStart_CalendarExtender" runat="server" 
                    Enabled="True" TargetControlID="txtImmunoStart" PopupButtonID="ImageButton11" Format="dd-MM-yyyy">
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton11" runat="server" 
                    ImageUrl="~/images/Calendar_scheduleHS.png" CausesValidation="false" />
            </td>
			<td class="style7">
			<asp:dropdownlist ID="ddlImmunoSupp1" runat="server" AppendDataBoundItems="True" DataSourceID="SqlDataSource2" DataTextField="imDesc" DataValueField="imID" Font-Size="100%" AutoPostBack="true">
				<asp:listitem Text="Select" Value=""></asp:listitem>
			</asp:dropdownlist>
			</td>
			<td class="style7">
			<asp:TextBox id="txtImmunoEnd" runat="server" Width="70px"></asp:TextBox>
			    <cc1:CalendarExtender ID="txtImmunoEnd_CalendarExtender" runat="server" 
                    Enabled="True" TargetControlID="txtImmunoEnd" PopupButtonID="ImageButton2" Format="dd-MM-yyyy">
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton2" runat="server" 
                    ImageUrl="~/images/Calendar_scheduleHS.png" CausesValidation="false" />
			</td>
			<td class="style7">
			<asp:textbox id="txtIMMUN_SUP_DOSE" runat="server" Width="70px" Visible="false"></asp:textbox>
			</td>
			<td class="style7">
			<asp:button id="btnAddMono" runat="server" Text="Add" ValidationGroup="Immuno2" />
			</td>
		</tr>
	    <tr>
            <td class="style7" colspan="2">
                <asp:Label ID="lblWarnImmuno" runat="server" ForeColor="Red" Text=""></asp:Label>
            </td>
            <td class="style7">
                &nbsp;</td>
            <td class="style7">
                &nbsp;</td>
            <td class="style7">
                &nbsp;</td>
        </tr>
	</table>
	
	</fieldset><br />
	</td>
	</tr>

	<tr>
	<td style="width:68%; vertical-align:top;">
	
	<fieldset><legend>Drugs</legend>
	<brt /><br />
	<table style="width:100%; border-collapse:collapse;  border:0px solid blue" cellpadding="5px" cellspacing="0" id="tblDrugs">
	<tr>
	<td>
	<table cellpadding="5" style="width: 80%; font-size:85%; border-collapse:collapse; table-layout:fixed" cellspacing="0" id="tblTop" >
		
		<tr>
			<td class="style3">&nbsp;</td>
			<td  class="style6" ><strong>Current</strong></td>
		</tr>
		<tr id="rowNSAID" runat="server">
			<td class="style3">NSAID</td>
			<td class="style6"  >
			    <asp:RadioButton ID="radNsaidYes" runat="server" GroupName="NSAID2" /> Yes
                <asp:RadioButton ID="radNsaidNo" runat="server" GroupName="NSAID2" /> No
			</td>
		</tr>
		<tr id="rowDiuretic" runat="server">
			<td class="style3">Diuretic</td>
			<td class="style6"  >
			    <asp:RadioButton ID="radDiureticYes" GroupName="DIURETIC2" runat="server" /> Yes
                <asp:RadioButton ID="radDiureticNo" GroupName="DIURETIC2" runat="server" /> No
			</td>
		</tr>
		<tr>
			<td class="style3">Antihypertensive</td>
			<td class="style6"  >
			    <asp:RadioButton ID="radANTI_HtnYes" GroupName="ANTI_HTN2" runat="server" 
                    AutoPostBack="True" /> Yes
                <asp:RadioButton ID="radANTI_HtnNo" GroupName="ANTI_HTN2" runat="server" 
                    AutoPostBack="True" /> No
			</td>
		</tr>
		<tr id="rowACE" runat="server">
			<td class="style3" title="Angiotensin-converting enzyme inhibitors">ACE Inhibitor</td>
			<td class="style6"  >
			    <asp:RadioButton ID="radACE_InhibYes" GroupName="ACE_INHIB2" runat="server" /> Yes
                <asp:RadioButton ID="radACE_InhibNo" GroupName="ACE_INHIB2" runat="server" /> No
			</td>
		</tr>
		<tr id="rowARB" runat="server">
			<td class="style3" title="Angiotensin II receptor antagonist">ARB-1 Antagonist</td>
			<td class="style6"  >
			    <asp:RadioButton ID="radARB_AntagYes" GroupName="ARB_ANTAG2" runat="server" /> Yes
                <asp:RadioButton ID="radARB_AntagNo" GroupName="ARB_ANTAG2" runat="server" /> No
			</td>
		</tr>
		<tr id="rowCalcium" runat="server">
			<td class="style3">Calcium Channel Blocker</td>
			<td class="style6"  >
			    <asp:RadioButton ID="radCA_CH_BlockYes" GroupName="CA_CH_BLOCK2" runat="server" /> Yes
                <asp:RadioButton ID="radCA_CH_BlockNo" GroupName="CA_CH_BLOCK2" runat="server" /> No
			</td>
		</tr>
		<tr id="rowBeta" runat="server">
			<td class="style3">&beta; Blocker</td>
			<td class="style6"  >
			    <asp:RadioButton ID="radB_BlockYes" GroupName="B_BLOCK2" runat="server" /> Yes
                <asp:RadioButton ID="radB_BlockNo" GroupName="B_BLOCK2" runat="server" /> No
			</td>
		</tr>
		<tr id="rowOther" runat="server">
			<td class="style3">Other Antihypertensive</td>
			<td class="style6"  >
			    <asp:RadioButton ID="radOTHER_ANTI_HtnYes" GroupName="OTHER_ANTI_HTN2" runat="server" /> Yes
                <asp:RadioButton ID="radOTHER_ANTI_HtnNo" GroupName="OTHER_ANTI_HTN2" runat="server" /> No
			</td>
		</tr>
		<tr id="rowInsulin" runat="server">
			<td class="style3">Insulin</td>
			<td class="style6"  >
			    <asp:RadioButton ID="radInsulinYes" GroupName="INSULIN2" runat="server" /> Yes
                <asp:RadioButton ID="radInsulinNo" GroupName="INSULIN2" runat="server" /> No
			</td>
		</tr>
		<tr id="rowLipid" runat="server">
			<td class="style3">Lipid lowering agent</td>
			<td class="style6"  >
			    <asp:RadioButton ID="radLIP_LOWER_AgYes" GroupName="LIP_LOWER2" runat="server" /> Yes
                <asp:RadioButton ID="radLIP_LOWER_AgNo" GroupName="LIP_LOWER2" runat="server" /> No
			</td>
		</tr>
		<tr>
			<td class="style3">EPO</td>
			<td class="style6"  >
			    <asp:RadioButton ID="radEPOYes" GroupName="EPO2" runat="server" /> Yes
                <asp:RadioButton ID="radEPONo" GroupName="EPO2" runat="server" /> No
			</td>
		</tr>
		
		<tr>
			<td class="style3">Other 1</td>
			<td class="style2"  >
			<asp:TextBox id="txtOTHER_DRUG1" runat="server" Width="105px"></asp:TextBox>
			</td>
		</tr>
		<tr>
			<td class="style3">Other 2</td>
			<td class="style2"  >
			<asp:TextBox id="txtOTHER_DRUG2" runat="server" Width="105px"></asp:TextBox>
			</td>
		</tr>
		<tr>
            <td class="style3">
                Other 3</td>
            <td class="style2" >
                <asp:TextBox ID="txtOTHER_DRUG3" runat="server" Width="105px"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td class="style3">
                Other 4</td>
            <td class="style2" >
                <asp:TextBox ID="txtOTHER_DRUG4" runat="server" Width="105px"></asp:TextBox>
            </td>
        </tr>
		
	</table>
	<table style="width:100%; border:0;" id="tblBottom">
	<tr>
	<td style="vertical-align:top">
	
	</td>
	</tr>
	</table>
		</td>
	</tr>
	
	</table>
	</fieldset>
	</td>
	<td style="vertical-align:top;">
	</td>
	</tr>
	<tr>
	<td>

<fieldset ><legend>Plasmapheresis</legend>
<p>
    <asp:Label ID="lblPlasmaAdd" runat="server" 
        Text="Please enter all plasmapheresis"></asp:Label>
    </p>
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
                      <%--<%#GetResponse(DataBinder.Eval(Container.DataItem, "RESPONSE_TO_PLASMA"))%>--%>
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
    <fieldset style="border-color:Red; width:95%;"><legend>Edit</legend>
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
	<br /><br />

	<%--
	<fieldset><legend>PD</legend>
		<table cellpadding="5" class="style2" style="width: 100%; font-size:85%; margin-top:10px">
		<tr>
			<td>PD Type</td>
			<td>Date Started
                <br />
                <asp:CompareValidator ID="CompareValidator5" runat="server" 
                    ControlToCompare="txtDateToday" ControlToValidate="txtDATE_START_PD" 
                    ErrorMessage="&gt; today" Operator="LessThanEqual" Type="Date"></asp:CompareValidator>
            </td>
			<td>Date Stopped
                <br />
                <asp:CompareValidator ID="CompareValidator2" runat="server" 
                    ControlToCompare="txtDATE_START_PD" ControlToValidate="txtDATE_STOP_PD" 
                    ErrorMessage="&lt; start date" Operator="GreaterThanEqual" 
                    SetFocusOnError="True" Type="Date" Display="Dynamic"></asp:CompareValidator>
            &nbsp;<asp:CompareValidator ID="CompareValidator6" runat="server" 
                    ControlToCompare="txtDateToday" ControlToValidate="txtDATE_STOP_PD" 
                    ErrorMessage="&gt; today" Operator="LessThanEqual" Type="Date"></asp:CompareValidator>
            </td>
			<td>
			</td>
		</tr>
		<tr>
			<td>
			<asp:DropDownList id="ddlPD" runat="server" DataSourceID="SqlDataSource4" 
                    DataTextField="mType" DataValueField="mID" AppendDataBoundItems="true" 
                    Font-Size="100%">
			<asp:ListItem Value="" Text="Select"></asp:ListItem>
			</asp:DropDownList>
			</td>
			<td>
			<asp:TextBox id="txtDATE_START_PD" runat="server" Width="70px"></asp:TextBox>
			    <cc1:CalendarExtender ID="txtDATE_START_PD_CalendarExtender" runat="server" 
                    Enabled="True" TargetControlID="txtDATE_START_PD" PopupButtonID="ImageButton11" Format="dd-MM-yyyy">
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton11" runat="server" CausesValidation="False" ImageUrl="~/images/Calendar_scheduleHS.png"  />
			</td>
			<td>
			<asp:TextBox id="txtDATE_STOP_PD" runat="server" Width="70px"></asp:TextBox>
			    <cc1:CalendarExtender ID="txtDATE_STOP_PD_CalendarExtender" runat="server" 
                    Enabled="True" TargetControlID="txtDATE_STOP_PD" PopupButtonID="ImageButton14" Format="dd-MM-yyyy">
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton14" runat="server" CausesValidation="False" ImageUrl="~/images/Calendar_scheduleHS.png"  />
			</td>
			<td>
			<asp:Button id="btnPDAdd" runat="server" Text="Add" CausesValidation="false" />
			</td>
		</tr>
	</table>

	</fieldset> --%>      
	</td>
	</tr>	
	
		<tr>
	<td colspan="2"><asp:Button runat="server" Text="Save" ID="btnUpdate" CssClass="saveBtn" CausesValidation="true" />&nbsp;&nbsp;<asp:Button ID="btnSaveNew" runat="server" Text="Add" CausesValidation="true" CssClass="saveBtn" /><asp:ValidationSummary 
            ID="ValidationSummary1" runat="server" CssClass="inline" 
            DisplayMode="List" 
            HeaderText="One or more required fields are missing" ShowMessageBox="True" 
            ShowSummary="False" />
        &nbsp;&nbsp;<asp:Label ID="lblUpdate" runat="server" CssClass="inline"></asp:Label>
        </td>
	</tr>
	</table>
	
        <asp:Label ID="lblDebug" runat="server" Text=""></asp:Label>
        </asp:panel>
        <%--<asp:Panel ID="pnlWarn" runat="server" Visible="false">
        <p>Is this the first treatment record? If so, please enter it in the <strong>First 
            Visit</strong> section</p>
        <p>If this is a Follow Up treatment but you&#39;ve not yet entered the First Visit 
            record, please continue.
            <asp:Button ID="txtBtnContinue" CssClass="saveBtn" runat="server" Text="Continue" />
            </p>
        </asp:Panel>--%>
     <p>   
     <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
            ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
            SelectCommand="SELECT [mID], [mDesc] FROM [tbl_RT_Modality] ORDER BY [mID]">
        </asp:SqlDataSource>
        <asp:SqlDataSource ID="SqlDataSource2" runat="server" 
            ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
            SelectCommand="SELECT [imID], [imDesc] FROM [tbl_ImmunoSupp] ORDER BY [imDesc] ASC">
        </asp:SqlDataSource>
	
	<asp:SqlDataSource ID="SqlDataSource3" runat="server" 
        ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
        SelectCommand="SELECT [mID], [mType] FROM [tbl_RRT_MODALITY] WHERE (([mID] >= @mID) AND ([mID] <= @mID2))">
        <SelectParameters>
            <asp:Parameter DefaultValue="1" Name="mID" Type="Int32" />
            <asp:Parameter DefaultValue="19" Name="mID2" Type="Int32" />
        </SelectParameters>
	</asp:SqlDataSource>
	
			
			<asp:SqlDataSource ID="SqlDataSource4" runat="server" 
        ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
        SelectCommand="SELECT [mID], [mType] FROM [tbl_RRT_MODALITY] WHERE (([mID] &gt;= @mID) AND ([mID] &lt;= @mID2) AND FIRST_FLAG = false)">
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
                DeleteCommand="DELETE FROM [tbl_RRT_TREATMENT] WHERE [tID] = @tID"

            SelectCommand="SELECT tID, tbl_RRT_TREATMENT.DATE_START, tbl_RRT_TREATMENT.DATE_STOP, tbl_RRT_MODALITY.mType, tbl_RRT_TREATMENT.RADAR_NO, tbl_RRT_TREATMENT.MODALITY, tbl_RRT_TREATMENT.FIRST_FLAG FROM tbl_RRT_MODALITY INNER JOIN tbl_RRT_TREATMENT ON tbl_RRT_MODALITY.mID = tbl_RRT_TREATMENT.MODALITY WHERE (tbl_RRT_TREATMENT.RADAR_NO = @RADAR_NO) AND (tbl_RRT_TREATMENT.MODALITY &gt;= 1) AND (tbl_RRT_TREATMENT.MODALITY &lt;= 9) ORDER BY tbl_RRT_TREATMENT.DATE_START DESC">
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
        DeleteCommand="DELETE FROM [tbl_RRT_PLASMA] WHERE [plID] = @plID">
        <SelectParameters>
            <asp:SessionParameter Name="RADAR_NO" SessionField="patientID" Type="Int32" />
        </SelectParameters>
         <DeleteParameters>
                <asp:Parameter Name="plID" Type="Int32" />
            </DeleteParameters>
    </asp:SqlDataSource>
    <asp:SqlDataSource ID="SqlDataSource9" runat="server" 
                ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
                SelectCommand="SELECT [tID], [DATE_TREAT], [SEQ_NO] FROM [tbl_Therapy] WHERE (([SEQ_NO] >= @SEQ_NO) AND ([RADAR_NO] = @RADAR_NO)) ORDER BY [DATE_TREAT] DESC">
                <SelectParameters>
                    <asp:Parameter DefaultValue="0" Name="SEQ_NO" Type="Int32" />
                    <asp:SessionParameter Name="RADAR_NO" SessionField="patientID" Type="Int32" />
                </SelectParameters>
            </asp:SqlDataSource>
                <asp:SqlDataSource ID="SqlDataSource10" runat="server" 
                    ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
                    
                    
             SelectCommand="SELECT [exID], [exDesc] FROM [tbl_RRT_PLASMA_LU] ORDER BY [exID]">
                </asp:SqlDataSource>
    </p>
    </div>
    <br />
    <asp:Label ID="lblPage" runat="server"></asp:Label>
</asp:Content>

