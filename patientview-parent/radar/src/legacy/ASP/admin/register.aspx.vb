Imports System
Imports System.IO
Imports System.Text
Imports System.Security.Cryptography
Imports System.Net.Mail
Imports System.Data.OleDb
Imports System.Data.SqlClient
Partial Class admin_register
    Inherits System.Web.UI.Page
    Private characterArray() As Char

    Private passwordLength As Int32 = 8

    Public Sub New()
        characterArray = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789#".ToCharArray
    End Sub

    Private Function GetRandomCharacter() As Char
        Randomize()
        Dim location As Int32 = -1
        While Not (location >= 0 AndAlso location <= Me.characterArray.GetUpperBound(0))
            location = Convert.ToInt32(Me.characterArray.GetUpperBound(0) * Rnd() + 1)
        End While

        Return Me.characterArray(location)
    End Function

    Public Function Generate() As String

        Dim count As Int32
        Dim sb As New StringBuilder

        sb.Capacity = passwordLength

        For count = 0 To passwordLength - 1
            sb.Append(GetRandomCharacter())
        Next count

        If (Not sb Is Nothing) Then

            Return sb.ToString
        End If

        Return String.Empty

    End Function

    Protected Sub btnSubmit_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnSubmit.Click

        Dim strName As String = txtName.Text
        Dim strEmail As String = txtEmail.Text
        
        Dim dtReg As Date = Now()

        'Dim strPass As String = Generate()
        Dim strPass As String = "fiona266"
        Dim TDES As New TripleDES()
        Dim bytEmail() As Byte = TDES.Encrypt(strEmail)
        Dim bytPass() As Byte = TDES.Encrypt(strPass)

        If (strName = "" Or strEmail = "") Then

            lblMsg.Text = "Please enable JavaScript in your browser to use this form"
            'Response.Redirect("Default.aspx") ' handle entries non-javascript browsers
        Else

            'If (Right(strEmail, 7) = "nhs.net" Or Right(strEmail, 6) = "nhs.uk") Then


            Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RadarConnectionString").ConnectionString
            Using objConnect As New SqlConnection(strConnect)
                Dim strSQL As String = "INSERT INTO [tbl_AdminUsers] ([uName], [uEmail], [uPass], [uUserName]) VALUES (@Name, @Email, @Pass, @UserName)"
                Using objCommand As New SqlCommand(strSQL, objConnect)
                    Try
                        With objCommand.Parameters
                            .Add(New SqlParameter("@Name", strName))
                            .Add(New SqlParameter("@Email", strEmail))
                            .Add(New SqlParameter("@Pass", bytPass))
                            .Add(New SqlParameter("@UserName", bytEmail))
                        End With
                        objConnect.Open()
                        objCommand.ExecuteNonQuery()
                    Finally
                        objConnect.Close()
                    End Try
                End Using
            End Using

            lblMsg.Text = String.Format("Registration sucessful: Username = {0}<br/>Password = {1}", strEmail, strPass)
            btnSubmit.Enabled = False


            'Else
            'lblMsg.Text = "<span class='red'>You must enter a valid NHS email address</span>"
            'End If

        End If

    End Sub

    Shared Function GetDecrypt(ByVal encName As Byte()) As String

        Dim TDES As TripleDES = New TripleDES()
        GetDecrypt = TDES.Decrypt(encName)

    End Function
End Class
