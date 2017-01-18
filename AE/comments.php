<?php 
require_once "init.php";

$id = $_POST["id_comments"];

$sql = "SELECT id_comment,textcomment,id_user FROM comment WHERE id_products LIKE '".$id."'";



    $result = mysqli_query($DBcon, $sql) or die("Error in Selecting " . mysqli_error($DBcon));

    $emparray = array();
    while($row =mysqli_fetch_assoc($result))
    {
        $emparray[] = $row;
    }
    echo json_encode($emparray);

    mysqli_close($DBcon);
?>