/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import dao.entities.classes.PersonneDaoImpl;
import dao.entities.interfaces.PersonneDao;
import java.util.Map;
import service.entities.classes.Personne;
import utils.DatabaseHandler;

/**
 *
 * @author touir
 */
public class mainTest {
    
    public static void main(String[] args) {
        PersonneDao personneDao = new PersonneDaoImpl();
        System.out.println("FROM DAO");
        for(Personne personne : personneDao.selectAll()){
            System.out.println(personne);
        }
        
        System.out.println("FROM DatabaseHandler");
        String request = "SELECT * FROM personne";
        for(Map<String, Object> row : DatabaseHandler.select(request)){
            System.out.println(row);
        }
    }
    
}
