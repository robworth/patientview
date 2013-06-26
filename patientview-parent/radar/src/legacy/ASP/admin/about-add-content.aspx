<%@ Page Title="" Language="VB" MasterPageFile="~/admin/AdminMaster.master" AutoEventWireup="false" CodeFile="about-add-content.aspx.vb" Inherits="admin_about_add_content" ValidateRequest="false" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <h1>
        Add &#39;About &#39; Content</h1>
    <p>
        <asp:FormView ID="FormView1" runat="server" DataKeyNames="dID" 
            DataSourceID="SqlDataSource1">
            <EditItemTemplate>
                dID:
                <asp:Label ID="dIDLabel1" runat="server" Text='<%# Eval("dID") %>' />
                <br />
                dText:
                <asp:TextBox TextMode="MultiLine" Rows="20" Columns="60" ID="dTextTextBox" runat="server" Text='<%# Bind("dText") %>' />
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
                <asp:TextBox ID="dTextTextBox" Columns="60" TextMode="MultiLine" Rows="20" runat="server" Text='<%# Bind("dText") %>' />
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
                <asp:LinkButton ID="EditButton" runat="server" CausesValidation="False" 
                    CommandName="Edit" Text="Edit" />
                &nbsp;<asp:LinkButton ID="DeleteButton" runat="server" CausesValidation="False" 
                    CommandName="Delete" Text="Delete" />
                &nbsp;<asp:LinkButton ID="NewButton" runat="server" CausesValidation="False" 
                    CommandName="New" Text="New" />
            </ItemTemplate>
            <EmptyDataTemplate>
            <asp:LinkButton ID="NewButton" runat="server" CausesValidation="False" 
                    CommandName="New" Text="New" />
            </EmptyDataTemplate>
        </asp:FormView>
        <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
            ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
            DeleteCommand="DELETE FROM [tbl_DiseaseData] WHERE [dID] = @dID" 
            InsertCommand="INSERT INTO [tbl_DiseaseData] ([dID], [dText]) VALUES (@dID, @dText)" 
            SelectCommand="SELECT [dID], [dText] FROM [tbl_DiseaseData] WHERE ([dID] = @dID)" 
            UpdateCommand="UPDATE [tbl_DiseaseData] SET [dText] = @dText WHERE [dID] = @dID">
            <SelectParameters>
                <asp:QueryStringParameter Name="dID" QueryStringField="d" Type="Int32" />
            </SelectParameters>
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
    </p>
</asp:Content>

