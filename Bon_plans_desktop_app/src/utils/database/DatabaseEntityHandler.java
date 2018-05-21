/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils.database;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import utils.Converter;
import utils.entity.EnumDatabaseSortOrder;
import utils.service.ServiceResponse;

/**
 *
 * @author touir
 */
public class DatabaseEntityHandler {
    
    public static <E> List<E> selectAll(Class<E> entityClass, String tableName){
        String sql = "SELECT * FROM "+tableName;
        return Converter.convertMapListToEntityList(DatabaseHandler.select(sql), entityClass);
    }
    
    public static <E> List<E> selectAll(Class<E> entityClass){
        return selectAll(
                entityClass, 
                entityClass.getSimpleName().toLowerCase()
        );
    }
    
    public static <E> E save(E entity, String tableName, String idAttribute){

        String values = "";
        boolean firstField = true;
        String sql = "INSERT INTO "+tableName+"(";
        String selectSQL = "SELECT * FROM "+tableName+" WHERE 1=1";
        
        Map<String, Object> entityMap = Converter.convertEntityToMap(entity);
        
        for (Map.Entry<String, Object> entry : entityMap.entrySet())
        {
            if(!entry.getKey().toLowerCase().trim().equals(idAttribute.toLowerCase().trim())){
                if(firstField){
                    firstField = false;
                }
                else{
                    sql += ",";
                    values += ",";
                }

                sql += "`"+entry.getKey()+"`";
                values += Converter.convertObjectToSQLString(entry.getValue());
                selectSQL += " AND "+"`"+entry.getKey()+"`" + Converter.replaceNull(entry.getValue()," = "," is ")+ Converter.convertObjectToSQLString(entry.getValue());
            }
        }
        
        sql += ") VALUES("+values+")";
        DatabaseHandler.update(sql);
        
        List<Map<String, Object>> selectResult = DatabaseHandler.select(selectSQL);
        if(selectResult!=null && !selectResult.isEmpty()){
            return Converter.convertMapToEntity(
                    selectResult.get(selectResult.size()-1), 
                    (Class<E>) entity.getClass()
            );
        }
        else{
            return null;
        }
    }
    
    public static <E> E save(E entity, String idAttribute){
        return save(
                entity,
                entity.getClass().getSimpleName().toLowerCase(),
                idAttribute
        );
    }
    
    public static <E> E save(E entity){
        return save(
                entity,
                entity.getClass().getSimpleName().toLowerCase(),
                "id"+entity.getClass().getSimpleName()
        );
    }
    
    public static <E> E update(E entity, String tableName, String idAttribute){
        
        boolean firstField = true;
        String sql = "UPDATE "+tableName+" SET ";
        Object idValue = null;
        
        Map<String, Object> entityMap = Converter.convertEntityToMap(entity);
        
        for (Map.Entry<String, Object> entry : entityMap.entrySet())
        {
            if(!entry.getKey().toLowerCase().trim().equals(idAttribute.toLowerCase().trim())){
                if(firstField){
                    firstField = false;
                }
                else{
                    sql += ",";
                }

                sql += "`"+entry.getKey()+"`"+"="+Converter.convertObjectToSQLString(entry.getValue());
            }
            else{
                idValue = entry.getValue();
            }
        }
        
        sql += " WHERE "+idAttribute+"="+Converter.convertObjectToSQLString(idValue);
        DatabaseHandler.update(sql);
        
        return getByID((Class<E>) entity.getClass(), idAttribute, Converter.convertObjectToInt(idValue));
    }
    
    public static <E> E update(E entity, String idAttribute){
        
        return update(
                entity,
                entity.getClass().getSimpleName().toLowerCase(),
                idAttribute
        );
    }
    
    public static <E> E update(E entity){
        
        return update(
                entity, 
                entity.getClass().getSimpleName().toLowerCase(), 
                "id"+entity.getClass().getSimpleName()
        );
    }
    
