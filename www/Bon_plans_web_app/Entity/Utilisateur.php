<?php
/**
 * Created by PhpStorm.
 * User: SadfiAmine
 * Date: 15/04/2018
 * Time: 13:13
 */

class Utilisateur
{
    private $idUtilisateur;
    private $idGroupe;
    private $mdp;
    private $email;
    private $nom;
    private $prenom;
    private $urlphoto;
    private $ville;
    private $adresse;
    private $dateCreation;
    private $dateAcces;
    private $tempsAcces;

    public function __construct()
    {
    }
    public function __construct1($idUtilisateur,$idGroupe,$mdp,$email,$nom,$prenom,$urlphoto,$ville,$adresse,$dateCreation,$dateAcces,$tempsAcces)
    {   $this->$idUtilisateur=$idUtilisateur;
        $this->$idGroupe=$idGroupe;
        $this->$mdp=$mdp;
        $this->$email=$email;
        $this->$nom=$nom;
        $this->$prenom=$prenom;
        $this->$urlphoto=$urlphoto;
        $this->$ville=$ville;
        $this->$adresse=$adresse;
        $this->$dateCreation=$dateCreation;
        $this->$dateAcces=$dateAcces;
        $this->$tempsAcces=$tempsAcces;

    }
    public function __construct2($idGroupe,$mdp,$email,$nom,$prenom,$urlphoto,$ville,$adresse,$dateCreation,$dateAcces,$tempsAcces)
    {
        $this->$idGroupe=$idGroupe;
        $this->$mdp=$mdp;
        $this->$email=$email;
        $this->$nom=$nom;
        $this->$prenom=$prenom;
        $this->$urlphoto=$urlphoto;
        $this->$ville=$ville;
        $this->$adresse=$adresse;
        $this->$dateCreation=$dateCreation;
        $this->$dateAcces=$dateAcces;
        $this->$tempsAcces=$tempsAcces;
    }

    /**
     * @return mixed
     */
    public function getUrlphoto()
    {
        return $this->urlphoto;
    }

    /**
     * @param mixed $urlphoto
     */
    public function setUrlphoto($urlphoto)
    {
        $this->urlphoto = $urlphoto;
    }

    /**
     * @return mixed
     */
    public function getVille()
    {
        return $this->ville;
    }

    /**
     * @param mixed $ville
     */
    public function setVille($ville)
    {
        $this->ville = $ville;
    }

    /**
     * @return mixed
     */
    public function getPrenom()
    {
        return $this->prenom;
    }

    /**
     * @param mixed $prenom
     */
    public function setPrenom($prenom)
    {
        $this->prenom = $prenom;
    }

    /**
     * @return mixed
     */
    public function getNom()
    {
        return $this->nom;
    }

    /**
     * @param mixed $nom
     */
    public function setNom($nom)
    {
        $this->nom = $nom;
    }

    /**
     * @return mixed
     */
    public function getAdresse()
    {
        return $this->adresse;
    }

    /**
     * @param mixed $adresse
     */
    public function setAdresse($adresse)
    {
        $this->adresse = $adresse;
    }

    /**
     * @return mixed
     */
    public function getDateAcces()
    {
        return $this->dateAcces;
    }

    /**
     * @param mixed $dateAcces
     */
    public function setDateAcces($dateAcces)
    {
        $this->dateAcces = $dateAcces;
    }

    /**
     * @return mixed
     */
    public function getDateCreation()
    {
        return $this->dateCreation;
    }

    /**
     * @param mixed $dateCreation
     */
    public function setDateCreation($dateCreation)
    {
        $this->dateCreation = $dateCreation;
    }

    /**
     * @return mixed
     */
    public function getEmail()
    {
        return $this->email;
    }

    /**
     * @param mixed $email
     */
    public function setEmail($email)
    {
        $this->email = $email;
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
    public function getIdUtilisateur()
    {
        return $this->idUtilisateur;
    }

    /**
     * @param mixed $idUtilisateur
     */
    public function setIdUtilisateur($idUtilisateur)
    {
        $this->idUtilisateur = $idUtilisateur;
    }

    /**
     * @return mixed
     */
    public function getMdp()
    {
        return $this->mdp;
    }

    /**
     * @param mixed $mdp
     */
    public function setMdp($mdp)
    {
        $this->mdp = $mdp;
    }

    /**
     * @return mixed
     */
    public function getTempsAcces()
    {
        return $this->tempsAcces;
    }

    /**
     * @param mixed $tempsAcces
     */
    public function setTempsAcces($tempsAcces)
    {
        $this->tempsAcces = $tempsAcces;
    }
}