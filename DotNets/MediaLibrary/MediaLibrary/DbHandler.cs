using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MediaLibrary
{
    class DbHandler
    {
        SqlConnection con;

        public DbHandler()
        {
            con = new SqlConnection(@"Data Source=(LocalDB)\v11.0;AttachDbFilename='D:\Coding\Visual Studio 2013\Projects\MediaLibrary\db\MediaLib.mdf';Integrated Security=True;Connect Timeout=30");
            //con = new SqlConnection(@"Data Source=.\SQLEXPRESS;AttachDbFilename=C:\Users\mca322\Documents\ProChat.mdf;Integrated Security=True;Connect Timeout=30;User Instance=True");
        }
        public int UpdateData(string q)
        {
            SqlCommand cmd = new SqlCommand(q, con);
            con.Open();
            int check = cmd.ExecuteNonQuery();
            con.Close();
            return check;
        }

        public DataTable SelectData(string q)
        {
            SqlDataAdapter sd = new SqlDataAdapter(q, con);
            con.Open();
            DataSet ds = new DataSet();
            sd.Fill(ds);
            return ds.Tables[0];
        }
    }
}
