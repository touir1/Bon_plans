<?php
/**
 * Created by PhpStorm.
 * User: SadfiAmine
 * Date: 15/04/2018
 * Time: 12:34
 */

class Commentaire
{
    private  $idCommentaire;
    private  $texte;
    private  $date;
    private  $nbJaime;
    private  $nbJaimePas;
    private  $idClient;
    private  $idPlan;

    public function __construct()
    {
    }
    public function __construct1($idCommentaire,$texte,$date,$nbJaime,$nbJaimePas)
    {   $this->$idCommentaire=$idCommentaire;
        $this->texte=$texte;
        $this->$date=$date;
        $this->$nbJaime=$nbJaime;
        $this->nbJaimePas=$nbJaimePas;
    }
    public function __construct2($texte,$date,$nbJaime,$nbJaimePas)
    {   $this->texte=$texte;
        $this->$date=$date;
        $this->$nbJaime=$nbJaime;
        $this->nbJaimePas=$nbJaimePas;
    }
    public function __construct3($texte,$date,$nbJaime,$nbJaimePas,$idClient,$idPlan)
    {   $this->texte=$texte;
        $this->$date=$date;
        $this->$nbJaime=$nbJaime;
        $this->nbJaimePas=$nbJaimePas;
        $this->$idClient=$idClient;
        $this->$idPlan=$idPlan;


    }

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
    public function getNbJaimePas()
    {
        return $this->nbJaimePas;
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

}