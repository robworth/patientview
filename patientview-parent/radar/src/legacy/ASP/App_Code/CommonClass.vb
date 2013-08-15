Imports Microsoft.VisualBasic
Imports System.Drawing

Public Class CommonClass

    Public Shared Sub DisableControls(ByVal c As Control)
        If TypeOf c Is WebControl Then
            CType(c, WebControl).Enabled = False
            CType(c, WebControl).ForeColor = Color.Black
        End If

        For Each child As Control In c.Controls
            DisableControls(child)
        Next

    End Sub

    Public Shared Sub EmptyTexBoxes(ByVal c As Control)

        If TypeOf c Is TextBox Then
            CType(c, TextBox).Text = ""
        End If

        For Each child As Control In c.Controls
            EmptyTexBoxes(child)
        Next

    End Sub

    Public Shared Sub ClearRadioButtons(ByVal c As Control)

        If TypeOf c Is RadioButton Then
            CType(c, RadioButton).Checked = False
        End If

        For Each child As Control In c.Controls
            ClearRadioButtons(child)
        Next

    End Sub

    Public Shared Function GetDiagnosis(ByVal diagID As Object) As String

        If diagID IsNot DBNull.Value Then

            Select Case diagID

                Case "1"
                    GetDiagnosis = "SRNS"
                Case "2"
                    GetDiagnosis = "MPGN/DDD"
                Case Else
                    GetDiagnosis = "Not specified"

            End Select

        Else

            GetDiagnosis = "-"

        End If

    End Function

    Public Shared Function chkNullSave(ByVal value As Object) As Object

        chkNullSave = IIf(String.Compare(value, "", False) = 0, DBNull.Value, value.ToString)

    End Function

    Public Shared Function chkNullIntSave(ByVal value As Object) As Object

        If value = "" Then
            chkNullIntSave = DBNull.Value
        Else
            chkNullIntSave = CInt(value)
        End If

    End Function

    Shared Function chkNull(ByVal value As Object) As String

        If value Is DBNull.Value Then
            chkNull = ""
        Else
            chkNull = value.ToString
        End If

    End Function

End Class
