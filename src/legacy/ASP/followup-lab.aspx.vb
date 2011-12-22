Imports System.Data.SqlClient
Imports ConfigurationAlias = System.Configuration

Partial Class followup_lab
    Inherits System.Web.UI.Page

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load

        If Not Session("blnAuthenticated") Then
            Response.Redirect("default.aspx")
        End If

        If Session("diag") = 1 Then
            lblPage.Text = "7B"
        ElseIf Session("diag") = 2 Then
            lblPage.Text = "7A"
        End If

        If Session("uType") = "p" Then
            btnUpdate.Visible = False
            btnAdd.Visible = False
            CommonClass.DisableControls(tblMain)
            rowHIV.Visible = False
        Else
            lnkRelapse.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkDemographics.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnk3Months.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkLabResults.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkDiagnosis.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkFollowupClinical.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            'lnkFollowupLab.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkFollowupTreatment.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkTherapy.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkTimelines.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkPathology.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkTimelines.Visible = False
        End If

        'ASPxDateEdit1.MaxDate = DateTime.Today

        If Not IsPostBack Then

            'check the Diagnosis has been entered before allowing data entry on this form
            Dim bolDiagCheck As Boolean = CheckDiagnosis(Session("patientID"))

            If bolDiagCheck = False Then

                btnUpdate.Enabled = False
                'ASPxPopupControl1.ShowOnPageLoad = True


            Else
                'ASPxPopupControl1.ShowOnPageLoad = False

            End If

            If Session("dtRecord").ToString <> "" Then
                Dim intSeqNo As Integer = GetSeqNo(Session("dtRecord"))
                If intSeqNo = 0 Then
                    'do nothing
                Else
                    pagefill(intSeqNo)
                    DropDownList4.SelectedIndex = 0
                    btnSaveNew.Visible = False
                    btnUpdate.Visible = True
                    pnlAdd.Visible = True
                End If
            End If

        End If

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
        btnUpdate.Visible = False
        btnSaveNew.Visible = True

        txtALB_CREAT_RAT.Text = ""
        txtALBUMIN.Text = ""
        txtANTI_CLQ.Text = ""

        txtANTI_STREP_O.Text = ""
        txtCHOL_HDL.Text = ""
        txtCHOL_LDL.Text = ""
        txtCHOL_TOTAL.Text = ""
        txtCOMP_C3.Text = ""
        txtCOMP_C4.Text = ""
        txtCOMP_OTHER.Text = ""
        'txtCREAT_CLEAR_24.Text = ""
        'txtCREAT_CLEAR_RADIO.Text = ""
        txtCREAT_CLEAR_SCHZ.Text = ""
        txtCreatSer.Text = ""
        txtCRP.Text = ""
        txtDateLab.Text = ""
        'txtDNA_ANTI_DS.Text = ""
        'radAntiBND.Checked = False
        'radAntiBNo.Checked = False
        'radAntiBYes.Checked = False
        radNitriteND.Checked = False
        radNitriteNo.Checked = False
        radNitriteYes.Checked = False
        txtPHOS.Text = ""
        txtPROTEIN.Text = ""
        txtSODIUM.Text = ""
        txtWBC.Text = ""
        txtHB.Text = ""
        txtNEUTRO.Text = ""
        txtPLATELETS.Text = ""
        txtUREA_SER.Text = ""
        txtFERRITIN.Text = ""
        txtTRIG.Text = ""
        txtTHYROX.Text = ""
        txtTSH.Text = ""
        txtINR.Text = ""
        txtPOTASSIUM.Text = ""
        txtUR_VOL_24H.Text = ""
        txtPROT_CREAT_RAT.Text = ""
        txtOSMOLALITY.Text = ""
        txtIGA.Text = ""
        txtIGG.Text = ""
        txtIGM.Text = ""
        txtCOMP_C3.Text = ""
        txtCOMP_C4.Text = ""
        txtCOMP_OTHER.Text = ""
        chkBACT_URINE.Checked = False
        'chkLEUC_URINE.Checked = False
        'chkGLUC_URINE.Checked = False
        radLeucoND.Checked = False
        radLeucoNo.Checked = False
        radLeucoYes.Checked = False
        radGlucND.Checked = False
        radGlucNo.Checked = False
        radGlucYes.Checked = False
        chkCMV_SYM.Checked = False
        chkOTHER_INFECT.Checked = False
        DropDownList2.SelectedIndex = 0
        DropDownList3.SelectedIndex = 0
        DropDownList5.SelectedIndex = 0
        DropDownList9.SelectedIndex = 0
        DropDownList6.SelectedIndex = 0
        DropDownListCMV.SelectedIndex = 0
        DropDownListEBV.SelectedIndex = 0
        DropDownListParvo.SelectedIndex = 0
        DropDownList1.SelectedIndex = 0
        DropDownList10.SelectedIndex = 0
        DropDownList11.SelectedIndex = 0
        DropDownList12.SelectedIndex = 0
        DropDownList13.SelectedIndex = 0
        DropDownList15.SelectedIndex = 0
        DropDownList16.SelectedIndex = 0
        ddlProteinuria.SelectedIndex = 0
        ddlDysEryth.SelectedIndex = 0
        ddlRedCellCast.SelectedIndex = 0
        ddlWhiteCellCast.SelectedIndex = 0
        ddlANTI_DS_DNA.SelectedIndex = 0
        rowOther_Infect.Visible = False
        lblUpdate.Text = ""
        lblDebug.Text = ""


        If txtDIAGNOSIS.Text = "MPGN/DDD" Then

            txtTHYROX.Visible = True
            lblTHYROX.Visible = True

            'these no apply for both 18-Jul-09

            'txtIGG.Visible = False
            'lblIGG.Visible = False
            'txtIGA.Visible = False
            'lblIGA.Visible = False
            'txtIGM.Visible = False
            'lblIGM.Visible = False
            'rowIGG.Visible = False
            'rowIGA.Visible = False
            'rowIGM.Visible = False

            txtCRP.Visible = True
            lblCRP.Visible = True
            'txtANTI_STREP_O.Visible = False
            'lblANTI_STREP_O.Visible = False
            DropDownListParvo.Visible = True
            lblPARVO_ANTIB.Visible = True
            rowNitrite.Visible = True
            lblNITRITE.Visible = True
            rowBacteria.Visible = False
            rowOsmolarity.Visible = False
            rowParvoVirus.Visible = True

            rowPhosphate.Visible = False
            rowFerritin.Visible = False
            rowHDL.Visible = False
            rowLDL.Visible = False
            rowINR.Visible = False
            rowUrineVol24.Visible = False

            'these removed for both
            'rowCreat1.Visible = False
            'rowCreat2.Visible = False
            rowUrVol.Visible = False
            rowAlbuminaria.Visible = False

            lblPage.Text = "7A"

        ElseIf txtDIAGNOSIS.Text = "FSGS" Then ' FSGS


            rowOsmolarity.Visible = True
            rowDysmorphic.Visible = False
            rowRedCells.Visible = False
            rowWhiteCells.Visible = False
            rowAnti_CLQ.Visible = False

            rowC3NEPH.Visible = False
            rowANTI_STREP.Visible = False

            'rowCreat1.Visible = False
            'rowCreat2.Visible = False
            rowUrVol.Visible = False
            rowAlbuminaria.Visible = False
            rowUrineVol24.Visible = True

            lblPage.Text = "7B"

        End If

    End Sub

    Protected Sub btnUpdate_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnUpdate.Click
        pageSave()
    End Sub

    Sub pageSave()
        Dim strSodium As String = txtSODIUM.Text
        Dim intCCS As Integer
        Dim intSEQ_NO As Integer = CInt(txtSEQ_NO.Text)
        lblDebug.Text = ""

        Dim strSQL As String = "UPDATE [tbl_LabData] SET [RADAR_NO] = @RADAR_NO, [DATE_LAB_RES] = @DATE_LAB_RES, [PROTEIN] = @PROTEIN, [CREAT_SER] = @CREAT_SER, [PROT_CREAT_RAT] = @PROT_CREAT_RAT, [PHOS] = @PHOS, [POTASSIUM] = @POTASSIUM, [SODIUM] = @SODIUM, [UREA_SER] = @UREA_SER, [ALBUMIN] = @ALBUMIN, [FERRITIN] = @FERRITIN, [PLATELETS] = @PLATELETS, [HB] = @HB, [NEUTRO] = @NEUTRO, [WBC] = @WBC, [ALB_CREAT_RAT] = @ALB_CREAT_RAT, [CREAT_CLEAR_RADIO] = @CREAT_CLEAR_RADIO, [ENA] = @ENA, [HEP_C] = @HEP_C, [HIV] = @HIV, [EBV] = @EBV, [HEP_B] = @HEP_B, [ANTI_STREP_O] = @ANTI_STREP_O, [CRP] = @CRP, [INR] = @INR, [C3_NEPH_FAC] = @C3_NEPH_FAC, [COMP_C4] = @COMP_C4, [COMP_C3] = @COMP_C3, [COMP_OTHER] = @COMP_OTHER, [IGM] = @IGM, [PARVO_ANTIB] = @PARVO_ANTIB, [HANTAVIRUS] = @HANTAVIRUS, [BKV_SYM] = @BKV_SYM, [BKV] = @BKV, [CMV_SYM] = @CMV_SYM, [CMV] = @CMV, [ALBUMINURIA] = @ALBUMINURIA, [HAEMATURIA] = @HAEMATURIA, [UR_VOL_24H_COND] = @UR_VOL_24H_COND, [UR_VOL_24H] = @UR_VOL_24H, [OTHER_INFECT_SP] = @OTHER_INFECT_SP, [OTHER_INFECT] = @OTHER_INFECT, [BACT_URINE] = @BACT_URINE, [NITRITE] = @NITRITE, [LEUC_URINE] = @LEUC_URINE, [WBC_CASTS_URINE] = @WBC_CASTS_URINE, [RED_CCASTS_URINE] = @RED_CCASTS_URINE, [DYS_ERYTH_URINE] = @DYS_ERYTH_URINE, [OSMOLARITY] = @OSMOLARITY, [GLUC_URINE] = @GLUC_URINE, [ANTI_SLT] = @ANTI_SLT, [IGA] = @IGA, [IGG] = @IGG, [ANTI_GBM] = @ANTI_GBM, [CRYOGLOB] = @CRYOGLOB, [DNA_ANTI_DS] = @DNA_ANTI_DS, [DNA_ANTIB] = @DNA_ANTIB, [ANA] = @ANA, [ANCA] = @ANCA, [CHOL_HDL] = @CHOL_HDL, [CREAT_CLEAR_SCHZ] = @CREAT_CLEAR_SCHZ, [THYROX] = @THYROX, [TSH] = @TSH, [CREAT_CLEAR_24_URINE] = @CREAT_CLEAR_24_URINE, [TRIG] = @TRIG, [CHOL_LDL] = @CHOL_LDL, [CHOL_TOTAL] = @CHOL_TOTAL, [PROTEINURIA_DIP] = @PROTEINURIA_DIP, [ANTI_CLQ] = @ANTI_CLQ, [DNA_FACTOR_H] = @DNA_FACTOR_H  WHERE [labID] = @labID"
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        With objCommand.Parameters

            If txtDateLab.Text = "" Then
                .Add(New SqlParameter("@DATE_LAB_RES", DBNull.Value))
            Else
                .Add(New SqlParameter("@DATE_LAB_RES", CDate(txtDateLab.Text)))
            End If

            If txtALB_CREAT_RAT.Text = "" Then
                .Add(New SqlParameter("@ALB_CREAT_RAT", DBNull.Value))
            Else
                .Add(New SqlParameter("@ALB_CREAT_RAT", txtALB_CREAT_RAT.Text))
            End If

            If txtALBUMIN.Text = "" Then
                .Add(New SqlParameter("@ALBUMIN", DBNull.Value))
            Else
                .Add(New SqlParameter("@ALBUMIN", txtALBUMIN.Text))
            End If

            If txtCHOL_HDL.Text = "" Then
                .Add(New SqlParameter("@CHOL_HDL", DBNull.Value))
            Else
                .Add(New SqlParameter("@CHOL_HDL", txtCHOL_HDL.Text))
            End If

            If txtCHOL_LDL.Text = "" Then
                .Add(New SqlParameter("@CHOL_LDL", DBNull.Value))
            Else
                .Add(New SqlParameter("@CHOL_LDL", txtCHOL_LDL.Text))
            End If

            If txtCHOL_TOTAL.Text = "" Then
                .Add(New SqlParameter("@CHOL_TOTAL", DBNull.Value))
            Else
                .Add(New SqlParameter("@CHOL_TOTAL", txtCHOL_TOTAL.Text))
            End If

            'If txtCREAT_CLEAR_24.Text = "" Then
            .Add(New SqlParameter("@CREAT_CLEAR_24_URINE", DBNull.Value))
            'Else
            ' .Add(New SqlParameter("@CREAT_CLEAR_24_URINE", txtCREAT_CLEAR_24.Text))
            ' End If

            'If txtCREAT_CLEAR_RADIO.Text = "" Then
            .Add(New SqlParameter("@CREAT_CLEAR_RADIO", DBNull.Value))
            'Else
            ' .Add(New SqlParameter("@CREAT_CLEAR_RADIO", txtCREAT_CLEAR_RADIO.Text))
            'End If

            If getHeight(CInt(txtRADAR_NO.Text)) <> 0 Then

                If txtCREAT_CLEAR_SCHZ.Text = "" Then
                    .Add(New SqlParameter("@CREAT_CLEAR_SCHZ", DBNull.Value))

                ElseIf txtCreatSer.Text = "" Then
                    .Add(New SqlParameter("@CREAT_CLEAR_SCHZ", DBNull.Value))


                Else

                    intCCS = (getHeight(txtRADAR_NO.Text) * 40 / CInt(txtCreatSer.Text))

                    If intCCS = 0 Then
                        .Add(New SqlParameter("@CREAT_CLEAR_SCHZ", DBNull.Value))
                    Else
                        .Add(New SqlParameter("@CREAT_CLEAR_SCHZ", intCCS))
                    End If



                End If
            Else
                'If txtCREAT_CLEAR_SCHZ.Text = "Calc" Then
                .Add(New SqlParameter("@CREAT_CLEAR_SCHZ", DBNull.Value))
                lblHeightWarn.Text = "Height not entered on Clinical page"
                'End If
            End If

            If txtCreatSer.Text = "" Then
                .Add(New SqlParameter("@CREAT_SER", DBNull.Value))
            Else
                .Add(New SqlParameter("@CREAT_SER", txtCreatSer.Text))
            End If

            If txtCRP.Text = "" Then
                .Add(New SqlParameter("@CRP", DBNull.Value))
            Else
                .Add(New SqlParameter("@CRP", txtCRP.Text))
            End If

            If txtDNA_ANTIB.Text = "" Then
                .Add(New SqlParameter("@DNA_ANTIB", DBNull.Value))
            Else
                .Add(New SqlParameter("@DNA_ANTIB", txtDNA_ANTIB.Text))
            End If

            'If txtELISA_ASS.Text = "" Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@ELISA_ASS", DBNull.Value))
            'Else
            '    .Add(New System.Data.SqlClient.SqlParameter("@ELISA_ASS", txtELISA_ASS.Text))
            'End If

            If txtFERRITIN.Text = "" Then
                .Add(New SqlParameter("@FERRITIN", DBNull.Value))
            Else
                .Add(New SqlParameter("@FERRITIN", txtFERRITIN.Text))
            End If


            If txtHB.Text = "" Then
                .Add(New SqlParameter("@HB", DBNull.Value))
            Else
                .Add(New SqlParameter("@HB", txtHB.Text))
            End If

            If txtINR.Text = "" Then
                .Add(New SqlParameter("@INR", DBNull.Value))
            Else
                .Add(New SqlParameter("@INR", txtINR.Text))
            End If

            If txtNEUTRO.Text = "" Then
                .Add(New SqlParameter("@NEUTRO", DBNull.Value))
            Else
                .Add(New SqlParameter("@NEUTRO", txtNEUTRO.Text))
            End If

            If txtOSMOLALITY.Text = "" Then
                .Add(New SqlParameter("@OSMOLARITY", DBNull.Value))
            Else
                .Add(New SqlParameter("@OSMOLARITY", txtOSMOLALITY.Text))
            End If

            If txtOTHER_INFECT_SP.Text = "" Then
                .Add(New SqlParameter("@OTHER_INFECT_SP", DBNull.Value))
            ElseIf chkOTHER_INFECT.Checked = False Then
                .Add(New SqlParameter("@OTHER_INFECT_SP", DBNull.Value))
            Else
                .Add(New SqlParameter("@OTHER_INFECT_SP", txtOTHER_INFECT_SP.Text))
            End If

            If txtPHOS.Text = "" Then
                .Add(New SqlParameter("@PHOS", DBNull.Value))
            Else
                .Add(New SqlParameter("@PHOS", txtPHOS.Text))
            End If

            If txtPLATELETS.Text = "" Then
                .Add(New SqlParameter("@PLATELETS", DBNull.Value))
            Else
                .Add(New SqlParameter("@PLATELETS", txtPLATELETS.Text))
            End If

            If txtPROT_CREAT_RAT.Text = "" Then
                .Add(New SqlParameter("@PROT_CREAT_RAT", DBNull.Value))
            Else
                .Add(New SqlParameter("@PROT_CREAT_RAT", txtPROT_CREAT_RAT.Text))
            End If

            If txtPOTASSIUM.Text = "" Then
                .Add(New SqlParameter("@POTASSIUM", DBNull.Value))
            Else
                .Add(New SqlParameter("@POTASSIUM", txtPOTASSIUM.Text))
            End If

            If txtPROTEIN.Text = "" Then
                .Add(New SqlParameter("@PROTEIN", DBNull.Value))
            Else
                .Add(New SqlParameter("@PROTEIN", txtPROTEIN.Text))
            End If

            If txtSODIUM.Text = "" Then
                .Add(New SqlParameter("@SODIUM", DBNull.Value))
            Else
                .Add(New SqlParameter("@SODIUM", txtSODIUM.Text))
            End If

            If txtTHYROX.Text = "" Then
                .Add(New SqlParameter("@THYROX", DBNull.Value))
            Else
                .Add(New SqlParameter("@THYROX", txtTHYROX.Text))
            End If

            If txtTSH.Text = "" Then
                .Add(New SqlParameter("@TSH", DBNull.Value))
            Else
                .Add(New SqlParameter("@TSH", txtTSH.Text))
            End If

            If txtTRIG.Text = "" Then
                .Add(New SqlParameter("@TRIG", DBNull.Value))
            Else
                .Add(New SqlParameter("@TRIG", txtTRIG.Text))
            End If

            If txtUR_VOL_24H.Text = "" Then
                .Add(New SqlParameter("@UR_VOL_24H", DBNull.Value))
            Else
                .Add(New SqlParameter("@UR_VOL_24H", txtUR_VOL_24H.Text))
            End If

            If txtUREA_SER.Text = "" Then
                .Add(New SqlParameter("@UREA_SER", DBNull.Value))
            Else
                .Add(New SqlParameter("@UREA_SER", txtUREA_SER.Text))
            End If

            If txtWBC.Text = "" Then
                .Add(New SqlParameter("@WBC", DBNull.Value))
            Else
                .Add(New SqlParameter("@WBC", txtWBC.Text))
            End If

            If txtIGA.Text = "" Then
                .Add(New SqlParameter("@IGA", DBNull.Value))
            Else
                .Add(New SqlParameter("@IGA", txtIGA.Text))
            End If

            If txtIGG.Text = "" Then
                .Add(New SqlParameter("@IGG", DBNull.Value))
            Else
                .Add(New SqlParameter("@IGG", txtIGG.Text))
            End If

            If txtIGM.Text = "" Then
                .Add(New SqlParameter("@IGM", DBNull.Value))
            Else
                .Add(New SqlParameter("@IGM", txtIGM.Text))
            End If

            If txtANTI_STREP_O.Text = "" Then
                .Add(New SqlParameter("@ANTI_STREP_O", DBNull.Value))
            Else
                .Add(New SqlParameter("@ANTI_STREP_O", txtANTI_STREP_O.Text))
            End If

            ' If txtANTI_SLT.Text = "" Then
            .Add(New SqlParameter("@ANTI_SLT", DBNull.Value))
            'Else
            '.Add(New SqlParameter("@ANTI_SLT", txtANTI_SLT.Text))
            'End If

            'If radAntiBND.Checked Then
            '    .Add(New SqlParameter("@DNA_ANTI_DS", DBNull.Value))
            'ElseIf radAntiBNo.Checked Then
            '    .Add(New SqlParameter("@DNA_ANTI_DS", False))
            'ElseIf radAntiBYes.Checked Then
            '    .Add(New SqlParameter("@DNA_ANTI_DS", True))
            'End If

            If ddlANTI_DS_DNA.SelectedValue = "" Then
                .Add(New SqlParameter("@DNA_ANTI_DS", DBNull.Value))
            Else
                .Add(New SqlParameter("@DNA_ANTI_DS", ddlANTI_DS_DNA.SelectedValue))
            End If


            If txtANTI_CLQ.Text = "" Then
                .Add(New SqlParameter("@ANTI_CLQ", DBNull.Value))
            Else
                .Add(New SqlParameter("@ANTI_CLQ", txtANTI_CLQ.Text))
            End If


            .Add(New SqlParameter("@BACT_URINE", chkBACT_URINE.Checked))
            .Add(New SqlParameter("@BKV", DBNull.Value)) ' removed
            .Add(New SqlParameter("@BKV_SYM", DBNull.Value)) ' removed

            .Add(New SqlParameter("@CMV_SYM", chkCMV_SYM.Checked))

            '.Add(New SqlParameter("@GLUC_URINE", chkGLUC_URINE.Checked))
            .Add(New SqlParameter("@HANTAVIRUS", DBNull.Value)) 'removed
            .Add(New SqlParameter("@OTHER_INFECT", chkOTHER_INFECT.Checked))


            '.Add(New SqlParameter("@LEUC_URINE", chkLEUC_URINE.Checked))

            If radLeucoYes.Checked Then
                .Add(New SqlParameter("@LEUC_URINE", True))
            ElseIf radLeucoNo.Checked Then
                .Add(New SqlParameter("@LEUC_URINE", False))
            ElseIf radLeucoND.Checked Then
                .Add(New SqlParameter("@LEUC_URINE", DBNull.Value))
            End If

            If radGlucYes.Checked Then
                .Add(New SqlParameter("@GLUC_URINE", True))
            ElseIf radGlucNo.Checked Then
                .Add(New SqlParameter("@GLUC_URINE", False))
            ElseIf radGlucND.Checked Then
                .Add(New SqlParameter("@GLUC_URINE", DBNull.Value))
            End If


            If DropDownListParvo.SelectedValue = "" Then
                .Add(New SqlParameter("@PARVO_ANTIB", DBNull.Value))
            Else
                .Add(New SqlParameter("@PARVO_ANTIB", DropDownListParvo.SelectedValue))
            End If

            If DropDownListCMV.SelectedValue = "" Then
                .Add(New SqlParameter("@CMV", DBNull.Value))
            Else
                .Add(New SqlParameter("@CMV", DropDownListCMV.SelectedValue))
            End If

            If DropDownListEBV.SelectedValue = "" Then
                .Add(New SqlParameter("@EBV", DBNull.Value))
            Else
                .Add(New SqlParameter("@EBV", DropDownListEBV.SelectedValue))
            End If

            If ddlDysEryth.SelectedValue = "" Then
                .Add(New SqlParameter("@DYS_ERYTH_URINE", DBNull.Value))
            Else
                .Add(New SqlParameter("@DYS_ERYTH_URINE", ddlDysEryth.SelectedValue))
            End If

            If ddlRedCellCast.SelectedValue = "" Then
                .Add(New SqlParameter("@RED_CCASTS_URINE", DBNull.Value))
            Else
                .Add(New SqlParameter("@RED_CCASTS_URINE", ddlRedCellCast.SelectedValue))
            End If

            If ddlWhiteCellCast.SelectedValue = "" Then
                .Add(New SqlParameter("@WBC_CASTS_URINE", DBNull.Value))
            Else
                .Add(New SqlParameter("@WBC_CASTS_URINE", ddlWhiteCellCast.SelectedValue))
            End If

            If ddlProteinuria.SelectedValue = "" Then
                .Add(New SqlParameter("@PROTEINURIA_DIP", DBNull.Value))
            Else
                .Add(New SqlParameter("@PROTEINURIA_DIP", ddlProteinuria.SelectedValue))
            End If

            If DropDownList1.SelectedValue = "" Then
                .Add(New SqlParameter("@ANCA", DBNull.Value))
            Else
                .Add(New SqlParameter("@ANCA", DropDownList1.SelectedValue))
            End If


            If DropDownList2.SelectedValue = "" Then
                .Add(New SqlParameter("@ENA", DBNull.Value))
            Else
                .Add(New SqlParameter("@ENA", DropDownList2.SelectedValue))
            End If

            If DropDownList3.SelectedValue = "" Then
                .Add(New SqlParameter("@ANA", DBNull.Value))
            Else
                .Add(New SqlParameter("@ANA", DropDownList3.SelectedValue))
            End If

            If DropDownList5.SelectedValue = "" Then
                .Add(New SqlParameter("@CRYOGLOB", DBNull.Value))
            Else
                .Add(New SqlParameter("@CRYOGLOB", DropDownList5.SelectedValue))
            End If

            If DropDownList6.SelectedValue = "" Then
                .Add(New SqlParameter("@ANTI_GBM", DBNull.Value))
            Else
                .Add(New SqlParameter("@ANTI_GBM", DropDownList6.SelectedValue))
            End If




            If txtCOMP_C3.Text = "" Then
                .Add(New SqlParameter("@COMP_C3", DBNull.Value))
            Else
                .Add(New SqlParameter("@COMP_C3", txtCOMP_C3.Text))
            End If

            If txtCOMP_C4.Text = "" Then
                .Add(New SqlParameter("@COMP_C4", DBNull.Value))
            Else
                .Add(New SqlParameter("@COMP_C4", txtCOMP_C4.Text))
            End If

            If txtCOMP_OTHER.Text = "" Then
                .Add(New SqlParameter("@COMP_OTHER", DBNull.Value))
            Else
                .Add(New SqlParameter("@COMP_OTHER", txtCOMP_OTHER.Text))
            End If

            If DropDownList9.SelectedValue = "" Then
                .Add(New SqlParameter("@HEP_B", DBNull.Value))
            Else
                .Add(New SqlParameter("@HEP_B", DropDownList9.SelectedValue))
            End If

            If DropDownList10.SelectedValue = "" Then
                .Add(New SqlParameter("@HEP_C", DBNull.Value))
            Else
                .Add(New SqlParameter("@HEP_C", DropDownList10.SelectedValue))
            End If

            If DropDownList11.SelectedValue = "" Then
                .Add(New SqlParameter("@HIV", DBNull.Value))
            Else
                .Add(New SqlParameter("@HIV", DropDownList11.SelectedValue))
            End If

            If DropDownList12.SelectedValue = "" Then
                .Add(New SqlParameter("@UR_VOL_24H_COND", DBNull.Value))
            Else
                .Add(New SqlParameter("@UR_VOL_24H_COND", DropDownList12.SelectedValue))
            End If

            If DropDownList13.SelectedValue = "" Then
                .Add(New SqlParameter("@ALBUMINURIA", DBNull.Value))
            Else
                .Add(New SqlParameter("@ALBUMINURIA", DropDownList13.SelectedValue))
            End If

            If radNitriteYes.Checked Then
                .Add(New SqlParameter("@NITRITE", True))
            ElseIf radNitriteNo.Checked Then
                .Add(New SqlParameter("@NITRITE", False))
            Else
                .Add(New SqlParameter("@NITRITE", DBNull.Value))
            End If


            If DropDownList15.SelectedValue = "" Then
                .Add(New SqlParameter("@C3_NEPH_FAC", DBNull.Value))
            Else
                .Add(New SqlParameter("@C3_NEPH_FAC", DropDownList15.SelectedValue))
            End If

            If DropDownList16.SelectedValue = "" Then
                .Add(New SqlParameter("@HAEMATURIA", DBNull.Value))
            Else
                .Add(New SqlParameter("@HAEMATURIA", DropDownList16.SelectedValue))
            End If

            If radDNAYes.Checked Then
                .Add(New SqlParameter("@DNA_FACTOR_H", True))
            ElseIf radDNANo.Checked = True Then
                .Add(New SqlParameter("@DNA_FACTOR_H", False))
            Else
                .Add(New SqlParameter("@DNA_FACTOR_H", DBNull.Value))
            End If

            .Add(New SqlParameter("@RADAR_NO", txtRADAR_NO.Text))
            .Add(New SqlParameter("@labID", lblLabID.Text))



        End With

        Try
            objConnect.Open()
            objCommand.ExecuteNonQuery()

        Catch objError As Exception

            lblDebug.Text = "An error occurred: '" & objError.Message & "'"
            'lblDebug.Text = "An error occurred: ccs=" & intCCS
            objConnect.Close()
            Exit Sub

        Finally
            objConnect.Close()
        End Try

        lblUpdate.Text = "<strong>Update successful:</strong>&nbsp;&nbsp;" & Now().ToShortTimeString
        lblUpdate2.Text = "<strong>Update successful:</strong>&nbsp;&nbsp;" & Now().ToShortTimeString


        pagefill(intSEQ_NO)

    End Sub



    Function CheckDiagnosis(ByVal patientID As Integer) As Boolean

        Dim strSQL As String = "SELECT [DATE_DIAG] FROM [tbl_Diagnosis] WHERE [RADAR_NO] = " & Session("patientID")
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

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

            lblDebug.Text = String.Format("An error occurred: '{0}'{1}", objError.Message, strSQL)
            CheckDiagnosis = False

        Finally

            objConnect.Close()
        End Try


    End Function

    Sub pagefill(ByVal SEQ_NO As Integer)

        Dim TDES As New TripleDES()

        'reset dna radio buttons before reload
        radDNANo.Checked = False
        radDNAYes.Checked = False

        Dim strSQL As String = "SELECT tbl_LabData.labID, tbl_LabData.RADAR_NO, tbl_LabData.DATE_LAB_RES, tbl_LabData.PROTEIN, tbl_LabData.CREAT_SER, tbl_LabData.PROT_CREAT_RAT, tbl_LabData.PHOS, tbl_LabData.POTASSIUM, tbl_LabData.SODIUM, tbl_LabData.UREA_SER, tbl_LabData.ALBUMIN, tbl_LabData.FERRITIN, tbl_LabData.PLATELETS, tbl_LabData.HB, tbl_LabData.NEUTRO, tbl_LabData.WBC, tbl_LabData.ALB_CREAT_RAT, tbl_LabData.CREAT_CLEAR_RADIO, tbl_LabData.ENA, tbl_LabData.HEP_C, tbl_LabData.HIV, tbl_LabData.EBV, tbl_LabData.HEP_B, tbl_LabData.ANTI_STREP_O, tbl_LabData.CRP, tbl_LabData.INR, tbl_LabData.C3_NEPH_FAC, tbl_LabData.COMP_C4, tbl_LabData.COMP_C3, tbl_LabData.COMP_OTHER, tbl_LabData.IGM, tbl_LabData.PARVO_ANTIB, tbl_LabData.HANTAVIRUS, tbl_LabData.BKV_SYM, tbl_LabData.BKV, tbl_LabData.CMV_SYM, tbl_LabData.CMV, tbl_LabData.ALBUMINURIA, tbl_LabData.HAEMATURIA, tbl_LabData.UR_VOL_24H_COND, tbl_LabData.UR_VOL_24H, tbl_LabData.OTHER_INFECT_SP, tbl_LabData.OTHER_INFECT, tbl_LabData.BACT_URINE, tbl_LabData.NITRITE, tbl_LabData.LEUC_URINE, tbl_LabData.WBC_CASTS_URINE, tbl_LabData.RED_CCASTS_URINE, tbl_LabData.DYS_ERYTH_URINE, tbl_LabData.OSMOLARITY, tbl_LabData.GLUC_URINE, tbl_LabData.ANTI_SLT, tbl_LabData.IGA, tbl_LabData.IGG, tbl_LabData.ANTI_GBM, tbl_LabData.CRYOGLOB, tbl_LabData.DNA_ANTI_DS, tbl_LabData.DNA_ANTIB, tbl_LabData.ANA, tbl_LabData.ANCA, tbl_LabData.CHOL_HDL, tbl_LabData.CREAT_CLEAR_SCHZ, tbl_LabData.THYROX, tbl_LabData.TSH, tbl_LabData.CREAT_CLEAR_24_URINE, tbl_LabData.TRIG, tbl_LabData.CHOL_LDL, tbl_LabData.CHOL_TOTAL, tbl_LabData.PROTEINURIA_DIP, tbl_LabData.ANTI_CLQ, tbl_LabData.DNA_FACTOR_H, tbl_Demographics.SNAME, tbl_Demographics.FNAME, tbl_Demographics.DOB, tbl_Demographics.HOSP_NO, tbl_Diagnosis.DIAG, tbl_ClinicalData.DATE_CLIN_PIC FROM tbl_LabData INNER JOIN tbl_Demographics ON tbl_LabData.RADAR_NO = tbl_Demographics.RADAR_NO INNER JOIN tbl_Diagnosis ON tbl_Demographics.RADAR_NO = tbl_Diagnosis.RADAR_NO INNER JOIN tbl_ClinicalData ON tbl_Demographics.RADAR_NO = tbl_ClinicalData.RADAR_NO WHERE (tbl_LabData.RADAR_NO = '" & Session("patientID") & "' AND tbl_LabData.SEQ_NO = '" & SEQ_NO & "')"

        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        'lblDebug.Text = strSQL

        Try

            Dim objDataReader As SqlDataReader
            objConnect.Open()
            objDataReader = objCommand.ExecuteReader()

            If objDataReader.Read() Then
                txtSEQ_NO.Text = SEQ_NO
                txtRADAR_NO.Text = objDataReader("RADAR_NO")
                txtHOSP_NO.Text = TDES.Decrypt(objDataReader("HOSP_NO"))

                If objDataReader("DIAG") Is DBNull.Value Then
                    txtDIAGNOSIS.Text = ""
                Else
                    txtDIAGNOSIS.Text = CommonClass.GetDiagnosis(objDataReader("DIAG").ToString)
                End If

                If objDataReader("DATE_LAB_RES") Is DBNull.Value Then
                Else
                    txtDateLab.Text = objDataReader("DATE_LAB_RES")
                End If

                If objDataReader("DATE_CLIN_PIC") Is DBNull.Value Then
                    lnk3Months.Enabled = False
                Else
                    lnk3Months.Enabled = True
                End If

                txtFNAME.Text = TDES.Decrypt(objDataReader("FNAME"))
                txtSNAME.Text = TDES.Decrypt(objDataReader("SNAME"))
                Dim dt As Date = CDate(TDES.Decrypt(objDataReader("DOB")))
                txtDOB.Text = Format(dt, "dd-MMM-yyyy")

                txtALB_CREAT_RAT.Text = objDataReader("ALB_CREAT_RAT").ToString
                txtALBUMIN.Text = objDataReader("ALBUMIN").ToString

                txtCHOL_HDL.Text = objDataReader("CHOL_HDL").ToString
                txtCHOL_LDL.Text = objDataReader("CHOL_LDL").ToString
                txtCHOL_TOTAL.Text = objDataReader("CHOL_TOTAL").ToString
                'txtCREAT_CLEAR_24.Text = objDataReader("CREAT_CLEAR_24_URINE").ToString
                'txtCREAT_CLEAR_RADIO.Text = objDataReader("CREAT_CLEAR_RADIO").ToString
                txtANTI_CLQ.Text = objDataReader("ANTI_CLQ").ToString

                If objDataReader("CREAT_CLEAR_SCHZ") Is DBNull.Value Then
                    txtCREAT_CLEAR_SCHZ.Text = "Calc"
                    txtCREAT_CLEAR_SCHZ.Font.Italic = True
                    txtCREAT_CLEAR_SCHZ.ForeColor = Drawing.Color.Gray
                Else
                    txtCREAT_CLEAR_SCHZ.Text = objDataReader("CREAT_CLEAR_SCHZ").ToString
                    txtCREAT_CLEAR_SCHZ.Font.Italic = False
                    txtCREAT_CLEAR_SCHZ.ForeColor = Drawing.Color.Black
                End If


                txtCreatSer.Text = objDataReader("CREAT_SER").ToString
                txtCRP.Text = objDataReader("CRP").ToString
                txtDNA_ANTIB.Text = objDataReader("DNA_ANTIB").ToString
                'txtDNA_ANTI_DS.Text = objDataReader("DNA_ANTI_DS").ToString
                'txtELISA_ASS.Text = objDataReader("ELISA_ASS").ToString

                'Select Case objDataReader("DNA_ANTI_DS")
                '    Case False
                '        radAntiBNo.Checked = True
                '    Case True
                '        radAntiBYes.Checked = True
                '    Case Else
                '        radAntiBND.Checked = True
                'End Select

                'Select Case objDataReader("DNA_ANTI_DS")
                '    Case False
                '        ddlANTI_DS_DNA.SelectedValue = "false"
                '    Case True
                '        ddlANTI_DS_DNA.SelectedValue = "true"
                '    Case Else
                '        ddlANTI_DS_DNA.SelectedValue = "NULL"
                'End Select

                'If objDataReader("DNA_ANTI_DS") IsNot DBNull.Value Then
                '    If objDataReader("DNA_ANTI_DS") = True Then
                '        ddlANTI_DS_DNA.SelectedValue = "true"
                '    ElseIf objDataReader("DNA_ANTI_DS") = False Then
                '        ddlANTI_DS_DNA.SelectedValue = "false"
                '    End If
                'Else
                '    ddlANTI_DS_DNA.SelectedValue = "null"
                'End If


                txtFERRITIN.Text = objDataReader("FERRITIN").ToString
                txtHB.Text = objDataReader("HB").ToString
                txtINR.Text = objDataReader("INR").ToString
                txtNEUTRO.Text = objDataReader("NEUTRO").ToString
                txtOSMOLALITY.Text = objDataReader("OSMOLARITY").ToString
                txtOTHER_INFECT_SP.Text = objDataReader("OTHER_INFECT_SP").ToString
                txtPHOS.Text = objDataReader("PHOS").ToString
                txtPLATELETS.Text = objDataReader("PLATELETS").ToString
                txtPROT_CREAT_RAT.Text = objDataReader("PROT_CREAT_RAT").ToString
                txtPOTASSIUM.Text = objDataReader("POTASSIUM").ToString
                txtPROTEIN.Text = objDataReader("PROTEIN").ToString
                txtSODIUM.Text = objDataReader("SODIUM").ToString
                txtTHYROX.Text = objDataReader("THYROX").ToString
                txtTSH.Text = objDataReader("TSH").ToString
                txtTRIG.Text = objDataReader("TRIG").ToString
                txtUR_VOL_24H.Text = objDataReader("UR_VOL_24H").ToString
                txtUREA_SER.Text = objDataReader("UREA_SER").ToString
                txtWBC.Text = objDataReader("WBC").ToString
                txtIGA.Text = objDataReader("IGA").ToString
                txtIGG.Text = objDataReader("IGG").ToString
                txtIGM.Text = objDataReader("IGM").ToString
                txtANTI_STREP_O.Text = objDataReader("ANTI_STREP_O").ToString
                lblLabID.Text = objDataReader("labID").ToString

                chkBACT_URINE.Checked = objDataReader("BACT_URINE")
                'chkBKV.Checked = objDataReader("BKV")
                'chkBKV_SYM.Checked = objDataReader("BKV_SYM")
                'chkCMV.Checked = objDataReader("CMV")
                chkCMV_SYM.Checked = objDataReader("CMV_SYM")
                'chkEBV.Checked = objDataReader("EBV")
                'chkGLUC_URINE.Checked = objDataReader("GLUC_URINE")
                'chkHANTAVIRUS.Checked = objDataReader("HANTAVIRUS")
                chkOTHER_INFECT.Checked = objDataReader("OTHER_INFECT")

                If objDataReader("OTHER_INFECT") = True Then
                    rowOther_Infect.Visible = True
                Else
                    rowOther_Infect.Visible = False
                End If

                'chkPARVO_ANTIB.Checked = objDataReader("PARVO_ANTIB")
                'chkDYS_ERYTH_URINE.Checked = objDataReader("DYS_ERYTH_URINE")
                'chkLEUC_URINE.Checked = objDataReader("LEUC_URINE")
                'chkRED_CCASTS_URINE.Checked = objDataReader("RED_CCASTS_URINE")
                'chkWBC_CASTS_URINE.Checked = objDataReader("WBC_CASTS_URINE")
                'chkProteinuria.Checked = objDataReader("PROTEINURIA_DIP")

                Select Case objDataReader("LEUC_URINE").ToString
                    Case False
                        radLeucoNo.Checked = True
                    Case True
                        radLeucoYes.Checked = True
                    Case Else
                        radLeucoND.Checked = True
                End Select

                Select Case objDataReader("GLUC_URINE").ToString
                    Case False
                        radGlucNo.Checked = True
                    Case True
                        radGlucYes.Checked = True
                    Case Else
                        radGlucND.Checked = True
                End Select

                Select Case objDataReader("NITRITE").ToString
                    Case True
                        radNitriteYes.Checked = True
                    Case False
                        radNitriteNo.Checked = True
                    Case Else
                        radNitriteND.Checked = True
                End Select

                DropDownListParvo.SelectedValue = chkNull(objDataReader("PARVO_ANTIB"))
                DropDownList12.SelectedValue = chkNull(objDataReader("UR_VOL_24H_COND"))
                DropDownList13.SelectedValue = chkNull(objDataReader("ALBUMINURIA"))
                DropDownList1.SelectedValue = chkNull(objDataReader("ANCA"))
                DropDownList9.SelectedValue = chkNull(objDataReader("HEP_B"))
                DropDownList10.SelectedValue = chkNull(objDataReader("HEP_C"))
                DropDownList11.SelectedValue = chkNull(objDataReader("HIV"))
                DropDownList2.SelectedValue = chkNull(objDataReader("ENA"))
                DropDownList3.SelectedValue = chkNull(objDataReader("ANA"))
                DropDownList5.SelectedValue = chkNull(objDataReader("CRYOGLOB"))
                DropDownList6.SelectedValue = chkNull(objDataReader("ANTI_GBM"))

                DropDownList15.SelectedValue = chkNull(objDataReader("C3_NEPH_FAC"))
                DropDownList16.SelectedValue = chkNull(objDataReader("HAEMATURIA"))
                ddlDysEryth.SelectedValue = chkNull(objDataReader("DYS_ERYTH_URINE"))
                ddlRedCellCast.SelectedValue = chkNull(objDataReader("RED_CCASTS_URINE"))
                ddlWhiteCellCast.SelectedValue = chkNull(objDataReader("WBC_CASTS_URINE"))
                ddlProteinuria.SelectedValue = chkNull(objDataReader("PROTEINURIA_DIP"))
                ddlANTI_DS_DNA.SelectedValue = chkNull(objDataReader("DNA_ANTI_DS"))

                txtCOMP_C3.Text = objDataReader("COMP_C3").ToString
                txtCOMP_C4.Text = objDataReader("COMP_C4").ToString

                If objDataReader("COMP_OTHER") IsNot DBNull.Value Then
                    chkCompOther.Checked = True
                    txtCOMP_OTHER.Text = objDataReader("COMP_OTHER").ToString
                    rowCOMP_OTHER.Visible = True
                Else
                    chkCompOther.Checked = False
                    rowCOMP_OTHER.Visible = False
                End If


                If chkNull(objDataReader("ANA")) = "1" Then
                    rowDNA_ANTIB.Visible = True
                Else
                    rowDNA_ANTIB.Visible = False
                End If

                Select Case objDataReader("DNA_FACTOR_H").ToString

                    Case True
                        radDNAYes.Checked = True
                    Case False
                        radDNANo.Checked = True
                    Case Else
                        radDNANo.Checked = False
                        radDNAYes.Checked = False
                End Select

                'If objDataReader("DNA_FACTOR_H") Is DBNull.Value Then
                '    radDNANo.Checked = False
                '    radDNAYes.Checked = False
                'ElseIf objDataReader("DNA_FACTOR_H") = False Then
                '    radDNANo.Checked = True
                'ElseIf objDataReader("DNA_FACTOR_H") = True Then
                '    radDNAYes.Checked = True
                'End If


                If objDataReader("DIAG") Is DBNull.Value Then

                Else

                    If objDataReader("DIAG") = 2 Then 'MPGN

                        txtTHYROX.Visible = True
                        lblTHYROX.Visible = True

                        'these no apply for both 18-Jul-09

                        'txtIGG.Visible = False
                        'lblIGG.Visible = False
                        'txtIGA.Visible = False
                        'lblIGA.Visible = False
                        'txtIGM.Visible = False
                        'lblIGM.Visible = False
                        'rowIGG.Visible = False
                        'rowIGA.Visible = False
                        'rowIGM.Visible = False

                        txtCRP.Visible = True
                        lblCRP.Visible = True
                        'txtANTI_STREP_O.Visible = False
                        'lblANTI_STREP_O.Visible = False
                        'chkPARVO_ANTIB.Visible = False
                        rowParvoVirus.Visible = True
                        rowNitrite.Visible = True
                        lblNITRITE.Visible = True
                        rowBacteria.Visible = False
                        rowOsmolarity.Visible = False

                        rowCMVSymp.Visible = False
                        rowPhosphate.Visible = False
                        rowFerritin.Visible = False
                        rowHDL.Visible = False
                        rowLDL.Visible = False
                        rowINR.Visible = False
                        rowUrineVol24.Visible = False

                        '#these removed for both
                        'rowCreat1.Visible = False
                        'rowCreat2.Visible = False
                        rowUrVol.Visible = False
                        rowAlbuminaria.Visible = False

                        lblPage.Text = "7A"

                    ElseIf objDataReader("DIAG") = 1 Then ' SRNS


                        rowOsmolarity.Visible = True
                        rowDysmorphic.Visible = False
                        rowRedCells.Visible = False
                        rowWhiteCells.Visible = False
                        rowAnti_CLQ.Visible = False
                        rowDNAFactorH.Visible = False
                        rowParvoVirus.Visible = False
                        rowC3NEPH.Visible = False
                        rowANTI_STREP.Visible = False

                        'rowCreat1.Visible = False
                        'rowCreat2.Visible = False
                        rowUrVol.Visible = False
                        rowAlbuminaria.Visible = False
                        rowUrineVol24.Visible = True

                        lblPage.Text = "7B"

                    End If

                End If





            End If

        Catch objError As Exception

            lblDebug.Text = "An error occurred: '" & objError.Message & "'" & strSQL


        Finally

            objConnect.Close()

        End Try


    End Sub

    Shared Function chkNull(ByVal value As Object) As String

        If value Is DBNull.Value Then
            chkNull = ""
        Else
            chkNull = value.ToString
        End If

    End Function

    Function GetNextRecord() As Integer

        Dim strConnect As String = ConfigurationAlias.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim strSQL As String = "SELECT MAX(SEQ_NO) AS LastSEQ_NO FROM [tbl_LabData] WHERE [RADAR_NO] = " & Session("patientID")
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

    Protected Sub DropDownList4_SelectedIndexChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles DropDownList4.SelectedIndexChanged

        Dim intRecord As Integer = DropDownList4.SelectedValue
        Session("dtRecord") = CDate((DropDownList4.SelectedItem).ToString)
        pnlAdd.Visible = True
        lblDebug.Text = ""
        lblUpdate.Text = ""
        pagefill(intRecord)
        DropDownList4.SelectedIndex = 0
        btnSaveNew.Visible = False
        btnUpdate.Visible = True

    End Sub

    Protected Sub btnSaveNew_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnSaveNew.Click

        lblDebug.Text = ""
        Dim intCCS As Integer

        Dim dtLabRes As Date = CDate(txtDateLab.Text)
        lblDebug.Text = ""


        If chkDate(dtLabRes) = False Then
            'do nothing
        Else
            lblUpdate.Text = "<span style='color:red;'>A record already exists for this date.</span>"
            Exit Sub
        End If



        Dim strSQL As String = "INSERT INTO [tbl_LabData] ([RADAR_NO], [DATE_LAB_RES], [CREAT_SER], [PROTEIN], [ALBUMIN], [UREA_SER], [SODIUM], [POTASSIUM], [PHOS], [PROT_CREAT_RAT], [ALB_CREAT_RAT], [WBC], [HB], [NEUTRO], [PLATELETS], [FERRITIN], [CHOL_TOTAL], [CHOL_HDL], [CHOL_LDL], [TRIG], [CREAT_CLEAR_24_URINE], [CREAT_CLEAR_RADIO], [CREAT_CLEAR_SCHZ], [THYROX], [TSH], [ANCA], [ELISA_ASS], [ENA], [ANA], [DNA_ANTIB], [DNA_ANTI_DS], [CRYOGLOB], [ANTI_GBM], [IGG], [IGA], [IGM], [COMP_C3], [COMP_C4], [COMP_OTHER], [C3_NEPH_FAC], [ANTI_SLT], [INR], [CRP], [ANTI_STREP_O], [HEP_B], [HEP_C], [HIV], [EBV], [CMV], [CMV_SYM], [BKV], [BKV_SYM], [HANTAVIRUS], [PARVO_ANTIB], [OTHER_INFECT], [OTHER_INFECT_SP], [UR_VOL_24H], [UR_VOL_24H_COND], [HAEMATURIA], [ALBUMINURIA], [DYS_ERYTH_URINE], [RED_CCASTS_URINE], [WBC_CASTS_URINE], [LEUC_URINE], [NITRITE], [BACT_URINE], [GLUC_URINE], [OSMOLARITY], [PROTEINURIA_DIP], [ANTI_CLQ], [DNA_FACTOR_H], [SEQ_NO]) VALUES (@RADAR_NO, @DATE_LAB_RES, @CREAT_SER, @PROTEIN, @ALBUMIN, @UREA_SER, @SODIUM, @POTASSIUM, @PHOS, @PROT_CREAT_RAT, @ALB_CREAT_RAT, @WBC, @HB, @NEUTRO, @PLATELETS, @FERRITIN, @CHOL_TOTAL, @CHOL_HDL, @CHOL_LDL, @TRIG, @CREAT_CLEAR_24_URINE, @CREAT_CLEAR_RADIO, @CREAT_CLEAR_SCHZ, @THYROX, @TSH, @ANCA, @ELISA_ASS, @ENA, @ANA, @DNA_ANTIB, @DNA_ANTI_DS, @CRYOGLOB, @ANTI_GBM, @IGG, @IGA, @IGM, @COMP_C3, @COMP_C4, @COMP_OTHER, @C3_NEPH_FAC, @ANTI_SLT, @INR, @CRP, @ANTI_STREP_O, @HEP_B, @HEP_C, @HIV, @EBV, @CMV, @CMV_SYM, @BKV, @BKV_SYM, @HANTAVIRUS, @PARVO_ANTIB, @OTHER_INFECT, @OTHER_INFECT_SP, @UR_VOL_24H, @UR_VOL_24H_COND, @HAEMATURIA, @ALBUMINURIA, @DYS_ERYTH_URINE, @RED_CCASTS_URINE, @WBC_CASTS_URINE, @LEUC_URINE, @NITRITE, @BACT_URINE, @GLUC_URINE, @OSMOLARITY, @PROTEINURIA_DIP, @ANTI_CLQ, @DNA_FACTOR_H, @SEQ_NO)"

        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        With objCommand.Parameters



            If txtDateLab.Text = "" Then
                .Add(New SqlParameter("@DATE_LAB_RES", DBNull.Value))
            Else
                .Add(New SqlParameter("@DATE_LAB_RES", CDate(txtDateLab.Text)))
            End If

            If txtALB_CREAT_RAT.Text = "" Then
                .Add(New SqlParameter("@ALB_CREAT_RAT", DBNull.Value))
            Else
                .Add(New SqlParameter("@ALB_CREAT_RAT", txtALB_CREAT_RAT.Text))
            End If

            If txtALBUMIN.Text = "" Then
                .Add(New SqlParameter("@ALBUMIN", DBNull.Value))
            Else
                .Add(New SqlParameter("@ALBUMIN", txtALBUMIN.Text))
            End If

            If txtCHOL_HDL.Text = "" Then
                .Add(New SqlParameter("@CHOL_HDL", DBNull.Value))
            Else
                .Add(New SqlParameter("@CHOL_HDL", txtCHOL_HDL.Text))
            End If

            If txtCHOL_LDL.Text = "" Then
                .Add(New SqlParameter("@CHOL_LDL", DBNull.Value))
            Else
                .Add(New SqlParameter("@CHOL_LDL", txtCHOL_LDL.Text))
            End If

            If txtCHOL_TOTAL.Text = "" Then
                .Add(New SqlParameter("@CHOL_TOTAL", DBNull.Value))
            Else
                .Add(New SqlParameter("@CHOL_TOTAL", txtCHOL_TOTAL.Text))
            End If

            'If txtCREAT_CLEAR_24.Text = "" Then
            .Add(New SqlParameter("@CREAT_CLEAR_24_URINE", DBNull.Value))
            'Else
            ' .Add(New SqlParameter("@CREAT_CLEAR_24_URINE", txtCREAT_CLEAR_24.Text))
            ' End If

            'If txtCREAT_CLEAR_RADIO.Text = "" Then
            .Add(New SqlParameter("@CREAT_CLEAR_RADIO", DBNull.Value))
            'Else
            '.Add(New SqlParameter("@CREAT_CLEAR_RADIO", txtCREAT_CLEAR_RADIO.Text))
            'End If

            If getHeight(CInt(txtRADAR_NO.Text)) <> 0 Then

                lblHeightWarn.Text = ""

                If txtCREAT_CLEAR_SCHZ.Text = "" Then
                    .Add(New SqlParameter("@CREAT_CLEAR_SCHZ", DBNull.Value))

                ElseIf txtCreatSer.Text = "" Then
                    .Add(New SqlParameter("@CREAT_CLEAR_SCHZ", DBNull.Value))
                Else

                    intCCS = (getHeight(txtRADAR_NO.Text) * 40 / CInt(txtCreatSer.Text))

                    If intCCS = 0 Then
                        .Add(New SqlParameter("@CREAT_CLEAR_SCHZ", DBNull.Value))
                    Else
                        .Add(New SqlParameter("@CREAT_CLEAR_SCHZ", intCCS))
                    End If



                End If

            Else
                .Add(New SqlParameter("@CREAT_CLEAR_SCHZ", DBNull.Value))
                lblHeightWarn.Text = "Height not entered on Clinical page"

            End If

            If txtCreatSer.Text = "" Then
                .Add(New SqlParameter("@CREAT_SER", DBNull.Value))
            Else
                .Add(New SqlParameter("@CREAT_SER", txtCreatSer.Text))
            End If

            If txtCRP.Text = "" Then
                .Add(New SqlParameter("@CRP", DBNull.Value))
            Else
                .Add(New SqlParameter("@CRP", txtCRP.Text))
            End If

            If txtDNA_ANTIB.Text = "" Then
                .Add(New SqlParameter("@DNA_ANTIB", DBNull.Value))
            Else
                .Add(New SqlParameter("@DNA_ANTIB", txtDNA_ANTIB.Text))
            End If

            'If txtELISA_ASS.Text = "" Then
            .Add(New System.Data.SqlClient.SqlParameter("@ELISA_ASS", DBNull.Value))
            'Else
            '    .Add(New System.Data.SqlClient.SqlParameter("@ELISA_ASS", txtELISA_ASS.Text))
            'End If

            If txtFERRITIN.Text = "" Then
                .Add(New SqlParameter("@FERRITIN", DBNull.Value))
            Else
                .Add(New SqlParameter("@FERRITIN", txtFERRITIN.Text))
            End If


            If txtHB.Text = "" Then
                .Add(New SqlParameter("@HB", DBNull.Value))
            Else
                .Add(New SqlParameter("@HB", txtHB.Text))
            End If

            If txtINR.Text = "" Then
                .Add(New SqlParameter("@INR", DBNull.Value))
            Else
                .Add(New SqlParameter("@INR", txtINR.Text))
            End If

            If txtNEUTRO.Text = "" Then
                .Add(New SqlParameter("@NEUTRO", DBNull.Value))
            Else
                .Add(New SqlParameter("@NEUTRO", txtNEUTRO.Text))
            End If

            If txtOSMOLALITY.Text = "" Then
                .Add(New SqlParameter("@OSMOLARITY", DBNull.Value))
            Else
                .Add(New SqlParameter("@OSMOLARITY", txtOSMOLALITY.Text))
            End If

            If txtOTHER_INFECT_SP.Text = "" Then
                .Add(New SqlParameter("@OTHER_INFECT_SP", DBNull.Value))
            Else
                .Add(New SqlParameter("@OTHER_INFECT_SP", txtOTHER_INFECT_SP.Text))
            End If

            If txtPHOS.Text = "" Then
                .Add(New SqlParameter("@PHOS", DBNull.Value))
            Else
                .Add(New SqlParameter("@PHOS", txtPHOS.Text))
            End If

            If txtPLATELETS.Text = "" Then
                .Add(New SqlParameter("@PLATELETS", DBNull.Value))
            Else
                .Add(New SqlParameter("@PLATELETS", txtPLATELETS.Text))
            End If

            If txtPROT_CREAT_RAT.Text = "" Then
                .Add(New SqlParameter("@PROT_CREAT_RAT", DBNull.Value))
            Else
                .Add(New SqlParameter("@PROT_CREAT_RAT", txtPROT_CREAT_RAT.Text))
            End If

            If txtPOTASSIUM.Text = "" Then
                .Add(New SqlParameter("@POTASSIUM", DBNull.Value))
            Else
                .Add(New SqlParameter("@POTASSIUM", txtPOTASSIUM.Text))
            End If

            If txtPROTEIN.Text = "" Then
                .Add(New SqlParameter("@PROTEIN", DBNull.Value))
            Else
                .Add(New SqlParameter("@PROTEIN", txtPROTEIN.Text))
            End If

            If txtSODIUM.Text = "" Then
                .Add(New SqlParameter("@SODIUM", DBNull.Value))
            Else
                .Add(New SqlParameter("@SODIUM", txtSODIUM.Text))
            End If

            If txtTHYROX.Text = "" Then
                .Add(New SqlParameter("@THYROX", DBNull.Value))
            Else
                .Add(New SqlParameter("@THYROX", txtTHYROX.Text))
            End If

            If txtTSH.Text = "" Then
                .Add(New SqlParameter("@TSH", DBNull.Value))
            Else
                .Add(New SqlParameter("@TSH", txtTSH.Text))
            End If

            If txtTRIG.Text = "" Then
                .Add(New SqlParameter("@TRIG", DBNull.Value))
            Else
                .Add(New SqlParameter("@TRIG", txtTRIG.Text))
            End If

            If txtUR_VOL_24H.Text = "" Then
                .Add(New SqlParameter("@UR_VOL_24H", DBNull.Value))
            Else
                .Add(New SqlParameter("@UR_VOL_24H", txtUR_VOL_24H.Text))
            End If

            If txtUREA_SER.Text = "" Then
                .Add(New SqlParameter("@UREA_SER", DBNull.Value))
            Else
                .Add(New SqlParameter("@UREA_SER", txtUREA_SER.Text))
            End If

            If txtWBC.Text = "" Then
                .Add(New SqlParameter("@WBC", DBNull.Value))
            Else
                .Add(New SqlParameter("@WBC", txtWBC.Text))
            End If

            If txtIGA.Text = "" Then
                .Add(New SqlParameter("@IGA", DBNull.Value))
            Else
                .Add(New SqlParameter("@IGA", txtIGA.Text))
            End If

            If txtIGG.Text = "" Then
                .Add(New SqlParameter("@IGG", DBNull.Value))
            Else
                .Add(New SqlParameter("@IGG", txtIGG.Text))
            End If

            If txtIGM.Text = "" Then
                .Add(New SqlParameter("@IGM", DBNull.Value))
            Else
                .Add(New SqlParameter("@IGM", txtIGM.Text))
            End If

            If txtANTI_STREP_O.Text = "" Then
                .Add(New SqlParameter("@ANTI_STREP_O", DBNull.Value))
            Else
                .Add(New SqlParameter("@ANTI_STREP_O", txtANTI_STREP_O.Text))
            End If

            ' If txtANTI_SLT.Text = "" Then
            .Add(New SqlParameter("@ANTI_SLT", DBNull.Value))
            'Else
            '.Add(New SqlParameter("@ANTI_SLT", txtANTI_SLT.Text))
            'End If

            'If radAntiBND.Checked Then
            '    .Add(New SqlParameter("@DNA_ANTI_DS", DBNull.Value))
            'ElseIf radAntiBNo.Checked Then
            '    .Add(New SqlParameter("@DNA_ANTI_DS", False))
            'ElseIf radAntiBYes.Checked Then
            '    .Add(New SqlParameter("@DNA_ANTI_DS", True))
            'Else
            '    .Add(New SqlParameter("@DNA_ANTI_DS", DBNull.Value))
            'End If

            If ddlANTI_DS_DNA.SelectedValue = "" Then
                .Add(New SqlParameter("@DNA_ANTI_DS", DBNull.Value))
            Else
                .Add(New SqlParameter("@DNA_ANTI_DS", ddlANTI_DS_DNA.SelectedValue))
            End If



            .Add(New SqlParameter("@BACT_URINE", chkBACT_URINE.Checked))
            .Add(New SqlParameter("@BKV", DBNull.Value)) ' removed
            .Add(New SqlParameter("@BKV_SYM", DBNull.Value)) ' removed

            .Add(New SqlParameter("@CMV_SYM", chkCMV_SYM.Checked))

            '.Add(New SqlParameter("@GLUC_URINE", chkGLUC_URINE.Checked))
            .Add(New SqlParameter("@HANTAVIRUS", DBNull.Value)) 'removed
            .Add(New SqlParameter("@OTHER_INFECT", chkOTHER_INFECT.Checked))


            '.Add(New SqlParameter("@LEUC_URINE", chkLEUC_URINE.Checked))

            If radLeucoYes.Checked Then
                .Add(New SqlParameter("@LEUC_URINE", True))
            ElseIf radLeucoNo.Checked Then
                .Add(New SqlParameter("@LEUC_URINE", False))
            ElseIf radLeucoND.Checked Then
                .Add(New SqlParameter("@LEUC_URINE", DBNull.Value))
            Else
                .Add(New SqlParameter("@LEUC_URINE", DBNull.Value))
            End If

            If radGlucYes.Checked Then
                .Add(New SqlParameter("@GLUC_URINE", True))
            ElseIf radGlucNo.Checked Then
                .Add(New SqlParameter("@GLUC_URINE", False))
            ElseIf radGlucND.Checked Then
                .Add(New SqlParameter("@GLUC_URINE", DBNull.Value))
            Else
                .Add(New SqlParameter("@GLUC_URINE", DBNull.Value))
            End If


            If DropDownListParvo.SelectedValue = "" Then
                .Add(New SqlParameter("@PARVO_ANTIB", DBNull.Value))
            Else
                .Add(New SqlParameter("@PARVO_ANTIB", DropDownListParvo.SelectedValue))
            End If

            If DropDownListCMV.SelectedValue = "" Then
                .Add(New SqlParameter("@CMV", DBNull.Value))
            Else
                .Add(New SqlParameter("@CMV", DropDownListCMV.SelectedValue))
            End If

            If DropDownListEBV.SelectedValue = "" Then
                .Add(New SqlParameter("@EBV", DBNull.Value))
            Else
                .Add(New SqlParameter("@EBV", DropDownListEBV.SelectedValue))
            End If

            If ddlDysEryth.SelectedValue = "" Then
                .Add(New SqlParameter("@DYS_ERYTH_URINE", DBNull.Value))
            Else
                .Add(New SqlParameter("@DYS_ERYTH_URINE", ddlDysEryth.SelectedValue))
            End If

            If ddlRedCellCast.SelectedValue = "" Then
                .Add(New SqlParameter("@RED_CCASTS_URINE", DBNull.Value))
            Else
                .Add(New SqlParameter("@RED_CCASTS_URINE", ddlRedCellCast.SelectedValue))
            End If

            If ddlWhiteCellCast.SelectedValue = "" Then
                .Add(New SqlParameter("@WBC_CASTS_URINE", DBNull.Value))
            Else
                .Add(New SqlParameter("@WBC_CASTS_URINE", ddlWhiteCellCast.SelectedValue))
            End If

            If ddlProteinuria.SelectedValue = "" Then
                .Add(New SqlParameter("@PROTEINURIA_DIP", DBNull.Value))
            Else
                .Add(New SqlParameter("@PROTEINURIA_DIP", ddlProteinuria.SelectedValue))
            End If

            If DropDownList1.SelectedValue = "" Then
                .Add(New SqlParameter("@ANCA", DBNull.Value))
            Else
                .Add(New SqlParameter("@ANCA", DropDownList1.SelectedValue))
            End If


            If DropDownList2.SelectedValue = "" Then
                .Add(New SqlParameter("@ENA", DBNull.Value))
            Else
                .Add(New SqlParameter("@ENA", DropDownList2.SelectedValue))
            End If

            If DropDownList3.SelectedValue = "" Then
                .Add(New SqlParameter("@ANA", DBNull.Value))
            Else
                .Add(New SqlParameter("@ANA", DropDownList3.SelectedValue))
            End If

            If DropDownList5.SelectedValue = "" Then
                .Add(New SqlParameter("@CRYOGLOB", DBNull.Value))
            Else
                .Add(New SqlParameter("@CRYOGLOB", DropDownList5.SelectedValue))
            End If

            If DropDownList6.SelectedValue = "" Then
                .Add(New SqlParameter("@ANTI_GBM", DBNull.Value))
            Else
                .Add(New SqlParameter("@ANTI_GBM", DropDownList6.SelectedValue))
            End If





            If txtCOMP_C3.Text = "" Then
                .Add(New SqlParameter("@COMP_C3", DBNull.Value))
            Else
                .Add(New SqlParameter("@COMP_C3", txtCOMP_C3.Text))
            End If

            If txtCOMP_C4.Text = "" Then
                .Add(New SqlParameter("@COMP_C4", DBNull.Value))
            Else
                .Add(New SqlParameter("@COMP_C4", txtCOMP_C4.Text))
            End If

            If txtCOMP_OTHER.Text = "" Then
                .Add(New SqlParameter("@COMP_OTHER", DBNull.Value))
            Else
                .Add(New SqlParameter("@COMP_OTHER", txtCOMP_OTHER.Text))
            End If

            If DropDownList9.SelectedValue = "" Then
                .Add(New SqlParameter("@HEP_B", DBNull.Value))
            Else
                .Add(New SqlParameter("@HEP_B", DropDownList9.SelectedValue))
            End If

            If DropDownList10.SelectedValue = "" Then
                .Add(New SqlParameter("@HEP_C", DBNull.Value))
            Else
                .Add(New SqlParameter("@HEP_C", DropDownList10.SelectedValue))
            End If

            If DropDownList11.SelectedValue = "" Then
                .Add(New SqlParameter("@HIV", DBNull.Value))
            Else
                .Add(New SqlParameter("@HIV", DropDownList11.SelectedValue))
            End If

            If DropDownList12.SelectedValue = "" Then
                .Add(New SqlParameter("@UR_VOL_24H_COND", DBNull.Value))
            Else
                .Add(New SqlParameter("@UR_VOL_24H_COND", DropDownList12.SelectedValue))
            End If

            If DropDownList13.SelectedValue = "" Then
                .Add(New SqlParameter("@ALBUMINURIA", DBNull.Value))
            Else
                .Add(New SqlParameter("@ALBUMINURIA", DropDownList13.SelectedValue))
            End If

            If radNitriteYes.Checked Then
                .Add(New SqlParameter("@NITRITE", True))
            ElseIf radNitriteNo.Checked Then
                .Add(New SqlParameter("@NITRITE", False))
            Else
                .Add(New SqlParameter("@NITRITE", DBNull.Value))
            End If

            If DropDownList15.SelectedValue = "" Then
                .Add(New SqlParameter("@C3_NEPH_FAC", DBNull.Value))
            Else
                .Add(New SqlParameter("@C3_NEPH_FAC", DropDownList15.SelectedValue))
            End If

            If DropDownList16.SelectedValue = "" Then
                .Add(New SqlParameter("@HAEMATURIA", DBNull.Value))
            Else
                .Add(New SqlParameter("@HAEMATURIA", DropDownList16.SelectedValue))
            End If

            If txtANTI_CLQ.Text = "" Then
                .Add(New SqlParameter("@ANTI_CLQ", DBNull.Value))
            Else
                .Add(New SqlParameter("@ANTI_CLQ", txtANTI_CLQ.Text))
            End If

            .Add(New SqlParameter("@RADAR_NO", txtRADAR_NO.Text))
            .Add(New SqlParameter("@labID", lblLabID.Text))

            If radDNAYes.Checked Then
                .Add(New SqlParameter("@DNA_FACTOR_H", True))
            ElseIf radDNANo.Checked = True Then
                .Add(New SqlParameter("@DNA_FACTOR_H", False))
            Else
                .Add(New SqlParameter("@DNA_FACTOR_H", DBNull.Value))
            End If

            .Add(New SqlParameter("@SEQ_NO", txtSEQ_NO.Text))


        End With

        Try
            objConnect.Open()
            objCommand.ExecuteNonQuery()

        Catch objError As Exception

            lblDebug.Text = "An error occurred: '" & objError.Message & "'"
            'lblDebug.Text = "An error occurred: ccs=" & intCCS
            objConnect.Close()
            Exit Sub

        Finally
            objConnect.Close()
        End Try

        lblUpdate.Text = "<strong>Update successful:</strong>&nbsp;&nbsp;" & Now().ToShortTimeString

        btnSaveNew.Visible = False
        btnUpdate.Visible = True
        ddlRefresh(DropDownList4)
        pagefill(txtSEQ_NO.Text)

    End Sub

    Shared Function getHeight(ByVal intRadar As Integer) As Integer

        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim strSQL As String = "SELECT HEIGHT FROM tbl_ClinicalData WHERE RADAR_NO = " & intRadar
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        Try

            Dim objDataReader As SqlDataReader
            objConnect.Open()
            objDataReader = objCommand.ExecuteReader()


            If objDataReader.Read() Then
                If objDataReader("HEIGHT") Is DBNull.Value Then
                    getHeight = 0
                Else
                    getHeight = CInt(objDataReader("HEIGHT"))
                End If
            Else
                getHeight = 0
            End If

        Finally

            objConnect.Close()


        End Try


    End Function

    Protected Sub chkOTHER_INFECT_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles chkOTHER_INFECT.CheckedChanged

        If chkOTHER_INFECT.Checked = True Then
            rowOther_Infect.Visible = True
        Else
            rowOther_Infect.Visible = False
        End If

    End Sub

    Shared Sub ddlRefresh(ByVal ddl As DropDownList)

        ddl.Items.Clear()
        'ddl.DataBind()

        Dim li As New System.Web.UI.WebControls.ListItem

        li.Value = ""
        li.Text = "Select"
        ddl.Items.Insert(0, li)   'Add(li)
        ddl.AppendDataBoundItems = True

    End Sub

    Function chkDate(ByVal dt As Date) As Boolean

        Dim strSQL As String = "SELECT labID FROM tbl_LabData WHERE [RADAR_NO] =" & Session("patientID") & " AND DATE_LAB_RES = Convert(DATETIME, '" & dt & "',103)"
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

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


    End Function

    Function GetSeqNo(ByVal dtEntry As Date) As Integer

        Dim strSQL As String = "SELECT [SEQ_NO] FROM [tbl_LabData] WHERE ([RADAR_NO] = '" & Session("patientID") & "' AND Dateadd(d, datediff(d,0,[DATE_LAB_RES]),0) = Convert(DATETIME, '" & dtEntry & "',103))"
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
                End If

            Catch objError As Exception
                lblDebug.Text = String.Format("An error occurred: '{0}'{1}", objError.Message, strSQL)
                GetSeqNo = 0
            Finally
                objConnect.Close()
            End Try
        End Using

    End Function

    Protected Sub chkCompOther_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles chkCompOther.CheckedChanged
        If chkCompOther.Checked = True Then
            rowCOMP_OTHER.Visible = True
        Else
            rowCOMP_OTHER.Visible = False
        End If

    End Sub

   
    
    Protected Sub btnUpdate2_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnUpdate2.Click
        pageSave()
    End Sub
End Class



