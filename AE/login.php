<?php 
require_once "init.php";

$username = $_POST["username"];
$password = $_POST["password"];

$queryy = $DBcon->query("SELECT id_user,name,email FROM user_info WHERE username = '$username' AND password = '$password'");

$count=$queryy->num_rows;

$response = array();

if ($count == 0) {
	$code = "login_failed";
	$message = "User not found..";
	array_push($response, array("code" => $code,"message"=>$message));
	echo json_encode($response);
} else {
	$row = mysqli_fetch_row($queryy);	
	$id_user = $row[0];
	$name = $row[1];
	$email = $row[2];
	$code = "login_success";
	array_push($response, array("code" => $code,"id_user"=>$id_user,"name"=>$name,"email"=>$email));
	echo json_encode($response);
}
mysqli_close($DBcon);

?>