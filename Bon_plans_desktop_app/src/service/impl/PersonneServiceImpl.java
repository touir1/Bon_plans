/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service.impl;

import dao.entities.impl.PersonneDaoImpl;
import dao.entities.interfaces.PersonneDao;
import java.util.List;
import service.entities.classes.Personne;
import service.interfaces.PersonneService;
import utils.Converter;

/**
 *
 * @author touir
 */
public class PersonneServiceImpl implements PersonneService{
    
    PersonneDao personneDao;
    
    public PersonneServiceImpl(){
        personneDao = new PersonneDaoImpl();
    }
    
    @Override
    public List<Personne> selectAll() {
        return Converter.convertMapListToEntityList(personneDao.selectAll(), Personne.class);
        
    }

}
