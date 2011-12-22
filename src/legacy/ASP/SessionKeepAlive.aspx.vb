
Partial Class SessionKeepAlive
    Inherits System.Web.UI.Page

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load
        Response.ContentType = "text/html"
        Response.Write("Alive")
    End Sub
End Class
