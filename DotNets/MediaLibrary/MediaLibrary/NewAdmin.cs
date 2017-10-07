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
    public partial class NewAdmin : Form
    {
        public NewAdmin()
        {
            InitializeComponent();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            if (button2.Text == "Show")
            {
                textBox2.PasswordChar='\0';
                button2.Text = "Hide";
            }
            else if (button2.Text == "Hide")
            {
                textBox2.PasswordChar = '*';
                button2.Text = "Show";
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            new DbHandler().UpdateData(String.Format("insert into Admins values('{0}','{1}')", textBox1.Text, textBox2.Text));
            MessageBox.Show("Data Saved");
            textBox1.Text = "";
            textBox2.Text = "";
        }
    }
}
