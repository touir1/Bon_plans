<?php
/**
 * Created by PhpStorm.
 * User: SadfiAmine
 * Date: 15/04/2018
 * Time: 12:14
 */

class Categorie
{
    private  $idCategorie;
    private $titre;
    private $urlphoto;

    function __construct()
    {
    }
    function __construct1($idCategorie,$titre,$urlphoto)
    {$this->idCategorie=$idCategorie;
     $this->$titre=$titre;
     $this->$urlphoto=$urlphoto;
    }
    function __construct2($titre,$urlphoto)
    {
        $this->$titre=$titre;
        $this->$urlphoto=$urlphoto;
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
}