<?php
/**
 * Created by PhpStorm.
 * User: SadfiAmine
 * Date: 15/04/2018
 * Time: 12:53
 */

class Reservation
{
    private $idReservation;
    private $date;
    private $urlBonRes;
        //
    private $idClient;
    private $idPlan;

    public function __construct()
    {
    }
    public function __construct1($idReservation,$date,$urlBonRes)
    {   $this->$idReservation=$idReservation;
        $this->$date=$date;
        $this->$urlBonRes=$urlBonRes;
    }
    public function __construct2($date,$urlBonRes,$idClient,$idPlan)
    {
        $this->$date=$date;
        $this->$urlBonRes=$urlBonRes;
        $this->$idClient=$idClient;
        $this->$idPlan=$idPlan;
    }
    public function __construct3($date,$urlBonRes)
    {
        $this->$date=$date;
        $this->$urlBonRes=$urlBonRes;
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
    public function getIdReservation()
    {
        return $this->idReservation;
    }

    /**
     * @param mixed $idReservation
     */
    public function setIdReservation($idReservation)
    {
        $this->idReservation = $idReservation;
    }

    /**
     * @return mixed
     */
    public function getUrlBonRes()
    {
        return $this->urlBonRes;
    }

    /**
     * @param mixed $UrlBonRes
     */
    public function setUrlBonRes($urlBonRes)
    {
        $this->urlBonRes = $urlBonRes;
    }


}