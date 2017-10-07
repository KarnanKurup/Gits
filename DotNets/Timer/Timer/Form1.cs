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

namespace Timer
{
    public partial class Form1 : Form
    {
        private DataTable counterTable;
        private DataView counterView;
        private DateTime TargetEvent;
        public Form1()
        {
            InitializeComponent();
            axWindowsMediaPlayer1.uiMode = "none";
            axWindowsMediaPlayer1.settings.autoStart = true;
            axWindowsMediaPlayer1.URL = "NVEExport.mp4";
            axWindowsMediaPlayer1.settings.setMode("loop", true);
            axWindowsMediaPlayer1.Ctlcontrols.play();

            
            LoadTable();
        }
        private void Runner()
        {
            System.Windows.Forms.Timer timer = new System.Windows.Forms.Timer();
            timer.Tick += new EventHandler(OnTick);
            timer.Interval = 1000;
            timer.Start();
        }
        public void OnTick(object sender, EventArgs e)
        {
            DisplayStruct disp = GetGroupValues();
            TimeSpan when = TargetEvent - DateTime.Now;
            Color color = Color.Red;

            if (when.TotalDays > 3)
            {
                color = Color.Green;
            }
            else if (when.TotalDays > 1)
            {
                color = Color.Yellow;
            }
            else if (when.TotalSeconds < 0)
            {
                color = Color.Purple;
            }
            int a;
            //a.ToString()
            label4.Text = disp.seconds.ToString(disp.secondFormat);
            label3.Text = disp.minutes.ToString(disp.minuteFormat);
            label2.Text = disp.hours.ToString(disp.hourFormat);
            label1.Text = disp.days.ToString("D2");
        }
        protected DisplayStruct GetGroupValues()
        {
            TimeSpan ts = TargetEvent - DateTime.Now;
            DisplayStruct disp = new DisplayStruct();

            if (ts.TotalSeconds > 0)
            {
                disp.days = ts.Days;

                disp.hours += ts.Hours;

                disp.minutes += ts.Minutes;

                disp.seconds += ts.Seconds;
            }

            return disp;
        }
        private void LoadTable()
        {
            if (File.Exists("counters.xml"))
            {
                counterTable = new DataTable("Counters");
                counterTable.ReadXml("counters.xml");
                counterView = new DataView(counterTable);
                foreach (DataRowView row in counterView)
                {
                    TargetEvent=(DateTime)row["TargetDate"];
                }
                Runner();
            }
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }
    }
}
