 <%@ Page Title="" Language="VB" MasterPageFile="~/MasterPage.master" AutoEventWireup="false" CodeFile="pathology.aspx.vb" Inherits="pathology" %>
<%@ Register assembly="AjaxControlToolkit" namespace="AjaxControlToolkit" tagprefix="cc1" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
    <link href="tabs_lab.css" rel="stylesheet" type="text/css" />
<link href="tabs.css" rel="stylesheet" type="text/css" />
<script src="jquery/jquery-1.3.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="jquery-impromptu.2.7.min.js"></script>
   <link href="lightboxfiles/lightbox.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="lightboxfiles/lightbox.js"></script>
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
.style2 {
	background-color: #B6DF9F;
	text-align: right;
	font-weight:bold;
}
    
.btmBorder {
border-bottom: 1px green solid;
height:30px;
vertical-align:middle;
}

 .style3
    {
        width: 100%;
        border-collapse: collapse;
        
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
    <li><asp:hyperlink runat="server" ID="lnkPathology" NavigateUrl="#" Tootip="Pathology"><span class="hovered">Pathology</span></asp:hyperlink></li> 
    <li><asp:hyperlink runat="server" ID="lnkRelapse" NavigateUrl="relapse.aspx" Tooltip="Relapse"><span>Relapse</span></asp:hyperlink></li> 
    <li><asp:hyperlink runat="server" ID="lnkHospital" NavigateUrl="hospitalisation.aspx" Tooltip="Hospitalisation"><span>Hospitalisation</span></asp:hyperlink></li> 
    <li><asp:hyperlink runat="server" ID="lnkTimeLines" NavigateUrl="timelines.aspx" Tootip="Timelines"><span >Timelines</span></asp:hyperlink></li> 
</ul> 
</div> 
<div id="mainBlock">
<%--<div id="tabsLab" style="margin:-20px 0 0 -40px;">
<ul>
	<li><asp:hyperlink runat="server" ID="lnkFollowupClinical" NavigateUrl="followup-clinical.aspx" Tooltip="Clinical Picture"><span>Clinical Picture</span></asp:hyperlink></li> 
    <li><asp:hyperlink runat="server" ID="lnkFollowupLab" NavigateUrl="followup-lab.aspx" ToolTip="Lab Results"><span>Lab Results</span></asp:hyperlink></li>
    <li><asp:hyperlink runat="server" ID="lnkFollowupTreatment" NavigateUrl="followup-treatment.aspx" ToolTip="Disease Treatment"><span>Disease Treatment</span></asp:hyperlink></li>
    <li><asp:hyperlink runat="server" ID="lnkTherapy" NavigateUrl="rrt_therapy.aspx" Tooltip="Therapy"><span >RRT Therapy</span></asp:hyperlink></li> 
    <li><asp:hyperlink runat="server" ID="lnkTimelines" NavigateUrl="timelines.aspx" Tootip="Timelines"><span>Timelines</span></asp:hyperlink></li> 
</ul>
</div>
<div id="subBlock">--%>
<table cellpadding="5" style="width: 100%; background-color: #B6DF9F;">
	<tr>
        
		<td class="btmBorder">
            <asp:DropDownList ID="DropDownListBXDate" runat="server" 
                 AppendDataBoundItems="True" 
                DataTextFormatString="{0:d}" AutoPostBack="True" 
                DataSourceID="SqlDataSource1" DataTextField="BX_DATE" DataValueField="SEQ_NO" >
                <asp:ListItem Value="" Text="Select"></asp:ListItem>
            </asp:DropDownList>
            
            
            
&nbsp;Select previous record by date&nbsp;
            <asp:Button ID="btnAdd" runat="server" Text="Add new record" CausesValidation="false" />
        </td>
		<td class="btmBorder">&nbsp;</td>
		<td class="btmBorder" style="width:120px">
            <strong>Pathology</strong></td>
	</tr>
	</table>
	<asp:Panel ID="pnlAdd" runat="server" Visible="false">
		<table cellpadding="5" style="width: 100%; background-color: #B6DF9F;">

	<tr>
        
		<td><b>Biopsy Record</b></td>
		<td class="style2"><strong>RADAR No.</strong></td>
		<td class="style2" style="width:120px">
            <asp:TextBox ID="txtRADAR_NO" runat="server" ReadOnly="True" Width="100px"></asp:TextBox>
									</td>
	</tr>
	<tr>
		<td><b>Name:</b> 
		<asp:Label id="lblFirstName" runat="server" Text="" >&nbsp;</asp:Label>&nbsp;<asp:Label ID="lblSurname" runat="server" Width="120px"></asp:Label>
            &nbsp;&nbsp;<b>DoB:</b>&nbsp;<asp:Label ID="lblDOB" runat="server"></asp:Label>
		</td>
		<td class="style2"><strong>Hospital No.</strong></td>
		<td class="style2">
            <asp:TextBox ID="txtHOSP_NO" runat="server" ReadOnly="True" Width="100px"></asp:TextBox>
									</td>
	</tr>
	<tr>
		<td>Date of Biopsy 
            <asp:TextBox ID="txtBX_DATE" runat="server" Width="70px"></asp:TextBox>
           
            <cc1:CalendarExtender ID="txtBX_DATE_CalendarExtender" runat="server" 
                Enabled="True" TargetControlID="txtBX_DATE" Format="dd-MM-yyyy" 
                PopupButtonID="ImageButton1">
            </cc1:CalendarExtender><asp:ImageButton ID="ImageButton1" runat="server" CausesValidation="False" ImageUrl="~/images/Calendar_scheduleHS.png"  />
           
            <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" 
                ControlToValidate="txtBX_DATE" ErrorMessage="*" Font-Bold="True" 
                SetFocusOnError="true"></asp:RequiredFieldValidator>&nbsp;<asp:CompareValidator 
                ID="CompareValidator1" runat="server" ControlToCompare="txtDateToday" 
                ControlToValidate="txtBX_DATE" Display="Dynamic" 
                ErrorMessage="&lt;=Today" Operator="LessThanEqual" Type="Date" 
                SetFocusOnError="True">Date of Biopsy</asp:CompareValidator>
&nbsp;<asp:TextBox ID="txtDateToday" runat="server" BorderColor="#b6df9f" BackColor="#b6df9f" ForeColor="#b6df9f"></asp:TextBox>
            &nbsp;<asp:Label ID="lblPid" runat="server" BackColor="#B6DF9F" 
                ForeColor="#B6DF9F"></asp:Label>
&nbsp;<asp:TextBox ID="txtSEQ_NO" runat="server" BorderColor="#b6df9f" BackColor="#b6df9f" 
                ForeColor="#B6DF9F"></asp:TextBox>
        </td>
		<td class="style2"><strong>Diagnosis</strong></td>
		<td class="style2">
            <asp:TextBox ID="txtDIAGNOSIS" runat="server" ReadOnly="True" 
                BackColor="#FFFF99" Width="100px"></asp:TextBox>
									</td>
	</tr>
</table>
<table style="width:100%; background-color:#EEF6ED; font-size:85%; table-layout:fixed" cellpadding="5" cellspacing="0" id="tblMain" runat="server">
<tr>
<td colspan="3" style="height:40px;">
    <asp:Button ID="btnSaveNew2" runat="server" CssClass="saveBtn" Text="Save" />
    &nbsp;
    <asp:Button ID="btnUpdate2" runat="server" CssClass="saveBtn" Text="Update" />
    &nbsp;
    <asp:Label ID="lblUpdate2" runat="server"></asp:Label>
    </td>
</tr>
<tr>
<td style="width:33%">
<br />
<fieldset>
    <table class="style3" cellpadding="5" cellspacing="0" id="tblSample">
    
        <tr>
            <td>
                <b>Sample</b></td>
            <td>
                &nbsp;</td>
        </tr>
        <tr>
            <td colspan="2">
                <asp:RadioButton ID="radNat" runat="server" GroupName="radNatTx" 
                    Text="Native" />
                &nbsp;
                <asp:RadioButton ID="radTX" runat="server" GroupName="radNatTx" 
                    Text="Tx Kidney" />
            </td>
        </tr>
        <tr>
            <td>
                Side:&nbsp;
                <asp:RadioButton ID="radLeft" runat="server" GroupName="radSide" Text="Left" />
                &nbsp;
                <asp:RadioButton ID="radRight" runat="server" GroupName="radSide" 
                    Text="Right" />
            </td>
            <td>
                &nbsp;</td>
        </tr>
        <tr>
            <td>
                <asp:Label ID="lblLabNo" runat="server" Text="Lab No. of sample"></asp:Label>
                &nbsp;<asp:TextBox ID="txtSAMPLE_LAB_NO" runat="server" BorderWidth="1px"></asp:TextBox>
            </td>
            <td>
                &nbsp;</td>
        </tr>
        </table>
        </fieldset>
        <br />
        <fieldset>
      <table class="style3" cellpadding="5" cellspacing="0">
        <tr>
            <td>
                &nbsp;</td>
            <td>
                &nbsp;</td>
        </tr>
        <tr>
            <td colspan="2">
                <asp:Label ID="lblInt" runat="server" AssociatedControlID="txtINTER_INFLAM_INFIL" 
                    Text="Interstital inflamatory infiltrate"></asp:Label>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <asp:TextBox ID="txtINTER_INFLAM_INFIL" runat="server" MaxLength="250" Rows="3" 
                    TextMode="MultiLine" Width="100%"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td>
                &nbsp;</td>
            <td>
                &nbsp;</td>
        </tr>
        <tr>
            <td colspan="2">
                <asp:Label ID="lblArterial" runat="server" AssociatedControlID="txtART_ABNORMAL" 
                    Text="Arterial &amp; arteriolar abnormalities"></asp:Label>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <asp:TextBox ID="txtART_ABNORMAL" runat="server" MaxLength="250" Rows="3" 
                    TextMode="MultiLine" Width="100%"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td>
                &nbsp;</td>
            <td>
                &nbsp;</td>
        </tr>
        <tr>
            <td colspan="2">
                <asp:Label ID="lblImmunoFind" runat="server" AssociatedControlID="txtIMM_HIST_FIND" 
                    Text="Immunohistological findings"></asp:Label>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <asp:TextBox ID="txtIMM_HIST_FIND" runat="server" MaxLength="250" Rows="3" 
                    TextMode="MultiLine" Width="100%"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td>
                &nbsp;</td>
            <td>
                &nbsp;</td>
        </tr>
        <tr>
            <td colspan="2">
                <asp:Label ID="lblElectron" runat="server" AssociatedControlID="txtELECT_MSCOPE_FIND" 
                    Text="Electron microscopic findings"></asp:Label>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <asp:TextBox ID="txtELECT_MSCOPE_FIND" runat="server" Rows="3" TextMode="MultiLine" 
                    Width="100%"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td>
                &nbsp;</td>
            <td>
                &nbsp;</td>
        </tr>
        <tr>
            <td>
                
            </td>
            <td>
                &nbsp;</td>
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
    </fieldset>
     <table cellpadding="5" class="style3">
     <tr><td>&nbsp;</td></tr>
     <tr>
     <td><asp:Button ID="btnSaveNew" runat="server" Text="Save" CssClass="saveBtn" />
                <asp:Button ID="btnUpdate" runat="server" Text="Update" 
             CssClass="saveBtn" /><asp:Label ID="lblUpdate" runat="server"></asp:Label></td>
     </tr>
     </table>
     
    </td>
<td style="width:33%; vertical-align:top;">
<br />
<fieldset>
    <table cellpadding="5" class="style3">
        <tr>
            <td>
                <b>Diagnosis</b></td>
            <td>
                &nbsp;</td>
        </tr>
        </table>
        </fieldset>
        <br />
        <fieldset>
        <table cellpadding="5" class="style3">
        <tr>
            <td>
                <b>Tubules</b></td>
            <td>
                &nbsp;</td>
        </tr>
        <tr><td colspan="2">Extent of altrophy + internal fibrosis</td></tr>
        <tr>
            <td>
                <asp:Label ID="lblEstimated" runat="server" AssociatedControlID="txtEstimated" 
                    Text="Estimated %"></asp:Label>
            </td>
            <td>
                <asp:TextBox ID="txtEstimated" runat="server" Width="80px"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td bgcolor="#E7E78B">
                <asp:Label ID="lblMeasured" runat="server" AssociatedControlID="txtMeasured" 
                    Text="Measured %"></asp:Label>
            </td>
            <td bgcolor="#E7E78B">
                <asp:TextBox ID="txtMeasured" runat="server" Width="80px"></asp:TextBox>
                <cc1:MaskedEditExtender ID="txtMeasured_MaskedEditExtender" runat="server" 
                    CultureAMPMPlaceholder="" CultureCurrencySymbolPlaceholder="" 
                    CultureDateFormat="" CultureDatePlaceholder="" CultureDecimalPlaceholder="" 
                    CultureThousandsPlaceholder="" CultureTimePlaceholder="" Enabled="True" 
                    TargetControlID="txtMeasured" Mask="99.9" MaskType="Number">
                </cc1:MaskedEditExtender>
            </td>
        </tr>
        <tr>
            <td>
                &nbsp;</td>
            <td>
                &nbsp;</td>
        </tr>
        <tr>
            <td>
                <asp:Label ID="lblOther" runat="server" Text="Other feature" 
                    AssociatedControlID="txtTUB_OTHER_FEAT"></asp:Label>
            </td>
            <td>
                &nbsp;</td>
        </tr>
        <tr>
            <td colspan="2">
                <asp:TextBox ID="txtTUB_OTHER_FEAT" runat="server" Rows="3" TextMode="MultiLine" 
                    Width="100%"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td>
                &nbsp;</td>
            <td>
                &nbsp;</td>
        </tr>
        </table>
        </fieldset>
        <br />
        <fieldset>
        <table cellpadding="5" class="style3">
        <tr>
            <td>
                <asp:Label ID="lblImages" runat="server" Font-Bold="True" Text="Images"></asp:Label>
                &nbsp;enter the full url, inc http://</td>
            <td>
                &nbsp;</td>
        </tr>
        <tr>
            <td colspan="2">
                <a ID="img1" runat="server" target="_blank">Image 1</a><br />
                <asp:Label ID="lblImg1" runat="server" Text="1"></asp:Label>
                &nbsp;<asp:TextBox ID="txtImage1" runat="server" Width="210px"></asp:TextBox>
                <br />
            </td>
        </tr>
        <tr>
            <td colspan="2">
             <a ID="img2" runat="server" target="_blank">Image 2</a><br />
                <asp:Label ID="lblImg2" runat="server" Text="2"></asp:Label>
                &nbsp;<asp:TextBox ID="txtImage2" runat="server" Width="210px"></asp:TextBox>
                <br />
            </td>
        </tr>
       
        <tr>
            <td colspan="2">
             <a ID="img3" runat="server" target="_blank">Image 3</a><br />
                <asp:Label ID="lblImg3" runat="server" Text="3"></asp:Label>
                &nbsp;<asp:TextBox ID="txtImage3" runat="server" Width="210px"></asp:TextBox>
                <br />
            </td>
        </tr>
       
        <tr>
            <td colspan="2">
             <a ID="img4" runat="server" target="_blank">Image 4</a><br />
                <asp:Label ID="lblImg4" runat="server" Text="4"></asp:Label>
                &nbsp;<asp:TextBox ID="txtImage4" runat="server" Width="210px"></asp:TextBox>
                <br />
            </td>
        </tr>
       
        <tr>
            <td colspan="2">
             <a ID="img5" runat="server" target="_blank">Image 5</a><br />
                <asp:Label ID="lblImg5" runat="server" Text="5"></asp:Label>
                &nbsp;<asp:TextBox ID="txtImage5" runat="server" Width="210px"></asp:TextBox>
                <br />
            </td>
        </tr>
    </table>
    </fieldset>
    <p>
        <asp:Label ID="lblUploadWarn" runat="server" ForeColor="#EEF6ED" 
            Text="Uploading images, please wait ....." Font-Bold="True"></asp:Label>
    </p>
    </td>
<td valign="top">
<br />
<fieldset>
    <table cellpadding="5" class="style3">
        <tr>
            <td>
                <b>Glomeruli</b></td>
            <td>
                &nbsp;</td>
        </tr>
        <tr>
            <td>
                Total No
                <asp:RangeValidator ID="RangeValidator1" runat="server" 
                    ControlToValidate="txtGLOM_TOTAL_NO" ErrorMessage="0-150" MaximumValue="150" 
                    MinimumValue="0" Type="Integer"></asp:RangeValidator>
            </td>
            <td>
                <asp:TextBox ID="txtGLOM_TOTAL_NO" runat="server" Width="50px" MaxLength="3" 
                    ToolTip="0-150"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td>
                No globally Sclerosed&nbsp;
                <asp:RangeValidator ID="RangeValidator2" runat="server" 
                    ControlToValidate="txtGLOM_GLOB_SCL" ErrorMessage="0-150" MaximumValue="150" 
                    MinimumValue="0" Type="Integer"></asp:RangeValidator>
            </td>
            <td>
                <asp:TextBox ID="txtGLOM_GLOB_SCL" runat="server" Width="50px" MaxLength="3" 
                    ToolTip="0-150"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td>
                No segmentally sclerosed&nbsp;
                <asp:RangeValidator ID="RangeValidator3" runat="server" 
                    ControlToValidate="txtGLOM_SEQ_SCL" Display="Dynamic" ErrorMessage="0-150" 
                    MaximumValue="150" MinimumValue="0" Type="Integer"></asp:RangeValidator>
            </td>
            <td>
                <asp:TextBox ID="txtGLOM_SEQ_SCL" runat="server" Width="50px" MaxLength="3" 
                    ToolTip="0-150"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td>
                No with cellular/fibrocellular crescents<br />
&nbsp;<asp:RangeValidator ID="RangeValidator4" runat="server" 
                    ControlToValidate="txtGLOM_CELL_CRES" Display="Dynamic" ErrorMessage="0-150" 
                    MaximumValue="150" MinimumValue="0" Type="Integer"></asp:RangeValidator>
            </td>
            <td>
                <asp:TextBox ID="txtGLOM_CELL_CRES" runat="server" Width="50px" MaxLength="3" 
                    ToolTip="0-150"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td>
                No with fibrous crescents&nbsp;
                <asp:RangeValidator ID="RangeValidator5" runat="server" 
                    ControlToValidate="txtGLOM_FIB_CRES" ErrorMessage="0-150" MaximumValue="150" 
                    MinimumValue="0" Type="Integer"></asp:RangeValidator>
            </td>
            <td>
                <asp:TextBox ID="txtGLOM_FIB_CRES" runat="server" Width="50px" MaxLength="3" 
                    ToolTip="0-150"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td>
                No with endocapillary hypercellularity<br />
&nbsp;<asp:RangeValidator ID="RangeValidator6" runat="server" 
                    ControlToValidate="txtGLOM_END_HYPER" Display="Dynamic" ErrorMessage="0-150" 
                    MaximumValue="150" MinimumValue="0" Type="Integer"></asp:RangeValidator>
            </td>
            <td>
                <asp:TextBox ID="txtGLOM_END_HYPER" runat="server" Width="50px" MaxLength="3" 
                    ToolTip="0-150"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td>
                No with fibrinoid necrosis&nbsp;
                <asp:RangeValidator ID="RangeValidator7" runat="server" 
                    ControlToValidate="txtGLOM_FIN_NEC" ErrorMessage="1-150" MaximumValue="150" 
                    MinimumValue="0" Type="Integer"></asp:RangeValidator>
            </td>
            <td>
                <asp:TextBox ID="txtGLOM_FIN_NEC" runat="server" Width="50px" MaxLength="3" 
                    ToolTip="0-150"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td>
                <asp:Label ID="lblGLOM_OTHER" runat="server" 
                    AssociatedControlID="txtGLOM_ANY_OTH_FEAT" Text="Other feature"></asp:Label>
            </td>
            <td>
                &nbsp;</td>
        </tr>
        <tr>
            <td colspan="2">
                <asp:TextBox ID="txtGLOM_ANY_OTH_FEAT" runat="server" MaxLength="150" Rows="3" 
                    TextMode="MultiLine" Width="100%"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td>
                &nbsp;</td>
            <td>
                &nbsp;</td>
        </tr>
        </table>
        </fieldset>
        <br />
        <fieldset>
         <table cellpadding="5" class="style3">
        
        <tr>
            <td>
                <asp:Label ID="lblPATH_TEXT" runat="server" Text="Histological Summary"></asp:Label>
            </td>
            <td>
                &nbsp;</td>
        </tr>
        <tr>
            <td colspan="2">
                <asp:TextBox ID="txtPATH_TEXT" runat="server" MaxLength="500" Rows="15" 
                    TextMode="MultiLine" Width="100%"></asp:TextBox>
            </td>
        </tr>
    </table>
    </fieldset>
    </td>
</tr>
</table>
	</asp:Panel>
	<p><asp:Label ID="lblDebug" runat="server"></asp:Label>&nbsp;</p>
	<asp:ValidationSummary ID="ValidationSummary1" runat="server" 
        HeaderText="One or more fields are missing " ShowMessageBox="True" 
        ShowSummary="False" /><br />
<asp:Label ID="lblPage" runat="server"></asp:Label>
	</div>
	
	<p>&nbsp;</p>
	<asp:SqlDataSource ID="SqlDataSource1" runat="server" 
                ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
                
        SelectCommand="SELECT [BX_DATE], [SEQ_NO] FROM [tbl_Pathology] WHERE ([RADAR_NO] = @RADAR_NO) ORDER BY [BX_DATE] DESC">
        <SelectParameters>
            <asp:SessionParameter Name="RADAR_NO" SessionField="patientID" Type="Int32" />
        </SelectParameters>
            </asp:SqlDataSource>
</asp:Content>

