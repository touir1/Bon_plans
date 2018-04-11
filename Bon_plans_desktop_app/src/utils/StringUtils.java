/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author touir
 */
public class StringUtils {
    private StringUtils(){}
    
    public static boolean isEmpty(String string){
        return (string == null || "".equals(string.trim()));
    }
}
