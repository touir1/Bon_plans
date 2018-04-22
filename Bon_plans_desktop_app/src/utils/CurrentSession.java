/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import tn.esprit.bonplans.entity.Utilisateur;

/**
 *
 * @author touir
 */
public class CurrentSession {
    private static Map<String, Object> dataMap;
    private static Utilisateur utilisateur;
    private static String projectPath;
    
    private CurrentSession(){};
    
    private static boolean initCurrentSession(){
        if(dataMap == null){
            dataMap = new HashMap<String, Object>();
            File file = new File(".");
            projectPath = file.getAbsolutePath();
        }
        return true;
    }
    
    public static void setUtilisateur(Utilisateur user){
        if(initCurrentSession()){
            utilisateur = user;
        }
    }
    
    public static String getProjectPath(){
        if(initCurrentSession()){
            return projectPath;
        }
        return ".";
    }
    
    public static <E> void addData(String key, E data){
        if(initCurrentSession()){
            dataMap.put(key, data);
        }
        
    }
    
    public static <E> Object getData(String key){
        if(initCurrentSession()){
            return dataMap.get(key);
        }
        
        return null;
    }
}
