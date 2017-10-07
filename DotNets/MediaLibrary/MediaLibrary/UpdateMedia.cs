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
    public partial class UpdateMedia : Form
    {
        DataTable ddt;
        string fname=null;
        string iid;

        public UpdateMedia()
        {
            InitializeComponent();
        }

        public UpdateMedia(DataTable dt,string id)
        {
            InitializeComponent();
            iid = id;
            comboBox1.SelectedIndex = comboBox1.Items.IndexOf(dt.Rows[0].ItemArray[3].ToString());
            textBox1.Text = dt.Rows[0].ItemArray[1].ToString();
            textBox2.Text = dt.Rows[0].ItemArray[2].ToString();
            textBox3.Text = dt.Rows[0].ItemArray[4].ToString();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            OpenFileDialog op = new OpenFileDialog();
            op.Multiselect = false;
            op.Filter = "(mp3,wav,mp4,mov,wmv,mpg)|*.mp3;*.wav;*.mp4;*.mov;*.wmv;*.mpg";
            if (op.ShowDialog() == DialogResult.OK)
            {
                fname = "media/" + op.SafeFileName;
                textBox3.Text = op.FileName;
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            if (!Directory.Exists("media"))
            {
                Directory.CreateDirectory("media");

            }
            if (button1.Enabled == true && fname!=null)
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
            new DbHandler().UpdateData(String.Format("update Medias set mtype='{0}',mtitle='{1}',mdesc='{2}',mpath='{3}' where mid={4} and adminid='{5}'", comboBox1.SelectedItem.ToString(), textBox1.Text, textBox2.Text, fname, iid,Form1.admin));
            MessageBox.Show("Data Updated");
            this.Close();

        }

        private void button3_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (comboBox1.SelectedItem.ToString() == "YouTube")
            {
                button1.Enabled = false;

            }
            else if (comboBox1.SelectedItem.ToString() == "Offline")
            {
                button1.Enabled = true;
            }
        }
    }
}
