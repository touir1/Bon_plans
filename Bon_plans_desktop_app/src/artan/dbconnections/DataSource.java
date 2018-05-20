/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artan.dbconnections;

/**
 *
 * @author Mohamed Ali
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    private  static DataSource instance=null;

    String url="jdbc:mysql://localhost/1cinfo01pi";
    String username="root";
    String password="";
    Connection connection=null;

    private DataSource() {
        try {
            connection= DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            System.out.println("SQL Exception");
            ex.printStackTrace();
        }
    }

    public static DataSource getInstance(){
        if(instance==null){
            instance=new DataSource();
        }
        return instance;
    }

    public Connection getConnection(){
        return connection;
    }
}
