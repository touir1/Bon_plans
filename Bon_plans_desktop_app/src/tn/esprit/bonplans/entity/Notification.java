/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.entity;

/**
 *
 * @author touir
 */
public class Notification {
    private int idNotification;
    private int idPlan;
    private String message;
    private boolean seen;

    public Notification() {
    }

    public Notification(int idNotification, int idPlan, String message, boolean seen) {
        this.idNotification = idNotification;
        this.idPlan = idPlan;
        this.message = message;
        this.seen = seen;
    }

    public Notification(int idPlan, String message, boolean seen) {
        this.idPlan = idPlan;
        this.message = message;
        this.seen = seen;
    }

    public int getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(int idNotification) {
        this.idNotification = idNotification;
    }

    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.idNotification;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Notification other = (Notification) obj;
        if (this.idNotification != other.idNotification) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Notification{" + "idNotification=" + idNotification + ", idPlan=" + idPlan + ", message=" + message + ", seen=" + seen + '}';
    }
    
    
}
