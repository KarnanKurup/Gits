/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvoice;

import java.io.*;
import java.util.Scanner;


public class MyClass {
    public static void main(String[] args) {
        File file= new File("asas.txt");
        try{
        Scanner s = new Scanner(System.in);
        FileWriter fw= new FileWriter(file);
        String msg=s.next();
        fw.write(msg);
        fw.close();
        }catch(Exception e){
            
        }
        try{
            FileReader fr= new FileReader(file);
            BufferedReader br= new BufferedReader(fr);
            System.out.println(br.readLine());
        }catch(Exception e){
            
        }
    }
}
