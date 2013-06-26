Imports System.Data.SqlClient
Partial Class first_clinical
    Inherits System.Web.UI.Page
    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load

        If Not Session("blnAuthenticated") Then
            Response.Redirect("default.aspx")
        End If

        If Session("uType") = "p" Then
            btnSubmit.Visible = False
            CommonClass.DisableControls(mainForm)
            txtDateClinicalPicture.Enabled = False
            txtDateClinicalPicture_CalendarExtender.Enabled = False
            lnkTimelines.Visible = False
        Else
            lnkRelapse.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkDemographics.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnk3Months.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkLabResults.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkDiagnosis.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkFirstLab.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkFirstTreatment.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkPathology.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkHospital.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkTimelines.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkTimelines.Visible = False
        End If

        'ASPxDateEdit1.MaxDate = DateTime.Today
        
        If Not IsPostBack Then

            'check the Diagnosis has been entered before allowing data entry on this form
            Dim bolDiagCheck As Boolean = CheckDiagnosis(Session("patientID"))

            If bolDiagCheck = False Then

                btnSubmit.Enabled = False
                'ASPxPopupControl1.ShowOnPageLoad = True


            Else
                'ASPxPopupControl1.ShowOnPageLoad = False

                pagefill()

            End If

        End If

        'If IsPostBack Then
        
        'End If


    End Sub

    Shared Function chkNull(ByVal value As Object) As String

        If value Is DBNull.Value Then
            chkNull = ""
        Else
            chkNull = value.ToString
        End If

    End Function

    

    Sub pagefill()

        Dim TDES As New TripleDES()

        Dim patientID As Integer = Session("patientID")


        Dim strSQL As String = "SELECT tbl_ClinicalData.cID, tbl_ClinicalData.RADAR_NO, tbl_ClinicalData.DATE_CLIN_PIC, tbl_ClinicalData.PREC_INF, tbl_ClinicalData.PREC_INF_DETAIL, tbl_ClinicalData.CLIN_EV_CHR_INF, tbl_ClinicalData.CLIN_EV_CHR_INF_DETAIL, tbl_ClinicalData.PHENOTYPE1, tbl_ClinicalData.PHENOTYPE2, tbl_ClinicalData.PHENOTYPE3, tbl_ClinicalData.PHENOTYPE4, tbl_ClinicalData.HEIGHT, tbl_ClinicalData.WEIGHT, tbl_ClinicalData.DATE_BX, tbl_ClinicalData.DIALYSIS_REQ, tbl_ClinicalData.MAP_BP, tbl_ClinicalData.DIA_BP, tbl_ClinicalData.SYS_BP, tbl_ClinicalData.COURSE_DIS, tbl_ClinicalData.OEDEMA, tbl_ClinicalData.ANAEMIA, tbl_ClinicalData.HYPOVAL, tbl_ClinicalData.PERITONITIS, tbl_ClinicalData.THROMBOSIS, tbl_ClinicalData.FEVER, tbl_ClinicalData.URTICARIA, tbl_ClinicalData.DIABETES, tbl_ClinicalData.HTH_REQ_TMT, tbl_ClinicalData.PUL_OED, tbl_ClinicalData.COMMENTS, tbl_ClinicalData.IMMUNIS_TRIGGER, tbl_ClinicalData.OPTHALM_DETAIL, tbl_ClinicalData.OPTHALM, tbl_ClinicalData.RASH, tbl_ClinicalData.RASH_DETAIL, tbl_ClinicalData.PART_LIPODYS, tbl_Demographics.HOSP_NO, tbl_Demographics.FNAME, tbl_Demographics.DOB, tbl_Demographics.SNAME, tbl_Diagnosis.SIG_DIAG1, tbl_Diagnosis.SIG_DIAG2, tbl_Diagnosis.DIAG FROM tbl_ClinicalData INNER JOIN tbl_Demographics ON tbl_ClinicalData.RADAR_NO = tbl_Demographics.RADAR_NO INNER JOIN tbl_Diagnosis ON tbl_Demographics.RADAR_NO = tbl_Diagnosis.RADAR_NO WHERE (tbl_ClinicalData.RADAR_NO = " & Session("patientID") & " AND [SEQ_NO] = '1')"
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
                    Case False
                        radHyperNo.Checked = True
                    Case Else
                        radOpthalmUn.Checked = True
                End Select

                If objDataReader("RASH") Is DBNull.Value Then
                    rowRash.Visible = False
                    radRashUkn.Checked = True
                ElseIf objDataReader("RASH") = True Then
                    rowRash.Visible = True
                    radRashYes.Checked = True
                ElseIf objDataReader("RASH") = False Then
                    rowRash.Visible = False
                    radRashNo.Checked = True
                End If

                If objDataReader("DATE_CLIN_PIC") Is DBNull.Value Then
                    'ASPxDateEdit1.Date = DateTime.MinValue
                    lnk3Months.Enabled = False
                    lnkPathology.Enabled = False
                    lnkHospital.Enabled = False
                    lnkTimelines.Enabled = False
                    lnkRelapse.Enabled = False
                Else
                    txtDateClinicalPicture.Text = objDataReader("DATE_CLIN_PIC")
                    lnk3Months.Enabled = True
                    lnkPathology.Enabled = True
                    lnkHospital.Enabled = True
                    lnkTimelines.Enabled = True
                    lnkRelapse.Enabled = True
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

                Select Case objDataReader("IMMUNIS_TRIGGER").ToString
                    Case False
                        radImmTrigNo.Checked = True
                    Case True
                        radImmTrigYes.Checked = True
                    Case Else
                        radImmTrigUn.Checked = True
                End Select

                Select Case objDataReader("PART_LIPODYS").ToString
                    Case False
                        radLipodNo.Checked = True
                    Case True
                        radLipodYes.Checked = True
                    Case Else
                        radLipodUn.Checked = True
                End Select

                'chkAnaemia.Checked = objDataReader("ANAEMIA")
                'chkHypo.Checked = objDataReader("HYPOVAL")
                'chkFever.Checked = objDataReader("FEVER")
                'chkThrombosis.Checked = objDataReader("THROMBOSIS")
                'chkPeritonitis.Checked = objDataReader("PERITONITIS")
                'chkPulmonary.Checked = objDataReader("PUL_OED")
                'chkImmTrigger.Checked = objDataReader("IMMUNIS_TRIGGER")
                'chkSteroid.Checked = objDataReader("STEROID_RESIST")

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

                Select Case objDataReader("HTH_REQ_TMT").ToString
                    Case False
                        radHyperNo.Checked = True
                    Case True
                        radHyperYes.Checked = True
                    Case Else
                        radHyperUn.Checked = True
                End Select



                txtOpDetail.Text = chkNull(objDataReader("OPTHALM_DETAIL"))
                lblID.Text = objDataReader("cID")


                If objDataReader("DIAG") Is DBNull.Value Then
                    txtDIAGNOSIS.Text = "-"
                Else
                    txtDIAGNOSIS.Text = CommonClass.GetDiagnosis(objDataReader("DIAG").ToString)
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
                        lblTitle.Text = "Clinical features - Current"
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
                        lblPage.Text = "3A"

                        txtInfectionDetail.Text = objDataReader("PREC_INF_DETAIL").ToString

                        If objDataReader("PREC_INF") Is DBNull.Value Then
                            radPrecInfectUn.Checked = True
                            rowPreceedDetail.Visible = False
                        ElseIf objDataReader("PREC_INF") = True Then
                            radPrecInfectYes.Checked = True
                            rowPreceedDetail.Visible = True
                        ElseIf objDataReader("PREC_INF") = False Then
                            rowPreceedDetail.Visible = False
                            radPeritNo.Checked = True
                        End If

                        txtClinicalEvidence.Text = objDataReader("CLIN_EV_CHR_INF_DETAIL").ToString

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

                    ElseIf objDataReader("DIAG") = 1 Then  ' FSGS

                        'lblU.Visible = True
                        lblRash.Visible = True
                        lblOpth.Visible = True
                        radOpthalmYes.Visible = True
                        'DropDownList5.Visible = True
                        txtRashDetail.Visible = True
                        rowAnemia.Visible = False
                        rowLipod.Visible = False
                        rowPreceed.Visible = False
                        rowPreceedDetail.Visible = False
                        rowClinicalEvidence.Visible = False
                        rowClinicalEvidenceDetail.Visible = False
                        rowPeritonitis.Visible = True
                        rowThrombosis.Visible = True
                        rowPulmonary.Visible = True
                        rowDiabetes.Visible = True
                        lblTitle.Text = "Clinical Features at Diagnosis"
                        lblCourseDisease.Text = ""
                        DropDownList2.Visible = False
                        lblPage.Text = "3B"

                    End If

                End If

            End If

        Catch objError As Exception

            lblDebug.Text = "An error occurred (PageFill): '" & objError.Message & "'" '& strSQL


        Finally

            objConnect.Close()

        End Try
    End Sub

    Protected Sub btnSubmit_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnSubmit.Click
        pageSave()
    End Sub

    Sub pageSave()

        'clear debug message
        lblDebug.Text = ""

        Dim intID As Integer = CInt(lblID.Text)

        Dim intRadar = CInt(txtRADAR_NO.Text)
        Dim dtClinPic As Date = CDate(txtDateClinicalPicture.Text)
        'Dim dtBiop As Date = ASPxDateEdit2.Date

        Dim intMap As Integer

        If txtBPSys.Text <> "" And txtBPDia.Text <> "" Then

            Dim intSys As Integer = CInt(txtBPSys.Text)
            Dim intDia As Integer = CInt(txtBPDia.Text)
            intMap = ((2 * intDia) + intSys) / 3

        Else
            intMap = 0
        End If


        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim strSQL As String = "UPDATE [tbl_ClinicalData] SET [RADAR_NO] = @RADAR_NO, [DATE_CLIN_PIC] = @DATE_CLIN_PIC, [HEIGHT] = @HEIGHT, [WEIGHT] = @WEIGHT, [MAP_BP] = @MAP_BP, [DIA_BP] = @DIA_BP, [SYS_BP] = @SYS_BP, [COURSE_DIS] = @COURSE_DIS, [PERITONITIS] = @PERITONITIS, [FEVER] = @FEVER, [THROMBOSIS] = @THROMBOSIS, [HYPOVAL] = @HYPOVAL, [ANAEMIA] = @ANAEMIA, [OEDEMA] = @OEDEMA, [DIABETES] = @DIABETES, [HTH_REQ_TMT] = @HTH_REQ_TMT, [PUL_OED] = @PUL_OED, [COMMENTS] = @COMMENTS, [IMMUNIS_TRIGGER] = @IMMUNIS_TRIGGER, [OPTHALM_DETAIL] = @OPTHALM_DETAIL, [OPTHALM] = @OPTHALM, [RASH] = @RASH, [RASH_DETAIL] = @RASH_DETAIL, [PART_LIPODYS] = @PART_LIPODYS, [PHENOTYPE1]= @PHENOTYPE1, [PHENOTYPE2]= @PHENOTYPE2, [PHENOTYPE3]= @PHENOTYPE3, [PHENOTYPE4]= @PHENOTYPE4, [PREC_INF] = @PREC_INF, [PREC_INF_DETAIL] = @PREC_INF_DETAIL, [CLIN_EV_CHR_INF] = @CLIN_EV_CHR_INF, [CLIN_EV_CHR_INF_DETAIL] = @CLIN_EV_CHR_INF_DETAIL WHERE [cID] = @cID AND [SEQ_NO] = '1'; UPDATE tbl_Diagnosis SET [SIG_DIAG1] = @SIG_DIAG1, [SIG_DIAG2] = @SIG_DIAG2 WHERE [RADAR_NO] = @RADAR_NO "
        'Dim strSQL As String = "UPDATE [tbl_ClinicalData] SET [RADAR_NO] = @RADAR_NO, [DATE_CLIN_PIC] = @DATE_CLIN_PIC, [WEIGHT] = @WEIGHT  WHERE [cID] = @cID"
        Dim objConnect As New System.Data.SqlClient.SqlConnection(strConnect)
        Dim objCommand As New System.Data.SqlClient.SqlCommand(strSQL, objConnect)

        With objCommand.Parameters

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

            If intmap = 0 Then
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

            If radHyperYes.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@HTH_REQ_TMT", True))
            ElseIf radHyperNo.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@HTH_REQ_TMT", False))
            ElseIf radHyperUn.Checked Then
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
            End If

            If radHypovYes.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@HYPOVAL", True))
            ElseIf radHypovNo.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@HYPOVAL", False))
            ElseIf radHypovUn.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@HYPOVAL", DBNull.Value))
            End If

            If radFeverYes.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@FEVER", True))
            ElseIf radFeverNo.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@FEVER", False))
            ElseIf radFeverUn.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@FEVER", DBNull.Value))
            End If

            If radThrombosisYes.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@THROMBOSIS", True))
            ElseIf radThrombosisNo.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@THROMBOSIS", False))
            ElseIf radThrombosisUn.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@THROMBOSIS", DBNull.Value))
            End If

            If radPeritYes.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@PERITONITIS", True))
            ElseIf radPeritNo.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@PERITONITIS", False))
            ElseIf radPeritUn.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@PERITONITIS", DBNull.Value))
            End If

            If radPulYes.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@PUL_OED", True))
            ElseIf radPulNo.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@PUL_OED", False))
            ElseIf radPulUn.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@PUL_OED", DBNull.Value))
            End If

            If radImmTrigYes.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@IMMUNIS_TRIGGER", True))
            ElseIf radImmTrigNo.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@IMMUNIS_TRIGGER", False))
            ElseIf radImmTrigUn.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@IMMUNIS_TRIGGER", DBNull.Value))
            End If

            If radLipodYes.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@PART_LIPODYS", True))
            ElseIf radLipodNo.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@PART_LIPODYS", False))
            ElseIf radLipodUn.Checked Then
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
                .Add(New System.Data.SqlClient.SqlParameter("@CLIN_EV_CHR_INF_DETAIL", txtClinicalEvidence.Text))
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
                .Add(New System.Data.SqlClient.SqlParameter("@PREC_INF_DETAIL", txtInfectionDetail.Text))
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


        End With



        Try
            objConnect.Open()
            objCommand.ExecuteNonQuery()

        Catch objError As Exception

            lblDebug.Text = "An error occurred: (Update) '" & objError.Message & "'"
            objConnect.Close()
            Exit Sub

        Finally
            objConnect.Close()
        End Try

        lblUpdate.Text = "<strong>Update successful:</strong>&nbsp;&nbsp;" & Now().ToShortTimeString
        lblUpdate2.Text = "<strong>Updated: </strong>&nbsp;" & Now().ToShortTimeString
        'lblUpdate.Text = dtClinPic

        pagefill()

    End Sub


   

    Function CheckDiagnosis(ByVal patientID As Integer) As Boolean

        Dim strSQL As String = "SELECT [DATE_DIAG] FROM [tbl_Diagnosis] WHERE [RADAR_NO] = " & Session("patientID")
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New System.Data.SqlClient.SqlConnection(strConnect)
        Dim objCommand As New System.Data.SqlClient.SqlCommand(strSQL, objConnect)

        Try

            Dim objDataReader As SqlDataReader
            objConnect.Open()
            objDataReader = objCommand.ExecuteReader()

            If objDataReader.Read() Then
                If objDataReader("DATE_DIAG") IsNot DBNull.Value Then
                    CheckDiagnosis = True
                Else
                    CheckDiagnosis = False
                End If
            End If

        Catch objError As Exception

            lblDebug.Text = "An error occurred: '" & objError.Message & "'" & strSQL
            CheckDiagnosis = False

        Finally

            objConnect.Close()
        End Try


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

    Protected Sub btnSubmit2_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnSubmit2.Click
        pageSave()
    End Sub
End Class
