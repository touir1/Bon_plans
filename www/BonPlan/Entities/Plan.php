<?php

/**
 * Created by PhpStorm.
 * User: Mohamed Ali
 * Date: 21/04/2018
 * Time: 14:51
 */
class Plan
{
    private  $id;
    private $titre;
    private $description;
    private $urlPhote;
    private $prixInitial;
    private $prixPromo;
    private $nbPlaceTotal;
    private $dateDebut;
    private $dateFin;
    private $nbPlaceDispo;
    private $statut;
    private $idAnnonceur;
    private $idCategorie;

    /**
     * @return mixed
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * @param mixed $id
     */
    public function setId($id)
    {
        $this->id = $id;
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
     * @return mixed
     */
    public function getUrlPhote()
    {
        return $this->urlPhote;
    }

    /**
     * @param mixed $urlPhote
     */
    public function setUrlPhote($urlPhote)
    {
        $this->urlPhote = $urlPhote;
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
    public function getIdCategorie()
    {
        return $this->idCategorie;
    }

    /**
     * @param mixed $idCategorie
     */
    public function setIdCategorie($idCategorie)
    {
        $this->idCategorie = $idCategorie;
    }


    public function add($conn){
        $req = "INSERT INTO `plan` (`idPlan`, `titre`, `description`, `urlPhoto`, `prixInitial`, `prixPromo`, `nbPlaceTotal`, `dateDebut`, `dateFin`, `nbPlaceDispo`, `statut`, `idAnnonceur`, `idCategorie`) VALUES (NULL, '".$this->getTitre()."', '".$this->getDescription()."', 'test', '".$this->getPrixInitial()."', '".$this->getPrixPromo()."', '".$this->getNbPlaceTotal()."', '".$this->getDateDebut()."', '".$this->getDateFin()."', '".$this->getNbPlaceTotal()."', '0', '".$this->getIdAnnonceur()."', '".$this->getIdCategorie()."');";
        $conn->exec($req);
    }

    public function getAll($conn){

        $req = "select * from plan";

        return $conn->query($req);
    }

    public function getFor($conn, $id){
        $req = "SELECT * FROM `plan` WHERE `idAnnonceur` = ".$id;

        return $conn->query($req);
    }

    public function getOne($conn, $id){
        $req = "SELECT * FROM `plan` WHERE `idPlan` = ".$id;

        $rows = $conn->query($req);

        foreach($rows as $row){
            return $row;
        }
    }

    public function getForCategorie($conn, $id){
        $req = "SELECT * FROM `plan` WHERE `idCategorie` = ".$id;

        return $conn->query($req);
    }

    public function changerStatut($conn, $id, $statut){
        $req = "UPDATE `plan` SET `statut` = '".$statut."' WHERE `plan`.`idPlan` = ".$id.";";

        $conn->exec($req);
    }
}