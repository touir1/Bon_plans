<?php
session_start();

include("../Entities/Config.php");
include("../Entities/utilisateur.php");

$c = new Config();

$conn = $c->getConnexion();

$SALT = "J5hTeq";
$password = $_POST['mdp'];
$encrypted_password = sha1($password.$SALT);


$target_dir = "uploads/";
$target_file = $target_dir . basename($_FILES["fileToUpload"]["name"]);
$uploadOk = 1;
$imageFileType = strtolower(pathinfo($target_file,PATHINFO_EXTENSION));
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


$user = new utilisateur();
$user->setPrenom($_POST['prenom']);
$user->setAdresse($_POST['adresse']);
$user->setEmail($_POST['email']);
$user->setIdGroup($_POST['group']);
$user->setMdp($encrypted_password);
$user->setNom($_POST['nom']);
$user->setPhoto($target_file);
$user->setVille($_POST['ville']);

$user->add($conn);

if($user->getIdGroup() == 1){
    $_SESSION['connecter'] = $user->login($conn, $user->getEmail(), $user->getMdp());

    header("Location: plans.php");
}else{
    header("Location: profiles.php");
}

