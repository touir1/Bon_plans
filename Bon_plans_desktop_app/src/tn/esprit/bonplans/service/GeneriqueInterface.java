/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.service;

import java.util.List;

/**
 *
 * @author SadfiAmine
 */
public interface GeneriqueInterface<E> {
    public E save(E entity);
    public E update(E entity);
    public List<E>selectAll();
    public E GetByID(int id);
    public void remove(int id);
    public List<E>selectAll(String sortfield,String sort);
    public E FindOne(String ParamName,Object ParamValue);
    
}
