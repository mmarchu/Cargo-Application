<?php

    $connection = mysqli_connect("localhost","id18828570_surasee","Suwannapal711@gmail.com","id18828570_warehouse_db");
    
     $id = $_POST["id"];
     $name = $_POST["name"];
     $position = $_POST["position"];
     $amount = $_POST["amount"];
     
     $sql = "UPDATE product SET  name = '$name', position = '$position', amount = '$amount' WHERE id = '$id' ";
     
     $result = mysqli_query($connection,$sql);
     
     if($result){
         echo "Data Updated";
        
     }
     else{
         echo "Failed";
     }
     mysqli_close($connection);
     
        
?>