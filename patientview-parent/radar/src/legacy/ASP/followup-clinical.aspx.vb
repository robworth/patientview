Imports System.Data.SqlClient
Imports ConfigurationAlias = System.Configuration
Partial Class followup_clinical
    Inherits System.Web.UI.Page
    Public intDiag As Integer

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load

        If Not Session("blnAuthenticated") Then
            Response.Redirect("default.aspx")
        End If

        If Session("diag") = 1 Then
            lblPage.Text = "6B"
        ElseIf Session("diag") = 2 Then
            lblPage.Text = "6A"
        End If

        If Session("uType") = "p" Then
            btnUpdate.Visible = False
            btnAdd.Visible = False
            CommonClass.DisableControls(tblMain)
            txtDateClinicalPic.Enabled = False
            txtDateClinicalPic_CalendarExtender.Enabled = False
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

        'ASPxDateEdit1.MaxDate = DateTime.Today

        txtDateToday.Text = Format(DateTime.Today, "dd-MM-yyyy")

        'PageFill()

        If Not IsPostBack Then

            'rowRelapse.Visible = False
            'rowSpacer1.Visible = False
            'rowSpacer2.Visible = False

            If Session("dtRecord").ToString <> "" Then
                Dim intSeqNo As Integer = GetSeqNo(Session("dtRecord"))
                'lblDebug.Text = intSeqNo.ToString
                If intSeqNo = 0 Then
                    'do nothing
                    'lblDebug.Text = "Date=" & Session("dtRecord").ToString
                Else
                    PageFill(intSeqNo)
                    DropDownList10.SelectedIndex = 0
                    btnSaveNew.Visible = False
                    btnUpdate.Visible = True
                    pnlAdd.Visible = True
                End If
            Else
                lblDebug.Text = "no date"
            End If

        End If


    End Sub


    Sub pagefill(ByVal SEQ_NO As Integer)

        '# reset debug message
        lblDebug.Text = ""

        '# need to bind these before we select a value
        DropDownList6.DataBind()
        DropDownList7.DataBind()
        DropDownList8.DataBind()
        DropDownList9.DataBind()



        Dim TDES As New TripleDES()

        Dim patientID As Integer = Session("patientID")
        txtSEQ_NO.Text = SEQ_NO

        Dim strSQL As String = "SELECT tbl_ClinicalData.cID, tbl_ClinicalData.RADAR_NO, tbl_ClinicalData.DATE_CLIN_PIC, tbl_ClinicalData.PREC_INF, tbl_ClinicalData.PREC_INF_DETAIL, tbl_ClinicalData.CLIN_EV_CHR_INF, tbl_ClinicalData.CLIN_EV_CHR_INF_DETAIL, tbl_ClinicalData.PHENOTYPE1, tbl_ClinicalData.PHENOTYPE2, tbl_ClinicalData.PHENOTYPE3, tbl_ClinicalData.PHENOTYPE4, tbl_ClinicalData.HEIGHT, tbl_ClinicalData.WEIGHT, tbl_ClinicalData.DATE_BX, tbl_ClinicalData.DIALYSIS_REQ, tbl_ClinicalData.MAP_BP, tbl_ClinicalData.DIA_BP, tbl_ClinicalData.SYS_BP, tbl_ClinicalData.COURSE_DIS, tbl_ClinicalData.OEDEMA, tbl_ClinicalData.ANAEMIA, tbl_ClinicalData.HYPOVAL, tbl_ClinicalData.PERITONITIS, tbl_ClinicalData.THROMBOSIS, tbl_ClinicalData.FEVER, tbl_ClinicalData.URTICARIA, tbl_ClinicalData.DIABETES, tbl_ClinicalData.HTH_REQ_TMT, tbl_ClinicalData.PUL_OED, tbl_ClinicalData.COMMENTS, tbl_ClinicalData.IMMUNIS_TRIGGER, tbl_ClinicalData.OPTHALM_DETAIL, tbl_ClinicalData.OPTHALM, tbl_ClinicalData.RASH, tbl_ClinicalData.RASH_DETAIL, tbl_ClinicalData.PART_LIPODYS, tbl_ClinicalData.INFECTION, tbl_ClinicalData.INFECTION_TYPE, tbl_ClinicalData.INFECTION_DETAIL, tbl_ClinicalData.COMP_THROMBOSIS, tbl_ClinicalData.COMP_THROMBOSIS_DETAIL, tbl_ClinicalData.CKD_STAGE, tbl_ClinicalData.TX_LISTED, tbl_Demographics.HOSP_NO, tbl_Demographics.FNAME, tbl_Demographics.DOB, tbl_Demographics.SNAME, tbl_Diagnosis.SIG_DIAG1, tbl_Diagnosis.SIG_DIAG2, tbl_Diagnosis.DIAG FROM tbl_ClinicalData INNER JOIN tbl_Demographics ON tbl_ClinicalData.RADAR_NO = tbl_Demographics.RADAR_NO INNER JOIN tbl_Diagnosis ON tbl_Demographics.RADAR_NO = tbl_Diagnosis.RADAR_NO WHERE (tbl_ClinicalData.RADAR_NO = " & Session("patientID") & " AND [SEQ_NO] = '" & SEQ_NO & "')"
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New System.Data.SqlClient.SqlConnection(strConnect)
        Dim objCommand As New System.Data.SqlClient.SqlCommand(strSQL, objConnect)

        Try

            'declare a variable to hold the a DataReader object
            Dim objDataReader As SqlDataReader
            objConnect.Open()
            'execute the SQL statement
            objDataReader = objCommand.ExecuteReader()


            If objDataReader.Read() Then

                txtRADAR_NO.Text = objDataReader(1)
                txtHOSP_NO.Text = TDES.Decrypt(objDataReader("HOSP_NO"))
                lblFirstName.Text = TDES.Decrypt(objDataReader("FNAME"))
                lblSurname.Text = TDES.Decrypt(objDataReader("SNAME"))

                Dim dt As Date = CDate(TDES.Decrypt(objDataReader("DOB")))
                lblDOB.Text = Format(dt, "dd-MMM-yy")

                txtHeight.Text = chkNull(objDataReader("HEIGHT"))
                txtWeight.Text = chkNull(objDataReader("WEIGHT"))
                txtBPSys.Text = chkNull(objDataReader("SYS_BP"))
                txtBPDia.Text = chkNull(objDataReader("DIA_BP"))
                txtBPMap.Text = chkNull(objDataReader("MAP_BP"))

                If txtBPMap.Text = "" Then
                    txtBPMap.Text = "Auto"
                    txtBPMap.Font.Italic = True
                Else
                    txtBPMap.Font.Italic = False
                End If

                txtComments.Text = chkNull(objDataReader("COMMENTS"))
                DropDownList2.SelectedValue = chkNull(objDataReader("COURSE_DIS"))
                txtSigDiag1.Text = chkNull(objDataReader("SIG_DIAG1"))
                txtSigDiag2.Text = chkNull(objDataReader("SIG_DIAG2"))

                DropDownList4.SelectedValue = chkNull(objDataReader("DIABETES"))
                txtRashDetail.Text = chkNull(objDataReader("RASH_DETAIL"))
                DropDownList6.SelectedValue = chkNull(objDataReader("PHENOTYPE1"))
                DropDownList7.SelectedValue = chkNull(objDataReader("PHENOTYPE2"))
                DropDownList8.SelectedValue = chkNull(objDataReader("PHENOTYPE3"))
                DropDownList9.SelectedValue = chkNull(objDataReader("PHENOTYPE4"))

                Select Case objDataReader("HTH_REQ_TMT").ToString
                    Case True
                        radHyperYes.Checked = True
                        radHyperYes1.Checked = True
                    Case False
                        radHyperNo.Checked = True
                        radHyperNo1.Checked = True
                    Case Else
                        radHyperUn.Checked = True
                        radHyperUn1.Checked = True
                End Select

                If objDataReader("RASH") Is DBNull.Value Then
                    rowRashDetail.Visible = False
                    radRashUkn.Checked = True
                    txtRashDetail.Text = ""
                ElseIf objDataReader("RASH") = True Then
                    rowRashDetail.Visible = True
                    radRashYes.Checked = True
                ElseIf objDataReader("RASH") = False Then
                    rowRashDetail.Visible = False
                    radRashNo.Checked = True
                    txtRashDetail.Text = ""
                End If


                txtRashDetail.Text = chkNull(objDataReader("RASH_DETAIL"))

                If objDataReader("DATE_CLIN_PIC") Is DBNull.Value Then
                    'ASPxDateEdit1.Date = DateTime.MinValue
                    lnk3Months.Enabled = False
                Else
                    txtDateClinicalPic.Text = objDataReader("DATE_CLIN_PIC")
                    lnk3Months.Enabled = True
                End If

                If objDataReader("OEDEMA") Is DBNull.Value Then
                    radOedemaUnkn.Checked = True
                ElseIf objDataReader("OEDEMA") = True Then
                    radOedemaYes.Checked = True
                ElseIf objDataReader("OEDEMA") = False Then
                    radOedemaNo.Checked = True
                End If

                Select Case objDataReader("ANAEMIA").ToString
                    Case False
                        radAnaemiaNo.Checked = True
                    Case True
                        radAnaemiaYes.Checked = True
                    Case Else
                        radAnaemiaUn.Checked = True
                End Select

                Select Case objDataReader("HYPOVAL").ToString
                    Case False
                        radHypovNo.Checked = True
                    Case True
                        radHypovYes.Checked = True
                    Case Else
                        radHypovUn.Checked = True
                End Select

                Select Case objDataReader("FEVER").ToString
                    Case False
                        radFeverNo.Checked = True
                    Case True
                        radFeverYes.Checked = True
                    Case Else
                        radFeverUn.Checked = True
                End Select

                Select Case objDataReader("THROMBOSIS").ToString
                    Case False
                        radThrombosisNo.Checked = True
                    Case True
                        radThrombosisYes.Checked = True
                    Case Else
                        radThrombosisUn.Checked = True
                End Select

                Select Case objDataReader("PERITONITIS").ToString
                    Case False
                        radPeritNo.Checked = True
                    Case True
                        radPeritYes.Checked = True
                    Case Else
                        radPeritUn.Checked = True
                End Select

                Select Case objDataReader("PUL_OED").ToString
                    Case False
                        radPulNo.Checked = True
                    Case True
                        radPulYes.Checked = True
                    Case Else
                        radPulUn.Checked = True
                End Select


                '# removed 11-Mar-10
                'Select Case objDataReader("IMMUNIS_TRIGGER").ToString
                '    Case False
                '        radImmTrigNo.Checked = True
                '    Case True
                '        radImmTrigYes.Checked = True
                '    Case Else
                '        radImmTrigUn.Checked = True
                'End Select


                Select Case (objDataReader("PART_LIPODYS")).ToString
                    Case False
                        radLipodNo.Checked = True
                    Case True
                        radLipodYes.Checked = True
                    Case Else
                        radLipodUn.Checked = True
                End Select

               

                Select Case (objDataReader("OPTHALM")).ToString
                    Case False
                        radOpthalmNo.Checked = True
                        txtOpDetail.Visible = False
                    Case True
                        radOpthalmYes.Checked = True
                        txtOpDetail.Visible = True
                    Case Else
                        radOpthalmUn.Checked = True
                        txtOpDetail.Visible = False
                End Select



                txtOpDetail.Text = chkNull(objDataReader("OPTHALM_DETAIL"))
                lblID.Text = objDataReader("cID")


                If objDataReader("DIAG") Is DBNull.Value Then
                    txtDIAGNOSIS.Text = "-"
                Else
                    txtDIAGNOSIS.Text = CommonClass.GetDiagnosis(objDataReader("DIAG").ToString)
                End If

                If objDataReader("INFECTION") Is DBNull.Value Then
                    rowType.Visible = False
                    rowDetails.Visible = False
                    radCompInfectNo.Checked = False
                    radCompInfectYes.Checked = False
                ElseIf objDataReader("INFECTION") = False Then
                    radCompInfectNo.Checked = True
                    rowType.Visible = False
                    rowDetails.Visible = False
                ElseIf objDataReader("INFECTION") = True Then
                    radCompInfectYes.Checked = True
                    rowType.Visible = True
                    rowDetails.Visible = True
                    'txtINFECTION_TYPE.Text = chkNull(objDataReader("INFECTION_TYPE"))
                    txtINFECTION_DETAIL.Text = chkNull(objDataReader("INFECTION_DETAIL"))
                End If

                If objDataReader("TX_LISTED") IsNot DBNull.Value Then
                    If objDataReader("TX_LISTED") = True Then
                        radTxYes.Checked = True
                    ElseIf objDataReader("TX_LISTED") = False Then
                        radTxNo.Checked = True
                    End If
                End If



                If objDataReader("CKD_STAGE") IsNot DBNull.Value Then

                    Select Case objDataReader("CKD_STAGE")
                        Case 1
                            radCKD1.Checked = True
                        Case 2
                            radCKD2.Checked = True
                        Case 3
                            radCKD3.Checked = True
                        Case 4
                            radCKD4.Checked = True
                        Case 5
                            radCKD5.Checked = True
                        Case 0
                            radCKDUn.Checked = True
                        Case Else
                            CommonClass.ClearRadioButtons(rowCKD)
                    End Select

                Else
                    CommonClass.ClearRadioButtons(rowCKD)
                End If


               

                If objDataReader("DIAG") IsNot DBNull.Value Then 'this needed to avoid error below

                    If objDataReader("DIAG") = 2 Then  'MPGN
                        'lblU.Visible = True
                        lblRash.Visible = True
                        lblOpth.Visible = True
                        txtRashDetail.Visible = True
                        'DropDownList5.Visible = True
                        rowThrombosis.Visible = False
                        rowPeritonitis.Visible = False
                        rowPulmonary.Visible = False
                        rowDiabetes.Visible = False
                        rowPreceed.Visible = True
                        rowLipod.Visible = True
                        rowClinicalEvidence.Visible = True
                        rowAnemia.Visible = False
                        rowComplications.Visible = False
                        ' lblTitle.Text = "Clinical features - Current"
                        lblPhenotype1.Text = ""
                        lblPhenotype2.Text = ""
                        lblPhenotype3.Text = ""
                        lblPhenotype4.Text = ""

                        DropDownList6.Visible = False
                        DropDownList7.Visible = False
                        DropDownList8.Visible = False
                        DropDownList9.Visible = False
                        lblCourseDisease.Text = ""
                        DropDownList2.Visible = False
                        lblRash.Text = "Urticaria"

                        lblPrecInfect.Text = "Current acute infection"

                        txtPREC_INF_DETAIL.Text = objDataReader("PREC_INF_DETAIL").ToString

                        If objDataReader("PREC_INF") Is DBNull.Value Then
                            radPrecInfectUn.Checked = True
                            rowPreceedDetail.Visible = False
                        ElseIf objDataReader("PREC_INF") = True Then
                            radPrecInfectYes.Checked = True
                            rowPreceedDetail.Visible = True
                        ElseIf objDataReader("PREC_INF") = False Then
                            rowPreceedDetail.Visible = False
                            radPrecInfectNo.Checked = True
                        End If

                        txtCLIN_EV_CHR_INF_DETAIL.Text = objDataReader("CLIN_EV_CHR_INF_DETAIL").ToString

                        If objDataReader("CLIN_EV_CHR_INF") Is DBNull.Value Then
                            rowClinicalEvidenceDetail.Visible = False
                            radChronicUn.Checked = True
                        ElseIf objDataReader("CLIN_EV_CHR_INF") = True Then
                            rowClinicalEvidenceDetail.Visible = True
                            radChronicYes.Checked = True
                        ElseIf objDataReader("CLIN_EV_CHR_INF") = False Then
                            rowClinicalEvidenceDetail.Visible = False
                            radChronicNo.Checked = True
                        End If

                        lblPage.Text = "6A"

                    ElseIf objDataReader("DIAG") = 1 Then  ' FSGS

                        'lblU.Visible = True
                        lblRash.Visible = True
                        lblOpth.Visible = True
                        radOpthalmYes.Visible = True
                        'DropDownList5.Visible = True
                        txtRashDetail.Visible = True
                        rowAnemia.Visible = False
                        rowLipod.Visible = False
                        rowHypovalaemia.Visible = False
                        rowPreceed.Visible = False
                        rowPreceedDetail.Visible = False
                        rowClinicalEvidence.Visible = False
                        rowClinicalEvidenceDetail.Visible = False
                        rowPeritonitis.Visible = False
                        rowThrombosis.Visible = False
                        rowPulmonary.Visible = False
                        rowDiabetes.Visible = False
                        rowRash.Visible = False
                        rowRashDetail.Visible = False
                        rowOpthalm.Visible = False
                        rowOpthalmDetail.Visible = False
                        rowOedema.Visible = False
                        rowHypovalaemia.Visible = False
                        rowFever.Visible = False
                        rowHyperten.Visible = False

                        ' lblTitle.Text = "Clinical Features at Diagnosis"
                        lblCourseDisease.Text = ""
                        DropDownList2.Visible = False

                        rowPhenotype1.Visible = False
                        rowPhenotype2.Visible = False
                        rowPhenotype3.Visible = False
                        rowPhenotype4.Visible = False

                        lblPrecInfect.Text = "Preceeding infection(inactive)"

                        lblPage.Text = "6B"

                    End If



                End If

                If objDataReader("COMP_THROMBOSIS") Is DBNull.Value Then
                    radTNo.Checked = False
                    radTYes.Checked = False
                    rowCOMP_THROMBOSIS_DETAIL.Visible = False
                ElseIf objDataReader("COMP_THROMBOSIS") = True Then
                    radTYes.Checked = True
                    rowCOMP_THROMBOSIS_DETAIL.Visible = True
                    txtCOMP_THROMBOSIS_DETAIL.Text = CommonClass.chkNull(objDataReader("COMP_THROMBOSIS_DETAIL"))
                ElseIf objDataReader("COMP_THROMBOSIS") = False Then
                    radTNo.Checked = True
                    rowCOMP_THROMBOSIS_DETAIL.Visible = False
                    txtCOMP_THROMBOSIS_DETAIL.Text = ""
                End If


            End If

        Catch objError As Exception

            lblDebug.Text = "An error occurred (PageFill): '" & objError.Message & "'" '& strSQL


        Finally

            objConnect.Close()

        End Try

        'lblUpdate.Text = "Page fill"

    End Sub


    Protected Sub btnUpdate_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnUpdate.Click
        pageSave()
    End Sub

    Sub pageSave()
        Dim intID As Integer = CInt(lblID.Text)

        Dim intSEQ_NO As Integer = CInt(txtSEQ_NO.Text)

        Dim intRadar = CInt(txtRADAR_NO.Text)
        Dim dtClinPic As Date = CDate(txtDateClinicalPic.Text)

        Dim intMap As Integer

        If txtBPSys.Text <> "" And txtBPDia.Text <> "" Then

            Dim intSys As Integer = CInt(txtBPSys.Text)
            Dim intDia As Integer = CInt(txtBPDia.Text)
            intMap = ((2 * intDia) + intSys) / 3

        Else
            intMap = 0
        End If

        Dim strConnect As String = ConfigurationAlias.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim strSQL As String = "UPDATE [tbl_ClinicalData] SET [RADAR_NO] = @RADAR_NO, [DATE_CLIN_PIC] = @DATE_CLIN_PIC, [HEIGHT] = @HEIGHT, [WEIGHT] = @WEIGHT, [MAP_BP] = @MAP_BP, [DIA_BP] = @DIA_BP, [SYS_BP] = @SYS_BP, [COURSE_DIS] = @COURSE_DIS, [PERITONITIS] = @PERITONITIS, [FEVER] = @FEVER, [THROMBOSIS] = @THROMBOSIS, [THROMBOSIS_DETAIL] = @THROMBOSIS_DETAIL, [HYPOVAL] = @HYPOVAL, [ANAEMIA] = @ANAEMIA, [OEDEMA] = @OEDEMA, [DIABETES] = @DIABETES, [HTH_REQ_TMT] = @HTH_REQ_TMT, [PUL_OED] = @PUL_OED, [COMMENTS] = @COMMENTS, [IMMUNIS_TRIGGER] = @IMMUNIS_TRIGGER, [OPTHALM_DETAIL] = @OPTHALM_DETAIL, [OPTHALM] = @OPTHALM, [RASH] = @RASH, [PHENOTYPE1]= @PHENOTYPE1, [PHENOTYPE2]= @PHENOTYPE2, [PHENOTYPE3]= @PHENOTYPE3, [PHENOTYPE4]= @PHENOTYPE4, [PREC_INF] = @PREC_INF, [PREC_INF_DETAIL] = @PREC_INF_DETAIL, [CLIN_EV_CHR_INF] = @CLIN_EV_CHR_INF, [CLIN_EV_CHR_INF_DETAIL] = @CLIN_EV_CHR_INF_DETAIL, [INFECTION] = @INFECTION, [INFECTION_TYPE] = @INFECTION_TYPE, [INFECTION_DETAIL] = @INFECTION_DETAIL, [SIG_DIAG1] = @SIG_DIAG1, [SIG_DIAG2] = @SIG_DIAG2, [TX_LISTED] = @TX_LISTED, [PART_LIPODYS] = @PART_LIPODYS, [COMP_THROMBOSIS] = @COMP_THROMBOSIS, [COMP_THROMBOSIS_DETAIL] = @COMP_THROMBOSIS_DETAIL, [CKD_STAGE] = @CKD_STAGE WHERE [cID] = @cID AND [SEQ_NO] = '" & intSEQ_NO & "'"
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        With objCommand.Parameters

            '.Add(New SqlParameter("RADAR_NO", txtRADAR_NO.Text))

            'If txtHeight.Text = "" Then
            '    .Add(New SqlParameter("@HEIGHT", DBNull.Value))
            'Else
            '    .Add(New SqlParameter("@HEIGHT", txtHeight.Text))
            'End If

            'If txtWeight.Text = "" Then
            '    .Add(New SqlParameter("@WEIGHT", DBNull.Value))

            'Else
            '    .Add(New SqlParameter("@WEIGHT", txtWeight.Text))

            'End If

            'If txtBPSys.Text = "" Then
            '    .Add(New SqlParameter("@SYS_BP", DBNull.Value))
            'Else
            '    .Add(New SqlParameter("@SYS_BP", txtBPSys.Text))
            'End If

            'If txtBPDia.Text = "" Then
            '    .Add(New SqlParameter("@DIA_BP", DBNull.Value))
            'Else
            '    .Add(New SqlParameter("@DIA_BP", txtBPDia.Text))
            'End If

            'If intMap = 0 Then
            '    .Add(New SqlParameter("@MAP_BP", DBNull.Value))
            'Else
            '    .Add(New SqlParameter("@MAP_BP", intMap))
            'End If

            ''If DropDownList6.SelectedValue = "" Then
            '.Add(New SqlParameter("@PHENOTYPE1", DBNull.Value))
            ''Else
            ''    .Add(New SqlParameter("@PHENOTYPE1", DropDownList6.SelectedValue))
            ''End If

            ''If DropDownList7.SelectedValue = "" Then
            '.Add(New SqlParameter("@PHENOTYPE2", DBNull.Value))
            ''Else
            ''    .Add(New SqlParameter("@PHENOTYPE2", DropDownList7.SelectedValue))
            ''End If

            ''If DropDownList8.SelectedValue = "" Then
            '.Add(New SqlParameter("@PHENOTYPE3", DBNull.Value))
            ''Else
            ''    .Add(New SqlParameter("@PHENOTYPE3", DropDownList8.SelectedValue))
            ''End If

            ''If DropDownList9.SelectedValue = "" Then
            '.Add(New SqlParameter("@PHENOTYPE4", DBNull.Value))
            ''Else
            ''    .Add(New SqlParameter("@PHENOTYPE4", DropDownList9.SelectedValue))
            ''End If

            'If txtComments.Text = "" Then
            '    .Add(New SqlParameter("@COMMENTS", DBNull.Value))
            'Else
            '    .Add(New SqlParameter("@COMMENTS", txtComments.Text))
            'End If

            'If radYes.Checked = True Then
            '    .Add(New SqlParameter("@INFECTION", True))
            '    If txtINFECTION_TYPE.Text = "" Then
            '        .Add(New SqlParameter("@INFECTION_TYPE", DBNull.Value))
            '    Else
            '        .Add(New SqlParameter("@INFECTION_TYPE", txtINFECTION_TYPE.Text))
            '    End If

            '    If txtINFECTION_DETAIL.Text = "" Then
            '        .Add(New SqlParameter("@INFECTION_DETAIL", DBNull.Value))
            '    Else
            '        .Add(New SqlParameter("@INFECTION_DETAIL", txtINFECTION_DETAIL.Text))
            '    End If

            'ElseIf radNo.Checked = True Then
            '    .Add(New SqlParameter("@INFECTION", False))
            '    .Add(New SqlParameter("@INFECTION_DETAIL", DBNull.Value))
            '    .Add(New SqlParameter("@INFECTION_TYPE", DBNull.Value))
            'Else
            '    .Add(New SqlParameter("@INFECTION", DBNull.Value))
            '    .Add(New SqlParameter("@INFECTION_DETAIL", DBNull.Value))
            '    .Add(New SqlParameter("@INFECTION_TYPE", DBNull.Value))
            'End If


            'If radTYes.Checked = True Then
            '    .Add(New SqlParameter("@THROMBOSIS", True))
            '    .Add(New SqlParameter("@THROMBOSIS_DETAIL", txtThrombosis.Text))
            'ElseIf radTNo.Checked = True Then
            '    .Add(New SqlParameter("@THROMBOSIS", False))
            '    .Add(New SqlParameter("@THROMBOSIS_DETAIL", DBNull.Value))
            'Else
            '    .Add(New SqlParameter("@THROMBOSIS_DETAIL", DBNull.Value))
            '    .Add(New SqlParameter("@THROMBOSIS", DBNull.Value))
            'End If

            'If txtSigDiag1.Text = "" Then
            '    .Add(New SqlParameter("@SIG_DIAG1", DBNull.Value))
            'Else
            '    .Add(New SqlParameter("@SIG_DIAG1", txtSigDiag1.Text))
            'End If

            'If txtSigDiag2.Text = "" Then
            '    .Add(New SqlParameter("@SIG_DIAG2", DBNull.Value))
            'Else
            '    .Add(New SqlParameter("@SIG_DIAG2", txtSigDiag2.Text))
            'End If

            'If radTxYes.Checked = True Then
            '    .Add(New SqlParameter("@TX_LISTED", True))
            'ElseIf radTxNo.Checked = True Then
            '    .Add(New SqlParameter("@TX_LISTED", False))
            'Else
            '    .Add(New SqlParameter("@TX_LISTED", DBNull.Value))
            'End If

            '.Add(New SqlParameter("@DATE_CLIN_PIC", dtClinPic))
            '.Add(New SqlParameter("@cID", lblID.Text))

            '.Add(New SqlParameter("@COURSE_DIS", DBNull.Value))
            ''.Add(New SqlParameter("@PERITONITIS", DBNull.Value))
            ''.Add(New SqlParameter("@FEVER", DBNull.Value))

            ''.Add(New SqlParameter("@HYPOVAL", DBNull.Value))
            ''.Add(New SqlParameter("@ANAEMIA", DBNull.Value))
            ''.Add(New SqlParameter("@OEDEMA", DBNull.Value))
            ''.Add(New SqlParameter("@DIABETES", DBNull.Value))
            ''.Add(New SqlParameter("@HTH_REQ_TMT", DBNull.Value))
            ''.Add(New SqlParameter("@PUL_OED", DBNull.Value))
            ''.Add(New SqlParameter("@IMMUNIS_TRIGGER", DBNull.Value))
            ''.Add(New SqlParameter("@OPTHALM_DETAIL", DBNull.Value))
            ''.Add(New SqlParameter("@OPTHALM", DBNull.Value))
            ''.Add(New SqlParameter("@RASH", DBNull.Value))
            ''.Add(New SqlParameter("@PREC_INF", DBNull.Value))
            ''.Add(New SqlParameter("@PREC_INF_DETAIL", DBNull.Value))
            ''.Add(New SqlParameter("@CLIN_EV_CHR_INF", DBNull.Value))
            ''.Add(New SqlParameter("@CLIN_EV_CHR_INF_DETAIL", DBNull.Value))
            ''.Add(New SqlParameter("@", dbnull.value))
            ''.Add(New SqlParameter("@", dbnull.value))

            'If txtRashDetail.Text = "" Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@RASH_DETAIL", DBNull.Value))
            'Else
            '    .Add(New System.Data.SqlClient.SqlParameter("@RASH_DETAIL", txtRashDetail.Text))
            'End If

            'If radRashUkn.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@RASH", DBNull.Value))
            'ElseIf radRashYes.Checked = True Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@RASH", True))
            'ElseIf radRashNo.Checked = True Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@RASH", False))
            'End If

            'If radHyperYes.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@HTH_REQ_TMT", True))
            'ElseIf radHyperNo.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@HTH_REQ_TMT", False))
            'ElseIf radHyperUn.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@HTH_REQ_TMT", DBNull.Value))
            'End If

            'If radOedemaYes.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@OEDEMA", True))
            'ElseIf radOedemaNo.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@OEDEMA", False))
            'ElseIf radOedemaUnkn.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@OEDEMA", DBNull.Value))
            'End If

            'If radHypovYes.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@HYPOVAL", True))
            'ElseIf radHypovNo.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@HYPOVAL", False))
            'ElseIf radHypovUn.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@HYPOVAL", DBNull.Value))
            'End If

            'If radFeverYes.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@FEVER", True))
            'ElseIf radFeverNo.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@FEVER", False))
            'ElseIf radFeverUn.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@FEVER", DBNull.Value))
            'End If

            'If radThrombosisYes.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@THROMBOSIS", True))
            'ElseIf radThrombosisNo.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@THROMBOSIS", False))
            'ElseIf radThrombosisUn.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@THROMBOSIS", DBNull.Value))
            'End If

            'If radPeritYes.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@PERITONITIS", True))
            'ElseIf radPeritNo.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@PERITONITIS", False))
            'ElseIf radPeritUn.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@PERITONITIS", DBNull.Value))
            'End If

            'If radPulYes.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@PUL_OED", True))
            'ElseIf radPulNo.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@PUL_OED", False))
            'ElseIf radPulUn.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@PUL_OED", DBNull.Value))
            'End If

            'If radImmTrigYes.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@IMMUNIS_TRIGGER", True))
            'ElseIf radImmTrigNo.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@IMMUNIS_TRIGGER", False))
            'ElseIf radImmTrigUn.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@IMMUNIS_TRIGGER", DBNull.Value))
            'End If

            'If radLipodYes.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@PART_LIPODYS", True))
            'ElseIf radLipodNo.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@PART_LIPODYS", False))
            'ElseIf radLipodUn.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@PART_LIPODYS", DBNull.Value))
            'End If

            'If radOpthalmYes.Checked = True Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@OPTHALM", True))
            'ElseIf radOpthalmNo.Checked = True Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@OPTHALM", False))
            'Else
            '    .Add(New System.Data.SqlClient.SqlParameter("@OPTHALM", DBNull.Value))
            'End If

            'If radPrecInfectYes.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@PREC_INF", True))
            'ElseIf radPrecInfectNo.Checked = True Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@PREC_INF", False))
            'Else
            '    .Add(New System.Data.SqlClient.SqlParameter("@PREC_INF", DBNull.Value))
            'End If

            '.Add(New System.Data.SqlClient.SqlParameter("@RADAR_NO", intRadar))
            '.Add(New System.Data.SqlClient.SqlParameter("@DATE_CLIN_PIC", dtClinPic))
            '.Add(New System.Data.SqlClient.SqlParameter("@cID", lblID.Text))

            'If radChronicYes.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@CLIN_EV_CHR_INF", True))
            '    .Add(New System.Data.SqlClient.SqlParameter("@CLIN_EV_CHR_INF_DETAIL", txtClinicalEvidence.Text))
            'ElseIf radChronicNo.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@CLIN_EV_CHR_INF", False))
            '    .Add(New System.Data.SqlClient.SqlParameter("@CLIN_EV_CHR_INF_DETAIL", DBNull.Value))
            'ElseIf radChronicUn.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@CLIN_EV_CHR_INF", DBNull.Value))
            '    .Add(New System.Data.SqlClient.SqlParameter("@CLIN_EV_CHR_INF_DETAIL", DBNull.Value))
            'End If




            'If (radOpthalmYes.Checked = False) Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@OPTHALM_DETAIL", DBNull.Value))
            'ElseIf txtOpDetail.Text = "" Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@OPTHALM_DETAIL", DBNull.Value))
            'Else
            '    .Add(New System.Data.SqlClient.SqlParameter("@OPTHALM_DETAIL", txtOpDetail.Text))
            'End If

            'If (radPrecInfectYes.Checked) Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@PREC_INF_DETAIL", txtInfectionDetail.Text))
            'Else
            '    .Add(New System.Data.SqlClient.SqlParameter("@PREC_INF_DETAIL", DBNull.Value))
            'End If

            .Add(New System.Data.SqlClient.SqlParameter("@DIALYSIS_REQ", DBNull.Value))
            .Add(New System.Data.SqlClient.SqlParameter("@DATE_BX", DBNull.Value))
            .Add(New System.Data.SqlClient.SqlParameter("@URTICARIA", DBNull.Value))
            .Add(New System.Data.SqlClient.SqlParameter("@DATE_CLIN_PIC", CDate(txtDateClinicalPic.Text)))

            If txtHeight.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@HEIGHT", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@HEIGHT", CInt(txtHeight.Text)))
            End If

            If txtWeight.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@WEIGHT", DBNull.Value))

            Else
                .Add(New System.Data.SqlClient.SqlParameter("@WEIGHT", txtWeight.Text))

            End If

            If txtBPSys.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@SYS_BP", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@SYS_BP", txtBPSys.Text))
            End If

            If txtBPDia.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@DIA_BP", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@DIA_BP", txtBPDia.Text))
            End If

            If intMap = 0 Then
                .Add(New System.Data.SqlClient.SqlParameter("@MAP_BP", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@MAP_BP", intMap))
            End If

            'If DropDownList1.SelectedValue = "" Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@INFECTION", DBNull.Value))
            'Else
            '    .Add(New System.Data.SqlClient.SqlParameter("@INFECTION", DropDownList1.SelectedValue))
            'End If

            'If txtInfectDetails.Text = "" Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@INFECTION_DETAIL", DBNull.Value))
            'Else
            '    .Add(New System.Data.SqlClient.SqlParameter("@INFECTION_DETAIL", txtInfectDetails.Text))
            'End If

            If DropDownList2.SelectedValue = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@COURSE_DIS", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@COURSE_DIS", DropDownList2.SelectedValue))
            End If


            If radRashYes.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@RASH_DETAIL", txtRashDetail.Text))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@RASH_DETAIL", DBNull.Value))
            End If

            If radRashUkn.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@RASH", DBNull.Value))
            ElseIf radRashYes.Checked = True Then
                .Add(New System.Data.SqlClient.SqlParameter("@RASH", True))
            ElseIf radRashNo.Checked = True Then
                .Add(New System.Data.SqlClient.SqlParameter("@RASH", False))
            End If





            If (radHyperYes.Checked Or radHyperYes1.Checked) Then
                .Add(New System.Data.SqlClient.SqlParameter("@HTH_REQ_TMT", True))
            ElseIf (radHyperNo.Checked Or radHyperNo1.Checked) Then
                .Add(New System.Data.SqlClient.SqlParameter("@HTH_REQ_TMT", False))
            ElseIf (radHyperUn.Checked Or radHyperUn1.Checked) Then
                .Add(New System.Data.SqlClient.SqlParameter("@HTH_REQ_TMT", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@HTH_REQ_TMT", DBNull.Value))
            End If




            If DropDownList4.SelectedValue = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@DIABETES", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@DIABETES", DropDownList4.SelectedValue))
            End If

            If DropDownList6.SelectedValue = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@PHENOTYPE1", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@PHENOTYPE1", DropDownList6.SelectedValue))
            End If

            If DropDownList7.SelectedValue = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@PHENOTYPE2", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@PHENOTYPE2", DropDownList7.SelectedValue))
            End If

            If DropDownList8.SelectedValue = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@PHENOTYPE3", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@PHENOTYPE3", DropDownList8.SelectedValue))
            End If

            If DropDownList9.SelectedValue = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@PHENOTYPE4", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@PHENOTYPE4", DropDownList9.SelectedValue))
            End If




            If txtComments.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@COMMENTS", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@COMMENTS", txtComments.Text))
            End If

            'If DropDownList6.SelectedValue = "" Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@DIALYSIS_REQ", DBNull.Value))
            'Else
            '    .Add(New System.Data.SqlClient.SqlParameter("@DIALYSIS_REQ", DropDownList6.SelectedValue))
            'End If

            If radOedemaYes.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@OEDEMA", True))
            ElseIf radOedemaNo.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@OEDEMA", False))
            ElseIf radOedemaUnkn.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@OEDEMA", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@OEDEMA", DBNull.Value))
            End If

            If radHypovYes.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@HYPOVAL", True))
            ElseIf radHypovNo.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@HYPOVAL", False))
            ElseIf radHypovUn.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@HYPOVAL", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@HYPOVAL", DBNull.Value))
            End If

            If radFeverYes.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@FEVER", True))
            ElseIf radFeverNo.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@FEVER", False))
            ElseIf radFeverUn.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@FEVER", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@FEVER", DBNull.Value))
            End If

            If radThrombosisYes.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@THROMBOSIS", True))
            ElseIf radThrombosisNo.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@THROMBOSIS", False))
            ElseIf radThrombosisUn.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@THROMBOSIS", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@THROMBOSIS", DBNull.Value))
            End If

            .Add(New System.Data.SqlClient.SqlParameter("@THROMBOSIS_DETAIL", DBNull.Value))

            If radPeritYes.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@PERITONITIS", True))
            ElseIf radPeritNo.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@PERITONITIS", False))
            ElseIf radPeritUn.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@PERITONITIS", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@PERITONITIS", DBNull.Value))
            End If

            If radPulYes.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@PUL_OED", True))
            ElseIf radPulNo.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@PUL_OED", False))
            ElseIf radPulUn.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@PUL_OED", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@PUL_OED", DBNull.Value))
            End If

            'If radImmTrigYes.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@IMMUNIS_TRIGGER", True))
            'ElseIf radImmTrigNo.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@IMMUNIS_TRIGGER", False))
            'ElseIf radImmTrigUn.Checked Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@IMMUNIS_TRIGGER", DBNull.Value))
            'Else
            .Add(New System.Data.SqlClient.SqlParameter("@IMMUNIS_TRIGGER", DBNull.Value))
            'End If

            If radLipodYes.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@PART_LIPODYS", True))
            ElseIf radLipodNo.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@PART_LIPODYS", False))
            ElseIf radLipodUn.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@PART_LIPODYS", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@PART_LIPODYS", DBNull.Value))
            End If


            .Add(New System.Data.SqlClient.SqlParameter("@ANAEMIA", DBNull.Value))
            '.Add(New System.Data.SqlClient.SqlParameter("@FEVER", chkFever.Checked))

            '.Add(New System.Data.SqlClient.SqlParameter("@HYPOVAL", chkHypo.Checked))
            '.Add(New System.Data.SqlClient.SqlParameter("@THROMBOSIS", chkThrombosis.Checked))
            '.Add(New System.Data.SqlClient.SqlParameter("@PERITONITIS", chkPeritonitis.Checked))
            '.Add(New System.Data.SqlClient.SqlParameter("@PUL_OED", chkPulmonary.Checked))
            '.Add(New System.Data.SqlClient.SqlParameter("@IMMUNIS_TRIGGER", chkImmTrigger.Checked))
            '.Add(New System.Data.SqlClient.SqlParameter("@STEROID_RESIST", chkSteroid.Checked))

            If radOpthalmYes.Checked = True Then
                .Add(New System.Data.SqlClient.SqlParameter("@OPTHALM", True))
            ElseIf radOpthalmNo.Checked = True Then
                .Add(New System.Data.SqlClient.SqlParameter("@OPTHALM", False))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@OPTHALM", DBNull.Value))
            End If

            If radPrecInfectYes.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@PREC_INF", True))
            ElseIf radPrecInfectNo.Checked = True Then
                .Add(New System.Data.SqlClient.SqlParameter("@PREC_INF", False))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@PREC_INF", DBNull.Value))
            End If

            .Add(New System.Data.SqlClient.SqlParameter("@RADAR_NO", intRadar))

            If radCompInfectNo.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@INFECTION", False))
                .Add(New System.Data.SqlClient.SqlParameter("@INFECTION_TYPE", DBNull.Value))
                .Add(New System.Data.SqlClient.SqlParameter("@INFECTION_DETAIL", DBNull.Value))
            ElseIf radCompInfectYes.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@INFECTION", True))
                .Add(New System.Data.SqlClient.SqlParameter("@INFECTION_TYPE", DBNull.Value))
                .Add(New System.Data.SqlClient.SqlParameter("@INFECTION_DETAIL", txtINFECTION_DETAIL.Text))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@INFECTION", DBNull.Value))
                .Add(New System.Data.SqlClient.SqlParameter("@INFECTION_TYPE", DBNull.Value))
                .Add(New System.Data.SqlClient.SqlParameter("@INFECTION_DETAIL", DBNull.Value))
            End If


            .Add(New System.Data.SqlClient.SqlParameter("@cID", lblID.Text))

            If radChronicYes.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@CLIN_EV_CHR_INF", True))
                .Add(New System.Data.SqlClient.SqlParameter("@CLIN_EV_CHR_INF_DETAIL", txtCLIN_EV_CHR_INF_DETAIL.Text))
            ElseIf radChronicNo.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@CLIN_EV_CHR_INF", False))
                .Add(New System.Data.SqlClient.SqlParameter("@CLIN_EV_CHR_INF_DETAIL", DBNull.Value))
            ElseIf radChronicUn.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@CLIN_EV_CHR_INF", DBNull.Value))
                .Add(New System.Data.SqlClient.SqlParameter("@CLIN_EV_CHR_INF_DETAIL", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@CLIN_EV_CHR_INF", DBNull.Value))
                .Add(New System.Data.SqlClient.SqlParameter("@CLIN_EV_CHR_INF_DETAIL", DBNull.Value))
            End If


            If (radOpthalmYes.Checked = False) Then
                .Add(New System.Data.SqlClient.SqlParameter("@OPTHALM_DETAIL", DBNull.Value))
            ElseIf txtOpDetail.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@OPTHALM_DETAIL", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@OPTHALM_DETAIL", txtOpDetail.Text))
            End If

            If (radPrecInfectYes.Checked) Then
                .Add(New System.Data.SqlClient.SqlParameter("@PREC_INF_DETAIL", txtPREC_INF_DETAIL.Text))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@PREC_INF_DETAIL", DBNull.Value))
            End If

            If txtSigDiag1.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@SIG_DIAG1", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@SIG_DIAG1", txtSigDiag1.Text))
            End If

            If txtSigDiag2.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@SIG_DIAG2", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@SIG_DIAG2", txtSigDiag2.Text))
            End If

            If radTxYes.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@TX_LISTED", True))
            ElseIf radTxNo.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@TX_LISTED", False))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@TX_LISTED", DBNull.Value))
            End If


            If radTYes.Checked Then
                .Add(New SqlParameter("@COMP_THROMBOSIS", True))
                .Add(New SqlParameter("@COMP_THROMBOSIS_DETAIL", txtCOMP_THROMBOSIS_DETAIL.Text))
            ElseIf radTNo.Checked Then
                .Add(New SqlParameter("@COMP_THROMBOSIS", False))
                .Add(New SqlParameter("@COMP_THROMBOSIS_DETAIL", DBNull.Value))
            Else
                .Add(New SqlParameter("@COMP_THROMBOSIS", DBNull.Value))
                .Add(New SqlParameter("@COMP_THROMBOSIS_DETAIL", DBNull.Value))
            End If

            If radCKD1.Checked Then
                .Add(New SqlParameter("@CKD_STAGE", 1))
            ElseIf radCKD2.Checked Then
                .Add(New SqlParameter("@CKD_STAGE", 2))
            ElseIf radCKD3.Checked Then
                .Add(New SqlParameter("@CKD_STAGE", 3))
            ElseIf radCKD4.Checked Then
                .Add(New SqlParameter("@CKD_STAGE", 4))
            ElseIf radCKD5.Checked Then
                .Add(New SqlParameter("@CKD_STAGE", 5))
            ElseIf radCKDUn.Checked Then
                .Add(New SqlParameter("@CKD_STAGE", 0))
            Else
                .Add(New SqlParameter("@CKD_STAGE", DBNull.Value))
            End If

            .Add(New SqlParameter("@SEQ_NO", txtSEQ_NO.Text))



        End With

        Try
            objConnect.Open()
            objCommand.ExecuteNonQuery()

        Catch objError As Exception

            lblDebug.Text = String.Format("An error occurred: (Update) '{0}'", objError.Message)
            objConnect.Close()
            Exit Sub

        Finally
            objConnect.Close()
        End Try


        lblUpdate.Text = String.Format("<strong>Update successful:</strong>&nbsp;&nbsp;{0}", Now().ToShortTimeString)
        lblUpdate2.Text = String.Format("<strong>Update successful:</strong>&nbsp;&nbsp;{0}", Now().ToShortTimeString)

        pagefill(intSEQ_NO)

    End Sub

    Protected Sub btnAdd_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnAdd.Click

        'get basic data for new entries

        Dim TDES As New TripleDES()

        Dim patientID As Integer = Session("patientID")
        Dim strConnect As String = ConfigurationAlias.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim strSQL2 As String = "SELECT tbl_Diagnosis.DIAG, tbl_Demographics.SNAME, tbl_Demographics.FNAME, tbl_Demographics.DOB, tbl_Demographics.RADAR_NO, tbl_Demographics.HOSP_NO FROM tbl_Demographics INNER JOIN tbl_Diagnosis ON tbl_Demographics.RADAR_NO = tbl_Diagnosis.RADAR_NO WHERE (tbl_Demographics.RADAR_NO = '" & patientID & "')"
        Dim objConnect2 As New SqlConnection(strConnect)

        Dim objCommand2 As New SqlCommand(strSQL2, objConnect2)

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
                    intDiag = objDataReader("DIAG")
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

        pnlAdd.Visible = True
        txtSEQ_NO.Text = GetNextRecord()

        '#reset the form fields
        CommonClass.EmptyTexBoxes(tblMain)
        CommonClass.ClearRadioButtons(tblMain)

        txtDateClinicalPic.Text = ""
        lblDebug.Text = ""
        lblUpdate.Text = ""
        rowThrombosis.Visible = False
        rowDetails.Visible = False
        rowType.Visible = False
        rowRash.Visible = False
        rowPreceedDetail.Visible = False
        rowClinicalEvidenceDetail.Visible = False
       
        'DropDownList6.SelectedIndex = 0
        'DropDownList7.SelectedIndex = 0
        'DropDownList8.SelectedIndex = 0
        'DropDownList9.SelectedIndex = 0


        If intDiag = 2 Then  'MPGN
            'lblU.Visible = True
            lblRash.Visible = True
            lblRash.Text = "Urticaria"
            txtRashDetail.Visible = True
            lblOpth.Visible = True
            'DropDownList5.Visible = True
            rowThrombosis.Visible = False
            rowPeritonitis.Visible = False
            rowPulmonary.Visible = False
            rowDiabetes.Visible = False
            rowPreceed.Visible = True
            rowLipod.Visible = True
            rowClinicalEvidence.Visible = True
            rowAnemia.Visible = False

            lblPhenotype1.Text = ""
            lblPhenotype2.Text = ""
            lblPhenotype3.Text = ""
            lblPhenotype4.Text = ""
            DropDownList6.Visible = False
            DropDownList7.Visible = False
            DropDownList8.Visible = False
            DropDownList9.Visible = False
            lblCourseDisease.Text = ""
            DropDownList2.Visible = False

            rowComplications.Visible = False
            lblPrecInfect.Text = "Current acute infection"

            lblPage.Text = "6A"

        ElseIf intDiag = 1 Then  ' FSGS

            'lblU.Visible = True
            lblRash.Visible = True
            txtRashDetail.Visible = True
            lblOpth.Visible = True
            radOpthalmYes.Visible = True
            'DropDownList5.Visible = True

            rowAnemia.Visible = False
            rowLipod.Visible = False
            rowPreceed.Visible = False
            rowPreceedDetail.Visible = False
            rowClinicalEvidence.Visible = False
            rowClinicalEvidenceDetail.Visible = False
            rowPeritonitis.Visible = False
            rowThrombosis.Visible = False
            rowPulmonary.Visible = False
            rowDiabetes.Visible = False
            rowCOMP_THROMBOSIS_DETAIL.Visible = False

            lblCourseDisease.Text = ""
            DropDownList2.Visible = False
            lblPrecInfect.Text = "Preceeding infection(inactive)"

            lblPage.Text = "6B"

        End If



        btnUpdate.Visible = False
        btnSaveNew.Visible = True

    End Sub

    Function GetNextRecord() As Integer

        Dim strConnect As String = ConfigurationAlias.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim strSQL As String = "SELECT MAX(SEQ_NO) AS LastSEQ_NO FROM [tbl_ClinicalData] WHERE [RADAR_NO] = " & Session("patientID")
        Using objConnect As New SqlConnection(strConnect)
            Using objCommand As New SqlCommand(strSQL, objConnect)
                Try
                    'declare a variable to hold the a DataReader object
                    Dim objDataReader As SqlDataReader
                    objConnect.Open()
                    'execute the SQL statement
                    objDataReader = objCommand.ExecuteReader()
                    If objDataReader.Read() Then
                        GetNextRecord = CInt(objDataReader("LastSEQ_NO")) + 1
                        'lblDebug.Text = strSQL
                    End If
                Catch objError As Exception
                    lblDebug.Text = String.Format("An error occurred: '{0}'{1}", objError.Message, strSQL)
                End Try
            End Using
        End Using



    End Function

    Protected Sub btnSaveNew_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnSaveNew.Click



        Dim intRadar = CInt(txtRADAR_NO.Text)
        Dim dtClinPic As Date = CDate(txtDateClinicalPic.Text)
        lblDebug.Text = ""

        If chkDate(dtClinPic) = False Then
            'do nothing
        Else
            lblUpdate.Text = "<span style='color:red;'>A record already exists for this date.</span>"
            lblDebug.Text = dtClinPic
            Exit Sub
        End If


        Dim intMap As Integer

        If txtBPSys.Text <> "" And txtBPDia.Text <> "" Then

            Dim intSys As Integer = CInt(txtBPSys.Text)
            Dim intDia As Integer = CInt(txtBPDia.Text)
            intMap = ((2 * intDia) + intSys) / 3

        Else
            intMap = 0
        End If

        Dim strConnect As String = ConfigurationAlias.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        'Dim strSQL As String = "INSERT INTO [tbl_ClinicalData] ([RADAR_NO], [HEIGHT], [WEIGHT], [DATE_CLIN_PIC], [MAP_BP], [DIA_BP],[SYS_BP],[PHENOTYPE1], [PHENOTYPE2], [PHENOTYPE3], [PHENOTYPE4], [COMMENTS], [THROMBOSIS], [THROMBOSIS_DETAIL], [SEQ_NO], [TX_LISTED], [INFECTION], [INFECTION_TYPE], [INFECTION_DETAIL]) VALUES (@RADAR_NO, @HEIGHT, @WEIGHT, @DATE_CLIN_PIC, @MAP_BP, @DIA_BP, @SYS_BP, @PHENOTYPE1, @PHENOTYPE2, @PHENOTYPE3, @PHENOTYPE4, @COMMENTS, @THROMBOSIS, @THROMBOSIS_DETAIL, @SEQ_NO, @TX_LISTED, @INFECTION, @INFECTION_TYPE, @INFECTION_DETAIL)   "
        Dim strSQL As String = "INSERT INTO [tbl_ClinicalData] ([RADAR_NO], [DATE_CLIN_PIC], [HEIGHT], [WEIGHT], [COURSE_DIS], [SYS_BP], [DIA_BP], [MAP_BP], [DIALYSIS_REQ], [DATE_BX], [OEDEMA], [ANAEMIA], [HYPOVAL], [FEVER], [THROMBOSIS], [PERITONITIS], [PUL_OED], [HTH_REQ_TMT], [PREC_INF], [PREC_INF_DETAIL], [CLIN_EV_CHR_INF], [CLIN_EV_CHR_INF_DETAIL], [DIABETES], [URTICARIA], [RASH], [RASH_DETAIL], [PART_LIPODYS], [OPTHALM], [OPTHALM_DETAIL], [IMMUNIS_TRIGGER], [COMMENTS], [PHENOTYPE1], [PHENOTYPE2], [PHENOTYPE3], [PHENOTYPE4], [SIG_DIAG1], [SIG_DIAG2], [INFECTION], [INFECTION_DETAIL], [INFECTION_TYPE], [COMP_THROMBOSIS], [COMP_THROMBOSIS_DETAIL], [CKD_STAGE], [TX_LISTED], [SEQ_NO]) VALUES (@RADAR_NO, @DATE_CLIN_PIC, @HEIGHT, @WEIGHT, @COURSE_DIS, @SYS_BP, @DIA_BP, @MAP_BP, @DIALYSIS_REQ, @DATE_BX, @OEDEMA, @ANAEMIA, @HYPOVAL, @FEVER, @THROMBOSIS, @PERITONITIS, @PUL_OED, @HTH_REQ_TMT, @PREC_INF, @PREC_INF_DETAIL, @CLIN_EV_CHR_INF, @CLIN_EV_CHR_INF_DETAIL, @DIABETES, @URTICARIA, @RASH, @RASH_DETAIL, @PART_LIPODYS, @OPTHALM, @OPTHALM_DETAIL, @IMMUNIS_TRIGGER, @COMMENTS, @PHENOTYPE1, @PHENOTYPE2, @PHENOTYPE3, @PHENOTYPE4, @SIG_DIAG1, @SIG_DIAG2, @INFECTION, @INFECTION_DETAIL, @INFECTION_TYPE, @COMP_THROMBOSIS, @COMP_THROMBOSIS_DETAIL, @CKD_STAGE, @TX_LISTED, @SEQ_NO)"
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        Try
            'With objCommand.Parameters

            '    .Add(New SqlParameter("@RADAR_NO", intRadar))

            '    If txtHeight.Text = "" Then
            '        .Add(New SqlParameter("@HEIGHT", DBNull.Value))
            '    Else
            '        .Add(New SqlParameter("@HEIGHT", CInt(txtHeight.Text)))
            '    End If

            '    If txtWeight.Text = "" Then
            '        .Add(New SqlParameter("@WEIGHT", DBNull.Value))

            '    Else
            '        .Add(New SqlParameter("@WEIGHT", txtWeight.Text))

            '    End If

            '    If txtBPSys.Text = "" Then
            '        .Add(New SqlParameter("@SYS_BP", DBNull.Value))
            '    Else
            '        .Add(New SqlParameter("@SYS_BP", txtBPSys.Text))
            '    End If

            '    If txtBPDia.Text = "" Then
            '        .Add(New SqlParameter("@DIA_BP", DBNull.Value))
            '    Else
            '        .Add(New SqlParameter("@DIA_BP", txtBPDia.Text))
            '    End If

            '    If intMap = 0 Then
            '        .Add(New SqlParameter("@MAP_BP", DBNull.Value))
            '    Else
            '        .Add(New SqlParameter("@MAP_BP", intMap))
            '    End If

            '    'If DropDownList6.SelectedValue = "" Then
            '    .Add(New SqlParameter("@PHENOTYPE1", DBNull.Value))
            '    'Else
            '    '    .Add(New SqlParameter("@PHENOTYPE1", DropDownList6.SelectedValue))
            '    'End If

            '    'If DropDownList7.SelectedValue = "" Then
            '    .Add(New SqlParameter("@PHENOTYPE2", DBNull.Value))
            '    'Else
            '    '    .Add(New SqlParameter("@PHENOTYPE2", DropDownList7.SelectedValue))
            '    'End If

            '    'If DropDownList8.SelectedValue = "" Then
            '    .Add(New SqlParameter("@PHENOTYPE3", DBNull.Value))
            '    'Else
            '    '    .Add(New SqlParameter("@PHENOTYPE3", DropDownList8.SelectedValue))
            '    'End If

            '    'If DropDownList9.SelectedValue = "" Then
            '    .Add(New SqlParameter("@PHENOTYPE4", DBNull.Value))
            '    'Else
            '    '    .Add(New SqlParameter("@PHENOTYPE4", DropDownList9.SelectedValue))
            '    'End If

            '    If txtComments.Text = "" Then
            '        .Add(New SqlParameter("@COMMENTS", DBNull.Value))
            '    Else
            '        .Add(New SqlParameter("@COMMENTS", txtComments.Text))
            '    End If

            'If radTNo.Checked = True Then
            '    .Add(New SqlParameter("@THROMBOSIS", False))
            '    .Add(New SqlParameter("@THROMBOSIS_DETAIL", DBNull.Value))
            'ElseIf radTYes.Checked = True Then
            '    .Add(New SqlParameter("@THROMBOSIS", True))
            '    .Add(New SqlParameter("@THROMBOSIS_DETAIL", txtThrombosis.Text))
            'Else
            '    .Add(New SqlParameter("@THROMBOSIS_DETAIL", DBNull.Value))
            '    .Add(New SqlParameter("@THROMBOSIS", DBNull.Value))
            'End If

            '    If radYes.Checked = True Then
            '        .Add(New SqlParameter("@INFECTION", True))
            '        If txtINFECTION_TYPE.Text = "" Then
            '            .Add(New SqlParameter("@INFECTION_TYPE", DBNull.Value))
            '        Else
            '            .Add(New SqlParameter("@INFECTION_TYPE", txtINFECTION_TYPE.Text))
            '        End If

            '        If txtINFECTION_DETAIL.Text = "" Then
            '            .Add(New SqlParameter("@INFECTION_DETAIL", DBNull.Value))
            '        Else
            '            .Add(New SqlParameter("@INFECTION_DETAIL", txtINFECTION_DETAIL.Text))
            '        End If

            '    ElseIf radNo.Checked = True Then
            '        .Add(New SqlParameter("@INFECTION", False))
            '        .Add(New SqlParameter("@INFECTION_DETAIL", DBNull.Value))
            '        .Add(New SqlParameter("@INFECTION_TYPE", DBNull.Value))
            '    Else
            '        .Add(New SqlParameter("@INFECTION", DBNull.Value))
            '        .Add(New SqlParameter("@INFECTION_DETAIL", DBNull.Value))
            '        .Add(New SqlParameter("@INFECTION_TYPE", DBNull.Value))
            '    End If


            '    If radTxYes.Checked = True Then
            '        .Add(New SqlParameter("@TX_LISTED", True))
            '    ElseIf radTxNo.Checked = True Then
            '        .Add(New SqlParameter("@TX_LISTED", False))
            '    Else
            '        .Add(New SqlParameter("@TX_LISTED", DBNull.Value))
            '    End If

            '    .Add(New SqlParameter("@DATE_CLIN_PIC", dtClinPic))
            '    .Add(New SqlParameter("@SEQ_NO", txtSEQ_NO.Text))

            'End With

            With objCommand.Parameters

                .Add(New System.Data.SqlClient.SqlParameter("@DIALYSIS_REQ", DBNull.Value))
                .Add(New System.Data.SqlClient.SqlParameter("@DATE_BX", DBNull.Value))
                .Add(New System.Data.SqlClient.SqlParameter("@URTICARIA", DBNull.Value))

                If txtHeight.Text = "" Then
                    .Add(New System.Data.SqlClient.SqlParameter("@HEIGHT", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@HEIGHT", CInt(txtHeight.Text)))
                End If

                If txtWeight.Text = "" Then
                    .Add(New System.Data.SqlClient.SqlParameter("@WEIGHT", DBNull.Value))

                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@WEIGHT", txtWeight.Text))

                End If

                If txtBPSys.Text = "" Then
                    .Add(New System.Data.SqlClient.SqlParameter("@SYS_BP", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@SYS_BP", txtBPSys.Text))
                End If

                If txtBPDia.Text = "" Then
                    .Add(New System.Data.SqlClient.SqlParameter("@DIA_BP", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@DIA_BP", txtBPDia.Text))
                End If

                If intMap = 0 Then
                    .Add(New System.Data.SqlClient.SqlParameter("@MAP_BP", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@MAP_BP", intMap))
                End If

                'If DropDownList1.SelectedValue = "" Then
                '    .Add(New System.Data.SqlClient.SqlParameter("@INFECTION", DBNull.Value))
                'Else
                '    .Add(New System.Data.SqlClient.SqlParameter("@INFECTION", DropDownList1.SelectedValue))
                'End If

                'If txtInfectDetails.Text = "" Then
                '    .Add(New System.Data.SqlClient.SqlParameter("@INFECTION_DETAIL", DBNull.Value))
                'Else
                '    .Add(New System.Data.SqlClient.SqlParameter("@INFECTION_DETAIL", txtInfectDetails.Text))
                'End If

                If DropDownList2.SelectedValue = "" Then
                    .Add(New System.Data.SqlClient.SqlParameter("@COURSE_DIS", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@COURSE_DIS", DropDownList2.SelectedValue))
                End If

                If txtRashDetail.Text = "" Then
                    .Add(New System.Data.SqlClient.SqlParameter("@RASH_DETAIL", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@RASH_DETAIL", txtRashDetail.Text))
                End If

                If radRashUkn.Checked Then
                    .Add(New System.Data.SqlClient.SqlParameter("@RASH", DBNull.Value))
                ElseIf radRashYes.Checked = True Then
                    .Add(New System.Data.SqlClient.SqlParameter("@RASH", True))
                ElseIf radRashNo.Checked = True Then
                    .Add(New System.Data.SqlClient.SqlParameter("@RASH", False))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@RASH", False))
                End If

                If (radHyperYes.Checked Or radHyperYes1.Checked) Then
                    .Add(New System.Data.SqlClient.SqlParameter("@HTH_REQ_TMT", True))
                ElseIf (radHyperNo.Checked Or radHyperNo1.Checked) Then
                    .Add(New System.Data.SqlClient.SqlParameter("@HTH_REQ_TMT", False))
                ElseIf (radHyperUn.Checked Or radHyperUn1.Checked) Then
                    .Add(New System.Data.SqlClient.SqlParameter("@HTH_REQ_TMT", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@HTH_REQ_TMT", DBNull.Value))
                End If




                If DropDownList4.SelectedValue = "" Then
                    .Add(New System.Data.SqlClient.SqlParameter("@DIABETES", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@DIABETES", DropDownList4.SelectedValue))
                End If

                If DropDownList6.SelectedValue = "" Then
                    .Add(New System.Data.SqlClient.SqlParameter("@PHENOTYPE1", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@PHENOTYPE1", DropDownList6.SelectedValue))
                End If

                If DropDownList7.SelectedValue = "" Then
                    .Add(New System.Data.SqlClient.SqlParameter("@PHENOTYPE2", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@PHENOTYPE2", DropDownList7.SelectedValue))
                End If

                If DropDownList8.SelectedValue = "" Then
                    .Add(New System.Data.SqlClient.SqlParameter("@PHENOTYPE3", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@PHENOTYPE3", DropDownList8.SelectedValue))
                End If

                If DropDownList9.SelectedValue = "" Then
                    .Add(New System.Data.SqlClient.SqlParameter("@PHENOTYPE4", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@PHENOTYPE4", DropDownList9.SelectedValue))
                End If




                If txtComments.Text = "" Then
                    .Add(New System.Data.SqlClient.SqlParameter("@COMMENTS", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@COMMENTS", txtComments.Text))
                End If

                'If DropDownList6.SelectedValue = "" Then
                '    .Add(New System.Data.SqlClient.SqlParameter("@DIALYSIS_REQ", DBNull.Value))
                'Else
                '    .Add(New System.Data.SqlClient.SqlParameter("@DIALYSIS_REQ", DropDownList6.SelectedValue))
                'End If

                If radOedemaYes.Checked Then
                    .Add(New System.Data.SqlClient.SqlParameter("@OEDEMA", True))
                ElseIf radOedemaNo.Checked Then
                    .Add(New System.Data.SqlClient.SqlParameter("@OEDEMA", False))
                ElseIf radOedemaUnkn.Checked Then
                    .Add(New System.Data.SqlClient.SqlParameter("@OEDEMA", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@OEDEMA", DBNull.Value))
                End If

                If radHypovYes.Checked Then
                    .Add(New System.Data.SqlClient.SqlParameter("@HYPOVAL", True))
                ElseIf radHypovNo.Checked Then
                    .Add(New System.Data.SqlClient.SqlParameter("@HYPOVAL", False))
                ElseIf radHypovUn.Checked Then
                    .Add(New System.Data.SqlClient.SqlParameter("@HYPOVAL", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@HYPOVAL", DBNull.Value))
                End If

                If radFeverYes.Checked Then
                    .Add(New System.Data.SqlClient.SqlParameter("@FEVER", True))
                ElseIf radFeverNo.Checked Then
                    .Add(New System.Data.SqlClient.SqlParameter("@FEVER", False))
                ElseIf radFeverUn.Checked Then
                    .Add(New System.Data.SqlClient.SqlParameter("@FEVER", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@FEVER", DBNull.Value))
                End If

                If radThrombosisYes.Checked Then
                    .Add(New System.Data.SqlClient.SqlParameter("@THROMBOSIS", True))
                ElseIf radThrombosisNo.Checked Then
                    .Add(New System.Data.SqlClient.SqlParameter("@THROMBOSIS", False))
                ElseIf radThrombosisUn.Checked Then
                    .Add(New System.Data.SqlClient.SqlParameter("@THROMBOSIS", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@THROMBOSIS", DBNull.Value))
                End If

                If radPeritYes.Checked Then
                    .Add(New System.Data.SqlClient.SqlParameter("@PERITONITIS", True))
                ElseIf radPeritNo.Checked Then
                    .Add(New System.Data.SqlClient.SqlParameter("@PERITONITIS", False))
                ElseIf radPeritUn.Checked Then
                    .Add(New System.Data.SqlClient.SqlParameter("@PERITONITIS", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@PERITONITIS", DBNull.Value))
                End If

                If radPulYes.Checked Then
                    .Add(New System.Data.SqlClient.SqlParameter("@PUL_OED", True))
                ElseIf radPulNo.Checked Then
                    .Add(New System.Data.SqlClient.SqlParameter("@PUL_OED", False))
                ElseIf radPulUn.Checked Then
                    .Add(New System.Data.SqlClient.SqlParameter("@PUL_OED", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@PUL_OED", DBNull.Value))
                End If


                '# removed 11-Mar-10
                'If radImmTrigYes.Checked Then
                '    .Add(New System.Data.SqlClient.SqlParameter("@IMMUNIS_TRIGGER", True))
                'ElseIf radImmTrigNo.Checked Then
                '    .Add(New System.Data.SqlClient.SqlParameter("@IMMUNIS_TRIGGER", False))
                'ElseIf radImmTrigUn.Checked Then
                '    .Add(New System.Data.SqlClient.SqlParameter("@IMMUNIS_TRIGGER", DBNull.Value))
                'Else
                .Add(New System.Data.SqlClient.SqlParameter("@IMMUNIS_TRIGGER", DBNull.Value))
                'End If

                If radLipodYes.Checked Then
                    .Add(New System.Data.SqlClient.SqlParameter("@PART_LIPODYS", True))
                ElseIf radLipodNo.Checked Then
                    .Add(New System.Data.SqlClient.SqlParameter("@PART_LIPODYS", False))
                ElseIf radLipodUn.Checked Then
                    .Add(New System.Data.SqlClient.SqlParameter("@PART_LIPODYS", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@PART_LIPODYS", DBNull.Value))
                End If


                .Add(New System.Data.SqlClient.SqlParameter("@ANAEMIA", DBNull.Value))
                '.Add(New System.Data.SqlClient.SqlParameter("@FEVER", chkFever.Checked))

                '.Add(New System.Data.SqlClient.SqlParameter("@HYPOVAL", chkHypo.Checked))
                '.Add(New System.Data.SqlClient.SqlParameter("@THROMBOSIS", chkThrombosis.Checked))
                '.Add(New System.Data.SqlClient.SqlParameter("@PERITONITIS", chkPeritonitis.Checked))
                '.Add(New System.Data.SqlClient.SqlParameter("@PUL_OED", chkPulmonary.Checked))
                '.Add(New System.Data.SqlClient.SqlParameter("@IMMUNIS_TRIGGER", chkImmTrigger.Checked))
                '.Add(New System.Data.SqlClient.SqlParameter("@STEROID_RESIST", chkSteroid.Checked))

                If radOpthalmYes.Checked = True Then
                    .Add(New System.Data.SqlClient.SqlParameter("@OPTHALM", True))
                ElseIf radOpthalmNo.Checked = True Then
                    .Add(New System.Data.SqlClient.SqlParameter("@OPTHALM", False))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@OPTHALM", DBNull.Value))
                End If

                If radPrecInfectYes.Checked Then
                    .Add(New System.Data.SqlClient.SqlParameter("@PREC_INF", True))
                ElseIf radPrecInfectNo.Checked = True Then
                    .Add(New System.Data.SqlClient.SqlParameter("@PREC_INF", False))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@PREC_INF", DBNull.Value))
                End If

                .Add(New System.Data.SqlClient.SqlParameter("@RADAR_NO", intRadar))
                .Add(New System.Data.SqlClient.SqlParameter("@DATE_CLIN_PIC", dtClinPic))
                .Add(New System.Data.SqlClient.SqlParameter("@cID", lblID.Text))

                If radChronicYes.Checked Then
                    .Add(New System.Data.SqlClient.SqlParameter("@CLIN_EV_CHR_INF", True))
                    .Add(New System.Data.SqlClient.SqlParameter("@CLIN_EV_CHR_INF_DETAIL", txtCLIN_EV_CHR_INF_DETAIL.Text))
                ElseIf radChronicNo.Checked Then
                    .Add(New System.Data.SqlClient.SqlParameter("@CLIN_EV_CHR_INF", False))
                    .Add(New System.Data.SqlClient.SqlParameter("@CLIN_EV_CHR_INF_DETAIL", DBNull.Value))
                ElseIf radChronicUn.Checked Then
                    .Add(New System.Data.SqlClient.SqlParameter("@CLIN_EV_CHR_INF", DBNull.Value))
                    .Add(New System.Data.SqlClient.SqlParameter("@CLIN_EV_CHR_INF_DETAIL", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@CLIN_EV_CHR_INF", DBNull.Value))
                    .Add(New System.Data.SqlClient.SqlParameter("@CLIN_EV_CHR_INF_DETAIL", DBNull.Value))
                End If




                If (radOpthalmYes.Checked = False) Then
                    .Add(New System.Data.SqlClient.SqlParameter("@OPTHALM_DETAIL", DBNull.Value))
                ElseIf txtOpDetail.Text = "" Then
                    .Add(New System.Data.SqlClient.SqlParameter("@OPTHALM_DETAIL", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@OPTHALM_DETAIL", txtOpDetail.Text))
                End If

                If (radPrecInfectYes.Checked) Then
                    .Add(New System.Data.SqlClient.SqlParameter("@PREC_INF_DETAIL", txtPREC_INF_DETAIL.Text))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@PREC_INF_DETAIL", DBNull.Value))
                End If

                If txtSigDiag1.Text = "" Then
                    .Add(New System.Data.SqlClient.SqlParameter("@SIG_DIAG1", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@SIG_DIAG1", txtSigDiag1.Text))
                End If

                If txtSigDiag2.Text = "" Then
                    .Add(New System.Data.SqlClient.SqlParameter("@SIG_DIAG2", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@SIG_DIAG2", txtSigDiag2.Text))
                End If

                If radCompInfectNo.Checked Then
                    .Add(New System.Data.SqlClient.SqlParameter("@INFECTION", False))
                    .Add(New System.Data.SqlClient.SqlParameter("@INFECTION_TYPE", DBNull.Value))
                    .Add(New System.Data.SqlClient.SqlParameter("@INFECTION_DETAIL", DBNull.Value))
                ElseIf radCompInfectYes.Checked Then
                    .Add(New System.Data.SqlClient.SqlParameter("@INFECTION", True))
                    .Add(New System.Data.SqlClient.SqlParameter("@INFECTION_TYPE", DBNull.Value))
                    .Add(New System.Data.SqlClient.SqlParameter("@INFECTION_DETAIL", txtINFECTION_DETAIL.Text))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@INFECTION", DBNull.Value))
                    .Add(New System.Data.SqlClient.SqlParameter("@INFECTION_TYPE", DBNull.Value))
                    .Add(New System.Data.SqlClient.SqlParameter("@INFECTION_DETAIL", DBNull.Value))
                End If

                If radTYes.Checked Then
                    .Add(New SqlParameter("@COMP_THROMBOSIS", True))
                    .Add(New SqlParameter("@COMP_THROMBOSIS_DETAIL", txtCOMP_THROMBOSIS_DETAIL.Text))
                ElseIf radTNo.Checked Then
                    .Add(New SqlParameter("@COMP_THROMBOSIS", False))
                    .Add(New SqlParameter("@COMP_THROMBOSIS_DETAIL", DBNull.Value))
                Else
                    .Add(New SqlParameter("@COMP_THROMBOSIS", DBNull.Value))
                    .Add(New SqlParameter("@COMP_THROMBOSIS_DETAIL", DBNull.Value))
                End If

                If radCKD1.Checked Then
                    .Add(New SqlParameter("@CKD_STAGE", 1))
                ElseIf radCKD2.Checked Then
                    .Add(New SqlParameter("@CKD_STAGE", 2))
                ElseIf radCKD3.Checked Then
                    .Add(New SqlParameter("@CKD_STAGE", 3))
                ElseIf radCKD4.Checked Then
                    .Add(New SqlParameter("@CKD_STAGE", 4))
                ElseIf radCKD5.Checked Then
                    .Add(New SqlParameter("@CKD_STAGE", 5))
                ElseIf radCKDUn.Checked Then
                    .Add(New SqlParameter("@CKD_STAGE", 0))
                Else
                    .Add(New SqlParameter("@CKD_STAGE", DBNull.Value))
                End If

                If radTxYes.Checked Then
                    .Add(New System.Data.SqlClient.SqlParameter("@TX_LISTED", True))
                ElseIf radTxNo.Checked Then
                    .Add(New System.Data.SqlClient.SqlParameter("@TX_LISTED", False))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@TX_LISTED", DBNull.Value))
                End If

                .Add(New SqlParameter("@SEQ_NO", txtSEQ_NO.Text))

            End With

            objConnect.Open()
            objCommand.ExecuteNonQuery()

        Catch objError As Exception

            lblDebug.Text = String.Format("An error occurred (Save): '{0}'{1}", objError.Message, strSQL)
            objConnect.Close()
            Exit Sub

        Finally

            objConnect.Close()

        End Try

        lblUpdate.Text = String.Format("<strong>Update successful:</strong>&nbsp;&nbsp;{0}", Now().ToShortTimeString)

        btnSaveNew.Visible = False
        btnUpdate.Visible = True
        ddlRefresh(DropDownList10)

        PageFill(txtSEQ_NO.Text)


    End Sub

    Protected Sub DropDownList10_SelectedIndexChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles DropDownList10.SelectedIndexChanged

        Dim intRecord As Integer = DropDownList10.SelectedValue
        Session("dtRecord") = CDate((DropDownList10.SelectedItem).ToString)
        pnlAdd.Visible = True
        'DropDownList6.DataBind()
        'DropDownList7.DataBind()
        'DropDownList8.DataBind()
        'DropDownList9.DataBind()
        lblDebug.Text = ""
        PageFill(intRecord)
        DropDownList10.SelectedIndex = 0
        lblUpdate.Text = ""
        btnSaveNew.Visible = False
        btnUpdate.Visible = True

    End Sub

    Shared Function chkNull(ByVal value As Object) As String

        If value Is DBNull.Value Then
            chkNull = ""
        Else
            chkNull = value.ToString
        End If

    End Function

    Shared Sub ddlRefresh(ByVal ddl As DropDownList)

        ddl.Items.Clear()
        'ddl.DataBind()

        Dim li As New System.Web.UI.WebControls.ListItem() With {.Value = "", .Text = "Select"}
        ddl.Items.Insert(0, li)   'Add(li)
        ddl.AppendDataBoundItems = True

    End Sub

    Function chkDate(ByVal dt As Date) As Boolean

        Dim strSQL As String = "SELECT RADAR_NO FROM tbl_ClinicalData WHERE DATE_CLIN_PIC = Convert(DATETIME, '" & dt & "',103)"
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


    Protected Sub radYes_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radCompInfectYes.CheckedChanged
        If radCompInfectYes.Checked = True Then
            rowDetails.Visible = True
            rowType.Visible = True
        Else
            rowDetails.Visible = False
            rowType.Visible = False
        End If

    End Sub

    Protected Sub radNo_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radCompInfectNo.CheckedChanged
        If radCompInfectNo.Checked = True Then
            rowDetails.Visible = False
            rowType.Visible = False
            txtINFECTION_DETAIL.Text = ""
        Else
            rowDetails.Visible = True
            rowType.Visible = True
        End If
    End Sub

    Protected Sub radTNo_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radTNo.CheckedChanged
        If radTNo.Checked = True Then
            rowCOMP_THROMBOSIS_DETAIL.Visible = False
            txtCOMP_THROMBOSIS_DETAIL.Text = ""
        Else
            rowCOMP_THROMBOSIS_DETAIL.Visible = True

        End If
    End Sub


    Protected Sub radTYes_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radTYes.CheckedChanged
        If radTYes.Checked = True Then
            rowCOMP_THROMBOSIS_DETAIL.Visible = True

        Else
            rowCOMP_THROMBOSIS_DETAIL.Visible = False

        End If
    End Sub

    Function GetSeqNo(ByVal dtEntry As Date) As Integer

        'Dim strSQL As String = "SELECT [SEQ_NO] FROM [tbl_ClinicalData] WHERE ([RADAR_NO] = '" & Session("patientID") & "' AND Dateadd(d, datediff(d,0,[DATE_CLIN_PIC]),0) = '" & dtEntry & "')"
        Dim strSQL As String = "SELECT [SEQ_NO] FROM [tbl_ClinicalData] WHERE ([RADAR_NO] = '" & Session("patientID") & "' AND [DATE_CLIN_PIC] = Convert(DATETIME, '" & dtEntry & "',103))"

        'Dim strSQL As String = "SELECT [SEQ_NO] FROM [tbl_ClinicalData] WHERE ([RADAR_NO] = '" & Session("patientID") & "' AND [DATE_CLINICAL_PIC] = (SELECT MAX([DATE_CLINICAL_PIC]) FROM [tbl_ClinicalData] WHERE [RADAR_NO] = '" & Session("patientID") & "'))"

        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Using objConnect As New SqlConnection(strConnect)
            Dim objCommand As New SqlCommand(strSQL, objConnect)
            Try
                Dim objDataReader As SqlDataReader
                objConnect.Open()
                objDataReader = objCommand.ExecuteReader()
                If objDataReader.Read() Then
                    GetSeqNo = objDataReader("SEQ_NO")
                    'lblDebug.Text = strSQL

                Else
                    GetSeqNo = 0
                    'lblDebug.Text = strSQL
                End If

            Catch objError As Exception
                lblDebug.Text = String.Format("An error occurred: '{0}'{1}", objError.Message, strSQL)
                GetSeqNo = 0
            Finally
                objConnect.Close()
            End Try
        End Using

    End Function

    Protected Sub radRashYes_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radRashYes.CheckedChanged
        If radRashYes.Checked = True Then
            rowRash.Visible = True
        Else
            rowRash.Visible = False
        End If
    End Sub

    Protected Sub radRashNo_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radRashNo.CheckedChanged
        If radRashNo.Checked = True Then
            rowRash.Visible = False
        End If
    End Sub

    Protected Sub radRashUkn_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radRashUkn.CheckedChanged
        If radRashUkn.Checked = True Then
            rowRash.Visible = False
        End If
    End Sub

    Protected Sub radOpthalmYes_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radOpthalmYes.CheckedChanged
        If radOpthalmYes.Checked Then
            txtOpDetail.Visible = True
        Else
            txtOpDetail.Visible = False
        End If
    End Sub

    Protected Sub radOpthalmNo_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radOpthalmNo.CheckedChanged
        txtOpDetail.Visible = False
    End Sub

    Protected Sub radOpthalmUn_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radOpthalmUn.CheckedChanged
        txtOpDetail.Visible = False
    End Sub

    Protected Sub radPrecInfectYes_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radPrecInfectYes.CheckedChanged


        rowPreceedDetail.Visible = True


    End Sub

    Protected Sub radPrecInfectNo_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radPrecInfectNo.CheckedChanged
        rowPreceedDetail.Visible = False
    End Sub

    Protected Sub radPrecInfectUn_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radPrecInfectUn.CheckedChanged
        rowPreceedDetail.Visible = False
    End Sub

    Protected Sub radChronicYes_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radChronicYes.CheckedChanged
        rowClinicalEvidenceDetail.Visible = True
    End Sub



    Protected Sub radChronicNo_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radChronicNo.CheckedChanged
        rowClinicalEvidenceDetail.Visible = False
    End Sub

    Protected Sub radChronicUn_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radChronicUn.CheckedChanged
        rowClinicalEvidenceDetail.Visible = False
    End Sub

   
    Protected Sub btnUpdate2_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnUpdate2.Click
        pageSave()
    End Sub
End Class
