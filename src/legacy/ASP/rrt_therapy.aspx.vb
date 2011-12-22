Imports System.Data
Imports System.Data.OleDb
Imports System.Data.SqlClient
Imports System.IO
Imports System.Web.HttpBrowserCapabilities
Partial Class rrt_therapy3
    Inherits System.Web.UI.Page
    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load

        If Not Session("blnAuthenticated") Then
            Response.Redirect("default.aspx")
        End If

        If Session("uType") = "p" Then
            lblLogAll.Visible = False
            btnAddTx.Visible = False
            btnDialAdd.Visible = False
            CommonClass.DisableControls(tblMain)
        Else
            lnkRelapse.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkDemographics.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnk3Months.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkLabResults.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkDiagnosis.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkFollowupClinical.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkFollowupLab.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkFollowupTreatment.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkTherapy.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkTimelines.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkPathology.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkTimelines.Visible = False
        End If

        txtDateToday.Text = Format(DateTime.Today, "dd-MM-yyyy")

        If GridView3.Rows.Count > 1 Then
            GridView3.Rows(0).BackColor = Drawing.Color.LemonChiffon
        End If

        If Not IsPostBack Then

            PageFill()

        End If


    End Sub

    Sub PageFill()

        'lblDebug.Text = ""

        Dim TDES As New TripleDES()

        Dim patientID As Integer = Session("patientID")

        Dim strSQL As String = "SELECT tbl_Diagnosis.DIAG, tbl_Diagnosis.DATE_ESRF, tbl_Demographics.SNAME, tbl_Demographics.FNAME, tbl_Demographics.DOB, tbl_Demographics.RADAR_NO, tbl_Demographics.HOSP_NO FROM tbl_Demographics INNER JOIN tbl_Diagnosis ON tbl_Demographics.RADAR_NO = tbl_Diagnosis.RADAR_NO WHERE tbl_Demographics.RADAR_NO = " & patientID
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        Try

            Dim objDataReader As SqlDataReader
            objConnect.Open()
            objDataReader = objCommand.ExecuteReader()


            If objDataReader.Read() Then

                txtRADAR_NO.Text = objDataReader("RADAR_NO")
                txtFNAME.Text = TDES.Decrypt(objDataReader("FNAME"))
                txtSNAME.Text = TDES.Decrypt(objDataReader("SNAME"))
                txtHOSP_NO.Text = TDES.Decrypt(objDataReader("HOSP_NO"))
                Dim dt As Date = CDate(TDES.Decrypt(objDataReader("DOB")))
                txtDOB.Text = Format(dt, "dd-MMM-yyyy")

                If objDataReader("DATE_ESRF") IsNot DBNull.Value Then
                    'txtDATE_ESRF.Text = Format(objDataReader("DATE_ESRF"), "dd-MM-yyyy")
                    lblESRF.Text = Format(objDataReader("DATE_ESRF"), "dd-MMM-yyyy")
                Else
                    lblESRF.Text = "<em>Not entered</em>"
                End If


                If objDataReader("DIAG") Is DBNull.Value Then
                    txtDIAG.Text = "-"
                Else
                    txtDIAG.Text = CommonClass.GetDiagnosis(objDataReader("DIAG"))
                End If

                If objDataReader("DIAG") = 1 Then
                    lblPage.Text = "9B"
                ElseIf objDataReader("DIAG") = 2 Then
                    lblPage.Text = "9A"
                End If

            End If
        Catch objError As Exception

            lblDebug.Text = "An error occurred: '" & objError.Message & "'" & strSQL
            Exit Sub

        Finally

            objConnect.Close()

        End Try

    End Sub



    'Protected Sub btnHDAdd_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnHDAdd.Click

    '    If ddlHD.SelectedIndex = 0 Then
    '        ddlHD.BackColor = Drawing.Color.LemonChiffon
    '        Exit Sub
    '    End If

    '    If txtDATE_START_HDIAL.Text = "" Then
    '        txtDATE_START_HDIAL.BackColor = Drawing.Color.LemonChiffon
    '        Exit Sub
    '    End If

    '    'If txtDATE_STOP_HDIAL.Text = "" Then
    '    '    txtDATE_STOP_HDIAL.BackColor = Drawing.Color.LemonChiffon
    '    '    Exit Sub
    '    'End If


    '    Dim intHD_TMT_MODALITY As Integer = ddlHD.SelectedValue
    '    Dim dtDATE_START_HDIAL As Date = CDate(txtDATE_START_HDIAL.Text)
    '    Dim dtDATE_STOP_HDIAL As Date = CDate(txtDATE_STOP_HDIAL.Text)

    '    If CheckDates(Session("patientID"), 1, dtDATE_START_HDIAL, dtDATE_STOP_HDIAL, intHD_TMT_MODALITY) = False Then
    '        'ASPNET_MsgBox("The Start Date conflicts with an existing treatment")
    '        'UserMsgBox(Me, "The start date conflicts")
    '        MessageBox("Test")
    '        lblWarn.Text = "Dates conflict with treatments above"
    '        Exit Sub
    '    Else
    '        lblWarn.Text = ""
    '    End If

    '    Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RadarConnectionString").ConnectionString
    '    Dim objConnect As New System.Data.SqlClient.SqlConnection(strConnect)
    '    'Dim strSQL As String = "INSERT INTO tbl_RRT_HD (RADAR_NO, HD_TMT_MODALITY, DATE_START_HDIAL, DATE_STOP_HDIAL) VALUES (@RADAR_NO, @HD_TMT_MODALITY, @DATE_START_HDIAL, @DATE_STOP_HDIAL)"

    '    Dim strSQL As String = "INSERT INTO tbl_RRT_TREATMENT (RADAR_NO, MODALITY, DATE_START, DATE_STOP, UNIT_CODE) VALUES (@RADAR_NO, @HD_TMT_MODALITY, @DATE_START_HDIAL, @DATE_STOP_HDIAL, @UNIT_ID)"


    '    Dim objCommand As New System.Data.SqlClient.SqlCommand(strSQL, objConnect)
    '    Try
    '        With objCommand.Parameters

    '            .Add(New System.Data.SqlClient.SqlParameter("@RADAR_NO", Session("patientID")))
    '            .Add(New System.Data.SqlClient.SqlParameter("@HD_TMT_MODALITY", intHD_TMT_MODALITY))
    '            .Add(New System.Data.SqlClient.SqlParameter("@DATE_START_HDIAL", dtDATE_START_HDIAL))
    '            .Add(New System.Data.SqlClient.SqlParameter("@DATE_STOP_HDIAL", dtDATE_STOP_HDIAL))
    '            .Add(New System.Data.SqlClient.SqlParameter("@UNIT_ID", Session("unitID")))


    '        End With

    '        objConnect.Open()
    '        objCommand.ExecuteNonQuery()

    '    Catch

    '    Finally
    '        objConnect.Close()
    '    End Try

    '    GridView1.DataBind()
    '    ddlHD.SelectedIndex = 0
    '    txtDATE_START_HDIAL.Text = ""
    '    txtDATE_STOP_HDIAL.Text = ""

    'End Sub

    Protected Sub btnDial_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnDialAdd.Click

        If ddlPD.SelectedIndex = 0 Then
            ddlPD.BackColor = Drawing.Color.LemonChiffon
            Exit Sub
        Else
            ddlPD.BackColor = Drawing.Color.White
        End If

        If txtDATE_START_DIAL.Text = "" Then
            txtDATE_START_DIAL.BackColor = Drawing.Color.LemonChiffon
            Exit Sub
        Else
            txtDATE_START_DIAL.BackColor = Drawing.Color.White
        End If

        'If txtDATE_STOP_PD.Text = "" Then
        '    txtDATE_STOP_PD.BackColor = Drawing.Color.LemonChiffon
        '    Exit Sub
        'End If

        If txtDATE_STOP_DIAL.Text = "" Then
            'OK, stop date not known
        Else
            'check for stop date =< today and < start date
            If IsDate(txtDATE_STOP_DIAL.Text) Then

                If CDate(txtDATE_STOP_DIAL.Text) > CDate(txtDateToday.Text) Then
                    lblStopWarn.Text = "> Today"
                    lblWarn.Text = ""
                    Exit Sub
                Else
                    lblStopWarn.Text = ""
                End If

                If CDate(txtDATE_STOP_DIAL.Text) < CDate(txtDATE_START_DIAL.Text) Then
                    lblStopWarn.Text = "< Start"
                    lblWarn.Text = ""
                    Exit Sub
                Else
                    lblStopWarn.Text = ""
                End If
            Else
                lblStopWarn.Text = "Enter a date"
                lblWarn.Text = ""
                Exit Sub
            End If

        End If

        Dim intD_TMT_MODALITY As Integer = ddlPD.SelectedValue
        Dim dtDATE_START_DIAL As Date = CDate(txtDATE_START_DIAL.Text)

        Dim dtDATE_STOP_DIAL As Date

        If txtDATE_STOP_DIAL.Text = "" Then
            dtDATE_STOP_DIAL = CDate(txtDATE_START_DIAL.Text).AddDays(1)  ' can we get another treatment in between existing dates
        Else
            dtDATE_STOP_DIAL = CDate(txtDATE_STOP_DIAL.Text)

        End If

        If CheckDates(1, Session("patientID"), 1, dtDATE_START_DIAL, dtDATE_STOP_DIAL, intD_TMT_MODALITY) = False Then
            'ASPNET_MsgBox("The Start Date conflicts with an existing treatment")
            'UserMsgBox(Me, "The start date conflicts")
            'MessageBox("Test")
            'lblWarn.Text = "Dates conflict with treatments above"
            'lblDebug.Text = dtDATE_STOP_DIAL
            Exit Sub
        Else
            lblWarn.Text = ""
        End If

        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RadarConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)
        'Dim strSQL As String = "INSERT INTO tbl_RRT_PD (RADAR_NO, PD_TMT_MODALITY, DATE_START_PD, DATE_STOP_PD) VALUES (@RADAR_NO, @PD_TMT_MODALITY, @DATE_START_PD, @DATE_STOP_PD)"

        Dim strSQL As String = "INSERT INTO tbl_RRT_TREATMENT (RADAR_NO, MODALITY, DATE_START, DATE_STOP, UNIT_CODE) VALUES (@RADAR_NO, @PD_TMT_MODALITY, @DATE_START_PD, @DATE_STOP_PD, @UNIT_ID)"
        Dim objCommand As New SqlCommand(strSQL, objConnect)
        Try
            With objCommand.Parameters

                .Add(New SqlParameter("@RADAR_NO", Session("patientID")))
                .Add(New SqlParameter("@PD_TMT_MODALITY", intD_TMT_MODALITY))
                .Add(New SqlParameter("@DATE_START_PD", dtDATE_START_DIAL))

                If txtDATE_STOP_DIAL.Text = "" Then
                    .Add(New SqlParameter("@DATE_STOP_PD", DBNull.Value))
                Else
                    .Add(New SqlParameter("@DATE_STOP_PD", CDate(txtDATE_STOP_DIAL.Text)))
                End If

                .Add(New SqlParameter("@UNIT_ID", Session("unitID")))



            End With

            objConnect.Open()
            objCommand.ExecuteNonQuery()

        Catch

        Finally
            objConnect.Close()
        End Try

        GridView2.DataBind()
        ddlPD.SelectedIndex = 0
        txtDATE_START_DIAL.Text = ""
        txtDATE_STOP_DIAL.Text = ""
        ddlPD.BackColor = Drawing.Color.White
        txtDATE_START_DIAL.BackColor = Drawing.Color.White
        txtDATE_STOP_DIAL.BackColor = Drawing.Color.White

    End Sub

    Function CheckDates(ByVal intMode As Integer, ByVal intRADAR_NO As Integer, ByVal intType As Integer, ByVal dtStart As Date, ByVal dtEnd As Date, ByVal intModality As Integer) As Boolean

        Dim strStartDate As String = CStr(Year(dtStart)) & "-" & CStr(Month(dtStart)) & "-" & CStr(Day(dtStart))
        Dim strStopDate As String = CStr(Year(dtEnd)) & "-" & CStr(Month(dtEnd)) & "-" & CStr(Day(dtEnd))

        dtStart = CDate(strStartDate)
        dtEnd = CDate(strStopDate)

        Dim strSQL As String
        Dim strSQL2 As String
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RadarConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)

        If intMode = 1 Then 'New entry: test for open treatments

            strSQL2 = "SELECT [RADAR_NO] FROM [tbl_RRT_TREATMENT] WHERE ( ([DATE_START] <= (cast(@START_DATE as smalldatetime)) AND [DATE_STOP] IS NULL ) AND [RADAR_NO] = @RADAR_NO)"

        ElseIf intMode = 2 Then 'updating an existing entry

            strSQL2 = "SELECT [RADAR_NO] FROM [tbl_RRT_TREATMENT] WHERE ( (([DATE_START] <= (cast(@START_DATE as smalldatetime)) OR [DATE_START] <= (cast(@END_DATE as smalldatetime))) AND [DATE_STOP] IS NULL ) AND ([RADAR_NO] = @RADAR_NO) AND [tID] <> @tID )"

        End If

        Dim objCommand2 As New SqlCommand(strSQL2, objConnect)

        Try
            With objCommand2.Parameters

                .Add(New SqlParameter("@RADAR_NO", intRADAR_NO))
                .Add(New SqlParameter("@START_DATE", dtStart))
                .Add(New SqlParameter("@END_DATE", dtEnd))
                If intMode = 2 Then
                    .Add(New SqlParameter("@tID", CInt(lblIntID.Text)))
                End If

            End With

            Dim objDataReader2 As SqlDataReader
            objConnect.Open()
            objDataReader2 = objCommand2.ExecuteReader()


            If objDataReader2.Read() Then
                CheckDates = False
                If intMode = 1 Then
                    lblWarn.Text = "Please close the previous treatment before adding a new one with later dates"
                ElseIf intMode = 2 Then
                    lblEditWarn.Text = "Please close the previous treatment before adding a new one with later dates"
                End If
                objConnect.Close()
                Exit Function
            Else
                lblWarn.Text = ""
                objConnect.Close()
            End If

        Catch ex As Exception
            lblDebug.Text = ex.Message
            objConnect.Close()
        End Try



        Select Case intType

            Case 1  ' Dialysis, checking all types 1-19, which can't overlap

                Select Case intMode
                    Case 1 'new entry
                        strSQL = "SELECT RADAR_NO FROM [tbl_RRT_TREATMENT] WHERE (((cast(@START_DATE as smalldatetime) >= [DATE_START] AND cast(@START_DATE as smalldatetime) < [DATE_STOP]) OR (cast(@END_DATE as smalldatetime) > [DATE_START] AND cast(@END_DATE as smalldatetime)<= [DATE_STOP] ) OR (cast(@START_DATE as smalldatetime) <= [DATE_START] AND cast(@END_DATE as smalldatetime) >= [DATE_STOP])) AND [RADAR_NO] = @RADAR_NO)"

                    Case 2 'editing an existing entry
                        strSQL = "SELECT RADAR_NO FROM [tbl_RRT_TREATMENT] WHERE (((cast(@START_DATE as smalldatetime) >= [DATE_START] AND cast(@START_DATE as smalldatetime) < [DATE_STOP]) OR (cast(@END_DATE as smalldatetime) > [DATE_START] AND cast(@END_DATE as smalldatetime)<= [DATE_STOP] ) OR (cast(@START_DATE as smalldatetime) <= [DATE_START] AND cast(@END_DATE as smalldatetime) >= [DATE_STOP])) AND ([RADAR_NO] = @RADAR_NO AND [tID] <> @tID))"

                End Select


            Case 3  ' Plasmapheresis NOT NOW USED ON THIS PAGE

                strSQL = "SELECT RADAR_NO FROM [tbl_RRT_PLASMA] WHERE (((cast(@START_DATE as smalldatetime) >= [DATE_START_PLASMAPH] AND cast(@START_DATE as smalldatetime) < [DATE_STOP_PLASMAPH]) OR (cast(@END_DATE as smalldatetime) > [DATE_START_PLASMAPH] AND cast(@END_DATE as smalldatetime)<= [DATE_STOP_PLASMAPH] ) OR (cast(@START_DATE as smalldatetime) <= [DATE_START_PLASMAPH]) AND (cast(@END_DATE as smalldatetime) >= [DATE_STOP_PLASMAPH])) AND [RADAR_NO] = @RADAR_NO)"

        End Select



        Dim objCommand As New SqlCommand(strSQL, objConnect)

        Try

            With objCommand.Parameters

                .Add(New SqlParameter("@RADAR_NO", intRADAR_NO))
                .Add(New SqlParameter("@START_DATE", dtStart))
                .Add(New SqlParameter("@END_DATE", dtEnd))
                If intMode = 2 Then
                    .Add(New SqlParameter("@tID", CInt(lblIntID.Text)))
                End If


            End With

            'declare a variable to hold the a DataReader object
            Dim objDataReader As SqlDataReader
            objConnect.Open()
            'execute the SQL statement
            objDataReader = objCommand.ExecuteReader()


            If objDataReader.Read() Then

                CheckDates = False
                'lblDebug.Text = "dates conflict: startdate = " & dtStart & " enddate = " & dtEnd & "<br/>" & strSQL
                If intMode = 1 Then
                    lblWarn.Text = "This patient is still on HD/PD. Simultaneous PD and HD not allowed"
                ElseIf intMode = 2 Then
                    lblEditWarn.Text = "This patient is still on HD/PD. Simultaneous PD and HD not allowed"
                End If

            Else

                CheckDates = True
                'lblDebug.Text = "dates don't conflict: " & dtStart & "<br/>" & strSQL
                lblWarn.Text = ""
                lblEditWarn.Text = ""
            End If

            ' lblDebug.Text = strSQL

        Catch objError As Exception

            lblDebug.Text = "An error occurred (CheckDates): '" & objError.Message & "'" & strSQL & "<br/>Start = " & dtStart & "<br/>Stop = " & dtEnd
            objConnect.Close()
            Exit Function

        Finally

            objConnect.Close()

        End Try


    End Function

    Public Sub ASPNET_MsgBox(ByVal Message As String)

        Dim strMsg As String = "<script language=javascript>alert('test');</script>"

        'System.Web.HttpContext.Current.Response.Write("<SCRIPT LANGUAGE='JavaScript'>" & vbCrLf)

        'System.Web.HttpContext.Current.Response.Write("alert('" & Message & "')" & vbCrLf)

        'System.Web.HttpContext.Current.Response.Write("</SCRIPT>")

        System.Web.HttpContext.Current.Response.Write(strMsg)

    End Sub


    Public Sub UserMsgBox(ByVal F As Object, ByVal sMsg As String)
        Dim sb As New StringBuilder()
        Dim oFormObject As System.Web.UI.Control = Nothing
        Try
            sMsg = sMsg.Replace("'", "\'")
            sMsg = sMsg.Replace(Chr(34), "\" & Chr(34))
            sMsg = sMsg.Replace(vbCrLf, "\n")
            sMsg = "<script language='javascript'>alert('" & sMsg & "');</script>"
            sb = New StringBuilder()
            sb.Append(sMsg)
            For Each oFormObject In F.Controls
                If TypeOf oFormObject Is HtmlForm Then
                    Exit For
                End If
            Next
            oFormObject.Controls.AddAt(oFormObject.Controls.Count, New LiteralControl(sb.ToString()))
        Catch ex As Exception

        End Try
    End Sub

    Private Sub MessageBox(ByVal msg As String)

        ' define a javascript alertbox containing

        ' the string passed in as argument



        ' create a new label

        Dim lbl As New Label()



        ' add the javascript to fire an alertbox to the label and

        ' add the string argument passed to the subroutine as the

        ' message payload for the alertbox

        lbl.Text = "<script language='javascript'>" & Environment.NewLine & "window.alert('" + msg + "')</script>"



        ' add the label to the page to display the alertbox

        Page.Controls.Add(lbl)

    End Sub



    Protected Sub btnAddTx_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnAddTx.Click

        lblDebug.Text = ""

        If txtDateTX.Text = "" Then
            txtDateTX.BackColor = Drawing.Color.LemonChiffon
            Exit Sub
        End If

        If DropDownList1.SelectedIndex = 0 Then
            DropDownList1.BackColor = Drawing.Color.LemonChiffon
            Exit Sub
        End If

        'If txtDateBiop.Text = "" Then
        '    txtDateBiop.BackColor = Drawing.Color.LemonChiffon
        '    Exit Sub
        'End If

        Dim dtTx As Date = CDate(txtDateTx.Text)
        Dim intRadarNo As Integer = Session("patientID")

        If CheckTransplantDates(1, dtTx, 0, intRadarNo) = False Then

            Exit Sub
        Else
            'lblDebug.Text = "Dates OK"
        End If




        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RadarConnectionString").ConnectionString
        Dim strSQL As String = "INSERT INTO [tbl_transplant] (RADAR_NO, DATE_TRANSPLANT, TRANS_TYPE, TRANSPLANT_COUNTER, DATE_NEPHRECT, TRANS_RECURR, DATE_RECURR_TXK, DATE_TX_REJECT, DATE_BX_TXK) VALUES (@RADAR_NO, @DATE_TRANSPLANT, @TRANS_TYPE, @TRANSPLANT_COUNTER, @DATE_NEPHRECT, @TRANS_RECURR, @DATE_RECURR_TXK, @DATE_TX_REJECT, @DATE_BX_TXK)"
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)


        Try
            With objCommand.Parameters

                .Add(New SqlParameter("@RADAR_NO", Session("patientID")))
                .Add(New SqlParameter("@DATE_TRANSPLANT", CDate(txtDateTX.Text)))
                .Add(New SqlParameter("@TRANS_TYPE", DropDownList1.SelectedValue))
                .Add(New SqlParameter("@TRANSPLANT_COUNTER", 1))

                If txtDATE_NEPHRECT.Text = "" Then
                    .Add(New SqlParameter("@DATE_NEPHRECT", DBNull.Value))
                Else
                    .Add(New SqlParameter("@DATE_NEPHRECT", CDate(txtDATE_NEPHRECT.Text)))
                End If


                If RadNo.Checked Then
                    .Add(New SqlParameter("@TRANS_RECURR", False))
                ElseIf RadYes.Checked Then
                    .Add(New SqlParameter("@TRANS_RECURR", True))
                Else
                    .Add(New SqlParameter("@TRANS_RECURR", DBNull.Value))
                End If

                If txtDateRecurr.Text = "" Then
                    .Add(New SqlParameter("@DATE_RECURR_TXK", DBNull.Value))
                Else
                    .Add(New SqlParameter("@DATE_RECURR_TXK", CDate(txtDateRecurr.Text)))
                End If

                'If txtDateReject.Text = "" Then
                .Add(New SqlParameter("@DATE_TX_REJECT", DBNull.Value))
                'Else
                '    .Add(New SqlParameter("@DATE_TX_REJECT", CDate(txtDateReject.Text)))
                'End If

                'If txtDateBiop.Text = "" Then
                .Add(New SqlParameter("@DATE_BX_TXK", DBNull.Value))

                'Else
                '    .Add(New SqlParameter("@DATE_BX_TXK", CDate(txtDateBiop.Text)))

                'End If


            End With

            objConnect.Open()
            objCommand.ExecuteNonQuery()

        Catch objError As Exception
            lblDebug.Text = objError.Message & "<br/>" & strSQL
            Exit Sub

        Finally
            objConnect.Close()
        End Try

        GridView3.DataBind()
        txtDateTX.Text = ""
        'txtDateBiop.Text = ""
        txtDATE_NEPHRECT.Text = ""
        txtDateRecurr.Text = ""
        'txtDateReject.Text = ""
        DropDownList1.SelectedIndex = 0
        radNo.Checked = False
        radYes.Checked = False
        txtDateTX.BackColor = Drawing.Color.White
        DropDownList1.BackColor = Drawing.Color.White


    End Sub

    Protected Sub btnDialEditCancel_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnDialEditCancel.Click
        pnlEditDial.Visible = False
        GridView2.Enabled = True
        btnDialAdd.Enabled = True
        lblEditWarn.Text = ""
        PageFill()
    End Sub

    Protected Sub btnDialUpdate_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnDialUpdate.Click

        If ddlEditDial.SelectedValue = "" Then
            ddlEditDial.BackColor = Drawing.Color.LemonChiffon
            Exit Sub
        Else
            ddlEditDial.BackColor = Drawing.Color.White
        End If

        If txtEditDialStart.Text = "" Then

            txtEditDialStart.BackColor = Drawing.Color.LemonChiffon
            Exit Sub
        Else
            txtEditDialStart.BackColor = Drawing.Color.White
        End If

        If CDate(txtEditDialStart.Text) > CDate(txtDateToday.Text) Then
            lblEditStartWarn.Text = "> today"
            Exit Sub
        Else
            lblEditStartWarn.Text = ""
        End If

        If txtEditDialStop.Text = "" Then
            'ok
        Else
            If CDate(txtEditDialStop.Text) > CDate(txtDateToday.Text) Then
                lblEditStopWarn.Text = "> today"

                Exit Sub
            Else
                lblEditStopWarn.Text = ""
            End If


            If CDate(txtEditDialStop.Text) <= CDate(txtEditDialStart.Text) Then
                lblEditStopWarn.Text = "< start date"
                Exit Sub
            Else
                lblEditStopWarn.Text = ""
            End If

        End If

        Dim dtDATE_START_DIAL As Date = CDate(txtEditDialStart.Text)
        Dim dtDATE_STOP_DIAL As Date = CDate(txtEditDialStop.Text)
        Dim intD_TMT_MODALITY As Integer = ddlEditDial.SelectedValue
        Dim intDialID As Integer = CInt(lblIntID.Text)

        If CheckDates(2, Session("patientID"), 1, dtDATE_START_DIAL, dtDATE_STOP_DIAL, intD_TMT_MODALITY) = False Then
            lblDebug.Text = "conflict"
            Exit Sub
        Else
            lblWarn.Text = ""
        End If

        Dim strSQL As String = "UPDATE [tbl_RRT_TREATMENT] SET [DATE_START] = @DATE_START, DATE_STOP= @DATE_STOP, MODALITY = @MODALITY WHERE [tID] = @tID"
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Using objConnect As New SqlConnection(strConnect)
            Using objCommand As New SqlCommand(strSQL, objConnect)
                With objCommand.Parameters
                    .Add(New SqlParameter("@tID", intDialID))
                    .Add(New SqlParameter("@DATE_START", CDate(txtEditDialStart.Text)))

                    If txtEditDialStop.Text = "" Then
                        .Add(New SqlParameter("@DATE_STOP", DBNull.Value))
                    Else
                        .Add(New SqlParameter("@DATE_STOP", CDate(txtEditDialStop.Text)))
                    End If

                    .Add(New SqlParameter("@MODALITY", ddlEditDial.SelectedValue))
                End With
                Try
                    objConnect.Open()
                    objCommand.ExecuteNonQuery()
                Catch objError As Exception
                    lblDebug.Text = String.Format("An error occurred: '{0}'", objError.Message)
                    objConnect.Close()
                    Exit Sub
                Finally
                    objConnect.Close()
                End Try
            End Using
        End Using


        pnlEditDial.Visible = False
        btnDialUpdate.Enabled = True
        GridView2.Enabled = True
        GridView2.DataBind()
        btnDialAdd.Enabled = True

    End Sub

    Sub GridView2_Row_Editing(ByVal sender As Object, ByVal e As GridViewEditEventArgs)
        Dim intID As Integer = CInt(GridView2.Rows(e.NewEditIndex).Cells(0).Text)
        e.Cancel = True 'cancel the default edit mode

        ddlEditDial.DataBind()


        Dim strSQL As String = "SELECT [DATE_START], [DATE_STOP], [MODALITY] FROM [tbl_RRT_TREATMENT] WHERE tid = '" & intID & "'"
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Using objConnect As New SqlConnection(strConnect)
            Using objCommand As New SqlCommand(strSQL, objConnect)
                Try
                    Dim objDataReader As SqlDataReader
                    objConnect.Open()
                    objDataReader = objCommand.ExecuteReader()
                    If objDataReader.Read() Then
                        'txtEditDialStart.Text = objDataReader("DATE_START")
                        'txtEditDialStop.Text = objDataReader("DATE_STOP")
                        ddlEditDial.SelectedValue = objDataReader("MODALITY")
                        txtEditDialStart_CalendarExtender.SelectedDate = CDate(objDataReader("DATE_START"))
                        If objDataReader("DATE_STOP") Is DBNull.Value Then
                            txtEditDialStop.Text = ""
                        Else
                            txtEditDialStop_CalendarExtender.SelectedDate = CDate(objDataReader("DATE_STOP"))
                        End If

                        lblIntID.Text = intID
                    End If
                Catch objError As Exception
                    lblDebug.Text = objError.Message
                Finally
                    objConnect.Close()
                End Try
            End Using
        End Using

        pnlEditDial.Visible = True
        GridView2.Enabled = False
        btnDialAdd.Enabled = False
        'txtImmunoStart.Focus()
        lblEditWarn.Text = ""
        PageFill()

    End Sub

    Sub GridView3_Row_Editing(ByVal sender As Object, ByVal e As GridViewEditEventArgs)
        Dim intID As Integer = CInt(GridView3.Rows(e.NewEditIndex).Cells(0).Text)
        e.Cancel = True 'cancel the default edit mode
        DropDownList2.DataBind()
        pnlTxEdit.Visible = True
        btnAddTx.Enabled = False
        GridView3.Enabled = False

        Dim strSQL As String = "SELECT [DATE_TRANSPLANT], [TRANS_TYPE], [DATE_NEPHRECT], [TRANS_RECURR], [DATE_RECURR_TXK], [DATE_TX_REJECT], [DATE_BX_TXK] FROM [tbl_Transplant] WHERE [trID] = " & intID
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Using objConnect As New SqlConnection(strConnect)
            Using objCommand As New SqlCommand(strSQL, objConnect)
                Try
                    Dim objDataReader As SqlDataReader
                    objConnect.Open()
                    objDataReader = objCommand.ExecuteReader()
                    If objDataReader.Read() Then
                        lblTxID.Text = intID
                        CalendarExtender_EditDateTx.SelectedDate = CDate(objDataReader("DATE_TRANSPLANT"))

                        'If objDataReader("DATE_TX_REJECT") Is DBNull.Value Then
                        'Else
                        '    CalendarExtender_EditDateReject.SelectedDate = CDate(objDataReader("DATE_TX_REJECT"))
                        'End If

                        'If objDataReader("DATE_BX_TXK") Is DBNull.Value Then
                        'Else
                        '    CalendarExtender_EditDateBiop.SelectedDate = CDate(objDataReader("DATE_BX_TXK"))
                        'End If

                        If objDataReader("DATE_RECURR_TXK") Is DBNull.Value Then
                        Else
                            CalendarExtender_EditDateRecurr.SelectedDate = CDate(objDataReader("DATE_RECURR_TXK"))
                        End If

                        If objDataReader("DATE_NEPHRECT") Is DBNull.Value Then
                        Else
                            txtEditDateFail_CalendarExtender.SelectedDate = CDate(objDataReader("DATE_NEPHRECT"))
                        End If


                        If objDataReader("TRANS_TYPE") Is DBNull.Value Then
                            DropDownList2.SelectedValue = ""
                            'lblDebug.Text = ""
                        Else
                            DropDownList2.SelectedValue = objDataReader("TRANS_TYPE")
                            'lblDebug.Text = objDataReader("TRANS_TYPE")
                        End If

                        If objDataReader("TRANS_RECURR") Is DBNull.Value Then
                            radEditRecurrNo.Checked = False
                            radEditRecurrYes.Checked = False
                        ElseIf objDataReader("TRANS_RECURR") = True Then
                            radEditRecurrYes.Checked = True
                            radEditRecurrNo.Checked = False
                        ElseIf objDataReader("TRANS_RECURR") = False Then
                            radEditRecurrNo.Checked = True
                            radEditRecurrYes.Checked = False
                        End If

                    End If
                Catch objError As Exception
                    lblDebug.Text = objError.Message & "<br/>" & strSQL
                Finally
                    objConnect.Close()
                End Try
            End Using
        End Using

    End Sub

    Shared Sub GridView_RowCreated(ByVal sender As Object, ByVal e As GridViewRowEventArgs)
        e.Row.Cells(0).Visible = False

    End Sub

    Protected Sub btnTxEdit_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnTxEdit.Click

        lblDebug.Text = ""

        If txtEditDateTx.Text = "" Then
            txtEditDateTx.BackColor = Drawing.Color.LemonChiffon
            Exit Sub
        End If

        If DropDownList2.SelectedIndex = 0 Then
            DropDownList2.BackColor = Drawing.Color.LemonChiffon
            Exit Sub
        End If

        'If txtDateBiop.Text = "" Then
        '    txtDateBiop.BackColor = Drawing.Color.LemonChiffon
        '    Exit Sub
        'End If

        Dim dtTx As Date = CDate(txtEditDateTx.Text)
        Dim intRadarNo As Integer = Session("patientID")

        If CheckTransplantDates(2, dtTx, CInt(lblTxID.Text), intRadarNo) = False Then

            Exit Sub
        Else
            'lblDebug.Text = "Dates OK"
        End If

        Dim strSQL As String = "UPDATE [tbl_transplant] SET [DATE_TRANSPLANT] = @DATE_TRANSPLANT, [TRANS_TYPE] = @TRANS_TYPE, [DATE_NEPHRECT] = @DATE_NEPHRECT, [TRANS_RECURR] = @TRANS_RECURR, [DATE_RECURR_TXK] = @DATE_RECURR_TXK, [DATE_TX_REJECT] = @DATE_TX_REJECT, [DATE_BX_TXK] = @DATE_BX_TXK WHERE [trID] = @trID"
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Using objConnect As New SqlConnection(strConnect)
            Using objCommand As New SqlCommand(strSQL, objConnect)
                With objCommand.Parameters
                    .Add(New SqlParameter("@trID", CInt(lblTxID.Text)))
                    .Add(New SqlParameter("@DATE_TRANSPLANT", CDate(txtEditDateTx.Text)))
                    .Add(New SqlParameter("@TRANS_TYPE", DropDownList2.SelectedValue))

                    If txtEditDateFail.Text = "" Then
                        .Add(New SqlParameter("@DATE_NEPHRECT", DBNull.Value))
                    Else
                        .Add(New SqlParameter("@DATE_NEPHRECT", CDate(txtEditDateFail.Text)))
                    End If


                    If radEditRecurrNo.Checked Then
                        .Add(New SqlParameter("@TRANS_RECURR", False))
                    ElseIf radEditRecurrYes.Checked Then
                        .Add(New SqlParameter("@TRANS_RECURR", True))
                    Else
                        .Add(New SqlParameter("@TRANS_RECURR", DBNull.Value))
                    End If

                    If txtEditDateRecurr.Text = "" Then
                        .Add(New SqlParameter("@DATE_RECURR_TXK", DBNull.Value))
                    Else
                        .Add(New SqlParameter("@DATE_RECURR_TXK", CDate(txtEditDateRecurr.Text)))
                    End If

                    If txtEditDateFail.Text = "" Then
                        .Add(New SqlParameter("@DATE_TX_REJECT", DBNull.Value))
                    Else
                        .Add(New SqlParameter("@DATE_TX_REJECT", CDate(txtEditDateFail.Text)))
                    End If

                    'If txtEditDateBiop.Text = "" Then
                    .Add(New SqlParameter("@DATE_BX_TXK", DBNull.Value))
                    'Else
                    '    .Add(New SqlParameter("@DATE_BX_TXK", CDate(txtEditDateBiop.Text)))
                    'End If

                End With
                Try
                    objConnect.Open()
                    objCommand.ExecuteNonQuery()
                Catch objError As Exception
                    lblDebug.Text = String.Format("An error occurred: '{0}'", objError.Message)
                    objConnect.Close()
                    Exit Sub
                Finally
                    objConnect.Close()
                End Try
            End Using
        End Using

        pnlTxEdit.Visible = False
        GridView3.DataBind()
        GridView3.Enabled = True
        btnAddTx.Enabled = True



    End Sub

    Protected Sub txEditCancel_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles txEditCancel.Click
        pnlTxEdit.Visible = False
        GridView3.Enabled = True
        btnAddTx.Enabled = True


    End Sub

    Shared Function chkNull(ByVal value As Object) As String

        If value Is DBNull.Value Then
            chkNull = ""
        Else
            chkNull = value.ToString
        End If

    End Function

    '# removed 03-Sep-10
    'Protected Sub btnSave_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnSave.Click

    '    Dim strSQL As String = "UPDATE [tbl_Diagnosis] SET [DATE_ESRF] = @DATE_ESRF WHERE [RADAR_NO] = @RADAR_NO"
    '    Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
    '    Using objConnect As New SqlConnection(strConnect)
    '        Using objCommand As New SqlCommand(strSQL, objConnect)


    '            Try
    '                With objCommand.Parameters

    '                    .Add(New SqlParameter("@RADAR_NO", Session("patientID")))
    '                    If txtDATE_ESRF.Text = "" Then
    '                        .Add(New SqlParameter("@DATE_ESRF", DBNull.Value))
    '                    Else
    '                        .Add(New SqlParameter("@DATE_ESRF", CDate(txtDATE_ESRF.Text)))
    '                    End If
    '                End With

    '                objConnect.Open()
    '                objCommand.ExecuteNonQuery()


    '            Catch objError As Exception
    '                lblDebug.Text = "An error occurred: " & objError.Message
    '                Exit Sub

    '            Finally
    '                objConnect.Close()
    '            End Try
    '        End Using
    '    End Using

    '    lblUpdate.Text = "<strong>Update successful:</strong>&nbsp;&nbsp;" & Now().ToShortTimeString

    'End Sub

    Protected Sub GridView2_RowDeleted(ByVal senedr As Object, ByVal e As GridViewDeletedEventArgs)
        lblWarn.Text = ""
        lblDebug.Text = ""
    End Sub

    Protected Sub GridView3_RowDeleted(ByVal senedr As Object, ByVal e As GridViewDeletedEventArgs)
        lblTxWarn.Text = ""
        lblDebug.Text = ""
    End Sub

    Function CheckTransplantDates(ByVal intMode As Integer, ByVal dtTx As Date, ByVal intTrID As Integer, ByVal intRadarNo As Integer) As Boolean

        '# check for overlapping dates < 2 weeks


        Dim strSQL2 As String
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RadarConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)

        If intMode = 1 Then ' New entry
            strSQL2 = "SELECT [RADAR_NO] FROM [tbl_Transplant] WHERE ([DATE_TRANSPLANT] BETWEEN cast(@DATE_TRANSPLANT1 as smalldatetime) AND cast(@DATE_TRANSPLANT2 as smalldatetime) AND [RADAR_NO] = @RADAR_NO )"

        ElseIf intMode = 2 Then  'Editing an entry
            strSQL2 = "SELECT [RADAR_NO] FROM [tbl_Transplant] WHERE ([DATE_TRANSPLANT] BETWEEN cast(@DATE_TRANSPLANT1 as smalldatetime) AND cast(@DATE_TRANSPLANT2 as smalldatetime) AND ([RADAR_NO] = @RADAR_NO AND [trID] <> @trID) )"
        End If

        Dim objCommand2 As New SqlCommand(strSQL2, objConnect)

        Try
            With objCommand2.Parameters
                .Add(New SqlParameter("@DATE_TRANSPLANT1", CDate(dtTx).AddDays(-14)))
                .Add(New SqlParameter("@DATE_TRANSPLANT2", CDate(dtTx).AddDays(14)))
                .Add(New SqlParameter("@RADAR_NO", intRadarNo))
                If intMode = 2 Then
                    .Add(New SqlParameter("@trID", intTrID))
                End If
            End With

            Dim objDataReader2 As SqlDataReader
            objConnect.Open()
            objDataReader2 = objCommand2.ExecuteReader()


            If objDataReader2.Read() Then
                CheckTransplantDates = False
                If intMode = 1 Then
                    lblTxWarn.Text = "Transplant dates must be  a min of 14 days apart"
                ElseIf intMode = 2 Then
                    lblEditTxWarn.Text = "Transplant dates must be  a min of 14 days apart"
                End If

                'lblDebug.Text = CDate(dtTx.AddDays(-14)) '"Dates conflict"
                objConnect.Close()
                Exit Function
            Else
                CheckTransplantDates = True
                lblTxWarn.Text = ""
            End If

        Catch ex As Exception
            lblDebug.Text = "Error (CheckTxDates): " & ex.Message

        Finally
            objConnect.Close()

        End Try
        'lblDebug.Text = CDate(dtTx).AddDays(-14) & "<BR/>" & CDate(dtTx).AddDays(14)
        lblEditTxWarn.Text = ""

    End Function

    Function GetRecurr(ByVal bolRecurr As Object) As String

        If bolRecurr Is DBNull.Value Then
            GetRecurr = ""
        ElseIf bolRecurr = True Then
            GetRecurr = "Yes"
        ElseIf bolRecurr = False Then
            GetRecurr = "No"
        Else
            GetRecurr = ""
        End If

    End Function

    Protected Sub txInsert(ByVal sender As Object, ByVal e As GridViewCommandEventArgs)
        Dim intID As Integer = CInt(GridView3.DataKeys(CInt(e.CommandArgument)).Value)
        lblDebug.Text = intID
    End Sub

    'Protected Sub GridView3_RowCommand(ByVal sender As Object, ByVal e As GridViewCommandEventArgs) Handles GridView3.RowCommand
    '    Dim intID As Integer = CInt(GridView3.DataKeys(CInt(e.CommandArgument)).Value)
    '    lblDebug.Text = intID

    'End Sub

    Protected Sub GridView3_RowDataBound(ByVal sender As Object, ByVal e As System.Web.UI.WebControls.GridViewRowEventArgs) Handles GridView3.RowDataBound


        If e.Row.RowType = DataControlRowType.DataRow Then

            Dim s As SqlDataSource = CType(e.Row.FindControl("SqlDataSource7"), SqlDataSource)
            s.SelectParameters(0).DefaultValue = e.Row.DataItem("trID")

            Dim GridView4 As GridView = CType(e.Row.FindControl("GridView4"), GridView)

            If Request.Browser.Browser = "IE" Then
                GridView4.ControlStyle.BorderWidth = 1
            Else
                GridView4.ControlStyle.BorderWidth = 0
            End If


        End If


    End Sub

    Protected Sub AddRejectData(ByVal sender As Object, ByVal e As EventArgs)

        Dim addRejectButton As Button = DirectCast(sender, Button)
        Dim row As GridViewRow = DirectCast(addRejectButton.Parent.Parent, GridViewRow)
        txtTrID.Text = row.Cells(0).Text
        txtDateTxCompare.Text = row.Cells(1).Text
        lblDateTx.Text = row.Cells(1).Text


        ''# check for existing failure date
        'Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RadarConnectionString").ConnectionString
        ''Dim strSQL As String = "SELECT [trFailureDate] FROM [tbl_Transplant_Reject] WHERE [trID] = " & CInt(txtTrID.Text)
        'Dim strSQL As String = "SELECT [DATE_NEPHRECT] FROM [tbl_Transplant] WHERE [trID] = " & CInt(txtTrID.Text)
        'Dim objConnect As New SqlConnection(strConnect)
        'Dim objCommand As New SqlCommand(strSQL, objConnect)
        'Dim objDataReader As SqlDataReader

        'objConnect.Open()

        'objDataReader = objCommand.ExecuteReader

        'If objDataReader.Read Then
        '    If objDataReader("DATE_NEPHRECT") IsNot DBNull.Value Then
        '        lblDebug.Text = objDataReader("DATE_NEPHRECT").ToString + "we have an existing failure record"
        '        objConnect.Close()
        '        Exit Sub
        '    End If


        'End If

        pnlAdReject.Visible = True

    End Sub

    Protected Sub btnAddRejectData_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnAddRejectData.Click

        lblAddRejectWarn.Visible = False

        If chkRejectDate(CInt(txtTrID.Text), CDate(txtDateRejectAdd.Text)) = True Then
            lblAddRejectWarn.Visible = True
            Exit Sub
        End If

        'lblDebug.Text = txtTrID.Text

        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RadarConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)
        Dim strSQL As String = "INSERT INTO [tbl_Transplant_Reject] (trID, trRejectDate, trBiopsyDate, trFailureDate ) VALUES (@trID, @trRejectDate, @trBiopsyDate, @trFailureDate)"
        Dim objCommand As New SqlCommand(strSQL, objConnect)


        Try
            With objCommand.Parameters

                .Add(New SqlParameter("@trID", txtTrID.text))

                If txtDateRejectAdd.Text = "" Then
                    .Add(New SqlParameter("@trRejectDate", DBNull.Value))
                Else
                    .Add(New SqlParameter("@trRejectDate", CDate(txtDateRejectAdd.Text)))
                End If

                If txtDateBiopsyAdd.Text = "" Then
                    .Add(New SqlParameter("@trBiopsyDate", DBNull.Value))
                Else
                    .Add(New SqlParameter("@trBiopsyDate", CDate(txtDateBiopsyAdd.Text)))
                End If

                'If txtDateFailureAdd.Text = "" Then
                .Add(New SqlParameter("@trFailureDate", DBNull.Value))
                'Else
                '    .Add(New SqlParameter("@trFailureDate", CDate(txtDateFailureAdd.Text)))
                'End If

            End With

            objConnect.Open()
            objCommand.ExecuteNonQuery()

        Catch objError As Exception
            lblDebug.Text = objError.Message
        Finally
            objConnect.Close()
        End Try

        pnlAdReject.Visible = False
        GridView3.DataBind()


    End Sub


    Protected Sub btnRejectEditCancel_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnRejectEditCancel.Click
        pnlAdReject.Visible = False
    End Sub

    'Function GetFailVisible(ByVal entry As Object) As Boolean

    '    If entry Is DBNull.Value Then
    '        GetFailVisible = True
    '    Else
    '        GetFailVisible = False
    '    End If

    'End Function

    Function chkRejectDate(ByVal trID As Integer, ByVal rejectDate As Date) As Boolean

        

        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RadarConnectionString").ConnectionString
        Dim strSQL As String = "SELECT [DATE_NEPHRECT] FROM [tbl_Transplant] WHERE [trID] = @trID AND [DATE_NEPHRECT] >=  cast(@trRejectDate as smalldatetime)"
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)
        Dim objDataReader As SqlDataReader

        With objCommand.Parameters

            .Add(New SqlParameter("@trRejectDate", rejectDate))
            .Add(New SqlParameter("@trID", trID))


        End With

        objConnect.Open()

        objDataReader = objCommand.ExecuteReader

        If objDataReader.Read Then
            If objDataReader("DATE_NEPHRECT") IsNot DBNull.Value Then

                Return False
            End If
        Else

            Return True
        End If

        objConnect.Close()

    End Function

End Class
