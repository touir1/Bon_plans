/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author touir
 */
public enum SceneEnum {
    
    VALIDER_NEW_PLAN(
            "ValiderNewPlan", 
            "../tn/esprit/bonplans/Gui/GererAnnonce/administration/validerNewPlan/ValiderNewPlan.fxml", 
            "Validation nouveau plans"),
    VALIDER_MODIFIED_PLAN(
            "ValiderModifiedPlan", 
            "../tn/esprit/bonplans/Gui/GererAnnonce/administration/validerModifiedPlan/ValiderModifiedPlan.fxml", 
            "Validation plans modifiés"),
    VALIDER_PLAN_MENU(
            "ValiderPlanMenu", 
            "../tn/esprit/bonplans/Gui/GererAnnonce/administration/validationPlanMenu/ValiderPlanMenu.fxml", 
            "Validation plans"
            ),
    SE_CONNECTER(
            "SeConnecter",
            "../tn/esprit/bonplans/Gui/GererUtilisateur/SeConnecter.fxml",
            "Se connecter"),
    LISTE_CATEGORIE(
            "ListeCategorie",
            "../tn/esprit/bonplans/Gui/GererCategorie/Consulter/Categories.fxml",
            "Consultation liste des catégories"),
    MODIFIER_CATEGORIE(
            "ModifierCategorie",
            "../tn/esprit/bonplans/Gui/GererCategorie/Modifier/ModifierCategorie.fxml",
            "Modifier catégorie"),
    AJOUTER_CATEGORIE(
            "AjouterCategorie",
            "../tn/esprit/bonplans/Gui/GererCategorie/Ajouter/AjouterCategorie.fxml",
            "Ajouter Categorie"),
    CONSULTER_PLAN_UTILISATEUR(
            "ConsulterPlanUtilisateur",
            "../tn/esprit/bonplans/Gui/GererPlan/ConsulterPlanUtilisateur/ConsulterPlan.fxml",
            "Consulter plan"),
    CONSULTER_PLAN_ADMIN(
            "ConsulterPlanAdmin",
            "../tn/esprit/bonplans/Gui/GererPlan/ConsulterPlanAdmin/ConsulterPlan.fxml",
            "Consulter plan"),
    CONSULTER_LISTE_NOTIFICATION(
            "ConsulterNotificationClient",
            "../tn/esprit/bonplans/Gui/GererNotification/listeNotificationClient/ConsulterNotificationClient.fxml",
            "Consulter notifications"),
    RESERVATION(
            "Reservation",
            "../tn/esprit/bonplans/Gui/GererReservation/TestReservation.fxml",
            "Réservation"),
     HistoriqueRESERVATIONClient(
            "HistoriqueReservationClient",
            "../tn/esprit/bonplans/Gui/GererReservation/HistoriqueReserv_Client/Historique_ReservClient.fxml",
            "Historique Réservation"),
     HistoriqueRESERVATIONAnnonceur(
            "HistoriqueReservationAnnonceur",
            "../tn/esprit/bonplans/Gui/GererReservation/ListeReservation_Annonceur/ListeReservation_Annonceur.fxml",
            "Historiques des Réservations")
    
    ;
    
    private final String sceneName;
    private final String fxmlScenePath;
    private final String sceneTitle;

    SceneEnum(String sceneName, String fxmlScenePath, String sceneTitle){
        this.sceneName = sceneName;
        this.fxmlScenePath = fxmlScenePath;
        this.sceneTitle = sceneTitle;
    }

    public String getSceneName() {
        return sceneName;
    }

    public String getFxmlScenePath() {
        return fxmlScenePath;
    }

    public String getSceneTitle() {
        return sceneTitle;
    }
    
    
    
}
