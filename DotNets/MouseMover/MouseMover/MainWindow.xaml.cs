using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Net.Sockets;
using System.Net;
using System.Runtime.InteropServices;
using System.Windows.Forms;

namespace MouseMover
{
    
    public partial class MainWindow : Window
    {

        // Dll import. -> method mouse_move
        [DllImport("user32.dll")]
        static extern void mouse_event(int dwFlags, int dx, int dy, int dwData, int dwExtraInfo);

        private EndPoint point;
        private Socket receiveSocket;
        private byte[] recBuffer;
        private int speed = 2;
        //bool isDrag = false;

        // Flags for mouse_event api
        [Flags]
        public enum MouseEventFlagsAPI
        {
            RIGHTDOWN = 0x00000008,
            RIGHTUP = 0x00000010,
            MIDDLEDOWN = 0x00000020,
            MIDDLEUP = 0x00000040,
            MOVE = 0x00000001,
            ABSOLUTE = 0x00008000,
            LEFTDOWN = 0x00000002,
            LEFTUP = 0x00000004
        }

        public MainWindow()
        {
            InitializeComponent();
        }

        private void Window_Loaded(object sender, RoutedEventArgs e)
        {
            // initilization and listening
            receiveSocket = new Socket(AddressFamily.InterNetwork, SocketType.Dgram, ProtocolType.Udp);
                point = new IPEndPoint(IPAddress.Any, 8000);
            this.recBuffer = new byte[60];
            receiveSocket.Bind(point);
            receiveSocket.BeginReceiveFrom(recBuffer, 0, recBuffer.Length, 
                SocketFlags.None, ref point, 
                new AsyncCallback(MessageReceiveCallback), (object)this);
            
        }
        private void SendClick()
        {
            // Send click to system
            mouse_event((int)MouseEventFlagsAPI.LEFTDOWN, 0, 0, 0, 0);
            mouse_event((int)MouseEventFlagsAPI.LEFTUP, 0, 0, 0, 0);
        }
        private void RightClick()
        {
            mouse_event((int)MouseEventFlagsAPI.RIGHTDOWN, 0, 0, 0, 0);
            mouse_event((int)MouseEventFlagsAPI.RIGHTUP,0,0,0,0);
        }
        private void MessageReceiveCallback(IAsyncResult result)
        {
            EndPoint remote = new IPEndPoint(0, 0);
            string pos = "";

            try
            {
                // get received message.
                pos = Encoding.UTF8.GetString(recBuffer);
                // clicked?
                if (pos.StartsWith("click"))
                    this.SendClick();
                // long click = double click
                else if (pos.StartsWith("d.click"))
                {
                    this.RightClick();
                }
                /*else if (pos.StartsWith("drag"))
                {
                    isDrag = true;
                    Console.WriteLine(pos);
                    pos = pos.Substring(pos.IndexOf(",") + 1, pos.IndexOf("\0") - pos.IndexOf(",") + 1);
                }
                else if (pos.StartsWith("release"))
                {
                    isDrag = false;
                    mouse_event((int)MouseEventFlagsAPI.LEFTUP, 0, 0, 0, 0);
                    Console.WriteLine("-----");
                   // Console.WriteLine(pos);
                }*/
                // Otherwise move
                else
                {
                   // Console.WriteLine(pos);
                    // calculate delta
                    int deltaX = (int)float.Parse(pos.Substring(0, pos.IndexOf(","))) * this.speed;
                    int deltaY = (int)float.Parse(pos.Substring(pos.IndexOf(",") + 1, pos.IndexOf("\0") - pos.IndexOf(",") + 1)) * this.speed;
                    
                   // Console.WriteLine("+++++ X:"+deltaX+" Y: "+deltaY);
                    
                    // set new point
                  /*  if(isDrag)
                        mouse_event((int)MouseEventFlagsAPI.LEFTDOWN, 0, 0, 0, 0);*/
                    System.Drawing.Point pt = System.Windows.Forms.Cursor.Position;
                    System.Windows.Forms.Cursor.Position = new System.Drawing.Point(pt.X + deltaX, pt.Y + deltaY);
                    
                }
            }
            catch (Exception e)
            {
                Console.WriteLine("Error"+e.Message);
            }
            // End and "begin" for next package
            this.receiveSocket.EndReceiveFrom(result, ref remote);
            receiveSocket.BeginReceiveFrom(recBuffer, 0, recBuffer.Length, 
                SocketFlags.None, ref point, 
                new AsyncCallback(MessageReceiveCallback), (object)this);
        }
    }
}
