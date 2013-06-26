
Partial Class MasterPage2
    Inherits System.Web.UI.MasterPage

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load


        Select Case Session("mode")

            Case 1 'entry

                'lnkDiagnosis.Enabled = True
                'lnk3Months.Enabled = True
                'lnkLabResults.Enabled = True
                'lnkClinical.Enabled = True
                'lnkTherapy.Enabled = True
                lnkDiagnosis.Enabled = False
                lnk3Months.Enabled = False
                lnkLabResults.Enabled = False
                lnkClinical.Enabled = False
                lnkTherapy.Enabled = False

                If Page.Title = "RADAR - Clinical" Then
                    lnkClinical.CssClass = "hovered"
                End If


            Case 2 'edit
                lnkDiagnosis.Enabled = True
                lnk3Months.Enabled = True
                lnkLabResults.Enabled = True
                lnkClinical.Enabled = True
                lnkTherapy.Enabled = True

            Case Else

                lnkDiagnosis.Enabled = False
                lnk3Months.Enabled = False
                lnkLabResults.Enabled = False
                lnkClinical.Enabled = False
                lnkTherapy.Enabled = False

        End Select


    End Sub
End Class

