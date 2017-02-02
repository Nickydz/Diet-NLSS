<?php
    $conn = mysqli_connect("localhost", "Dummy_user", "Dummy_passwd", "Dummy_DB");
    
    
    $carbohydrate_m = $_POST["carbs_m"];
    $protein_m = $_POST["protein_m"];
    $fat_m = $_POST["fat_m"];
    $carbohydrate_s = $_POST["carbs_s"];
    $protein_s = $_POST["protein_s"];
    $fat_s = $_POST["fat_s"];

$carbs = $carbohydrate_m - 100;
$carbl = $carbohydrate_m + 100;
$prots = $protein_m - 100;
$protl = $protein_m + 100;
$fats = $fat_m - 100;
$fatl = $fat_m + 100;

$sql = "SELECT * FROM Breakfast WHERE Carbohydrates between $carbs and $carbl AND Proteins between $prots and $protl AND Fats between $fats and $fatl";
$result = mysqli_query($conn, $sql);
 $response = array();
$i = -1;
if (mysqli_num_rows($result) > 0) {
    // output data of each row
    while($row = mysqli_fetch_assoc($result)) {
           $i++;
        $response[$i]=$row["Name"];
    }
$response[$i++]= "endbf";
} else {
    echo "Error";
}

$sql = "SELECT * FROM Lunch WHERE Carbohydrates between $carbs and $carbl AND Proteins between $prots and $protl AND Fats between $fats and $fatl";
$result = mysqli_query($conn, $sql);

if (mysqli_num_rows($result) > 0) {
    // output data of each row
    while($row = mysqli_fetch_assoc($result)) {
        $response[$i]=$row["Name"];   
          $i++;        
    }
$response[$i++]= "endln";
} else {
    echo "Error";
}

$sql = "SELECT * FROM Dinner WHERE Carbohydrates between $carbs and $carbl AND Proteins between $prots and $protl AND Fats between $fats and $fatl";
$result = mysqli_query($conn, $sql);

if (mysqli_num_rows($result) > 0) {
    // output data of each row
    while($row = mysqli_fetch_assoc($result)) {
        $response[$i]=$row["Name"];   
          $i++;        
    }
$response[$i++]= "enddn";
} else {
    echo "Error";
}


$carbs = $carbohydrate_s - 100;
$carbl = $carbohydrate_s + 100;
$prots = $protein_s - 100;
$protl = $protein_s + 100;
$fats = $fat_s - 100;
$fatl = $fat_s + 100;

$sql = "SELECT * FROM Snacks WHERE Carbohydrates between $carbs and $carbl AND Proteins between $prots and $protl AND Fats between $fats and $fatl";
$result = mysqli_query($conn, $sql);

if (mysqli_num_rows($result) > 0) {
    // output data of each row
    while($row = mysqli_fetch_assoc($result)) {
        $response[$i]=$row["Name"];   
          $i++;        
    }
$response[$i++]= "endsn";
} else {
    echo "Error";
}

$response["success"] = true;
echo json_encode($response);