Imports System.Data.SqlClient
Imports System.Text
Imports System.Net.Mail

Imports ConfigurationAlias = System.Configuration
Partial Class patient_reg
    Inherits System.Web.UI.Page
    Public dtDOB As Date
    Public strEmail As String

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load
        Dim lnkReg As HyperLink = Master.FindControl("lnkReg")
        lnkReg.NavigateUrl = "patients.aspx"
        lnkReg.Visible = False
        lnkReg.Text = "Patient Log-In"

        Dim lnkClinicianInfo As HyperLink = Master.FindControl("lnkClinicianInfo")
        lnkClinicianInfo.Visible = False

    End Sub

    Protected Sub btnSubmit_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnSubmit.Click

        Dim strEmail = txtEmail.Text
        Dim intRadar As Integer = CInt(txtRadarNo.Text)
        Dim dtReg As Date = Now()


        Dim TDES As New TripleDES()

        Dim patientID As Integer = intRadar
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim strSQL2 As String = "SELECT tbl_Demographics.SNAME, tbl_Demographics.FNAME, tbl_Demographics.DOB FROM [tbl_Demographics] WHERE (tbl_Demographics.RADAR_NO = '" & patientID & "')"
        Using objConnect2 As New SqlConnection(strConnect)
            Using objCommand2 As New SqlCommand(strSQL2, objConnect2)
                Try
                    Dim objDataReader As SqlDataReader
                    objConnect2.Open()
                    objDataReader = objCommand2.ExecuteReader()
                    If objDataReader.Read() Then
                       
                        Dim dt As Date = CDate(TDES.Decrypt(objDataReader("DOB")))
                        dtDOB = dt
                        If CDate(txtDOB.Text) <> dt Then
                            lblSuccess.Text = "The date of birth you gave does not match the records we have. Please ensure the correct date was selected."
                            objConnect2.Close()
                            Exit Sub
                        End If





                    End If
                Catch objError As Exception
                    lblDebug.Text = String.Format("An error occurred: '{0}'{1}", objError.Message, strSQL2)
                    objConnect2.Close()
                    Exit Sub
                Finally
                    objConnect2.Close()
                End Try
            End Using
        End Using

        'generate the password

        Dim strPass As String = Generate()

        Dim bytEmail() As Byte = TDES.Encrypt(strEmail)
        Dim bytPass() As Byte = TDES.Encrypt(strPass)

        'save the patient data

        Dim objConnect As New SqlConnection(strConnect)
        Dim strSQL As String = "INSERT INTO [tbl_Patient_Users] ([RADAR_NO], [pPassWord], [pUserName], [pDOB], [pDateReg]) VALUES (@RADAR_NO, @Pass, @UserName, @DOB, @DATE_REG)"

        Dim objCommand As New SqlCommand(strSQL, objConnect)

        Try
            With objCommand.Parameters


                .Add(New SqlParameter("@RADAR_NO", intRadar))
                .Add(New SqlParameter("@Pass", bytPass))
                .Add(New SqlParameter("@UserName", strEmail))
                .Add(New SqlParameter("@DOB", dtDOB))
                .Add(New SqlParameter("@DATE_REG", dtReg))

            End With

            objConnect.Open()
            objCommand.ExecuteNonQuery()

            Dim strSQL3 As String = "SELECT @@IDENTITY FROM [tbl_Patient_Users]"
            Dim objCommand2 As New SqlCommand(strSQL3, objConnect)

            'declare a variable to hold the a DataReader object
            Dim objDataReader As SqlDataReader

            'execute the SQL statement
            objDataReader = objCommand2.ExecuteReader()


            If objDataReader.Read() Then
                Dim intLastRecord As Integer = objDataReader(0)
                'lblDebug.Text = intLastRecord.ToString
                sendMail(strEmail, strPass, intLastRecord, dtReg, intRadar)
            Else
                'lblDebug.Text = "ID Not found"

            End If


        Catch objError As Exception
            lblDebug.Text = objError.Message
            objConnect.Close()
            Exit Sub

        Finally
            objConnect.Close()

        End Try

        lblSuccess.Text = "Your registration has been successful and you password has been emailed to you.<br/><br/>You can now log on to the site. <a href='patients.aspx'>Patients</a>"
        btnSubmit.Enabled = False
        
    End Sub

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

    Shared Sub sendMail(ByVal strEmail As String, ByVal strPass As String, ByVal intLastRecord As Integer, ByVal dtReg As Date, ByVal intRadar As Integer)


        Dim strHTMLuser As String
        Dim objMsgUser As New MailMessage
        Dim addressFrom As New MailAddress("Radar Website<website@renalradar.org>")

        strHTMLuser = "<style> p, td {font-family: verdana; color:#000000; font-size:12px;} </style>"
        strHTMLuser = strHTMLuser & "<p>Thank you for registering with the RaDaR website;</p>"
        strHTMLuser = strHTMLuser & "<p>&nbsp;</p>"
        strHTMLuser = strHTMLuser & "<p>Your access details are as follows:"
        strHTMLuser = strHTMLuser & "<p>&nbsp;</p>"
        strHTMLuser = strHTMLuser & "<p>Username: " & strEmail & "</p>"
        strHTMLuser = strHTMLuser & "<p>Password: " & strPass & "</p>"
        strHTMLuser = strHTMLuser & "<p>&nbsp;</p>"
        strHTMLuser = strHTMLuser & "<p>&nbsp;</p>"

        Dim mailClient As New System.Net.Mail.SmtpClient()
        Dim basicAuthenticationInfo As New System.Net.NetworkCredential("website@renalradar.org", "renal28H44")
        mailClient.Host = "smtp.renalradar.org"
        mailClient.UseDefaultCredentials = False
        mailClient.Credentials = basicAuthenticationInfo

        objMsgUser.To.Add(strEmail)
        objMsgUser.From = addressFrom
        objMsgUser.Bcc.Add("ihaynes@data-insite.co.uk")
        objMsgUser.IsBodyHtml = True
        objMsgUser.Priority = MailPriority.Normal
        objMsgUser.Subject = "Your RaDaR website registration "
        objMsgUser.Body = strHTMLuser

        mailClient.Send(objMsgUser)

        'Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RadarConnectionString").ConnectionString
        'Dim objConnect As New SqlConnection(strConnect)
        'Dim strSQL As String = "SELECT [uEmail] FROM [tbl_Users], [tbl_Demographics] WHERE [tbl_Patient_Users].[uCentre] = [tbl_Centres].[cID] "
        'strSQL = strSQL & " AND [uID] = " & intID
        'Dim objCommand As New SqlCommand(strSQL, objConnect)

        'objConnect.Open()

        'Try

        '    Dim objDataReader As SqlDataReader

        '    'execute the SQL statement
        '    objDataReader = objCommand.ExecuteReader()

        '    If objDataReader.Read() Then

        '        Dim strTitle As String = objDataReader(0)
        '        Dim strForename As String = objDataReader(1)
        '        Dim strSurname As String = objDataReader(2)
        '        Dim dtJoin As Date = objDataReader(3)
        '        Dim strCentre As String = objDataReader(4)

        Dim strHTML As String

        strHTML = "<style> p, td {font-family: verdana; color:#000000; font-size:12px;} </style>"
        strHTML = strHTML & "<p>A new patient user has registered with the Radar website as follows:</p>"
        strHTML = strHTML & "<p>&nbsp;</p>"
        strHTML = strHTML & "<p>Radar Number: " & intRadar & "</p>"
        strHTML = strHTML & "<p>&nbsp;</p>"

        Dim objMsg As New System.Net.Mail.MailMessage()

        'objMsg.To.Add("ihaynes@data-insite.co.uk")
        objMsg.To.Add("fiona.braddon@nhs.net")
        objMsg.From = addressFrom
        objMsg.Bcc.Add("ihaynes@data-insite.co.uk")
        objMsg.IsBodyHtml = True
        objMsg.Priority = MailPriority.Normal
        objMsg.Subject = "New Radar patient registrant on: " & dtReg
        objMsg.Body = strHTML


        mailClient.Send(objMsg)



        '    End If

        'objDataReader.Close()

        'Finally

        '    objConnect.Close()


        'End Try

    End Sub
End Class
