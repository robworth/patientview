
Partial Class Default3
    Inherits System.Web.UI.Page

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load


        If Request.ServerVariables("SERVER_NAME") = "www.renal-test.org" Then
            pnlDemo.Visible = True
           
        End If

        Dim lnkAbout As HyperLink = Master.FindControl("lnkAbout")
        Dim lnkStudy As HyperLink = Master.FindControl("lnkStudy")
        Dim lnkProfessionals As HyperLink = Master.FindControl("lnkProfessionals")
        Dim lnkPatients As HyperLink = Master.FindControl("lnkPatients")
        Dim lblSpace As Label = Master.FindControl("lblSpace")
        Dim lblSpace2 As Label = Master.FindControl("lblSpace2")
        Dim lblSpace3 As Label = Master.FindControl("lblSpace3")

        If IsPostBack Then

            Dim d As Integer = Request.QueryString("d")

            'Select Case d

            '    Case 1
            '        lnkAbout.Text = "About FSGS"
            '        lnkAbout.Visible = True
            '        lnkStudy.Text = "FSGS Study Group"
            '        lnkStudy.Visible = True
            '        lnkProfessionals.Visible = True
            '        lnkProfessionals.NavigateUrl = "professionals.aspx?d=1"
            '        lnkPatients.Visible = True
            '        lnkPatients.NavigateUrl = "patients.aspx?d=1"
            '        lblSpace.Visible = True
            '        lblSpace2.Visible = True
            '        lblSpace3.Visible = True
            '        lnkMPGN.ForeColor = Drawing.Color.FromArgb(109, 152, 99)
            '        lnkFSGS.ForeColor = Drawing.Color.DarkGreen
            '    Case 2
            '        lnkAbout.Text = "About MPGN"
            '        lnkAbout.Visible = True
            '        lnkStudy.Text = "MPGN Study Group"
            '        lnkStudy.Visible = True
            '        lnkProfessionals.Visible = True
            '        lnkProfessionals.NavigateUrl = "professionals.aspx?d=2"
            '        lnkFSGS.ForeColor = Drawing.Color.FromArgb(109, 152, 99)
            '        lnkMPGN.ForeColor = Drawing.Color.DarkGreen

            '        lnkPatients.NavigateUrl = "patients.aspx?d=2"
            '        lnkPatients.Visible = True
            '        lblSpace.Visible = True
            '        lblSpace2.Visible = True
            '        lblSpace3.Visible = True
            '        lnkMPGN.ForeColor = Drawing.Color.DarkGreen
            '    Case Else
            '        lnkAbout.Visible = False
            '        lnkStudy.Visible = False
            '        lnkProfessionals.Visible = False
            '        lnkPatients.Visible = False
            '        lblSpace.Visible = False
            '        lblSpace2.Visible = False
            '        lblSpace3.Visible = False



            'End Select


            lblSession.Text = d.ToString


        End If

    End Sub

    'Protected Sub lnkMPGN_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles lnkMPGN.Click
    '    Session("disease") = 2
    '    AutoPostBackControl = lnkMPGN
    'End Sub

    'Protected Sub lnkFSGS_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles lnkFSGS.Click
    '    Session("disease") = 1
    '    AutoPostBackControl = lnkFSGS
    'End Sub


End Class
