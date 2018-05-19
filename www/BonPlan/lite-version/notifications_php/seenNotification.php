<?php
/**
 * Created by PhpStorm.
 * User: touir
 * Date: 19/05/2018
 * Time: 17:11
 */
session_start();

include_once("../../Entities/Config.php");
include_once("../../Entities/Notification.php");

$c = new Config();

$conn = $c->getConnexion();
$notification = new Notification();
$notification->changeNotificationAsSeen($conn,$_GET['id']);

