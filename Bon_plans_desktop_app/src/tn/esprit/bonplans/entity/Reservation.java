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
    private String UrlBonRes; 
    //
    private int idClient;
    private int idPlan;

    public Reservation(Date date) {
        this.date = date;
    }

    public Reservation(int idReservation, Date date,String UrlBonRes) {
        this.idReservation = idReservation;
        this.date = date;
        this.UrlBonRes=UrlBonRes;
    }

    public Reservation(Date date, String UrlBonRes, int idClient, int idPlan) {
        this.date = date;
        this.UrlBonRes = UrlBonRes;
        this.idClient = idClient;
        this.idPlan = idPlan;
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

    public String getUrlBonRes() {
        return UrlBonRes;
    }

    public void setUrlBonRes(String UrlBonRes) {
        this.UrlBonRes = UrlBonRes;
    }
    

    @Override
    public String toString() {
        return "Reservation{" + "idReservation=" + idReservation + ", date=" + date + ", idClient=" + idClient + ", idPlan=" + idPlan + '}';
    }
    
    
    
}
