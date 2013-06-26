Imports System

Imports DevExpress.Web.ASPxGridView

Partial Class admin_patients
    Inherits System.Web.UI.Page

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load
        If Session("blnAdminAuthenticated") = False Then
            ' Response.Redirect("default.aspx")
        End If

    End Sub


    Shared Function GetDecrypt(ByVal encName As Byte()) As String

        Dim TDES As TripleDES = New TripleDES()
        GetDecrypt = TDES.Decrypt(encName)

    End Function

    Protected Sub ASPxGridView1_CustomButtonCallback(ByVal sender As Object, ByVal e As ASPxGridViewCustomButtonCallbackEventArgs)

        If e.ButtonID = "Email" Then
            Dim intID As Integer = ASPxGridView1.GetRowValues(e.VisibleIndex, "pID")
            lblDebug.Text = "test"
        Else
            Return
        End If

    End Sub

End Class
