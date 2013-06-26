Imports System.Data
Imports System.Data.OleDb
Imports System.Data.SqlClient
Imports System.IO
Partial Class drugs_events
    Inherits System.Web.UI.Page

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load

        If Not Session("blnAuthenticated") Then
            Response.Redirect("default.aspx")
        End If

        If Not IsPostBack Then
            pagefill()
        End If

    End Sub

    Sub pagefill()

        Dim strSQL As String = "SELECT tbl_6Month.fuID, tbl_6Month.RADAR_NO, tbl_6Month.DATE_FUP, tbl_6Month.RELAP_SINCE_LAST, tbl_6Month.RELAP_LEN, tbl_6Month.IMMUNOSUP_DUR, tbl_6Month.IMMUNOSUP_DOSE, tbl_6Month.IMMUNOSUP_INC, tbl_6Month.OTHER_TRIG, tbl_6Month.IMMUN_TRIG, tbl_6Month.VIRAL_TRIG, tbl_6Month.COMP1, tbl_6Month.COMP2, tbl_6Month.COMP3, tbl_6Month.COMP4, tbl_6Month.MIN_SER_ALB, tbl_6Month.MAX_PR_CREAT_RATIO, tbl_6Month.PLASMA_EXCH_NO, tbl_6Month.PLASMA_EXCH, tbl_6Month.RESPONSE_TO, tbl_6Month.DIAL_TYPE, tbl_6Month.DATE_BX, tbl_6Month.DATE_NEPHRECT, tbl_6Month.DATE_TX_REJECT, tbl_6Month.DRUG1, tbl_6Month.TRANS_RECURR, tbl_6Month.TRANS_TYPE, tbl_6Month.DATE_TRANSPLANT, tbl_6Month.SIG_CHANGE_STATUS, tbl_6Month.DRUG6, tbl_6Month.DRUG5, tbl_6Month.DRUG4, tbl_6Month.DRUG3, tbl_6Month.DRUG2, tbl_Demographics.SNAME, tbl_Demographics.FNAME, tbl_Demographics.DOB, tbl_Demographics.HOSP_NO, tbl_Diagnosis.DIAG FROM tbl_6Month INNER JOIN tbl_Demographics ON tbl_6Month.RADAR_NO = tbl_Demographics.RADAR_NO INNER JOIN tbl_Diagnosis ON tbl_Demographics.RADAR_NO = tbl_Diagnosis.RADAR_NO WHERE (tbl_6Month.RADAR_NO = " & Session("patientID") & ")"
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New System.Data.SqlClient.SqlConnection(strConnect)
        Dim objCommand As New System.Data.SqlClient.SqlCommand(strSQL, objConnect)

        Try

            Dim objDataReader As SqlDataReader
            objConnect.Open()
            objDataReader = objCommand.ExecuteReader()

            If objDataReader.Read() Then

                txtRADAR_NO.Text = objDataReader("RADAR_NO")
                txtHOSP_NO.Text = objDataReader("HOSP_NO").ToString

                If objDataReader("DIAG") Is DBNull.Value Then
                    txtDIAG.Text = ""
                Else
                    txtDIAG.Text = GetDiagnosis(objDataReader("DIAG").ToString)
                End If

                If objDataReader("DATE_FUP") Is DBNull.Value Then
                Else
                    ASPxDateEdit1.Date = objDataReader("DATE_FUP")
                End If

                txtFNAME.Text = objDataReader("FNAME")
                txtSNAME.Text = objDataReader("SNAME")
                txtDOB.Text = Format(objDataReader("DOB"), "dd-MMM-yyyy")


                If objDataReader("DATE_BX") Is DBNull.Value Then
                Else
                    ASPxDateEdit7.Date = objDataReader("DATE_BX")
                End If

                If objDataReader("DATE_NEPHRECT") Is DBNull.Value Then
                Else
                    ASPxDateEdit8.Date = objDataReader("DATE_NEPHRECT")
                End If

                If objDataReader("DATE_FUP") Is DBNull.Value Then
                Else
                    ASPxDateEdit1.Date = objDataReader("DATE_FUP")
                End If


                ddlCOMP1.SelectedValue = chkNull(objDataReader("COMP1"))
                ddlCOMP2.SelectedValue = chkNull(objDataReader("COMP2"))
                ddlCOMP3.SelectedValue = chkNull(objDataReader("COMP3"))
                ddlCOMP4.SelectedValue = chkNull(objDataReader("COMP4"))


            End If

        Catch objError As Exception

            lblDebug.Text = "An error occurred: '" & objError.Message & "'" & strSQL


        Finally

            objConnect.Close()

        End Try

    End Sub

    Function GetDiagnosis(ByVal diagID As Integer) As String

        Select Case diagID
            Case "1"
                GetDiagnosis = "FSGS"
            Case "2"
                GetDiagnosis = "MPGN"
            Case Else
                GetDiagnosis = "Not specified"
        End Select

    End Function

    Function chkNull(ByVal value As Object) As String

        If value Is DBNull.Value Then
            chkNull = ""
        Else
            chkNull = value.ToString
        End If

    End Function

End Class
