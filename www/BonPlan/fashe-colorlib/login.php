<?php
    session_start();
/**
 * Created by PhpStorm.
 * User: Mohamed Ali
 * Date: 23/04/2018
 * Time: 10:29
 */
    include('../Entities/Config.php');
    include('../Entities/utilisateur.php');

    $c = new Config();

    $conn = $c->getConnexion();

    $ut = new utilisateur();

    $SALT = "J5hTeq";
    $password = $_POST['mdp'];
    $encrypted_password = sha1($password.$SALT);

    $test = $ut->login($conn, $_POST['email'], $encrypted_password);

    if($test){
        $_SESSION['connecter'] = $test;
        header("Location: ../lite-version/plans.php");
        if($test[12] == 1){
            header("Location: ../lite-version/blocker.html");
        }
    }else{
        header("Location: page1.php");
    }