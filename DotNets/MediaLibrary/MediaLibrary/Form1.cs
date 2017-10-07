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
    public partial class Form1 : Form
    {
        public static string admin = null;
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void showLibraryToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Library lb= new Library();
            lb.MdiParent = this;
            lb.Show();

        }

        private void addToolStripMenuItem_Click(object sender, EventArgs e)
        {
            AddMedia am = new AddMedia();
            am.MdiParent = this;
            am.Show();
        }

        private void viewToolStripMenuItem_Click(object sender, EventArgs e)
        {
            ViewAll va = new ViewAll();
            va.MdiParent = this;
            va.Show();
        }

        private void loginToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Login log = new Login(this);
            log.MdiParent = this;
            log.Show();
        }

        private void newAdminToolStripMenuItem_Click(object sender, EventArgs e)
        {
            NewAdmin na = new NewAdmin();
            na.MdiParent = this;
            na.Show();
        }

        private void changePasswordToolStripMenuItem_Click(object sender, EventArgs e)
        {
            ChangePassword cp = new ChangePassword();
            cp.MdiParent = this;
            cp.Show();
        }

        private void logoutToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Form[] childs = this.MdiChildren;
            foreach (Form f in childs)
            {
                f.Close();
            }
            loginToolStripMenuItem.Enabled = true;
            logoutToolStripMenuItem.Enabled = false;
            admin = "";
            addNewItemToolStripMenuItem.Enabled = false;
        }

        private void aboutToolStripMenuItem_Click(object sender, EventArgs e)
        {
            AboutBox1 a = new AboutBox1();
            a.Show();
        }
    }
}
