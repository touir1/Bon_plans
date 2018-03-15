/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author touir
 */
public class PropertyHandler {
    private static Properties properties;
    private static InputStream inputStream;
    
    private PropertyHandler(){};
    
    public static Map<String, String> getProperties(String path){
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
            Logger.getLogger(PropertyHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
}
