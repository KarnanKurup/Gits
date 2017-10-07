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
    public partial class Login : Form
    {
        Form1 ff;
        public Login()
        {
            InitializeComponent();
        }

        public Login(Form1 f)
        {
            InitializeComponent();
            ff = f;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (textBox1.Text != "" && textBox2.Text != "")
            {
                DataTable dt = new DbHandler().SelectData("select * from Admins where AdminId='" + textBox1.Text + "' and pass='" + textBox2.Text + "'");
                if (dt.Rows.Count == 1)
                {
                    MessageBox.Show("Login Success");
                    Form1.admin = textBox1.Text;
                    ff.addNewItemToolStripMenuItem.Enabled = true;
                    ff.logoutToolStripMenuItem.Enabled = true;
                    ff.loginToolStripMenuItem.Enabled = false;
                    this.Close();
                }
            }
        }
    }
}
