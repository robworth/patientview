<%@ Page Title="" Language="VB" MasterPageFile="~/admin/AdminMaster.master" AutoEventWireup="false" CodeFile="about-content-edit.aspx.vb" Inherits="admin_about_content_edit" ValidateRequest="false" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <asp:FormView ID="FormView1" runat="server" AllowPaging="True" 
        DataKeyNames="dID" DataSourceID="SqlDataSource1">
        <EditItemTemplate>
            dID:
            <asp:Label ID="dIDLabel1" runat="server" Text='<%# Eval("dID") %>' />
            <br />
            dText:
            <asp:TextBox ID="dTextTextBox" runat="server" Text='<%# Bind("dText") %>' Width="500px" TextMode="MultiLine" Rows="25" />
            <br />
            <asp:LinkButton ID="UpdateButton" runat="server" CausesValidation="True" 
                CommandName="Update" Text="Update" />
            &nbsp;<asp:LinkButton ID="UpdateCancelButton" runat="server" 
                CausesValidation="False" CommandName="Cancel" Text="Cancel" />
        </EditItemTemplate>
        <InsertItemTemplate>
            dID:
            <asp:TextBox ID="dIDTextBox" runat="server" Text='<%# Bind("dID") %>' />
            <br />
            dText:
            <asp:TextBox ID="dTextTextBox" runat="server" Text='<%# Bind("dText") %>' />
            <br />
            <asp:LinkButton ID="InsertButton" runat="server" CausesValidation="True" 
                CommandName="Insert" Text="Insert" />
            &nbsp;<asp:LinkButton ID="InsertCancelButton" runat="server" 
                CausesValidation="False" CommandName="Cancel" Text="Cancel" />
        </InsertItemTemplate>
        <ItemTemplate>
            dID:
            <asp:Label ID="dIDLabel" runat="server" Text='<%# Eval("dID") %>' />
            <br />
            dText:
            <asp:Label ID="dTextLabel" runat="server" Text='<%# Bind("dText") %>' />
            <br />
           <asp:Button runat="server" CommandName="Edit" Text="Edit" />
        </ItemTemplate>
    </asp:FormView>
    <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
        ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
        SelectCommand="SELECT [dID], [dText] FROM [tbl_DiseaseData]" 
        DeleteCommand="DELETE FROM [tbl_DiseaseData] WHERE [dID] = @dID" 
        InsertCommand="INSERT INTO [tbl_DiseaseData] ([dID], [dText]) VALUES (@dID, @dText)" 
        UpdateCommand="UPDATE [tbl_DiseaseData] SET [dText] = @dText WHERE [dID] = @dID">
        <DeleteParameters>
            <asp:Parameter Name="dID" Type="Int32" />
        </DeleteParameters>
        <UpdateParameters>
            <asp:Parameter Name="dText" Type="String" />
            <asp:Parameter Name="dID" Type="Int32" />
        </UpdateParameters>
        <InsertParameters>
            <asp:Parameter Name="dID" Type="Int32" />
            <asp:Parameter Name="dText" Type="String" />
        </InsertParameters>
    </asp:SqlDataSource>
</asp:Content>

