<%@ Page Title="RADAR National Renal Rare Disease Registry" Language="VB" MasterPageFile="~/MasterPage.master" AutoEventWireup="false" CodeFile="professionals.aspx.vb" Inherits="professionals" ClientTarget="uplevel" %>
<%@ Register assembly="DevExpress.XtraCharts.v9.2.Web, Version=9.2.4.0, Culture=neutral, PublicKeyToken=b88d1754d700e49a" namespace="DevExpress.XtraCharts.Web" tagprefix="dxchartsui" %>
<%@ Register assembly="DevExpress.XtraCharts.v9.2, Version=9.2.4.0, Culture=neutral, PublicKeyToken=b88d1754d700e49a" namespace="DevExpress.XtraCharts" tagprefix="cc1" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">

    <script type="text/javascript" src="bookmark.js"></script>

    <style type="text/css">
        .style2
        {
            width: 400px;
            border-collapse: collapse;
        }
        .style3
        {
            width: 85px;
            
        }
    .pageLabel {
    position:relative;
    bottom:-15px;
    left:0px;
    }    
       
    </style>

</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <asp:Panel ID="pnlLogIn" runat="server">
<h1 style="display:inline;">
        Health Professional Log In</h1>&nbsp;&nbsp;&nbsp;
 
    <p>Please enter your username and password<span style="margin-left:400px;"><a runat="server" id="lnkBkmark" href="javascript:bookmarksite('RADAR', 'http://www.renalradar.org/professionals.aspx')">Bookmark this page</a>
</span>
        </p>
        <table cellpadding="8" class="style2">
            <tr>
                <td class="style3">
                    <asp:Label ID="lblUser" runat="server" AssociatedControlID="txtUid" 
                        Text="Username" ></asp:Label>
                </td>
                <td>
                    <asp:TextBox ID="txtUid" runat="server"  Width="200px"></asp:TextBox>
                </td>
            </tr>
            <tr>
                <td class="style3">
                    <asp:Label ID="lblPass" runat="server" AssociatedControlID="txtPwd" 
                        Text="Password"></asp:Label>
                </td>
                <td>
                    <asp:TextBox ID="txtPwd" runat="server"  
                        TextMode="Password" ></asp:TextBox>
                </td>
            </tr>
        </table>
        <asp:Panel ID="pnlDemo" runat="server" Visible="false">
      <table style="float:right; width:300px; margin-right:150px; margin-top:-70px; background-color:lemonchiffon; border:1px solid black" cellpadding="10px">
        <tr>
        <td>
         <p>The <b>Renal Radar</b> site is now live at <a href="http://www.renalradar.org">www.renalradar.org</a></p>
        <p>This test site should only be used for development and training purposes.</p>
        </td>
        </tr>
        </table>
        </asp:Panel>
    <p>
        &nbsp;
        <asp:Button ID="btnEnter" runat="server" Text="Enter" />
    </p>
    <p>
        <a runat="server" id="lnkRecover" href="recover.aspx">Forgotten your password?</a></p>
    <p>
        <a runat="server" id="lnkChange" href="changereg.aspx">Change Registration Details</a></p>
    <p>
        <asp:Label ID="outMsg" runat="server" Text=""></asp:Label>
    </p>
    <p>
        <asp:Label ID="warnMsg" runat="server" Text=""></asp:Label>
    </p>
    </asp:Panel>
    <asp:Panel ID="pnlLoggedIn" runat="server">
    <h1>Professionals</h1>
        <p>
            This section of the website allows professional users to enter and maintain 
            patient details.
        </p>
        <p>
            You are also able to give access to these details to other users at your centre.</p>
        <p>
            &nbsp;</p>
        <p>
            Use the menus above for patient data entry and management.</p>
        <p>
            <asp:Label ID="lblDebug" runat="server" Text=""></asp:Label>
        </p>
            <p>
                &nbsp;</p>
              <div id="regPatients" style="width:750px; border: 1px solid green; padding:20px;" runat="server">
              
                <asp:UpdatePanel ID="UpdatePanel2" runat="server">
                <ContentTemplate>
            <p><asp:Label ID="lblCentre" runat="server" Text="" Font-Bold="true"></asp:Label></p>
            <p>The number of patients currently registered at this unit is: <asp:Label ID="lblReg" runat="server" Text=""></asp:Label>
                &nbsp;
                <asp:Button ID="btnGraph" runat="server" Text="Graph" />
                </p>
                <p>
                    <asp:Label ID="lblCentres" runat="server" Font-Bold="True" Visible="False"></asp:Label>
                </p>
            </ContentTemplate>
            </asp:UpdatePanel>
            <asp:UpdateProgress ID="UpdateProgress1" runat="server" 
            AssociatedUpdatePanelID="UpdatePanel2" DisplayAfter="100">
            <ProgressTemplate><p><img src="images/chart-load.gif" title="Chart loading" alt="Chart loading" /> </p></ProgressTemplate>
            </asp:UpdateProgress>
             </div>
       <p>
            &nbsp;</p>
            <p>If you have any comments or questions about this website or need help using it, 
                please contact
                <br />
                Fiona Braddon on 0117 323 8209 or by email at <a href="mailto:fiona.braddon@nhs.net">fiona.braddon@nhs.net</a> </p>
       
    </asp:Panel>
    <p></p> 
    <p><asp:Label ID="lblPage" runat="server" Text="17" CssClass="pageLabel"></asp:Label></p>
</asp:Content>

