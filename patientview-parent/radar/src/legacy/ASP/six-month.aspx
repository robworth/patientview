<%@ Page Title="Renal RADAR - Follow Up" Language="VB" MasterPageFile="~/MasterPage.master" AutoEventWireup="false" CodeFile="six-month.aspx.vb" Inherits="six_month" %>
<%@ Register assembly="DevExpress.Web.ASPxEditors.v9.2, Version=9.2.4.0, Culture=neutral, PublicKeyToken=b88d1754d700e49a" namespace="DevExpress.Web.ASPxEditors" tagprefix="dxe" %>

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

#tblHeader {
background-color: #b6df9f;
}

#tblMain {
background-color:#EEF6ED;
}

#tblMain td {
vertical-align:top;
}

#mainBlock table {
margin: 0;
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
    <li><a href="patient-entry2.aspx" title="Demographics"><span >Demographics</span></a></li> 
    <li><asp:hyperlink runat="server" ID="lnkDiagnosis" NavigateUrl="diagnosis2.aspx" ToolTip="Diagnosis"><span>Diagnosis</span></asp:hyperlink></li> 
<%--    <li><asp:hyperlink runat="server" ID="lnkClinical" NavigateUrl="clinical3.aspx" Tooltip="Clinical Picture"><span>Clinical Picture</span></asp:hyperlink></li> 
--%>    <li><asp:hyperlink runat="server" ID="lnkLabResults" NavigateUrl="first-clinical.aspx" Tooltip="First Visit"><span>First Visit</span></asp:hyperlink></li> 
    <li><asp:hyperlink runat="server" ID="lnk3Months" NavigateUrl="#" Tooltip="Follow Up"><span class="hovered">Follow Up</span></asp:hyperlink></li> 
  </ul> 
