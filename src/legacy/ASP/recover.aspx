<%@ Page Title="" Language="VB" MasterPageFile="~/MasterPage.master" AutoEventWireup="false" CodeFile="recover.aspx.vb" Inherits="recover" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <h2>
        Recover your password - Clinicians</h2>
    <p>
        Please enter your email address</p>
    <p>
        <asp:TextBox ID="txtEmail" runat="server" Width="300px"></asp:TextBox>
    </p>
    <p>
        <asp:Button ID="btnSubmit" runat="server" Text="Submit" />
    </p>
    <p>
        <asp:Label ID="outMsg" runat="server" Text=""></asp:Label>
    </p>
</asp:Content>

