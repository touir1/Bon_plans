<?php
session_start();

include('../Entities/Config.php');
include('../Entities/Plan.php');

$c = new Config();

$conn = $c->getConnexion();

$com = new Plan();

$com->avis($conn, $_GET['id'], $_GET['avis']);

header('Location: ' . $_SERVER['HTTP_REFERER']);