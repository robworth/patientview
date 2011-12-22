
Partial Class MasterPage
    Inherits System.Web.UI.MasterPage

    'Protected WithEvents body As System.Web.UI.HtmlControls.HtmlGenericControl

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
            'body.Attributes.Add("onload", "window.setTimeout(""window.location.href='professionals.aspx'"",5000);")


            'rightNav.Visible = True
        Else

            lnkReg.Visible = True
            'lnkCurrent.Visible = False
            'lnkDisease.Visible = False
            lnkEnter.Visible = False
            lnkModify.Visible = False
            'lblSpace.Visible = False ####
            'lblSpace2.Visible = False ####
            'lblSpace3.Visible = False
            'rightNav.Visible = False
        End If

        If Session("userID") = 28 Then
            lnkEnter.Enabled = False
        End If

        Select Session("diag")
            '    Case 1
            '        lnkAbout.Text = "About SRNS"
            '        lnkAbout.Visible = True
            '        lnkAbout.NavigateUrl = "about.aspx?d=1"
            '        lnkStudy.Text = "SRNS Study Group"
            '        lnkStudy.Visible = True
            '        lnkStudy.NavigateUrl = "study.aspx?d=1"
            '        lblSpace3.Visible = True
            '        lblSpace4.Visible = True
            '        lnkDisease.Visible = False
            '        lblSpace2.Visible = False
            Case 2
                '        lnkAbout.Text = "About MPGN"
                '        lnkAbout.Visible = True
                '        lnkAbout.NavigateUrl = "about.aspx?d=2"
                '        lnkStudy.Text = "MPGN Study Group"
                '        lnkStudy.Visible = True
                '        lnkStudy.NavigateUrl = "study.aspx?d=2"
                '        lblSpace3.Visible = True
                '        lblSpace4.Visible = True
                '        lnkDisease.Visible = False
                '        lblSpace2.Visible = False
                'Dim lnkRelapse As HyperLink = ContentPlaceHolder1.FindControl("lnkRelapse")
                'lnkRelapse.Visible = False
                '    Case Else
                '        lnkAbout.Visible = False
                '        lnkStudy.Visible = False
                '        lblSpace3.Visible = False
                '        lblSpace4.Visible = False
                '        lnkDisease.Visible = True
                '        lblSpace2.Visible = True
        End Select

        If Session("uType") = "p" Then
            lnkEnter.Visible = False
            lnkModify.Visible = False
            'lblSpace.Visible = False ####
            'lblSpace4.Visible = False

        End If

    End Sub
End Class

