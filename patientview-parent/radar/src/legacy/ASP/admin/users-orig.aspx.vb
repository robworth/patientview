Imports System
Imports System.IO
Imports System.Text
Imports System.Security.Cryptography

Partial Class admin_users
    Inherits System.Web.UI.Page

    Protected Sub Page_Load(ByVal sender As Object, ByVal e As System.EventArgs) Handles Me.Load
        
    End Sub

    Function GetDecrypt(ByVal encName As Byte()) As String

        Dim TDES As TripleDES
        TDES = New TripleDES()
        GetDecrypt = TDES.Decrypt(encName)

    End Function
End Class

'Public Class TripleDES

'    Private key() As Byte = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24}
'    Private iv() As Byte = {65, 110, 68, 26, 69, 178, 200, 219}

'    Public Function Encrypt(ByVal plainText As String) As Byte()
'        ' Declare a UTF8Encoding object so we may use the GetByte 
'        ' method to transform the plainText into a Byte array. 
'        Dim utf8encoder As UTF8Encoding = New UTF8Encoding()
'        Dim inputInBytes() As Byte = utf8encoder.GetBytes(plainText)

'        ' Create a new TripleDES service provider 
'        Dim tdesProvider As TripleDESCryptoServiceProvider = New TripleDESCryptoServiceProvider()

'        ' The ICryptTransform interface uses the TripleDES 
'        ' crypt provider along with encryption key and init vector 
'        ' information 
'        Dim cryptoTransform As ICryptoTransform = tdesProvider.CreateEncryptor(Me.key, Me.iv)

'        ' All cryptographic functions need a stream to output the 
'        ' encrypted information. Here we declare a memory stream 
'        ' for this purpose. 
'        Dim encryptedStream As MemoryStream = New MemoryStream()
'        Dim cryptStream As CryptoStream = New CryptoStream(encryptedStream, cryptoTransform, CryptoStreamMode.Write)

'        ' Write the encrypted information to the stream. Flush the information 
'        ' when done to ensure everything is out of the buffer. 
'        cryptStream.Write(inputInBytes, 0, inputInBytes.Length)
'        cryptStream.FlushFinalBlock()
'        encryptedStream.Position = 0

'        ' Read the stream back into a Byte array and return it to the calling 
'        ' method. 
'        Dim result(encryptedStream.Length - 1) As Byte
'        encryptedStream.Read(result, 0, encryptedStream.Length)
'        cryptStream.Close()
'        Return result
'    End Function

'    Public Function Decrypt(ByVal inputInBytes() As Byte) As String
'        ' UTFEncoding is used to transform the decrypted Byte Array 
'        ' information back into a string. 
'        Dim utf8encoder As UTF8Encoding = New UTF8Encoding()
'        Dim tdesProvider As TripleDESCryptoServiceProvider = New TripleDESCryptoServiceProvider()

'        ' As before we must provide the encryption/decryption key along with 
'        ' the init vector. 
'        Dim cryptoTransform As ICryptoTransform = tdesProvider.CreateDecryptor(Me.key, Me.iv)

'        ' Provide a memory stream to decrypt information into 
'        Dim decryptedStream As MemoryStream = New MemoryStream()
'        Dim cryptStream As CryptoStream = New CryptoStream(decryptedStream, cryptoTransform, CryptoStreamMode.Write)
'        cryptStream.Write(inputInBytes, 0, inputInBytes.Length)
'        cryptStream.FlushFinalBlock()
'        decryptedStream.Position = 0

'        ' Read the memory stream and convert it back into a string 
'        Dim result(decryptedStream.Length - 1) As Byte
'        decryptedStream.Read(result, 0, decryptedStream.Length)
'        cryptStream.Close()
'        Dim myutf As UTF8Encoding = New UTF8Encoding()
'        Return myutf.GetString(result)
'    End Function
'End Class
