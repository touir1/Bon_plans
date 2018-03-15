/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

/**
 *
 * @author touir
 */
public class Converter {
    
    private Converter(){};
    
    public static String convertObjectToString(Object obj){
        if(obj != null)
            return String.valueOf(obj);
        return "";
    }
    
    public static Integer convertObjectToInt(Object obj){
        if(obj !=null)
            if(obj instanceof Integer)
                return (Integer) obj;
            else
                return Integer.valueOf(convertObjectToString(obj));
        return 0;
    }
    
    public static Double convertObjectToDouble(Object obj){
        if(obj !=null)
            if(obj instanceof Double)
                return (Double) obj;
            else
                return Double.valueOf(convertObjectToString(obj));
        return 0.0;
    }
}
