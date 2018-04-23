<?php
class config
{
    function getConnexion()
    {
        $servername = "localhost";
        $username ="root";
        $dbname= "1cinfo01pi";
        $password ="";
        $conn = new PDO("mysql:host=$servername;
		dbname=$dbname",$username,$password);
        return $conn;
    }
}