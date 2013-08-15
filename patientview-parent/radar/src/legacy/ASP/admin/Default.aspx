<%@ Page Title="" Language="VB" MasterPageFile="~/admin/AdminMaster.master" AutoEventWireup="false" CodeFile="Default.aspx.vb" Inherits="admin_Default" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <asp:Panel ID="pnlLogIn" runat="server">
<h1 style="display:inline;">
        Log In</h1>&nbsp;&nbsp;&nbsp;
 
    <p>
        Please enter you username and password</p>
    <p>
        <asp:Label ID="lblUser" runat="server" Text="Username" AssociatedControlID="txtUid" Width="140px" ></asp:Label>
        <asp:TextBox ID="txtUid" runat="server"></asp:TextBox>
    </p>
    <p>
        <asp:Label ID="lblPass" runat="server" Text="Password" Width="140px" AssociatedControlID="txtPwd"></asp:Label>&nbsp;
        <asp:TextBox ID="txtPwd" runat="server" TextMode="Password" ></asp:TextBox>
    </p>
    <p>
        <asp:Button ID="btnEnter" runat="server" Text="Enter"  />
    </p>
    <p>
        <asp:Label ID="outMsg" runat="server" Text=""></asp:Label>
    </p>
    <p>
        <asp:Label ID="warnMsg" runat="server" Text=""></asp:Label>
    </p>
    </asp:Panel>
    <asp:Panel ID="pnlLoggedIn" runat="server">
    <h1>Logged In</h1>
        <p>
            &nbsp;</p>
        <p>
            Use the menus above for data entry and management.</p>
        <p>
            <asp:Label ID="lblDebug" runat="server" Text=""></asp:Label>
        </p>
            <p>
                &nbsp;</p>
           
        <p>
            &nbsp;</p>
    </asp:Panel>
</asp:Content>

