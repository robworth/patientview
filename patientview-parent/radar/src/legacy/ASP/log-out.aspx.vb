
Partial Class log_out
    Inherits System.Web.UI.Page

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load
        Session.Abandon()
        Session("blnAuthenticated") = False
        Session("diag") = 0
        Response.Redirect("default.aspx")
    End Sub
End Class
