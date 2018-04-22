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
            "Ajouter Categorie")
    
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