    public static <E> E getByID(Class<E> entityClass, String tableName,String idAttribute, int id){
        String sql = "SELECT * FROM "+tableName
                + " WHERE "+idAttribute+"="+id;
        
        List<Map<String, Object>> resultList = DatabaseHandler.select(sql);
        if(resultList!=null && !resultList.isEmpty()){
            return Converter.convertMapToEntity(
                    resultList.get(0), 
                    entityClass
            );
        }
        else{
            return null;
        }
    }
    
    public static <E> E getByID(Class<E> entityClass,String idAttribute, int id){

        return getByID(
                entityClass, 
                entityClass.getSimpleName().toLowerCase(), 
                idAttribute, 
                id
        );
    }
    
    public static <E> E getByID(Class<E> entityClass, int id){
        return getByID(
                entityClass, 
                entityClass.getSimpleName().toLowerCase(), 
                "id"+entityClass.getSimpleName(), 
                id
        );
    }
    
    public static <E> void remove(Class<E> entityClass, String tableName, String idAttribute, int id){
        String sql = "DELETE FROM "+tableName
                + " WHERE "+idAttribute+" = "+id;
        
        DatabaseHandler.update(sql);
    }
    
    public static <E> void remove(Class<E> entityClass, String idAttribute, int id){
        remove(
                entityClass,
                entityClass.getSimpleName().toLowerCase(),
                idAttribute,
                id
        );
    }
    
    public static <E> void remove(Class<E> entityClass, int id){
        remove(
                entityClass,
                entityClass.getSimpleName().toLowerCase(),
                "id"+entityClass.getSimpleName(),
                id
        );
    }
    
    public static <E> List<E> selectAll(Class<E> entityClass, String tableName, String sortField, EnumDatabaseSortOrder sortOrder){
        String sql = "SELECT * FROM "
                + tableName
                + " ORDER BY "+sortField+" "+sortOrder.getValue();
        
        return Converter.convertMapListToEntityList(
                DatabaseHandler.select(sql),
                entityClass
        );
    }
    
    public static <E> List<E> selectAll(Class<E> entityClass, String sortField, EnumDatabaseSortOrder sortOrder){
        return selectAll(
                entityClass, 
                entityClass.getSimpleName().toLowerCase(),
                sortField,
                sortOrder
        );
    }
    
    public static <E> int findCountBy(Class<E> entityClass, String tableName, String paramName, Object paramValue){
        String sql = "SELECT COUNT(*) AS count FROM "+tableName
                + " WHERE "+paramName+Converter.replaceNull(paramValue," = "," is ")+ Converter.convertObjectToSQLString(paramValue);
        
        return Converter.convertObjectToInt(
                DatabaseHandler.select(sql).get(0).get("COUNT")
        );
        
    }
    
    public static <E> int findCountBy(Class<E> entityClass, String paramName, Object paramValue){
        return findCountBy(
                entityClass,
                entityClass.getSimpleName().toLowerCase(),
                paramName,
                paramValue
        );
    }
    
    public static <E> List<E> findOne(Class<E> entityClass, String tableName, String paramName,Object paramValue){
        String sql = "SELECT * FROM "+tableName
                + " WHERE "+paramName+Converter.replaceNull(paramValue," = "," is ")+Converter.convertObjectToSQLString(paramValue);
        
        List<Map<String, Object>> resultList = DatabaseHandler.select(sql);
        if(resultList!=null && !resultList.isEmpty()){
            return Converter.convertMapListToEntityList(
                    DatabaseHandler.select(sql), 
                    entityClass
            );
        }
        else{
            return new ArrayList<>();
        }
    }
    
    public static <E> List<E> findOne(Class<E> entityClass, String paramName,Object paramValue){
        return findOne(
                entityClass,
                entityClass.getSimpleName().toLowerCase(),
                paramName,
                paramValue
        );
    }
    
    public static <E> List<E> selectAll(Class<E> entityClass, String tableName, ServiceResponse serviceResponse){
        String sql = "SELECT * FROM "+tableName;
        return Converter.convertMapListToEntityList(DatabaseHandler.select(sql,serviceResponse), entityClass);
    }
    
    public static <E> List<E> selectAll(Class<E> entityClass, ServiceResponse serviceResponse){
        return selectAll(
                entityClass, 
                entityClass.getSimpleName().toLowerCase(),
                serviceResponse
        );
    }
    
