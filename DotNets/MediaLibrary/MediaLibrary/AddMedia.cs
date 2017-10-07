using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MediaLibrary
{
    public partial class AddMedia : Form
    {
        string fname;
        public AddMedia()
        {
            InitializeComponent();
        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (comboBox1.SelectedItem.ToString() == "YouTube")
            {
                button1.Enabled = false;

            }
            else if(comboBox1.SelectedItem.ToString()=="Offline")
            {
                button1.Enabled = true;
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            if (!Directory.Exists("media"))
            {
                Directory.CreateDirectory("media");
                
            }
            if (button1.Enabled == true)
            {
                try
                {
                    
                    File.Copy(textBox3.Text, fname);
                }
                catch (Exception ex)
                {
                    MessageBox.Show(ex.Message);
                }
            }
            else
            {
                fname = textBox3.Text.Trim();
            }
            new DbHandler().UpdateData(String.Format("insert into Medias (adminid,mtype,mtitle,mdesc,mpath) values ('{0}','{1}','{2}','{3}','{4}')",Form1.admin,comboBox1.SelectedItem.ToString(),textBox1.Text,textBox2.Text,fname));

            MessageBox.Show("Media Added Successfully");
            textBox1.Text = "";
            textBox2.Text = "";
            textBox3.Text = "";
        }

        private void button1_Click(object sender, EventArgs e)
        {
            OpenFileDialog op = new OpenFileDialog();
            op.Multiselect = false;
            op.Filter = "(mp3,wav,mp4,mov,wmv,mpg)|*.mp3;*.wav;*.mp4;*.mov;*.wmv;*.mpg";
            if (op.ShowDialog() == DialogResult.OK)
            {
                fname = "media/"+op.SafeFileName;
                textBox3.Text = op.FileName;
            }

        }
    }
}
