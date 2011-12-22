<%@ Page Title="Renal RADAR - Lab Results" Language="VB" MasterPageFile="~/MasterPage.master" AutoEventWireup="false" CodeFile="first-lab.aspx.vb" Inherits="first_lab" %>
<%@ Register assembly="DevExpress.Web.ASPxEditors.v9.2, Version=9.2.4.0, Culture=neutral, PublicKeyToken=b88d1754d700e49a" namespace="DevExpress.Web.ASPxEditors" tagprefix="dxe" %>
<%@ Register Assembly="DevExpress.Web.v9.2, Version=9.2.4.0, Culture=neutral, PublicKeyToken=b88d1754d700e49a"
    Namespace="DevExpress.Web.ASPxPopupControl" TagPrefix="dxpc" %>

<%@ Register assembly="AjaxControlToolkit" namespace="AjaxControlToolkit" tagprefix="cc1" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
    <link href="tabs_lab.css" rel="stylesheet" type="text/css" />
<link href="tabs.css" rel="stylesheet" type="text/css" />
<style type="text/css">
input, select, textarea {
border: 1px solid gray;
padding-left:3px;
}

select, input, textarea {
font-size: 95%;
color: #2D2E2E;
}

.style1 {
border-collapse: collapse;
}

.style2 {
width: 200px;
}

#tblHeader {
background-color: #b6df9f;
}

#tblMain {
background-color:#EEF6ED;
}

#tblMain td {
vertical-align:top;
height:25px;
}

#mainBlock table {
	margin: 0;
}
.indent {
	padding-left: 50px;
}
.noborder {
	border: 0px;
}

fieldset {
border: 1px solid green;
padding:5px;
}

 .style5
    {
        width: 100px;
    }
    /*.style7
    {
        width: 200px;
    }*/
    .style7
    {
        border-collapse: collapse;
        width: 100%;
        table-layout: auto;
        margin-left: 20px;
        margin-top: 30px;
    }
    
    #ctl00_ContentPlaceHolder1_rowNitrite input, #ctl00_ContentPlaceHolder1_rowDNAFactorH input {
    border:0;
    }
    
    #ctl00_ContentPlaceHolder1_radLeucoYes, #ctl00_ContentPlaceHolder1_radLeucoNo, #ctl00_ContentPlaceHolder1_radLeucoND {
    border:0;
    }
     #ctl00_ContentPlaceHolder1_radGlucYes, #ctl00_ContentPlaceHolder1_radGlucNo, #ctl00_ContentPlaceHolder1_radGlucND {
    border:0;
    }
     #ctl00_ContentPlaceHolder1_radAntiBYes, #ctl00_ContentPlaceHolder1_radAntiBNo, #ctl00_ContentPlaceHolder1_radAntiBND {
    border:0;
    }
    
    #ctl00_ContentPlaceHolder1_chkCMV_SYM, #ctl00_ContentPlaceHolder1_chkOTHER_INFECT, #ctl00_ContentPlaceHolder1_chkBACT_URINE, #ctl00_ContentPlaceHolder1_chkCompOther {
    border:0;
    }
 </style>

<!--[if IE]>
<style type="text/css">
#mainBlock {
margin-top:0;
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
<li><asp:hyperlink runat="server" ID="lnkFirstClinical" NavigateUrl="first-clinical.aspx" Tooltip="Clinical Picture"><span >Clinical Picture</span></asp:hyperlink></span></li> 
    <li><asp:hyperlink runat="server" ID="lnkFirstLab" NavigateUrl="#" Tootip="Laboratory Results"><span class="hovered">Laboratory Results</span></asp:hyperlink></li> 
    <li><asp:hyperlink runat="server" ID="lnkFirstTreatment" NavigateUrl="first-treatment.aspx" Tooltip="Treatment"><span>Treatment</span></asp:hyperlink></span></li> 

</ul>
</div>
<div id="subBlock">
   
    <table cellpadding="4" style="width: 100%;" id="tblHeader">
	<tr>
		<td align="left">
            <asp:Label ID="lblTitle" runat="server" Font-Bold="True"></asp:Label>
        </td>
		<td style="text-align:right; width:100px;">RADAR No.</td>
		<td style="width:120px;">
            <asp:TextBox ID="txtRADAR_NO" runat="server" ReadOnly="True" Width="100px"></asp:TextBox>
        </td>
	</tr>
	<tr>
		<td align="left">Name
		<asp:textbox id="txtFNAME" runat="server" ReadOnly="True" Width="100px"></asp:textbox>
		&nbsp;<asp:textbox id="txtSNAME" runat="server" ReadOnly="True" Width="120px"></asp:textbox>
		&nbsp;DoB
		<asp:textbox id="txtDOB" runat="server" ReadOnly="True" Width="80px"></asp:textbox>
		    <asp:Label ID="lblLabID" runat="server" Text="" Visible="false"></asp:Label>
		</td>
		<td style="text-align:right; " class="style5">Hospital No.</td>
		<td>
            <asp:TextBox ID="txtHOSP_NO" runat="server" ReadOnly="True" Width="100px"></asp:TextBox>
        </td>
	</tr>
	<tr>
		<td style="text-align:left;" >Date of LAB Results&nbsp;<asp:TextBox ID="txtDateLab" 
                runat="server" Width="70px"></asp:TextBox>
            <cc1:CalendarExtender ID="txtDateLab_CalendarExtender" runat="server" 
                Enabled="True" TargetControlID="txtDateLab" PopupButtonID="ImageButton1" Format="dd-MM-yyyy">
            </cc1:CalendarExtender>&nbsp;<asp:ImageButton ID="ImageButton1" runat="server" CausesValidation="False" ImageUrl="~/images/Calendar_scheduleHS.png"  />
            &nbsp;<asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" 
                ControlToValidate="txtDateLab">*</asp:RequiredFieldValidator>
        </td>
		<td style="text-align:right; " class="style5">Diagnosis</td>
		<td>
            <asp:TextBox ID="txtDIAG" runat="server" ReadOnly="True" BackColor="#FFFF99" 
                Width="100px"></asp:TextBox>
        </td>
	</tr>
