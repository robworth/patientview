<%@ Page Title="" Language="VB" MasterPageFile="~/admin/AdminMaster.master" AutoEventWireup="false" CodeFile="patients-dxgrid.aspx.vb" Inherits="admin_patients" %>

<%@ Register assembly="DevExpress.Web.ASPxGridView.v9.2, Version=9.2.4.0, Culture=neutral, PublicKeyToken=b88d1754d700e49a" namespace="DevExpress.Web.ASPxGridView" tagprefix="dxwgv" %>
<%@ Register assembly="DevExpress.Web.ASPxEditors.v9.2, Version=9.2.4.0, Culture=neutral, PublicKeyToken=b88d1754d700e49a" namespace="DevExpress.Web.ASPxEditors" tagprefix="dxe" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">

</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <asp:ScriptManager ID="ScriptManager1" runat="server">
    </asp:ScriptManager>
    <h2>
    Admin - Patients</h2>
    <p></p>
    <asp:UpdatePanel ID="UpdatePanel1" runat="server">
    <ContentTemplate>
    

    <dxwgv:ASPxGridView ID="ASPxGridView1" ClientInstanceName="ASPxGridView1" runat="server" 
        AutoGenerateColumns="False" DataSourceID="SqlDataSource1" KeyFieldName="pID" OnCustomButtonCallback="ASPxGridView1_CustomButtonCallback">
        <Columns>
            <dxwgv:GridViewCommandColumn VisibleIndex="0">
                <ClearFilterButton Visible="True">
                </ClearFilterButton>
            </dxwgv:GridViewCommandColumn>
            <dxwgv:GridViewDataTextColumn FieldName="pID" ReadOnly="True" Visible="True" 
                VisibleIndex="1">
                <EditFormSettings Visible="False" />
<EditFormSettings Visible="False"></EditFormSettings>
            </dxwgv:GridViewDataTextColumn>
            <dxwgv:GridViewDataTextColumn FieldName="RADAR_NO" VisibleIndex="1" 
                Caption="RADAR No">
            </dxwgv:GridViewDataTextColumn>
            <dxwgv:GridViewDataTextColumn FieldName="pUserName" VisibleIndex="2" 
                Caption="Username">
            </dxwgv:GridViewDataTextColumn>
            <dxwgv:GridViewDataTextColumn Caption="Password" VisibleIndex="4">
            <DataItemTemplate>
                        <%# GetDecrypt(DataBinder.Eval(Container, "DataItem.pPassword"))%>
            </DataItemTemplate>
            </dxwgv:GridViewDataTextColumn>
            <dxwgv:GridViewDataDateColumn FieldName="pDOB" VisibleIndex="3" Caption="DOB">
            </dxwgv:GridViewDataDateColumn>
            <dxwgv:GridViewDataDateColumn FieldName="pDateReg" VisibleIndex="5" 
                Caption="Date Reg">
            </dxwgv:GridViewDataDateColumn>
            <dxwgv:GridViewCommandColumn Caption="Email" VisibleIndex="6">
           <CustomButtons>
           <dxwgv:GridViewCommandColumnCustomButton Text="Email" ID="Email"></dxwgv:GridViewCommandColumnCustomButton>
           </CustomButtons>
            </dxwgv:GridViewCommandColumn>
        </Columns>
        <Settings ShowFilterRow="True" />

<Settings ShowFilterRow="True"></Settings>
    </dxwgv:ASPxGridView>
    <p>&nbsp;</p>
    <p><asp:Label ID="lblDebug" runat="server"></asp:Label></p>
    </ContentTemplate>
    </asp:UpdatePanel>
    <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
        ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
        SelectCommand="SELECT [pID], [RADAR_NO], [pUserName], [pPassWord], [pDOB], [pDateReg] FROM [tbl_Patient_Users]">
    </asp:SqlDataSource>

</asp:Content>

