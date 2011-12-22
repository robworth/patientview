<%@ Page Title="" Language="VB" MasterPageFile="~/MasterPage.master" AutoEventWireup="false" CodeFile="timelines.aspx.vb" Inherits="timelines" %>
<%@ Register assembly="DevExpress.Web.ASPxEditors.v9.2, Version=9.2.4.0, Culture=neutral, PublicKeyToken=b88d1754d700e49a" namespace="DevExpress.Web.ASPxEditors" tagprefix="dxe" %>

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
.style6
    {
        width: 100px;
    }
    
    #ctl00_ContentPlaceHolder1_GridView2 input {
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
   <%-- <asp:ScriptManager ID="ScriptManager1" runat="server">
    </asp:ScriptManager>--%>
    <div id="tabsC" > 
    <ul> 
        <li><asp:hyperlink runat="server" ID="lnkDemographics" NavigateUrl="demographics.aspx" title="Demographics"><span>Demographics</span></asp:hyperlink></li> 
        <li><asp:hyperlink runat="server" ID="lnkDiagnosis" NavigateUrl="diagnosis.aspx" ToolTip="Diagnosis"><span>Diagnosis</span></asp:hyperlink></li> 
        <li><asp:hyperlink runat="server" ID="lnkLabResults" NavigateUrl="first-clinical.aspx" Tooltip="Laboratory Results"><span>First Visit</span></asp:hyperlink></li> 
        <li><asp:hyperlink runat="server" ID="lnk3Months" NavigateUrl="followup-clinical.aspx" Tooltip="Follow Up"><span >Follow Up</span></asp:hyperlink></li> 
        <li><asp:hyperlink runat="server" ID="lnkPathology" NavigateUrl="pathology.aspx" Tootip="Pathology"><span>Pathology</span></asp:hyperlink></li> 
        <li><asp:hyperlink runat="server" ID="lnkRelapse" NavigateUrl="relapse.aspx" Tooltip="Relapse"><span>Relapse</span></asp:hyperlink></li> 
        <li><asp:hyperlink runat="server" ID="lnkHospital" NavigateUrl="#" Tooltip="Hospitalisation"><span>Hospitalisation</span></asp:hyperlink></li> 
        <li><asp:hyperlink runat="server" ID="lnkTimeLines" NavigateUrl="#" Tootip="Timelines"><span class="hovered">Timelines</span></asp:hyperlink></li> 
</ul> 
</div> 
<div id="mainBlock">
<%--<div id="tabsLab" style="margin:-20px 0 0 -40px;">
<ul>
	<li><asp:hyperlink runat="server" ID="lnkFollowupClinical" NavigateUrl="followup-clinical.aspx" Tooltip="Clinical Picture"><span>Clinical Picture</span></asp:hyperlink></li> 
    <li><asp:hyperlink runat="server" ID="lnkFollowupLab" NavigateUrl="followup-lab.aspx" ToolTip="Lab Results"><span >Lab Results</span></asp:hyperlink></li>
    <li><asp:hyperlink runat="server" ID="lnkFollowupTreatment" NavigateUrl="followup-treatment.aspx" ToolTip="Disease Treatment"><span>Disease Treatment</span></asp:hyperlink></li>
    <li><asp:hyperlink runat="server" ID="lnkTherapy" NavigateUrl="rrt_therapy.aspx" Tooltip="Therapy"><span >RRT Therapy</span></asp:hyperlink></li> 
    <li><asp:hyperlink runat="server" ID="lnkTimelines" NavigateUrl="#" Tootip="Timelines"><span class="hovered">Timelines</span></asp:hyperlink></li> 
