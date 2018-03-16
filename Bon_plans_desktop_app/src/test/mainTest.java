/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import dao.entities.impl.PersonneDaoImpl;
import dao.entities.interfaces.PersonneDao;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import service.entities.classes.Personne;
import service.impl.PersonneServiceImpl;
import service.interfaces.PersonneService;
import utils.DatabaseHandler;

/**
 *
 * @author touir
 */
public class mainTest {
    
    public static void main(String[] args) {
        PersonneService personneService = new PersonneServiceImpl();
        
        System.out.println("FROM SERVICE");
        for(Personne personne : personneService.selectAll()){
            System.out.println(personne);
        }
        
        Personne p = new Personne(5, "test", 20);
        Method[] methods = Personne.class.getDeclaredMethods();
        Map<String, Method> methodMap = new HashMap<String, Method>();
        for(Method method : methods){
            methodMap.put(method.getName(), method);
        }
        try{
            for(Field f: Personne.class.getDeclaredFields()){

                Method method = methodMap.get("get"+f.getName().substring(0,1).toUpperCase()+f.getName().substring(1));
                System.out.println(
                        "Nom: "+ f.getName()
                        + " Type: " + f.getType()
                        + " Value: " +  method.invoke(p, null));


                    method.invoke(p, null);

            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        System.out.println("FROM DatabaseHandler");
        String request = "SELECT * FROM personne";
        for(Map<String, Object> row : DatabaseHandler.select(request)){
            System.out.println(row);
        }
    }
    
}
