<?php
session_start();
include("../Entities/Config.php");
include("../Entities/Plan.php");

$c = new Config();

$conn = $c->getConnexion();

$p = new Plan();

$p->setIdAnnonceur($_SESSION['connecter'][0]);
$p->setIdCategorie($_POST['idCategorie']);
$p->setTitre($_POST['titre']);
$p->setDateDebut($_POST['dd']);
$p->setDateFin($_POST['df']);
$p->setDescription($_POST['description']);
$p->setNbPlaceTotal($_POST['qte']);
$p->setNbPlaceDispo($_POST['qte']);
$p->setPrixInitial($_POST['prixInitial']);
$p->setPrixPromo($_POST['prixPromo']);
$p->setUrlPhote("test");

$p->add($conn);
echo("idA : ".$p->getIdAnnonceur());
echo(" | idC : ".$p->getIdCategorie());
echo(" | titre : ".$p->getTitre());
echo(" | description : ".$p->getDescription());
echo(" | datedebut : ".$p->getDateDebut());
echo(" | datefin : ".$p->getDateFin());
echo(" | quantite : ".$p->getNbPlaceTotal());
echo(" | prix ini : ".$p->getPrixInitial());
echo(" | prix promo : ".$p->getPrixPromo());

header("Location: plans.php");