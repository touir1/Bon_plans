/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.entities.impl;

import dao.entities.interfaces.PersonneDao;
import java.util.List;
import java.util.Map;
import utils.database.DatabaseEntity;
import utils.database.DatabaseHandler;

/**
 *
 * @author touir
 */
public class PersonneDaoImpl implements PersonneDao{
    
    private static Map<String,String> sqlQueryMap;
    
    public PersonneDaoImpl(){
        sqlQueryMap = DatabaseEntity.getSqlQueryMap(PersonneDaoImpl.class);
    }
    
    @Override
    public List<Map<String,Object>> selectAll(){
        return DatabaseHandler.select(
                sqlQueryMap.get("selectAll")
        );
    }
    
}
