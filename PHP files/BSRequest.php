<?php
    $con = mysqli_connect("localhost", "Dummy_user", "Dummy_passwd", "Dummy_DB");
    
    
    $username = $_POST["username"];
    $prommeal = $_POST["prommeal"];
    $noofmeal = $_POST["noofmeal"];
    $height = $_POST["height"];
    $prefdrink = $_POST["prefdrink"];
    $activitylevel = $_POST["activitylevel"];
$advancedone = "1";

    $statements = mysqli_prepare($con, "UPDATE Account SET prommeal= ? ,noofmeal= ? ,prefdrink= ? ,activitylevel= ?  WHERE username = ? AND height = ?");
    mysqli_stmt_bind_param($statements, "sisssi", $prommeal , $noofmeal, $prefdrink, $activitylevel, $username,  $height);
    mysqli_stmt_execute($statements);

 $statement = mysqli_prepare($con, "SELECT * FROM Account WHERE username = ? AND height= ?");
    mysqli_stmt_bind_param($statement, "ss", $username, $height);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $userID, $username, $email,  $password, $dob, $basicDone, $gender, $weight, $height, $wristCir,  $prommeal , $noofmeal, $prefdrink, $activitylevel);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;  
        $response["username"] = $username;
		$response["email"] = $email;
		$response["dob"] = $dob;
		$response["advancedone"] = $advancedone;

    }

    echo json_encode($response);
?>