/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.GererNotification.listeNotificationClient;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import tn.esprit.bonplans.entity.Notification;
import tn.esprit.bonplans.entity.Plan;
import tn.esprit.bonplans.entity.Utilisateur;
import tn.esprit.bonplans.service.INotification;
import tn.esprit.bonplans.service.IPlan;
import tn.esprit.bonplans.service.implementation.NotificationImpl;
import tn.esprit.bonplans.service.implementation.PlanImpl;
import utils.CurrentSession;
import utils.SceneEnum;
import utils.SceneHandler;

/**
 * FXML Controller class
 *
 * @author touir
 */
public class ConsulterNotificationClientController extends Application implements Initializable{
    
    private IPlan planService;
    private INotification notificationService;
    
    @FXML
    private TableView<Notification> notificationList;
    @FXML
    private TableColumn<Notification, String> message;
    @FXML
    private TableColumn<Notification, String> seen;
    @FXML
    private TableColumn<Notification, String> plan;
    @FXML
    private TableColumn<Notification, String> global;
    @FXML
    private ImageView refreshButton;
    @FXML
    private JFXTextField messageFilter;
    @FXML
    private Label globalFilter;
    @FXML
    private Label vueFilter;
    
    private ObservableList<Notification> observableList;
    private int globalFilterState;
    private int vueFilterState;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        planService = new PlanImpl();
        notificationService = new NotificationImpl();
        
