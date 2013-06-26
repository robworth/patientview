Imports System
Imports System.IO
Imports System.Text
Imports System.Security.Cryptography
Imports System.Net.Mail
Imports System.Data.OleDb
Imports System.Data.SqlClient
Partial Class admin_user_add
    Inherits System.Web.UI.Page
    Private characterArray() As Char

    Private passwordLength As Int32 = 8
  

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As EventArgs) Handles Me.Load
        If Not Session("blnAdminAuthenticated") Then
            Response.Redirect("default.aspx")
        End If

        If Not IsPostBack Then

            btnNew.Visible = False
            btnEmail.Visible = False
            lblEmailUser.Visible = False

        End If
    End Sub

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
    Sub btnSubmit_Click(ByVal sender As Object, ByVal e As EventArgs) Handles btnSubmit.Click

        Dim strSurname As String = txtSurname.Text
        Dim strEmail As String = txtEmail.Text
        Dim strPass As String
        Dim intCentre As Integer = DropDownList1.SelectedValue
        Dim strForename As String = txtForename.Text
        Dim strTitle As String = DropDownList2.SelectedValue
        Dim strGMC As String = txtGMC.Text
        Dim strRole As String = txtRole.Text
        Dim strPhone As String = txtPhone.Text
        Dim dtReg As Date = Now()
        Dim intLastRecord As Integer


        If txtPwd.Text = "" Then
            strPass = Generate()
        Else
            strPass = txtPwd.Text
        End If

        Dim TDES As New TripleDES()
        Dim bytEmail() As Byte = TDES.Encrypt(strEmail)
        Dim bytPass() As Byte = TDES.Encrypt(strPass)

        If (strSurname = "" Or strEmail = "") Then

            lblMsg.Text = "Please enable JavaScript in your browser to use this form"
            'Response.Redirect("Default.aspx") ' handle entries non-javascript browsers
        Else

            If (Right(strEmail, 7) = "nhs.net" Or Right(strEmail, 6) = "nhs.uk") Then


                Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RadarConnectionString").ConnectionString
                Using objConnect As New SqlConnection(strConnect)
                    Dim strSQL As String = "INSERT INTO [tbl_Users] ([uSurname], [uForename], [uTitle], [uRole], [uEmail], [uPhone], [uCentre], [uDateJoin], [uPass], [uUserName], [uGMC]) VALUES (@Surname, @Forename, @Title, @Role, @Email, @Phone, @Centre, @DateJoin, @Pass, @UserName, @GMC)"
                    Using objCommand As New SqlCommand(strSQL, objConnect)
                        Try
                            With objCommand.Parameters
                                .Add(New SqlParameter("@Surname", strSurname))
                                .Add(New SqlParameter("@Forename", strForename))
                                .Add(New SqlParameter("@Title", strTitle))
                                .Add(New SqlParameter("@Role", strRole))
                                .Add(New SqlParameter("@Email", strEmail))
                                .Add(New SqlParameter("@Phone", strPhone))
                                .Add(New SqlParameter("@Centre", intCentre))
                                .Add(New SqlParameter("@DateJoin", dtReg))
                                .Add(New SqlParameter("@Pass", bytPass))
                                .Add(New SqlParameter("@UserName", bytEmail))
                                .Add(New SqlParameter("@GMC", strGMC))
                            End With
                            objConnect.Open()
                            objCommand.ExecuteNonQuery()

                            Dim strSQL2 As String = "SELECT @@IDENTITY FROM [tbl_Users]"
                            Using objCommand2 As New SqlCommand(strSQL2, objConnect)
                                'declare a variable to hold the a DataReader object
                                Dim objDataReader As SqlDataReader
                                'execute the SQL statement
                                objDataReader = objCommand2.ExecuteReader()
                                If objDataReader.Read() Then
                                    intLastRecord = objDataReader(0)
                                    txtNewID.Text = intLastRecord
                                    'lblDebug.Text = intLastRecord.ToString
                                    'SendMail(intLastRecord)
                                Else
                                    'lblDebug.Text = "ID Not found"
                                End If
                            End Using
                        Finally
                            objConnect.Close()
                        End Try
                    End Using
                End Using

                lblMsg.Text = "User added "
                txtPwd.Text = strPass
                btnSubmit.Enabled = False
                btnEmail.Visible = True


            Else
                lblMsg.Text = "<span class='red'>You must enter a valid NHS email address</span>"
            End If

        End If

    End Sub

    
    Protected Sub btnNew_Click(ByVal sender As Object, ByVal e As EventArgs) Handles btnNew.Click
        Response.Redirect("user-add.aspx")
    End Sub

    Protected Sub btnEmail_Click(ByVal sender As Object, ByVal e As EventArgs) Handles btnEmail.Click
        SendMail(CInt(txtNewID.Text))
        btnNew.Visible = True
        btnEmail.Enabled = False
    End Sub

    Sub SendMail(ByVal intID As Integer)

        lblMsg.Text = intID
        Dim strHTML As String

        strHTML = "<style> p, td {font-family: verdana; color:#000000; font-size:12px;} </style>"
        strHTML = strHTML & "<p>&nbsp;</p><p>A user account has been created for you on the RADAR website as follows:</p>"
        strHTML = strHTML & "<p>Username: " & txtEmail.Text & "</p>"
        strHTML = strHTML & "<p>Password: " & txtPwd.Text & "</p>"
        strHTML = strHTML & "<p>&nbsp;</p><p>&nbsp;</p>"
        strHTML = strHTML & "<p>RADAR Website Admin</p>"

        Using objMsg As New MailMessage()
            Dim addressFrom As New MailAddress("Radar Website<website@renalradar.org>")
            'objMsg.To.Add("ihaynes@data-insite.co.uk")
            objMsg.To.Add(txtEmail.Text)
            objMsg.From = addressFrom
            objMsg.Bcc.Add("ihaynes@data-insite.co.uk")
            objMsg.IsBodyHtml = True
            objMsg.Priority = MailPriority.Normal
            objMsg.Subject = "Registration details have been created for you on the RADAR website"
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
