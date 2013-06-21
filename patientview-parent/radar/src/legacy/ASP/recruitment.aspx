<%@ Page Title="" Language="VB" MasterPageFile="~/MasterPage.master" AutoEventWireup="false" CodeFile="recruitment.aspx.vb" Inherits="recruitment" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <h2>
        Recruitment</h2>
    <p>
        <asp:Label ID="lblSort" runat="server" 
            Text="Label">Click column headings to sort records.</asp:Label>
    </p>
    <p>
        <asp:GridView ID="GridView1" runat="server" AllowSorting="True" 
            AutoGenerateColumns="False" DataSourceID="SqlDataSource1" CellPadding="5" BorderColor="#A0A0A0" BorderStyle="Solid" BorderWidth="1px" Font-Size="95%" >
            <Columns>
                <asp:BoundField DataField="cName" HeaderText="Centre" SortExpression="cName" />
                <asp:BoundField DataField="RADAR_NO" HeaderText="Radar No" 
                    InsertVisible="False" ReadOnly="True" SortExpression="RADAR_NO" />
                <asp:BoundField DataField="dcAbbr" HeaderText="Diagnosis" 
                    SortExpression="dcAbbr" />
                <asp:BoundField DataField="DATE_REG" DataFormatString="{0:dd-MMM-yyyy}" 
                    HeaderText="Date Registered" SortExpression="DATE_REG" >
                <ItemStyle HorizontalAlign="Center" />
                </asp:BoundField>
                <asp:BoundField DataField="sAbbrev" HeaderText="Status" 
                    SortExpression="sAbbrev" />
            </Columns>
             <EmptyDataRowStyle BorderWidth="0" BorderColor="White"  />
            <EmptyDataTemplate>There are no patients registered.</EmptyDataTemplate>
            <HeaderStyle BackColor="#EEF6ED" />
        </asp:GridView>
        
    </p>
<p>
        &nbsp;</p>
  <p>
      <asp:Label ID="lblPage" runat="server" Text="19"></asp:Label></p>      
        
        <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
            ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
            SelectCommand="SELECT tbl_Demographics.RADAR_NO, tbl_Demographics.DATE_REG, tbl_Centres.cName, tbl_Status.sAbbrev, tbl_DiagCode.dcAbbr FROM tbl_Diagnosis LEFT OUTER JOIN tbl_DiagCode ON tbl_Diagnosis.DIAG = tbl_DiagCode.dcID LEFT OUTER JOIN tbl_Demographics ON tbl_Diagnosis.RADAR_NO = tbl_Demographics.RADAR_NO LEFT OUTER JOIN tbl_Centres ON tbl_Demographics.RENAL_UNIT = tbl_Centres.cID LEFT OUTER JOIN tbl_Status ON tbl_Demographics.STATUS = tbl_Status.sID WHERE tbl_Centres.cID <> 15  ORDER BY tbl_Centres.cName">
        </asp:SqlDataSource>
</asp:Content>

