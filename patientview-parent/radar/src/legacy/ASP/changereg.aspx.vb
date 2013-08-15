Imports System
Imports System.IO
Imports System.Text
Imports System.Security.Cryptography
Imports System.Data
Imports System.Data.OleDb
Imports System.Data.SqlClient
Partial Class changereg
    Inherits System.Web.UI.Page

    Protected Sub btnEnter_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnEnter.Click

        Dim strUid As String = txtUid.Text
        Dim strPwd As String = txtPwd.Text


        Dim strSQL As String
        Dim strConnect As String
        Dim strPassCheck As String

        Dim TDES As TripleDES
        TDES = New TripleDES


        strConnect = System.Configuration.ConfigurationManager.ConnectionStrings("RadarConnectionString").ConnectionString
        strSQL = " SELECT [uID], [uPass] FROM [tbl_Users] WHERE [uEmail] = '" & strUid & "'"

        'do this to avoid complicated decryption. uName and email are the same.

        Try

            'create a new connection object and open it
            Dim objConnect As New SqlConnection(strConnect)
            objConnect.Open()

            'create a new command using the connection object and SQL statement
            Dim objCommand As New SqlCommand(strSQL, objConnect)

            'declare a variable to hold the a DataReader object
            Dim objDataReader As SqlDataReader

            'execute the SQL statement
            objDataReader = objCommand.ExecuteReader()

            'check password
            If objDataReader.Read() Then

                strPassCheck = TDES.Decrypt(objDataReader("uPass"))
                Session("userID") = objDataReader("uID")

                If strPassCheck = txtPwd.Text Then

                    pnlTest.visible = False
                    pnlDetail.Visible = True
                    btnEnter.Enabled = False
                    outMsg.Text = ""

                Else

                    outMsg.Text = "The password have not been recognised"

                End If

            Else

                outMsg.Text = "Username not recognised"

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

    End Sub


    Protected Sub btnChange_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnChange.Click

        Dim strPass1 As String = txtNew.Text
        Dim strPass2 As String = txtRepeat.Text


        If strPass1 <> strPass2 Then
            outMsg.Text = "Your passwords do not match, please enter them again"
        Else


            outMsg.Text = ""


            Dim strSQL As String
            Dim strConnect As String
           
            Dim TDES As TripleDES
            TDES = New TripleDES
            Dim bytPass() As Byte = TDES.Encrypt(strPass1)

            strConnect = System.Configuration.ConfigurationManager.ConnectionStrings("RadarConnectionString").ConnectionString
            strSQL = "UPDATE tbl_Users SET [uPass] = @Pass WHERE [uID] = @ID"


            Dim objConnect As New SqlConnection(strConnect)


            'create a new command using the connection object and SQL statement
            Dim objCommand As New SqlCommand(strSQL, objConnect)

            Try
                With objCommand.Parameters

                    .Add(New System.Data.SqlClient.SqlParameter("@Pass", bytPass))
                    .Add(New System.Data.SqlClient.SqlParameter("@ID", Session("userID")))

                End With

                objConnect.Open()
                objCommand.ExecuteNonQuery()
            Finally
                objConnect.Close()
            End Try

            pnlDetail.Visible = False
            outMsg.Text = "Your password has been changed"
            btnChange.Enabled = False
            txtNew.Text = ""
            txtRepeat.Text = ""

        End If
    End Sub

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load

        

    End Sub
End Class