</table>

<table cellpadding="4" cellspacing="10" class="style1" style="width: 100%; background-color:#eef6ed" id="tblMain" runat="server">
	<tr>
    <td colspan="3"><asp:Button Text="Save" ID="btnSave2" runat="server" CssClass="saveBtn" />&nbsp;<asp:Label 
            ID="lblUpdate2" runat="server" Text=""></asp:Label>
        </td>
    </tr>
    <tr>
		<td valign="top" style="width:25%">
		<fieldset ><legend style="font-weight:bold;">Bloods</legend>
		<table cellpadding="4" class="style1" style="width: 100%; margin-top:15px;">
			
			<tr>
				<td>Hb g/dL<br />
                    <asp:RangeValidator ID="RangeValidator13" runat="server" 
                        ControlToValidate="txtHB" ErrorMessage="2.0-20" MaximumValue="20.0" 
                        MinimumValue="2.0" Type="Double"></asp:RangeValidator>
                </td>
				<td>
				    <asp:TextBox ID="txtHB" runat="server" MaxLength="4" ToolTip="2.0-20" 
                        Width="50px"></asp:TextBox>
                    <cc1:FilteredTextBoxExtender ID="txtHB_FilteredTextBoxExtender" runat="server" 
                        Enabled="True" TargetControlID="txtHB" ValidChars=".0123456789">
                    </cc1:FilteredTextBoxExtender>
				   
				</td>
			</tr>
			<tr>
                <td>
                    WBC 10<sup>9</sup>/L<br />
                    <asp:RangeValidator ID="RangeValidator9" runat="server" 
                        ControlToValidate="txtWBC" ErrorMessage="0.1-30" MaximumValue="30" 
                        MinimumValue="0.1" SetFocusOnError="True" Type="Double"></asp:RangeValidator>
                </td>
                <td>
                    <asp:TextBox ID="txtWBC" runat="server" MaxLength="4" ToolTip="0.1-30" 
                        Width="50px"></asp:TextBox>
                    <cc1:FilteredTextBoxExtender ID="txtWBC_FilteredTextBoxExtender" runat="server" 
                        Enabled="True" TargetControlID="txtWBC" ValidChars=".0123456789">
                    </cc1:FilteredTextBoxExtender>
                </td>
            </tr>
            <tr>
                <td>
                    Neutrophils 10<sup>9</sup>/L<br />
                    <asp:RangeValidator ID="RangeValidator11" runat="server" 
                        ControlToValidate="txtNEUTRO" ErrorMessage="0.1-80" MaximumValue="80" 
                        MinimumValue="0.1" SetFocusOnError="True" Type="Double"></asp:RangeValidator>
                </td>
                <td>
                    <asp:TextBox ID="txtNEUTRO" runat="server" MaxLength="4" ToolTip="0.1-80" 
                        Width="50px"></asp:TextBox>
                    <cc1:FilteredTextBoxExtender ID="txtNEUTRO_FilteredTextBoxExtender" 
                        runat="server" Enabled="True" TargetControlID="txtNEUTRO" 
                        ValidChars=".0123456789">
                    </cc1:FilteredTextBoxExtender>
                </td>
            </tr>
            <tr>
                <td>
                    Platelets 10<sup>9</sup>/L<br />
                    <asp:RangeValidator ID="RangeValidator12" runat="server" 
                        ControlToValidate="txtPlatelets" ErrorMessage="1-800" MaximumValue="800" 
                        MinimumValue="1" SetFocusOnError="True" Type="Integer"></asp:RangeValidator>
                </td>
                <td>
                    <asp:TextBox ID="txtPLATELETS" runat="server" MaxLength="3" ToolTip="1-800" 
                        Width="50px"></asp:TextBox>
                    <cc1:FilteredTextBoxExtender ID="txtPLATELETS_FilteredTextBoxExtender" 
                        runat="server" Enabled="True" TargetControlID="txtPLATELETS" 
                        ValidChars="0123456789">
                    </cc1:FilteredTextBoxExtender>
                </td>
            </tr>
            <tr>
                <td>
                    Sodium Na mmol/L<br />
                    <asp:RangeValidator ID="RangeValidator4" runat="server" 
                        ControlToValidate="txtSodium" ErrorMessage="90-180" MaximumValue="180" 
                        MinimumValue="90" SetFocusOnError="True" Type="Integer"></asp:RangeValidator>
                </td>
                <td>
                    <asp:TextBox ID="txtSODIUM" runat="server" MaxLength="3" ToolTip="90-180" 
                        Width="50px"></asp:TextBox>
                    <cc1:FilteredTextBoxExtender ID="txtSODIUM_FilteredTextBoxExtender" 
                        runat="server" Enabled="True" TargetControlID="txtSODIUM" 
                        ValidChars="0123456789">
                    </cc1:FilteredTextBoxExtender>
                </td>
            </tr>
            <tr>
                <td>
                    Potassium K mmol/L<br />
                    <asp:RangeValidator ID="RangeValidator5" runat="server" 
                        ControlToValidate="txtPotassium" ErrorMessage="1.0-9.9" MaximumValue="9.9" 
                        MinimumValue="1.0" SetFocusOnError="True" Type="Double"></asp:RangeValidator>
                </td>
                <td>
                    <asp:TextBox ID="txtPOTASSIUM" runat="server" MaxLength="3" ToolTip="1.0-9.9" 
                        Width="50px"></asp:TextBox>
                    <cc1:FilteredTextBoxExtender ID="txtPOTASSIUM_FilteredTextBoxExtender" 
                        runat="server" Enabled="True" TargetControlID="txtPOTASSIUM" 
                        ValidChars=".1234567890">
                    </cc1:FilteredTextBoxExtender>
                </td>
            </tr>
            <tr>
                <td>
                    BUN Blood Urea mmol/L<br />
                    <asp:RangeValidator ID="RangeValidator3" runat="server" 
                        ControlToValidate="txtUREA_SER" ErrorMessage="1.0-50.0" MaximumValue="50.0" 
                        MinimumValue="1.0" SetFocusOnError="True" Type="Double"></asp:RangeValidator>
                </td>
                <td>
                    <asp:TextBox ID="txtUREA_SER" runat="server" MaxLength="4" ToolTip="1-50" 
                        Width="50px"></asp:TextBox>
                </td>
            </tr>
            <tr>
                <td>
                    Serum Creatinine umol/L<br />
                    <asp:RangeValidator ID="RangeValidator1" runat="server" 
                        ControlToValidate="txtCreatSer" ErrorMessage="10-2800" MaximumValue="2800" 
                        MinimumValue="10" SetFocusOnError="True" Type="Integer"></asp:RangeValidator>
                </td>
                <td>
                    <asp:TextBox ID="txtCreatSer" runat="server" MaxLength="4" ToolTip="10-2800" 
                        Width="50px"></asp:TextBox>
                    <cc1:FilteredTextBoxExtender ID="txtCreatSer_FilteredTextBoxExtender" 
                        runat="server" Enabled="True" TargetControlID="txtCreatSer" 
                        ValidChars="0123456789">
                    </cc1:FilteredTextBoxExtender>
                </td>
            </tr>
			<tr>
				<td>Protein g/L<br />
                    <asp:RangeValidator ID="RangeValidator25" runat="server" 
                        ControlToValidate="txtPROTEIN" ErrorMessage="5-90" MaximumValue="90" 
                        MinimumValue="5" Type="Integer"></asp:RangeValidator>
                </td>
				<td>
				<asp:TextBox id="txtPROTEIN" runat="server" Width="50px" ToolTip="5-90" 
                        MaxLength="2"></asp:TextBox>
					
					<cc1:FilteredTextBoxExtender ID="txtPROTEIN_FilteredTextBoxExtender" 
                        runat="server" Enabled="True" TargetControlID="txtPROTEIN" 
                        ValidChars="0123456789">
                    </cc1:FilteredTextBoxExtender>
					
					</td>
			</tr>
			<tr>
				<td>Albumin g/L<br />
                    <asp:RangeValidator ID="RangeValidator2" runat="server" 
                        ControlToValidate="txtAlbumin" ErrorMessage="5-60" MaximumValue="60" 
                        MinimumValue="5" Type="Integer"></asp:RangeValidator>
                </td>
				<td>
				<asp:TextBox id="txtALBUMIN" runat="server" Width="50px" ToolTip="5-60" 
                        MaxLength="2"></asp:TextBox>
					<cc1:FilteredTextBoxExtender ID="txtALBUMIN_FilteredTextBoxExtender" 
                        runat="server" Enabled="True" TargetControlID="txtALBUMIN" 
                        ValidChars="0123456789">
                    </cc1:FilteredTextBoxExtender>
					</td>
			</tr>
			<tr>
				<td>
                    <asp:Label ID="lblCRP" runat="server" Text="CRP"></asp:Label>
                    &nbsp;mU/L<br />
                    <asp:RangeValidator ID="RangeValidator20" runat="server" 
                        controltovalidate="txtCRP" ErrorMessage="0-200" maximumvalue="200" 
                        minimumvalue="0" type="integer"></asp:RangeValidator>
                    <br />
                </td>
				<td>
				    <asp:TextBox ID="txtCRP" runat="server" MaxLength="3" ToolTip="0-200" 
                        Width="50px"></asp:TextBox>
                    <cc1:FilteredTextBoxExtender ID="txtCRP_FilteredTextBoxExtender" runat="server" 
                        Enabled="True" TargetControlID="txtCRP" ValidChars="0123456789">
                    </cc1:FilteredTextBoxExtender>
					</td>
			</tr>
			<tr>
				<td>Total Cholesterol mmol/L<br />
                    <asp:RangeValidator ID="RangeValidator14" runat="server" 
                        ControlToValidate="txtCHOL_TOTAL" ErrorMessage="1.0-30.0" MaximumValue="30.0" 
                        MinimumValue="1.0" Type="Double"></asp:RangeValidator>
                </td>
				<td>
				    <asp:TextBox ID="txtCHOL_TOTAL" runat="server" MaxLength="4" ToolTip="1.0-30.0" 
                        Width="50px"></asp:TextBox>
                    <cc1:FilteredTextBoxExtender ID="txtCHOL_TOTAL_FilteredTextBoxExtender" 
                        runat="server" Enabled="True" TargetControlID="txtCHOL_TOTAL" 
                        ValidChars=".0123456789">
                    </cc1:FilteredTextBoxExtender>
					</td>
			</tr>
			<tr id="rowHDL" runat="server">
				<td>HDL Cholesterol mmol/L<br />
                    <asp:RangeValidator ID="RangeValidator15" runat="server" 
                        ControlToValidate="txtCHOL_HDL" ErrorMessage="0.1-30.0" MaximumValue="30.0" 
                        MinimumValue="0.1" Type="Double"></asp:RangeValidator>
                    <br />
                </td>
				<td>
				    <asp:TextBox ID="txtCHOL_HDL" runat="server" MaxLength="4" ToolTip="0.1-30.0" 
                        Width="50px"></asp:TextBox>
                    <cc1:FilteredTextBoxExtender ID="txtCHOL_HDL_FilteredTextBoxExtender" 
                        runat="server" Enabled="True" TargetControlID="txtCHOL_HDL" 
                        ValidChars=".0123456789">
                    </cc1:FilteredTextBoxExtender>
					
					</td>
			</tr>
			<tr id="rowLDL" runat="server" >
				<td>LDL Cholesterol mmol/L<br />
                    <asp:RangeValidator ID="RangeValidator16" runat="server" 
                        ControlToValidate="txtCHOL_LDL" ErrorMessage="1.0-30.0" MaximumValue="30.0" 
                        MinimumValue="1.0" Type="Double"></asp:RangeValidator>
                </td>
				<td>
				<asp:TextBox id="txtCHOL_LDL" runat="server" Width="50px" ToolTip="1.0-30.0" 
                        MaxLength="4"></asp:TextBox>
					<cc1:FilteredTextBoxExtender ID="txtCHOL_LDL_FilteredTextBoxExtender" 
                        runat="server" Enabled="True" TargetControlID="txtCHOL_LDL" 
                        ValidChars=".0123456789">
                    </cc1:FilteredTextBoxExtender>
					</td>
			</tr>
			<tr >
                <td>
                    Triglycerides mmol/L<br />
                    <asp:RangeValidator ID="RangeValidator18" runat="server" 
                        ControlToValidate="txtTRIG" ErrorMessage="0.0-30.0" MaximumValue="30.0" 
                        MinimumValue="0.0" ToolTip="0.0-30.0" Type="double"></asp:RangeValidator>
                </td>
                <td>
                    <asp:TextBox ID="txtTRIG" runat="server" MaxLength="4" ToolTip="0.0-30.0" 
                        Width="50px"></asp:TextBox>
                    <cc1:FilteredTextBoxExtender ID="txtTRIG_FilteredTextBoxExtender" 
                        runat="server" Enabled="True" TargetControlID="txtTRIG" 
                        ValidChars=".0123456789">
                    </cc1:FilteredTextBoxExtender>
                </td>
            </tr>
            <tr >
                <td>
                    <asp:Label ID="lblTHYROX" runat="server" Text="Thyroxine T4 pmol/L"></asp:Label>
                    <br />
                    <asp:RangeValidator ID="RangeValidator30" runat="server" 
                        ControlToValidate="txtTHYROX" ErrorMessage="0.0-30.0" MaximumValue="30.0" 
                        MinimumValue="0.0" Type="Double"></asp:RangeValidator>
                </td>
                <td>
                    <asp:TextBox ID="txtTHYROX" runat="server" MaxLength="4" ToolTip="0.0-30.0" 
                        Width="50px"></asp:TextBox>
                </td>
            </tr>
            <tr >
                <td>
                    TSH mu/L<br />
                    <asp:RangeValidator ID="RangeValidator26" runat="server" 
                        ControlToValidate="txtTSH" ErrorMessage="0.00-50" MaximumValue="50" 
                        MinimumValue="0.00" Type="Double"></asp:RangeValidator>
                </td>
                <td>
                    <asp:TextBox ID="txtTSH" runat="server" MaxLength="5" ToolTip="0.00-50" 
                        Width="50px"></asp:TextBox>
                    <cc1:FilteredTextBoxExtender ID="txtTSH_FilteredTextBoxExtender" runat="server" 
                        Enabled="True" TargetControlID="txtTSH" ValidChars=".0123456789">
                    </cc1:FilteredTextBoxExtender>
                </td>
            </tr>
            <tr ID="rowPhosphate" runat="server">
                <td>
                    Phosphate PO4 mmol/L<br />
                    <asp:RangeValidator ID="RangeValidator6" runat="server" 
                        ControlToValidate="txtPHOS" ErrorMessage="0.10-5.60" MaximumValue="5.60" 
                        MinimumValue="0.10" SetFocusOnError="True" Type="Double"></asp:RangeValidator>
                </td>
                <td>
                    <asp:TextBox ID="txtPHOS" runat="server" MaxLength="4" ToolTip="0.10-5.6" 
                        Width="50px"></asp:TextBox>
                    <cc1:FilteredTextBoxExtender ID="txtPHOS_FilteredTextBoxExtender" 
                        runat="server" Enabled="True" TargetControlID="txtPHOS" 
                        ValidChars=".0123456789">
                    </cc1:FilteredTextBoxExtender>
                </td>
            </tr>
			<tr id="rowFerritin" runat="server">
				<td>Ferritin ug/L<br />
				    <asp:RangeValidator ID="RangeValidator17" runat="server" 
                        ControlToValidate="txtFERRITIN" ErrorMessage="1-5000" MaximumValue="5000" 
                        MinimumValue="1" Type="Integer"></asp:RangeValidator>
				</td>
				<td>
				    <asp:TextBox ID="txtFERRITIN" runat="server" MaxLength="4" ToolTip="1-5000" 
                        Width="50px"></asp:TextBox>
                    <cc1:FilteredTextBoxExtender ID="txtFERRITIN_FilteredTextBoxExtender" 
                        runat="server" Enabled="True" TargetControlID="txtFERRITIN" 
                        ValidChars="0123456789">
                    </cc1:FilteredTextBoxExtender>
					</td>
			</tr>
			<tr id="rowINR" runat="server">
				<td>INR<br />
                    <asp:RangeValidator ID="RangeValidator19" runat="server" 
                        ControlToValidate="txtINR" ErrorMessage="0.5-5.0" MaximumValue="5.0" 
                        MinimumValue="0.5" Type="Double"></asp:RangeValidator>
                    <br />
				</td>
				<td>
				    <asp:TextBox ID="txtINR" runat="server" MaxLength="3" ToolTip="0.5-5.0" 
                        Width="50px"></asp:TextBox>
                    <cc1:FilteredTextBoxExtender ID="txtINR_FilteredTextBoxExtender" runat="server" 
                        Enabled="True" TargetControlID="txtINR" ValidChars=".0123456789">
                    </cc1:FilteredTextBoxExtender>
					</td>
			</tr>
			<tr>
				<td><br />
				</td>
				<td>
				    &nbsp;</td>
			</tr>
		</table>
		</fieldset>
		</td>
		<td valign="top" style="width:41%">
		
		
		<fieldset ><legend style="font-weight:bold;">Urinalysis - Dipstick </legend>
		<table style="width: 100%; margin-top:15px;">
			
			
			<tr id="rowUrVol" runat="server">
				<td class="style2">Urine Volume Condition</td>
				<td>
				<asp:DropDownList id="DropDownList12" runat="server">
				<asp:ListItem Value="">Select</asp:ListItem>
				<asp:ListItem Value="0">Normal</asp:ListItem>
				<asp:ListItem Value="1">Anuria</asp:ListItem>
				<asp:ListItem Value="2">Oliguria</asp:ListItem>
				<asp:ListItem Value="3">Polyuria</asp:ListItem>
				
			<asp:ListItem Value="8">Not 24 Hr collection</asp:ListItem>
				</asp:DropDownList>
					</td>
			</tr>
			
			
			<tr>
				<td class="style2">Haematuria</td>
				<td>
				<asp:dropdownlist id="DropDownList16" runat="server">
					<asp:listitem Selected="True" Value="">Select</asp:listitem>
					<asp:listitem Value="9">Not Tested</asp:listitem>
					<asp:listitem Value="1">Microscopic</asp:listitem>
					<asp:listitem Value="2">Macroscopic</asp:listitem>
				</asp:dropdownlist>
					</td>
			</tr>
			<tr>
			<td>Proteinuria</td>
			<td><asp:DropDownList id="ddlProteinuria" runat="server">
				<asp:ListItem Selected="True" Value="">Select</asp:ListItem>
				<asp:ListItem Value="0">Negative</asp:ListItem>
				<asp:ListItem Value="9">Trace</asp:ListItem>
				<asp:ListItem Value="1">1+</asp:ListItem>
				<asp:ListItem Value="2">2+</asp:ListItem>
				<asp:ListItem Value="3">3+</asp:ListItem>
				<asp:ListItem Value="4">4+</asp:ListItem>
				</asp:DropDownList></td>
			</tr>
			<tr id="rowAlbuminaria" runat="server">
				<td class="style2">Albuminuria</td>
				<td>
				<asp:DropDownList id="DropDownList13" runat="server">
				<asp:ListItem Value="">Select</asp:ListItem>
				<asp:ListItem Value="0">Negative</asp:ListItem>
				<asp:ListItem Value="1">1+</asp:ListItem>
				<asp:ListItem Value="2">2+</asp:ListItem>
				<asp:ListItem Value="3">3+</asp:ListItem>
				<asp:ListItem Value="4">4+</asp:ListItem>
				</asp:DropDownList>
					</td>
			</tr>
			
			<tr>
				<td class="style2">Leucocytes in urine &gt;50</td>
				<td style="height: 26px">
				    <asp:RadioButton ID="radLeucoYes" runat="server" GroupName="radLeuco" 
                        Text="Yes" />
