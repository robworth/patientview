Imports System.Data
Imports System.Data.OleDb
Imports System.Data.SqlClient
Imports System.IO

Partial Class clinical
    Inherits System.Web.UI.Page

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load

        If Not Session("blnAuthenticated") Then
            Response.Redirect("default.aspx")
        End If

        FormView1.DefaultMode = FormViewMode.Edit

        Dim Outer_CP As ContentPlaceHolder = Me.Master.Master.FindControl("ContentPlaceholder1")
        Dim Inner_CP As ContentPlaceHolder = Outer_CP.FindControl("childContent1")
        Dim FrmView As FormView = Inner_CP.FindControl("FormView1")

        Dim OPTHALMCheckBox As CheckBox = FrmView.FindControl("OPTHALMCheckBox")
        Dim OPTHALM_DETAILTextBox As TextBox = FrmView.FindControl("OPTHALM_DETAILTextBox")
        Dim lblU As Label = FrmView.FindControl("lblU")
        Dim lblRash As Label = FrmView.FindControl("lblRash")
        Dim lblOpthal As Label = FrmView.FindControl("lblOpthal")
        Dim DIAGTextBox As TextBox = FrmView.FindControl("DIAGTextBox")
        Dim DropDownList5 As DropDownList = FrmView.FindControl("DropDownList5")
        Dim RASHTextBox As TextBox = FrmView.FindControl("RASHTextBox")

        Dim txtDiagText As TextBox = FrmView.FindControl("txtDiagText")

        If DIAGTextBox.Text = "" Then
            txtDiagText.Text = ""
        Else
            txtDiagText.Text = GetDiagnosis(CInt(DIAGTextBox.Text))
        End If



        'If IsPostBack Then

        If txtDiagText.Text = "MCGN" Then
            lblU.Visible = True
            lblRash.Visible = True
            lblOpthal.Visible = True
            OPTHALMCheckBox.Visible = True
            DropDownList5.Visible = True
            RASHTextBox.Visible = True
        Else
            lblU.Visible = False
            lblRash.Visible = False
            lblOpthal.Visible = False
            OPTHALMCheckBox.Visible = False
            DropDownList5.Visible = False
            RASHTextBox.Visible = False
        End If

        If OPTHALMCheckBox.Checked = True Then
            OPTHALM_DETAILTextBox.Visible = True

        ElseIf OPTHALMCheckBox.Checked = False Then

            OPTHALM_DETAILTextBox.Text = ""
            OPTHALM_DETAILTextBox.Visible = False
        End If

        'End If

        

    End Sub

    'Protected Sub FormView1_ItemUpdated(ByVal sender As Object, ByVal e As FormViewUpdatedEventArgs) Handles FormView1.ItemUpdated
    '    'Dim lblUpdate As Label = FormView1.FindControl("lblUpdate")
    '    lblUpdate.Text = "Update successful:&nbsp;" & (Now().ToLongTimeString).ToString
    '    lblUpdate.Focus()
    '    'FormView1.DefaultMode = FormViewMode.ReadOnly
    'End Sub

    Protected Sub FormView1_ItemUpdated(ByVal sender As Object, ByVal e As FormViewUpdatedEventArgs) Handles FormView1.ItemUpdated

        Dim Outer_CP As ContentPlaceHolder = Me.Master.Master.FindControl("ContentPlaceholder1")
        Dim Inner_CP As ContentPlaceHolder = Outer_CP.FindControl("childContent1")
        Dim FrmView As FormView = Inner_CP.FindControl("FormView1")


        Dim txtSys As TextBox = FrmView.FindControl("SYS_BPTextBox")
        Dim txtDia As TextBox = FrmView.FindControl("DIA_BPTextBox")
        Dim txtMap As TextBox = FrmView.FindControl("MAP_BPTextBox")
        Dim lblID As Label = FrmView.FindControl("cIDLabel1")
        Dim cID As Integer = CInt(lblID.Text)

        If txtSys.Text <> "" And txtDia.Text <> "" Then

            Dim intSys As Integer = CInt(txtSys.Text)
            Dim intDia As Integer = CInt(txtDia.Text)
            Dim intMap As Integer = ((2 * intDia) + intSys) / 3

            upDateMAP(intMap, cID)

        End If

        If e.AffectedRows = 1 Then
            lblUpdate.Text = "<strong>Update successful:</strong>&nbsp;" & Now().ToLongTimeString
        Else
            lblUpdate.Text = e.Exception.Message
        End If


        Dim OPTHALMCheckBox As CheckBox = FrmView.FindControl("OPTHALMCheckBox")
        Dim OPTHALM_DETAILTextBox As TextBox = FrmView.FindControl("OPTHALM_DETAILTextBox")
        Dim lblU As Label = FrmView.FindControl("lblU")
        Dim lblRash As Label = FrmView.FindControl("lblRash")
        Dim lblOpthal As Label = FrmView.FindControl("lblOpthal")
        Dim DIAGTextBox As TextBox = FrmView.FindControl("DIAGTextBox")
        Dim DropDownList5 As DropDownList = FrmView.FindControl("DropDownList5")
        Dim RASHTextBox As TextBox = FrmView.FindControl("RASHTextBox")

        Dim txtDiagText As TextBox = FrmView.FindControl("txtDiagText")

        If DIAGTextBox.Text = "" Then
            txtDiagText.Text = ""
        Else
            txtDiagText.Text = GetDiagnosis(CInt(DIAGTextBox.Text))
        End If


        If txtDiagText.Text = "MCGN" Then
            lblU.Visible = True
            lblRash.Visible = True
            lblOpthal.Visible = True
        Else
            lblU.Visible = False
            lblRash.Visible = False
            lblOpthal.Visible = False
            OPTHALMCheckBox.Visible = False
            DropDownList5.Visible = False
            RASHTextBox.Visible = False
        End If

        If OPTHALMCheckBox.Checked = True Then
            OPTHALM_DETAILTextBox.Visible = True

        ElseIf OPTHALMCheckBox.Checked = False Then

            OPTHALM_DETAILTextBox.Text = ""
            OPTHALM_DETAILTextBox.Visible = False
        End If

       

    End Sub

    Protected Sub upDateMAP(ByVal intMap As Integer, ByVal cID As Integer)

        Dim i As Integer

        For i = 1 To 10000

        Next

        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim objConnect As New System.Data.SqlClient.SqlConnection(strConnect)
        Dim strSQL As String = "UPDATE [tbl_ClinicalData] SET [MAP_BP] = @MAP_BP WHERE [cID] = @cID "
        Dim objCommand As New System.Data.SqlClient.SqlCommand(strSQL, objConnect)

        With objCommand.Parameters
            .Add(New System.Data.SqlClient.SqlParameter("@MAP_BP", intMap))
            .Add(New System.Data.SqlClient.SqlParameter("@cID", cID))
        End With

        Dim intRowsAffected As Integer

        Try
            objConnect.Open()
            intRowsAffected = objCommand.ExecuteNonQuery()

        Catch objError As Exception

            lblDebug.Text = "An error occurred: '" & objError.Message & "'"

        Finally
            objConnect.Close()
        End Try


    End Sub

    Function GetDiagnosis(ByVal diagID As Integer) As String

        Select Case diagID
            Case "1"
                GetDiagnosis = "FSGS"
            Case "2"
                GetDiagnosis = "MCGN/DDD"
            Case Else
                GetDiagnosis = "Not specified"
        End Select

    End Function

    'Protected Sub FormView1_ItemUpdating(ByVal sender As Object, ByVal e As FormViewUpdateEventArgs) Handles FormView1.ItemUpdating

    '    If e.Keys("OPTHALM") Is DBNull.Value Then
    '        e.Keys("OPTHALM") = 0
    '    End If

    '    If e.Keys("URTICARIA") Is DBNull.Value Then
    '        e.Keys("URTICARIA") = 0
    '    End If


    'End Sub

End Class
