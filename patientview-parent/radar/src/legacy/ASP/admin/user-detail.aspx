<%@ Page Title="" Language="VB" MasterPageFile="~/admin/AdminMaster.master" AutoEventWireup="false" CodeFile="user-detail.aspx.vb" Inherits="admin_user_detail" %>

<%@ Register assembly="DevExpress.Web.v9.2, Version=9.2.4.0, Culture=neutral, PublicKeyToken=b88d1754d700e49a" namespace="DevExpress.Web.ASPxDataView" tagprefix="dxdv" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <h2>
        Admin - Users</h2>
    <p>
      
        <asp:DetailsView ID="DetailsView1" runat="server" AutoGenerateRows="False" 
            DataKeyNames="uID" DataSourceID="SqlDataSource1" Height="50px" Width="250px" CellPadding="10">
            <Fields>
                <asp:BoundField DataField="uID" HeaderText="uID" InsertVisible="False" 
                    ReadOnly="True" SortExpression="uID" />
                <asp:BoundField DataField="uSurname" HeaderText="Surname" 
                    SortExpression="uSurname" />
                <asp:BoundField DataField="uForename" HeaderText="Forename" 
                    SortExpression="uForename" />
                <asp:BoundField DataField="uTitle" HeaderText="Title" 
                    SortExpression="uTitle" />
                <asp:BoundField DataField="uRole" HeaderText="Role" SortExpression="uRole" />
                <asp:BoundField DataField="uEmail" HeaderText="Email" 
                    SortExpression="uEmail" />
                    <asp:BoundField DataField="uPhone" HeaderText="Phone" 
                    SortExpression="uPhone" />
                <asp:BoundField DataField="uCentre" HeaderText="Centre" 
                    SortExpression="uCentre" />
                <asp:BoundField DataField="uDateJoin" HeaderText="Date Join" 
                    SortExpression="uDateJoin" DataFormatString="{0:dd-MMM-yyy}" ApplyFormatInEditMode="True"/>
                    <asp:TemplateField>
                    <HeaderTemplate>Username</HeaderTemplate>
                    <ItemTemplate><%#GetDecrypt(DataBinder.Eval(Container, "DataItem.uUserName"))%></ItemTemplate>
                    </asp:TemplateField>
                    <asp:TemplateField>
                    <HeaderTemplate>Password</HeaderTemplate>
                    <ItemTemplate><%#GetDecrypt(DataBinder.Eval(Container, "DataItem.uPass"))%></ItemTemplate>
                    </asp:TemplateField>
                <asp:CommandField ShowDeleteButton="True" ShowEditButton="True" ShowCancelButton="true" />
            </Fields>
        </asp:DetailsView>
        <br />
        <asp:Button ID="btnCancelEdit" runat="server" Text="Back to the user list" />
        <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
            ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
            DeleteCommand="DELETE FROM [tbl_Users] WHERE [uID] = @uID" 
            InsertCommand="INSERT INTO [tbl_Users] ([uID], [uSurname], [uForename], [uTitle], [uGMC], [uRole], [uEmail], [uPhone], [uCentre], [uDateJoin], [uPass], [uUserName]) VALUES (@uID, @uSurname, @uForename, @uTitle, @uGMC, @uRole, @uEmail, @uPhone, @uCentre, @uDateJoin, @uPass, @uUserName)" 
            SelectCommand="SELECT [uID], [uSurname], [uForename], [uTitle], [uGMC], [uRole], [uEmail], [uPhone], [uCentre], [uDateJoin], [uPass], [uUserName] FROM [tbl_Users] WHERE ([uID] = @uID)" 
            
            UpdateCommand="UPDATE [tbl_Users] SET [uSurname] = @uSurname, [uForename] = @uForename, [uTitle] = @uTitle, [uGMC] = @uGMC, [uRole] = @uRole, [uEmail] = @uEmail, [uPhone] = @uPhone, [uCentre] = @uCentre, [uDateJoin] = @uDateJoin WHERE [uID] = @uID">
            <SelectParameters>
                <asp:QueryStringParameter Name="uID" QueryStringField="id" Type="Int32" />
            </SelectParameters>
            <DeleteParameters>
                <asp:Parameter Name="uID" Type="Int32" />
            </DeleteParameters>
            <UpdateParameters>
                <asp:Parameter Name="uSurname" Type="String" />
                <asp:Parameter Name="uForename" Type="String" />
                <asp:Parameter Name="uTitle" Type="String" />
                <asp:Parameter Name="uGMC" Type="String" />
                <asp:Parameter Name="uRole" Type="String" />
                <asp:Parameter Name="uEmail" Type="String" />
                <asp:Parameter Name="uPhone" Type="String" />
                <asp:Parameter Name="uCentre" Type="Int32" />
                <asp:Parameter Name="uDateJoin" Type="DateTime" />
                <asp:Parameter Name="uPass" Type="Object" />
                <asp:Parameter Name="uUserName" Type="Object" />
                <asp:Parameter Name="uID" Type="Int32" />
            </UpdateParameters>
            <InsertParameters>
                <asp:Parameter Name="uID" Type="Int32" />
                <asp:Parameter Name="uSurname" Type="String" />
                <asp:Parameter Name="uForename" Type="String" />
                <asp:Parameter Name="uTitle" Type="String" />
                <asp:Parameter Name="uGMC" Type="String" />
                <asp:Parameter Name="uRole" Type="String" />
                <asp:Parameter Name="uEmail" Type="String" />
                <asp:Parameter Name="uPhone" Type="String" />
                <asp:Parameter Name="uCentre" Type="Int32" />
                <asp:Parameter Name="uDateJoin" Type="DateTime" />
                <asp:Parameter Name="uPass" Type="Object" />
                <asp:Parameter Name="uUserName" Type="Object" />
            </InsertParameters>
        </asp:SqlDataSource>
      
    </p>
</asp:Content>

