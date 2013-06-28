Imports System.Net.Mail
Imports System.Data.OleDb
Imports System.Data.SqlClient
Partial Class admin_patients2
    Inherits System.Web.UI.Page

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load
        If Session("blnAdminAuthenticated") = False Then
            Response.Redirect("default.aspx")
        End If

    End Sub

    Sub GridView1_RowSelecting(ByVal sender As Object, ByVal e As GridViewSelectEventArgs)

        Dim intID As Integer = CInt(GridView1.Rows(e.NewSelectedIndex).Cells(0).Text)
        e.Cancel = True

        'lblDebug.Text = intID.ToString
        Response.Redirect("patient-detail.aspx?p=" & intID)

    End Sub

    Sub GridView1_RowEditing(ByVal sender As Object, ByVal e As GridViewEditEventArgs)

        Dim intID As Integer = CInt(GridView1.Rows(e.NewEditIndex).Cells(0).Text)
        e.Cancel = True

        'lblDebug.Text = intID.ToString

        Dim strUserName As String
        Dim strPass As String

        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RadarConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)
        Dim strSQL As String = "SELECT pUserName, pPassWord FROM [tbl_Patient_Users] WHERE pID = " & intID
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        objConnect.Open()

        Try

            Dim objDataReader As SqlDataReader

            'execute the SQL statement
            objDataReader = objCommand.ExecuteReader()

            If objDataReader.Read() Then

                strUserName = objDataReader("pUserName")
                strPass = GetDecrypt(objDataReader("pPassWord"))
                SendMail(strUserName, strPass)
            End If

            objDataReader.Close()

        Catch objError As Exception
            lblDebug.Text = "An error occurred: (Read data) " & strSQL
        Finally


            objConnect.Close()


        End Try

        'lblDebug.Text = strPass






    End Sub

    Shared Sub GridView_RowCreated(ByVal sender As Object, ByVal e As GridViewRowEventArgs)
        e.Row.Cells(0).Visible = False
    End Sub

    Shared Function GetDecrypt(ByVal encName As Byte()) As String

        Dim TDES As TripleDES = New TripleDES()
        GetDecrypt = TDES.Decrypt(encName)

    End Function

    Sub SendMail(ByVal strUserName As String, ByVal strPassWord As String)

        Dim strHTML As String

        strHTML = "<style> p, td {font-family: verdana; color:#000000; font-size:12px;} </style>"
        strHTML = strHTML & "<p>&nbsp;</p><p>Your registration details for the RADAR website are as follows:</p>"
        strHTML = strHTML & "<p>Username: " & strUserName & "</p>"
        strHTML = strHTML & "<p>Password: " & strPassWord & "</p>"
        strHTML = strHTML & "<p>&nbsp;</p><p>&nbsp;</p>"
        strHTML = strHTML & "<p>RADAR Website Admin</p>"

        Using objMsg As New MailMessage()
            Dim addressFrom As New MailAddress("Radar Website<website@renalradar.org>")
            'objMsg.To.Add("ihaynes@data-insite.co.uk")
            objMsg.To.Add(strUserName)
            objMsg.From = addressFrom
            objMsg.Bcc.Add("ihaynes@data-insite.co.uk")
            objMsg.IsBodyHtml = True
            objMsg.Priority = MailPriority.Normal
            objMsg.Subject = "Registration reminder for the RADAR website"
            objMsg.Body = strHTML
            Try
                Dim mailClient As New SmtpClient()
                Dim basicAuthenticationInfo As New System.Net.NetworkCredential("website@renalradar.org", "renal28H44")
                mailClient.Host = "smtp.renalradar.org"
                mailClient.UseDefaultCredentials = False
                mailClient.Credentials = basicAuthenticationInfo
                mailClient.Send(objMsg)
                lblMsg.Text = "Email sent successfully"
            Catch ex As Exception
                lblMsg.Text = String.Format("An error occurred: {0}", ex.Message)
                Exit Sub
            End Try
        End Using


    End Sub


End Class
