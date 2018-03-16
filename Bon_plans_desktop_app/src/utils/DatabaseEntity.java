/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.util.Map;

/**
 *
 * @author touir
 */
public class DatabaseEntity {
    
    private DatabaseEntity(){};
    
    public static Map<String,String> getSqlQueryMap(Class<?> daoClass){
        return initSqlQueryMap(daoClass);
    }
    
    private static Map<String, String> initSqlQueryMap(Class<?> daoClass){
        String enclosingClass = enclosingClass(daoClass);
        return PropertyHandler
                .getProperties(
                        "dao/entities/properties/"
                        + enclosingClass.replace("DaoImpl", "").toLowerCase()
                        + ".config.properties"
                );
    }
    
    private static String enclosingClass(Class<?> daoClass){
        Class<?> enclosingClass = daoClass.getEnclosingClass();
        String result;
        if (enclosingClass != null) {
          result = enclosingClass.getSimpleName();
        } else {
          result = daoClass.getSimpleName();
        }
        if(result != null && result.contains(".")){
            result = result.substring(result.lastIndexOf("."+1));
        }
        
        return result;
    }
}
