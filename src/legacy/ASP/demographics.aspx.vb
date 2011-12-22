Imports System.Data
Imports System.Data.OleDb
Imports System.Data.SqlClient
Imports System.Security.Cryptography
Imports System.IO
Partial Class demographics
    Inherits System.Web.UI.Page

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load

        If Session("blnAuthenticated") = False Then
            Response.Redirect("default.aspx")
        End If

        If Session("uType") = "p" Then
            btnEdit.Visible = False
            CommonClass.DisableControls(mainForm)
            rowConsent.Visible = False
            rowConsent2.Visible = False
            rowInfo2.Visible = False
            rowPatientInfo.Visible = False
        Else
            lnkRelapse.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnk3Months.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkLabResults.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkDiagnosis.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkPathology.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkHospital.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkTimelines.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkTimelines.Visible = False
        End If

        txtDateToday.Text = Format(DateTime.Today, "dd-MM-yyyy")


        If Not IsPostBack Then


            Select Case Session("mode")

                Case 1 ' entry
                    lnk3Months.Enabled = False
                    lnkDiagnosis.Enabled = False
                    lnkLabResults.Enabled = False
                    lnkRelapse.Enabled = False
                    btnSubmit.Text = "Add this Patient"
                    RADAR_NOTextBox.Text = "Auto allocated"
                    DATE_REGTextBox.Text = "Today's date"
                    btnSubmit.Visible = True
                    btnEdit.Visible = False
                    lnkPathology.Enabled = False
                    lnkHospital.Enabled = False
                    lnkTimelines.Enabled = False
                    DropDownList2.SelectedValue = Session("unitID")
                    SqlDataSource1.SelectCommand = "SELECT cID, (cSNAME + '  ' + cFNAME) AS NAME, cFNAME, cSNAME FROM tbl_Consultants WHERE (cCentre = " & Session("unitID") & ") ORDER BY cSNAME"

                    Session("diag") = 0

                   

                Case 2 'edit
                    btnSubmit.Visible = False

                    If Session("uType") = "p" Then
                        btnEdit.Visible = False
                    Else
                        btnEdit.Visible = True
                    End If

                    lnk3Months.Enabled = True
                    lnkDiagnosis.Enabled = True
                    lnkLabResults.Enabled = True
                    lnkRelapse.Enabled = True
                    lblNew.Text = "&nbsp;Demographics"
                    pagefill()

            End Select

            If IsPostBack Then

                Select Case Session("mode")


                    Case 2 'edit
                        btnSubmit.Visible = False
                        btnEdit.Visible = True
                        lnk3Months.Enabled = True
                        lnkDiagnosis.Enabled = True
                        lnkLabResults.Enabled = True
                        lblNew.Text = "&nbsp;Demographics"



                End Select

            End If

        End If

        lblpage.text = "1"

    End Sub


    Protected Sub btnSubmit_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnSubmit.Click

        If checkExists(FNAMETextBox.Text, SNAMETextBox.Text) = True Then
            btnSubmit.Enabled = False
            lblUpdate.Text = "A patient with this name already exists, do you wish to continue?"
            btnContinue.Visible = True
        Else
            saveNew()
        End If

    End Sub

    Sub saveNew()



        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim strSQL As String = "INSERT INTO [tbl_Demographics] ([RR_NO], [DATE_REG], [NHS_NO], [HOSP_NO], [UKT_NO], [CHI_NO], [SNAME], [SNAME_ALIAS], [FNAME], [DOB], [AGE], [SEX], [ETHNIC_GP], [ADD1], [ADD2], [ADD3], [ADD4], [POSTCODE], [POSTCODE_OLD], [CONSENT], [DATE_BAPN_REG], [CONS_NEPH], [RENAL_UNIT], [STATUS]) VALUES (@RR_NO, @DATE_REG, @NHS_NO, @HOSP_NO, @UKT_NO, @CHI_NO, @SNAME, @SNAME_ALIAS, @FNAME, @DOB, @AGE, @SEX, @ETHNIC_GP, @ADD1, @ADD2, @ADD3, @ADD4, @POSTCODE, @POSTCODE_OLD, @CONSENT, @DATE_BAPN_REG, @CONS_NEPH, @RENAL_UNIT, @STATUS); "
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        Dim TDES As New TripleDES()

        'Dim strFNAME As String = FNAMETextBox.Text
        'Dim strSNAME As String = SNAMETextBox.Text
        'Dim dtDOB As String = (DOBTextBox.Text)
        'Dim strNHSNO As String = NHS_NOTextBox.Text

        Dim strSNAMEALIAS As String

        If SNAME_ALIASTextBox.Text = "" Then
            strSNAMEALIAS = "-"  ' need to do this to handle encryption
        Else
            strSNAMEALIAS = SNAME_ALIASTextBox.Text
        End If

        Dim strNHS_NO As String

        If NHS_NOTextBox.Text = "" Then
            strNHS_NO = "-"
        Else
            strNHS_NO = NHS_NOTextBox.Text
        End If

        Dim strADDR1 As String

        If ADD1TextBox.Text = "" Then
            strADDR1 = "-"
        Else
            strADDR1 = ADD1TextBox.Text
        End If

        Dim strADDR2 As String

        If ADD2TextBox.Text = "" Then
            strADDR2 = "-"
        Else
            strADDR2 = ADD2TextBox.Text
        End If


        Dim strADDR3 As String

        If ADD3TextBox.Text = "" Then
            strADDR3 = "-"
        Else
            strADDR3 = ADD3TextBox.Text
        End If


        Dim strADDR4 As String

        If ADD4TextBox.Text = "" Then
            strADDR4 = "-"
        Else
            strADDR4 = ADD4TextBox.Text
        End If

        Dim strPOSTCODE As String

        If POSTCODETextBox.Text = "" Then
            strPOSTCODE = "-"
        Else
            strPOSTCODE = POSTCODETextBox.Text
        End If

        Dim strPOSTCODE_OLD As String

        If POSTCODE_OLDTextBox.Text = "" Then
            strPOSTCODE_OLD = "-"
        Else
            strPOSTCODE_OLD = POSTCODE_OLDTextBox.Text
        End If

        Dim bytFNAME() As Byte = TDES.Encrypt(FNAMETextBox.Text)
        Dim bytSNAME() As Byte = TDES.Encrypt(SNAMETextBox.Text)
        Dim bytDOB() As Byte = TDES.Encrypt(DOBTextBox.Text)
        Dim bytNHS_NO() As Byte = TDES.Encrypt(strNHS_NO)
        Dim bytSNAME_ALIAS() As Byte = TDES.Encrypt(strSNAMEALIAS)

        Dim bytADDR1() As Byte = TDES.Encrypt(strADDR1)
        Dim bytADDR2() As Byte = TDES.Encrypt(strADDR2)
        Dim bytADDR3() As Byte = TDES.Encrypt(strADDR3)
        Dim bytADDR4() As Byte = TDES.Encrypt(strADDR4)
        Dim bytPOSTCODE() As Byte = TDES.Encrypt(strPOSTCODE)
        Dim bytPOSTCODE_OLD() As Byte = TDES.Encrypt(strPOSTCODE_OLD)





        With objCommand.Parameters

            .Add(New SqlParameter("@DATE_REG", Now()))

            If RR_NOTextBox.Text = "" Then
                .Add(New SqlParameter("@RR_NO", DBNull.Value))
            Else
                .Add(New SqlParameter("@RR_NO", RR_NOTextBox.Text))
            End If

            .Add(New SqlParameter("@NHS_NO", bytNHS_NO))

            If HOSP_NOTextBox.Text = "" Then
                .Add(New SqlParameter("@HOSP_NO", TDES.Encrypt("-")))
            Else
                .Add(New SqlParameter("@HOSP_NO", TDES.Encrypt(HOSP_NOTextBox.Text)))
            End If

            If UKT_NOTextBox.Text = "" Then
                .Add(New SqlParameter("@UKT_NO", DBNull.Value))
            Else
                .Add(New SqlParameter("@UKT_NO", UKT_NOTextBox.Text))
            End If

            If CHI_NOTextBox.Text = "" Then
                .Add(New SqlParameter("@CHI_NO", DBNull.Value))
            Else
                .Add(New SqlParameter("@CHI_NO", CHI_NOTextBox.Text))
            End If

            If SNAMETextBox.Text = "" Then
                .Add(New SqlParameter("@SNAME", DBNull.Value))
            Else
                .Add(New SqlParameter("@SNAME", bytSNAME))
            End If


            If FNAMETextBox.Text = "" Then
                .Add(New SqlParameter("@FNAME", DBNull.Value))
            Else
                .Add(New SqlParameter("@FNAME", bytFNAME))
            End If

            If DOBTextBox.Text = "" Then
                .Add(New SqlParameter("@DOB", DBNull.Value))
            Else
                .Add(New SqlParameter("@DOB", bytDOB))
            End If

            'If ASPxDateEdit1.Date = DateTime.MinValue Then
            .Add(New SqlParameter("@AGE", DBNull.Value))
            'Else
            '.Add(New System.Data.SqlClient.SqlParameter("@AGE", ))
            'End If

            .Add(New SqlParameter("@SEX", DropDownList1.SelectedValue))

            .Add(New SqlParameter("@ETHNIC_GP", DropDownList3.SelectedValue))

            .Add(New SqlParameter("@SNAME_ALIAS", bytSNAME_ALIAS))
            .Add(New SqlParameter("@ADD1", bytADDR1))
            .Add(New SqlParameter("@ADD2", bytADDR2))
            .Add(New SqlParameter("@ADD3", bytADDR3))
            .Add(New SqlParameter("@ADD4", bytADDR4))
            .Add(New SqlParameter("@POSTCODE", bytPOSTCODE))
            .Add(New SqlParameter("@POSTCODE_OLD", bytPOSTCODE_OLD))


            .Add(New SqlParameter("@CONSENT", CONSENTCheckBox.Checked))

            .Add(New SqlParameter("@DATE_BAPN_REG", DBNull.Value))

            If DropDownList4.SelectedValue = "" Then
                .Add(New SqlParameter("@CONS_NEPH", DBNull.Value))
            Else
                .Add(New SqlParameter("@CONS_NEPH", DropDownList4.SelectedValue))
            End If

            .Add(New SqlParameter("@RENAL_UNIT", Session("unitID")))

            If DropDownList4.SelectedValue = "" Then
                .Add(New SqlParameter("@STATUS", DBNull.Value))
            Else
                .Add(New SqlParameter("@STATUS", DropDownList5.SelectedValue))
            End If

        End With

        Try
            objConnect.Open()
            objCommand.ExecuteNonQuery()

            Dim strSQL2 As String = "SELECT @@IDENTITY FROM [tbl_Demographics]"
            Dim objCommand2 As New SqlCommand(strSQL2, objConnect)

            'declare a variable to hold the a DataReader object
            Dim objDataReader As SqlDataReader

            'execute the SQL statement
            objDataReader = objCommand2.ExecuteReader()


            If objDataReader.Read() Then
                Session("patientID") = objDataReader(0)
                'lblDebug2.Text = "patient ID = " & Session("patientID")
            End If


        Catch objError As Exception

            'lblDebug.Text = "An error occurred: '" & objError.Message & "'&nbsp;<br/>" & strSQL
            lblUpdate.Text = "An error occurred, please review the data you have entered"
            objConnect.Close()
            Exit Sub

        Finally
            objConnect.Close()

        End Try

        lblUpdate.Text = "<strong>Entry successful:</strong>&nbsp;&nbsp;" & Now().ToShortTimeString
        lblDebug.Text = ""
        lblMsg.Text = "Once you have entered the demographic details below you can edit and add to the entry at any time."

        btnSubmit.Visible = False
        btnEdit.Visible = True
        btnContinue.Visible = False


        lnk3Months.Enabled = True
        lnkDiagnosis.Enabled = True
        lnkLabResults.Enabled = True


        lblNew.Text = "&nbsp;Demographics"
        Session("mode") = 2
        'lblDebug2.Text = Session("mode")




        Dim strSQL3 As String = "INSERT INTO [tbl_Diagnosis] ([RADAR_NO]) VALUES (@RADAR_NO ); INSERT INTO [tbl_ClinicalData] ([RADAR_NO], [SEQ_NO]) VALUES (@RADAR_NO, @SEQ_NO ); INSERT INTO [tbl_LabData] ([RADAR_NO], [SEQ_NO]) VALUES (@RADAR_NO, @SEQ_NO); INSERT INTO [tbl_6Month] ([RADAR_NO]) VALUES (@RADAR_NO ); INSERT INTO [tbl_Therapy] ([RADAR_NO], [SEQ_NO]) VALUES (@RADAR_NO, @SEQ_NO)"
        Dim objCommand3 As New SqlCommand(strSQL3, objConnect)

        Try
            With objCommand3.Parameters
                .Add(New SqlParameter("@RADAR_NO", Session("patientID")))
                .Add(New SqlParameter("@SEQ_NO", 1))
            End With

            objConnect.Open()
            objCommand3.ExecuteNonQuery()

        Catch objError As Exception

            lblDebug2.Text = "An error occurred: '" & objError.Message & "'" & strSQL3

        Finally

            objConnect.Close()

        End Try

        pagefill()



    End Sub

    Sub pagefill()

        Dim TDES As New TripleDES

        lnk3Months.Enabled = True
        lnkDiagnosis.Enabled = True
        lnkLabResults.Enabled = True

        Dim intPatientID As Integer = Session("patientID")

        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim strSQL As String = "SELECT [tbl_Demographics].[RADAR_NO], [RR_NO], [DATE_REG], [NHS_NO], [HOSP_NO], [UKT_NO], [CHI_NO], [SNAME], [SNAME_ALIAS], [FNAME], [DOB], [AGE], [SEX], [ETHNIC_GP], [ADD1], [ADD2], [ADD3], [ADD4], [POSTCODE], [POSTCODE_OLD], [CONSENT], [DATE_BAPN_REG], [CONS_NEPH], [RENAL_UNIT], [tbl_Diagnosis].[DIAG] FROM [tbl_Demographics], [tbl_Diagnosis] WHERE ([tbl_Demographics].[RADAR_NO] = [tblDiagnosis].[RADAR_NO]) AND [tbl_Demographics].[RADAR_NO] = " & intPatientID
        Dim strSQL2 As String = "SELECT tbl_Demographics.*, tbl_Diagnosis.DIAG FROM tbl_Demographics INNER JOIN tbl_Diagnosis ON tbl_Demographics.RADAR_NO = tbl_Diagnosis.RADAR_NO WHERE [tbl_Demographics].[RADAR_NO] =" & intPatientID
        'Dim strSQL3 As String = "SELECT tbl_Demographics.*, tbl_Diagnosis.DIAG FROM tbl_Demographics INNER JOIN tbl_Diagnosis ON tbl_Demographics.RADAR_NO = tbl_Diagnosis.RADAR_NO WHERE [tbl_Demographics].[RADAR_NO] =" & intPatientID
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL2, objConnect)

        Try

            'declare a variable to hold the a DataReader object
            Dim objDataReader As SqlDataReader
            objConnect.Open()
            'execute the SQL statement
            objDataReader = objCommand.ExecuteReader()


            If objDataReader.Read() Then

                SNAMETextBox.Text = TDES.Decrypt(objDataReader("SNAME"))
                FNAMETextBox.Text = TDES.Decrypt(objDataReader("FNAME"))
                RADAR_NOTextBox.Text = objDataReader("RADAR_NO")
                DATE_REGTextBox.Text = Format(objDataReader("DATE_REG"), "dd-MMM-yyyy")

                If objDataReader("DIAG") Is DBNull.Value Then
                    lnk3Months.Enabled = False
                    lnkLabResults.Enabled = False
                    lnkPathology.Enabled = False
                    lnkRelapse.Enabled = False
                    lnkHospital.Enabled = False
                End If

                'lblDebug.Text = chkClinical(intPatientID)

                If chkClinical(intPatientID) = True Then  ' check for a diagnosis before allowing First Visit data entry
                    lnk3Months.Enabled = True
                Else
                    lnk3Months.Enabled = False
                    lnkRelapse.Enabled = False
                    lnkPathology.Enabled = False
                    lnkHospital.Enabled = False
                    lnkTimelines.Enabled = False
                End If


                If objDataReader("DOB") Is DBNull.Value Then
                    'do nothing
                Else
                    DOBTextBox.Text = TDES.Decrypt(objDataReader("DOB"))
                End If

                DropDownList1.SelectedValue = chkNull(objDataReader("SEX"))
                DropDownList3.SelectedValue = chkNull(objDataReader("ETHNIC_GP"))
                ADD1TextBox.Text = TDES.Decrypt(objDataReader("ADD1"))
                ADD2TextBox.Text = TDES.Decrypt(objDataReader("ADD2"))
                ADD3TextBox.Text = TDES.Decrypt(objDataReader("ADD3"))
                ADD4TextBox.Text = TDES.Decrypt(objDataReader("ADD4"))

                If TDES.Decrypt(objDataReader("POSTCODE")) = "-" Then
                    POSTCODETextBox.Text = ""
                Else
                    POSTCODETextBox.Text = TDES.Decrypt(objDataReader("POSTCODE"))
                End If

                SNAME_ALIASTextBox.Text = TDES.Decrypt(objDataReader("SNAME_ALIAS"))
                POSTCODE_OLDTextBox.Text = TDES.Decrypt(objDataReader("POSTCODE_OLD"))
                HOSP_NOTextBox.Text = TDES.Decrypt(objDataReader("HOSP_NO"))
                NHS_NOTextBox.Text = TDES.Decrypt(objDataReader("NHS_NO"))
                RR_NOTextBox.Text = chkNull(objDataReader("RR_NO"))
                UKT_NOTextBox.Text = chkNull(objDataReader("UKT_NO"))
                CHI_NOTextBox.Text = chkNull(objDataReader("CHI_NO"))
                DropDownList5.SelectedValue = chkNull(objDataReader("STATUS"))

                Dim intAge As Integer = GetAge(TDES.Decrypt(objDataReader("DOB")), objDataReader("DATE_REG"))

                '# documents removed from demographics page 09 Apr 2010

                'If intAge < 7 Then
                '    lblConsent.Text = "<a href='docs/National Registry Consent form parents V2 16-11-09 (2).pdf' target='_blank'>Parent/Guardian Consent Form</a>"
                '    lblPatientInfo.Text = "<a href='docs/National Registry PIS parents v4 26-08-091.pdf' target='_blank'>Parent/Guardian Information Sheet</a>"
                '    lblInfo2.Text = ""

                'ElseIf intAge >= 7 And intAge < 12 Then
                '    lblConsent.Text = "<a href='docs/National Registry Consent form parents V2 16-11-09 (2).pdf' target='_blank'>Parent/Guardian Consent Form</a>"
                '    lblPatientInfo.Text = "<a href='docs/National Registry PIS parents v4 26-08-091.pdf' target='_blank'>Parent/Guardian Information Sheet</a>"
                '    lblInfo2.Text = "<a href='docs/National Registry PIS children v3 29-10-09 (2).pdf' target='_blank'>Childrens Information Sheet</a>"

                'ElseIf intAge >= 12 And intAge <= 18 Then
                '    lblConsent.Text = "<a href='docs/National Registry Consent form parents V2 16-11-09 (2).pdf' target='_blank'>Parent/Guardian Consent Form</a>"
                '    lblPatientInfo.Text = "<a href='docs/National Registry PIS parents v4 26-08-091.pdf' target='_blank'>Parent/Guardian Information Sheet</a>"
                '    lblInfo2.Text = "<a href='docs/National Registry PIS adolescents v4 11-11-09.pdf' target='_blank'>Adolescent Information Sheet</a>"
                '    lblConsent2.Text = "<a href='docs/National Registry Adolescent assent form v1 30 10 09.pdf' target='_blank'>Adolescent Assent Form</a>"

                'ElseIf intAge > 18 Then
                '    lblConsent.Text = "<a href='docs/National Registry Consent form participants V3 12-11-09 (2).pdf' target='_blank'>Participant Consent Form</a>"
                '    lblPatientInfo.Text = "<a href='docs/National Registry PIS v4 26-08-091.pdf' target='_blank'>Participant Information Sheet</a>"
                '    lblInfo2.Text = ""
                '    lblConsent2.Text = ""

                'Else
                '    lblConsent.Text = ""
                '    lblPatientInfo.Text = ""

                'End If


                Dim intUnit As Integer = objDataReader("RENAL_UNIT")

                SqlDataSource1.SelectCommand = "SELECT cID, (cSNAME + '  ' + cFNAME) AS NAME, cFNAME, cSNAME FROM tbl_Consultants WHERE (cCentre = " & intUnit & ") ORDER BY cSNAME"

                DropDownList4.DataBind()


                If objDataReader("CONS_NEPH") Is DBNull.Value Then
                Else
                    DropDownList4.SelectedValue = chkNull(objDataReader("CONS_NEPH"))
                End If



                DropDownList2.SelectedValue = objDataReader("RENAL_UNIT")
                CONSENTCheckBox.Checked = objDataReader("CONSENT")



            End If

        Catch objError As Exception

            lblDebug.Text = "An error occurred: '" & objError.Message & "'" & strSQL2
            objConnect.Close()
            Exit Sub

        Finally

            objConnect.Close()

        End Try


    End Sub

    Function chkNull(ByVal value As Object) As String

        If value Is DBNull.Value Then
            chkNull = ""
        Else
            chkNull = value.ToString
        End If

    End Function

    Protected Sub btnEdit_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnEdit.Click

        'clear error messages
        lblDebug.Text = ""
        lblDebug2.Text = ""
        lblMsg.Text = ""

        Dim TDES As New TripleDES()


        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim strSQL As String = "UPDATE [tbl_Demographics] SET [RR_NO] = @RR_NO, [DATE_REG] = @DATE_REG, [NHS_NO] = @NHS_NO, [HOSP_NO] = @HOSP_NO, [UKT_NO] = @UKT_NO, [CHI_NO] = @CHI_NO, [SNAME] = @SNAME, [SNAME_ALIAS] = @SNAME_ALIAS, [FNAME] = @FNAME, [DOB] = @DOB, [AGE] = @AGE, [SEX] = @SEX, [ETHNIC_GP] = @ETHNIC_GP, [ADD1] = @ADD1, [ADD2] = @ADD2, [ADD3] = @ADD3, [ADD4] = @ADD4, [POSTCODE] = @POSTCODE, [POSTCODE_OLD] = @POSTCODE_OLD, [CONSENT] = @CONSENT, [DATE_BAPN_REG] = @DATE_BAPN_REG, [CONS_NEPH] = @CONS_NEPH, [RENAL_UNIT] = @RENAL_UNIT, [STATUS] = @STATUS WHERE [RADAR_NO] = @RADAR_NO"
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)


        With objCommand.Parameters


            If RR_NOTextBox.Text = "" Then
                .Add(New SqlParameter("@RR_NO", DBNull.Value))
            Else
                .Add(New SqlParameter("@RR_NO", RR_NOTextBox.Text))
            End If

            .Add(New SqlParameter("@DATE_REG", DATE_REGTextBox.Text))

            Dim strNHS_NO As String

            If NHS_NOTextBox.Text = "" Then
                strNHS_NO = "-"
            Else
                strNHS_NO = NHS_NOTextBox.Text
            End If
            .Add(New SqlParameter("@NHS_NO", TDES.Encrypt(strNHS_NO)))


            If HOSP_NOTextBox.Text = "" Then
                .Add(New SqlParameter("@HOSP_NO", TDES.Encrypt("-")))
            Else
                .Add(New SqlParameter("@HOSP_NO", TDES.Encrypt(HOSP_NOTextBox.Text)))
            End If

            If UKT_NOTextBox.Text = "" Then
                .Add(New SqlParameter("@UKT_NO", DBNull.Value))
            Else
                .Add(New SqlParameter("@UKT_NO", UKT_NOTextBox.Text))
            End If

            If CHI_NOTextBox.Text = "" Then
                .Add(New SqlParameter("@CHI_NO", DBNull.Value))
            Else
                .Add(New SqlParameter("@CHI_NO", CHI_NOTextBox.Text))
            End If


            .Add(New SqlParameter("@SNAME", TDES.Encrypt(SNAMETextBox.Text)))

            Dim strSNAMEALIAS As String

            If SNAME_ALIASTextBox.Text = "" Then
                strSNAMEALIAS = "-"  ' need to do this to handle encryption
            Else
                strSNAMEALIAS = SNAME_ALIASTextBox.Text
            End If

            .Add(New SqlParameter("@SNAME_ALIAS", TDES.Encrypt(strSNAMEALIAS)))
            .Add(New SqlParameter("@FNAME", TDES.Encrypt(FNAMETextBox.Text)))
            .Add(New SqlParameter("@DOB", TDES.Encrypt(DOBTextBox.Text)))

            .Add(New SqlParameter("@AGE", DBNull.Value))

            .Add(New SqlParameter("@SEX", DropDownList1.SelectedValue))

            .Add(New SqlParameter("@ETHNIC_GP", DropDownList3.SelectedValue))

            If ADD1TextBox.Text = "" Then
                .Add(New SqlParameter("@ADD1", TDES.Encrypt("-")))
            Else
                .Add(New SqlParameter("@ADD1", TDES.Encrypt(ADD1TextBox.Text)))
            End If

            If ADD2TextBox.Text = "" Then
                .Add(New SqlParameter("@ADD2", TDES.Encrypt("-")))
            Else
                .Add(New SqlParameter("@ADD2", TDES.Encrypt(ADD2TextBox.Text)))
            End If

            If ADD3TextBox.Text = "" Then
                .Add(New SqlParameter("@ADD3", TDES.Encrypt("-")))
            Else
                .Add(New SqlParameter("@ADD3", TDES.Encrypt(ADD3TextBox.Text)))
            End If

            If ADD4TextBox.Text = "" Then
                .Add(New SqlParameter("@ADD4", TDES.Encrypt("-")))
            Else
                .Add(New SqlParameter("@ADD4", TDES.Encrypt(ADD4TextBox.Text)))
            End If


            If POSTCODETextBox.Text = "" Then
                .Add(New SqlParameter("@POSTCODE", TDES.Encrypt("-")))
            Else
                .Add(New SqlParameter("@POSTCODE", TDES.Encrypt(POSTCODETextBox.Text)))
            End If

            If POSTCODE_OLDTextBox.Text = "" Then
                .Add(New SqlParameter("@POSTCODE_OLD", TDES.Encrypt("-")))
            Else
                .Add(New SqlParameter("@POSTCODE_OLD", TDES.Encrypt(POSTCODE_OLDTextBox.Text)))
            End If


            .Add(New SqlParameter("@CONSENT", CONSENTCheckBox.Checked))

            .Add(New SqlParameter("@DATE_BAPN_REG", DBNull.Value))

            If DropDownList4.SelectedValue = "" Then
                .Add(New SqlParameter("@CONS_NEPH", DBNull.Value))
            Else
                .Add(New SqlParameter("@CONS_NEPH", DropDownList4.SelectedValue))
            End If

            .Add(New SqlParameter("@RENAL_UNIT", Session("unitID")))

            .Add(New SqlParameter("@RADAR_NO", RADAR_NOTextBox.Text))

            If DropDownList5.SelectedValue = "" Then
                .Add(New SqlParameter("@STATUS", DBNull.Value))
            Else
                .Add(New SqlParameter("@STATUS", DropDownList5.SelectedValue))
            End If

        End With

        Try
            objConnect.Open()
            objCommand.ExecuteNonQuery()

        Catch objError As Exception

            lblDebug2.Text = "An error occurred: '" & objError.Message & "'" & strSQL
            objConnect.Close()
            Exit Sub

        Finally

            objConnect.Close()
            lblUpdate.Text = "<strong>Update successful:</strong>&nbsp;&nbsp;" & Now().ToShortTimeString
            'lblDebug2.Text = RADAR_NOTextBox.Text

        End Try

        pagefill()

    End Sub

    Function chkClinical(ByVal intRadar As Integer) As Boolean

        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim strSQL As String = "SELECT DATE_CLIN_PIC FROM tbl_ClinicalData WHERE [RADAR_NO] = " & intRadar
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

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

            'lblDebug.Text = chkClinical

        Finally

            objConnect.Close()


        End Try


    End Function

    Function GetAge(ByVal dtDOB As Date, ByVal dtNow As Date) As Int16


        Dim intLeapYear As Int16 = 0
        Dim i As Int16 = 0

        For i = (dtNow.Year) To (dtDOB.Year) Step -1  'this isn't giving the right answer
            If i Mod 4 = 0 Then
            Else
                intLeapYear = intLeapYear + 1
            End If
        Next

        Dim ts As Int16 = CInt((dtNow - dtDOB).TotalDays)

        Dim intAge As Integer = (ts - intLeapYear) \ 365

        GetAge = intAge


    End Function

    Function checkExists(ByVal fName As String, ByVal sName As String) As Boolean

        Dim TDES As New TripleDES


        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString

        Dim strSQL As String = "SELECT [SNAME], [FNAME] FROM [tbl_Demographics] WHERE [SNAME]= @SNAME AND [FNAME] = @FNAME"
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        With objCommand.Parameters

            .Add(New SqlParameter("@FNAME", TDES.Encrypt(FNAMETextBox.Text)))
            .Add(New SqlParameter("@SNAME", TDES.Encrypt(SNAMETextBox.Text)))

        End With

        Try
            Dim objDataReader As SqlDataReader
            objConnect.Open()
            'execute the SQL statement
            objDataReader = objCommand.ExecuteReader()


            If objDataReader.Read() Then
                checkExists = True
            Else
                checkExists = False
            End If
        Catch ex As Exception
            lblDebug.Text = ex.Message
            checkExists = False
        End Try


    End Function
    

    Protected Sub btnContinue_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnContinue.Click
        saveNew()
    End Sub
End Class
