Imports System.Data
Imports System.Data.OleDb
Imports System.Data.SqlClient
Imports System.IO

Partial Class patient_entry2
    Inherits System.Web.UI.Page
    Public insertTag As Boolean = False
    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load

        If Session("blnAuthenticated") = False Then
            Response.Redirect("default.aspx")
        End If



        Select Case Session("mode")

            Case 1 ' entry

                'If FormView1.DefaultMode = FormViewMode.Insert Then

                FormView1.DefaultMode = FormViewMode.Insert
                lblMsg.Text = "Once you have entered the demographic details below you can edit and add to the entry at any time."

                'Dim txtTest As TextBox = Master.FindControl("ContentPlaceHolder1").FindControl("SNAMETextBox")
                'txtTest.Text = Session("unitID")
                'Dim txtTest As TextBox = FindControl("ctl00").FindControl("ContentPlaceHolder1").FindControl("FormView1").FindControl("SNAMETextBox")
                'Dim txtTest As TextBox = FindControl("ctl00_ContentPlaceHolder1_FormView1_SNAMETextBox")
                'txtTest.Text = "test"

                Dim txtRadarNo As TextBox = FormView1.FindControl("RADAR_NOTextBox")
                txtRadarNo.Text = "Auto Allocated"
                'txtRadarNo.Text = Session("unitID")
                Dim txtRegDate As TextBox = FormView1.FindControl("DATE_REGTextBox")
                txtRegDate.Text = (Now().ToShortDateString).ToString

                Dim DropDownList2 As DropDownList = FindControl("ctl00").FindControl("ContentPlaceHolder1").FindControl("FormView1").FindControl("DropDownList2")
                'Dim DropDownList2 As DropDownList = FormView1.FindControl("DropDownList2")
                DropDownList2.SelectedValue = Session("unitID")
                'DropDownList2.SelectedIndex = 2

                'lnkClinical.Enabled = False
                lnk3Months.Enabled = False
                lnkDiagnosis.Enabled = False
                lnkLabResults.Enabled = False
                'lnkTherapy.Enabled = False


                'End If

            Case 2 'edit

                FormView1.DefaultMode = FormViewMode.Edit
                'lnkClinical.Enabled = True
                lnk3Months.Enabled = True
                lnkDiagnosis.Enabled = True
                lnkLabResults.Enabled = True
                'lnkTherapy.Enabled = True

        End Select

        If IsPostBack Then
            Select Case Session("mode")

                Case 1 ' entry

                    If FormView1.DefaultMode = FormViewMode.Insert Then
                        lblMsg.Text = "Once you have entered the demographic details below you can edit and add to the entry at any time."
                        Dim txtRadarNo As TextBox = FormView1.FindControl("RADAR_NOTextBox")
                        txtRadarNo.Text = "Auto Allocated"
                        Dim txtRegDate As TextBox = FormView1.FindControl("DATE_REGTextBox")
                        txtRegDate.Text = (Now().ToShortDateString).ToString
                        'lnkClinical.Enabled = False
                        lnk3Months.Enabled = False
                        lnkDiagnosis.Enabled = False
                        lnkLabResults.Enabled = False
                        'lnkTherapy.Enabled = False
                       
                    End If

                Case 2 'edit

                    FormView1.DefaultMode = FormViewMode.Edit
                    'lnkClinical.Enabled = True
                    lnk3Months.Enabled = True
                    lnkDiagnosis.Enabled = True
                    lnkLabResults.Enabled = True
                    'lnkTherapy.Enabled = True

            End Select
        End If

        'If FormView1.CurrentMode = FormViewMode.ReadOnly Then
        'Dim txtRadarNo As TextBox = FormView1.FindControl("RADAR_NOTextbox")
        'lblDebug.Text = "ID = " & FormView1.DataKey.Value.ToString
        'End If

    End Sub

    Sub FormView1_ItemInserted(ByVal sender As Object, ByVal e As FormViewInsertedEventArgs) Handles FormView1.ItemInserted

        Dim lblNew As Label = FormView1.FindControl("lblNew")

        If e.Exception Is Nothing Then

            If e.AffectedRows = 1 Then
                lblMsg.Text = "Patient entered successfully. Other data can now be entered using the tabs above. Details below can be edited."
                insertTag = True
                FormView1.PageIndex = FormView1.PageCount
                FormView1.DefaultMode = FormViewMode.Edit
                'Dim txtID As TextBox = FormView1.FindControl("RADAR_NOTextbox")
                'Dim intID As Integer = getID()
                'lnkDiagnosis.NavigateUrl = "patient-entry3.aspx?id=" & intID
                'Dim intID As String = e.Values("SNAME")
                'lblDebug.Text = "Inserted ID = " & intID
                'lblDebug.Text = "ID = " & FormView1.DataKey.Value.ToString

                'lnkClinical.Enabled = True
                lnk3Months.Enabled = True
                lnkDiagnosis.Enabled = True
                lnkLabResults.Enabled = True
                'lnkTherapy.Enabled = True
                Session("mode") = 2
            Else
                lblMsg.Text = "An error ocurred during the add operation"
                e.KeepInInsertMode = True
            End If
        End If

    End Sub

    'Sub FormView1_PreRender(ByVal sender As Object, ByVal e As EventArgs) Handles FormView1.PreRender
    '    If insertTag = True Then
    '        'Dim intID As Integer = getID()

    '        FormView1.PageIndex = FormView1.PageCount
    '        FormView1.DefaultMode = FormViewMode.ReadOnly
    '    End If
    'End Sub

    Protected Sub NewButton_Click(ByVal sender As Object, ByVal e As System.EventArgs)

        lblMsg.Text = "Once you have entered the demographic details below you can edit and add to the entry at any time."
        Dim txtRadarNo As TextBox = FormView1.FindControl("RADAR_NOTextbox")
        txtRadarNo.Text = "Auto Allocated"
        Dim txtRegDate As TextBox = FormView1.FindControl("DATE_REGTextbox")
        txtRegDate.Text = (Now().ToShortDateString).ToString

    End Sub


    Protected Sub EditButton_Click(ByVal sender As Object, ByVal e As System.EventArgs)
        lblMsg.Text = ""
    End Sub

    Sub FormView1_ModeChanged(ByVal sender As Object, ByVal e As EventArgs) Handles FormView1.ModeChanged

        Select Case FormView1.CurrentMode
            Case FormViewMode.Insert
                Dim txtRegDate As TextBox = FormView1.FindControl("DATE_REGTextbox")
                txtRegDate.Text = (Now().ToShortDateString).ToString
        End Select

        'If FormView1.CurrentMode = FormViewMode.ReadOnly Then
        '    Dim txtRadarNo As TextBox = FormView1.FindControl("RADAR_NOTextbox")
        '    lblDebug.Text = "ID = " & FormView1.DataKey.Value.ToString
        'End If


        'If FormView1.CurrentMode = FormViewMode.Insert Then
        '    lblMsg.Text = "Once you have entered the demographic details below you can edit and add to the entry at any time."
        '    Dim txtRadarNo As TextBox = FormView1.FindControl("RADAR_NOTextbox")
        '    txtRadarNo.Text = "Auto Allocated"
        '    Dim txtRegDate As TextBox = FormView1.FindControl("DATE_REGTextbox")
        '    txtRegDate.Text = (Now().ToShortDateString).ToString
        'End If


    End Sub

    Function getID() As Integer
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New System.Data.SqlClient.SqlConnection(strConnect)
        Dim strSQL As String = "SELECT @@IDENTITY FROM [tbl_Demographics]"
        Dim objCommand As New System.Data.SqlClient.SqlCommand(strSQL, objConnect)

        Try

            'declare a variable to hold the a DataReader object
            Dim objDataReader As SqlDataReader
            objConnect.Open()
            'execute the SQL statement
            objDataReader = objCommand.ExecuteReader()


            If objDataReader.Read() Then
                getID = CInt(objDataReader(0))
                lblDebug.Text = "Inserted ID = " & objDataReader(0)

            Else
                lblDebug.Text = "ID Not found"

            End If

            objDataReader.Close()


        Catch objError As Exception

            lblDebug.Text = "An error occurred: '" & objError.Message & "'" & strSQL


        Finally

            objConnect.Close()

        End Try

        Return getID

    End Function

    'Protected Sub patient_Inserted(ByVal sender As Object, ByVal e As SqlDataSourceStatusEventArgs)
    '    If e.Exception Is Nothing Then
    '        lblDebug.Text = e.Command.Parameters("@NewID").Value
    '    End If
    'End Sub

    'Protected Sub FormView1_ItemInserting(ByVal sender As Object, ByVal e As FormViewInsertEventArgs) Handles FormView1.ItemInserting
    '    lblDebug.Text = "ID = " & FormView1.DataKey.Value.ToString
    'End Sub

    Protected Sub sqlDataSource1_Inserted(ByVal sender As Object, ByVal e As System.Web.UI.WebControls.SqlDataSourceStatusEventArgs) Handles SqlDataSource1.Inserted
        Dim newID As Integer = Convert.ToInt32(e.Command.Parameters("@newID").Value)
        'lblDebug.Text = newID.ToString

        Session("patientID") = newID

        'lnkDiagnosis.NavigateUrl = "diagnosis.aspx?id=" & newID
        'lnkClinical.NavigateUrl = "patient-entry4.aspx?id=" & newID
        'lnkLabResults.NavigateUrl = "patient-entry5.aspx?id=" & newID
        'lnkTherapy.NavigateUrl = "patient-entry6.aspx?id=" & newID
        'lnk3Months.NavigateUrl = "patient-entry7.aspx?id=" & newID

        'lnkClinical.Enabled = True
        'lnk3Months.Enabled = True
        'lnkDiagnosis.Enabled = True
        'lnkLabResults.Enabled = True

        'add record to other tables

        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New System.Data.SqlClient.SqlConnection(strConnect)
        Dim strSQL As String = "INSERT INTO [tbl_Diagnosis] ([RADAR_NO]) VALUES (@RADAR_NO ); INSERT INTO [tbl_ClinicalData] ([RADAR_NO]) VALUES (@RADAR_NO ); INSERT INTO [tbl_LabData] ([RADAR_NO]) VALUES (@RADAR_NO); INSERT INTO [tbl_6Month] ([RADAR_NO]) VALUES (@RADAR_NO ); INSERT INTO [tbl_Therapy] ([RADAR_NO]) VALUES (@RADAR_NO)"
        Dim objCommand As New System.Data.SqlClient.SqlCommand(strSQL, objConnect)

        Try
            With objCommand.Parameters
                .Add(New System.Data.SqlClient.SqlParameter("@RADAR_NO", newID))
                '.Add(New System.Data.SqlClient.SqlParameter("@DIAG", DBNull.Value))
            End With

            objConnect.Open()
            objCommand.ExecuteNonQuery()

        Catch objError As Exception

            lblDebug.Text = "An error occurred: '" & objError.Message & "'" & strSQL
            'lblDebug.Text = newID.ToString

        Finally

            objConnect.Close()

        End Try

    End Sub

    Protected Sub FormView1_ItemUpdate(ByVal sender As Object, ByVal e As FormViewUpdatedEventArgs) Handles FormView1.ItemUpdated
        lblUpdate.Text = "<strong>Update successful: </strong>" & Now.ToShortTimeString
    End Sub

End Class
