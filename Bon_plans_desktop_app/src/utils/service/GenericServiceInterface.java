/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.service;

import java.util.List;
import utils.entity.EnumDatabaseSortOrder;

/**
 *
 * @author SadfiAmine
 * @param <E>
 */
public interface GenericServiceInterface<E> {

    /**
     * saves the entity into the database
     * @param entity
     * @return
     */
    public E save(E entity);

    /**
     * updates the entity in the database
     * @param entity
     * @return
     */
    public E update(E entity);

    /**
     * selects all the entities in the database
     * @return
     */
    public List<E> selectAll();

    /**
     * gets the entity by the id
     * @param id
     * @return
     */
    public E getByID(int id);

    /**
     * removes the entity from the database
     * @param id
     */
    public void remove(int id);

    /**
     * select entities from the database sorted by the sortfield ordered by sort
     * @param sortField
     * @param sortOrder
     * @return
     */
    public List<E> selectAll(String sortField, EnumDatabaseSortOrder sortOrder);

    /**
     * find a list of entities which respects a condition
     * @param paramName
     * @param paramValue
     * @return
     */
    public List<E> findOne(String paramName,Object paramValue);

    /**
     * counts the entities by the parameter
     * @param paramName
     * @param paramValue
     * @return
     */
    public int findCountBy(String paramName, Object paramValue);
    
    /**
     * saves the entity into the database
     * @param entity
     * @param serviceResponse
     * @return
     */
    public E save(E entity, ServiceResponse serviceResponse);

    /**
     * updates the entity in the database
     * @param entity
     * @param serviceResponse
     * @return
     */
    public E update(E entity, ServiceResponse serviceResponse);

    /**
     * selects all the entities in the database
     * @param serviceResponse
     * @return
     */
    public List<E> selectAll(ServiceResponse serviceResponse);

    /**
     * gets the entity by the id
     * @param id
     * @param serviceResponse
     * @return
     */
    public E getByID(int id, ServiceResponse serviceResponse);

    /**
     * removes the entity from the database
     * @param id
     * @param serviceResponse
     */
    public void remove(int id, ServiceResponse serviceResponse);

    /**
     * select entities from the database sorted by the sortfield ordered by sort
     * @param sortField
     * @param sortOrder
     * @param serviceResponse
     * @return
     */
    public List<E> selectAll(String sortField, EnumDatabaseSortOrder sortOrder, ServiceResponse serviceResponse);

    /**
     * find a list of entities which respects a condition
     * @param paramName
     * @param paramValue
     * @param serviceResponse
     * @return
     */
    public List<E> findOne(String paramName,Object paramValue, ServiceResponse serviceResponse);

    /**
     * counts the entities by the parameter
     * @param paramName
     * @param paramValue
     * @param serviceResponse
     * @return
     */
    public int findCountBy(String paramName, Object paramValue, ServiceResponse serviceResponse);
}
