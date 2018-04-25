<?php
session_start();

include('../Entities/Config.php');
include('../Entities/Commentaire.php');

$c = new Config();

$conn = $c->getConnexion();

$com = new Commentaire();

$com->signaler($conn, $_GET['id']);

header('Location: ' . $_SERVER['HTTP_REFERER']);