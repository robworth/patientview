
Partial Class entry
    Inherits System.Web.UI.Page

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load

        Session("mode") = 1 'entry
        Session("patientID") = DBNull.Value
        Response.Redirect("demographics.aspx")
        Session("diag") = 0
    End Sub
End Class
