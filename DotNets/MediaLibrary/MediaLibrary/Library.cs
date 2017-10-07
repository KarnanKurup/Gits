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
    public partial class Library : Form
    {
        public Library()
        {
            InitializeComponent();
        }

        private void Library_Load(object sender, EventArgs e)
        {
            DataTable dt = new DbHandler().SelectData("select mid,adminid,mtitle,mdesc from Medias");
            dataGridView1.DataSource = dt;
        }


        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            var senderG = (DataGridView)sender;
            if (senderG.Columns[e.ColumnIndex] is DataGridViewButtonColumn && e.RowIndex >= 0)
            {
                string id= senderG.Rows[e.RowIndex].Cells["Id"].Value.ToString();
                DataTable dt = new DbHandler().SelectData("select mpath,mtype from Medias where mid=" + id);
                if (dt.Rows[0].ItemArray[1].ToString().Equals("Offline"))
                {
                    MPlayer mp = new MPlayer(dt.Rows[0].ItemArray[0].ToString());
                    //mp.MdiParent = this.MdiParent;
                    mp.Show();
                }
                else
                {
                    YPlayer yp = new YPlayer(dt.Rows[0].ItemArray[0].ToString());
                    yp.Show();
                }
                
            }
        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {
            string cons = textBox1.Text;
            if (radioButton1.Checked == true)
            {
                string qr = "select mid,adminid,mtitle,mdesc from Medias where adminid like '%" + cons + "%' or mtitle like '%" + cons + "%' or mdesc like'%" + cons + "%'";
                DataTable dt = new DbHandler().SelectData(qr);
                dataGridView1.DataSource = dt;
            }
            else if (radioButton2.Checked == true)
            {
                string qr = "select mid,adminid,mtitle,mdesc from Medias where adminid like '%" + cons + "%'";
                DataTable dt = new DbHandler().SelectData(qr);
                dataGridView1.DataSource = dt;
            }
            else if (radioButton3.Checked == true)
            {
                string qr = "select mid,adminid,mtitle,mdesc from Medias where mtitle like '%" + cons + "%'";
                DataTable dt = new DbHandler().SelectData(qr);
                dataGridView1.DataSource = dt;
            }
            else if (radioButton4.Checked == true)
            {
                string qr = "select mid,adminid,mtitle,mdesc from Medias where mdesc like'%" + cons + "%'";
                DataTable dt = new DbHandler().SelectData(qr);
                dataGridView1.DataSource = dt;
            }
        }
    }
}
