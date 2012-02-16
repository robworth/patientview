<%@ Page Title="Renal Radar Admin - Issue Tracking" Language="VB" MasterPageFile="~/admin/AdminMaster.master" AutoEventWireup="false" CodeFile="issues.aspx.vb" Inherits="admin_issues" %>

<%@ Register assembly="DevExpress.Web.ASPxGridView.v9.2, Version=9.2.4.0, Culture=neutral, PublicKeyToken=b88d1754d700e49a" namespace="DevExpress.Web.ASPxGridView" tagprefix="dxwgv" %>
<%@ Register assembly="DevExpress.Web.ASPxEditors.v9.2, Version=9.2.4.0, Culture=neutral, PublicKeyToken=b88d1754d700e49a" namespace="DevExpress.Web.ASPxEditors" tagprefix="dxe" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
    <style type="text/css">

.editCell {
vertical-align:top;
}



input {

vertical-align:top;
}

textarea {
width:400px;
}


</style>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <h1>
    Issue Tracker</h1>
    <p>
        Use the boxes in the header to sort entries. Use % or * when the full text is 
        unknown. Click the column titles to sort. <a href="#" class="info">Note.<span>If you get an error message 'Response.Redirect cannot be called in a Page callback', you will need to log out and log-in again</span></a></p>
