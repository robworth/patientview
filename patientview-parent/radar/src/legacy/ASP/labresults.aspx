<%@ Page Title="RADAR - Lab Results" Language="VB" MasterPageFile="~/MasterPage.master" AutoEventWireup="false" CodeFile="labresults.aspx.vb" Inherits="labresults" Debug="true" %>

<%@ Register assembly="DevExpress.Web.ASPxEditors.v9.2, Version=9.2.4.0, Culture=neutral, PublicKeyToken=b88d1754d700e49a" namespace="DevExpress.Web.ASPxEditors" tagprefix="dxe" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">

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
width: 234px;
}

.style3 {
height: 26px;
width: 234px;
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
</style>
	
<link href="tabs.css" rel="stylesheet" type="text/css" />

<!--[if IE]>
<style type="text/css">
#mainBlock {
margin-top: 0;
}
</style>
<![endif]-->

</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
	<div id="tabsC" > 
  <ul> 
    <li><a href="patient-entry2.aspx" title="Demographics"><span>Demographics</span></a></li> 
    <li><asp:hyperlink runat="server" ID="lnkDiagnosis" NavigateUrl="diagnosis.aspx" ToolTip="Diagnosis"><span >Diagnosis</span></asp:hyperlink></li> 
    <li><asp:hyperlink runat="server" ID="lnkClinical" NavigateUrl="clinical3.aspx" Tooltip="Clinical Picture"><span >Clinical Picture</span></asp:hyperlink></li> 
    <li><asp:hyperlink runat="server" ID="lnkLabResults" NavigateUrl="labresults.aspx" Tooltip="Laboratory Results"><span class="hovered">Laboratory Results</span></asp:hyperlink></li> 
    <li><asp:hyperlink runat="server" ID="lnk3Months" NavigateUrl="six-month.aspx" Tooltip="6 Month Follow Up"><span>6 Month Follow Up</span></asp:hyperlink></li> 
  </ul> 
</div> 
<div id="mainBlock">
<asp:ScriptManager ID="ScriptManager1" runat="server">
        </asp:ScriptManager>
    <asp:UpdatePanel ID="UpdatePanel1" runat="server">
    <ContentTemplate>
    <table cellpadding="4" class="style1" style="width: 100%" id="tblHeader">
	<tr>
		<td><b>Laboratory Results</b></td>
		<td style="text-align:right; width:100px;">RADAR No.</td>
		<td style="width:120px;">
            <asp:TextBox ID="txtRADAR_NO" runat="server" ReadOnly="True"></asp:TextBox>
        </td>
	</tr>
	<tr>
		<td>Name
		<asp:textbox id="txtFNAME" runat="server" ReadOnly="True"></asp:textbox>
		&nbsp;<asp:textbox id="txtSNAME" runat="server" ReadOnly="True"></asp:textbox>
		&nbsp;DoB
		<asp:textbox id="txtDOB" runat="server" ReadOnly="True" Width="100px"></asp:textbox>
		</td>
		<td style="text-align:right; width:100px;">Hospital No.</td>
		<td>
            <asp:TextBox ID="txtHOSP_NO" runat="server" ReadOnly="True"></asp:TextBox>
        </td>
	</tr>
	<tr>
		<td>Date of LAB Results
            <dxe:ASPxDateEdit ID="ASPxDateEdit1" runat="server" cssclass="inline" Width="200px">
            </dxe:ASPxDateEdit>
        &nbsp;<asp:label id="lblLabID" runat="server" Text="" Visible="false"></asp:label>
        </td>
		<td style="text-align:right; width:100px;">Diagnosis</td>
		<td>
            <asp:TextBox ID="txtDIAG" runat="server" ReadOnly="True"></asp:TextBox>
        </td>
	</tr>
</table>

