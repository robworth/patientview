
Partial Class consent_forms
    Inherits System.Web.UI.Page

    
    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load

        If Not Session("blnAuthenticated") Then
            Response.Redirect("professionals.aspx")
        End If

        Select Case Session("unitID")

            Case 1 'Belfast
                pnlGenericSRNS.Visible = True
            Case 2 'Birmingham
                pnlBhamSRNS.Visible = True

            Case 3 'Bristol
                pnlBristolSRNS.Visible = True
            Case 4 'Cardiff
                pnlCardiffSRNS.Visible = True
            Case 5 'Glasgow
                pnlGlasgowSRNS.Visible = True
            Case 6 'Leeds
                pnlLeedsSRNS.Visible = True
            Case 7 'Liverpool
                pnlLiverpoolSRNS.Visible = True
            Case 8 'London Evelina
                pnlEvelinaSRNS.Visible = True
            Case 9 'London GOS
                pnlGoshSRNS.Visible = True
            Case 10 ' Manchester
                pnlManchesterSRNS.Visible = True
            Case 11 'Newscastle
                pnlNewcastleSRNS.Visible = True
            Case 12 'Nottingham
                pnlNottinghamSRNS.Visible = True
            Case 13 'Southampton
                pnlGenericSRNS.Visible = True
            Case Else
                pnlGenericSRNS.Visible = True

        End Select


    End Sub
End Class
