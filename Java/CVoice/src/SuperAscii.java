
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hare Krishna
 */
public class SuperAscii {
    private int[] freq=new int[26];
    private final int add=2,rep=1,del=3;
    private int cost;
    int letters[];
    public SuperAscii() {
        cost=0;
    }
    
    public int getCost(String s){
        getFreq(s);
        for(int i=0;i<26;i++){
            if(letters[i]>0){
                
            }
        }
        return cost;
    }
    void getFreq(String st){
        letters=new int[st.length()];
        for(int i=0;i<st.length();i++){
            switch(st.charAt(i)){
                case 'a':freq[0]+=1;letters[i]=1;break;
                case 'b':freq[1]+=1;letters[i]=2;break;
                case 'c':freq[2]+=1;letters[i]=3;break;
                case 'd':freq[3]+=1;letters[i]=4;break;
                case 'e':freq[4]+=1;letters[i]=5;break;
                case 'f':freq[5]+=1;letters[i]=6;break;
                case 'g':freq[6]+=1;letters[i]=7;break;
                case 'h':freq[7]+=1;letters[i]=8;break;
                case 'i':freq[8]+=1;letters[i]=9;break;
                case 'j':freq[9]+=1;letters[i]=10;break;
                case 'k':freq[10]+=1;letters[i]=11;break;
                case 'l':freq[11]+=1;letters[i]=12;break;
                case 'm':freq[12]+=1;letters[i]=13;break;
                case 'n':freq[13]+=1;letters[i]=14;break;
                case 'o':freq[14]+=1;letters[i]=15;break;
                case 'p':freq[15]+=1;letters[i]=16;break;
                case 'q':freq[16]+=1;letters[i]=17;break;
                case 'r':freq[17]+=1;letters[i]=18;break;
                case 's':freq[18]+=1;letters[i]=19;break;
                case 't':freq[19]+=1;letters[i]=20;break;
                case 'u':freq[20]+=1;letters[i]=21;break;
                case 'v':freq[21]+=1;letters[i]=22;break;
                case 'w':freq[22]+=1;letters[i]=23;break;
                case 'x':freq[23]+=1;letters[i]=24;break;
                case 'y':freq[24]+=1;letters[i]=25;break;
                case 'z':freq[25]+=1;letters[i]=26;break;
            }
        }
    }
    public static void main(String[] args) {
        int no;
        SuperAscii ob =new SuperAscii();
        Scanner r=new Scanner(System.in);
        no=r.nextInt();
        int costs[]=new int[no];
        for (int i=0;i<no;i++){
            costs[i]=ob.getCost(r.next());
        }
        for (int i=0;i<no;i++){
            System.out.println(costs[i]);
        }
    }
}
