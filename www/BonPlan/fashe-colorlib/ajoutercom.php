<?php
session_start();

include('../Entities/Config.php');
include('../Entities/Commentaire.php');

$c = new Config();

$conn = $c->getConnexion();

$com = new Commentaire();

$com->setIdClient($_POST['user']);
$com->setIdPlan($_POST['plan']);
$com->setTexte($_POST['commentaire']);

$com->add($conn);

header("Location: single.php?id=".$_POST['plan']);