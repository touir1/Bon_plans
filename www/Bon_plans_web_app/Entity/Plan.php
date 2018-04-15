<?php
/**
 * Created by PhpStorm.
 * User: SadfiAmine
 * Date: 15/04/2018
 * Time: 13:32
 */

class Plan
{
    private  $idPlan;
    private  $titre ;
    private  $description;
    private  $urlPhoto;
    private  $prixInitial;
    private  $prixPromo;
    private  $nbPlaceTotal;
    private  $dateDebut;
    private  $dateFin;
    private  $nbPlaceDispo;
    private  $statut;
    private  $nbJaime ;
    private  $nbJaimePas;
    private  $note ;
        //
    private  $idClient;
    private  $idAnnonceur;
    private  $idCategorie;

    public function __construct()
    {
    }
    public function __construct1($idPlan,$titre,$description,$urlPhoto,$prixInitial,$prixPromo,$nbPlaceTotal,$dateDebut,$dateFin,$nbPlaceDispo,$statut,$nbJaime,$nbJaimePas,$note,$idClient,$idAnnonceur,$idCategorie)
    {
        $this->$idPlan=$idPlan;
        $this->$titre=$titre;
        $this->$description=$description;
        $this->$urlPhoto=$urlPhoto;
        $this->$prixInitial=$prixInitial;
        $this->$prixPromo=$prixPromo;
        $this->$nbPlaceTotal=$nbPlaceTotal;
        $this->$dateDebut=$dateDebut;
        $this->$dateFin=$dateFin;
        $this->$nbPlaceDispo=$nbPlaceDispo;
        $this->$statut=$statut;
        $this->$nbJaime=$nbJaime;
        $this->$nbJaimePas=$nbJaimePas;
        $this->$note=$note;
        $this->$idClient=$idClient;
        $this->$idAnnonceur=$idAnnonceur;
        $this->$idCategorie=$idCategorie;
    }
    public function __construct2($titre,$description,$urlPhoto,$prixInitial,$prixPromo,$nbPlaceTotal,$dateDebut,$dateFin,$nbPlaceDispo,$statut,$nbJaime,$nbJaimePas,$note,$idClient,$idAnnonceur,$idCategorie)
    {
        $this->$titre=$titre;
        $this->$description=$description;
        $this->$urlPhoto=$urlPhoto;
        $this->$prixInitial=$prixInitial;
        $this->$prixPromo=$prixPromo;
        $this->$nbPlaceTotal=$nbPlaceTotal;
        $this->$dateDebut=$dateDebut;
        $this->$dateFin=$dateFin;
        $this->$nbPlaceDispo=$nbPlaceDispo;
        $this->$statut=$statut;
        $this->$nbJaime=$nbJaime;
        $this->$nbJaimePas=$nbJaimePas;
        $this->$note=$note;
        $this->$idClient=$idClient;
        $this->$idAnnonceur=$idAnnonceur;
        $this->$idCategorie=$idCategorie;
    }

    /**
     * @return mixed
     */
    public function getIdPlan()
    {
        return $this->idPlan;
    }

    /**
     * @param mixed $idPlan
     */
    public function setIdPlan($idPlan)
    {
        $this->idPlan = $idPlan;
    }

    /**
     * @return mixed
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * @param mixed $description
     */
    public function setDescription($description)
    {
        $this->description = $description;
    }

    /**
     * @param mixed $idClient
     */
    public function setIdClient($idClient)
    {
        $this->idClient = $idClient;
    }

    /**
     * @return mixed
     */
    public function getIdClient()
    {
        return $this->idClient;
    }

    /**
     * @param mixed $nbJaimePas
     */
    public function setNbJaimePas($nbJaimePas)
    {
        $this->nbJaimePas = $nbJaimePas;
    }

    /**
     * @return mixed
     */
    public function getNbJaimePas()
    {
        return $this->nbJaimePas;
    }

    /**
     * @return mixed
     */
    public function getTitre()
    {
        return $this->titre;
    }

    /**
     * @param mixed $titre
     */
    public function setTitre($titre)
    {
        $this->titre = $titre;
    }

    /**
     * @param mixed $idCategorie
     */
    public function setIdCategorie($idCategorie)
    {
        $this->idCategorie = $idCategorie;
    }

    /**
     * @return mixed
     */
    public function getIdCategorie()
    {
        return $this->idCategorie;
    }

    /**
     * @return mixed
     */
    public function getNbJaime()
    {
        return $this->nbJaime;
    }

    /**
     * @param mixed $nbJaime
     */
    public function setNbJaime($nbJaime)
    {
        $this->nbJaime = $nbJaime;
    }

    /**
     * @return mixed
     */
    public function getDateDebut()
    {
        return $this->dateDebut;
    }

    /**
     * @param mixed $dateDebut
     */
    public function setDateDebut($dateDebut)
    {
        $this->dateDebut = $dateDebut;
    }

    /**
     * @return mixed
     */
    public function getDateFin()
    {
        return $this->dateFin;
    }

    /**
     * @param mixed $dateFin
     */
    public function setDateFin($dateFin)
    {
        $this->dateFin = $dateFin;
    }

    /**
     * @return mixed
     */
    public function getIdAnnonceur()
    {
        return $this->idAnnonceur;
    }

    /**
     * @param mixed $idAnnonceur
     */
    public function setIdAnnonceur($idAnnonceur)
    {
        $this->idAnnonceur = $idAnnonceur;
    }

    /**
     * @return mixed
     */
    public function getNbPlaceDispo()
    {
        return $this->nbPlaceDispo;
    }

    /**
     * @param mixed $nbPlaceDispo
     */
    public function setNbPlaceDispo($nbPlaceDispo)
    {
        $this->nbPlaceDispo = $nbPlaceDispo;
    }

    /**
     * @return mixed
     */
    public function getNbPlaceTotal()
    {
        return $this->nbPlaceTotal;
    }

    /**
     * @param mixed $nbPlaceTotal
     */
    public function setNbPlaceTotal($nbPlaceTotal)
    {
        $this->nbPlaceTotal = $nbPlaceTotal;
    }

    /**
     * @return mixed
     */
    public function getNote()
    {
        return $this->note;
    }

    /**
     * @param mixed $note
     */
    public function setNote($note)
    {
        $this->note = $note;
    }

    /**
     * @return mixed
     */
    public function getPrixInitial()
    {
        return $this->prixInitial;
    }

    /**
     * @param mixed $prixInitial
     */
    public function setPrixInitial($prixInitial)
    {
        $this->prixInitial = $prixInitial;
    }

    /**
     * @return mixed
     */
    public function getPrixPromo()
    {
        return $this->prixPromo;
    }

    /**
     * @param mixed $prixPromo
     */
    public function setPrixPromo($prixPromo)
    {
        $this->prixPromo = $prixPromo;
    }

    /**
     * @return mixed
     */
    public function getStatut()
    {
        return $this->statut;
    }

    /**
     * @param mixed $statut
     */
    public function setStatut($statut)
    {
        $this->statut = $statut;
    }

    /**
     * @return mixed
     */
    public function getUrlPhoto()
    {
        return $this->urlPhoto;
    }

    /**
     * @param mixed $urlPhoto
     */
    public function setUrlPhoto($urlPhoto)
    {
        $this->urlPhoto = $urlPhoto;
    }


}