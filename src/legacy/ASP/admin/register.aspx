<%@ Page Language="VB" AutoEventWireup="false" CodeFile="register.aspx.vb" Inherits="admin_register" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
    <h1>
            Admin User
            Registration</h1>
        <p>
            To register please enter your name and email address below. </p>
            <fieldset style="padding:10px; width:760px;">
        <table cellpadding="4" class="style1">
            <tr>
                <td style="width:140px;">
                    <asp:Label ID="lblName" runat="server" Text="Name:" 
                        AssociatedControlID="txtName"></asp:Label>
                </td>
                <td>
                    <asp:TextBox ID="txtName" runat="server" Width="230px"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" 
                        ControlToValidate="txtName" ErrorMessage="" Text="*"></asp:RequiredFieldValidator>
                </td>
            </tr>
          
            
            <tr>
                <td>
                    <asp:Label ID="lblEmail" runat="server" Text="Email Address:" 
                        AssociatedControlID="txtEmail"></asp:Label>
                </td>
                <td>
                    <asp:TextBox ID="txtEmail" runat="server" Width="230px"></asp:TextBox> <asp:RequiredFieldValidator
                        ID="RequiredFieldValidator6" runat="server" ErrorMessage="" Text="*" ControlToValidate="txtEmail"></asp:RequiredFieldValidator>
                   
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
            <asp:Button ID="btnSubmit" runat="server" Text="Register" CssClass="inline" />
                </td>
                <td>
                    <asp:ValidationSummary ID="ValidationSummary1" runat="server" DisplayMode="SingleParagraph" HeaderText="* Required Fields" CssClass="inline" />
                </td>
            </tr>
        </table>
        </fieldset>
    </div>
    <p>&nbsp;</p>
    <asp:Label ID="lblMsg" runat="server" Text=""></asp:Label>
    </form>
</body>
</html>
