Public Class Form1
    Dim point As Integer
    Dim ans As String
    Sub loadquestion()
        Label1.Text = "question 1 who is the president of india?" 'replace with data from database
        RadioButton1.Text = "answer1" 'replace with data from database
        RadioButton2.Text = "answer2" 'replace with data from database
        RadioButton3.Text = "answer3" 'replace with data from database
        ans = "answer2" 'replace with data from database
        RadioButton1.Checked = False
        RadioButton2.Checked = False
        RadioButton3.Checked = False
    End Sub
    Private Sub Form1_Load(sender As Object, e As EventArgs) Handles MyBase.Load
        point = 0
        loadquestion()
    End Sub

    Private Sub Button1_Click(sender As Object, e As EventArgs) Handles Button1.Click
        If RadioButton1.Checked = True And RadioButton1.Text = ans Then
            point += 1
        ElseIf RadioButton2.Checked = True And RadioButton2.Text = ans Then
            point += 1
        ElseIf RadioButton3.Checked = True And RadioButton3.Text = ans Then
            point += 1
        End If
        loadquestion()
        ans = "answer3"
        MsgBox(point)
    End Sub

    Private Sub Form1_Shown(sender As Object, e As EventArgs) Handles Me.Shown
        RadioButton1.Checked = False
        RadioButton2.Checked = False
        RadioButton3.Checked = False
    End Sub
End Class
