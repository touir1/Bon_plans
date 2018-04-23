<?php
session_start();

session_start();

include ("../Entities/Config.php");
include ("../Entities/utilisateur.php");

$c = new Config();

$conn = $c->getConnexion();

$user = new Utilisateur();
/*
$user->setPrenom("prenom");
$user->setAdresse("Tunis");
$user->setEmail("prenom@email.com");
$user->setIdGroup(1);
$user->setMdp("secret");
$user->setNom("nom");
$user->setPhoto("test");
$user->setVille("Tunis");
*/

$_SESSION['utilisateur'] = $user->login($conn, "artan@email.com", "secret");

include("tete.php");
?>
    <h1><?php
        if($_SESSION['utilisateur'][2]){

            echo ($_SESSION['utilisateur'][2]);
        }
        ?></h1>
<?php
include("pied.html");
session_destroy();
?>