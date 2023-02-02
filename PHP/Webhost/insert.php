<?php

    $connection = mysqli_connect("localhost","id18828570_surasee","Suwannapal711@gmail.com","id18828570_warehouse_db");
    
     $name = $_POST["name"];
     $position = $_POST["position"];
     $amount = $_POST["amount"];
     
     $sql = "INSERT INTO product(name,position,amount) VALUES ('$name','$position','$amount')";
     
     $result = mysqli_query($connection,$sql);
     
     if($result){
         echo "Data Inserted";
        
     }
     else{
         echo "Failed";
     }
     mysqli_close($connection);
     
          
    
    


?>