/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artan.interfaces;

import artan.entities.Plan;
import java.util.ArrayList;

/**
 *
 * @author Mohamed Ali
 */
public interface IPlan {
    
    public void ajouterPlan(Plan plan);
    public void supprimerPlan(int id);
    public void modifierPlan(Plan plan);
    public Plan rechercheParID(int id);
    public ArrayList<Plan> listePlan();
    public ArrayList<Plan> rechercheParUtilisateur(int idUtilisateur);
    public ArrayList<Plan> rechercheParCategorie(int idCategorie);
    public void changerStatut( int id, int statut);
}
