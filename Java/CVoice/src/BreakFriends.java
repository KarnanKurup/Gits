
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
public class BreakFriends {
    int A[],B[],aend,bend,achk,bchk;

    public BreakFriends() {
        aend=0;
        bend=0;
        achk=bchk=0;
    }
    
    void breaker(int[][]fri,int N){
        System.out.println("Processing...");
        A=new int[N];
        B=new int[N];
        int M=fri.length,z;
        
       // for(int i=0;i<M;i++){
            A[aend++]=fri[0][0];
            B[bend++]=fri[0][1];
                while(bchk<=bend){
                    for(int j=1;j<M;j++){
                        if(B[bend-1]==fri[j][0]){
                            for(z=0;z<aend;z++){
                                if(A[z]==fri[j][1]){
                                    break;
                                }
                            }
                            if(z==aend){
                                A[aend++]=fri[j][1];
                            }
                        }else if(B[bend-1]==fri[j][1]){
                            for(z=0;z<aend;z++){
                                if(A[z]==fri[j][0]){
                                    break;
                                }
                            }
                            if(z==aend){
                                A[aend++]=fri[j][0];
                            }
                        }
                        bchk++;
                    }
                    for(int j=1;j<M;j++){
                        if(A[aend-1]==fri[j][0]){
                            for(z=0;z<bend;z++){
                                if(B[z]==fri[j][1]){
                                    break;
                                }
                                    
                            }
                            if(z==bend){
                                B[bend++]=fri[j][1];
                            }
                        }else if(A[aend-1]==fri[j][1]){
                            for(z=0;z<bend;z++){
                                if(B[z]==fri[j][0]){
                                    break;
                                }
                            }
                            if(z==bend){
                                B[bend++]=fri[j][0];
                            }
                        }
                        achk++;
                    }
                }
        //    }
            if(aend+bend==N){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
    }
    
    public static void main(String[] args) {
        Scanner r=new Scanner(System.in);
        int N=r.nextInt();
        int M;
        int [][]fri;
        BreakFriends o =new BreakFriends();
        if(N>0&&N<=50){
            M=r.nextInt();
            if(M>=1&&M<=(N*(N-1))/2){
                fri=new int[M][2];
                for(int i=0;i<M;i++){
                    fri[i][0]=r.nextInt();
                    fri[i][1]=r.nextInt();
                }
                o.breaker(fri, N);
            }
        }
    }
}
