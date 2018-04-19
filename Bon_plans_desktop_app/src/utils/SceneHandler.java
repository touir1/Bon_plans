/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
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
    private static Stack<Map<String,Object>> sceneStack;
    
    private SceneHandler(){}
    
    public static void initPrimaryStage(Stage stage){
        primaryStage = stage;
    }
    
    public static void openScene(String screenName) throws IOException {
        initSceneHandler();
        
        Parent root = FXMLLoader.load(SceneHandler.class.getResource(fxmlPaths.get(screenName)));
        
        Scene scene = new Scene(root);
        primaryStage.getIcons().add(new Image("./resources/images/Logo.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setTitle(sceneTitles.get(screenName));
        
        addToStack(scene, screenName);
    }
    
    public static void openPreviousScene() {
        if(!sceneStack.empty()){
            Map<String, Object> element = popFromStack();
            if(element != null){
                primaryStage.setScene((Scene)element.get("scene"));
                primaryStage.show();
                primaryStage.setTitle((String)element.get("screenName"));
            }
        }
    }
    
    private static void addToStack(Scene scene, String screenName){
        Map<String, Object> element = new HashMap<>();
        element.put("scene",scene);
        element.put("screenName", screenName);
        sceneStack.push(element);
    }
    
    private static Map<String, Object> popFromStack(){
        if(!sceneStack.empty()){
            return sceneStack.pop();
        }
        else{
            return null;
        }
    }
    
    private static boolean initSceneHandler(){
        if(fxmlPaths == null){
            fxmlPaths = new HashMap<>();
            sceneTitles = new HashMap<>();
            sceneStack = new Stack<>();
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
