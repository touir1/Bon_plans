/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artan.tests;

import artan.entities.Plan;
import artan.entities.Statistique;
import artan.services.PlanServices;
import artan.services.StatistiqueServices;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Mohamed Ali
 */
public class Test {
    public static void main(String args[]){
        
        LocalDate date = java.time.LocalDate.now();
        Plan plan = new Plan(15, "test", "java", "url photo", 15.00, 12.00, 100, java.sql.Date.valueOf(date),java.sql.Date.valueOf(date), 100, 0, 33, 11);
        PlanServices planservices = new PlanServices();
        StatistiqueServices ss = new StatistiqueServices();
        LocalDate date1 = LocalDate.of(2018, 4, 24);
        
        //planservices.ajouterPlan(plan);
        //planservices.supprimerPlan(19);
        //planservices.modifierPlan(plan);
        //System.out.println(planservices.listePlan());
        //System.out.println(planservices.rechercheParID(17));
        //System.out.println(planservices.rechercheParUtilisateur(33));
        //System.out.println(planservices.rechercheParCategorie(11));
        //planservices.changerStatut(14, 0);
        
        //System.out.println(ss.meilleurDixVentes());
        //System.out.println(ss.nombreDesJaimes(plan));
        //System.out.println(ss.pireDixVentes());
        //System.out.println(ss.nombresDesAbominer(plan));
        //System.out.println(ss.planLePlusAimer());
        //System.out.println(ss.planlePlusCommenter());
        //System.out.println(ss.planLeMoinsCommenter());
        //System.out.println(ss.meilleurVenteParCategorie(11));
        //System.out.println(ss.meilleurVenteParPersonne(34));
        /*
        HashMap<Integer, ArrayList<Statistique>> hm = ss.pireDixVentesParPersonne();
        for(Map.Entry<Integer, ArrayList<Statistique>> item : hm.entrySet()){
            System.out.println("****************************");
            System.out.println(item.getKey() + ":");
            System.out.println(item.getValue());
        }
        */
        
        //System.out.println(ss.nombreDesPlansPourJour(date1));
        //System.out.println(ss.nombreDesPlansParMois(5));
        planservices.incLike(9, 10);
        //System.out.println(ss.meilleursVentesDuJours(date1));
        //System.out.println(ss.meilleursVentesDuMois(5));
        /*
        DecimalFormat df = new DecimalFormat("#.###");
        System.out.println(df.format(ss.moyenneDesPlansParJour(4)));
        */
        
        //System.out.println(planservices.listeDesCommentaires(7));
        /*
        String chaine1 = "dali dali dali kelma dali dali yosra";
        
        System.out.println(Test.controle(chaine1));
        */
    }
    
    public static boolean controle(String chaine){
        ArrayList<String> dictionnaire = new ArrayList();
        dictionnaire.add("kelma");
        dictionnaire.add("zeyda");
        
        String[] items = chaine.split(" ");
        
        for (int i = 0; i < items.length; i++) {
            if(dictionnaire.contains(items[i])){
                return true;
            }
        }
        
        return false;
    }
}