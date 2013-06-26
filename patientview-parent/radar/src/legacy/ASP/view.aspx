<%@ Page Title="RADAR - View Patient Entries" Language="VB" MasterPageFile="~/MasterPage.master" AutoEventWireup="false" CodeFile="view.aspx.vb" Inherits="view" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    
   
      
        
        <h2> View Patient Details</h2>
    <p>
        <asp:Label ID="lblSort" 
            runat="server">Click green column headings to sort records</asp:Label>
       </p>
        <asp:GridView ID="GridView1" runat="server" CellPadding="5" 
            AllowSorting="True" AutoGenerateColumns="False" DataKeyNames="RADAR_NO" 
            DataSourceID="SqlDataSource1" BorderColor="#A0A0A0" BorderStyle="Solid" BorderWidth="1px" Font-Size="90%"  >
            <Columns>
            <asp:TemplateField HeaderText="View / Edit" ItemStyle-HorizontalAlign="Center">
                <ItemTemplate>
                <a href="diagnosis.aspx?id=<%#Databinder.Eval(Container, "DataItem.RADAR_NO") %>">Edit</a>
                </ItemTemplate>
                <ItemStyle HorizontalAlign="Center" />
                </asp:TemplateField>
                <asp:TemplateField HeaderText="Surname" >
                <ItemTemplate>
                <%#Decrypt(DataBinder.Eval(Container, "DataItem.SNAME"))%>
                </ItemTemplate>
                </asp:TemplateField>
                <%--<asp:BoundField DataField="SNAME" HeaderText="Surname" SortExpression="SNAME" />--%>
                <asp:TemplateField HeaderText="First Name" >
                <ItemTemplate>
                <%#Decrypt(DataBinder.Eval(Container, "DataItem.FNAME"))%>
                </ItemTemplate>
                </asp:TemplateField>
               <%-- <asp:BoundField DataField="FNAME" HeaderText="First Name" SortExpression="FNAME" />--%>
                <asp:TemplateField HeaderText="DOB" >
                <ItemTemplate >
                <%#Decrypt(DataBinder.Eval(Container, "DataItem.DOB"))%>
                </ItemTemplate>
                <HeaderStyle HorizontalAlign="Center" />
                
                </asp:TemplateField>
                
                <%--<asp:BoundField DataField="DOB" DataFormatString="{0:dd-MMM-yyyy}" 
                    HeaderText="DOB" SortExpression="DOB" >

                	<HeaderStyle HorizontalAlign="Center" />
				</asp:Boundfield>--%>

                <asp:BoundField DataField="RADAR_NO" HeaderText="RADAR No." 
                    InsertVisible="False" ReadOnly="True" SortExpression="RADAR_NO" >
                	<ItemStyle HorizontalAlign="Center" />
				</asp:BoundField>
				<asp:TemplateField HeaderText="Diagnosis" ItemStyle-HorizontalAlign="Center" SortExpression="DIAG">
                <ItemTemplate>
                <%#CommonClass.GetDiagnosis(DataBinder.Eval(Container, "DataItem.DIAG"))%>
                </ItemTemplate>
                <ItemStyle HorizontalAlign="Center" />
                </asp:TemplateField>
                <asp:TemplateField HeaderText="NHS Number" >
                <ItemTemplate>
                <%#Decrypt(DataBinder.Eval(Container, "DataItem.NHS_NO"))%>
                </ItemTemplate>
                </asp:TemplateField>
                <%--<asp:BoundField DataField="NHS_NO" HeaderText="NHS Number" 
                    SortExpression="NHS_NO" />--%>
                     <asp:TemplateField HeaderText="Hospital No" >
                <ItemTemplate>
                <%#Decrypt(DataBinder.Eval(Container, "DataItem.HOSP_NO"))%>
                </ItemTemplate>
                </asp:TemplateField>
                <%--<asp:BoundField DataField="HOSP_NO" HeaderText="Hospital No." 
                    SortExpression="HOSP_NO" />--%>

                <asp:BoundField DataField="DATE_REG" DataFormatString="{0:dd-MMM-yyyy}" 
                    HeaderText="Date Reg" SortExpression="DATE_REG" >

            		<HeaderStyle HorizontalAlign="Center" />
				</asp:BoundField>
				<asp:BoundField DataField="cAbbrev" HeaderText="Centre" SortExpression="cAbbrev" />
				<asp:BoundField DataField="sAbbrev" HeaderText="Status" SortExpression="sAbbrev" />
                
            </Columns>
           
            <EmptyDataTemplate>There are no patients registered for your unit.</EmptyDataTemplate>
            <HeaderStyle BackColor="#EEF6ED" />
        </asp:GridView>
        
        <p>
        <asp:SqlDataSource ID="SqlDataSource1" runat="server" 
            ConnectionString="<%$ ConnectionStrings:RadarConnectionString %>" >
            
        </asp:SqlDataSource>
    </p><p></p>
    <p >
<asp:Label ID="lblPage" runat="server" Text=""></asp:Label></p>
</asp:Content>

