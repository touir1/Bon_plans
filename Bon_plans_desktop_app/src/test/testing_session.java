/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.File;
import utils.CurrentSession;
import utils.FileUploadHandler;

/**
 *
 * @author touir
 */
public class testing_session {
    
    public static void main(String[] args){
        File file = new File(CurrentSession.getProjectPath()+"/src/resources/images/Logo.png");
        System.out.println(file.exists());
        System.out.println(CurrentSession.getProjectPath());
        String filePath = FileUploadHandler.uploadFile(file);
        System.out.println("filePath: "+filePath);
    }
}
