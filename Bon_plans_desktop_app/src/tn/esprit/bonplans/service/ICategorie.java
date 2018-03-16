/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.service;

import java.util.List;
import tn.esprit.bonplans.entity.Categorie;

/**
 *
 * @author SadfiAmine
 */
public interface ICategorie {
     public Categorie  save(Categorie  Categorie );
	public Categorie  update(Categorie  Categorie );
	public List<Categorie >selectAll();
	public Categorie  GetByID(int id);
	public void remove(int id);
	public List<Categorie >selectAll(String sortfield,String sort);
	public Categorie  FindOne(String ParamName,Object ParamValue);
	public int findCountBy(String ParamName,Object ParamValue);
}
