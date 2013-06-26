
Partial Class MasterPage
    Inherits System.Web.UI.MasterPage

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load

        'If Request.ServerVariables("SERVER_PORT") <> 443 Then
        '    If Request.ServerVariables("SERVER_NAME") = "localhost" Then

        '    Else
        '        Response.Redirect("https://www.renal-test.org/default.aspx")
        '    End If
        'End If

        If Request.ServerVariables("HTTPS") = "off" Then
            If Request.ServerVariables("SERVER_NAME") = "localhost" Then

            Else
                Response.Redirect("https://www.renalradar.org/default.aspx")
            End If
        End If


        If Session("blnAuthenticated") = True Then
            lnkReg.Text = "Log-Out"
            lnkReg.NavigateUrl = "log-out.aspx"


            'rightNav.Visible = True
        Else

            lnkReg.Visible = True

            'rightNav.Visible = False
        End If

        If Session("uType") = "p" Then
            lnkPatients.Text = "Your Records"
        Else
            lnkPatients.Text = "Patients"
        End If

        If IsPostBack Then


            'Select Case Session("disease")


            '    Case 1

            '        lnkAbout.Text = "About FSGS"
            '        lnkAbout.Visible = True
            '        lnkStudy.Text = "FSGS Study Group"
            '        lnkStudy.Visible = True
            '        lnkProfessionals.Visible = True
            '        lnkPatients.Visible = True
            '        lblSpace.Visible = True
            '        lblSpace2.Visible = True
            '        lblSpace3.Visible = True

            '    Case 2

            '        lnkAbout.Text = "About MPGN"
            '        lnkAbout.Visible = True
            '        lnkStudy.Text = "MPGN Study Group"
            '        lnkStudy.Visible = True
            '        lnkProfessionals.Visible = True
            '        lnkPatients.Visible = True
            '        lblSpace.Visible = True
            '        lblSpace2.Visible = True
            '        lblSpace3.Visible = True

            '    Case Else

            '        lnkAbout.Visible = False
            '        lnkStudy.Visible = False
            '        lnkProfessionals.Visible = False
            '        lnkPatients.Visible = False
            '        lblSpace.Visible = False
            '        lblSpace2.Visible = False
            '        lblSpace3.Visible = False

            'End Select

        End If

    End Sub
End Class

