<?php
  
    $file_path = "/files";
     
    $file_path = $file_path . "acd.mp4";
    if(move_uploaded_file($_FILES['uploaded_file']['tmp_name'], $file_path)) {
        $response="Success";
	echo json_encode($response);
    } else{
		$response="failure";
	   echo json_encode($response);
    }