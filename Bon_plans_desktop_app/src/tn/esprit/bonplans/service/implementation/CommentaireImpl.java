/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.service.implementation;

import tn.esprit.bonplans.entity.Commentaire;
import tn.esprit.bonplans.service.ICommentaire;
import utils.service.GenericServiceImplementation;

/**
 *
 * @author SadfiAmine
 */
public class CommentaireImpl extends GenericServiceImplementation<Commentaire> implements ICommentaire{
    
    public CommentaireImpl(){
        super(Commentaire.class);
    }
}
