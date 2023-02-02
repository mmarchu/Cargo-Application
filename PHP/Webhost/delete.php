<?php

    $connection = mysqli_connect("localhost","id18828570_surasee","Suwannapal711@gmail.com","id18828570_warehouse_db");
    
     $id = $_POST["id"];
     
     $sql = "DELETE FROM product WHERE id='$id'";
     
     $result = mysqli_query($connection,$sql);
     
     if($result){
         echo "Data Deleted";
        
     }
     else{
         echo "Failed";
     }
     mysqli_close($connection);
     


?>