/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.entity;

/**
 *
 * @author SadfiAmine
 */
public enum EnumValidation implements GenericEnumInterface<String,Integer> {
    validee("VALIDEE",1),
    EnAttentedeValidation("ATTENTE",0),
    refusée("REFUSEE",-1),
    modifiee("MODIFIEE",2);
    
    private final String key;
    private final int value;
    
    EnumValidation(String key, int value){
        this.key = key;
        this.value = value;
    }
    
    @Override
    public Integer getValue(){
        return this.value;
    }
    
    @Override
    public String getKey(){
        return this.key;
    }
    
    @Override
    public String toString(){
        return Integer.toString(this.value);
    }
}
