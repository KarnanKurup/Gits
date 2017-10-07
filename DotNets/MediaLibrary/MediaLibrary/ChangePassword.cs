using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MediaLibrary
{
    public partial class ChangePassword : Form
    {
        public ChangePassword()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (textBox1.Text != "" && textBox2.Text != "" && textBox3.Text != "")
            {
                if (textBox2.Text == textBox3.Text)
                {
                    int a=new DbHandler().UpdateData(String.Format("update Admins set pass='{0}' where AdminId='{1}' and pass='{2}'", textBox2.Text, Form1.admin, textBox1.Text));
                    if (a == 0)
                    {
                        MessageBox.Show("Current Password is not correct");
                    }
                    else
                    {
                        MessageBox.Show("Password Changed");
                        this.Close();
                    }
                }
                else
                {
                    MessageBox.Show("New Password Don't Match");
                }
            }else
            {
                MessageBox.Show("Please fill all the fields");
            }
        }
    }
}
