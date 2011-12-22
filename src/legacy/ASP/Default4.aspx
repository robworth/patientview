<%@ Page Title="" Language="VB" MasterPageFile="~/MasterPage3.master" AutoEventWireup="false" CodeFile="Default4.aspx.vb" Inherits="Default4" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
    <style type="text/css">
    .style2
    {
        text-decoration: underline;
    }
</style>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <h1>
        Navigating RADAR</h1>
    <p>
        The next page contains a list of rare kidney disease. Diseases in bold are 
        actively recruiting patients.
    </p>
    <p>
        Clicking the disease of interest will provide four menu options:
    </p>
    <ul style="line-height:200%">
        <li><span class="style2">About</span> the disease is a page that provides general public information about the 
            condition. </li>
        <li><span class="style2">Study Group</span> gives information about the research being undertaken into the 
            disease. The study group may publish care pathways or guidelines here. </li>
        <li><span class="style2">Professionals</span> is the portal for doctors to access the secure, password-protected 
            database to enter patient data. </li>
        <li><span class="style2">Patients</span> is the portal for patients who are participating in the registry to 
            access their own secure, password-protected data. </li>
    </ul>
    <p>
        &nbsp;</p>
    <p>
        To go to the list of rare kidney diseases click <a href="default5.aspx">NEXT</a>    </p>
</asp:Content>

