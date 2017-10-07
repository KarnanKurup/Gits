
import java.util.Scanner;


public class Cpm {
    boolean chkprime(long num){
        boolean flag=true;
        for(long i=2;i<=num/2;i++){
            if(num%i==0){
                flag=false;
                break;
            }
        }
        return flag;
    }
    long prime(long n){
        long ans[] =new long[(int)n/2];
        
        int count=0,j,sum=2,k=3,rs=0;
        ans[count++]=2;
        for(long i=3;i<n;i++){
            if(chkprime(i)){
                ans[count++]=i;
            }
        }
        for(int i=1;i<ans.length && ans[i]>0;i++){
            sum+=ans[i];
            if(chkprime(sum)&& sum<=n){                
                rs++;
            }
        }
        return rs;
    }
    public static void main(String[] args) {
        Scanner sin =new Scanner(System.in);
        long n=sin.nextLong();
        Cpm o=new Cpm();
        if(n>2 && n<=12000000000L)
        System.out.print(o.prime(n));
    }
}
