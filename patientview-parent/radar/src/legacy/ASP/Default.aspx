<%@ Page Title="RADAR - National Renal Rare Disease Registry" Language="VB" MasterPageFile="~/MasterPage3.master" AutoEventWireup="false" CodeFile="Default.aspx.vb" Inherits="Default3" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
	<style type="text/css">
	.auto-style1 {
		margin-right: 38px;
		margin-bottom: 47px;
	margin-top: 0px;
}
	.auto-style3 {
		margin-right: 60px;
		margin-bottom: 42px;
	}
	.auto-style4 {
		margin-right: 14px;
		margin-bottom: 0px;
	margin-top: 13px;
}
	.auto-style5 {
		margin-bottom: 47px;
	}
	</style>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
	<table style="width:100%; border-collapse:collapse">
    <tr>
    <td style="padding-right:30px; vertical-align:top;">
<h1>RaDaR</h1>
	<p>Welcome to the homepage of the UK Registry for Rare Kidney Diseases, eponym 
        RADAR. </p>
        <%--<div style="background-color:lemonchiffon; border:1px solid black; padding:20px; width:600px; height:40px;">
        <p style="font-weight: 700">The database is being recreated and is currently unavailable</p>
        </div>--%>
        <asp:Panel runat="server" ID="pnlDemo" Visible="false">
        <div style="background-color:lemonchiffon; border:1px solid black; padding:20px; width:600px; height:70px;">
        <p>The <b>Renal Radar</b> site is now live at <a href="http://www.renalradar.org">www.renalradar.org</a></p>
        <p>This test site should only be used for development and training puposes.</p>
        </div></asp:Panel>
        <p>The purpose of the registry is to assemble cohorts of patients whose kidney 
            disease is thoroughly investigated and characterised. Patients in the registry 
            can then be approached to participate in &quot;translational&quot; research. Translational 
            research aims to connect the basic scientific investigation of a disease to 
            tangible benefit for the patient. Benefits might include better diagnosis, 
            patient information, treatment trials, and ultimately better care.</p>
        <p>Patient involvement in the registry is strongly encouraged. </p>
        <p>RADAR is an initiative of the Renal Association and the British Association for 
            Paediatric Nephrology. It operates within the UK Renal Registry, and its governance 
            lies with the Renal Association. The initiative is supported by the Medical 
            Research Council and Kidney Research UK. </p>
        <p>To go to the Disease Index <a href="default5.aspx">NEXT </a> </p>
        <p>&nbsp;</p>
        <div id="pnlLogos" style="width:90%; height:120px; margin-left:auto; margin-right:auto; text-align:center;">
        <p>
            &nbsp;
            <img alt="The Renal Organisation" src="images/logos/ra_60_logo.gif" 
                style="height: 90px; margin-left: 15px;" 
                title="The Renal Organisation" class="auto-style1" /><img alt="British Association of Paediatric Nephrology" title="British Association of Paediatric Nephrologists" class="auto-style4" height="150" src="images/logos/bapn-logo3.jpg" width="150" />&nbsp;
            <img alt="UK Renal Registry" 
                src="images/logos/Reports_logo%5B1%5D.gif"
                style="height: 90px; " title="UK Renal Registry" class="auto-style3" /><img alt="Medical Research Council" src="images/logos/logo_mrc.gif" 
                style="width: 181px; height: 79px; " title="Medical Research Council" class="auto-style5" /></p>
                
                </div>
        <p>&nbsp;</p><p>
            <asp:Label ID="lblSession" runat="server" Text=""></asp:Label>
        </p>
	</td>
	<%--<td id="tbRH" style="width:300px; padding-left:20px; vertical-align:top; border-left:1px solid green; " runat="server">
	<h1>Select a Disease</h1>
	<p>
        <asp:LinkButton ID="lnkMPGN" runat="server" PostBackUrl="~/Default3.aspx?d=2" Font-Size="110%">MPGN</asp:LinkButton></p>
        
    <p>    <asp:LinkButton ID="lnkFSGS" runat="server" PostBackUrl="~/Default3.aspx?d=1" Font-Size="110%">FSGS</asp:LinkButton></p>
        
	</td>--%>
	</tr>
	</table>
</asp:Content>

