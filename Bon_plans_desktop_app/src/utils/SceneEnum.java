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
    STAT_PLAN(
            "MenuStatPlan", 
            "../artan/statistique/plan/PlanStat.fxml", 
            "Menu statistique"),
    STAT_CAT(
            "MenuStatCat", 
            "../artan/statistique/categorie/CategorieStat.fxml", 
            "Categorie statistique"),
    STAT_MENU(
            "MenuStat", 
            "../artan/statistique/menu/StatMenu.fxml", 
            "Menu statistique"),
    LISTE_PLAN(
            "ListeDesPlan", 
            "../artan/plan/liste/PlanListe.fxml", 
            "liste des plans"),
    MODIFIER_PLAN(
            "ModifierPlan", 
            "../artan/plan/modifier/PlanModifier.fxml", 
            "Modifier un plan"),
    SINGLE_PLAN(
            "AfficherPlan", 
            "../artan/plan/single/PlanSingle.fxml", 
            "Afficher un plan"),
    AJOUTER_PLAN(
            "AjouterPlan", 
            "../artan/plan/create/PlanCreate.fxml", 
            "Création dun nouveau plans"),
    VALIDER_NEW_PLAN(
            "ValiderNewPlan", 
            "../tn/esprit/bonplans/Gui/GererAnnonce/administration/validerNewPlan/ValiderNewPlan.fxml", 
            "Validation nouveau plans"),
    SINSCRIRE(
            "Sinscrire", 
            "../tn/esprit/bonplans/Gui/GererUtilisateur/sinscrire/Sinscrire.fxml", 
            "S'inscrire"),
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
            "../tn/esprit/bonplans/Gui/GererUtilisateur/seConnecter/SeConnecter.fxml",
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
    CONSULTER_PLAN_OUTSIDER(
            "ConsulterPlanOutsider",
            "../tn/esprit/bonplans/Gui/GererPlan/ConsulterPlanOutsider/ConsulterPlanOutsider.fxml",
            "Consulter plan"
            ),
    CONSULTER_PLAN_UTILISATEUR(
            "ConsulterPlanUtilisateur",
            "../tn/esprit/bonplans/Gui/GererPlan/ConsulterPlanUtilisateur/ConsulterPlan.fxml",
            "Consulter plan"),
    CONSULTER_PLAN_ADMIN(
            "ConsulterPlanAdmin",
            "../tn/esprit/bonplans/Gui/GererPlan/ConsulterPlanAdmin/ConsulterPlan.fxml",
            "Consulter plan"),
    CONSULTER_LISTE_NOTIFICATION_CLIENT(
            "ConsulterNotificationClient",
            "../tn/esprit/bonplans/Gui/GererNotification/listeNotificationClient/ConsulterNotificationClient.fxml",
            "Consulter notifications"),
    CONSULTER_LISTE_NOTIFICATION_ADMIN(
            "ConsulterNotificationAdmin",
            "../tn/esprit/bonplans/Gui/GererNotification/listeNotificationAdmin/ConsulterNotificationAdmin.fxml",
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
            "Historiques des Réservations"),
    ACCEUIL(
            "Accueil",
            "../tn/esprit/bonplans/Gui/Accueil/Accueil.fxml",
            "Accueil"),
    CLIENT_ACCEUIL(
            "Client Accueil",
            "../tn/esprit/bonplans/Gui/GererUtilisateur/client/acceuil/Acceuil.fxml",
            "Accueil"),
    CLIENT_MODIFIER(
            "Mon Compte",
            "../tn/esprit/bonplans/Gui/GererUtilisateur/client/modifier/Modifier.fxml",
            "Mon Compte"
            )
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
