/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artan.services;

import artan.dbconnections.DataSource;
import artan.entities.Plan;
import artan.interfaces.IPlan;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import tn.esprit.bonplans.entity.Commentaire;

/**
 *
 * @author Mohamed Ali
 */
public class PlanServices implements IPlan{

    Connection connection=null;

    public PlanServices() {
        connection = DataSource.getInstance().getConnection();
    }
    
    @Override
    public void ajouterPlan(Plan plan) {
        String requete = "INSERT INTO `plan` (`idPlan`, `titre`, `description`, `urlPhoto`, `prixInitial`, `prixPromo`, `nbPlaceTotal`, `dateDebut`, `dateFin`, `nbPlaceDispo`, `statut`, `idAnnonceur`, `idCategorie`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, '0', ?, ?);";
        
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(requete);

            
            preparedStatement.setString(1, plan.getTitre());
            preparedStatement.setString(2, plan.getDescription());
            preparedStatement.setString(3, plan.getUrlPhoto());
            preparedStatement.setDouble(4, plan.getPrix());
            preparedStatement.setDouble(5, plan.getPrixPromo());
            preparedStatement.setInt(6, plan.getQuantite());
            preparedStatement.setDate(7, (Date) plan.getDateDebut());
            preparedStatement.setDate(8, (Date) plan.getDateFin());
            preparedStatement.setInt(9, plan.getQuantite());
            preparedStatement.setInt(10, plan.getIdAnnonceur());
            preparedStatement.setInt(11, plan.getIdCategorie());
            
            preparedStatement.execute();

            System.out.println("Insertion effectuée");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void supprimerPlan(int id) {
        String requete = "DELETE FROM `plan` WHERE `plan`.`idPlan` = " + id;
        
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(requete);

            preparedStatement.execute();

            System.out.println("Suppression effectuée");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void modifierPlan(Plan plan) {
        
        String requete = "UPDATE `plan` SET `titre` = ?, `description` = ?, `prixInitial` = ?, `prixPromo` = ?, `nbPlaceTotal` = ?, `dateDebut` = ?, `dateFin` = ?, `nbPlaceDispo` = ?, `statut` = ? WHERE `plan`.`idPlan` = ?;";
        
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(requete);

            preparedStatement.setString(1, plan.getTitre());
            preparedStatement.setString(2, plan.getDescription());
            preparedStatement.setDouble(3, plan.getPrix());
            preparedStatement.setDouble(4, plan.getPrixPromo());
            preparedStatement.setInt(5, plan.getQuantite());
            preparedStatement.setDate(6, (Date) plan.getDateDebut());
            preparedStatement.setDate(7, (Date) plan.getDateFin());
            preparedStatement.setInt(8, plan.getQuantite());
            preparedStatement.setInt(9, plan.getStatut());
            preparedStatement.setInt(10, plan.getIdPlan());
            
            preparedStatement.execute();

            System.out.println("Modification effectuée");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Plan> listePlan() {
        
        ArrayList<Plan> plans = new ArrayList<>();
        
        String requete = "SELECT * FROM `plan`";
        
        try {
            Statement statement=connection.createStatement();
            
            ResultSet resultSet =statement.executeQuery(requete);
            
            while(resultSet.next()){
                plans.add(new Plan(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5)
                                    , resultSet.getDouble(6), resultSet.getInt(7), resultSet.getDate(8), resultSet.getDate(9), resultSet.getInt(10)
                                    , resultSet.getInt(11), resultSet.getInt(12), resultSet.getInt(13)));
            }
            
            System.out.println("Requete select effectuée");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return plans;
    }

    @Override
    public Plan rechercheParID(int id) {
        Plan plan = new Plan();
        
        String requete = "SELECT * FROM `plan` WHERE `idPlan` = " + id;
        
        try {
            Statement statement=connection.createStatement();
            
            ResultSet resultSet =statement.executeQuery(requete);
            
            while(resultSet.next()){
                plan = new Plan(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5)
                                    , resultSet.getDouble(6), resultSet.getInt(7), resultSet.getDate(8), resultSet.getDate(9), resultSet.getInt(10)
                                    , resultSet.getInt(11), resultSet.getInt(12), resultSet.getInt(13));
            }
            
            System.out.println("Requete select effectuée");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return plan;
    }

    @Override
    public ArrayList<Plan> rechercheParUtilisateur(int idUtilisateur) {
        
        ArrayList<Plan> plans = new ArrayList<>();
        
        String requete = "SELECT * FROM `plan` WHERE `idAnnonceur` = " + idUtilisateur;
        
        try {
            Statement statement=connection.createStatement();
            
            ResultSet resultSet =statement.executeQuery(requete);
            
            while(resultSet.next()){
                plans.add(new Plan(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5)
                                    , resultSet.getDouble(6), resultSet.getInt(7), resultSet.getDate(8), resultSet.getDate(9), resultSet.getInt(10)
                                    , resultSet.getInt(11), resultSet.getInt(12), resultSet.getInt(13)));
            }
            
            System.out.println("Requete select effectuée");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return plans;
    }

    @Override
    public void changerStatut(int id, int statut) {
        
        String requete = "UPDATE `plan` SET `statut` = ? WHERE `plan`.`idPlan` = ?";
        
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(requete);

            preparedStatement.setInt(1, statut);
            preparedStatement.setInt(2, id);
            
            preparedStatement.execute();

            System.out.println("Changement de statut est effectuée");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Commentaire> listeDesCommentaires(int idPlan) {
        
        ArrayList<Commentaire> plans = new ArrayList<>();
        
        String requete = "SELECT * FROM `commentaire` WHERE `idPlan` = " + idPlan;
        
        try {
            Statement statement=connection.createStatement();
            
            ResultSet resultSet =statement.executeQuery(requete);
            
            while(resultSet.next()){
                plans.add(new Commentaire(resultSet.getInt(1), resultSet.getString(2), resultSet.getDate(3), resultSet.getInt(4), resultSet.getInt(5), resultSet.getInt(6), resultSet.getInt(7)));
            }
            
            System.out.println("Requete select effectuée");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return plans;
        
    }
    
    @Override
    public ArrayList<Plan> rechercheParCategorie(int idCategorie) {
        
        ArrayList<Plan> plans = new ArrayList<>();
        
        String requete = "SELECT * FROM `plan` WHERE `idCategorie` = " + idCategorie;
        
        try {
            Statement statement=connection.createStatement();
            
            ResultSet resultSet =statement.executeQuery(requete);
            
            while(resultSet.next()){
                plans.add(new Plan(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5)
                                    , resultSet.getDouble(6), resultSet.getInt(7), resultSet.getDate(8), resultSet.getDate(9), resultSet.getInt(10)
                                    , resultSet.getInt(11), resultSet.getInt(12), resultSet.getInt(13)));
            }
            
            System.out.println("Requete select effectuée");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return plans;
        
    }
    
    public void incLike(int idPlan, int nb){
        
        nb = nb +1;
        String requete = "UPDATE `plan` SET `like` = ? WHERE `plan`.`idPlan` = ? ";
        
        try {
            System.out.println("ici");
            PreparedStatement preparedStatement= connection.prepareStatement(requete);
            preparedStatement.setInt(1, nb);
            preparedStatement.setInt(2, idPlan);
            
            preparedStatement.execute();

            System.out.println("like incrimenter bd");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void incDislike(int idPlan, int nb){
        
        nb = nb +1;
        String requete = "UPDATE `plan` SET `dislike` = ? WHERE `plan`.`idPlan` = ? ";
        
        try {
            System.out.println("ici");
            PreparedStatement preparedStatement= connection.prepareStatement(requete);
            preparedStatement.setInt(1, nb);
            preparedStatement.setInt(2, idPlan);
            
            preparedStatement.execute();

            System.out.println("dislike incrimenter bd");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
