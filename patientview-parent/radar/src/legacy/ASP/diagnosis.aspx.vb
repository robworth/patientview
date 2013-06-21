Imports System.Data.SqlClient
Partial Class diagnosis
    Inherits System.Web.UI.Page


    Protected Sub Page_Load(ByVal sender As Object, ByVal e As EventArgs) Handles Me.Load

        If Session("uType") = "p" Then
            UpdateButton.Visible = False
            CommonClass.DisableControls(mainForm)
            'rowGeneMutate.Visible = False
            rowGeneMutation.Visible = False
            lnkTimelines.Visible = False

        Else
            lnkRelapse.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnk3Months.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkLabResults.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkDemographics.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkPathology.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkHospital.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkTimelines.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkTimelines.Visible = False
        End If


        If Not Session("blnAuthenticated") Then
            Response.Redirect("professionals.aspx")
        End If

        If Request.QueryString("id") = "" Then
            'Response.Redirect("default.aspx")
        Else
            Session("patientID") = Request.QueryString("id")
            Session("mode") = 2
        End If


        txtDateToday.Text = Format(DateTime.Today, "dd-MMM-yyyy")
        'ASPxDateEdit1.MaxDate = DateTime.Today

        If Not IsPostBack Then
            pagefill()
        End If

        If IsPostBack Then

        End If


    End Sub

    Protected Sub Page_UnLoad(ByVal sender As Object, ByVal e As EventArgs) Handles Me.Unload
        'PageSave()
    End Sub

    Function chkNull(ByVal value As Object) As String

        If value Is DBNull.Value Then
            chkNull = ""
        Else
            chkNull = value.ToString
        End If

    End Function

    
    Sub pagefill()

        Dim TDES As New TripleDES()

        Dim patientID As Integer = Session("patientID")
        'Dim strSQL As String = "SELECT tbl_Diagnosis.dID, tbl_Diagnosis.RADAR_NO, tbl_Diagnosis.DATE_DIAG, tbl_Diagnosis.DATE_ESRF, tbl_Diagnosis.DIAG, tbl_Diagnosis.DIAG_TXT, tbl_Diagnosis.GENE_MUT, tbl_Diagnosis.GENE_MUT_TEXT, tbl_Diagnosis.CLIN_PRES, tbl_Diagnosis.CLIN_PRES_B, tbl_Diagnosis.PREPUB_DIAG, tbl_Diagnosis.BX_PROVEN_DIAG, tbl_Diagnosis.HEIGHT_FIRST_VISIT, tbl_Diagnosis.AGE_AT_DIAG, tbl_Diagnosis.REL2, tbl_Diagnosis.REL1, tbl_Diagnosis.FAM_HIST, tbl_Diagnosis.CONSANGUINITY, tbl_Diagnosis.DATE_ONSET_RENALDIS, tbl_Diagnosis.KARYOTYPE, tbl_Diagnosis.SIG_DIAG2, tbl_Diagnosis.SIG_DIAG1, tbl_Diagnosis.STEROID_RESIST, tbl_Diagnosis.REL6, tbl_Diagnosis.REL5, tbl_Diagnosis.REL4, tbl_Diagnosis.REL3, tbl_Diagnosis.REL1_RADAR, tbl_Diagnosis.REL2_RADAR, tbl_Diagnosis.REL3_RADAR, tbl_Diagnosis.REL4_RADAR, tbl_Diagnosis.REL5_RADAR, tbl_Diagnosis.REL6_RADAR, tbl_Demographics.HOSP_NO, tbl_Demographics.SNAME, tbl_Demographics.FNAME, tbl_Demographics.DOB FROM tbl_Diagnosis INNER JOIN tbl_Demographics ON tbl_Diagnosis.RADAR_NO = tbl_Demographics.RADAR_NO WHERE tbl_Diagnosis.RADAR_NO = " & patientID
        Dim strSQL As String = "SELECT [dID], [tbl_Diagnosis].[RADAR_NO], [DATE_DIAG], [DIAG], [DIAG_TXT], [AGE_AT_DIAG], [HEIGHT_FIRST_VISIT], [BX_PROVEN_DIAG], [PREPUB_DIAG], [CLIN_PRES], [CLIN_PRES_B], [GENE_MUT], [GENE_MUT_TEXT], [KARYOTYPE], [DATE_ONSET_RENALDIS], [CONSANGUINITY], [FAM_HIST], [REL1], [REL1_RADAR], [REL2], [REL2_RADAR], [REL3], [REL3_RADAR], [REL4], [DATE_ESRF], [STEROID_RESIST], [REL4_RADAR], [REL5], [REL5_RADAR], [REL6], [REL6_RADAR], [SIG_DIAG1], [SIG_DIAG2], [MUTATION_1], [MUTATION_1S], [MUTATION_2], [MUTATION_2S], [MUTATION_3], [MUTATION_3S], [MUTATION_4], [MUTATION_4S], [MUTATION_5], [MUTATION_5S], [MUTATION_6], [MUTATION_6S], [MUTATION_7], [MUTATION_7S], [MUTATION_8], [MUTATION_8S], [MUTATION_9], [MUTATION_9S], tbl_Demographics.HOSP_NO, tbl_Demographics.SNAME, tbl_Demographics.FNAME, tbl_Demographics.DOB FROM tbl_Diagnosis INNER JOIN tbl_Demographics ON tbl_Diagnosis.RADAR_NO = tbl_Demographics.RADAR_NO WHERE tbl_Diagnosis.RADAR_NO = " & patientID

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

                lbldID.Text = objDataReader("dID")

                RADAR_NOTextbox.Text = objDataReader("RADAR_NO")
                FNAMETextbox.Text = TDES.Decrypt(objDataReader("FNAME"))
                SNAMETextbox.Text = TDES.Decrypt(objDataReader("SNAME"))

                Dim dt As Date = CDate(TDES.Decrypt(objDataReader("DOB")))
                DOBTextbox.Text = Format(dt, "dd-MMM-yyyy")

                If objDataReader("DATE_ESRF") Is DBNull.Value Then

                Else
                    txtDATE_ESRF.Text = Format(objDataReader("DATE_ESRF"), "dd-MM-yyyy")
                End If

                HOSP_NOTextbox.Text = TDES.Decrypt(objDataReader("HOSP_NO"))

                If objDataReader("DIAG") Is DBNull.Value Then  ' If no diagnosis, don't allow First Visit or Follow Up entry
                    lnk3Months.Enabled = False
                    lnkLabResults.Enabled = False
                    lnkRelapse.Enabled = False
                    lnkPathology.Enabled = False
                    lnkHospital.Enabled = False
                    lnkTimelines.Enabled = False
                Else
                    DropDownList7.SelectedValue = chkNull(objDataReader("DIAG"))
                    lnkLabResults.Enabled = True
                    If chkClinical(objDataReader("RADAR_NO")) = False Then
                        lnkRelapse.Enabled = False
                        lnkPathology.Enabled = False
                        lnkHospital.Enabled = False
                        lnkTimelines.Enabled = False
                    End If
                End If

                If objDataReader("DIAG") Is DBNull.Value Then
                    Session("diag") = 0
                    rowGeneLabel.Visible = False
                    rowGeneMutateA.Visible = False
                Else
                    Select Case objDataReader("DIAG")

                        Case 1 'SRNS, B pages

                            Session("diag") = 1
                            DropDownList7.Enabled = False
                            lblPSteroid.Text = " Primary Steroid Resistance"
                            SqlDataSource6.SelectCommand = "SELECT [gmID], [GENE_MUTATION] FROM [tbl_GeneMutation] WHERE [gmID] < 100"
                            lblPage.Text = "2B"
                            diagHelp.InnerHtml = "Approximate date of diagnosis of steroid resistant nephrotic syndrome"
                            onsetHelp.InnerHtml = "Approximate date of onset of symptoms or nephrosis"
                            rowGeneLabel.Visible = True
                            rowGeneMutateA.Visible = True

                            lblBiopsy.Text = "Biopsy Diagnosis"
                            '------------------
                            'build Biopsy DDL
                            Dim liMin As New ListItem
                            liMin.Text = "Minimal change"
                            liMin.Value = 2
                            DropDownList11.Items.Add(liMin)
                            Dim liFSGS As New ListItem
                            liFSGS.Text = "FSGS"
                            liFSGS.Value = 3
                            DropDownList11.Items.Add(liFSGS)
                            Dim liMH As New ListItem
                            liMH.Text = "Mesangial hypertrophy"
                            liMH.Value = 4
                            DropDownList11.Items.Add(liMH)
                            Dim liOther As New ListItem
                            liOther.Text = "Other"
                            liOther.Value = 5
                            DropDownList11.Items.Add(liOther)
                            '--------------------

                        Case 2 'MPGN/DDD, A pages

                            Session("diag") = 2
                            DropDownList7.Enabled = False
                            rowPresSR.Visible = False
                            rowPSS.Visible = False
                            rowSSR.Visible = False

                            'lblPSteroid.Text = "Primary Steroid Resistance"
                            'lblBPS.Visible = False
                            'radBPS.Visible = False
                            SqlDataSource6.SelectCommand = "SELECT [gmID], [GENE_MUTATION] FROM [tbl_GeneMutation] WHERE [gmID] >= 200"
                            lblPage.Text = "2A"
                            hlpDiag.Visible = False
                            onsetHelp.InnerHtml = "Date of onset of symptoms or date of presentation to Renal unit"
                            rowGeneLabel.Visible = True
                            rowGeneMutateA.Visible = True

                            lblBiopsy.Text = "Biopsy Proven Diagnosis"
                            '-------------------
                            'build biopsy DDL
                            Dim liNo As New ListItem
                            liNo.Text = "No"
                            liNo.Value = 0
                            DropDownList11.Items.Add(liNo)
                            Dim liYes As New ListItem
                            liYes.Text = "Yes"
                            liYes.Value = 1
                            DropDownList11.Items.Add(liYes)
                            '-------------------
                        Case Else

                            Session("diag") = 0
                            DropDownList7.Enabled = True
                            SqlDataSource6.SelectCommand = "SELECT [gmID], [GENE_MUTATION] FROM [tbl_GeneMutation]"
                            lblPage.Text = "2"
                            rowGeneLabel.Visible = False
                            rowGeneMutateA.Visible = False

                    End Select
                End If




                If chkClinical(objDataReader("RADAR_NO")) = True Then  ' if a Clinical Picture exists, allow Follow Up entries
                    lnk3Months.Enabled = True

                Else
                    lnk3Months.Enabled = False
                End If


                DIAG_TXTTextbox.Text = chkNull(objDataReader("DIAG_TXT"))

                If objDataReader("AGE_AT_DIAG") IsNot DBNull.Value Then
                    AGE_AT_DIAGTextbox.Text = CInt(objDataReader("AGE_AT_DIAG"))
                Else
                    AGE_AT_DIAGTextbox.Text = ""
                End If

                If objDataReader("PREPUB_DIAG") Is DBNull.Value Then
                    CheckBox10.Checked = False
                Else
                    CheckBox10.Checked = objDataReader("PREPUB_DIAG")

                End If
                TextBox11.Text = chkNull(objDataReader("HEIGHT_FIRST_VISIT"))

                DropDownList8.SelectedValue = chkNull(objDataReader("CLIN_PRES"))
                DropDownList14.SelectedValue = chkNull(objDataReader("CLIN_PRES_B"))

                'If objDataReader("CLIN_PRES") Is DBNull.Value Then

                'Else
                '    DropDownList14.Enabled = True
                '    'DropDownList14.Items.RemoveAt(DropDownList14.Items.IndexOf(DropDownList14.Items.FindByValue(objDataReader("CLIN_PRES"))))
                'End If

                If objDataReader("DATE_ONSET_RENALDIS") Is DBNull.Value Then
                    DATE_ONSET_RENALDISTextbox.Text = ""
                Else
                    'DATE_ONSET_RENALDISTextbox.Text = Format(objDataReader("DATE_ONSET_RENALDIS"), "dd-MMM-yyyy")
                    DATE_ONSET_RENALDISTextbox_CalendarExtender.SelectedDate = objDataReader("DATE_ONSET_RENALDIS")
                End If


                SIG_DIAG1Textbox.Text = chkNull(objDataReader("SIG_DIAG1"))
                SIG_DIAG2Textbox.Text = chkNull(objDataReader("SIG_DIAG2"))

                If objDataReader("STEROID_RESIST") Is DBNull.Value Then

                    radPrimarySteroid.Checked = False
                    radSSR.Checked = False
                    radPresSR.Checked = False
                    radBPS.Checked = False

                Else
                    Select Case objDataReader("STEROID_RESIST")
                        Case 1
                            radPrimarySteroid.Checked = True
                        Case 2
                            radSSR.Checked = True
                        Case 3
                            radPresSR.Checked = True
                        Case 4
                            radBPS.Checked = True
                    End Select

                End If

                DropDownList11.SelectedValue = chkNull(objDataReader("BX_PROVEN_DIAG"))
                'DropDownList10.SelectedValue = chkNull(objDataReader("GENE_MUT"))

                'If objDataReader("GEN_MUT") Is DBNull.Value Then
                '    chkGMNo.Enabled = False
                '    chkGMNSent.Enabled = False
                '    chkGMSent.Enabled = False
                '    chkGMYes.Enabled = False
                'End If

                If objDataReader("GENE_MUT") Is DBNull.Value Then
                    rowGeneMutation.Visible = False
                Else
                    txtGENE_MUT_TEXT.Text = chkNull(objDataReader("GENE_MUT_TEXT"))
                End If


                DropDownList9.SelectedValue = chkNull(objDataReader("KARYOTYPE"))

                DropDownList13.SelectedValue = chkNull(objDataReader("FAM_HIST"))
                'chkFAMILY_HISTORY.Checked = objDataReader("FAM_HIST")

                DropDownList1.SelectedValue = chkNull(objDataReader("REL1"))
                DropDownList2.SelectedValue = chkNull(objDataReader("REL2"))
                DropDownList3.SelectedValue = chkNull(objDataReader("REL3"))
                DropDownList4.SelectedValue = chkNull(objDataReader("REL4"))
                DropDownList5.SelectedValue = chkNull(objDataReader("REL5"))
                DropDownList6.SelectedValue = chkNull(objDataReader("REL6"))
                DropDownList12.SelectedValue = chkNull(objDataReader("CONSANGUINITY"))
                txtREL1_RADAR.Text = chkNull(objDataReader("REL1_RADAR"))
                txtREL2_RADAR.Text = chkNull(objDataReader("REL2_RADAR"))
                txtREL3_RADAR.Text = chkNull(objDataReader("REL3_RADAR"))
                txtREL4_RADAR.Text = chkNull(objDataReader("REL4_RADAR"))
                txtREL5_RADAR.Text = chkNull(objDataReader("REL5_RADAR"))
                txtREL6_RADAR.Text = chkNull(objDataReader("REL6_RADAR"))


                If objDataReader("DATE_DIAG") Is DBNull.Value Then
                    'ASPxDateEdit1.Date
                Else
                    'txtDateDiag.Text = Format(objDataReader("DATE_DIAG"), "dd-MMM-yyyy")
                    txtDateDiag_CalendarExtender.SelectedDate = objDataReader("DATE_DIAG")
                End If

                If objDataReader("FAM_HIST") IsNot DBNull.Value Then
                    If objDataReader("FAM_HIST") = 1 Then
                        DropDownList1.Enabled = True
                        DropDownList2.Enabled = True
                        DropDownList3.Enabled = True
                        DropDownList4.Enabled = True
                        DropDownList5.Enabled = True
                        DropDownList6.Enabled = True
                        DropDownList1.Visible = True
                        DropDownList2.Visible = True
                        DropDownList3.Visible = True
                        DropDownList4.Visible = True
                        DropDownList5.Visible = True
                        DropDownList6.Visible = True
                        lblFirstRel.Visible = True
                        lblSecondRel.Visible = True
                        lblThirdRel.Visible = True
                        lblFourthRel.Visible = True
                        lblFifthRel.Visible = True
                        lblSixthRel.Visible = True
                        txtREL1_RADAR.Visible = True
                        txtREL2_RADAR.Visible = True
                        txtREL3_RADAR.Visible = True
                        txtREL4_RADAR.Visible = True
                        txtREL5_RADAR.Visible = True
                        txtREL6_RADAR.Visible = True
                        lblRelRADAR.Visible = True
                        'rowREL1.Visible = True
                        'rowREL2.Visible = True
                        'rowREL3.Visible = True
                        'rowREL4.Visible = True
                        'rowREL5.Visible = True
                        'rowREL6.Visible = True
                    Else
                        'rowREL1.Visible = False
                        'rowREL2.Visible = False
                        'rowREL3.Visible = False
                        'rowREL4.Visible = False
                        'rowREL5.Visible = False
                        'rowREL6.Visible = False
                    End If
                End If



                If objDataReader("DIAG") Is DBNull.Value Then

                Else
                    If objDataReader("DIAG") = 2 Then  'MSGN/DDD

                        lblKarotype.Visible = False
                        DropDownList9.Visible = False
                        'lblPSteroid.Visible = False
                        'radPrimarySteroid.Visible = False
                        'radSSR.Visible = False
                        radBPS.Visible = False
                        'radPresSR.Visible = False
                        'lblPSteroid.Visible = False
                        'lblSSR.Visible = False
                        'lblPresSR.Visible = False
                        lblBPS.Visible = False
                        hypSteroid.Visible = True
                        hypSSR.Visible = True
                        hypPresSR.Visible = True
                        hypBPS.Visible = False
                        lblDateDiag.Text = "Date of original biopsy"
                        'rowGeneMutate.Visible = False
                        'rowGeneMutation.Visible = False
                        'rowGeneMutateA.Visible = False
                        'rowGeneLabel.Visible = False

                    Else  ' SRNS

                        lblKarotype.Visible = True
                        DropDownList9.Visible = True
                        lblPSteroid.Visible = True
                        radPrimarySteroid.Visible = True
                        radSSR.Visible = True
                        radBPS.Visible = True
                        radPresSR.Visible = True
                        lblPSteroid.Visible = True
                        lblSSR.Visible = True
                        lblPresSR.Visible = True
                        lblBPS.Visible = True
                        hypSteroid.Visible = True
                        hypSSR.Visible = True
                        hypPresSR.Visible = True
                        hypBPS.Visible = True



                    End If


                    If objDataReader("MUTATION_1") Is DBNull.Value Then
                    Else
                        If objDataReader("MUTATION_1") = True Then
                            radACTN4Y.Checked = True
                        ElseIf objDataReader("MUTATION_1") = False Then
                            radACTN4N.Checked = True
                            radACTN4S.Enabled = False
                            radACTN4NS.Enabled = False
                        End If
                    End If

                    If objDataReader("MUTATION_1S") Is DBNull.Value Then
                    Else
                        If objDataReader("MUTATION_1S") = True Then
                            radACTN4S.Checked = True
                        ElseIf objDataReader("MUTATION_1S") = False Then
                            radACTN4NS.Checked = True
                        End If
                    End If

                    If objDataReader("MUTATION_2") Is DBNull.Value Then
                    Else
                        If objDataReader("MUTATION_2") = True Then
                            radCD2APYes.Checked = True
                        ElseIf objDataReader("MUTATION_2") = False Then
                            radCD2APNo.Checked = True
                            radCD2APS.Enabled = False
                            radCD2APNS.Enabled = False
                        End If
                    End If

                    If objDataReader("MUTATION_2S") Is DBNull.Value Then
                    Else
                        If objDataReader("MUTATION_2S") = True Then
                            radCD2APS.Checked = True
                        ElseIf objDataReader("MUTATION_2S") = False Then
                            radCD2APNS.Checked = True
                        End If
                    End If

                    If objDataReader("MUTATION_3") Is DBNull.Value Then
                    Else
                        If objDataReader("MUTATION_3") = True Then
                            radLAMB2Yes.Checked = True
                        ElseIf objDataReader("MUTATION_3") = False Then
                            radLAMB2No.Checked = True
                            radLAMBS.Enabled = False
                            radLAMBNS.Enabled = False
                        End If
                    End If

                    If objDataReader("MUTATION_3S") Is DBNull.Value Then
                    Else
                        If objDataReader("MUTATION_3S") = True Then
                            radLAMBS.Checked = True
                        ElseIf objDataReader("MUTATION_3S") = False Then
                            radLAMBNS.Checked = True
                        End If
                    End If

                    If objDataReader("MUTATION_4") Is DBNull.Value Then
                    Else
                        If objDataReader("MUTATION_4") = True Then
                            radNPHS1Y.Checked = True
                        ElseIf objDataReader("MUTATION_4") = False Then
                            radNPHS1N.Checked = True
                            radNPHS1S.Enabled = False
                            radNPHS1NS.Enabled = False
                        End If
                    End If

                    If objDataReader("MUTATION_4S") Is DBNull.Value Then
                    Else
                        If objDataReader("MUTATION_4S") = True Then
                            radNPHS1S.Checked = True
                        ElseIf objDataReader("MUTATION_4S") = False Then
                            radNPHS1NS.Checked = True
                        End If
                    End If

                    If objDataReader("MUTATION_5") Is DBNull.Value Then
                    Else
                        If objDataReader("MUTATION_5") = True Then
                            radNPHS2Y.Checked = True
                        ElseIf objDataReader("MUTATION_5") = False Then
                            radNPHS2N.Checked = True
                            radNPHS2S.Enabled = False
                            radNPHS2NS.Enabled = False
                        End If
                    End If

                    If objDataReader("MUTATION_5S") Is DBNull.Value Then
                    Else
                        If objDataReader("MUTATION_5S") = True Then
                            radNPHS2S.Checked = True
                        ElseIf objDataReader("MUTATION_5S") = False Then
                            radNPHS2NS.Checked = True
                        End If
                    End If

                    If objDataReader("MUTATION_6") Is DBNull.Value Then
                    Else
                        If objDataReader("MUTATION_6") = True Then
                            radNPHS3Y.Checked = True
                        ElseIf objDataReader("MUTATION_6") = False Then
                            radNPHS3N.Checked = True
                            radNPHS3S.Enabled = False
                            radNPHS3NS.Enabled = False
                        End If
                    End If

                    If objDataReader("MUTATION_6S") Is DBNull.Value Then
                    Else
                        If objDataReader("MUTATION_6S") = True Then
                            radNPHS3S.Checked = True
                        ElseIf objDataReader("MUTATION_6S") = False Then
                            radNPHS3NS.Checked = True
                        End If
                    End If

                    If objDataReader("MUTATION_7") Is DBNull.Value Then
                    Else
                        If objDataReader("MUTATION_7") = True Then
                            radTRPC6Y.Checked = True
                        ElseIf objDataReader("MUTATION_7") = False Then
                            radTRPC6N.Checked = True
                            radTRPC6S.Enabled = False
                            radTRPC6NS.Enabled = False
                        End If
                    End If

                    If objDataReader("MUTATION_7S") Is DBNull.Value Then
                    Else
                        If objDataReader("MUTATION_7S") = True Then
                            radTRPC6S.Checked = True
                        ElseIf objDataReader("MUTATION_7S") = False Then
                            radTRPC6NS.Checked = True
                        End If
                    End If

                    If objDataReader("MUTATION_8") Is DBNull.Value Then
                    Else
                        If objDataReader("MUTATION_8") = True Then
                            radWilmY.Checked = True
                        ElseIf objDataReader("MUTATION_8") = False Then
                            radWilmN.Checked = True
                            radWilmS.Enabled = False
                            radWilmNS.Enabled = False
                        End If
                    End If

                    If objDataReader("MUTATION_8S") Is DBNull.Value Then
                    Else
                        If objDataReader("MUTATION_8S") = True Then
                            radWilmS.Checked = True
                        ElseIf objDataReader("MUTATION_8S") = False Then
                            radWilmNS.Checked = True
                        End If
                    End If

                    If objDataReader("MUTATION_9") Is DBNull.Value Then
                    Else
                        If objDataReader("MUTATION_9") = True Then
                            radOtherY.Checked = True
                            rowGeneMutation.Visible = True
                            txtGENE_MUT_TEXT.Text = objDataReader("GENE_MUT_TEXT")
                        ElseIf objDataReader("MUTATION_9") = False Then
                            radOtherN.Checked = True
                            radOtherS.Enabled = False
                            radOtherNS.Enabled = False
                            rowGeneMutation.Visible = False
                        End If
                    End If

                    If objDataReader("MUTATION_9S") Is DBNull.Value Then
                    Else
                        If objDataReader("MUTATION_9S") = True Then
                            radOtherS.Checked = True
                        ElseIf objDataReader("MUTATION_9S") = False Then
                            radOtherNS.Checked = True
                        End If
                    End If

                End If


            End If

        Catch objError As Exception

            lblDebug.Text = String.Format("An error occurred (PageFill): '{0}'{1}", objError.Message, strSQL)


        Finally

            objConnect.Close()

        End Try


        'get the Gene Mutation data
        'Dim strSQL2 As String = "SELECT [GENE_MUTATION], [GENE_YES], [GENE_SENT] FROM [tbl_GENE_MUTATION] WHERE [RADAR_NO] = " & patientID
        'Dim objConnect2 As New System.Data.SqlClient.SqlConnection(strConnect)
        'Dim objCommand2 As New System.Data.SqlClient.SqlCommand(strSQL2, objConnect2)

        'Try

        '    Dim objDataReader As SqlDataReader
        '    objConnect2.Open()
        '    objDataReader = objCommand2.ExecuteReader()


        '    If objDataReader.Read() Then 'this misses first record

        '        While objDataReader.Read

        '            Select Case objDataReader("GENE_MUTATION")

        '                Case 1

        '                    If objDataReader("GENE_YES") = True Then
        '                        radACTN4Y.Checked = True
        '                    End If

        '                    If objDataReader("GENE_SENT") = True Then
        '                        radACTN4S.Checked = True
        '                    End If

        '                Case 2

        '                    If objDataReader("GENE_YES") = True Then
        '                        radAD2APYes.Checked = True
        '                    End If

        '                    If objDataReader("GENE_SENT") = True Then
        '                        radAD2APS.Checked = True
        '                    End If

        '                Case 3

        '                    If objDataReader("GENE_YES") = True Then

        '                    End If

        '                    If objDataReader("GENE_SENT") = True Then

        '                    End If

        '                Case 4

        '                    If objDataReader("GENE_YES") = True Then

        '                    End If

        '                    If objDataReader("GENE_SENT") = True Then

        '                    End If

        '                Case 5

        '                    If objDataReader("GENE_YES") = True Then

        '                    End If

        '                    If objDataReader("GENE_SENT") = True Then

        '                    End If

        '                Case 6

        '                    If objDataReader("GENE_YES") = True Then
        '                        radNPHS3Y.Checked = True
        '                    End If

        '                    If objDataReader("GENE_SENT") = True Then
        '                        radNPHS3S.Checked = True
        '                    End If

        '                Case 7

        '                    If objDataReader("GENE_YES") = True Then

        '                    End If

        '                    If objDataReader("GENE_SENT") = True Then

        '                    End If

        '                Case 8

        '                    If objDataReader("GENE_YES") = True Then

        '                    End If

        '                    If objDataReader("GENE_SENT") = True Then

        '                    End If

        '                Case 9

        '                    If objDataReader("GENE_YES") = True Then

        '                    End If

        '                    If objDataReader("GENE_SENT") = True Then

        '                    End If

        '                Case Else

        '            End Select

        '        End While

        '    End If

        'Catch objError As Exception

        '    lblDebug.Text = String.Format("An error occurred (PageFill - Gene): '{0}'{1}", objError.Message, strSQL2)

        'Finally

        '    objConnect2.Close()

        'End Try

    End Sub


    Protected Sub DropDownList7_SelectedIndexChanged(ByVal sender As Object, ByVal e As EventArgs) Handles DropDownList7.SelectedIndexChanged

        'Dim lnkAbout As HyperLink = Master.FindControl("lnkAbout")
        'Dim lnkStudy As HyperLink = Master.FindControl("lnkStudy")
        'Dim lblSpace3 As Label = Master.FindControl("lblSpace3")
        'Dim lblSpace4 As Label = Master.FindControl("lblSpace4")
        'Dim lnkDisease As HyperLink = Master.FindControl("lnkDisease")
        'Dim lblSpace2 As Label = Master.FindControl("lblSpace2")

        If DropDownList7.SelectedIndex = 0 Then
            'do nothing
            rowGeneLabel.Visible = False
            rowGeneMutateA.Visible = False
        Else
            If DropDownList7.SelectedValue = 2 Then  'MPGN

                lblKarotype.Visible = False
                DropDownList9.Visible = False
                lblPSteroid.Visible = False
                radPrimarySteroid.Visible = False
                radSSR.Visible = False
                radBPS.Visible = False
                radPresSR.Visible = False
                lblPSteroid.Visible = False
                lblSSR.Visible = False
                lblPresSR.Visible = False
                lblBPS.Visible = False
                hypSteroid.Visible = False
                hypSSR.Visible = False
                hypPresSR.Visible = False
                hypBPS.Visible = False
                lblDateDiag.Text = "Date of original biopsy"
                rowGeneLabel.Visible = False
                rowGeneMutateA.Visible = False

                lblBiopsy.Text = "Biopsy Proven Diagnosis"
                '-------------------
                'build biopsy DDL
                Dim liNo As New ListItem
                liNo.Text = "No"
                liNo.Value = 0
                DropDownList11.Items.Add(liNo)
                Dim liYes As New ListItem
                liYes.Text = "Yes"
                liYes.Value = 1
                DropDownList11.Items.Add(liYes)
                '-------------------

                'lnkAbout.Text = "About MPGN"
                'lnkAbout.Visible = True
                'lnkAbout.NavigateUrl = "about.aspx?d=2"
                'lnkStudy.Text = "MPGN Study Group"
                'lnkStudy.Visible = True
                'lnkStudy.NavigateUrl = "study.aspx?d=2"
                'lblSpace3.Visible = True
                'lblSpace4.Visible = True
                'lnkDisease.Visible = False
                'lblSpace2.Visible = False



                'DropDownList10.Items.Clear()
                'SqlDataSource6.SelectCommand = "SELECT [gmID], [GENE_MUTATION] FROM [tbl_GeneMutation] WHERE [gmID]>= 200"
                'DropDownList10.DataBind()
                'Dim liItem As ListItem = New ListItem("Select", "")
                'DropDownList10.Items.Insert(0, liItem)


            ElseIf DropDownList7.SelectedValue = 1 Then 'FSGS
                lblKarotype.Visible = True
                DropDownList9.Visible = True
                lblPSteroid.Visible = True
                radPrimarySteroid.Visible = True
                radSSR.Visible = True
                radBPS.Visible = True
                radPresSR.Visible = True
                lblPSteroid.Visible = True
                lblSSR.Visible = True
                lblPresSR.Visible = True
                lblBPS.Visible = True
                hypSteroid.Visible = True
                hypSSR.Visible = True
                hypPresSR.Visible = True
                hypBPS.Visible = True
                'DropDownList7.BackColor = Drawing.Color.YellowGreen
                lblDateDiag.Text = "Date of Diagnosis"
                rowGeneLabel.Visible = True
                rowGeneMutateA.Visible = True

                lblBiopsy.Text = "Biopsy Diagnosis"
                '------------------
                'build Biopsy DDL
                Dim liMin As New ListItem
                liMin.Text = "Minimal change"
                liMin.Value = 2
                DropDownList11.Items.Add(liMin)
                Dim liFSGS As New ListItem
                liFSGS.Text = "FSGS"
                liFSGS.Value = 3
                DropDownList11.Items.Add(liFSGS)
                Dim liMH As New ListItem
                liMH.Text = "Mesangial hypertrophy"
                liMH.Value = 4
                DropDownList11.Items.Add(liMH)
                Dim liOther As New ListItem
                liOther.Text = "Other"
                liOther.Value = 5
                DropDownList11.Items.Add(liOther)

                'lnkAbout.Text = "About SRSN"
                'lnkAbout.Visible = True
                'lnkAbout.NavigateUrl = "about.aspx?d=1"
                'lnkStudy.Text = "SRSN Study Group"
                'lnkStudy.Visible = True
                'lnkStudy.NavigateUrl = "study.aspx?d=1"
                'lblSpace3.Visible = True
                'lblSpace4.Visible = True
                'lnkDisease.Visible = False
                'lblSpace2.Visible = False

                'DropDownList10.Items.Clear()
                'SqlDataSource6.SelectCommand = "SELECT [gmID], [GENE_MUTATION] FROM [tbl_GeneMutation] WHERE [gmID] < 100"
                'Dim liItem As ListItem = New ListItem("Select", "")
                'DropDownList10.Items.Insert(0, liItem)
            Else
                lblKarotype.Visible = True
                DropDownList9.Visible = True
                lblPSteroid.Visible = True
                radPrimarySteroid.Visible = True
                radSSR.Visible = True
                radBPS.Visible = True
                radPresSR.Visible = True
                lblPSteroid.Visible = True
                lblSSR.Visible = True
                lblPresSR.Visible = True
                lblBPS.Visible = True
                hypSteroid.Visible = True
                hypSSR.Visible = True
                hypPresSR.Visible = True
                hypBPS.Visible = True
                'DropDownList7.BackColor = Drawing.Color.YellowGreen
                lblDateDiag.Text = "Date of Diagnosis"
            End If
        End If



        If DropDownList7.SelectedValue = 2 Then
            'DropDownList7.BackColor = Drawing.Color.Yellow
        End If

    End Sub


    Protected Sub UpdateButton_Click(ByVal sender As Object, ByVal e As EventArgs) Handles UpdateButton.Click

        PageSave()

    End Sub


    Sub PageSave()

        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        'Dim strSQL As String = "UPDATE [tbl_Diagnosis] SET [RADAR_NO] = @RADAR_NO, [DATE_DIAG] = @DATE_DIAG, [DATE_ESRF] = @DATE_ESRF, [DIAG] = @DIAG, [DIAG_TXT] = @DIAG_TXT, [GENE_MUT] = @GENE_MUT, [GENE_MUT_TEXT] = @GENE_MUT_TEXT, [CLIN_PRES] = @CLIN_PRES, [CLIN_PRES_B] = @CLIN_PRES_B,[PREPUB_DIAG] = @PREPUB_DIAG, [BX_PROVEN_DIAG] = @BX_PROVEN_DIAG, [HEIGHT_FIRST_VISIT] = @HEIGHT_FIRST_VISIT, [AGE_AT_DIAG] = @AGE_AT_DIAG, [REL2] = @REL2, [REL1] = @REL1, [FAM_HIST] = @FAM_HIST, [CONSANGUINITY] = @CONSANGUINITY, [DATE_ONSET_RENALDIS] = @DATE_ONSET_RENALDIS, [KARYOTYPE] = @KARYOTYPE, [SIG_DIAG2] = @SIG_DIAG2, [SIG_DIAG1] = @SIG_DIAG1, [REL6] = @REL6, [REL5] = @REL5, [REL4] = @REL4, [REL3] = @REL3, [STEROID_RESIST] = @STEROID_RESIST, [REL1_RADAR] = @REL1_RADAR,  [REL2_RADAR] = @REL2_RADAR, [REL3_RADAR] = @REL3_RADAR, [REL4_RADAR] = @REL4_RADAR, [REL5_RADAR] = @REL5_RADAR, [REL6_RADAR] = @REL6_RADAR WHERE [dID] = @dID"
        Dim strSQL As String = "UPDATE [tbl_Diagnosis] SET [RADAR_NO] = @RADAR_NO, [DATE_DIAG] = @DATE_DIAG, [DIAG] = @DIAG, [DIAG_TXT] = @DIAG_TXT, [AGE_AT_DIAG] = @AGE_AT_DIAG, [HEIGHT_FIRST_VISIT] = @HEIGHT_FIRST_VISIT, [BX_PROVEN_DIAG] = @BX_PROVEN_DIAG, [PREPUB_DIAG] = @PREPUB_DIAG, [CLIN_PRES] = @CLIN_PRES, [CLIN_PRES_B] = @CLIN_PRES_B, [GENE_MUT] = @GENE_MUT, [GENE_MUT_TEXT] = @GENE_MUT_TEXT, [KARYOTYPE] = @KARYOTYPE, [DATE_ONSET_RENALDIS] = @DATE_ONSET_RENALDIS, [CONSANGUINITY] = @CONSANGUINITY, [FAM_HIST] = @FAM_HIST, [REL1] = @REL1, [REL1_RADAR] = @REL1_RADAR, [REL2] = @REL2, [REL2_RADAR] = @REL2_RADAR, [REL3] = @REL3, [REL3_RADAR] = @REL3_RADAR, [REL4] = @REL4, [REL4_RADAR] = @REL4_RADAR, [REL5] = @REL5, [REL5_RADAR] = @REL5_RADAR, [REL6] = @REL6, [REL6_RADAR] = @REL6_RADAR, [SIG_DIAG1] = @SIG_DIAG1, [SIG_DIAG2] = @SIG_DIAG2, [STEROID_RESIST] = @STEROID_RESIST, [DATE_ESRF] = @DATE_ESRF, [MUTATION_1] = @MUTATION_1, [MUTATION_1S] = @MUTATION_1S, [MUTATION_2] = @MUTATION_2, [MUTATION_2S] = @MUTATION_2S, [MUTATION_3] = @MUTATION_3, [MUTATION_3S] = @MUTATION_3S, [MUTATION_4] = @MUTATION_4, [MUTATION_4S] = @MUTATION_4S, [MUTATION_5] = @MUTATION_5, [MUTATION_5S] = @MUTATION_5S, [MUTATION_6] = @MUTATION_6, [MUTATION_6S] = @MUTATION_6S, [MUTATION_7] = @MUTATION_7, [MUTATION_7S] = @MUTATION_7S, [MUTATION_8] = @MUTATION_8, [MUTATION_8S] = @MUTATION_8S, [MUTATION_9] = @MUTATION_9, [MUTATION_9S] = @MUTATION_9S WHERE [dID] = @dID"

        Dim objConnect As New System.Data.SqlClient.SqlConnection(strConnect)
        Dim objCommand As New System.Data.SqlClient.SqlCommand(strSQL, objConnect)

        Dim dtDOB As Date = CDate(DOBTextbox.Text)
        'Dim dtDiag As Date = CDate(txtDateDiag.Text)

        Dim intAge As Integer

        If txtDateDiag.Text = "" Then
            'intAge = 
        Else
            intAge = GetAge(dtDOB, CDate(txtDateDiag.Text))
        End If

        If IsDate(DATE_ONSET_RENALDISTextbox.Text) Then
            If CDate(DATE_ONSET_RENALDISTextbox.Text) >= CDate(txtDateDiag.Text) Then
                lblDateWarn.Text = "> Date of Diagnosis"
                Exit Sub
            Else
                lblDateWarn.Text = ""
            End If
        End If



        With objCommand.Parameters

            .Add(New System.Data.SqlClient.SqlParameter("@dID", lbldID.Text))
            .Add(New System.Data.SqlClient.SqlParameter("@RADAR_NO", RADAR_NOTextbox.Text))



            If DropDownList7.SelectedValue = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@DIAG", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@DIAG", DropDownList7.SelectedValue))
            End If

            If DIAG_TXTTextbox.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@DIAG_TXT", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@DIAG_TXT", DIAG_TXTTextbox.Text))
            End If

            If txtDateDiag.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@DATE_DIAG", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@DATE_DIAG", CDate(txtDateDiag.Text)))
            End If


            If txtDATE_ESRF.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@DATE_ESRF", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@DATE_ESRF", CDate(txtDATE_ESRF.Text)))
            End If

            'If AGE_AT_DIAGTextbox.Text = "" Then
            '.Add(New System.Data.SqlClient.SqlParameter("@AGE_AT_DIAG", DBNull.Value))
            'Else
            .Add(New System.Data.SqlClient.SqlParameter("@AGE_AT_DIAG", intAge))
            'End If

            .Add(New System.Data.SqlClient.SqlParameter("@PREPUB_DIAG", CheckBox10.Checked))

            If TextBox11.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@HEIGHT_FIRST_VISIT", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@HEIGHT_FIRST_VISIT", TextBox11.Text))
            End If

            If DropDownList8.SelectedValue = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@CLIN_PRES", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@CLIN_PRES", DropDownList8.SelectedValue))
            End If

            If DropDownList14.SelectedValue = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@CLIN_PRES_B", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@CLIN_PRES_B", DropDownList14.SelectedValue))
            End If

            If DATE_ONSET_RENALDISTextbox.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@DATE_ONSET_RENALDIS", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@DATE_ONSET_RENALDIS", CDate(DATE_ONSET_RENALDISTextbox.Text)))
            End If

            '========================================
            If radPrimarySteroid.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@STEROID_RESIST", 1))
            ElseIf radSSR.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@STEROID_RESIST", 2))
            ElseIf radPresSR.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@STEROID_RESIST", 3))
            ElseIf radBPS.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@STEROID_RESIST", 4))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@STEROID_RESIST", DBNull.Value))
            End If
            '==========================================

            If SIG_DIAG1Textbox.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@SIG_DIAG1", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@SIG_DIAG1", SIG_DIAG1Textbox.Text))
            End If

            If SIG_DIAG2Textbox.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@SIG_DIAG2", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@SIG_DIAG2", SIG_DIAG2Textbox.Text))
            End If

            If DropDownList11.SelectedValue = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@BX_PROVEN_DIAG", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@BX_PROVEN_DIAG", DropDownList11.SelectedValue))
            End If

            'If DropDownList10.SelectedValue = "" Then
            .Add(New System.Data.SqlClient.SqlParameter("@GENE_MUT", DBNull.Value))
            'Else
            '    .Add(New System.Data.SqlClient.SqlParameter("@GENE_MUT", DropDownList10.SelectedValue))
            'End If

            If txtGENE_MUT_TEXT.Text = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@GENE_MUT_TEXT", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@GENE_MUT_TEXT", txtGENE_MUT_TEXT.Text))
            End If

            If DropDownList9.SelectedValue = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@KARYOTYPE", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@KARYOTYPE", DropDownList9.SelectedValue))
            End If

            If DropDownList12.SelectedValue = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@CONSANGUINITY", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@CONSANGUINITY", DropDownList12.SelectedValue))
            End If

            If DropDownList13.SelectedValue = "" Then
                .Add(New System.Data.SqlClient.SqlParameter("@FAM_HIST", DBNull.Value))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@FAM_HIST", DropDownList13.SelectedValue))
            End If


            If (DropDownList13.SelectedValue = 99) Or (DropDownList13.SelectedValue = 9) Or (DropDownList13.SelectedValue = 0) Then

                .Add(New System.Data.SqlClient.SqlParameter("@REL1", DBNull.Value))
                .Add(New System.Data.SqlClient.SqlParameter("@REL2", DBNull.Value))
                .Add(New System.Data.SqlClient.SqlParameter("@REL3", DBNull.Value))
                .Add(New System.Data.SqlClient.SqlParameter("@REL4", DBNull.Value))
                .Add(New System.Data.SqlClient.SqlParameter("@REL5", DBNull.Value))
                .Add(New System.Data.SqlClient.SqlParameter("@REL6", DBNull.Value))
                .Add(New System.Data.SqlClient.SqlParameter("@REL1_RADAR", DBNull.Value))
                .Add(New System.Data.SqlClient.SqlParameter("@REL2_RADAR", DBNull.Value))
                .Add(New System.Data.SqlClient.SqlParameter("@REL3_RADAR", DBNull.Value))
                .Add(New System.Data.SqlClient.SqlParameter("@REL4_RADAR", DBNull.Value))
                .Add(New System.Data.SqlClient.SqlParameter("@REL5_RADAR", DBNull.Value))
                .Add(New System.Data.SqlClient.SqlParameter("@REL6_RADAR", DBNull.Value))

            ElseIf DropDownList13.SelectedValue = 1 Then

                If DropDownList1.SelectedValue = "" Then
                    .Add(New System.Data.SqlClient.SqlParameter("@REL1", DBNull.Value))
                    .Add(New System.Data.SqlClient.SqlParameter("@REL1_RADAR", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@REL1", DropDownList1.SelectedValue))
                    .Add(New System.Data.SqlClient.SqlParameter("@REL1_RADAR", CommonClass.chkNullSave(txtREL1_RADAR.Text)))
                End If


                If DropDownList2.SelectedValue = "" Then
                    .Add(New System.Data.SqlClient.SqlParameter("@REL2", DBNull.Value))
                    .Add(New System.Data.SqlClient.SqlParameter("@REL2_RADAR", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@REL2", DropDownList2.SelectedValue))
                    .Add(New System.Data.SqlClient.SqlParameter("@REL2_RADAR", CommonClass.chkNullSave(txtREL2_RADAR.Text)))
                End If


                If DropDownList3.SelectedValue = "" Then
                    .Add(New System.Data.SqlClient.SqlParameter("@REL3", DBNull.Value))
                    .Add(New System.Data.SqlClient.SqlParameter("@REL3_RADAR", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@REL3", DropDownList3.SelectedValue))
                    .Add(New System.Data.SqlClient.SqlParameter("@REL3_RADAR", CommonClass.chkNullSave(txtREL3_RADAR.Text)))
                End If


                If DropDownList4.SelectedValue = "" Then
                    .Add(New System.Data.SqlClient.SqlParameter("@REL4", DBNull.Value))
                    .Add(New System.Data.SqlClient.SqlParameter("@REL4_RADAR", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@REL4", DropDownList4.SelectedValue))
                    .Add(New System.Data.SqlClient.SqlParameter("@REL4_RADAR", CommonClass.chkNullSave(txtREL4_RADAR.Text)))
                End If


                If DropDownList5.SelectedValue = "" Then
                    .Add(New System.Data.SqlClient.SqlParameter("@REL5", DBNull.Value))
                    .Add(New System.Data.SqlClient.SqlParameter("@REL5_RADAR", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@REL5", DropDownList5.SelectedValue))
                    .Add(New System.Data.SqlClient.SqlParameter("@REL5_RADAR", CommonClass.chkNullSave(txtREL5_RADAR.Text)))
                End If


                If DropDownList6.SelectedValue = "" Then
                    .Add(New System.Data.SqlClient.SqlParameter("@REL6", DBNull.Value))
                    .Add(New System.Data.SqlClient.SqlParameter("@REL6_RADAR", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@REL6", DropDownList6.SelectedValue))
                    .Add(New System.Data.SqlClient.SqlParameter("@REL6_RADAR", CommonClass.chkNullSave(txtREL6_RADAR.Text)))
                End If

            End If


            '=================================================================
            '# Revised Gene Mutation handling. Aug-2010

            If radACTN4Y.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_1", True))
            ElseIf radACTN4N.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_1", False))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_1", DBNull.Value))
            End If

            If radACTN4S.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_1S", True))
            ElseIf radACTN4NS.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_1S", False))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_1S", DBNull.Value))
            End If

            '----------------

            If radCD2APYes.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_2", True))
            ElseIf radCD2APNo.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_2", False))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_2", DBNull.Value))
            End If

            If radCD2APS.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_2S", True))
            ElseIf radCD2APNS.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_2S", False))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_2S", DBNull.Value))
            End If

            '-----------------

            If radLAMB2Yes.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_3", True))
            ElseIf radLAMB2No.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_3", False))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_3", DBNull.Value))
            End If

            If radLAMBS.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_3S", True))
            ElseIf radLAMBNS.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_3S", False))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_3S", DBNull.Value))
            End If

            '----------------

            If radNPHS1Y.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_4", True))
            ElseIf radNPHS1N.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_4", False))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_4", DBNull.Value))
            End If

            If radNPHS1S.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_4S", True))
            ElseIf radNPHS1NS.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_4S", False))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_4S", DBNull.Value))
            End If

            '---------------

            If radNPHS2Y.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_5", True))
            ElseIf radNPHS2N.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_5", False))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_5", DBNull.Value))
            End If

            If radNPHS2S.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_5S", True))
            ElseIf radNPHS2NS.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_5S", False))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_5S", DBNull.Value))
            End If

            '-----------------

            If radNPHS3Y.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_6", True))
            ElseIf radNPHS3N.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_6", False))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_6", DBNull.Value))
            End If

            If radNPHS3S.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_6S", True))
            ElseIf radNPHS3NS.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_6S", False))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_6S", DBNull.Value))
            End If

            '-----------------

            If radTRPC6Y.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_7", True))
            ElseIf radTRPC6N.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_7", False))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_7", DBNull.Value))
            End If

            If radTRPC6S.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_7S", True))
            ElseIf radTRPC6NS.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_7S", False))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_7S", DBNull.Value))
            End If

            '-----------------

            If radWilmY.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_8", True))
            ElseIf radWilmN.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_8", False))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_8", DBNull.Value))
            End If

            If radWilmS.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_8S", True))
            ElseIf radWilmNS.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_8S", False))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_8S", DBNull.Value))
            End If

            '-----------------

            If radOtherY.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_9", True))
            ElseIf radOtherN.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_9", False))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_9", DBNull.Value))
            End If

            If radOtherS.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_9S", True))
            ElseIf radOtherNS.Checked Then
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_9S", False))
            Else
                .Add(New System.Data.SqlClient.SqlParameter("@MUTATION_9S", DBNull.Value))
            End If

            '-----------------

            '=================================================================

        End With

        Try
            objConnect.Open()
            objCommand.ExecuteNonQuery()

        Catch objError As Exception

            lblDebug.Text = "An error occurred: '" & objError.Message & "'"
            objConnect.Close()
            Exit Sub

        Finally
            objConnect.Close()
        End Try

        lblUpdate.Text = "<strong>Update successful:</strong>&nbsp;&nbsp;" & Now().ToShortTimeString
        lblUpdate2.Text = "<strong>Update successful:</strong>&nbsp;&nbsp;" & Now().ToShortTimeString

        'lblUpdate.Text = strSQL

        pagefill()

    End Sub

    'Protected Sub ASPxDateEdit1_DateChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles ASPxDateEdit1.DateChanged

    '    'Dim strAge As String = (ASPxDateEdit1.Date - CDate(DOBTextbox.Text)).TotalDays
    '    'TextBox7.Text = strAge
    '    'Dim intAge As Integer = CInt(strAge) / 365
    '    'TextBox9.Text = intAge.ToString

    '    Dim dtDOB As Date = CDate(DOBTextbox.Text)
    '    Dim dtDiag As Date = ASPxDateEdit1.Date

    '    'TextBox7.Text = ts.ToString

    '    AGE_AT_DIAGTextbox.Text = GetAge(dtDOB, dtDiag)

    'End Sub


    Function GetAge(ByVal dtDOB As Date, ByVal dtDiag As Date) As Int16

        Dim intLeapYear As Int16 = 0
        Dim i As Int16 = 0

        For i = (dtDiag.Year) To (dtDOB.Year) Step -1  'this isn't giving the right answer
            If i Mod 4 = 0 Then
            Else
                intLeapYear = intLeapYear + 1
            End If
        Next

        Dim ts As Int16 = CInt((dtDiag - dtDOB).TotalDays)

        Dim intAge As Integer = (ts - intLeapYear) \ 365

        GetAge = intAge


    End Function

    Function chkClinical(ByVal intRadar As Integer) As Boolean

        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim strSQL As String = "SELECT DATE_CLIN_PIC FROM tbl_ClinicalData WHERE [RADAR_NO] = " & intRadar
        Dim objConnect As New System.Data.SqlClient.SqlConnection(strConnect)
        Dim objCommand As New System.Data.SqlClient.SqlCommand(strSQL, objConnect)

        Try

            Dim objDataReader As SqlDataReader
            objConnect.Open()
            objDataReader = objCommand.ExecuteReader()


            If objDataReader.Read() Then
                If objDataReader("DATE_CLIN_PIC") Is DBNull.Value Then
                    chkClinical = False
                Else
                    chkClinical = True
                End If
            Else
                chkClinical = False
            End If

        Catch objError As Exception

            lblDebug.Text = String.Format("An error occurred: '{0}'{1}", objError.Message, strSQL)
            chkClinical = False
        Finally
            objConnect.Close()


        End Try


    End Function

    'Protected Sub DropDownList10_SelectedIndexChanged(ByVal sender As Object, ByVal e As EventArgs) Handles DropDownList10.SelectedIndexChanged
    '    If DropDownList10.SelectedIndex > 0 Then
    '        rowGeneMutation.Visible = True
    '    Else
    '        rowGeneMutation.Visible = False
    '    End If

    'End Sub



    Protected Sub lnkDemographics_Click(ByVal sender As Object, ByVal e As EventArgs) Handles lnkDemographics.Click
        PageSave()
        Response.Redirect("demographics.aspx")
    End Sub

    Protected Sub lnkLabResults_Click(ByVal sender As Object, ByVal e As EventArgs) Handles lnkLabResults.Click
        PageSave()
        Response.Redirect("first-clinical.aspx")
    End Sub

    Protected Sub lnk3Months_Click(ByVal sender As Object, ByVal e As EventArgs) Handles lnk3Months.Click
        PageSave()
        Response.Redirect("followup-clinical.aspx")
    End Sub

    Protected Sub DropDownList13_SelectedIndexChanged(ByVal sender As Object, ByVal e As EventArgs) Handles DropDownList13.SelectedIndexChanged
        Select Case DropDownList13.SelectedValue

            Case 0

                DropDownList1.Enabled = False
                DropDownList2.Enabled = False
                DropDownList3.Enabled = False
                DropDownList4.Enabled = False
                DropDownList5.Enabled = False
                DropDownList6.Enabled = False
                DropDownList1.Visible = False
                DropDownList2.Visible = False
                DropDownList3.Visible = False
                DropDownList4.Visible = False
                DropDownList5.Visible = False
                DropDownList6.Visible = False
                lblFirstRel.Visible = False
                lblSecondRel.Visible = False
                lblThirdRel.Visible = False
                lblFourthRel.Visible = False
                lblFifthRel.Visible = False
                lblSixthRel.Visible = False
                txtREL1_RADAR.Visible = False
                txtREL2_RADAR.Visible = False
                txtREL3_RADAR.Visible = False
                txtREL4_RADAR.Visible = False
                txtREL5_RADAR.Visible = False
                txtREL6_RADAR.Visible = False
                lblRelRADAR.Visible = False
                'rowREL1.Visible = False
                'rowREL2.Visible = False
                'rowREL3.Visible = False
                'rowREL4.Visible = False
                'rowREL5.Visible = False
                'rowREL6.Visible = False
            Case 1
                DropDownList1.Enabled = True
                DropDownList2.Enabled = True
                DropDownList3.Enabled = True
                DropDownList4.Enabled = True
                DropDownList5.Enabled = True
                DropDownList6.Enabled = True
                DropDownList1.Visible = True
                DropDownList2.Visible = True
                DropDownList3.Visible = True
                DropDownList4.Visible = True
                DropDownList5.Visible = True
                DropDownList6.Visible = True
                lblFirstRel.Visible = True
                lblSecondRel.Visible = True
                lblThirdRel.Visible = True
                lblFourthRel.Visible = True
                lblFifthRel.Visible = True
                lblSixthRel.Visible = True
                txtREL1_RADAR.Visible = True
                txtREL2_RADAR.Visible = True
                txtREL3_RADAR.Visible = True
                txtREL4_RADAR.Visible = True
                txtREL5_RADAR.Visible = True
                txtREL6_RADAR.Visible = True
                lblRelRADAR.Visible = True
                'rowREL1.Visible = True
                'rowREL2.Visible = True
                'rowREL3.Visible = True
                'rowREL4.Visible = True
                'rowREL5.Visible = True
                'rowREL6.Visible = True
            Case Else

                DropDownList1.Enabled = False
                DropDownList2.Enabled = False
                DropDownList3.Enabled = False
                DropDownList4.Enabled = False
                DropDownList5.Enabled = False
                DropDownList6.Enabled = False
                DropDownList1.Visible = False
                DropDownList2.Visible = False
                DropDownList3.Visible = False
                DropDownList4.Visible = False
                DropDownList5.Visible = False
                DropDownList6.Visible = False
                lblFirstRel.Visible = False
                lblSecondRel.Visible = False
                lblThirdRel.Visible = False
                lblFourthRel.Visible = False
                lblFifthRel.Visible = False
                lblSixthRel.Visible = False
                txtREL1_RADAR.Visible = False
                txtREL2_RADAR.Visible = False
                txtREL3_RADAR.Visible = False
                txtREL4_RADAR.Visible = False
                txtREL5_RADAR.Visible = False
                txtREL6_RADAR.Visible = False
                lblRelRADAR.Visible = False
                'rowREL1.Visible = False
                'rowREL2.Visible = False
                'rowREL3.Visible = False
                'rowREL4.Visible = False
                'rowREL5.Visible = False
                'rowREL6.Visible = False

        End Select
    End Sub

    Protected Sub DropDownList8_SelectedIndexChanged(ByVal sender As Object, ByVal e As EventArgs) Handles DropDownList8.SelectedIndexChanged
        'Dim intIndex As Integer = DropDownList8.SelectedIndex
        'DropDownList14.Items.RemoveAt(DropDownList14.Items.IndexOf(DropDownList14.Items.FindByValue(intIndex)))
        'DropDownList14.Enabled = True
        If DropDownList8.SelectedValue = DropDownList14.SelectedValue Then
            lblWarnA.Visible = True
            DropDownList8.SelectedIndex = 0
        Else
            lblWarnA.Visible = False
        End If
    End Sub

    Protected Sub DropDownList14_SelectedIndexChanged(ByVal sender As Object, ByVal e As EventArgs) Handles DropDownList14.SelectedIndexChanged
        'Dim intIndex As Integer = DropDownList14.SelectedIndex
        'DropDownList8.Items.RemoveAt(DropDownList8.Items.IndexOf(DropDownList8.Items.FindByValue(intIndex)))
        If DropDownList14.SelectedValue = DropDownList8.SelectedValue Then
            lblWarnB.Visible = True
            DropDownList14.SelectedIndex = 0
        Else
            lblWarnB.Visible = False
        End If

    End Sub



    Protected Sub radOtherY_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radOtherY.CheckedChanged
        If radOtherY.Checked Then
            rowGeneMutation.Visible = True
            radOtherNS.Enabled = True
            radOtherS.Enabled = True
        Else
            rowGeneMutation.Visible = False
        End If
    End Sub

    Protected Sub radOtherN_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radOtherN.CheckedChanged
        If radOtherN.Checked Then
            rowGeneMutation.Visible = False
            radOtherNS.Enabled = False
            radOtherS.Enabled = False
            radOtherNS.Checked = False
            radOtherS.Checked = False
        Else
            rowGeneMutation.Visible = True
        End If
    End Sub

    Protected Sub radWilmY_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radWilmY.CheckedChanged
        If radWilmY.Checked = True Then
            radWilmS.Enabled = True
            radWilmNS.Enabled = True
        End If
    End Sub

    Protected Sub radWilmN_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radWilmN.CheckedChanged
        If radWilmN.Checked Then
            radWilmS.Enabled = False
            radWilmS.Checked = False
            radWilmNS.Enabled = False
            radWilmNS.Checked = False
        End If
    End Sub

    Protected Sub radTRPC6Y_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radTRPC6Y.CheckedChanged
        If radTRPC6Y.Checked = True Then
            radTRPC6S.Enabled = True
            radTRPC6NS.Enabled = True
        End If
    End Sub

    Protected Sub radTRPC6N_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radTRPC6N.CheckedChanged
        If radTRPC6N.Checked = True Then
            radTRPC6S.Enabled = False
            radTRPC6S.Checked = False
            radTRPC6NS.Enabled = False
            radTRPC6NS.Checked = False
        End If
    End Sub

    Protected Sub radNPHS3Y_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radNPHS3Y.CheckedChanged
        If radNPHS3Y.Checked = True Then
            radNPHS3S.Enabled = True
            radNPHS3NS.Enabled = True
        End If
    End Sub

    Protected Sub radNPHS3N_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radNPHS3N.CheckedChanged
        If radNPHS3N.Checked = True Then
            radNPHS3S.Enabled = False
            radNPHS3S.Checked = False
            radNPHS3NS.Enabled = False
            radNPHS2NS.Checked = False
        End If
    End Sub

    Protected Sub radNPHS2Y_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radNPHS2Y.CheckedChanged
        If radNPHS2Y.Checked = True Then
            radNPHS2S.Enabled = True
            radNPHS2NS.Enabled = True
        End If
    End Sub

    Protected Sub radNPHS2N_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radNPHS2N.CheckedChanged
        If radNPHS2N.Checked = True Then
            radNPHS2S.Enabled = False
            radNPHS2S.Checked = False
            radNPHS2NS.Enabled = False
            radNPHS2NS.Checked = False
        End If
    End Sub

    Protected Sub radNPHS1Y_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radNPHS1Y.CheckedChanged
        If radNPHS1Y.Checked = True Then
            radNPHS1S.Enabled = True
            radNPHS1NS.Enabled = True
        End If
    End Sub

    Protected Sub radNPHS1N_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radNPHS1N.CheckedChanged
        If radNPHS1N.Checked = True Then
            radNPHS1S.Enabled = False
            radNPHS1S.Checked = False
            radNPHS1NS.Enabled = False
            radNPHS1NS.Checked = False
        End If
    End Sub

    Protected Sub radLAMB2Yes_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radLAMB2Yes.CheckedChanged
        If radLAMB2Yes.Checked = True Then
            radLAMBS.Enabled = True
            radLAMBNS.Enabled = True
        End If
    End Sub

    Protected Sub radLAMB2No_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radLAMB2No.CheckedChanged
        If radLAMB2No.Checked = True Then
            radLAMBS.Enabled = False
            radLAMBS.Checked = False
            radLAMBNS.Enabled = False
            radLAMBNS.Checked = False
        End If
    End Sub

    Protected Sub radAD2APYes_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radCD2APYes.CheckedChanged
        If radCD2APYes.Checked = True Then
            radCD2APS.Enabled = True
            radCD2APNS.Enabled = True
        End If
    End Sub

    Protected Sub radAD2APNo_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radCD2APNo.CheckedChanged
        If radCD2APNo.Checked = True Then
            radCD2APS.Enabled = False
            radCD2APS.Checked = False
            radCD2APNS.Enabled = False
            radCD2APNS.Checked = False
        End If
    End Sub

    
    Protected Sub radACTN4Y_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radACTN4Y.CheckedChanged
        If radACTN4Y.Checked = True Then
            radACTN4S.Enabled = True
            radACTN4NS.Enabled = True
        End If
    End Sub

    Protected Sub radACTN4N_CheckedChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles radACTN4N.CheckedChanged
        If radACTN4N.Checked = True Then
            radACTN4S.Enabled = False
            radACTN4S.Checked = False
            radACTN4NS.Enabled = False
            radACTN4NS.Checked = False
        End If
    End Sub

    Protected Sub btnUpdate2_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnUpdate2.Click
        PageSave()
    End Sub
End Class
