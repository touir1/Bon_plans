/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.entity;

/**
 *
 * @author KC
 */
public enum EnumGroupe implements GenericEnumInterface<String,Integer> {
    
    Client("Client", 1),
    Administrateur("Administrateur", 2),
    SuperAdmin("Super Administrateur", 3);
    
    private final String key;
    private final int value;
    
    EnumGroupe(String key, int value) {
        this.key = key;
        this.value = value;
    }
    
    @Override
    public String getKey() {
       return key;
    }

    @Override
    public Integer getValue() {
        return value;
    }

}
