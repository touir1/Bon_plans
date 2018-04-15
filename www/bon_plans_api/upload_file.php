<?php
try {
	$target_dir = "uploads/";
	$target_file = $target_dir . basename($_FILES["fileToUpload"]["name"]);
	$uploadOk = 1;
	$imageFileType = strtolower(pathinfo($target_file,PATHINFO_EXTENSION));
	// Check if image file is a actual image or fake image
	/*
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
	*/
	// Check if file already exists
	if (file_exists($target_file)) {
		echo "Sorry, file already exists.";
		error_log("Sorry, file already exists.");
		$uploadOk = 0;
	}
	// Check file size
	if ($_FILES["fileToUpload"]["size"] > 50000000) {
		echo "Sorry, your file is too large.";
		error_log("Sorry, your file is too large.");
		$uploadOk = 0;
	}
	// Allow certain file formats
	if($imageFileType != "jpg" && $imageFileType != "png" && $imageFileType != "jpeg"
	&& $imageFileType != "gif" && $imageFileType != "pdf") {
		echo "Sorry, only JPG, JPEG, PNG & GIF & PDF files are allowed.";
		error_log("Sorry, only JPG, JPEG, PNG & GIF & PDF files are allowed.");
		$uploadOk = 0;
	}
	// Check if $uploadOk is set to 0 by an error
	if ($uploadOk == 0) {
		http_response_code(500);
		echo "Sorry, your file was not uploaded.";
		error_log("Sorry, your file was not uploaded.");
		
	// if everything is ok, try to upload file
	} else {
		if (move_uploaded_file($_FILES["fileToUpload"]["tmp_name"], $target_file)) {
			echo "The file ". basename( $_FILES["fileToUpload"]["name"]). " has been uploaded.";
		} else {
			http_response_code(500);
			echo "Sorry, there was an error uploading your file.";
			error_log("Sorry, there was an error uploading your file.");
			error_log("move_uploaded_file parameters: ".$_FILES["fileToUpload"]["tmp_name"].",".$target_file);
			foreach($_FILES["fileToUpload"] as $key => $value) {
				error_log("$key : $value");
			}
		}
	}
} catch (Exception $e) {
    echo 'Exception reçue : ',  $e->getMessage(), "\n";
	error_log('Exception reçue : '.$e->getMessage()."\n");
	
}
?>