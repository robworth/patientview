<%@ Page Language="VB" AutoEventWireup="false" MasterPageFile="~/MasterPage.master" CodeFile="register.aspx.vb" Inherits="register" %>

<%@ Register assembly="DevExpress.Web.ASPxEditors.v9.2, Version=9.2.4.0, Culture=neutral, PublicKeyToken=b88d1754d700e49a" namespace="DevExpress.Web.ASPxEditors" tagprefix="dxe" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
    <link href="normal.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
        .style1
        {
            width: 750px;
            border-collapse: collapse;
        }
        </style></asp:Content>

<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <div>
    
        <h1>
            Health Professional
            Registration</h1>
        <p>
            To register please enter your name, NHS email address and Centre below. </p>
        <p>
            Once 
            validated you will be given a username and password to enter the site</p>
            <fieldset style="padding:10px; width:760px;">
        <table cellpadding="4" class="style1">
            <tr>
                <td style="width:140px;">
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
                    <asp:Label ID="lblGMC" runat="server" Text="GMC/NMC No.:"></asp:Label>
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
                    Telephone number:</td>
                <td>
                    <asp:TextBox ID="txtPhone" runat="server" Width="230px"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="RequiredFieldValidator8" runat="server" 
                        ControlToValidate="txtPhone" ErrorMessage="*"></asp:RequiredFieldValidator>
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
            <asp:Button ID="btnSubmit" runat="server" Text="Register" CssClass="inline" />
                </td>
                <td>
                    <asp:ValidationSummary ID="ValidationSummary1" runat="server" DisplayMode="SingleParagraph" HeaderText="* Required Fields" CssClass="inline" />
                </td>
            </tr>
        </table><p>
            <asp:Label ID="lblMsg" runat="server" Text=""></asp:Label></p>
                    &nbsp;</fieldset>
                    <p><asp:Label ID="lblDebug" runat="server"></asp:Label></p>
                    <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
                        ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
                        SelectCommand="SELECT [cID], [cName] FROM [tbl_Centres] ORDER BY [cID]">
                    </asp:SqlDataSource>
    
    </div>
   
    </asp:content>
