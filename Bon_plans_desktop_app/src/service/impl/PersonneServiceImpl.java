/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service.impl;

import dao.entities.impl.PersonneDaoImpl;
import dao.entities.interfaces.PersonneDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        return mapListToPersonneList(
                personneDao.selectAll()
        );
        
    }
    
    private Personne mapToPersonne(Map<String,Object> mapPersonne){
        Personne result = new Personne();
        
        result.setId(
                Converter.convertObjectToInt(mapPersonne.get("ID"))
        );
        result.setAge(
                Converter.convertObjectToInt(mapPersonne.get("AGE"))
        );
        result.setNom(
                Converter.convertObjectToString(mapPersonne.get("NOM"))
        );
        return result;
    }
    
    private List<Personne> mapListToPersonneList(List<Map<String,Object>> mapListPersonne){
        List<Personne> resultList = new ArrayList<>();
        
        for(Map<String,Object> row : mapListPersonne){
            resultList.add(mapToPersonne(row));
        }
        
        return resultList;
    }

}
