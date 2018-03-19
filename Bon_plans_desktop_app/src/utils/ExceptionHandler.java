/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author touir
 */
public class ExceptionHandler {
    
    public static void handleException(Exception e){
        System.out.println(e.getMessage());
    }
    
    public static void handleException(String className, String methodName, Exception e){
        System.out.println("Error while executing "+methodName+" in class "+className+": "+e.getMessage());
    }
}
