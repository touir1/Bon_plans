/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author touir
 */
public class DatabaseHandler {
    private static Connection connection;
    private static Statement statement;
    private static String url = "jdbc:mysql://localhost:3306/1cinfo01pi";
    private static String username = "root";
    private static String password = "";
    
    private DatabaseHandler(){}
    
    private static boolean initStatement(){
        try {
            if(statement == null || statement.isClosed()){
                if(connection == null || connection.isClosed()){
                    connection = DriverManager.getConnection(url,username,password);
                }
                statement = connection.createStatement();
            }
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public static boolean update(String sql){
        try {
            if(initStatement()){
                statement.executeUpdate(sql);
                return true;
            }
            else{
                return false;
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public static List<Map<String, Object>> select(String sql){
        List<Map<String, Object>> result = new ArrayList<>();
        try {
            if(initStatement()){
                ResultSet resultSet = statement.executeQuery(sql);
                int columnCount = resultSet.getMetaData().getColumnCount();
                while(resultSet.next()){
                    Map<String, Object> row = new HashMap<>();
                    for(int i=1;i<=columnCount;i++){
                        row.put(resultSet.getMetaData().getColumnLabel(i), resultSet.getObject(i));
                    }
                    result.add(row);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return result;
    }
    
}
