<?php

/**
 * Created by PhpStorm.
 * User: Mohamed Ali
 * Date: 21/04/2018
 * Time: 22:08
 */
class utilisateur
{
    private $idUtilisateur;
    private $idGroup;
    private $nom;
    private $prenom;
    private $adresse;
    private $email;
    private $mdp;
    private $ville;
    private $photo;

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
    public function getIdGroup()
    {
        return $this->idGroup;
    }

    /**
     * @param mixed $idGroup
     */
    public function setIdGroup($idGroup)
    {
        $this->idGroup = $idGroup;
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
    public function getPhoto()
    {
        return $this->photo;
    }

    /**
     * @param mixed $photo
     */
    public function setPhoto($photo)
    {
        $this->photo = $photo;
    }

    public function add($conn){
        $req = "INSERT INTO `utilisateur` (`idGroupe`, `mdp`, `email`, `nom`, `prenom`, `urlphoto`, `ville`, `adresse`, `dateCreation`, `dateAcces`, `tempsAcces`, `isBanned`) VALUES ('".$this->getIdGroup()."', '".$this->getMdp()."', '".$this->getEmail()."', '".$this->getNom()."', '".$this->getPrenom()."', 'photo', '".$this->getVille()."', '".$this->getAdresse()."', CURRENT_TIMESTAMP, NULL, NULL, '0');";
        $conn->exec($req);
    }

    public function getAll($conn){

        $req = "select * from utilisateur";

        return $conn->query($req);
    }

    public function bloquer($id, $etat, $conn){
        $req = "UPDATE `utilisateur` SET `isBanned` = '".$etat."' WHERE `utilisateur`.`idUtilisateur` = ".$id.";";
        $conn->exec($req);
    }

    public function login($conn, $email, $password){
        $req = "SELECT * FROM `utilisateur` WHERE `email` = '".$email."' And `mdp` = '".$password."';";

        $rows = $conn->query($req);

        foreach($rows as $row){
            return $row;
        }
    }

}