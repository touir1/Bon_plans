/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Random;

/**
 *
 * @author KC
 */
public class Other {
    private static final int MAX = 99999;
    private static final int MIN = 10000;
    
    public static int generateActivationCode() {
        Random rand = new Random();
        return rand.nextInt((MAX - MIN) + 1) + MIN;
    }
    
}