</ul>
</div>
<div id="subBlock">--%>
<table cellpadding="4" style="width: 100%;" id="tblHeader">
		<tr>
			<td >
                <b>Timelines</b></td>
			<td style="width:100px; text-align:right;">RADAR No:</td>
			<td  style="width:160px;padding-right:10px;">
			<asp:TextBox id="txtRADAR_NO" runat="server" ReadOnly="True" Width="100px"></asp:TextBox>
			</td>
		</tr>
		<tr>
			<td>Name
			<asp:TextBox id="txtFNAME" runat="server" Width="100px" ReadOnly="True"></asp:TextBox>
			&nbsp;<asp:TextBox id="txtSNAME" runat="server" Width="120px" ReadOnly="True"></asp:TextBox>
			&nbsp; DoB
			<asp:TextBox id="txtDOB" runat="server" Width="80px"></asp:TextBox>
			    &nbsp;<asp:Label ID="lblID" runat="server" Text="" Visible="false"></asp:Label>
			</td>
			<td style="text-align:right;" >Hospital No:</td>
			<td >
			<asp:TextBox id="txtHOSP_NO" runat="server" ReadOnly="True" Width="100px"></asp:TextBox>
			</td>
		</tr>
		<tr>
			<td><%--Date of Treatment record &nbsp;<asp:TextBox ID="txtDOT" 
                    runat="server" Width="80px"></asp:TextBox>
                <cc1:CalendarExtender ID="txtDOT_CalendarExtender" runat="server" 
                    Enabled="True" TargetControlID="txtDOT" PopupButtonID="ImageButton10" Format="dd-MMM-yyyy">
                </cc1:CalendarExtender>
                <asp:ImageButton ID="ImageButton10" runat="server" 
                    CausesValidation="False" ImageUrl="~/images/Calendar_scheduleHS.png" />
                <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" 
                    ControlToValidate="txtDOT">*</asp:RequiredFieldValidator>
            &nbsp;<a href="#" class="info2"><em>Help</em><span>To select a different month in the 
                date picker, click the current month at the top.<br /><br /> Click again to select 
                a year.</span>  </a>--%>&nbsp;</td>
			<td style="text-align:right;" class="style6">Diagnosis</td>
			<td>
                <asp:TextBox ID="txtDIAG" runat="server" ReadOnly="True" BackColor="#FFFF99" 
                    Width="100px"></asp:TextBox>
            </td>
		</tr>
		<%--<tr>
			<td colspan="3">Current Renal treatment Modality
			<asp:TextBox id="txtTMT_MODALITY" runat="server" Width="100px"></asp:TextBox>
			&nbsp;
			    <asp:TextBox ID="txtDateToday" runat="server" BackColor="#B6DF9F" 
                    BorderColor="#B6DF9F" ForeColor="#B6DF9F"></asp:TextBox>
			&nbsp;</td>
		</tr>--%>
	</table>
	<table style="width: 100%; background-color:#EEF6ED; border:0px solid red;" cellpadding="0" cellspacing="10" id="tblMain" runat="server">
	<%--<tr>
	<td colspan="2" style="padding-left:10px;">Log ALL treatments given in the last six months&nbsp;</td>
	</tr>--%>
	<tr>
	<td style="width:60%; vertical-align:top; height: 168px;">
	<fieldset><legend>RRT Timeline</legend>
	<br />
        <asp:GridView ID="GridView1" runat="server" Width="100%" 
            AutoGenerateColumns="False" DataSourceID="SqlDataSource2" Font-Size="85%" CellPadding="3" HeaderStyle-ForeColor="#3c982d">
            <Columns>
                <asp:BoundField DataField="DATE_START" DataFormatString="{0:dd-MMM-yyyy}" 
                    HeaderText="Date Started" SortExpression="DATE_START" ItemStyle-Width="90" />
                <asp:BoundField DataField="DATE_STOP" DataFormatString="{0:dd-MMM-yyyy}" 
                    HeaderText="Date Stopped" SortExpression="DATE_STOP" ItemStyle-Width="90" />
                <asp:BoundField DataField="mType" HeaderText="Type" SortExpression="mType" />
                <asp:BoundField DataField="UNIT_CODE" HeaderText="Paediatric Unit code" 
                    SortExpression="UNIT_CODE" />
                <asp:BoundField DataField="MODALITY" HeaderText="MODALITY" 
                    SortExpression="MODALITY" Visible="False" />
            </Columns>
            <EmptyDataTemplate>
            <p>No data for this patient</p><br />
            </EmptyDataTemplate>

