/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.service.implementation;

import java.util.List;
import tn.esprit.bonplans.entity.Plan;
import tn.esprit.bonplans.service.Iplan;
import utils.entity.EnumDatabaseSortOrder;

/**
 *
 * @author SadfiAmine
 */
public class PlanImpl implements Iplan{

    @Override
    public Plan save(Plan entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Plan update(Plan entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Plan> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Plan getByID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Plan> selectAll(String sortField, EnumDatabaseSortOrder sortOrder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Plan findOne(String paramName, Object paramValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

<<<<<<< HEAD:Bon_plans_desktop_app/src/tn/esprit/bonplans/service/implementation/planImpl.java
   
=======
    @Override
    public int findCountBy(String paramName, Object paramValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
>>>>>>> 1a309ab73806eb09db0a6c2dfe4013b13b376979:Bon_plans_desktop_app/src/tn/esprit/bonplans/service/implementation/PlanImpl.java
    
}
