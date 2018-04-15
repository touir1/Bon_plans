/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Map;
import tn.esprit.bonplans.entity.Utilisateur;

/**
 *
 * @author touir
 */
public class CurrentSession {
    private static Map<String, Object> data;
    private static Utilisateur utilisateur;
    
    private CurrentSession(){};
    
    private static boolean initCurrentSession(){
        
        return true;
    }
}
