using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Timer
{
    public class DisplayStruct
    {
        public int days;
        public int hours;
        public int minutes;
        public int seconds;
        public string hourFormat;
        public string minuteFormat;
        public string secondFormat;

        public DisplayStruct()
        {
            days = 0;
            hours = 0;
            minutes = 0;
            seconds = 0;
            hourFormat = "D2";
            minuteFormat = "D2";
            secondFormat = "D2";
        }
    }
}
