/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.bonplans.Gui.GererNotification.listeNotificationAdmin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.esprit.bonplans.entity.Notification;
import tn.esprit.bonplans.service.INotification;
import tn.esprit.bonplans.service.implementation.NotificationImpl;
import utils.SceneEnum;
import utils.SceneHandler;
import utils.StringUtils;

/**
 * FXML Controller class
 *
 * @author touir
 */
public class ConsulterNotificationAdminController extends Application implements Initializable {
    
    private INotification notificationService;
    
    @FXML
    private TableView<Notification> notificationList;
    @FXML
    private TableColumn<Notification, String> message;
    @FXML
    private ImageView refreshButton;
    @FXML
    private JFXTextField messageFilter;
    
    private ObservableList<Notification> observableList;
    @FXML
    private JFXButton deleteButton;
    @FXML
    private JFXTextField globalNotificationField;
    @FXML
    private JFXButton sendGlobalNotificationButton;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        notificationService = new NotificationImpl();
        
        //init data
        initListNotification();
        //init template title
        SceneHandler.initTemplate(SceneEnum.CONSULTER_LISTE_NOTIFICATION_ADMIN);
    }    
    
    private void initListNotification(){
        List<Notification> notifications = notificationService.getListNotificationsGlobal();
        
        observableList = FXCollections.observableArrayList(notifications);
        
        message.setCellValueFactory(new PropertyValueFactory<>("message"));
        
        //filtering data
        FilteredList<Notification> filteredData = new FilteredList<>(observableList, n -> true);
        SortedList<Notification> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(notificationList.comparatorProperty());
        notificationList.setItems(sortedData);
        
        messageFilter.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(n -> {
                return filterList(n);
            });
        });
    }
    
    private boolean filterList(Notification notification){
        boolean result = true;
        
        result &= notification.getMessage().trim().toLowerCase().contains(messageFilter.getText());
        
        return result;
    }

    @FXML
    private void refreshList(MouseEvent event) {
        messageFilter.setText("");
        globalNotificationField.setText("");
        
        List<Notification> notifications = notificationService.getListNotificationsGlobal();
        observableList.clear();
        observableList.addAll(notifications);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneHandler.initPrimaryStage(primaryStage);
        SceneHandler.openScene(SceneEnum.CONSULTER_LISTE_NOTIFICATION_ADMIN);
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }

    @FXML
    private void deleteSelectedNotification(MouseEvent event) {
        Notification notification = notificationList.getSelectionModel().getSelectedItem();
        if(notification != null){
            notificationService.remove(notification.getIdNotification());
            refreshList(null);
        }
    }

    @FXML
    private void sendGlobalNotification(MouseEvent event) {
        if(!StringUtils.isEmpty(globalNotificationField.getText())){
            notificationService.sendGlobalNotification(globalNotificationField.getText());
            refreshList(null);
        }
    }
    
}
