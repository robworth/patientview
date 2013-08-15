<%@ Page Language="VB" AutoEventWireup="false" CodeFile="users.aspx.vb" Inherits="admin_users" Debug="true" %>

<%@ Register assembly="DevExpress.Web.ASPxGridView.v9.2, Version=9.2.4.0, Culture=neutral, PublicKeyToken=b88d1754d700e49a" namespace="DevExpress.Web.ASPxGridView" tagprefix="dxwgv" %>
<%@ Register assembly="DevExpress.Web.ASPxEditors.v9.2, Version=9.2.4.0, Culture=neutral, PublicKeyToken=b88d1754d700e49a" namespace="DevExpress.Web.ASPxEditors" tagprefix="dxe" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
    <link href="../normal.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <form id="form1" runat="server">
    <div>
    
        <h2>
            Admin - Users</h2>
        <p>
            <dxwgv:ASPxGridView ID="ASPxGridView1" runat="server" 
                AutoGenerateColumns="False" DataSourceID="SqlDataSource1" Styles-Header-Font-Bold="true" e>
<Styles>
<Header Font-Bold="True"></Header>
</Styles>
                <Columns>
                    <dxwgv:GridViewCommandColumn VisibleIndex="0">
                        <ClearFilterButton Visible="True">
                        </ClearFilterButton>
                    </dxwgv:GridViewCommandColumn>
                      <dxwgv:GridViewDataColumn Caption="Edit" VisibleIndex="1" 
                    CellStyle-HorizontalAlign="Center">
                <DataItemTemplate>
                <a href="user-detail.aspx?id=<%# DataBinder.Eval(Container, "DataItem.uID") %>"><img src="../images/edit.gif" style="border:0;" alt="View" /></a>
                </DataItemTemplate><CellStyle HorizontalAlign="Center"></CellStyle>
                </dxwgv:GridViewDataColumn> 

                    <dxwgv:GridViewDataTextColumn FieldName="uID" ReadOnly="True" VisibleIndex="1" Visible="false">
                        <EditFormSettings Visible="False" />
                    </dxwgv:GridViewDataTextColumn>
                    <dxwgv:GridViewDataTextColumn Caption="Surname" FieldName="uSurname" 
                        VisibleIndex="2">
                    </dxwgv:GridViewDataTextColumn>
                    <dxwgv:GridViewDataTextColumn Caption="Forename" FieldName="uForename" 
                        VisibleIndex="3">
                    </dxwgv:GridViewDataTextColumn>
                    <dxwgv:GridViewDataTextColumn Caption="Title" FieldName="uTitle" 
                        VisibleIndex="4">
                    </dxwgv:GridViewDataTextColumn>
                    <dxwgv:GridViewDataTextColumn Caption="Role" FieldName="uRole" VisibleIndex="5">
                    </dxwgv:GridViewDataTextColumn>
                    <dxwgv:GridViewDataTextColumn Caption="Email" FieldName="uEmail" 
                        VisibleIndex="6">
                    </dxwgv:GridViewDataTextColumn>
                    <dxwgv:GridViewDataTextColumn Caption="Centre" FieldName="cName" 
                        VisibleIndex="7">
                    </dxwgv:GridViewDataTextColumn>
                    <dxwgv:GridViewDataDateColumn Caption="Date Reg" FieldName="uDateJoin" 
                        VisibleIndex="8">
                    </dxwgv:GridViewDataDateColumn>
                    <dxwgv:GridViewDataColumn Caption="Username" VisibleIndex="9">
                        <DataItemTemplate>
                        <%#GetDecrypt(DataBinder.Eval(Container, "DataItem.uUserName"))%>
                        </DataItemTemplate>
                    </dxwgv:GridViewDataColumn>
                    <dxwgv:GridViewDataColumn Caption="Password" VisibleIndex="10">
                        <DataItemTemplate>
                        <%#GetDecrypt(DataBinder.Eval(Container, "DataItem.uPass"))%>
                        </DataItemTemplate>
                    </dxwgv:GridViewDataColumn>
                </Columns>
                <Settings ShowFilterRow="True" />
            </dxwgv:ASPxGridView>
            <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
                ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
                
                SelectCommand="SELECT tbl_Users.uID, tbl_Users.uSurname, tbl_Users.uForename, tbl_Users.uTitle, tbl_Users.uRole, tbl_Users.uEmail, tbl_Users.uDateJoin, tbl_Users.uPass, tbl_Users.uUserName, tbl_Centres.cName FROM tbl_Users INNER JOIN tbl_Centres ON tbl_Users.uCentre = tbl_Centres.cID ORDER BY tbl_Users.uSurname">
            </asp:SqlDataSource>
        </p>
    
    </div>
    </form>
</body>
</html>
