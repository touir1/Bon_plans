<?php

/**
 * Created by PhpStorm.
 * User: Mohamed Ali
 * Date: 22/04/2018
 * Time: 12:32
 */
class Reservation
{
    private $idreservation;
    private $date;
    private $idClient;
    private $idPlan;
    private $nbPlace;

    /**
     * @return mixed
     */
    public function getIdreservation()
    {
        return $this->idreservation;
    }

    /**
     * @param mixed $idreservation
     */
    public function setIdreservation($idreservation)
    {
        $this->idreservation = $idreservation;
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

    /**
     * @return mixed
     */
    public function getNbPlace()
    {
        return $this->nbPlace;
    }

    /**
     * @param mixed $nbPlace
     */
    public function setNbPlace($nbPlace)
    {
        $this->nbPlace = $nbPlace;
    }

    public function getAll($conn){

        $req = "SELECT reservation.idReservation, plan.titre, utilisateur.nom, reservation.date, reservation.Nbplace FROM plan, utilisateur, reservation WHERE reservation.idClient = utilisateur.idUtilisateur AND reservation.idPlan = plan.idPlan";

        return $conn->query($req);
    }

    public function getFor($conn, $id){
        $req = "SELECT reservation.idReservation, plan.titre, utilisateur.nom, reservation.date, reservation.Nbplace FROM plan, utilisateur, reservation WHERE reservation.idClient = utilisateur.idUtilisateur AND reservation.idPlan = plan.idPlan
AND reservation.idClient = ".$id;

        return $conn->query($req);
    }


    public  function reserver($conn, $nouveau){
        $req = "INSERT INTO `reservation` (`idReservation`, `date`, `UrlBonRes`, `Nbplace`, `statut`, `idClient`, `idPlan`) VALUES (NULL, '".date("Y-m-d H:i:s")."', 'testurl', '".$this->getNbPlace()."', '0', '".$this->getIdClient()."', '".$this->getIdPlan()."');";
        $conn->exec($req);
        //UPDATE `plan` SET `nbPlaceDispo` = '6' WHERE `plan`.`idPlan` = 4;

        $req1 = "UPDATE `plan` SET `nbPlaceDispo` = '".$nouveau."' WHERE `plan`.`idPlan` = ".$this->getIdPlan();
        $conn->exec($req1);

    }

}