
import java.util.Scanner;

/**
 *
 * @author Hare Krishna
 */
public class Milkman {
    int bottels[]={1,5,7,10};
    int getMinBottels(int lit){
        int i=3;
        int bot=lit/bottels[i];
        int x=lit%bottels[i];
        while(x!=0){
            i--;
            bot+=x/bottels[i];
            x=x%bottels[i];
        }
        return bot;
    }
    
    public static void main(String[] args) {
        Scanner r=new Scanner(System.in);
        int N=r.nextInt();
        if(1<=N && N<=1000){
            int botls[]=new int[N];
            Milkman ob =new Milkman();
            for(int i=0;i<N;i++){
                int t=r.nextInt();
                if(t>0)
                    botls[i]=ob.getMinBottels(t);
            }
            for(int i=0;i<N;i++){
                System.out.println(botls[i]);
            }
        }
    }
}
