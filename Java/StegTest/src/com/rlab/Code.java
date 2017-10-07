package com.rlab;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.StringTokenizer;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;




public class Code
{
  String embfilename;
  
  public Code() {}
  
  private String promtPass(){
	JPanel panel = new JPanel();
	JLabel label = new JLabel("Enter a password:");
	JPasswordField pass = new JPasswordField(10);
	panel.add(label);
	panel.add(pass);
	String[] options = new String[]{"OK", "Cancel"};
	int option = JOptionPane.showOptionDialog(null, panel, "The title",
					 JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
					 null, options, options[1]);
	if(option == 0) // pressing OK button
	{
            char[] password = pass.getPassword();
            return new String(password);
	}
	return "";
  }
  
  public String emb(String media, String data)
  {
    try
    {
      File file = new File(media);
      File file1 = new File(data);
      String filename = file1.getName();
      FileInputStream fileinputstream = new FileInputStream(media);
      FileOutputStream fileoutputstream = new FileOutputStream("temp");
      String coder = "DATAFILE#" + promtPass(); 
      System.out.println(coder);
      int len = coder.length();
      byte[] abyte0 = new byte[len];
      
      int i,k;
      for (k = 0; (i = fileinputstream.read(abyte0, 0, len)) > 0; k = i) {
        fileoutputstream.write(abyte0, 0, i);
      }
      for (int l = 1; l <= len - k; l++) {
            fileoutputstream.write(65);
        }
      fileinputstream.close();
      
      

      fileoutputstream.write(coder.getBytes(), 0, len);
      
      System.out.println("File name===" + file1.getName());
      StringBuffer stringbuffer = new StringBuffer(file1.getName());
      stringbuffer.setLength(50);
      fileoutputstream.write(stringbuffer.toString().getBytes(), 0, 50);
      fileinputstream = new FileInputStream(data);
      int j;
      while ((j = fileinputstream.read(abyte0, 0, len)) > 0)
        fileoutputstream.write(abyte0, 0, j);
      fileinputstream.close();
      fileoutputstream.close();
      file.delete();
      File file2 = new File("temp");
      file2.renameTo(file);
      embfilename = file.getName();
    }
    catch (Exception e) {
      e.printStackTrace();
      embfilename = "";
    }
    return embfilename;
  }
  

  public String demb(String s)
  {
    String demfile = "";
    try
    {
      File file = new File(s);
      String outpath = s.substring(0, s.lastIndexOf("\\") + 1);
      FileInputStream fileinputstream = new FileInputStream(s);
      int c = 16;
      byte[] abyte0 = new byte[c];
      String s1 = "";
      int i;
      while ((i = fileinputstream.read(abyte0, 0, c)) > 0)
      {
        s1 = new String(abyte0);
        if (s1.contains("DATAFILE")) {
          break;
        }
      }
      

      if (!s1.contains("DATAFILE"))
      {
        boolean flag = false;
        fileinputstream.close();
        return demfile;
      }
      StringTokenizer ob = new StringTokenizer(s1, "#");
      String pass = ob.nextToken();
      pass = ob.nextToken();
      String cpass = promtPass();
      if (pass.equals(cpass))
      {
        abyte0 = new byte[50];
        fileinputstream.read(abyte0, 0, 50);
        s1 = new String(abyte0);
        String s2 = s1.trim();
        String fpath = s2;
        System.out.println("fpath------" + fpath);
        FileOutputStream fileoutputstream = new FileOutputStream(outpath + fpath);
        c = 20480;
        abyte0 = new byte[c];
        while ((i = fileinputstream.read(abyte0, 0, c)) > 0)
          fileoutputstream.write(abyte0, 0, i);
        fileinputstream.close();
        fileoutputstream.close();
        demfile = outpath + fpath;
      }
      else
      {
        demfile = "";
      }
    }
    catch (Exception exception)
    {
      demfile = "";
      exception.printStackTrace();
      System.out.println(exception);
    }
    return demfile;
  }
}
