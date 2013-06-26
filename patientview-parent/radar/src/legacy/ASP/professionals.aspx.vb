Imports System
Imports System.Data.SqlClient
Imports DevExpress.XtraCharts
Imports DevExpress.XtraCharts.Web

Partial Class professionals
    Inherits System.Web.UI.Page

    


    Protected Sub btnEnter_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnEnter.Click

        Dim strUid As String = CleanStr(txtUid.Text)
        Dim strPwd As String = CleanStr(txtPwd.Text)

        Dim strSQL As String
        Dim strConnect As String
        Dim strPassCheck As String


        Dim TDES As TripleDES
        TDES = New TripleDES


        strConnect = System.Configuration.ConfigurationManager.ConnectionStrings("RadarConnectionString").ConnectionString
        strSQL = " SELECT [uID], [uPass], [uCentre] FROM [tbl_Users] WHERE [uEmail] = '" & strUid & "'"

        'do this to avoid complicated decryption. uName and email are the same.

        Try

            Dim objConnect As New SqlConnection(strConnect)
            objConnect.Open()

            Dim objCommand As New SqlCommand(strSQL, objConnect)

            Dim objDataReader As SqlDataReader

            objDataReader = objCommand.ExecuteReader()

            'check password
            If objDataReader.Read() Then

                strPassCheck = TDES.Decrypt(objDataReader("uPass"))

                If strPassCheck = txtPwd.Text Then

                    Session("blnAuthenticated") = True
                    Session("unitID") = objDataReader("uCentre")
                    Session("userID") = objDataReader("uID")
                    Session("dtRecord") = ""
                    Session.Timeout = "30"

                    
                End If

            End If


            'close the DataReader and Connection

            objDataReader.Close()
            objConnect.Close()


        Catch objError As Exception

            'display error

            outMsg.Text = "Error accessing the database<br / >" _
            & objError.Message & "<br />" & objError.Source _
            '& strSQL

            Exit Sub ' stop execution

        End Try

        If Session("blnAuthenticated") Then

            Response.Redirect("professionals.aspx")


        Else
            'outMsg.Text = strPassCheck
            warnMsg.Text = "The details you entered are incorrect.<br/><br/> Please try again or contact the RRA Office.<br/>"

            warnMsg.Visible = True

        End If


    End Sub

    Public Shared Function CleanStr(ByVal strDirty As String) As String

        CleanStr = Replace(strDirty, "'", "''")
        CleanStr = Replace(CleanStr, "|", "''")

    End Function


    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load

        'Form.Attributes.Add("autocomplete", "off")

        WebChartControl.ProcessImageRequest(Me)

        If Session("blnAuthenticated") = True Then
            pnlLogIn.Visible = False
            pnlLoggedIn.Visible = True
            lblReg.Text = GetRegistrants(Session("unitID"))
            lblCentre.Text = GetCentre(Session("unitID"))
        Else
            pnlLogIn.Visible = True
            pnlLoggedIn.Visible = False
        End If

        If Request.ServerVariables("SERVER_NAME") = "www.renal-test.org" Then
            pnlDemo.Visible = True
            'txtUid.Enabled = False
            'txtPwd.Enabled = False
            'btnEnter.Enabled = False
            lnkRecover.Visible = False
            lnkChange.Visible = False
            lnkBkmark.Visible = False
        End If

    End Sub

    Function GetRegistrants(ByVal idCentre As Integer) As Object


        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RadarConnectionString").ConnectionString
        Dim strSQL As String = "SELECT COUNT(RENAL_UNIT) as Registrants FROM [tbl_Demographics] WHERE [RENAL_UNIT] = " & idCentre
        'Dim strSQL As String = "SELECT COUNT(*) AS Registrants FROM [tbl_Demographics]"

        Try

            Dim objConnect As New SqlConnection(strConnect)
            objConnect.Open()

            Dim objCommand As New SqlCommand(strSQL, objConnect)

            Dim objDataReader As SqlDataReader

            objDataReader = objCommand.ExecuteReader()

            If objDataReader.Read() Then

                GetRegistrants = objDataReader("Registrants")
                'lblDebug.Text = strSQL
            Else
                GetRegistrants = "No patients yet registered"

            End If

            objDataReader.Close()
            objConnect.Close()

        Catch objError As Exception
            lblDebug.Text = objError.Message & "<br/>" & objError.Source & "<br/>" & strSQL
            GetRegistrants = ""

        End Try


    End Function

    Function GetCentre(ByVal idCentre As Integer) As String
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RadarConnectionString").ConnectionString
        Dim strSQL As String = "SELECT [cName] FROM [tbl_Centres] WHERE [cID] = " & idCentre

        Try

            Dim objConnect As New SqlConnection(strConnect)
            objConnect.Open()

            Dim objCommand As New SqlCommand(strSQL, objConnect)

            Dim objDataReader As SqlDataReader

            objDataReader = objCommand.ExecuteReader()

            If objDataReader.Read() Then

                GetCentre = objDataReader("cName")
                'lblDebug.Text = strSQL
            Else
                GetCentre = ""

            End If

            objDataReader.Close()
            objConnect.Close()

        Catch objError As Exception
            lblDebug.Text = objError.Message & "<br/>" & objError.Source & "<br/>" & strSQL
            GetCentre = ""

        End Try
    End Function

    'Protected Sub btnGraph_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnGraph.Click

    '    Try
    '        Dim WebChartControl2 As New WebChartControl

    '        Dim series1 As New Series("FSGS", ViewType.Bar)
    '        series1.Points.Add(New SeriesPoint("FSGS", New Double() {GetPatientsByDisease(Session("unitID"), 1)}))
    '        Dim series2 As New Series("MPGN", ViewType.Bar)
    '        series2.Points.Add(New SeriesPoint("MPGN", New Double() {GetPatientsByDisease(Session("unitID"), 2)}))

    '        WebChartControl2.Series.Add(series1)
    '        WebChartControl2.Series.Add(series2)
    '        WebChartControl2.ToolTip = "Graph of registered patients by disease"

    '        regPatients.Controls.Add(WebChartControl2)
    '        'lblDebug.Text = Session("unitID")
    '        lblCentres.Text = "Total number of registered patients by disease"
    '        lblCentres.Visible = True
    '    Catch ex As Exception
    '        lblDebug.Text = ex.Message

    '    End Try


    'End Sub

    Protected Sub btnGraph_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnGraph.Click

        Dim strSQL As String = "SELECT [cID], [cName] FROM [tbl_Centres]"

        Try
            Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RadarConnectionString").ConnectionString
            Dim objConnect As New SqlConnection(strConnect)
            objConnect.Open()

            Dim objCommand As New SqlCommand(strSQL, objConnect)

            Dim objDataReader As SqlDataReader

            objDataReader = objCommand.ExecuteReader()

            If objDataReader.Read() Then

                Dim WebChartControl2 As New WebChartControl
                WebChartControl2.BeginInit()
                Dim series1 As New Series("SRNS", ViewType.Bar)
                Dim series2 As New Series("MPGN", ViewType.Bar)


                While objDataReader.Read



                    Try

                        'series1.Points.Add(New SeriesPoint(objDataReader("cID"), New Double() {GetPatientsByDisease(objDataReader("cID"), 1)}))
                        'series2.Points.Add(New SeriesPoint(objDataReader("cID"), New Double() {GetPatientsByDisease(objDataReader("cID"), 2)}))

                        series1.Points.Add(New SeriesPoint("Belfast", New Double() {GetPatientsByDisease(1, 1)}))
                        series1.Points.Add(New SeriesPoint("Birmingham", New Double() {GetPatientsByDisease(2, 1)}))
                        series1.Points.Add(New SeriesPoint("Bristol", New Double() {GetPatientsByDisease(3, 1)}))
                        series1.Points.Add(New SeriesPoint("Cardiff", New Double() {GetPatientsByDisease(4, 1)}))
                        series1.Points.Add(New SeriesPoint("Glasgow", New Double() {GetPatientsByDisease(5, 1)}))
                        series1.Points.Add(New SeriesPoint("Leeds", New Double() {GetPatientsByDisease(6, 1)}))
                        series1.Points.Add(New SeriesPoint("Liverpool - Alder Hey", New Double() {GetPatientsByDisease(7, 1)}))
                        series1.Points.Add(New SeriesPoint("London Evelina", New Double() {GetPatientsByDisease(8, 1)}))
                        series1.Points.Add(New SeriesPoint("London GOS", New Double() {GetPatientsByDisease(9, 1)}))
                        series1.Points.Add(New SeriesPoint("Manchester", New Double() {GetPatientsByDisease(10, 1)}))
                        series1.Points.Add(New SeriesPoint("Newcastle", New Double() {GetPatientsByDisease(11, 1)}))
                        series1.Points.Add(New SeriesPoint("Nottingham", New Double() {GetPatientsByDisease(12, 1)}))
                        series1.Points.Add(New SeriesPoint("Southampton", New Double() {GetPatientsByDisease(13, 1)}))

                        series2.Points.Add(New SeriesPoint("Belfast", New Double() {GetPatientsByDisease(1, 2)}))
                        series2.Points.Add(New SeriesPoint("Birmingham", New Double() {GetPatientsByDisease(2, 2)}))
                        series2.Points.Add(New SeriesPoint("Bristol", New Double() {GetPatientsByDisease(3, 2)}))
                        series2.Points.Add(New SeriesPoint("Cardiff", New Double() {GetPatientsByDisease(4, 2)}))
                        series2.Points.Add(New SeriesPoint("Glasgow", New Double() {GetPatientsByDisease(5, 2)}))
                        series2.Points.Add(New SeriesPoint("Leeds", New Double() {GetPatientsByDisease(6, 2)}))
                        series2.Points.Add(New SeriesPoint("Liverpool - Alder Hey", New Double() {GetPatientsByDisease(7, 2)}))
                        series2.Points.Add(New SeriesPoint("London Evelina", New Double() {GetPatientsByDisease(8, 2)}))
                        series2.Points.Add(New SeriesPoint("London GOS", New Double() {GetPatientsByDisease(9, 2)}))
                        series2.Points.Add(New SeriesPoint("Manchester", New Double() {GetPatientsByDisease(10, 2)}))
                        series2.Points.Add(New SeriesPoint("Newcastle", New Double() {GetPatientsByDisease(11, 2)}))
                        series2.Points.Add(New SeriesPoint("Nottingham", New Double() {GetPatientsByDisease(12, 2)}))
                        series2.Points.Add(New SeriesPoint("Southampton", New Double() {GetPatientsByDisease(13, 2)}))



                    Catch ex As Exception
                        lblDebug.Text = ex.Message

                    End Try



                End While


                WebChartControl2.Series.Add(series1)
                WebChartControl2.Series.Add(series2)

                WebChartControl2.EndInit()

                WebChartControl2.ToolTip = "Graph of registered patients by disease"
                WebChartControl2.Width = 700
                WebChartControl2.Height = 250
                WebChartControl2.AlternateText = "Graph of registered patients by disease"



                'lblDebug.Text = Session("unitID")
                lblCentres.Text = "Total number of registered patients by unit"
                lblCentres.Visible = True

                CType(WebChartControl2.Diagram, XYDiagram).AxisX.Label.Angle = 30
                CType(WebChartControl2.Diagram, XYDiagram).AxisX.Label.Antialiasing = True
                CType(WebChartControl2.Diagram, XYDiagram).Margins.Top = 20
                CType(WebChartControl2.Diagram, XYDiagram).Margins.Bottom = 80

                btnGraph.Visible = False
                regPatients.Controls.Add(WebChartControl2)


            End If

            objDataReader.Close()
            objConnect.Close()

        Catch objError As Exception
            lblDebug.Text = objError.Message & "<br/>" & objError.Source & "<br/>" & strSQL

        Finally


        End Try




    End Sub

    Function GetPatientsByDisease(ByRef intCentre As Integer, ByVal intDisease As Integer) As Integer

        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RadarConnectionString").ConnectionString
        Dim strSQL As String = "SELECT COUNT(DISTINCT tbl_Diagnosis.dID) AS CountPatients FROM tbl_Diagnosis INNER JOIN tbl_Demographics ON tbl_Diagnosis.RADAR_NO = tbl_Demographics.RADAR_NO CROSS JOIN tbl_Centres WHERE (tbl_Demographics.RENAL_UNIT = " & intCentre & ") AND (tbl_Diagnosis.DIAG = " & intDisease & ")"
        'Dim strSQL As String = "SELECT COUNT(DISTINCT tbl_Diagnosis.dID) AS CountPatients FROM tbl_Diagnosis WHERE (tbl_Diagnosis.DIAG = " & intDisease & ")"

        Try

            Dim objConnect As New SqlConnection(strConnect)
            objConnect.Open()

            Dim objCommand As New SqlCommand(strSQL, objConnect)

            Dim objDataReader As SqlDataReader

            objDataReader = objCommand.ExecuteReader()

            If objDataReader.Read() Then

                GetPatientsByDisease = objDataReader("CountPatients")
                'lblDebug.Text = strSQL
            Else
                GetPatientsByDisease = 0

            End If

            objDataReader.Close()
            objConnect.Close()

        Catch objError As Exception
            lblDebug.Text = objError.Message & "<br/>" & objError.Source & "<br/>" & strSQL
            GetPatientsByDisease = 0

        End Try

    End Function

End Class
