<%@ Page Title="" Language="VB" MasterPageFile="~/MasterPage3.master" AutoEventWireup="false" CodeFile="about.aspx.vb" Inherits="about" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
    <style type="text/css">
.heading {
margin-bottom:50px;
}
</style>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <asp:Label ID="lblTitle" runat="server" Text="" Font-Size="140%" Font-Bold="true" ForeColor="#6d9863" CssClass="heading"></asp:Label>
	
	<div id="pnlText" runat="server" style="margin-top:30px"></div>
	<asp:Label ID="lblDebug" runat="server"></asp:Label>
   <p>
       
      
    </p>
	
</asp:Content>

