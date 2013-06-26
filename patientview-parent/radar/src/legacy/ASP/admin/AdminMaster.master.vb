
Partial Class admin_AdminMaster
    Inherits System.Web.UI.MasterPage
    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load
        If Session("blnAdminAuthenticated") = True Then
            lnkLog.Text = "Log-Out"
            lnkLog.Visible = True
            lnkLog.NavigateUrl = "logout.aspx"
            lnkUsers.Visible = True
            lnkPatients.Visible = True
            lnkIssues.Visible = True


            'rightNav.Visible = True
        Else

            lnkLog.Visible = False

            'lnkCurrent.Visible = False
            'lnkDisease.Visible = False
            'lnkEnter.Visible = False
            'lnkModify.Visible = False
            lnkUsers.Visible = False
            lblSpace.Visible = False
            lblSpace2.Visible = False
            lblSpace3.Visible = False
            lnkPatients.Visible = False
            lnkIssues.Visible = False
            'rightNav.Visible = False
        End If
    End Sub
End Class

