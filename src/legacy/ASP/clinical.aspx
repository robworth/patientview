<%@ Page Title="RADAR - Clinical" Language="VB" MasterPageFile="~/MasterPage2.master" AutoEventWireup="false" CodeFile="clinical.aspx.vb" Inherits="clinical" Debug="true" %>

<%@ Register assembly="DevExpress.Web.ASPxEditors.v9.2, Version=9.2.4.0, Culture=neutral, PublicKeyToken=b88d1754d700e49a" namespace="DevExpress.Web.ASPxEditors" tagprefix="dxe" %>

<asp:Content ID="Content1" ContentPlaceHolderID="childContentHead" Runat="Server">
    <style type="text/css">
.style1 {
	border-collapse: collapse;
}
.style2 {
	text-align: right;
}
#ctl00_ctl00_ContentPlaceHolder1_childContent1_FormView1 table {
margin:0;
}
.indent {
padding-left:30px;
}
#spnClinical {
background-position:100% -42px;
}

#ctl00_ctl00_ContentPlaceHolder1_lnkClinical  {

background-position:100% -42px;

}

</style>

</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="childContent1" Runat="Server">
    <%--<asp:ScriptManager ID="ScriptManager1" runat="server">
    </asp:ScriptManager>
    <asp:UpdatePanel ID="UpdatePanel1" runat="server">
    <ContentTemplate>--%>
    <asp:FormView ID="FormView1" runat="server" DataKeyNames="cID" 
        DataSourceID="SqlDataSource1" Width="100%" OnItemUpdated="FormView1_ItemUpdated">
               
        
        <EditItemTemplate>
        
        <table cellpadding="5" class="style1" style="width: 100%; background-color: #B6DF9F;">
	<tr>
		<td><b>Clinical Picture</b></td>
		<td class="style2">RADAR No.<asp:TextBox ID="RADAR_NOTextBox" runat="server" 
                Text='<%# Bind("RADAR_NO") %>' ReadOnly="True" />
</td>
	</tr>
	<tr>
		<td>Name&nbsp;<asp:TextBox ID="FNAMETextBox" runat="server" Text='<%# Bind("FNAME") %>' />&nbsp; <asp:TextBox ID="SNAMETextBox" runat="server" Text='<%# Bind("SNAME") %>' />

            &nbsp;DoB&nbsp; <asp:TextBox ID="DOBTextBox" runat="server" 
                Text='<%# Bind("DOB", "{0:dd-MMM-yyyy}") %>' ReadOnly="True" 
                Width="80px" />
</td>
		<td class="style2">Hospital No.&nbsp;<asp:TextBox ID="HOSP_NOTextBox" 
                runat="server" Text='<%# Bind("HOSP_NO") %>' ReadOnly="True" />
</td>
	</tr>
	<tr>
		<td>Date of Clinical Picture &nbsp;<asp:TextBox ID="DATE_CLIN_PICTextBox" runat="server" 
                    Text='<%# Bind("DATE_CLIN_PIC", "{0:dd-MMM-yyyy}") %>' Width="80px" />

		<%--<dxe:ASPxDateEdit ID="ASPxDateEdit1" 
                runat="server" Date='<%# Bind("DATE_CLIN_PIC") %>' CssClass="inline" AllowNull="true" >
            </dxe:ASPxDateEdit>
           
            <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" 
                ControlToValidate="ASPxDateEdit1" ErrorMessage="*" Font-Bold="True"></asp:RequiredFieldValidator>--%>
</td>
		<td class="style2">Diagnosis&nbsp;<asp:TextBox ID="DIAGTextBox" runat="server" 
                Text='<%# Bind("DIAG") %>' ReadOnly="True" Width="1" Visible="false" /><asp:TextBox ID="txtDiagText" runat="server"></asp:TextBox>
</td>
	</tr>
</table>
<table style="width: 100%; background-color:#EEF6ED;">
	<tr>
		<td style="width:50%; vertical-align:top;">
		<table style="width: 100%">
			<tr>
				<td align="right">&nbsp;</td>
				<td> &nbsp;</td>
			</tr>
			<tr>
                <td align="right">
                    Height cm&nbsp;</td>
                <td>
                    <asp:TextBox ID="HEIGHTTextBox" runat="server" Text='<%# Bind("HEIGHT") %>' 
                        Width="40px" />
                </td>
            </tr>
			<tr>
				<td align="right">Weight kg&nbsp;</td>
				<td> <asp:TextBox ID="WEIGHTTextBox" runat="server" Text='<%# Bind("WEIGHT") %>' 
                        Width="40px" />
</td>
			</tr>
			<tr>
				<td align="right">BP&nbsp;</td>
				
			    <td>
                    <asp:TextBox ID="SYS_BPTextBox" runat="server" Text='<%# Bind("SYS_BP") %>' 
                        Width="50px" />&nbsp;<strong>/</strong>&nbsp;
                    <asp:TextBox ID="DIA_BPTextBox" runat="server" Text='<%# Bind("DIA_BP") %>' 
                        Width="50px" />&nbsp;
                    <asp:TextBox ID="MAP_BPTextBox" runat="server" Text='<%# Bind("MAP_BP") %>' 
                        Width="50px" ReadOnly="True" />
                </td>
				
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td><b>Infection</b></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>Type</td>
				<td>
                    <asp:DropDownList ID="DropDownList1" runat="server" SelectedValue='<%# Bind("INFECTION") %>' >
                    <asp:ListItem Value="" Selected="True">Select</asp:ListItem>
                    <asp:ListItem Value="1">Bacterial</asp:ListItem>
                    <asp:ListItem Value="2">Viral</asp:ListItem>
                    </asp:DropDownList>
                </td>
			</tr>
			<tr>
				<td>Details</td>
				<td><asp:TextBox ID="INFECTION_DETAILTextBox" runat="server" 
                    Text='<%# Bind("INFECTION_DETAIL") %>' />
</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2">Primary Steroid Resistance&nbsp; <asp:CheckBox ID="STEROID_RESISTCheckBox" runat="server" 
                    Checked='<%# Bind("STEROID_RESIST") %>' />
</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>Comments</td>
				<td>&nbsp;</td>
			</tr>