        //init data
        initListNotification();
        //init template title
        SceneHandler.initTemplate(SceneEnum.CONSULTER_LISTE_NOTIFICATION_CLIENT);
    }  
    
    private void initListNotification(){
        Utilisateur utilisateur = CurrentSession.getUtilisateur();
        List<Notification> notifications = notificationService.getListNotifications(utilisateur.getIdUtilisateur());
        
        observableList = FXCollections.observableArrayList(notifications);
        
        message.setCellValueFactory(new PropertyValueFactory<>("message"));
        seen.setCellValueFactory(c-> {
            String result = "NON";
            if(c.getValue().getSeen()){
                result = "OUI";
            }
            return new SimpleStringProperty(result);
        });
        plan.setCellValueFactory(c-> {
            String result = "";
            if(c.getValue().getIdPlan()> 0){
                Plan plan = planService.getByID(c.getValue().getIdPlan());
                if(plan != null){
                    result = plan.getTitre();
                }
            }
            return new SimpleStringProperty(result);
        });
        global.setCellValueFactory(c-> {
            String result = "OUI";
            if(c.getValue().getIdPlan()> 0){
                result = "NON";
            }
            return new SimpleStringProperty(result);
        });
        
        //filtering data
        FilteredList<Notification> filteredData = new FilteredList<>(observableList, n -> true);
        SortedList<Notification> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(notificationList.comparatorProperty());
        notificationList.setItems(sortedData);
        globalFilterState = 0;
        vueFilterState = 0;
        
        messageFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(n -> {
                return filterList(n);
            });
        });
        globalFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(n -> {
                return filterList(n);
            });
        });
        vueFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(n -> {
                return filterList(n);
            });
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneHandler.initPrimaryStage(primaryStage);
        SceneHandler.openScene(SceneEnum.CONSULTER_LISTE_NOTIFICATION_CLIENT);
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }

    @FXML
    private void refreshList(MouseEvent event) {
        messageFilter.setText("");
        globalFilter.setText("Tout");
        globalFilter.setTextFill(Paint.valueOf("#00FF00"));
        globalFilterState = 0;
        vueFilter.setText("Tout");
        vueFilter.setTextFill(Paint.valueOf("#00FF00"));
        vueFilterState = 0;
        
        List<Notification> notifications = notificationService.getListNotifications(CurrentSession.getUtilisateur().getIdUtilisateur());
        observableList.clear();
        observableList.addAll(notifications);
    }

    @FXML
    private void changeGlobalFilter(MouseEvent event) {
        globalFilterState = (globalFilterState+1 >= 3) ? 0 : globalFilterState+1;
        switch(globalFilterState){
            case 0: 
                globalFilter.setText("Tout");
                globalFilter.setTextFill(Paint.valueOf("#00FF00"));
                break;
            case 1: 
                globalFilter.setText("Non");
                globalFilter.setTextFill(Paint.valueOf("#FFFF00"));
                break;
            case 2: 
                globalFilter.setText("Oui");
                globalFilter.setTextFill(Paint.valueOf("#FFFF00"));
                break;
        }
    }

    @FXML
    private void changeVueFilter(MouseEvent event) {
        vueFilterState = (vueFilterState+1 >= 3) ? 0 : vueFilterState+1;
        switch(vueFilterState){
            case 0: 
                vueFilter.setText("Tout");
                vueFilter.setTextFill(Paint.valueOf("#00FF00"));
                break;
            case 1: 
                vueFilter.setText("Non");
                vueFilter.setTextFill(Paint.valueOf("#FFFF00"));
                break;
            case 2: 
                vueFilter.setText("Oui");
                vueFilter.setTextFill(Paint.valueOf("#FFFF00"));
                break;
        }
    }
    
    private boolean filterList(Notification notification){
        boolean result = true;
        
        result &= notification.getMessage().trim().toLowerCase().contains(messageFilter.getText());
        result &= (globalFilterState==0 || (globalFilterState==1 && notification.getIdPlan()!=0) || (globalFilterState==2 && notification.getIdPlan()==0));
        result &= (vueFilterState==0 || (vueFilterState==1 && !notification.getSeen()) || (vueFilterState==2 && notification.getSeen()));
        
        return result;
    }
    
    //USED ONLY FOR DOUBLE CLICK
    private Notification temp;
    private Date lastClickTime;
    
    @FXML
    private void doubleClickNotification() {
        initDoubleClickOut();
        Notification row = notificationList.getSelectionModel().getSelectedItem();
        if (row == null){
            notificationList.getSelectionModel().clearSelection();
            return;
        }
        if(!row.equals(temp)){
            temp = row;
            lastClickTime = new Date();
        } 
        else {
            Date now = new Date();
            long diff = now.getTime() - lastClickTime.getTime();
            if (diff < 300){ //another click registered in 300 millis
                if(!row.getSeen()){
                    row.setSeen(true);
                    notificationService.update(row);
                    refreshList(null);
                }
                
                if(row.getIdPlan()>0){
                    Plan plan = planService.getByID(row.getIdPlan());
                    CurrentSession.addData("openedPlan", plan);
                    SceneHandler.openScene(SceneEnum.CONSULTER_PLAN_UTILISATEUR);
                }
                else{
                    lastClickTime = new Date();
                }
                
            } else {
                lastClickTime = new Date();
            }
        }
    }
    
    //USED ONLY FOR INIT DOUBLE CLICK OUT
    private boolean isInitDoubleClickOut = false;
    private void initDoubleClickOut(){
        if(!isInitDoubleClickOut){
            Scene scene = notificationList.getScene();
            scene.addEventFilter(MouseEvent.MOUSE_CLICKED, evt -> {
                Node source = evt.getPickResult().getIntersectedNode();

                // move up through the node hierarchy until a TableRow or scene root is found 
                while (source != null && !preventLooseSelection(source) && !(source instanceof TableRow)) {
                    source = source.getParent();
                }


                // clear selection on click anywhere but on a filled row
                if (source == null || (source instanceof TableRow && ((TableRow) source).isEmpty())) {
                    notificationList.getSelectionModel().clearSelection();
                }
            });
            isInitDoubleClickOut = true;
        }
    }
    
    private boolean preventLooseSelection(Node source){
        if(source instanceof JFXButton && "deleteButton".equals(((JFXButton)source).getId())){
            return true;
        }
        return false;
    }

    @FXML
    private void deleteSelectedNotification(MouseEvent event) {
        Notification notification = notificationList.getSelectionModel().getSelectedItem();
        System.out.println(notification);
        if(notification != null){
            notificationService.remove(notification.getIdNotification());
            refreshList(null);
        }
    }
    
}
