<?php
if(isset($_POST['name']) && isset($_POST['username']) && isset($_POST['password'])){

    require_once "conn.php";
    require_once "validate.php";

    $name = validate($_POST['name']);
    $username = validate($_POST['username']);
    $password = validate($_POST['password']);
    $sql = "insert into users values('','$name','$username','" . md5($password) . "')";

    if(!$conn->query($sql)){
        echo "failure";
    }else{
        echo "success";
    }
}
?>