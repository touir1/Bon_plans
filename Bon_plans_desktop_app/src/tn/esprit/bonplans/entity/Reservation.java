/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.entity;

import java.util.Date;

/**
 *
 * @author SadfiAmine
 */
public class Reservation {
    private int idReservation;
    private Date date;
    //
    private int idClient;
    private int idPlan;

    public Reservation(Date date) {
        this.date = date;
    }

    public Reservation(int idReservation, Date date) {
        this.idReservation = idReservation;
        this.date = date;
    }

    public Reservation() {
    }

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    @Override
    public String toString() {
        return "Reservation{" + "idReservation=" + idReservation + ", date=" + date + ", idClient=" + idClient + ", idPlan=" + idPlan + '}';
    }
    
    
    
}
