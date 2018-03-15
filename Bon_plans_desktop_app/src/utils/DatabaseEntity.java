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
    
    private Map<String,String> sqlQueryMap;
    
    protected DatabaseEntity(){
        sqlQueryMap = initSqlQueryMap();
    };
    
    protected Map<String,String> getSqlQueryMap(){
        return sqlQueryMap;
    }
    
    private Map<String, String> initSqlQueryMap(){
        String enclosingClass = enclosingClass();
        return PropertyHandler
                .getProperties(
                        "resources/properties/entities/"
                        + enclosingClass.toLowerCase()
                        + ".config.properties"
                );
    }
    
    private String enclosingClass(){
        Class<?> enclosingClass = getClass().getEnclosingClass();
        String result;
        if (enclosingClass != null) {
          result = enclosingClass.getSimpleName();
        } else {
          result = getClass().getSimpleName();
        }
        if(result != null && result.contains(".")){
            result = result.substring(result.lastIndexOf("."+1));
        }
        
        return result;
    }
}
