<%@ Page Title="" Language="VB" MasterPageFile="~/admin/AdminMaster.master" AutoEventWireup="false" CodeFile="user-add.aspx.vb" Inherits="admin_user_add" %>

<%@ Register assembly="AjaxControlToolkit" namespace="AjaxControlToolkit" tagprefix="cc1" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
<style type="text/css">
.wmark {
color:Gray;
}

</style>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <asp:ScriptManager ID="ScriptManager1" runat="server">
    </asp:ScriptManager>
    <asp:UpdatePanel ID="UpdatePanel1" runat="server">
    <ContentTemplate>
    <h2>
        Admin - Add a new User</h2>
    <p>
        &nbsp;</p>
        <fieldset style="padding:10px; width:760px;">
        <table cellpadding="4" class="style1">
            <tr>
                <td style="width:180px;">
                    <asp:Label ID="lblSurname" runat="server" Text="Surname:" 
                        AssociatedControlID="txtSurname"></asp:Label>
                </td>
                <td>
                    <asp:TextBox ID="txtSurname" runat="server" Width="230px"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="RequiredFieldValidator1" runat="server" 
                        ControlToValidate="txtSurname" ErrorMessage="" Text="*"></asp:RequiredFieldValidator>
                </td>
            </tr>
            <tr>
                <td>
                    <asp:Label ID="lblForename" runat="server" Text="Forename:" 
                        AssociatedControlID="txtForename"></asp:Label>
                </td>
                <td>
                    <asp:TextBox ID="txtForename" runat="server" Width="230px"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="RequiredFieldValidator2" runat="server" 
                        ControlToValidate="txtForename" ErrorMessage="" Text="*"></asp:RequiredFieldValidator>
                    </td>
            </tr>
            <tr>
                <td>
                    <asp:Label ID="lblTitle" runat="server" Text="Title:" 
                        AssociatedControlID="txtGMC"></asp:Label>
                </td>
                <td>
                    <asp:DropDownList ID="DropDownList2" runat="server">
                        <asp:ListItem Value="">Select</asp:ListItem>
                        <asp:ListItem>Dr</asp:ListItem>
                        <asp:ListItem>Professor</asp:ListItem>
                        <asp:ListItem>Mr</asp:ListItem>
                        <asp:ListItem>Mrs</asp:ListItem>
                        <asp:ListItem>Miss</asp:ListItem>
                    </asp:DropDownList>
                    <asp:RequiredFieldValidator ID="RequiredFieldValidator3" runat="server" 
                        ControlToValidate="DropDownList2" ErrorMessage="" Text="*"></asp:RequiredFieldValidator>
                </td>
            </tr>
            <tr>
                <td>
                    <asp:Label ID="lblGMC" runat="server" Text="GMC No.:"></asp:Label>
                </td>
                <td>
                    <asp:TextBox ID="txtGMC" runat="server" Width="230px"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="RequiredFieldValidator4" runat="server" 
                        ControlToValidate="txtGMC" ErrorMessage="" Text="*"></asp:RequiredFieldValidator>
                </td>
            </tr>
            <tr>
                <td>
                    <asp:Label ID="lblRole" runat="server" Text="Role:" 
                        AssociatedControlID="txtRole"></asp:Label>
                </td>
                <td>
                    <asp:TextBox ID="txtRole" runat="server" Width="230px"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="RequiredFieldValidator5" runat="server" 
                        ControlToValidate="txtRole" ErrorMessage="" Text="*"></asp:RequiredFieldValidator>
                </td>
            </tr>
            <tr>
                <td>
                    <asp:Label ID="lblEmail" runat="server" Text="NHS Email Address:" 
                        AssociatedControlID="txtEmail"></asp:Label>
                </td>
                <td>
                    <asp:TextBox ID="txtEmail" runat="server" Width="230px"></asp:TextBox> <asp:RequiredFieldValidator
                        ID="RequiredFieldValidator6" runat="server" ErrorMessage="" Text="*" ControlToValidate="txtEmail"></asp:RequiredFieldValidator>
                    <%--<asp:RegularExpressionValidator ID="RegularExpressionValidator1" runat="server" 
                        ErrorMessage="" Text="Invalid address" 
                        
                        
                        ValidationExpression="^[a-zA-Z][\w\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\w\.-]*[a-zA-Z0-9]\.nhs.net$"
                        ControlToValidate="txtEmail"></asp:RegularExpressionValidator>--%>
                </td>
            </tr>
            <tr>
                <td>
                    Telephone Number:</td>
                <td>
                    <asp:TextBox ID="txtPhone" runat="server" Width="230px"></asp:TextBox>
                </td>
            </tr>
            <tr>
                <td>
                    <asp:Label ID="lblPwd" runat="server" Text="Password" AssociatedControlID="txtPwd"></asp:Label>
                    : <span style="font-size:80%;">optional<br />
                    auto-generated if left blank</span></td>
                <td>
                    <asp:TextBox ID="txtPwd" runat="server" Width="230px"></asp:TextBox>
                    <cc1:TextBoxWatermarkExtender ID="txtPwd_TextBoxWatermarkExtender" 
                        runat="server" Enabled="True" TargetControlID="txtPwd" 
                        WatermarkCssClass="wmark" WatermarkText="8 chars minimum, numeric and text">
                    </cc1:TextBoxWatermarkExtender>
                    <cc1:PasswordStrength ID="txtPwd_PasswordStrength" runat="server" 
                        Enabled="True" MinimumLowerCaseCharacters="2" MinimumNumericCharacters="2" 
                        MinimumUpperCaseCharacters="2" PreferredPasswordLength="8" 
                        RequiresUpperAndLowerCaseCharacters="True" TargetControlID="txtPwd">
                    </cc1:PasswordStrength>
&nbsp;<asp:TextBox ID="txtNewID" runat="server" Visible="false"></asp:TextBox>
                </td>
            </tr>
            <tr>
                <td>
                    <asp:Label ID="lblCentre" runat="server" Text="Centre:" 
                        AssociatedControlID="DropDownList1"></asp:Label>
                </td>
                <td>
                    <asp:DropDownList ID="DropDownList1" runat="server" 
                        DataSourceID="SqlDataSource1" DataTextField="cName" DataValueField="cID" AppendDataBoundItems="true">
                        <asp:ListItem Value="">Select</asp:ListItem>
                    </asp:DropDownList>
                    <asp:RequiredFieldValidator ID="RequiredFieldValidator7" runat="server" 
                        ControlToValidate="DropDownList1" ErrorMessage="" Text="*" ></asp:RequiredFieldValidator>
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
            <asp:Button ID="btnSubmit" runat="server" Text="Add" CssClass="inline" />
                </td>
                <td>
                    <asp:ValidationSummary ID="ValidationSummary1" runat="server" DisplayMode="SingleParagraph" HeaderText="* Required Fields" CssClass="inline" />
                </td>
            </tr>
        </table><p>
            <asp:Label ID="lblMsg" runat="server" Text=""></asp:Label>
                    <p><asp:Button ID="btnEmail" runat="server" Text="Send Email" />
                        &nbsp;
                        <asp:Label ID="lblEmailUser" runat="server" Text="Send the user site access details"></asp:Label>
                        </p>
            <p>
                <asp:Button ID="btnNew" runat="server" Text="Add another user" />
            </p></fieldset>
            
            </ContentTemplate>
    </asp:UpdatePanel>
                    <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
                        ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
                        SelectCommand="SELECT [cID], [cName] FROM [tbl_Centres] ORDER BY [cID]">
                    </asp:SqlDataSource>
</asp:Content>

