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
public class LogHandler {
    
    public static void handleException(Exception e){
        System.out.println(e.getMessage());
    }
    
    public static void handleException(String className, String methodName, Exception e){
        System.out.println("Error while executing "+methodName+" in class "+className+": "+e.getMessage());
    }
    
    public static void log(String message){
        System.out.println(message);
    }
    
    public static void log(String className, String methodName, String message){
        System.out.println("Executing "+methodName+" in class "+className+" : "+message);
    }
}
