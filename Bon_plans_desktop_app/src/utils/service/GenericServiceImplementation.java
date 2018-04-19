/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils.service;

import java.util.List;
import utils.database.DatabaseEntityHandler;
import utils.entity.EnumDatabaseSortOrder;

/**
 *
 * @author touir
 */
public class GenericServiceImplementation<E> implements GenericServiceInterface<E>{
    
    private Class<E> myClass = null;
    
    @Override
    public E save(E entity) {
        return DatabaseEntityHandler.save(entity);
    }

    @Override
    public E update(E entity) {
        
        return DatabaseEntityHandler.update(entity);
    }

    @Override
    public List<E> selectAll() {
        return DatabaseEntityHandler.selectAll(myClass);
    }

    @Override
    public E getByID(int id) {
        return DatabaseEntityHandler.getByID(myClass, id);
    }

    @Override
    public void remove(int id) {
        DatabaseEntityHandler.remove(myClass, id);
    }

    @Override
    public List<E> selectAll(String sortField, EnumDatabaseSortOrder sortOrder) {
        return DatabaseEntityHandler.selectAll(myClass, sortField, sortOrder);
    }

    @Override
    public List<E> findOne(String paramName, Object paramValue) {
        return DatabaseEntityHandler.findOne(myClass, paramName, paramValue);
    }

    @Override
    public int findCountBy(String paramName, Object paramValue) {
        return DatabaseEntityHandler.findCountBy(myClass, paramName, paramValue);
    }
    
    @Override
    public E save(E entity, ServiceResponse serviceResponse) {
        return DatabaseEntityHandler.save(entity, serviceResponse);
    }

    @Override
    public E update(E entity, ServiceResponse serviceResponse) {
        
        return DatabaseEntityHandler.update(entity, serviceResponse);
    }

    @Override
    public List<E> selectAll(ServiceResponse serviceResponse) {
        return DatabaseEntityHandler.selectAll(myClass, serviceResponse);
    }

    @Override
    public E getByID(int id, ServiceResponse serviceResponse) {
        return DatabaseEntityHandler.getByID(myClass, id, serviceResponse);
    }

    @Override
    public void remove(int id, ServiceResponse serviceResponse) {
        DatabaseEntityHandler.remove(myClass, id, serviceResponse);
    }

    @Override
    public List<E> selectAll(String sortField, EnumDatabaseSortOrder sortOrder, ServiceResponse serviceResponse) {
        return DatabaseEntityHandler.selectAll(myClass, sortField, sortOrder, serviceResponse);
    }

    @Override
    public List<E> findOne(String paramName, Object paramValue, ServiceResponse serviceResponse) {
        return DatabaseEntityHandler.findOne(myClass, paramName, paramValue, serviceResponse);
    }

    @Override
    public int findCountBy(String paramName, Object paramValue, ServiceResponse serviceResponse) {
        return DatabaseEntityHandler.findCountBy(myClass, paramName, paramValue, serviceResponse);
    }
    
    protected GenericServiceImplementation(Class<E> myClass){
        this.myClass = myClass;
    }
}
