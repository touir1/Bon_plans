/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author touir
 */
public class FileHandler {
    
    private FileHandler(){}
    
    public static String readFile(String path, Charset encoding)
    {
        try{
            byte[] encoded = Files.readAllBytes(Paths.get(path));
            return new String(encoded, encoding);
        }
        catch(Exception e){
            LogHandler.handleException("FileHandler", "readFile", e);
            return "";
        }
    }
}
