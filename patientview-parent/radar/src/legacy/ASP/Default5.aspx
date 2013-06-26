<%@ Page Title="Renal Radar - Disease Index" Language="VB" MasterPageFile="~/MasterPage3.master" AutoEventWireup="false" CodeFile="Default5.aspx.vb" Inherits="Default5" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
    <style type="text/css">
.style2 {
	margin-left: 20px;
}
</style>
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <h1>
        Index of rare kidney diseases</h1>
    <p>
        Click the condition you want to open. Titles in bold print are actively 
        recruiting.</p>
    <p style="line-height:170%; " class="style2">Alport syndrome, and disorders of basement membrane structure<br />
	Arthrogryposis, renal dysfunction and cholestasis<br />
	Autoimmune polyendocrinopathy syndrome Type 1<br />
	Bardet Biedl syndrome<br />
	Bartter syndrome<br />
	Branchio-oto-renal syndrome<br />
	Cystinosis<br />
	Denys-Drash syndrome<br />
	EAST syndrome<br />
	Fabry&#39;s disease<br />
	Frasier syndrome<br />
	Gitelman syndrome<br />
	Gordon syndrome<br />
	Glucocorticoid-remediable hypertension<br />
	Haemolytic uraemic syndrome<br />
	Hypocalciuria hypercalcaemia Type 1<br />
	Hyperoxaluria<br />
	Hypoparathyroidism (primary)<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; isolated 
	familial, with Sensorineural Deafness and renal Dysplasia<br />
	Hyperparathyroidism (primary)<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; monogenetic 
	forms, Neonatal severe primary, type 2<br />
	Hyperuricemic Nephropathy Familial Juvenile<br />
	Hypophosphatemic Rickets:<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;Autosomal 
	Dominant, Autosomal Recessive, with Hypercalciuria Hereditary<br />
	Liddle syndrome<br />
	Lipoprotein Glomerulopathy<br />
	Lowe Oculocerebrorenal Syndrome<br />
	Maturity-Onset Diabetes of the Young Type V<br />
	Medullary Cystic Kidney Disease 2<br />
	<asp:linkbutton runat="server" ID="lnkMPGN" postbackUrl="about.aspx?d=2" Text="Membranoproliferative glomerulonephritis / Dense Deposit Disease"></asp:linkbutton><br />
	Nail Patella Syndrome<br />
	Nephrolithiasis X-Linked Recessive with Renal Failure<br />
	Nephronophthisis <br />
	Nephrotic Syndrome<br />
	
	Polycystic kidney<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Autosomal 
	recessive, autosomal dominant 1 and 2<br />
	Renal Cell Carcinoma Papillary 2<br />
	Renal tubular Acidosis<br />
	<asp:linkbutton runat="server" ID="lnkFSGS" postbackUrl="about.aspx?d=1" Text="SRNS: 
&nbsp;&nbsp;&nbsp;&nbsp; Steroid Resistant Nephrotic Syndrome (including inherited, monogenic, primary FSGS and syndromic forms)" ></asp:linkbutton>
        <br />
	Tuberous Sclerosis <br />
	Von Hippel-Lindau Syndrome</p>
<p style="line-height:170%; " class="style2"><a href="default.aspx">BACK</a></p>
    <p style="line-height:150%; " class="style2">
        <asp:Label ID="lblSession" runat="server"></asp:Label>
    </p>
    <p>
        &nbsp;</p>
</asp:Content>

