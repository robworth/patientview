<%@ Page Title="" Language="VB" MasterPageFile="~/admin/AdminMaster.master" AutoEventWireup="false" CodeFile="z-test-gridexporter.aspx.vb" Inherits="admin_z_test_gridexporter" %>

<%@ Register assembly="DevExpress.Web.ASPxGridView.v9.2, Version=9.2.4.0, Culture=neutral, PublicKeyToken=b88d1754d700e49a" namespace="DevExpress.Web.ASPxGridView" tagprefix="dxwgv" %>
<%@ Register assembly="DevExpress.Web.ASPxEditors.v9.2, Version=9.2.4.0, Culture=neutral, PublicKeyToken=b88d1754d700e49a" namespace="DevExpress.Web.ASPxEditors" tagprefix="dxe" %>
<%@ Register assembly="DevExpress.Web.ASPxGridView.v9.2.Export, Version=9.2.4.0, Culture=neutral, PublicKeyToken=b88d1754d700e49a" namespace="DevExpress.Web.ASPxGridView.Export" tagprefix="dxwgv" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <h1>
        Grid Export Test</h1>
    <p>
        <dxwgv:ASPxGridView ID="ASPxGridView1" runat="server" 
            AutoGenerateColumns="False" DataSourceID="SqlDataSource1">
            <Columns>
                <dxwgv:GridViewDataTextColumn FieldName="uForename" VisibleIndex="0">
                </dxwgv:GridViewDataTextColumn>
                <dxwgv:GridViewDataTextColumn FieldName="uSurname" VisibleIndex="1">
                </dxwgv:GridViewDataTextColumn>
                <dxwgv:GridViewDataTextColumn FieldName="uTitle" VisibleIndex="2">
                </dxwgv:GridViewDataTextColumn>
                <dxwgv:GridViewDataTextColumn FieldName="uGMC" VisibleIndex="3">
                </dxwgv:GridViewDataTextColumn>
                <dxwgv:GridViewDataDateColumn FieldName="uDateJoin" VisibleIndex="4">
                </dxwgv:GridViewDataDateColumn>
            </Columns>
        </dxwgv:ASPxGridView>
    </p>
    <p>
        <dxe:ASPxButton ID="ASPxButton1" runat="server" Text="Export to PDF" CssClass="inline">
        </dxe:ASPxButton>
        <dxe:ASPxButton ID="ASPxButton2" runat="server" Text="Export to XLS" CssClass="inline">
        </dxe:ASPxButton>
    </p>
    <p>
        &nbsp;</p>
    <p>
        <dxwgv:ASPxGridViewExporter ID="ASPxGridViewExporter1" runat="server">
        </dxwgv:ASPxGridViewExporter>
    </p>
    <p>
        <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
            ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
            SelectCommand="SELECT [uForename], [uSurname], [uTitle], [uGMC], [uDateJoin] FROM [tbl_Users]">
        </asp:SqlDataSource>
    </p>
</asp:Content>

