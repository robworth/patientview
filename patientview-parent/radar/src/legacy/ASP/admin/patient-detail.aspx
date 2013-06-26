<%@ Page Title="" Language="VB" MasterPageFile="~/admin/AdminMaster.master" AutoEventWireup="false" CodeFile="patient-detail.aspx.vb" Inherits="admin_patient_detail" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
    <style type="text/css">
        .style1
        {
            width: 500px;
            border-collapse: collapse;
            border-style: solid;
            border-width: 1px;
        }
        .style2
        {
            width: 132px;
        }
        .style3
        {
            margin-bottom: 0px;
        }
    </style>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <asp:ScriptManager ID="ScriptManager1" runat="server">
    </asp:ScriptManager> 
    <h2>Admin - Patients</h2>
    <asp:UpdatePanel ID="UpdatePanel1" runat="server">
    <ContentTemplate>
        <p>&nbsp;</p>
    <table cellpadding="5" class="style1">
        <tr>
            <td class="style2">
                Radar Number</td>
            <td>
                <asp:Label ID="lblRDAR_NO" runat="server" Text=""></asp:Label>
            </td>
        </tr>
        <tr>
            <td class="style2">
                First Name</td>
            <td>
                <asp:Label ID="txtFNAME" runat="server"></asp:Label>
            </td>
        </tr>
        <tr>
            <td class="style2">
                Surname</td>
            <td>
                <asp:Label ID="txtSNAME" runat="server"></asp:Label>
            </td>
        </tr>
        <tr>
            <td class="style2">
                Username</td>
            <td>
                <asp:TextBox ID="txtUSERNAME" runat="server" Width="300px" CssClass="style3"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td class="style2">
                Password</td>
            <td>
                <asp:TextBox ID="txtPWD" runat="server"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td class="style2">
                DOB</td>
            <td>
                <asp:Label ID="txtDOB" runat="server"></asp:Label>
            </td>
        </tr>
        <tr>
            <td class="style2">
                Date Registered</td>
            <td>
                <asp:label ID="txtDATE_REG" runat="server"></asp:label>
            </td>
        </tr>
        <tr>
            <td class="style2">
                <asp:Button ID="btnUpdate" runat="server" Text="Update" />
            &nbsp;</td>
            <td>
                &nbsp;</td>
        </tr>
        <tr>
            <td colspan="2">
                <asp:Button ID="btCancel" runat="server" Text="Back to the Patient list" />
            </td>
        </tr>
    </table>
    <p>
        <asp:Label ID="lblUpdate" runat="server"></asp:Label>
    </p>
    <p><asp:Label ID="lblDebug" runat="server"></asp:Label></p>
    </ContentTemplate>
    </asp:UpdatePanel>
</asp:Content>

