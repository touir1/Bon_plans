/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.template.Admin;

import com.gluonhq.charm.glisten.control.Avatar;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.PopOver;
import tn.esprit.bonplans.service.IPlan;
import tn.esprit.bonplans.service.IUtilisateur;
import tn.esprit.bonplans.service.implementation.PlanImpl;
import tn.esprit.bonplans.service.implementation.UtilisateurImpl;
import utils.CurrentSession;
import utils.FileHandler;
import utils.SceneEnum;
import utils.SceneHandler;

/**
 * FXML Controller class
 *
 * @author touir
 */
public class BonPlanTemplateController extends Application implements Initializable {

    private final String FXML_FILE_PATH = "BonPlanTemplate.fxml";
    private final String LOGO_PATH = "./resources/images/Logo.png";
    private final String TITLE = "bon plans";
    private final String NOTIFICATION_HTML_TEMPLATE_PATH = "./src/tn/esprit/bonplans/Gui/template/notification/notificationPopUpTemplate.html";
    
    private static PopOver notificationPopOver;
    private Thread mainThread;
    
    private static int 
            lastNonValidatedPlansCount,
            lastModifiedPlansCount;
    
    private IPlan planService;
    private IUtilisateur utilisateurService;
    
    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane Menu;
    @FXML
    private Label screenTitle;
    @FXML
    private Avatar userImage;
    @FXML
    private Label username;
    @FXML
    private Hyperlink btnLogout;
    @FXML
    private Label btnNotification;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        planService = new PlanImpl();
        utilisateurService = new UtilisateurImpl();
        mainThread = Thread.currentThread();
        
        CurrentSession.addData("screenTitleLabel", screenTitle);
        CurrentSession.addData("notificationCountLabel", btnNotification);
        Object verif = CurrentSession.getData("lastNonValidatedPlansCount");
        if(verif != null){
            lastNonValidatedPlansCount = (int) verif;
        }
        else{
            CurrentSession.addData("lastNonValidatedPlansCount", 0);
        }
        verif = CurrentSession.getData("lastModifiedPlansCount");
        if(verif != null){
            lastModifiedPlansCount = (int) verif;
        }
        else{
            CurrentSession.addData("lastModifiedPlansCount", 0);
        }
        initNotifications();
        initForceExit();
    }
    
    public void initForceExit(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask(){

            @Override
            public void run() {
                if(!mainThread.isAlive()){
                    System.exit(0);
                }
            }
            
        },200,200);
    }
    
    private void initNotifications(){
        Timer timerVerif = (Timer) CurrentSession.getData("notificationTimer");
        if(timerVerif==null){
            Timer timer = new Timer();
            timer.schedule(new TimerTask(){

                @Override
                public void run() {

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            int countModified = planService.getCountOfModifiedPlans();
                            int countToValidate = planService.getCountOfNonValidatedPlans();
                            int notificationCount = countModified+countToValidate;
                            CurrentSession.addData("notificationCount", notificationCount);
                            
                            Label notificationCountLabel = (Label) CurrentSession.getData("notificationCountLabel");
                            notificationCountLabel.setText("\n"+notificationCount);
                            
                            if(countToValidate != lastNonValidatedPlansCount){
                                lastNonValidatedPlansCount = countToValidate;
                                CurrentSession.addData("lastNonValidatedPlansCount", lastNonValidatedPlansCount);
                                Notifications notification;
                                notification = Notifications.create()
                                        .title("notification")
                                        .text("Il y a "+lastNonValidatedPlansCount+" nouveaux plans à valider/refuser.");
                                notification.showInformation();
                            }
                            if(countModified!=lastModifiedPlansCount){
                                lastModifiedPlansCount = countModified;
                                CurrentSession.addData("lastModifiedPlansCount", lastModifiedPlansCount);
                                Notifications notification;
                                notification = Notifications.create()
                                        .title("notification")
                                        .text("Il y a "+lastModifiedPlansCount+" plans modifiés à valider/refuser.");
                                notification.showInformation();
                            }
                        }
                    });
                    
                    System.out.println("synchronize notifications");
                }


            }, 1000, 10000);

            CurrentSession.addData("notificationTimer", timer);
        }
    }
    
    //data class
    public class JavaExecutor {
        public void execute(int index){
            notificationPopOver.hide();
            if(index == 0){
                SceneHandler.openScene(SceneEnum.VALIDER_NEW_PLAN);
            }
            else{
                SceneHandler.openScene(SceneEnum.VALIDER_MODIFIED_PLAN);
            }
        }

    }
    
    private void initNotificationContent(){
        notificationPopOver = new PopOver();
        notificationPopOver.setCloseButtonEnabled(true);
        notificationPopOver.setDetached(true);
        notificationPopOver.setTitle("Notifications");
        
        WebView notificationContent = new WebView();
        WebEngine webEngine = notificationContent.getEngine();
        String notificationHTMLContent = FileHandler.readFile(NOTIFICATION_HTML_TEMPLATE_PATH, StandardCharsets.UTF_8);
        webEngine.loadContent(notificationHTMLContent);
        notificationContent.setMaxSize(450, 200);
        
        // Get window object of page.
        JSObject jsobj = (JSObject) webEngine.executeScript("window");
        
        //data init
        List<String> list = new ArrayList<>();
        
        list.add("Il y a "+lastNonValidatedPlansCount+" nouveaux plans à valider/refuser.");
        list.add("Il y a "+lastModifiedPlansCount+" plans modifiés à valider/refuser.");
        //set data object in window object
        jsobj.setMember("data", list.toArray());
        jsobj.setMember("JavaExecutor",new JavaExecutor());

        notificationPopOver.setContentNode(notificationContent);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(FXML_FILE_PATH));
        
        Scene Scene = new Scene(root);
        primaryStage.setTitle(TITLE);
        primaryStage.getIcons().add(new Image(LOGO_PATH));
        primaryStage.setScene(Scene);
        primaryStage.show();
        SceneHandler.initPrimaryStage(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    private void goPrevious(MouseEvent event) {
        SceneHandler.openPreviousScene();
    }

    @FXML
    private void openValidationScreensMenu(MouseEvent event) {
        SceneHandler.openScene(SceneEnum.VALIDER_PLAN_MENU);
    }

    @FXML
    private void openCategorieListScene(MouseEvent event) {
        System.out.println("clicked");
        SceneHandler.openScene(SceneEnum.LISTE_CATEGORIE);
    }

    @FXML
    private void logout(MouseEvent event) {
        SceneHandler.clearSceneHistory();
        CurrentSession.clearData();
        SceneHandler.openScene(SceneEnum.SE_CONNECTER, false);
    }

    @FXML
    private void showNotifications(MouseEvent event) {
        //TODO
        System.out.println("show notification popup");
        if(notificationPopOver == null || !notificationPopOver.isShowing()){
            initNotificationContent();
            
            notificationPopOver.show(btnNotification);
        }
    }
    
    @Override
    public void stop(){
        Timer timer = (Timer) CurrentSession.getData("notificationTimer");
        if(timer!= null){
            timer.cancel();
        }
        System.out.println("Stage is closing");
        // Save file
    }
}