<HeaderStyle ForeColor="#3C982D"></HeaderStyle>
        </asp:GridView>
	</fieldset>
	<br />
<%--	<fieldset><legend>Drugs</legend>
	<table style="width:100%; border-collapse:collapse" cellpadding="5px">
	<tr><td></td></tr>
	<tr><td>&nbsp;</td></tr>
	<tr><td>&nbsp;</td></tr>
	</table>
	</fieldset>
--%><%--	
<fieldset><legend>Plasmapheresis</legend>
<br />
    <asp:GridView ID="GridView3" runat="server" Width="95%" 
        AutoGenerateColumns="False" DataSourceID="SqlDataSource6" Font-Size="85%" HeaderStyle-ForeColor="#3c982d" CellPadding="3" >
        <Columns>
            <asp:BoundField DataField="DATE_START_PLASMAPH" 
                HeaderText="Date Started" DataFormatString="{0:dd-MMM-yyyy}" ItemStyle-Width="90"  />
            <asp:BoundField DataField="DATE_STOP_PLASMAPH" HeaderText="Date Stopped" ItemStyle-Width="90" DataFormatString="{0:dd-MMM-yyyy}" 
                 />
            <asp:BoundField DataField="NO_EXCH_PLASMAPH" HeaderText="No of Exchanges" 
                 />
            
                  <asp:TemplateField HeaderText="Response">
                      
                     
            </asp:TemplateField>
        </Columns>
        <HeaderStyle ForeColor="#3C982D" />
        <EmptyDataTemplate>
        <p>No data for this patient</p><br />
        </EmptyDataTemplate>
    </asp:GridView>
	
	        
	
	</fieldset>
