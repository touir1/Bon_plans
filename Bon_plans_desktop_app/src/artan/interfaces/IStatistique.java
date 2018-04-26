/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package artan.interfaces;

import artan.entities.Plan;
import artan.entities.Statistique;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Mohamed Ali
 */
public interface IStatistique {
    
    public Statistique meilleurVenteParCategorie();
    public Statistique meilleurVenteParPersonne();
    
    public ArrayList<Statistique> meilleurDixVentes();
    public ArrayList<Statistique> pireDixVentes();
    
    public ArrayList<Statistique> meilleurDixVentesParCategorie();
    public ArrayList<Statistique> pireDixVentesParCategorie();
    
    public ArrayList<Statistique> meilleurDixVentesParPersonne();
    public ArrayList<Statistique> pireDixVentesParPersonne();
    
    public Plan planlePlusCommenter();
    public Plan planLeMoinsCommenter();
    
    public Plan planlePlusAimer();
    public Plan planlePlusDetester();
    
    public int nombreDesJaimes(Plan plan);
    public int nombresDesAbominer(Plan plan);
    
    public int nombreDesPlansPourJour(LocalDate date);
    public int nombreDesPlansParMois(int mois);
    
    public double moyenneDesPlansParJourParMois(int mois);
    
}
