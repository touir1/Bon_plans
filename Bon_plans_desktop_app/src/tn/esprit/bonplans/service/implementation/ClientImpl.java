/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.service.implementation;

import tn.esprit.bonplans.entity.Client;
import tn.esprit.bonplans.service.IClient;
import utils.service.GenericServiceImplementation;

/**
 *
 * @author SadfiAmine
 */
public class ClientImpl extends GenericServiceImplementation<Client> implements IClient{
    
    public ClientImpl(){
        super(Client.class);
    }
    
}
