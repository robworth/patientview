<%@ Page Title="" Language="VB" MasterPageFile="~/MasterPage3.master" AutoEventWireup="false" CodeFile="patients.aspx.vb" Inherits="patients" %>

<%@ Register assembly="AjaxControlToolkit" namespace="AjaxControlToolkit" tagprefix="cc1" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
    <style type="text/css">
        .style2
        {
            width: 450px;
            border-collapse: collapse;
        }
        .feint {
        color:Silver;
        }
    </style>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <asp:ScriptManager ID="ScriptManager1" runat="server">
    </asp:ScriptManager>
<h1 style="display:inline">Patient Log-In</h1>&nbsp;&nbsp;&nbsp;
 <span style="margin-left:400px;"><a href="javascript:bookmarksite('RADAR', 'http://www.renal-test.org/patients.aspx')">Bookmark this page</a>
</span>
    <p>Please enter your username, password and date of birth</p>
    <table cellpadding="5" class="style2">
        <tr>
            <td>
                <asp:Label ID="lblUserName" runat="server" AssociatedControlID="txtUserName" 
                    Text="Username" Width="120px"></asp:Label>
            </td>
            <td>
                <asp:TextBox ID="txtUserName" runat="server" Width="250px" AutoCompleteType="Disabled" autocomplete="off"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td>
                <asp:Label ID="lblPwd" runat="server" AssociatedControlID="txtPwd" 
                    Text="Password" Width="120px"></asp:Label>
            </td>
            <td>
                <asp:TextBox ID="txtPwd" runat="server" TextMode="Password" AutoCompleteType="Disabled" autocomplete="off"></asp:TextBox>
            </td>
        </tr>
        <tr>
            <td>
                <asp:Label ID="lblDOB" runat="server" AssociatedControlID="txtDOB" Text="DoB" 
                    Width="120px"></asp:Label>
            </td>
            <td>
                <asp:TextBox ID="txtDOB" runat="server" ></asp:TextBox> <cc1:TextBoxWatermarkExtender ID="txtDOB_TextBoxWatermarkExtender" 
                runat="server" Enabled="True" TargetControlID="txtDOB" WatermarkText="Select with the Calendar" WatermarkCssClass="feint">
            </cc1:TextBoxWatermarkExtender>
                <cc1:CalendarExtender ID="txtDOB_CalendarExtender" runat="server" 
                    Enabled="True" TargetControlID="txtDOB" Format="dd-MM-yyyy" PopupButtonID="ImageButton1">
                </cc1:CalendarExtender><asp:ImageButton ID="ImageButton1" runat="server" CausesValidation="False" ImageUrl="~/images/Calendar_scheduleHS.png"  />
                &nbsp;&nbsp;<a href="#" class="info" style="background-color:White"><em>Help</em><span>To select a different month in the 
                date picker, click the current month at the top.<br /><br /> Click again to select 
                a year.</span>  </a></td>
        </tr>
        <tr>
            <td>
                <asp:Button ID="btnEnter" runat="server" Text="Enter" />
            </td>
            <td>
                &nbsp;</td>
        </tr>
    </table>
    <p><a href="patient-recover.aspx">Forgotten your password?</a></p>
    <p><a href="patient-reg.aspx">Not yet registered?</a></p>
    <p>
        <asp:Label ID="lblOutMsg" runat="server"></asp:Label>
    </p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
</asp:Content>

