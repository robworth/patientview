Imports System.Data
Imports System.Data.OleDb
Imports System.Data.SqlClient
Imports System.IO

Partial Class patient_entry
    Inherits System.Web.UI.Page

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load
        If Not Session("blnAuthenticated") Then
            Response.Redirect("default.aspx")
        End If
    End Sub

    Protected Sub btnAdd_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnAdd.Click

        Dim strRRNo As String = checkNull(txtRenalRegNo.Text)
        Dim dtReg As Date = Now().ToShortDateString
        Dim intNHSno As String = txtNHSNo.Text
        Dim strHospNo As String = checkNull(txtHospNo.Text)
        Dim intUKT As String = txtUKTrNo.Text
        Dim intCHI As String = txtCHINo.Text
        Dim strSName As String = checkNull(txtSurname.Text)
        Dim strFName As String = checkNull(txtFirstName.Text)
        Dim dtDOB As Date = CDate(txtDOB.Text)
        Dim intAge As Integer
        Dim intSex As Integer = DropDownList1.SelectedValue
        Dim strEthnicGrp As String = checkNull(DropDownList3.SelectedValue)

        intAge = GetAge(dtDOB)

        Dim strAddress = checkNull(txtAddr.Text)

        Dim strAddr1 As String = ""
        Dim strAddr2 As String = ""
        Dim strAddr3 As String = ""
        Dim strAddr4 As String = ""

        If strAddress IsNot DBNull.Value Then

            Dim addrArray As String() = Split(strAddress, vbCrLf)

            For i As Integer = 0 To addrArray.GetUpperBound(0)
                Select Case i
                    Case 0
                        strAddr1 = addrArray(0)
                    Case 1
                        strAddr2 = addrArray(1)
                    Case 2
                        strAddr3 = addrArray(2)
                    Case 3
                        strAddr4 = addrArray(3)
                End Select
            Next


        End If


        Dim strPostCode As String = checkNull(txtPostCode.Text)
        Dim strConsent As String

        'Dim dtBAPN As Date = CDate(txtDateBAPN.Text)
        Dim strConsNeph As String = checkNull(txtCGMC.Text)
        Dim intUnit As Integer = DropDownList2.SelectedValue

        Dim intLastRecord As Integer


        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New System.Data.SqlClient.SqlConnection(strConnect)
        Dim strSQL As String = "INSERT INTO [tbl_Demographics] ([RR_NO], [DATE_REG], [NHS_NO], [HOSP_NO], [UKT_NO], [CHI_NO], [SNAME], [SNAME_ALIAS], [FNAME], [DOB], [AGE], [SEX], [ETHNIC_GP], [ADD1], [ADD2], [ADD3], [ADD4], [POSTCODE], [POSTCODE_OLD], [CONSENT], [DATE_BAPN_REG], [CONS_NEPH], [RENAL_UNIT]) VALUES (@RR_NO, @DATE_REG, @NHS_NO, @HOSP_NO, @UKT_NO, @CHI_NO, @SNAME, @SNAME_ALIAS, @FNAME, @DOB, @AGE, @SEX, @ETHNIC_GP, @ADD1, @ADD2, @ADD3, @ADD4, @POSTCODE, @POSTCODE_OLD, @CONSENT, @DATE_BAPN_REG, @CONS_NEPH, @RENAL_UNIT)"

        Dim objCommand As New System.Data.SqlClient.SqlCommand(strSQL, objConnect)

        Try
            With objCommand.Parameters
                .Add(New System.Data.SqlClient.SqlParameter("@RR_NO", strRRNo))
                .Add(New System.Data.SqlClient.SqlParameter("@DATE_REG", dtReg))

                If intNHSno = "" Then
                    .Add(New System.Data.SqlClient.SqlParameter("@NHS_NO", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@NHS_NO", CInt(intNHSno)))
                End If

                .Add(New System.Data.SqlClient.SqlParameter("@HOSP_NO", strHospNo))

                If intUKT = "" Then
                    .Add(New System.Data.SqlClient.SqlParameter("@UKT_NO", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@UKT_NO", CInt(intUKT)))
                End If

                If intCHI = "" Then
                    .Add(New System.Data.SqlClient.SqlParameter("@CHI_NO", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@CHI_NO", CInt(intCHI)))
                End If

                .Add(New System.Data.SqlClient.SqlParameter("@SNAME", strSName))
                .Add(New System.Data.SqlClient.SqlParameter("@SNAME_ALIAS", DBNull.Value))
                .Add(New System.Data.SqlClient.SqlParameter("@FNAME", strFName))
                .Add(New System.Data.SqlClient.SqlParameter("@DOB", dtDOB))
                .Add(New System.Data.SqlClient.SqlParameter("@AGE", intAge))
                .Add(New System.Data.SqlClient.SqlParameter("@SEX", intSex))
                .Add(New System.Data.SqlClient.SqlParameter("@ETHNIC_GP", strEthnicGrp))
                .Add(New System.Data.SqlClient.SqlParameter("@ADD1", strAddr1))
                .Add(New System.Data.SqlClient.SqlParameter("@ADD2", strAddr2))
                .Add(New System.Data.SqlClient.SqlParameter("@ADD3", strAddr3))
                .Add(New System.Data.SqlClient.SqlParameter("@ADD4", strAddr4))
                .Add(New System.Data.SqlClient.SqlParameter("@POSTCODE", strPostCode))
                .Add(New System.Data.SqlClient.SqlParameter("@POSTCODE_OLD", DBNull.Value))
                .Add(New System.Data.SqlClient.SqlParameter("@CONSENT", DBNull.Value))

                If txtDateBAPN.Text = "" Then
                    .Add(New System.Data.SqlClient.SqlParameter("@DATE_BAPN_REG", DBNull.Value))
                Else
                    .Add(New System.Data.SqlClient.SqlParameter("@DATE_BAPN_REG", CDate(txtDateBAPN.Text)))
                End If


                .Add(New System.Data.SqlClient.SqlParameter("@CONS_NEPH", strConsNeph))
                .Add(New System.Data.SqlClient.SqlParameter("@RENAL_UNIT", intUnit))




            End With

            objConnect.Open()
            objCommand.ExecuteNonQuery()

            'get record ID

            Dim strSQL2 As String = "SELECT @@IDENTITY FROM [tbl_Demographics]"
            Dim objCommand2 As New System.Data.SqlClient.SqlCommand(strSQL2, objConnect)

            'declare a variable to hold the a DataReader object
            Dim objDataReader As SqlDataReader

            'execute the SQL statement
            objDataReader = objCommand2.ExecuteReader()


            If objDataReader.Read() Then
                intLastRecord = objDataReader(0)
                'lblDebug.Text = intLastRecord.ToString

            Else
                'lblDebug.Text = "ID Not found"

            End If

            objDataReader.Close()


        Catch objError As Exception

            lblDebug.Text = "An error occurred: '" & objError.Message & "'" & strSQL


        Finally

            objConnect.Close()

        End Try

        btnAdd.Enabled = False
        'lblDebug.Text = strSQL
        pageLoad(intLastRecord)


    End Sub

    Function checkNull(ByVal obj As Object) As String
        If obj Is DBNull.Value Then
            checkNull = ""
        Else
            checkNull = obj.ToString
        End If
    End Function

    'Function checkNullInt(ByVal obj As Object) As Integer
    '    If obj Is "" Then
    '        checkNullInt = DBNull.Value
    '    Else
    '        checkNullInt = CInt(obj)
    '    End If
    'End Function

    Function CheckNullDate(ByVal obj As Object) As Date
        If obj Is "" Then
            CheckNullDate = Nothing
        Else
            CheckNullDate = CDate(obj)
        End If
    End Function

    Public Function GetAge(ByVal Birthdate As System.DateTime, _
    Optional ByVal AsOf As System.DateTime = #1/1/1700#) _
    As String

        'Don't set second parameter if you want Age as of today

        'Demo 1: get age of person born 2/11/1954
        'Dim objDate As New System.DateTime(1954, 2, 11)
        'Debug.WriteLine(GetAge(objDate))

        'Demo 1: get same person's age 10 years from now
        'Dim objDate As New System.DateTime(1954, 2, 11)
        'Dim objdate2 As System.DateTime
        'objdate2 = Now.AddYears(10)
        'Debug.WriteLine(GetAge(objDate, objdate2))

        Dim iMonths As Integer
        Dim iYears As Integer
        Dim dYears As Decimal
        Dim lDayOfBirth As Long
        Dim lAsOf As Long
        Dim iBirthMonth As Integer
        Dim iAsOFMonth As Integer

        If AsOf = "#1/1/1700#" Then
            AsOf = DateTime.Now
        End If
        lDayOfBirth = DatePart(DateInterval.Day, Birthdate)
        lAsOf = DatePart(DateInterval.Day, AsOf)

        iBirthMonth = DatePart(DateInterval.Month, Birthdate)
        iAsOFMonth = DatePart(DateInterval.Month, AsOf)

        iMonths = DateDiff(DateInterval.Month, Birthdate, AsOf)

        dYears = iMonths / 12

        iYears = Math.Floor(dYears)

        If iBirthMonth = iAsOFMonth Then
            If lAsOf < lDayOfBirth Then
                iYears = iYears - 1
            End If
        End If

        Return iYears

    End Function

    Sub pageLoad(ByVal intLastRecord As Integer)

       

        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New System.Data.SqlClient.SqlConnection(strConnect)

        Dim strSQL As String = "SELECT [RADAR_NO], [RR_NO], [DATE_REG], [NHS_NO], [HOSP_NO], [UKT_NO], [CHI_NO], [SNAME], [SNAME_ALIAS], [FNAME], [DOB], [AGE], [SEX], [ETHNIC_GP], [ADD1], [ADD2], [ADD3], [ADD4], [POSTCODE], [POSTCODE_OLD], [CONSENT], [DATE_BAPN_REG], [CONS_NEPH], [RENAL_UNIT] FROM [tbl_Demographics] WHERE [RADAR_NO] =" & intLastRecord
        Dim objCommand As New System.Data.SqlClient.SqlCommand(strSQL, objConnect)
        Dim objReader As System.Data.SqlClient.SqlDataReader

        Try
            objConnect.Open()
            objReader = objCommand.ExecuteReader()

            While objReader.Read


                txtRadarNo.Text = objReader(0).ToString
                txtRenalRegNo.Text = objReader(1).ToString
                lblDateReg.Text = Format(objReader(2), "dd-MMM-yyyy").ToString
                txtNHSNo.Text = objReader(3).ToString
                txtHospNo.Text = objReader(4).ToString
                txtUKTrNo.Text = objReader(5).ToString
                txtCHINo.Text = objReader(6).ToString
                txtSurname.Text = objReader(7).ToString
                txtFirstName.Text = objReader(9).ToString
                txtDOB.Text = Format(objReader(10), "dd-MMM-yyyy").ToString
                txtAge.Text = objReader(11).ToString
                DropDownList1.SelectedValue = objReader(12)
                DropDownList3.SelectedValue = objReader(13)
                txtAddr.Text = objReader(14).ToString & vbCrLf & objReader(15).ToString & vbCrLf & objReader(16).ToString & vbCrLf & objReader(17).ToString
                txtPostCode.Text = objReader(18).ToString
                If objReader(21) Is DBNull.Value Then
                    txtDateBAPN.Text = ""
                Else
                    txtDateBAPN.Text = Format(objReader(21), "dd-MMM-yyyy").ToString
                End If

                txtCGMC.Text = objReader(22).ToString
                DropDownList2.SelectedValue = objReader(23)


            End While

            objReader.Close()
        Catch objError As Exception

            lblDebug.Text = "An error occurred: '" & objError.Message & "'" & strSQL

        Finally
            objConnect.Close()
        End Try



        'lblDebug.Text = intLastRecord

    End Sub


End Class
