<?php
session_start();

include("../Entities/Config.php");
include("../Entities/Plan.php");
include_once("utils.php");

$utils = new utils();

$c = new Config();

$conn = $c->getConnexion();

$p = new Plan();

$target_dir = "../../bon_plans_api/uploads/";
$random_str = $utils->generateRandomString(20);
$uploadOk = 1;
$imageFileType = strtolower(pathinfo(basename($_FILES["fileToUpload"]["name"]),PATHINFO_EXTENSION));
$target_file = $target_dir . $random_str . '.' . $imageFileType;
// Check if image file is a actual image or fake image
if(isset($_POST["submit"])) {
    $check = getimagesize($_FILES["fileToUpload"]["tmp_name"]);
    if($check !== false) {
        echo "File is an image - " . $check["mime"] . ".";
        $uploadOk = 1;
    } else {
        echo "File is not an image.";
        $uploadOk = 0;
    }
}
// Check if file already exists
if (file_exists($target_file)) {
    echo "Sorry, file already exists.";
    $uploadOk = 0;
}
// Check file size
if ($_FILES["fileToUpload"]["size"] > 500000) {
    echo "Sorry, your file is too large.";
    $uploadOk = 0;
}
// Allow certain file formats
if($imageFileType != "jpg" && $imageFileType != "png" && $imageFileType != "jpeg"
    && $imageFileType != "gif" ) {
    echo "Sorry, only JPG, JPEG, PNG & GIF files are allowed.";
    $uploadOk = 0;
}
// Check if $uploadOk is set to 0 by an error
if ($uploadOk == 0) {
    echo "Sorry, your file was not uploaded.";
// if everything is ok, try to upload file
} else {
    if (move_uploaded_file($_FILES["fileToUpload"]["tmp_name"], $target_file)) {
        echo "The file ". basename( $_FILES["fileToUpload"]["name"]). " has been uploaded.";
    } else {
        echo "Sorry, there was an error uploading your file.";
    }
}
$p->setTitre($_POST['titre']);
$p->setDescription($_POST['description']);
$p->setUrlPhote($target_file);
$p->setPrixInitial($_POST['prixInitial']);
$p->setPrixPromo($_POST['prixPromo']);;
$p->setNbPlaceTotal($_POST['qte']);
$p->setDateDebut($_POST['dd']);
$p->setDateFin($_POST['df']);
$p->setNbPlaceDispo($_POST['qte']);
$p->setIdAnnonceur($_SESSION['connecter'][0]);
$p->setIdCategorie($_POST['idCategorie']);

$p->add($conn);

echo("idA : ".$p->getIdAnnonceur());
echo(" | idC : ".$p->getIdCategorie());
echo(" | titre : ".$p->getTitre());
echo(" | description : ".$p->getDescription());
echo(" | datedebut : ".$p->getDateDebut());
echo(" | datefin : ".$p->getDateFin());
echo(" | quantite : ".$p->getNbPlaceTotal());
echo(" | prix ini : ".$p->getPrixInitial());
echo(" | prix promo : ".$p->getPrixPromo());
echo(" | prix promo : ".$p->getUrlPhote());

header("Location: plans.php");