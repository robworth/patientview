Imports System
Imports System.Data.SqlClient

Partial Class patients
    Inherits System.Web.UI.Page

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load

        Dim lnkAbout As HyperLink = Master.FindControl("lnkAbout")
        Dim lnkStudy As HyperLink = Master.FindControl("lnkStudy")
        Dim lnkProfessionals As HyperLink = Master.FindControl("lnkProfessionals")
        Dim lnkPatients As HyperLink = Master.FindControl("lnkPatients")
        Dim lnkDiseases As HyperLink = Master.FindControl("lnkDiseases")
        Dim lnkReg As HyperLink = Master.FindControl("lnkReg")

        lnkReg.NavigateUrl = "patient-reg.aspx"
        lnkReg.Text = "Patient Registration"
        

        Dim lblSpace As Label = Master.FindControl("lblSpace")
        Dim lblSpace2 As Label = Master.FindControl("lblSpace2")
        Dim lblSpace3 As Label = Master.FindControl("lblSpace3")
        Dim lblSpace4 As Label = Master.FindControl("lblSpace4")

        If (Session("uType") = "p" And Session("patientID") IsNot DBNull.Value) Then
            Response.Redirect("diagnosis.aspx")
        End If


        Dim intD As Integer = Request.QueryString("d")

        'Select Case intD



        '    Case 1

        '        lblTitle.Text = "FSGS Study Group"

        '    Case 2

        '        lblTitle.Text = "MPGN Study Group"

        '    Case 3

        '        lblTitle.Text = "Dense Deposit Disease Study Group"

        '    Case Else

        'End Select

        Select Case intD

            Case 1

                lnkAbout.Text = "About FSGS"
                lnkAbout.NavigateUrl = "about.aspx?d=1"
                lnkAbout.Visible = True
                lnkStudy.Text = "FSGS Study Group"
                lnkStudy.NavigateUrl = "study.aspx?d=1"
                lnkStudy.Visible = True
                lnkProfessionals.Visible = True
                lnkProfessionals.NavigateUrl = "professionals.aspx?d=1"
                lnkPatients.Visible = True
                lnkPatients.NavigateUrl = "patients.aspx?d=1"
                lblSpace.Visible = True
                lblSpace2.Visible = True
                lblSpace3.Visible = True
                lblSpace4.Visible = True
                lnkDiseases.Visible = True

            Case 2

                lnkAbout.Text = "About MPGN"
                lnkAbout.NavigateUrl = "about.aspx?d=2"
                lnkAbout.Visible = True
                lnkStudy.Text = "MPGN Study Group"
                lnkStudy.Visible = True
                lnkStudy.NavigateUrl = "study.aspx?d=2"
                lnkProfessionals.Visible = True
                lnkProfessionals.NavigateUrl = "professionals.aspx?d=2"
                lnkPatients.NavigateUrl = "patients.aspx?d=2"
                lnkPatients.Visible = True
                lblSpace.Visible = True
                lblSpace2.Visible = True
                lblSpace3.Visible = True
                lblSpace4.Visible = True
                lnkDiseases.Visible = True

            Case 3

                lnkAbout.Text = "About DDD"
                lnkAbout.NavigateUrl = "about.aspx?d=3"
                lnkAbout.Visible = True
                lnkStudy.Text = "DDD Study Group"
                lnkStudy.NavigateUrl = "study.aspx?d=3"
                lnkStudy.Visible = True
                lnkProfessionals.Visible = True
                lnkProfessionals.NavigateUrl = "professionals.aspx?d=3"
                lnkPatients.NavigateUrl = "patients.aspx?d=3"
                lnkPatients.Visible = True
                lblSpace.Visible = True
                lblSpace2.Visible = True
                lblSpace3.Visible = True
                lblSpace4.Visible = True
                lnkDiseases.Visible = True


            Case Else

                lnkAbout.Visible = False
                lnkStudy.Visible = False
                lnkProfessionals.Visible = False
                lnkPatients.Visible = False
                lnkDiseases.Visible = False

                lblSpace.Visible = False
                lblSpace2.Visible = False
                lblSpace3.Visible = False
                lblSpace4.Visible = False


        End Select



    End Sub

    Protected Sub btnEnter_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnEnter.Click

        Dim strEmail As String = txtUserName.Text

        Dim dtDOB As Date = CDate(txtDOB.Text)
        Dim strPassCheck As String

        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim strSQL As String = "SELECT [pID], [RADAR_NO],[pDOB], [pPassWord] FROM [tbl_Patient_Users] WHERE ( [pUserName] = '" & strEmail & "')"

        Dim TDES As TripleDES = New TripleDES

        Try

            Dim objConnect As New SqlConnection(strConnect)
            objConnect.Open()

            Dim objCommand As New SqlCommand(strSQL, objConnect)

            Dim objDataReader As SqlDataReader

            objDataReader = objCommand.ExecuteReader()

            'check password
            If objDataReader.Read() Then

                strPassCheck = TDES.Decrypt(objDataReader("pPassWord"))

                If (strPassCheck = txtPwd.Text And dtDOB = CDate(objDataReader("pDOB"))) Then

                    Session("blnAuthenticated") = True
                    Session("patientID") = objDataReader("RADAR_NO")
                    Session("dtRecord") = ""
                    Session("uType") = "p"
                    Session.Timeout = "30"
                    Session("mode") = 2


                End If

            End If


            'close the DataReader and Connection

            objDataReader.Close()
            objConnect.Close()


        Catch objError As Exception

            'display error

            lblOutMsg.Text = "Error accessing the database<br / >" _
            & objError.Message & "<br />" & objError.Source _
            '& strSQL

            Exit Sub ' stop execution

        End Try

        If Session("blnAuthenticated") Then

            Response.Redirect("diagnosis.aspx")


        Else
            'outMsg.Text = strPassCheck
            lblOutMsg.Text = "The details you entered are incorrect.<br/><br/> Please try again or contact the RRA Office.<br/>"



        End If

    End Sub
End Class