<table cellpadding="4" cellspacing="10" class="style1" style="width: 100%" id="tblMain">
	<tr>
		<td valign="top" style="width:25%">
		<table cellpadding="4" class="style1" style="width: 100%">
			<tr>
				<td colspan="2">&nbsp;</td>
				
			</tr>
			<tr>
                <td colspan="2">
                    <strong>Bloods</strong></td>
            </tr>
			<tr>
				<td>Serum Creatinine<br />
				    <asp:RangeValidator ID="RangeValidator1" runat="server" 
                        ErrorMessage="20-2800" ControlToValidate="txtCreatSer" MaximumValue="2800" 
                        MinimumValue="20" SetFocusOnError="True" Type="Integer"  ></asp:RangeValidator></td>
				<td>
				<asp:TextBox id="txtCreatSer" runat="server" Width="70px"></asp:TextBox>
				</td>
			</tr>
			<tr>
				<td>Protein<br />
                    <asp:RangeValidator ID="RangeValidator25" runat="server" 
                        ControlToValidate="txtPROTEIN" ErrorMessage="5-90" MaximumValue="90" 
                        MinimumValue="5" Type="Integer"></asp:RangeValidator>
                </td>
				<td>
				<asp:TextBox id="txtPROTEIN" runat="server" Width="70px"></asp:TextBox>
					</td>
			</tr>
			<tr>
				<td>Albumin<br />
                    <asp:RangeValidator ID="RangeValidator2" runat="server" 
                        ControlToValidate="txtAlbumin" ErrorMessage="5-60" MaximumValue="60" 
                        MinimumValue="5" Type="Integer"></asp:RangeValidator>
                </td>
				<td>
				<asp:TextBox id="txtALBUMIN" runat="server" Width="70px"></asp:TextBox>
					</td>
			</tr>
			<tr>
				<td>BUN Blood Urea<br />
                    <asp:RangeValidator ID="RangeValidator3" runat="server" 
                        ControlToValidate="txtUREA_SER" ErrorMessage="1-50" MaximumValue="50" 
                        MinimumValue="1" SetFocusOnError="True" Type="Integer"></asp:RangeValidator>
                </td>
				<td>
				<asp:TextBox id="txtUREA_SER" runat="server" Width="70px"></asp:TextBox>
					</td>
			</tr>
			<tr>
				<td>Sodium Na<br />
                    <asp:RangeValidator ID="RangeValidator4" runat="server" 
                        ControlToValidate="txtSodium" ErrorMessage="90-180" SetFocusOnError="True" Type="Integer" MinimumValue="90" MaximumValue="180"></asp:RangeValidator>
                </td>
				<td>
				<asp:TextBox id="txtSODIUM" runat="server" Width="70px"></asp:TextBox>
					</td>
			</tr>
			<tr>
				<td>Potassium K<br />
                    <asp:RangeValidator ID="RangeValidator5" runat="server" 
                        ControlToValidate="txtPotassium" ErrorMessage="1.0-9.9" MaximumValue="9.9" 
                        MinimumValue="1.0" SetFocusOnError="True" Type="Double"></asp:RangeValidator>
                </td>
				<td>
				<asp:TextBox id="txtPOTASSIUM" runat="server" Width="70px"></asp:TextBox>
					</td>
			</tr>
			<tr>
				<td>Phosphate PO4<br />
                    <asp:RangeValidator ID="RangeValidator6" runat="server" 
                        ControlToValidate="txtPHOS" ErrorMessage="0.00-5.50" MaximumValue="5.50" 
                        MinimumValue="0.00" SetFocusOnError="True" Type="Double"></asp:RangeValidator>
                </td>
				<td>
				<asp:TextBox id="txtPHOS" runat="server" Width="70px" ToolTip="0.00-5.50"></asp:TextBox>
					</td>
			</tr>
			<tr>
				<td>WBC<br />
				<asp:rangevalidator id="RangeValidator9" runat="server" ControlToValidate="txtWBC" ErrorMessage="0.0-30" MaximumValue="30" MinimumValue="0" SetFocusOnError="True" Type="Double"></asp:rangevalidator>
				</td>
				<td>
				<asp:TextBox id="txtWBC" runat="server" ToolTip="1.0-30" Width="70px"></asp:TextBox>
					</td>
			</tr>
			<tr>
				<td>Hb<br />
				<asp:rangevalidator id="RangeValidator13" runat="server" ControlToValidate="txtHB" ErrorMessage="2-30" MaximumValue="30" MinimumValue="2" Type="Integer"></asp:rangevalidator>
				</td>
				<td>
				<asp:TextBox id="txtHB" runat="server" Width="70px"></asp:TextBox>
					</td>
			</tr>
			<tr>
				<td>Neutrophils<br />
				<asp:rangevalidator id="RangeValidator11" runat="server" ControlToValidate="txtNEUTRO" ErrorMessage="0.0-80" MaximumValue="80" MinimumValue="0" SetFocusOnError="True" ToolTip="3-80" Type="Integer"></asp:rangevalidator>
				</td>
				<td>
				<asp:TextBox id="txtNEUTRO" runat="server" Width="70px"></asp:TextBox>
					</td>
			</tr>
			<tr>
				<td>Platelets<br />
				<asp:rangevalidator id="RangeValidator12" runat="server" ControlToValidate="txtPlatelets" ErrorMessage="1-800" MaximumValue="800" MinimumValue="1" SetFocusOnError="True" Type="Integer"></asp:rangevalidator>
				</td>
				<td>
				<asp:TextBox id="txtPLATELETS" runat="server" Width="70px"></asp:TextBox>
					</td>
			</tr>
			<tr>
				<td>Ferritin<br />
				<asp:RangeValidator id="RangeValidator17" runat="server" ErrorMessage="1-5000" Type="Integer" MinimumValue="1" MaximumValue="5000" ControlToValidate="txtFERRITIN"></asp:RangeValidator>
				<br />
				</td>
				<td>
				<asp:TextBox id="txtFERRITIN" runat="server" Width="70px"></asp:TextBox>
					</td>
			</tr>
			<tr>
				<td>Total Cholesterol<br />
                    <asp:RangeValidator ID="RangeValidator14" runat="server" 
                        ErrorMessage="1.0-22.0" Type="Double" MaximumValue="22.0" MinimumValue="1.0" ControlToValidate="txtCHOL_TOTAL"></asp:RangeValidator>
                </td>
				<td>
				<asp:TextBox id="txtCHOL_TOTAL" runat="server" Width="70px"></asp:TextBox>
					</td>
			</tr>
			<tr>
				<td>HDL Cholesterol<br />
                    <asp:RangeValidator ID="RangeValidator15" runat="server" 
                        ErrorMessage="1-30" Type="Integer" MinimumValue="1" MaximumValue="30" ControlToValidate="txtCHOL_HDL"></asp:RangeValidator>
                </td>
				<td>
				<asp:TextBox id="txtCHOL_HDL" runat="server" Width="70px"></asp:TextBox>
					</td>
			</tr>
			<tr>
				<td>LDL Cholesterol<br />
				<asp:RangeValidator id="RangeValidator16" runat="server" ErrorMessage="1-30" Type="Integer" MinimumValue="1" MaximumValue="30" ControlToValidate="txtCHOL_LDL"></asp:RangeValidator>
				</td>
				<td>
				<asp:TextBox id="txtCHOL_LDL" runat="server" Width="70px"></asp:TextBox>
					</td>
			</tr>
			<tr>
				<td>Triglycerides<br />
				<asp:RangeValidator id="RangeValidator18" runat="server" ErrorMessage="2.0-30" Type="double" MinimumValue="2.0" MaximumValue="30" ControlToValidate="txtTRIG"></asp:RangeValidator>
				</td>
				<td>
				<asp:TextBox id="txtTRIG" runat="server" Width="70px"></asp:TextBox>
					</td>
			</tr>
			<tr>
				<td><asp:label ID="lblTHYROX" runat="server" Text="Thyroxine T4 pmol/L"></asp:label></td>
				<td>
				<asp:TextBox id="txtTHYROX" runat="server" Width="70px"></asp:TextBox>
				</td>
			</tr>
			<tr>
				<td>INR<br />
				<asp:RangeValidator id="RangeValidator19" runat="server" ErrorMessage="0.5-50" Type="Double" MinimumValue="0.5" MaximumValue="50" ControlToValidate="txtINR"></asp:RangeValidator>
				</td>
				<td>
				<asp:TextBox id="txtINR" runat="server" Width="70px"></asp:TextBox>
					</td>
			</tr>
			<tr>
				<td>
				<asp:label id="lblCRP" runat="server" Text="CRP"></asp:label>
				<br />
				<asp:RangeValidator id="RangeValidator20" runat="server" ErrorMessage="< 8" type="integer" minimumvalue="0" maximumvalue="8" controltovalidate="txtCRP" ></asp:RangeValidator>
				</td>
				<td>
				<asp:TextBox id="txtCRP" runat="server" Width="70px"></asp:TextBox>
					</td>
			</tr>
		    <tr>
                <td>
                    &nbsp;</td>
                <td>
                    &nbsp;</td>
            </tr>
		</table>
		</td>
		<td valign="top" style="width:41%">
		<table style="width: 100%">
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
                <td colspan="2">
                    <strong>Creatinine Clearance</strong></td>
            </tr>
			<tr>
				<td>Creatine Clearance 24H
				Urine Method ml/min
				<asp:RangeValidator id="RangeValidator21" runat="server" ErrorMessage="1-160" Type="Integer" MinimumValue="1" MaximumValue="160" ControlToValidate="txtCREAT_CLEAR_24"></asp:RangeValidator>
				</td>
				<td>
				<asp:TextBox id="txtCREAT_CLEAR_24" runat="server" Width="70px"></asp:TextBox>
					</td>
			</tr>
			<tr>
				<td>Creatinine Clearance Radioactivity Method ml/min
				<asp:RangeValidator id="RangeValidator22" runat="server" ErrorMessage="1-160" Type="Integer" MinimumValue="1" MaximumValue="160" ControlToValidate="txtCREAT_CLEAR_RADIO"></asp:RangeValidator>
				</td>
				<td>
				<asp:TextBox id="txtCREAT_CLEAR_RADIO" runat="server" Width="70px"></asp:TextBox>
				</td>
			</tr>
			<tr>
				<td>Creatinine Clearance Schwartz Method ml/min
				<asp:RangeValidator id="RangeValidator23" runat="server" ErrorMessage="1-160" Type="Integer" MinimumValue="1" MaximumValue="160" ControlToValidate="txtCREAT_CLEAR_SCHZ"></asp:RangeValidator>
				</td>
				<td>
				<asp:TextBox id="txtCREAT_CLEAR_SCHZ" runat="server" Width="70px"></asp:TextBox>
				</td>
			</tr>
		</table><p>&nbsp;</p>
		<table style="width: 100%">
			<tr>
				<td colspan="2"><strong>Urinalysis</strong></td>
				
			</tr>
			<tr>
				<td class="style2">Urine Volume ml/24hr<br />
				<asp:RangeValidator id="RangeValidator24" runat="server" ErrorMessage="0-4000" Type="Integer" MinimumValue="0" MaximumValue="4000" ControlToValidate="txtUR_VOL_24H"></asp:RangeValidator>
				</td>
				<td>
				<asp:TextBox id="txtUR_VOL_24H" runat="server" Width="70px"></asp:TextBox>
					</td>
			</tr>
			<tr>
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
				<td class="style2">Protein Creatinine Ratio<br />
                    <asp:RangeValidator ID="RangeValidator7" runat="server" 
                        ControlToValidate="txtPROT_CREAT_RAT" ErrorMessage="1.0-100" MaximumValue="100" 
                        MinimumValue="1.0" SetFocusOnError="True" Type="Double"></asp:RangeValidator>
                </td>
				<td>
				<asp:TextBox id="txtPROT_CREAT_RAT" runat="server" Width="70px"></asp:TextBox>
					</td>
			</tr>
			<tr>
				<td class="style2">Albumin Creatinine Ratio<br />
                    <asp:RangeValidator ID="RangeValidator8" runat="server" 
                        ControlToValidate="txtALB_CREAT_RAT" ErrorMessage="1.0-100" MaximumValue="100" 
                        MinimumValue="1.0" SetFocusOnError="True" Type="Double"></asp:RangeValidator>
                </td>
				<td>
				<asp:TextBox id="txtALB_CREAT_RAT" runat="server" Width="70px"></asp:TextBox>
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
				<td class="style2">Dysmorphic Erythrocytes in urine</td>
				<td>
				<asp:checkbox id="chkDYS_ERYTH_URINE" runat="server" />
				</td>
			</tr>
			<tr>
				<td class="style2">Red Cell Cast in urine</td>
				<td>
				<asp:checkbox id="chkRED_CCASTS_URINE" runat="server" />
					</td>
			</tr>
			<tr>
				<td class="style2">White Cell Casts in urine &gt;50<br />
                    
                </td>
				<td>
				<asp:checkbox id="chkWBC_CASTS_URINE" runat="server" />
					</td>
			</tr>
			<tr>
				<td class="style3">Leucocytes in urine &gt;50</td>
				<td style="height: 26px">
				<asp:checkbox id="chkLEUC_URINE" runat="server" />
					</td>
			</tr>
			<tr>
				<td class="style2">
				<asp:label id="lblNITRITE" runat="server" Text="Nitrite"></asp:label>
				</td>
				<td>
				<asp:dropdownlist id="DropDownList14" runat="server">
				<asp:ListItem Value="">Select</asp:ListItem>
				<asp:ListItem Value="1">Yes</asp:ListItem>
				<asp:ListItem Value="0">No</asp:ListItem>
				<asp:ListItem Value="9">Not Done</asp:ListItem>
				</asp:dropdownlist>
					</td>
			</tr>
			<tr>
				<td class="style2">Bacteria in urine &gt;10<sup>5</sup>/ml</td>
				<td>
				    <asp:CheckBox ID="chkBACT_URINE" runat="server" />
					</td>
			</tr>
			<tr>
				<td class="style2">Glucose in urine</td>
				<td>
				    <asp:CheckBox ID="chkGLUC_URINE" runat="server" />
					</td>
			</tr>
			<tr>
				<td class="style2">Osmolartity</td>
				<td>
				<asp:TextBox id="txtOSMOLARITY" runat="server" Width="70px"></asp:TextBox>
				</td>
			</tr>
			<tr>
				<td class="style2">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</table>
		</td>
		<td valign="top">
		<table cellpadding="4" class="style1" style="width: 100%">
			<tr>
				<td colspan="2">&nbsp;</td>
				
			</tr>
			<tr>
                <td colspan="2">
                    <strong>Antibodies &amp; Infections</strong></td>
            </tr>
			<tr>
				<td>ANCA</td>
				<td>
				<asp:DropDownList id="DropDownList1" runat="server">
				<asp:ListItem Value="">Select</asp:ListItem>
				<asp:ListItem Value="0">Neg</asp:ListItem>
				<asp:ListItem Value="1">C(PR3)</asp:ListItem>
				<asp:ListItem Value="2">P(MPO)</asp:ListItem>
				<asp:ListItem Value="9">Not done</asp:ListItem>
				</asp:DropDownList>
					</td>
			</tr>
			<tr>
				<td>Elisa Assay</td>
				<td>
				<asp:TextBox id="txtELISA_ASS" runat="server"></asp:TextBox>
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
			<tr>
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
				<asp:textbox id="txtDNA_ANTI_DS" runat="server"></asp:textbox>
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
			<tr>
				<td><asp:label id="lblIGG" runat="server" Text="IgG g/L"></asp:label></td>
				<td>
				<asp:textbox id="txtIGG" runat="server"></asp:textbox>
				</td>
			</tr>
			<tr>
				<td><asp:Label ID="lblIGA" runat="server" Text="IgA g/L"></asp:Label></td>
				<td>
				<asp:textbox id="txtIGA" runat="server"></asp:textbox>
				</td>
			</tr>
			<tr>
				<td><asp:Label ID="lblIGM" runat="server" Text="IgM g/L"></asp:Label></td>
				<td>
				<asp:textbox id="txtIGM" runat="server"></asp:textbox>
				</td>
			</tr>
			<tr>
				<td>Complement C3</td>
				<td>
				<asp:DropDownList id="DropDownList7" runat="server">
					<asp:listitem Selected="True" Value="">Select</asp:listitem>
					<asp:listitem Value="1">Comp 1</asp:listitem>
					<asp:listitem Value="2">Comp 2</asp:listitem>
					<asp:listitem Value="3">Comp 3</asp:listitem>
					<asp:listitem Value="4">Comp 4</asp:listitem>
				</asp:DropDownList>
					</td>
			</tr>
			<tr>
				<td>Complement C4</td>
				<td>
				<asp:DropDownList id="DropDownList8" runat="server">
				<asp:listitem Selected="True" Value="">Select</asp:listitem>
					<asp:listitem Value="1">Comp 1</asp:listitem>
					<asp:listitem Value="2">Comp 2</asp:listitem>
					<asp:listitem Value="3">Comp 3</asp:listitem>

					<asp:listitem Value="4">Comp 4</asp:listitem>

				</asp:DropDownList>
					</td>
			</tr>
			<tr>
				<td>
				<asp:label id="lblC3_NEPH_FAC" runat="server" Text="C3 Nephritic factor"></asp:label>
				</td>
				<td>
				<asp:dropdownlist id="DropDownList15" runat="server">
					<asp:listitem Selected="True">Select</asp:listitem>
					<asp:listitem Value="1">Pos</asp:listitem>
					<asp:listitem Value="0">Neg</asp:listitem>
					<asp:listitem Value="9">Unknown</asp:listitem>
				</asp:dropdownlist>
				</td>
			</tr>
			<tr>
				<td>Anti-SLT (ASOT)</td>
				<td>
				<asp:textbox id="txtANTI_SLT" runat="server"></asp:textbox>
				</td>
			</tr>
			<tr>
				<td>
				<asp:label id="lblANTI_STREP_O" runat="server" Text="Antistreptolysin 0 titre IU"></asp:label>
				</td>
				<td>
				<asp:textbox id="txtANTI_STREP_O" runat="server"></asp:textbox>
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
			<tr>
				<td>HIV Antibody</td>
				<td><asp:DropDownList id="DropDownList11" runat="server">
				<asp:ListItem Value="">Select</asp:ListItem>
				<asp:ListItem Value="1">Positive</asp:ListItem>
				<asp:ListItem Value="0">Negative</asp:ListItem>
				<asp:ListItem Value="9">Unknown</asp:ListItem>
				</asp:DropDownList>
