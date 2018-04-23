<?php
session_start();
include("../Entities/Config.php");
include("../Entities/Plan.php");

$c = new Config();

$conn = $c->getConnexion();

$plan = new Plan();

$plan->changerStatut($conn, $_GET['id'], 1);

header("Location: plans.php");

