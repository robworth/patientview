Imports System.Data
Imports System.Data.OleDb
Imports System.Data.SqlClient
Imports System.Security.Cryptography
Imports System.IO
Partial Class admin_patient_detail
    Inherits System.Web.UI.Page



    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load

        Dim intID As Integer = Request.QueryString("p")

        If Not IsPostBack Then
            PageFill(intID)
        End If



    End Sub



    Protected Sub btnUpdate_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnUpdate.Click

        Dim intID As Integer = Request.QueryString("p")
        Dim TDES As New TripleDES()
        Dim strPass As String = txtPWD.Text
        Dim bytPass() As Byte = TDES.Encrypt(strPass)

        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        'Dim strSQL As String = "UPDATE [tbl_Patient_Users] SET [RADAR_NO] = @RADAR_NO, [pUserName] = @pUserName, [pPassWord] = @pPassWord, [pDOB] = @pDOB, [pDateReg] = @pDateReg WHERE [pID] = @pID"
        Dim strSQL As String = "UPDATE [tbl_Patient_Users] SET [pUserName] = @pUserName, [pPassWord] = @pPassWord WHERE [pID] = @pID"
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        Try

            With objCommand.Parameters

                .Add(New SqlParameter("@pPassWord", bytPass))
                .Add(New SqlParameter("@pUserName", txtUSERNAME.Text))
                .Add(New SqlParameter("@pID", intID))

            End With

            objConnect.Open()
            objCommand.ExecuteNonQuery()

        Catch objError As Exception

            lblDebug.Text = "An error occurred: '" & objError.Message & "'" & strSQL
            Exit Sub
        Finally

            objConnect.Close()
            lblUpdate.Text = "<strong>Update successful:</strong>&nbsp;&nbsp;" & Now().ToShortTimeString
            'lblDebug.Text = txtUSERNAME.Text

        End Try

        PageFill(intID)

    End Sub

    Shared Function GetDecrypt(ByVal encName As Byte()) As String

        Dim TDES As TripleDES = New TripleDES()
        GetDecrypt = TDES.Decrypt(encName)

    End Function

    Protected Sub btCancel_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btCancel.Click
        Response.Redirect("patients.aspx")
    End Sub

    Protected Sub PageFill(ByVal intID As Integer)


        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RADARConnectionString").ConnectionString
        Dim strSQL As String = "SELECT tbl_Patient_Users.pID, tbl_Patient_Users.RADAR_NO, tbl_Patient_Users.pUserName, tbl_Patient_Users.pPassWord, tbl_Patient_Users.pDOB, tbl_Patient_Users.pDateReg, tbl_Demographics.SNAME, tbl_Demographics.FNAME FROM tbl_Patient_Users INNER JOIN tbl_Demographics ON tbl_Patient_Users.RADAR_NO = tbl_Demographics.RADAR_NO WHERE tbl_Patient_Users.pID = " & intID
        Dim objConnect As New SqlConnection(strConnect)
        Dim objCommand As New SqlCommand(strSQL, objConnect)

        Try

            'declare a variable to hold the a DataReader object
            Dim objDataReader As SqlDataReader
            objConnect.Open()
            'execute the SQL statement
            objDataReader = objCommand.ExecuteReader()


            If objDataReader.Read() Then

                lblRDAR_NO.Text = objDataReader("RADAR_NO")
                txtFNAME.Text = GetDecrypt(objDataReader("FNAME"))
                txtSNAME.Text = GetDecrypt(objDataReader("SNAME"))
                txtUSERNAME.Text = objDataReader("pUserName")
                txtPWD.Text = GetDecrypt(objDataReader("pPassWord"))
                txtDOB.Text = FormatDateTime(CommonClass.chkNull(objDataReader("pDOB")))
                If CommonClass.chkNull(objDataReader("pDateReg")) <> "" Then
                    txtDATE_REG.Text = FormatDateTime(CommonClass.chkNull(objDataReader("pDateReg")))
                End If

            End If

        Catch objError As Exception
            lblDebug.Text = objError.Message
        Finally
            objConnect.Close()
        End Try


    End Sub
End Class
