
import java.util.Scanner;

public class LogicPyramid {
    int pattern(int n){
        int t;
        t=(n*(n+1))/2;
        return t;
    }
    void pyramid(int n){
        int k=3,p;
        for(int i=0;i<n;i++){
            for(int m=n-1;m>i;m--){
                System.out.print(" ");
            }
            for(int j=0;j<=i;j++){
                p=pattern(k);
                k+=4;
                
                System.out.printf("%05d ", p);
            }
            System.out.println("");
        }
    }
    public static void main(String[] args) {
        Scanner sin = new Scanner(System.in);
        int n= sin.nextInt();
        new LogicPyramid().pyramid(n);
    }
}
