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
    public partial class YPlayer : Form
    {
        string path;
        public YPlayer()
        {
            InitializeComponent();
        }
        public YPlayer(string pa)
        {
            InitializeComponent();
            path = pa;
        }

        private void YPlayer_Load(object sender, EventArgs e)
        {
            path=path.Replace("watch?v=", "v/");
            axShockwaveFlash1.Movie = path;
            axShockwaveFlash1.Play();
        }
    }
}
