<%@ Page Title="" Language="VB" MasterPageFile="~/MasterPage.master" AutoEventWireup="false" CodeFile="changereg.aspx.vb" Inherits="changereg" Debug="true" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
    <style type="text/css">
        .style1
        {
            width: 646px;
            border-collapse: collapse;
        }
        .style2
        {
            width: 103px;
        }
    </style>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <h1>
        Clinicians - Change your registration details</h1>
        <asp:Panel ID="pnlTest" runat="server">
    <p>
        Please enter you current username and password:</p>
    <table cellpadding="5" class="style1">
        <tr>
            <td class="style2">
        <asp:Label ID="lblUser" runat="server" Text="Username" AssociatedControlID="txtUid" Width="140px" ></asp:Label>
            </td>
            <td>
        <asp:TextBox ID="txtUid" runat="server" AutoCompleteType="Disabled" autocomplete="off"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td class="style2">
        <asp:Label ID="lblPass" runat="server" Text="Password" Width="140px" AssociatedControlID="txtPwd"></asp:Label>
            </td>
            <td>
        <asp:TextBox ID="txtPwd" runat="server" TextMode="Password" AutoCompleteType="Disabled" autocomplete="off" ></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td class="style2">
                &nbsp;</td>
            <td>
                &nbsp;</td>
        </tr>
        <tr>
            <td class="style2">
        <asp:Button ID="btnEnter" runat="server" Text="Enter" UseSubmitBehavior="true" />
            </td>
            <td>
                &nbsp;</td>
        </tr>
    </table>
    <br />
   </asp:Panel>
    <asp:Panel ID="pnlDetail" runat="server" Visible="false">
      <p>Please enter a new password</p>
            <table cellpadding="5" class="style1">
                <tr>
                    <td>
                        <asp:Label ID="lblNew" runat="server" Text="New Password"></asp:Label>
                    </td>
                    <td>
                        <asp:TextBox ID="txtNew" runat="server"></asp:TextBox> &nbsp; &nbsp;Between 6 and 10 characters
                    </td>
                </tr>
                <tr>
                    <td>
                        Please Repeat the password</td>
                    <td>
                        <asp:TextBox ID="txtRepeat" runat="server"></asp:TextBox>
                    </td>
                </tr>
                <tr>
                    <td>
                        &nbsp;</td>
                    <td>
                        &nbsp;</td>
                </tr>
                <tr>
                    <td>
                        <asp:Button ID="btnChange" runat="server" Text="Change" />
                    </td>
                    <td>
                        &nbsp;</td>
                </tr>
            </table>
         
        <p>
            <br />
        </p>
    </asp:Panel>
    <p>            
    <asp:Label ID="outMsg" runat="server" Text=""></asp:Label></p>

</asp:Content>

