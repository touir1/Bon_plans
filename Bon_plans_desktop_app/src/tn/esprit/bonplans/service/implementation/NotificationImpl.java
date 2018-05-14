/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.service.implementation;

import java.util.List;
import java.util.Map;
import tn.esprit.bonplans.entity.Notification;
import tn.esprit.bonplans.service.INotification;
import utils.Converter;
import utils.database.DatabaseHandler;
import utils.service.GenericServiceImplementation;

/**
 *
 * @author touir
 */
public class NotificationImpl extends GenericServiceImplementation<Notification> implements INotification {

    public NotificationImpl() {
        super(Notification.class);
    }
    
    @Override
    public List<Notification> getListNotifications(int idUtilisateur){
        String sql = "select * from notification where idPlan = 0"
                + " UNION ALL"
                + " select notification.* from notification,plan where"
                + " plan.idAnnonceur = ?"
                + " and plan.idPlan = notification.idPlan";
        List<Map<String, Object>> listMap = DatabaseHandler.select(sql, new Object[]{idUtilisateur});
        return Converter.convertMapListToEntityList(listMap, Notification.class);
        
    }
    
    @Override
    public List<Notification> getListNotificationsGlobal(){
        return findOne("idPlan", 0);
    }
}
