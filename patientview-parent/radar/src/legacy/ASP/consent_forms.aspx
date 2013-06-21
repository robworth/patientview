<%@ Page Title="" Language="VB" MasterPageFile="~/MasterPage.master" AutoEventWireup="false" CodeFile="consent_forms.aspx.vb" Inherits="consent_forms" %>

<%@ Register Assembly="AjaxControlToolkit" Namespace="AjaxControlToolkit" TagPrefix="asp" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
	<script type="text/javascript">
<!--
function EW_jumpMenu(el,frm,sel) {//v1.0
 var href=el.options[el.selectedIndex].value; if(sel) el.selectedIndex=0;
 if('_new'==frm) open(href); else eval(frm+".location='"+href+"'");
}
// -->
</script>
    <style type="text/css">
        .style1
        {
            font-weight: normal;
        }
    </style>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
	<h2>
    Consent Forms</h2>
    <p>
        &nbsp;</p>
<%-- <p style="font-weight: 700">
    RADAR&nbsp;&nbsp;
    
    <select id="id1" onchange="EW_jumpMenu(this,'_new',true)" size="1" name="D1">
	<option value="">Select form</option>
	<option value="docs/National Registry Adolescent assent form v1 30 10 09.pdf">
	National Registry Adolescent assent form</option>
	<option value="docs/National Registry Consent form parents V2 16-11-09 (2).pdf">
	National Registry Consent Form parents</option>
	<option value="docs/National Registry Consent form participants V3 12-11-09 (2).pdf">
	National Registry Consent Form Participants</option>
	<option value="docs/National Registry PIS adolescents v4 11-11-09.pdf">
	National Registry PIS Adolescents</option>
	<option value="docs/National Registry PIS children v3 29-10-09 (2).pdf">
	National Registry PIS Children</option>
	<option value="docs/National Registry PIS parents v4 26-08-091.pdf">National 
	Registry PIS Parents</option>
	<option value="docs/National Registry PIS v4 26-08-091.pdf">National 
	Registry PIS</option>
	</select></p>
 --%>    <p style="font-weight: 700">
        <asp:Image ID="Image1" runat="server" />&nbsp; RaDaR National Registry Forms</p>
    <asp:Panel ID="pnlNational" runat="server">
    <blockquote>
    <p>
	<a href="docs/national2/Adolescent%20Assent%20Form.pdf" target="_blank" title="Adolescent Assent Form">
	Adolescent Assent Form</a></p>
        <p>
            <a href="docs/national2/Adolescent%20Info%20Sheet.pdf" target="_blank" title="Adolescent Information Sheet">
			Adolescent Information Sheet</a></p>
        <p>
            <a href="docs/national2/Children's%20Info%20Sheet.pdf" target="_blank" title="Children's Information Sheet">
			Children&#39;s Information Sheet</a></p>
        <p>
		<a href="docs/national2/Adult%20Patient%20Consent%20Form.pdf" target="_blank" title="Adult Patient Consent Form">
		Adult Patient Consent Form</a></p>
		<p>
		<a href="docs/national2/Adult%20Patient%20Info%20Sheet.pdf" target="_blank" title="Adult Patient Information Sheet">
		Adult Patient Information Sheet</a></p>
        <p>
            <a href="docs/national2/GP-Consultant%20Info%20Sheet.pdf" target="_blank" title="GP Information Sheet">
			GP Information Sheet</a></p>
        <p>
            <a href="docs/national2/Parent-Guardian%20Consent%20Form.pdf" target="_blank" title="Parent Guardian Consent Form">
			Parent Guardian Consent Form</a></p>
        <p>
            <a href="docs/national2/Parent's%20Info%20Sheet.pdf" target="_blank" title="Parent Guardian Information Sheet">
			Parent Guardian Information Sheet</a></p>
    </blockquote>

    </asp:Panel>
    <asp:CollapsiblePanelExtender ID="CollapsiblePanelExtender2" runat="server" CollapseControlID="Image1" CollapsedImage="~/images/expand.jpg" ExpandedImage="~/images/collapse.jpg" Collapsed="true" ImageControlID="Image1" TargetControlID="pnlNational" ExpandControlID="Image1">
    </asp:CollapsiblePanelExtender>
   
    
    <p style="font-weight: 700"> 
        <asp:Image ID="Image10" runat="server" />&nbsp; MPGN<span class="style1"> 
        (Specific to your centre if available)</span></p>
    <asp:Panel ID="pnlMPGN" runat="server">
    <p>MPGN Consent Forms are being collated</p>
    </asp:Panel>
    <asp:CollapsiblePanelExtender ID="pnlMPGN_CollapsiblePanelExtender" 
        runat="server" Enabled="True" TargetControlID="pnlMPGN" CollapseControlID="Image10" ExpandControlID="Image10" Collapsed="true" CollapsedImage="~/images/expand.jpg" ExpandedImage="~/images/collapse.jpg" ImageControlID="IMage10">
    </asp:CollapsiblePanelExtender>
    <p style="font-weight: 700"><asp:Image ID="Image9" runat="server" />&nbsp; SRNS
        <span class="style1">(Specific to your centre if available)</span></p>
    <asp:Panel ID="pnlSRNS" runat="server">

    <asp:Panel ID="pnlGenericSRNS" runat="server" Visible="false" >
    <p><strong>Generic SRNS Consent Forms</strong></p>
    <blockquote>
    <p>
	<a href="docs/generic/Adolescent%20Assent%20Form.pdf" target="_blank" title="Adolescent Assent Form">
	Adolescent Assent Form</a></p>
        <p>
            <a href="docs/generic/Adolescent%20Info%20Sheet.pdf" target="_blank" title="Adolescent Information Sheet">
			Adolescent Information Sheet</a></p>
        <p>
            <a href="docs/generic/Children's%20Info%20Sheet.pdf" target="_blank" title="Children's Information Sheet">
			Children&#39;s Information Sheet</a></p>
        <p>
            <a href="docs/generic/GP-Consultant%20Info%20Sheet.pdf" target="_blank" title="GP-Consultant Information Sheet">
			GP-Consultant Information Sheet</a></p>
        <p>
            <a href="docs/generic/Letter%20of%20invitation.pdf" target="_blank" title="GP-Consultant Information Sheet">
			Letter of Invitation</a></p>
        <p>
            <a href="docs/generic/Parent-Guardian%20Consent%20Form.pdf" target="_blank" title="Parent Guardian Consent Form">
			Parent Guardian Consent Form</a></p>
        <p>
            <a href="docs/generic/Parent-Guardian%20Info%20Sheet.pdf" target="_blank" title="Parent Guardian Information Sheet">
			Parent Guardian Information Sheet</a></p>
            </blockquote>
    </asp:Panel>
     
    <asp:Panel ID="pnlBhamSRNS" runat="server" Visible="false">
     <blockquote>
    <p>
	<a href="docs/birmingham/BCH%20Adolescent%20Assent%20Form.pdf" target="_blank" title="Adolescent Assent Form">Adolescent Assent 
        Form</a></p>
        <p>
            <a href="docs/birmingham/BCH%20Adolescent%20Info%20Sheet.pdf" target="_blank" title="Adolescent Information Sheet">Adolescent 
            Information Sheet</a></p>
        <p>
            <a href="docs/birmingham/BCH%20Children's%20Info%20Sheet.pdf" target="_blank" title="Children's Information Sheet">
			Children&#39;s Information Sheet</a></p>
        <p>
            <a href="docs/birmingham/BCH%20GP%20Info%20Sheet.pdf" target="_blank" title="GP Information Sheet">
			GP Information Sheet</a></p>
        <p>
            <a href="docs/birmingham/BCH%20Letter%20of%20Invitation.pdf" target="_blank" title="Letter of Invitation">
			Letter of Invitation</a></p>
        <p>
            <a href="docs/birmingham/BCH%20Parent%20Guardian%20Consent%20Form.pdf" target="_blank" title="Parent Guardian Consent Form">
			Parent Guardian Consent Form</a></p>
        <p>
            <a href="docs/birmingham/BCH%20Parent%20Guardian%20Info%20Sheet.pdf" target="_blank" title="Parent Guardian Information Sheet">
			Parent Guardian Information Sheet</a></p>
            </blockquote>        
    </asp:Panel>
   
     
    <asp:Panel ID="pnlBristolSRNS" runat="server" Visible="false">
    <blockquote>
    <p>
	<a href="docs/Bristol/BRCH%20Adolescent%20Assent%20Form.pdf" target="_blank" title="Adolescent Assent Form">
	Adolescent Assent Form</a></p>
        <p>
            <a href="docs/Bristol/BRCH%20Adolescent%20Info%20Sheet.pdf" target="_blank" title="Adolescent Information Sheet">
			Adolescent Information Sheet</a></p>
        <p>
            <a href="docs/Bristol/BRCH%20Children's%20Info%20Sheet.pdf" target="_blank" title="Children's Information Sheet">
			Children&#39;s Information Sheet</a></p>
        <p>
            <a href="docs/Bristol/BRCH%20GP-Consultant%20Info%20Sheet.pdf" target="_blank" title="GP Information Sheet">
			GP Information Sheet</a></p>
        <p>
            <a href="docs/Bristol/BRCH%20Letter%20of%20Invitation.pdf" target="_blank" title="Letter of Invitation">
			Letter of Invitation</a></p>
        <p>
            <a href="docs/Bristol/BRCH%20Parent-Guardian%20Consent%20Form.pdf" target="_blank" title="Parent Guardian Consent Form">
			Parent Guardian Consent Form</a></p>
        <p>
            <a href="docs/Bristol/BRCH%20Parent-Guardian%20Info%20Sheet.pdf" target="_blank" title="Parent Guardian Information Sheet">
			Parent Guardian Information Sheet</a></p>
            </blockquote>
    </asp:Panel>
    
    <asp:Panel ID="pnlCardiffSRNS" runat="server" Visible="false">
    <blockquote>
    <p><a href="docs/cardiff/Adolescent%20Assent%20Form%20v1%2001.10.09.pdf" target="_blank" title="Adolescent Assent Form">Adolescent Assent Form</a></p>
    <p><a href="docs/cardiff/Adolescent%20Info%20Sheet%20v4%2010.05.2010.pdf" target="_blank" title="Adolescent Information Sheet">Adolescent Information Sheet</a></p>
    <p><a href="docs/cardiff/Children's%20Info%20Sheet%20v1%2023.09.09.pdf" target="_blank" title="Children's Information Sheet">Children's Information Sheet</a></p>
    <p><a href="docs/cardiff/GP%20Consultant%20Info%20Sheet%20v1%2011.11.09.pdf" target="_blank" title="GP-Consultant Information Sheet">GP-Consultant Information Sheet</a></p>
    <p><a href="docs/cardiff/Letter%20of%20Invitation%20v1%2021.09.09.pdf" target="_blank" title="Letter of Invitation">Letter of Invitation</a></p>
    <p><a href="docs/cardiff/Parent%20Guardian%20Consent%20Form%20v3%2023.09.09.pdf" target="_blank" title="Parent Guardian Consent Form">Parent Guardian Consent Form</a></p>
    </blockquote>
    </asp:Panel>

    <asp:Panel ID="pnlEvelinaSRNS" runat="server" Visible="false">
    <blockquote>
    <p><a href="docs/evelina/SRNS%20Assent%20Form%20v1%2001%2010%2009_ECH.pdf" target="_blank" title="Adolescent Assent Form">Adolescent Assent Form</a></p>
    <p><a href="docs/evelina/SRNS%20Adolescent%20PIS%20v3%2011%2011%2009_ECH.pdf" target="_blank" title="Adolescent Information Sheet">Adolescent Information Sheet</a></p>
    <p><a href="docs/evelina/SRNS%20Children%20PIS%20v1%2023%2009%2009_ECH.pdf" target="_blank" title="Children's Information Sheet">Children's Information Sheet</a></p>
    <p><a href="docs/evelina/GP-Consultant%20Information%20Sheet_ECH.pdf" target="_blank" title="GP-Consultant Information Sheet">GP-Consultant Information Sheet</a></p>
    <p><a href="docs/evelina/Letter%20of%20invitation_ECH.pdf" target="_blank" title="Letter of Invitation">Letter of Invitation</a></p>
    <p><a href="docs/evelina/SRNS%20Parent%20PIS%20v5%2011%2011%2009_ECH.pdf" target="_blank" tabindex="Parent Guardian Information Sheet">Parent Guardian Information Sheet</a></p>
    <p><a href="docs/evelina/SRNS%20Consent%20Form%20v4%2030%2010%2009_ECH.pdf" target="_blank" title="Parent Guardian Consent Form">Parent Guardian Consent Form</a></p>
       </blockquote>
    </asp:Panel>
    
    
    <asp:Panel ID="pnlGlasgowSRNS" runat="server" Visible="false">
    <blockquote>
    <p>
	<a href="docs/glasgow/Glasgow%20Adolescent%20Assent%20Form.pdf" target="_blank" title="Adolescent Assent Form">
	Adolescent Assent Form</a></p>
        <p>
            <a href="docs/glasgow/Glasgow%20Adolescent%20Info%20Sheet.pdf" target="_blank" title="Adolescent Information Sheet">
			Adolescent Information Sheet</a></p>
        <p>
            <a href="docs/glasgow/Glasgow%20Children's%20Info%20Sheet.pdf" target="_blank" title="Children's Information Sheet">
			Children&#39;s Information Sheet</a></p>
        <p>
            <a href="docs/glasgow/Glasgow%20GP%20Consultant%20Info%20Sheet.pdf" target="_blank" title="GP Information Sheet">
			GP Information Sheet</a></p>
        <p>
            <a href="docs/glasgow/Glasgow%20Letter%20of%20invitation.pdf" target="_blank" title="Letter of Invitation">
			Letter of Invitation</a></p>
        <p>
            <a href="docs/glasgow/Glasgow%20Parent-Guardian%20Consent%20Form.pdf" target="_blank" title="Parent Guardian Consent Form">
			Parent Guardian Consent Form</a></p>
        <p>
            <a href="docs/glasgow/Glasgow%20Parent%20Guardian%20Info%20Sheet.pdf" target="_blank" title="Parent Guardian Information Sheet">
			Parent Guardian Information Sheet</a></p>
            </blockquote>
    </asp:Panel>
   
    
   
    
    <asp:Panel ID="pnlGoshSRNS" runat="server" Visible="false">
    <blockquote>
    <p>
	<a href="docs/GOSH/GOSH%20Adolescent%20Assent%20Form.pdf" target="_blank" title="Adolescent Assent Form">
	Adolescent Assent Form</a></p>
        <p>
            <a href="docs/GOSH/GOSH%20Adolescent%20Info.pdf" target="_blank" title="Adolescent Information Sheet">
			Adolescent Information Sheet</a></p>
        <p>
            <a href="docs/GOSH/GOSH%20Childrens%20Info.pdf" target="_blank" title="Children's Information Sheet">
			Children&#39;s Information Sheet</a></p>
        <p>
            <a href="docs/GOSH/GOSH%20GP%20Consultant%20Info.pdf" target="_blank" title="GP Information Sheet">
			GP Information Sheet</a></p>
        <p>
            <a href="docs/GOSH/GOSH%20Letter%20of%20Invitation.pdf" target="_blank" title="Letter of Invitation">
			Letter of Invitation</a></p>
        <p>
            <a href="docs/GOSH/GOSH%20Parent%20Guardian%20Consent%20Form.pdf" target="_blank" title="Parent Guardian Consent Form">
			Parent Guardian Consent Form</a></p>
        <p>
            <a href="docs/GOSH/GOSH%20Parent%20Guardian%20Info.pdf" target="_blank" title="Parent Guardian Information Sheet">
			Parent Guardian Information Sheet</a></p>
            </blockquote>
    </asp:Panel>
   
    <asp:Panel id="pnlLeedsSRNS" runat="server" visible="false">
    <blockquote>
    <p><a href="docs/leeds/Adolescent%20PIS%20v3%2011.11.09.pdf" target="_blank" title="Adolescent Information Sheet">Adolescent Information Sheet</a></p>
    <p><a href="docs/leeds/Assent%20Form%20v1%2001.10.09.pdf" target="_blank" title="Adolescent Assent Form">Adolescent Assent Form</a></p>
    <p><a href="docs/leeds/Children%20PIS%20v1%2023.09.09.pdf" target="_blank" title="Children's Information Sheet">Children's Information Sheet</a></p>
    	<p>
		<a href="docs/leeds/Consent%20Form%20v4%2030.10.09.pdf" target="_blank" title="Parent Guardian Consent Form">
		Parent Guardian Consent Form</a></p>
    	<p>
		<a href="docs/leeds/GP-Consultant%20Information%20Sheet%20v1%2011.11.09.pdf" target="_blank" title="GP-Consultant Information Sheet">
		GP-Consultant Information Sheet</a></p>
		<p>
		<a href="docs/leeds/Letter%20of%20invitation%20v1%2021.09.09.pdf" target="_blank" title="Letter of Invitation">
		Letter of Invitation</a></p>
    </blockquote>
    </asp:Panel>
    
    
    <asp:Panel ID="pnlLiverpoolSRNS" runat="server" Visible="false">
    <blockquote>
    <p>
	<a href="docs/liverpool/Liverpool%20Adolescent%20Assent%20Form.pdf" target="_blank" title="Adolescent Assent Form">
	Adolescent Assent Form</a></p>
        <p>
            <a href="docs/liverpool/Liverpool%20Adolescent%20Info.pdf" target="_blank" title="Adolescent Information Sheet">
			Adolescent Information Sheet</a></p>
        <p>
            <a href="docs/liverpool/Liverpool%20Children%20Info.pdf" target="_blank" title="Children's Information Sheet">
			Children&#39;s Information Sheet</a></p>
        <p>
            <a href="docs/liverpool/Liverpool%20GP-Consultant%20Info.pdf" target="_blank" title="GP Information Sheet">
			GP Information Sheet</a></p>
        <p>
            <a href="docs/liverpool/Liverpool%20Letter%20of%20invitation.pdf" target="_blank" title="Letter of Invitation">
			Letter of Invitation</a></p>
        <p>
            <a href="docs/liverpool/Liverpool%20Parent%20Consent%20Form.pdf" target="_blank" title="Parent Guardian Consent Form">
			Parent Guardian Consent Form</a></p>
        <p>
            <a href="docs/liverpool/Liverpool%20Parent%20Info%20Sheet.pdf" target="_blank" title="Parent Guardian Information Sheet">
			Parent Guardian Information Sheet</a></p>
            </blockquote>
    </asp:Panel>
   
    
    
    <asp:Panel ID="pnlManchesterSRNS" runat="server" Visible="false">
     <blockquote>
    <p>
	<a href="docs/manchester/Manchester%20Adolescent%20Assent%20Form.pdf" target="_blank" title="Adolescent Assent Form">
	Adolescent Assent Form</a></p>
        <p>
            <a href="docs/manchester/Manchester%20Adolescent%20Info.pdf" target="_blank" title="Adolescent Information Sheet">
			Adolescent Information Sheet</a></p>
        <p>
            <a href="docs/manchester/Manchester%20Children's%20Info.pdf" target="_blank" title="Children's Information Sheet">
			Children&#39;s Information Sheet</a></p>
        <p>
            <a href="docs/manchester/Manchester%20GP-Consultant%20Info.pdf" target="_blank" title="GP Information Sheet">
			GP Information Sheet</a></p>
        <p>
            <a href="docs/manchester/Manchester%20Letter%20of%20invitation.pdf" target="_blank" title="Letter of Invitation">
			Letter of Invitation</a></p>
        <p>
            <a href="docs/manchester/Manchester%20Parent%20Guardian%20Consent%20Form.pdf" target="_blank" title="Parent Guardian Consent Form">
			Parent Guardian Consent Form</a></p>
        <p>
            <a href="docs/manchester/Manchester%20Parent%20Guardian%20Info.pdf" target="_blank" title="Parent Guardian Information Sheet">
			Parent Guardian Information Sheet</a></p>
            </blockquote>
    </asp:Panel>
    
    
    
    <asp:Panel ID="pnlNewcastleSRNS" runat="server" Visible="false">
    <blockquote>
    <p>
	<a href="docs/newcastle/Newcastle%20Adolescent%20Assent%20Form.pdf" target="_blank" title="Adolescent Assent Form">
	Adolescent Assent Form</a></p>
        <p>
            <a href="docs/newcastle/Newcastle%20Adolescent%20Info.pdf" target="_blank" title="Adolescent Information Sheet">
			Adolescent Information Sheet</a></p>
        <p>
            <a href="docs/newcastle/Newcastle%20Children's%20info.pdf" target="_blank" title="Children's Information Sheet">
			Children&#39;s Information Sheet</a></p>
        <p>
            <a href="docs/newcastle/Newcastle%20GP%20Info.pdf" target="_blank" title="GP Information Sheet">
			GP Information Sheet</a></p>
        <p>
            <a href="docs/newcastle/Newcastle%20Letter%20of%20Invitation.pdf" target="_blank" title="Letter of Invitation">
			Letter of Invitation</a></p>
        <p>
            <a href="docs/newcastle/Newcastle%20Parent%20Guardian%20Consent%20Form.pdf" target="_blank" title="Parent Guardian Consent Form">
			Parent Guardian Consent Form</a></p>
        <p>
            <a href="docs/newcastle/Newcastle%20Parent%20Guardian%20Info.pdf" target="_blank" title="Parent Guardian Information Sheet">
			Parent Guardian Information Sheet</a></p>
            </blockquote>
    </asp:Panel>
     
    
    
    
    <asp:Panel ID="pnlNottinghamSRNS" runat="server" Visible="false">
    <blockquote>
    <p>
	<a href="docs/nottingham/Nottingham%20Adolecsent%20Assent%20Form.pdf" target="_blank" title="Adolescent Assent Form">
	Adolescent Assent Form</a></p>
        <p>
            <a href="docs/nottingham/Nottingham%20Adolescent%20Info.pdf" target="_blank" title="Adolescent Information Sheet">
			Adolescent Information Sheet</a></p>
        <p>
            <a href="docs/nottingham/Nottingham%20Children%20Info.pdf" target="_blank" title="Children's Information Sheet">
			Children&#39;s Information Sheet</a></p>
        <p>
            <a href="docs/nottingham/Nottingham%20GP%20Info.pdf" target="_blank" title="GP Information Sheet">
			GP Information Sheet</a></p>
        <p>
            <a href="docs/nottingham/Nottingham%20Letter%20of%20invitation.pdf" target="_blank" title="Letter of Invitation">
			Letter of Invitation</a></p>
        <p>
            <a href="docs/nottingham/Nottingham%20Parent%20Guardian%20Consent%20Form.pdf" target="_blank" title="Parent Guardian Consent Form">
			Parent Guardian Consent Form</a></p>
        <p>
            <a href="docs/nottingham/Nottingham%20Parent%20Guardian%20Info.pdf" target="_blank" title="Parent Guardian Information Sheet">
			Parent Guardian Information Sheet</a></p>
            </blockquote>
    </asp:Panel>
    
    </asp:Panel>
  
    <asp:CollapsiblePanelExtender ID="pnlSRNS_CollapsiblePanelExtender" 
        runat="server" Enabled="True" TargetControlID="pnlSRNS" CollapseControlID="Image9" CollapsedImage="~/images/expand.jpg" ExpandedImage="~/images/collapse.jpg" ExpandControlID="Image9" ImageControlID="Image9" Collapsed="true">
    </asp:CollapsiblePanelExtender>
</asp:Content>

