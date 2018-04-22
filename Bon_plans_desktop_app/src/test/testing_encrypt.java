/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Encrypt;

/**
 *
 * @author touir
 */
public class testing_encrypt {
    public static void main(String[] args) {
        try {
            System.out.println(Encrypt.sha1("test"));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(testing_encrypt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
