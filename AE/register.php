<?php 
require_once "init.php";

$name = $_POST["name"];
$lastname = $_POST["lastname"];
$email = $_POST["email"];
$username = $_POST["username"];
$password = $_POST["password"];


$response = array();

$check_email = $DBcon->query("SELECT * FROM user_info WHERE email = '$email'");
$count=$check_email->num_rows;

if($count == 0)
{
	$query = "INSERT INTO user_info(name,lastname,email,username,password) VALUES('$name','$lastname','$email','$username','$password')";

if ($DBcon->query($query)) {
  	$code = "reg_success";
	$message = "Thank you for register with us. Now you can login.";
	array_push($response, array("code" => $code, "message"=>$message));
	echo json_encode($response);
  }else {
   	$code = "reg_failed";
	$message = "User already exist ......";
	array_push($response, array("code" => $code, "message"=>$message));
	echo json_encode($response);
  }	
}
else 
{
	$code = "reg_failed";
	$message = "General Exception ......";
	array_push($response, array("code" => $code, "message"=>$message));
	echo json_encode($response);
	
	} 

mysqli_close($DBcon);

?>