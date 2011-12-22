Imports System.Data
Imports System.Data.OleDb
Imports System.Data.SqlClient
Imports System.IO
Partial Class labresults
    Inherits System.Web.UI.Page

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load

        If Not Session("blnAuthenticated") Then
            Response.Redirect("default.aspx")
        End If

        If Not IsPostBack Then
            pagefill()
        End If

        If IsPostBack Then
            If DropDownList3.SelectedValue = "1" Then
                txtDNA_ANTIB.Visible = True
                lblDNA_ANTIB.Visible = True
            Else
                txtDNA_ANTIB.Visible = False
                lblDNA_ANTIB.Visible = False
            End If
        End If

    End Sub

    Sub pagefill()

        Dim strSQL As String = "SELECT tbl_LabData.labID, tbl_LabData.RADAR_NO, tbl_LabData.DATE_LAB_RES, tbl_LabData.PROTEIN, tbl_LabData.CREAT_SER, tbl_LabData.PROT_CREAT_RAT, tbl_LabData.PHOS, tbl_LabData.POTASSIUM, tbl_LabData.SODIUM, tbl_LabData.UREA_SER, tbl_LabData.ALBUMIN, tbl_LabData.FERRITIN, tbl_LabData.PLATELETS, tbl_LabData.HB, tbl_LabData.NEUTRO, tbl_LabData.WBC, tbl_LabData.ALB_CREAT_RAT, tbl_LabData.CREAT_CLEAR_RADIO, tbl_LabData.ENA, tbl_LabData.HEP_C, tbl_LabData.HIV, tbl_LabData.EBV, tbl_LabData.HEP_B, tbl_LabData.ANTI_STREP_O, tbl_LabData.CRP, tbl_LabData.INR, tbl_LabData.C3_NEPH_FAC, tbl_LabData.COMP_C4, tbl_LabData.COMP_C3, tbl_LabData.IGM, tbl_LabData.PARVO_ANTIB, tbl_LabData.HANTAVIRUS, tbl_LabData.BKV_SYM, tbl_LabData.BKV, tbl_LabData.CMV_SYM, tbl_LabData.CMV, tbl_LabData.ALBUMINURIA, tbl_LabData.HAEMATURIA, tbl_LabData.UR_VOL_24H_COND, tbl_LabData.UR_VOL_24H, tbl_LabData.OTHER_INFECT_SP, tbl_LabData.OTHER_INFECT, tbl_LabData.BACT_URINE, tbl_LabData.NITRITE, tbl_LabData.LEUC_URINE, tbl_LabData.WBC_CASTS_URINE, tbl_LabData.RED_CCASTS_URINE, tbl_LabData.DYS_ERYTH_URINE, tbl_LabData.OSMOLARITY, tbl_LabData.GLUC_URINE, tbl_LabData.ANTI_SLT, tbl_LabData.IGA, tbl_LabData.IGG, tbl_LabData.ANTI_GBM, tbl_LabData.CRYOGLOB, tbl_LabData.DNA_ANTI_DS, tbl_LabData.DNA_ANTIB, tbl_LabData.ANA, tbl_LabData.ELISA_ASS, tbl_LabData.ANCA, tbl_LabData.CHOL_HDL, tbl_LabData.CREAT_CLEAR_SCHZ, tbl_LabData.THYROX, tbl_LabData.CREAT_CLEAR_24_URINE, tbl_LabData.TRIG, tbl_LabData.CHOL_LDL, tbl_LabData.CHOL_TOTAL, tbl_Demographics.SNAME, tbl_Demographics.FNAME, tbl_Demographics.DOB, tbl_Demographics.HOSP_NO, tbl_Diagnosis.DIAG FROM tbl_LabData INNER JOIN tbl_Demographics ON tbl_LabData.RADAR_NO = tbl_Demographics.RADAR_NO INNER JOIN tbl_Diagnosis ON tbl_Demographics.RADAR_NO = tbl_Diagnosis.RADAR_NO WHERE (tbl_LabData.RADAR_NO = " & Session("patientID") & ")"

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

                If objDataReader("DATE_LAB_RES") Is DBNull.Value Then
                Else
                    ASPxDateEdit1.Date = objDataReader("DATE_LAB_RES")
                End If

                txtFNAME.Text = objDataReader("FNAME")
                txtSNAME.Text = objDataReader("SNAME")
                txtDOB.Text = Format(objDataReader("DOB"), "dd-MMM-yyyy")
                txtALB_CREAT_RAT.Text = objDataReader("ALB_CREAT_RAT").ToString
                txtALBUMIN.Text = objDataReader("ALBUMIN").ToString
                txtANTI_SLT.Text = objDataReader("ANTI_SLT").ToString
                txtCHOL_HDL.Text = objDataReader("CHOL_HDL").ToString
                txtCHOL_LDL.Text = objDataReader("CHOL_LDL").ToString
                txtCHOL_TOTAL.Text = objDataReader("CHOL_TOTAL").ToString
                txtCREAT_CLEAR_24.Text = objDataReader("CREAT_CLEAR_24_URINE").ToString
                txtCREAT_CLEAR_RADIO.Text = objDataReader("CREAT_CLEAR_RADIO").ToString
                txtCREAT_CLEAR_SCHZ.Text = objDataReader("CREAT_CLEAR_SCHZ").ToString
                txtCreatSer.Text = objDataReader("CREAT_SER").ToString
                txtCRP.Text = objDataReader("CRP").ToString
                txtDNA_ANTIB.Text = objDataReader("DNA_ANTIB").ToString
                txtDNA_ANTI_DS.Text = objDataReader("DNA_ANTI_DS").ToString
                txtELISA_ASS.Text = objDataReader("ELISA_ASS").ToString
                txtFERRITIN.Text = objDataReader("FERRITIN").ToString
                txtHB.Text = objDataReader("HB").ToString
                txtINR.Text = objDataReader("INR").ToString
                txtNEUTRO.Text = objDataReader("NEUTRO").ToString
                txtOSMOLARITY.Text = objDataReader("OSMOLARITY").ToString
                txtOTHER_INFECT_SP.Text = objDataReader("OTHER_INFECT_SP").ToString
                txtPHOS.Text = objDataReader("PHOS").ToString
                txtPLATELETS.Text = objDataReader("PLATELETS").ToString
                txtPROT_CREAT_RAT.Text = objDataReader("PROT_CREAT_RAT").ToString
                txtPOTASSIUM.Text = objDataReader("POTASSIUM").ToString
                txtPROTEIN.Text = objDataReader("PROTEIN").ToString
                txtSODIUM.Text = objDataReader("SODIUM").ToString
                txtTHYROX.Text = objDataReader("THYROX").ToString
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
                chkCMV.Checked = objDataReader("CMV")
                chkCMV_SYM.Checked = objDataReader("CMV_SYM")
                chkEBV.Checked = objDataReader("EBV")
                chkGLUC_URINE.Checked = objDataReader("GLUC_URINE")
                'chkHANTAVIRUS.Checked = objDataReader("HANTAVIRUS")
                chkOTHER_INFECT.Checked = objDataReader("OTHER_INFECT")
                chkPARVO_ANTIB.Checked = objDataReader("PARVO_ANTIB")
                chkDYS_ERYTH_URINE.Checked = objDataReader("DYS_ERYTH_URINE")
                chkLEUC_URINE.Checked = objDataReader("LEUC_URINE")
                chkRED_CCASTS_URINE.Checked = objDataReader("RED_CCASTS_URINE")
                chkWBC_CASTS_URINE.Checked = objDataReader("WBC_CASTS_URINE")

                DropDownList12.SelectedValue = chkNull(objDataReader("UR_VOL_24H_COND"))
                DropDownList13.SelectedValue = chkNull(objDataReader("ALBUMINURIA"))
                DropDownList1.SelectedValue = chkNull(objDataReader("ANCA"))
                DropDownList9.SelectedValue = chkNull(objDataReader("HEP_B"))
                DropDownList10.SelectedValue = chkNull(objDataReader("HEP_C"))
                DropDownList11.SelectedValue = chkNull(objDataReader("HIV"))
                DropDownList2.SelectedValue = chkNull(objDataReader("ENA"))
                DropDownList3.SelectedValue = chkNull(objDataReader("ANA"))
                DropDownList7.SelectedValue = chkNull(objDataReader("COMP_C3"))
                DropDownList8.SelectedValue = chkNull(objDataReader("COMP_C4"))
                DropDownList14.SelectedValue = chkNull(objDataReader("NITRITE"))
                DropDownList16.SelectedValue = chkNull(objDataReader("HAEMATURIA"))


                If chkNull(objDataReader("ANA")) = "1" Then
                    txtDNA_ANTIB.Visible = True
                    lblDNA_ANTIB.Visible = True
                Else
                    txtDNA_ANTIB.Visible = False
                    lblDNA_ANTIB.Visible = False
                End If


                If objDataReader("DIAG") Is DBNull.Value Then
                Else

                    If objDataReader("DIAG") = 2 Then

                        txtTHYROX.Visible = False
                        lblTHYROX.Visible = False
                        txtIGG.Visible = False
                        lblIGG.Visible = False
                        txtIGA.Visible = False
                        lblIGA.Visible = False
                        txtIGM.Visible = False
                        lblIGM.Visible = False
                        DropDownList15.Visible = False
                        lblC3_NEPH_FAC.Visible = False
                        txtCRP.Visible = False
                        lblCRP.Visible = False
                        txtANTI_STREP_O.Visible = False
                        lblANTI_STREP_O.Visible = False
                        chkPARVO_ANTIB.Visible = False
                        lblPARVO_ANTIB.Visible = False
                        DropDownList14.Visible = False
                        lblNITRITE.Visible = False


                    End If

                End If





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

    Protected Sub btnUpdate_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnUpdate.Click

        Dim strSodium As String = txtSODIUM.Text

        Dim strSQL As String = "UPDATE [tbl_LabData] SET [RADAR_NO] = @RADAR_NO, [DATE_LAB_RES] = @DATE_LAB_RES, [PROTEIN] = @PROTEIN, [CREAT_SER] = @CREAT_SER, [PROT_CREAT_RAT] = @PROT_CREAT_RAT, [PHOS] = @PHOS, [POTASSIUM] = @POTASSIUM, [SODIUM] = @SODIUM, [UREA_SER] = @UREA_SER, [ALBUMIN] = @ALBUMIN, [FERRITIN] = @FERRITIN, [PLATELETS] = @PLATELETS, [HB] = @HB, [NEUTRO] = @NEUTRO, [WBC] = @WBC, [ALB_CREAT_RAT] = @ALB_CREAT_RAT, [CREAT_CLEAR_RADIO] = @CREAT_CLEAR_RADIO, [ENA] = @ENA, [HEP_C] = @HEP_C, [HIV] = @HIV, [EBV] = @EBV, [HEP_B] = @HEP_B, [ANTI_STREP_O] = @ANTI_STREP_O, [CRP] = @CRP, [INR] = @INR, [C3_NEPH_FAC] = @C3_NEPH_FAC, [COMP_C4] = @COMP_C4, [COMP_C3] = @COMP_C3, [IGM] = @IGM, [PARVO_ANTIB] = @PARVO_ANTIB, [HANTAVIRUS] = @HANTAVIRUS, [BKV_SYM] = @BKV_SYM, [BKV] = @BKV, [CMV_SYM] = @CMV_SYM, [CMV] = @CMV, [ALBUMINURIA] = @ALBUMINURIA, [HAEMATURIA] = @HAEMATURIA, [UR_VOL_24H_COND] = @UR_VOL_24H_COND, [UR_VOL_24H] = @UR_VOL_24H, [OTHER_INFECT_SP] = @OTHER_INFECT_SP, [OTHER_INFECT] = @OTHER_INFECT, [BACT_URINE] = @BACT_URINE, [NITRITE] = @NITRITE, [LEUC_URINE] = @LEUC_URINE, [WBC_CASTS_URINE] = @WBC_CASTS_URINE, [RED_CCASTS_URINE] = @RED_CCASTS_URINE, [DYS_ERYTH_URINE] = @DYS_ERYTH_URINE, [OSMOLARITY] = @OSMOLARITY, [GLUC_URINE] = @GLUC_URINE, [ANTI_SLT] = @ANTI_SLT, [IGA] = @IGA, [IGG] = @IGG, [ANTI_GBM] = @ANTI_GBM, [CRYOGLOB] = @CRYOGLOB, [DNA_ANTI_DS] = @DNA_ANTI_DS, [DNA_ANTIB] = @DNA_ANTIB, [ANA] = @ANA, [ELISA_ASS] = @ELISA_ASS, [ANCA] = @ANCA, [CHOL_HDL] = @CHOL_HDL, [CREAT_CLEAR_SCHZ] = @CREAT_CLEAR_SCHZ, [THYROX] = @THYROX, [CREAT_CLEAR_24_URINE] = @CREAT_CLEAR_24_URINE, [TRIG] = @TRIG, [CHOL_LDL] = @CHOL_LDL, [CHOL_TOTAL] = @CHOL_TOTAL WHERE [labID] = @labID"
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New System.Data.SqlClient.SqlConnection(strConnect)
        Dim objCommand As New System.Data.SqlClient.SqlCommand(strSQL, objConnect)

        With objCommand.Parameters

            If ASPxDateEdit1.Date = DateTime.MinValue Then
                .Add(New System.Data.SqlClient.SqlParameter("@DATE_LAB_RES", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@DATE_LAB_RES", ASPxDateEdit1.Date))
            End If

            If txtALB_CREAT_RAT.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@ALB_CREAT_RAT", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@ALB_CREAT_RAT", txtALB_CREAT_RAT.Text))
            End If

            If txtALBUMIN.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@ALBUMIN", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@ALBUMIN", txtALBUMIN.Text))
            End If

            If txtCHOL_HDL.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@CHOL_HDL", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@CHOL_HDL", txtCHOL_HDL.Text))
            End If

            If txtCHOL_LDL.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@CHOL_LDL", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@CHOL_LDL", txtCHOL_LDL.Text))
            End If

            If txtCHOL_TOTAL.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@CHOL_TOTAL", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@CHOL_TOTAL", txtCHOL_TOTAL.Text))
            End If

            If txtCREAT_CLEAR_24.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@CREAT_CLEAR_24_URINE", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@CREAT_CLEAR_24_URINE", txtCREAT_CLEAR_24.Text))
            End If

            If txtCREAT_CLEAR_RADIO.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@CREAT_CLEAR_RADIO", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@CREAT_CLEAR_RADIO", txtCREAT_CLEAR_RADIO.Text))
            End If

            If txtCREAT_CLEAR_SCHZ.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@CREAT_CLEAR_SCHZ", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@CREAT_CLEAR_SCHZ", txtCREAT_CLEAR_SCHZ.Text))
            End If

            If txtCreatSer.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@CREAT_SER", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@CREAT_SER", txtCreatSer.Text))
            End If

            If txtCRP.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@CRP", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@CRP", txtCRP.Text))
            End If

            If txtDNA_ANTIB.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@DNA_ANTIB", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@DNA_ANTIB", txtDNA_ANTIB.Text))
            End If

            If txtELISA_ASS.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@ELISA_ASS", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@ELISA_ASS", txtELISA_ASS.Text))
            End If

            If txtFERRITIN.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@FERRITIN", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@FERRITIN", txtFERRITIN.Text))
            End If


            If txtHB.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@HB", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@HB", txtHB.Text))
            End If

            If txtINR.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@INR", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@INR", txtINR.Text))
            End If

            If txtNEUTRO.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@NEUTRO", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@NEUTRO", txtNEUTRO.Text))
            End If

            If txtOSMOLARITY.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@OSMOLARITY", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@OSMOLARITY", txtOSMOLARITY.Text))
            End If

            If txtOTHER_INFECT_SP.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@OTHER_INFECT_SP", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@OTHER_INFECT_SP", txtOTHER_INFECT_SP.Text))
            End If

            If txtPHOS.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@PHOS", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@PHOS", txtPHOS.Text))
            End If

            If txtPLATELETS.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@PLATELETS", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@PLATELETS", txtPLATELETS.Text))
            End If

            If txtPROT_CREAT_RAT.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@PROT_CREAT_RAT", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@PROT_CREAT_RAT", txtPROT_CREAT_RAT.Text))
            End If

            If txtPOTASSIUM.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@POTASSIUM", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@POTASSIUM", txtPOTASSIUM.Text))
            End If

            If txtPROTEIN.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@PROTEIN", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@PROTEIN", txtPROTEIN.Text))
            End If

            If txtSODIUM.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@SODIUM", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@SODIUM", txtSODIUM.Text))
            End If

            If txtTHYROX.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@THYROX", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@THYROX", txtTHYROX.Text))
            End If

            If txtTRIG.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@TRIG", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@TRIG", txtTRIG.Text))
            End If

            If txtUR_VOL_24H.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@UR_VOL_24H", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@UR_VOL_24H", txtUR_VOL_24H.Text))
            End If

            If txtUREA_SER.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@UREA_SER", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@UREA_SER", txtUREA_SER.Text))
            End If

            If txtWBC.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@WBC", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@WBC", txtWBC.Text))
            End If

            If txtIGA.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@IGA", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@IGA", txtIGA.Text))
            End If

            If txtIGG.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@IGG", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@IGG", txtIGG.Text))
            End If

            If txtIGM.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@IGM", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@IGM", txtIGM.Text))
            End If

            If txtANTI_STREP_O.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@ANTI_STREP_O", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@ANTI_STREP_O", txtANTI_STREP_O.Text))
            End If

            If txtANTI_SLT.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@ANTI_SLT", txtANTI_SLT.Text))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@ANTI_SLT", txtANTI_SLT.Text))
            End If

            If txtDNA_ANTI_DS.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@DNA_ANTI_DS", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@DNA_ANTI_DS", txtDNA_ANTI_DS.Text))
            End If



            .Add(New System.Data.SqlClient.SqlParameter("@BACT_URINE", chkBACT_URINE.Checked))
            .Add(New System.Data.SqlClient.SqlParameter("@BKV", DBNull.Value)) ' removed
            .Add(New System.Data.SqlClient.SqlParameter("@BKV_SYM", DBNull.Value)) ' removed
            .Add(New System.Data.SqlClient.SqlParameter("@CMV", chkCMV.Checked))
            .Add(New System.Data.SqlClient.SqlParameter("@CMV_SYM", chkCMV_SYM.Checked))
            .Add(New System.Data.SqlClient.SqlParameter("@EBV", chkEBV.Checked))
            .Add(New System.Data.SqlClient.SqlParameter("@GLUC_URINE", chkGLUC_URINE.Checked))
            .Add(New System.Data.SqlClient.SqlParameter("@HANTAVIRUS", DBNull.Value)) 'removed
            .Add(New System.Data.SqlClient.SqlParameter("@OTHER_INFECT", chkOTHER_INFECT.Checked))
            .Add(New System.Data.SqlClient.SqlParameter("@PARVO_ANTIB", chkPARVO_ANTIB.Checked))
            .Add(New System.Data.SqlClient.SqlParameter("@DYS_ERYTH_URINE", chkDYS_ERYTH_URINE.Checked))
            .Add(New System.Data.SqlClient.SqlParameter("@LEUC_URINE", chkLEUC_URINE.Checked))
            .Add(New System.Data.SqlClient.SqlParameter("@RED_CCASTS_URINE", chkRED_CCASTS_URINE.Checked))
            .Add(New System.Data.SqlClient.SqlParameter("@WBC_CASTS_URINE", chkWBC_CASTS_URINE.Checked))


            If DropDownList1.SelectedValue = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@ANCA", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@ANCA", DropDownList1.SelectedValue))
            End If


            If DropDownList2.SelectedValue = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@ENA", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@ENA", DropDownList2.SelectedValue))
            End If

            If DropDownList3.SelectedValue = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@ANA", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@ANA", DropDownList3.SelectedValue))
            End If

            If DropDownList5.SelectedValue = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@CRYOGLOB", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@CRYOGLOB", DropDownList5.SelectedValue))
            End If

            If DropDownList6.SelectedValue = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@ANTI_GBM", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@ANTI_GBM", DropDownList6.SelectedValue))
            End If

            If DropDownList5.SelectedValue = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@C3_NEPH_FAC", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@C3_NEPH_FAC", DropDownList5.SelectedValue))
            End If



            If DropDownList7.SelectedValue = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@COMP_C3", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@COMP_C3", DropDownList7.SelectedValue))
            End If

            If DropDownList8.SelectedValue = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@COMP_C4", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@COMP_C4", DropDownList8.SelectedValue))
            End If

            If DropDownList9.SelectedValue = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@HEP_B", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@HEP_B", DropDownList9.SelectedValue))
            End If

            If DropDownList10.SelectedValue = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@HEP_C", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@HEP_C", DropDownList10.SelectedValue))
            End If

            If DropDownList11.SelectedValue = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@HIV", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@HIV", DropDownList11.SelectedValue))
            End If

            If DropDownList12.SelectedValue = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@UR_VOL_24H_COND", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@UR_VOL_24H_COND", DropDownList12.SelectedValue))
            End If

            If DropDownList13.SelectedValue = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@ALBUMINURIA", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@ALBUMINURIA", DropDownList13.SelectedValue))
            End If

            If DropDownList14.SelectedValue = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@NITRITE", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@NITRITE", DropDownList14.SelectedValue))
            End If

            If DropDownList16.SelectedValue = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@HAEMATURIA", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@HAEMATURIA", DropDownList16.SelectedValue))
            End If

            .Add(New System.Data.SqlClient.SqlParameter("@RADAR_NO", txtRADAR_NO.Text))
            .Add(New System.Data.SqlClient.SqlParameter("@labID", lblLabID.Text))


        End With

        Try
            objConnect.Open()
            objCommand.ExecuteNonQuery()

        Catch objError As Exception

            lblDebug.Text = "An error occurred: '" & objError.Message & "'"

        Finally
            objConnect.Close()
        End Try

        lblUpdate.Text = "<strong>Update successful:</strong>&nbsp;&nbsp;" & Now().ToShortTimeString
        'lblDebug.Text = strSodium

        pagefill()

    End Sub
End Class
