<?php
/**
 * Created by PhpStorm.
 * User: touir
 * Date: 18/05/2018
 * Time: 23:48
 */

class Notification
{
    private $idNotification;
    private $seen;
    private $message;
    private $idPlan;

    /**
     * @return mixed
     */
    public function getIdNotification()
    {
        return $this->idNotification;
    }

    /**
     * @param mixed $idNotification
     */
    public function setIdNotification($idNotification)
    {
        $this->idNotification = $idNotification;
    }

    /**
     * @return mixed
     */
    public function getSeen()
    {
        return $this->seen;
    }

    /**
     * @param mixed $seen
     */
    public function setSeen($seen)
    {
        $this->seen = $seen;
    }

    /**
     * @return mixed
     */
    public function getMessage()
    {
        return $this->message;
    }

    /**
     * @param mixed $message
     */
    public function setMessage($message)
    {
        $this->message = $message;
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


    public function getAll($conn){

        $req = "SELECT * FROM `notification`";

        return $conn->query($req);
    }

    public function getGlobalNotifications($conn){

        $req = "SELECT * FROM `notification` WHERE idPlan = 0";

        return $conn->query($req);
    }

    public function getNotificationsByUser($conn,$idUtilisateur){
        $req = "SELECT * FROM `notification` WHERE idPlan = 0 OR idPlan in(SELECT idPlan FROM `plan` WHERE idAnnonceur = ".$idUtilisateur.")";

        return $conn->query($req);
    }

    public function getCountNonValidatedModifiedPlans($conn){
        $req = "SELECT count(*) FROM `plan` WHERE statut = 2";
        $rows = $conn->query($req);
        foreach($rows as $row) {
            return $row[0];
        }
    }

    public function getCountNonValidatedNewPlans($conn){
        $req = "SELECT count(*) FROM `plan` WHERE statut = 0";
        $rows = $conn->query($req);
        foreach($rows as $row) {
            return $row[0];
        }
    }

    public function getListOfNonValidatedModifiedPlans($conn){
        $req = "SELECT * FROM `plan` WHERE statut = 2";
        $rows = $conn->query($req);
        foreach($rows as $row) {
            return $row;
        }
    }

    public function getListOfNonValidatedNewPlans($conn){
        $req = "SELECT * FROM `plan` WHERE statut = 0";
        $rows = $conn->query($req);
        foreach($rows as $row) {
            return $row;
        }
    }
    public function changeNotificationAsSeen($conn, $id){
        $req = "UPDATE `notification` SET seen = 1 WHERE idNotification = ".$id;
        $conn->exec($req);
    }

    public function add($conn){
        $req = "INSERT INTO `notification` (`idNotification`, `seen`, `message`, `idPlan`) VALUES (NULL, ".$this->getSeen().", '".$this->getMessage()."', ".$this->getIdPlan().");";
        $conn->exec($req);
    }

    public function get($conn, $id){

        $req = "SELECT `idNotification`, `seen`, `message`, `idPlan` FROM `notification` WHERE `idNotification` = " . $id;
        $rows = $conn->query($req);
        foreach($rows as $row) {
            return $row;
        }
    }

    public function update($conn, $id){
        $req = "UPDATE `notification` SET `message` = '".$this->getMessage()."', `seen` = ".$this->getSeen().", `idPlan` = ".$this->getIdPlan()." WHERE `notification`.`idNotification` = ".$id.";";
        $conn->exec($req);
    }

}