&nbsp;<asp:RadioButton ID="radLeucoNo" runat="server" GroupName="radLeuco" Text="No" />
&nbsp;<asp:RadioButton ID="radLeucoND" runat="server" GroupName="radLeuco" Text="ND" />
					</td>
			</tr>
			<tr id="rowNitrite" runat="server">
				<td class="style2">
				<asp:label id="lblNITRITE" runat="server" Text="Nitrite"></asp:label>
				</td>
				<td>
				    <asp:RadioButton ID="radNitriteYes" runat="server" GroupName="nitrite" 
                        Text="Yes" />
&nbsp;<asp:RadioButton ID="radNitriteNo" runat="server" GroupName="nitrite" Text="No" />
&nbsp;<asp:RadioButton ID="radNitriteND" runat="server" GroupName="nitrite" Text="ND" />
					</td>
			</tr>
			
			<tr>
				<td class="style2">Glucose in urine</td>
				<td>
				    <asp:RadioButton ID="radGlucYes" runat="server" GroupName="radGluc" 
                        Text="Yes" />
&nbsp;<asp:RadioButton ID="radGlucNo" runat="server" GroupName="radGluc" Text="No" />
&nbsp;<asp:RadioButton ID="radGlucND" runat="server" GroupName="radGluc" Text="ND" />
					</td>
			</tr>
			
			<tr>
				<td class="style2">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
		</fieldset>
		<fieldset ><legend style="font-weight:bold;">Urinalysis - Lab </legend>
		<table style="width: 100%; margin-top:15px;">
		<tr id="rowUVol24" runat="server">
				<td class="style2">Urine Volume ml/24hr<br />
				<asp:RangeValidator id="RangeValidator24" runat="server" ErrorMessage="0-4000" Type="Integer" MinimumValue="0" MaximumValue="4000" ControlToValidate="txtUR_VOL_24H"></asp:RangeValidator>
				</td>
				<td>
				<asp:TextBox id="txtUR_VOL_24H" runat="server" Width="50px" MaxLength="4" 
                        ToolTip="0-4000"></asp:TextBox>
					<cc1:FilteredTextBoxExtender ID="txtUR_VOL_24H_FilteredTextBoxExtender" 
                        runat="server" Enabled="True" TargetControlID="txtUR_VOL_24H" 
                        ValidChars=".0123456789">
                    </cc1:FilteredTextBoxExtender>
					</td>
			</tr>
			<tr>
				<td class="style2">Protein Creatinine Ratio mg/mmol&nbsp;
                    <asp:RangeValidator ID="RangeValidator7" runat="server" 
                        ControlToValidate="txtPROT_CREAT_RAT" ErrorMessage="0.0-15000" MaximumValue="15000" 
                        MinimumValue="0.0" SetFocusOnError="True" Type="Double"></asp:RangeValidator>
                </td>
				<td>
				<asp:TextBox id="txtPROT_CREAT_RAT" runat="server" Width="50px" MaxLength="6" 
                        ToolTip="0.0-15000"></asp:TextBox>
					<cc1:FilteredTextBoxExtender ID="txtPROT_CREAT_RAT_FilteredTextBoxExtender" 
                        runat="server" Enabled="True" TargetControlID="txtPROT_CREAT_RAT" 
                        ValidChars="0123456789.">
                    </cc1:FilteredTextBoxExtender>
					</td>
			</tr>
			<tr>
				<td class="style2">Albumin Creatinine Ratio mg/mmol&nbsp;
                    <asp:RangeValidator ID="RangeValidator8" runat="server" 
                        ControlToValidate="txtALB_CREAT_RAT" ErrorMessage="1.0-3000" MaximumValue="3000" 
                        MinimumValue="1.0" SetFocusOnError="True" Type="Double"></asp:RangeValidator>
                </td>
				<td>
				<asp:TextBox id="txtALB_CREAT_RAT" runat="server" Width="50px" ToolTip="1.0-3000" 
                        MaxLength="6"></asp:TextBox>
					<cc1:FilteredTextBoxExtender ID="txtALB_CREAT_RAT_FilteredTextBoxExtender" 
                        runat="server" Enabled="True" TargetControlID="txtALB_CREAT_RAT" 
                        ValidChars=".0123456789">
                    </cc1:FilteredTextBoxExtender>
					</td>
			</tr>
			<tr id="rowBacteria" runat="server">
				<td class="style2">Bacteria in urine &gt;10<sup>5</sup>/ml</td>
				<td>
				    <asp:CheckBox ID="chkBACT_URINE" runat="server" />
					</td>
			</tr>
			<tr id="rowOsmolarity" runat="server">
				<td class="style2">Osmolality&nbsp;<asp:RangeValidator ID="RangeValidator31" 
                        runat="server" ControlToValidate="txtOSMOLALITY" ErrorMessage="200-350" 
                        MaximumValue="350" MinimumValue="200" Type="Integer"></asp:RangeValidator>
                </td>
				<td>
				<asp:TextBox id="txtOSMOLALITY" runat="server" Width="50px" MaxLength="3" 
                        ToolTip="200-350"></asp:TextBox>
				    <cc1:FilteredTextBoxExtender ID="txtOSMOLALITY_FilteredTextBoxExtender" 
                        runat="server" Enabled="True" TargetControlID="txtOSMOLALITY" 
                        ValidChars="0123456789">
                    </cc1:FilteredTextBoxExtender>
				</td>
			</tr>
			<tr id="rowDysmorphic" runat="server">
				<td class="style2">Dysmorphic Erythrocytes in urine</td>
				<td>
				    <asp:DropDownList ID="ddlDysEryth" runat="server">
                        <asp:ListItem Selected="True" Value="">Select</asp:ListItem>
                        <asp:ListItem Value="1">Present</asp:ListItem>
                        <asp:ListItem Value="0">Not present</asp:ListItem>
                        <asp:ListItem Value="9">Not done</asp:ListItem>
                    </asp:DropDownList></td>
			</tr>
			<tr id="rowRedCells" runat="server">
				<td class="style2">Red Cell Cast in urine</td>
				<td>
				    <asp:DropDownList ID="ddlRedCellCast" runat="server">
                        <asp:ListItem Selected="True" Value="">Select</asp:ListItem>
                       <asp:ListItem Value="1">Present</asp:ListItem>
                        <asp:ListItem Value="0">Not present</asp:ListItem>
                        <asp:ListItem Value="9">Not done</asp:ListItem>
                    </asp:DropDownList></td>
			</tr>
			<tr id="rowWhiteCells" runat="server">
				<td class="style2">White Cell Casts in urine &gt;50<br />
                    
                </td>
				<td>
				    <asp:DropDownList ID="ddlWhiteCellCast" runat="server">
                        <asp:ListItem Selected="True" Value="">Select</asp:ListItem>
                        <asp:ListItem Value="1">Present</asp:ListItem>
                        <asp:ListItem Value="0">Not present</asp:ListItem>
                        <asp:ListItem Value="9">Not done</asp:ListItem>
                    </asp:DropDownList></td>
			</tr>