--%>	</td>
	
	</tr>
	<tr><td colspan="2">
	<fieldset><legend>Transplant</legend>
	<br />
	<asp:GridView ID="GridView2" runat="server" AutoGenerateColumns="False" 
            DataKeyNames="trID" DataSourceID="SqlDataSource7" Width="100%" CellPadding="3" >
            <HeaderStyle ForeColor="#3c982d" Font-Size="85%" HorizontalAlign="Center" />
            <RowStyle HorizontalAlign="Center" Font-Size="85%" />
            <EmptyDataRowStyle Font-Size="85%" />
            <Columns>
                <asp:BoundField DataField="trID" HeaderText="trID" InsertVisible="False" 
                    ReadOnly="True" SortExpression="trID" Visible="false" />
                <asp:BoundField DataField="RADAR_NO" HeaderText="RADAR_NO" 
                    SortExpression="RADAR_NO"  Visible="false"/>
                <asp:BoundField DataField="TRANSPLANT_COUNTER" HeaderText="TRANSPLANT_COUNTER" 
                    SortExpression="TRANSPLANT_COUNTER" Visible="false" />
                <asp:BoundField DataField="DATE_TRANSPLANT" HeaderText="Date Tx" 
                    SortExpression="DATE_TRANSPLANT" DataFormatString="{0:dd-MMM-yyyy}" ItemStyle-Width="100px" >
                	<ItemStyle Width="100px" />
				</asp:boundfield>
                <asp:BoundField DataField="trDesc" HeaderText="Type" 
                    SortExpression="trDesc" ItemStyle-HorizontalAlign="Left" HeaderStyle-HorizontalAlign="Left" />
                <asp:BoundField DataField="DATE_TX_REJECT" HeaderText="Date Reject" 
                    SortExpression="DATE_TX_REJECT" DataFormatString="{0:dd-MMM-yyyy}" ItemStyle-Width="100px">
                	<ItemStyle Width="100px" />
				</asp:boundfield>
                <asp:BoundField DataField="DATE_BX_TXK" HeaderText="Date Biopsy" 
                    SortExpression="DATE_BX_TXK" DataFormatString="{0:dd-MMM-yyyy}"  ItemStyle-Width="100px">
                    <ItemStyle Width="100px" />
				</asp:boundfield>
                <asp:CheckBoxField DataField="TRANS_RECURR" HeaderText="Recurr" 
                    SortExpression="TRANS_RECURR" ItemStyle-Width="60px" >
                	<ItemStyle Width="60px" />
				</asp:checkboxfield>
                <asp:BoundField DataField="DATE_RECURR_TXK" HeaderText="Date Recurr" 
                    SortExpression="DATE_RECURR_TXK" DataFormatString="{0:dd-MMM-yyyy}" ItemStyle-Width="100px" >
                	<ItemStyle Width="100px" />
				</asp:boundfield>
                <asp:BoundField DataField="DATE_NEPHRECT" HeaderText="Date Neph" 
                    SortExpression="DATE_NEPHRECT" DataFormatString="{0:dd-MMM-yyyy}" ItemStyle-Width="100px">
                	<ItemStyle Width="100px" />
				</asp:boundfield>
                    <%--<asp:TemplateField ItemStyle-Width="55px">
                <ItemTemplate>
                <asp:Button ID="delButton" runat="server" CausesValidation="false" CommandName="Delete" OnClientClick="return confirm('Are you sure you want to delete this record?');" Text="Delete"></asp:Button>
                </ItemTemplate>
                		<ItemStyle Width="55px" />
                </asp:TemplateField>--%>
            </Columns>
            <EmptyDataTemplate>
            <p>No data for this patient</p><br />
            </EmptyDataTemplate>
            <HeaderStyle ForeColor="#3C982D" />
        </asp:GridView>
	</fieldset>
	</td></tr>
	</table>
	<p>&nbsp;</p>
	<p>
        <asp:Label ID="lblDebug" runat="server" Text=""></asp:Label>
        <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
            ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
            SelectCommand="SELECT [trID], [trDesc] FROM [tbl_TRANSPLANT_MODALITY] ORDER BY [trID]">
        </asp:SqlDataSource>
	    <asp:SqlDataSource ID="SqlDataSource2" runat="server" 
            ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
            
            SelectCommand="SELECT tbl_RRT_MODALITY.mType, tbl_RRT_TREATMENT.DATE_START, tbl_RRT_TREATMENT.DATE_STOP, tbl_RRT_TREATMENT.UNIT_CODE, tbl_RRT_TREATMENT.MODALITY, tbl_RRT_TREATMENT.RADAR_NO FROM tbl_RRT_MODALITY INNER JOIN tbl_RRT_TREATMENT ON tbl_RRT_MODALITY.mID = tbl_RRT_TREATMENT.MODALITY WHERE (tbl_RRT_TREATMENT.MODALITY >= 1) AND (tbl_RRT_TREATMENT.MODALITY <= 19) AND (tbl_RRT_TREATMENT.RADAR_NO = @RADAR_NO) ORDER BY tbl_RRT_TREATMENT.DATE_START DESC">
            <SelectParameters>
                <asp:SessionParameter Name="RADAR_NO" SessionField="patientID" />
            </SelectParameters>
        </asp:SqlDataSource>
        
        <asp:SqlDataSource ID="SqlDataSource3" runat="server"></asp:SqlDataSource>
        
        <asp:SqlDataSource ID="SqlDataSource6" runat="server" 
        ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
        SelectCommand="SELECT [DATE_START_PLASMAPH], [DATE_STOP_PLASMAPH], [NO_EXCH_PLASMAPH], [DUR_PLASMAPH], [RESPONSE_TO_PLASMA] FROM [tbl_RRT_PLASMA] WHERE ([RADAR_NO] = @RADAR_NO) ORDER BY [DATE_START_PLASMAPH] DESC">
        <SelectParameters>
            <asp:SessionParameter Name="RADAR_NO" SessionField="patientID" Type="Int32" />
        </SelectParameters>
    </asp:SqlDataSource>
    	    <asp:SqlDataSource ID="SqlDataSource7" runat="server" 
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

    </p>
</div>
<%--</div>--%>

</asp:Content>

