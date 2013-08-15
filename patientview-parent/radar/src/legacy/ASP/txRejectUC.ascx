<%@ Control Language="VB" AutoEventWireup="false" CodeFile="txRejectUC.ascx.vb" Inherits="txRejectUC" %>
<asp:GridView ID="GridView1" runat="server" AutoGenerateColumns="False" 
    DataSourceID="SqlDataSource1">
    <Columns>
        <asp:BoundField DataField="trID" HeaderText="trID" SortExpression="trID" />
        <asp:BoundField DataField="trRejectDate" HeaderText="trRejectDate" 
            SortExpression="trRejectDate" />
        <asp:BoundField DataField="trBiopsyDate" HeaderText="trBiopsyDate" 
            SortExpression="trBiopsyDate" />
        <asp:BoundField DataField="trFailureDate" HeaderText="trFailureDate" 
            SortExpression="trFailureDate" />
    </Columns>
</asp:GridView>
<asp:SqlDataSource ID="SqlDataSource1" runat="server" 
    ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" 
    SelectCommand="SELECT [trID], [trRejectDate], [trBiopsyDate], [trFailureDate] FROM [tbl_Transplant_Reject] WHERE ([trID] = @trID)">
    <SelectParameters>
        <asp:Parameter Name="trID" Type="Int32" DefaultValue="16" />
    </SelectParameters>
</asp:SqlDataSource>
