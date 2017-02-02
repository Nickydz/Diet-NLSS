<?php
    $con = mysqli_connect("localhost", "Dummy_user", "Dummy_passwd", "Dummy_DB");
    
    
    $username = $_POST["username"];
    $email = $_POST["email"];
    $password = $_POST["password"];
    $dob = $_POST["dob"];

    $statement = mysqli_prepare($con, "INSERT INTO `Account` (`user_id`, `username`, `email`, `password`, `dob`) VALUES (NULL, ?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "ssss", $username, $email, $password , $dob);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo "Successfully Registered";
?>