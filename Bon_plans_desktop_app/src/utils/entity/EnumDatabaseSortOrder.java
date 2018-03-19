/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils.entity;

/**
 *
 * @author touir
 */
public enum EnumDatabaseSortOrder {
    ASC("ASC"),
    DESC("DESC");
    
    private String value;
    
    EnumDatabaseSortOrder(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
}
