
Partial Class recruitment
    Inherits System.Web.UI.Page

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load


        If Not Session("blnAuthenticated") Then
            Response.Redirect("default.aspx")
        End If

    End Sub

    Public Sub gridview1_OnRowCreated(ByVal sender As Object, ByVal e As Web.UI.WebControls.GridViewRowEventArgs) Handles GridView1.RowCreated

        If GridView1.Rows.Count > 1 Then
            lblSort.Visible = True
        Else
            lblSort.Visible = False
        End If
      
    End Sub

End Class
