<?php
/**
 * Created by PhpStorm.
 * User: touir
 * Date: 15/04/2018
 * Time: 13:53
 */

include_once "utils/mysql_db_manager.php";

$dbm = new mysql_db_manager();

$dbm->select("select * from utilisateur");
