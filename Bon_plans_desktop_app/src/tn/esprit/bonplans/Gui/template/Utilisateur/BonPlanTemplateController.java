/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.template.Utilisateur;

import com.gluonhq.charm.glisten.control.Avatar;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.PopOver;
import tn.esprit.bonplans.entity.Notification;
import tn.esprit.bonplans.entity.Plan;
import tn.esprit.bonplans.entity.Utilisateur;
import tn.esprit.bonplans.service.INotification;
import tn.esprit.bonplans.service.IPlan;
import tn.esprit.bonplans.service.IUtilisateur;
import tn.esprit.bonplans.service.implementation.NotificationImpl;
import tn.esprit.bonplans.service.implementation.PlanImpl;
import tn.esprit.bonplans.service.implementation.UtilisateurImpl;
import utils.CurrentSession;
import utils.FileHandler;
import utils.SceneEnum;
import utils.SceneHandler;
import utils.StringUtils;
import utils.entity.EnumGroupe;

/**
 * FXML Controller class
 *
 * @author touir
 */
public class BonPlanTemplateController extends Application implements Initializable {

    private final String FXML_FILE_PATH = "BonPlanTemplate.fxml";
    private final String LOGO_PATH = "./resources/images/Logo.png";
    private final String TITLE = "bon plans";
    private final String NOTIFICATION_HTML_TEMPLATE_PATH = "./src/tn/esprit/bonplans/Gui/template/notification/notificationPopUpUtilisateurTemplate.html";
    
    private static PopOver notificationPopOver;
    private Thread mainThread;
    
    private INotification notificationService;
    private IUtilisateur utilisateurService;
    private IPlan planService;
    
    private static int lastNotificationCount;
    private static List<Notification> notifications;
    
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
    private Hyperlink btnNotifications;
    @FXML
    private Label btnNotification;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CurrentSession.addData("screenTitleLabel", screenTitle);
        CurrentSession.addData("notificationCountLabel", btnNotification);
        
        notificationService = new NotificationImpl();
        utilisateurService = new UtilisateurImpl();
        planService = new PlanImpl();
        mainThread = Thread.currentThread();
        
        Object verif = CurrentSession.getData("lastNotificationCount");
        if(verif != null){
            lastNotificationCount = (int) verif;
        }
        else{
            CurrentSession.addData("lastNotificationCount", 0);
        }
        
        //set dummy user
        if(CurrentSession.getUtilisateur()==null){
            CurrentSession.setUtilisateur(utilisateurService.findOne("idGroupe", EnumGroupe.Client.getValue()).get(0));
        }
        
        Utilisateur user = CurrentSession.getUtilisateur();
        username.setText(user.getPrenom());
        if(!StringUtils.isEmpty(user.getUrlphoto())){
            Image image = new Image(user.getUrlphoto(),true);
            userImage.setImage(image);
        }
        
        initNotifications();
        initForceExit();
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
    private void openCategorieListScene(MouseEvent event) {
        System.out.println("clicked");
        SceneHandler.openScene(SceneEnum.LISTE_CATEGORIE);
    }

    @FXML
    private void logout(MouseEvent event) {
        SceneHandler.clearSceneHistory();
        SceneHandler.openScene(SceneEnum.SE_CONNECTER, false);
    }

