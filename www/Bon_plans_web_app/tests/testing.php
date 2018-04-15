<?php
/**
 * Created by PhpStorm.
 * User: touir
 * Date: 15/04/2018
 * Time: 13:53
 */

include_once "../utils/mysql_db_manager.php";
include_once "../Entity/Utilisateur.php";
include_once "../services/implementation/CategorieImpl.php";

$dbm = new mysql_db_manager();

print_r($dbm->select("select * from utilisateur"));

$utilisateur = new Utilisateur();

$method = 'setNom';
$utilisateur->$method('test');

print_r($utilisateur);

$categorieService = new CategorieImpl();

print_r($categorieService->selectAll());