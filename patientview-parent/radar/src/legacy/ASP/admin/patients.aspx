<%@ Page Title="" Language="VB" MasterPageFile="~/admin/AdminMaster.master" AutoEventWireup="false" CodeFile="patients.aspx.vb" Inherits="admin_patients2" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
<style type="text/css">

input, select, textarea {
	border: 1px solid gray;
}
select, input, textarea {
	font-size: 95%;
	color: #2D2E2E;
    }
    </style>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <asp:ScriptManager ID="ScriptManager1" runat="server">
    </asp:ScriptManager>  
    <asp:UpdatePanel ID="UpdatePanel1" runat="server">
    <ContentTemplate>
    <h2>
        Admin - Patients</h2>
        <p>
            Click column headings to sort.</p>
    <p>
        <asp:GridView ID="GridView1" runat="server" AutoGenerateColumns="False" 
            DataSourceID="SqlDataSource1" EnableModelValidation="True" CellPadding="4" 
            DataKeyNames="pID" OnRowEditing="GridView1_RowEditing" 
            OnRowCreated="GridView_RowCreated" 
            OnSelectedIndexChanging="GridView1_RowSelecting"              
            Font-Size="80%" AllowSorting="True" BorderWidth="1px" BorderColor="#9f9f9f" 
            HeaderStyle-BackColor="#dcdcdc"  >
            <Columns>
            <asp:BoundField DataField="pID" HeaderText="ID" />
            <asp:TemplateField>
            <ItemTemplate>
            <asp:Button ID="selectBtn" runat="server" CommandName="Select" Text="Edit" ToolTip="Edit" />
            </ItemTemplate>
            </asp:TemplateField>
                
                <asp:BoundField DataField="RADAR_NO" HeaderText="RADAR No" 
                    SortExpression="RADAR_NO" HeaderStyle-HorizontalAlign="Center" 
                    ItemStyle-HorizontalAlign="Center" >
                <HeaderStyle HorizontalAlign="Center" />
                <ItemStyle HorizontalAlign="Center" />
                </asp:BoundField>
                <asp:TemplateField HeaderText="First Name">
                    <ItemTemplate>
                        <asp:Label ID="lblFname" runat="server" Text='<%# GetDecrypt(DataBinder.Eval(Container, "DataItem.FNAME"))%>'></asp:Label>
                    </ItemTemplate>                   
                </asp:TemplateField>
                <asp:TemplateField HeaderText="Surname">
                    <ItemTemplate>
                        <asp:Label ID="lblSurname" runat="server" Text='<%# GetDecrypt(DataBinder.Eval(Container, "DataItem.SNAME"))%>'></asp:Label>
                    </ItemTemplate>
                   
                </asp:TemplateField>
                <asp:BoundField DataField="pUserName" HeaderText="UserName" 
                    SortExpression="pUserName" />
                <asp:BoundField DataField="pDOB" HeaderText="DOB" 
                    DataFormatString="{0:dd-MMM-yyyy}" SortExpression="pDOB" 
                    HeaderStyle-HorizontalAlign="Center" ItemStyle-HorizontalAlign="Center" >
                <HeaderStyle HorizontalAlign="Center" />
                <ItemStyle HorizontalAlign="Center" />
                </asp:BoundField>
                <asp:TemplateField HeaderText="Password">
                    <ItemTemplate>
                        <asp:Label ID="Label1" runat="server" Text='<%# GetDecrypt(DataBinder.Eval(Container, "DataItem.pPassword"))%>'></asp:Label>
                    </ItemTemplate>
                   
                </asp:TemplateField>
                <asp:BoundField DataField="pDateReg" HeaderText="Date Reg" 
                    DataFormatString="{0:dd-MMM-yyyy}" HeaderStyle-HorizontalAlign="Center" 
                    ItemStyle-HorizontalAlign="Center" SortExpression="pDateReg" >
                <HeaderStyle HorizontalAlign="Center" />
                <ItemStyle HorizontalAlign="Center" />
                </asp:BoundField>
               <asp:TemplateField>
                <ItemTemplate>
                <asp:Button ID="delButton" runat="server" CausesValidation="false" CommandName="Delete" OnClientClick="return confirm('Are you sure you want to delete this record?');" Text="Delete"></asp:Button>
                <asp:Button ID="editButton" runat="server" CausesValidation="false" CommandName="Edit" Text="Email" ToolTip="Send a password reminder email" />
                </ItemTemplate>
                </asp:TemplateField>
            </Columns>
            <HeaderStyle ForeColor="#3C982D" />
        </asp:GridView>
    </p>
    <p><asp:Label ID="lblMsg" runat="server"></asp:Label></p>
    <p><asp:Label ID="lblDebug" runat="server"></asp:Label></p>
    </ContentTemplate>
    </asp:UpdatePanel>
    <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
        ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
        
        SelectCommand="SELECT tbl_Patient_Users.pID, tbl_Patient_Users.RADAR_NO, tbl_Patient_Users.pUserName, tbl_Patient_Users.pPassWord, tbl_Patient_Users.pDOB, tbl_Patient_Users.pDateReg, tbl_Demographics.SNAME, tbl_Demographics.FNAME FROM tbl_Patient_Users INNER JOIN tbl_Demographics ON tbl_Patient_Users.RADAR_NO = tbl_Demographics.RADAR_NO" 
        DeleteCommand="DELETE FROM [tbl_Patient_Users] WHERE [pID] = @pID" 
        InsertCommand="INSERT INTO [tbl_Patient_Users] ([RADAR_NO], [pUserName], [pPassWord], [pDOB], [pDateReg]) VALUES (@RADAR_NO, @pUserName, @pPassWord, @pDOB, @pDateReg)" 
        
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
        <UpdateParameters>
            <asp:Parameter Name="RADAR_NO" Type="Int32" />
            <asp:Parameter Name="pUserName" Type="String" />
            <asp:Parameter Name="pPassWord" Type="Object" />
            <asp:Parameter Name="pDOB" Type="DateTime" />
            <asp:Parameter Name="pDateReg" Type="DateTime" />
            <asp:Parameter Name="pID" Type="Int32" />
        </UpdateParameters>
    </asp:SqlDataSource>
</asp:Content>

