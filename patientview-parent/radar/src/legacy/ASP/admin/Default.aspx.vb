Imports System
Imports System.IO
Imports System.Text
Imports System.Security.Cryptography
Imports System.Data
Imports System.Data.OleDb
Imports System.Data.SqlClient
Partial Class admin_Default
    Inherits System.Web.UI.Page

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load
        If Session("blnAdminAuthenticated") = True Then
            pnlLogIn.Visible = False
            pnlLoggedIn.Visible = True

        Else
            pnlLogIn.Visible = True
            pnlLoggedIn.Visible = False
        End If

    End Sub

    Protected Sub btnEnter_Click(ByVal sender As Object, ByVal e As System.EventArgs) Handles btnEnter.Click

        Dim strUid As String = CleanStr(txtUid.Text)
        Dim strPwd As String = CleanStr(txtPwd.Text)

        Dim strSQL As String = " SELECT [uPass] FROM [tbl_AdminUsers] WHERE [uEmail] = '" & strUid & "'"
        Dim strConnect As String = System.Configuration.ConfigurationManager.ConnectionStrings("RadarConnectionString").ConnectionString
        Dim strPassCheck As String


        Dim TDES As TripleDES = New TripleDES

        'do this to avoid complicated decryption. uName and email are the same.

        Try

            'create a new connection object and open it
            Using objConnect As New SqlConnection(strConnect)
                objConnect.Open()
                'create a new command using the connection object and SQL statement
                Using objCommand As New SqlCommand(strSQL, objConnect)
                    'declare a variable to hold the a DataReader object
                    Dim objDataReader As SqlDataReader
                    'execute the SQL statement
                    objDataReader = objCommand.ExecuteReader()
                    'check password
                    If objDataReader.Read() Then
                        strPassCheck = TDES.Decrypt(objDataReader("uPass"))
                        If strPassCheck = txtPwd.Text Then
                            Session("blnAdminAuthenticated") = True

                        End If
                    End If
                    'close the DataReader and Connection
                    objDataReader.Close()
                End Using
                objConnect.Close()
            End Using


        Catch objError As Exception

            'display error

            outMsg.Text = "Error accessing the database<br / >" _
            & objError.Message & "<br />" & objError.Source _
            '& strSQL

            Exit Sub ' stop execution

        End Try

        If Session("blnAdminAuthenticated") Then

            Response.Redirect("default.aspx")


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
End Class
