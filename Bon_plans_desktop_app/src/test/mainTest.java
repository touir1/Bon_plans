/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package test;

import java.util.List;
import java.util.Map;
import service.entities.classes.Personne;
import service.impl.PersonneServiceImpl;
import service.interfaces.PersonneService;
import tn.esprit.bonplans.entity.Client;
import tn.esprit.bonplans.service.IClient;
import tn.esprit.bonplans.service.implementation.ClientImpl;
import utils.Converter;
import utils.database.DatabaseEntityHandler;
import utils.database.DatabaseHandler;
import utils.entity.EnumDatabaseSortOrder;

/**
 *
 * @author touir
 */
public class mainTest {
    
    public static void main(String[] args) {
        /*
        PersonneService personneService = new PersonneServiceImpl();
        
        System.out.println("FROM SERVICE");
        for(Personne personne : personneService.selectAll()){
            System.out.println(personne);
        }
        */
        
        /*
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
        */
        
        /*
        System.out.println("FROM DatabaseHandler");
        String request = "SELECT * FROM personne";
        for(Map<String, Object> row : DatabaseHandler.select(request)){
            System.out.println(row);
        }
        
        System.out.println("With DatabaseHandler + converting Map to Entity");
        request = "SELECT * FROM personne";
        List<Map<String, Object>> result = DatabaseHandler.select(request);
        for(Personne personne : Converter.convertMapListToEntityList(result, Personne.class)){
            System.out.println(personne);
        }
        
        DatabaseEntityHandler.selectAll(Personne.class);

        */
        
        IClient clientService = new ClientImpl();
        
        Client toInsert1 = new Client("Touir", "Mohamed Ali", "", "Rades", "06 kawafel Street Rades, Ben Arous, Tunisie", "mohamedali.touir@esprit.tn", "12345678");
        toInsert1 = clientService.save(toInsert1);
        System.out.println(toInsert1);
        
        for(Client client : clientService.selectAll()){
            System.out.println(client);
        }
        
        System.out.println(clientService.findCountBy("nom", "Touir"));
        System.out.println(clientService.getByID(toInsert1.getIdClient()));
        
        System.out.println(clientService.findOne("nom", "Touir"));
        
        Client toInsert2 = new Client("Touir", "Mehdi", "", "Rades", "06 kawafel Street Rades, Ben Arous, Tunisie", "mehdi.touir@esprit.tn", "12345678");
        toInsert2 = clientService.save(toInsert2);
        System.out.println(toInsert2);
        
        
        for(Client client : clientService.selectAll("prenom", EnumDatabaseSortOrder.ASC)){
            System.out.println(client);
        }
        for(Client client : clientService.selectAll("prenom", EnumDatabaseSortOrder.DESC)){
            System.out.println(client);
        }
        
        toInsert1.setEmail("touir.mat@gmail.com");
        System.out.println(clientService.update(toInsert1));
        
        clientService.remove(toInsert1.getIdClient());
        clientService.remove(toInsert2.getIdClient());
        
        for(Client client : clientService.selectAll()){
            System.out.println(client);
        }
    }
    
}
