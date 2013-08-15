Imports System.Data
Imports System.Data.OleDb
Imports System.Data.SqlClient
Imports System.IO
Partial Class followup_treatment
    Inherits System.Web.UI.Page

    'Public bolFirstVisit As Boolean = False  'used to track whether Fisrt Visit record exists

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load

        If Not Session("blnAuthenticated") Then
            Response.Redirect("default.aspx")
        End If

        If Session("diag") = 1 Then
            lblPage.Text = "8B"
        ElseIf Session("diag") = 2 Then
            lblPage.Text = "8A"
        End If

        If Session("uType") = "p" Then
            btnUpdate.Visible = False
            btnAdd.Visible = False
            CommonClass.DisableControls(tblMain)
            tblAddPlasma.Visible = False
            tblAddImmuno.Visible = False
            lblPlasmaAdd.Visible = False
            lblImmunoAdd.visible = False
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

        If Not IsPostBack Then
            rowACE.Visible = False
            rowARB.Visible = False
            rowCalcium.Visible = False
            rowBeta.Visible = False
            rowOther.Visible = False


            Dim strMode As String = Request.QueryString("m")

            If strMode = "ei" Then
                Dim intMonoID As Integer = Request.QueryString("id")
                pnlAdd.Visible = True
                lblDebug.Text = ""
                lblUpdate.Text = ""
                GridView1.Enabled = False
                btnAddMono.Enabled = False

                Dim strSQL As String = "SELECT [IMMUNSUP_DRUG_STARTDATE], [IMMUNSUP_DRUG_ENDDATE], [IMMUNSUP_DRUG], [CYCLOPHOS_TOT_DOSE] FROM [tbl_IMMUNSUP_TREATMENT] WHERE [tID] = " & intMonoID
                Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
                Dim objConnect As New SqlConnection(strConnect)
                Dim objCommand As New SqlCommand(strSQL, objConnect)

                Try

                    Dim objDataReader As SqlDataReader
                    objConnect.Open()
                    objDataReader = objCommand.ExecuteReader()

                    If objDataReader.Read() Then
                        txtMonoDoseEdit.Text = objDataReader("CYCLOPHOS_TOT_DOSE").ToString
                        Dropdownlist2.SelectedValue = objDataReader("IMMUNSUP_DRUG")

                        If objDataReader("IMMUNSUP_DRUG_STARTDATE") Is DBNull.Value Then
                            'CalendarExtender1.SelectedDate = CDate(Now)
                        Else
                            CalendarExtender1.SelectedDate = CDate(objDataReader("IMMUNSUP_DRUG_STARTDATE"))
                        End If

                        If objDataReader("IMMUNSUP_DRUG_ENDDATE") Is DBNull.Value Then
                            'CalendarExtender2.SelectedDate = CDate(Now)
                        Else
                            CalendarExtender2.SelectedDate = CDate(objDataReader("IMMUNSUP_DRUG_ENDDATE"))
                        End If
                        
                        'CalendarExtender2.SelectedDate = IIf(objDataReader("IMMUNSUP_DRUG_ENDDATE") Is DBNull.Value, "", CDate(objDataReader("IMMUNSUP_DRUG_ENDDATE")))
                        'CalendarExtender1.SelectedDate = IIf(objDataReader("IMMUNSUP_DRUG_STARTDATE") Is DBNull.Value, "", CDate(objDataReader("IMMUNSUP_DRUG_STARTDATE")))
                        If objDataReader("IMMUNSUP_DRUG") = 8 Then
                            txtMonoDoseEdit.Visible = True
                            lblMonoDoseEdit.Visible = True
                        End If

                    End If

                Catch objError As Exception

                lblDebug.Text = String.Format("An error occurred: '{0}'{1}", objError.Message, strSQL)


            Finally

                objConnect.Close()
            End Try

                PageFill(Session("intRecord"))
                DropDownList4.SelectedIndex = 0
                btnSaveNew.Visible = False
                btnSaveNew2.Visible = False
                btnUpdate.Visible = True
                btnUpdate2.Visible = True
                pnlIEdit.Visible = True
                btnUpdate.Focus()

            Else

                If Session("dtRecord").ToString <> "" Then
                    Dim intSeqNo As Integer = GetSeqNo(Session("dtRecord"))
                    If intSeqNo = 0 Then
                        'do nothing
                    Else
                        PageFill(intSeqNo)
                        DropDownList4.SelectedIndex = 0
                        btnSaveNew.Visible = False
                        btnSaveNew2.Visible = False
                        btnUpdate.Visible = True
                        btnUpdate2.Visible = True
                        pnlAdd.Visible = True
                    End If
                End If


            End If
        End If


        If IsPostBack Then
            'If chkP_ANTI_HTN.Checked = True Then
            '    rowACE.Visible = True
            '    rowARB.Visible = True
            '    rowCalcium.Visible = True
            '    rowBeta.Visible = True
            '    rowOther.Visible = True
            'Else
            '    rowACE.Visible = False
            '    rowARB.Visible = False
            '    rowCalcium.Visible = False
            '    rowBeta.Visible = False
            '    rowOther.Visible = False
            'End If
        End If

        txtDateToday.Text = Format(DateTime.Today, "dd-MM-yyyy")

        'PageFill()

    End Sub

    Sub PageFill(ByVal SEG_NO As Integer)

        Dim TDES As New TripleDES()

        'DropDownList1.DataBind()

        Dim patientID As Integer = Session("patientID")

        Dim strSQL As String = "SELECT tbl_Therapy.tID, tbl_Therapy.RADAR_NO, tbl_Therapy.SIG_CHANGE_STATUS, tbl_Therapy.P_NSAID, tbl_Therapy.NSAID, tbl_Therapy.P_DIURETIC, tbl_Therapy.DIURETIC, tbl_Therapy.P_ANTI_HTN, tbl_Therapy.ANTI_HTN, tbl_Therapy.P_ACE_INHIB, tbl_Therapy.ACE_INHIB, tbl_Therapy.P_ARB_ANTAG, tbl_Therapy.ARB_ANTAG, tbl_Therapy.P_CA_CH_BLOCK, tbl_Therapy.CA_CH_BLOCK, tbl_Therapy.P_B_BLOCK, tbl_Therapy.B_BLOCK, tbl_Therapy.P_OTHER_ANTI_HTN, tbl_Therapy.OTHER_ANTI_HTN, tbl_Therapy.P_INSULIN, tbl_Therapy.INSULIN, tbl_Therapy.P_LIP_LOWER_AG, tbl_Therapy.LIP_LOWER_AG, tbl_Therapy.P_EPO, tbl_Therapy.EPO, tbl_Therapy.P_OTHER_DRUG1, tbl_Therapy.OTHER_DRUG1, tbl_Therapy.P_OTHER_DRUG2, tbl_Therapy.OTHER_DRUG2,  tbl_Therapy.P_OTHER_DRUG3, tbl_Therapy.OTHER_DRUG3,  tbl_Therapy.P_OTHER_DRUG4, tbl_Therapy.OTHER_DRUG4,tbl_Therapy.P_IMMUN_SUP, tbl_Therapy.IMMUN_SUP, tbl_Therapy.P_IMMUN_SUP_DRUG, tbl_Therapy.IMMUN_SUP_DRUG, tbl_Therapy.MONOCLONAL_YN, tbl_Therapy.MONOCLONAL_NAME, tbl_Therapy.DATE_TREAT, tbl_Therapy.TMT_MODALITY, tbl_Demographics.SNAME, tbl_Demographics.FNAME, tbl_Demographics.DOB, tbl_Demographics.HOSP_NO, tbl_Diagnosis.DIAG, tbl_ClinicalData.DATE_CLIN_PIC, tbl_Therapy.SEQ_NO FROM tbl_Therapy INNER JOIN tbl_Demographics ON tbl_Therapy.RADAR_NO = tbl_Demographics.RADAR_NO INNER JOIN tbl_Diagnosis ON tbl_Demographics.RADAR_NO = tbl_Diagnosis.RADAR_NO  INNER JOIN tbl_ClinicalData ON tbl_Demographics.RADAR_NO = tbl_ClinicalData.RADAR_NO WHERE (tbl_Therapy.SEQ_NO = " & SEG_NO & " AND tbl_Therapy.RADAR_NO = " & Session("patientID") & ")"
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        'lblDebug.Text = strSQL

        Try

            Dim objDataReader As SqlDataReader
            objConnect.Open()
            objDataReader = objCommand.ExecuteReader()

            If objDataReader.Read() Then

                txtRADAR_NO.Text = objDataReader("RADAR_NO")
                txtHOSP_NO.Text = TDES.Decrypt(objDataReader("HOSP_NO"))
                lblID.Text = objDataReader("tID")
                txtSEQ_NO.Text = objDataReader("SEQ_NO")

                'If objDataReader("DATE_CLIN_PIC") Is DBNull.Value Then
                '    lnk3Months.Enabled = False
                'Else
                '    lnk3Months.Enabled = True
                'End If

                If objDataReader("DATE_TREAT") Is DBNull.Value Then
                Else
                    txtDOT.Text = objDataReader("DATE_TREAT")
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



                If objDataReader("DIAG") = 1 Then 'FSGS

                    rowNSAID.Visible = True
                    rowDiuretic.Visible = True
                    rowInsulin.Visible = True
                    rowLipid.Visible = True
                    lblPage.Text = "8B"

                ElseIf objDataReader("DIAG") = 2 Then  'MSGN

                    rowNSAID.Visible = False
                    rowDiuretic.Visible = False
                    rowInsulin.Visible = False
                    rowLipid.Visible = False
                    lblPage.Text = "8A"

                End If

                'If objDataReader("P_NSAID") Is DBNull.Value Then
                '    radNsaidInitNo.Checked = False
                '    radNsaidInitYes.Checked = False
                'ElseIf objDataReader("P_NSAID") = True Then
                '    radNsaidInitYes.Checked = True
                'ElseIf objDataReader("P_NSAID") = False Then
                '    radNsaidInitNo.Checked = True
                'End If

                'If objDataReader("P_DIURETIC") Is DBNull.Value Then
                '    radP_DiureticNo.Checked = False
                '    radP_DiureticYes.Checked = False
                'ElseIf objDataReader("P_DIURETIC") = True Then
                '    radP_DiureticYes.Checked = True
                'ElseIf objDataReader("P_DIURETIC") = False Then
                '    radP_DiureticNo.Checked = True
                'End If

                'If objDataReader("P_ANTI_HTN") Is DBNull.Value Then
                '    radP_ANTI_HtnNo.Checked = False
                '    radP_ANTI_HtnYes.Checked = False
                'ElseIf objDataReader("P_ANTI_HTN") = True Then
                '    radP_ANTI_HtnYes.Checked = True
                'ElseIf objDataReader("P_ANTI_HTN") = False Then
                '    radP_ANTI_HtnNo.Checked = True
                'End If

                'If objDataReader("P_ACE_INHIB") Is DBNull.Value Then
                '    radP_ACE_InhibNo.Checked = False
                '    radP_ACE_InhibYes.Checked = False
                'ElseIf objDataReader("P_ACE_INHIB") = True Then
                '    radP_ACE_InhibYes.Checked = True
                'ElseIf objDataReader("P_ACE_INHIB") = False Then
                '    radP_ACE_InhibNo.Checked = True
                'End If

                'If objDataReader("P_ARB_ANTAG") Is DBNull.Value Then
                '    radP_ARB_AntagNo.Checked = False
                '    radP_ARB_AntagYes.Checked = False
                'ElseIf objDataReader("P_ARB_ANTAG") = True Then
                '    radP_ARB_AntagYes.Checked = True
                'ElseIf objDataReader("P_ARB_ANTAG") = False Then
                '    radP_ARB_AntagNo.Checked = True
                'End If

                'If objDataReader("P_CA_CH_BLOCK") Is DBNull.Value Then
                '    radP_CA_CH_BlockNo.Checked = False
                '    radP_CA_CH_BlockYes.Checked = False
                'ElseIf objDataReader("P_CA_CH_BLOCK") = True Then
                '    radP_CA_CH_BlockYes.Checked = True
                'ElseIf objDataReader("P_CA_CH_BLOCK") = False Then
                '    radP_CA_CH_BlockNo.Checked = True
                'End If

                'If objDataReader("P_B_BLOCK") Is DBNull.Value Then
                '    radP_B_BlockNo.Checked = False
                '    radP_B_BlockYes.Checked = False
                'ElseIf objDataReader("P_B_BLOCK") = True Then
                '    radP_B_BlockYes.Checked = True
                'ElseIf objDataReader("P_B_BLOCK") = False Then
                '    radP_B_BlockNo.Checked = True
                'End If

                'If objDataReader("P_OTHER_ANTI_HTN") Is DBNull.Value Then
                '    radP_OTHER_ANTI_HtnNo.Checked = False
                '    radP_OTHER_ANTI_HtnYes.Checked = False
                'ElseIf objDataReader("P_OTHER_ANTI_HTN") = True Then
                '    radP_OTHER_ANTI_HtnYes.Checked = True
                'ElseIf objDataReader("P_OTHER_ANTI_HTN") = False Then
                '    radP_OTHER_ANTI_HtnNo.Checked = True
                'End If

                'If objDataReader("P_INSULIN") Is DBNull.Value Then
                '    radP_InsulinNo.Checked = False
                '    radP_InsulinYes.Checked = False
                'ElseIf objDataReader("P_INSULIN") = True Then
                '    radP_InsulinYes.Checked = True
                'ElseIf objDataReader("P_INSULIN") = False Then
                '    radP_InsulinNo.Checked = True
                'End If

                'If objDataReader("P_LIP_LOWER_AG") Is DBNull.Value Then
                '    radP_LIP_LOWER_AGNo.Checked = False
                '    radP_LIP_LOWER_AGYes.Checked = False
                'ElseIf objDataReader("P_LIP_LOWER_AG") = True Then
                '    radP_LIP_LOWER_AGYes.Checked = True
                'ElseIf objDataReader("P_LIP_LOWER_AG") = False Then
                '    radP_LIP_LOWER_AGNo.Checked = True
                'End If

                'If objDataReader("P_EPO") Is DBNull.Value Then
                '    radP_EPONo.Checked = False
                '    radP_EPOYes.Checked = False
                'ElseIf objDataReader("P_EPO") = True Then
                '    radP_EPOYes.Checked = True
                'ElseIf objDataReader("P_EPO") = False Then
                '    radP_EPONo.Checked = True
                'End If

                'chkP_NSAID.Checked = objDataReader("P_NSAID")

                'chkP_DIURETIC.Checked = objDataReader("P_DIURETIC")

                'chkP_ANTI_HTN.Checked = objDataReader("P_ANTI_HTN")

                'chkP_ACE_INHIB.Checked = objDataReader("P_ACE_INHIB")

                'chkP_ARB_ANTAG.Checked = objDataReader("P_ARB_ANTAG")

                'chkP_CA_CH_BLOCK.Checked = objDataReader("P_CA_CH_BLOCK")

                'chkP_B_BLOCK.Checked = objDataReader("P_B_BLOCK")

                'chkP_OTHER_ANTI_HTN.Checked = objDataReader("P_OTHER_ANTI_HTN")

                'chkP_INSULIN.Checked = objDataReader("P_INSULIN")

                'chkP_LIP_LOWER_AG.Checked = objDataReader("P_LIP_LOWER_AG")

                'chkP_EPO.Checked = objDataReader("P_EPO")

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

                'txtP_OTHER_DRUG1.Text = chkNull(objDataReader("P_OTHER_DRUG1"))
                txtOTHER_DRUG1.Text = chkNull(objDataReader("OTHER_DRUG1"))
                'txtP_OTHER_DRUG2.Text = chkNull(objDataReader("P_OTHER_DRUG2"))
                txtOTHER_DRUG2.Text = chkNull(objDataReader("OTHER_DRUG2"))
                txtOTHER_DRUG3.Text = chkNull(objDataReader("OTHER_DRUG3"))
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


                If objDataReader("ANTI_HTN") Is DBNull.Value Then

                    rowACE.Visible = False
                    rowARB.Visible = False
                    rowCalcium.Visible = False
                    rowBeta.Visible = False
                    rowOther.Visible = False

                ElseIf objDataReader("ANTI_HTN") = False Then

                    rowACE.Visible = False
                    rowARB.Visible = False
                    rowCalcium.Visible = False
                    rowBeta.Visible = False
                    rowOther.Visible = False

                Else

                    rowACE.Visible = True
                    rowARB.Visible = True
                    rowCalcium.Visible = True
                    rowBeta.Visible = True
                    rowOther.Visible = True


                End If





            End If

        Catch objError As Exception

            lblDebug.Text = String.Format("An error occurred (PageFill): '{0}'{1}", objError.Message, strSQL)


        Finally

            objConnect.Close()
            'lblDebug.Text = strSQL
        End Try



        'Dim strSQL As String = "SELECT tbl_Diagnosis.DIAG, tbl_Demographics.SNAME, tbl_Demographics.FNAME, tbl_Demographics.DOB, tbl_Demographics.RADAR_NO FROM tbl_Demographics INNER JOIN tbl_Diagnosis ON tbl_Demographics.RADAR_NO = tbl_Diagnosis.RADAR_NO WHERE tbl_Demographics.RADAR_NO = " & patientID
        'Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        'Dim objConnect As New System.Data.SqlClient.SqlConnection(strConnect)
        'Dim objCommand As New System.Data.SqlClient.SqlCommand(strSQL, objConnect)

        'Try

        '    'declare a variable to hold the a DataReader object
        '    Dim objDataReader As SqlDataReader
        '    objConnect.Open()
        '    'execute the SQL statement
        '    objDataReader = objCommand.ExecuteReader()


        '    If objDataReader.Read() Then

        '        txtRADAR_NO.Text = objDataReader("RADAR_NO")
        '        txtFNAME.Text = objDataReader("FNAME").ToString
        '        txtSNAME.Text = objDataReader("SNAME").ToString
        '        txtDOB.Text = Format(objDataReader("DOB"), "dd-MMM-yyyy")
        '        If objDataReader("DIAG") Is DBNull.Value Then
        '            txtDIAG.Text = "-"
        '        Else
        '            txtDIAG.Text = GetDiagnosis(objDataReader("DIAG"))
        '        End If

        '    End If
        'Catch objError As Exception

        '    lblDebug.Text = "An error occurred: '" & objError.Message & "'" & strSQL
        '    Exit Sub

        'Finally

        '    objConnect.Close()

        'End Try

    End Sub

    Function CheckDatesMono(ByVal intRADAR_NO As Integer, ByVal dtStart As Date, ByVal dtStop As Date, ByVal intImmunoDrugID As Integer) As Boolean


        '# most have stop dates before allowing new entries (of same drug)
        '# the same drug cannot have concurrent entries
        '# different drugs may be given concurrently
        '# cyclophospahmide may start on day a previous cyclo treatment stops
        '# all others must start at least next day after previous

        Dim strStartDate As String = CStr(Year(dtStart)) & "-" & CStr(Month(dtStart)) & "-" & CStr(Day(dtStart))

        dtStart = CDate(strStartDate)


        '# check for unclosed entries

        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New System.Data.SqlClient.SqlConnection(strConnect)

        Dim strSQL2 As String = "SELECT [RADAR_NO] FROM [tbl_IMMUNSUP_TREATMENT] WHERE [RADAR_NO] = @RADAR_NO AND [IMMUNSUP_DRUG_ENDDATE] IS NULL AND [IMMUNSUP_DRUG] = @IMMUNSUP_DRUG"

        Dim objCommand2 As New System.Data.SqlClient.SqlCommand(strSQL2, objConnect)

        Try

            With objCommand2.Parameters

                .Add(New SqlParameter("@RADAR_NO", intRADAR_NO))
                .Add(New SqlParameter("@IMMUNSUP_DRUG", intImmunoDrugID))

            End With

            Dim objDataReader As SqlDataReader
            objConnect.Open()
            objDataReader = objCommand2.ExecuteReader()


            If objDataReader.Read() Then

                lblWarnImmuno.Text = "Please close open treatments"
                objConnect.Close()
                Exit Function
            Else
                lblWarnImmuno.Text = ""

            End If



        Catch ex As Exception
            lblDebug.Text = ex.Message

        Finally

            objConnect.Close()

        End Try


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


        '# check for unclosed entries - don't need to do this for editing

        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New System.Data.SqlClient.SqlConnection(strConnect)

        'Dim strSQL2 As String = "SELECT [RADAR_NO] FROM [tbl_IMMUNSUP_TREATMENT] WHERE [RADAR_NO] = @RADAR_NO AND [IMMUNSUP_DRUG_ENDDATE] IS NULL AND [IMMUNSUP_DRUG] <> @IMMUNSUP_DRUG"

        'Dim objCommand2 As New System.Data.SqlClient.SqlCommand(strSQL2, objConnect)

        'Try

        '    With objCommand2.Parameters

        '        .Add(New SqlParameter("@RADAR_NO", intRADAR_NO))
        '        .Add(New SqlParameter("@IMMUNSUP_DRUG", intImmunoDrugID))

        '    End With

        '    Dim objDataReader As SqlDataReader
        '    objConnect.Open()
        '    objDataReader = objCommand2.ExecuteReader()


        '    If objDataReader.Read() Then

        '        lblMonoEditWarn.Text = "Please close open treatments 1"
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

        Dim strSQL As String = "SELECT [RADAR_NO], [tID] FROM [tbl_IMMUNSUP_TREATMENT] WHERE (((cast(@START_DATE as smalldatetime) >= [IMMUNSUP_DRUG_STARTDATE] AND cast(@START_DATE as smalldatetime) <= [IMMUNSUP_DRUG_ENDDATE]) OR (cast(@END_DATE as smalldatetime) >= [IMMUNSUP_DRUG_STARTDATE] AND cast(@END_DATE as smalldatetime) <= [IMMUNSUP_DRUG_ENDDATE] ) OR (cast(@START_DATE as smalldatetime) <= [IMMUNSUP_DRUG_STARTDATE]) AND (cast(@END_DATE as smalldatetime)  >= [IMMUNSUP_DRUG_STARTDATE])) AND ([RADAR_NO] = @RADAR_NO AND [IMMUNSUP_DRUG] = @IMMUNSUP_DRUG AND [tID] <> @tID) )"

        Dim objCommand As New System.Data.SqlClient.SqlCommand(strSQL, objConnect)


        Try

            With objCommand.Parameters

                .Add(New SqlParameter("@RADAR_NO", intRADAR_NO))


                .Add(New SqlParameter("@IMMUNSUP_DRUG", intImmunoDrugID))
                .Add(New SqlParameter("@END_DATE", dtStop))
                .Add(New SqlParameter("@tID", recordID))

                Select Case Dropdownlist2.SelectedValue
                    Case 8 'Cyclophosphamide
                        .Add(New SqlParameter("@START_DATE", dtStart.AddDays(-1)))
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
                'lblDebug.Text = objDataReader("tID")
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


    'Protected Sub ddlImmunoSupp1_SelectedIndexChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles ddlImmunoSupp1.SelectedIndexChanged
    '    If ddlImmunoSupp1.SelectedValue = 8 Then
    '        lblDose.Visible = True
    '        txtIMMUN_SUP_DOSE.Visible = True
    '    End If
    'End Sub

    'Protected Sub btnHDAdd_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnHDAdd.Click

    '    If ddlHD.SelectedIndex = 0 Then
    '        ddlHD.BackColor = Drawing.Color.LemonChiffon
    '        Exit Sub
    '    End If

    '    If txtDATE_START_DIAL.Text = "" Then
    '        txtDATE_START_DIAL.BackColor = Drawing.Color.LemonChiffon
    '        Exit Sub
    '    End If

    '    'If txtDATE_STOP_DIAL.Text = "" Then
    '    '    txtDATE_STOP_DIAL.BackColor = Drawing.Color.LemonChiffon
    '    '    Exit Sub
    '    'End If

    '    If txtDATE_STOP_DIAL.Text = "" Then
    '        'OK, stop date not known
    '    Else

    '        If IsDate(txtDATE_STOP_DIAL.Text) Then

    '            If CDate(txtDATE_STOP_DIAL.Text) > CDate(txtDateToday.Text) Then
    '                lblStopWarn.Text = "> Today"
    '                lblWarn.Text = ""
    '                Exit Sub
    '            Else
    '                lblStopWarn.Text = ""
    '            End If

    '            If CDate(txtDATE_STOP_DIAL.Text) < CDate(txtDATE_START_DIAL.Text) Then
    '                lblStopWarn.Text = "< Start"
    '                lblWarn.Text = ""
    '                Exit Sub
    '            Else
    '                lblStopWarn.Text = ""
    '            End If
    '        Else
    '            lblStopWarn.Text = "Enter a date"
    '            lblWarn.Text = ""
    '            Exit Sub
    '        End If

    '    End If

    '    Dim intD_TMT_MODALITY As Integer = ddlHD.SelectedValue
    '    Dim dtDATE_START_DIAL As Date = CDate(txtDATE_START_DIAL.Text)

    '    Dim dtDATE_STOP_DIAL As Date

    '    If txtDATE_STOP_DIAL.Text = "" Then
    '        dtDATE_STOP_DIAL = CDate(txtDATE_START_DIAL.Text).AddDays(1)  ' can we get another treatment in between existing dates
    '    Else
    '        dtDATE_STOP_DIAL = CDate(txtDATE_STOP_DIAL.Text)

    '    End If

    '    If CheckDates(Session("patientID"), 1, dtDATE_START_DIAL, dtDATE_STOP_DIAL, intD_TMT_MODALITY) = False Then
    '        'ASPNET_MsgBox("The Start Date conflicts with an existing treatment")
    '        'UserMsgBox(Me, "The start date conflicts")
    '        'MessageBox("Test")
    '        lblWarn.Text = "Dates conflict with other treatments"
    '        'lblDebug.Text = dtDATE_STOP_DIAL
    '        Exit Sub
    '    Else
    '        lblWarn.Text = ""
    '    End If


    '    Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RadarConnectionString").ConnectionString
    '    Dim objConnect As New SqlConnection(strConnect)
    '    'Dim strSQL As String = "INSERT INTO tbl_RRT_HD (RADAR_NO, HD_TMT_MODALITY, DATE_START_HDIAL, DATE_STOP_HDIAL) VALUES (@RADAR_NO, @HD_TMT_MODALITY, @DATE_START_HDIAL, @DATE_STOP_HDIAL)"

    '    Dim strSQL As String = "INSERT INTO tbl_RRT_TREATMENT (RADAR_NO, MODALITY, DATE_START, DATE_STOP, UNIT_CODE, FIRST_FLAG) VALUES (@RADAR_NO, @HD_TMT_MODALITY, @DATE_START_DIAL, @DATE_STOP_DIAL, @UNIT_ID, @FIRST_FLAG)"


    '    Dim objCommand As New SqlCommand(strSQL, objConnect)
    '    Try
    '        With objCommand.Parameters

    '            .Add(New SqlParameter("@RADAR_NO", Session("patientID")))
    '            .Add(New SqlParameter("@HD_TMT_MODALITY", intD_TMT_MODALITY))
    '            .Add(New SqlParameter("@DATE_START_DIAL", dtDATE_START_DIAL))
    '            .Add(New SqlParameter("@DATE_STOP_DIAL", dtDATE_STOP_DIAL))
    '            .Add(New SqlParameter("@UNIT_ID", Session("unitID")))
    '            .Add(New SqlParameter("@FIRST_FLAG", False))


    '        End With

    '        objConnect.Open()
    '        objCommand.ExecuteNonQuery()

    '    Catch

    '    Finally
    '        objConnect.Close()
    '    End Try

    '    GridView2.DataBind()
    '    ddlHD.Enabled = False
    '    ddlHD.BackColor = Drawing.Color.White
    '    txtDATE_START_DIAL.Text = ""
    '    txtDATE_STOP_DIAL.Text = ""
    '    ddlHD.BackColor = Drawing.Color.White
    '    txtDATE_START_DIAL.BackColor = Drawing.Color.White
    '    txtDATE_STOP_DIAL.BackColor = Drawing.Color.White


    'End Sub

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

    'Protected Sub btnAddMono_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnAddMono.Click


    '    If txtImmunoStart.Text = "" Then
    '        txtImmunoStart.BackColor = Drawing.Color.LemonChiffon
    '        Exit Sub
    '    Else
    '        txtImmunoStart.BackColor = Drawing.Color.White
    '    End If

    '    If ddlImmunoSupp1.SelectedIndex = 0 Then
    '        ddlImmunoSupp1.BackColor = Drawing.Color.LemonChiffon
    '        Exit Sub
    '    Else
    '        ddlImmunoSupp1.BackColor = Drawing.Color.White
    '    End If

    '    If txtImmunoEnd.Text = "" Then
    '        'ok end date not known
    '    Else

    '        If IsDate(txtImmunoEnd.Text) Then


    '            If CDate(txtImmunoEnd.Text) > CDate(txtDateToday.Text) Then
    '                lblStopWarn.Text = "> today"
    '                lblWarn.Text = ""
    '                Exit Sub
    '            Else
    '                lblStopWarn.Text = ""
    '            End If

    '            If CDate(txtImmunoEnd.Text) < CDate(txtImmunoStart.Text) Then
    '                lblStopWarn.Text = "< start"
    '                Exit Sub
    '            Else
    '                lblStopWarn.Text = ""
    '            End If

    '        Else
    '            lblStopWarn.Text = "Enter a date"
    '            lblWarn.Text = ""
    '            Exit Sub

    '        End If

    '    End If




    '    Dim dtIMMUNSUP_START As Date = CDate(txtImmunoStart.Text)
    '    Dim intIMMUNSUP_DRUG As Integer = ddlImmunoSupp1.SelectedValue
    '    'Dim dtIMMUNSUP_END As Date

    '    'If txtImmunoEnd.Text = "" Then
    '    '    dtIMMUNSUP_END = CDate(txtImmunoStart.Text).AddDays(1)
    '    'Else
    '    '    dtIMMUNSUP_END = CDate(txtImmunoEnd.Text)
    '    'End If

    '    'If CheckDates(Session("patientID"), dtIMMUNSUP_START, dtIMMUNSUP_END) = False Then
    '    '    lblWarn.Text = "Dates conflict with treatments above"
    '    '    Exit Sub
    '    'Else
    '    '    lblWarn.Text = ""
    '    'End If

    '    Dim strSQL As String = "INSERT INTO tbl_IMMUNSUP_TREATMENT (IMMUNSUP_DRUG_STARTDATE, IMMUNSUP_DRUG, IMMUNSUP_DRUG_ENDDATE, CYCLOPHOS_TOT_DOSE, RADAR_NO, FIRST_FLAG) VALUES (@START_DATE, @DRUG, @END_DATE, @DOSE, @RADAR_NO, @FIRST_FLAG)"
    '    Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
    '    Dim objConnect As New SqlConnection(strConnect)
    '    Dim objCommand As New SqlCommand(strSQL, objConnect)

    '    Try
    '        With objCommand.Parameters

    '            .Add(New SqlParameter("@RADAR_NO", Session("patientID")))
    '            .Add(New SqlParameter("@START_DATE", dtIMMUNSUP_START))

    '            If txtImmunoEnd.Text = "" Then
    '                .Add(New SqlParameter("@END_DATE", DBNull.Value))
    '            Else
    '                .Add(New SqlParameter("@END_DATE", CDate(txtImmunoEnd.Text)))
    '            End If

    '            .Add(New SqlParameter("@DRUG", intIMMUNSUP_DRUG))

    '            If txtIMMUN_SUP_DOSE.Text = "" Then
    '                .Add(New SqlParameter("@DOSE", DBNull.Value))
    '            Else
    '                .Add(New SqlParameter("@DOSE", CDec(txtIMMUN_SUP_DOSE.Text)))
    '            End If

    '            .Add(New SqlParameter("@FIRST_FLAG", True))

    '        End With

    '        objConnect.Open()
    '        objCommand.ExecuteNonQuery()

    '    Catch objError As Exception
    '        lblDebug.Text = String.Format("An error occurred: '{0}'{1}", objError.Message, strSQL)
    '        objConnect.Close()
    '        Exit Sub


    '    Finally
    '        objConnect.Close()

    '    End Try

    '    txtImmunoStart.Text = ""
    '    txtImmunoEnd.Text = ""
    '    ddlImmunoSupp1.SelectedIndex = 0

    '    GridView1.DataBind()
    'End Sub

    Protected Sub btnAdd_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnAdd.Click
        ShowAddForm()
    End Sub

    Public Sub ShowAddForm()
        Dim tdes As New TripleDES()

        Dim patientID As Integer = Session("patientID")

        'If (GetNextRecord() = 0 And bolFirstVisit = False) Then
        '    pnlWarn.Visible = True
        '    Exit Sub
        'Else
        '    pnlWarn.Visible = False
        'End If

        Dim strSQL As String = "SELECT tbl_Diagnosis.DIAG, tbl_Demographics.SNAME, tbl_Demographics.FNAME, tbl_Demographics.DOB, tbl_Demographics.RADAR_NO, tbl_DEMOGRAPHICS.HOSP_NO FROM tbl_Demographics INNER JOIN tbl_Diagnosis ON tbl_Demographics.RADAR_NO = tbl_Diagnosis.RADAR_NO WHERE tbl_Demographics.RADAR_NO = " & patientID
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

                txtRADAR_NO.Text = objDataReader("RADAR_NO")
                txtFNAME.Text = tdes.Decrypt(objDataReader("FNAME"))
                txtSNAME.Text = tdes.Decrypt(objDataReader("SNAME"))
                txtHOSP_NO.Text = tdes.Decrypt(objDataReader("HOSP_NO"))
                Dim dt As Date = CDate(tdes.Decrypt(objDataReader("DOB")))
                txtDOB.Text = Format(dt, "dd-MMM-yyyy")

                If objDataReader("DIAG") Is DBNull.Value Then
                    txtDIAG.Text = "-"
                Else
                    txtDIAG.Text = CommonClass.GetDiagnosis(objDataReader("DIAG"))

                    If objDataReader("DIAG") = 1 Then 'FSGS

                        rowNSAID.Visible = True
                        rowDiuretic.Visible = True
                        rowInsulin.Visible = True
                        rowLipid.Visible = True
                        lblPage.Text = "8B"

                    ElseIf objDataReader("DIAG") = 2 Then  'MSGN

                        rowNSAID.Visible = False
                        rowDiuretic.Visible = False
                        rowInsulin.Visible = False
                        rowLipid.Visible = False
                        lblPage.Text = "8A"

                    End If

                End If

            End If
        Catch objError As Exception

            lblDebug.Text = "An error occurred: '" & objError.Message & "'" & strSQL
            Exit Sub

        Finally

            objConnect.Close()

        End Try



        pnlAdd.Visible = True

        '# handles follow-up visit where no first visit exists
        'If GetNextRecord() = 0 Then
        '    txtSEQ_NO.Text = "2"
        'Else
        'txtSEQ_NO.Text = GetNextRecord().ToString
        'End If

        txtSEQ_NO.Text = GetNextRecord().ToString

        radACE_InhibNo.Checked = False
        radACE_InhibYes.Checked = False
        radANTI_HtnNo.Checked = False
        radANTI_HtnYes.Checked = False
        radARB_AntagNo.Checked = False
        radARB_AntagYes.Checked = False
        radB_BlockNo.Checked = False
        radB_BlockYes.Checked = False
        radCA_CH_BlockNo.Checked = False
        radCA_CH_BlockYes.Checked = False
        radDiureticNo.Checked = False
        radDiureticYes.Checked = False
        radEPONo.Checked = False
        radEPOYes.Checked = False
        radInsulinNo.Checked = False
        radInsulinYes.Checked = False
        radLIP_LOWER_AgNo.Checked = False
        radLIP_LOWER_AgYes.Checked = False
        radNsaidNo.Checked = False
        radNsaidYes.Checked = False
        radOTHER_ANTI_HtnNo.Checked = False
        radOTHER_ANTI_HtnYes.Checked = False
        txtOTHER_DRUG1.Text = ""
        txtOTHER_DRUG2.Text = ""
        txtOTHER_DRUG3.Text = ""
        txtOTHER_DRUG4.Text = ""
        txtDOT.Text = ""

        lblDebug.Text = ""
        lblUpdate.Text = ""
        'DropDownList1.SelectedIndex = 0




        btnUpdate.Visible = False
        btnUpdate2.Visible = False
        btnSaveNew.Visible = True
        btnSaveNew2.Visible = True



    End Sub

    Shared Function chkNull(ByVal value As Object) As String

        If value Is DBNull.Value Then
            chkNull = ""
        Else
            chkNull = value.ToString
        End If

    End Function

    Protected Sub btnSaveNew_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnSaveNew.Click
        SaveNewRecord()
    End Sub

    Sub SaveNewRecord()
        Dim dtTreat As Date = CDate(txtDOT.Text)
        lblDebug.Text = ""
        lblUpdate.Text = ""


        If chkDate(dtTreat) = False Then
            'do nothing
        Else
            lblUpdate.Text = "<span style='color:red;'>A record already exists for this date.</span>"
            Exit Sub
        End If



        Dim strSQL As String = "INSERT INTO [tbl_Therapy] ([RADAR_NO], [SIG_CHANGE_STATUS], [P_NSAID], [NSAID], [P_DIURETIC], [DIURETIC], [P_ANTI_HTN], [ANTI_HTN], [P_ACE_INHIB], [ACE_INHIB], [P_ARB_ANTAG], [ARB_ANTAG], [P_CA_CH_BLOCK], [CA_CH_BLOCK], [P_B_BLOCK], [B_BLOCK], [P_OTHER_ANTI_HTN], [OTHER_ANTI_HTN], [P_INSULIN], [INSULIN], [P_LIP_LOWER_AG], [LIP_LOWER_AG], [P_EPO], [EPO], [P_OTHER_DRUG1], [OTHER_DRUG1], [P_OTHER_DRUG2], [OTHER_DRUG2], [P_OTHER_DRUG3], [OTHER_DRUG3], [P_OTHER_DRUG4], [OTHER_DRUG4], [P_IMMUN_SUP], [IMMUN_SUP], [P_IMMUN_SUP_DRUG], [IMMUN_SUP_DRUG], [MONOCLONAL_YN], [MONOCLONAL_NAME], [DATE_TREAT], [TMT_MODALITY], [SEQ_NO]) VALUES (@RADAR_NO, @SIG_CHANGE_STATUS, @P_NSAID, @NSAID, @P_DIURETIC, @DIURETIC, @P_ANTI_HTN, @ANTI_HTN, @P_ACE_INHIB, @ACE_INHIB, @P_ARB_ANTAG, @ARB_ANTAG, @P_CA_CH_BLOCK, @CA_CH_BLOCK, @P_B_BLOCK, @B_BLOCK, @P_OTHER_ANTI_HTN, @OTHER_ANTI_HTN, @P_INSULIN, @INSULIN, @P_LIP_LOWER_AG, @LIP_LOWER_AG, @P_EPO, @EPO, @P_OTHER_DRUG1, @OTHER_DRUG1, @P_OTHER_DRUG2, @OTHER_DRUG2, @P_OTHER_DRUG3, @OTHER_DRUG3, @P_OTHER_DRUG4, @OTHER_DRUG4, @P_IMMUN_SUP, @IMMUN_SUP, @P_IMMUN_SUP_DRUG, @IMMUN_SUP_DRUG, @MONOCLONAL_YN, @MONOCLONAL_NAME, @DATE_TREAT, @TMT_MODALITY, @SEQ_NO)"
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        With objCommand.Parameters

            .Add(New SqlParameter("@SEQ_NO", txtSEQ_NO.Text))
            .Add(New SqlParameter("@RADAR_NO", txtRADAR_NO.Text))
            .Add(New SqlParameter("@tID", lblID.Text))

            If txtDOT.Text = "" Then
                lblUpdate.Text = "A date of treatment must be entered"
                Exit Sub
            Else
                .Add(New SqlParameter("@DATE_TREAT", CDate(txtDOT.Text)))
            End If

            'If txtChangeStatus.Text = "" Then
            .Add(New SqlParameter("@SIG_CHANGE_STATUS", DBNull.Value))
            'Else
            '    .Add(New System.Data.SqlClient.SqlParameter("@SIG_CHANGE_STATUS", txtChangeStatus.Text))
            'End If

            'If DropDownList1.SelectedValue = "" Then
            .Add(New SqlParameter("@TMT_MODALITY", DBNull.Value))
            'Else
            '    .Add(New SqlParameter("@TMT_MODALITY", DropDownList1.SelectedValue))
            'End If


            .Add(New SqlParameter("@P_NSAID", DBNull.Value))
            .Add(New SqlParameter("@P_DIURETIC", DBNull.Value))
            .Add(New SqlParameter("@P_ANTI_HTN", DBNull.Value))
            .Add(New SqlParameter("@P_ACE_INHIB", DBNull.Value))
            .Add(New SqlParameter("@P_ARB_ANTAG", DBNull.Value))
            .Add(New SqlParameter("@P_CA_CH_BLOCK", DBNull.Value))
            .Add(New SqlParameter("@P_B_BLOCK", DBNull.Value))
            .Add(New SqlParameter("@P_OTHER_ANTI_HTN", DBNull.Value))
            .Add(New SqlParameter("@P_INSULIN", DBNull.Value))
            .Add(New SqlParameter("@P_LIP_LOWER_AG", DBNull.Value))
            .Add(New SqlParameter("@P_EPO", DBNull.Value))
            .Add(New SqlParameter("@P_OTHER_DRUG1", DBNull.Value))
            .Add(New SqlParameter("@P_OTHER_DRUG2", DBNull.Value))
            .Add(New SqlParameter("@P_OTHER_DRUG3", DBNull.Value))
            .Add(New SqlParameter("@P_OTHER_DRUG4", DBNull.Value))
            .Add(New SqlParameter("@P_IMMUN_SUP", DBNull.Value))
            .Add(New SqlParameter("@IMMUN_SUP", DBNull.Value))
            .Add(New SqlParameter("@IMMUN_SUP_DRUG", DBNull.Value))
            .Add(New SqlParameter("@MONOCLONAL_YN", DBNull.Value))
            .Add(New SqlParameter("@MONOCLONAL_NAME", DBNull.Value))


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

            If radANTI_HtnNo.Checked = True Then
                .Add(New SqlParameter("@ANTI_HTN", False))
            ElseIf radANTI_HtnYes.Checked = True Then
                .Add(New SqlParameter("@ANTI_HTN", True))
            Else
                .Add(New SqlParameter("@ANTI_HTN", DBNull.Value))
            End If

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


            If txtOTHER_DRUG1.Text = "" Then
                .Add(New SqlParameter("@OTHER_DRUG1", DBNull.Value))
            Else
                .Add(New SqlParameter("@OTHER_DRUG1", txtOTHER_DRUG1.Text))
            End If

            If txtOTHER_DRUG2.Text = "" Then
                .Add(New SqlParameter("@OTHER_DRUG2", DBNull.Value))
            Else
                .Add(New SqlParameter("@OTHER_DRUG2", txtOTHER_DRUG2.Text))
            End If

            If txtOTHER_DRUG3.Text = "" Then
                .Add(New SqlParameter("@OTHER_DRUG3", DBNull.Value))
            Else
                .Add(New SqlParameter("@OTHER_DRUG3", txtOTHER_DRUG3.Text))
            End If


            If txtOTHER_DRUG4.Text = "" Then
                .Add(New SqlParameter("@OTHER_DRUG4", DBNull.Value))
            Else
                .Add(New SqlParameter("@OTHER_DRUG4", txtOTHER_DRUG4.Text))
            End If

            'If ddlImmunoSupp1.SelectedValue = "" Then
            .Add(New SqlParameter("@P_IMMUN_SUP_DRUG", DBNull.Value))
            'Else
            '.Add(New SqlParameter("@P_IMMUN_SUP_DRUG", ddlImmunoSupp1.SelectedValue))
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

        lblUpdate.Text = String.Format("<strong>Update successful:</strong>&nbsp;&nbsp;{0}", Now().ToShortTimeString)
        lblUpdate2.Text = String.Format("<strong>Update successful:</strong>&nbsp;&nbsp;{0}", Now().ToShortTimeString)

        btnSaveNew.Visible = False
        btnSaveNew2.Visible = False
        btnUpdate.Visible = True
        btnUpdate2.Visible = True
        ddlRefresh(DropDownList4)
        PageFill(txtSEQ_NO.Text)


    End Sub

    Protected Sub DropDownList4_SelectedIndexChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles DropDownList4.SelectedIndexChanged
        Session("intRecord") = DropDownList4.SelectedValue
        Session("dtRecord") = CDate((DropDownList4.SelectedItem).ToString)
        pnlAdd.Visible = True
        lblDebug.Text = ""
        lblUpdate.Text = ""
        lblUpdate2.Text = ""
        PageFill(Session("intRecord"))
        DropDownList4.SelectedIndex = 0
        btnSaveNew.Visible = False
        btnSaveNew2.Visible = False
        btnUpdate.Visible = True
        btnUpdate2.Visible = True
    End Sub

    Function GetNextRecord() As Integer

        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim strSQL As String = "SELECT MAX(SEQ_NO) AS LastSEQ_NO FROM [tbl_Therapy] WHERE [RADAR_NO] = " & Session("patientID")
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

        Dim strSQL As String = "SELECT tID FROM tbl_Therapy WHERE [RADAR_NO] =" & Session("patientID") & " AND DATE_TREAT = Convert(DATETIME, '" & dt & "',103)"
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

    Protected Sub radANTI_HtnYes_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radANTI_HtnYes.CheckedChanged
        If radANTI_HtnYes.Checked = True Then
            rowACE.Visible = True
            rowARB.Visible = True
            rowCalcium.Visible = True
            rowBeta.Visible = True
            rowOther.Visible = True
        Else

            rowACE.Visible = False
            rowARB.Visible = False
            rowCalcium.Visible = False
            rowBeta.Visible = False
            rowOther.Visible = False
        End If
    End Sub

    Protected Sub radANTI_HtnNo_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radANTI_HtnNo.CheckedChanged
        If radANTI_HtnNo.Checked = True Then
            rowACE.Visible = False
            rowARB.Visible = False
            rowCalcium.Visible = False
            rowBeta.Visible = False
            rowOther.Visible = False
        Else
            rowACE.Visible = True
            rowARB.Visible = True
            rowCalcium.Visible = True
            rowBeta.Visible = True
            rowOther.Visible = True
        End If
    End Sub

    Protected Sub btnCancelUpdateMono_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnCancelUpdateMono.Click
        pnlIEdit.Visible = False
        GridView1.Enabled = True
        btnAddMono.Enabled = True
        lblMonoEditWarn.Text = ""
        txtMonoEDEdit.Text = ""

        PageFill(Session("intRecord"))
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
                    lblMonoEditStopWarn.Text = "> today"
                    Exit Sub
                Else
                    txtMonoEDEdit.BackColor = Drawing.Color.White
                    lblMonoEditStopWarn.Text = ""
                End If

                If CDate(txtMonoEDEdit.Text) < CDate(txtMonoSDEdit.Text) Then
                    lblMonoEditStopWarn.Text = "< start date"
                    txtMonoEDEdit.BackColor = Drawing.Color.LemonChiffon
                    Exit Sub
                Else
                    lblMonoEditStopWarn.Text = ""
                    txtMonoEDEdit.BackColor = Drawing.Color.White
                End If

            Else
                lblMonoEditStopWarn.Text = "Enter a date"
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

        Dim intMonoID As Integer = CInt(lblMonoID.Text)

        If CheckDatesMono2(Session("patientID"), dtStart, dtStop, Dropdownlist2.SelectedValue, intMonoID) = False Then
            'lblMonoEditWarn.Text = "Dates conflict with a treatment above"
            Exit Sub
        Else
            lblWarnImmuno.Text = ""
        End If




        Dim strSQL As String = "UPDATE [tbl_IMMUNSUP_TREATMENT] SET [IMMUNSUP_DRUG_STARTDATE] = @IMMUNSUP_DRUG_STARTDATE, [IMMUNSUP_DRUG_ENDDATE] = @IMMUNSUP_DRUG_ENDDATE, [IMMUNSUP_DRUG] = @IMMUNSUP_DRUG, [CYCLOPHOS_TOT_DOSE] = @CYCLOPHOS_TOT_DOSE WHERE [tID] = " & intMonoID
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

    Protected Sub btnAddMono_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnAddMono.Click

        If txtImmunoStart.Text = "" Then
            txtImmunoStart.BackColor = Drawing.Color.LemonChiffon
            Exit Sub
        Else
            txtImmunoStart.BackColor = Drawing.Color.White
        End If

        If CDate(txtImmunoStart.Text) > CDate(txtDateToday.Text) Then
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
                    lblStopWarnImmuno.Text = "> today"
                    lblWarnImmuno.Text = ""
                    Exit Sub
                Else
                    lblStopWarnImmuno.Text = ""
                End If

                If CDate(txtImmunoEnd.Text) < CDate(txtImmunoStart.Text) Then
                    lblStopWarnImmuno.Text = "< start"
                    Exit Sub
                Else
                    lblStopWarnImmuno.Text = ""
                End If

            Else
                lblStopWarnImmuno.Text = "Enter a date"
                lblWarnImmuno.Text = ""
                Exit Sub

            End If

        End If

        If Session("diag") = 1 Then
            If ddlImmunoSupp1.SelectedValue = 8 Then
                If txtIMMUN_SUP_DOSE.Text = "" Then
                    txtIMMUN_SUP_DOSE.BackColor = Drawing.Color.LemonChiffon
                    Exit Sub
                End If
            End If
        End If



        Dim dtStart As Date = CDate(txtImmunoStart.Text)
        Dim dtStop As Date

        If txtImmunoEnd.Text = "" Then
            dtStop = CDate(txtImmunoStart.Text).AddDays(1)
        Else
            dtStop = CDate(txtImmunoEnd.Text)

        End If

        If CheckDatesMono(Session("patientID"), dtStart, dtStop, ddlImmunoSupp1.SelectedValue) = False Then
            'lblDebug.Text = "Test"
            Exit Sub
        Else
            lblWarnImmuno.Text = ""
        End If


        Dim dtIMMUNSUP_START As Date = CDate(txtImmunoStart.Text)
        Dim intIMMUNSUP_DRUG As Integer = ddlImmunoSupp1.SelectedValue


        Dim strSQL As String = "INSERT INTO tbl_IMMUNSUP_TREATMENT (IMMUNSUP_DRUG_STARTDATE, IMMUNSUP_DRUG, IMMUNSUP_DRUG_ENDDATE, CYCLOPHOS_TOT_DOSE, RADAR_NO, FIRST_FLAG) VALUES (@START_DATE, @DRUG, @END_DATE, @DOSE, @RADAR_NO, @FIRST_FLAG)"
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        Try
            With objCommand.Parameters

                .Add(New SqlParameter("@RADAR_NO", Session("patientID")))
                .Add(New SqlParameter("@START_DATE", dtIMMUNSUP_START))

                If txtImmunoEnd.Text = "" Then
                    .Add(New SqlParameter("@END_DATE", DBNull.Value))
                Else
                    .Add(New SqlParameter("@END_DATE", CDate(txtImmunoEnd.Text)))
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

    Function GetSeqNo(ByVal dtEntry As Date) As Integer

        Dim strSQL As String = "SELECT [SEQ_NO] FROM [tbl_Therapy] WHERE ([RADAR_NO] = '" & Session("patientID") & "' AND Dateadd(d, datediff(d,0,[DATE_TREAT]),0) = Convert(DATETIME, '" & dtEntry & "',103))"
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

    Sub GridView1_RowEditing(ByVal sender As Object, ByVal e As GridViewEditEventArgs)

        Dim intID As Integer = CInt(GridView1.Rows(e.NewEditIndex).Cells(0).Text)
        e.Cancel = True 'cancel the default edit mode

        Dropdownlist2.Items.Clear()
        Dropdownlist2.DataBind()

        pnlAdd.Visible = True
        lblDebug.Text = ""
        lblUpdate.Text = ""
        GridView1.Enabled = False
        btnAddMono.Enabled = False
        lblWarnImmuno.Text = ""

        Dim strSQL As String = "SELECT [IMMUNSUP_DRUG_STARTDATE], [IMMUNSUP_DRUG_ENDDATE], [IMMUNSUP_DRUG], [CYCLOPHOS_TOT_DOSE] FROM [tbl_IMMUNSUP_TREATMENT] WHERE [tID] = " & intID
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        Try

            Dim objDataReader As SqlDataReader
            objConnect.Open()
            objDataReader = objCommand.ExecuteReader()

            If objDataReader.Read() Then

                txtMonoDoseEdit.Text = objDataReader("CYCLOPHOS_TOT_DOSE").ToString
                Dropdownlist2.SelectedValue = objDataReader("IMMUNSUP_DRUG")

                If Session("diag") = 1 Then

                    If objDataReader("IMMUNSUP_DRUG") = 8 Then
                        txtMonoDoseEdit.Visible = True
                        lblMonoDoseEdit.Visible = True
                   
                    End If

                End If


                If objDataReader("IMMUNSUP_DRUG_STARTDATE") Is DBNull.Value Then
                    'CalendarExtender1.SelectedDate = CDate(Now)

                Else
                    CalendarExtender1.SelectedDate = CDate(objDataReader("IMMUNSUP_DRUG_STARTDATE"))
                End If

                If objDataReader("IMMUNSUP_DRUG_ENDDATE") Is DBNull.Value Then
                    'CalendarExtender2.SelectedDate = System.DBNull
                    txtMonoEDEdit.Text = String.Empty
                Else
                    CalendarExtender2.SelectedDate = CDate(objDataReader("IMMUNSUP_DRUG_ENDDATE"))
                End If

                'CalendarExtender2.SelectedDate = IIf(objDataReader("IMMUNSUP_DRUG_ENDDATE") Is DBNull.Value, "", CDate(objDataReader("IMMUNSUP_DRUG_ENDDATE")))
                'CalendarExtender1.SelectedDate = IIf(objDataReader("IMMUNSUP_DRUG_STARTDATE") Is DBNull.Value, "", CDate(objDataReader("IMMUNSUP_DRUG_STARTDATE")))
               

                lblMonoID.Text = intID

            End If

        Catch objError As Exception

            lblDebug.Text = String.Format("An error occurred: '{0}'{1}", objError.Message, strSQL)


        Finally

            objConnect.Close()
        End Try

        'PageFill(Session("intRecord"))
        DropDownList4.SelectedIndex = 0
        btnSaveNew.Visible = False
        btnUpdate.Visible = True
        pnlIEdit.Visible = True
        txtMonoSDEdit.Focus()


    End Sub

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

        PageFill(Session("intRecord"))
        DropDownList4.SelectedIndex = 0
        btnSaveNew.Visible = False
        btnSaveNew2.Visible = False
        pnlPlasmaEdit.Visible = True
        GridView3.Enabled = False

    End Sub

    Shared Sub GridView_RowCreated(ByVal sender As Object, ByVal e As GridViewRowEventArgs)
        e.Row.Cells(0).Visible = False
    End Sub

    Protected Sub btnCancelPlasmaEdit_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnCancelPlasmaEdit.Click
        pnlPlasmaEdit.Visible = False
        GridView3.Enabled = True
        btnSaveNew.Visible = True
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


        pnlPlasmaEdit.Visible = False
        GridView3.DataBind()
        GridView3.Enabled = True
        PageFill(Session("intRecord"))


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

    Protected Sub btnUpdate_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnUpdate.Click

        updateRecord()

    End Sub

    Sub updateRecord()

        Dim strSQL As String = "UPDATE [tbl_Therapy] SET [RADAR_NO] = @RADAR_NO, [SIG_CHANGE_STATUS] = @SIG_CHANGE_STATUS, [P_NSAID] = @P_NSAID, [NSAID] = @NSAID, [P_DIURETIC] = @P_DIURETIC, [DIURETIC] = @DIURETIC, [P_ANTI_HTN] = @P_ANTI_HTN, [ANTI_HTN] = @ANTI_HTN, [P_ACE_INHIB] = @P_ACE_INHIB, [ACE_INHIB] = @ACE_INHIB, [P_ARB_ANTAG] = @P_ARB_ANTAG, [ARB_ANTAG] = @ARB_ANTAG, [P_CA_CH_BLOCK] = @P_CA_CH_BLOCK, [CA_CH_BLOCK] = @CA_CH_BLOCK, [P_B_BLOCK] = @P_B_BLOCK, [B_BLOCK] = @B_BLOCK, [P_OTHER_ANTI_HTN] = @P_OTHER_ANTI_HTN, [OTHER_ANTI_HTN] = @OTHER_ANTI_HTN, [P_INSULIN] = @P_INSULIN, [INSULIN] = @INSULIN, [P_LIP_LOWER_AG] = @P_LIP_LOWER_AG, [LIP_LOWER_AG] = @LIP_LOWER_AG, [P_EPO] = @P_EPO, [EPO] = @EPO, [P_OTHER_DRUG1] = @P_OTHER_DRUG1, [OTHER_DRUG1] = @OTHER_DRUG1, [P_OTHER_DRUG2] = @P_OTHER_DRUG2, [OTHER_DRUG2] = @OTHER_DRUG2, [P_OTHER_DRUG3] = @P_OTHER_DRUG3, [OTHER_DRUG3] = @OTHER_DRUG3, [P_OTHER_DRUG4] = @P_OTHER_DRUG4, [OTHER_DRUG4] = @OTHER_DRUG4, [DATE_TREAT] = @DATE_TREAT, [TMT_MODALITY] = @TMT_MODALITY, [SEQ_NO] = @SEQ_NO WHERE [tID] = @tID"
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        With objCommand.Parameters

            .Add(New SqlParameter("@SEQ_NO", txtSEQ_NO.Text))
            .Add(New SqlParameter("@RADAR_NO", txtRADAR_NO.Text))
            .Add(New SqlParameter("@tID", lblID.Text))

            If txtDOT.Text = "" Then
                lblUpdate.Text = "A date of treatment must be entered"
                Exit Sub
            Else
                .Add(New SqlParameter("@DATE_TREAT", CDate(txtDOT.Text)))
            End If

            'If txtChangeStatus.Text = "" Then
            .Add(New SqlParameter("@SIG_CHANGE_STATUS", DBNull.Value))
            'Else
            '    .Add(New System.Data.SqlClient.SqlParameter("@SIG_CHANGE_STATUS", txtChangeStatus.Text))
            'End If

            'If DropDownList1.SelectedValue = "" Then
            .Add(New SqlParameter("@TMT_MODALITY", DBNull.Value))
            'Else
            '    .Add(New SqlParameter("@TMT_MODALITY", DropDownList1.SelectedValue))
            'End If


            .Add(New SqlParameter("@P_NSAID", DBNull.Value))
            .Add(New SqlParameter("@P_DIURETIC", DBNull.Value))
            .Add(New SqlParameter("@P_ANTI_HTN", DBNull.Value))
            .Add(New SqlParameter("@P_ACE_INHIB", DBNull.Value))
            .Add(New SqlParameter("@P_ARB_ANTAG", DBNull.Value))
            .Add(New SqlParameter("@P_CA_CH_BLOCK", DBNull.Value))
            .Add(New SqlParameter("@P_B_BLOCK", DBNull.Value))
            .Add(New SqlParameter("@P_OTHER_ANTI_HTN", DBNull.Value))
            .Add(New SqlParameter("@P_INSULIN", DBNull.Value))
            .Add(New SqlParameter("@P_LIP_LOWER_AG", DBNull.Value))
            .Add(New SqlParameter("@P_EPO", DBNull.Value))
            .Add(New SqlParameter("@P_OTHER_DRUG2", DBNull.Value))
            .Add(New SqlParameter("@P_OTHER_DRUG1", DBNull.Value))
            .Add(New SqlParameter("@P_OTHER_DRUG3", DBNull.Value))
            .Add(New SqlParameter("@P_OTHER_DRUG4", DBNull.Value))
            .Add(New SqlParameter("@P_IMMUN_SUP", DBNull.Value))
            .Add(New SqlParameter("@IMMUN_SUP", DBNull.Value))
            .Add(New SqlParameter("@IMMUN_SUP_DRUG", DBNull.Value))
            .Add(New SqlParameter("@MONOCLONAL_YN", DBNull.Value))
            .Add(New SqlParameter("@MONOCLONAL_NAME", DBNull.Value))

            '#-------------------------------------------------------------

            '# NSAID and Diurectic used for SRNS only

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

            '#--------------------------------------------------------------

            If radANTI_HtnNo.Checked = True Then
                .Add(New SqlParameter("@ANTI_HTN", False))
            ElseIf radANTI_HtnYes.Checked = True Then
                .Add(New SqlParameter("@ANTI_HTN", True))
            Else
                .Add(New SqlParameter("@ANTI_HTN", DBNull.Value))
            End If

            '#--------------------------------------------------------------

            '# If ANTI_HTN not checked we need to write NULL to the following

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

            '#----------------------------------------------------------------

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


            If txtOTHER_DRUG1.Text = "" Then
                .Add(New SqlParameter("@OTHER_DRUG1", DBNull.Value))
            Else
                .Add(New SqlParameter("@OTHER_DRUG1", txtOTHER_DRUG1.Text))
            End If

            If txtOTHER_DRUG2.Text = "" Then
                .Add(New SqlParameter("@OTHER_DRUG2", DBNull.Value))
            Else
                .Add(New SqlParameter("@OTHER_DRUG2", txtOTHER_DRUG2.Text))
            End If


            If txtOTHER_DRUG3.Text = "" Then
                .Add(New SqlParameter("@OTHER_DRUG3", DBNull.Value))
            Else
                .Add(New SqlParameter("@OTHER_DRUG3", txtOTHER_DRUG3.Text))
            End If


            If txtOTHER_DRUG4.Text = "" Then
                .Add(New SqlParameter("@OTHER_DRUG4", DBNull.Value))
            Else
                .Add(New SqlParameter("@OTHER_DRUG4", txtOTHER_DRUG4.Text))
            End If



            'If ddlImmunoSupp1.SelectedValue = "" Then
            .Add(New SqlParameter("@P_IMMUN_SUP_DRUG", DBNull.Value))
            'Else
            '.Add(New SqlParameter("@P_IMMUN_SUP_DRUG", ddlImmunoSupp1.SelectedValue))
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

        lblUpdate.Text = String.Format("<strong>Update successful:</strong>&nbsp;&nbsp;{0}", Now().ToShortTimeString)
        lblUpdate2.Text = String.Format("<strong>Update successful:</strong>&nbsp;&nbsp;{0}", Now().ToShortTimeString)

        PageFill(txtSEQ_NO.Text)


    End Sub

    Protected Sub GridView3_RowDeleting(ByVal sender As Object, ByVal e As GridViewDeletedEventArgs)
        lblWarnP.Text = ""
    End Sub


    Protected Sub btnUpdate2_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnUpdate2.Click
        updateRecord()
    End Sub

    'Protected Sub txtBtnContinue_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles txtBtnContinue.Click
    '    bolFirstVisit = True
    '    ShowAddForm()
    'End Sub

    Protected Sub btnSaveNew2_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnSaveNew2.Click
        SaveNewRecord()
    End Sub
End Class
