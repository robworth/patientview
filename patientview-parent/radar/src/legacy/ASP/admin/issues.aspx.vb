
Partial Class admin_issues
    Inherits System.Web.UI.Page

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load
        If Session("blnAdminAuthenticated") = False Then
            Response.Redirect("default.aspx")
        End If
    End Sub

    Protected Sub ASPxGridView1_HtmlDataCellPrepared(ByVal sender As Object, ByVal e As DevExpress.Web.ASPxGridView.ASPxGridViewTableDataCellEventArgs) Handles ASPxGridView1.HtmlDataCellPrepared
        If e.DataColumn.FieldName = "bStatus" Then
            If (e.CellValue) = "Pending" Then
                e.Cell.BackColor = System.Drawing.Color.LemonChiffon
            ElseIf (e.CellValue) = "Open" Then
                e.Cell.BackColor = Drawing.Color.FromArgb(240, 240, 240)
            End If
        End If
    End Sub



End Class
