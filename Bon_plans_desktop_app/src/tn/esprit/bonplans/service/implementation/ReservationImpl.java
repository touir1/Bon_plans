/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.service.implementation;

import tn.esprit.bonplans.entity.Reservation;
import tn.esprit.bonplans.service.IReservation;
import utils.service.GenericServiceImplementation;

/**
 *
 * @author SadfiAmine
 */
public class ReservationImpl extends GenericServiceImplementation<Reservation> implements IReservation{

    public ReservationImpl(){
        super(Reservation.class);
    }
    
}
