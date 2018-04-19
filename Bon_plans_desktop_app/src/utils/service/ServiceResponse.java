/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author touir
 */
public class ServiceResponse {
    
    public static final boolean OK = true;
    public static final boolean KO = false;
    
    private boolean status;
    private List<Exception> exceptions;

    public ServiceResponse() {
        this.status = true;
        this.exceptions = new ArrayList<>();
    }
    
    public void initResponse(){
        this.status = true;
        this.exceptions = new ArrayList<>();
    }
    
    public List<Exception> getExceptions(){
        return exceptions;
    }
    
    public void addException(Exception exception){
        if(exceptions == null){
            exceptions = new ArrayList<>();
        }
        exceptions.add(exception);
    }
    
    public boolean isOk(){
        return status;
    }
    
    public void setStatus(boolean status){
        this.status = status;
    }
}
