/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.entities.classes;

import dao.entities.interfaces.PersonneDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import service.entities.classes.Personne;
import utils.Converter;
import utils.DatabaseEntity;
import utils.DatabaseHandler;

/**
 *
 * @author touir
 */
public class PersonneDaoImpl extends DatabaseEntity implements PersonneDao{
    
    public PersonneDaoImpl(){
        super();
    }
    
    @Override
    public List<Personne> selectAll(){
        List<Personne> result = new ArrayList<Personne>();
        
        List<Map<String,Object>> resultList = DatabaseHandler.select(
                getSqlQueryMap().get("selectAll")
        );
        for(Map<String,Object> row : resultList){
            Personne personne = mapToPersonne(row);
            result.add(personne);
        }
        
        return result;
    }
    
    private Personne mapToPersonne(Map<String,Object> mapPersonne){
        Personne result = new Personne();
        
        result.setId(
                Converter.convertObjectToInt(mapPersonne.get("id"))
        );
        result.setAge(
                Converter.convertObjectToInt(mapPersonne.get("age"))
        );
        result.setNom(
                Converter.convertObjectToString(mapPersonne.get("nom"))
        );
        return result;
    }
}
