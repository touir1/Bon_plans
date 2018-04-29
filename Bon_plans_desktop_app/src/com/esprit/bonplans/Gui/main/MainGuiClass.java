/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.bonplans.Gui.main;

import javafx.application.Application;
import javafx.stage.Stage;
import utils.SceneEnum;
import utils.SceneHandler;

/**
 *
 * @author touir
 */
public class MainGuiClass extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneHandler.initPrimaryStage(primaryStage);
        SceneHandler.openScene(SceneEnum.SE_CONNECTER, false);
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
}
