
import java.util.Scanner;


public class NumberGame {
    public int ivalute(int n){
        int nums=0;
        if(n==1)
            nums=0;
        else{
            for(int i=2;i<=n;i++){
                if(n%i==0){
                    n=n/i;
                    i=1;
                    nums++;
                }
            }
        }
        return nums;
    }
    int playGame(int cards[]){
        int plays=0;
        for(int i=0;i<cards.length;i++){
            plays+=ivalute(cards[i]);
        }
        return plays;
    }
    public static void main(String[] args) {
        Scanner sin =new Scanner(System.in);
        int ncards=sin.nextInt();
        int cards[]=new int[ncards];
        for(int i=0;i<ncards;i++){
            cards[i]=sin.nextInt();
        }
        int plays=new NumberGame().playGame(cards);
        if(plays%2==0 && ncards>1){
            System.out.println("JASBIR");
        }else{
            System.out.println("AMAN");
        }
    }
}

