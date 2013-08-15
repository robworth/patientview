Imports System.Data
Imports System.Data.OleDb
Imports System.Data.SqlClient
Imports System.IO
Partial Class first_treatment
    Inherits System.Web.UI.Page



    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load

        If Not Session("blnAuthenticated") Then
            Response.Redirect("default.aspx")
        End If



        If Session("uType") = "p" Then
            btnSave.Visible = False
            CommonClass.DisableControls(tblMain)
            txtDateTmt.Enabled = False
            txtDateTmt_CalendarExtender.Enabled = False
            lnkTimelines.Visible = False

        Else
            lnkDemographics.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkRelapse.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnk3Months.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkLabResults.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkDiagnosis.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkFirstClinical.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkFirstLab.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkPathology.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkHospital.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkTimelines.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkTimelines.Visible = False
        End If

        'ASPxDateEdit1.MaxDate = DateTime.Today

        txtDateToday.Text = Format(DateTime.Today, "dd-MM-yyyy")

        If Not Page.IsPostBack Then

            'check the Diagnosis has been entered before allowing data entry on this form
            Dim bolDiagCheck As Boolean = CheckDiagnosis(Session("patientID"))

            If bolDiagCheck = False Then

                btnSave.Enabled = False
                'ASPxPopupControl1.ShowOnPageLoad = True


            Else
                'ASPxPopupControl1.ShowOnPageLoad = False

            End If

           

            pagefill()

        End If







    End Sub

    Sub pagefill()

        Dim TDES As New TripleDES()

        txtDateToday.Text = Now()
        lblDebug.Text = ""

        Dim strSQL As String = "SELECT tbl_Therapy.tID, tbl_Therapy.RADAR_NO, tbl_Therapy.SIG_CHANGE_STATUS, tbl_Therapy.P_NSAID, tbl_Therapy.NSAID, tbl_Therapy.P_DIURETIC, tbl_Therapy.DIURETIC, tbl_Therapy.P_ANTI_HTN, tbl_Therapy.ANTI_HTN, tbl_Therapy.P_ACE_INHIB, tbl_Therapy.ACE_INHIB, tbl_Therapy.P_ARB_ANTAG, tbl_Therapy.ARB_ANTAG, tbl_Therapy.P_CA_CH_BLOCK, tbl_Therapy.CA_CH_BLOCK, tbl_Therapy.P_B_BLOCK, tbl_Therapy.B_BLOCK, tbl_Therapy.P_OTHER_ANTI_HTN, tbl_Therapy.OTHER_ANTI_HTN, tbl_Therapy.P_INSULIN, tbl_Therapy.INSULIN, tbl_Therapy.P_LIP_LOWER_AG, tbl_Therapy.LIP_LOWER_AG, tbl_Therapy.P_EPO, tbl_Therapy.EPO, tbl_Therapy.P_OTHER_DRUG1, tbl_Therapy.OTHER_DRUG1, tbl_Therapy.P_OTHER_DRUG2, tbl_Therapy.OTHER_DRUG2, tbl_Therapy.P_OTHER_DRUG3, tbl_Therapy.OTHER_DRUG3, tbl_Therapy.P_OTHER_DRUG4, tbl_Therapy.OTHER_DRUG4, tbl_Therapy.P_IMMUN_SUP, tbl_Therapy.IMMUN_SUP, tbl_Therapy.P_IMMUN_SUP_DRUG, tbl_Therapy.IMMUN_SUP_DRUG, tbl_Therapy.MONOCLONAL_YN, tbl_Therapy.MONOCLONAL_NAME, tbl_Therapy.DATE_TREAT, tbl_Therapy.TMT_MODALITY, tbl_Demographics.SNAME, tbl_Demographics.FNAME, tbl_Demographics.DOB, tbl_Demographics.HOSP_NO, tbl_Diagnosis.DIAG, tbl_ClinicalData.DATE_CLIN_PIC FROM tbl_Therapy INNER JOIN tbl_Demographics ON tbl_Therapy.RADAR_NO = tbl_Demographics.RADAR_NO INNER JOIN tbl_Diagnosis ON tbl_Demographics.RADAR_NO = tbl_Diagnosis.RADAR_NO  INNER JOIN tbl_ClinicalData ON tbl_Demographics.RADAR_NO = tbl_ClinicalData.RADAR_NO WHERE (tbl_Therapy.SEQ_NO = 1 AND tbl_Therapy.RADAR_NO = " & Session("patientID") & ")"
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)


        Try

            Dim objDataReader As SqlDataReader
            objConnect.Open()
            objDataReader = objCommand.ExecuteReader()

            If objDataReader.Read() Then

                txtRADAR_NO.Text = objDataReader("RADAR_NO")
                txtHOSP_NO.Text = TDES.Decrypt(objDataReader("HOSP_NO"))
                lblID.Text = objDataReader("tID")

                If objDataReader("DATE_CLIN_PIC") Is DBNull.Value Then
                    lnk3Months.Enabled = False
                Else
                    lnk3Months.Enabled = True
                End If

                If objDataReader("DATE_TREAT") Is DBNull.Value Then
                Else
                    txtDateTmt.Text = objDataReader("DATE_TREAT")
                End If

                'If objDataReader("TMT_MODALITY") Is DBNull.Value Then
                '    DropDownList1.SelectedValue = ""
                'Else
                '    DropDownList1.SelectedValue = objDataReader("TMT_MODALITY")
                'End If

                'If objDataReader("SIG_CHANGE_STATUS") Is DBNull.Value Then
                '    txtChangeStatus.Text = ""
                'Else
                '    txtChangeStatus.Text = objDataReader("SIG_CHANGE_STATUS")
                'End If

                If objDataReader("DIAG") Is DBNull.Value Then
                    txtDIAG.Text = ""
                Else
                    txtDIAG.Text = CommonClass.GetDiagnosis(objDataReader("DIAG").ToString)
                End If

                txtFNAME.Text = TDES.Decrypt(objDataReader("FNAME"))
                txtSNAME.Text = TDES.Decrypt(objDataReader("SNAME"))

                Dim dt As Date = CDate(TDES.Decrypt(objDataReader("DOB")))
                txtDOB.Text = Format(dt, "dd-MMM-yyyy")

                If objDataReader("DIAG") = 1 Then 'SRNS

                    rowNSAID.Visible = True
                    rowDiuretic.Visible = True
                    rowInsulin.Visible = True
                    rowLipid.Visible = True
                    'rowNSAIDPrior.Visible = True
                    'rowDiureticPrior.Visible = True
                    'rowInsulinPrior.Visible = True
                    'rowLipidPrior.Visible = True
                    'rowNSAIDCurrent.Visible = True
                    'rowDiureticCurrent.Visible = True
                    'rowInsulinCurrent.Visible = True
                    'rowLipidCurrent.Visible = True
                    'tblPrior.Visible = True
                    lblCurrent.Text = "Current"
                    lblPage.Text = "5B"

                ElseIf objDataReader("DIAG") = 2 Then  'MPGN

                    rowNSAID.Visible = False
                    rowDiuretic.Visible = False
                    rowInsulin.Visible = False
                    rowLipid.Visible = False
                    tdPrior.Attributes.CssStyle.Add("display", "none")
                    tdNSAID.Attributes.CssStyle.Add("display", "none")
                    tdDiuretic.Attributes.CssStyle.Add("display", "none")
                    tdANTI_HTN.Attributes.CssStyle.Add("display", "none")
                    tdACE_INHIB.Attributes.CssStyle.Add("display", "none")
                    tdARB_ANTAG.Attributes.CssStyle.Add("display", "none")
                    tdCA_CH_BLOCK.Attributes.CssStyle.Add("display", "none")
                    tdB_BLOCK.Attributes.CssStyle.Add("display", "none")
                    tdOTHER_ANTI_HTN.Attributes.CssStyle.Add("display", "none")
                    tdINSULIN.Attributes.CssStyle.Add("display", "none")
                    tdLIPID_LOWER.Attributes.CssStyle.Add("display", "none")
                    tdEPO.Attributes.CssStyle.Add("display", "none")
                    tdOTHER_DRUG1.Attributes.CssStyle.Add("display", "none")
                    tdOTHER_DRUG2.Attributes.CssStyle.Add("display", "none")
                    tdOTHER_DRUG3.Attributes.CssStyle.Add("display", "none")
                    tdOTHER_DRUG4.Attributes.CssStyle.Add("display", "none")
                    'rowNSAIDPrior.Visible = False
                    'rowDiureticPrior.Visible = False
                    'rowInsulinPrior.Visible = False
                    'rowLipidPrior.Visible = False
                    'rowNSAIDCurrent.Visible = False
                    'rowDiureticCurrent.Visible = False
                    'rowInsulinCurrent.Visible = False
                    'rowLipidCurrent.Visible = False
                    'tblPrior.Visible = False
                    lblCurrent.Text = "Drugs in the 4 weeks after Biopsy"
                    lblPage.Text = "5A"

                End If



                If objDataReader("P_NSAID") Is DBNull.Value Then
                    radNsaidInitNo.Checked = False
                    radNsaidInitYes.Checked = False
                ElseIf objDataReader("P_NSAID") = True Then
                    radNsaidInitYes.Checked = True
                ElseIf objDataReader("P_NSAID") = False Then
                    radNsaidInitNo.Checked = True
                End If

                If objDataReader("P_DIURETIC") Is DBNull.Value Then
                    radP_DiureticNo.Checked = False
                    radP_DiureticYes.Checked = False
                ElseIf objDataReader("P_DIURETIC") = True Then
                    radP_DiureticYes.Checked = True
                ElseIf objDataReader("P_DIURETIC") = False Then
                    radP_DiureticNo.Checked = True
                End If

                If objDataReader("P_ANTI_HTN") Is DBNull.Value Then
                    radP_ANTI_HtnNo.Checked = False
                    radP_ANTI_HtnYes.Checked = False
                ElseIf objDataReader("P_ANTI_HTN") = True Then
                    radP_ANTI_HtnYes.Checked = True
                ElseIf objDataReader("P_ANTI_HTN") = False Then
                    radP_ANTI_HtnNo.Checked = True
                End If

                If objDataReader("P_ACE_INHIB") Is DBNull.Value Then
                    radP_ACE_InhibNo.Checked = False
                    radP_ACE_InhibYes.Checked = False
                ElseIf objDataReader("P_ACE_INHIB") = True Then
                    radP_ACE_InhibYes.Checked = True
                ElseIf objDataReader("P_ACE_INHIB") = False Then
                    radP_ACE_InhibNo.Checked = True
                End If

                If objDataReader("P_ARB_ANTAG") Is DBNull.Value Then
                    radP_ARB_AntagNo.Checked = False
                    radP_ARB_AntagYes.Checked = False
                ElseIf objDataReader("P_ARB_ANTAG") = True Then
                    radP_ARB_AntagYes.Checked = True
                ElseIf objDataReader("P_ARB_ANTAG") = False Then
                    radP_ARB_AntagNo.Checked = True
                End If

                If objDataReader("P_CA_CH_BLOCK") Is DBNull.Value Then
                    radP_CA_CH_BlockNo.Checked = False
                    radP_CA_CH_BlockYes.Checked = False
                ElseIf objDataReader("P_CA_CH_BLOCK") = True Then
                    radP_CA_CH_BlockYes.Checked = True
                ElseIf objDataReader("P_CA_CH_BLOCK") = False Then
                    radP_CA_CH_BlockNo.Checked = True
                End If

                If objDataReader("P_B_BLOCK") Is DBNull.Value Then
                    radP_B_BlockNo.Checked = False
                    radP_B_BlockYes.Checked = False
                ElseIf objDataReader("P_B_BLOCK") = True Then
                    radP_B_BlockYes.Checked = True
                ElseIf objDataReader("P_B_BLOCK") = False Then
                    radP_B_BlockNo.Checked = True
                End If

                If objDataReader("P_OTHER_ANTI_HTN") Is DBNull.Value Then
                    radP_OTHER_ANTI_HtnNo.Checked = False
                    radP_OTHER_ANTI_HtnYes.Checked = False
                ElseIf objDataReader("P_OTHER_ANTI_HTN") = True Then
                    radP_OTHER_ANTI_HtnYes.Checked = True
                ElseIf objDataReader("P_OTHER_ANTI_HTN") = False Then
                    radP_OTHER_ANTI_HtnNo.Checked = True
                End If

                If objDataReader("P_INSULIN") Is DBNull.Value Then
                    radP_InsulinNo.Checked = False
                    radP_InsulinYes.Checked = False
                ElseIf objDataReader("P_INSULIN") = True Then
                    radP_InsulinYes.Checked = True
                ElseIf objDataReader("P_INSULIN") = False Then
                    radP_InsulinNo.Checked = True
                End If

                If objDataReader("P_LIP_LOWER_AG") Is DBNull.Value Then
                    radP_LIP_LOWER_AgNo.Checked = False
                    radP_LIP_LOWER_AgYes.Checked = False
                ElseIf objDataReader("P_LIP_LOWER_AG") = True Then
                    radP_LIP_LOWER_AgYes.Checked = True
                ElseIf objDataReader("P_LIP_LOWER_AG") = False Then
                    radP_LIP_LOWER_AgNo.Checked = True
                End If

                If objDataReader("P_EPO") Is DBNull.Value Then
                    radP_EPONo.Checked = False
                    radP_EPOYes.Checked = False
                ElseIf objDataReader("P_EPO") = True Then
                    radP_EPOYes.Checked = True
                ElseIf objDataReader("P_EPO") = False Then
                    radP_EPONo.Checked = True
                End If


                If objDataReader("NSAID") Is DBNull.Value Then
                    radNsaidNo.Checked = False
                    radNsaidYes.Checked = False
                ElseIf objDataReader("NSAID") = True Then
                    radNsaidYes.Checked = True
                ElseIf objDataReader("NSAID") = False Then
                    radNsaidNo.Checked = True
                End If

                If objDataReader("DIURETIC") Is DBNull.Value Then
                    radDiureticNo.Checked = False
                    radDiureticYes.Checked = False
                ElseIf objDataReader("DIURETIC") = True Then
                    radDiureticYes.Checked = True
                ElseIf objDataReader("DIURETIC") = False Then
                    radDiureticNo.Checked = True
                End If

                If objDataReader("ANTI_HTN") Is DBNull.Value Then
                    radANTI_HtnNo.Checked = False
                    radANTI_HtnYes.Checked = False
                ElseIf objDataReader("ANTI_HTN") = True Then
                    radANTI_HtnYes.Checked = True
                ElseIf objDataReader("ANTI_HTN") = False Then
                    radANTI_HtnNo.Checked = True
                End If

                If objDataReader("ACE_INHIB") Is DBNull.Value Then
                    radACE_InhibNo.Checked = False
                    radACE_InhibYes.Checked = False
                ElseIf objDataReader("ACE_INHIB") = True Then
                    radACE_InhibYes.Checked = True
                ElseIf objDataReader("ACE_INHIB") = False Then
                    radACE_InhibNo.Checked = True
                End If

                If objDataReader("ARB_ANTAG") Is DBNull.Value Then
                    radARB_AntagNo.Checked = False
                    radARB_AntagYes.Checked = False
                ElseIf objDataReader("ARB_ANTAG") = True Then
                    radARB_AntagYes.Checked = True
                ElseIf objDataReader("ARB_ANTAG") = False Then
                    radARB_AntagNo.Checked = True
                End If

                If objDataReader("CA_CH_BLOCK") Is DBNull.Value Then
                    radCA_CH_BlockNo.Checked = False
                    radCA_CH_BlockYes.Checked = False
                ElseIf objDataReader("CA_CH_BLOCK") = True Then
                    radCA_CH_BlockYes.Checked = True
                ElseIf objDataReader("CA_CH_BLOCK") = False Then
                    radCA_CH_BlockNo.Checked = True
                End If

                If objDataReader("B_BLOCK") Is DBNull.Value Then
                    radB_BlockNo.Checked = False
                    radB_BlockYes.Checked = False
                ElseIf objDataReader("B_BLOCK") = True Then
                    radB_BlockYes.Checked = True
                ElseIf objDataReader("B_BLOCK") = False Then
                    radB_BlockNo.Checked = True
                End If

                If objDataReader("OTHER_ANTI_HTN") Is DBNull.Value Then
                    radOTHER_ANTI_HtnNo.Checked = False
                    radOTHER_ANTI_HtnYes.Checked = False
                ElseIf objDataReader("OTHER_ANTI_HTN") = True Then
                    radOTHER_ANTI_HtnYes.Checked = True
                ElseIf objDataReader("OTHER_ANTI_HTN") = False Then
                    radOTHER_ANTI_HtnNo.Checked = True
                End If

                If objDataReader("INSULIN") Is DBNull.Value Then
                    radInsulinNo.Checked = False
                    radInsulinYes.Checked = False
                ElseIf objDataReader("INSULIN") = True Then
                    radInsulinYes.Checked = True
                ElseIf objDataReader("INSULIN") = False Then
                    radInsulinNo.Checked = True
                End If

                If objDataReader("LIP_LOWER_AG") Is DBNull.Value Then
                    radLIP_LOWER_AgNo.Checked = False
                    radLIP_LOWER_AgYes.Checked = False
                ElseIf objDataReader("LIP_LOWER_AG") = True Then
                    radLIP_LOWER_AgYes.Checked = True
                ElseIf objDataReader("LIP_LOWER_AG") = False Then
                    radLIP_LOWER_AgNo.Checked = True
                End If

                If objDataReader("EPO") Is DBNull.Value Then
                    radEPONo.Checked = False
                    radEPOYes.Checked = False
                ElseIf objDataReader("EPO") = True Then
                    radEPOYes.Checked = True
                ElseIf objDataReader("EPO") = False Then
                    radEPONo.Checked = True
                End If


                'chkDIURETIC.Checked = objDataReader("DIURETIC")
                'chkNSAID.Checked = objDataReader("NSAID")
                'chkANTI_HTN.Checked = objDataReader("ANTI_HTN")
                'chkACE_INHIB.Checked = objDataReader("ACE_INHIB")
                'chkARB_ANTAG.Checked = objDataReader("ARB_ANTAG")
                'chkCA_CH_BLOCK.Checked = objDataReader("CA_CH_BLOCK")
                'chkB_BLOCK.Checked = objDataReader("B_BLOCK")
                'chkOTHER_ANTI_HTN.Checked = objDataReader("OTHER_ANTI_HTN")
                'chkINSULIN.Checked = objDataReader("INSULIN")
                'chkLIP_LOWER_AG.Checked = objDataReader("LIP_LOWER_AG")
                'chkEPO.Checked = objDataReader("EPO")

                txtP_OTHER_DRUG1.Text = chkNull(objDataReader("P_OTHER_DRUG1"))
                txtOTHER_DRUG1.Text = chkNull(objDataReader("OTHER_DRUG1"))
                txtP_OTHER_DRUG2.Text = chkNull(objDataReader("P_OTHER_DRUG2"))
                txtOTHER_DRUG2.Text = chkNull(objDataReader("OTHER_DRUG2"))
                txtP_OTHER_DRUG3.Text = chkNull(objDataReader("P_OTHER_DRUG3"))
                txtOTHER_DRUG3.Text = chkNull(objDataReader("OTHER_DRUG3"))
                txtP_OTHER_DRUG4.Text = chkNull(objDataReader("P_OTHER_DRUG4"))
                txtOTHER_DRUG4.Text = chkNull(objDataReader("OTHER_DRUG4"))

                'chkP_IMMUN_SUP.Checked = objDataReader("P_IMMUN_SUP")
                'chkIMMUN_SUP.Checked = objDataReader("IMMUN_SUP")

                'If objDataReader("P_IMMUN_SUP_DRUG") Is DBNull.Value Then

                'Else
                '    ddlImmunoSupp1.SelectedValue = objDataReader("P_IMMUN_SUP_DRUG")
                'End If

                'If objDataReader("IMMUN_SUP_DRUG") Is DBNull.Value Then
                'Else
                '    ddlImmunoSupp2.SelectedValue = objDataReader("IMMUN_SUP_DRUG")
                'End If

                'chkMONOCLONAL_YN.Checked = objDataReader("MONOCLONAL_YN")

                'If objDataReader("MONOCLONAL_NAME") Is DBNull.Value Then
                'Else
                '    ddlMONO.SelectedValue = objDataReader("MONOCLONAL_NAME")
                'End If



                If (chkTrue(objDataReader("P_ANTI_HTN")) = True Or chkTrue(objDataReader("ANTI_HTN")) = True) Then

                    rowACE.Visible = True
                    rowARB.Visible = True
                    rowCalcium.Visible = True
                    rowBeta.Visible = True
                    rowOther.Visible = True
                    'rowACEPrior.Visible = True
                    'rowARBPrior.Visible = True
                    'rowBetaPrior.Visible = True
                    'rowOtherPrior.Visible = True
                    'rowCalciumPrior.Visible = True
                    'rowBetaCurrent.Visible = True
                    'rowOtherCurrent.Visible = True
                    'rowAceCurrent.Visible = True
                    'rowARBCurrent.Visible = True
                    'rowCalciumCurrent.Visible = True

                Else

                    rowACE.Visible = False
                    rowARB.Visible = False
                    rowCalcium.Visible = False
                    rowBeta.Visible = False
                    rowOther.Visible = False
                    'rowBetaPrior.Visible = False
                    'rowOtherPrior.Visible = False
                    'rowACEPrior.Visible = False
                    'rowARBPrior.Visible = False
                    'rowCalciumPrior.Visible = False
                    'rowBetaCurrent.Visible = False
                    'rowOtherCurrent.Visible = False
                    'rowAceCurrent.Visible = False
                    'rowARBCurrent.Visible = False
                    'rowCalciumCurrent.Visible = False

                End If



            End If

        Catch objError As Exception

            lblDebug.Text = String.Format("An error occurred (PageFill): '{0}'{1}", objError.Message, strSQL)


        Finally

            objConnect.Close()
            'lblDebug.Text = strSQL
        End Try



    End Sub


    Shared Function chkNull(ByVal value As Object) As String

        If value Is DBNull.Value Then
            chkNull = ""
        Else
            chkNull = value.ToString
        End If

    End Function


    'Protected Sub chkAntiHyperInit_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles chkAntiHyperInit.CheckedChanged

    '    If chkAntiHyperInit.Checked = True Then

    '    End If

    'End Sub

    Function CheckDiagnosis(ByVal patientID As Integer) As Boolean

        Dim strSQL As String = "SELECT [DATE_DIAG] FROM [tbl_Diagnosis] WHERE [RADAR_NO] = " & Session("patientID")
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Using objConnect As New SqlConnection(strConnect)
            Using objCommand As New SqlCommand(strSQL, objConnect)
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
            End Using
        End Using


    End Function
  
    Protected Sub btnSave_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnSave.Click
        pageSave()
    End Sub

    Sub pageSave()
        Dim strSQL As String = "UPDATE [tbl_Therapy] SET [RADAR_NO] = @RADAR_NO, [SIG_CHANGE_STATUS] = @SIG_CHANGE_STATUS, [P_NSAID] = @P_NSAID, [NSAID] = @NSAID, [P_DIURETIC] = @P_DIURETIC, [DIURETIC] = @DIURETIC, [P_ANTI_HTN] = @P_ANTI_HTN, [ANTI_HTN] = @ANTI_HTN, [P_ACE_INHIB] = @P_ACE_INHIB, [ACE_INHIB] = @ACE_INHIB, [P_ARB_ANTAG] = @P_ARB_ANTAG, [ARB_ANTAG] = @ARB_ANTAG, [P_CA_CH_BLOCK] = @P_CA_CH_BLOCK, [CA_CH_BLOCK] = @CA_CH_BLOCK, [P_B_BLOCK] = @P_B_BLOCK, [B_BLOCK] = @B_BLOCK, [P_OTHER_ANTI_HTN] = @P_OTHER_ANTI_HTN, [OTHER_ANTI_HTN] = @OTHER_ANTI_HTN, [P_INSULIN] = @P_INSULIN, [INSULIN] = @INSULIN, [P_LIP_LOWER_AG] = @P_LIP_LOWER_AG, [LIP_LOWER_AG] = @LIP_LOWER_AG, [P_EPO] = @P_EPO, [EPO] = @EPO, [P_OTHER_DRUG1] = @P_OTHER_DRUG1, [OTHER_DRUG1] = @OTHER_DRUG1, [P_OTHER_DRUG2] = @P_OTHER_DRUG2, [OTHER_DRUG2] = @OTHER_DRUG2, [P_OTHER_DRUG3] = @P_OTHER_DRUG3, [OTHER_DRUG3] = @OTHER_DRUG3, [P_OTHER_DRUG4] = @P_OTHER_DRUG4, [OTHER_DRUG4] = @OTHER_DRUG4, [DATE_TREAT] = @DATE_TREAT, [TMT_MODALITY] = @TMT_MODALITY, [SEQ_NO] = @SEQ_NO WHERE [tID] = @tID"
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        With objCommand.Parameters

            .Add(New SqlParameter("@SEQ_NO", 1))
            .Add(New SqlParameter("@RADAR_NO", txtRADAR_NO.Text))
            .Add(New SqlParameter("@tID", lblID.Text))

            If txtDateTmt.Text = "" Then
                .Add(New SqlParameter("@DATE_TREAT", DBNull.Value))
            Else
                .Add(New SqlParameter("@DATE_TREAT", CDate(txtDateTmt.Text)))
            End If

            'If txtChangeStatus.Text = "" Then
            .Add(New SqlParameter("@SIG_CHANGE_STATUS", DBNull.Value))
            'Else
            '    .Add(New System.Data.SqlClient.SqlParameter("@SIG_CHANGE_STATUS", txtChangeStatus.Text))
            'End If

            'If DropDownList1.SelectedValue = "" Then
            .Add(New SqlParameter("@TMT_MODALITY", DBNull.Value))
            'Else
            '.Add(New SqlParameter("@TMT_MODALITY", DropDownList1.SelectedValue))
            'End If

            '.Add(New System.Data.SqlClient.SqlParameter("@P_NSAID", chkP_NSAID.Checked))

            '-------------------------------------------------------------
            '# Prior drugs not used for MPGN

            '# NSAID and Diurectic SRNS only

            If radNsaidInitNo.Checked = True Then
                .Add(New SqlParameter("@P_NSAID", False))
            ElseIf radNsaidInitYes.Checked = True Then
                .Add(New SqlParameter("@P_NSAID", True))
            Else
                .Add(New SqlParameter("@P_NSAID", DBNull.Value))
            End If

            If radP_DiureticNo.Checked = True Then
                .Add(New SqlParameter("@P_DIURETIC", False))
            ElseIf radP_DiureticYes.Checked = True Then
                .Add(New SqlParameter("@P_DIURETIC", True))
            Else
                .Add(New SqlParameter("@P_DIURETIC", DBNull.Value))
            End If

            '#--------------------------------------------------------------

            If radP_ANTI_HtnNo.Checked = True Then
                .Add(New SqlParameter("@P_ANTI_HTN", False))
            ElseIf radP_ANTI_HtnYes.Checked = True Then
                .Add(New SqlParameter("@P_ANTI_HTN", True))
            Else
                .Add(New SqlParameter("@P_ANTI_HTN", DBNull.Value))
            End If

            '# If P_ANTI_HTN not checked, write null to the following drugs

            If radP_ANTI_HtnNo.Checked Then

                .Add(New SqlParameter("@P_ACE_INHIB", DBNull.Value))
                .Add(New SqlParameter("@P_ARB_ANTAG", DBNull.Value))
                .Add(New SqlParameter("@P_CA_CH_BLOCK", DBNull.Value))
                .Add(New SqlParameter("@P_B_BLOCK", DBNull.Value))
                .Add(New SqlParameter("@P_OTHER_ANTI_HTN", DBNull.Value))

            Else

                If radP_ACE_InhibNo.Checked = True Then
                    .Add(New SqlParameter("@P_ACE_INHIB", False))
                ElseIf radP_ACE_InhibYes.Checked = True Then
                    .Add(New SqlParameter("@P_ACE_INHIB", True))
                Else
                    .Add(New SqlParameter("@P_ACE_INHIB", DBNull.Value))
                End If

                If radP_ARB_AntagNo.Checked = True Then
                    .Add(New SqlParameter("@P_ARB_ANTAG", False))
                ElseIf radP_ARB_AntagYes.Checked = True Then
                    .Add(New SqlParameter("@P_ARB_ANTAG", True))
                Else
                    .Add(New SqlParameter("@P_ARB_ANTAG", DBNull.Value))
                End If

                If radP_CA_CH_BlockNo.Checked = True Then
                    .Add(New SqlParameter("@P_CA_CH_BLOCK", False))
                ElseIf radP_CA_CH_BlockYes.Checked = True Then
                    .Add(New SqlParameter("@P_CA_CH_BLOCK", True))
                Else
                    .Add(New SqlParameter("@P_CA_CH_BLOCK", DBNull.Value))
                End If

                If radP_B_BlockNo.Checked = True Then
                    .Add(New SqlParameter("@P_B_BLOCK", False))
                ElseIf radP_B_BlockYes.Checked = True Then
                    .Add(New SqlParameter("@P_B_BLOCK", True))
                Else
                    .Add(New SqlParameter("@P_B_BLOCK", DBNull.Value))
                End If

                If radP_OTHER_ANTI_HtnNo.Checked = True Then
                    .Add(New SqlParameter("@P_OTHER_ANTI_HTN", False))
                ElseIf radP_OTHER_ANTI_HtnYes.Checked = True Then
                    .Add(New SqlParameter("@P_OTHER_ANTI_HTN", True))
                Else
                    .Add(New SqlParameter("@P_OTHER_ANTI_HTN", DBNull.Value))
                End If

            End If

            '#----------------------------------------------------------------

            If radP_InsulinNo.Checked = True Then
                .Add(New SqlParameter("@P_INSULIN", False))
            ElseIf radP_InsulinYes.Checked = True Then
                .Add(New SqlParameter("@P_INSULIN", True))
            Else
                .Add(New SqlParameter("@P_INSULIN", DBNull.Value))
            End If

            If radP_LIP_LOWER_AgNo.Checked = True Then
                .Add(New SqlParameter("@P_LIP_LOWER_AG", False))
            ElseIf radP_LIP_LOWER_AgYes.Checked = True Then
                .Add(New SqlParameter("@P_LIP_LOWER_AG", True))
            Else
                .Add(New SqlParameter("@P_LIP_LOWER_AG", DBNull.Value))
            End If

            If radP_EPONo.Checked = True Then
                .Add(New SqlParameter("@P_EPO", False))
            ElseIf radP_EPOYes.Checked = True Then
                .Add(New SqlParameter("@P_EPO", True))
            Else
                .Add(New SqlParameter("@P_EPO", DBNull.Value))
            End If



            '#-------------------------------------------------

            '# NSAID and Diuretic are SRNS only

            If radNsaidNo.Checked = True Then
                .Add(New SqlParameter("@NSAID", False))
            ElseIf radNsaidYes.Checked = True Then
                .Add(New SqlParameter("@NSAID", True))
            Else
                .Add(New SqlParameter("@NSAID", DBNull.Value))
            End If

            If radDiureticNo.Checked = True Then
                .Add(New SqlParameter("@DIURETIC", False))
            ElseIf radDiureticYes.Checked = True Then
                .Add(New SqlParameter("@DIURETIC", True))
            Else
                .Add(New SqlParameter("@DIURETIC", DBNull.Value))
            End If

            '#--------------------------------------------------


            '# need to save nulls to drugs not used if anti htn not selected

            If radANTI_HtnNo.Checked = True Then
                .Add(New SqlParameter("@ANTI_HTN", False))
            ElseIf radANTI_HtnYes.Checked = True Then
                .Add(New SqlParameter("@ANTI_HTN", True))
            Else
                .Add(New SqlParameter("@ANTI_HTN", DBNull.Value))
            End If

            '#---------------------------------------------------

            '# These drugs, down to Insulin, only used if ANTI_HTN is checked
            '# So if ANTI_HTN not checked, we write NULL to these

            If radANTI_HtnNo.Checked Then

                .Add(New SqlParameter("@ACE_INHIB", DBNull.Value))
                .Add(New SqlParameter("@ARB_ANTAG", DBNull.Value))
                .Add(New SqlParameter("@CA_CH_BLOCK", DBNull.Value))
                .Add(New SqlParameter("@B_BLOCK", DBNull.Value))
                .Add(New SqlParameter("@OTHER_ANTI_HTN", DBNull.Value))

            Else

                If radACE_InhibNo.Checked = True Then
                    .Add(New SqlParameter("@ACE_INHIB", False))
                ElseIf radACE_InhibYes.Checked = True Then
                    .Add(New SqlParameter("@ACE_INHIB", True))
                Else
                    .Add(New SqlParameter("@ACE_INHIB", DBNull.Value))
                End If

                If radARB_AntagNo.Checked = True Then
                    .Add(New SqlParameter("@ARB_ANTAG", False))
                ElseIf radARB_AntagYes.Checked = True Then
                    .Add(New SqlParameter("@ARB_ANTAG", True))
                Else
                    .Add(New SqlParameter("@ARB_ANTAG", DBNull.Value))
                End If

                If radCA_CH_BlockNo.Checked = True Then
                    .Add(New SqlParameter("@CA_CH_BLOCK", False))
                ElseIf radCA_CH_BlockYes.Checked = True Then
                    .Add(New SqlParameter("@CA_CH_BLOCK", True))
                Else
                    .Add(New SqlParameter("@CA_CH_BLOCK", DBNull.Value))
                End If

                If radB_BlockNo.Checked = True Then
                    .Add(New SqlParameter("@B_BLOCK", False))
                ElseIf radB_BlockYes.Checked = True Then
                    .Add(New SqlParameter("@B_BLOCK", True))
                Else
                    .Add(New SqlParameter("@B_BLOCK", DBNull.Value))
                End If

                If radOTHER_ANTI_HtnNo.Checked = True Then
                    .Add(New SqlParameter("@OTHER_ANTI_HTN", False))
                ElseIf radOTHER_ANTI_HtnYes.Checked = True Then
                    .Add(New SqlParameter("@OTHER_ANTI_HTN", True))
                Else
                    .Add(New SqlParameter("@OTHER_ANTI_HTN", DBNull.Value))
                End If


            End If

            '#--------------------------------------------------------


            If radInsulinNo.Checked = True Then
                .Add(New SqlParameter("@INSULIN", False))
            ElseIf radInsulinYes.Checked = True Then
                .Add(New SqlParameter("@INSULIN", True))
            Else
                .Add(New SqlParameter("@INSULIN", DBNull.Value))
            End If

            If radLIP_LOWER_AgNo.Checked = True Then
                .Add(New SqlParameter("@LIP_LOWER_AG", False))
            ElseIf radLIP_LOWER_AgYes.Checked = True Then
                .Add(New SqlParameter("@LIP_LOWER_AG", True))
            Else
                .Add(New SqlParameter("@LIP_LOWER_AG", DBNull.Value))
            End If

            If radEPONo.Checked = True Then
                .Add(New SqlParameter("@EPO", False))
            ElseIf radEPOYes.Checked = True Then
                .Add(New SqlParameter("@EPO", True))
            Else
                .Add(New SqlParameter("@EPO", DBNull.Value))
            End If




            '.Add(New System.Data.SqlClient.SqlParameter("@P_IMMUN_SUP", chkP_IMMUN_SUP.Checked))
            '.Add(New System.Data.SqlClient.SqlParameter("@IMMUN_SUP", chkIMMUN_SUP.Checked))
            '.Add(New System.Data.SqlClient.SqlParameter("@MONOCLONAL_YN", chkMONOCLONAL_YN.Checked))

            If txtP_OTHER_DRUG1.Text = "" Then
                .Add(New SqlParameter("@P_OTHER_DRUG1", DBNull.Value))
            Else
                .Add(New SqlParameter("@P_OTHER_DRUG1", txtP_OTHER_DRUG1.Text))
            End If

            If txtOTHER_DRUG1.Text = "" Then
                .Add(New SqlParameter("@OTHER_DRUG1", DBNull.Value))
            Else
                .Add(New SqlParameter("@OTHER_DRUG1", txtOTHER_DRUG1.Text))
            End If

            If txtP_OTHER_DRUG2.Text = "" Then
                .Add(New SqlParameter("@P_OTHER_DRUG2", DBNull.Value))
            Else
                .Add(New SqlParameter("@P_OTHER_DRUG2", txtP_OTHER_DRUG2.Text))
            End If

            If txtOTHER_DRUG2.Text = "" Then
                .Add(New SqlParameter("@OTHER_DRUG2", DBNull.Value))
            Else
                .Add(New SqlParameter("@OTHER_DRUG2", txtOTHER_DRUG2.Text))
            End If


            If txtP_OTHER_DRUG3.Text = "" Then
                .Add(New SqlParameter("@P_OTHER_DRUG3", DBNull.Value))
            Else
                .Add(New SqlParameter("@P_OTHER_DRUG3", txtP_OTHER_DRUG3.Text))
            End If

            If txtOTHER_DRUG3.Text = "" Then
                .Add(New SqlParameter("@OTHER_DRUG3", DBNull.Value))
            Else
                .Add(New SqlParameter("@OTHER_DRUG3", txtOTHER_DRUG3.Text))
            End If

            If txtP_OTHER_DRUG4.Text = "" Then
                .Add(New SqlParameter("@P_OTHER_DRUG4", DBNull.Value))
            Else
                .Add(New SqlParameter("@P_OTHER_DRUG4", txtP_OTHER_DRUG4.Text))
            End If

            If txtOTHER_DRUG4.Text = "" Then
                .Add(New SqlParameter("@OTHER_DRUG4", DBNull.Value))
            Else
                .Add(New SqlParameter("@OTHER_DRUG4", txtOTHER_DRUG4.Text))
            End If

            If ddlImmunoSupp1.SelectedValue = "" Then
                .Add(New SqlParameter("@P_IMMUN_SUP_DRUG", DBNull.Value))
            Else
                .Add(New SqlParameter("@P_IMMUN_SUP_DRUG", ddlImmunoSupp1.SelectedValue))
            End If

            'If ddlImmunoSupp2.SelectedValue = "" Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@IMMUN_SUP_DRUG", DBNull.Value))
            'Else
            '    .Add(New System.Data.SqlClient.SqlParameter("@IMMUN_SUP_DRUG", ddlImmunoSupp2.SelectedValue))
            'End If

            'If ddlMONO.SelectedValue = "" Then
            '    .Add(New System.Data.SqlClient.SqlParameter("@MONOCLONAL_NAME", DBNull.Value))
            'Else
            '    .Add(New System.Data.SqlClient.SqlParameter("@MONOCLONAL_NAME", ddlMONO.SelectedValue))
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

        lblSucess.Text = String.Format("<strong>Update successful:</strong>&nbsp;&nbsp;{0}", Now().ToShortTimeString)
        lblSucess2.Text = String.Format("<strong>Update successful:</strong>&nbsp;&nbsp;{0}", Now().ToShortTimeString)

        pagefill()


    End Sub

    Protected Sub btnHDAdd_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnHDAdd.Click



        If ddlHD.SelectedIndex = 0 Then
            ddlHD.BackColor = Drawing.Color.LemonChiffon
            Exit Sub
        Else
            ddlHD.BackColor = Drawing.Color.White
        End If

        If txtDATE_START_DIAL.Text = "" Then
            txtDATE_START_DIAL.BackColor = Drawing.Color.LemonChiffon
            Exit Sub
        End If

        'If txtDATE_STOP_DIAL.Text = "" Then
        '    txtDATE_STOP_DIAL.BackColor = Drawing.Color.LemonChiffon
        '    Exit Sub
        'End If

        If txtDATE_STOP_DIAL.Text = "" Then
            'OK, stop date not known
        Else

            ' If IsDate(txtDATE_STOP_DIAL.Text) Then

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

            'Else
            'lblStopWarn.Text = "Enter a date"
            'lblWarn.Text = ""
            'Exit Sub
            'lblDebug.Text = "This far"

        End If



        Dim intD_TMT_MODALITY As Integer = ddlHD.SelectedValue
        Dim dtDATE_START_DIAL As Date = CDate(txtDATE_START_DIAL.Text)

        Dim dtDATE_STOP_DIAL As Date

        If txtDATE_STOP_DIAL.Text = "" Then
            dtDATE_STOP_DIAL = CDate(txtDATE_START_DIAL.Text).AddDays(1)  ' can we get another treatment in between existing dates
        Else
            dtDATE_STOP_DIAL = CDate(txtDATE_STOP_DIAL.Text)

        End If

        If CheckDates(Session("patientID"), 1, dtDATE_START_DIAL, dtDATE_STOP_DIAL, intD_TMT_MODALITY) = False Then

            lblWarn.Text = "Dates conflict with treatments above"
            'lblDebug.Text = dtDATE_STOP_DIAL
            Exit Sub
        Else
            lblWarn.Text = ""
        End If

       


        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RadarConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)
        'Dim strSQL As String = "INSERT INTO tbl_RRT_HD (RADAR_NO, HD_TMT_MODALITY, DATE_START_HDIAL, DATE_STOP_HDIAL) VALUES (@RADAR_NO, @HD_TMT_MODALITY, @DATE_START_HDIAL, @DATE_STOP_HDIAL)"

        Dim strSQL As String = "INSERT INTO tbl_RRT_TREATMENT (RADAR_NO, MODALITY, DATE_START, DATE_STOP, UNIT_CODE, FIRST_FLAG) VALUES (@RADAR_NO, @HD_TMT_MODALITY, @DATE_START_DIAL, @DATE_STOP_DIAL, @UNIT_ID, @FIRST_FLAG)"


        Dim objCommand As New SqlCommand(strSQL, objConnect)
        Try
            With objCommand.Parameters

                .Add(New SqlParameter("@RADAR_NO", Session("patientID")))
                .Add(New SqlParameter("@HD_TMT_MODALITY", intD_TMT_MODALITY))
                .Add(New SqlParameter("@DATE_START_DIAL", dtDATE_START_DIAL))
                If txtDATE_STOP_DIAL.Text = "" Then
                    .Add(New SqlParameter("@DATE_STOP_DIAL", DBNull.Value))
                Else
                    .Add(New SqlParameter("@DATE_STOP_DIAL", dtDATE_STOP_DIAL))
                End If

                .Add(New SqlParameter("@UNIT_ID", Session("unitID")))
                .Add(New SqlParameter("@FIRST_FLAG", True))


            End With

            objConnect.Open()
            objCommand.ExecuteNonQuery()

        Catch objError As Exception
            lblDebug.Text = objError.Message

        Finally
            objConnect.Close()
        End Try

        GridView2.DataBind()
        ddlHD.ClearSelection()
        ddlHD.Enabled = True
        ddlHD.BackColor = Drawing.Color.White
        txtDATE_START_DIAL.Text = ""
        txtDATE_STOP_DIAL.Text = ""
        ddlHD.BackColor = Drawing.Color.White
        txtDATE_START_DIAL.BackColor = Drawing.Color.White
        txtDATE_STOP_DIAL.BackColor = Drawing.Color.White


    End Sub

    'Protected Sub btnPDAdd_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnPDAdd.Click

    '    If ddlPD.SelectedIndex = 0 Then
    '        ddlPD.BackColor = Drawing.Color.LemonChiffon
    '        Exit Sub
    '    End If

    '    If txtDATE_START_PD.Text = "" Then
    '        txtDATE_START_PD.BackColor = Drawing.Color.LemonChiffon
    '        Exit Sub
    '    End If

    '    If txtDATE_STOP_PD.Text = "" Then
    '        txtDATE_STOP_PD.BackColor = Drawing.Color.LemonChiffon
    '        Exit Sub
    '    End If

    '    Dim intPD_TMT_MODALITY As Integer = ddlPD.SelectedValue
    '    Dim dtDATE_START_PD As Date = CDate(txtDATE_START_PD.Text)
    '    Dim dtDATE_STOP_PD As Date = CDate(txtDATE_STOP_PD.Text)

    '    Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RadarConnectionString").ConnectionString
    '    Dim objConnect As New System.Data.SqlClient.SqlConnection(strConnect)
    '    'Dim strSQL As String = "INSERT INTO tbl_RRT_PD (RADAR_NO, PD_TMT_MODALITY, DATE_START_PD, DATE_STOP_PD) VALUES (@RADAR_NO, @PD_TMT_MODALITY, @DATE_START_PD, @DATE_STOP_PD)"

    '    Dim strSQL As String = "INSERT INTO tbl_RRT_TREATMENT (RADAR_NO, MODALITY, DATE_START, DATE_STOP, UNIT_CODE) VALUES (@RADAR_NO, @PD_TMT_MODALITY, @DATE_START_PD, @DATE_STOP_PD, @UNIT_ID)"
    '    Dim objCommand As New System.Data.SqlClient.SqlCommand(strSQL, objConnect)
    '    Try
    '        With objCommand.Parameters

    '            .Add(New System.Data.SqlClient.SqlParameter("@RADAR_NO", Session("patientID")))
    '            .Add(New System.Data.SqlClient.SqlParameter("@PD_TMT_MODALITY", intPD_TMT_MODALITY))
    '            .Add(New System.Data.SqlClient.SqlParameter("@DATE_START_PD", dtDATE_START_PD))
    '            .Add(New System.Data.SqlClient.SqlParameter("@DATE_STOP_PD", dtDATE_STOP_PD))
    '            .Add(New System.Data.SqlClient.SqlParameter("@UNIT_ID", Session("unitID")))



    '        End With

    '        objConnect.Open()
    '        objCommand.ExecuteNonQuery()

    '    Catch

    '    Finally
    '        objConnect.Close()
    '    End Try

    '    ddlPD.Enabled = False
    '    txtDATE_START_PD.Enabled = False
    '    txtDATE_STOP_PD.Enabled = False
    '    txtDATE_START_PD.BackColor = Drawing.Color.White
    '    txtDATE_STOP_PD.BackColor = Drawing.Color.White
    '    txtDATE_START_PD_CalendarExtender.Enabled = False
    '    txtDATE_STOP_PD_CalendarExtender.Enabled = False

    'End Sub

    Function CheckDates(ByVal intRADAR_NO As Integer, ByVal intType As Integer, ByVal dtStart As Date, ByVal dtEnd As Date, ByVal intModality As Integer) As Boolean


        Dim strStartDate As String = CStr(Year(dtStart)) & "-" & CStr(Month(dtStart)) & "-" & CStr(Day(dtStart))
        Dim strEndDate As String = CStr(Year(dtEnd)) & "-" & CStr(Month(dtEnd)) & "-" & CStr(Day(dtEnd))


        dtStart = CDate(strStartDate)
        dtEnd = CDate(strEndDate)


        Dim strSQL As String
        Dim strSQL2 As String
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RadarConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)

        '#check for open plasma treatments

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


        '#check dates dont overlap etc

        Select Case intType

            Case 1  ' Dialysis
                strSQL = "SELECT RADAR_NO FROM [tbl_RRT_TREATMENT] WHERE ((Cast(@START_DATE as smalldatetime) BETWEEN [DATE_START] AND [DATE_STOP]) AND ([MODALITY] <= '19') AND ([RADAR_NO] = @RADAR_NO))"

            Case 3  ' Plasmapheresis
                strSQL = "SELECT RADAR_NO FROM [tbl_RRT_PLASMA] WHERE (((Cast(@START_DATE As smalldatetime) BETWEEN [DATE_START_PLASMAPH] AND [DATE_STOP_PLASMAPH]) OR (Cast(@END_DATE as smalldatetime) BETWEEN [DATE_START_PLASMAPH] AND [DATE_STOP_PLASMAPH] ) OR ((cast(@START_DATE as smalldatetime) <= [DATE_START_PLASMAPH]) AND (Cast(@END_DATE as smalldatetime) >= [DATE_START_PLASMAPH]))) AND [RADAR_NO] = @RADAR_NO)"

        End Select

        'lblDebug.Text = strSQL

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
                lblWarnP.Text = "Dates conflict with treatments above"
                'lblDebug.Text = "dates conflict:"

            Else

                CheckDates = True
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



    Protected Sub btnAddMono_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnAddMono.Click

        If txtImmunoStart.Text = "" Then
            txtImmunoStart.BackColor = Drawing.Color.LemonChiffon
            Exit Sub
        Else
            txtImmunoStart.BackColor = Drawing.Color.White
        End If

        If ddlImmunoSupp1.SelectedIndex = 0 Then
            ddlImmunoSupp1.BackColor = Drawing.Color.LemonChiffon
            Exit Sub
        Else
            ddlImmunoSupp1.BackColor = Drawing.Color.White
        End If

        If txtImmunoEnd.Text = "" Then
            'ok end date not known
        Else

            If IsDate(txtImmunoEnd.Text) Then


                If CDate(txtImmunoEnd.Text) > CDate(txtDateToday.Text) Then
                    lblStopWarn.Text = "> today"
                    lblWarn.Text = ""
                    Exit Sub
                Else
                    lblStopWarn.Text = ""
                End If

                If CDate(txtImmunoEnd.Text) < CDate(txtImmunoStart.Text) Then
                    lblStopWarn.Text = "< start"
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



        Dim intIMMUNSUP_DRUG As Integer = ddlImmunoSupp1.SelectedValue

        Dim dtStart As Date = CDate(txtImmunoStart.Text)
        Dim dtStop As Date

        If txtImmunoEnd.Text = "" Then
            dtStop = CDate(txtImmunoStart.Text).AddDays(1)
        Else
            dtStop = CDate(txtImmunoEnd.Text)

        End If
       

        If CheckDatesMono(Session("patientID"), dtStart, dtStop, ddlImmunoSupp1.SelectedValue) = False Then
            Exit Sub
        Else
            lblWarnImmuno.Text = ""
        End If

        Dim strSQL As String = "INSERT INTO tbl_IMMUNSUP_TREATMENT (IMMUNSUP_DRUG_STARTDATE, IMMUNSUP_DRUG, IMMUNSUP_DRUG_ENDDATE, CYCLOPHOS_TOT_DOSE, RADAR_NO, FIRST_FLAG) VALUES (@START_DATE, @DRUG, @END_DATE, @DOSE, @RADAR_NO, @FIRST_FLAG)"
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        Try
            With objCommand.Parameters

                .Add(New SqlParameter("@RADAR_NO", Session("patientID")))
                .Add(New SqlParameter("@START_DATE", dtStart))

                If txtImmunoEnd.Text = "" Then
                    .Add(New SqlParameter("@END_DATE", DBNull.Value))
                Else
                    .Add(New SqlParameter("@END_DATE", dtStop))
                End If

                .Add(New SqlParameter("@DRUG", intIMMUNSUP_DRUG))

                If txtIMMUN_SUP_DOSE.Text = "" Then
                    .Add(New SqlParameter("@DOSE", DBNull.Value))
                Else
                    .Add(New SqlParameter("@DOSE", CDec(txtIMMUN_SUP_DOSE.Text)))
                End If

                .Add(New SqlParameter("@FIRST_FLAG", True))

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

        txtImmunoStart.Text = ""
        txtImmunoEnd.Text = ""
        ddlImmunoSupp1.SelectedIndex = 0
        txtIMMUN_SUP_DOSE.Text = ""
        txtIMMUN_SUP_DOSE.Visible = False
        lblDose.Visible = False

        GridView1.DataBind()

    End Sub

    Protected Sub btnUpdateMono_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnUpdateMono.Click

        If txtMonoSDEdit.Text = "" Then
            txtMonoSDEdit.BackColor = Drawing.Color.LemonChiffon
            lblMonoEditWarn.Text = "Enter a date"
            Exit Sub
        Else
            txtMonoSDEdit.BackColor = Drawing.Color.White
            lblMonoEditWarn.Text = ""
        End If

        If CDate(txtMonoSDEdit.Text) > CDate(txtDateToday.Text) Then
            txtMonoSDEdit.BackColor = Drawing.Color.LemonChiffon
            Exit Sub
        Else
            txtMonoSDEdit.BackColor = Drawing.Color.White
        End If

        If Dropdownlist2.SelectedValue = "" Then
            Dropdownlist2.BackColor = Drawing.Color.LemonChiffon
            Exit Sub
        Else
            Dropdownlist2.BackColor = Drawing.Color.White
        End If

        If txtMonoEDEdit.Text = "" Then
            'ok end date not known
        Else


            If IsDate(txtMonoEDEdit.Text) Then


                If CDate(txtMonoEDEdit.Text) > CDate(txtDateToday.Text) Then
                    txtMonoEDEdit.BackColor = Drawing.Color.LemonChiffon
                    lblMonoEDEditWarn.Text = "> today"
                    Exit Sub
                Else
                    txtMonoEDEdit.BackColor = Drawing.Color.White
                    lblMonoEDEditWarn.Text = ""
                End If

                If CDate(txtMonoEDEdit.Text) < CDate(txtMonoSDEdit.Text) Then
                    lblMonoEDEditWarn.Text = "< start date"
                    txtMonoEDEdit.BackColor = Drawing.Color.LemonChiffon
                    Exit Sub
                Else
                    lblMonoEDEditWarn.Text = ""
                    txtMonoEDEdit.BackColor = Drawing.Color.White
                End If

            Else
                lblMonoEDEditWarn.Text = "Enter a date"
            End If
        End If

        If Dropdownlist2.SelectedValue = 8 Then

            If Session("diag") = 1 Then
                If txtMonoDoseEdit.Text = "" Then
                    txtMonoDoseEdit.BackColor = Drawing.Color.LemonChiffon
                    Exit Sub
                End If
            End If

        End If

        Dim dtStart As Date = CDate(txtMonoSDEdit.Text)
        Dim dtStop As Date = CDate(txtMonoEDEdit.Text)

        Dim intMonoID As Integer = CInt(lblmID.Text)

        If CheckDatesMono2(Session("patientID"), dtStart, dtStop, Dropdownlist2.SelectedValue, intMonoID) = False Then
            'lblMonoEditWarn.Text = "Dates conflict with a treatment above"
            Exit Sub
        Else
            lblWarnImmuno.Text = ""
        End If




        Dim strSQL As String = "UPDATE [tbl_IMMUNSUP_TREATMENT] SET [IMMUNSUP_DRUG_STARTDATE] = @IMMUNSUP_DRUG_STARTDATE, [IMMUNSUP_DRUG_ENDDATE] = @IMMUNSUP_DRUG_ENDDATE, [IMMUNSUP_DRUG] = @IMMUNSUP_DRUG, [CYCLOPHOS_TOT_DOSE] = @CYCLOPHOS_TOT_DOSE WHERE [tID] = @tID"
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        With objCommand.Parameters

            .Add(New SqlParameter("@tID", intMonoID))
            .Add(New SqlParameter("@IMMUNSUP_DRUG_STARTDATE", CDate(txtMonoSDEdit.Text)))

            If txtMonoEDEdit.Text = "" Then
                .Add(New SqlParameter("@IMMUNSUP_DRUG_ENDDATE", DBNull.Value))
            Else
                .Add(New SqlParameter("@IMMUNSUP_DRUG_ENDDATE", CDate(txtMonoEDEdit.Text)))

            End If

            .Add(New SqlParameter("@IMMUNSUP_DRUG", Dropdownlist2.SelectedValue))


            If Session("diag") = 1 Then
                If txtMonoDoseEdit.Text = "" Then
                    .Add(New SqlParameter("@CYCLOPHOS_TOT_DOSE", DBNull.Value))
                ElseIf Dropdownlist2.SelectedValue = 8 Then
                    .Add(New SqlParameter("@CYCLOPHOS_TOT_DOSE", txtMonoDoseEdit.Text))
                Else
                    .Add(New SqlParameter("@CYCLOPHOS_TOT_DOSE", DBNull.Value))
                End If
            Else
                .Add(New SqlParameter("@CYCLOPHOS_TOT_DOSE", DBNull.Value))
            End If


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

        pnlIEdit.Visible = False
        GridView1.Enabled = True
        GridView1.DataBind()
        btnAddMono.Enabled = True
        'PageFill(Session("intRecord"))

    End Sub

    Function CheckDatesMono(ByVal intRADAR_NO As Integer, ByVal dtStart As Date, ByVal dtStop As Date, ByVal intImmunoDrugID As Integer) As Boolean


        '# most have stop dates before allowing new entries
        '# the same drug cannot have concurrent entries
        '# different drugs may be given concurrently
        '# cyclophospahmide may start on day a previous cyclo treatment stops
        '# all others must start at least next day after previous

        Dim strStartDate As String = CStr(Year(dtStart)) & "-" & CStr(Month(dtStart)) & "-" & CStr(Day(dtStart))

        dtStart = CDate(strStartDate)


        '# check for unclosed entries [ this removed 6 Apr 10 ]

        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New System.Data.SqlClient.SqlConnection(strConnect)

        'Dim strSQL2 As String = "SELECT [RADAR_NO] FROM [tbl_IMMUNSUP_TREATMENT] WHERE [RADAR_NO] = @RADAR_NO AND [IMMUNSUP_DRUG_ENDDATE] IS NULL"

        'Dim objCommand2 As New System.Data.SqlClient.SqlCommand(strSQL2, objConnect)

        'Try

        '    With objCommand2.Parameters

        '        .Add(New SqlParameter("@RADAR_NO", intRADAR_NO))

        '    End With

        'Dim objDataReader As SqlDataReader
        'objConnect.Open()
        '    objDataReader = objCommand2.ExecuteReader()


        '    If objDataReader.Read() Then

        '        lblWarnImmuno.Text = "Please close open treatments"
        '        objConnect.Close()
        '        Exit Function
        '    Else
        '        lblWarnImmuno.Text = ""

        '    End If



        'Catch ex As Exception
        '    lblDebug.Text = ex.Message

        'Finally

        '    objConnect.Close()

        'End Try


        '# now check for overlapping dates

        Dim strSQL As String = "SELECT RADAR_NO FROM [tbl_IMMUNSUP_TREATMENT] WHERE (((cast(@START_DATE as smalldatetime) >= [IMMUNSUP_DRUG_STARTDATE] AND cast(@START_DATE as smalldatetime) <= [IMMUNSUP_DRUG_ENDDATE]) OR (cast(@END_DATE as smalldatetime) > [IMMUNSUP_DRUG_STARTDATE] AND cast(@END_DATE as smalldatetime) < [IMMUNSUP_DRUG_ENDDATE] ) OR (cast(@START_DATE as smalldatetime) <= [IMMUNSUP_DRUG_STARTDATE]) AND (cast(@END_DATE as smalldatetime)  >= [IMMUNSUP_DRUG_ENDDATE])) AND [RADAR_NO] = @RADAR_NO) AND [IMMUNSUP_DRUG] = @IMMUNSUP_DRUG"

        Dim objCommand As New System.Data.SqlClient.SqlCommand(strSQL, objConnect)


        Try

            With objCommand.Parameters

                .Add(New SqlParameter("@RADAR_NO", intRADAR_NO))


                .Add(New SqlParameter("@IMMUNSUP_DRUG", intImmunoDrugID))
                .Add(New SqlParameter("@END_DATE", dtStop))

                Select Case ddlImmunoSupp1.SelectedValue
                    Case 8 'Cyclophosphamide
                        .Add(New SqlParameter("@START_DATE", dtStart.AddDays(1)))
                    Case Else 'other drugs
                        .Add(New SqlParameter("@START_DATE", dtStart))
                End Select

            End With


            Dim objDataReader As SqlDataReader
            objConnect.Open()
            objDataReader = objCommand.ExecuteReader()


            If objDataReader.Read() Then

                CheckDatesMono = False
                lblWarnImmuno.Text = "Dates conflict with those above"
                'lblDebug.Text = "dates conflict: " & dtStart & "<br/>" & dtStop & "<br/>" & intImmunoDrugID

            Else

                CheckDatesMono = True
                lblWarnImmuno.Text = ""
                'lblDebug.Text = "dates don't conflict: " & strSQL

            End If

            ' lblDebug.Text = strSQL

        Catch objError As Exception

            lblDebug.Text = "An error occurred (CheckDatesMono): '" & dtStart & "  " & dtStop & "<br/>" & objError.Message & "'" & strSQL
            objConnect.Close()
            Exit Function

        Finally

            objConnect.Close()

        End Try

    End Function

    Function CheckDatesMono2(ByVal intRADAR_NO As Integer, ByVal dtStart As Date, ByVal dtStop As Date, ByVal intImmunoDrugID As Integer, ByVal recordID As Integer) As Boolean

        '# this for edited entries
        '# omit current treatment in validation search

        '# most have stop dates before allowing new entries
        '# the same drug cannot have concurrent entries
        '# different drugs may be given concurrently
        '# cyclophospahmide may start on day a previous cyclo treatment stops
        '# all others must start at least next day

        Dim strStartDate As String = CStr(Year(dtStart)) & "-" & CStr(Month(dtStart)) & "-" & CStr(Day(dtStart))

        dtStart = CDate(strStartDate)


        '# check for unclosed entries - not needed for editing 11-Aug-10

        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New System.Data.SqlClient.SqlConnection(strConnect)

        'Dim strSQL2 As String = "SELECT [RADAR_NO] FROM [tbl_IMMUNSUP_TREATMENT] WHERE [RADAR_NO] = @RADAR_NO AND [IMMUNSUP_DRUG_ENDDATE] IS NULL"

        'Dim objCommand2 As New System.Data.SqlClient.SqlCommand(strSQL2, objConnect)

        'Try

        '    With objCommand2.Parameters

        '        .Add(New SqlParameter("@RADAR_NO", intRADAR_NO))

        '    End With

        '    Dim objDataReader As SqlDataReader
        '    objConnect.Open()
        '    objDataReader = objCommand2.ExecuteReader()


        '    If objDataReader.Read() Then

        '        lblMonoEditWarn.Text = "Please close open treatments"
        '        objConnect.Close()
        '        Exit Function
        '    Else
        '        lblWarnImmuno.Text = ""

        '    End If



        'Catch ex As Exception
        '    lblDebug.Text = ex.Message

        'Finally

        '    objConnect.Close()

        'End Try


        '# now check for overlapping dates

        Dim strSQL As String = "SELECT RADAR_NO FROM [tbl_IMMUNSUP_TREATMENT] WHERE (((cast(@START_DATE as smalldatetime) >= [IMMUNSUP_DRUG_STARTDATE] AND cast(@START_DATE as smalldatetime) <= [IMMUNSUP_DRUG_ENDDATE]) OR (cast(@END_DATE as smalldatetime) > [IMMUNSUP_DRUG_STARTDATE] AND cast(@END_DATE as smalldatetime) < [IMMUNSUP_DRUG_ENDDATE] ) OR (cast(@START_DATE as smalldatetime) <= [IMMUNSUP_DRUG_STARTDATE]) AND (cast(@END_DATE as smalldatetime)  >= [IMMUNSUP_DRUG_STARTDATE])) AND ([RADAR_NO] = @RADAR_NO AND [IMMUNSUP_DRUG] = @IMMUNSUP_DRUG AND [tID] <> @tID) )"

        Dim objCommand As New System.Data.SqlClient.SqlCommand(strSQL, objConnect)


        Try

            With objCommand.Parameters

                .Add(New SqlParameter("@RADAR_NO", intRADAR_NO))


                .Add(New SqlParameter("@IMMUNSUP_DRUG", intImmunoDrugID))
                .Add(New SqlParameter("@END_DATE", dtStop))
                .Add(New SqlParameter("@tID", recordID))

                Select Case ddlImmunoSupp1.SelectedValue
                    Case 8 'Cyclophosphamide
                        .Add(New SqlParameter("@START_DATE", dtStart.AddDays(1)))
                    Case Else 'other drugs
                        .Add(New SqlParameter("@START_DATE", dtStart))
                End Select

            End With


            Dim objDataReader As SqlDataReader
            objConnect.Open()
            objDataReader = objCommand.ExecuteReader()


            If objDataReader.Read() Then

                CheckDatesMono2 = False
                lblMonoEditWarn.Text = "Dates conflict with those above"
                'lblDebug.Text = "dates conflict: " & dtStart & "<br/>" & dtStop & "<br/>" & intImmunoDrugID

            Else

                CheckDatesMono2 = True
                lblMonoEditWarn.Text = ""
                'lblDebug.Text = "dates don't conflict: " & strSQL

            End If

            ' lblDebug.Text = strSQL

        Catch objError As Exception

            lblDebug.Text = "An error occurred (CheckDatesMono2): '" & dtStart & "  " & dtStop & "<br/>" & objError.Message & "'" & strSQL
            objConnect.Close()
            Exit Function

        Finally

            objConnect.Close()

        End Try

    End Function

    Protected Sub btnCancelUpdateMono_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnCancelUpdateMono.Click
        pnlIEdit.Visible = False
        GridView1.Enabled = True
        btnAddMono.Enabled = True
        pagefill()

    End Sub

    Sub GridView1_Row_Editing(ByVal sender As Object, ByVal e As GridViewEditEventArgs)
        Dim intID As Integer = CInt(GridView1.Rows(e.NewEditIndex).Cells(0).Text)
        e.Cancel = True 'cancel the default edit mode
        Dropdownlist2.Items.Clear()
        Dropdownlist2.DataBind()
        lblmID.Text = intID.ToString

        Dim strSQL As String = "SELECT [IMMUNSUP_DRUG_STARTDATE], [IMMUNSUP_DRUG_ENDDATE], [IMMUNSUP_DRUG], [CYCLOPHOS_TOT_DOSE] FROM [tbl_IMMUNSUP_TREATMENT] WHERE [tID] = " & intID
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Using objConnect As New SqlConnection(strConnect)
            Dim objCommand As New SqlCommand(strSQL, objConnect)
            Try
                Dim objDataReader As SqlDataReader
                objConnect.Open()
                objDataReader = objCommand.ExecuteReader()

                If objDataReader.Read() Then

                    txtMonoDoseEdit.Text = objDataReader("CYCLOPHOS_TOT_DOSE").ToString
                    Dropdownlist2.SelectedValue = objDataReader("IMMUNSUP_DRUG")
                    CalendarExtender1.SelectedDate = CDate(objDataReader("IMMUNSUP_DRUG_STARTDATE"))
                    If objDataReader("IMMUNSUP_DRUG_ENDDATE") IsNot DBNull.Value Then
                        CalendarExtender2.SelectedDate = CDate(objDataReader("IMMUNSUP_DRUG_ENDDATE"))
                    End If

                    If Session("diag") = 1 Then

                        If objDataReader("IMMUNSUP_DRUG") = 8 Then
                            txtMonoDoseEdit.Visible = True
                            lblMonoDoseEdit.Visible = True
                        End If

                    End If

                End If

            Catch objError As Exception
                lblDebug.Text = String.Format("An error occurred: '{0}'{1}", objError.Message, strSQL)
            Finally
                objConnect.Close()
            End Try
        End Using

        pnlIEdit.Visible = True
        GridView1.Enabled = False
        btnAddMono.Enabled = False
    End Sub

    Protected Sub ddlImmunoSupp1_SelectedIndexChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles ddlImmunoSupp1.SelectedIndexChanged

        If Session("diag") = 1 Then


            If ddlImmunoSupp1.SelectedValue = 8 Then
                lblDose.Visible = True
                txtIMMUN_SUP_DOSE.Visible = True
            Else
                lblDose.Visible = False
                txtIMMUN_SUP_DOSE.Visible = False
            End If

        End If

    End Sub

    Protected Sub Dropdownlist2_SelectedIndexChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles Dropdownlist2.SelectedIndexChanged

        If Session("diag") = 1 Then

            If Dropdownlist2.SelectedValue = 8 Then
                lblMonoDoseEdit.Visible = True
                txtMonoDoseEdit.Visible = True
            Else
                lblMonoDoseEdit.Visible = False
                txtMonoDoseEdit.Visible = False
            End If

        End If
    End Sub



    Protected Sub radP_ANTI_HtnYes_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radP_ANTI_HtnYes.CheckedChanged

        If radP_ANTI_HtnYes.Checked = True Then

            Session("bolAntiHyperP") = True
            rowAce.Visible = True
            rowARB.Visible = True
            rowCalcium.Visible = True
            rowBeta.Visible = True
            rowOther.Visible = True
            'rowACEPrior.Visible = True
            'rowARBPrior.Visible = True
            'rowBetaPrior.Visible = True
            'rowOtherPrior.Visible = True
            'rowCalciumPrior.Visible = True
            'rowBetaCurrent.Visible = True
            'rowOtherCurrent.Visible = True
            'rowAceCurrent.Visible = True
            'rowARBCurrent.Visible = True
            'rowCalciumCurrent.Visible = True

        End If
        'lblDebug.Text = "BolP = " & Session("bolAntiHyperP") & " BolC = " & Session("bolAntiHyperC")

    End Sub

    Protected Sub radP_ANTI_HtnNo_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radP_ANTI_HtnNo.CheckedChanged

        If radP_ANTI_HtnNo.Checked = True Then

            Session("bolAntiHyperP") = False

            If Session("bolAntiHyperC") = True Then
                'do nothing
            Else
                rowAce.Visible = False
                rowARB.Visible = False
                rowCalcium.Visible = False
                rowBeta.Visible = False
                rowOther.Visible = False
                'rowACEPrior.Visible = False
                'rowARBPrior.Visible = False
                'rowBetaPrior.Visible = False
                'rowOtherPrior.Visible = False
                'rowCalciumPrior.Visible = False
                'rowAceCurrent.Visible = False
                'rowARBCurrent.Visible = False
                'rowCalciumCurrent.Visible = False
                'rowBetaCurrent.Visible = False
                'rowOtherCurrent.Visible = False
            End If

        End If

        'lblDebug.Text = "BolP = " & Session("bolAntiHyperP") & " BolC = " & Session("bolAntiHyperC")
    End Sub

    Protected Sub radANTI_HtnYes_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radANTI_HtnYes.CheckedChanged

        If radANTI_HtnYes.Checked = True Then

            Session("bolAntiHyperC") = True
            rowAce.Visible = True
            rowARB.Visible = True
            rowCalcium.Visible = True
            rowBeta.Visible = True
            rowOther.Visible = True
            'rowACEPrior.Visible = True
            'rowARBPrior.Visible = True
            'rowBetaPrior.Visible = True
            'rowOtherPrior.Visible = True
            'rowCalciumPrior.Visible = True
            'rowBetaCurrent.Visible = True
            'rowOtherCurrent.Visible = True
            'rowAceCurrent.Visible = True
            'rowARBCurrent.Visible = True
            'rowCalciumCurrent.Visible = True

        End If

        'lblDebug.Text = "BolP = " & Session("bolAntiHyperP") & " BolC = " & Session("bolAntiHyperC")

    End Sub

    Protected Sub radANTI_HtnNo_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radANTI_HtnNo.CheckedChanged

        If radANTI_HtnNo.Checked = True Then

            Session("bolAntiHyperC") = False

            If Session("bolAntiHyperP") = True Then
                'do nothing
            Else

                rowAce.Visible = False
                rowARB.Visible = False
                rowCalcium.Visible = False
                rowBeta.Visible = False
                rowOther.Visible = False
                'rowACEPrior.Visible = False
                'rowARBPrior.Visible = False
                'rowBetaPrior.Visible = False
                'rowOtherPrior.Visible = False
                'rowCalciumPrior.Visible = False
                'rowAceCurrent.Visible = False
                'rowARBCurrent.Visible = False
                'rowCalciumCurrent.Visible = False
                'rowBetaCurrent.Visible = False
                'rowOtherCurrent.Visible = False

            End If

        End If

        'lblDebug.Text = "BolP = " & Session("bolAntiHyperP") & " BolC = " & Session("bolAntiHyperC")


    End Sub

    Protected Sub Page_UnLoad(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Unload
        'Session("bolAntiHyperP") = False
        'Session("bolAntiHyperC") = False
    End Sub

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

        If ddlPlasmaExch.SelectedIndex = 0 Then
            ddlPlasmaExch.BackColor = Drawing.Color.LemonChiffon
            Exit Sub
        Else
            ddlPlasmaExch.BackColor = Drawing.Color.White
        End If

        If ddlPlasResponse.SelectedIndex = 0 Then
            ddlPlasResponse.BackColor = Drawing.Color.LemonChiffon
            Exit Sub
        Else
            ddlPlasResponse.BackColor = Drawing.Color.White
        End If

        Dim dtDATE_START_PLASMAPH As Date = CDate(txtDATE_START_PLASMAPH.Text)
        'Dim dtDATE_STOP_PLASMAPH As Date = CDate(txtDATE_STOP_PLASMAPH.Text)
        Dim intNO_EXCH_PLASMAPH As Integer = ddlPlasmaExch.SelectedValue
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
        ddlPlasmaExch.SelectedIndex = 0
        ddlPlasResponse.SelectedIndex = 0


    End Sub

    Function GetResponse(ByVal intResponse As Object) As String

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




    Protected Sub btnCancelDialEdit_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnCancelDialEdit.Click
        pnlDiEdit.Visible = False
        GridView2.Enabled = True
        btnHDAdd.Enabled = True
        pagefill()
    End Sub

    Protected Sub btnUpdateDial_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnUpdateDial.Click

        Dim intDialID As Integer = CInt(lblDialID.Text)
        Dim dtStart As Date = txtEditDialStart.Text
        Dim dtStop As Date


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
            dtStop = CDate(txtEditDialStart.Text).AddDays(1)
        Else
            dtStop = CDate(txtEditDialStop.Text)

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



        If CheckDates2(Session("patientID"), 1, dtStart, dtStop, ddlEditDial.SelectedValue) = False Then

            lblDialEditWarn.Text = "Dates conflict with treatments above"
            'lblDebug.Text = dtDATE_STOP_DIAL
            Exit Sub
        Else
            lblWarn.Text = ""
        End If


        Dim strSQL As String = "UPDATE [tbl_RRT_TREATMENT] SET [DATE_START] = @DATE_START, [DATE_STOP] = @DATE_STOP, [MODALITY] = @MODALITY WHERE [tID] = @tID  "
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

        pnlDiEdit.Visible = False
        GridView2.Enabled = True
        GridView2.DataBind()
        btnHDAdd.Enabled = True
        pagefill()

    End Sub

    Protected Sub btnCancelPlasmaUpdate_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnCancelPlasmaUpdate.Click
        pnlPlasmaEdit.Visible = False
        GridView3.Enabled = True
        btnPlasmaAdd.Enabled = True
        pagefill()
    End Sub

    Sub gridView3_plasma_edit(ByVal sender As Object, ByVal e As GridViewEditEventArgs)

        Dim intID As Integer = CInt(GridView3.Rows(e.NewEditIndex).Cells(0).Text)
        e.Cancel = True 'cancel the default edit

        ddllPlasmaExchEdit.Items.Clear()
        ddllPlasmaExchEdit.DataBind()



        Dim strSQL As String = "SELECT [plID], [DATE_START_PLASMAPH], [DATE_STOP_PLASMAPH], [NO_EXCH_PLASMAPH], [DUR_PLASMAPH], [RESPONSE_TO_PLASMA] FROM [tbl_RRT_PLASMA] WHERE [plID] = '" & intID & "'"
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        Try

            'declare a variable to hold the a DataReader object
            Dim objDataReader As SqlDataReader
            objConnect.Open()
            'execute the SQL statement
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

                ddllPlasmaExchEdit.SelectedValue = CInt(objDataReader("NO_EXCH_PLASMAPH"))
                DropDownList3.SelectedValue = objDataReader("RESPONSE_TO_PLASMA")
                lblpID.Text = objDataReader("plID")

            End If


        Catch objError As Exception
            lblDebug.Text = objError.Message
        Finally
            objConnect.Close()

        End Try

        pnlPlasmaEdit.Visible = True
        GridView3.Enabled = False
        btnPlasmaAdd.Enabled = False
        lblPlasmaStopEditWarn.Text = ""
        lblWarnP.Text = ""

    End Sub


    Sub GridView2_Row_Editing(ByVal sender As Object, ByVal e As GridViewEditEventArgs) Handles GridView2.RowEditing

        Dim intID As Integer = CInt(GridView2.Rows(e.NewEditIndex).Cells(0).Text)
        e.Cancel = True 'cancel the default edit mode
        ddlEditDial.Items.Clear()
        ddlEditDial.DataBind()


        Dim strSQL As String = "SELECT [tID], [DATE_START], [DATE_STOP], [MODALITY] FROM [tbl_RRT_TREATMENT] WHERE tid = '" & intID & "'"
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

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

                lblDialID.Text = intID

            End If

        Catch objError As Exception
            lblDebug.Text = objError.Message

        Finally
            objConnect.Close()
        End Try

        pnlDiEdit.Visible = True
        GridView2.Enabled = False
        btnHDAdd.Enabled = False
        lblDialEditWarn.Text = ""
        btnSave.Focus()
        pagefill()



    End Sub

    Shared Sub GridView_RowCreated(ByVal sender As Object, ByVal e As GridViewRowEventArgs)
        e.Row.Cells(0).Visible = False
    End Sub

    Protected Sub btnUpdatePlasma_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnUpdatePlasma.Click

        If txtDateStartPlasmaEdit.Text = "" Then
            txtDateStartPlasmaEdit.BackColor = Drawing.Color.LemonChiffon
            Exit Sub
        Else
            txtDateStartPlasmaEdit.BackColor = Drawing.Color.White
        End If

        If ddllPlasmaExchEdit.SelectedIndex = 0 Then
            ddllPlasmaExchEdit.BackColor = Drawing.Color.LemonChiffon
            Exit Sub
        Else
            ddllPlasmaExchEdit.BackColor = Drawing.Color.White
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
            dtStop = dtStart.AddDays(1)
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

            .Add(New SqlParameter("@NO_EXCH_PLASMAPH", ddllPlasmaExchEdit.SelectedValue))
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


        pnlPlasmaEdit.Visible = False
        GridView3.DataBind()
        GridView3.Enabled = True
        btnPlasmaAdd.Enabled = True
        pagefill()




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
    
    Function chkTrue(ByVal bolValue As Object) As Boolean

        If bolValue Is DBNull.Value Then
            chkTrue = False
        ElseIf bolValue = True Then
            chkTrue = True
        Else
            chkTrue = False
        End If

    End Function
    
    Protected Sub btnSave2_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnSave2.Click
        pageSave()
    End Sub
End Class
