

import java.util.Scanner;


public class Catch22 {
    int calc(int F,int B,int T,int FD,int BD){
        int pos=0,stat=1, total=0;
        boolean ss=false;
        do{
            int i=1,j=1;
            while(i<=F){
                i++;
                pos++;
                total++;
                if(pos==FD){
                    stat=0;
                    break;
                }
            }
            while(j<=B && stat!=0){
                j++;
                pos--;
                total++;
                if(Math.abs(pos)==BD){
                    stat=0;
                    ss=true;
                    break;
                }
            }
        }while(stat!=0);
        total=total*T;
        if(ss){
            total=-total;
        }
        return total;
    }
    public static void main(String[] args) {
        Scanner sin =new Scanner(System.in);
        int n=sin.nextInt();
        Catch22 o =new Catch22();
        int [][] data= new int[n][5];
        for(int i=0;i<n;i++){
            data[i][0]=sin.nextInt();
            data[i][1]=sin.nextInt();
            data[i][2]=sin.nextInt();
            data[i][3]=sin.nextInt();
            data[i][4]=sin.nextInt();
        }
        for(int i=0;i<n;i++){
            if(data[i][0]==data[i][1] && data[i][0]<data[i][3] && data[i][1]<data[i][4]){
                System.out.println("No Ditch");
            }else{
                int v=o.calc(data[i][0], data[i][1], data[i][2], data[i][3], data[i][4]);
                if(v>0){
                    System.out.println(v+" F");
                }else{
                    System.out.println(Math.abs(v)+" B");
                }
            }
        }
    }
}
