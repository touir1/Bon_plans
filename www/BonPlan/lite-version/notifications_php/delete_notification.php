<?php
/**
 * Created by PhpStorm.
 * User: touir
 * Date: 20/05/2018
 * Time: 02:26
 */

session_start();
$conn = null;

if(isset($_SESSION['connecter'])) {
    include_once("../../Entities/Config.php");
    include_once("../../Entities/notification.php");

    $c = new Config();

    $conn = $c->getConnexion();
    $notification = new Notification();

    $notification->delete($conn, $_GET['id']);
}