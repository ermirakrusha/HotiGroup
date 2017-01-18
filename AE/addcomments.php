<?php 
require_once "init.php";

$txt = $_POST["textcomment"];
$idp = $_POST["id_products"];
$idu = $_POST["id_user"];


$query = "INSERT INTO comment(textcomment,id_products,id_user) VALUES('$txt','$idp','$idu')";

$DBcon->query($query);

mysqli_close($DBcon);
?>