<?php
session_start();

include("../Entities/Config.php");
include("../Entities/utilisateur.php");

$c = new Config();

$conn = $c->getConnexion();
$user = new utilisateur();
$user->bloquer($_GET['id'], $_GET['etat'], $conn);

header("Location: profiles.php");
