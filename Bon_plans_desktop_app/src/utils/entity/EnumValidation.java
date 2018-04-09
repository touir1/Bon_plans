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
public enum EnumValidation implements GenericEnumInterface<String,String> {
    validee("VALIDEE","1"),
    EnAttentedeValidation("ATTENTE","0"),
    refusée("REFUSEE","-1");
    
    private final String key;
    private final String value;
    
    EnumValidation(String key, String value){
        this.key = key;
        this.value = value;
    }
    
    @Override
    public String getValue(){
        return this.value;
    }
    
    @Override
    public String getKey(){
        return this.key;
    }
    
    @Override
    public String toString(){
        return this.value;
    }
}