<tr>
				<td colspan="2">
                    &nbsp;</td>
				
			</tr>

		    <tr>
                <td colspan="2">
                    <asp:TextBox ID="COMMENTSTextBox" runat="server" Rows="5" 
                        Text='<%# Bind("COMMENTS") %>' TextMode="MultiLine" Width="381px" />
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    &nbsp;</td>
            </tr>
            <tr>
                <td colspan="2">
                    <asp:Button ID="UpdateButton" runat="server" CausesValidation="True" 
                        CommandName="Update" Text="Update" />
                    &nbsp;
                    <asp:LinkButton ID="UpdateCancelButton" runat="server" CausesValidation="False" 
                        CommandName="Cancel" Text="Cancel" Visible="false" />
                </td>
            </tr>

		</table>
		</td>
		<td style="width:50%; vertical-align:top;">
		<table class="style1" style="width: 100%">
			<tr>
				<td style="width:250px;">&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
                <td style="width:250px;">
                    Course of Disease</td>
                <td>
                    <asp:DropDownList ID="DropDownList2" runat="server" SelectedValue='<%# Bind("COURSE_DIS") %>'>
                    <asp:ListItem Value="">Select</asp:ListItem>
                    <asp:ListItem Value="1">Acute</asp:ListItem>
                    <asp:ListItem Value="2">Chronic</asp:ListItem>
                    <asp:ListItem Value="9">Unknown</asp:ListItem>
                    </asp:DropDownList>
                    <%--<asp:TextBox ID="COURSE_DISTextBox" runat="server" 
                        Text='<%# Bind("COURSE_DIS") %>' />--%>
                </td>
            </tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td>Additional Significant Diagnosis 1</td>
				<td><asp:TextBox ID="SIG_DIAG1TextBox" runat="server" 
                Text='<%# Bind("SIG_DIAG1") %>' />
</td>
			</tr>
			<tr>
				<td>Additional Significant Diagnosis 2</td>
				<td><asp:TextBox ID="SIG_DIAG2TextBox" runat="server" 
                Text='<%# Bind("SIG_DIAG2") %>' />
</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td class="indent">Oedema</td>
				<td> <asp:CheckBox ID="OEDEMACheckBox" runat="server" 
                    Checked='<%# Bind("OEDEMA") %>' />

</td>
			</tr>
			<tr>
				<td class="indent">Anaemia</td>
				<td><asp:CheckBox ID="ANAEMIACheckBox" runat="server" 
                    Checked='<%# Bind("ANAEMIA") %>' />
</td>
			</tr>
			<tr>
				<td class="indent">Hypovalaemia</td>
				<td><asp:CheckBox ID="HYPOVALCheckBox" runat="server" 
                    Checked='<%# Bind("HYPOVAL") %>' />
</td>
			</tr>
			<tr>
				<td class="indent">Fever</td>
				<td><asp:CheckBox ID="FEVERCheckBox" runat="server" 
                    Checked='<%# Bind("FEVER") %>' />

</td>
			</tr>
			<tr>
				<td class="indent">Thrombosis</td>
				<td><asp:CheckBox ID="THROMBOSISCheckBox" runat="server" 
                    Checked='<%# Bind("THROMBOSIS") %>' />

</td>
			</tr>
			<tr>
				<td class="indent">Peritonitis</td>
				<td> <asp:CheckBox ID="PERITONITISCheckBox" runat="server" 
                    Checked='<%# Bind("PERITONITIS") %>' />

</td>
			</tr>
			<tr>
				<td class="indent">Pulmonary Oedema</td>
				<td> <asp:CheckBox ID="PUL_OEDCheckBox" runat="server" 
                    Checked='<%# Bind("PUL_OED") %>' />

</td>
			</tr>
			<tr>
				<td class="indent">Hypertension, req Rx</td>
				<td><%--<asp:TextBox ID="HTH_REQ_TMTTextBox" runat="server" 
                Text='<%# Bind("HTH_REQ_TMT") %>' />--%><asp:DropDownList ID="DropDownList3" runat="server" SelectedValue='<%# Bind("HTH_REQ_TMT") %>'>
                <asp:ListItem Value="" Selected="True">Select</asp:ListItem>
                <asp:ListItem Value="0">No</asp:ListItem>
                <asp:ListItem Value="1">Yes</asp:ListItem>
                <asp:ListItem Value="2">BP Controlled on Med'n</asp:ListItem>
                </asp:DropDownList>
</td>
			</tr>
			<tr>
				<td class="indent">Diabetes</td>
				<td><%--<asp:TextBox ID="DIABETESTextBox" runat="server" 
                Text='<%# Bind("DIABETES") %>' />--%><asp:DropDownList ID="DropDownList4" runat="server" SelectedValue='<%# Bind("DIABETES") %>'>
                <asp:ListItem Value="" Selected="True">Select</asp:ListItem>
                <asp:ListItem Value="1">Type I IDDM</asp:ListItem>
                <asp:ListItem Value="2">Type II NIDDM</asp:ListItem>
                </asp:DropDownList>
</td>
			</tr>
			<tr>
				<td class="indent">
                    <asp:Label ID="lblU" runat="server" Text="Urticaria"></asp:Label>
                </td>
				<td><%--<asp:TextBox ID="URICARIATextBox" runat="server" 
                Text='<%# Bind("URTICARIA") %>' Height="22px" />--%><asp:DropDownList ID="DropDownList5" runat="server" SelectedValue='<%# Bind("URTICARIA") %>'>
                    <asp:ListItem Value="" Selected="True">Select</asp:ListItem>
                    <asp:ListItem Value="0">No</asp:ListItem>
                    <asp:ListItem Value="1">Yes</asp:ListItem>
                    <asp:ListItem Value="8">Equivocal</asp:ListItem>
                </asp:DropDownList>
</td>
			</tr>
			<tr id="trR" runat="server">
				<td class="indent">
                    <asp:Label ID="lblRash" runat="server" Text="Rash"></asp:Label>
                </td>
				<td> <asp:TextBox ID="RASHTextBox" runat="server" Text='<%# Bind("RASH") %>' 
                        Rows="3" TextMode="MultiLine" Width="198px" />
</td>
			</tr>
			<tr>
				<td class="indent">Immunisation trigger</td>
				<td><asp:CheckBox ID="IMMUNIS_TRIGGERCheckBox" runat="server" 
                    Checked='<%# Bind("IMMUNIS_TRIGGER") %>' />
</td>
			</tr>
			<tr id="trOpth" runat="server">
				<td class="indent">
                    <asp:Label ID="lblOpthal" runat="server" Text="Opthalmoscopy"></asp:Label>
                </td>
				<td>
                    <asp:CheckBox ID="OPTHALMCheckBox" runat="server" 
                Checked='<%# Bind("OPTHALM") %>' />
                </td>
			</tr>
			<tr>
				<td colspan="2" class="indent">
                    <asp:TextBox ID="OPTHALM_DETAILTextBox" runat="server" 
                    Text='<%# Bind("OPTHALM_DETAIL") %>' Rows="5" TextMode="MultiLine" 
                        Visible="false" Width="369px" />
