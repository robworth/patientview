<%@ Page Title="" Language="VB" MasterPageFile="~/admin/AdminMaster.master" AutoEventWireup="false" CodeFile="patient-edit.aspx.vb" Inherits="admin_patient_edit" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <h2>
        Admin - Patients</h2>
    <p>
        <asp:DetailsView ID="DetailsView1" runat="server" AutoGenerateRows="False" 
            DataKeyNames="pID" DataSourceID="SqlDataSource1" EnableModelValidation="True" 
            Height="50px" Width="400px" CellPadding="10">
            <Fields>
                <asp:BoundField DataField="pID" HeaderText="pID" InsertVisible="False" 
                    ReadOnly="True" SortExpression="pID" />
                <asp:BoundField DataField="RADAR_NO" HeaderText="RADAR NO" 
                    SortExpression="RADAR_NO" />
                <asp:BoundField DataField="pUserName" HeaderText="Username" 
                    SortExpression="pUserName"  />
                <asp:BoundField DataField="pDOB" HeaderText="DOB" SortExpression="pDOB" DataFormatString="{0:dd-MMM-yyy}" ApplyFormatInEditMode="True" />
                <asp:BoundField DataField="pDateReg" HeaderText="Date Reg" 
                    SortExpression="pDateReg" />
                <asp:CommandField ShowDeleteButton="True" ShowEditButton="True" ShowCancelButton="true" />
            </Fields>
        </asp:DetailsView>
        <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
            ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
            DeleteCommand="DELETE FROM [tbl_Patient_Users] WHERE [pID] = @pID" 
            InsertCommand="INSERT INTO [tbl_Patient_Users] ([RADAR_NO], [pUserName], [pPassWord], [pDOB], [pDateReg]) VALUES (@RADAR_NO, @pUserName, @pPassWord, @pDOB, @pDateReg)" 
            SelectCommand="SELECT tbl_Patient_Users.pID, tbl_Patient_Users.RADAR_NO, tbl_Patient_Users.pUserName, tbl_Patient_Users.pPassWord, tbl_Patient_Users.pDOB, tbl_Patient_Users.pDateReg, tbl_Demographics.SNAME, tbl_Demographics.FNAME FROM tbl_Patient_Users INNER JOIN tbl_Demographics ON tbl_Patient_Users.RADAR_NO = tbl_Demographics.RADAR_NO WHERE (tbl_Patient_Users.pID = @pID)" 
            UpdateCommand="UPDATE [tbl_Patient_Users] SET [RADAR_NO] = @RADAR_NO, [pUserName] = @pUserName, [pPassWord] = @pPassWord, [pDOB] = @pDOB, [pDateReg] = @pDateReg WHERE [pID] = @pID">
            <DeleteParameters>
                <asp:Parameter Name="pID" Type="Int32" />
            </DeleteParameters>
            <InsertParameters>
                <asp:Parameter Name="RADAR_NO" Type="Int32" />
                <asp:Parameter Name="pUserName" Type="String" />
                <asp:Parameter Name="pPassWord" Type="Object" />
                <asp:Parameter Name="pDOB" Type="DateTime" />
                <asp:Parameter Name="pDateReg" Type="DateTime" />
            </InsertParameters>
            <SelectParameters>
                <asp:QueryStringParameter Name="pID" QueryStringField="p" Type="Int32" />
            </SelectParameters>
            <UpdateParameters>
                <asp:Parameter Name="RADAR_NO" Type="Int32" />
                <asp:Parameter Name="pUserName" Type="String" />
                <asp:Parameter Name="pPassWord" Type="Object" />
                <asp:Parameter Name="pDOB" Type="DateTime" />
                <asp:Parameter Name="pDateReg" Type="DateTime" />
                <asp:Parameter Name="pID" Type="Int32" />
            </UpdateParameters>
        </asp:SqlDataSource>
    </p>
</asp:Content>

