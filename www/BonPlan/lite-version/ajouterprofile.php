<?php
session_start();

include("../Entities/Config.php");
include("../Entities/utilisateur.php");

$c = new Config();

$conn = $c->getConnexion();

$SALT = "J5hTeq";
$password = $_POST['mdp'];
$encrypted_password = sha1($password.$SALT);

$user = new utilisateur();
$user->setPrenom($_POST['prenom']);
$user->setAdresse($_POST['adresse']);
$user->setEmail($_POST['email']);
$user->setIdGroup($_POST['group']);
$user->setMdp($encrypted_password);
$user->setNom($_POST['nom']);
$user->setPhoto("test");
$user->setVille($_POST['ville']);

$user->add($conn);

if($user->getIdGroup() == 1){
    $_SESSION['connecter'] = $user->login($conn, $user->getEmail(), $user->getMdp());

    header("Location: plans.php");
}else{
    header("Location: profiles.php");
}

