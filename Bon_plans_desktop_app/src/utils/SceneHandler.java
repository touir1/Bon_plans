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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author touir
 */
public class SceneHandler {
    
    private static final String LOGO_PATH = "./resources/images/Logo.png";
    
    private static Map<String,String> fxmlPaths;
    private static Map<String,String> sceneTitles;
    private static Stage primaryStage;
    private static Stack<Map<String,Object>> sceneStack;
    
    private SceneHandler(){}
    
    public static void initPrimaryStage(Stage stage){
        primaryStage = stage;
    }
    
    public static void openScene(SceneEnum scene){
        openScene(scene.getSceneName());
    }
    
    public static void openScene(SceneEnum scene, boolean addToStack){
        openScene(scene.getSceneName(), addToStack);
    }
    
    public static void initTemplate(SceneEnum scene){
        setTitle(scene);
        Integer notificationCount = (Integer) CurrentSession.getData("notificationCount");               
        Label notificationCountLabel = (Label) CurrentSession.getData("notificationCountLabel");
        
        notificationCountLabel.setText("\n"+notificationCount);
    }
    
    public static void setTitle(SceneEnum scene){
        Label screenTitleLabel = (Label)CurrentSession.getData("screenTitleLabel");
        screenTitleLabel.setText(scene.getSceneTitle());
    }
    
    public static void openScene(String screenName) {
        try {
            initSceneHandler();
            if(sceneStack== null || sceneStack.isEmpty() || !getCurrentScreenName().equals(screenName)){
                Parent root = FXMLLoader.load(SceneHandler.class.getResource(fxmlPaths.get(screenName)));

                Scene scene = new Scene(root);
                primaryStage.getIcons().add(new Image(LOGO_PATH));
                primaryStage.setScene(scene);
                primaryStage.show();
                primaryStage.setResizable(false);
                primaryStage.sizeToScene();
                primaryStage.setTitle(sceneTitles.get(screenName));

                addToStack(scene, screenName);
            }
        } catch (IOException ex) {
            LogHandler.handleException("SceneHandler", "openScene", ex);
        }
    }
    
    public static void openScene(String screenName, boolean addToStack) {
        try {
            initSceneHandler();
            if(sceneStack== null || sceneStack.isEmpty() || !getCurrentScreenName().equals(screenName)){
                Parent root = FXMLLoader.load(SceneHandler.class.getResource(fxmlPaths.get(screenName)));

                Scene scene = new Scene(root);
                primaryStage.getIcons().add(new Image(LOGO_PATH));
                primaryStage.setScene(scene);
                primaryStage.show();
                primaryStage.setResizable(false);
                primaryStage.sizeToScene();
                primaryStage.setTitle(sceneTitles.get(screenName));
                
                if(addToStack){
                    addToStack(scene, screenName);
                }
            }
        } catch (IOException ex) {
            LogHandler.handleException("SceneHandler", "openScene", ex);
        }
    }
    
    public static void openPreviousScene() {
        if(sceneStack!=null){
            if(!sceneStack.empty() && sceneStack.size()>1){
                //removing the opened one
                popFromStack();
                //getting the previous scene
                Map<String, Object> element = peekFromStack();
                if(element != null){
                    primaryStage.setScene((Scene)element.get("scene"));
                    primaryStage.show();
                    primaryStage.setResizable(false);
                    primaryStage.setTitle((String)element.get("screenName"));
                }
            }
        }
    }
    
    public static Scene getCurrentScene(){
        if(sceneStack!=null && !sceneStack.isEmpty()){
            Map<String, Object> sceneMap =  peekFromStack();
            return (Scene) sceneMap.get("scene");
        }
        return null;
    }
    
    public static void clearSceneHistory(){
        if(sceneStack != null && !sceneStack.isEmpty()){
            sceneStack.clear();
        }
    }
    
    private static String getCurrentScreenName(){
        if(sceneStack!=null && !sceneStack.isEmpty()){
            Map<String, Object> sceneMap =  peekFromStack();
            return (String) sceneMap.get("screenName");
        }
        return "";
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
    
    private static Map<String, Object> peekFromStack(){
        if(!sceneStack.empty()){
            return sceneStack.peek();
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
        for(SceneEnum sceneEnum : SceneEnum.values()){
            addSceneDescription(
                    sceneEnum.getSceneName(),
                    sceneEnum.getFxmlScenePath(),
                    sceneEnum.getSceneTitle()
            );
        }
    }
}
