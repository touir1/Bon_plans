<?php
    include("../Entities/Config.php");
    include("../Entities/Categorie.php");

    $c = new Config();

    $conn = $c->getConnexion();

    $cat = new Categorie();

    $cat->setTitre($_POST['titre']);
    $cat->setUrlPhoto("test");

    $cat->add($conn);

    header("Location: categories.php");