</table>
</fieldset>
		<br/>
		<fieldset ><legend style="font-weight:bold;">Creatinine Clearance</legend>
		<table style="width: 100%; margin-top:15px;">
			
			<%--</ContentTemplate>
    </asp:UpdatePanel>--%>
			<tr>
				<td>Creatinine Clearance Schwartz Method ml/min/per 1.73m<sup>2</sup>
				</td>
				<td>
				<asp:TextBox id="txtCREAT_CLEAR_SCHZ" runat="server" Width="50px" ReadOnly="true" 
                        ToolTip="Calculated"></asp:TextBox>
				</td>
			</tr>
			<tr>
			<td>
                <%--</ContentTemplate>
    </asp:UpdatePanel>--%>
			    <asp:Label ID="lblHeightWarn" runat="server" ForeColor="Red"></asp:Label>
			</td>
			</tr>
			
		</table>
		</fieldset>
		
		</td>
		<td valign="top">
		<fieldset ><legend style="font-weight:bold;">Antibodies &amp; Infections</legend>
		<table cellpadding="4" class="style7" style="margin-top:15px; table-layout:fixed ">
			
			<tr>
				<td style="width:120px">ANCA</td>
				<td>
				<asp:DropDownList id="DropDownList1" runat="server">
				<asp:ListItem Value="">Select</asp:ListItem>
				
				<asp:ListItem Value="1">C(PR3)</asp:ListItem>
				<asp:ListItem Value="2">P(MPO)</asp:ListItem>
				<asp:ListItem Value="3">C+P</asp:ListItem>
				<asp:ListItem Value="4">CANCA</asp:ListItem>
				<asp:ListItem Value="5">PANCA</asp:ListItem>
				<asp:ListItem Value="6">Negative</asp:ListItem>
				<asp:ListItem Value="9">Not done</asp:ListItem>
				</asp:DropDownList>
					</td>
			</tr>
			<tr>
				<td>ENA</td>
				<td>
				<asp:dropdownlist id="DropDownList2" runat="server">
				<asp:ListItem Value="">Select</asp:ListItem>
				<asp:ListItem Value="1">Positive</asp:ListItem>
				<asp:ListItem Value="0">Negative</asp:ListItem>
				<asp:ListItem Value="9">Not Done</asp:ListItem>
				</asp:dropdownlist>
					</td>
			</tr>
			<tr>
				<td>ANA</td>
				<td>
				<asp:dropdownlist id="DropDownList3" runat="server" AutoPostBack="True">
				<asp:ListItem Value="">Select</asp:ListItem>
				<asp:ListItem Value="1">Positive</asp:ListItem>
				<asp:ListItem Value="0">Negative</asp:ListItem>
				<asp:ListItem Value="9">Not Done</asp:ListItem>
				</asp:dropdownlist>
					</td>
			</tr>
			<tr id="rowDNA_ANTIB" runat="server">
				<td>
				<asp:label id="lblDNA_ANTIB" runat="server" Text="DNA Antibodies (desc + titre"></asp:label>
				</td>
				<td>
				<asp:TextBox id="txtDNA_ANTIB" runat="server" Rows="3" TextMode="MultiLine"></asp:TextBox>
					</td>
			</tr>
			<tr>
				<td>Anti-dsDNA</td>
				<td>
				    <asp:DropDownList ID="ddlANTI_DS_DNA" runat="server">
				    <asp:ListItem Value="">Select</asp:ListItem>
				    <asp:ListItem Value="1">Yes</asp:ListItem>
				    <asp:ListItem Value="0">No</asp:ListItem>
				    <asp:ListItem Value="9">Not done</asp:ListItem>
                    </asp:DropDownList>
					</td>
			</tr>
			<tr>
				<td>Cryoglobulins</td>
				<td>
				<asp:DropDownList id="DropDownList5" runat="server">
					<asp:listitem Selected="True" Value="">Select</asp:listitem>
					<asp:listitem Value="0">Negative</asp:listitem>
					<asp:listitem Value="1">Positive</asp:listitem>
					<asp:listitem Value="9">Not Done</asp:listitem>
				</asp:DropDownList>
				</td>
			</tr>
			<tr>
				<td>Anti-GBM</td>
				<td>
				<asp:DropDownList id="DropDownList6" runat="server">
				<asp:listitem Selected="True" Value="">Select</asp:listitem>
					<asp:listitem Value="0">Negative</asp:listitem>
					<asp:listitem Value="1">Positive</asp:listitem>
					<asp:listitem Value="9">Not Done</asp:listitem>

				</asp:DropDownList>
					</td>
			</tr>
			<tr id="rowIGG" runat="server">
				<td><asp:label id="lblIGG" runat="server" Text="IgG g/L"></asp:label></td>
				<td>
				<asp:textbox id="txtIGG" runat="server" Width="50px" ToolTip="0.0-20.0"></asp:textbox>
				&nbsp;<asp:RangeValidator ID="RangeValidator33" runat="server" 
                        ControlToValidate="txtIGG" ErrorMessage="0.0-20.0" MaximumValue="20.0" 
                        MinimumValue="0" Type="Double"></asp:RangeValidator>
				</td>
			</tr>
			<tr id="rowIGA" runat="server">
				<td><asp:Label ID="lblIGA" runat="server" Text="IgA g/L"></asp:Label></td>
				<td>
				<asp:textbox id="txtIGA" runat="server" Width="50px" ToolTip="0.0-10.0"></asp:textbox>
				&nbsp;<asp:RangeValidator ID="RangeValidator34" runat="server" 
                        ControlToValidate="txtIGA" ErrorMessage="0.0-10.0" MaximumValue="10" 
                        MinimumValue="0" Type="Double"></asp:RangeValidator>
				</td>
			</tr>
			<tr id="rowIGM" runat="server">
				<td><asp:Label ID="lblIGM" runat="server" Text="IgM g/L"></asp:Label></td>
				<td>
				<asp:textbox id="txtIGM" runat="server" Width="50px" ToolTip="0.0-10.0"></asp:textbox>
				&nbsp;<asp:RangeValidator ID="RangeValidator35" runat="server" 
                        ControlToValidate="txtIGM" ErrorMessage="0.0-10.0" MaximumValue="10" 
                        MinimumValue="0" Type="Double"></asp:RangeValidator>
				</td>
			</tr>
			<tr>
				<td>Complement C3</td>
				<td>
				    <asp:TextBox ID="txtCOMP_C3" runat="server" Width="50px" MaxLength="4" 
                        ToolTip="0.01-9.99"></asp:TextBox>&nbsp;
                    <asp:RangeValidator ID="RangeValidator27" runat="server" 
                        ControlToValidate="txtCOMP_C3" ErrorMessage="0.01-9.99" MaximumValue="9.99" 
                        MinimumValue="0.01" Type="Double"></asp:RangeValidator>
					</td>
			</tr>
			<tr>
				<td>Complement C4</td>
				<td>
				    <asp:TextBox ID="txtCOMP_C4" runat="server" Width="50px" MaxLength="4" 
                        ToolTip="0.01-9.99"></asp:TextBox>&nbsp;
                    <asp:RangeValidator ID="RangeValidator28" runat="server" 
                        ControlToValidate="txtCOMP_C4" ErrorMessage="0.01-9.99" 
                        MaximumValue="9.99" Type="Double" MinimumValue="0.01"></asp:RangeValidator>
					</td>
			</tr>
			<tr>
				<td>
				    Complement Other</td>
				<td>
				    &nbsp;
				    <asp:CheckBox ID="chkCompOther" runat="server" AutoPostBack="True" />
				</td>
			</tr>
			<tr id="rowCOMP_OTHER" visible="false" runat="server">
				<td colspan="2">
				    Other detail<br />
                    <asp:TextBox ID="txtCOMP_OTHER" runat="server" Rows="5" TextMode="MultiLine" 
                        Width="257px"></asp:TextBox>
                </td>
			</tr>
			<tr id="rowC3NEPH" runat="server">
                <td>
                    <asp:Label ID="lblC3_NEPH_FAC" runat="server" Text="C3 Nephritic factor"></asp:Label>
                </td>
                <td>
                    <asp:DropDownList ID="DropDownList15" runat="server">
                        <asp:ListItem Selected="True" Value="">Select</asp:ListItem>
                        <asp:ListItem Value="1">Pos</asp:ListItem>
                        <asp:ListItem Value="0">Neg</asp:ListItem>
                        <asp:ListItem Value="9">Unknown</asp:ListItem>
                    </asp:DropDownList>
                </td>
            </tr>
			<tr id="rowAnti_CLQ" runat="server">
			<td>Anti Clq Antibodies</td>
			<td>
                <asp:TextBox ID="txtANTI_CLQ" runat="server" Width="50px" MaxLength="5" 
                    ToolTip="0.0-150.0"></asp:TextBox>
