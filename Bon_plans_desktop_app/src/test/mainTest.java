/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import entities.Personne;
import java.util.Map;
import utils.DatabaseHandler;

/**
 *
 * @author touir
 */
public class mainTest {
    
    public static void main(String[] args) {
        Personne personne = new Personne();
        personne.hello();
        
        String request = "SELECT * FROM personne";
        for(Map<String, Object> row : DatabaseHandler.select(request)){
            System.out.println(row);
        }
    }
    
}
