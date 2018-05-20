/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.service;

import java.util.List;
import utils.service.GenericServiceInterface;
import tn.esprit.bonplans.entity.Commentaire;

/**
 *
 * @author SadfiAmine
 */
public interface ICommentaire extends GenericServiceInterface<Commentaire>{
    public interface Icommentaire {
       public List<Commentaire> getAll(int i);
        public Commentaire findById(Integer idCommentaire);
          public void delete(Integer idCommentaire);
           public void Update(Commentaire t);
           public void add(Commentaire t);
}
    
    
}