&nbsp;<asp:RangeValidator ID="RangeValidator32" runat="server" ControlToValidate="txtANTI_CLQ" 
                    ErrorMessage="0.0 - 150.0" MaximumValue="150.0" MinimumValue="0.0" 
                    Type="Double"></asp:RangeValidator>
                </td>
			</tr>
			<tr id="rowANTI_STREP" runat="server">
				<td>
				<asp:label id="lblANTI_STREP_O" runat="server" Text="Antistreptolysin 0 titre IU"></asp:label>
				</td>
				<td>
				<asp:textbox id="txtANTI_STREP_O" runat="server" Width="50px"></asp:textbox>
				</td>
			</tr>
			<tr>
				<td>Hepatitis B Antibody</td>
				<td>
				<asp:DropDownList id="DropDownList9" runat="server">
				<asp:ListItem Value="">Select</asp:ListItem>
				<asp:ListItem Value="1">Positive</asp:ListItem>
				<asp:ListItem Value="0">Negative</asp:ListItem>
				<asp:ListItem Value="9">Unknown</asp:ListItem>
				</asp:DropDownList>
					</td>
			</tr>
			<tr>
				<td>Hepatitis C Antigen</td>
				<td><asp:DropDownList id="DropDownList10" runat="server">
				<asp:ListItem Value="">Select</asp:ListItem>
				<asp:ListItem Value="1">Positive</asp:ListItem>
				<asp:ListItem Value="0">Negative</asp:ListItem>
				<asp:ListItem Value="9">Unknown</asp:ListItem>
				</asp:DropDownList>
