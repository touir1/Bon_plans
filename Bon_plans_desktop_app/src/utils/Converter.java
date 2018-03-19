/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    public static <E> E convertMapToEntity(Map<String, Object> map, Class<E> resultClass){
        E result = null;
        try {
            result = resultClass.newInstance();
            
            Map<String, Method> methodMap = new HashMap<>();
            for(Method method : resultClass.getMethods()){
                methodMap.put(method.getName(), method);
            }
            
            for(Field field : resultClass.getDeclaredFields()){
                Method method = methodMap.get("set"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1));
                method.invoke(result, map.get(field.getName().toUpperCase()));
            }
            
        } catch (Exception e) {
            ExceptionHandler.handleException(Converter.class.getName(), "convertMapToEntity", e);
        }
        return result;
    }
    
    public static <E> List<E> convertMapListToEntityList(List<Map<String, Object>> mapList, Class<E> resultClass){
        List<E> resultList = new ArrayList<>();
        
        for(Map<String, Object> map : mapList){
            resultList.add(convertMapToEntity(map,resultClass));
        }
        
        return resultList;
    }
}
