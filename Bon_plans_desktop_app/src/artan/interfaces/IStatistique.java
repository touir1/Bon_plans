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
    public ArrayList<Statistique> meilleurVenteParCategorie(int idCategorie);// fait
    public ArrayList<Statistique> pireVenteParCategorie(int idCategorie);// fait
    public ArrayList<Statistique> meilleurVenteParPersonne(int idPersonne);// fait
    public ArrayList<Statistique> pireVenteParPersonne(int idPersonne);// fait
    
    public ArrayList<Statistique> meilleurDixVentes();// fait
    public ArrayList<Statistique> pireDixVentes();// fait
    
    public HashMap<Integer, ArrayList<Statistique>> meilleurDixVentesParCategorie();// fait
    public HashMap<Integer, ArrayList<Statistique>> pireDixVentesParCategorie();// fait
    
    public HashMap<Integer, ArrayList<Statistique>> meilleurDixVentesParPersonne();// fait
    public HashMap<Integer, ArrayList<Statistique>> pireDixVentesParPersonne();// fait
    
    public Plan planlePlusCommenter();// fait
    public Plan planLeMoinsCommenter();// fait
    
    public Plan planLePlusAimer();// fait
    public Plan planLePlusDetester();// fait
    
    public int nombreDesJaimes(Plan plan);// fait
    public int nombresDesAbominer(Plan plan);// fait
    
    public int nombreDesPlansPourJour(LocalDate date);// fait
    public int nombreDesPlansParMois(int mois);// fait
    
    public double moyenneDesPlansParJour(int mois);// fait
    
}
