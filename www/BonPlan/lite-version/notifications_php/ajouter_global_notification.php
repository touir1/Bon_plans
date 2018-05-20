<?php
/**
 * Created by PhpStorm.
 * User: touir
 * Date: 20/05/2018
 * Time: 05:20
 */

session_start();
$conn = null;

if(isset($_SESSION['connecter'])) {
    include_once("../../Entities/Config.php");
    include_once("../../Entities/notification.php");

    $c = new Config();

    $conn = $c->getConnexion();
    $notification = new Notification();

    error_log(json_decode($_POST));

    $notification->addGlobalNotification($conn,$_POST['message']);
}