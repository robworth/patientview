<%@ Page Language="VB" AutoEventWireup="false" CodeFile="six-month-form.aspx.vb" Inherits="six_month_form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    
        <asp:FormView ID="FormView1" runat="server" DataKeyNames="fuID" 
            DataSourceID="SqlDataSource1">
            <EditItemTemplate>
                fuID:
                <asp:Label ID="fuIDLabel1" runat="server" Text='<%# Eval("fuID") %>' />
                <br />
                RADAR_NO:
                <asp:TextBox ID="RADAR_NOTextBox" runat="server" 
                    Text='<%# Bind("RADAR_NO") %>' />
                <br />
                DATE_FUP:
                <asp:TextBox ID="DATE_FUPTextBox" runat="server" 
                    Text='<%# Bind("DATE_FUP") %>' />
                <br />
                RELAP_SINCE_LAST:
                <asp:CheckBox ID="RELAP_SINCE_LASTCheckBox" runat="server" 
                    Checked='<%# Bind("RELAP_SINCE_LAST") %>' />
                <br />
                RELAP_LEN:
                <asp:TextBox ID="RELAP_LENTextBox" runat="server" 
                    Text='<%# Bind("RELAP_LEN") %>' />
                <br />
                IMMUNOSUP_DUR:
                <asp:TextBox ID="IMMUNOSUP_DURTextBox" runat="server" 
                    Text='<%# Bind("IMMUNOSUP_DUR") %>' />
                <br />
                IMMUNOSUP_DOSE:
                <asp:TextBox ID="IMMUNOSUP_DOSETextBox" runat="server" 
                    Text='<%# Bind("IMMUNOSUP_DOSE") %>' />
                <br />
                IMMUNOSUP_INC:
                <asp:CheckBox ID="IMMUNOSUP_INCCheckBox" runat="server" 
                    Checked='<%# Bind("IMMUNOSUP_INC") %>' />
                <br />
                OTHER_TRIG:
                <asp:TextBox ID="OTHER_TRIGTextBox" runat="server" 
                    Text='<%# Bind("OTHER_TRIG") %>' />
                <br />
                IMMUN_TRIG:
                <asp:TextBox ID="IMMUN_TRIGTextBox" runat="server" 
                    Text='<%# Bind("IMMUN_TRIG") %>' />
                <br />
                VIRAL_TRIG:
                <asp:TextBox ID="VIRAL_TRIGTextBox" runat="server" 
                    Text='<%# Bind("VIRAL_TRIG") %>' />
                <br />
                COMP1:
                <asp:TextBox ID="COMP1TextBox" runat="server" Text='<%# Bind("COMP1") %>' />
                <br />
                MIN_SER_ALB:
                <asp:TextBox ID="MIN_SER_ALBTextBox" runat="server" 
                    Text='<%# Bind("MIN_SER_ALB") %>' />
                <br />
                MAX_PR_CREAT_RATIO:
                <asp:TextBox ID="MAX_PR_CREAT_RATIOTextBox" runat="server" 
                    Text='<%# Bind("MAX_PR_CREAT_RATIO") %>' />
                <br />
                PLASMA_EXCH_NO:
                <asp:TextBox ID="PLASMA_EXCH_NOTextBox" runat="server" 
                    Text='<%# Bind("PLASMA_EXCH_NO") %>' />
                <br />
                PLASMA_EXCH:
                <asp:TextBox ID="PLASMA_EXCHTextBox" runat="server" 
                    Text='<%# Bind("PLASMA_EXCH") %>' />
                <br />
                RESPONSE_TO:
                <asp:TextBox ID="RESPONSE_TOTextBox" runat="server" 
                    Text='<%# Bind("RESPONSE_TO") %>' />
                <br />
                DIAL_TYPE:
                <asp:TextBox ID="DIAL_TYPETextBox" runat="server" 
                    Text='<%# Bind("DIAL_TYPE") %>' />
                <br />
                DATE_BX:
                <asp:TextBox ID="DATE_BXTextBox" runat="server" Text='<%# Bind("DATE_BX") %>' />
                <br />
                DATE_TX_REJECT:
                <asp:TextBox ID="DATE_TX_REJECTTextBox" runat="server" 
                    Text='<%# Bind("DATE_TX_REJECT") %>' />
                <br />
                DRUG1:
                <asp:TextBox ID="DRUG1TextBox" runat="server" Text='<%# Bind("DRUG1") %>' />
                <br />
                TRANS_RECURR:
                <asp:CheckBox ID="TRANS_RECURRCheckBox" runat="server" 
                    Checked='<%# Bind("TRANS_RECURR") %>' />
                <br />
                TRANS_TYPE:
                <asp:TextBox ID="TRANS_TYPETextBox" runat="server" 
                    Text='<%# Bind("TRANS_TYPE") %>' />
                <br />
                DATE_TRANSPLANT:
                <asp:TextBox ID="DATE_TRANSPLANTTextBox" runat="server" 
                    Text='<%# Bind("DATE_TRANSPLANT") %>' />
                <br />
                SIG_CHANGE_STATUS:
                <asp:TextBox ID="SIG_CHANGE_STATUSTextBox" runat="server" 
                    Text='<%# Bind("SIG_CHANGE_STATUS") %>' />
                <br />
                DRUG6:
                <asp:TextBox ID="DRUG6TextBox" runat="server" Text='<%# Bind("DRUG6") %>' />
                <br />
                DRUG5:
                <asp:TextBox ID="DRUG5TextBox" runat="server" Text='<%# Bind("DRUG5") %>' />
                <br />
                DRUG4:
                <asp:TextBox ID="DRUG4TextBox" runat="server" Text='<%# Bind("DRUG4") %>' />
                <br />
                DRUG3:
                <asp:TextBox ID="DRUG3TextBox" runat="server" Text='<%# Bind("DRUG3") %>' />
                <br />
                DRUG2:
                <asp:TextBox ID="DRUG2TextBox" runat="server" Text='<%# Bind("DRUG2") %>' />
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
                HOSP_NO:
                <asp:TextBox ID="HOSP_NOTextBox" runat="server" Text='<%# Bind("HOSP_NO") %>' />
                <br />
                DIAG:
                <asp:TextBox ID="DIAGTextBox" runat="server" Text='<%# Bind("DIAG") %>' />
                <br />
                <asp:LinkButton ID="UpdateButton" runat="server" CausesValidation="True" 
                    CommandName="Update" Text="Update" />
                &nbsp;<asp:LinkButton ID="UpdateCancelButton" runat="server" 
                    CausesValidation="False" CommandName="Cancel" Text="Cancel" />
            </EditItemTemplate>
            <InsertItemTemplate>
                RADAR_NO:
                <asp:TextBox ID="RADAR_NOTextBox" runat="server" 
                    Text='<%# Bind("RADAR_NO") %>' />
                <br />
                DATE_FUP:
                <asp:TextBox ID="DATE_FUPTextBox" runat="server" 
                    Text='<%# Bind("DATE_FUP") %>' />
                <br />
                RELAP_SINCE_LAST:
                <asp:CheckBox ID="RELAP_SINCE_LASTCheckBox" runat="server" 
                    Checked='<%# Bind("RELAP_SINCE_LAST") %>' />
                <br />
                RELAP_LEN:
                <asp:TextBox ID="RELAP_LENTextBox" runat="server" 
                    Text='<%# Bind("RELAP_LEN") %>' />
                <br />
                IMMUNOSUP_DUR:
                <asp:TextBox ID="IMMUNOSUP_DURTextBox" runat="server" 
                    Text='<%# Bind("IMMUNOSUP_DUR") %>' />
                <br />
                IMMUNOSUP_DOSE:
                <asp:TextBox ID="IMMUNOSUP_DOSETextBox" runat="server" 
                    Text='<%# Bind("IMMUNOSUP_DOSE") %>' />
                <br />
                IMMUNOSUP_INC:
                <asp:CheckBox ID="IMMUNOSUP_INCCheckBox" runat="server" 
                    Checked='<%# Bind("IMMUNOSUP_INC") %>' />
                <br />
                OTHER_TRIG:
                <asp:TextBox ID="OTHER_TRIGTextBox" runat="server" 
                    Text='<%# Bind("OTHER_TRIG") %>' />
                <br />
                IMMUN_TRIG:
                <asp:TextBox ID="IMMUN_TRIGTextBox" runat="server" 
                    Text='<%# Bind("IMMUN_TRIG") %>' />
                <br />
                VIRAL_TRIG:
                <asp:TextBox ID="VIRAL_TRIGTextBox" runat="server" 
                    Text='<%# Bind("VIRAL_TRIG") %>' />
                <br />
                COMP1:
                <asp:TextBox ID="COMP1TextBox" runat="server" Text='<%# Bind("COMP1") %>' />
                <br />
                MIN_SER_ALB:
                <asp:TextBox ID="MIN_SER_ALBTextBox" runat="server" 
                    Text='<%# Bind("MIN_SER_ALB") %>' />
                <br />
                MAX_PR_CREAT_RATIO:
                <asp:TextBox ID="MAX_PR_CREAT_RATIOTextBox" runat="server" 
                    Text='<%# Bind("MAX_PR_CREAT_RATIO") %>' />
                <br />
                PLASMA_EXCH_NO:
                <asp:TextBox ID="PLASMA_EXCH_NOTextBox" runat="server" 
                    Text='<%# Bind("PLASMA_EXCH_NO") %>' />
                <br />
                PLASMA_EXCH:
                <asp:TextBox ID="PLASMA_EXCHTextBox" runat="server" 
                    Text='<%# Bind("PLASMA_EXCH") %>' />
                <br />
                RESPONSE_TO:
                <asp:TextBox ID="RESPONSE_TOTextBox" runat="server" 
                    Text='<%# Bind("RESPONSE_TO") %>' />
                <br />
                DIAL_TYPE:
                <asp:TextBox ID="DIAL_TYPETextBox" runat="server" 
                    Text='<%# Bind("DIAL_TYPE") %>' />
                <br />
                DATE_BX:
                <asp:TextBox ID="DATE_BXTextBox" runat="server" Text='<%# Bind("DATE_BX") %>' />
                <br />
                DATE_TX_REJECT:
                <asp:TextBox ID="DATE_TX_REJECTTextBox" runat="server" 
                    Text='<%# Bind("DATE_TX_REJECT") %>' />
                <br />
                DRUG1:
                <asp:TextBox ID="DRUG1TextBox" runat="server" Text='<%# Bind("DRUG1") %>' />
                <br />
                TRANS_RECURR:
                <asp:CheckBox ID="TRANS_RECURRCheckBox" runat="server" 
                    Checked='<%# Bind("TRANS_RECURR") %>' />
                <br />
                TRANS_TYPE:
                <asp:TextBox ID="TRANS_TYPETextBox" runat="server" 
                    Text='<%# Bind("TRANS_TYPE") %>' />
                <br />
                DATE_TRANSPLANT:
                <asp:TextBox ID="DATE_TRANSPLANTTextBox" runat="server" 
                    Text='<%# Bind("DATE_TRANSPLANT") %>' />
                <br />
                SIG_CHANGE_STATUS:
                <asp:TextBox ID="SIG_CHANGE_STATUSTextBox" runat="server" 
                    Text='<%# Bind("SIG_CHANGE_STATUS") %>' />
                <br />
                DRUG6:
                <asp:TextBox ID="DRUG6TextBox" runat="server" Text='<%# Bind("DRUG6") %>' />
                <br />
                DRUG5:
                <asp:TextBox ID="DRUG5TextBox" runat="server" Text='<%# Bind("DRUG5") %>' />
                <br />
                DRUG4:
                <asp:TextBox ID="DRUG4TextBox" runat="server" Text='<%# Bind("DRUG4") %>' />
                <br />
                DRUG3:
                <asp:TextBox ID="DRUG3TextBox" runat="server" Text='<%# Bind("DRUG3") %>' />
                <br />
                DRUG2:
                <asp:TextBox ID="DRUG2TextBox" runat="server" Text='<%# Bind("DRUG2") %>' />
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
                HOSP_NO:
                <asp:TextBox ID="HOSP_NOTextBox" runat="server" Text='<%# Bind("HOSP_NO") %>' />
                <br />
                DIAG:
                <asp:TextBox ID="DIAGTextBox" runat="server" Text='<%# Bind("DIAG") %>' />
                <br />
                <asp:LinkButton ID="InsertButton" runat="server" CausesValidation="True" 
                    CommandName="Insert" Text="Insert" />
                &nbsp;<asp:LinkButton ID="InsertCancelButton" runat="server" 
                    CausesValidation="False" CommandName="Cancel" Text="Cancel" />
            </InsertItemTemplate>
            <ItemTemplate>
                fuID:
                <asp:Label ID="fuIDLabel" runat="server" Text='<%# Eval("fuID") %>' />
                <br />
                RADAR_NO:
                <asp:Label ID="RADAR_NOLabel" runat="server" Text='<%# Bind("RADAR_NO") %>' />
                <br />
                DATE_FUP:
                <asp:Label ID="DATE_FUPLabel" runat="server" Text='<%# Bind("DATE_FUP") %>' />
                <br />
                RELAP_SINCE_LAST:
                <asp:CheckBox ID="RELAP_SINCE_LASTCheckBox" runat="server" 
                    Checked='<%# Bind("RELAP_SINCE_LAST") %>' Enabled="false" />
                <br />
                RELAP_LEN:
                <asp:Label ID="RELAP_LENLabel" runat="server" Text='<%# Bind("RELAP_LEN") %>' />
                <br />
                IMMUNOSUP_DUR:
                <asp:Label ID="IMMUNOSUP_DURLabel" runat="server" 
                    Text='<%# Bind("IMMUNOSUP_DUR") %>' />
                <br />
                IMMUNOSUP_DOSE:
                <asp:Label ID="IMMUNOSUP_DOSELabel" runat="server" 
                    Text='<%# Bind("IMMUNOSUP_DOSE") %>' />
                <br />
                IMMUNOSUP_INC:
                <asp:CheckBox ID="IMMUNOSUP_INCCheckBox" runat="server" 
                    Checked='<%# Bind("IMMUNOSUP_INC") %>' Enabled="false" />
                <br />
                OTHER_TRIG:
                <asp:Label ID="OTHER_TRIGLabel" runat="server" 
                    Text='<%# Bind("OTHER_TRIG") %>' />
                <br />
                IMMUN_TRIG:
                <asp:Label ID="IMMUN_TRIGLabel" runat="server" 
                    Text='<%# Bind("IMMUN_TRIG") %>' />
                <br />
                VIRAL_TRIG:
                <asp:Label ID="VIRAL_TRIGLabel" runat="server" 
                    Text='<%# Bind("VIRAL_TRIG") %>' />
                <br />
                COMP1:
                <asp:Label ID="COMP1Label" runat="server" Text='<%# Bind("COMP1") %>' />
                <br />
                MIN_SER_ALB:
                <asp:Label ID="MIN_SER_ALBLabel" runat="server" 
                    Text='<%# Bind("MIN_SER_ALB") %>' />
                <br />
                MAX_PR_CREAT_RATIO:
                <asp:Label ID="MAX_PR_CREAT_RATIOLabel" runat="server" 
                    Text='<%# Bind("MAX_PR_CREAT_RATIO") %>' />
                <br />
                PLASMA_EXCH_NO:
                <asp:Label ID="PLASMA_EXCH_NOLabel" runat="server" 
                    Text='<%# Bind("PLASMA_EXCH_NO") %>' />
                <br />
                PLASMA_EXCH:
                <asp:Label ID="PLASMA_EXCHLabel" runat="server" 
                    Text='<%# Bind("PLASMA_EXCH") %>' />
                <br />
                RESPONSE_TO:
                <asp:Label ID="RESPONSE_TOLabel" runat="server" 
                    Text='<%# Bind("RESPONSE_TO") %>' />
                <br />
                DIAL_TYPE:
                <asp:Label ID="DIAL_TYPELabel" runat="server" Text='<%# Bind("DIAL_TYPE") %>' />
                <br />
                DATE_BX:
                <asp:Label ID="DATE_BXLabel" runat="server" Text='<%# Bind("DATE_BX") %>' />
                <br />
                DATE_TX_REJECT:
                <asp:Label ID="DATE_TX_REJECTLabel" runat="server" 
                    Text='<%# Bind("DATE_TX_REJECT") %>' />
                <br />
                DRUG1:
                <asp:Label ID="DRUG1Label" runat="server" Text='<%# Bind("DRUG1") %>' />
                <br />
                TRANS_RECURR:
                <asp:CheckBox ID="TRANS_RECURRCheckBox" runat="server" 
                    Checked='<%# Bind("TRANS_RECURR") %>' Enabled="false" />
                <br />
                TRANS_TYPE:
                <asp:Label ID="TRANS_TYPELabel" runat="server" 
                    Text='<%# Bind("TRANS_TYPE") %>' />
                <br />
                DATE_TRANSPLANT:
                <asp:Label ID="DATE_TRANSPLANTLabel" runat="server" 
                    Text='<%# Bind("DATE_TRANSPLANT") %>' />
                <br />
                SIG_CHANGE_STATUS:
                <asp:Label ID="SIG_CHANGE_STATUSLabel" runat="server" 
                    Text='<%# Bind("SIG_CHANGE_STATUS") %>' />
                <br />
                DRUG6:
                <asp:Label ID="DRUG6Label" runat="server" Text='<%# Bind("DRUG6") %>' />
                <br />
                DRUG5:
                <asp:Label ID="DRUG5Label" runat="server" Text='<%# Bind("DRUG5") %>' />
                <br />
                DRUG4:
                <asp:Label ID="DRUG4Label" runat="server" Text='<%# Bind("DRUG4") %>' />
                <br />
                DRUG3:
                <asp:Label ID="DRUG3Label" runat="server" Text='<%# Bind("DRUG3") %>' />
                <br />
                DRUG2:
                <asp:Label ID="DRUG2Label" runat="server" Text='<%# Bind("DRUG2") %>' />
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
                HOSP_NO:
                <asp:Label ID="HOSP_NOLabel" runat="server" Text='<%# Bind("HOSP_NO") %>' />
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
            </ItemTemplate>
        </asp:FormView>
        <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
            ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
            DeleteCommand="DELETE FROM [tbl_6Month] WHERE [fuID] = @fuID" 
            InsertCommand="INSERT INTO [tbl_6Month] ([RADAR_NO], [DATE_FUP], [RELAP_SINCE_LAST], [RELAP_LEN], [IMMUNOSUP_DUR], [IMMUNOSUP_DOSE], [IMMUNOSUP_INC], [OTHER_TRIG], [IMMUN_TRIG], [VIRAL_TRIG], [COMP1], [MIN_SER_ALB], [MAX_PR_CREAT_RATIO], [PLASMA_EXCH_NO], [PLASMA_EXCH], [RESPONSE_TO], [DIAL_TYPE], [DATE_BX], [DATE_TX_REJECT], [DRUG1], [TRANS_RECURR], [TRANS_TYPE], [DATE_TRANSPLANT], [SIG_CHANGE_STATUS], [DRUG6], [DRUG5], [DRUG4], [DRUG3], [DRUG2]) VALUES (@RADAR_NO, @DATE_FUP, @RELAP_SINCE_LAST, @RELAP_LEN, @IMMUNOSUP_DUR, @IMMUNOSUP_DOSE, @IMMUNOSUP_INC, @OTHER_TRIG, @IMMUN_TRIG, @VIRAL_TRIG, @COMP1, @MIN_SER_ALB, @MAX_PR_CREAT_RATIO, @PLASMA_EXCH_NO, @PLASMA_EXCH, @RESPONSE_TO, @DIAL_TYPE, @DATE_BX, @DATE_TX_REJECT, @DRUG1, @TRANS_RECURR, @TRANS_TYPE, @DATE_TRANSPLANT, @SIG_CHANGE_STATUS, @DRUG6, @DRUG5, @DRUG4, @DRUG3, @DRUG2)" 
            SelectCommand="SELECT tbl_6Month.fuID, tbl_6Month.RADAR_NO, tbl_6Month.DATE_FUP, tbl_6Month.RELAP_SINCE_LAST, tbl_6Month.RELAP_LEN, tbl_6Month.IMMUNOSUP_DUR, tbl_6Month.IMMUNOSUP_DOSE, tbl_6Month.IMMUNOSUP_INC, tbl_6Month.OTHER_TRIG, tbl_6Month.IMMUN_TRIG, tbl_6Month.VIRAL_TRIG, tbl_6Month.COMP1, tbl_6Month.MIN_SER_ALB, tbl_6Month.MAX_PR_CREAT_RATIO, tbl_6Month.PLASMA_EXCH_NO, tbl_6Month.PLASMA_EXCH, tbl_6Month.RESPONSE_TO, tbl_6Month.DIAL_TYPE, tbl_6Month.DATE_BX, tbl_6Month.DATE_TX_REJECT, tbl_6Month.DRUG1, tbl_6Month.TRANS_RECURR, tbl_6Month.TRANS_TYPE, tbl_6Month.DATE_TRANSPLANT, tbl_6Month.SIG_CHANGE_STATUS, tbl_6Month.DRUG6, tbl_6Month.DRUG5, tbl_6Month.DRUG4, tbl_6Month.DRUG3, tbl_6Month.DRUG2, tbl_Demographics.SNAME, tbl_Demographics.FNAME, tbl_Demographics.DOB, tbl_Demographics.HOSP_NO, tbl_Diagnosis.DIAG FROM tbl_6Month INNER JOIN tbl_Demographics ON tbl_6Month.RADAR_NO = tbl_Demographics.RADAR_NO INNER JOIN tbl_Diagnosis ON tbl_Demographics.RADAR_NO = tbl_Diagnosis.RADAR_NO WHERE (tbl_6Month.RADAR_NO = @RADAR_NO)" 
            UpdateCommand="UPDATE [tbl_6Month] SET [RADAR_NO] = @RADAR_NO, [DATE_FUP] = @DATE_FUP, [RELAP_SINCE_LAST] = @RELAP_SINCE_LAST, [RELAP_LEN] = @RELAP_LEN, [IMMUNOSUP_DUR] = @IMMUNOSUP_DUR, [IMMUNOSUP_DOSE] = @IMMUNOSUP_DOSE, [IMMUNOSUP_INC] = @IMMUNOSUP_INC, [OTHER_TRIG] = @OTHER_TRIG, [IMMUN_TRIG] = @IMMUN_TRIG, [VIRAL_TRIG] = @VIRAL_TRIG, [COMP1] = @COMP1, [MIN_SER_ALB] = @MIN_SER_ALB, [MAX_PR_CREAT_RATIO] = @MAX_PR_CREAT_RATIO, [PLASMA_EXCH_NO] = @PLASMA_EXCH_NO, [PLASMA_EXCH] = @PLASMA_EXCH, [RESPONSE_TO] = @RESPONSE_TO, [DIAL_TYPE] = @DIAL_TYPE, [DATE_BX] = @DATE_BX, [DATE_TX_REJECT] = @DATE_TX_REJECT, [DRUG1] = @DRUG1, [TRANS_RECURR] = @TRANS_RECURR, [TRANS_TYPE] = @TRANS_TYPE, [DATE_TRANSPLANT] = @DATE_TRANSPLANT, [SIG_CHANGE_STATUS] = @SIG_CHANGE_STATUS, [DRUG6] = @DRUG6, [DRUG5] = @DRUG5, [DRUG4] = @DRUG4, [DRUG3] = @DRUG3, [DRUG2] = @DRUG2 WHERE [fuID] = @fuID">
            <SelectParameters>
                <asp:SessionParameter Name="RADAR_NO" SessionField="patientID" Type="Int32" />
            </SelectParameters>
            <DeleteParameters>
                <asp:Parameter Name="fuID" Type="Int32" />
            </DeleteParameters>
            <UpdateParameters>
                <asp:Parameter Name="RADAR_NO" Type="Int32" />
                <asp:Parameter Name="DATE_FUP" Type="DateTime" />
                <asp:Parameter Name="RELAP_SINCE_LAST" Type="Boolean" />
                <asp:Parameter Name="RELAP_LEN" Type="Int32" />
                <asp:Parameter Name="IMMUNOSUP_DUR" Type="Int32" />
                <asp:Parameter Name="IMMUNOSUP_DOSE" Type="Int32" />
                <asp:Parameter Name="IMMUNOSUP_INC" Type="Boolean" />
                <asp:Parameter Name="OTHER_TRIG" Type="Int32" />
                <asp:Parameter Name="IMMUN_TRIG" Type="Int32" />
                <asp:Parameter Name="VIRAL_TRIG" Type="Int32" />
                <asp:Parameter Name="COMP1" Type="Int32" />
                <asp:Parameter Name="MIN_SER_ALB" Type="Int32" />
                <asp:Parameter Name="MAX_PR_CREAT_RATIO" Type="Int32" />
                <asp:Parameter Name="PLASMA_EXCH_NO" Type="Int32" />
                <asp:Parameter Name="PLASMA_EXCH" Type="Int32" />
                <asp:Parameter Name="RESPONSE_TO" Type="Int32" />
                <asp:Parameter Name="DIAL_TYPE" Type="Int32" />
                <asp:Parameter Name="DATE_BX" Type="DateTime" />
                <asp:Parameter Name="DATE_TX_REJECT" Type="DateTime" />
                <asp:Parameter Name="DRUG1" Type="String" />
                <asp:Parameter Name="TRANS_RECURR" Type="Boolean" />
                <asp:Parameter Name="TRANS_TYPE" Type="Int32" />
                <asp:Parameter Name="DATE_TRANSPLANT" Type="DateTime" />
                <asp:Parameter Name="SIG_CHANGE_STATUS" Type="String" />
                <asp:Parameter Name="DRUG6" Type="String" />
                <asp:Parameter Name="DRUG5" Type="String" />
                <asp:Parameter Name="DRUG4" Type="String" />
                <asp:Parameter Name="DRUG3" Type="String" />
                <asp:Parameter Name="DRUG2" Type="String" />
                <asp:Parameter Name="fuID" Type="Int32" />
            </UpdateParameters>
            <InsertParameters>
                <asp:Parameter Name="RADAR_NO" Type="Int32" />
                <asp:Parameter Name="DATE_FUP" Type="DateTime" />
                <asp:Parameter Name="RELAP_SINCE_LAST" Type="Boolean" />
                <asp:Parameter Name="RELAP_LEN" Type="Int32" />
                <asp:Parameter Name="IMMUNOSUP_DUR" Type="Int32" />
                <asp:Parameter Name="IMMUNOSUP_DOSE" Type="Int32" />
                <asp:Parameter Name="IMMUNOSUP_INC" Type="Boolean" />
                <asp:Parameter Name="OTHER_TRIG" Type="Int32" />
                <asp:Parameter Name="IMMUN_TRIG" Type="Int32" />
                <asp:Parameter Name="VIRAL_TRIG" Type="Int32" />
                <asp:Parameter Name="COMP1" Type="Int32" />
                <asp:Parameter Name="MIN_SER_ALB" Type="Int32" />
                <asp:Parameter Name="MAX_PR_CREAT_RATIO" Type="Int32" />
                <asp:Parameter Name="PLASMA_EXCH_NO" Type="Int32" />
                <asp:Parameter Name="PLASMA_EXCH" Type="Int32" />
                <asp:Parameter Name="RESPONSE_TO" Type="Int32" />
                <asp:Parameter Name="DIAL_TYPE" Type="Int32" />
                <asp:Parameter Name="DATE_BX" Type="DateTime" />
                <asp:Parameter Name="DATE_TX_REJECT" Type="DateTime" />
                <asp:Parameter Name="DRUG1" Type="String" />
                <asp:Parameter Name="TRANS_RECURR" Type="Boolean" />
                <asp:Parameter Name="TRANS_TYPE" Type="Int32" />
                <asp:Parameter Name="DATE_TRANSPLANT" Type="DateTime" />
                <asp:Parameter Name="SIG_CHANGE_STATUS" Type="String" />
                <asp:Parameter Name="DRUG6" Type="String" />
                <asp:Parameter Name="DRUG5" Type="String" />
                <asp:Parameter Name="DRUG4" Type="String" />
                <asp:Parameter Name="DRUG3" Type="String" />
                <asp:Parameter Name="DRUG2" Type="String" />
            </InsertParameters>
        </asp:SqlDataSource>
    
    </div>
    </form>
</body>
</html>
