
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hare Krishna
 */
public class Chess1 {
    char board[][]=new char[8][8];
    void makeBoard(String s){
        int who=0;
        StringTokenizer st =new StringTokenizer(s," ");
        String []ss=new String[2];
        int i=0;
        while(st.hasMoreElements()){
            ss[i++]=st.nextToken();
        }
        if(ss[1].equals("w")){
            who=1;
        }else if(ss[1].equals("W")){
            who=2;
        }
        st=new StringTokenizer(ss[0],"/");
        String[] coms=new String[st.countTokens()];
        for(i=0;st.hasMoreTokens();i++){
            coms[i]=st.nextToken("/");
        }
        int j=0;
        for(i=0;i<8;i++){
            for(int k=0;k<coms[i].length();k++){
                j=j+coms[i].charAt(k);
            }
        }
    }
    public static void main(String[] args) {
        Scanner sin = new Scanner(System.in);
        String inp=sin.nextLine();
        new Chess1().makeBoard(inp);
    }
}
