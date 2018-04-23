<?php

/**
 * Created by PhpStorm.
 * User: Mohamed Ali
 * Date: 22/04/2018
 * Time: 00:48
 */
class Categorie
{
    private $idCategorie;
    private $titre;
    private $urlPhoto;

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

    public function getAll($conn){

        $req = "SELECT * FROM `categorie`";

        return $conn->query($req);
    }

    public function add($conn){
        $req = "INSERT INTO `categorie` (`idCategorie`, `titre`, `urlPhoto`) VALUES (NULL, '".$this->getTitre()."', '".$this->getUrlPhoto()."');";
        $conn->exec($req);
    }

    public function get($conn, $id){

        $req = "SELECT `idCategorie`, `titre`, `urlPhoto` FROM `categorie` WHERE `idCategorie` = " . $id;
        $rows = $conn->query($req);
        foreach($rows as $row) {
            return $row;
        }
    }

    public function update($conn, $id){
        $req = "UPDATE `categorie` SET `titre` = '".$this->getTitre()."' WHERE `categorie`.`idCategorie` = ".$id.";";
        $conn->exec($req);
    }

}