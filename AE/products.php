<?php 
require_once "init.php";

$sql = "SELECT * FROM products";


    $result = mysqli_query($DBcon, $sql) or die("Error in Selecting " . mysqli_error($DBcon));

    $emparray = array();
    while($row =mysqli_fetch_assoc($result))
    {
        $emparray[] = $row;
    }
    echo json_encode($emparray);

    mysqli_close($DBcon);
?>