/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.service;

import java.util.List;
import tn.esprit.bonplans.entity.Notification;
import utils.service.GenericServiceInterface;

/**
 *
 * @author touir
 */
public interface INotification extends GenericServiceInterface<Notification> {
    public List<Notification> getListNotifications(int idUtilisateur);
    public List<Notification> getListNotificationsGlobal();
    public void sendGlobalNotification(String message);
}
