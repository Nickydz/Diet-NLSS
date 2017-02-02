<?php
    $con = mysqli_connect("localhost", "Dummy_user", "Dummy_passwd", "Dummy_DB");
    
    $username = $_POST["username"];
    $password = $_POST["password"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM Account WHERE username = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $username, $password);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userID, $username, $email,  $password, $dob, $basicDone,  $gender, $weight, $height, $wristcir);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;  
        $response["username"] = $username;
		$response["email"] = $email;
        $response["password"] = $password;
		$response["dob"] = $dob;
		$response["basicDone"] = $basicDone;
    }
    
    echo json_encode($response);
?>