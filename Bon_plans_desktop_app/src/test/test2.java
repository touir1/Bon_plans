/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;
import utils.FileUploadHandler;

/**
 *
 * @author touir
 */
public class test2 {
    public static void main(String[] args) {
        File file = new File("E:\\To Copy\\Dossier Personnel\\Images\\Others\\Sans titre.png");
        FileUploadHandler.uploadFile(file);
        
   } 
}
