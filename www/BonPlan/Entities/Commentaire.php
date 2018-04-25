<?php

/**
 * Created by PhpStorm.
 * User: Mohamed Ali
 * Date: 23/04/2018
 * Time: 15:42
 */
class Commentaire
{

    private $idCommentaire;
    private $texte;
    private $date;
    private $idClient;
    private $idPlan;

    /**
     * @return mixed
     */
    public function getIdCommentaire()
    {
        return $this->idCommentaire;
    }

    /**
     * @param mixed $idCommentaire
     */
    public function setIdCommentaire($idCommentaire)
    {
        $this->idCommentaire = $idCommentaire;
    }

    /**
     * @return mixed
     */
    public function getTexte()
    {
        return $this->texte;
    }

    /**
     * @param mixed $texte
     */
    public function setTexte($texte)
    {
        $this->texte = $texte;
    }

    /**
     * @return mixed
     */
    public function getDate()
    {
        return $this->date;
    }

    /**
     * @param mixed $date
     */
    public function setDate($date)
    {
        $this->date = $date;
    }

    /**
     * @return mixed
     */
    public function getIdClient()
    {
        return $this->idClient;
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

    public function getFor($conn, $id){
        $req = "SELECT utilisateur.nom, commentaire.texte, commentaire.idCommentaire FROM commentaire, utilisateur WHERE `commentaire`.`idClient` =  `utilisateur`.`idUtilisateur` AND `idPlan` = ".$id;

        return $conn->query($req);
    }

    public function count($conn, $id){
        //
        $req = "SELECT count(*) FROM commentaire, utilisateur WHERE `commentaire`.`idClient` =  `utilisateur`.`idUtilisateur` AND `idPlan` = ".$id;
        $rows =$conn->query($req);

        foreach($rows as $row){
            return $row;
        }
    }

    public function add($conn){
        $req = "INSERT INTO `commentaire` (`idCommentaire`, `texte`, `date`, `idClient`, `idPlan`) VALUES (NULL, '".$this->getTexte()."', '".date("Y-m-d H:i:s")."', '".$this->getIdClient()."', '".$this->getIdPlan()."');";

        $conn->exec($req);
    }

    public function  delete($conn, $id){
        $req = "DELETE FROM `commentaire` WHERE `idCommentaire` = ".$id;
        $conn->exec($req);
    }

    public function signaler($conn, $id){
        $req = "INSERT INTO `signaler` (`idSignaler`, `idCommentaire`) VALUES (NULL, '".$id."');";
        $conn->exec($req);
    }

    public function countSignaler($conn, $id){
        $req = "SELECT COUNT(*) FROM `signaler` WHERE `idCommentaire` = ".$id;
        $rows = $conn->query($req);
        foreach($rows as $row){
            return $row[0];
        }
    }
}