    @FXML
    private void openNotifications(ActionEvent event) {
        SceneHandler.openScene(SceneEnum.CONSULTER_LISTE_NOTIFICATION_CLIENT);
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
      @FXML
    void OuvrireMesReservation(ActionEvent event) {
    SceneHandler.openScene(SceneEnum.HistoriqueRESERVATIONClient);
    }
@FXML
    void OuvrireNotif(ActionEvent event) {
    SceneHandler.openScene(SceneEnum.CONSULTER_LISTE_NOTIFICATION_CLIENT);

    }
        @FXML
    void OuvrirConsulterPlan(ActionEvent event) {

    }

    @FXML
    void OuvrireAjouterPlan(ActionEvent event) {

    }
    @FXML
    void OuvrireOffre(ActionEvent event) {
    SceneHandler.openScene(SceneEnum.HistoriqueRESERVATIONAnnonceur);
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
                            Utilisateur user = CurrentSession.getUtilisateur();
                            notifications = notificationService.getListNotifications(user.getIdUtilisateur());
                            int notificationCount = notifications.size();
                            CurrentSession.addData("notificationCount", notificationCount);
                            
                            
                            int notificationCountLabelNumber = 0;
                            for(Notification notification : notifications){
                                if(!notification.getSeen()){
                                    notificationCountLabelNumber++;
                                }
                            }
                            CurrentSession.addData("notificationCountLabelNumber", notificationCountLabelNumber);
                            
                            Label notificationCountLabel = (Label) CurrentSession.getData("notificationCountLabel");
                            notificationCountLabel.setText("\n"+notificationCountLabelNumber);
                            
                            if(notificationCount != lastNotificationCount){
                                
                                for(int i=lastNotificationCount; i < notificationCount;i++){
                                    if(!notifications.get(i).getSeen()){
                                        Notifications notification;
                                        notification = Notifications.create()
                                                .title("notification")
                                                .text(notifications.get(i).getMessage());
                                        notification.showInformation();
                                    }
                                }
                                lastNotificationCount = notificationCount;
                                CurrentSession.addData("lastNotificationCount", lastNotificationCount);
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
            Notification notification = notifications.get(notifications.size() - 1 - index);
            notification.setSeen(true);
            notificationService.update(notification);
            if(notification.getIdPlan() != 0){
                Plan plan = planService.getByID(notification.getIdPlan());
                CurrentSession.addData("openedPlan",plan);
                SceneHandler.openScene(SceneEnum.CONSULTER_PLAN_UTILISATEUR);
            }
        }

    }
    
    private void initNotificationContent(){
        notificationPopOver = new PopOver();
        notificationPopOver.setCloseButtonEnabled(true);
        notificationPopOver.setDetached(true);
        notificationPopOver.setTitle("Notifications");
        
        WebView notificationContent = new WebView();
        notificationContent.setMaxSize(450, 200);
        
        WebEngine webEngine = notificationContent.getEngine();
        String notificationHTMLContent = FileHandler.readFile(NOTIFICATION_HTML_TEMPLATE_PATH, StandardCharsets.UTF_8);
        webEngine.loadContent(notificationHTMLContent);
        
        // Get window object of page.
        JSObject jsobj = (JSObject) webEngine.executeScript("window");
        
        //data init
        List<String> list = new ArrayList<>();
        List<String> seenList = new ArrayList<>();
        
        for(int i=notifications.size()-1;i>=0;i--){
            String message = notifications.get(i).getMessage();
            if(notifications.get(i).getIdPlan()!=0){
                Plan plan = planService.getByID(notifications.get(i).getIdPlan());
                if(plan!=null){
                    message+=" ["+plan.getTitre()+"]";
                }
            }
            message+=".";
            
            list.add(message);
            if(notifications.get(i).getSeen()){
                seenList.add("1");
            }
            else{
                seenList.add("0");
            }
        }
        
        //set data object in window object
        jsobj.setMember("data", list.toArray());
        jsobj.setMember("JavaExecutor",new JavaExecutor());
        jsobj.setMember("seenData", seenList.toArray());
        
        notificationPopOver.setContentNode(notificationContent);
    }
    
    @FXML
    private void showNotifications(MouseEvent event) {
        //TODO
        System.out.println("show notification popup");
        if(notificationPopOver == null || !notificationPopOver.isShowing()){
            initNotificationContent();
            
            notificationPopOver.show(btnNotification);
            
            //repeat to prevent load problems
            notificationPopOver.hide();
            initNotificationContent();
            notificationPopOver.show(btnNotification);
        }
    }

    @FXML
    void btn_monCompte_templateUser_Click(ActionEvent event) {
        SceneHandler.openScene(SceneEnum.CLIENT_MODIFIER);
    }

    @FXML
    void btn_acceuil_templateUser_Click(ActionEvent event) {
        SceneHandler.openScene(SceneEnum.CLIENT_ACCEUIL);
    }
}
