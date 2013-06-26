Imports System.Data.SqlClient
Imports ConfigurationAlias = System.Configuration
Partial Class relapse
    Inherits System.Web.UI.Page

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load

        If Not Session("blnAuthenticated") Then
            Response.Redirect("default.aspx")
        End If

        If Session("uType") = "p" Then
            btnAdd.Visible = False
            CommonClass.DisableControls(tblMain)
            fieldsetAdd.Visible = False

        Else
            lnkRelapse.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnk3Months.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkLabResults.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkDiagnosis.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkDemographics.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkPathology.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkHospital.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkTimeLines.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkTimeLines.Visible = False
        End If

        If Request.QueryString("id") = "" Then
            'Response.Redirect("default.aspx")
        Else
            Session("patientID") = Request.QueryString("id")
            Session("mode") = 2
        End If

        txtDateToday.Text = Format(DateTime.Today, "dd-MM-yyyy")

        pageFill()

    End Sub

    Sub pageFill()

        Dim TDES As New TripleDES()

        lnkTimeLines.Visible = False

        Dim patientID As Integer = Session("patientID")
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim strSQL2 As String = "SELECT tbl_Diagnosis.DIAG, tbl_Demographics.SNAME, tbl_Demographics.FNAME, tbl_Demographics.DOB, tbl_Demographics.RADAR_NO, tbl_Demographics.HOSP_NO FROM tbl_Demographics INNER JOIN tbl_Diagnosis ON tbl_Demographics.RADAR_NO = tbl_Diagnosis.RADAR_NO WHERE (tbl_Demographics.RADAR_NO = '" & patientID & "')"
        Using objConnect2 As New SqlConnection(strConnect)
            Using objCommand2 As New SqlCommand(strSQL2, objConnect2)
                Try
                    'declare a variable to hold the a DataReader object
                    Dim objDataReader As SqlDataReader
                    objConnect2.Open()
                    'execute the SQL statement
                    objDataReader = objCommand2.ExecuteReader()
                    If objDataReader.Read() Then
                        txtRADAR_NO.Text = objDataReader("RADAR_NO")
                        txtHOSP_NO.Text = TDES.Decrypt(objDataReader("HOSP_NO"))
                        txtFNAME.Text = TDES.Decrypt(objDataReader("FNAME"))
                        txtSNAME.Text = TDES.Decrypt(objDataReader("SNAME"))
                        Dim dt As Date = CDate(TDES.Decrypt(objDataReader("DOB")))
                        txtDOB.Text = Format(dt, "dd-MMM-yyyy")

                        If objDataReader("DIAG") Is DBNull.Value Then
                            txtDIAGNOSIS.Text = "-"
                        Else
                            txtDIAGNOSIS.Text = CommonClass.GetDiagnosis(objDataReader("DIAG"))
                        End If

                        If objDataReader("DIAG") = 1 Then
                            lblPage.Text = "12B"
                        ElseIf objDataReader("DIAG") = 2 Then
                            lblPage.Text = "12A"
                        End If

                    End If
                Catch objError As Exception
                    lblDebug.Text = String.Format("An error occurred: '{0}'{1}", objError.Message, strSQL2)
                    objConnect2.Close()
                    Exit Sub
                Finally
                    objConnect2.Close()
                End Try
            End Using
        End Using



    End Sub

    

    Shared Function chkNull(ByVal value As Object) As String

        If value Is DBNull.Value Then
            chkNull = ""
        Else
            chkNull = value.ToString
        End If

    End Function

    Shared Function chkNull2(ByVal value As Object) As Object

        If value Is "" Then
            chkNull2 = DBNull.Value
        Else
            chkNull2 = value.ToString
        End If

    End Function

    Sub GridView1_RowEditing(ByVal sender As Object, ByVal e As GridViewEditEventArgs) Handles GridView1.RowEditing
        Dim intID As Integer = CInt(GridView1.Rows(e.NewEditIndex).Cells(0).Text)
        e.Cancel = True 'cancel the default edit mode
        pnlRelapseEdit.Visible = True
        btnAdd.Enabled = False
        GridView1.Enabled = False

        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim strSQL As String = "SELECT * FROM [tbl_Relapse] WHERE [relID] = " & intID
        Using objConnect As New SqlConnection(strConnect)
            Using objCommand As New SqlCommand(strSQL, objConnect)
                Try
                    Dim objDataReader As SqlDataReader
                    objConnect.Open()
                    objDataReader = objCommand.ExecuteReader()

                    If objDataReader.Read() Then
                        lblEditID.Text = intID
                        CalendarExtender_EditDateRelapse.SelectedDate = objDataReader("DATE_ONSET_RELAP")
                        txtEditViralTrigger.Text = chkNull(objDataReader("TRIG_VIRAL"))
                        txtEditImmunTrig.Text = chkNull(objDataReader("TRIG_IMMUN"))
                        txtEditOtherTrig.Text = chkNull(objDataReader("TRIG_OTHER"))
                        txtEditDrug1.Text = chkNull(objDataReader("RELAP_DRUG_1"))
                        txtEditDrug2.Text = chkNull(objDataReader("RELAP_DRUG_2"))
                        txtEditDrug3.Text = chkNull(objDataReader("RELAP_DRUG_3"))
                        If objDataReader("DATE_REMISSION") Is DBNull.Value Then
                        Else
                            CalendarExtender_EditDateRemission.SelectedDate = objDataReader("DATE_REMISSION")
                        End If
                        If objDataReader("RELAP_TX_NAT") Is DBNull.Value Then
                            radEditTxYes.Checked = False
                            radEditTxNo.Checked = False
                        ElseIf objDataReader("RELAP_TX_NAT") = True Then
                            radEditTxYes.Checked = True
                            radEditTxNo.Checked = False
                        ElseIf objDataReader("RELAP_TX_NAT") = False Then
                            radEditTxYes.Checked = False
                            radEditTxNo.Checked = True
                        End If

                        If objDataReader("REMISS_ACHIEVE") Is DBNull.Value Then
                            radEditAchieveYes.Checked = False
                            radEditAchieveNo.Checked = False
                            radEditAchievePartial.Checked = False
                        ElseIf objDataReader("REMISS_ACHIEVE") = 1 Then
                            radEditAchieveYes.Checked = True
                            radEditAchieveNo.Checked = False
                            radEditAchievePartial.Checked = False
                        ElseIf objDataReader("REMISS_ACHIEVE") = 2 Then
                            radEditAchieveYes.Checked = False
                            radEditAchievePartial.Checked = True
                            radEditAchieveNo.Checked = False
                        ElseIf objDataReader("REMISS_ACHIEVE") = 9 Then
                            radEditAchieveYes.Checked = False
                            radEditAchievePartial.Checked = False
                            radEditAchieveNo.Checked = True
                        End If

                    End If

                Catch objError As Exception
                    lblDebug.Text = objError.Message
                Finally
                    objConnect.Close()
                End Try
            End Using
        End Using



    End Sub

    Protected Sub btnAdd_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnAdd.Click


        Dim intRadar As Integer = CInt(txtRADAR_NO.Text)


        Dim strSQL As String = "INSERT INTO [tbl_Relapse] ([RADAR_NO], [DATE_ONSET_RELAP], [RELAP_TX_NAT], [TRIG_VIRAL], [TRIG_IMMUN], [TRIG_OTHER], [RELAP_DRUG_1], [RELAP_DRUG_2], [RELAP_DRUG_3], [REMISS_ACHIEVE], [DATE_REMISSION]) VALUES (@RADAR_NO, @DATE_ONSET_RELAP, @RELAP_TX_NAT, @TRIG_VIRAL, @TRIG_IMMUN, @TRIG_OTHER, @RELAP_DRUG_1, @RELAP_DRUG_2, @RELAP_DRUG_3, @REMISS_ACHIEVE, @DATE_REMISSION)"
        Dim strConnect As String = ConfigurationAlias.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString

        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        Try
            With objCommand.Parameters

                .Add(New SqlParameter("@RADAR_NO", intRadar))
                .Add(New SqlParameter("@DATE_ONSET_RELAP", CDate(txtDateRelapse.Text)))

                If radTxYes.Checked Then
                    .Add(New SqlParameter("@RELAP_TX_NAT", True))
                ElseIf radTxNo.Checked Then
                    .Add(New SqlParameter("@RELAP_TX_NAT", False))
                Else
                    .Add(New SqlParameter("@RELAP_TX_NAT", False))
                End If

                .Add(New SqlParameter("@TRIG_VIRAL", chkNull2(txtViralTrig.Text)))
                .Add(New SqlParameter("@TRIG_IMMUN", chkNull2(txtImmunTrig.Text)))
                .Add(New SqlParameter("@TRIG_OTHER", chkNull2(txtOtherTrig.Text)))
                .Add(New SqlParameter("@RELAP_DRUG_1", chkNull2(txtDrug1.Text)))
                .Add(New SqlParameter("@RELAP_DRUG_2", chkNull2(txtDrug2.Text)))
                .Add(New SqlParameter("@RELAP_DRUG_3", chkNull2(txtDrug3.Text)))

                If radAchievedYes.Checked Then
                    .Add(New SqlParameter("@REMISS_ACHIEVE", 1))
                ElseIf radAchievePartial.Checked Then
                    .Add(New SqlParameter("@REMISS_ACHIEVE", 2))
                ElseIf radAchieveNo.Checked Then
                    .Add(New SqlParameter("@REMISS_ACHIEVE", 9))
                Else
                    .Add(New SqlParameter("@REMISS_ACHIEVE", DBNull.Value))
                End If

                If txtEditDateRemission.Text = "" Then
                    .Add(New SqlParameter("@DATE_REMISSION", DBNull.Value))
                Else
                    .Add(New SqlParameter("@DATE_REMISSION", CDate(txtDateRemission.Text)))
                End If

            End With

            objConnect.Open()
            objCommand.ExecuteNonQuery()

        Catch objError As Exception

            lblDebug.Text = String.Format("An error occurred: '{0}'{1}", objError.Message, strSQL)
            objConnect.Close()
            Exit Sub

        Finally

            objConnect.Close()
        End Try

        txtDateRelapse.Text = ""
        radTxNo.Checked = False
        radTxNo.Checked = False
        txtViralTrig.Text = ""
        txtImmunTrig.Text = ""
        txtOtherTrig.Text = ""
        txtDrug1.Text = ""
        txtDrug2.Text = ""
        txtDrug3.Text = ""
        txtDateRemission.Text = ""
        radAchievedYes.Checked = False
        radAchieveNo.Checked = False
        radAchievePartial.Checked = False

        GridView1.DataBind()


    End Sub

  

    Protected Sub btnEditCancel_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnEditCancel.Click
        pnlRelapseEdit.Visible = False
        btnAdd.Enabled = True
        GridView1.Enabled = True
    End Sub

    
    Protected Sub btnEditSave_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnEditSave.Click

        Dim intRelID As Integer = CInt(lblEditID.Text)

        Dim strSQL As String = "UPDATE [tbl_Relapse] SET [DATE_ONSET_RELAP] = @DATE_ONSET_RELAP, [RELAP_TX_NAT] = @RELAP_TX_NAT, [TRIG_VIRAL] = @TRIG_VIRAL, [TRIG_IMMUN] = @TRIG_IMMUN, [TRIG_OTHER] = @TRIG_OTHER, [RELAP_DRUG_1] = @RELAP_DRUG_1,[RELAP_DRUG_2] = @RELAP_DRUG_2,[RELAP_DRUG_3] = @RELAP_DRUG_3, [REMISS_ACHIEVE] = @REMISS_ACHIEVE, [DATE_REMISSION] = @DATE_REMISSION WHERE [relID] = @relID "
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString

        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        Try
            With objCommand.Parameters

                .Add(New SqlParameter("@relID", intRelID))
                .Add(New SqlParameter("@DATE_ONSET_RELAP", CDate(txtEditDateRelapse.Text)))

                If radEditTxYes.Checked Then
                    .Add(New SqlParameter("@RELAP_TX_NAT", True))
                ElseIf radEditTxNo.Checked Then
                    .Add(New SqlParameter("@RELAP_TX_NAT", False))
                Else
                    .Add(New SqlParameter("@RELAP_TX_NAT", False))
                End If

                .Add(New SqlParameter("@TRIG_VIRAL", chkNull2(txtEditViralTrigger.Text)))
                .Add(New SqlParameter("@TRIG_IMMUN", chkNull2(txtEditImmunTrig.Text)))
                .Add(New SqlParameter("@TRIG_OTHER", chkNull2(txtEditOtherTrig.Text)))
                .Add(New SqlParameter("@RELAP_DRUG_1", chkNull2(txtEditDrug1.Text)))
                .Add(New SqlParameter("@RELAP_DRUG_2", chkNull2(txtEditDrug2.Text)))
                .Add(New SqlParameter("@RELAP_DRUG_3", chkNull2(txtEditDrug3.Text)))

                If radEditAchieveYes.Checked Then
                    .Add(New SqlParameter("@REMISS_ACHIEVE", 1))
                ElseIf radEditAchievePartial.Checked Then
                    .Add(New SqlParameter("@REMISS_ACHIEVE", 2))
                ElseIf radEditAchieveNo.Checked Then
                    .Add(New SqlParameter("@REMISS_ACHIEVE", 9))
                Else
                    .Add(New SqlParameter("@REMISS_ACHIEVE", DBNull.Value))
                End If

                If txtEditDateRemission.Text = "" Then
                    .Add(New SqlParameter("@DATE_REMISSION", DBNull.Value))
                Else
                    .Add(New SqlParameter("@DATE_REMISSION", CDate(txtEditDateRemission.Text)))
                End If

            End With

            objConnect.Open()
            objCommand.ExecuteNonQuery()

        Catch objError As Exception

            lblDebug.Text = String.Format("An error occurred: '{0}'{1}", objError.Message, strSQL)
            objConnect.Close()
            Exit Sub

        Finally

            objConnect.Close()
        End Try

        pnlRelapseEdit.Visible = False
        btnAdd.Enabled = True
        GridView1.Enabled = True
        GridView1.DataBind()

    End Sub

    Shared Function getRemission(ByVal intRem As Object) As String

        If intRem Is DBNull.Value Then
            getRemission = ""
        Else
            Select Case intRem

                Case 1
                    getRemission = "Complete"
                Case 2
                    getRemission = "Partial"
                Case 9
                    getRemission = "None"
                Case Else
                    getRemission = "Unknown"
            End Select
        End If



    End Function

    Function GetTxType(ByVal bolTX As Boolean) As String

        If bolTX = False Then
            GetTxType = "Nat"
        ElseIf bolTX = True Then
            GetTxType = "Trans"
        Else
            GetTxType = ""
        End If

    End Function

    Sub GridView3_RowEditing(ByVal sender As Object, ByVal e As GridViewEditEventArgs)
        Dim intID As Integer = CInt(GridView3.Rows(e.NewEditIndex).Cells(0).Text)
        e.Cancel = True
        DropDownList3.DataBind()

        ddlPlasmaExchEdit.Items.Clear()
        ddlPlasmaExchEdit.DataBind()


        lblPlasmaStopEditWarn.Text = ""

        Dim strSQL As String = "SELECT [plID], [DATE_START_PLASMAPH], [DATE_STOP_PLASMAPH], [NO_EXCH_PLASMAPH], [DUR_PLASMAPH], [RESPONSE_TO_PLASMA] FROM [tbl_RRT_PLASMA] WHERE [plID] = '" & intID & "'"
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        Try

            Dim objDataReader As SqlDataReader
            objConnect.Open()
            objDataReader = objCommand.ExecuteReader()


            If objDataReader.Read() Then

                txtDateStartPlasmaEdit.Text = objDataReader("DATE_START_PLASMAPH")
                CalendarExtender3.SelectedDate = objDataReader("DATE_START_PLASMAPH")
                If objDataReader("DATE_STOP_PLASMAPH") Is DBNull.Value Then
                    txtDateStopPlasmaEdit.Text = ""
                Else
                    txtDateStopPlasmaEdit.Text = objDataReader("DATE_STOP_PLASMAPH")
                    CalendarExtender4.SelectedDate = objDataReader("DATE_STOP_PLASMAPH")
                End If

                ddlPlasmaExchEdit.SelectedValue = CInt(objDataReader("NO_EXCH_PLASMAPH"))
                DropDownList3.SelectedIndex = objDataReader("RESPONSE_TO_PLASMA")
                lblpID.Text = objDataReader("plID")

            End If


        Catch objError As Exception
            lblDebug.Text = "Plasma Edit " & objError.Message
        Finally
            objConnect.Close()
        End Try


        'lblDebug.Text = strSQL


        btnPlasmaAdd.Enabled = False
        pnlPlasmaEdit.Visible = True
        GridView3.Enabled = False

    End Sub

    Shared Sub GridView_RowCreated(ByVal sender As Object, ByVal e As GridViewRowEventArgs)
        e.Row.Cells(0).Visible = False
    End Sub

    Protected Sub btnCancelPlasmaEdit_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnCancelPlasmaEdit.Click
        pnlPlasmaEdit.Visible = False
        GridView3.Enabled = True
        btnPlasmaAdd.Enabled = True
    End Sub

    Protected Sub btnUpdatePlasma_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnUpdatePlasma.Click
        If txtDateStartPlasmaEdit.Text = "" Then
            txtDateStartPlasmaEdit.BackColor = Drawing.Color.LemonChiffon
            Exit Sub
        Else
            txtDateStartPlasmaEdit.BackColor = Drawing.Color.White
        End If

        If ddlPlasmaExchEdit.SelectedValue = "" Then
            ddlPlasmaExchEdit.BackColor = Drawing.Color.LemonChiffon
            Exit Sub
        Else
            ddlPlasmaExchEdit.BackColor = Drawing.Color.White
        End If

        If DropDownList3.SelectedIndex = 0 Then
            DropDownList3.BackColor = Drawing.Color.LemonChiffon
            Exit Sub
        Else
            DropDownList3.BackColor = Drawing.Color.White
        End If


        Dim intRecord As Integer = CInt(lblpID.Text)


        Dim dtStart As Date = CDate(txtDateStartPlasmaEdit.Text)

        Dim dtStop As Date

        If txtDateStopPlasmaEdit.Text = "" Then
            dtStop = (dtStart).AddDays(1)
        Else
            dtStop = CDate(txtDateStopPlasmaEdit.Text)
            If dtStop <= dtStart Then
                lblPlasmaStopEditWarn.Text = "< start date"
                Exit Sub
            End If
        End If





        If CheckDates2(Session("patientID"), 3, dtStart, dtStop, intRecord) = False Then
            lblPlasmaEditWarn.Text = "Dates conflict with those above"
            Exit Sub
        Else
            lblPlasmaEditWarn.Text = ""
        End If

        Dim strSQL As String = "UPDATE [tbl_RRT_PLASMA] SET [DATE_START_PLASMAPH] = @DATE_START_PLASMAPH, [DATE_STOP_PLASMAPH] = @DATE_STOP_PLASMAPH, [NO_EXCH_PLASMAPH] = @NO_EXCH_PLASMAPH, [RESPONSE_TO_PLASMA] = @RESPONSE_TO_PLASMA WHERE [plID] = @plID"
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        With objCommand.Parameters

            .Add(New SqlParameter("@plID", lblpID.Text))
            .Add(New SqlParameter("@DATE_START_PLASMAPH", dtStart))
            If txtDateStopPlasmaEdit.Text = "" Then
                .Add(New SqlParameter("@DATE_STOP_PLASMAPH", DBNull.Value))
            Else
                .Add(New SqlParameter("@DATE_STOP_PLASMAPH", dtStop))
            End If

            .Add(New SqlParameter("@NO_EXCH_PLASMAPH", ddlPlasmaExchEdit.SelectedValue))
            .Add(New SqlParameter("@RESPONSE_TO_PLASMA", DropDownList3.SelectedValue))

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

        btnPlasmaAdd.Enabled = True
        pnlPlasmaEdit.Visible = False
        GridView3.DataBind()
        GridView3.Enabled = True
        'PageFill(Session("intRecord"))


    End Sub

    Function CheckDates2(ByVal intRADAR_NO As Integer, ByVal intType As Integer, ByVal dtStart As Date, ByVal dtEnd As Date, ByVal intRecord As Integer) As Boolean

        Dim strStartDate As String = CStr(Year(dtStart)) & "-" & CStr(Month(dtStart)) & "-" & CStr(Day(dtStart))
        Dim strEndDate As String = CStr(Year(dtEnd)) & "-" & CStr(Month(dtEnd)) & "-" & CStr(Day(dtEnd))


        dtStart = CDate(strStartDate)
        dtEnd = CDate(strEndDate)

        Dim strSQL As String

        Select Case intType

            Case 1  '# Dialysis

                strSQL = "SELECT tID, RADAR_NO FROM [tbl_RRT_TREATMENT] WHERE (((Cast(@START_DATE as smalldatetime) BETWEEN [DATE_START] AND [DATE_STOP]) OR (Cast(@END_DATE as smalldatetime) BETWEEN [DATE_START] AND [DATE_STOP] ) OR (Cast(@START_DATE as smalldatetime) <= [DATE_START] AND Cast(@END_DATE as smalldatetime) >= [DATE_STOP])) AND ([MODALITY] <= '19') AND [RADAR_NO] = @RADAR_NO)"
                'strSQL = "SELECT RADAR_NO FROM [tbl_RRT_TREATMENT] WHERE (('" & dtStart & "' BETWEEN [DATE_START] AND [DATE_STOP]) AND ([MODALITY] <= '19') AND ([RADAR_NO] = '" & intRADAR_NO & "'))"

            Case 3  '# Plasmapheresis

                'strSQL = "SELECT RADAR_NO FROM [tbl_RRT_PLASMA] WHERE (((Cast(@START_DATE as smalldatetime) BETWEEN [DATE_START_PLASMAPH] AND [DATE_STOP_PLASMAPH]) OR (Cast(@END_DATE as smalldatetime) BETWEEN [DATE_START_PLASMAPH] AND [DATE_STOP_PLASMAPH] ) OR (Cast(@START_DATE as smalldatetime) <= [DATE_START_PLASMAPH]) AND (Cast(@END_DATE as smalldatetime) >= [DATE_STOP_PLASMAPH])) AND [RADAR_NO] = @RADAR_NO AND [plID] <> @pID)"
                strSQL = "SELECT RADAR_NO FROM [tbl_RRT_PLASMA] WHERE (((Cast(@START_DATE As smalldatetime) BETWEEN [DATE_START_PLASMAPH] AND [DATE_STOP_PLASMAPH]) OR (Cast(@END_DATE as smalldatetime) BETWEEN [DATE_START_PLASMAPH] AND [DATE_STOP_PLASMAPH] ) OR ((cast(@START_DATE as smalldatetime) <= [DATE_START_PLASMAPH]) AND (Cast(@END_DATE as smalldatetime) >= [DATE_START_PLASMAPH]))) AND [RADAR_NO] = @RADAR_NO AND [plID] <> @pID)"

        End Select

        'lblDebug.Text = strSQL

        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RadarConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        Try

            With objCommand.Parameters

                .Add(New SqlParameter("@RADAR_NO", intRADAR_NO))
                .Add(New SqlParameter("@START_DATE", dtStart))
                .Add(New SqlParameter("@END_DATE", dtEnd))
                .Add(New SqlParameter("@pID", intRecord))


            End With


            Dim objDataReader As SqlDataReader
            objConnect.Open()
            objDataReader = objCommand.ExecuteReader()


            If objDataReader.Read() Then

                CheckDates2 = False
                'lblDebug.Text = "dates conflict:"

            Else

                CheckDates2 = True
                'lblDebug.Text = "dates don't conflict: " '& strSQL

            End If

            ' lblDebug.Text = strSQL

        Catch objError As Exception

            lblDebug.Text = String.Format("An error occurred: Function CheckDates. '{0}'{1}", objError.Message, strSQL)
            Exit Function

        Finally

            objConnect.Close()

        End Try


    End Function

    Protected Sub GridView3_RowDeleting(ByVal sender As Object, ByVal e As GridViewDeletedEventArgs)
        'lblWarnP.Text = ""
    End Sub

    Shared Function GetResponse(ByVal intResponse As Object) As String

        If intResponse Is DBNull.Value Then

            GetResponse = ""

        Else

            Select Case intResponse

                Case 1
                    GetResponse = "Complete"
                Case 2
                    GetResponse = "Partial"
                Case 3
                    GetResponse = "None"

                Case Else
                    GetResponse = ""
            End Select

        End If


    End Function

    Protected Sub btnPlasmaAdd_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnPlasmaAdd.Click

        If txtDATE_START_PLASMAPH.Text = "" Then
            txtDATE_START_PLASMAPH.BackColor = Drawing.Color.LemonChiffon
            Exit Sub
        Else
            txtDATE_START_PLASMAPH.BackColor = Drawing.Color.White
        End If

        'If txtDATE_STOP_PLASMAPH.Text = "" Then
        '    txtDATE_STOP_PLASMAPH.BackColor = Drawing.Color.LemonChiffon
        '    Exit Sub
        'Else
        '    txtDATE_STOP_PLASMAPH.BackColor = Drawing.Color.White
        'End If

        If ddlPlamsExch.SelectedValue = "" Then
            ddlPlamsExch.BackColor = Drawing.Color.LemonChiffon
            Exit Sub
        Else
            ddlPlamsExch.BackColor = Drawing.Color.White
        End If

        If ddlPlasResponse.SelectedIndex = 0 Then
            ddlPlasResponse.BackColor = Drawing.Color.LemonChiffon
            Exit Sub
        Else
            ddlPlasResponse.BackColor = Drawing.Color.White
        End If

        Dim dtDATE_START_PLASMAPH As Date = CDate(txtDATE_START_PLASMAPH.Text)
        'Dim dtDATE_STOP_PLASMAPH As Date = CDate(txtDATE_STOP_PLASMAPH.Text)
        Dim intNO_EXCH_PLASMAPH As Integer = ddlPlamsExch.SelectedValue
        Dim strPModality As String = "8P"


        If txtDATE_STOP_PLASMAPH.Text = "" Then
            'OK, stop date not known
        Else

            If IsDate(txtDATE_STOP_PLASMAPH.Text) Then

                If CDate(txtDATE_STOP_PLASMAPH.Text) > CDate(txtDateToday.Text) Then
                    lblStopWarnP.Text = "> Today"
                    lblWarnP.Text = ""
                    Exit Sub
                Else
                    lblStopWarnP.Text = ""
                End If

                If CDate(txtDATE_STOP_PLASMAPH.Text) < CDate(txtDATE_START_PLASMAPH.Text) Then
                    lblStopWarnP.Text = "< Start"
                    lblWarnP.Text = ""
                    Exit Sub
                Else
                    lblStopWarnP.Text = ""
                End If
            Else
                lblStopWarnP.Text = "Enter a date"
                lblWarnP.Text = ""
                Exit Sub
            End If

        End If

        Dim dtDATE_STOP_P As Date

        If txtDATE_STOP_PLASMAPH.Text = "" Then
            dtDATE_STOP_P = CDate(txtDATE_START_PLASMAPH.Text).AddDays(1)
        Else
            dtDATE_STOP_P = CDate(txtDATE_STOP_PLASMAPH.Text)

        End If

        If CheckDates(Session("patientID"), 3, dtDATE_START_PLASMAPH, dtDATE_STOP_P, 3) = False Then
            'lblWarnP.Text = "Dates conflict with treatments above"
            'lblDebug.Text = dtDATE_STOP_DIAL
            Exit Sub
        Else
            lblWarnP.Text = ""
        End If

        'Dim intDUR_PLASMAPH As Integer = (dtDATE_STOP_PLASMAPH - dtDATE_START_PLASMAPH).TotalDays

        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RadarConnectionString").ConnectionString
        Dim objConnect As New System.Data.SqlClient.SqlConnection(strConnect)
        Dim strSQL As String = "INSERT INTO tbl_RRT_PLASMA (RADAR_NO, PLASMAPH, DATE_START_PLASMAPH, DATE_STOP_PLASMAPH, NO_EXCH_PLASMAPH, RESPONSE_TO_PLASMA ) VALUES (@RADAR_NO, @PLASMAPH, @DATE_START_PLASMAPH, @DATE_STOP_PLASMAPH, @NO_EXCH_PLASMAPH, @RESPONSE_TO_PLASMA)"

        Dim objCommand As New System.Data.SqlClient.SqlCommand(strSQL, objConnect)
        Try
            With objCommand.Parameters

                .Add(New System.Data.SqlClient.SqlParameter("@RADAR_NO", Session("patientID")))
                .Add(New System.Data.SqlClient.SqlParameter("@PLASMAPH", strPModality))
                .Add(New System.Data.SqlClient.SqlParameter("@DATE_START_PLASMAPH", dtDATE_START_PLASMAPH))

                If txtDATE_STOP_PLASMAPH.Text = "" Then
                    .Add(New System.Data.SqlClient.SqlParameter("@DATE_STOP_PLASMAPH", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@DATE_STOP_PLASMAPH", CDate(txtDATE_STOP_PLASMAPH.Text)))
                End If

                .Add(New System.Data.SqlClient.SqlParameter("@NO_EXCH_PLASMAPH", intNO_EXCH_PLASMAPH))
                '.Add(New System.Data.SqlClient.SqlParameter("@DUR_PLASMAPH", intDUR_PLASMAPH))

                If ddlPlasResponse.SelectedValue = "" Then
                    .Add(New System.Data.SqlClient.SqlParameter("@RESPONSE_TO_PLASMA", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@RESPONSE_TO_PLASMA", ddlPlasResponse.SelectedValue))
                End If

            End With

            objConnect.Open()
            objCommand.ExecuteNonQuery()

        Catch objError As Exception

            lblDebug.Text = "An error occurred: '" & objError.Message & "'" & strSQL

        Finally
            objConnect.Close()
        End Try

        GridView3.DataBind()

        txtDATE_START_PLASMAPH.Text = ""
        txtDATE_STOP_PLASMAPH.Text = ""
        ddlPlamsExch.SelectedIndex = 0
        ddlPlasResponse.SelectedIndex = 0


    End Sub

    Function CheckDates(ByVal intRADAR_NO As Integer, ByVal intType As Integer, ByVal dtStart As Date, ByVal dtEnd As Date, ByVal intModality As Integer) As Boolean

        dtStart = CDate(dtStart)
        dtEnd = CDate(dtEnd)
        lblDebug.Text = ""

        Dim strStartDate As String = CStr(Year(dtStart)) & "-" & CStr(Month(dtStart)) & "-" & CStr(Day(dtStart))
        Dim strEndDate As String = CStr(Year(dtEnd)) & "-" & CStr(Month(dtEnd)) & "-" & CStr(Day(dtEnd))

        dtStart = CDate(strStartDate)
        dtEnd = CDate(strEndDate)

        Dim strSQL As String
        Dim strSQL2 As String
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RadarConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)

        'check for open plasma treatments

        Select Case intType

            Case 3 'plasmapheresis

                'strSQL2 = "SELECT [RADAR_NO] FROM [tbl_RRT_PLASMA] WHERE ((cast(@START_DATE as smalldatetime) >= [DATE_START_PLASMAPH] AND [DATE_STOP_PLASMAPH] IS NULL ) AND [RADAR_NO] = @RADAR_NO)"
                strSQL2 = "SELECT [RADAR_NO] FROM [tbl_RRT_PLASMA] WHERE ( ([DATE_START_PLASMAPH] <= (cast(@START_DATE as smalldatetime)) AND [DATE_STOP_PLASMAPH] IS NULL ) AND [RADAR_NO] = @RADAR_NO)"

        End Select

        Dim objCommand2 As New SqlCommand(strSQL2, objConnect)

        Try
            With objCommand2.Parameters

                .Add(New SqlParameter("@RADAR_NO", intRADAR_NO))
                .Add(New SqlParameter("@START_DATE", dtStart))
                .Add(New SqlParameter("@END_DATE", dtEnd))

            End With

            Dim objDataReader2 As SqlDataReader
            objConnect.Open()
            objDataReader2 = objCommand2.ExecuteReader()


            If objDataReader2.Read() Then
                CheckDates = False
                lblWarnP.Text = "Please close the previous treatment before adding a new one"
                objConnect.Close()
                Exit Function
            Else
                lblWarnP.Text = ""
                objConnect.Close()
            End If

        Catch ex As Exception
            lblDebug.Text = ex.Message
            objConnect.Close()
        End Try

        'Exit Function


        Select Case intType

            Case 1  ' Dialysis

                strSQL = "SELECT RADAR_NO FROM [tbl_RRT_TREATMENT] WHERE ((('" & strStartDate & "' BETWEEN [DATE_START] AND [DATE_STOP]) OR ('" & strEndDate & "' BETWEEN [DATE_START] AND [DATE_STOP] ) OR ('" & strStartDate & "' <= [DATE_START]) AND ('" & strEndDate & "' >= [DATE_STOP])) AND [RADAR_NO] = " & intRADAR_NO & ")"


            Case 3  ' Plasmapheresis

                'strSQL = "SELECT RADAR_NO FROM [tbl_RRT_PLASMA] WHERE (((cast(@START_DATE as smalldatetime) >= [DATE_START_PLASMAPH] AND cast(@START_DATE as smalldatetime) < [DATE_STOP_PLASMAPH]) OR (cast(@END_DATE as smalldatetime) > [DATE_START_PLASMAPH] AND cast(@END_DATE as smalldatetime)<= [DATE_STOP_PLASMAPH] ) OR (cast(@START_DATE as smalldatetime) <= [DATE_START_PLASMAPH]) AND (cast(@END_DATE as smalldatetime) >= [DATE_STOP_PLASMAPH])) AND [RADAR_NO] = @RADAR_NO)"
                strSQL = "SELECT RADAR_NO FROM [tbl_RRT_PLASMA] WHERE (((Cast(@START_DATE As smalldatetime) BETWEEN [DATE_START_PLASMAPH] AND [DATE_STOP_PLASMAPH]) OR (Cast(@END_DATE as smalldatetime) BETWEEN [DATE_START_PLASMAPH] AND [DATE_STOP_PLASMAPH] ) OR ((cast(@START_DATE as smalldatetime) <= [DATE_START_PLASMAPH]) AND (Cast(@END_DATE as smalldatetime) >= [DATE_START_PLASMAPH]))) AND [RADAR_NO] = @RADAR_NO)"

        End Select

        Dim objCommand As New SqlCommand(strSQL, objConnect)


        Try

            With objCommand.Parameters

                .Add(New SqlParameter("@RADAR_NO", intRADAR_NO))
                .Add(New SqlParameter("@START_DATE", dtStart))
                .Add(New SqlParameter("@END_DATE", dtEnd))

            End With

            Dim objDataReader As SqlDataReader
            objConnect.Open()
            objDataReader = objCommand.ExecuteReader()


            If objDataReader.Read() Then

                CheckDates = False
                'lblDebug.Text = "dates conflict: " & strSQL
                lblWarnP.Text = "Dates conflict with treatments above"
            Else

                CheckDates = True
                'lblDebug.Text = "dates don't conflict: " & strSQL
                lblWarnP.Text = ""
            End If

            ' lblDebug.Text = strSQL

        Catch objError As Exception

            lblDebug.Text = String.Format("An error occurred: Function 'CheckDates' '{0}'{1}", objError.Message, strSQL)
            objConnect.Close()
            Exit Function

        Finally

            objConnect.Close()

        End Try


    End Function

End Class
