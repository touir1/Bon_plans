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
    
    protected GenericServiceImplementation(Class<E> myClass){
        this.myClass = myClass;
    }
}