</div> 
<div id="mainBlock">
<table cellpadding="4" class="style1" style="width: 100%" id="tblHeader">
		<tr>
			<td>
                <h3>
                    6 Month Follow Up</h3>
            </td>
			<td style="width:100px; text-align:right;">RADAR No:</td>
			<td  style="width:120px;">
			<asp:TextBox id="txtRADAR_NO" runat="server"></asp:TextBox>
			</td>
		</tr>
		<tr>
			<td>Name
			<asp:TextBox id="txtFNAME" runat="server" Width="100px"></asp:TextBox>
			&nbsp;<asp:TextBox id="txtSNAME" runat="server" Width="120px"></asp:TextBox>
			&nbsp; DoB
			<asp:TextBox id="txtDOB" runat="server" Width="80px"></asp:TextBox>
			</td>
			<td style="width:100px; text-align:right;" >Hospital No:</td>
			<td style="width:120px;">
			<asp:TextBox id="txtHOSP_NO" runat="server"></asp:TextBox>
			</td>
		</tr>
		<tr>
			<td>Date of Treatment record &nbsp;<dxe:ASPxDateEdit ID="ASPxDateEdit1" 
                    runat="server" CssClass="inline">
                </dxe:ASPxDateEdit>
            </td>
			<td style="width:100px; text-align:right;">Diagnosis</td>
			<td>
                <asp:TextBox ID="txtDIAG" runat="server"></asp:TextBox>
            </td>
		</tr>
		<tr>
			<td colspan="3">Current Renal treatment Modality
			<asp:TextBox id="txtTMT_MODALITY" runat="server" Width="100px"></asp:TextBox>
			&nbsp; Dialysis type
			<asp:TextBox id="txtDIAL_TYPE" runat="server" Width="100px"></asp:TextBox>
			&nbsp;Transplant Recurrence
			<asp:CheckBox id="chkTRANS_RECURR" runat="server" BorderColor="Transparent" 
                    BorderStyle="None" BorderWidth="0px" />
			</td>
		</tr>
	</table>
	<table cellspacing="4" class="style1" style="width: 100%" id="tblMain">
	<tr>
		<td style="vertical-align:top; width:60%">
		<table cellpadding="4" class="style1" style="width: 100%; border:1px solid orange; margin-top:20px;">
			<tr>
				<td colspan="5" >Log ALL treatments given in the last six months</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td><strong>HD</strong></td>
				<td><strong>PD</strong></td>
					<td colspan="2"><strong>Plasmapherisis</strong></td>
					<td colspan="2"><strong>Transplant</strong></td>
				</tr>
				<tr>
					<td>Date started</td>
					<td>Date started</td>
					<td>Date started</td>
					<td>&nbsp;</td>
					<td>Date</td>
					<td>
                        <dxe:ASPxDateEdit ID="ASPxDateEdit5" runat="server" Width="100px" AutoResizeWithContainer="true">
                        </dxe:ASPxDateEdit>
                    </td>
				</tr>
				<tr>
					<td>
                        <dxe:ASPxDateEdit ID="ASPxDateEdit2" runat="server" Width="100px">
                        </dxe:ASPxDateEdit>
                    </td>
					<td>
                        <dxe:ASPxDateEdit ID="ASPxDateEdit3" runat="server" Width="100px">
                        </dxe:ASPxDateEdit>
                    </td>
					<td>
                        <dxe:ASPxDateEdit ID="ASPxDateEdit4" runat="server" Width="100px">
                        </dxe:ASPxDateEdit>
                    </td>
					<td>&nbsp;</td>
					<td>Type</td>
					<td>
					<asp:TextBox id="txtTRANS_TYPE" runat="server"></asp:TextBox>
											</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>Recurr</td>
					<td>
					<asp:CheckBox id="chkTRANS_RECURR2" runat="server" />
					</td>
				</tr>
			</table>
			
				<table cellpadding="0" cellspacing="4" class="style1" style="width: 100%; margin-top:30px;">
					<tr>
						<td style="vertical-align:top; width:50%">
						<table cellpadding="4" class="style1" style="width: 100%">
							<tr>
								<td>Significant change in status</td>
								<td>
								<asp:TextBox id="txtSIG_CHANGE_STATUS" runat="server" Width="100px"></asp:TextBox>
													</td>
							</tr>
							<tr>
								<td>Transplant rejection</td>
								<td>
                                    <dxe:ASPxDateEdit ID="ASPxDateEdit6" runat="server" Width="100px">
                                    </dxe:ASPxDateEdit>
                                </td>
							</tr>
							<tr>
								<td>Date of Biopsy</td>
								<td>
                                    <dxe:ASPxDateEdit ID="ASPxDateEdit7" runat="server" Width="100px">
                                    </dxe:ASPxDateEdit>
                                </td>
							</tr>
							<tr>
								<td>Date Nephrectomy</td>
								<td>
                                    <dxe:ASPxDateEdit ID="ASPxDateEdit8" runat="server" Width="100px">
                                    </dxe:ASPxDateEdit>
                                </td>
							</tr>
						</table>
						
						<table cellpadding="4" class="style1" style="width: 100%; margin-top:30px;">
							<tr>
								<td colspan="2"><strong>ADVERSE EVENTS</strong></td>
							</tr>
							<tr>
								<td colspan="2">Since last seen</td>
							</tr>
							<tr>
								<td>1</td>
								<td>
								<asp:DropDownList id="ddlCOMP1" runat="server" DataSourceID="SqlDataSource1" DataTextField="cmpDesc" DataValueField="cmpID" AppendDataBoundItems="true">
								<asp:ListItem Value="" Selected="True">Select</asp:ListItem>
								</asp:DropDownList>
								</td>
							</tr>
							<tr>
								<td>2</td>
								<td>
								<asp:DropDownList id="ddlCOMP2" runat="server" DataSourceID="SqlDataSource1" DataTextField="cmpDesc" DataValueField="cmpID" AppendDataBoundItems="true">
								<asp:ListItem Value="" Selected="True">Select</asp:ListItem>
								</asp:DropDownList>
								</td>
							</tr>
							<tr>
								<td>3</td>
								<td>
								<asp:DropDownList id="ddlCOMP3" runat="server" DataSourceID="SqlDataSource1" DataTextField="cmpDesc" DataValueField="cmpID" AppendDataBoundItems="true">
								<asp:ListItem Value="" Selected="True">Select</asp:ListItem>
								</asp:DropDownList>
								</td>
							</tr>
							<tr>
								<td>4</td>
								<td>
								<asp:DropDownList id="ddlCOMP4" runat="server" DataSourceID="SqlDataSource1" DataTextField="cmpDesc" DataValueField="cmpID" AppendDataBoundItems="true">
								<asp:ListItem Value="" Selected="True">Select</asp:ListItem>
								</asp:DropDownList>
								</td>
							</tr>
							<tr>
								<td>Other</td>
								<td>
								<asp:TextBox id="txtOTHER_COMP" runat="server"></asp:TextBox>
								</td>
							</tr>
						</table>
						
						</td>
						<td style="vertical-align:top">
						<table cellpadding="4" cellspacing="4" class="style1" style="width: 100%">
							<tr>
								<td colspan="2"><strong>RELAPSE pre/post treatment</strong></td>
							</tr>
							<tr>
								<td>Relapse since last</td>
								<td>
								<asp:CheckBox id="chkCHECK_RELAP_SINCE_LAST" runat="server" />
													</td>
							</tr>
							<tr>
								<td>Length of Relapse</td>
								<td>
								<asp:TextBox id="txtRELAP_LEN" runat="server" Width="70px"></asp:TextBox>
													</td>
							</tr>
							<tr>
								<td>Viral trigger</td>
								<td>
								<asp:TextBox id="txtVIRAL_TRIG" runat="server" Width="70px"></asp:TextBox>
													</td>
							</tr>
							<tr>
								<td>Immunisation Trigger</td>
								<td>
								<asp:TextBox id="txtIMMUN_TRIG" runat="server" Width="70px"></asp:TextBox>
													</td>
							</tr>
							<tr>
								<td>Other Trigger</td>
								<td>
								<asp:TextBox id="txt_OTHER_TRIG" runat="server" Width="70px"></asp:TextBox>
													</td>
							</tr>
						</table>
						</td>
					</tr>
					<tr>
						<td style="vertical-align:top; width:50%">
						&nbsp;</td>
						<td style="vertical-align:top">
						&nbsp;</td>
					</tr>
					<tr>
						<td style="vertical-align:top; width:50%">
						&nbsp;</td>
						<td style="vertical-align:top">
						&nbsp;</td>
					</tr>
					<tr>
						<td style="vertical-align:top; width:50%">
						&nbsp;</td>
						<td style="vertical-align:top">
						&nbsp;</td>
					</tr>
					<tr>
						<td style="vertical-align:top; width:50%">
						&nbsp;</td>
						<td style="vertical-align:top">
						&nbsp;</td>
					</tr>
					<tr>
						<td style="vertical-align:top; width:50%">
						<asp:Button id="btnUpdate" runat="server" Text="Update" CausesValidation="true" />
						
						</td>
						<td style="vertical-align:top">
						&nbsp;</td>
					</tr>
				</table>
			
			</td>
		<td style="vertical-align:top">
		<table cellpadding="4" class="style1" style="width: 100%">
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2"><strong>Drugs</strong></td>
			</tr>
			<tr>
				<td style="width:200px">Corticosteroids</td>
				<td>
				<asp:CheckBox id="chkCORTICOSTER" runat="server" />
											</td>
			</tr>
			<tr>
				<td>NSAID</td>
				<td>
				<asp:CheckBox id="chkNSAID" runat="server" />
											</td>
			</tr>
			<tr>
				<td>Diuretic</td>
				<td>
				<asp:CheckBox id="chkDIURETIC" runat="server" />
											</td>
			</tr>
			<tr>
				<td>Antihypertensive</td>
				<td>
				<asp:DropDownList id="ddlANTI_HTN" runat="server">
					<asp:listitem Selected="True" Value="">Select</asp:listitem>
					<asp:listitem Value="0">No</asp:listitem>
					<asp:listitem Value="1">Yes</asp:listitem>
					<asp:listitem Value="9">Unknown</asp:listitem>
				</asp:DropDownList>
				</td>
			</tr>
			<tr>
				<td><asp:Label ID="lblACE" text="ACE Inhibitor" runat="server"></asp:Label></td>
				<td>
				<asp:CheckBox id="chkACE_INHIB" runat="server" />
											</td>
			</tr>
			<tr>
				<td><asp:Label ID="lblATR" text="ATR-1" runat="server"></asp:Label></td>
				<td>
				<asp:CheckBox id="chkATR_ANTAG" runat="server" />
											</td>
			</tr>
			<tr>
				<td><asp:Label ID="lblCCB" Text="Calcium Channel Blocker" runat="server"></asp:Label></td>
				<td>
				<asp:CheckBox id="chkCA_CH_BLOCK" runat="server" />
											</td>
			</tr>
			<tr>
				<td><asp:Label ID="lblBETA" Text="Beta Blocker" runat="server"></asp:Label></td>
				<td>
				<asp:CheckBox id="chkB_BLOCK" runat="server" />
											</td>
			</tr>
			<tr>
				<td><asp:label ID="lblOther" Text="Other Antihypertensive" runat="server"></asp:label></td>
				<td>
				<asp:CheckBox id="chkOTHER_ANTI_HTN" runat="server" />
											</td>
			</tr>
			<tr>
				<td>Insulin</td>
				<td>
				<asp:DropDownList id="ddlINSULIN" runat="server">
				<asp:listitem Selected="True" Value="">Select</asp:listitem>
					<asp:listitem Value="0">No</asp:listitem>
					<asp:listitem Value="1">Yes</asp:listitem>
					<asp:listitem Value="9">Unknown</asp:listitem>

				</asp:DropDownList>
											</td>
			</tr>
			<tr>
				<td>Lipid Lowering Agent</td>
				<td>
				<asp:CheckBox id="chkLIP_LOWER_AG" runat="server" />
											</td>
			</tr>
			<tr>
				<td>Rituximab</td>
				<td>
				<asp:CheckBox id="chkRITUX" runat="server" />
				</td>
			</tr>
			<tr>
				<td>EPO</td>
				<td>
				<asp:DropDownList id="ddlEPO" runat="server">
				<asp:listitem Selected="True" Value="">Select</asp:listitem>
					<asp:listitem Value="0">No</asp:listitem>
					<asp:listitem Value="1">Yes</asp:listitem>
					<asp:listitem Value="9">Unknown</asp:listitem>
				</asp:DropDownList>
											</td>
			</tr>
			<tr>
				<td>Growth Hormone</td>
				<td>
				<asp:CheckBox id="chkGROWTH_HOR" runat="server" />
											</td>
			</tr>
			<tr>
				<td>Other 1</td>
				<td>
				<asp:TextBox id="txtOTHER_DRUG1" runat="server"></asp:TextBox>
											</td>
			</tr>
			<tr>
				<td>Other 2</td>
				<td>
				<asp:TextBox id="txtOTHER_DRUG2" runat="server"></asp:TextBox>
											</td>
			</tr>
		</table>
		
		<table cellpadding="0" cellspacing="5" class="style1" style="width: 100%; margin-top:30px;">
			<tr>
				<td style="vertical-align:top; width:50%">
				<table cellpadding="4" class="style1" style="width: 100%">
					<tr>
						<td>Max Protein /<br />
						Creatinine ratio</td>
						<td>
						<asp:TextBox id="txtMAX_PR_CREAT_RATIO" runat="server" Width="50px"></asp:TextBox>
							</td>
					</tr>
					<tr>
						<td>Max Albumin /<br />
						Creatinine ratio</td>
						<td>
						<asp:TextBox id="txtMAX_ALB_CREAT_RATIO" runat="server" Width="50px"></asp:TextBox>
							</td>
					</tr>
					<tr>
						<td>Max serum Creatinine</td>
						<td>
						<asp:TextBox id="txtMAX_SER_CREAT" runat="server" Width="50px"></asp:TextBox>
							<asp:RangeValidator id="RangeValidator1" runat="server" ErrorMessage="20-2500" MinimumValue="20" MaximumValue="2500" Type="Integer" ControlToValidate="txtMAX_SER_CREAT"></asp:RangeValidator>
							</td>
					</tr>
					<tr>
						<td>Min serum Albumin</td>
						<td>
						<asp:TextBox id="txtMN_SER_ALB" runat="server" Width="50px"></asp:TextBox>
							<asp:RangeValidator id="RangeValidator2" runat="server" ErrorMessage="10-60" MinimumValue="10" MaximumValue="60" Type="Integer" ControlToValidate="txtMN_SER_ALB"></asp:RangeValidator>
							</td>
					</tr>
				</table>
				</td>
				<td style="vertical-align:top">
				<table cellpadding="4" class="style1" style="width: 100%">
					<tr>
						<td colspan="2" class="style2"><strong>IMMUNOSUPRESSION</strong></td>
					</tr>
					<tr>
						<td>Change</td>
						<td>
						<asp:CheckBox id="chkIMMUNOSUP_INC" runat="server" />
						</td>
					</tr>
					<tr>
						<td>Type</td>
						<td>
						<asp:TextBox id="txtIMMUNOSUP_TYPE" runat="server" Width="70px"></asp:TextBox>
							</td>
					</tr>
					<tr>
						<td>Dose</td>
						<td>
						<asp:TextBox id="txtIMMUNOSUP_DOSE" runat="server" Width="70px"></asp:TextBox>
							</td>
					</tr>
					<tr>
						<td>Duration</td>
						<td>
						<asp:TextBox id="txtIMMUNOSUP_DUR" runat="server" Width="70px"></asp:TextBox>
							</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		
		</td>
	</tr>
</table>


<asp:Label ID="lblDebug" runat="server"></asp:Label>
	<asp:SqlDataSource ID="SqlDataSource1" runat="server" ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" SelectCommand="SELECT [cmpID], [cmpDesc] FROM [tbl_Complication]">
	</asp:SqlDataSource>
</div>
</asp:Content>