</td>
			</tr>
			<tr>
				<td>EBV</td>
				<td>
				<asp:CheckBox id="chkEBV" runat="server" CssClass="noborder" />
					</td>
			</tr>
			<tr>
				<td>CMV seriology</td>
				<td>
				<asp:CheckBox id="chkCMV" runat="server" CssClass="noborder" />
					</td>
			</tr>
			<tr>
				<td>CMV symptomatic</td>
				<td>
				<asp:CheckBox id="chkCMV_SYM" runat="server" />
					</td>
			</tr>
			<tr>
				<td>
				<asp:label id="lblPARVO_ANTIB" runat="server" Text="Parvovirus antibody"></asp:label>
				</td>
				<td>
				<asp:CheckBox id="chkPARVO_ANTIB" runat="server" BorderStyle="None" />
					</td>
			</tr>
			<tr>
				<td>Other infection</td>
				<td>
				<asp:CheckBox id="chkOTHER_INFECT" runat="server" BorderStyle="None" />
					</td>
			</tr>
			<tr>
				<td colspan="2">
				Other infection specify</td>
			</tr>
			<tr>
				<td colspan="2">
				<asp:textbox ID="txtOTHER_INFECT_SP" runat="server" Rows="5" TextMode="MultiLine" Width="257px"></asp:textbox>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			</table>
		</td>
	</tr>
	<tr><td>
        <asp:Button ID="btnUpdate" runat="server" Text="Update" CausesValidation="true" />
        </td></tr>
</table>

    <asp:Label ID="lblUpdate" runat="server"></asp:Label>&nbsp;&nbsp;<asp:Label ID="lblDebug" runat="server"></asp:Label>
</ContentTemplate>
    </asp:UpdatePanel>
</div>
</asp:Content>


