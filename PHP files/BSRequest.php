<?php
    $con = mysqli_connect("localhost", "Dummy_user", "Dummy_passwd", "Dummy_DB");
    
    
    $username = $_POST["username"];
    $dob = $_POST["dob"];
    $gender = $_POST["gender"];
    $height = $_POST["height"];
    $weight = $_POST["weight"];
    $wristCir = $_POST["wristCir"];
$basicdone = "1";

    $statements = mysqli_prepare($con, "UPDATE Account SET basicDone= ? ,gender= ? ,weight= ? ,height= ? ,wristcir= ?  WHERE username = ? AND dob = ?");
    mysqli_stmt_bind_param($statements, "ssiiiss", $basicdone , $gender, $weight ,$height, $wristCir, $username,  $dob);
    mysqli_stmt_execute($statements);

 $statement = mysqli_prepare($con, "SELECT * FROM Account WHERE username = ? AND dob= ?");
    mysqli_stmt_bind_param($statement, "ss", $username, $dob);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userID, $username, $email,  $password, $dob, $basicDone, $gender, $weight, $height, $wristCir);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;  
        $response["username"] = $username;
		$response["email"] = $email;
		$response["dob"] = $dob;
		$response["basicDone"] = $basicDone;
		$response["gender"] = $gender;
		$response["weight"] = $weight;
		$response["height"] = $height;
		$response["wristCir"] = $wristCir;

    }

    echo json_encode($response);
?>