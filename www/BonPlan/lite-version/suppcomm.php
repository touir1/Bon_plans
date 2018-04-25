<?php
/**
 * Created by PhpStorm.
 * User: Mohamed Ali
 * Date: 24/04/2018
 * Time: 00:06
 */

    include("../Entities/Config.php");
    include("../Entities/Commentaire.php");

    $c = new Config();

    $conn = $c->getConnexion();

    $commentaire = new Commentaire();

    $commentaire->delete($conn, $_GET['id']);

    header("Location: plans.php");