    public static <E> E save(E entity, String tableName, String idAttribute, ServiceResponse serviceResponse){

        String values = "";
        boolean firstField = true;
        String sql = "INSERT INTO "+tableName+"(";
        String selectSQL = "SELECT * FROM "+tableName+" WHERE 1=1";
        
        Map<String, Object> entityMap = Converter.convertEntityToMap(entity);
        
        for (Map.Entry<String, Object> entry : entityMap.entrySet())
        {
            if(!entry.getKey().toLowerCase().trim().equals(idAttribute.toLowerCase().trim())){
                if(firstField){
                    firstField = false;
                }
                else{
                    sql += ",";
                    values += ",";
                }

                sql += "`"+entry.getKey()+"`";
                values += Converter.convertObjectToSQLString(entry.getValue());
                selectSQL += " AND "+"`"+entry.getKey()+"`" + Converter.replaceNull(entry.getValue()," = "," is ")+ Converter.convertObjectToSQLString(entry.getValue());
            }
        }
        
        sql += ") VALUES("+values+")";
        DatabaseHandler.update(sql,serviceResponse);
        
        List<Map<String, Object>> selectResult = DatabaseHandler.select(selectSQL,serviceResponse);
        if(selectResult!=null && !selectResult.isEmpty()){
            return Converter.convertMapToEntity(
                    selectResult.get(selectResult.size()-1), 
                    (Class<E>) entity.getClass()
            );
        }
        else{
            return null;
        }
    }
    
    public static <E> E save(E entity, String idAttribute, ServiceResponse serviceResponse){
        return save(
                entity,
                entity.getClass().getSimpleName().toLowerCase(),
                idAttribute,
                serviceResponse
        );
    }
    
    public static <E> E save(E entity, ServiceResponse serviceResponse){
        return save(
                entity,
                entity.getClass().getSimpleName().toLowerCase(),
                "id"+entity.getClass().getSimpleName(),
                serviceResponse
        );
    }
    
    public static <E> E update(E entity, String tableName, String idAttribute, ServiceResponse serviceResponse){
        
        boolean firstField = true;
        String sql = "UPDATE "+tableName+" SET ";
        Object idValue = null;
        
        Map<String, Object> entityMap = Converter.convertEntityToMap(entity);
        
        for (Map.Entry<String, Object> entry : entityMap.entrySet())
        {
            if(!entry.getKey().toLowerCase().trim().equals(idAttribute.toLowerCase().trim())){
                if(firstField){
                    firstField = false;
                }
                else{
                    sql += ",";
                }

                sql += "`"+entry.getKey()+"`"+"="+Converter.convertObjectToSQLString(entry.getValue());
            }
            else{
                idValue = entry.getValue();
            }
        }
        
        sql += " WHERE "+idAttribute+"="+Converter.convertObjectToSQLString(idValue);
        DatabaseHandler.update(sql,serviceResponse);
        
        return getByID((Class<E>) entity.getClass(), idAttribute, Converter.convertObjectToInt(idValue),serviceResponse);
    }
    
    public static <E> E update(E entity, String idAttribute, ServiceResponse serviceResponse){
        
        return update(
                entity,
                entity.getClass().getSimpleName().toLowerCase(),
                idAttribute,
                serviceResponse
        );
    }
    
    public static <E> E update(E entity, ServiceResponse serviceResponse){
        
        return update(
                entity, 
                entity.getClass().getSimpleName().toLowerCase(), 
                "id"+entity.getClass().getSimpleName(),
                serviceResponse
        );
    }
    
    public static <E> E getByID(Class<E> entityClass, String tableName,String idAttribute, int id, ServiceResponse serviceResponse){
        String sql = "SELECT * FROM "+tableName
                + " WHERE "+idAttribute+"="+id;
        
        List<Map<String, Object>> resultList = DatabaseHandler.select(sql,serviceResponse);
        if(resultList!=null && !resultList.isEmpty()){
            return Converter.convertMapToEntity(
                    resultList.get(0), 
                    entityClass
            );
        }
        else{
            return null;
        }
    }
    
