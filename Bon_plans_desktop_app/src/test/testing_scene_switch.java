/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javafx.application.Application;
import javafx.stage.Stage;
import utils.SceneHandler;

/**
 *
 * @author touir
 */
public class testing_scene_switch extends Application{
    
    public static void main(String[] args){
        
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneHandler.initPrimaryStage(primaryStage);
        SceneHandler.openScene("ValiderPlan");
    }
}
