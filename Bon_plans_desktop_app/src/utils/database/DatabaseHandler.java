/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.LogHandler;
import utils.PropertyHandler;

/**
 *
 * @author touir
 */
public class DatabaseHandler {
    private static Connection connection;
    private static Statement statement;
    private static String propertyPath = "resources/common/properties/database.config.properties";
    
    private DatabaseHandler(){}
    
    private static boolean initConnection(){
        
        try {
            if(connection == null || connection.isClosed()){
                Map<String,String> configuration = PropertyHandler.getProperties(propertyPath);
                connection = DriverManager.getConnection(
                        configuration.get("url"),
                        configuration.get("username"),
                        configuration.get("password")
                );
            }
            return true;
        } catch (SQLException ex) {
            LogHandler.handleException(DatabaseHandler.class.getName(), "initConnection", ex);
            return false;
        }
    }
    
    private static boolean initStatement(){
        try {
            if(statement == null || statement.isClosed()){
                if(initConnection()){
                    statement = connection.createStatement();
                    return true;
                }
                return false;
            }
            return true;
        } catch (SQLException ex) {
            LogHandler.handleException(DatabaseHandler.class.getName(), "initStatement", ex);
            return false;
        }
    }
    
    public static final boolean update(String sql){
        LogHandler.log(DatabaseHandler.class.getName(), "update", sql);
        
        try {
            if(initStatement()){
                statement.executeUpdate(sql);
                return true;
            }
            else{
                return false;
            }
            
        } catch (SQLException ex) {
            LogHandler.handleException(DatabaseHandler.class.getName(), "update", ex);
            return false;
        }
    }
    
    public static final boolean update(String sql, Object[] parameters){
        LogHandler.log(DatabaseHandler.class.getName(), "update", sql);

        try {
            if(initConnection()){
                PreparedStatement ps = connection.prepareStatement(sql);
                for(int i=1; i<=parameters.length; i++){
                    ps.setObject(i, parameters[i-1]);
                }
                ps.execute();
                return true;
            }
            else{
                return false;
            }

        } catch (SQLException ex) {
            LogHandler.handleException(DatabaseHandler.class.getName(), "update", ex);
            return false;
        }
    }
    
    public static final List<Map<String, Object>> select(String sql){
        LogHandler.log(DatabaseHandler.class.getName(), "select", sql);
        
        List<Map<String, Object>> result = new ArrayList<>();
        try {
            if(initStatement()){
                ResultSet resultSet = statement.executeQuery(sql);
                int columnCount = resultSet.getMetaData().getColumnCount();
                while(resultSet.next()){
                    Map<String, Object> row = new HashMap<>();
                    for(int i=1;i<=columnCount;i++){
                        row.put(resultSet.getMetaData().getColumnLabel(i).toUpperCase(), resultSet.getObject(i));
                    }
                    result.add(row);
                }
            }
        } catch (SQLException ex) {
            LogHandler.handleException(DatabaseHandler.class.getName(), "select", ex);
        }
        
        return result;
    }
    
    public static final List<Map<String, Object>> select(String sql, Object[] parameters){
        LogHandler.log(DatabaseHandler.class.getName(), "select", sql);
        
        List<Map<String, Object>> result = new ArrayList<>();
        try {
            if(initConnection()){
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                for(int i=1; i<=parameters.length; i++){
                    preparedStatement.setObject(i, parameters[i-1]);
                }
                ResultSet resultSet = preparedStatement.executeQuery(sql);
                
                int columnCount = resultSet.getMetaData().getColumnCount();
                while(resultSet.next()){
                    Map<String, Object> row = new HashMap<>();
                    for(int i=1;i<=columnCount;i++){
                        row.put(resultSet.getMetaData().getColumnLabel(i).toUpperCase(), resultSet.getObject(i));
                    }
                    result.add(row);
                }
            }
        } catch (SQLException ex) {
            LogHandler.handleException(DatabaseHandler.class.getName(), "select", ex);
        }
        
        return result;
    }
    
}
