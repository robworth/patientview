Imports System
Imports System.Data.SqlClient
Imports System.Net.Mail

Partial Class patient_recover
    Inherits System.Web.UI.Page

    Protected Sub btnSubmit_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnSubmit.Click

        

        Dim strEmail As String = txtEmail.Text

        Dim strSQL As String
        Dim strConnect As String
        Dim strPassCheck As String

        Dim TDES As TripleDES = New TripleDES


        strConnect = System.Configuration.ConfigurationManager.ConnectionStrings("RadarConnectionString").ConnectionString
        strSQL = " SELECT [pPassWord] FROM [tbl_Patient_Users] WHERE [pUserName] = '" & strEmail & "'"


        Try

            Using objConnect As New SqlConnection(strConnect)
                objConnect.Open()
                Using objCommand As New SqlCommand(strSQL, objConnect)
                    Dim objDataReader As SqlDataReader
                    objDataReader = objCommand.ExecuteReader()
                    If objDataReader.Read() Then
                        strPassCheck = TDES.Decrypt(objDataReader("pPassWord"))
                        lblMsg.Text = "Your password has been emailed to you. Please check your inbox"
                        sendmail(strEmail, strPassCheck)
                    Else
                        lblMsg.Text = "The email address you entered was not recognised"
                    End If
                    objDataReader.Close()
                End Using
                objConnect.Close()
            End Using


        Catch objError As Exception


            lblMsg.Text = "Error accessing the database<br / >" _
            & objError.Message & "<br />" & objError.Source _
            '& strSQL

            Exit Sub

        End Try



    End Sub

    Shared Sub sendmail(ByVal strEmail As String, ByVal strPassCheck As String)

        Dim strHTML As String

        strHTML = "<style> p, td {font-family: verdana; color:#000000; font-size:12px;} </style>"
        strHTML = strHTML & "<p>Your password for the RADAR website is as follows</p>"

        strHTML = strHTML & "<p>" & strPassCheck & "</p>"
        strHTML = strHTML & "<p>&nbsp;</p>"

        Dim objMsg As New MailMessage()
        Dim addressFrom As New MailAddress("Radar Website<website@renal-test.org>")

        'objMsg.To.Add("ihaynes@data-insite.co.uk")
        objMsg.To.Add(strEmail)
        objMsg.From = addressFrom
        objMsg.Bcc.Add("ihaynes@data-insite.co.uk")
        objMsg.IsBodyHtml = True
        objMsg.Priority = MailPriority.Normal
        objMsg.Subject = "RADAR website password"
        objMsg.Body = strHTML

        Dim mailClient As New SmtpClient()
        Dim basicAuthenticationInfo As New System.Net.NetworkCredential("admin310", "m51k52h")
        mailClient.Host = "smtp.renal-test.org"
        mailClient.UseDefaultCredentials = False
        mailClient.Credentials = basicAuthenticationInfo
        mailClient.Send(objMsg)




    End Sub



    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load


        
        Dim lnkReg As HyperLink = Master.FindControl("lnkReg")
        Dim lnkEnter As HyperLink = Master.FindControl("lnkEnter")
        Dim lnkModigy As HyperLink = Master.FindControl("lnkModify")
        Dim lnkClinicianInfo As HyperLink = Master.FindControl("lnkClinicianInfo")

        lnkReg.NavigateUrl = "patient-reg.aspx"
        lnkReg.Text = "Patient Registration"


        lnkReg.NavigateUrl = "#"
        lnkReg.Text = ""

        lnkReg.Visible = False
        lnkClinicianInfo.Visible = False
        lnkEnter.Visible = False
        lnkModigy.Visible = False


    End Sub
End Class
