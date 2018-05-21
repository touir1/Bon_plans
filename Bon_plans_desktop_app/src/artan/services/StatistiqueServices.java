/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artan.services;

import artan.dbconnections.DataSource;
import artan.entities.Plan;
import artan.entities.Statistique;
import artan.interfaces.IStatistique;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import tn.esprit.bonplans.entity.Categorie;
import tn.esprit.bonplans.entity.Utilisateur;
import tn.esprit.bonplans.service.ICategorie;
import tn.esprit.bonplans.service.IUtilisateur;
import tn.esprit.bonplans.service.implementation.CategorieImpl;
import tn.esprit.bonplans.service.implementation.UtilisateurImpl;

/**
 *
 * @author Mohamed Ali
 */
public class StatistiqueServices implements IStatistique {

    PlanServices ps = new PlanServices();
    Connection connection = null;

    public StatistiqueServices() {        
        connection = DataSource.getInstance().getConnection();        
    }    

    @Override
    public ArrayList<Statistique> meilleursVentesDuMois(int mois) {
        //SELECT reservation.idPlan, COUNT(*)FROM reservation WHERE reservation.date BETWEEN "2018-04-1" AND "2018-04-31" GROUP BY reservation.idPlan ORDER BY COUNT(*) DESC LIMIT 10
        ArrayList<Statistique> statistiques = new ArrayList<>();
        String requete = "SELECT reservation.idPlan, plan.titre, COUNT(*) nb FROM reservation, plan WHERE reservation.date BETWEEN \"2018-" + mois + "-1\" AND \"2018-" + mois + "-31\" AND plan.idPlan = reservation.idPlan GROUP BY reservation.idPlan ORDER BY COUNT(*) DESC LIMIT 10";        
        
        try {
            Statement statement=connection.createStatement();
            
            ResultSet resultSet =statement.executeQuery(requete);
            
            while(resultSet.next()){
                statistiques.add(new Statistique(resultSet.getInt("idPlan"), resultSet.getString(2), resultSet.getInt("nb")));
            }
            
            System.out.println("Requete select effectuée");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return statistiques;
    }

    @Override
    public ArrayList<Statistique> meilleursVentesDuJours(LocalDate jour) {
        //SELECT reservation.idPlan, COUNT(*)FROM reservation GROUP BY reservation.idPlan ORDER BY COUNT(*) DESC LIMIT 10
        ArrayList<Statistique> statistiques = new ArrayList<>();
        String requete = "SELECT reservation.idPlan, plan.titre, COUNT(*) nb FROM reservation, plan WHERE reservation.date = \"" + jour + "\" And plan.idPlan = reservation.idPlan GROUP BY reservation.idPlan ORDER BY COUNT(*) DESC LIMIT 10";        
        
        try {
            Statement statement=connection.createStatement();
            
            ResultSet resultSet =statement.executeQuery(requete);
            
            while(resultSet.next()){
                statistiques.add(new Statistique(resultSet.getInt("idPlan"), resultSet.getString(2), resultSet.getInt("nb")));
            }
            
            System.out.println("Requete select effectuée");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return statistiques;
    }

    @Override
    public ArrayList<Statistique> meilleurVenteParCategorie(int idCategorie) {
        //SELECT plan_reservation.idPlan, nb FROM `plan_reservation`, plan WHERE plan_reservation.idPlan=plan.idPlan AND plan.idCategorie = 11 ORDER BY nb DESC LIMIT 10
        ArrayList<Statistique> statistiques = new ArrayList<>();
        String requete = "SELECT plan_reservation.idPlan, nb FROM `plan_reservation`, plan WHERE plan_reservation.idPlan=plan.idPlan AND plan.idCategorie = " + idCategorie + " ORDER BY nb DESC LIMIT 10";        
        
        try {
            Statement statement=connection.createStatement();
            
            ResultSet resultSet =statement.executeQuery(requete);
            
            while(resultSet.next()){
                statistiques.add(new Statistique(resultSet.getInt("idPlan"), resultSet.getInt("nb")));
            }
            
            System.out.println("Requete select effectuée");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return statistiques;
    }
    
    @Override
    public ArrayList<Statistique> pireVenteParCategorie(int idCategorie) {
        ArrayList<Statistique> statistiques = new ArrayList<>();
        String requete = "SELECT plan_reservation.idPlan, nb FROM `plan_reservation`, plan WHERE plan_reservation.idPlan=plan.idPlan AND plan.idCategorie = " + idCategorie + " ORDER BY nb ASC LIMIT 10";        
        
        try {
            Statement statement=connection.createStatement();
            
            ResultSet resultSet =statement.executeQuery(requete);
            
            while(resultSet.next()){
                statistiques.add(new Statistique(resultSet.getInt("idPlan"), resultSet.getInt("nb")));
            }
            
            System.out.println("Requete select effectuée");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return statistiques;
    }

    @Override
    public ArrayList<Statistique> meilleurVenteParPersonne(int idPersonne) {
        //SELECT plan_reservation.idPlan, nb FROM `plan_reservation`, plan WHERE plan_reservation.idPlan=plan.idPlan AND plan.idAnnonceur = 34 ORDER BY nb DESC LIMIT 10
        ArrayList<Statistique> statistiques = new ArrayList<>();
        String requete = "SELECT plan_reservation.idPlan, nb FROM `plan_reservation`, plan WHERE plan_reservation.idPlan=plan.idPlan AND plan.idAnnonceur = " + idPersonne + " ORDER BY nb DESC LIMIT 10";        
        
        try {
            Statement statement=connection.createStatement();
            
            ResultSet resultSet =statement.executeQuery(requete);
            
            while(resultSet.next()){
                statistiques.add(new Statistique(resultSet.getInt("idPlan"), resultSet.getInt("nb")));
            }
            
            System.out.println("Requete select effectuée");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return statistiques;
    }
    
    @Override
    public ArrayList<Statistique> pireVenteParPersonne(int idPersonne) {
        //SELECT plan_reservation.idPlan, nb FROM `plan_reservation`, plan WHERE plan_reservation.idPlan=plan.idPlan AND plan.idAnnonceur = 34 ORDER BY nb DESC LIMIT 10
        ArrayList<Statistique> statistiques = new ArrayList<>();
        String requete = "SELECT plan_reservation.idPlan, nb FROM `plan_reservation`, plan WHERE plan_reservation.idPlan=plan.idPlan AND plan.idAnnonceur = " + idPersonne + " ORDER BY nb ASC LIMIT 10";        
        
        try {
            Statement statement=connection.createStatement();
            
            ResultSet resultSet =statement.executeQuery(requete);
            
            while(resultSet.next()){
                statistiques.add(new Statistique(resultSet.getInt("idPlan"), resultSet.getInt("nb")));
            }
            
            System.out.println("Requete select effectuée");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return statistiques;
    }

    @Override
    public ArrayList<Statistique> meilleurDixVentes() {
        
        ArrayList<Statistique> statistiques = new ArrayList<>();
        String requete = "SELECT * FROM `plan_reservation` ORDER BY `nb` DESC LIMIT 10";        
        
        try {
            Statement statement=connection.createStatement();
            
            ResultSet resultSet =statement.executeQuery(requete);
            
            while(resultSet.next()){
                statistiques.add(new Statistique(resultSet.getInt("idPlan"), resultSet.getInt("nb")));
            }
            
            System.out.println("Requete select effectuée");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return statistiques;
    }

    @Override
    public ArrayList<Statistique> pireDixVentes() {
        
        ArrayList<Statistique> statistiques = new ArrayList<>();
        String requete = "SELECT * FROM `plan_reservation` ORDER BY `nb` ASC LIMIT 10";        
        
        try {
            Statement statement=connection.createStatement();
            
            ResultSet resultSet = statement.executeQuery(requete);
            
            while(resultSet.next()){
                statistiques.add(new Statistique(resultSet.getInt("idPlan"), ps.rechercheParID(resultSet.getInt("idPlan")).getTitre(), resultSet.getInt("nb")));
            }
            
            System.out.println("Requete select effectuée");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return statistiques;
    }

    @Override
    public HashMap<Integer, ArrayList<Statistique>> meilleurDixVentesParCategorie() {
        StatistiqueServices ss = new StatistiqueServices();
        HashMap<Integer, ArrayList<Statistique>> hashMap = new HashMap<>();
        ICategorie ic = new CategorieImpl();
        ArrayList<Categorie> categories = (ArrayList<Categorie>) ic.selectAll();
        
        for(Categorie c : categories) {
            hashMap.put(c.getIdCategorie(), ss.meilleurVenteParCategorie(c.getIdCategorie()));
        }
        
        return hashMap;
    }

    @Override
    public HashMap<Integer, ArrayList<Statistique>> pireDixVentesParCategorie() {
        StatistiqueServices ss = new StatistiqueServices();
        HashMap<Integer, ArrayList<Statistique>> hashMap = new HashMap<>();
        ICategorie ic = new CategorieImpl();
        ArrayList<Categorie> categories = (ArrayList<Categorie>) ic.selectAll();
        
        for(Categorie c : categories) {
            hashMap.put(c.getIdCategorie(), ss.pireVenteParCategorie(c.getIdCategorie()));
        }
        
        return hashMap;
    }

    @Override
    public HashMap<Integer, ArrayList<Statistique>> meilleurDixVentesParPersonne() {
        StatistiqueServices ss = new StatistiqueServices();
        HashMap<Integer, ArrayList<Statistique>> map = new HashMap<>();
        IUtilisateur iu = new UtilisateurImpl();
        ArrayList<Utilisateur> users = (ArrayList<Utilisateur>) iu.selectAll();
        
        for(Utilisateur u : users){
            map.put(u.getIdUtilisateur(), ss.meilleurVenteParPersonne(u.getIdUtilisateur()));
        }
        
        return map;
    }

    @Override
    public HashMap<Integer, ArrayList<Statistique>> pireDixVentesParPersonne() {
       StatistiqueServices ss = new StatistiqueServices();
        HashMap<Integer, ArrayList<Statistique>> map = new HashMap<>();
        IUtilisateur iu = new UtilisateurImpl();
        ArrayList<Utilisateur> users = (ArrayList<Utilisateur>) iu.selectAll();
        
        for(Utilisateur u : users){
            map.put(u.getIdUtilisateur(), ss.pireVenteParPersonne(u.getIdUtilisateur()));
        }
        
        return map;
    }

    @Override
    public Plan planlePlusCommenter() {
        String requete = "SELECT idPlan, COUNT(*) FROM `commentaire` group by idPlan ORDER BY COUNT(*) DESC LIMIT 1";
        
        PlanServices ps = new PlanServices();
        
        try {
            Statement statement=connection.createStatement();
            
            ResultSet resultSet = statement.executeQuery(requete);
            
            resultSet.next();
            
            System.out.println("Requete select effectuée");
            
            return ps.rechercheParID(resultSet.getInt(1));
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Plan planLeMoinsCommenter() {
        String requete = "SELECT idPlan, COUNT(*) FROM `commentaire` group by idPlan ORDER BY COUNT(*) ASC LIMIT 1";
        
        PlanServices ps = new PlanServices();
        
        try {
            Statement statement=connection.createStatement();
            
            ResultSet resultSet =statement.executeQuery(requete);
            
            resultSet.next();
            
            System.out.println("Requete select effectuée");
            
            return ps.rechercheParID(resultSet.getInt(1));
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Plan planLePlusAimer() {
        
        String requete = "SELECT * FROM `plan` WHERE plan.like = (SELECT MAX(plan.like) FROM `plan`) LIMIT 1";
        
        PlanServices ps = new PlanServices();
        
        try {
            Statement statement=connection.createStatement();
            
            ResultSet resultSet =statement.executeQuery(requete);
            
            resultSet.next();
            
            System.out.println("Requete select effectuée");
            
            return ps.rechercheParID(resultSet.getInt(1));
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Plan planLePlusDetester() {
        
        String requete = "SELECT * FROM `plan` WHERE plan.dislike = (SELECT MAX(plan.dislike) FROM `plan`) LIMIT 1";
        
        PlanServices ps = new PlanServices();
        
        try {
            Statement statement=connection.createStatement();
            
            ResultSet resultSet =statement.executeQuery(requete);
            
            resultSet.next();
            
            System.out.println("Requete select effectuée");
            
            return ps.rechercheParID(resultSet.getInt(1));
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
        
    }

    @Override
    public int nombreDesJaimes(Plan plan) {
        String requete = "SELECT count(*) FROM `avis` WHERE avi = 1 AND avis.annonce = " + plan.getIdPlan();    
        
        try {
            Statement statement=connection.createStatement();
            
            ResultSet resultSet =statement.executeQuery(requete);
            
            resultSet.next();
            
            System.out.println("Requete select effectuée");
            
            return resultSet.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return 0;
    }

    @Override
    public int nombresDesAbominer(Plan plan) {
        
        String requete = "SELECT count(*) FROM `avis` WHERE avi = 2 AND avis.annonce = " + plan.getIdPlan();    
        try {
            Statement statement=connection.createStatement();
            
            ResultSet resultSet =statement.executeQuery(requete);
            
            resultSet.next();
            
            System.out.println("Requete select effectuée");
            
            return resultSet.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return 0;
        
    }

    @Override
    public int nombreDesPlansPourJour(LocalDate date) {
        //SELECT COUNT(*) FROM plan WHERE plan.dateDebut = "2018-04-24"
        String requete = "SELECT COUNT(*) FROM plan WHERE plan.dateDebut = \"" + date + "\"";    
        
        try {
            Statement statement=connection.createStatement();
            
            ResultSet resultSet =statement.executeQuery(requete);
            
            resultSet.next();
            
            System.out.println("Requete select effectuée");
            
            return resultSet.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return 0;
    }

    @Override
    public int nombreDesPlansParMois(int mois) {
        //SELECT COUNT(*) FROM plan WHERE plan.dateDebut BETWEEN "2018-04-01" AND "2018-04-31"
        String requete = "SELECT COUNT(*) FROM plan WHERE plan.dateDebut BETWEEN \"2018-" + mois + "-01\" AND \"2018-" + mois + "-31\"";    
        
        try {
            Statement statement=connection.createStatement();
            
            ResultSet resultSet =statement.executeQuery(requete);
            
            resultSet.next();
            
            System.out.println("Requete select effectuée");
            
            return resultSet.getInt(1);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return 0;
    }

    @Override
    public double moyenneDesPlansParJour(int mois) {
        //SELECT COUNT(*) FROM plan WHERE plan.dateDebut BETWEEN "2018-04-01" AND "2018-04-31"
        String requete = "SELECT COUNT(*) nb FROM plan WHERE plan.dateDebut BETWEEN \"2018-" + mois + "-01\" AND \"2018-" + mois + "-31\"";    
        int nbPlans = 0;
        
        try {
            Statement statement=connection.createStatement();
            
            ResultSet resultSet =statement.executeQuery(requete);
            
            resultSet.next();
            
            System.out.println("Requete select effectuée");
            
            nbPlans = resultSet.getInt(1);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return (double) nbPlans/(double) 31;
    }
}
