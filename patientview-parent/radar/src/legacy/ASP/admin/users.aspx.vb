Imports System

Partial Class admin_users
    Inherits System.Web.UI.Page

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load
        If Session("blnAdminAuthenticated") = False Then
            Response.Redirect("default.aspx")
        End If

    End Sub

    Shared Function GetDecrypt(ByVal encName As Byte()) As String

        Dim TDES As TripleDES = New TripleDES()
        GetDecrypt = TDES.Decrypt(encName)

    End Function

    
    Protected Sub btnPdfExport_Click(ByVal sender As Object, ByVal e As EventArgs) Handles btnPdfExport.Click

        Me.ASPxGridViewExporter1.WritePdfToResponse()
    End Sub

    Protected Sub btnXlsExport_Click(ByVal sender As Object, ByVal e As EventArgs) Handles btnXlsExport.Click
        Me.ASPxGridViewExporter1.WriteXlsToResponse()

    End Sub

End Class