</td>
			</tr>
			<tr id="rowHIV" runat="server">
				<td>HIV Antibody</td>
				<td><asp:DropDownList id="DropDownList11" runat="server">
				<asp:ListItem Value="">Select</asp:ListItem>
				<asp:ListItem Value="1">Positive</asp:ListItem>
				<asp:ListItem Value="0">Negative</asp:ListItem>
				<asp:ListItem Value="9">Unknown</asp:ListItem>
				</asp:DropDownList>
</td>
			</tr>
			<tr id="rowDNAFactorH" runat="server">
				<td>DNA taken for genetic Factor H</td>
				<td>
				    <asp:RadioButton ID="radDNAYes" runat="server" GroupName="dna" Text="Yes" />
&nbsp;<asp:RadioButton ID="radDNANo" runat="server" GroupName="dna" Text="No" />
					</td>
			</tr>
			<tr>
				<td>EBV</td>
				<td>
				    <asp:DropDownList ID="DropDownListEBV" runat="server">
				    <asp:ListItem Value="" Text="Select"></asp:ListItem>
				    <asp:ListItem Value="1" Text="Neg"></asp:ListItem>
				    <asp:ListItem Value="2" Text="IgG Pos"></asp:ListItem>
				    <asp:ListItem Value="3" Text="IgM Pos"></asp:ListItem>
				    <asp:ListItem Value="4" Text="IgG + IgM Pos"></asp:ListItem>
				    <asp:ListItem Value="9" Text="Not done"></asp:ListItem>
                    </asp:DropDownList>
					</td>
			</tr>
			<tr>
				<td>CMV serology</td>
				<td>
				   <asp:DropDownList ID="DropDownListCMV" runat="server">
				    <asp:ListItem Value="" Text="Select"></asp:ListItem>
				    <asp:ListItem Value="1" Text="Neg"></asp:ListItem>
				    <asp:ListItem Value="2" Text="IgG Pos"></asp:ListItem>
				    <asp:ListItem Value="3" Text="IgM Pos"></asp:ListItem>
				    <asp:ListItem Value="4" Text="IgG + IgM Pos"></asp:ListItem>
				    <asp:ListItem Value="9" Text="Not done"></asp:ListItem>
                    </asp:DropDownList></td>
			</tr>
			<tr id="rowCMVSymp" runat="server">
				<td>CMV symptomatic</td>
				<td>
				<asp:CheckBox id="chkCMV_SYM" runat="server" />
					</td>
			</tr>
			<tr id="rowParvoVirus" runat="server">
				<td>
				<asp:label id="lblPARVO_ANTIB" runat="server" Text="Parvovirus antibody"></asp:label>
				</td>
				<td>
				    <asp:DropDownList ID="DropDownListParvo" runat="server">
				    <asp:ListItem Value="" Text="Select"></asp:ListItem>
				    <asp:ListItem Value="1" Text="Neg"></asp:ListItem>
				    <asp:ListItem Value="2" Text="Pos"></asp:ListItem>
				    <asp:ListItem Value="9" Text="Not done"></asp:ListItem>
                    </asp:DropDownList>
					</td>
			</tr>
			<tr>
				<td>Other infection</td>
				<td>
				<asp:CheckBox id="chkOTHER_INFECT" runat="server" BorderStyle="None" AutoPostBack="true" />
					</td>
			</tr>
			
			<tr id="rowOther_Infect" runat="server" visible="false">
				<td colspan="2">Other infection specify<br />
				<asp:textbox ID="txtOTHER_INFECT_SP" runat="server" Rows="5" TextMode="MultiLine" Width="257px"></asp:textbox>
				</td>
			</tr>
			
			</table>
		</fieldset>	
		</td>
	</tr>
	<tr><td colspan="3">
        <asp:Button ID="btnUpdate" runat="server" CausesValidation="true" 
            Text="Save" CssClass="saveBtn" />
        
&nbsp;<asp:Label ID="lblUpdate" runat="server"></asp:Label>
        &nbsp;
        <asp:ValidationSummary ID="ValidationSummary1" runat="server" CssClass="inline" 
            DisplayMode="SingleParagraph" 
            HeaderText=" One or more required fields are missing or out of range" ShowMessageBox="True" 
            ShowSummary="False" />
        </td></tr>
</table>

        
    &nbsp;<br />
    <br />
    <asp:Label ID="lblDebug" runat="server"></asp:Label>


</div><br /><asp:Label ID="lblPage" runat="server"></asp:Label>
</div>
</asp:Content>

