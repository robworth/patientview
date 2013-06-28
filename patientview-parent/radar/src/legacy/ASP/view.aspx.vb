
Partial Class view
    Inherits System.Web.UI.Page

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load

        If Not Session("blnAuthenticated") Then
            Response.Redirect("default.aspx")
        End If

        Dim TDES As New TripleDES()

        Session("dtRecord") = DBNull.Value  'reset the date of record flag for followup pages
        Session("mode") = 2  'edit

        If (Session("userID") = 28 Or Session("userID") = 15) Then
            'SqlDataSource1.SelectCommand = "SELECT tbl_DEMOGRAPHICS.RADAR_NO, NHS_NO, HOSP_NO, SNAME, FNAME, DOB, DATE_REG, sAbbrev, DIAG FROM tbl_Demographics, tbl_Diagnosis, tbl_Status WHERE (tbl_Diagnosis.RADAR_NO = tbl_Demographics.RADAR_NO) AND (tbl_STATUS.sID = tbl_DEMOGRAPHICS.STATUS) ORDER BY DATE_REG DESC"
            'SqlDataSource1.SelectCommand = "SELECT tbl_Demographics.SNAME, tbl_Demographics.FNAME, tbl_Demographics.DOB, tbl_Demographics.RADAR_NO, tbl_Demographics.NHS_NO, tbl_Demographics.HOSP_NO, tbl_Demographics.DATE_REG, tbl_Diagnosis.DIAG, tbl_Status.sAbbrev FROM tbl_Demographics LEFT OUTER JOIN tbl_Diagnosis ON tbl_Demographics.RADAR_NO = tbl_Diagnosis.RADAR_NO LEFT OUTER JOIN tbl_Status ON tbl_Demographics.STATUS = tbl_Status.sID ORDER BY DATE_REG DESC"
            SqlDataSource1.SelectCommand = "SELECT tbl_Demographics.RADAR_NO, tbl_Demographics.SNAME, tbl_Demographics.FNAME, tbl_Demographics.DOB, tbl_Demographics.NHS_NO, tbl_Demographics.HOSP_NO, tbl_Demographics.DATE_REG, tbl_Centres.cAbbrev, tbl_Status.sAbbrev, tbl_Diagnosis.DIAG FROM tbl_Demographics INNER JOIN tbl_Diagnosis ON tbl_Demographics.RADAR_NO = tbl_Diagnosis.RADAR_NO LEFT OUTER JOIN tbl_Status ON tbl_Demographics.STATUS = tbl_Status.sID LEFT OUTER JOIN tbl_Centres ON tbl_Demographics.RENAL_UNIT = tbl_Centres.cID"
        Else
            'SqlDataSource1.SelectCommand = "SELECT tbl_DEMOGRAPHICS.RADAR_NO, NHS_NO, HOSP_NO, SNAME, FNAME, DOB, DATE_REG, sAbbrev, DIAG FROM tbl_Demographics, tbl_Diagnosis, tbl_Status WHERE (tbl_Diagnosis.RADAR_NO = tbl_Demographics.RADAR_NO) AND (tbl_STATUS.sID = tbl_DEMOGRAPHICS.STATUS) AND (RENAL_UNIT = '" & Session("unitID") & "') ORDER BY DATE_REG DESC"
            'SqlDataSource1.SelectCommand = "SELECT tbl_Demographics.SNAME, tbl_Demographics.FNAME, tbl_Demographics.DOB, tbl_Demographics.RADAR_NO, tbl_Demographics.NHS_NO, tbl_Demographics.HOSP_NO, tbl_Demographics.DATE_REG, tbl_Diagnosis.DIAG, tbl_Status.sAbbrev FROM tbl_Demographics LEFT OUTER JOIN tbl_Diagnosis ON tbl_Demographics.RADAR_NO = tbl_Diagnosis.RADAR_NO LEFT OUTER JOIN tbl_Status ON tbl_Demographics.STATUS = tbl_Status.sID  WHERE (RENAL_UNIT = '" & Session("unitID") & "') ORDER BY DATE_REG DESC"
            SqlDataSource1.SelectCommand = "SELECT tbl_Demographics.RADAR_NO, tbl_Demographics.SNAME, tbl_Demographics.FNAME, tbl_Demographics.DOB, tbl_Demographics.NHS_NO, tbl_Demographics.HOSP_NO, tbl_Demographics.DATE_REG, tbl_Centres.cAbbrev, tbl_Status.sAbbrev, tbl_Diagnosis.DIAG FROM tbl_Demographics INNER JOIN tbl_Diagnosis ON tbl_Demographics.RADAR_NO = tbl_Diagnosis.RADAR_NO LEFT OUTER JOIN tbl_Status ON tbl_Demographics.STATUS = tbl_Status.sID LEFT OUTER JOIN tbl_Centres ON tbl_Demographics.RENAL_UNIT = tbl_Centres.cID WHERE (RENAL_UNIT = '" & Session("unitID") & "') ORDER BY DATE_REG DESC"
            'GridView1.DataBind()
            GridView1.Columns(9).Visible = False
        End If

        Session("diag") = 0

        lblPage.Text = "15"

    End Sub


    Function Decrypt(ByVal encryptedObject As Object) As String

        Dim TDES As New TripleDES()

        Decrypt = TDES.Decrypt(encryptedObject)


    End Function

    Shared Function C2SDate(ByVal Convertdate As Object) As String

        If Not Convertdate Is System.DBNull.Value Then

            Dim aDay, aMonth, aYear

            aDay = Day(Convertdate)
            aMonth = MonthName(Month(Convertdate), True)
            aYear = Year(Convertdate)

            If Len(aDay) = 1 Then aDay = "0" & aDay

            C2SDate = aDay & "-" & aMonth & "-" & aYear

        Else
            'C2SDate = 

        End If


    End Function



    'Public Sub gridview1_OnRowCreated(ByVal sender As Object, ByVal e As Web.UI.WebControls.GridViewRowEventArgs) Handles GridView1.RowCreated

    '    # only show centre for super users

    '    If Session("userID") <> 28 Then
    '        If GridView1.Rows.Count Then
    '            e.Row.Cells(9).Visible = False
    '            'GridView1.Columns.RemoveAt(9)
    '        Else
    '            lblSort.Visible = False
    '        End If
    '    End If

    'End Sub


End Class
