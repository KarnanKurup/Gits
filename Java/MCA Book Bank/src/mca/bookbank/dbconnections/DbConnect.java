/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mca.bookbank.dbconnections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Hare Krishna
 */
public class DbConnect {
    public Connection con;
    private Statement stmt;
//192.168.1.20
    public DbConnect() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        con= DriverManager.getConnection("jdbc:mysql://localhost:3306/bookbank","root","root");
        //con= DriverManager.getConnection("jdbc:mysql://192.168.1.20:3306/bookbank","root","");
        stmt=con.createStatement();
    }
    
    public int dbIdu(String query) throws SQLException{
        int x=stmt.executeUpdate(query);
        return x;
    }
    
    public ResultSet dbSelect(String query) throws SQLException{
        ResultSet rs=stmt.executeQuery(query);
        return rs;
    }
    
}
