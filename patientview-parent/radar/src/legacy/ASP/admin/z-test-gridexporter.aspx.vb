
Partial Class admin_z_test_gridexporter
    Inherits System.Web.UI.Page

    Protected Sub ASPxButton1_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles ASPxButton1.Click
        Me.ASPxGridViewExporter1.WritePdfToResponse()
    End Sub

    Protected Sub ASPxButton2_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles ASPxButton2.Click
        Me.ASPxGridViewExporter1.WriteXlsToResponse()
    End Sub
End Class
