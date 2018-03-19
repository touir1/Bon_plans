/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

/**
 *
 * @author touir
 */
public class PropertyHandler {
    private static Properties properties;
    private static InputStream inputStream;
    
    private PropertyHandler(){};
    
    public static final Map<String, String> getProperties(String path){
        Map<String, String> result = new HashMap<String, String>();
        try {
            properties = new Properties();
            
            inputStream = PropertyHandler.class.getClassLoader().getResourceAsStream(path);
            if (inputStream != null) {
                properties.load(inputStream);
            }
            else {
                throw new FileNotFoundException("property file '" + path + "' not found in the classpath");
            }
            
            for(Entry<Object,Object> entry: properties.entrySet()){
                result.put((String) entry.getKey(), (String) entry.getValue());
            }
        } 
        catch (IOException ex) {
            ExceptionHandler.handleException(PropertyHandler.class.getName(), "getProperties", ex);
        }
        
        return result;
    }
}
