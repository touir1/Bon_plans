/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.service.implementation;

import DataSource.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.bonplans.entity.Categorie;
import tn.esprit.bonplans.entity.Commentaire;
import tn.esprit.bonplans.service.ICommentaire;
import utils.entity.EnumDatabaseSortOrder;

/**
 *
 * @author SadfiAmine
 */
public class CommentaireImpl implements ICommentaire{

     static  Connection con;
    private static  Statement ste;
    @Override
    public Commentaire save(Commentaire entity) {
        con=DataSource.getInstance().getConnection();
         try {
             ste=con.createStatement();
             String requete = "INSERT INTO commentaire (Texte,Date,NbJaime,NbJaimePas,IdClient,IdPlan) VALUES('"+entity.getTexte()+"','"+entity.getDate()+"','"+entity.getNbJaime()+"','"+entity.getNbJaimePas()+"','"+entity.getIdClient()+"','"+entity.getIdPlan()+")";
            int nbMaj = ste.executeUpdate(requete);
         } catch (SQLException ex) {
             Logger.getLogger(CommentaireImpl.class.getName()).log(Level.SEVERE, null, ex);
         }
         return entity;
    }

    @Override
    public Commentaire update(Commentaire entity) {
        con=DataSource.getInstance().getConnection();
         try {
             ste=con.createStatement();
             String requete = "UPDATE commentaire SET Texte ="+entity.getTexte()+",Date ="+entity.getDate()+",NbJaime ="+entity.getNbJaime()+",NbJaimePas ="+entity.getNbJaime()+",IdClient ="+entity.getIdClient()+",IdPlan ="+entity.getIdPlan()+"WHERE IdCommentaire="+entity.getIdCommentaire();
            int nbMaj = ste.executeUpdate(requete);
         } catch (SQLException ex) {
             Logger.getLogger(CommentaireImpl.class.getName()).log(Level.SEVERE, null, ex);
         }
         return entity;
    }

    @Override
    public List<Commentaire> selectAll() {
        con=DataSource.getInstance().getConnection();
         List<Commentaire> lc=new ArrayList();
        try {
            String requete = "SELECT * FROM commentaire";
            ste=con.createStatement();
            ResultSet s = ste.executeQuery(requete);
            while(s.next())
            {
                Commentaire c=new Commentaire();
                c.setIdCommentaire(s.getInt(1));
                c.setTexte(s.getString(2));
                c.setDate(s.getDate(3));
                c.setNbJaime(s.getInt(4));
                c.setNbJaimePas(s.getInt(5));
                c.setIdClient(s.getInt(6));
                c.setIdPlan(s.getInt(7));
                
                lc.add(c);
            }
         } catch (SQLException ex) {
             Logger.getLogger(CommentaireImpl.class.getName()).log(Level.SEVERE, null, ex);
         }
        return lc;
    }

    @Override
<<<<<<< HEAD
    public Commentaire GetByID(int id) {
        con=DataSource.getInstance().getConnection();
          String requete = "select * from commentaire where IdCommentaire ="+id;
             Commentaire c= new Commentaire();
        try {
            ste=con.createStatement();
            ResultSet s = ste.executeQuery(requete);
             while(s.next())
            {
              
                c.setIdCommentaire(s.getInt(1));
                c.setTexte(s.getString(2));
                c.setDate(s.getDate(3));
                c.setNbJaime(s.getInt(4));
                c.setNbJaimePas(s.getInt(5));
                c.setIdClient(s.getInt(6));
                c.setIdPlan(s.getInt(7));
                
                
            }
         } catch (SQLException ex) {
             Logger.getLogger(CommentaireImpl.class.getName()).log(Level.SEVERE, null, ex);
         }
        return c;
=======
    public Commentaire getByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
>>>>>>> 1a309ab73806eb09db0a6c2dfe4013b13b376979
    }

    @Override
    public void remove(int id) {
        con=DataSource.getInstance().getConnection();
         String requete = "DELETE * FROM commentaire WHERE IdCommentaire = "+id;
        try {
            ste=con.createStatement();
            int nbMaj = ste.executeUpdate(requete);
         } catch (SQLException ex) {
             Logger.getLogger(CommentaireImpl.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
<<<<<<< HEAD
    public List<Commentaire> selectAll(String sortfield, String sort) {
        con=DataSource.getInstance().getConnection();
        List <Commentaire> lc=new ArrayList();
        try {
            String requete="select * from commentaire order by"+sortfield+" "+sort;
            ste=con.createStatement();
            ResultSet s = ste.executeQuery(requete);
            while(s.next())
            {
                Commentaire c=new Commentaire();
                c.setIdCommentaire(s.getInt(1));
                c.setTexte(s.getString(2));
                c.setDate(s.getDate(3));
                c.setNbJaime(s.getInt(4));
                c.setNbJaimePas(s.getInt(5));
                c.setIdClient(s.getInt(6));
                c.setIdPlan(s.getInt(7));
                
                lc.add(c);
            }
         } catch (SQLException ex) {
             Logger.getLogger(CommentaireImpl.class.getName()).log(Level.SEVERE, null, ex);
         }
        return lc;
=======
    public List<Commentaire> selectAll(String sortField, EnumDatabaseSortOrder sortOrder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
>>>>>>> 1a309ab73806eb09db0a6c2dfe4013b13b376979
    }
    @Override
<<<<<<< HEAD
    public Commentaire FindOne(String ParamName, Object ParamValue) {
        con=DataSource.getInstance().getConnection();
         Commentaire c =new Commentaire();
              String requete="select * from commentaire where"+ParamName+"="+ParamValue;
        try {
            ste=con.createStatement();
             ResultSet s = ste.executeQuery(requete);
             while(s.next())
            {
                  c.setIdCommentaire(s.getInt(1));
                c.setTexte(s.getString(2));
                c.setDate(s.getDate(3));
                c.setNbJaime(s.getInt(4));
                c.setNbJaimePas(s.getInt(5));
                c.setIdClient(s.getInt(6));
                c.setIdPlan(s.getInt(7));
           
            }
         } catch (SQLException ex) {
             Logger.getLogger(CommentaireImpl.class.getName()).log(Level.SEVERE, null, ex);
         }
        return c;
    }

  
=======
    public Commentaire findOne(String paramName, Object paramValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int findCountBy(String paramName, Object paramValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
>>>>>>> 1a309ab73806eb09db0a6c2dfe4013b13b376979
    
}
