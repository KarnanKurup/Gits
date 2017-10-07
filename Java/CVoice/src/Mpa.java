
import java.math.BigInteger;
import java.util.Scanner;

public class Mpa {
    int min(int a[],int b[],int n,int k){
        int min=0,ch=0,sum=0,i,t=0;
        for(i=0;i<n;i++){
            int x=0;
            if((a[i]<0 && b[i]<0) || (b[i]<0)){
                x=(a[i]+2*k)*b[i];
                if(min>=x){
                    min=x;
                    ch=i;
                    t=1;
                }
            }else if((a[i]>0 && b[i]>0) ||(a[i]<0)){
                x=(a[i]-2*k)*b[i];
                if(min>=x){
                    min=x;
                    ch=i;
                    t=2;
                }
            }
        }
         
        if(t==1){
            a[ch]=a[ch]+2*k;
        }else if(t==2){
            a[ch]=a[ch]-2*k;
        }
        for(i=0;i<n;i++){
            sum=sum+(a[i]*b[i]);
        }
        return sum;
    }
    public static void main(String[] args) {
        Scanner sin=new Scanner(System.in);
        BigInteger n=sin.nextBigInteger();
        BigInteger k=sin.nextBigInteger();
        Mpa o=new Mpa();
        if(n.longValue()>=1 && n.longValue()<=100000 && n.longValue()>=0 && n.longValue()<=1000000000){
            int[] a=new int[n.intValue()];
            int[] b=new int[n.intValue()];
            for(int i=0;i<n.intValue();i++){
                a[i]=sin.nextInt();
            }
            for(int i=0;i<n.intValue();i++){
                b[i]=sin.nextInt();
            }
            System.out.print(new Mpa().min(a, b, n.intValue(), k.intValue()));
        }
    }
}
