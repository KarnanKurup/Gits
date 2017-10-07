/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cvoice;

/**
 *
 * @author Hare Krishna
 */
class Gen<T>{
    T ob;

    public T getOb() {
        return ob;
    }

    public void setOb(T ob) {
        this.ob = ob;
    }
    
}


public class MainClass {
    public static void main(String[] args) {
        String s="valkue";
        Character c='A';
        Integer i=5655;
        
        Gen<String> o1= new Gen<String>();
        o1.setOb(s);
        Gen<Character> o2= new Gen<>();
        o2.setOb(c);
        
    }
}
