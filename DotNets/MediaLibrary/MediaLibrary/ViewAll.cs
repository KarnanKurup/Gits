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
    public partial class ViewAll : Form
    {
        public ViewAll()
        {
            InitializeComponent();
        }

        private void ViewAll_Load(object sender, EventArgs e)
        {
            DataTable dt = new DbHandler().SelectData("select mid,mtitle,mdesc,mtype,mpath from Medias where adminid='"+Form1.admin+"'");
            dataGridView1.DataSource = dt;
        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            var senderG = (DataGridView)sender;
            if (senderG.Columns[e.ColumnIndex] is DataGridViewButtonColumn && e.RowIndex >= 0)
            {
                string check= senderG.Columns[e.ColumnIndex].HeaderText;
                string id = senderG.Rows[e.RowIndex].Cells["col1"].Value.ToString();
                if (check.Equals("Update"))
                {
                    
                    DbHandler db = new DbHandler();
                    DataTable dt = db.SelectData("select mid,mtitle,mdesc,mtype,mpath from Medias where mid=" + id);
                    UpdateMedia um = new UpdateMedia(dt, id);
                    um.MdiParent = this.MdiParent;
                    um.Show();
                }
                else if (check.Equals("Delete"))
                {

                    DialogResult dr = MessageBox.Show("Are you sure you want to permanently delete this Item?", "Delete", MessageBoxButtons.YesNo);

                    if (dr.Equals(DialogResult.Yes))
                    {
                        DbHandler db = new DbHandler();
                        db.UpdateData("delete from Medias where mid=" + id);
                        DataTable dt = db.SelectData("select mid,mtitle,mdesc,mtype,mpath from Medias where adminid='" + Form1.admin + "'");
                        dataGridView1.DataSource = dt;
                    }
                }
                
            }
        }

        private void ViewAll_Activated(object sender, EventArgs e)
        {
            DataTable dt = new DbHandler().SelectData("select mid,mtitle,mdesc,mtype,mpath from Medias where adminid='" + Form1.admin + "'");
            dataGridView1.DataSource = dt;
        }

    }
}