    public static <E> E getByID(Class<E> entityClass,String idAttribute, int id, ServiceResponse serviceResponse){

        return getByID(
                entityClass, 
                entityClass.getSimpleName().toLowerCase(), 
                idAttribute, 
                id,
                serviceResponse
        );
    }
    
    public static <E> E getByID(Class<E> entityClass, int id, ServiceResponse serviceResponse){
        return getByID(
                entityClass, 
                entityClass.getSimpleName().toLowerCase(), 
                "id"+entityClass.getSimpleName(), 
                id,
                serviceResponse
        );
    }
    
    public static <E> void remove(Class<E> entityClass, String tableName, String idAttribute, int id, ServiceResponse serviceResponse){
        String sql = "DELETE FROM "+tableName
                + " WHERE "+idAttribute+" = "+id;
        
        DatabaseHandler.update(sql,serviceResponse);
    }
    
    public static <E> void remove(Class<E> entityClass, String idAttribute, int id, ServiceResponse serviceResponse){
        remove(
                entityClass,
                entityClass.getSimpleName().toLowerCase(),
                idAttribute,
                id,
                serviceResponse
        );
    }
    
    public static <E> void remove(Class<E> entityClass, int id, ServiceResponse serviceResponse){
        remove(
                entityClass,
                entityClass.getSimpleName().toLowerCase(),
                "id"+entityClass.getSimpleName(),
                id,
                serviceResponse
        );
    }
    
    public static <E> List<E> selectAll(Class<E> entityClass, String tableName, String sortField, EnumDatabaseSortOrder sortOrder, ServiceResponse serviceResponse){
        String sql = "SELECT * FROM "
                + tableName
                + " ORDER BY "+sortField+" "+sortOrder.getValue();
        
        return Converter.convertMapListToEntityList(
                DatabaseHandler.select(sql,serviceResponse),
                entityClass
        );
    }
    
    public static <E> List<E> selectAll(Class<E> entityClass, String sortField, EnumDatabaseSortOrder sortOrder, ServiceResponse serviceResponse){
        return selectAll(
                entityClass, 
                entityClass.getSimpleName().toLowerCase(),
                sortField,
                sortOrder,
                serviceResponse
        );
    }
    
    public static <E> int findCountBy(Class<E> entityClass, String tableName, String paramName, Object paramValue, ServiceResponse serviceResponse){
        String sql = "SELECT COUNT(*) AS count FROM "+tableName
                + " WHERE "+paramName+Converter.replaceNull(paramValue," = "," is ")+ Converter.convertObjectToSQLString(paramValue);
        
        return Converter.convertObjectToInt(
                DatabaseHandler.select(sql,serviceResponse).get(0).get("COUNT")
        );
        
    }
    
    public static <E> int findCountBy(Class<E> entityClass, String paramName, Object paramValue, ServiceResponse serviceResponse){
        return findCountBy(
                entityClass,
                entityClass.getSimpleName().toLowerCase(),
                paramName,
                paramValue,
                serviceResponse
        );
    }
    
    public static <E> List<E> findOne(Class<E> entityClass, String tableName, String paramName,Object paramValue, ServiceResponse serviceResponse){
        String sql = "SELECT * FROM "+tableName
                + " WHERE "+paramName+Converter.replaceNull(paramValue," = "," is ")+Converter.convertObjectToSQLString(paramValue);
        
        List<Map<String, Object>> resultList = DatabaseHandler.select(sql,serviceResponse);
        if(resultList!=null && !resultList.isEmpty()){
            return Converter.convertMapListToEntityList(
                    DatabaseHandler.select(sql), 
                    entityClass
            );
        }
        else{
            return new ArrayList<>();
        }
    }
    
    public static <E> List<E> findOne(Class<E> entityClass, String paramName,Object paramValue, ServiceResponse serviceResponse){
        return findOne(
                entityClass,
                entityClass.getSimpleName().toLowerCase(),
                paramName,
                paramValue,
                serviceResponse
        );
    }
}