</td>
			</tr>
			<tr>
				<td><asp:Label ID="cIDLabel1" runat="server" Text='<%# Eval("cID") %>' 
                        Visible="False" />
</td>
				<td>&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
</table>

            <%--cID:
            <asp:Label ID="cIDLabel1" runat="server" Text='<%# Eval("cID") %>' />
            <br />
            RADAR_NO:
            <asp:TextBox ID="RADAR_NOTextBox" runat="server" 
                Text='<%# Bind("RADAR_NO") %>' />
            <br />
            DATE_CLIN_PIC:
            <asp:TextBox ID="DATE_CLIN_PICTextBox" runat="server" 
                Text='<%# Bind("DATE_CLIN_PIC") %>' />
            <br />
            HEIGHT:
            <asp:TextBox ID="HEIGHTTextBox" runat="server" Text='<%# Bind("HEIGHT") %>' />
            <br />
            WEIGHT:
            <asp:TextBox ID="WEIGHTTextBox" runat="server" Text='<%# Bind("WEIGHT") %>' />
            <br />
            DATE_BX:
            <asp:TextBox ID="DATE_BXTextBox" runat="server" Text='<%# Bind("DATE_BX") %>' />
            <br />
            MAP_BP:
            <asp:TextBox ID="MAP_BPTextBox" runat="server" Text='<%# Bind("MAP_BP") %>' />
            <br />
            DIALYSIS_REQ:
            <asp:TextBox ID="DIALYSIS_REQTextBox" runat="server" 
                Text='<%# Bind("DIALYSIS_REQ") %>' />
            <br />
            DIA_BP:
            <asp:TextBox ID="DIA_BPTextBox" runat="server" Text='<%# Bind("DIA_BP") %>' />
            <br />
            SYS_BP:
            <asp:TextBox ID="SYS_BPTextBox" runat="server" Text='<%# Bind("SYS_BP") %>' />
            <br />
            COURSE_DIS:
            <asp:TextBox ID="COURSE_DISTextBox" runat="server" 
                Text='<%# Bind("COURSE_DIS") %>' />
            <br />
            PERITONITIS:
            <asp:TextBox ID="PERITONITISTextBox" runat="server" 
                Text='<%# Bind("PERITONITIS") %>' />
            <br />
            DIABETES:
            <asp:TextBox ID="DIABETESTextBox" runat="server" 
                Text='<%# Bind("DIABETES") %>' />
            <br />
            OPTHALM_MAC_DRUS:
            <asp:TextBox ID="OPTHALM_MAC_DRUSTextBox" runat="server" 
                Text='<%# Bind("OPTHALM_MAC_DRUS") %>' />
            <br />
            SIG_DIAG1:
            <asp:TextBox ID="SIG_DIAG1TextBox" runat="server" 
                Text='<%# Bind("SIG_DIAG1") %>' />
            <br />
            CHRON_INF_CLINEV:
            <asp:TextBox ID="CHRON_INF_CLINEVTextBox" runat="server" 
                Text='<%# Bind("CHRON_INF_CLINEV") %>' />
            <br />
            URICARIA:
            <asp:TextBox ID="URICARIATextBox" runat="server" 
                Text='<%# Bind("URICARIA") %>' />
            <br />
            VIRAL_INF:
            <asp:TextBox ID="VIRAL_INFTextBox" runat="server" 
                Text='<%# Bind("VIRAL_INF") %>' />
            <br />
            BACT_INF:
            <asp:TextBox ID="BACT_INFTextBox" runat="server" 
                Text='<%# Bind("BACT_INF") %>' />
            <br />
            HTH_REQ_TMT:
            <asp:TextBox ID="HTH_REQ_TMTTextBox" runat="server" 
                Text='<%# Bind("HTH_REQ_TMT") %>' />
            <br />
            PUL_OED:
            <asp:TextBox ID="PUL_OEDTextBox" runat="server" Text='<%# Bind("PUL_OED") %>' />
            <br />
            PREC_INF:
            <asp:TextBox ID="PREC_INFTextBox" runat="server" 
                Text='<%# Bind("PREC_INF") %>' />
            <br />
            RASH:
            <asp:TextBox ID="RASHTextBox" runat="server" Text='<%# Bind("RASH") %>' />
            <br />
            PART_LIP_DIS:
            <asp:TextBox ID="PART_LIP_DISTextBox" runat="server" 
                Text='<%# Bind("PART_LIP_DIS") %>' />
            <br />
            COMMENTS:
            <asp:TextBox ID="COMMENTSTextBox" runat="server" 
                Text='<%# Bind("COMMENTS") %>' />
            <br />
            SIG_DIAG2:
            <asp:TextBox ID="SIG_DIAG2TextBox" runat="server" 
                Text='<%# Bind("SIG_DIAG2") %>' />
            <br />
            THROMBOSIS:
            <asp:TextBox ID="THROMBOSISTextBox" runat="server" 
                Text='<%# Bind("THROMBOSIS") %>' />
            <br />
            FEVER:
            <asp:TextBox ID="FEVERTextBox" runat="server" Text='<%# Bind("FEVER") %>' />
            <br />
            HYPOVAL:
            <asp:TextBox ID="HYPOVALTextBox" runat="server" Text='<%# Bind("HYPOVAL") %>' />
            <br />
            ANAEMIA:
            <asp:TextBox ID="ANAEMIATextBox" runat="server" Text='<%# Bind("ANAEMIA") %>' />
            <br />
            OEDEMA:
            <asp:TextBox ID="OEDEMATextBox" runat="server" Text='<%# Bind("OEDEMA") %>' />
            <br />
            HOSP_NO:
            <asp:TextBox ID="HOSP_NOTextBox" runat="server" Text='<%# Bind("HOSP_NO") %>' />
            <br />
            SNAME:
            <asp:TextBox ID="SNAMETextBox" runat="server" Text='<%# Bind("SNAME") %>' />
            <br />
            FNAME:
            <asp:TextBox ID="FNAMETextBox" runat="server" Text='<%# Bind("FNAME") %>' />
            <br />
            DOB:
            <asp:TextBox ID="DOBTextBox" runat="server" Text='<%# Bind("DOB") %>' />
            <br />
            DIAG:
            <asp:TextBox ID="DIAGTextBox" runat="server" Text='<%# Bind("DIAG") %>' />--%>
            <br />
            &nbsp;
        </EditItemTemplate>
    <%--    <InsertItemTemplate>
            RADAR_NO:
            <asp:TextBox ID="RADAR_NOTextBox" runat="server" 
                Text='<%# Bind("RADAR_NO") %>' />
            <br />
            DATE_CLIN_PIC:
            <asp:TextBox ID="DATE_CLIN_PICTextBox" runat="server" 
                Text='<%# Bind("DATE_CLIN_PIC") %>' />
            <br />
            HEIGHT:
            <asp:TextBox ID="HEIGHTTextBox" runat="server" Text='<%# Bind("HEIGHT") %>' />
            <br />
            WEIGHT:
            <asp:TextBox ID="WEIGHTTextBox" runat="server" Text='<%# Bind("WEIGHT") %>' />
            <br />
            DATE_BX:
            <asp:TextBox ID="DATE_BXTextBox" runat="server" Text='<%# Bind("DATE_BX") %>' />
            <br />
            MAP_BP:
            <asp:TextBox ID="MAP_BPTextBox" runat="server" Text='<%# Bind("MAP_BP") %>' />
            <br />
            DIALYSIS_REQ:
            <asp:TextBox ID="DIALYSIS_REQTextBox" runat="server" 
                Text='<%# Bind("DIALYSIS_REQ") %>' />
            <br />
            DIA_BP:
            <asp:TextBox ID="DIA_BPTextBox" runat="server" Text='<%# Bind("DIA_BP") %>' />
            <br />
            SYS_BP:
            <asp:TextBox ID="SYS_BPTextBox" runat="server" Text='<%# Bind("SYS_BP") %>' />
            <br />
            COURSE_DIS:
            <asp:TextBox ID="COURSE_DISTextBox" runat="server" 
                Text='<%# Bind("COURSE_DIS") %>' />
            <br />
            PERITONITIS:
            <asp:TextBox ID="PERITONITISTextBox" runat="server" 
                Text='<%# Bind("PERITONITIS") %>' />
            <br />
            DIABETES:
            <asp:TextBox ID="DIABETESTextBox" runat="server" 
                Text='<%# Bind("DIABETES") %>' />
            <br />
            OPTHALM_MAC_DRUS:
            <asp:TextBox ID="OPTHALM_MAC_DRUSTextBox" runat="server" 
                Text='<%# Bind("OPTHALM_MAC_DRUS") %>' />
            <br />
            SIG_DIAG1:
            <asp:TextBox ID="SIG_DIAG1TextBox" runat="server" 
                Text='<%# Bind("SIG_DIAG1") %>' />
            <br />
            CHRON_INF_CLINEV:
            <asp:TextBox ID="CHRON_INF_CLINEVTextBox" runat="server" 
                Text='<%# Bind("CHRON_INF_CLINEV") %>' />
            <br />
            URICARIA:
            <asp:TextBox ID="URICARIATextBox" runat="server" 
                Text='<%# Bind("URICARIA") %>' />
            <br />
            VIRAL_INF:
            <asp:TextBox ID="VIRAL_INFTextBox" runat="server" 
                Text='<%# Bind("VIRAL_INF") %>' />
            <br />
            BACT_INF:
            <asp:TextBox ID="BACT_INFTextBox" runat="server" 
                Text='<%# Bind("BACT_INF") %>' />
            <br />
            HTH_REQ_TMT:
            <asp:TextBox ID="HTH_REQ_TMTTextBox" runat="server" 
                Text='<%# Bind("HTH_REQ_TMT") %>' />
            <br />
            PUL_OED:
            <asp:TextBox ID="PUL_OEDTextBox" runat="server" Text='<%# Bind("PUL_OED") %>' />
            <br />
            PREC_INF:
            <asp:TextBox ID="PREC_INFTextBox" runat="server" 
                Text='<%# Bind("PREC_INF") %>' />
            <br />
            RASH:
            <asp:TextBox ID="RASHTextBox" runat="server" Text='<%# Bind("RASH") %>' />
            <br />
            PART_LIP_DIS:
            <asp:TextBox ID="PART_LIP_DISTextBox" runat="server" 
                Text='<%# Bind("PART_LIP_DIS") %>' />
            <br />
            COMMENTS:
            <asp:TextBox ID="COMMENTSTextBox" runat="server" 
                Text='<%# Bind("COMMENTS") %>' />
            <br />
            SIG_DIAG2:
            <asp:TextBox ID="SIG_DIAG2TextBox" runat="server" 
                Text='<%# Bind("SIG_DIAG2") %>' />
            <br />
            THROMBOSIS:
            <asp:TextBox ID="THROMBOSISTextBox" runat="server" 
                Text='<%# Bind("THROMBOSIS") %>' />
            <br />
            FEVER:
            <asp:TextBox ID="FEVERTextBox" runat="server" Text='<%# Bind("FEVER") %>' />
            <br />
            HYPOVAL:
            <asp:TextBox ID="HYPOVALTextBox" runat="server" Text='<%# Bind("HYPOVAL") %>' />
            <br />
            ANAEMIA:
            <asp:TextBox ID="ANAEMIATextBox" runat="server" Text='<%# Bind("ANAEMIA") %>' />
            <br />
            OEDEMA:
            <asp:TextBox ID="OEDEMATextBox" runat="server" Text='<%# Bind("OEDEMA") %>' />
            <br />
            HOSP_NO:
            <asp:TextBox ID="HOSP_NOTextBox" runat="server" Text='<%# Bind("HOSP_NO") %>' />
            <br />
            SNAME:
            <asp:TextBox ID="SNAMETextBox" runat="server" Text='<%# Bind("SNAME") %>' />
            <br />
            FNAME:
            <asp:TextBox ID="FNAMETextBox" runat="server" Text='<%# Bind("FNAME") %>' />
            <br />
            DOB:
            <asp:TextBox ID="DOBTextBox" runat="server" Text='<%# Bind("DOB") %>' />
            <br />
            DIAG:
            <asp:TextBox ID="DIAGTextBox" runat="server" Text='<%# Bind("DIAG") %>' />
            <br />
            <asp:LinkButton ID="InsertButton" runat="server" CausesValidation="True" 
                CommandName="Insert" Text="Insert" />
            &nbsp;<asp:LinkButton ID="InsertCancelButton" runat="server" 
                CausesValidation="False" CommandName="Cancel" Text="Cancel" />
        </InsertItemTemplate>--%>
        <%--<ItemTemplate>
            cID:
            <asp:Label ID="cIDLabel" runat="server" Text='<%# Eval("cID") %>' />
            <br />
            RADAR_NO:
            <asp:Label ID="RADAR_NOLabel" runat="server" Text='<%# Bind("RADAR_NO") %>' />
            <br />
            DATE_CLIN_PIC:
            <asp:Label ID="DATE_CLIN_PICLabel" runat="server" 
                Text='<%# Bind("DATE_CLIN_PIC") %>' />
            <br />
            HEIGHT:
            <asp:Label ID="HEIGHTLabel" runat="server" Text='<%# Bind("HEIGHT") %>' />
            <br />
            WEIGHT:
            <asp:Label ID="WEIGHTLabel" runat="server" Text='<%# Bind("WEIGHT") %>' />
            <br />
            DATE_BX:
            <asp:Label ID="DATE_BXLabel" runat="server" Text='<%# Bind("DATE_BX") %>' />
            <br />
            MAP_BP:
            <asp:Label ID="MAP_BPLabel" runat="server" Text='<%# Bind("MAP_BP") %>' />
            <br />
            DIALYSIS_REQ:
            <asp:Label ID="DIALYSIS_REQLabel" runat="server" 
                Text='<%# Bind("DIALYSIS_REQ") %>' />
            <br />
            DIA_BP:
            <asp:Label ID="DIA_BPLabel" runat="server" Text='<%# Bind("DIA_BP") %>' />
            <br />
            SYS_BP:
            <asp:Label ID="SYS_BPLabel" runat="server" Text='<%# Bind("SYS_BP") %>' />
            <br />
            COURSE_DIS:
            <asp:Label ID="COURSE_DISLabel" runat="server" 
                Text='<%# Bind("COURSE_DIS") %>' />
            <br />
            PERITONITIS:
            <asp:Label ID="PERITONITISLabel" runat="server" 
                Text='<%# Bind("PERITONITIS") %>' />
            <br />
            DIABETES:
            <asp:Label ID="DIABETESLabel" runat="server" Text='<%# Bind("DIABETES") %>' />
            <br />
            OPTHALM_MAC_DRUS:
            <asp:Label ID="OPTHALM_MAC_DRUSLabel" runat="server" 
                Text='<%# Bind("OPTHALM_MAC_DRUS") %>' />
            <br />
            SIG_DIAG1:
            <asp:Label ID="SIG_DIAG1Label" runat="server" Text='<%# Bind("SIG_DIAG1") %>' />
            <br />
            CHRON_INF_CLINEV:
            <asp:Label ID="CHRON_INF_CLINEVLabel" runat="server" 
                Text='<%# Bind("CHRON_INF_CLINEV") %>' />
            <br />
            URICARIA:
            <asp:Label ID="URICARIALabel" runat="server" Text='<%# Bind("URICARIA") %>' />
            <br />
            VIRAL_INF:
            <asp:Label ID="VIRAL_INFLabel" runat="server" Text='<%# Bind("VIRAL_INF") %>' />
            <br />
            BACT_INF:
            <asp:Label ID="BACT_INFLabel" runat="server" Text='<%# Bind("BACT_INF") %>' />
            <br />
            HTH_REQ_TMT:
            <asp:Label ID="HTH_REQ_TMTLabel" runat="server" 
                Text='<%# Bind("HTH_REQ_TMT") %>' />
            <br />
            PUL_OED:
            <asp:Label ID="PUL_OEDLabel" runat="server" Text='<%# Bind("PUL_OED") %>' />
            <br />
            PREC_INF:
            <asp:Label ID="PREC_INFLabel" runat="server" Text='<%# Bind("PREC_INF") %>' />
            <br />
            RASH:
            <asp:Label ID="RASHLabel" runat="server" Text='<%# Bind("RASH") %>' />
            <br />
            PART_LIP_DIS:
            <asp:Label ID="PART_LIP_DISLabel" runat="server" 
                Text='<%# Bind("PART_LIP_DIS") %>' />
            <br />
            COMMENTS:
            <asp:Label ID="COMMENTSLabel" runat="server" Text='<%# Bind("COMMENTS") %>' />
            <br />
            SIG_DIAG2:
            <asp:Label ID="SIG_DIAG2Label" runat="server" Text='<%# Bind("SIG_DIAG2") %>' />
            <br />
            THROMBOSIS:
            <asp:Label ID="THROMBOSISLabel" runat="server" 
                Text='<%# Bind("THROMBOSIS") %>' />
            <br />
            FEVER:
            <asp:Label ID="FEVERLabel" runat="server" Text='<%# Bind("FEVER") %>' />
            <br />
            HYPOVAL:
            <asp:Label ID="HYPOVALLabel" runat="server" Text='<%# Bind("HYPOVAL") %>' />
            <br />
            ANAEMIA:
            <asp:Label ID="ANAEMIALabel" runat="server" Text='<%# Bind("ANAEMIA") %>' />
            <br />
            OEDEMA:
            <asp:Label ID="OEDEMALabel" runat="server" Text='<%# Bind("OEDEMA") %>' />
            <br />
            HOSP_NO:
            <asp:Label ID="HOSP_NOLabel" runat="server" Text='<%# Bind("HOSP_NO") %>' />
            <br />
            SNAME:
            <asp:Label ID="SNAMELabel" runat="server" Text='<%# Bind("SNAME") %>' />
            <br />
            FNAME:
            <asp:Label ID="FNAMELabel" runat="server" Text='<%# Bind("FNAME") %>' />
            <br />
            DOB:
            <asp:Label ID="DOBLabel" runat="server" Text='<%# Bind("DOB") %>' />
            <br />
            DIAG:
            <asp:Label ID="DIAGLabel" runat="server" Text='<%# Bind("DIAG") %>' />
            <br />
            <asp:LinkButton ID="EditButton" runat="server" CausesValidation="False" 
                CommandName="Edit" Text="Edit" />
            &nbsp;<asp:LinkButton ID="DeleteButton" runat="server" CausesValidation="False" 
                CommandName="Delete" Text="Delete" />
            &nbsp;<asp:LinkButton ID="NewButton" runat="server" CausesValidation="False" 
                CommandName="New" Text="New" />
        </ItemTemplate>--%>
        <EmptyDataTemplate>You need to enter the Demographic detail first</EmptyDataTemplate>
    </asp:FormView>
    <p><asp:Label ID="lblUpdate" runat="server"></asp:Label>&nbsp;
        <asp:Label ID="lblDebug" runat="server"></asp:Label>
   <%--     </p>
    </ContentTemplate>
    </asp:UpdatePanel>--%>
    <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
    ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
    DeleteCommand="DELETE FROM [tbl_ClinicalData] WHERE [cID] = @cID" 
    InsertCommand="INSERT INTO [tbl_ClinicalData] ([RADAR_NO], [DATE_CLIN_PIC], [HEIGHT], [WEIGHT], [DATE_BX], [DIALYSIS_REQ], [MAP_BP], [DIA_BP], [SYS_BP], [COURSE_DIS], [OEDEMA], [ANAEMIA], [HYPOVAL], [PERITONITIS], [THROMBOSIS], [FEVER], [URTICARIA], [DIABETES], [INFECTION_DETAIL], [INFECTION], [HTH_REQ_TMT], [PUL_OED], [COMMENTS], [STEROID_RESIST], [IMMUNIS_TRIGGER], [OPTHALM_DETAIL], [OPTHALM], [RASH]) VALUES (@RADAR_NO, @DATE_CLIN_PIC, @HEIGHT, @WEIGHT, @DATE_BX, @DIALYSIS_REQ, @MAP_BP, @DIA_BP, @SYS_BP, @COURSE_DIS, @OEDEMA, @ANAEMIA, @HYPOVAL, @PERITONITIS, @THROMBOSIS, @FEVER, @URTICARIA, @DIABETES, @INFECTION_DETAIL, @INFECTION, @HTH_REQ_TMT, @PUL_OED, @COMMENTS, @STEROID_RESIST, @IMMUNIS_TRIGGER, @OPTHALM_DETAIL, @OPTHALM, @RASH)" 
    SelectCommand="SELECT tbl_ClinicalData.cID, tbl_ClinicalData.RADAR_NO, tbl_ClinicalData.DATE_CLIN_PIC, tbl_ClinicalData.HEIGHT, tbl_ClinicalData.WEIGHT, tbl_ClinicalData.DATE_BX, tbl_ClinicalData.DIALYSIS_REQ, tbl_ClinicalData.MAP_BP, tbl_ClinicalData.DIA_BP, tbl_ClinicalData.SYS_BP, tbl_ClinicalData.COURSE_DIS, tbl_ClinicalData.OEDEMA, tbl_ClinicalData.ANAEMIA, tbl_ClinicalData.HYPOVAL, tbl_ClinicalData.PERITONITIS, tbl_ClinicalData.THROMBOSIS, tbl_ClinicalData.FEVER, tbl_ClinicalData.URTICARIA, tbl_ClinicalData.DIABETES, tbl_ClinicalData.INFECTION_DETAIL, tbl_ClinicalData.INFECTION, tbl_ClinicalData.HTH_REQ_TMT, tbl_ClinicalData.PUL_OED, tbl_ClinicalData.COMMENTS, tbl_ClinicalData.STEROID_RESIST, tbl_ClinicalData.IMMUNIS_TRIGGER, tbl_ClinicalData.OPTHALM_DETAIL, tbl_ClinicalData.OPTHALM, tbl_ClinicalData.RASH, tbl_Demographics.HOSP_NO, tbl_Demographics.FNAME, tbl_Demographics.DOB, tbl_Demographics.SNAME, tbl_Diagnosis.SIG_DIAG1, tbl_Diagnosis.SIG_DIAG2, tbl_Diagnosis.DIAG FROM tbl_ClinicalData INNER JOIN tbl_Demographics ON tbl_ClinicalData.RADAR_NO = tbl_Demographics.RADAR_NO INNER JOIN tbl_Diagnosis ON tbl_Demographics.RADAR_NO = tbl_Diagnosis.RADAR_NO WHERE (tbl_ClinicalData.RADAR_NO = @PatientID)" 
        UpdateCommand="UPDATE [tbl_ClinicalData] SET [RADAR_NO] = @RADAR_NO, [DATE_CLIN_PIC] = @DATE_CLIN_PIC, [HEIGHT] = @HEIGHT, [WEIGHT] = @WEIGHT, [DATE_BX] = @DATE_BX, [DIALYSIS_REQ] = @DIALYSIS_REQ, [MAP_BP] = @MAP_BP, [DIA_BP] = @DIA_BP, [SYS_BP] = @SYS_BP, [COURSE_DIS] = @COURSE_DIS, [PERITONITIS] = @PERITONITIS, [FEVER] = @FEVER, [THROMBOSIS] = @THROMBOSIS, [HYPOVAL] = @HYPOVAL, [ANAEMIA] = @ANAEMIA, [OEDEMA] = @OEDEMA, [DIABETES] = @DIABETES, [INFECTION_DETAIL] = @INFECTION_DETAIL, [INFECTION] = @INFECTION, [HTH_REQ_TMT] = @HTH_REQ_TMT, [PUL_OED] = @PUL_OED, [URTICARIA] = @URTICARIA, [COMMENTS] = @COMMENTS, [STEROID_RESIST] = @STEROID_RESIST, [IMMUNIS_TRIGGER] = @IMMUNIS_TRIGGER, [OPTHALM_DETAIL] = @OPTHALM_DETAIL, [OPTHALM] = @OPTHALM, [RASH] = @RASH WHERE [cID] = @cID">
            <SelectParameters>
                <asp:SessionParameter Name="PatientID" SessionField="PatientID" />
            </SelectParameters>
            <DeleteParameters>
                <asp:Parameter Name="cID" Type="Int32" />
            </DeleteParameters>
            <UpdateParameters>
            <asp:Parameter Name="RADAR_NO" Type="Int32" />
            <asp:Parameter Name="DATE_CLIN_PIC" Type="DateTime" />
            <asp:Parameter Name="HEIGHT" Type="Int32" />
            <asp:Parameter Name="WEIGHT" Type="Int32" />
            <asp:Parameter Name="DATE_BX" Type="DateTime" />
            <asp:Parameter Name="DIALYSIS_REQ" Type="Int32" />
            <asp:Parameter Name="MAP_BP" Type="Int32" />
            <asp:Parameter Name="DIA_BP" Type="Int32" />
            <asp:Parameter Name="SYS_BP" Type="Int32" />
            <asp:Parameter Name="COURSE_DIS" Type="Int32" />
            <asp:Parameter Name="PERITONITIS" Type="Boolean" />
            <asp:Parameter Name="FEVER" Type="Boolean" />
            <asp:Parameter Name="THROMBOSIS" Type="Boolean" />
            <asp:Parameter Name="HYPOVAL" Type="Boolean" />
            <asp:Parameter Name="ANAEMIA" Type="Boolean" />
            <asp:Parameter Name="OEDEMA" Type="Boolean" />
            <asp:Parameter Name="DIABETES" Type="Int16" />
            <asp:Parameter Name="INFECTION_DETAIL" Type="String" />
            <asp:Parameter Name="INFECTION" Type="Int16" />
            <asp:Parameter Name="HTH_REQ_TMT" Type="Int16" />
            <asp:Parameter Name="PUL_OED" Type="Boolean" />
            <asp:Parameter Name="URTICARIA" Type="Int16" />
            <asp:Parameter Name="COMMENTS" Type="String" />
            <asp:Parameter Name="STEROID_RESIST" Type="Boolean" />
            <asp:Parameter Name="IMMUNIS_TRIGGER" Type="Boolean" />
            <asp:Parameter Name="OPTHALM_DETAIL" Type="String" />
            <asp:Parameter Name="OPTHALM" Type="Boolean" />
            <asp:Parameter Name="RASH" Type="String" />
            <asp:Parameter Name="cID" Type="Int32" />
        </UpdateParameters>
            <InsertParameters>
                <asp:Parameter Name="RADAR_NO" Type="Int32" />
                <asp:Parameter Name="DATE_CLIN_PIC" Type="DateTime" />
                <asp:Parameter Name="HEIGHT" Type="Int32" />
                <asp:Parameter Name="WEIGHT" Type="Int32" />
                <asp:Parameter Name="DATE_BX" Type="DateTime" />
                <asp:Parameter Name="DIALYSIS_REQ" Type="Int32" />
                <asp:Parameter Name="MAP_BP" Type="Int32" />
                <asp:Parameter Name="DIA_BP" Type="Int32" />
                <asp:Parameter Name="SYS_BP" Type="Int32" />
                <asp:Parameter Name="COURSE_DIS" Type="Int32" />
                <asp:Parameter Name="OEDEMA" Type="Boolean" />
                <asp:Parameter Name="ANAEMIA" Type="Boolean" />
                <asp:Parameter Name="HYPOVAL" Type="Boolean" />
                <asp:Parameter Name="PERITONITIS" Type="Boolean" />
                <asp:Parameter Name="THROMBOSIS" Type="Boolean" />
                <asp:Parameter Name="FEVER" Type="Boolean" />
                <asp:Parameter Name="URTICARIA" Type="Int16" />
                <asp:Parameter Name="DIABETES" Type="Int16" />
                <asp:Parameter Name="INFECTION_DETAIL" Type="String" />
                <asp:Parameter Name="INFECTION" Type="Int16" />
                <asp:Parameter Name="HTH_REQ_TMT" Type="Int16" />
                <asp:Parameter Name="PUL_OED" Type="Boolean" />
                <asp:Parameter Name="COMMENTS" Type="String" />
                <asp:Parameter Name="STEROID_RESIST" Type="Boolean" />
                <asp:Parameter Name="IMMUNIS_TRIGGER" Type="Boolean" />
                <asp:Parameter Name="OPTHALM_DETAIL" Type="String" />
                <asp:Parameter Name="OPTHALM" Type="Boolean" />
                <asp:Parameter Name="RASH" Type="String" />
            </InsertParameters>
        </asp:SqlDataSource>

    
    <%--<asp:SqlDataSource ID="SqlDataSource1" runat="server" 
        ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
        DeleteCommand="DELETE FROM [tbl_ClinicalData] WHERE [cID] = @cID" 
        InsertCommand="INSERT INTO [tbl_ClinicalData] ([RADAR_NO], [DATE_CLIN_PIC], [HEIGHT], [WEIGHT], [DATE_BX], [MAP_BP], [DIALYSIS_REQ], [DIA_BP], [SYS_BP], [COURSE_DIS], [PERITONITIS], [DIABETES], [OPTHALM_MAC_DRUS], [SIG_DIAG1], [CHRON_INF_CLINEV], [URICARIA], [VIRAL_INF], [BACT_INF], [HTH_REQ_TMT], [PUL_OED], [PREC_INF], [RASH], [PART_LIP_DIS], [COMMENTS], [SIG_DIAG2], [THROMBOSIS], [FEVER], [HYPOVAL], [ANAEMIA], [OEDEMA]) VALUES (@RADAR_NO, @DATE_CLIN_PIC, @HEIGHT, @WEIGHT, @DATE_BX, @MAP_BP, @DIALYSIS_REQ, @DIA_BP, @SYS_BP, @COURSE_DIS, @PERITONITIS, @DIABETES, @OPTHALM_MAC_DRUS, @SIG_DIAG1, @CHRON_INF_CLINEV, @URICARIA, @VIRAL_INF, @BACT_INF, @HTH_REQ_TMT, @PUL_OED, @PREC_INF, @RASH, @PART_LIP_DIS, @COMMENTS, @SIG_DIAG2, @THROMBOSIS, @FEVER, @HYPOVAL, @ANAEMIA, @OEDEMA)" 
        SelectCommand="SELECT tbl_ClinicalData.cID, tbl_ClinicalData.RADAR_NO, tbl_ClinicalData.DATE_CLIN_PIC, tbl_ClinicalData.HEIGHT, tbl_ClinicalData.WEIGHT, tbl_ClinicalData.DATE_BX, tbl_ClinicalData.MAP_BP, tbl_ClinicalData.DIALYSIS_REQ, tbl_ClinicalData.DIA_BP, tbl_ClinicalData.SYS_BP, tbl_ClinicalData.COURSE_DIS, tbl_ClinicalData.PERITONITIS, tbl_ClinicalData.DIABETES, tbl_ClinicalData.OPTHALM_MAC_DRUS, tbl_ClinicalData.SIG_DIAG1, tbl_ClinicalData.CHRON_INF_CLINEV, tbl_ClinicalData.URICARIA, tbl_ClinicalData.VIRAL_INF, tbl_ClinicalData.BACT_INF, tbl_ClinicalData.HTH_REQ_TMT, tbl_ClinicalData.PUL_OED, tbl_ClinicalData.PREC_INF, tbl_ClinicalData.RASH, tbl_ClinicalData.PART_LIP_DIS, tbl_ClinicalData.COMMENTS, tbl_ClinicalData.SIG_DIAG2, tbl_ClinicalData.THROMBOSIS, tbl_ClinicalData.FEVER, tbl_ClinicalData.HYPOVAL, tbl_ClinicalData.ANAEMIA, tbl_ClinicalData.OEDEMA, tbl_Demographics.HOSP_NO, tbl_Demographics.SNAME, tbl_Demographics.FNAME, tbl_Demographics.DOB, tbl_Diagnosis.DIAG FROM tbl_ClinicalData INNER JOIN tbl_Demographics ON tbl_ClinicalData.RADAR_NO = tbl_Demographics.RADAR_NO INNER JOIN tbl_Diagnosis ON tbl_Demographics.RADAR_NO = tbl_Diagnosis.RADAR_NO WHERE (tbl_ClinicalData.RADAR_NO = @RADAR_NO)" 
        UpdateCommand="UPDATE [tbl_ClinicalData] SET [RADAR_NO] = @RADAR_NO, [DATE_CLIN_PIC] = @DATE_CLIN_PIC, [HEIGHT] = @HEIGHT, [WEIGHT] = @WEIGHT, [DATE_BX] = @DATE_BX, [MAP_BP] = @MAP_BP, [DIALYSIS_REQ] = @DIALYSIS_REQ, [DIA_BP] = @DIA_BP, [SYS_BP] = @SYS_BP, [COURSE_DIS] = @COURSE_DIS, [PERITONITIS] = @PERITONITIS, [DIABETES] = @DIABETES, [OPTHALM_MAC_DRUS] = @OPTHALM_MAC_DRUS, [SIG_DIAG1] = @SIG_DIAG1, [CHRON_INF_CLINEV] = @CHRON_INF_CLINEV, [URICARIA] = @URICARIA, [VIRAL_INF] = @VIRAL_INF, [BACT_INF] = @BACT_INF, [HTH_REQ_TMT] = @HTH_REQ_TMT, [PUL_OED] = @PUL_OED, [PREC_INF] = @PREC_INF, [RASH] = @RASH, [PART_LIP_DIS] = @PART_LIP_DIS, [COMMENTS] = @COMMENTS, [SIG_DIAG2] = @SIG_DIAG2, [THROMBOSIS] = @THROMBOSIS, [FEVER] = @FEVER, [HYPOVAL] = @HYPOVAL, [ANAEMIA] = @ANAEMIA, [OEDEMA] = @OEDEMA WHERE [cID] = @cID">
        <SelectParameters>
            <asp:SessionParameter Name="RADAR_NO" SessionField="patientID" Type="Int32" />
        </SelectParameters>
        <DeleteParameters>
            <asp:Parameter Name="cID" Type="Int32" />
        </DeleteParameters>
        <UpdateParameters>
            <asp:Parameter Name="RADAR_NO" Type="Int32" />
            <asp:Parameter Name="DATE_CLIN_PIC" Type="DateTime" />
            <asp:Parameter Name="HEIGHT" Type="Int32" />
            <asp:Parameter Name="WEIGHT" Type="Int32" />
            <asp:Parameter Name="DATE_BX" Type="DateTime" />
            <asp:Parameter Name="MAP_BP" Type="Int32" />
            <asp:Parameter Name="DIALYSIS_REQ" Type="Int32" />
            <asp:Parameter Name="DIA_BP" Type="Int32" />
            <asp:Parameter Name="SYS_BP" Type="Int32" />
            <asp:Parameter Name="COURSE_DIS" Type="Int32" />
            <asp:Parameter Name="PERITONITIS" Type="Int16" />
            <asp:Parameter Name="DIABETES" Type="Int16" />
            <asp:Parameter Name="OPTHALM_MAC_DRUS" Type="String" />
            <asp:Parameter Name="SIG_DIAG1" Type="String" />
            <asp:Parameter Name="CHRON_INF_CLINEV" Type="String" />
            <asp:Parameter Name="URICARIA" Type="Int16" />
            <asp:Parameter Name="VIRAL_INF" Type="Int16" />
            <asp:Parameter Name="BACT_INF" Type="Int16" />
            <asp:Parameter Name="HTH_REQ_TMT" Type="Int16" />
            <asp:Parameter Name="PUL_OED" Type="Int16" />
            <asp:Parameter Name="PREC_INF" Type="String" />
            <asp:Parameter Name="RASH" Type="String" />
            <asp:Parameter Name="PART_LIP_DIS" Type="Int16" />
            <asp:Parameter Name="COMMENTS" Type="String" />
            <asp:Parameter Name="SIG_DIAG2" Type="String" />
            <asp:Parameter Name="THROMBOSIS" Type="Int16" />
            <asp:Parameter Name="FEVER" Type="Int16" />
            <asp:Parameter Name="HYPOVAL" Type="Int32" />
            <asp:Parameter Name="ANAEMIA" Type="Int32" />
            <asp:Parameter Name="OEDEMA" Type="Int32" />
            <asp:Parameter Name="cID" Type="Int32" />
        </UpdateParameters>
        <InsertParameters>
            <asp:Parameter Name="RADAR_NO" Type="Int32" />
            <asp:Parameter Name="DATE_CLIN_PIC" Type="DateTime" />
            <asp:Parameter Name="HEIGHT" Type="Int32" />
            <asp:Parameter Name="WEIGHT" Type="Int32" />
            <asp:Parameter Name="DATE_BX" Type="DateTime" />
            <asp:Parameter Name="MAP_BP" Type="Int32" />
            <asp:Parameter Name="DIALYSIS_REQ" Type="Int32" />
            <asp:Parameter Name="DIA_BP" Type="Int32" />
            <asp:Parameter Name="SYS_BP" Type="Int32" />
            <asp:Parameter Name="COURSE_DIS" Type="Int32" />
            <asp:Parameter Name="PERITONITIS" Type="Int16" />
            <asp:Parameter Name="DIABETES" Type="Int16" />
            <asp:Parameter Name="OPTHALM_MAC_DRUS" Type="String" />
            <asp:Parameter Name="SIG_DIAG1" Type="String" />
            <asp:Parameter Name="CHRON_INF_CLINEV" Type="String" />
            <asp:Parameter Name="URICARIA" Type="Int16" />
            <asp:Parameter Name="VIRAL_INF" Type="Int16" />
            <asp:Parameter Name="BACT_INF" Type="Int16" />
            <asp:Parameter Name="HTH_REQ_TMT" Type="Int16" />
            <asp:Parameter Name="PUL_OED" Type="Int16" />
            <asp:Parameter Name="PREC_INF" Type="String" />
            <asp:Parameter Name="RASH" Type="String" />
            <asp:Parameter Name="PART_LIP_DIS" Type="Int16" />
            <asp:Parameter Name="COMMENTS" Type="String" />
            <asp:Parameter Name="SIG_DIAG2" Type="String" />
            <asp:Parameter Name="THROMBOSIS" Type="Int16" />
            <asp:Parameter Name="FEVER" Type="Int16" />
            <asp:Parameter Name="HYPOVAL" Type="Int32" />
            <asp:Parameter Name="ANAEMIA" Type="Int32" />
            <asp:Parameter Name="OEDEMA" Type="Int32" />
        </InsertParameters>
    </asp:SqlDataSource>--%>
</asp:Content>

