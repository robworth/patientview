Imports System
Imports System.IO
Imports System.Text
Imports System.Security.Cryptography
Imports System.Net.Mail
Imports System.Data.OleDb
Imports System.Data.SqlClient


Partial Class register
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

        Dim strSurname As String = txtSurname.Text
        Dim strEmail As String = txtEmail.Text
        Dim strPhone As String = txtPhone.Text
        Dim intCentre As Integer = DropDownList1.SelectedValue
        Dim strForename As String = txtForename.Text
        Dim strTitle As String = DropDownList2.SelectedValue
        Dim strGMC As String = txtGMC.Text
        Dim strRole As String = txtRole.Text
        Dim dtReg As Date = Now()

        Dim strPass As String = Generate()
        Dim TDES As New TripleDES()
        Dim bytEmail() As Byte = TDES.Encrypt(strEmail)
        Dim bytPass() As Byte = TDES.Encrypt(strPass)

        If (strSurname = "" Or strEmail = "") Then

            lblMsg.Text = "Please enable JavaScript in your browser to use this form"
            'Response.Redirect("Default.aspx") ' handle entries non-javascript browsers
        Else

            If (Right(strEmail, 7) = "nhs.net" Or Right(strEmail, 6) = "nhs.uk") Then

                If checkUser(strEmail) = False Then

                    Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RadarConnectionString").ConnectionString
                    Dim objConnect As New SqlConnection(strConnect)
                    Dim strSQL As String = "INSERT INTO [tbl_Users] ([uSurname], [uForename], [uTitle], [uRole], [uEmail], [uPhone], [uCentre], [uDateJoin], [uPass], [uUserName], [uGMC]) VALUES (@Surname, @Forename, @Title, @Role, @Email, @Phone, @Centre, @DateJoin, @Pass, @UserName, @GMC)"

                    Dim objCommand As New SqlCommand(strSQL, objConnect)
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
                        Dim objCommand2 As New SqlCommand(strSQL2, objConnect)

                        'declare a variable to hold the a DataReader object
                        Dim objDataReader As SqlDataReader

                        'execute the SQL statement
                        objDataReader = objCommand2.ExecuteReader()


                        If objDataReader.Read() Then
                            Dim intLastRecord As Integer = objDataReader(0)
                            'lblDebug.Text = intLastRecord.ToString
                            SendMail(intLastRecord)
                        Else
                            'lblDebug.Text = "ID Not found"

                        End If



                    Finally
                        objConnect.Close()
                    End Try

                    lblMsg.Text = "Thank you. Your registration has been sucessful and will be confirmed by email shortly"
                    btnSubmit.Enabled = False


                Else
                    lblMsg.Text = "<span class='red'>The email you have used is already registered on the Radar site.</span><br/><br/>If you have forgotten your password <a href='recover.aspx'>request a password reminder</a> "
                    Exit Sub
                End If

            Else
                lblMsg.Text = "<span class='red'>You must enter a valid NHS email address</span>"
            End If

        End If

    End Sub

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load

    End Sub

    Sub SendMail(ByVal intID As Integer)

        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RadarConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)
        Dim strSQL As String = "SELECT [uTitle], [uForename], [uSurname], [uDateJoin], [cName] FROM [tbl_Users], [tbl_Centres] WHERE [tbl_Users].[uCentre] = [tbl_Centres].[cID] "
        strSQL = strSQL & " AND [uID] = " & intID
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        objConnect.Open()

        Try

            Dim objDataReader As SqlDataReader

            'execute the SQL statement
            objDataReader = objCommand.ExecuteReader()

            If objDataReader.Read() Then

                Dim strTitle As String = objDataReader(0)
                Dim strForename As String = objDataReader(1)
                Dim strSurname As String = objDataReader(2)
                Dim dtJoin As Date = objDataReader(3)
                Dim strCentre As String = objDataReader(4)

                Dim strHTML As String

                strHTML = "<style> p, td {font-family: verdana; color:#000000; font-size:12px;} </style>"
                strHTML = strHTML & "<p>A new user has registered with the Radar website as follows:</p>"
                strHTML = strHTML & "<p>" & strTitle & ".&nbsp;" & strForename & "&nbsp;" & strSurname & "&nbsp;&nbsp;Of:</p>"
                strHTML = strHTML & "<p>" & strCentre & "</p>"
                strHTML = strHTML & "<p>&nbsp;</p>"

                Dim objMsg As New System.Net.Mail.MailMessage()
                'Dim addressFrom As New System.Net.Mail.MailAddress("Radar Website<website@renal-test.org>")
                Dim addressFrom As New System.Net.Mail.MailAddress("Radar Website<website@renalradar.org>")

                'objMsg.To.Add("ihaynes@data-insite.co.uk")
                objMsg.To.Add("fiona.braddon@nhs.net")
                objMsg.From = addressFrom
                objMsg.Bcc.Add("ihaynes@data-insite.co.uk")
                objMsg.IsBodyHtml = True
                objMsg.Priority = MailPriority.Normal
                objMsg.Subject = "New Radar site registrant on: " & dtJoin
                objMsg.Body = strHTML

                Dim mailClient As New System.Net.Mail.SmtpClient()
                Dim basicAuthenticationInfo As New System.Net.NetworkCredential("website@renalradar.org", "renal28H44")
                mailClient.Host = "smtp.renalradar.org"
                mailClient.UseDefaultCredentials = False
                mailClient.Credentials = basicAuthenticationInfo
                mailClient.Send(objMsg)



            End If

            objDataReader.Close()

        Finally

            objConnect.Close()


        End Try


    End Sub

    Function checkUser(ByVal strEmail As String) As Boolean

        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RadarConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)
        Dim strSQL As String = "SELECT [uSurname] FROM [tbl_Users] WHERE [uEmail] = '" & strEmail & "'"
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        objConnect.Open()

        Try

            Dim objDataReader As SqlDataReader


            objDataReader = objCommand.ExecuteReader()

            If objDataReader.Read() Then
                checkUser = True
            Else
                checkUser = False
            End If

        Catch objError As Exception

        Finally
            objConnect.Close()
        End Try

        'lblDebug.Text = checkUser.ToString

        Return checkUser


    End Function

End Class





