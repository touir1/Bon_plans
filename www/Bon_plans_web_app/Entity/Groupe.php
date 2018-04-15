<?php
/**
 * Created by PhpStorm.
 * User: SadfiAmine
 * Date: 15/04/2018
 * Time: 13:27
 */

class Groupe
{
    private $idGroupe;
    private $description;

    public function  __construct()
    {
    }
    public function  __construct1($idGroupe,$description)
    {   $this->$idGroupe=$idGroupe;
        $this->$description=$description;
    }
    public function  __construct2($description)
    {   $this->$description=$description;
     }

    /**
     * @return mixed
     */
    public function getIdGroupe()
    {
        return $this->idGroupe;
    }

    /**
     * @param mixed $idGroupe
     */
    public function setIdGroupe($idGroupe)
    {
        $this->idGroupe = $idGroupe;
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

}