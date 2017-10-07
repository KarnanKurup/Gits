/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mca.bookbank;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Hare Krishna
 */
public class MsgBox {
    public static void errorBox(Component com,Object o){
        JOptionPane.showMessageDialog(com, o, "Error", JOptionPane.ERROR_MESSAGE);
    }
    public static void warringBox(Component com,Object o){
        JOptionPane.showMessageDialog(com, o, "Warring", JOptionPane.WARNING_MESSAGE);
    }
    public static void infoBox(Component com,Object o){
        JOptionPane.showMessageDialog(com, o, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}
