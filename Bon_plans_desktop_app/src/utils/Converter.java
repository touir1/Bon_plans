/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.entity.GenericEnumInterface;

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
            LogHandler.handleException(Converter.class.getName(), "convertMapToEntity", e);
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
    
    public static <E> Map<String, Object> convertEntityToMap(E entity){
        Map<String, Object> result = new HashMap<>();
        
        try {
            Map<String, Method> methodMap = new HashMap<>();
            for(Method method : entity.getClass().getMethods()){
                methodMap.put(method.getName(), method);
            }

            for(Field field : entity.getClass().getDeclaredFields()){
                Method method = methodMap.get("get"+field.getName().substring(0,1).toUpperCase()+field.getName().substring(1));
                result.put(field.getName(), method.invoke(entity, null));
            }
        } catch (Exception ex) {
            LogHandler.handleException(Converter.class.getName(), "convertEntityToMap", ex);
        }
        
        return result;
    }
    
    public static <E> List<Map<String, Object>> convertEntityListToMapList(List<E> entityList){
        List<Map<String, Object>> resultList = new ArrayList<>();
        
        for(E entity : entityList){
            resultList.add(convertEntityToMap(entity));
        }
        
        return resultList;
    }
    
    public static Date convertStringToDate(String date, String format){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(date);
            
        } catch (ParseException ex) {
            LogHandler.handleException(Converter.class.getName(), "convertStringToDate", ex);
        }
        return null;
    }
    
    public static String convertDateToString(Date date, String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
    
    public static String convertObjectToSQLString(Object o){
        if(o != null){
            if(o instanceof String){
                return "'"
                        +convertObjectToString(o)
                                .replace("\"","\\\"")
                                .replace("'", "\\'")
                        +"'";
            }
            else if(o instanceof Date){
                return "STR_TO_DATE('"+convertDateToString((Date) o, "dd/MM/yyyy")+"','%d/%m/%y')";
            }
            else if(o instanceof Double){
                return convertObjectToDouble(o).toString();
            }
            else if(o instanceof Integer){
                return convertObjectToInt(o).toString();
            }
            else if(o instanceof GenericEnumInterface){
                return convertObjectToSQLString(((GenericEnumInterface)o).getValue());
            }
            else{
                return o.toString();
            }
        }
        return null;
    }
}
