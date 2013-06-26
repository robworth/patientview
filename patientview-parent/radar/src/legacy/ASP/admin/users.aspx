<%@ Page Title="" Language="VB" MasterPageFile="~/admin/AdminMaster.master" AutoEventWireup="false" CodeFile="users.aspx.vb" Inherits="admin_users" %>

<%@ Register Assembly="DevExpress.Web.ASPxGridView.v9.2.Export, Version=9.2.4.0, Culture=neutral, PublicKeyToken=b88d1754d700e49a"
    Namespace="DevExpress.Web.ASPxGridView.Export" TagPrefix="dxwgv" %>
<%@ Register assembly="DevExpress.Web.ASPxGridView.v9.2, Version=9.2.4.0, Culture=neutral, PublicKeyToken=b88d1754d700e49a" namespace="DevExpress.Web.ASPxGridView" tagprefix="dxwgv" %>
<%@ Register assembly="DevExpress.Web.ASPxEditors.v9.2, Version=9.2.4.0, Culture=neutral, PublicKeyToken=b88d1754d700e49a" namespace="DevExpress.Web.ASPxEditors" tagprefix="dxe" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
    <style type="text/css">
table {
/*table-layout:fixed;*/
}

.button {
margin-left:50px;
margin-top:5px;
}
</style>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <div>
    
        <h2>
            Admin - Users&nbsp;
            <asp:Button ID="btnAdd" runat="server" Text="Add a new User" CssClass="button" PostBackUrl="user-add.aspx" />
        </h2>
        <p>Use the top boxes to filter the list. Use the left hand 'Clear' link to reset the filtered list.</p>
        <p>
            <dxwgv:ASPxGridView ID="ASPxGridView1" runat="server" 
                AutoGenerateColumns="False" DataSourceID="SqlDataSource1" Styles-Header-Font-Bold="true"  Width="100%" SettingsPager-PageSize="10">
<Styles>
<Header Font-Bold="True"></Header>
</Styles>
                <Columns>
                    <dxwgv:GridViewCommandColumn VisibleIndex="0" Width="35px">
                        <ClearFilterButton Visible="True">
                        </ClearFilterButton>
                    </dxwgv:GridViewCommandColumn>
                      <dxwgv:GridViewDataColumn Caption="Edit" VisibleIndex="1" 
                    CellStyle-HorizontalAlign="Center" Width="35px">
                <DataItemTemplate>
                <a href="user-detail.aspx?id=<%# DataBinder.Eval(Container, "DataItem.uID") %>">Edit</a>
                </DataItemTemplate><CellStyle HorizontalAlign="Center"></CellStyle>
                </dxwgv:GridViewDataColumn> 

                    <dxwgv:GridViewDataTextColumn FieldName="uID" ReadOnly="True" VisibleIndex="1" Visible="false">
                        <EditFormSettings Visible="False" />
                    </dxwgv:GridViewDataTextColumn>
                    <dxwgv:GridViewDataTextColumn Caption="Surname" FieldName="uSurname" 
                        VisibleIndex="2" Width="80px">
                    </dxwgv:GridViewDataTextColumn>
                    <dxwgv:GridViewDataTextColumn Caption="Forename" FieldName="uForename" 
                        VisibleIndex="3" Width="80px">
                    </dxwgv:GridViewDataTextColumn>
                    <dxwgv:GridViewDataTextColumn Caption="Title" FieldName="uTitle" 
                        VisibleIndex="4" Width="50px">
                    </dxwgv:GridViewDataTextColumn>
                    <dxwgv:GridViewDataTextColumn Caption="Role" FieldName="uRole" VisibleIndex="5" Width="100px">
                    </dxwgv:GridViewDataTextColumn>
                    <dxwgv:GridViewDataTextColumn Caption="Email" FieldName="uEmail" 
                        VisibleIndex="6">
                    </dxwgv:GridViewDataTextColumn>
                    <dxwgv:GridViewDataTextColumn Caption="Centre" FieldName="cName" 
                        VisibleIndex="7">
                    </dxwgv:GridViewDataTextColumn>
                    <dxwgv:GridViewDataDateColumn Caption="Date Reg" FieldName="uDateJoin" 
                        VisibleIndex="8" Width="70px">
                    </dxwgv:GridViewDataDateColumn>
                    <dxwgv:GridViewDataColumn Caption="Username" VisibleIndex="9">
                        <DataItemTemplate>
                        <%#GetDecrypt(DataBinder.Eval(Container, "DataItem.uUserName"))%>
                        </DataItemTemplate>
                    </dxwgv:GridViewDataColumn>
                    <dxwgv:GridViewDataColumn Caption="Password" VisibleIndex="10" Width="65px">
                        <DataItemTemplate>
                        <%#GetDecrypt(DataBinder.Eval(Container, "DataItem.uPass"))%>
                        </DataItemTemplate>
                    </dxwgv:GridViewDataColumn>
                     <dxwgv:GridViewDataTextColumn FieldName="uGMC" Caption="GMC" VisibleIndex="8" Width="65px">
                    </dxwgv:GridViewDataTextColumn>
                </Columns>
                <Settings ShowFilterRow="True" />
            </dxwgv:ASPxGridView>
            </p>
            <table style="margin-top:10px; margin-bottom:20px; margin-right:0px; padding-right:0; width:400px;">
  <tr>
  <td><dxe:ASPxButton ID="btnPdfExport" runat="server" Text="Export to PDF" UseSubmitBehavior="False" OnClick="btnPdfExport_Click" Width="150px" ></dxe:ASPxButton></td>
  <td><dxe:ASPxButton ID="btnXlsExport" runat="server" Text="Export to XLS" UseSubmitBehavior="False" OnClick="btnXlsExport_Click" Width="150px" ></dxe:ASPxButton></td>
  </tr>
  </table>


            <dxwgv:ASPxGridViewExporter ID="ASPxGridViewExporter1" runat="server">
            </dxwgv:ASPxGridViewExporter>

            <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
                ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
                
                SelectCommand="SELECT tbl_Users.uID, tbl_Users.uSurname, tbl_Users.uForename, tbl_Users.uTitle, tbl_Users.uRole, tbl_Users.uEmail, tbl_Users.uDateJoin, tbl_Users.uPass, tbl_Users.uUserName, tbl_Centres.cName, tbl_Users.uGMC FROM tbl_Users INNER JOIN tbl_Centres ON tbl_Users.uCentre = tbl_Centres.cID ORDER BY tbl_Users.uSurname">
            </asp:SqlDataSource>
       
    
    </div>
</asp:Content>

