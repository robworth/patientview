
Partial Class GenericError
    Inherits System.Web.UI.Page

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load


        If Not Session("blnAuthenticated") Then
            'Response.Redirect("default.aspx")
        End If

    End Sub

End Class
