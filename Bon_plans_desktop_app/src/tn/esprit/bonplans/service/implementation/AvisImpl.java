/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.service.implementation;

import tn.esprit.bonplans.entity.Avis;
import tn.esprit.bonplans.service.IAvis;
import utils.service.GenericServiceImplementation;

/**
 *
 * @author touir
 */
public class AvisImpl extends GenericServiceImplementation<Avis> implements IAvis{
    
    public AvisImpl(){
        super(Avis.class);
    }
}
