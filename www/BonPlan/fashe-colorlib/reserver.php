<?php
session_start();

include('../Entities/Config.php');
include('../Entities/Reservation.php');
include('../Entities/Plan.php');

$c = new Config();
$p =new Plan();

$conn = $c->getConnexion();

$res = new Reservation();
$plan = $p->getOne($conn, $_POST['plan']);

$res->setIdPlan($_POST['plan']);
$res->setIdClient($_POST['client']);
$res->setNbPlace($_POST['qte']);

$nv = $plan[9] - $_POST['qte'];

$res->reserver($conn, $nv);

header("Location: single.php?id=".$_POST['plan']);

echo($nv);