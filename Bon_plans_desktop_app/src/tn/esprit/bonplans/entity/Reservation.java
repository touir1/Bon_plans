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
    private int IdReservation;
    private Date Date;
    //
    private int IdClient;
    private int IdPlan;

    public Reservation(Date Date) {
        this.Date = Date;
    }

    public Reservation(int IdReservation, Date Date) {
        this.IdReservation = IdReservation;
        this.Date = Date;
    }

    public Reservation() {
    }

    public int getIdReservation() {
        return IdReservation;
    }

    public void setIdReservation(int IdReservation) {
        this.IdReservation = IdReservation;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }

    public int getIdClient() {
        return IdClient;
    }

    public void setIdClient(int IdClient) {
        this.IdClient = IdClient;
    }

    public int getIdPlan() {
        return IdPlan;
    }

    public void setIdPlan(int IdPlan) {
        this.IdPlan = IdPlan;
    }
    
    
}
