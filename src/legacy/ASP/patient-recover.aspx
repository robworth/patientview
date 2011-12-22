<%@ Page Title="" Language="VB" MasterPageFile="~/MasterPage.master" AutoEventWireup="false" CodeFile="patient-recover.aspx.vb" Inherits="patient_recover" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <h1>
        Patients - Recover Your Password</h1>
    <p>
        Please enter your email address</p>
    <p>
        <asp:TextBox ID="txtEmail" runat="server" Width="250px"></asp:TextBox>
    </p>
    <p>
        <asp:Button ID="btnSubmit" runat="server" Text="Submit" />
    </p>
    <p>
        &nbsp;</p>
    <p>
        <asp:Label ID="lblMsg" runat="server"></asp:Label>
    </p>
</asp:Content>

