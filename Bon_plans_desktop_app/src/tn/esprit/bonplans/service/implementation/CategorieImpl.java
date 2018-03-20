/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.service.implementation;

import tn.esprit.bonplans.entity.Categorie;
import tn.esprit.bonplans.service.ICategorie;
import utils.service.GenericServiceImplementation;

/**
 *
 * @author SadfiAmine
 */
public class CategorieImpl extends GenericServiceImplementation<Categorie> implements ICategorie{

    public CategorieImpl(){
        super(Categorie.class);
    }
  
}