<p>
    <dxwgv:ASPxGridView ID="ASPxGridView1" runat="server" 
        AutoGenerateColumns="False" DataSourceID="SqlDataSource1" KeyFieldName="bID" Width="95%" SettingsEditing-Mode="PopupEditForm" SettingsEditing-PopupEditFormModal="false" SettingsEditing-PopupEditFormHorizontalAlign="Center" SettingsEditing-PopupEditFormVerticalAlign="WindowCenter">
        
        <Columns>
            <dxwgv:GridViewCommandColumn VisibleIndex="0">
                <ClearFilterButton Visible="True">
                </ClearFilterButton>
                <EditButton Visible="True">
                </EditButton>
                <NewButton Visible="True">
                </NewButton>
            </dxwgv:GridViewCommandColumn>
            <dxwgv:GridViewDataTextColumn Caption="ID" FieldName="bID" ReadOnly="True" 
                VisibleIndex="1">
                <EditFormSettings Visible="False" />
            </dxwgv:GridViewDataTextColumn>
            <dxwgv:GridViewDataComboBoxColumn Caption="Type" FieldName="bType" 
                VisibleIndex="2">
                <PropertiesComboBox ValueType="System.String" Width="100px">
                <Items>
                <dxe:ListEditItem Value="Addition" />
                <dxe:ListEditItem Value="Correction" />
                <dxe:ListEditItem Value="Revision" />
                </Items>
                </PropertiesComboBox>
            </dxwgv:GridViewDataComboBoxColumn>
            <dxwgv:GridViewDataTextColumn Caption="Page" FieldName="bPage" VisibleIndex="3" Width="80px" PropertiesTextEdit-Width="150px">
            </dxwgv:GridViewDataTextColumn>
            <dxwgv:GridViewDataDateColumn Caption="Date Logged" FieldName="bDateLogged" 
                VisibleIndex="4" Width="90px" PropertiesDateEdit-Width="80px">
            </dxwgv:GridViewDataDateColumn>
            <dxwgv:GridViewDataDateColumn Caption="Date Resolved" FieldName="bDateResolved" 
                VisibleIndex="5" Width="90px" PropertiesDateEdit-Width="80px">
            </dxwgv:GridViewDataDateColumn>
            <%--<dxwgv:GridViewDataTextColumn Caption="Description" FieldName="bDesc" 
                VisibleIndex="6" Width="200px" PropertiesTextEdit-Height="100px" PropertiesTextEdit-Style-VerticalAlign="top" PropertiesTextEdit-Width="300px" PropertiesTextEdit-Style-CssClass="editCell" PropertiesTextEdit-Style-Wrap="True" >
            </dxwgv:GridViewDataTextColumn>--%>
            <dxwgv:GridViewDataMemoColumn FieldName="bDesc" Caption="Description" VisibleIndex="6" Width="150px">
            <EditFormSettings ColumnSpan="10" RowSpan="5" CaptionLocation="Near" />
            </dxwgv:GridViewDataMemoColumn>
            <%--<dxwgv:GridViewDataTextColumn Caption="Comments" FieldName="bComment" VisibleIndex="7" Width="150px" PropertiesTextEdit-Height="100px" PropertiesTextEdit-Style-VerticalAlign="top" PropertiesTextEdit-Width="300px">
            </dxwgv:GridViewDataTextColumn>--%>
            <dxwgv:GridViewDataMemoColumn Caption="Comments" FieldName="bComment" VisibleIndex="7">
            <EditFormSettings ColumnSpan="5" RowSpan="10" CaptionLocation="Near" />
            </dxwgv:GridViewDataMemoColumn>
            <dxwgv:GridViewDataComboBoxColumn Caption="Prority" FieldName="bPriority" 
                VisibleIndex="8">
                <PropertiesComboBox ValueType="System.String" Width="100px">
                <Items>
                <dxe:ListEditItem Value="Low" />
                <dxe:ListEditItem Value="Normal" />
                <dxe:ListEditItem Value="High" />
                </Items>
                </PropertiesComboBox>
            </dxwgv:GridViewDataComboBoxColumn>
            <dxwgv:GridViewDataComboBoxColumn Caption="Status" FieldName="bStatus" 
                VisibleIndex="9">
                <PropertiesComboBox ValueType="System.String" Width="80px">
               <Items>
               <dxe:ListEditItem Value="Open" />
               <dxe:ListEditItem Value="Pending" />
               <dxe:ListEditItem Value="Closed" />
               <dxe:ListEditItem Value="Postponed" />
               </Items>
                </PropertiesComboBox>
            </dxwgv:GridViewDataComboBoxColumn>
            <dxwgv:GridViewDataDateColumn Caption="Date Live" FieldName="bUpdated" 
                VisibleIndex="10" Width="90px" PropertiesDateEdit-Width="100px">
            </dxwgv:GridViewDataDateColumn>
        </Columns>
        <Settings ShowFilterRow="True" />
    </dxwgv:ASPxGridView>
    <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
        ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
        DeleteCommand="DELETE FROM [tbl_IssueTracker] WHERE [bID] = @bID" 
        InsertCommand="INSERT INTO [tbl_IssueTracker] ([bType], [bPage], [bDateLogged], [bDateResolved], [bDesc], [bComment], [bPriority], [bStatus], [bUpdated]) VALUES (@bType, @bPage, @bDateLogged, @bDateResolved, @bDesc, @bComment, @bPriority, @bStatus, @bUpdated)" 
        SelectCommand="SELECT * FROM [tbl_IssueTracker] ORDER BY [bID] DESC" 
        
        UpdateCommand="UPDATE [tbl_IssueTracker] SET [bType] = @bType, [bPage] = @bPage, [bDateLogged] = @bDateLogged, [bDateResolved] = @bDateResolved, [bDesc] = @bDesc, [bComment] = @bComment, [bPriority] = @bPriority, [bStatus] = @bStatus, [bUpdated] = @bUpdated WHERE [bID] = @bID">
        <DeleteParameters>
            <asp:Parameter Name="bID" Type="Int32" />
        </DeleteParameters>
        <InsertParameters>
            <asp:Parameter Name="bType" Type="String" />
            <asp:Parameter Name="bPage" Type="String" />
            <asp:Parameter Name="bDateLogged" Type="DateTime" />
            <asp:Parameter Name="bDateResolved" Type="DateTime" />
            <asp:Parameter Name="bDesc" Type="String" />
            <asp:Parameter Name="bComment" Type="String" />
            <asp:Parameter Name="bPriority" Type="String" />
            <asp:Parameter Name="bStatus" Type="String" />
            <asp:Parameter Name="bUpdated" Type="DateTime" />
        </InsertParameters>
        <UpdateParameters>
            <asp:Parameter Name="bType" Type="String" />
            <asp:Parameter Name="bPage" Type="String" />
            <asp:Parameter Name="bDateLogged" Type="DateTime" />
            <asp:Parameter Name="bDateResolved" Type="DateTime" />
            <asp:Parameter Name="bDesc" Type="String" />
            <asp:Parameter Name="bComment" Type="String" />
            <asp:Parameter Name="bPriority" Type="String" />
            <asp:Parameter Name="bStatus" Type="String" />
            <asp:Parameter Name="bUpdated" Type="DateTime" />
            <asp:Parameter Name="bID" Type="Int32" />
        </UpdateParameters>
    </asp:SqlDataSource>
</p>
</asp:Content>

