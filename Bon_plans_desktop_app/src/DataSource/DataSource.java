/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SadfiAmine
 */
public class DataSource {
    static DataSource data;
     String url="JDBC:mysql://localhost:3306/BonPlans";
    String login="root";
    String pwd="";
    static Connection connection;
private DataSource(){
     try{
            connection=DriverManager.getConnection(url, login, pwd);
        System.out.println("connection ok ");
        } catch (SQLException ex){
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
    
    }
    }
 
public static DataSource getInstance(){
    if(data==null){
    data=new DataSource();
    }
    
return data;
}
    public  Connection getConnection() {
        return connection;
    }

}
