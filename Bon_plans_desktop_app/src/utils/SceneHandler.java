/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author touir
 */
public class SceneHandler {
    private static Map<String,String> fxmlPaths;
    private static Map<String,String> sceneTitles;
    private static Stage primaryStage;
    
    private SceneHandler(){}
    
    public static void initPrimaryStage(Stage stage){
        primaryStage = stage;
    }
    
    public static void openScene(String screenName) throws IOException {
        initSceneHandler();
        
        Parent root = FXMLLoader.load(SceneHandler.class.getResource(fxmlPaths.get(screenName)));
        
        Scene Scene = new Scene(root);
        primaryStage.getIcons().add(new Image("./resources/images/Logo.png"));
        primaryStage.setScene(Scene);
        primaryStage.show();
        primaryStage.setTitle(sceneTitles.get(screenName));
    }
    
    private static boolean initSceneHandler(){
        if(fxmlPaths == null){
            fxmlPaths = new HashMap<>();
            sceneTitles = new HashMap<>();
            mapScenes();
        }
        return true;
    }
    
    private static void addSceneDescription(String sceneName, String fxmlPath, String sceneTitle){
        fxmlPaths.put(sceneName,fxmlPath);
        sceneTitles.put(sceneName, sceneTitle);
    }
    
    private static void mapScenes(){
        addSceneDescription(
                "ValiderPlan", 
                "../tn/esprit/bonplans/Gui/GererAnnonce/administration/validerPlan/ValiderPlan.fxml", 
                "Validation plans"
        );
        addSceneDescription(
                "AjouterCategorie", 
                "../tn/esprit/bonplans/Gui/GererCategorie/Ajouter/AjouterCategorie.fxml", 
                "Ajouter Catégorie"
        );
    }
}
