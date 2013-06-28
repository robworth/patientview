Imports System.Data.SqlClient
Imports ConfigurationAlias = System.Configuration
Partial Class hospitalisation2aspx
    Inherits System.Web.UI.Page
    Public newID As Integer
    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load


        If Not Session("blnAuthenticated") Then
            Response.Redirect("default.aspx")
        End If

        If Session("diag") = 1 Then
            lblPage.Text = "16B"
        ElseIf Session("diag") = 2 Then
            lblPage.Text = "16A"
        End If

        'txtDateToday.Text = Now().ToShortDateString

        If Session("uType") = "p" Then
            btnUpdate.Visible = False
            btnSave.Visible = False
            CommonClass.DisableControls(tblMain)
            'tblAddPlasma.Visible = False
            'tblAddImmuno.Visible = False
            'lblPlasmaAdd.Visible = False
            'lblImmunoAdd.visible = False
            lnkTimeLines.Visible = False
        Else
            lnkRelapse.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkDemographics.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnk3Months.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkLabResults.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkDiagnosis.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkPathology.Attributes.Add("onclick", "return confirm('Have you saved any changes?\nIf not click \'Cancel\' and save them now')")
            lnkTimeLines.Visible = False
        End If


        '# get basic patient data
        Dim TDES As New TripleDES()


        Dim patientID As Integer = Session("patientID")
        'lblDebug.Text = patientID.ToString
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
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
                        txtFNAME.Text = TDES.Decrypt(objDataReader("FNAME"))
                        txtSNAME.Text = TDES.Decrypt(objDataReader("SNAME"))
                        Dim dt As Date = CDate(TDES.Decrypt(objDataReader("DOB")))
                        txtDOB.Text = Format(dt, "dd-MMM-yyyy")

                        ' If objDataReader("DIAG") Is DBNull.Value Then
                        ' txtDIAGNOSIS.Text = "-"
                        'Else
                        txtDIAGNOSIS.Text = CommonClass.GetDiagnosis(objDataReader("DIAG"))
                        'End If



                    End If
                Catch objError As Exception
                    'lblDebug.Text = String.Format("An error occurred: '{0}'{1}", objError.Message, strSQL2)
                    objConnect2.Close()
                    Exit Sub
                Finally
                    objConnect2.Close()
                End Try
            End Using
        End Using



    End Sub

    Protected Sub btnAdd_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnAdd.Click

        CommonClass.EmptyTexBoxes(tblMain)
        pnlAdd.Visible = True
        btnUpdate.Visible = False
        btnUpdate2.Visible = False
        btnSave.Visible = True
        btnSave.Enabled = True
        btnSave2.Visible = True
        lblUpdate.Text = ""
        lblDebug.Text = ""
        
    End Sub

    Sub pageFill(ByVal intID As Integer)

        Dim patientID As Integer = Session("patientID")

        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString

        Dim strSQL As String = "SELECT [hID], [DATE_ADMIT], [DATE_DISCHARGE], [REASON_ADMIT], [COMMENT] FROM [tbl_Hospitalisation] WHERE [RADAR_NO] = " & patientID & " AND [hID] = " & intID
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)
        'lblDebug.Text = strSQL
        Try
            Dim objDataReader As SqlDataReader
            objConnect.Open()
            objDataReader = objCommand.ExecuteReader()

            If objDataReader.Read() Then

                lblHid.Text = objDataReader("hID")
                If objDataReader("DATE_ADMIT") IsNot DBNull.Value Then
                    'txtDATE_ADMIT_CalendarExtender.SelectedDate = objDataReader("DATE_ADMIT")
                    txtDATE_ADMIT.Text = Format(objDataReader("DATE_ADMIT"), "dd/MM/yyyy")
                Else
                    txtDATE_ADMIT.Text = ""
                End If
                If objDataReader("DATE_DISCHARGE") IsNot DBNull.Value Then
                    'txtDATE_DISCHARGE_CalendarExtender.SelectedDate = objDataReader("DATE_DISCHARGE")
                    txtDATE_DISCHARGE.Text = Format(objDataReader("DATE_DISCHARGE"), "dd/MM/yyyy")
                Else
                    txtDATE_DISCHARGE.Text = ""
                End If
                txtREASON_ADMIT.Text = CommonClass.chkNull(objDataReader("REASON_ADMIT"))
                txtCOMMENTS.Text = CommonClass.chkNull(objDataReader("COMMENT"))
                btnUpdate.Visible = True
                btnUpdate2.Visible = True
                btnSave.Visible = False
                btnSave2.Visible = False

            Else
                If Session("uType") <> "p" Then

                    btnSave.Visible = True
                    btnSave2.Visible = True
                End If

                btnUpdate.Visible = False
                btnUpdate2.Visible = False
            End If

        Catch objError As Exception
            lblDebug.Text = String.Format("An error occurred: '{0}'{1}", objError.Message, strSQL)
            objConnect.Close()
            Exit Sub
        Finally
            objConnect.Close()
        End Try

    End Sub


    Protected Sub btnSave_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnSave.Click
        save()
    End Sub

    Sub save()
        Dim intRadar As Integer = txtRADAR_NO.Text

        '# check that a record doesn't already exist for this date

        Dim dtAdmit As Date = CDate(txtDATE_ADMIT.Text)
        Dim dtDischarge As Date

        If txtDATE_DISCHARGE.Text = "" Then
            dtDischarge = dtAdmit
        Else
            dtDischarge = CDate(txtDATE_DISCHARGE.Text)
        End If

        lblDebug.Text = ""

        If chkDate(dtAdmit, dtDischarge, intRadar) = False Then
            'do nothing
        Else
            lblUpdate.Text = "<span style='color:red;'>A record already exists covering this admission date.</span>"
            lblUpdate2.Text = "<span style='color:red;'>A record already exists covering this admission date.</span>"

            Exit Sub
        End If

        Dim strSQL As String = "INSERT INTO [tbl_Hospitalisation] ([RADAR_NO], [DATE_ADMIT], [DATE_DISCHARGE], [REASON_ADMIT], [COMMENT]) VALUES (@RADAR_NO, @DATE_ADMIT, @DATE_DISCHARGE, @REASON_ADMIT, @COMMENT)"
        Dim strConnect As String = ConfigurationAlias.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim strSQL2 As String = "SELECT @@IDENTITY  FROM [tbl_Hospitalisation] "
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)
        Dim objCommand2 As New SqlCommand(strSQL2, objConnect)

        Try
            With objCommand.Parameters

                .Add(New SqlParameter("@RADAR_NO", intRadar))

                .Add(New SqlParameter("@DATE_ADMIT", CDate(txtDATE_ADMIT.Text)))

                If txtDATE_DISCHARGE.Text = "" Then
                    .Add(New SqlParameter("@DATE_DISCHARGE", DBNull.Value))
                Else
                    .Add(New SqlParameter("@DATE_DISCHARGE", CDate(txtDATE_DISCHARGE.Text)))

                End If

                .Add(New SqlParameter("@REASON_ADMIT", CommonClass.chkNullSave(txtREASON_ADMIT.Text)))
                .Add(New SqlParameter("@COMMENT", CommonClass.chkNullSave(txtCOMMENTS.Text)))

            End With

            objConnect.Open()
            objCommand.ExecuteNonQuery()


            'get new record





            Dim objDataReader As SqlDataReader

            objDataReader = objCommand2.ExecuteReader()

            If objDataReader.Read() Then
                newID = objDataReader(0)
            End If






        Catch objError As Exception

            lblDebug.Text = String.Format("An error occurred: '{0}'{1}", objError.Message, strSQL2)
            objConnect.Close()
            Exit Sub

        Finally

            lblUpdate.Text = "<strong>Record saved:</strong>&nbsp;&nbsp;" & Now().ToShortTimeString
            lblUpdate2.Text = "<strong>Record saved:</strong>&nbsp;&nbsp;" & Now().ToShortTimeString

            btnSave.Visible = False
            btnSave2.Visible = False
            btnUpdate.Visible = True
            btnUpdate2.Visible = True

            objConnect.Close()

        End Try

        ddlRefresh(DropDownListAdminDate)

        pageFill(newID)

    End Sub

    Protected Sub DropDownListAdminDate_SelectedIndexChanged(ByVal sender As Object, ByVal e As System.EventArgs) Handles DropDownListAdminDate.SelectedIndexChanged

        Dim intID As Integer = DropDownListAdminDate.SelectedValue
        pageFill(intID)
        DropDownListAdminDate.SelectedIndex = 0
        btnSave.Enabled = False
        btnUpdate.Enabled = True
        pnlAdd.Visible = True
        lblUpdate.Text = ""

    End Sub

    Protected Sub btnUpdate_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnUpdate.Click
        update()
    End Sub

    Sub update()
        Dim strSQL As String = "UPDATE [tbl_Hospitalisation] SET [DATE_ADMIT]= @DATE_ADMIT, [DATE_DISCHARGE] = @DATE_DISCHARGE, [REASON_ADMIT] = @REASON_ADMIT, [COMMENT] = @COMMENT WHERE [hID] = @hID"
        Dim strConnect As String = ConfigurationAlias.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString

        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        Dim intHid As Integer = lblHid.Text

        Try
            With objCommand.Parameters

                .Add(New SqlParameter("@hID", intHid))

                .Add(New SqlParameter("@DATE_ADMIT", CDate(txtDATE_ADMIT.Text)))

                If txtDATE_DISCHARGE.Text = "" Then
                    .Add(New SqlParameter("@DATE_DISCHARGE", DBNull.Value))
                Else
                    .Add(New SqlParameter("@DATE_DISCHARGE", CDate(txtDATE_DISCHARGE.Text)))

                End If

                If txtREASON_ADMIT.Text = "" Then
                    .Add(New SqlParameter("@REASON_ADMIT", DBNull.Value))
                Else
                    .Add(New SqlParameter("@REASON_ADMIT", (txtREASON_ADMIT.Text)))
                End If

                If txtCOMMENTS.Text = "" Then
                    .Add(New SqlParameter("@COMMENT", DBNull.Value))
                Else
                    .Add(New SqlParameter("@COMMENT", txtCOMMENTS.Text))
                End If
            End With

            objConnect.Open()
            objCommand.ExecuteNonQuery()

        Catch objError As Exception

            lblDebug.Text = String.Format("An error occurred: '{0}'{1}", objError.Message, strSQL)
            objConnect.Close()
            Exit Sub

        Finally
            lblUpdate.Text = " <strong>Update saved:</strong>&nbsp;&nbsp;" & Now().ToShortTimeString
            lblUpdate2.Text = " <strong>Update saved:</strong>&nbsp;&nbsp;" & Now().ToShortTimeString

            btnSave.Visible = False
            btnSave2.Visible = False
            btnUpdate.Visible = True
            btnUpdate2.Visible = True


            objConnect.Close()

        End Try

        pageFill(intHid)


    End Sub

    Function chkDate(ByVal dtstart As Date, ByVal dtStop As Date, ByVal intRadar As Integer) As Boolean

       

        Dim strStartDate As String = CStr(Year(dtStart)) & "/" & CStr(Month(dtStart)) & "/" & CStr(Day(dtStart))
        Dim strStopDate As String = CStr(Year(dtStop)) & "/" & CStr(Month(dtStop)) & "/" & CStr(Day(dtStop))
        dtStart = CDate(strStartDate)
        dtStop = CDate(strStopDate)


        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        'Dim strSQL As String = "SELECT [hID] FROM [tbl_Hospitalisation] WHERE [RADAR_NO] = '" & intRadar & "' AND [DATE_ADMIT] =< Convert(DATETIME, '" & dtAdmit & "', 103) AND [DATE_DISCHARGE] >= Convert(DATETIME, '" & dtAdmit & "', 103)"
        'Dim strSQL As String = "SELECT [hID] FROM [tbl_Hospitalisation] WHERE [RADAR_NO] = '" & intRadar & "' AND CONVERT(DATETIME,'" & dtAdmit & "', 103) BETWEEN [DATE_ADMIT] AND [DATE_DISCHARGE]"
        Dim strSQL As String = "SELECT [hID], [RADAR_NO] FROM [tbl_Hospitalisation] WHERE (((cast(@StartDate as smalldatetime) BETWEEN [DATE_ADMIT] AND [DATE_DISCHARGE]) OR (cast(@StopDate as smalldatetime) BETWEEN [DATE_ADMIT] AND [DATE_DISCHARGE]) OR (cast(@StartDate as smalldatetime) <= [DATE_ADMIT] AND cast(@StopDate as smalldatetime) >= [DATE_DISCHARGE])) AND ([RADAR_NO] = @RADAR_NO))"

        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        Try

            With objCommand.Parameters

                .Add(New SqlParameter("@StartDate", dtstart))
                .Add(New SqlParameter("@StopDate", dtStop))
                .Add(New SqlParameter("@RADAR_NO", intRadar))

            End With

            Dim objDataReader As SqlDataReader
            objConnect.Open()
            objDataReader = objCommand.ExecuteReader()

            'lblDebug.Text = strSQL

            If objDataReader.Read() Then
                chkDate = True
            Else
                chkDate = False
            End If

        Catch objError As Exception
            chkDate = False
        Finally

        End Try


    End Function

    Sub ddlRefresh(ByVal ddl As DropDownList)

        ddl.Items.Clear()
        'ddl.DataBind()

        Dim li As New System.Web.UI.WebControls.ListItem

        li.Value = ""
        li.Text = "Select"
        ddl.Items.Insert(0, li)   'Add(li)
        ddl.AppendDataBoundItems = True

    End Sub

    Protected Sub btnSave2_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnSave2.Click
        save()
    End Sub

    Protected Sub btnUpdate2_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnUpdate2.Click
        update()
    End Sub
End Class
