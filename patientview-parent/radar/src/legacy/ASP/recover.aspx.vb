Imports System
Imports System.IO
Imports System.Text
Imports System.Security.Cryptography
Imports System.Data
Imports System.Data.OleDb
Imports System.Data.SqlClient
Imports System.Net.Mail

Partial Class recover
    Inherits System.Web.UI.Page

    Protected Sub btnSubmit_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnSubmit.Click

        Dim strEmail As String = txtEmail.Text

        Dim strSQL As String
        Dim strConnect As String
        Dim strPassCheck As String

        Dim TDES As TripleDES = New TripleDES


        strConnect = System.Configuration.ConfigurationManager.ConnectionStrings("RadarConnectionString").ConnectionString
        strSQL = " SELECT [uPass] FROM [tbl_Users] WHERE [uEmail] = '" & strEmail & "'"


        Try

            Using objConnect As New SqlConnection(strConnect)
                objConnect.Open()
                Using objCommand As New SqlCommand(strSQL, objConnect)
                    Dim objDataReader As SqlDataReader
                    objDataReader = objCommand.ExecuteReader()
                    If objDataReader.Read() Then
                        strPassCheck = TDES.Decrypt(objDataReader("uPass"))
                        outMsg.Text = "Your password has been emailed to you. Please check your inbox"
                        sendmail(strEmail, strPassCheck)
                    Else
                        outMsg.Text = "The email address you entered was not recognised"
                    End If
                    objDataReader.Close()
                End Using
                objConnect.Close()
            End Using


        Catch objError As Exception


            outMsg.Text = "Error accessing the database<br / >" _
            & objError.Message & "<br />" & objError.Source _
            '& strSQL

            Exit Sub

        End Try



    End Sub

    Sub sendmail(ByVal strEmail As String, ByVal strPassCheck As String)

        Dim strHTML As String

        strHTML = "<style> p, td {font-family: verdana; color:#000000; font-size:12px;} </style>"
        strHTML = strHTML & "<p>Your password for the RADAR website is as follows:</p>"

        strHTML = strHTML & "<p>" & strPassCheck & "</p>"
        strHTML = strHTML & "<p>&nbsp;</p>"
        strHTML = strHTML & "<p>Your username is your email address</p>"

        Dim objMsg As New System.Net.Mail.MailMessage()
        Dim addressFrom As New System.Net.Mail.MailAddress("Radar Website<website@renalradar.org>")

        'objMsg.To.Add("ihaynes@data-insite.co.uk")
        objMsg.To.Add(strEmail)
        objMsg.From = addressFrom
        objMsg.Bcc.Add("ihaynes@data-insite.co.uk")
        objMsg.IsBodyHtml = True
        objMsg.Priority = MailPriority.Normal
        objMsg.Subject = "RADAR website password"
        objMsg.Body = strHTML

        Dim mailClient As New System.Net.Mail.SmtpClient()
        Dim basicAuthenticationInfo As New System.Net.NetworkCredential("website@renalradar.org", "renal28H44")
        mailClient.Host = "smtp.renalradar.org"
        mailClient.UseDefaultCredentials = False
        mailClient.Credentials = basicAuthenticationInfo
        mailClient.Send(objMsg)




    End Sub

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load



    End Sub
End Class
