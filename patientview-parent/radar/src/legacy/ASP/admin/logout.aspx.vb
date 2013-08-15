
Partial Class admin_logout
    Inherits System.Web.UI.Page

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load
        Session("blnAdminAuthenticated") = False
        Response.Redirect("default.aspx")
    End Sub
End Class
