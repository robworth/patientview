Imports System
Imports System.IO
Imports System.Data.SqlClient
Imports ConfigurationAlias = System.Configuration
Partial Class pathology

    Inherits System.Web.UI.Page

    Private strFileName1 As String
    Private strFileName2 As String
    Private strFileName3 As String
    Private strFileName4 As String
    Private strFileName5 As String
    Private strFolderPath As String = "pathology-images/"

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load


        If Not Session("blnAuthenticated") Then
            Response.Redirect("default.aspx")
        End If

        If Session("uType") = "p" Then
            btnUpdate.Visible = False
            btnUpdate2.Visible = False
            btnAdd.Visible = False
            CommonClass.DisableControls(tblMain)
            txtBX_DATE.Enabled = False
            txtBX_DATE_CalendarExtender.Enabled = False
            lnkTimeLines.Visible = False
        Else
            lnkRelapse.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkDemographics.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnk3Months.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkLabResults.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkDiagnosis.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            'lnkFollowupClinical.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            'lnkFollowupLab.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            'lnkFollowupTreatment.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            'lnkTherapy.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkHospital.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkTimeLines.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkPathology.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkTimeLines.Visible = False
        End If


        txtDateToday.Text = Format(DateTime.Today, "dd-MM-yyyy")

        If Not IsPostBack Then

            'rowRelapse.Visible = False
            'rowSpacer1.Visible = False
            'rowSpacer2.Visible = False

            'lblDebug.Text = Session("dtRecord").ToString

            '# pathology is a followup screen so seq numbers start at 0

            If Session("dtRecord").ToString <> "" Then
                Dim intSeqNo As Integer = GetSeqNo(Session("dtRecord"))
                'lblDebug.Text = "SEQ = " & intSeqNo.ToString
                'lblDebug.Text = Session("dtRecord")
                'If intSeqNo = 0 Then
                'do nothing
                'lblDebug.Text = "Date=" & Session("dtRecord").ToString
                'Else
                If intSeqNo = 99 Then
                    pnlAdd.Visible = False
                Else
                    pageFill(intSeqNo)
                    DropDownListBXDate.SelectedIndex = 0
                    btnSaveNew.Visible = False
                    btnSaveNew2.Visible = False
                    btnUpdate.Visible = True
                    btnUpdate2.Visible = True
                    pnlAdd.Visible = True

                End If

                'End If
            Else
                'lblDebug.Text = "no date"
            End If

        End If


    End Sub

    Protected Sub btnAdd_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnAdd.Click
        'get basic data for new entries

        Dim TDES As New TripleDES()

        Dim patientID As Integer = Session("patientID")
        Dim strConnect As String = ConfigurationAlias.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim strSQL2 As String = "SELECT tbl_Diagnosis.DIAG, tbl_Demographics.SNAME, tbl_Demographics.FNAME, tbl_Demographics.DOB, tbl_Demographics.RADAR_NO, tbl_Demographics.HOSP_NO FROM tbl_Demographics INNER JOIN tbl_Diagnosis ON tbl_Demographics.RADAR_NO = tbl_Diagnosis.RADAR_NO WHERE (tbl_Demographics.RADAR_NO = '" & patientID & "')"
        Using objConnect2 As New SqlConnection(strConnect)
            Using objCommand2 As New SqlCommand(strSQL2, objConnect2)
                Try
                    Dim objDataReader As SqlDataReader
                    objConnect2.Open()
                    objDataReader = objCommand2.ExecuteReader()
                    If objDataReader.Read() Then
                        txtRADAR_NO.Text = objDataReader("RADAR_NO")
                        txtHOSP_NO.Text = TDES.Decrypt(objDataReader("HOSP_NO"))
                        lblFirstName.Text = TDES.Decrypt(objDataReader("FNAME"))
                        lblSurname.Text = TDES.Decrypt(objDataReader("SNAME"))
                        Dim dt As Date = CDate(TDES.Decrypt(objDataReader("DOB")))
                        lblDOB.Text = Format(dt, "dd-MMM-yyyy")
                        If objDataReader("DIAG") Is DBNull.Value Then
                            txtDIAGNOSIS.Text = "-"
                        Else
                            txtDIAGNOSIS.Text = CommonClass.GetDiagnosis(objDataReader("DIAG"))
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


        '#reset the form fields
        CommonClass.EmptyTexBoxes(tblMain)
        CommonClass.ClearRadioButtons(tblMain)

        img1.Visible = False
        img2.Visible = False
        img3.Visible = False
        img4.Visible = False
        img5.Visible = False
        lblImg1.Visible = True
        lblImg2.Visible = True
        lblImg3.Visible = True
        lblImg4.Visible = True
        lblImg5.Visible = True
        lblUpdate.Text = ""
        img1.HRef = ""
        img2.HRef = ""
        img3.HRef = ""
        img4.HRef = ""
        img5.HRef = ""
        txtImage1.Visible = True
        txtImage2.Visible = True
        txtImage3.Visible = True
        txtImage4.Visible = True
        txtImage5.Visible = True

        pnlAdd.Visible = True
        btnSaveNew.Visible = True
        btnSaveNew2.Visible = True
        btnUpdate.Visible = False
        btnUpdate2.Visible = False

        txtSEQ_NO.Text = GetNextRecord()
        txtBX_DATE.Text = ""
        DropDownListBXDate.SelectedIndex = 0

        If txtDIAGNOSIS.Text = "MPGN/DDD" Then
            lblPage.Text = "11A"
        ElseIf txtDIAGNOSIS.Text = "SRNS" Then
            lblPage.Text = "11B"
        End If


    End Sub

    Function GetNextRecord() As Integer

        Dim strConnect As String = ConfigurationAlias.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim strSQL As String = "SELECT MAX(SEQ_NO) AS LastSEQ_NO FROM [tbl_Pathology] WHERE [RADAR_NO] = " & Session("patientID")
        Using objConnect As New SqlConnection(strConnect)
            Using objCommand As New SqlCommand(strSQL, objConnect)
                Try
                    Dim objDataReader As SqlDataReader
                    objConnect.Open()
                    objDataReader = objCommand.ExecuteReader()
                    If objDataReader.Read() Then
                        If objDataReader("LastSEQ_NO") IsNot DBNull.Value Then
                            GetNextRecord = CInt(objDataReader("LastSEQ_NO")) + 1
                        End If

                        'lblDebug.Text = strSQL
                    Else
                        GetNextRecord = 0

                    End If
                Catch objError As Exception
                    lblDebug.Text = String.Format("An error occurred: '{0}'{1}", objError.Message, strSQL)
                End Try
            End Using
        End Using



    End Function


    Sub pageFill(ByVal intSeqNo As Integer)

        Dim TDES As New TripleDES

        '# clear the image urls in case they are not changed when hidden

        img1.HRef = ""
        img2.HRef = ""
        img3.HRef = ""
        img4.HRef = ""
        img5.HRef = ""


        'DropDownListBXDate.DataBind()
        'DropDownListBXDate.SelectedIndex = 0
        'lblUpdate.Text = "pagefill"
        lblDebug.Text = ""
        Dim strSQL As String = "SELECT [pID], [tbl_Pathology].[RADAR_NO], tbl_Demographics.HOSP_NO, tbl_Demographics.SNAME, tbl_Demographics.FNAME, tbl_Demographics.DOB, tbl_Diagnosis.DIAG,  [BX_DATE], [NAT_TRANSP_KID], [LATERALITY_BX], [SAMPLE_LAB_NO], [PATHDIAG], [GLOM_TOTAL_NO], [GLOM_GLOB_SCL], [GLOM_SEQ_SCL], [GLOM_CELL_CRES], [GLOM_FIB_CRES], [GLOM_FIN_NEC], [GLOM_END_HYPER], [GLOM_ANY_OTH_FEAT], [TUB_ATROP_IF_EST], [TUB_ATROP_IF_MEAS], [TUB_OTHER_FEAT], [INTER_INFLAM_INFIL], [ART_ABNORMAL], [IMM_HIST_FIND], [ELECT_MSCOPE_FIND], [IMAGE_URL1], [IMAGE_URL2], [IMAGE_URL3], [IMAGE_URL4],  [IMAGE_URL5], [PATH_TXT], [SEQ_NO] FROM [tbl_Pathology] INNER JOIN tbl_Demographics ON tbl_Pathology.RADAR_NO = tbl_Demographics.RADAR_NO INNER JOIN tbl_Diagnosis ON tbl_Demographics.RADAR_NO = tbl_Diagnosis.RADAR_NO  WHERE [tbl_Pathology].[RADAR_NO] = " & Session("patientID") & " AND [SEQ_NO] = " & intSeqNo
        'Dim strSQL As String = "SELECT * FROM [tbl_Pathology] WHERE [RADAR_NO] = " & Session("patientID") & " AND [SEQ_NO] = " & intSeqNo
        Dim strConnect As String = ConfigurationAlias.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Using objConnect As New SqlConnection(strConnect)
            Using objCommand As New SqlCommand(strSQL, objConnect)
                Try
                    Dim objDataReader As SqlDataReader
                    objConnect.Open()
                    objDataReader = objCommand.ExecuteReader()
                    If objDataReader.Read() Then

                        txtRADAR_NO.Text = objDataReader("RADAR_NO")
                        txtHOSP_NO.Text = TDES.Decrypt(objDataReader("HOSP_NO"))
                        lblSurname.Text = TDES.Decrypt(objDataReader("SNAME"))
                        lblFirstName.Text = TDES.Decrypt(objDataReader("FNAME"))
                        Dim dt As Date = CDate(TDES.Decrypt(objDataReader("DOB")))
                        lblDOB.Text = Format(dt, "dd-MMM-yyyy")
                        lblPid.Text = objDataReader("pID")
                        txtSEQ_NO.Text = objDataReader("SEQ_NO")

                        If objDataReader("DIAG") Is DBNull.Value Then
                            txtDIAGNOSIS.Text = "-"
                        Else
                            txtDIAGNOSIS.Text = CommonClass.GetDiagnosis(objDataReader("DIAG"))
                        End If

                        If objDataReader("DIAG") = 1 Then
                            lblPage.Text = "11B"
                        ElseIf objDataReader("DIAG") = 2 Then
                            lblPage.Text = "11A"
                        End If

                        If objDataReader("NAT_TRANSP_KID") Is DBNull.Value Then
                            'do nothing
                        ElseIf objDataReader("NAT_TRANSP_KID") = 1 Then
                            radTX.Checked = True
                        ElseIf objDataReader("NAT_TRANSP_KID") = 0 Then
                            radNat.Checked = True
                        End If

                        If objDataReader("LATERALITY_BX") Is DBNull.Value Then
                            'do nothing
                        ElseIf objDataReader("LATERALITY_BX") = 1 Then
                            radLeft.Checked = True
                        ElseIf objDataReader("LATERALITY_BX") = 2 Then
                            radRight.Checked = True
                        End If

                        txtBX_DATE.Text = Format(objDataReader("BX_DATE"), "dd/MM/yyyy")
                        txtINTER_INFLAM_INFIL.Text = objDataReader("INTER_INFLAM_INFIL").ToString
                        txtART_ABNORMAL.Text = objDataReader("ART_ABNORMAL").ToString
                        txtIMM_HIST_FIND.Text = objDataReader("IMM_HIST_FIND").ToString
                        txtELECT_MSCOPE_FIND.Text = objDataReader("ELECT_MSCOPE_FIND").ToString
                        txtEstimated.Text = objDataReader("TUB_ATROP_IF_EST").ToString
                        txtMeasured.Text = objDataReader("TUB_ATROP_IF_MEAS").ToString
                        txtTUB_OTHER_FEAT.Text = objDataReader("TUB_OTHER_FEAT").ToString
                        txtGLOM_TOTAL_NO.Text = objDataReader("GLOM_TOTAL_NO").ToString
                        txtGLOM_GLOB_SCL.Text = objDataReader("GLOM_GLOB_SCL").ToString
                        txtGLOM_SEQ_SCL.Text = objDataReader("GLOM_SEQ_SCL").ToString
                        txtGLOM_CELL_CRES.Text = objDataReader("GLOM_CELL_CRES").ToString
                        txtGLOM_FIB_CRES.Text = objDataReader("GLOM_FIB_CRES").ToString
                        txtGLOM_END_HYPER.Text = objDataReader("GLOM_END_HYPER").ToString
                        txtGLOM_FIN_NEC.Text = objDataReader("GLOM_FIN_NEC").ToString
                        txtGLOM_ANY_OTH_FEAT.Text = objDataReader("GLOM_ANY_OTH_FEAT").ToString
                        txtPATH_TEXT.Text = objDataReader("PATH_TXT").ToString
                        txtSAMPLE_LAB_NO.Text = objDataReader("SAMPLE_LAB_NO").ToString

                        If objDataReader("IMAGE_URL1") IsNot DBNull.Value Then
                            img1.HRef = objDataReader("IMAGE_URL1")
                            img1.Visible = True
                            lblImg1.Visible = False
                            txtImage1.Text = objDataReader("IMAGE_URL1")
                            txtImage1.Visible = False
                        Else
                            img1.Visible = False
                            txtImage1.Visible = True
                            lblImg1.Visible = True
                        End If

                        If objDataReader("IMAGE_URL2") IsNot DBNull.Value Then
                            img2.HRef = objDataReader("IMAGE_URL2")
                            img2.Visible = True
                            lblImg2.Visible = False
                            txtImage2.Text = objDataReader("IMAGE_URL2")
                            txtImage2.Visible = False

                        Else
                            img2.Visible = False
                            txtImage2.Visible = True
                            lblImg2.Visible = True
                        End If

                        If objDataReader("IMAGE_URL3") IsNot DBNull.Value Then
                            img3.HRef = objDataReader("IMAGE_URL3")
                            img3.Visible = True
                            lblImg3.Visible = False
                            txtImage3.Text = objDataReader("IMAGE_URL3")
                            txtImage3.Visible = False
                        Else
                            img3.Visible = False
                            txtImage3.Visible = True
                            lblImg3.Visible = True
                        End If

                        If objDataReader("IMAGE_URL4") IsNot DBNull.Value Then
                            img4.HRef = objDataReader("IMAGE_URL4")
                            img4.Visible = True
                            lblImg4.Visible = False
                            txtImage4.Text = objDataReader("IMAGE_URL4")
                            txtImage4.Visible = False
                        Else
                            img4.Visible = False
                            txtImage4.Visible = True
                            lblImg4.Visible = True
                        End If

                        If objDataReader("IMAGE_URL5") IsNot DBNull.Value Then
                            img5.HRef = objDataReader("IMAGE_URL5")
                            img5.Visible = True
                            lblImg5.Visible = False
                            txtImage5.Text = objDataReader("IMAGE_URL5")
                            txtImage5.Visible = False
                        Else
                            img5.Visible = False
                            txtImage5.Visible = True
                            lblImg5.Visible = True
                        End If


                        'lblDebug.Text = strSQL
                    Else
                        'hide panel if no data read
                        pnlAdd.Visible = False
                    End If

                Catch objError As Exception
                    lblDebug.Text = objError.Message & "(PageFill(); " & strSQL
                    Exit Sub

                End Try
            End Using
        End Using

       


    End Sub


    Function GetSeqNo(ByVal dtEntry As Date) As Integer

        'Dim strSQL As String = "SELECT [SEQ_NO] FROM [tbl_Pathology] WHERE ([RADAR_NO] = '" & Session("patientID") & "' AND [BX_DATE] = (SELECT MAX([BX_DATE]) FROM [tbl_Pathology] WHERE [RADAR_NO] = '" & Session("patientID") & "'))"
        'Dim strSQL As String = "SELECT [SEQ_NO] FROM [tbl_Pathology] WHERE ([RADAR_NO] = '" & Session("patientID") & "' AND Dateadd(d, datediff(d,0,[BX_DATE]),0) = '" & dtEntry & "')"
        'Dim strSQL As String = "SELECT [SEQ_NO] FROM [tbl_Pathology] WHERE ([RADAR_NO] = '" & Session("patientID") & "' AND [BX_DATE] = '" & dtEntry & "')"

        Dim strSQL As String = "SELECT [SEQ_NO] FROM [tbl_Pathology] WHERE ([RADAR_NO] = '" & Session("patientID") & "' AND [BX_DATE] = Convert(DATETIME, '" & dtEntry & "',103))"


        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Using objConnect As New SqlConnection(strConnect)
            Dim objCommand As New SqlCommand(strSQL, objConnect)
            Try
                Dim objDataReader As SqlDataReader
                objConnect.Open()
                objDataReader = objCommand.ExecuteReader()
                If objDataReader.Read() Then
                    GetSeqNo = objDataReader("SEQ_NO")
                    'lblDebug.Text = objDataReader("SEQ_NO")
                    'lblDebug.Text = dtEntry.ToString

                Else
                    GetSeqNo = 99
                    'lblDebug.Text = strSQL
                End If

            Catch objError As Exception
                lblDebug.Text = String.Format("An error occurred: '{0}'{1}", objError.Message, strSQL)
                GetSeqNo = 0
            Finally
                objConnect.Close()
            End Try
        End Using

        'lblDebug.Text = dtEntry.ToString
        'GetSeqNo = 0

    End Function


    Protected Sub btnSaveNew_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnSaveNew.Click
        saveNew()
    End Sub

    Sub saveNew()

        btnUpdate.Visible = False
        btnUpdate2.Visible = False

        Dim intRadarNo As Integer = CInt(txtRADAR_NO.Text)

        'check for existing record on the same date

        Dim dtBx As Date = CDate(txtBX_DATE.Text)
        lblDebug.Text = ""

        If chkDate(dtBx) = False Then
            'do nothing
        Else
            lblUpdate.Text = "<span style='color:red;'>A record already exists for this date.</span>"
            Exit Sub
        End If

        Dim iSizeLimit As Integer = 5242880  '5Mb



        strFileName1 = txtImage1.Text
        strFileName2 = txtImage2.Text
        strFileName3 = txtImage3.Text
        strFileName4 = txtImage4.Text
        strFileName5 = txtImage5.Text

        Dim strSQL As String = "INSERT INTO [tbl_Pathology] ([RADAR_NO], [BX_DATE], [NAT_TRANSP_KID], [LATERALITY_BX], [SAMPLE_LAB_NO], [PATHDIAG], [GLOM_TOTAL_NO], [GLOM_GLOB_SCL], [GLOM_SEQ_SCL], [GLOM_CELL_CRES], [GLOM_FIB_CRES], [GLOM_END_HYPER], [GLOM_FIN_NEC], [GLOM_ANY_OTH_FEAT], [TUB_ATROP_IF_EST], [TUB_ATROP_IF_MEAS], [TUB_OTHER_FEAT], [INTER_INFLAM_INFIL], [ART_ABNORMAL], [IMM_HIST_FIND], [ELECT_MSCOPE_FIND], [IMAGE_URL1], [IMAGE_URL2], [IMAGE_URL3], [IMAGE_URL4], [IMAGE_URL5], [PATH_TXT], [SEQ_NO]) VALUES (@RADAR_NO, @BX_DATE, @NAT_TRANSP_KID, @LATERALITY_BX, @SAMPLE_LAB_NO, @PATHDIAG, @GLOM_TOTAL_NO, @GLOM_GLOB_SCL, @GLOM_SEQ_SCL, @GLOM_CELL_CRES, @GLOM_FIB_CRES, @GLOM_END_HYPER, @GLOM_FIN_NEC, @GLOM_ANY_OTH_FEAT, @TUB_ATROP_IF_EST, @TUB_ATROP_IF_MEAS, @TUB_OTHER_FEAT, @INTER_INFLAM_INFIL, @ART_ABNORMAL, @IMM_HIST_FIND, @ELECT_MSCOPE_FIND, @IMAGE_URL1, @IMAGE_URL2, @IMAGE_URL3, @IMAGE_URL4, @IMAGE_URL5, @PATH_TXT, @SEQ_NO)"
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        With objCommand.Parameters

            .Add(New SqlParameter("@SEQ_NO", txtSEQ_NO.Text))
            .Add(New SqlParameter("@RADAR_NO", txtRADAR_NO.Text))
            .Add(New SqlParameter("@BX_DATE", CDate(txtBX_DATE.Text)))

            If radTX.Checked = True Then
                .Add(New SqlParameter("@NAT_TRANSP_KID", 1))
            ElseIf radNat.Checked = True Then
                .Add(New SqlParameter("@NAT_TRANSP_KID", 0))
            Else
                .Add(New SqlParameter("@NAT_TRANSP_KID", DBNull.Value))
            End If

            If radLeft.Checked = True Then
                .Add(New SqlParameter("@LATERALITY_BX", 1))
            ElseIf radRight.Checked = True Then
                .Add(New SqlParameter("@LATERALITY_BX", 2))
            Else
                .Add(New SqlParameter("@LATERALITY_BX", DBNull.Value))
            End If

            If txtSAMPLE_LAB_NO.Text = "" Then
                .Add(New SqlParameter("@SAMPLE_LAB_NO", DBNull.Value))
            Else
                .Add(New SqlParameter("@SAMPLE_LAB_NO", txtSAMPLE_LAB_NO.Text))
            End If

            'this to be changed
            .Add(New SqlParameter("@PATHDIAG", 2))

            .Add(New SqlParameter("@GLOM_TOTAL_NO", CommonClass.chkNullIntSave(txtGLOM_TOTAL_NO.Text)))
            .Add(New SqlParameter("@GLOM_GLOB_SCL", CommonClass.chkNullIntSave(txtGLOM_GLOB_SCL.Text)))
            .Add(New SqlParameter("@GLOM_SEQ_SCL", CommonClass.chkNullIntSave(txtGLOM_SEQ_SCL.Text)))
            .Add(New SqlParameter("@GLOM_CELL_CRES", CommonClass.chkNullIntSave(txtGLOM_CELL_CRES.Text)))
            .Add(New SqlParameter("@GLOM_FIB_CRES", CommonClass.chkNullIntSave(txtGLOM_FIB_CRES.Text)))
            .Add(New SqlParameter("@GLOM_END_HYPER", CommonClass.chkNullIntSave(txtGLOM_END_HYPER.Text)))
            .Add(New SqlParameter("@GLOM_FIN_NEC", CommonClass.chkNullIntSave(txtGLOM_FIN_NEC.Text)))

            .Add(New SqlParameter("@GLOM_ANY_OTH_FEAT", CommonClass.chkNullSave(txtGLOM_ANY_OTH_FEAT.Text)))
            .Add(New SqlParameter("@TUB_ATROP_IF_EST", CommonClass.chkNullIntSave(txtEstimated.Text)))
            .Add(New SqlParameter("@TUB_ATROP_IF_MEAS", CommonClass.chkNullIntSave(txtMeasured.Text)))
            .Add(New SqlParameter("@TUB_OTHER_FEAT", CommonClass.chkNullSave(txtTUB_OTHER_FEAT.Text)))
            .Add(New SqlParameter("@INTER_INFLAM_INFIL", CommonClass.chkNullSave(txtINTER_INFLAM_INFIL.Text)))
            .Add(New SqlParameter("@ART_ABNORMAL", CommonClass.chkNullSave(txtART_ABNORMAL.Text)))
            .Add(New SqlParameter("@IMM_HIST_FIND", CommonClass.chkNullSave(txtIMM_HIST_FIND.Text)))
            .Add(New SqlParameter("@ELECT_MSCOPE_FIND", CommonClass.chkNullSave(txtELECT_MSCOPE_FIND.Text)))
            .Add(New SqlParameter("@IMAGE_URL1", IIf(strFileName1 = "", DBNull.Value, strFileName1)))
            .Add(New SqlParameter("@IMAGE_URL2", IIf(strFileName2 = "", DBNull.Value, strFileName2)))
            .Add(New SqlParameter("@IMAGE_URL3", IIf(strFileName3 = "", DBNull.Value, strFileName3)))
            .Add(New SqlParameter("@IMAGE_URL4", IIf(strFileName4 = "", DBNull.Value, strFileName4)))
            .Add(New SqlParameter("@IMAGE_URL5", IIf(strFileName5 = "", DBNull.Value, strFileName5)))
            .Add(New SqlParameter("@PATH_TXT", CommonClass.chkNullSave(txtPATH_TEXT.Text)))



        End With

        Try

            objConnect.Open()
            objCommand.ExecuteNonQuery()

        Catch objError As Exception

            lblDebug.Text = String.Format("An error occurred: '{0}'{1}", objError.Message, strSQL)
            objConnect.Close()
            Exit Sub

        Finally

            objConnect.Close()

        End Try

        lblUpdate.Text = String.Format("<strong> Update successful:</strong>&nbsp;&nbsp;{0}", Now().ToShortTimeString)
        lblUpdate2.Text = String.Format("<strong> Update successful:</strong>&nbsp;&nbsp;{0}", Now().ToShortTimeString)

        btnSaveNew.Visible = False
        btnSaveNew2.Visible = False
        btnUpdate.Visible = True
        btnUpdate2.Visible = True
        ddlRefresh(DropDownListBXDate)
        pageFill(txtSEQ_NO.Text)


    End Sub


    Protected Sub DropDownListBXDate_SelectedIndexChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles DropDownListBXDate.SelectedIndexChanged

        Session("intRecord") = DropDownListBXDate.SelectedValue
        Session("dtRecord") = CDate((DropDownListBXDate.SelectedItem).ToString)
        pnlAdd.Visible = True

        pageFill(Session("intRecord"))

        DropDownListBXDate.SelectedIndex = 0
        lblDebug.Text = ""
        lblUpdate.Text = ""
        btnSaveNew.Visible = False
        btnSaveNew2.Visible = False
        btnUpdate.Visible = True
        btnUpdate2.Visible = True

    End Sub

    Sub ddlRefresh(ByVal ddl As DropDownList)

        ddl.Items.Clear()
        'ddl.DataBind()

        Dim li As New System.Web.UI.WebControls.ListItem

        li.Value = ""
        li.Text = "Select"
        ddl.Items.Insert(0, li)   'Add(li)
        ddl.AppendDataBoundItems = True

    End Sub

    Function chkDate(ByVal dt As Date) As Boolean

        Dim strSQL As String = "SELECT [RADAR_NO] FROM [tbl_Pathology] WHERE [BX_DATE] = Convert(DATETIME, '" & dt & "',103)"
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Using objConnect As New SqlConnection(strConnect)
            Using objCommand As New SqlCommand(strSQL, objConnect)
                Try
                    Dim objDataReader As SqlDataReader
                    objConnect.Open()
                    objDataReader = objCommand.ExecuteReader()
                    If objDataReader.Read() Then
                        chkDate = True
                    Else
                        chkDate = False
                    End If
                Catch objError As Exception
                    lblDebug.Text = String.Format("An error occurred: '{0}'{1}", objError.Message, strSQL)
                    chkDate = False
                Finally
                    objConnect.Close()
                End Try
            End Using
        End Using


    End Function

    Protected Sub btnUpdate_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnUpdate.Click
        update()
    End Sub

    Sub update()
        Dim iSizeLimit As Integer = 5242880  '5Mb
        Dim intRadarNo As Integer = CInt(txtRADAR_NO.Text)


        strFileName1 = txtImage1.Text
        strFileName2 = txtImage2.Text
        strFileName3 = txtImage3.Text
        strFileName4 = txtImage4.Text
        strFileName5 = txtImage5.Text

        Dim strSQL As String = "UPDATE [tbl_Pathology] SET [RADAR_NO] = @RADAR_NO, [BX_DATE] = @BX_DATE, [NAT_TRANSP_KID] = @NAT_TRANSP_KID, [LATERALITY_BX] = @LATERALITY_BX, [SAMPLE_LAB_NO] = @SAMPLE_LAB_NO, [PATHDIAG] = @PATHDIAG, [GLOM_TOTAL_NO] = @GLOM_TOTAL_NO, [GLOM_GLOB_SCL] = @GLOM_GLOB_SCL, [GLOM_SEQ_SCL] = @GLOM_SEQ_SCL, [GLOM_CELL_CRES] = @GLOM_CELL_CRES, [GLOM_FIB_CRES] = @GLOM_FIB_CRES, [GLOM_ANY_OTH_FEAT] = @GLOM_ANY_OTH_FEAT, [TUB_ATROP_IF_EST] = @TUB_ATROP_IF_EST, [TUB_ATROP_IF_MEAS] = @TUB_ATROP_IF_MEAS, [TUB_OTHER_FEAT] = @TUB_OTHER_FEAT, [INTER_INFLAM_INFIL] = @INTER_INFLAM_INFIL, [ART_ABNORMAL] = @ART_ABNORMAL, [IMM_HIST_FIND] = @IMM_HIST_FIND, [ELECT_MSCOPE_FIND] = @ELECT_MSCOPE_FIND, [IMAGE_URL1] = @IMAGE_URL1, [IMAGE_URL2] = @IMAGE_URL2, [IMAGE_URL3] = @IMAGE_URL3, [IMAGE_URL4] = @IMAGE_URL4, [IMAGE_URL5] = @IMAGE_URL5, [PATH_TXT] = @PATH_TXT, [GLOM_FIN_NEC] = @GLOM_FIN_NEC, [GLOM_END_HYPER] = @GLOM_END_HYPER WHERE [pID] = @pID"
        Dim strConnect As String = ConfigurationAlias.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        With objCommand.Parameters

            .Add(New SqlParameter("@pID", CInt(lblPid.Text)))

            .Add(New SqlParameter("@RADAR_NO", txtRADAR_NO.Text))
            .Add(New SqlParameter("@BX_DATE", CDate(txtBX_DATE.Text)))

            If radTX.Checked = True Then
                .Add(New SqlParameter("@NAT_TRANSP_KID", 1))
            ElseIf radNat.Checked = True Then
                .Add(New SqlParameter("@NAT_TRANSP_KID", 0))
            Else
                .Add(New SqlParameter("@NAT_TRANSP_KID", DBNull.Value))
            End If

            If txtSAMPLE_LAB_NO.Text = "" Then
                .Add(New SqlParameter("@SAMPLE_LAB_NO", DBNull.Value))
            Else
                .Add(New SqlParameter("@SAMPLE_LAB_NO", txtSAMPLE_LAB_NO.Text))
            End If

            If radLeft.Checked = True Then
                .Add(New SqlParameter("@LATERALITY_BX", 1))
            ElseIf radRight.Checked = True Then
                .Add(New SqlParameter("@LATERALITY_BX", 2))
            Else
                .Add(New SqlParameter("@LATERALITY_BX", DBNull.Value))
            End If

            'this to be changed
            .Add(New SqlParameter("@PATHDIAG", 2))

            .Add(New SqlParameter("@GLOM_TOTAL_NO", CommonClass.chkNullIntSave(txtGLOM_TOTAL_NO.Text)))
            .Add(New SqlParameter("@GLOM_GLOB_SCL", CommonClass.chkNullIntSave(txtGLOM_GLOB_SCL.Text)))
            .Add(New SqlParameter("@GLOM_SEQ_SCL", CommonClass.chkNullIntSave(txtGLOM_SEQ_SCL.Text)))
            .Add(New SqlParameter("@GLOM_CELL_CRES", CommonClass.chkNullIntSave(txtGLOM_CELL_CRES.Text)))
            .Add(New SqlParameter("@GLOM_FIB_CRES", CommonClass.chkNullIntSave(txtGLOM_FIB_CRES.Text)))
            .Add(New SqlParameter("@GLOM_END_HYPER", CommonClass.chkNullIntSave(txtGLOM_END_HYPER.Text)))
            .Add(New SqlParameter("@GLOM_FIN_NEC", CommonClass.chkNullIntSave(txtGLOM_FIN_NEC.Text)))

            .Add(New SqlParameter("@GLOM_ANY_OTH_FEAT", CommonClass.chkNullSave(txtGLOM_ANY_OTH_FEAT.Text)))
            .Add(New SqlParameter("@TUB_ATROP_IF_EST", CommonClass.chkNullIntSave(txtEstimated.Text)))
            .Add(New SqlParameter("@TUB_ATROP_IF_MEAS", CommonClass.chkNullIntSave(txtMeasured.Text)))
            .Add(New SqlParameter("@TUB_OTHER_FEAT", CommonClass.chkNullSave(txtTUB_OTHER_FEAT.Text)))
            .Add(New SqlParameter("@INTER_INFLAM_INFIL", CommonClass.chkNullSave(txtINTER_INFLAM_INFIL.Text)))
            .Add(New SqlParameter("@ART_ABNORMAL", CommonClass.chkNullSave(txtART_ABNORMAL.Text)))
            .Add(New SqlParameter("@IMM_HIST_FIND", CommonClass.chkNullSave(txtIMM_HIST_FIND.Text)))
            .Add(New SqlParameter("@ELECT_MSCOPE_FIND", CommonClass.chkNullSave(txtELECT_MSCOPE_FIND.Text)))
            .Add(New SqlParameter("@IMAGE_URL1", IIf(strFileName1 = "", DBNull.Value, strFileName1)))
            .Add(New SqlParameter("@IMAGE_URL2", IIf(strFileName2 = "", DBNull.Value, strFileName2)))
            .Add(New SqlParameter("@IMAGE_URL3", IIf(strFileName3 = "", DBNull.Value, strFileName3)))
            .Add(New SqlParameter("@IMAGE_URL4", IIf(strFileName4 = "", DBNull.Value, strFileName4)))
            .Add(New SqlParameter("@IMAGE_URL5", IIf(strFileName5 = "", DBNull.Value, strFileName5)))
            .Add(New SqlParameter("@PATH_TXT", CommonClass.chkNullSave(txtPATH_TEXT.Text)))

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

        lblUpdate.Text = " <strong>Update successful:</strong>&nbsp;&nbsp;" & Now().ToShortTimeString
        lblUpdate2.Text = " <strong>Update successful:</strong>&nbsp;&nbsp;" & Now().ToShortTimeString

        'lblDebug.Text = txtSEQ_NO.Text
        pageFill(txtSEQ_NO.Text)

    End Sub

    Protected Sub btnSaveNew2_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnSaveNew2.Click
        saveNew()
    End Sub

    Protected Sub btnUpdate2_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnUpdate2.Click
        update()
    End Sub
End Class
