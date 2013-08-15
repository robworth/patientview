Imports System.Data
Imports System.Data.OleDb
Imports System.Data.SqlClient
Imports System.IO
Partial Class about
    Inherits System.Web.UI.Page

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load

        Dim lnkAbout As HyperLink = Master.FindControl("lnkAbout")
        Dim lnkStudy As HyperLink = Master.FindControl("lnkStudy")
        Dim lnkProfessionals As HyperLink = Master.FindControl("lnkProfessionals")
        Dim lnkPatients As HyperLink = Master.FindControl("lnkPatients")
        Dim lnkDiseases As HyperLink = Master.FindControl("lnkDiseases")

        Dim lblSpace As Label = Master.FindControl("lblSpace")
        Dim lblSpace2 As Label = Master.FindControl("lblSpace2")
        Dim lblSpace3 As Label = Master.FindControl("lblSpace3")
        Dim lblSpace4 As Label = Master.FindControl("lblSpace4")

        Dim intD As Integer = Request.QueryString("d")

        Select Case intD



            Case 1

                'lblTitle.Text = "About SRNS"

            Case 2

                'lblTitle.Text = "About MPGN/DDD"

                'Case 3

                '    lblTitle.Text = "About Dense Deposit Disease"

            Case Else

        End Select


        Select Case intD

            Case 1

                lnkAbout.Text = "About SRNS"
                lnkAbout.NavigateUrl = "about.aspx?d=1"
                lnkAbout.Visible = False
                lnkStudy.Text = "SRNS Study Group"
                lnkStudy.NavigateUrl = "study.aspx?d=1"
                lnkStudy.Visible = False
                lnkProfessionals.Visible = True
                lnkProfessionals.NavigateUrl = "professionals.aspx"
                lnkPatients.Visible = True
                lnkPatients.NavigateUrl = "patients.aspx"
                lblSpace.Visible = False
                lblSpace2.Visible = False
                lblSpace3.Visible = True
                lblSpace4.Visible = True
                lnkDiseases.Visible = True

            Case 2

                lnkAbout.Text = "About MPGN"
                lnkAbout.NavigateUrl = "about.aspx?d=2"
                lnkAbout.Visible = False
                lnkStudy.Text = "MPGN Study Group"
                lnkStudy.Visible = False
                lnkStudy.NavigateUrl = "study.aspx?d=2"
                lnkProfessionals.Visible = True
                lnkProfessionals.NavigateUrl = "professionals.aspx"
                lnkPatients.NavigateUrl = "patients.aspx"
                lnkPatients.Visible = True
                lblSpace.Visible = False
                lblSpace2.Visible = False
                lblSpace3.Visible = True
                lblSpace4.Visible = True
                lnkDiseases.Visible = True

                'Case 3

                '    lnkAbout.Text = "About DDD"
                '    lnkAbout.NavigateUrl = "about.aspx?d=3"
                '    lnkAbout.Visible = True
                '    lnkStudy.Text = "DDD Study Group"
                '    lnkStudy.NavigateUrl = "study.aspx?d=3"
                '    lnkStudy.Visible = True
                '    lnkProfessionals.Visible = True
                '    lnkProfessionals.NavigateUrl = "professionals.aspx?d=3"
                '    lnkPatients.NavigateUrl = "patients.aspx?d=3"
                '    lnkPatients.Visible = True
                '    lblSpace.Visible = True
                '    lblSpace2.Visible = True
                '    lblSpace3.Visible = True
                '    lblSpace4.Visible = True
                '    lnkDiseases.Visible = True


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



        Dim strSQL As String = "SELECT [dText] FROM [tbl_DiseaseData] WHERE ([dID] = '" & Request.QueryString("d") & "')"
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        Try

            'declare a variable to hold the a DataReader object
            Dim objDataReader As SqlDataReader
            objConnect.Open()
            'execute the SQL statement
            objDataReader = objCommand.ExecuteReader()


            If objDataReader.Read() Then
                pnlText.InnerHtml = objDataReader("dText")

            End If

        Catch objError As Exception
            lblDebug.Text = objError.Message & "<br/>" & strSQL

        End Try


    End Sub
End Class
