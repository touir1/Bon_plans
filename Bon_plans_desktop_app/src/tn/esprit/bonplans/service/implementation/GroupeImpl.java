/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.service.implementation;

import tn.esprit.bonplans.entity.Groupe;
import tn.esprit.bonplans.service.IGroupe;
import utils.service.GenericServiceImplementation;

/**
 *
 * @author KC
 */
public class GroupeImpl extends GenericServiceImplementation<Groupe> implements IGroupe {

    public GroupeImpl() {
        super(Groupe.class);
    }
    
}
