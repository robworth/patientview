
Partial Class admin_user_detail
    Inherits System.Web.UI.Page

    Protected Sub btnCancelEdit_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnCancelEdit.Click
        Response.Redirect("users.aspx")
    End Sub

    Shared Function GetDecrypt(ByVal encName As Byte()) As String

        Dim TDES As TripleDES = New TripleDES()
        GetDecrypt = TDES.Decrypt(encName)

    End Function
End Class
