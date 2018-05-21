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
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Mohamed Ali
 */
public interface IStatistique {
    
    public ArrayList<Statistique> meilleursVentesDuMois(int mois);// fait
    public ArrayList<Statistique> meilleursVentesDuJours(LocalDate jour);// fait
    public ArrayList<Statistique> meilleurVenteParCategorie(int idCategorie);
    public ArrayList<Statistique> pireVenteParCategorie(int idCategorie);
    public ArrayList<Statistique> meilleurVenteParPersonne(int idPersonne);
    public ArrayList<Statistique> pireVenteParPersonne(int idPersonne);
    
    public ArrayList<Statistique> meilleurDixVentes();
    public ArrayList<Statistique> pireDixVentes();//fait
    
    public HashMap<Integer, ArrayList<Statistique>> meilleurDixVentesParCategorie();
    public HashMap<Integer, ArrayList<Statistique>> pireDixVentesParCategorie();
    
    public HashMap<Integer, ArrayList<Statistique>> meilleurDixVentesParPersonne();
    public HashMap<Integer, ArrayList<Statistique>> pireDixVentesParPersonne();
    
    public Plan planlePlusCommenter();
    public Plan planLeMoinsCommenter();
    
    public Plan planLePlusAimer();
    public Plan planLePlusDetester();
    
    public int nombreDesJaimes(Plan plan);
    public int nombresDesAbominer(Plan plan);
    
    public int nombreDesPlansPourJour(LocalDate date);
    public int nombreDesPlansParMois(int mois);
    
    public double moyenneDesPlansParJour(int mois);
    
}
